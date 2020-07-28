package com.itheima.service.store;

import com.itheima.domain.store.Question;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

public class PoiTestWithData {
    @Test
    public void PoiTestTitleSubject() throws Exception {
        // 1.获取工作薄
        Workbook wb = new XSSFWorkbook();
        // 2.从工作薄中创建sheet
        Sheet s = wb.createSheet();
        // 3.从sheet中创建标题行,涉及到合并行
        Row row_1 = s.createRow(1);
        s.addMergedRegion(new CellRangeAddress(1, 1, 1, 12));// 合并列后,默认是左上的单元格
        // 创建单元格格式
        CellStyle cellStyle = wb.createCellStyle();
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);

        // 4.在行中创建单元格
        Cell cell_1_1 = row_1.createCell(1);
        cell_1_1.setCellValue("在线导出试题信息");
        // 设置单元格格式
        cell_1_1.setCellStyle(cellStyle);


        // 准备集合保存各列标题信息
        //制作表头
        String[] fields = {"题目ID","所属公司ID","所属目录ID","题目简介","题干描述",
                "题干配图","题目分析","题目类型","题目难度","是否经典题","题目状态","审核状态"};
        // 创建表头行
        Row row_2 = s.createRow(2);
        for (int i = 0; i < fields.length; i++) {
            Cell cell_2_tmp = row_2.createCell(1 + i);
            cell_2_tmp.setCellValue(fields[i]);
            cell_2_tmp.setCellStyle(cellStyle);
        }

        // 制作数据列
        CellStyle cs_field = wb.createCellStyle();
        cs_field.setAlignment(HorizontalAlignment.CENTER);
        cs_field.setBorderTop(BorderStyle.THIN);
        cs_field.setBorderBottom(BorderStyle.THIN);
        cs_field.setBorderLeft(BorderStyle.THIN);
        cs_field.setBorderRight(BorderStyle.THIN);

        // 准备测试数据
        List<Question> questionList = new ArrayList<>();
        Question qq = new Question();
        qq.setId("1");
        qq.setPicture("12");
        qq.setReviewStatus("13");
        qq.setAnalysis("14");
        qq.setCatalogId("15");
        qq.setCompanyId("16");
        qq.setDifficulty("17");
        qq.setIsClassic("18");
        qq.setRemark("19");
        qq.setState("21");
        qq.setSubject("31");
        qq.setType("41");
        questionList.add(qq);
        Question qqq = new Question();
        qqq.setId("1");
        qqq.setPicture("12");
        qqq.setReviewStatus("13");
        qqq.setAnalysis("14");
        qqq.setCatalogId("15");
        qqq.setCompanyId("16");
        qqq.setDifficulty("17");
        qqq.setIsClassic("18");
        qqq.setRemark("19");
        qqq.setState("21");
        qqq.setSubject("31");
        qqq.setType("41");
        questionList.add(qqq);

        int rowIndex = 0;
        for(Question q: questionList){
            int cell_index = 0;
            Row row_temp = s.createRow(3 + rowIndex++);
            Cell cell_data_1 = row_temp.createCell(1 + cell_index++);
            cell_data_1.setCellValue(q.getId());
            cell_data_1.setCellStyle(cs_field);

            Cell cell_data_2 = row_temp.createCell(1 + cell_index++);
            cell_data_2.setCellValue(q.getCompanyId());    //++
            cell_data_2.setCellStyle(cs_field);

            Cell cell_data_3 = row_temp.createCell(1 + cell_index++);
            cell_data_3.setCellValue(q.getCatalogId());    //++
            cell_data_3.setCellStyle(cs_field);

            Cell cell_data_4 = row_temp.createCell(1 + cell_index++);
            cell_data_4.setCellValue(q.getRemark());    //++
            cell_data_4.setCellStyle(cs_field);

            Cell cell_data_5 = row_temp.createCell(1 + cell_index++);
            cell_data_5.setCellValue(q.getSubject());    //++
            cell_data_5.setCellStyle(cs_field);

            Cell cell_data_6 = row_temp.createCell(1 + cell_index++);
            cell_data_6.setCellValue(q.getPicture());    //++
            cell_data_6.setCellStyle(cs_field);

            Cell cell_data_7 = row_temp.createCell(1 + cell_index++);
            cell_data_7.setCellValue(q.getAnalysis());    //++
            cell_data_7.setCellStyle(cs_field);

            Cell cell_data_8 = row_temp.createCell(1 + cell_index++);
            cell_data_8.setCellValue(q.getType());    //++
            cell_data_8.setCellStyle(cs_field);

            Cell cell_data_9 = row_temp.createCell(1 + cell_index++);
            cell_data_9.setCellValue(q.getDifficulty());    //++
            cell_data_9.setCellStyle(cs_field);

            Cell cell_data_10 = row_temp.createCell(1 + cell_index++);
            cell_data_10.setCellValue(q.getIsClassic());    //++
            cell_data_10.setCellStyle(cs_field);

            Cell cell_data_11 = row_temp.createCell(1 + cell_index++);
            cell_data_11.setCellValue(q.getState());    //++
            cell_data_11.setCellStyle(cs_field);

            Cell cell_data_12 = row_temp.createCell(1 + cell_index++);
            cell_data_12.setCellValue(q.getReviewStatus());    //++
            cell_data_12.setCellStyle(cs_field);
        }

        // 5.创建文件输出流
        FileOutputStream fos = new FileOutputStream(new File("test.xlsx"));
        // 6.写出到文件
        wb.write(fos);
        // 7.关闭资源
        fos.flush();
        fos.close();
        wb.close();
    }
}

package com.itheima.service.store;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.*;

public class PoiTest {
    @Test
    public void testExcelWrite() throws IOException {
        // 1.创建工作薄
        Workbook wb = new XSSFWorkbook();
        // 2.在工作簿的基础上创建工作表
        Sheet sheet985 = wb.createSheet("985");
        Sheet sheet211 = wb.createSheet("211");
        // 3.在工作表中创建行
        Row row = sheet985.createRow(1);
        // 4.在行中创建单元格
        Cell cell = row.createCell(1);
        // 5.给单元格设置数据
        cell.setCellValue("四川大学");
        // 6.创建字节输出流写出到文件中
        File file = new File("text.xlsx");
        OutputStream os = new FileOutputStream(file);
        wb.write(os);
        // 7.关闭资源
        os.close();
        wb.close();
    }

    @Test
    public void testExcelRead() throws IOException {
        // 1.获取工作簿
        Workbook wb = new XSSFWorkbook("text.xlsx");
        // 2.获取工作表
        Sheet sheet = wb.getSheet("985");
        // 3.获取指定行
        Row row = sheet.getRow(1);
        // 4.获取指定单元格
        Cell cell = row.getCell(1);
        // 5.获取指定单元格指定类型的值
        String stringCellValue = cell.getStringCellValue();
        System.out.println(stringCellValue);
        // 6.关闭工作薄
        wb.close();
    }
}

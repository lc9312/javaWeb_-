package com.itheima.service.store.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheima.dao.store.QuestionDao;
import com.itheima.domain.store.Question;
import com.itheima.factory.MapperFactory;
import com.itheima.service.store.QuestionService;
import com.itheima.utils.TransactionUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class QuestionServiceImpl implements QuestionService {
    @Override
    public void save(Question question,Boolean flag) {
        SqlSession sqlSession = null;
        try {
            // 1.获取sqlSession
            sqlSession = MapperFactory.getSqlSession();
            // 2.获取Dao
            QuestionDao mapper = MapperFactory.getMapper(sqlSession, QuestionDao.class);
            // 3.执行sql语句,设定id,创建日期,并返回结果
            String id = UUID.randomUUID().toString();
            Date date = new Date();
            question.setId(id);
            question.setCreateTime(date);
            // 4.若上传图片,则将id作为添加的图片的名称
            if(flag) {
                question.setPicture(id);
            }
            mapper.save(question);
            // 5.提交事务
            TransactionUtil.commit(sqlSession);
        } catch (Exception e) {
            if(sqlSession != null){
                TransactionUtil.rollback(sqlSession);
            }
            throw new RuntimeException(e);
        } finally {
            // 6.关闭资源
            if(sqlSession != null){
                TransactionUtil.close(sqlSession);
            }
        }
    }

    @Override
    public void update(Question question,Boolean flag) {
        SqlSession sqlSession = null;
        try {
            // 1.获取sqlSession
            sqlSession = MapperFactory.getSqlSession();
            // 2.获取Dao
            QuestionDao mapper = MapperFactory.getMapper(sqlSession, QuestionDao.class);
            // 若有文件上传,则设置picture属性值
            if(flag){
                question.setPicture(question.getId());
            }
            // 3.执行sql语句
            mapper.update(question);
            // 4.提交事务
            TransactionUtil.commit(sqlSession);
        } catch (Exception e) {
            if(sqlSession != null){
                TransactionUtil.rollback(sqlSession);
            }
            throw new RuntimeException(e);
        } finally {
            // 4.关闭资源
            if(sqlSession != null){
                TransactionUtil.close(sqlSession);
            }
        }
    }

    @Override
    public void delete(Question question) {
        SqlSession sqlSession = null;
        try {
            // 1.获取sqlSession
            sqlSession = MapperFactory.getSqlSession();
            // 2.获取Dao
            QuestionDao mapper = MapperFactory.getMapper(sqlSession, QuestionDao.class);
            // 3.执行sql语句
            mapper.delete(question);
            // 4.提交事务
            TransactionUtil.commit(sqlSession);
        } catch (Exception e) {
            if(sqlSession != null){
                TransactionUtil.rollback(sqlSession);
            }
            throw new RuntimeException(e);
        } finally {
            // 4.关闭资源
            if(sqlSession != null){
                TransactionUtil.close(sqlSession);
            }
        }
    }

    @Override
    public Question findById(String id) {
        SqlSession sqlSession = null;
        try {
            // 1.获取sqlSession
            sqlSession = MapperFactory.getSqlSession();
            // 2.获取Dao
            QuestionDao mapper = MapperFactory.getMapper(sqlSession,QuestionDao.class);
            // 3.执行sql,返回结果
            return  mapper.findById(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            // 4.关闭资源
            if(sqlSession != null){
                TransactionUtil.close(sqlSession);
            }
        }
    }

    @Override
    public List<Question> findAll() {
        SqlSession sqlSession = null;
        try {
            // 1.获取sqlSession
            sqlSession = MapperFactory.getSqlSession();
            // 2.获取Dao
            QuestionDao mapper = MapperFactory.getMapper(sqlSession, QuestionDao.class);
            // 3.执行sql语句,并返回结果
            return mapper.findAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            // 4.关闭资源
            if(sqlSession != null){
                TransactionUtil.close(sqlSession);
            }
        }
    }

    @Override
    public PageInfo findAll(Integer page, Integer pagesize) {
        SqlSession sqlSession = null;
        try {
            // 1.获取sqlSession
            sqlSession = MapperFactory.getSqlSession();
            // 2.获取Dao
            QuestionDao mapper = MapperFactory.getMapper(sqlSession, QuestionDao.class);
            // 3.执行sql语句,并返回结果
            PageHelper.startPage(page,pagesize);
            List<Question> all = mapper.findAll();
            return new PageInfo(all);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            // 4.关闭资源
            if(sqlSession != null){
                TransactionUtil.close(sqlSession);
            }
        }
    }

    @Override
    public ByteArrayOutputStream getReport() throws IOException {
        // 首先获取所有的Question数据
        SqlSession sqlSession = null;
        List<Question> questionList = null;
        try {
            // 1.获取sqlSession
            sqlSession = MapperFactory.getSqlSession();
            // 2.获取Dao
            QuestionDao mapper = MapperFactory.getMapper(sqlSession, QuestionDao.class);
            // 3.执行sql语句,并返回结果
            questionList = mapper.findAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            // 4.关闭资源
            if(sqlSession != null){
                TransactionUtil.close(sqlSession);
            }
        }

        // 对数据进行导出
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
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        // 6.写出到文件
        wb.write(bos);
        // 7.关闭资源
        wb.close();
        return bos;
    }
}

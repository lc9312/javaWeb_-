package com.itheima.service.store.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheima.dao.store.QuestionDao;
import com.itheima.domain.store.Question;
import com.itheima.factory.MapperFactory;
import com.itheima.service.store.QuestionService;
import com.itheima.utils.TransactionUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class QuestionServiceImpl implements QuestionService {
    @Override
    public void save(Question question) {
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
            mapper.save(question);
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
    public void update(Question question) {
        SqlSession sqlSession = null;
        try {
            // 1.获取sqlSession
            sqlSession = MapperFactory.getSqlSession();
            // 2.获取Dao
            QuestionDao mapper = MapperFactory.getMapper(sqlSession, QuestionDao.class);
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
}

package com.itheima.service.store.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheima.dao.store.CourseDao;
import com.itheima.domain.store.Course;
import com.itheima.factory.MapperFactory;
import com.itheima.service.store.CourseService;
import com.itheima.utils.TransactionUtil;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.UUID;

public class CourseServiceImpl implements CourseService {
    @Override
    public void save(Course course) {
        SqlSession sqlSession = null;
        try {
            // 1.获取sqlSession
            sqlSession = MapperFactory.getSqlSession();
            // 2.获取Dao
            CourseDao mapper = MapperFactory.getMapper(sqlSession, CourseDao.class);
            // 3.执行sql语句,设定id,并返回结果
            String id = UUID.randomUUID().toString();
            course.setId(id);
            mapper.save(course);
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
    public void update(Course course) {
        SqlSession sqlSession = null;
        try {
            // 1.获取sqlSession
            sqlSession = MapperFactory.getSqlSession();
            // 2.获取Dao
            CourseDao mapper = MapperFactory.getMapper(sqlSession, CourseDao.class);
            // 3.执行sql语句
            mapper.update(course);
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
    public void delete(Course course) {
        SqlSession sqlSession = null;
        try {
            // 1.获取sqlSession
            sqlSession = MapperFactory.getSqlSession();
            // 2.获取Dao
            CourseDao mapper = MapperFactory.getMapper(sqlSession, CourseDao.class);
            // 3.执行sql语句
            mapper.delete(course);
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
    public Course findById(String id) {
        SqlSession sqlSession = null;
        try {
            // 1.获取sqlSession
            sqlSession = MapperFactory.getSqlSession();
            // 2.获取Dao
            CourseDao mapper = MapperFactory.getMapper(sqlSession,CourseDao.class);
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
    public List<Course> findAll() {
        SqlSession sqlSession = null;
        try {
            // 1.获取sqlSession
            sqlSession = MapperFactory.getSqlSession();
            // 2.获取Dao
            CourseDao mapper = MapperFactory.getMapper(sqlSession, CourseDao.class);
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
            CourseDao mapper = MapperFactory.getMapper(sqlSession, CourseDao.class);
            // 3.执行sql语句,并返回结果
            PageHelper.startPage(page,pagesize);
            List<Course> all = mapper.findAll();
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

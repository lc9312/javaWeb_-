package com.itheima.service.system.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheima.dao.system.DeptDao;
import com.itheima.domain.system.Dept;
import com.itheima.factory.MapperFactory;
import com.itheima.service.system.DeptService;
import com.itheima.utils.TransactionUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.UUID;

public class DeptServiceImpl implements DeptService {
    @Override
    public void save(Dept dept) {
        SqlSession sqlSession = null;
        try {
            // 1.获取sqlSession
            sqlSession = MapperFactory.getSqlSession();
            // 2.获取Dao
            DeptDao mapper = MapperFactory.getMapper(sqlSession, DeptDao.class);
            // 3.添加ID
            String uuid = UUID.randomUUID().toString();
            dept.setId(uuid);
            // 4.执行sql语句
            mapper.save(dept);
            // 5.提交事务
            TransactionUtil.commit(sqlSession);
        } catch (Exception e) {
            if(sqlSession != null){
                TransactionUtil.rollback(sqlSession);
            }
            throw new RuntimeException(e);
        } finally {
            // 6.关闭资源
            if( sqlSession != null){
                TransactionUtil.close(sqlSession);
            }
        }
    }

    @Override
    public void delete(Dept dept) {
        SqlSession sqlSession = null;
        try {
            // 1.获取sqlSession
            sqlSession = MapperFactory.getSqlSession();
            // 2.获取Dao
            DeptDao mapper = MapperFactory.getMapper(sqlSession, DeptDao.class);
            // 3.执行sql语句
            mapper.delete(dept);
            // 4.提交事务
            TransactionUtil.commit(sqlSession);
        } catch (Exception e) {
            if(sqlSession != null){
                TransactionUtil.rollback(sqlSession);
            }
            throw new RuntimeException(e);
        } finally {
            // 5.关闭资源
            if( sqlSession != null){
                TransactionUtil.close(sqlSession);
            }
        }
    }

    @Override
    public void update(Dept dept) {
        SqlSession sqlSession = null;
        try {
            // 1.获取sqlSession
            sqlSession = MapperFactory.getSqlSession();
            // 2.获取Dao
            DeptDao mapper = MapperFactory.getMapper(sqlSession, DeptDao.class);
            // 3.执行sql语句
            mapper.update(dept);
            // 4.提交事务
            TransactionUtil.commit(sqlSession);
        } catch (Exception e) {
            if(sqlSession != null){
                TransactionUtil.rollback(sqlSession);
            }
            throw new RuntimeException(e);
        } finally {
            // 5.关闭资源
            if( sqlSession != null){
                TransactionUtil.close(sqlSession);
            }
        }
    }

    @Override
    public List<Dept> findAll() {
        SqlSession sqlSession = null;
        try {
            // 1.获取sqlSession
            sqlSession = MapperFactory.getSqlSession();
            // 2.获取Dao
            DeptDao mapper = MapperFactory.getMapper(sqlSession, DeptDao.class);
            // 3.执行语句,返回结果
            return mapper.findAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            // 4.关闭资源
            if( sqlSession != null){
                TransactionUtil.close(sqlSession);
            }
        }
    }

    @Override
    public PageInfo findAll(Integer page, Integer pageSize) {
        SqlSession sqlSession = null;
        try {
            // 1.获取sqlSession
            sqlSession = MapperFactory.getSqlSession();
            // 2.获取Dao
            DeptDao mapper = MapperFactory.getMapper(sqlSession, DeptDao.class);
            // 3.执行语句,返回结果
            PageHelper.startPage(page,pageSize);
            List<Dept> depts = mapper.findAll();
            PageInfo pageInfo = new PageInfo(depts);
            return pageInfo;
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
    public Dept findById(String id) {
        SqlSession sqlSession = null;
        try {
            // 1.获取sqlSession
            sqlSession = MapperFactory.getSqlSession();
            // 2.获取Dao
            DeptDao mapper = MapperFactory.getMapper(sqlSession, DeptDao.class);
            // 3.执行sql语句,返回结果
            return mapper.findById(id);
        } catch (Exception e) {
           throw new RuntimeException(e);
        } finally {
            // 4.关闭资源
            if( sqlSession != null){
                TransactionUtil.close(sqlSession);
            }
        }
    }
}

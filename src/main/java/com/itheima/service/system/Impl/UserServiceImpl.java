package com.itheima.service.system.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheima.dao.system.UserDao;
import com.itheima.domain.system.User;
import com.itheima.factory.MapperFactory;
import com.itheima.service.system.UserService;
import com.itheima.utils.TransactionUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public class UserServiceImpl implements UserService {

    @Override
    public void save(User user) {
        SqlSession sqlSession = null;
        try {
            // 1.获取sqlSession
            sqlSession = MapperFactory.getSqlSession();
            // 2.获取Dao
            UserDao mapper = MapperFactory.getMapper(sqlSession, UserDao.class);
            // 3.给定user的ID值
            String id = UUID.randomUUID().toString();
            user.setId(id);
            // 4.执行sql语句
            mapper.save(user);
            // 5.提交事务
            TransactionUtil.commit(sqlSession);
        } catch (Exception e) {
            // 6.失败进行事务回滚
            if( sqlSession != null){
                TransactionUtil.rollback(sqlSession);
            }
            throw new RuntimeException(e);
        } finally {
        }
        // 7.关闭资源
        if( sqlSession != null){
            TransactionUtil.close(sqlSession);
        }
    }

    @Override
    public void delete(User user) {
        SqlSession sqlSession = null;
        try {
            // 1.获取sqlSession
            sqlSession = MapperFactory.getSqlSession();
            // 2.获取Dao
            UserDao mapper = MapperFactory.getMapper(sqlSession, UserDao.class);
            // 3.执行sql语句
            int delete = mapper.delete(user);
            System.out.println("删除成功!"+delete);
            // 4.提交事务
            TransactionUtil.commit(sqlSession);
        } catch (Exception e) {
            // 5.失败进行事务回滚
            if( sqlSession != null){
                TransactionUtil.rollback(sqlSession);
            }
            throw new RuntimeException(e);
        } finally {
        }
        // 6.关闭资源
        if( sqlSession != null){
            TransactionUtil.close(sqlSession);
        }
    }

    @Override
    public void update(User user) {
        SqlSession sqlSession = null;
        try {
            // 1.获取sqlSession
            sqlSession = MapperFactory.getSqlSession();
            // 2.获取Dao
            UserDao mapper = MapperFactory.getMapper(sqlSession, UserDao.class);
            // 3.执行sql语句
            mapper.update(user);
            // 4.提交事务
            TransactionUtil.commit(sqlSession);
        } catch (Exception e) {
            // 5.失败进行事务回滚
            if( sqlSession != null){
                TransactionUtil.rollback(sqlSession);
            }
            throw new RuntimeException(e);
        } finally {
        }
        // 6.关闭资源
        if( sqlSession != null){
            TransactionUtil.close(sqlSession);
        }
    }

    @Override
    public List<User> findAll() {
        SqlSession sqlSession = null;
        try {
            // 1.获取sqlSession
            sqlSession = MapperFactory.getSqlSession();
            // 2.获取Daod的mapper
            UserDao mapper = MapperFactory.getMapper(sqlSession, UserDao.class);
            // 3.执行sql语句返回结果
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
            // 2.获取Daod的mapper
            UserDao mapper = MapperFactory.getMapper(sqlSession, UserDao.class);
            // 3.执行sql语句
            PageHelper.startPage(page,pageSize);
            List<User> all = mapper.findAll();
            //  4.结果分页返回
            PageInfo pageInfo = new PageInfo(all);
            return pageInfo;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            // 5.关闭资源
            if( sqlSession != null){
                TransactionUtil.close(sqlSession);
            }
        }
    }

    @Override
    public User findById(String id) {
        SqlSession sqlSession = null;
        try {
            // 1.获取sqlSession
            sqlSession = MapperFactory.getSqlSession();
            // 2.获取Daod的mapper
            UserDao mapper = MapperFactory.getMapper(sqlSession, UserDao.class);
            // 3.执行sql语句
            return  mapper.findById(id);
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

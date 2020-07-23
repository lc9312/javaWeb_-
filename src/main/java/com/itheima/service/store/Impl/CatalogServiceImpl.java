package com.itheima.service.store.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheima.dao.store.CatalogDao;
import com.itheima.domain.store.Catalog;
import com.itheima.factory.MapperFactory;
import com.itheima.service.store.CatalogService;
import com.itheima.utils.TransactionUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.UUID;

public class CatalogServiceImpl implements CatalogService {
    @Override
    public void save(Catalog catalog) {
        SqlSession sqlSession = null;
        try {
            // 1.获取sqlSession
            sqlSession = MapperFactory.getSqlSession();
            // 2.获取Dao
            CatalogDao mapper = MapperFactory.getMapper(sqlSession, CatalogDao.class);
            // 3.执行sql语句,设定id,并返回结果
            String id = UUID.randomUUID().toString();
            catalog.setId(id);
            mapper.save(catalog);
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
    public void update(Catalog catalog) {
        SqlSession sqlSession = null;
        try {
            // 1.获取sqlSession
            sqlSession = MapperFactory.getSqlSession();
            // 2.获取Dao
            CatalogDao mapper = MapperFactory.getMapper(sqlSession, CatalogDao.class);
            // 3.执行sql语句
            mapper.update(catalog);
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
    public void delete(Catalog catalog) {
        SqlSession sqlSession = null;
        try {
            // 1.获取sqlSession
            sqlSession = MapperFactory.getSqlSession();
            // 2.获取Dao
            CatalogDao mapper = MapperFactory.getMapper(sqlSession, CatalogDao.class);
            // 3.执行sql语句
            mapper.delete(catalog);
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
    public Catalog findById(String id) {
        SqlSession sqlSession = null;
        try {
            // 1.获取sqlSession
            sqlSession = MapperFactory.getSqlSession();
            // 2.获取Dao
            CatalogDao mapper = MapperFactory.getMapper(sqlSession,CatalogDao.class);
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
    public List<Catalog> findAll() {
        SqlSession sqlSession = null;
        try {
            // 1.获取sqlSession
            sqlSession = MapperFactory.getSqlSession();
            // 2.获取Dao
            CatalogDao mapper = MapperFactory.getMapper(sqlSession, CatalogDao.class);
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
            CatalogDao mapper = MapperFactory.getMapper(sqlSession, CatalogDao.class);
            // 3.执行sql语句,并返回结果
            PageHelper.startPage(page,pagesize);
            List<Catalog> all = mapper.findAll();
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

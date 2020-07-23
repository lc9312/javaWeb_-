package com.itheima.service.store.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheima.dao.store.CompanyDao;
import com.itheima.domain.store.Company;
import com.itheima.factory.MapperFactory;
import com.itheima.service.store.CompanyService;
import com.itheima.utils.TransactionUtil;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;
import java.util.UUID;

public class CompanyServiceImpl implements CompanyService {
    @Override
    public void save(Company company)
    {
        SqlSession sqlSession = null;
        try {
            // 1.获取SqlSession对象
            sqlSession = MapperFactory.getSqlSession();
            // 2.获取Dao层的mapper对象
            CompanyDao mapper = MapperFactory.getMapper(sqlSession,CompanyDao.class);
            // 3.给Company添加主键ID值
            String companyId = UUID.randomUUID().toString();
            company.setId(companyId);
            // 3.执行Sql语句
            mapper.save(company);
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
    public void update(Company company) {
        SqlSession sqlSession = null;
        try {
            // 1.获取sqlSession
            sqlSession = MapperFactory.getSqlSession();
            // 2.获取Dao层的mapper对象
            CompanyDao mapper = MapperFactory.getMapper(sqlSession, CompanyDao.class);
            // 3.执行sql语句
            mapper.update(company);
            // 4.提交事务
            TransactionUtil.commit(sqlSession);
        } catch (Exception e) {
            if(sqlSession != null){
                TransactionUtil.rollback(sqlSession);
            }
            throw new RuntimeException("修改失败!");
        } finally {
            // 4.关闭资源
            if(sqlSession != null){
                TransactionUtil.close(sqlSession);
            }
        }
    }

    @Override
    public void delete(Company company) {
        SqlSession sqlSession = null;
        try {
            // 1.获取sqlSession
            sqlSession = MapperFactory.getSqlSession();
            // 2.获取Dao层的mapper对象
            CompanyDao mapper = MapperFactory.getMapper(sqlSession, CompanyDao.class);
            // 3.执行sql语句
            mapper.delete(company);
            // 4.提交事务
            TransactionUtil.commit(sqlSession);
        } catch (Exception e) {
            if( sqlSession != null){
                TransactionUtil.rollback(sqlSession);
            }
            throw new RuntimeException("删除失败!");
        } finally {
            // 4.关闭资源
            if(sqlSession != null){
                TransactionUtil.close(sqlSession);
            }
        }
    }

    @Override
    public Company findById(String id)
    {
        SqlSession sqlSession = null;
        try {
            // 1.用工厂类获取sqlsession对象
            sqlSession = MapperFactory.getSqlSession();
            // 2.获取Dao的mapper
            CompanyDao dao = MapperFactory.getMapper(sqlSession, CompanyDao.class);
            // 3.调用mapper的Sql查询方法,并返回结果
            return dao.findById(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            // 5.关闭资源
            if( sqlSession!= null ){
                TransactionUtil.close(sqlSession);
            }
        }
    }

    @Override
    public List<Company> findAll() {
        SqlSession sqlSession = null;
        try {
            // 1.用工厂类获取sqlsession对象
            sqlSession = MapperFactory.getSqlSession();
            // 2.获取Dao的mapper
            CompanyDao dao = MapperFactory.getMapper(sqlSession, CompanyDao.class);
            // 3.调用mapper的Sql查询方法,并返回结果
            List<Company> all = dao.findAll();
            return dao.findAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            // 5.关闭资源
            if( sqlSession!= null ){
                TransactionUtil.close(sqlSession);
            }
        }
    }

    @Override
    public PageInfo findAll(Integer page, Integer pagesize) {
        SqlSession sqlSession = null;
        PageInfo pageInfo = null;
        try {
            // 1.获取sqlSession对象
            sqlSession = MapperFactory.getSqlSession();
            // 2.获取Dao层的mapper
            CompanyDao companyDao = MapperFactory.getMapper(sqlSession,CompanyDao.class);
            // 3. 调用mapper的sql查询方法,并返回结果
            PageHelper.startPage(page,pagesize);
            List<Company> all = companyDao.findAll();
            pageInfo = new PageInfo(all);
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
}

package com.itheima.service.system.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheima.dao.system.ModuleDao;
import com.itheima.dao.system.RoleDao;
import com.itheima.domain.system.Module;
import com.itheima.factory.MapperFactory;
import com.itheima.service.system.ModuleService;
import com.itheima.utils.TransactionUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public class ModuleServiceImpl implements ModuleService {

    @Override
    public void save(Module module) {
        SqlSession sqlSession = null;
        try {
            // 1.获取sqlSession
            sqlSession = MapperFactory.getSqlSession();
            // 2.获取Dao
            ModuleDao mapper = MapperFactory.getMapper(sqlSession, ModuleDao.class);
            // 3.给定module的ID值
            String id = UUID.randomUUID().toString();
            module.setId(id);
            // 4.执行sql语句
            mapper.save(module);
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
    public void delete(Module module) {
        SqlSession sqlSession = null;
        try {
            // 1.获取sqlSession
            sqlSession = MapperFactory.getSqlSession();
            // 2.获取Dao
            ModuleDao mapper = MapperFactory.getMapper(sqlSession, ModuleDao.class);
            // 3.执行sql语句
            int delete = mapper.delete(module);
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
    public void update(Module module) {
        SqlSession sqlSession = null;
        try {
            // 1.获取sqlSession
            sqlSession = MapperFactory.getSqlSession();
            // 2.获取Dao
            ModuleDao mapper = MapperFactory.getMapper(sqlSession, ModuleDao.class);
            // 3.执行sql语句
            mapper.update(module);
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
    public List<Module> findAll() {
        SqlSession sqlSession = null;
        try {
            // 1.获取sqlSession
            sqlSession = MapperFactory.getSqlSession();
            // 2.获取Daod的mapper
            ModuleDao mapper = MapperFactory.getMapper(sqlSession, ModuleDao.class);
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
            ModuleDao mapper = MapperFactory.getMapper(sqlSession, ModuleDao.class);
            // 3.执行sql语句
            PageHelper.startPage(page,pageSize);
            List<Module> all = mapper.findAll();
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
    public Module findById(String id) {
        SqlSession sqlSession = null;
        try {
            // 1.获取sqlSession
            sqlSession = MapperFactory.getSqlSession();
            // 2.获取Daod的mapper
            ModuleDao mapper = MapperFactory.getMapper(sqlSession, ModuleDao.class);
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

    public List<Map> getAuthDataByRid(String roleId){
        SqlSession sqlSession = null;
        try {
            // 1.获取sqlSession
            sqlSession = MapperFactory.getSqlSession();
            // 2.获取Daod的mapper
            ModuleDao mapper = MapperFactory.getMapper(sqlSession, ModuleDao.class);
            // 3.执行sql语句返回结果
            return mapper.getAuthDataByRid(roleId);
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

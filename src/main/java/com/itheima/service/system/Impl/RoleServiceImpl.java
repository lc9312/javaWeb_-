package com.itheima.service.system.Impl;

import com.alibaba.druid.pool.ha.selector.StickyDataSourceHolder;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheima.dao.system.RoleDao;
import com.itheima.domain.system.Role;
import com.itheima.factory.MapperFactory;
import com.itheima.service.system.RoleService;
import com.itheima.utils.TransactionUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public class RoleServiceImpl implements RoleService {

    @Override
    public void save(Role role) {
        SqlSession sqlSession = null;
        try {
            // 1.获取sqlSession
            sqlSession = MapperFactory.getSqlSession();
            // 2.获取Dao
            RoleDao mapper = MapperFactory.getMapper(sqlSession, RoleDao.class);
            // 3.给定role的ID值
            String id = UUID.randomUUID().toString();
            role.setId(id);
            // 4.执行sql语句
            mapper.save(role);
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
    public void delete(Role role) {
        SqlSession sqlSession = null;
        try {
            // 1.获取sqlSession
            sqlSession = MapperFactory.getSqlSession();
            // 2.获取Dao
            RoleDao mapper = MapperFactory.getMapper(sqlSession, RoleDao.class);
            // 3.执行sql语句
            int delete = mapper.delete(role);
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
    public void update(Role role) {
        SqlSession sqlSession = null;
        try {
            // 1.获取sqlSession
            sqlSession = MapperFactory.getSqlSession();
            // 2.获取Dao
            RoleDao mapper = MapperFactory.getMapper(sqlSession, RoleDao.class);
            // 3.执行sql语句
            mapper.update(role);
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
    public List<Role> findAll() {
        SqlSession sqlSession = null;
        try {
            // 1.获取sqlSession
            sqlSession = MapperFactory.getSqlSession();
            // 2.获取Daod的mapper
            RoleDao mapper = MapperFactory.getMapper(sqlSession, RoleDao.class);
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
            RoleDao mapper = MapperFactory.getMapper(sqlSession, RoleDao.class);
            // 3.执行sql语句
            PageHelper.startPage(page,pageSize);
            List<Role> all = mapper.findAll();
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
    public Role findById(String id) {
        SqlSession sqlSession = null;
        try {
            // 1.获取sqlSession
            sqlSession = MapperFactory.getSqlSession();
            // 2.获取Daod的mapper
            RoleDao mapper = MapperFactory.getMapper(sqlSession, RoleDao.class);
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

    @Override
    public void updateRoleModule(String roleId, String moduleIds) {
        SqlSession sqlSession = null;
        try {
            // 1.获取sqlSession
            sqlSession = MapperFactory.getSqlSession();
            // 2.获取Dao
            RoleDao mapper = MapperFactory.getMapper(sqlSession, RoleDao.class);
            // 3.将moduleIds转换成数组,执行sql语句,先删除,再添加
            mapper.deleteModule(roleId);
            String[] moduleIdArray = moduleIds.split(",");
//            for (int i = 0; i < moduleIdArray.length; i++) {
//                mapper.updateRoleModule(roleId,moduleIdArray[i]);
//            }
            mapper.updateRoleModule(roleId,moduleIdArray);
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
    public List<Map> findRolesByUid(String userId) {
        SqlSession sqlSession = null;
        try {
            // 1.获取sqlSession
            sqlSession = MapperFactory.getSqlSession();
            // 2.获取Daod的mapper
            RoleDao mapper = MapperFactory.getMapper(sqlSession, RoleDao.class);
            // 3.执行sql语句
            return  mapper.findRolesByUid(userId);
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
    public  void updateUserRole(String userId, String[] roleIds){
        SqlSession sqlSession = null;
        try {
            // 1.获取sqlSession
            sqlSession = MapperFactory.getSqlSession();
            // 2.获取Dao
            RoleDao mapper = MapperFactory.getMapper(sqlSession, RoleDao.class);
            // 3.执行sql语句
            // 首先删除用户的角色
            mapper.deleteRoleByUid(userId);
            // 然后添加用户角色
            mapper.saveUserRoles(userId,roleIds);
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

}

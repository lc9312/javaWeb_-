package com.itheima.dao.system;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.system.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface RoleDao {
    public abstract int save(Role role);

    public abstract int delete(Role role);

    public abstract int update(Role role);

    public abstract List<Role> findAll();

    public abstract PageInfo findAll(Integer page, Integer pageSize);

    public abstract Role findById(String id);

    void updateRoleModule(@Param("roleId") String roleId, @Param("moduleIds") String[] moduleId);

    void deleteModule(String roleId);

    List<Map> findRolesByUid(String userId);

    void deleteRoleByUid(String userId);

    void saveUserRoles(@Param("userId") String userId, @Param("roleIds") String[] roleIds);
}

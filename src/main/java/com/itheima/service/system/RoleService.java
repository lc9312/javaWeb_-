package com.itheima.service.system;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.system.Role;

import java.util.List;
import java.util.Map;

public interface RoleService {
    public abstract void save(Role role);

    public abstract void delete(Role role);

    public abstract void update(Role role);

    public abstract List<Role> findAll();

    public abstract PageInfo findAll(Integer page, Integer pageSize);

    public abstract Role findById(String id);

    void updateRoleModule(String roleId, String moduleIds);

    List<Map> findRolesByUid(String userId);
    void updateUserRole(String userId, String[] roleIds);

}

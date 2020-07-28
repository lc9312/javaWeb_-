package com.itheima.dao.system;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.system.Module;

import java.util.List;
import java.util.Map;

public interface ModuleDao {
    public abstract int save(Module module);

    public abstract int delete(Module module);

    public abstract int update(Module module);

    public abstract List<Module> findAll();

    public abstract PageInfo findAll(Integer page, Integer pageSize);

    public abstract Module findById(String id);

    List<Map> getAuthDataByRid(String roleId);

    List<Module> finAllModuleByUid(String userId);
}

package com.itheima.service.system;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.system.Module;

import java.util.List;
import java.util.Map;

public interface ModuleService {
    public abstract void save(Module module);

    public abstract void delete(Module module);

    public abstract void update(Module module);

    public abstract List<Module> findAll();

    public abstract PageInfo findAll(Integer page, Integer pageSize);

    public abstract Module findById(String id);

    public abstract List<Map> getAuthDataByRid(String roleId);
}

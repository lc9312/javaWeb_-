package com.itheima.service.system;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.system.Dept;

import java.util.List;

public interface DeptService {
    public abstract void save(Dept dept);

    public abstract void delete(Dept dept);

    public abstract void update(Dept dept);

    public abstract List<Dept> findAll();

    public abstract PageInfo findAll(Integer page, Integer pageSize);

    public abstract Dept findById(String id);
}

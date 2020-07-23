package com.itheima.dao.system;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.system.Dept;

import java.util.List;

public interface DeptDao {
    public abstract int save(Dept dept);

    public abstract int delete(Dept dept);

    public abstract int update(Dept dept);

    public abstract List<Dept> findAll();

    public abstract PageInfo findAll(Integer page, Integer pageSize);

    public abstract Dept findById(String id);
}

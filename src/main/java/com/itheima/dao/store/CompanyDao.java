package com.itheima.dao.store;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.store.Company;

import java.util.List;

public interface CompanyDao {
    public abstract int save(Company company);

    public abstract int update(Company company);

    public abstract int delete(Company company);

    public abstract Company findById(String id);

    public abstract List<Company> findAll();

    public abstract PageInfo findAll(Integer page, Integer pagesize);
}

package com.itheima.dao.store;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.store.Catalog;

import java.util.List;

public interface CatalogDao {
    public abstract int save(Catalog catalog);

    public abstract int update(Catalog catalog);

    public abstract int delete(Catalog catalog);

    public abstract Catalog findById(String id);

    public abstract List<Catalog> findAll();

    public abstract PageInfo findAll(Integer page, Integer pagesize);
}

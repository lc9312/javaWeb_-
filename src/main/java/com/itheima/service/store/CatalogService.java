package com.itheima.service.store;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.store.Catalog;

import java.util.List;

public interface CatalogService {
    /**
     *  添加
     * @param catalog
     * @return
     */
    public abstract void save(Catalog catalog);

    /**
     * 修改
     * @param catalog
     * @return
     */
    public abstract void update(Catalog catalog);

    /**
     * 删除
     * @param catalog
     * @return
     */
    public abstract void delete(Catalog catalog);

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    public abstract Catalog findById(String id);

    /**
     * 查询所有
     * @return
     */
    public abstract List<Catalog> findAll();

    /**
     * 分页查询
     * @param page
     * @param pagesize
     * @return
     */
    public abstract PageInfo findAll(Integer page, Integer pagesize);
}

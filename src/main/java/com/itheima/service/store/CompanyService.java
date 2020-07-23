package com.itheima.service.store;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.store.Company;

import java.util.List;

public interface CompanyService {
    /**
     *  添加
     * @param company
     * @return
     */
    public abstract void save(Company company);

    /**
     * 修改
     * @param company
     * @return
     */
    public abstract void update(Company company);

    /**
     * 删除
     * @param company
     * @return
     */
    public abstract void delete(Company company);

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    public abstract Company findById(String id);

    /**
     * 查询所有
     * @return
     */
    public abstract List<Company> findAll();

    /**
     * 分页查询
     * @param page
     * @param pagesize
     * @return
     */
    public abstract PageInfo findAll(Integer page, Integer pagesize);
}

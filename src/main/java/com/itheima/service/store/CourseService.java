package com.itheima.service.store;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.store.Course;

import java.util.List;

public interface CourseService {
    /**
     *  添加
     * @param course
     * @return
     */
    public abstract void save(Course course);

    /**
     * 修改
     * @param course
     * @return
     */
    public abstract void update(Course course);

    /**
     * 删除
     * @param course
     * @return
     */
    public abstract void delete(Course course);

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    public abstract Course findById(String id);

    /**
     * 查询所有
     * @return
     */
    public abstract List<Course> findAll();

    /**
     * 分页查询
     * @param page
     * @param pagesize
     * @return
     */
    public abstract PageInfo findAll(Integer page, Integer pagesize);
}

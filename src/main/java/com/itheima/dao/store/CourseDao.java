package com.itheima.dao.store;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.store.Course;

import java.util.List;

public interface CourseDao {
    public abstract int save(Course course);

    public abstract int update(Course course);

    public abstract int delete(Course course);

    public abstract Course findById(String id);

    public abstract List<Course> findAll();

    public abstract PageInfo findAll(Integer page, Integer pagesize);
}

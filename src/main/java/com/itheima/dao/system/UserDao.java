package com.itheima.dao.system;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.system.User;

import java.util.List;

public interface UserDao {
    public abstract int save(User user);

    public abstract int delete(User user);

    public abstract int update(User user);

    public abstract List<User> findAll();

    public abstract PageInfo findAll(Integer page, Integer pageSize);

    public abstract User findById(String id);
}

package com.itheima.service.system;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.system.User;

import java.util.List;

public interface UserService {
    public abstract void save(User user);

    public abstract void delete(User user);

    public abstract void update(User user);

    public abstract List<User> findAll();

    public abstract PageInfo findAll(Integer page, Integer pageSize);

    public abstract User findById(String id);
}

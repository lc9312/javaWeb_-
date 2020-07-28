package com.itheima.dao.system;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.system.Module;
import com.itheima.domain.system.Role;
import com.itheima.domain.system.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDao {
    public abstract int save(User user);

    public abstract int delete(User user);

    public abstract int update(User user);

    public abstract List<User> findAll();

    public abstract PageInfo findAll(Integer page, Integer pageSize);

    public abstract User findById(String id);

    List<Role> getUserRoles(String userId);

    User findByEmailAndPwd(@Param("email") String email,  @Param("password") String password);
}

package com.itheima.service.system;

import com.itheima.domain.system.Role;
import com.itheima.service.system.Impl.RoleServiceImpl;
import org.junit.Test;

import java.util.List;

public class RoleSystemServiceTest {
    @Test
    public void findAll(){
        RoleServiceImpl roleService = new RoleServiceImpl();
        List<Role> all = roleService.findAll();
        System.out.println(all.size());
    }
}

package com.itheima.web.controller.system;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.system.Dept;
import com.itheima.domain.system.Module;
import com.itheima.domain.system.Role;
import com.itheima.domain.system.User;
import com.itheima.utils.BeanUtil;
import com.itheima.utils.MD5Util;
import com.itheima.web.controller.BaseServlet;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *  HttpServlet
 */
 @WebServlet( urlPatterns = "/system/user" )
public class UserServlet extends BaseServlet{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1.获取页面传来的操作属性
        String operation = req.getParameter("operation");
        // 2.根据操作调用方法
        if( "list".equals(operation)){
            this.list(req,resp);
        }else if("toAdd".equals(operation)){
            this.toAdd(req,resp);
        }else if("save".equals(operation)){
            this.save(req,resp);
        }else if("toEdit".equals(operation)){
            this.toEdit(req,resp);
        }else if("edit".equals(operation)){
            this.edit(req,resp);
        }else if("delete".equals(operation)){
            this.delete(req,resp);
        }else if("userRoleList".equals(operation)){
            this.userRoleList(req,resp);
        }else if("updateUserRole".equals(operation)){
            this.updateUserRole(req,resp);
        }else if("login".equals(operation)){
            this.login(req,resp);
        }else if("home".equals(operation)){
            this.home(req,resp);
        }else if("logout".equals(operation)){
            this.logout(req,resp);
        }
    }

    private void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1.业务层查询数据
        int page = 1;
        int pageSize = 10;
        // 2.获取页面的页码和每页数据量
        if(StringUtils.isNotBlank(req.getParameter("page"))){
            page = Integer.parseInt(req.getParameter("page"));
        }

        // 3.查询数据
        PageInfo pageInfo = userService.findAll(page, pageSize);

        // 4.将数据写入req域
        req.setAttribute("page",pageInfo);

        // 5.请求转发
        req.getRequestDispatcher("/WEB-INF/pages/system/user/list.jsp").forward(req,resp);
    }

    private void toAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取所有部门信息
        List<Dept> allDept = deptService.findAll();
        req.setAttribute("deptList",allDept);
        // 跳转到新建界面
        req.getRequestDispatcher("/WEB-INF/pages/system/user/add.jsp").forward(req,resp);
    }
    private void save(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // 1.封装页面获取的数据
        User user = BeanUtil.fillBean(req, User.class, "yyyy-MM-dd");
        // 2.对密码加密
        String passwordMd5 = MD5Util.md5(user.getPassword());
        user.setPassword(passwordMd5);
        // 3.保存到数据库
        userService.save(user);
        // 4.跳转到查询全部页面
        resp.sendRedirect(req.getContextPath()+"/system/user?operation=list");
    }

    private void delete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // 1.获取传入的Id的对象
        User user = BeanUtil.fillBean(req, User.class);

        System.out.println(user.getId());

        // 2.根据Id删除对应数据
        userService.delete(user);
        // 3.返回查询全部
        resp.sendRedirect(req.getContextPath()+"/system/user?operation=list");
    }

    private void toEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1.根据Id查询数据及所有部门信息
        String id = req.getParameter("id");
        User user = userService.findById(id);
        List<Dept> all = deptService.findAll();
        // 2.数据写入req域
        req.setAttribute("user",user);
        req.setAttribute("deptList",all);
        // 3.请求转发到编辑界面
        req.getRequestDispatcher("/WEB-INF/pages/system/user/update.jsp").forward(req,resp);
    }

    private void edit(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // 1.封装页面的数据
        User user = BeanUtil.fillBean(req, User.class,"yyyy-MM-dd");
        // 2. 业务层写入数据库
        userService.update(user);
        // 3. 返回查询全部
        resp.sendRedirect(req.getContextPath()+"/system/user?operation=list");
    }

    private void userRoleList(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        // 获取用户ID
        String userId = req.getParameter("userId");
        User user = userService.findById(userId);
        // 调用业务层
        List<Map> roleList = roleService.findRolesByUid(userId);
        for (Map map : roleList) {
            Set<String> keys = map.keySet();
            for (String key : keys) {
                System.out.println(key+":"+map.get(key));
            }
        }
        req.setAttribute("roleList",roleList);
        req.setAttribute("user",user);
        // 回到用户角色列表
        req.getRequestDispatcher("/WEB-INF/pages/system/user/role.jsp").forward(req,resp);
    }

    private void updateUserRole(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        // 获取用户ID,及其roleId
        String userId = req.getParameter("userId");
        String[] roleIds = req.getParameterValues("roleIds");
        System.out.println(Arrays.toString(roleIds));
        // 调用业务层,更新并保存用户的角色
        roleService.updateUserRole(userId,roleIds);
        // 回到用户角色列表
        list(req,resp);
    }

    private void login(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        // 获取登录用户名,密码
        String email = req.getParameter("email");
        String passwordRaw = req.getParameter("password");
        // 密码加密
        String password = MD5Util.md5(passwordRaw);
        // 调用业务层,查询用户
        User user = userService.login(email, password);
        // 登录验证
        if( user == null ){
            // 返回登录界面
            resp.sendRedirect("/login.jsp");
        }else { // 进入主界面
            req.getSession().setAttribute("loginUser",user);
            // 查询用户对应的所有模块
            List<Module> moduleList = userService.finAllModuleByUid(user.getId());
            req.setAttribute("moduleList",moduleList);
            //当前登录用户对应的可操作模块的所有url拼接成一个大的字符串
            StringBuilder sbf = new StringBuilder();
            for (Module module : moduleList) {
                sbf.append(module.getCurl());
                sbf.append(',');
            }
            req.getSession().setAttribute("authorStr",sbf.toString());
            req.getRequestDispatcher("/WEB-INF/pages/home/main.jsp").forward(req, resp);
        }
    }

    private void logout(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        // 清除用户信息
        // 返回登录界面
        resp.sendRedirect("/login.jsp");
    }

    private void home(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        // 转到home界面
        req.getRequestDispatcher("/WEB-INF/pages/home/home.jsp").forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }
}

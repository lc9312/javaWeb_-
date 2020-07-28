package com.itheima.web.controller.system;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageInfo;
import com.itheima.domain.system.Dept;
import com.itheima.domain.system.Role;
import com.itheima.utils.BeanUtil;
import com.itheima.utils.MD5Util;
import com.itheima.web.controller.BaseServlet;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 *  HttpServlet
 */
 @WebServlet( urlPatterns = "/system/role" )
public class RoleServlet extends BaseServlet{
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
        }else if("author".equals(operation)){
            this.author(req,resp);
        }else if("updateRoleModule".equals(operation)){
            this.updateRoleModule(req,resp);
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
        PageInfo pageInfo = roleService.findAll(page, pageSize);

        // 4.将数据写入req域
        req.setAttribute("page",pageInfo);

        // 5.请求转发
        req.getRequestDispatcher("/WEB-INF/pages/system/role/list.jsp").forward(req,resp);
    }

    private void toAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 跳转到新建界面
        req.getRequestDispatcher("/WEB-INF/pages/system/role/add.jsp").forward(req,resp);
    }
    private void save(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // 1.封装页面获取的数据
        Role role = BeanUtil.fillBean(req, Role.class, "yyyy-MM-dd");
        // 2.保存到数据库
        roleService.save(role);
        // 3.跳转到查询全部页面
        resp.sendRedirect(req.getContextPath()+"/system/role?operation=list");
    }

    private void delete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // 1.获取传入的Id的对象
        Role role = BeanUtil.fillBean(req, Role.class);

        System.out.println(role.getId());

        // 2.根据Id删除对应数据
        roleService.delete(role);
        // 3.返回查询全部
        resp.sendRedirect(req.getContextPath()+"/system/role?operation=list");
    }

    private void toEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1.根据Id查询数据及所有部门信息
        String id = req.getParameter("id");
        Role role = roleService.findById(id);
        // 2.数据写入req域
        req.setAttribute("role",role);
        // 3.请求转发到编辑界面
        req.getRequestDispatcher("/WEB-INF/pages/system/role/update.jsp").forward(req,resp);
    }

    private void edit(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // 1.封装页面的数据
        Role role = BeanUtil.fillBean(req, Role.class,"yyyy-MM-dd");
        // 2. 业务层写入数据库
        roleService.update(role);
        // 3. 返回查询全部
        resp.sendRedirect(req.getContextPath()+"/system/role?operation=list");
    }

    private void author(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 传入角色信息
        String roleId = req.getParameter("id");
        Role role = roleService.findById(roleId);
        // 获取与角色相关的授权数据
        List<Map> authDataByRid = moduleService.getAuthDataByRid(roleId);
        // 封装成json数据,写入req域
        ObjectMapper objectMapper = new ObjectMapper();
        String roleAuthjson = objectMapper.writeValueAsString(authDataByRid);

        // 保存到req域
        req.setAttribute("roleAuthjson",roleAuthjson);
        req.setAttribute("role",role);
        // 转发给jsp界面
        req.getRequestDispatcher("/WEB-INF/pages/system/role/author.jsp").forward(req,resp);
    }

    private void updateRoleModule(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 传入角色及相关的模块信息
        String roleId = req.getParameter("roleId");
        String moduleIds = req.getParameter("moduleIds");
        System.out.println(roleId);
        // 调用业务层完成角色的模块修改
        roleService.updateRoleModule(roleId,moduleIds);
        // 返回到角色模块界面
        //跳转回到页面list
        req.getRequestDispatcher(req.getContextPath()+"/system/role?operation=list").forward(req,resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }
}

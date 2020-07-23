package com.itheima.web.controller.system;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.system.Dept;
import com.itheima.utils.BeanUtil;
import com.itheima.web.controller.BaseServlet;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 *  HttpServlet
 */
 @WebServlet( urlPatterns = "/system/dept" )
public class DeptServlet extends BaseServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1.获取页面传来的操作名称
        String operation = req.getParameter("operation");
        // 2.根据操作名称调用不同的方法
        if( "list".equals(operation)){
            this.list(req,resp);
        }else if ("toAdd".equals(operation)) {
            this.toAdd(req,resp);
        }else if ("save".equals(operation)) {
            this.save(req,resp);
        }else if ("delete".equals(operation)) {
            this.delete(req,resp);
        }else if ("toEdit".equals(operation)) {
            this.toEdit(req,resp);
        }else if ("edit".equals(operation)) {
            this.edit(req,resp);
        }
    }

    public void list(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
        // 1.初始页码和每页数据条数
        int page = 1;
        int pageSize = 5;

        // 2.从页面获取页码
        if(StringUtils.isNotBlank(req.getParameter("page"))){
            page = Integer.parseInt(req.getParameter("page"));
        }

        // 3.调用业务层实现功能
        PageInfo pageInfo = deptService.findAll(page, pageSize);

        // 4.将查询到的数据存到request域中
        req.setAttribute("page",pageInfo);

        // 5.请求转发
        req.getRequestDispatcher("/WEB-INF/pages/system/dept/list.jsp").forward(req,resp);
    }

    public void toAdd(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
        // 获取所有部门信息,传入add页面
        List<Dept> all = deptService.findAll();
        req.setAttribute("deptList",all);
        // 跳转到新加页面
        req.getRequestDispatcher("/WEB-INF/pages/system/dept/add.jsp").forward(req,resp);
    }

    public void save(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
        // 1.从页面请求域获取数据,封装对象
        Dept dept = BeanUtil.fillBean(req, Dept.class);
        // 2.业务层完成功能
        deptService.save(dept);
        // 3.跳转到查询结果页面
        resp.sendRedirect(req.getContextPath()+"/system/dept?operation=list");
    }

    public void delete(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
        // 1.从页面请求域获取数据,封装对象
        Dept dept = BeanUtil.fillBean(req, Dept.class);
        // 2.业务层完成删除功能
        deptService.delete(dept);
        // 3.跳转到查询结果页面
        resp.sendRedirect(req.getContextPath()+"/system/dept?operation=list");
    }

    public void toEdit(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
        // 获取要编辑的数据的id
        String id = req.getParameter("id");
        // 查询该数据
        Dept dept = deptService.findById(id);
        // 获取所有部门信息,传入编辑页面
        List<Dept> all = deptService.findAll();
        // 保存到req域
        req.setAttribute("deptList",all);
        req.setAttribute("dept",dept);
        // 跳转到新加页面
        req.getRequestDispatcher("/WEB-INF/pages/system/dept/update.jsp").forward(req,resp);
    }

    public void edit(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
        // 1.从页面请求域获取数据,封装对象
        Dept dept = BeanUtil.fillBean(req, Dept.class);
        // 2.业务层完成功能
        deptService.update(dept);
        // 3.跳转到查询结果页面
        resp.sendRedirect(req.getContextPath()+"/system/dept?operation=list");
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }
}

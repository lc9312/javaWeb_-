package com.itheima.web.controller.store;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.store.Catalog;
import com.itheima.domain.store.Course;
import com.itheima.utils.BeanUtil;
import com.itheima.web.controller.BaseServlet;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 *  HttpServlet
 */
 @WebServlet( urlPatterns = "/store/catalog" )
public class CatalogServlet extends BaseServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1.获取操作的属性
        String operation = req.getParameter("operation");
        // 2.根据操作调用不同的方法
        if( "list".equals(operation)){
            this.list(req,resp);
        }else if ("toAdd".equals(operation)){
            this.toAdd(req,resp);
        }else if ("save".equals(operation)){
            this.save(req,resp);
        }else if ("delete".equals(operation)){
            this.delete(req,resp);
        }else if ("toEdit".equals(operation)){
            this.toEdit(req,resp);
        }else if ("edit".equals(operation)){
            this.edit(req,resp);
        }
    }
    public void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 初始化页码和每页的数据
        int page = 1;
        int pageSize = 10;
        // 从页面获取页码和每页数据大小
        if(StringUtils.isNotBlank(req.getParameter("page"))){
            page = Integer.parseInt(req.getParameter("page"));
        }
        if(StringUtils.isNotBlank(req.getParameter("pageSize"))){
            pageSize = Integer.parseInt(req.getParameter("pageSize"));
        }
        // 利用业务层执行查询功能
        PageInfo pageInfo = catalogService.findAll(page, pageSize);
        // 存入req域
        req.setAttribute("page",pageInfo);
        // 请求转发
        req.getRequestDispatcher("/WEB-INF/pages/store/catalog/list.jsp").forward(req,resp);
    }

    public void toAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取所有学科
        List<Course> courses = courseService.findAll();
        // 存入req域
        req.setAttribute("courseList",courses);
        // 跳转到新建界面
        req.getRequestDispatcher("/WEB-INF/pages/store/catalog/add.jsp").forward(req,resp);
    }

    public void save(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1.封装页面传来的数据为对象
        Catalog catalog = BeanUtil.fillBean(req, Catalog.class);
        // 2.存入数据库
        catalogService.save(catalog);
        // 3.请求转发
        req.getRequestDispatcher(req.getContextPath()+"/store/catalog?operation=list").forward(req,resp);
    }

    public void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1.将页面传来的数据封装成对象
        Catalog catalog = BeanUtil.fillBean(req, Catalog.class, "yyyy-MM-dd");
        // 2.调用业务层完成功能
        catalogService.delete(catalog);
        // 3.转到查询全部
        req.getRequestDispatcher(req.getContextPath()+"/store/catalog?operation=list").forward(req,resp);
    }

    public void toEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1.获取数据id
        String id = req.getParameter("id");
        // 2.调用业务层查询数据
        Catalog catalog = catalogService.findById(id);
        // 3.将数据信息存入req域
        req.setAttribute("catalog",catalog);
        // 获取所有学科
        List<Course> courses = courseService.findAll();
        // 存入req域
        req.setAttribute("courseList",courses);
        // 4.请求转发
        req.getRequestDispatcher("/WEB-INF/pages/store/catalog/update.jsp").forward(req,resp);
    }

    public void edit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1.封装请求域对象
        Catalog catalog = BeanUtil.fillBean(req, Catalog.class, "yyyy-MM-dd");
        // 2.调用业务层执行修改功能
        catalogService.update(catalog);
        // 3.返回到查询全部界面
        req.getRequestDispatcher(req.getContextPath()+"/store/catalog?operation=list").forward(req,resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }
}

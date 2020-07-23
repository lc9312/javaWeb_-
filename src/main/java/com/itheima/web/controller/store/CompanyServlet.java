package com.itheima.web.controller.store;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.store.Company;
import com.itheima.utils.BeanUtil;
import com.itheima.web.controller.BaseServlet;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *  HttpServlet
 */
 @WebServlet( urlPatterns = "/store/company" )
public class CompanyServlet extends BaseServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1.获取请求参数operation的值
        String operation = req.getParameter("operation");
        // 2.根据该值调用不同的service层方法
        if( "list".equals(operation)){
            this.list(req,resp);
        }else if ("toAdd".equals(operation)){
            this.toAdd(req,resp);
        }else if ("delete".equals(operation)){
            this.delete(req,resp);
        }else if ("save".equals(operation)){
            this.save(req,resp);
        }else if ("toEdit".equals(operation)){
            this.toEdit(req,resp);
        }else if ("edit".equals(operation)){
            this.edit(req,resp);
        }


    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    public void list(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
        // 分页显示
        int page = 1;
        int pageSize = 5;

        // 从页面获取页数和页码
        if(StringUtils.isNotBlank(req.getParameter("page"))){
            page = Integer.parseInt(req.getParameter("page"));
        }
        if(StringUtils.isNotBlank(req.getParameter("pageSize"))){
            pageSize = Integer.parseInt(req.getParameter("pageSize"));
        }

        // 执行分页查询
        PageInfo all = companyService.findAll(page, pageSize);
        //将数据保存到指定的位置
        req.setAttribute("page",all);
        //跳转页面
        req.getRequestDispatcher("/WEB-INF/pages/store/company/list.jsp").forward(req,resp);
    }

    public void toAdd(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
        //跳转页面
        req.getRequestDispatcher("/WEB-INF/pages/store/company/add.jsp").forward(req,resp);
    }

    public void save(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
        // 1.将request传来的数据封装成company对象
        Company company = BeanUtil.fillBean(req,Company.class,"yyyy-MM-dd");
        // 2.通过service层添加该公司
        companyService.save(company);
        // 3.跳转到查询全部的列表页面
        resp.sendRedirect(req.getContextPath()+"/store/company?operation=list");
    }

    public void delete(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
        // 1.将request传来的数据封装成company对象
        Company company = BeanUtil.fillBean(req,Company.class);

        System.out.println(company);

        // 2.通过service层删除该公司
        companyService.delete(company);
        // 3.跳转到全部列表页面
        resp.sendRedirect(req.getContextPath()+"/store/company?operation=list");
    }

    public void toEdit(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
        // 1.从service获取全部的company信息
        Company company = companyService.findById(req.getParameter("id"));
        // 2.向request域中添加company信息,用于跳转页面获取
        req.setAttribute("company",company);
        //跳转页面
        req.getRequestDispatcher("/WEB-INF/pages/store/company/update.jsp").forward(req,resp);
    }
    public void edit(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
        // 1.将request传来的数据封装成company对象
        Company company = BeanUtil.fillBean(req,Company.class);
        // 2.通过service层更新该公司信息
        companyService.update(company);
        // 3.跳转到全部列表页面
        resp.sendRedirect(req.getContextPath()+"/store/company?operation=list");
    }
}

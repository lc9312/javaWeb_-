package com.itheima.web.controller;

import com.itheima.dao.store.CompanyDao;
import com.itheima.domain.store.Catalog;
import com.itheima.domain.store.Question;
import com.itheima.service.store.*;
import com.itheima.service.store.Impl.*;
import com.itheima.service.system.DeptService;
import com.itheima.service.system.Impl.DeptServiceImpl;
import com.itheima.service.system.Impl.UserServiceImpl;
import com.itheima.service.system.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *  HttpServlet
 */
 @WebServlet( urlPatterns = "/BaseServlet" )
public class BaseServlet extends HttpServlet {
     protected CompanyService companyService;
     protected DeptService deptService;
     protected UserService userService;
     protected CourseService courseService;
     protected CatalogService catalogService;
     protected QuestionService questionService;
     protected QuestionItemService questionItemService;

    @Override
    public void init() throws ServletException {
        companyService = new CompanyServiceImpl();
        deptService = new DeptServiceImpl();
        userService = new UserServiceImpl();
        courseService = new CourseServiceImpl();
        catalogService = new CatalogServiceImpl();
        questionService = new QuestionServiceImpl();
        questionItemService = new QuestionItemServiceImpl();
    }
}

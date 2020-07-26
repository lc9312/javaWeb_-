package com.itheima.web.controller.store;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.store.QuestionItem;
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
 @WebServlet( urlPatterns = "/store/questionItem" )
public class QuestionItemServlet extends BaseServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1.获取请求参数operation的值
        String operation = req.getParameter("operation");
        // 2.根据该值调用不同的service层方法
        if( "list".equals(operation)){
            this.list(req,resp);
        }else if ("delete".equals(operation)){
            this.delete(req,resp);
        }else if ("toEdit".equals(operation)){
            this.toEdit(req,resp);
        }else if ("saveOrUpdate".equals(operation)){
            this.saveOrUpdate(req,resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    public void list(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
        // 获取题目的ID
        String questionId = req.getParameter("questionId");
        String operation = (String) req.getAttribute("operation");

        // 分页显示
        int page = 1;
        int pageSize = 10;

        // 从页面获取页数和页码
        if(StringUtils.isNotBlank(req.getParameter("page"))){
            page = Integer.parseInt(req.getParameter("page"));
        }
        if(StringUtils.isNotBlank(req.getParameter("pageSize"))){
            pageSize = Integer.parseInt(req.getParameter("pageSize"));
        }
        // 根据operation判断是编辑还是新建
        if( operation != null){
        }else {
            operation = "save";
        }

        // 执行分页查询
        PageInfo all = questionItemService.findAll(questionId,page, pageSize);
        //将数据保存到指定的位置,传入题目的ID
        req.setAttribute("questionId",questionId);
        req.setAttribute("operation",operation);
        req.setAttribute("page",all);
        //跳转页面
        req.getRequestDispatcher("/WEB-INF/pages/store/questionItem/list.jsp").forward(req,resp);
    }

    public void toAdd(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
        //跳转页面
        req.getRequestDispatcher("/WEB-INF/pages/store/questionItem/add.jsp").forward(req,resp);
    }

    public void saveOrUpdate(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
        // 1.将request传来的数据封装成questionItem对象
        QuestionItem questionItem = BeanUtil.fillBean(req,QuestionItem.class);
        // 2.判断是否存在questionItemId,存在则为修改,不存在则为添加
        if(questionItem.getId().length() > 0){// 注意,这里的条件不能使用questionItem.getId() != null,因为可能是""字符串
            // 建议使用工具类StringUtils.isNotBlank(questionItem.getId())
            // 2.1通过service层
            questionItemService.update(questionItem);
        }else {
            // 2.2通过service层
            questionItemService.save(questionItem);
        }
        // 3.跳转到显示页面
        list(req,resp);
    }

    public void delete(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
        // 1.将request传来的数据封装成questionItem对象
        QuestionItem questionItem = BeanUtil.fillBean(req,QuestionItem.class);
        // 2.通过service层删除该公司
        questionItemService.delete(questionItem);
        String questionId = req.getParameter("questionId");
        // 3.跳转到全部列表页面
        req.getRequestDispatcher(req.getContextPath()+"/store/questionItem?operation=list").forward(req,resp);
    }

    public void toEdit(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
        // 1.从service获取全部的questionItem信息
        QuestionItem questionItem = questionItemService.findById(req.getParameter("id"));
        // 2.向request域中添加questionItem信息,用于跳转页面获取
        req.setAttribute("questionItem",questionItem);
        //跳转页面
        list(req,resp);
    }
}

package com.itheima.web.controller.store;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.store.Catalog;
import com.itheima.domain.store.Company;
import com.itheima.domain.store.Question;
import com.itheima.domain.store.Course;
import com.itheima.utils.BeanUtil;
import com.itheima.web.controller.BaseServlet;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 *  HttpServlet
 */
 @WebServlet( urlPatterns = "/store/question" )
public class QuestionServlet extends BaseServlet {
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
            try {
                this.save(req,resp);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else if ("delete".equals(operation)){
            this.delete(req,resp);
        }else if ("toEdit".equals(operation)){
            this.toEdit(req,resp);
        }else if ("edit".equals(operation)){
            try {
                this.edit(req,resp);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else if ("toImgUpload".equals(operation)){
            this.toImgUpload(req,resp);
        }else if ("imgUpload".equals(operation)){
            try {
                this.imgUpload(req,resp);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else if ("questionExport".equals(operation)){
            this.questionExport(req,resp);
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
        PageInfo pageInfo = questionService.findAll(page, pageSize);
        // 存入req域
        req.setAttribute("page",pageInfo);
        // 请求转发
        req.getRequestDispatcher("/WEB-INF/pages/store/question/list.jsp").forward(req,resp);
    }

    public void toAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取所有学科
        List<Course> courses = courseService.findAll();
        // 存入req域
        req.setAttribute("courseList",courses);
        // 获取所有类别和公司
        List<Catalog> catalogList = catalogService.findAll();
        List<Company> companyList = companyService.findAll();
        // 存入req域
        req.setAttribute("catalogList",catalogList);
        req.setAttribute("companyList",companyList);
        // 跳转到新建界面
        req.getRequestDispatcher("/WEB-INF/pages/store/question/add.jsp").forward(req,resp);
    }

    public void save(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        // 判断是否有文件上传的标记
        Boolean flag = false;
        //1.确认该操作是否支持文件上传操作，enctype="multipart/form-data"
        if(ServletFileUpload.isMultipartContent(req)) {
            //2.创建磁盘工厂对象
            DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
            //3.Servlet文件上传核心对象
            ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);
            //4.从request中读取数据
            List<FileItem> fileItems = servletFileUpload.parseRequest(req);
            // 5.封装请求域对象
            Question question = BeanUtil.fillBean(fileItems, Question.class);
            // 6.设置标记位,若有文件上传则上传,无则将标记置为false
            for (FileItem fileItem : fileItems) {
                // 6.1 当前表单是否是文件表单
                if(!fileItem.isFormField()) {
                    // 6.2有文件标记置为true,并保存文件到服务器
                    flag = true;
                    fileItem.write(new File(req.getServletContext().getRealPath("file"),question.getId()));
                }
            }
            // 7.调用业务层执行功能
            questionService.save(question,flag);
        }

        // 8.请求转发到查询全部界面
        req.getRequestDispatcher(req.getContextPath()+"/store/question?operation=list").forward(req,resp);
    }

    public void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1.将页面传来的数据封装成对象
        Question question = BeanUtil.fillBean(req, Question.class, "yyyy-MM-dd");
        // 2.调用业务层完成功能
        questionService.delete(question);
        // 3.转到查询全部
        req.getRequestDispatcher(req.getContextPath()+"/store/question?operation=list").forward(req,resp);
    }

    public void toEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1.获取数据id
        String id = req.getParameter("id");
        // 2.调用业务层查询数据
        Question question = questionService.findById(id);
        // 3.将数据信息存入req域
        req.setAttribute("question",question);
        // 获取所有类别和公司
        List<Catalog> catalogList = catalogService.findAll();
        List<Company> companyList = companyService.findAll();
        // 存入req域
        req.setAttribute("catalogList",catalogList);
        req.setAttribute("companyList",companyList);
        // 4.请求转发
        req.getRequestDispatcher("/WEB-INF/pages/store/question/update.jsp").forward(req,resp);
    }

    public void edit(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        // 设置是否上传文件标记
        Boolean flag = false;
        //1.确认该操作是否支持文件上传操作，enctype="multipart/form-data"
        if(ServletFileUpload.isMultipartContent(req)) {
            //2.创建磁盘工厂对象
            DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
            //3.Servlet文件上传核心对象
            ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);
            //4.从request中读取数据
            List<FileItem> fileItems = servletFileUpload.parseRequest(req);
            // 5.封装请求域对象
            Question question = BeanUtil.fillBean(fileItems, Question.class);
            // 6.保存文件
            for (FileItem fileItem : fileItems) {
                //7.1 当前表单是否是文件表单
                if(!fileItem.isFormField()) {
                    //7.2.有文件上传,则将flag设置为true,并从临时存储文件的地方将内容写入到指定位置
                    flag = true;
                    fileItem.write(new File(req.getServletContext().getRealPath("file"),question.getId()));
                }
            }
            // 7.调用业务层执行功能
            questionService.update(question,flag);

        }

        // 8.返回到查询全部界面
        req.getRequestDispatcher(req.getContextPath()+"/store/question?operation=list").forward(req,resp);
    }

    public void toImgUpload(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 跳转到文件上传测试界面
        req.getRequestDispatcher("/WEB-INF/pages/store/question/imgUpload.jsp").forward(req,resp);
    }


    private void imgUpload(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //1.确认该操作是否支持文件上传操作，enctype="multipart/form-data"
        if(ServletFileUpload.isMultipartContent(req)) {
            //2.创建磁盘工厂对象
            DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
            //3.Servlet文件上传核心对象
            ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);
            //4.从request中读取数据
            List<FileItem> fileItems = servletFileUpload.parseRequest(req);
            for (FileItem fileItem : fileItems) {
                System.out.println(fileItem);
                //5.当前表单是否是文件表单
                if(!fileItem.isFormField()) {
                    //6.从临时存储文件的地方将内容写入到指定位置
                    fileItem.write(new File(req.getServletContext().getRealPath("file"),fileItem.getName()));
                }
            }
        }
    }

    private void questionExport(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // 调用业务层完成题目报表的获取
        ByteArrayOutputStream report = questionService.getReport();
        // 设置响应的文件及响应头等信息,此步骤必须在获取响应输出流之前
        resp.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        String fileName = new String("题目报表.xlsx".getBytes(),"iso8859-1");
        resp.setHeader("Content-Disposition","attachment;fileName="+fileName);
        // 获取响应输出流
        ServletOutputStream os = resp.getOutputStream();
        // 将报表写入响应输出流
        report.writeTo(os);
        // 将输出流刷新,关闭资源
        os.flush();
        os.close();
        report.close();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }
}

package com.itheima.web.filter;

import com.itheima.domain.system.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = "/*")
public class AuthorFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
            // 1.定义和协议相关的请求和响应对象
            HttpServletRequest request = (HttpServletRequest) servletRequest;
            HttpServletResponse response = (HttpServletResponse) servletResponse;
            // 2.获取请求的资源URI
            String uri = request.getRequestURI();
            // 放行必要的资源
            if(uri.endsWith(".css")
                    || uri.endsWith(".js")
                    || uri.endsWith(".png")
                    || uri.endsWith(".jpg")
                    || uri.endsWith("index.jsp")
                    || uri.endsWith("login.jsp")){
                filterChain.doFilter(request,response);
                return;
            }

            // 3.获取请求的操作
            String queryString = request.getQueryString();
            if(queryString.endsWith("operation=login")
                || queryString.endsWith("operation=logout")
                || queryString.endsWith("operation=home")){
                filterChain.doFilter(request,response);
                return;
            }

            // 首先检查登录状态,前端界面实现此功能

            System.out.println(uri);
            System.out.println(queryString);
            int i = uri.indexOf("/");
            // 3.1 去掉最前面的"/",拼接成操作的字符串格式
            String substring = uri.substring(i + 1, uri.length());
            String userOper = substring + "?" + queryString;

            // 3.1 去掉操作后面的参数
            if(queryString.indexOf("&") != -1){
                userOper = substring + "?" + queryString.substring(0,queryString.indexOf("&"));
            }

            System.out.println(userOper);

            //4.获取到当前登录人允许的操作
            String authorStr = (String)request.getSession().getAttribute("authorStr");
            //5.比对本次操作是否在当前登录人允许的操作范围内
            if(authorStr.contains(userOper)){
                //5.1如果允许，放行
                filterChain.doFilter(request,response);
            }else {//5.2不允许跳转到非法访问页
                response.sendRedirect("unauthorized.jsp");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void destroy() {

    }
}

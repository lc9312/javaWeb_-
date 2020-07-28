<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../base.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" language="java" %>
<aside class="main-sidebar">
    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">
        <!-- Sidebar user panel -->
        <div class="user-panel">
            <div class="pull-left image">
                <img src="${ctx}/img/user2-160x160.jpg" class="img-circle" alt="User Image">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            </div>
            <div >
                <strong style="color: white"> 欢迎您,${sessionScope.loginUser.email}</strong>
            </div>
        </div>

        <!-- sidebar menu: : style can be found in sidebar.less -->
        <ul class="sidebar-menu">
            <li class="header">菜单</li>
                <c:forEach items="${moduleList}" var="firstMenu">
                            <%-- 一级菜单 --%>
                            <li class="treeview">
                                <c:if test="${firstMenu.ctype==0}">
                                    <a href=#>
                                         <i class="fa fa-cube"></i> <span>${firstMenu.name}</span>
                                           <span class="pull-right-container"><i class="fa fa-angle-left pull-right"></i></span>
                                    </a>
                                </c:if>
                                <ul class="treeview-menu">
                                    <c:forEach items="${moduleList}" var="secondMenu">
                                        <c:if test="${secondMenu.ctype==1 && secondMenu.parentId == firstMenu.id}">
                                            <li id="${secondMenu.id}">
                                                <a onclick="setSidebarActive(this)" href="/${secondMenu.curl}" target="iframe">
                                                    <i class="fa fa-circle-o"></i>${secondMenu.name}
                                                </a>
                                            </li>
                                        </c:if>
                                    </c:forEach>
                                </ul>
                            </li>
                </c:forEach>
</ul>
</section>
<!-- /.sidebar -->
</aside>

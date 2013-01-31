<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sitemesh" uri="http://www.opensymphony.com/sitemesh/decorator"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<c:set var="sc_ctx">${ctx}/sc</c:set>

<div class="navbar">
    <div class="navbar-inner">
        <div class="container ">
        	<a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
            	<span class="icon-bar"></span>
            	<span class="icon-bar"></span>
            	<span class="icon-bar"></span>
            </a>
            <p class="navbar-text pull-left">
                ${sessionScope.__SESSION_USER_INFO.name}&nbsp;您好，欢迎来到####网！
            </p>
            <div class="nav-collapse">
                <ul class="nav pull-right">
                    <li>
                        <a href="${sc_ctx}/member/logout">用户退出</a>
                    </li>
                    <li>
                    	<a href="#" class="dropdown-toggle" data-toggle="dropdown">销售信息<b class="caret"></b></a>
                    	<ul class="dropdown-menu">
                            <li>
                                <a href="${sc_ctx}/cashRun">信息录入</a>
                            </li>
                            <li>
                                <a href="#">销售日结</a>
                            </li>
                        </ul>
                    </li>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">入库信息<b class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li>
                                <a href="${sc_ctx}/storeRun">信息录入</a>
                            </li>
                            <li>
                                <a href="${sc_ctx}/storeRunAudit">信息审核</a>
                            </li>
                        </ul>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>

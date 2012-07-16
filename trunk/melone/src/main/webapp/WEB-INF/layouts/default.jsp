<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sitemesh" uri="http://www.opensymphony.com/sitemesh/decorator"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>melone POS
			<sitemesh:title />
		</title>
		<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
		<link type="text/css" href="${ctx}/static/css/960grid/css/960.css" rel="stylesheet"/>
		<link type="text/css" href="${ctx}/static/css/style.css" rel="stylesheet"/>
		<script type="text/javascript" src="${ctx}/static/js/jquery-1.7.2.min.js"></script>
		<sitemesh:head />
	</head>
	<body>
		<div class="container">
			<%@ include file="/WEB-INF/layouts/header-menu.jsp"%>

			<sitemesh:body />

			<%@ include file="/WEB-INF/layouts/footer.jsp"%>
		</div>
	</body>
</html>
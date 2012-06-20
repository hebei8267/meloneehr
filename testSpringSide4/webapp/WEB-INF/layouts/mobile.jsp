<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sitemesh"
	uri="http://www.opensymphony.com/sitemesh/decorator"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<title>mobile示例: <sitemesh:title /></title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="${ctx}/static/mobile/jquery.mobile-1.1.0.min.css" />
<script src="${ctx}/static/mobile/jquery-1.7.1.js"></script>
<script src="${ctx}/static/mobile/jquery.mobile-1.1.0.min.js"></script>
<sitemesh:head />
</head>
<body>
	<div data-role="page">1123112323
		<sitemesh:body />
	</div>
	<!-- /page -->
</body>
</html>
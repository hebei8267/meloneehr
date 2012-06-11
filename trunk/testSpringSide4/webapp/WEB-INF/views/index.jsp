<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
	<head>
		<title>首页</title>
		<script>
			$(document).ready(function() {
				$("#index-tab").addClass("active");				
			});
		</script>
	</head>
	<body>
		<p>
			各式主流的、实用的、好玩的开源项目大派对。
			<br><br><br><br>
			参见spring-mvc.xml
			<br>
			<textarea style="height: 150px;width: 550px;">
				<!-- 定义首页 -->
				<mvc:view-controller path="/" view-name="index" />
			</textarea>
		</p>
	</body>
</html>
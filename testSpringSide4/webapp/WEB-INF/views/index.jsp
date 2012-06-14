<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
				参见   index.html
			</textarea>
			<form:form action="${ctx}/sc/index/setSession" method="post">
				<input id="submit" class="btn btn-primary" type="submit" value="Set Session"/>
			</form:form>
			
			<form:form action="${ctx}/sc/index/getSession" method="post">
				<input id="submit" class="btn btn-primary" type="submit" value="Get Session"/>
			</form:form>
			
			<form:form action="${ctx}/sc/index/removeAttribute" method="post">
				<input id="submit" class="btn btn-primary" type="submit" value="Remove Attribute"/>
			</form:form>
			
			<form:form action="${ctx}/sc/index/removeSession" method="post">
				<input id="submit" class="btn btn-primary" type="submit" value="Remove Session"/>
			</form:form>
		</p>
	</body>
</html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<title>My Page</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="${ctx}/static/mobile/jquery.mobile-1.1.0.min.css" />
<script src="${ctx}/static/mobile/jquery-1.7.1.js"></script>
<script src="${ctx}/static/mobile/jquery.mobile-1.1.0.min.js"></script>
</head>
<body>
	<div data-role="page">
		<div data-role="header">
			<h1>mobile测试</h1>
		</div>
		<!-- /header -->

		<div data-role="content">
			<ul data-role="listview" data-inset="true" data-filter="true">
				<li><a href="#">Acura</a>
				</li>
				<li><a href="#">Audi</a>
				</li>
				<li><a href="#">BMW</a>
				</li>
				<li><a href="#">Cadillac</a>
				</li>
				<li><a href="#">Ferrari</a>
				</li>
			</ul>
		</div>

		<form>			
			&nbsp;&nbsp;<input type="range"	name="slider" id="slider-0" value="25" min="0" max="100" /> 
				
			<a href="#" data-role="button" data-icon="star" data-theme="a">Star button</a> 
			<a href="#" data-role="button" data-icon="star" data-theme="b">Star button</a>
			<a href="#" data-role="button" data-icon="star" data-theme="c">Star button</a>
			<a href="#" data-role="button" data-icon="star" data-theme="d">Star button</a>
			<a href="#" data-role="button" data-icon="star" data-theme="e">Star button</a>
		</form>
		<!-- /content -->
		
		<div data-role="footer">
			<h1>mobile测试</h1>
		</div>
	</div>
	<!-- /page -->
</body>
</html>
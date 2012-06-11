<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
	<title>Restful Service高级演示</title>
	<script>
		$(document).ready(function() {
			$("#webservice-tab").addClass("active");
			
			$.ajax({
				type : "get",
				url : "${ctx}/rs/users/2",
				data : {},
				dataType : "json",
				timeout : 7000,
				success : function(data) {
					
				}
			});
			
			$.ajax({
				type : "get",
				url : "${ctx}/rs/users/3",
				data : {},
				dataType : "xml",
				timeout : 7000,
				success : function(data) {
					
				}
			});
			
			$.ajax({
				type : "get",
				url : "${ctx}/rs/users/search",
				data : {"loginName":"admin","name":"333"},
				dataType : "xml",
				timeout : 7000,
				success : function(data) {
					
				}
			});
		});
	</script>
</head>

<body>
		<h2>Restful Service 高级演示</h2>

		<h3>技术说明:</h3>
		<ul>
			<li>Rest与Spring的集成</li>
			<li>CFX与Spring的集成</li>
			<br><br>
			<li>根据不同的输入参数, 返回不同编码格式(json/xml)</li>
			<li>
				<textarea style="height: 150px;width: 550px;">
				$.ajax({
					type : "get",
					url : "${ctx}/rs/users/2",
					data : {},
					dataType : "json",
					timeout : 7000,
					success : function(data) {
						
					}
				});
				
				$.ajax({
					type : "get",
					url : "${ctx}/rs/users/3",
					data : {},
					dataType : "xml",
					timeout : 7000,
					success : function(data) {
						
					}
				});
				</textarea>
			</li>
			<li>根据输入参数查询.</li>
			<li>
				<textarea style="height: 150px;width: 550px;">
				$.ajax({
					type : "get",
					url : "${ctx}/rs/users/search",
					data : {"loginName":"admin","name":"admin"},
					dataType : "xml",
					timeout : 7000,
					success : function(data) {
						
					}
				});
				</textarea>
			</li>
		</ul>
</body>
</html>
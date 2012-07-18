<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
	<head>
		<script>
			$(function() {
				$("#loginBtn").button();

				$("#loginBtn").click(function() {
					$("#inputForm").attr("action", "${ctx}/sc/account/login");
		        	$("#inputForm").submit();
				});
			});
		</script>
	</head>
	<body>
		<div class="grid_7 prefix_9">
			<form:form method="post" class="form" style="margin-top: 300px" id="inputForm">
				<table>
					<tr>
						<td class="item_name" width="70px">用户名:</td>
						<td class="item">
							<input type="text" name="name" id="name" class="text ui-widget-content ui-corner-all"/>
						</td>
					</tr>
					<tr>
						<td class="item_name">密码:</td>
						<td class="item">
							<input type="text" name="passWd" id="passWd" class="text ui-widget-content ui-corner-all"/>
						</td>
					</tr>
					<tr>
						<td colspan="2" align="right" class="item">
							<a id="forgetBtn" class="forget_link" style="margin-right: 10px">忘记密码?</a>
							<input id="loginBtn" type="button" class="submit" value="登录"/>
						</td>
					</tr>
				</table>
			</form:form>
		</div>
		<div class="clear"></div>
	</body>
</html>

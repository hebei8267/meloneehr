<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="page" uri="http://www.opensymphony.com/sitemesh/page"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<c:set var="sc_ctx">${ctx}/sc</c:set>
<html>
	<head>
	</head>
	<body>
		<form:form method="post" class="form cmxform" id="inputForm">
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
							<input type="password" name="passWd" id="passWd" class="text ui-widget-content ui-corner-all"/>
						</td>
					</tr>
					<tr>
						<td></td>
						<td class="item">
							<input id="loginBtn" type="button" class="submit" value="登录"/>
							&nbsp;&nbsp;&nbsp;
							<a id="forgetBtn" class="forget_link" style="margin-right: 10px">忘记密码?</a>
						</td>
					</tr>
				</table>
			</form:form>
	</body>
</html>
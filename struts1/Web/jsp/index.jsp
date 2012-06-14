<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%> 
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%> 

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		<script language="javascript">
			function login(){
				listActionForm.submit();
			}
		</script>
	</head>
	<body>
		<html:form action="/initListAction">
			初期化数据列表
			<html:button property="T1" value="确定" onclick="login();"/>
		</html:form>
	</body>
</html>
	
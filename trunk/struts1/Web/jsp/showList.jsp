<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%> 
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%> 
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested"%>

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
		<html:form action="/getListAction">
		<html:text name="listActionForm" property="ssa"/><br>
		显示数据列表详细信息
		<table>
		<nested:iterate name="listActionForm" property="dataList" id="dataList"> 
		<tr>
			<td>
				<nested:text name="dataList" property="a" indexed="true"/>
			</td>
			<td>
				<nested:text name="dataList" property="b" indexed="true"/> 
			</td>
		</tr>
		</nested:iterate>
		</table>
		<html:button property="T1" value="确定" onclick="login();"/>
		</html:form>
	</body>
</html>
	
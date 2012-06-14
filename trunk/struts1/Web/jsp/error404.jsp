<%@page contentType="text/html;charset=UTF-8"%>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%> 
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%> 

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
		<script language="javascript">
		function toBack(){
			window.history.back();
		}
		</script>
		<title>Error Page</title>
	</head>
	<body>
		<center>
			<h1><font color=red>Error Message</font></h1>
			对不起,你所寻找的资源在服务器上并不存在,请确认后再尝试!
		</center>
		<input type='button' value="BACK" onclick='toBack()'>
	</body>
	<html:text name="data" property="a" indexed="true"/>
</html>

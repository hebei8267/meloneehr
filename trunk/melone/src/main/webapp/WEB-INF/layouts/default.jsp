<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sitemesh" uri="http://www.opensymphony.com/sitemesh/decorator"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>melone POS<sitemesh:title /></title>
		<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />

		<link type="text/css" href="${ctx}/static/css/960grid/css/960.css" rel="stylesheet" />
		<link type="text/css" href="${ctx}/static/css/style.css" rel="stylesheet" />
		<link type="text/css" href="${ctx}/static/css/jqueryValidate/cmxform.css" rel="stylesheet" />
		<link type="text/css" href="${ctx}/static/css/redmond/jquery-ui-1.8.21.custom.css" rel="stylesheet" />
		<link type="text/css" href="${ctx}/static/css/menu/style.css" rel="stylesheet" />
		<link type="text/css" href="${ctx}/static/css/table/style.css" rel="stylesheet" />
		
		<script type="text/javascript" src="${ctx}/static/js/jquery-1.7.2.min.js"></script>
		<script type="text/javascript" src="${ctx}/static/js/jquery.validate.js"></script>
		<script type="text/javascript" src="${ctx}/static/js/jquery.validate_messages_cn.js"></script>
		<script type="text/javascript" src="${ctx}/static/js/jquery-ui-1.8.21.custom.min.js"></script>
		<script type="text/javascript" src="${ctx}/static/js/myScript.js"></script>

		<sitemesh:head />
		<script type="text/javascript">
        $(function () {
	        $('input:text:first').focus();
    	});
    	</script>
	</head>
	<body>
	<div id="__del-confirm" title="删除确认？" style="display:none;">
		<p>确定要删除选择的信息项吗？</p>
	</div>
	<div id="__frame_main">
		<div id="__frame_content">
			<%@ include file="/WEB-INF/layouts/infoList.jsp"%>
			<div class="container_16">
				<sitemesh:body />
			</div>
		</div>
		<div id="__frame_footer">
			<div class="container_16">
			<%@ include file="/WEB-INF/layouts/footer.jsp"%>
			</div>
		</div>
	</div>
	</body>
</html>
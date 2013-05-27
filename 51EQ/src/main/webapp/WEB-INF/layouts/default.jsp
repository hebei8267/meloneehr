<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sitemesh" uri="http://www.opensymphony.com/sitemesh/decorator"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
    <head>
        <title>EQ+
            <sitemesh:title />
        </title>
        <meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <link type="text/css" href="${ctx}/static/css/bootstrap.css" rel="stylesheet">
        <link type="text/css" href="${ctx}/static/css/bootstrap-responsive.css" rel="stylesheet">
        <link type="text/css" href="${ctx}/static/css/datepicker.css" rel="stylesheet">
        <link type="text/css" href="${ctx}/static/css/mystyle.css" rel="stylesheet">
		
        <script src="${ctx}/static/js/jquery-1.7.2.min.js"></script>
        <script src="${ctx}/static/js/bootstrap.js"></script>
        <script src="${ctx}/static/js/bootstrap-datepicker.js"></script>
        <script src="${ctx}/static/js/jquery.validate-1.10.0.js"></script>
        <script src="${ctx}/static/js/jquery.validate_cn.js"></script>

		<script type="text/javascript">
		$(document).ready(function() {
			$(':input:text:first').focus();
		
			$(document).keydown(function(event) {
				if (event.keyCode == 13) {
					$('form').each(function() {
						event.preventDefault();
					});
				}
			});
		});
		</script>

        <sitemesh:head />
    </head>
    <body>
        <%@ include file="/WEB-INF/layouts/infoList.jsp"%>
        <sitemesh:body />
    </body>
</html>
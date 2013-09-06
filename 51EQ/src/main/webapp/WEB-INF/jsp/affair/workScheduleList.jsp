<%@	page contentType="text/html;charset=UTF-8"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@	taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@	taglib prefix="page" uri="http://www.opensymphony.com/sitemesh/page"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@	page import="com.tjhx.common.utils.DateUtils"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"	/>
<c:set var="sc_ctx">
    ${ctx}/sc
</c:set>
<!DOCTYPE html>
<html>
    <head>
    </head>
    <body>
        <%// 系统菜单  %>
        <page:applyDecorator name="menu" />
        
        <div class="container">
            <form method="post"	class="form-horizontal"	id="listForm">
                <div class="row">
                    <div class="span12">
                        <legend>
                            <h3>${sessionScope.__SESSION_USER_INFO.orgName} 排班表 维护</h3>
                        </legend>
                    </div>
                    
                    <div class="span12"	style="margin-top: 10px;">
                    	<table class="table	table-striped table-bordered table-condensed mytable">
                            <thead>
                                <tr>
                                    <th width="100" class="center">
                                        日期
                                    </th>
                                    <th width="50" class="center">
                                        星期
                                    </th>
                                    <c:forEach items="${empList}" var="emp">
                                    <th class="center">
                                        ${emp.name}
                                    </th>
                                    </c:forEach>
                                </tr>
                            </thead>
                            <tbody>
	                            <c:forEach items="${wsList}" var="ws">
	                            <tr>
	                            	<td class="center">${ws.scheduleDate}</td>
	                            	<td class="center">${ws.week}</td>
	                            </tr>
								</c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </form>
        </div>
    </body>
</html>
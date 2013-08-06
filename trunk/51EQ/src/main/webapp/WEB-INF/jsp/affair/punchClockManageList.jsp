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
    	<script>
	        $(function() {
	        	$("#listForm").validate({
	                rules : {
	                	optDateShow : {
	                		required : true
	                    }
	                }
	            });
	        	
	            $('#optDateShow').datepicker({
	            	format : 'yyyy-mm',
	            	viewMode : 1,
	            	minViewMode : 1
	            });
	            
	            $("#searchBtn").click(function() {
                    $("input[type='text'],textarea").each(function(i) {
                        this.value = $.trim(this.value);
                    });

                    $("#listForm").attr("action", "${sc_ctx}/punchClock/manage/search");
                    $("#listForm").submit();
                });
	        });
        </script>
    </head>
    <body>
        <%// 系统菜单  %>
        <page:applyDecorator name="menu" />
        
        
        <div class="container">
            <form method="post"	class="form-horizontal"	id="listForm">
                <div class="row">
                    <div class="span12">
                        <legend>
                            <h3>考勤信息</h3>
                        </legend>
                    </div>
                    <div class="span3">
                        <label class="control-label">考勤日期 :</label>
                        <input id="optDateShow" name="optDateShow" type="text" class="input-medium" value="${optDateShow }"/>
                    </div>
                    <div class="span9">
                        <label class="control-label">机构 :</label>
                        <select name="orgId" class="input-medium">
                            <c:forEach items="${orgList}" var="org">
                                <c:if test="${org.key == orgId}">
                                    <option value="${org.key }" selected>${org.value }</option>
                                </c:if>
                                <c:if test="${org.key != orgId}">
                                    <option value="${org.key }">${org.value }</option>
                                </c:if>
                            </c:forEach>
                        </select>
                        <button	id="searchBtn" class="btn	btn-primary" type="button">查询</button>
                    </div>
                    <div class="span12"	style="margin-top: 10px;">
                        <table class="table	table-striped table-bordered table-condensed mytable">
                            <thead>
                                <tr>
                                    <th width="100" class="center">
                                        日期
                                    </th>
                                    <c:forEach items="${empList}" var="emp">
                                    <th class="center">
                                        ${emp.name}
                                    </th>
                                    </c:forEach>
                                </tr>
                            </thead>
                            <tbody>
                          
                            <c:forEach items="${punchClockList}" var="punchClock">
                            <tr>
                            	<td>${punchClock.clockTime}</td>
                            	<c:forEach items="${punchClock.punchClockList}" var="subPunchClock">
                            	<td class="center">${subPunchClock.clockTimeStart}<br>～<br>${subPunchClock.clockTimeEnd}</td>
                            	</c:forEach>
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
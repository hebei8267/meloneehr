<%@	page contentType="text/html;charset=UTF-8"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@	taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@	taglib prefix="page" uri="http://www.opensymphony.com/sitemesh/page"%>
<%@	page import="com.tjhx.common.utils.DateUtils"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"	/>
<c:set var="sc_ctx">
    ${ctx}/sc
</c:set>
<!DOCTYPE html>
<html>
    <head>
    	<style type="text/css">
            .form-horizontal .control-label {
                width: 70px;
            }
        </style>
    </head>
    <body>
        <%// 系统菜单  %>
        <page:applyDecorator name="menu" />
        
        <div class="container">
        	<form method="post"	class="form-horizontal"	id="listForm">
        		<div class="row">
                    <div class="span12">
                        <legend>
                            <h3>职员管理</h3>
                        </legend>
                    </div>
                    
                    <div class="span4">
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
                                    <th	width="25" class="center">
                                        <input id="checkAll" type="checkbox" />
                                    </th>
                                    <th width="80">
                                        账户状态
                                    </th>
                                    <th>
                                        用户帐号
                                    </th>
                                    <th>
                                        用户名称
                                    </th>
                                    <th>
                                        用户角色
                                    </th>
                                    <th>
                                        所属机构
                                    </th>
                                    <th	width="55">
                                        &nbsp;
                                    </th>
                                </tr>
                            </thead>
                            <tbody>
                        	</tbody>
                        </table>
                    </div>
                </div>
        	</form>
        </div>
    </body>
</html>
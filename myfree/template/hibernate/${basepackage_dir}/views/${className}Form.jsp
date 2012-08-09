<#include "/macro.include"/>
<#include "/java_copyright.include">
<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="page" uri="http://www.opensymphony.com/sitemesh/page"%>
<c:set var="ctx" value="${r"${pageContext.request.contextPath}"}"/>
<c:set var="sc_ctx">${r"${"}ctx}/sc</c:set>
<c:set var="pop_sc_ctx">${r"${"}ctx}/popsc</c:set>
<html>
	<head>
		<script>
			$().ready(function() {
				$("#inputForm").validate({
					rules: {
						// ？？？？？？？？？？？？？？？？？？？？？？
						// 添加表单效验
					}
				});

				$("#saveBtn,#cancelBtn").button();
				
				$("#cancelBtn").click(function() {
					// ？？？？？？？？？？？？？？？？？？？？？？
					$(location).attr('href', '${r"$"}{sc_ctx}/${classNameLower}/list');
				});

				$("#saveBtn").click(function() {
					$("input[type='text'],textarea").each(function(i){
  						this.value = $.trim(this.value);
 					});
					// ？？？？？？？？？？？？？？？？？？？？？？
					$("#inputForm").attr("action", "${r"$"}{sc_ctx}/${classNameLower}/save");
					$("#inputForm").submit();
				});
			});
		</script>
	</head>
	<body>
		<%// 系统菜单  %>
		<page:applyDecorator name="menu" >
			 <page:param name="sysCfg">current</page:param>
		</page:applyDecorator>

		<div class="grid_16 titleNav">
			// ？？？？？？？？？？？？？？？？？？？？？？
			<h2><a>配置管理</a>&#8711; <a>门店相关</a>&#8711; <a>仓库类型</a>&#8711;
			<c:if test="${r"$"}{empty ${classNameLower}.uuid}">
			新增
			</c:if>
			<c:if test="${r"$"}{!empty ${classNameLower}.uuid}">
			编辑
			</c:if>
			</h2>
		</div>
		<div class="clear"></div>

		<form:form method="POST" class="form cmxform" id="inputForm" modelAttribute="${classNameLower}">
			<form:hidden path="uuid"/>
			<table>
				<tr>
					<td class="item_name" width="100px">???????编号:</td>
					<td class="item">
					<c:if test="${r"${"}empty ${classNameLower}.uuid}" >
						<form:input path="id" class="text ui-widget-content ui-corner-all"/>
					</c:if>
					<c:if test="${r"${"}!empty ${classNameLower}.uuid}" >
						&nbsp;${r"${"}${classNameLower}.id}
					</c:if>
					</td>
				</tr>
				<#list table.columns as column>
				<tr>
					<td class="item_name" width="100px">${column.remarks}:</td>
					<td class="item">
						<form:input path="${column.columnNameLower}" class="text ui-widget-content ui-corner-all"/>
					</td>
				</tr>
				</#list>
				<tr>
					<td></td>
					<td class="item">
						<input id="saveBtn" type="button" class="submit" value="保存"/>
						&nbsp;&nbsp;&nbsp;
						<input id="cancelBtn" type="button" class="submit" value="取消"/>
					</td>
				</tr>
			</table>
		</form:form>
	</body>
</html>
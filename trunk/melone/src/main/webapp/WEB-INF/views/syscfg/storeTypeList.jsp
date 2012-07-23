<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="page" uri="http://www.opensymphony.com/sitemesh/page"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<c:set var="sc_ctx">${ctx}/sc</c:set>
<html>
	<head>
		<script>		
			$().ready(function() {
				$("#createBtn,#delBtn").button();

				$("#createBtn").click(function() {
					
				});
				
				$("#delBtn").click(function() {
					
				});
			});
		</script>
	</head>
	<body>
		<%// 系统菜单  %>
		<page:applyDecorator name="menu" >
			 <page:param name="sysCfg">current</page:param>
		</page:applyDecorator>
		
		<div class="grid_16">
			<h2><a>配置管理</a>&#8711; <a>门店相关</a>&#8711; <a>仓库类型</a></h2>
		</div>
		<div class="clear"></div>
		
		<div class="grid_16">
			<input id="createBtn" type="button" class="submit" value="新增"/>
			<input id="delBtn" type="button" class="submit" value="删除"/>
		</div>
		<div class="clear"></div>
		
		<div class="grid_16">
			<table id="rounded-corner">
				<thead>
					<tr>
						<th class="rounded-left" width="10">
							<input type="checkbox"></input>
						</th>
						<th width="150">仓库类型名称</th>
						<th width="280">详细描述</th>
						<th width="90" class="rounded-right">操作</th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${empty storeTypeList}" >
					<tfoot>
						<tr>
							<td colspan="3" class="rounded-foot-left"><em>无记录</em></td>
							<td class="rounded-foot-right"> &nbsp; </td>
						</tr>
					</tfoot>
					</c:if>
					<c:forEach items="${storeTypeList}" var="storeType">
					<tr>
						<td>
							<input type="checkbox" name="storeType" value="${storeType.uuid}"></input>
						</td>
						<td>
							${storeType.name}&nbsp;
						</td>
						<td>
							${storeType.descTxt}&nbsp;
						</td>
						<td>
							<a href="${sc_ctx}/syscfg/storeType/get/${storeType.uuid}">编辑</a>
						</td>
					</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<div class="clear"></div>
	</body>
</html>
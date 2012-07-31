<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="page" uri="http://www.opensymphony.com/sitemesh/page"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<c:set var="sc_ctx">${ctx}/sc</c:set>
<c:set var="pop_sc_ctx">${ctx}/popsc</c:set>
<html>
	<head>
		<script>		
			$().ready(function() {
				$("#inputForm").validate({
					rules: {
						name: {
							required: true,
							maxlength: 32
						},
						descTxt: {
							maxlength: 255
						}
					}
				});
				
				$("#saveBtn,#cancelBtn").button();
				
				$("#cancelBtn").click(function() {
					$(location).attr('href', '${sc_ctx}/product/productBrand/list');
				});
				
				$("#saveBtn").click(function() {
					$("#inputForm").attr("action", "${sc_ctx}/product/productBrand/save");
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
			<h2><a>配置管理</a>&#8711; <a>产品相关</a>&#8711; <a>产品品牌</a>&#8711; 
			<c:if test="${empty productBrand.uuid}" >
			新增
			</c:if>
			<c:if test="${!empty productBrand.uuid}" >
			编辑
			</c:if>
			</h2>
		</div>
		<div class="clear"></div>
		
		<form:form method="POST" class="form cmxform" id="inputForm" modelAttribute="productBrand">
			<form:hidden path="uuid"/>
			<table>
				<tr>
					<td class="item_name" width="100px">产品品牌名称:</td>
					<td class="item">
						<form:input path="name" class="text ui-widget-content ui-corner-all"/>
					</td>
				</tr>
				<tr>
					<td class="item_name" width="100px">详细描述:</td>
					<td class="item">
						<form:textarea path="descTxt" class="text ui-widget-content ui-corner-all"/>
					</td>
				</tr>				
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
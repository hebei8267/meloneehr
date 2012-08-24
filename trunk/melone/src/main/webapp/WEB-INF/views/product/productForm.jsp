<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="page" uri="http://www.opensymphony.com/sitemesh/page"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<c:set var="sc_ctx">${ctx}/sc</c:set>
<c:set var="pop_sc_ctx">${ctx}/popsc</c:set>
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
					$(location).attr('href', '${sc_ctx}/product/list');
				});

				$("#saveBtn").click(function() {
					$("input[type='text'],textarea").each(function(i){
  						this.value = $.trim(this.value);
 					});
					$("#inputForm").attr("action", "${sc_ctx}/product/save");
					$("#inputForm").submit();
				});
			});
		</script>
	</head>
	<body>
		<%// 系统菜单  %>
		<page:applyDecorator name="menu" />

		<div class="grid_16 titleNav">
			// ？？？？？？？？？？？？？？？？？？？？？？
			<h2><a>配置管理</a>&#8711; <a>门店相关</a>&#8711; <a>仓库类型</a>&#8711;
			<c:if test="${empty product.uuid}">
			新增
			</c:if>
			<c:if test="${!empty product.uuid}">
			编辑
			</c:if>
			</h2>
		</div>
		<div class="clear"></div>

		<form:form method="POST" class="form cmxform" id="inputForm" modelAttribute="product">
			<form:hidden path="uuid"/>
			<table>
				<tr>
					<td class="item_name" width="100px">???????编号:</td>
					<td class="item">
					<c:if test="${empty product.uuid}" >
						<form:input path="id" class="text ui-widget-content ui-corner-all"/>
					</c:if>
					<c:if test="${!empty product.uuid}" >
						&nbsp;${product.id}
					</c:if>
					</td>
				</tr>
				
				
				<tr>
					<td class="item_name" width="100px">:</td>
					<td class="item">
						<form:input path="barCode" class="text ui-widget-content ui-corner-all"/>
					</td>
				</tr>
				<tr>
					<td class="item_name" width="100px">:</td>
					<td class="item">
						<form:input path="descTxt" class="text ui-widget-content ui-corner-all"/>
					</td>
				</tr>
				<tr>
					<td class="item_name" width="100px">:</td>
					<td class="item">
						<form:input path="id" class="text ui-widget-content ui-corner-all"/>
					</td>
				</tr>
				<tr>
					<td class="item_name" width="100px">:</td>
					<td class="item">
						<form:input path="memberPrice" class="text ui-widget-content ui-corner-all"/>
					</td>
				</tr>
				<tr>
					<td class="item_name" width="100px">:</td>
					<td class="item">
						<form:input path="name" class="text ui-widget-content ui-corner-all"/>
					</td>
				</tr>
				<tr>
					<td class="item_name" width="100px">:</td>
					<td class="item">
						<form:input path="refPrice" class="text ui-widget-content ui-corner-all"/>
					</td>
				</tr>
				<tr>
					<td class="item_name" width="100px">:</td>
					<td class="item">
						<form:input path="retailPrice" class="text ui-widget-content ui-corner-all"/>
					</td>
				</tr>
				<tr>
					<td class="item_name" width="100px">:</td>
					<td class="item">
						<form:input path="wholeSalePrice" class="text ui-widget-content ui-corner-all"/>
					</td>
				</tr>
				<tr>
					<td class="item_name" width="100px">:</td>
					<td class="item">
						<form:input path="productBrand" class="text ui-widget-content ui-corner-all"/>
					</td>
				</tr>
				<tr>
					<td class="item_name" width="100px">:</td>
					<td class="item">
						<form:input path="productSupplier" class="text ui-widget-content ui-corner-all"/>
					</td>
				</tr>
				<tr>
					<td class="item_name" width="100px">:</td>
					<td class="item">
						<form:input path="productTag" class="text ui-widget-content ui-corner-all"/>
					</td>
				</tr>
				<tr>
					<td class="item_name" width="100px">:</td>
					<td class="item">
						<form:input path="productType" class="text ui-widget-content ui-corner-all"/>
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
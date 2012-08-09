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
						id: {
							required: true,
							maxlength: 16
						},
						name: {
							required: true,
							maxlength: 32
						},
						contact: {
							required: true,
							maxlength: 16
						},
						telNum: {
							required: true,
							maxlength: 32
						},
						email: {
							email: true,
							maxlength: 32
						},
						faxNum: {
							isTel: true,
							maxlength: 32
						},
						addr: {
							maxlength: 255
						},
						postCode: {
							zipCode: true
						},
						bankName: {
							maxlength: 32
						},
						bankAccount: {
							maxlength: 32
						},
						descTxt: {
							maxlength: 255
						}
					}
				});
				
				$("#saveBtn,#cancelBtn").button();
				
				$("#cancelBtn").click(function() {
					$(location).attr('href', '${sc_ctx}/product/productSupplier/list');
				});
				
				$("#saveBtn").click(function() {
					$("input[type='text'],textarea").each(function(i){
  						this.value = $.trim(this.value);
 					});
					$("#inputForm").attr("action", "${sc_ctx}/product/productSupplier/save");
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
			<h2><a>配置管理</a>&#8711; <a>商品相关</a>&#8711; <a>供应商（商品）</a>&#8711; 
			<c:if test="${empty productSupplier.uuid}" >
			新增
			</c:if>
			<c:if test="${!empty productSupplier.uuid}" >
			编辑
			</c:if>
			</h2>
		</div>
		<div class="clear"></div>
		
		<form:form method="POST" class="form cmxform" id="inputForm" modelAttribute="productSupplier">
			<form:hidden path="uuid"/>
			<table>
				<tr>
					<td class="item_name" width="100px">供应商编号:</td>
					<td class="item">
					<c:if test="${empty productSupplier.uuid}" >
						<form:input path="id" class="text ui-widget-content ui-corner-all"/>
					</c:if>
					<c:if test="${!empty productSupplier.uuid}" >
						&nbsp;${productSupplier.id}
					</c:if>
					</td>
					<td class="item_name" width="100px">供应商名称:</td>
					<td class="item">
						<form:input path="name" class="text ui-widget-content ui-corner-all"/>
					</td>
				</tr>
				<tr>
					<td class="item_name" width="100px">联系人:</td>
					<td class="item">
						<form:input path="contact" class="text ui-widget-content ui-corner-all"/>
					</td>
					<td class="item_name" width="100px">电话号码:</td>
					<td class="item">
						<form:input path="telNum" class="text ui-widget-content ui-corner-all"/>
					</td>
				</tr>
				<tr>
					<td class="item_name" width="100px">Email:</td>
					<td class="item">
						<form:input path="email" class="text ui-widget-content ui-corner-all"/>
					</td>
					<td class="item_name" width="100px">传真号码:</td>
					<td class="item">
						<form:input path="faxNum" class="text ui-widget-content ui-corner-all"/>
					</td>
				</tr>
				<tr>
					<td class="item_name" width="100px">供应商地址:</td>
					<td class="item">
						<form:input path="addr" class="text ui-widget-content ui-corner-all"/>
					</td>
					<td class="item_name" width="100px">邮编:</td>
					<td class="item">
						<form:input path="postCode" class="text ui-widget-content ui-corner-all"/>
					</td>
				</tr>
				<tr>
					<td class="item_name" width="100px">打款银行名称:</td>
					<td class="item">
						<form:input path="bankName" class="text ui-widget-content ui-corner-all"/>
					</td>
					<td class="item_name" width="100px">打款银行账号:</td>
					<td class="item">
						<form:input path="bankAccount" class="text ui-widget-content ui-corner-all"/>
					</td>
				</tr>
				<tr>
					<td class="item_name" width="100px">税务登记号:</td>
					<td class="item">
						<form:input path="taxRegNum" class="text ui-widget-content ui-corner-all"/>
					</td>
				</tr>
				<tr>
					<td class="item_name" width="100px">详细描述:</td>
					<td class="item" colspan="3">
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
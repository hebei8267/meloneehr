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
						storeIds: {
							required: true
						},
						telNum: {
							required: true,
							isPhone: true
						},
						faxNum: {
							isTel: true
						},
						addr: {
							required: true,
							maxlength: 255
						},
						descTxt: {
							maxlength: 255
						}
					}
				});
				
				$("#saveBtn,#cancelBtn,#lMove,#rMove").button();
				
				$("#cancelBtn").click(function() {
					$(location).attr('href', '${sc_ctx}/syscfg/shop/list');
				});
				
				$("#saveBtn").click(function() {
					$("input[type='text'],textarea").each(function(i){
  						this.value = $.trim(this.value);
 					});
					
					// 保存选择仓库
					var _storeIdsItem = $("#storeIds option");
					for(var i = 0; i < _storeIdsItem.length; i++) {
						_storeIdsItem[i].selected = true;
					}
					
					$("#inputForm").attr("action", "${sc_ctx}/syscfg/shop/save");
		        	$("#inputForm").submit();
				});
				
				$("#lMove").click(function() {
					moveShowItems('noStoreIds', 'storeIds');
				});
				$("#rMove").click(function() {
					moveShowItems('storeIds', 'noStoreIds');
				});
			});			
		</script>
	</head>
	<body>
		<%// 系统菜单  %>
		<page:applyDecorator name="menu" />
		
		<div class="grid_16 titleNav">
			<h2><a>配置管理</a>&#8711; <a>门店相关</a>&#8711; <a>门店</a>&#8711; 
			<c:if test="${empty shop.uuid}" >
			新增
			</c:if>
			<c:if test="${!empty shop.uuid}" >
			编辑
			</c:if>
			</h2>
		</div>
		<div class="clear"></div>
		
		<form:form method="POST" class="form cmxform" id="inputForm" modelAttribute="shop">
			<form:hidden path="uuid"/>
			<table>
				<tr>
					<td class="item_name" width="100px">门店编号:</td>
					<td class="item">
					<c:if test="${empty shop.uuid}" >
						<form:input path="id" class="text ui-widget-content ui-corner-all"/>
					</c:if>
					<c:if test="${!empty shop.uuid}" >
						&nbsp;${shop.id}
					</c:if>
					</td>
				</tr>
				<tr>
					<td class="item_name" width="100px">门店名称:</td>
					<td class="item">
						<form:input path="name" class="text ui-widget-content ui-corner-all"/>
					</td>
				</tr>
				<tr>
					<td></td>
					<td>仓库</td>
					<td></td>
					<td>已关联仓库</td>
				</tr>
				<tr>
					<td class="item_name" width="100px">仓库关联:</td>
					<td class="item">
						<form:select path="noStoreIds" multiple="true" class="text ui-widget-content ui-corner-all" size="6">
							<form:options items="${shop.noStoreList}" itemValue="uuid"  itemLabel="name"/>
						</form:select>
					</td>
					<td width="120px" align="center">
						<table>
							<tr><td><input type="button" id="lMove" class="submit" value="->" /></td></tr>
							<tr><td><input type="button" id="rMove" class="submit" value="<-" /></td></tr>
						</table>
					</td>
					<td class="item">
						<form:select path="storeIds" multiple="true" class="text ui-widget-content ui-corner-all" size="6">
							<form:options items="${shop.storeList}" itemValue="uuid" itemLabel="name"/>
						</form:select>
					</td>	
				</tr>
				<tr>
					<td class="item_name" width="100px">电话号码:</td>
					<td class="item">
						<form:input path="telNum" class="text ui-widget-content ui-corner-all"/>
					</td>
				</tr>
				<tr>
					<td class="item_name" width="100px">传真号码:</td>
					<td class="item">
						<form:input path="faxNum" class="text ui-widget-content ui-corner-all"/>
					</td>
				</tr>
				<tr>
					<td class="item_name" width="100px">门店地址:</td>
					<td class="item">
						<form:input path="addr" class="text ui-widget-content ui-corner-all"/>
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
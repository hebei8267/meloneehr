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
						// 添加表单效验
						name: {
							required: true,
							maxlength: 32
						},
						descTxt: {
							maxlength: 255
						}
					}
				});

				$("#saveBtn,#cancelBtn,#lMove,#rMove").button();
				
				$("#cancelBtn").click(function() {
					$(location).attr('href', '${sc_ctx}/account/role/list');
				});

				$("#saveBtn").click(function() {
					$("input[type='text'],textarea").each(function(i){
  						this.value = $.trim(this.value);
 					});
					$("#inputForm").attr("action", "${sc_ctx}/account/role/save");
					$("#inputForm").submit();
				});
				
				$("#lMove").click(function() {
					moveShowItems('noPermissionIds', 'permissionIds');
				});
				$("#rMove").click(function() {
					moveShowItems('permissionIds', 'noPermissionIds');
				});
			});
			// 实现两个多选框的左右移动
			function moveShowItems(srcObjId, discObjId) {
				var srcObj = document.getElementById(srcObjId);
				var discObj = document.getElementById(discObjId);
				if(srcObj != null && srcObj.options != null && discObj != null && discObj.options != null) {
					var itemListOptions = srcObj.options;
					if(itemListOptions != null && itemListOptions.length > 0) {
						var values = new Array();
						for(var i = itemListOptions.length - 1; i >= 0; i--) {
							if(itemListOptions[i].selected == true) {
								// new an Option
								var addrOption = document.createElement("OPTION");
								addrOption.value = itemListOptions[i].value;
								addrOption.text = itemListOptions[i].text;
								values.push(addrOption.value);
								// add Option
								discObj.options.add(addrOption);
								itemListOptions.remove(i);
							}
						}
					}
				}
			}
		</script>
	</head>
	<body>
		<%// 系统菜单  %>
		<page:applyDecorator name="menu" />

		<div class="grid_16 titleNav">
			<h2><a>配置管理</a>&#8711; <a>安全相关</a>&#8711; <a>角色</a>&#8711;
			<c:if test="${empty role.uuid}">
			新增
			</c:if>
			<c:if test="${!empty role.uuid}">
			编辑
			</c:if>
			</h2>
		</div>
		<div class="clear"></div>

		<form:form method="POST" class="form cmxform" id="inputForm" modelAttribute="role">
			<form:hidden path="uuid"/>
			<table>
				<tr>
					<td class="item_name" width="100px">角色名称:</td>
					<td class="item">
						<form:input path="name" class="text ui-widget-content ui-corner-all"/>
					</td>
				</tr>
				<tr>
					<td></td>
					<td>权限</td>
					<td></td>
					<td>拥有权限</td>
				</tr>
				<tr>
					<td class="item_name" width="100px">权限管理:</td>
					<td class="item">
						<form:select path="noPermissionIds" multiple="true" class="text ui-widget-content ui-corner-all">
							<form:option value="1" label="1"/>
							<form:option value="2" label="2"/>
							<form:option value="3" label="3"/>
							<form:option value="4" label="4"/>
							<form:option value="4" label="4"/>
							<form:option value="4" label="4"/>
							<form:option value="4" label="4"/>
							<form:option value="4" label="4"/>
						</form:select>
					</td>
					<td width="120px" align="center">
						<table>
							<tr><td><input type="button" id="lMove" class="submit" value=">>" /></td></tr>
							<tr><td><input type="button" id="rMove" class="submit" value="<<" /></td></tr>
						</table>
					</td>
					<td class="item">
						<form:select path="permissionIds" multiple="true" class="text ui-widget-content ui-corner-all"></form:select>
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
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
					
					// 保存选择资源菜单
					var _funIdsItem = $("#funIds option");
					for(var i = 0; i < _funIdsItem.length; i++) {
						_funIdsItem[i].selected = true;
					}
					
					$("#inputForm").attr("action", "${sc_ctx}/account/role/save");
					$("#inputForm").submit();
				});
				
				$("#lMove").click(function() {
					moveShowItems('noFunIds', 'funIds');
				});
				$("#rMove").click(function() {
					moveShowItems('funIds', 'noFunIds');
				});
			});
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
						<form:select path="noFunIds" multiple="true" class="text ui-widget-content ui-corner-all" size="6">
							<form:options items="${role.noFunList}" itemValue="uuid"  itemLabel="displayName"/>
						</form:select>
					</td>
					<td width="120px" align="center">
						<table>
							<tr><td><input type="button" id="lMove" class="submit" value="->" /></td></tr>
							<tr><td><input type="button" id="rMove" class="submit" value="<-" /></td></tr>
						</table>
					</td>
					<td class="item">
						<form:select path="funIds" multiple="true" class="text ui-widget-content ui-corner-all" size="6">
							<form:options items="${role.funList}" itemValue="uuid" itemLabel="displayName"/>
						</form:select>
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
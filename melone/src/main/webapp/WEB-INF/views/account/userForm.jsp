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
						loginName: {
							required: true,
							maxlength: 32
						},
						passWord: {
							minlength: 6,
							maxlength: 16
						},
						passWord2: {
							equalTo: "#passWord"
						},
						name: {
							required: true,
							maxlength: 32
						},
						telNum: {
							isPhone: true
						},
						roleUuid: "required",
						shopId: "required",
						descTxt: {
							maxlength: 255
						},
						imgFile: {
							img: true
						}
					}
				});
				<%// 新增用户时需效验【用户密码】必输入%>
				<c:if test="${empty user.uuid}">
				$("#passWord").rules("add",{required : true});
				</c:if>
				
				$("#saveBtn,#cancelBtn").button();
				
				$("#cancelBtn").click(function() {
					$(location).attr('href', '${sc_ctx}/account/user/list');
				});

				$("#saveBtn").click(function() {
					$("input[type='text'],textarea").each(function(i){
  						this.value = $.trim(this.value);
 					});
					$("#inputForm").attr("action", "${sc_ctx}/account/user/save");
					$("#inputForm").submit();
				});
				
				// 文件预览
				$("#imgFile").change(function() {
					var file = this.files[0];
					var maxWidth = 150;
					var maxHeight = 200;
					
					$("#img").onload = function() {
						var rect = _clacImgZoomParam(maxWidth, maxHeight, img.offsetWidth, img.offsetHeight);
						img.width = rect.width;
						img.height = rect.height;
						img.style.marginLeft = rect.left + 'px';
						img.style.marginTop = rect.top + 'px';
					}
					
					var reader = new FileReader();
					reader.onload = function(evt) {
						$("#img").attr("src", evt.target.result);
					}
					
					reader.readAsDataURL(file);
				});
				
			});
		</script>
	</head>
	<body>
		<%// 系统菜单  %>
		<page:applyDecorator name="menu" />

		<div class="grid_16 titleNav">
			<h2><a>配置管理</a>&#8711; <a>安全相关</a>&#8711; <a>用户</a>&#8711;
			<c:if test="${empty user.uuid}">
			新增
			</c:if>
			<c:if test="${!empty user.uuid}">
			编辑
			</c:if>
			</h2>
		</div>
		<div class="clear"></div>

		<form:form method="POST" class="form cmxform" id="inputForm" modelAttribute="user" enctype="multipart/form-data">
			<form:hidden path="uuid"/>
			<table>
				<tr>
					<td class="item_name" width="100px">登录名称:</td>
					<td class="item">
					<c:if test="${empty user.uuid}" >
						<form:input path="loginName" class="text ui-widget-content ui-corner-all"/>
					</c:if>
					<c:if test="${!empty user.uuid}" >
						&nbsp;${user.loginName}
					</c:if>
					</td>
					<td align="center" rowspan="7" style="padding-left: 40px;vertical-align: top;">
						<table>
							<tr>
								<td><img id="img" height="200px" width="150px" style="border: 1px;" src="${ctx}/photoServlet?photoName=${user.photoName}&type=userPhoto"/></td>
							</tr>
							<tr><%// 图像文件上传 %>
                    			<td colspan="3">上传照片:&nbsp;<input id="imgFile" name="imgFile" type="file" size="1"/></td>
                			</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td class="item_name" width="100px">登录密码:</td>
					<td class="item">
						<input id="passWord" name="passWord" class="text ui-widget-content ui-corner-all" type="password" value=""/>
					</td>
				</tr>
				<tr>
					<td class="item_name" width="100px">确认密码:</td>
					<td class="item">
						<input id="passWord2" name="passWord2" class="text ui-widget-content ui-corner-all" type="password" value=""/>
					</td>
				</tr>
				<tr>
					<td class="item_name" width="100px">用户姓名:</td>
					<td class="item">
						<form:input path="name" class="text ui-widget-content ui-corner-all"/>
					</td>
				</tr>
				<tr>
					<td class="item_name" width="100px">联系电话:</td>
					<td class="item">
						<form:input path="telNum" class="text ui-widget-content ui-corner-all"/>
					</td>
				</tr>
				<tr>
					<td class="item_name" width="100px">门店:</td>
					<td class="item">
						<form:select path="shopId" class="text ui-widget-content ui-corner-all">
							<form:option value="" label="请选择......"/>
							<form:options items="${shopList}" itemValue="id"  itemLabel="name"/>
						</form:select>
					</td>
				</tr>
				<tr>
					<td class="item_name" width="100px">角色:</td>
					<td class="item">
						<form:select path="roleUuid" class="text ui-widget-content ui-corner-all">
							<form:option value="" label="请选择......"/>
							<form:options items="${roleList}" itemValue="uuid"  itemLabel="name"/>
						</form:select>
					</td>
				</tr>				
				<tr>
					<td class="item_name" width="100px">Email地址:</td>
					<td class="item">
						<form:input path="email" class="text ui-widget-content ui-corner-all"/>
					</td>
				</tr>
				<tr>
					<td class="item_name" width="100px">详细描述:</td>
					<td class="item" colspan="2">
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
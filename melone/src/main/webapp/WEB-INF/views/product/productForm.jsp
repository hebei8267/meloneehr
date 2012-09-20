<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="page" uri="http://www.opensymphony.com/sitemesh/page"%>
<%@ page import="com.tjhx.globals.Constants" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<c:set var="sc_ctx">${ctx}/sc</c:set>
<c:set var="pop_sc_ctx">${ctx}/popsc</c:set>
<html>
	<head>
		<script>
			$().ready(function() {
				$("#inputForm").validate({
					rules: {
						barCode: {
							required: true,
							alNum: true,
							maxlength: 16
						},
						name: {
							required: true,
							maxlength: 32
						},
						retailPrice: {
							required: true,
							number: true
						},
						memberPrice: {
							number: true
						},
						wholeSalePrice: {
							number: true
						},
						productTypeUuid: {
							required: true
						},
						productBrandUuid: {
							required: true
						},
						productSupplierId: {
							required: true
						},
						descTxt: {
							maxlength: 255
						},
						imgFile: {
							required: true
						}
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
				
				// 文件预览
				$("#imgFile").change(function() {
					var file = this.files[0];
					var maxWidth = <%=Constants.PHOTO_IMG_WIDTH %>;
					var maxHeight = <%=Constants.PHOTO_IMG_HEIGHT %>;
					
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
			<h2><a>配置管理</a>&#8711; <a>商品相关</a>&#8711; <a>商品</a>&#8711;
			<c:if test="${empty product.uuid}">
			新增
			</c:if>
			<c:if test="${!empty product.uuid}">
			编辑
			</c:if>
			</h2>
		</div>
		<div class="clear"></div>

		<form:form method="POST" class="form cmxform" id="inputForm" modelAttribute="product" enctype="multipart/form-data">
			<form:hidden path="uuid"/>
			<table>
				<tr>
					<td class="item_name" width="120px">商品编号/条形码:</td>
					<td class="item">
					<c:if test="${empty product.uuid}" >
						<form:input path="barCode" class="text ui-widget-content ui-corner-all"/>
					</c:if>
					<c:if test="${!empty product.uuid}" >
						&nbsp;${product.barCode}
					</c:if>
					</td>
					<td align="center" rowspan="7" style="padding-left: 40px;vertical-align: top;">
						<table>
							<tr>
								<td><img id="img" height="<%=Constants.PHOTO_IMG_HEIGHT %>px" width="<%=Constants.PHOTO_IMG_WIDTH %>px" style="border: 1px;" src="${ctx}/photoServlet?photoName=${product.photoName}"/></td>
							</tr>
							<tr><%// 图像文件上传 %>
                    			<td colspan="3">上传照片:&nbsp;<input id="imgFile" name="imgFile" type="file" size="1"/></td>
                			</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td class="item_name">商品名称:</td>
					<td class="item">
						<form:input path="name" class="text ui-widget-content ui-corner-all"/>
					</td>
				</tr>
				<tr>
					<td class="item_name">参考进价:</td>
					<td class="item">
						<c:if test="${empty product.refPrice}" >
						&nbsp;无
						</c:if>
						<c:if test="${!empty product.refPrice}" >
						&nbsp;${product.refPrice}&nbsp;元
						</c:if>
					</td>
				</tr>
				<tr>
					<td class="item_name">零售价:</td>
					<td class="item">
						<form:input path="retailPrice" class="text ui-widget-content ui-corner-all"/>
						&nbsp;元
					</td>
				</tr>
				<tr>
					<td class="item_name">会员价:</td>
					<td class="item">
						<form:input path="memberPrice" class="text ui-widget-content ui-corner-all"/>
						&nbsp;元
					</td>
				</tr>
				<tr>
					<td class="item_name">批发价:</td>
					<td class="item">
						<form:input path="wholeSalePrice" class="text ui-widget-content ui-corner-all"/>
						&nbsp;元
					</td>
				</tr>
				<tr>
					<td class="item_name">商品类型:</td>
					<td class="item">
						<form:select path="productTypeUuid" class="text ui-widget-content ui-corner-all">
							<form:option value="" label="请选择......"/>
							<form:options items="${productTypeList}" itemValue="uuid"  itemLabel="name"/>
						</form:select>
					</td>
				</tr>
				<tr>
					<td class="item_name">商品品牌:</td>
					<td class="item">
						<form:select path="productBrandUuid" class="text ui-widget-content ui-corner-all">
							<form:option value="" label="请选择......"/>
							<form:options items="${productBrandList}" itemValue="uuid"  itemLabel="name"/>
						</form:select>
					</td>
				</tr>
				<tr>
					<td class="item_name">商品供应商:</td>
					<td class="item">
						<form:select path="productSupplierId" class="text ui-widget-content ui-corner-all">
							<form:option value="" label="请选择......"/>
							<form:options items="${productSupplierList}" itemValue="id"  itemLabel="name"/>
						</form:select>
					</td>
				</tr>
				<tr>
					<td class="item_name">详细描述:</td>
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
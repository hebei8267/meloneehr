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
				$("#listForm").validate({
					rules: {
						// 添加表单效验
						delBtn: {
							requiredSelect: 'uuid'
						}
					}
				});

				//--------------------------------------------------------------------
				// 全选/全部选
				//--------------------------------------------------------------------
				$("#checkAll").click(function() {
					$('input[name="uuid"]').attr("checked",this.checked);
				});
				var $subCheckBox = $("input[name='uuid']");
				$subCheckBox.click(function(){
					$("#checkAll").attr("checked",$subCheckBox.length == $("input[name='uuid']:checked").length ? true : false);
				});

				//--------------------------------------------------------------------
				// 按钮CSS
				//--------------------------------------------------------------------
				$("#newBtn,#delBtn,#serchBtn").button();

				//--------------------------------------------------------------------
				// 新增按钮点击
				//--------------------------------------------------------------------
				$("#newBtn").click(function() {
					$(location).attr('href', '${sc_ctx}/product/new');
				});

				//--------------------------------------------------------------------
				// 删除按钮点击
				//--------------------------------------------------------------------
				$("#delBtn").click(function() {
					if($("#listForm").valid()){
						var uuids = "";
						$.each($subCheckBox, function(index, _checkBox) {
							if(_checkBox.checked){
								uuids += _checkBox.value + ",";
							}
						});
						if(uuids.length > 0){
							uuids = uuids.substring(0, uuids.length - 1);
						}

						$("#__del-confirm").dialog({
							resizable: false,
							height: 190,
							modal: true,
							buttons: {
								"删除": function() {
									$( this ).dialog( "close" );
									
									$("#uuids").val(uuids);
									$("#listForm").attr("action", "${sc_ctx}/product/del");
						        	$("#listForm").submit();
								},
								"关闭": function() {
									$( this ).dialog( "close" );
								}
							}
						});
					}
				});
				//--------------------------------------------------------------------
				// 查询按钮点击
				//--------------------------------------------------------------------
				$("#serchBtn").click(function() {
					$("input[type='text']").each(function(i){
  						this.value = $.trim(this.value);
 					});
					$("#serchForm").attr("action", "${sc_ctx}/product/list");
					$("#serchForm").submit();
				});
			});
		</script>
	</head>
	<body>
		<%// 系统菜单  %>
		<page:applyDecorator name="menu" />
		
		<div class="grid_16 titleNav">
			<h2><a>配置管理</a>&#8711; <a>商品相关</a>&#8711; <a>商品</a></h2>
		</div>
		<div class="clear"></div>
		
		
		<form method="post" class="form cmxform" id="serchForm">
		<%// ----------------------------------------------------------- %>
		<%// 列表查询 %>
		<%// ----------------------------------------------------------- %>
		<div class="grid_13 suffix_3">
			<table class="search_table">
				<tr>
					<td class="item_name" width="90px">商品编号:</td>
					<td class="item" width="180px">
						<input type="text" name="barCode" value="${product.barCode}" class="text ui-widget-content ui-corner-all"/>
					</td>
					<td class="item_name" width="90px">商品名称:</td>
					<td class="item" width="180px">
						<input type="text" name="name" value="${product.name}" class="text ui-widget-content ui-corner-all"/>
					</td>
				</tr>
				<tr>
					<td class="item_name">商品品牌:</td>
					<td class="item">
						<input type="text" name="productBrandName" value="${product.productBrandName}" class="text ui-widget-content ui-corner-all"/>
					</td>
					<td class="item_name">商品供应商:</td>
					<td class="item">
						<input type="text" name="productSupplierName" value="${product.productSupplierName}" class="text ui-widget-content ui-corner-all"/>
					</td>
					<td align="right">
						<input id="serchBtn" type="button" class="submit" value="查询"/>
					</td>
				</tr>
			</table>
		</div>
		<div class="clear"></div>
		</form>
		
		
		<form method="post" class="form cmxform" id="listForm">
		<%// ----------------------------------------------------------- %>
		<%// 列表操作 %>
		<%// ----------------------------------------------------------- %>
		<div class="grid_16">
			<input id="newBtn" type="button" class="submit" value="新增"/>
			<input id="delBtn" name="delBtn" type="button" class="submit" value="删除"/>
		</div>
		<div class="clear"></div>
		
		<%// ----------------------------------------------------------- %>
		<%// 列表 %>
		<%// ----------------------------------------------------------- %>
		<div class="grid_16">
			<input type="hidden" name="uuids" id="uuids"/>
			<table id="rounded-corner">
				<thead>
					<tr>
						<th class="rounded-left" width="25">
							<input id="checkAll" type="checkbox"></input>
						</th>
						<th width="90">商品编号/条形码</th>
						<th width="90">商品名称</th>
						<th width="90">商品品牌</th>
						<th width="90">商品供应商</th>
						<th width="90">参考进价</th>
						<th width="90">零售价</th>
						<th width="90" class="rounded-right">操作</th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${empty productList}" >
					<tfoot>
						<tr>
							<td colspan="7" class="rounded-foot-left"><em>无记录</em></td>
							<td class="rounded-foot-right"> &nbsp; </td>
						</tr>
					</tfoot>
					</c:if>
					<c:forEach items="${productList}" var="product">
					<tr>
						<td class="first">
							<input type="checkbox" name="uuid" value="${product.uuid}"></input>
						</td>
						<td>
							${product.barCode}&nbsp;
						</td>
						<td>
							${product.name}&nbsp;
						</td>
						<td>
							${product.productBrandName}&nbsp;
						</td>
						<td>
							${product.productSupplierName}&nbsp;
						</td>
						<td>
							${product.refPrice}&nbsp;
						</td>
						<td>
							${product.retailPrice}&nbsp;
						</td>
						<td align="center">
							<a href="${sc_ctx}/product/edit/${product.uuid}">编辑</a>
						</td>
					</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<div class="clear"></div>
		</form>
	</body>
</html>
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
				$("#newBtn,#delBtn").button();

				//--------------------------------------------------------------------
				// 新增按钮点击
				//--------------------------------------------------------------------
				$("#newBtn").click(function() {
					$(location).attr('href', '${sc_ctx}/product/productSupplier/new');
				});
				
				//--------------------------------------------------------------------
				// 删除按钮点击
				//--------------------------------------------------------------------
				$("#delBtn").click(function() {
					var uuids = "";
					$.each($subCheckBox, function(index, _checkBox) {
						if(_checkBox.checked){
							uuids += _checkBox.value + ",";
						}
				    });
					if(uuids.length > 0){
						uuids = uuids.substring(0, uuids.length - 1);
					}
					$("#uuids").val(uuids);
					$("#listForm").attr("action", "${sc_ctx}/product/productSupplier/del");
		        	$("#listForm").submit();
				});
			});
		</script>
	</head>
	<body>
		<%// 系统菜单  %>
		<page:applyDecorator name="menu" />
		
		<div class="grid_16 titleNav">
			<h2><a>配置管理</a>&#8711; <a>产品相关</a>&#8711; <a>供应商（产品）</a></h2>
		</div>
		<div class="clear"></div>
		
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
						<th width="60">供应商编号</th>
						<th width="60">供应商名称</th>
						<th width="60">电话号码</th>
						<th width="60">传真号码</th>
						<th width="150">供应商地址</th>
						<th width="60">供应商邮编</th>
						<th width="90" class="rounded-right">操作</th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${empty productSupplierList}" >
					<tfoot>
						<tr>
							<td colspan="7" class="rounded-foot-left"><em>无记录</em></td>
							<td class="rounded-foot-right"> &nbsp; </td>
						</tr>
					</tfoot>
					</c:if>
					<c:forEach items="${productSupplierList}" var="productSupplier">
					<tr>
						<td class="first">
							<input type="checkbox" name="uuid" value="${productSupplier.uuid}"></input>
						</td>
						<td>
							${productSupplier.id}&nbsp;
						</td>
						<td>
							${productSupplier.name}&nbsp;
						</td>
						<td>
							${productSupplier.telNum}&nbsp;
						</td>
						<td>
							${productSupplier.faxNum}&nbsp;
						</td>
						<td>
							${productSupplier.addr}&nbsp;
						</td>
						<td>
							${productSupplier.postCode}&nbsp;
						</td>
						<td align="center">
							<a href="${sc_ctx}/product/productSupplier/edit/${productSupplier.uuid}">编辑</a>
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
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
				$("#newBtn,#delBtn,#serchBtn").button();

				//--------------------------------------------------------------------
				// 新增按钮点击
				//--------------------------------------------------------------------
				$("#newBtn").click(function() {
					$(location).attr('href', '${sc_ctx}/syscfg/shop/new');
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
							height:185,
							modal: true,
							buttons: {
								"删除": function() {
									$( this ).dialog( "close" );
									
									$("#uuids").val(uuids);
									$("#listForm").attr("action", "${sc_ctx}/syscfg/shop/del");
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
					$("#serchForm").attr('action', '${sc_ctx}/syscfg/shop/list');
					$("#serchForm").submit();
				});
			});
		</script>
	</head>
	<body>
		<%// 系统菜单  %>
		<page:applyDecorator name="menu" />
		
		<div class="grid_16 titleNav">
			<h2><a>配置管理</a>&#8711; <a>门店相关</a>&#8711; <a>门店</a></h2>
		</div>
		<div class="clear"></div>
		
		
		<form method="post" class="form cmxform" id="serchForm">
		<%// ----------------------------------------------------------- %>
		<%// 列表查询 %>
		<%// ----------------------------------------------------------- %>
		<div class="grid_12 suffix_4">
			<table class="search_table">
				<tr>
					<td class="item_name" width="90px">门店编号:</td>
					<td class="item" width="180px">
						<input type="text" name="id" value="${shop.id}" class="text ui-widget-content ui-corner-all"/>
					</td>
					<td class="item_name" width="90px">门店名称:</td>
					<td class="item" width="180px">
						<input type="text" name="name" value="${shop.name}" class="text ui-widget-content ui-corner-all"/>
					</td>
				</tr>
				<tr>
					<td class="item_name">门店地址:</td>
					<td class="item">
						<input type="text" name="addr" value="${shop.addr}" class="text ui-widget-content ui-corner-all"/>
					</td>
					<td colspan="2" align="right">
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
						<th width="60">门店编号</th>
						<th width="60">门店名称</th>
						<th width="60">电话号码</th>
						<th width="60">传真号码</th>
						<th width="150">门店地址</th>
						<th width="120">仓库信息</th>
						<th width="90" class="rounded-right">操作</th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${empty shopList}" >
					<tfoot>
						<tr>
							<td colspan="7" class="rounded-foot-left"><em>无记录</em></td>
							<td class="rounded-foot-right"> &nbsp; </td>
						</tr>
					</tfoot>
					</c:if>
					<c:forEach items="${shopList}" var="shop">
					<tr>
						<td class="first">
							<input type="checkbox" name="uuid" value="${shop.uuid}"></input>
						</td>
						<td>
							${shop.id}&nbsp;
						</td>
						<td>
							${shop.name}&nbsp;
						</td>
						<td>
							${shop.telNum}&nbsp;
						</td>
						<td>
							${shop.faxNum}&nbsp;
						</td>
						<td>
							${shop.addr}&nbsp;
						</td>						
						<td>
							${shop.storeName}&nbsp;
						</td>
						<td align="center">
							<a href="${sc_ctx}/syscfg/shop/edit/${shop.uuid}">编辑</a>
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
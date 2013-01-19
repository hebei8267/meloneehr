<%@	page contentType="text/html;charset=UTF-8"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@	taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@	taglib prefix="page" uri="http://www.opensymphony.com/sitemesh/page"%>
<%@	page import="com.tjhx.common.utils.DateUtils"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"	/>
<c:set var="sc_ctx">${ctx}/sc</c:set>
<!DOCTYPE html>
<html>
	<head>
		<script>
			$().ready(function() {
				//-----------------------------------
				// 表单效验
				//-----------------------------------
				$("#listForm").validate({
					rules: {
						delBtn : {
							requiredSelect : 'uuid'
						}
					}
				});
				//-----------------------------------
				// 全选/全部选
				//-----------------------------------
				$("#checkAll").click(function()	{
					$('input[name="uuid"]').attr("checked",this.checked);
				});
				var	$subCheckBox = $("input[name='uuid']");
				$subCheckBox.click(function(){
					$("#checkAll").attr("checked",$subCheckBox.length == $("input[name='uuid']:checked").length	? true : false);
				});
				
				//-----------------------------------
				// 删除按钮点击
				//-----------------------------------
				$("#delBtn").click(function() {
					if($("#listForm").valid()){
						$('#__del_confirm').modal({
							backdrop : true,
							keyboard : true,
							show : true
						});
					}
				});
			});
			//-----------------------------------
			// 删除
			//-----------------------------------
			function _del_confirm(){
				var	$subCheckBox = $("input[name='uuid']");
				var	uuids =	"";
				$.each($subCheckBox, function(index, _checkBox)	{
					if(_checkBox.checked){
						uuids += _checkBox.value + ",";
					}
				});
				if(uuids.length	> 0){
					uuids =	uuids.substring(0, uuids.length	- 1);
				}
				
				$("#uuids").val(uuids);
				$("#listForm").attr("action", "${sc_ctx}/cashRun/del");
				$("#listForm").submit();
			}	
		</script>
	</head>
	<body>
		<%// 系统菜单  %>
		<page:applyDecorator name="menu" />
		
		<div class="container">
			<form method="post"	class="form-horizontal"	id="listForm">
				<div class="row">
					<div class="span12">
						<legend>
							<h3>${sessionScope.__SESSION_USER_INFO.orgName}店 销售信息</h3>
						</legend>
					</div>
					<div class="span10">
						<a href="${sc_ctx}/cashRun/new"	class="btn btn-primary">新增</a>
						<input id="delBtn" name="delBtn" type="button" class="btn btn-danger" value="删除"/>
					</div>
					<div class="span2 right_text">
						<%String nowM =	DateUtils.getCurrentMonth(); %>
						<%String lastM=	DateUtils.getNextMonthFormatDate(-1, "yyyy-MM"); %>
						<a href="${sc_ctx}/cashRun/list/<%=nowM	%>"><%=nowM	%></a> | <a	href="${sc_ctx}/cashRun/list/<%=lastM %>"><%=lastM %></a>
					</div>
					<div class="span12"	style="margin-top: 10px;">
						<input type="hidden" name="uuids" id="uuids"/>
						<table class="table	table-striped table-bordered table-condensed mytable">
							<thead>
								<tr>
									<th	width="25" class="center">
										<input id="checkAll" type="checkbox" />
									</th>
									<th>
										日期
									</th>
									<th>
										上班类型
									</th>
									<th>
										班前余额
									</th>
									<th>
										当前销售
									</th>
									<th>
										实际现金
									</th>
									<th>
										刷卡金额(单据)
									</th>
									<th>
										刷卡金额(百威)
									</th>
									<th>
										存款金额
									</th>
									<th>
										存款人
									</th>
									<th>
										留存金额
									</th>
									<th	width="55">
										&nbsp;
									</th>
								</tr>
							</thead>
							<tbody>
								<c:if test="${empty	cashRunList}" >
									<tfoot>
										<tr>
											<td	colspan="12"	class="rounded-foot-left">
												无记录信息
											</td>
										</tr>
									</tfoot>
								</c:if>
								<c:forEach items="${cashRunList}" var="cashRun">
									<tr>
										<td	class="center">
											<c:if test="${cashRun.editFlg == 'true'	}">
												<input type="checkbox" name="uuid" value="${cardRun.uuid}">
												</input>
											</c:if>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</form>
		</div>
	</body>
</html>
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
				$("#listForm").attr("action", "${sc_ctx}/cardRun/del");
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
							<h3>${sessionScope.__SESSION_USER_INFO.orgName}店 刷卡信息</h3>
						</legend>
					</div>
					<div class="span10">
						<a href="${sc_ctx}/cardRun/new"	class="btn btn-primary">新增</a>
						<input id="delBtn" name="delBtn" type="button" class="btn btn-danger" value="删除"/>
					</div>
					<div class="span2 right_text">
						<%String nowM =	DateUtils.getCurrentMonth(); %>
						<%String lastM=	DateUtils.getNextMonthFormatDate(-1, "yyyy-MM"); %>
						<a href="${sc_ctx}/cardRun/list/<%=nowM	%>"><%=nowM	%></a> | <a	href="${sc_ctx}/cardRun/list/<%=lastM %>"><%=lastM %></a>
					</div>
					<div class="span12"	style="margin-top: 10px;">
						<input type="hidden" name="uuids" id="uuids"/>
						<table class="table	table-striped table-bordered table-condensed mytable">
							<thead>
								<tr>
									<th	width="25" class="center">
										<input id="checkAll" type="checkbox" />
									</th>
									<th	width="10%">
										日期
									</th>
									<th	width="10%">
										单据统计
									</th>
									<th	width="12%">
										电脑统计(百威)
									</th>
									<th	width="8%">
										刷卡笔数
									</th>
									<th	width="12%">
										凭证号
									</th>
									<th>
										备注(盈亏原因)
									</th>
									<th	width="55">
										&nbsp;
									</th>
								</tr>
							</thead>
							<tbody>
								<c:if test="${empty	cardRunList}" >
									<tfoot>
										<tr>
											<td	colspan="7"	class="rounded-foot-left">
												无记录信息
											</td>
										</tr>
									</tfoot>
								</c:if>
								<c:forEach items="${cardRunList}" var="cardRun">
									<tr>
										<td	class="center">
											<c:if test="${cardRun.editFlg == 'true'	}">
												<input type="checkbox" name="uuid" value="${cardRun.uuid}">
												</input>
											</c:if>
										</td>
										<td>
											${cardRun.optDateShow}
										</td>
										<td>
											${cardRun.recordStatisAmt}
										</td>
										<td>
											${cardRun.bwStatisAmt}
										</td>
										<td>
											${cardRun.optNum}
										</td>
										<td>
											${cardRun.certNo}
										</td>
										<td>
											${cardRun.descTxt}
										</td>
										<td>
											<c:if test="${cardRun.editFlg == 'true'	}">
												<a href="${sc_ctx}/cardRun/edit/${cardRun.uuid}" class="btn	btn-warning"/>修改</a>
											</c:if>
										</td>
									</tr>
								</c:forEach>
								<c:if test="${!empty cardRunList}" >
									<tr>
										<td	colspan="2">
											合计:
										</td>
										<td>
											${totalCardRun.recordStatisAmt}
										</td>
										<td>
											${totalCardRun.bwStatisAmt}
										</td>
										<td>
											${totalCardRun.optNum}
										</td>
										<td	colspan="3"></td>
									</tr>
								</c:if>
							</tbody>
						</table>
					</div>
				</div>
			</form>
		</div>
	</body>
</html>
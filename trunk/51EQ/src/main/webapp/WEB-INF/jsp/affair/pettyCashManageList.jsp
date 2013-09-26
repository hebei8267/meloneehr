<%@	page contentType="text/html;charset=UTF-8"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@	taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@	taglib prefix="page" uri="http://www.opensymphony.com/sitemesh/page"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@	page import="com.tjhx.common.utils.DateUtils"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<c:set var="sc_ctx">
    ${ctx}/sc
</c:set>
<!DOCTYPE html>
<html>
	<head>
		<style type="text/css">
		._warn1 {
			background-color: #FF6633;
			padding: 5px;
		}
		._warn2 {
			background-color: #33FF00;
			padding: 5px;
		}
		._warn3 {
			background-color: #FFDEAD;
			padding: 5px;
		}
		</style>
		<script>
			$(function() {
				$("#listForm").validate({
					rules : {
						optDateShow_start : {
							required : true,
							date : true
						},
						optDateShow_end : {
							required : true,
							date : true,
							compareDate : "#optDateShow_start"
						},
						orgId : {
							required : true
						}
					}
				});
		
				$('#optDateShow_start').datepicker({
					format : 'yyyy-mm-dd'
				});
				$('#optDateShow_end').datepicker({
					format : 'yyyy-mm-dd'
				});
		
				$("#searchBtn").click(function() {
					$("input[type='text'],textarea").each(function(i) {
						this.value = $.trim(this.value);
					});
		
					$("#listForm").attr("action", "${sc_ctx}/pettyCash/search");
					$("#listForm").submit();
				});
			});
		</script>
	</head>
	<body>
		<% // 系统菜单 %>
		<page:applyDecorator name="menu" />

		<div class="container">
			<form method="post" class="form-inline" id="listForm">
				<div class="row">
					<div class="span12">
						<legend>
							<h3>门店备用金信息</h3>
						</legend>
					</div>
					<div class="span5">
						<label class="control-label">业务日期 :</label>
						<input id="optDateShow_start" name="optDateShow_start" type="text" class="input-medium" value="${optDateShow_start }" /> ～ 
						<input id="optDateShow_end" name="optDateShow_end" type="text" class="input-medium" value="${optDateShow_end }" />
					</div>
					<div class="span7">
						<label class="control-label">机构 :</label>
						<select id="orgId" name="orgId" class="input-medium">
							<c:forEach items="${orgList}" var="org">
								<c:if test="${org.key == orgId}">
									<option value="${org.key }" selected>${org.value }</option>
								</c:if>
								<c:if test="${org.key != orgId}">
									<option value="${org.key }">${org.value }</option>
								</c:if>
							</c:forEach>
						</select>
						<button id="searchBtn" class="btn	btn-primary" type="button">查询</button>
					</div>
	
					<div class="span12" style="margin-top: 10px;">
						<table class="table	table-striped table-bordered table-condensed mytable">
							<thead>
								<tr>
									<th width="115">业务编号(UID)</th>
									<th width="35" class="center">星期</th>
									<th width="90" class="center">业务日期</th>
									<th width="80" class="center">填写时间</th>
									<th width="150">支出/拨入(金额)</th>
									<th>支出/拨入(事项)</th>
									<th width="110">备用金余额</th>
									<th width="55">&nbsp;</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${pettyCashList}" var="pettyCash">
									<tr>
										<td>${pettyCash.optUid}</td>
										<td class="center">${pettyCash.week}</td>
										<td>
											<c:if test="${!pettyCash.optDateShow.equals(pettyCash.createDateStr)}">
											<span class="_warn3">
											</c:if>
											${pettyCash.optDateShow}
											<c:if test="${!pettyCash.optDateShow.equals(pettyCash.createDateStr)}">
											</span>
											</c:if>
										</td>
										<td>${pettyCash.createDateStr}</td>
										<td>
											<% //操作类型 0-支出 1-拨入 %>
											<c:if test="${pettyCash.optType == 0}">
											<span class="_warn1">
											</c:if>
											
											<c:if test="${pettyCash.optType == 1}">
											<span class="_warn2">
											</c:if> 
											
											${pettyCash.optAmt} 
											</span>
										</td>
										<td>
											<% //操作类型 0-支出 1-拨入 %>
											<c:if test="${pettyCash.optType == 0}">
		                            		${pettyCash.expReason}
		                            		</c:if>
		                            		
		                            		<c:if test="${pettyCash.optType == 1}">
												<c:if test="${pettyCash.incomeSource.equals('1')}">
		                            			正常拨入
		                            			</c:if>
												<c:if test="${pettyCash.incomeSource.equals('2')}">
		                            			非常拨入
		                            			</c:if>
												<c:if test="${pettyCash.incomeSource.equals('4')}">
		                            			会计调帐
		                            			</c:if>
											</c:if>
										</td>
										<td>${pettyCash.balanceAmt}</td>
										<td><a href="${sc_ctx}/pettyCash/view/${pettyCash.uuid}" target="_blank" class="btn" />查看</a></td>
									</tr>
								</c:forEach>
							</tbody>
							<c:if test="${empty	pettyCashList}">
								<tfoot>
									<tr>
										<td colspan="8" class="rounded-foot-left">无记录信息</td>
									</tr>
								</tfoot>
							</c:if>
						</table>
					</div>
				</div>
			</form>
		</div>
	</body>
</html>
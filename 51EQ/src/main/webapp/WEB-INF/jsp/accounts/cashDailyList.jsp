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
							<h3>${sessionScope.__SESSION_USER_INFO.orgName}店 日结信息</h3>
						</legend>
					</div>
					<div class="span12">
						<b>未日结信息列表</b>
					</div>
					<div class="span12" style="margin-top: 10px;">
						<table class="table	table-striped table-bordered table-condensed mytable">
							<thead>
								<tr>
									<th>
										日期
									</th>
									<th>
										昨日余额
									</th>
									<th>
										当日销售
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
										刷卡笔数
									</th>
									<th>
										存款金额
									</th>
									<th>
										留存金额
									</th>
									<th width="55">
										明细
									</th>
									<th width="55">
										&nbsp;
									</th>
								</tr>
							</thead>
							<tbody>
								<c:if test="${empty	noCashDailyList}" >
									<tfoot>
										<tr>
											<td	colspan="11" class="rounded-foot-left">
												无记录信息
											</td>
										</tr>
									</tfoot>
								</c:if>
								<c:forEach items="${noCashDailyList}" var="noCashDaily">
									<tr>
										<td>${noCashDaily.optDateShow}</td>
										<td>${noCashDaily.initAmt}</td>
										<td>${noCashDaily.saleAmt}</td>
										<td>${noCashDaily.cashAmt}</td>
										<td>${noCashDaily.cardAmt}</td>
										<td>${noCashDaily.cardAmtBw}</td>
										<td>${noCashDaily.cardNum}</td>
										<td>${noCashDaily.depositAmt}</td>
										<td>${noCashDaily.retainedAmt}</td>
										<td><a href="${sc_ctx}/cashRun/" class="btn btn-warning" target="_blank"/>查看</a></td>
										<td><a href="${sc_ctx}/cashDaily/confirm/${noCashDaily.optDate}"	class="btn btn-danger">日结</a></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
					<div class="span12 cash_daily">
					</div>
					<div class="span9">
						<b>已日结信息列表</b>
					</div>
					<div class="pan3 right_text">
						<%String nowM =	DateUtils.getCurrentMonth(); %>
						<%String lastM=	DateUtils.getNextMonthFormatDate(-1, "yyyy-MM"); %>(日结日期)
						<a href="${sc_ctx}/cashDaily/list/<%=nowM	%>"><%=nowM	%></a> | <a	href="${sc_ctx}/cashDaily/list/<%=lastM %>"><%=lastM %></a>
					</div>
					<div class="span12"	style="margin-top: 10px;">
						<table class="table	table-striped table-bordered table-condensed mytable">
							<thead>
								<tr>
									<th>
										日期
									</th>
									<th>
										昨日余额
									</th>
									<th>
										当日销售
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
										刷卡笔数
									</th>
									<th>
										存款金额
									</th>
									<th>
										留存金额
									</th>
								</tr>
							</thead>
							<tbody>
								<c:if test="${empty	cashDailyList}" >
									<tfoot>
										<tr>
											<td	colspan="9" class="rounded-foot-left">
												无记录信息
											</td>
										</tr>
									</tfoot>
								</c:if>
								<c:forEach items="${cashDailyList}" var="cashDaily">
									<tr>
										<td>${cashDaily.optDateShow}</td>
										<td>${cashDaily.initAmt}</td>
										<td>${cashDaily.saleAmt}</td>
										<td>${cashDaily.cashAmt}</td>
										<td>${cashDaily.cardAmt}</td>
										<td>${cashDaily.cardAmtBw}</td>
										<td>${cashDaily.cardNum}</td>
										<td>${cashDaily.depositAmt}</td>
										<td>${cashDaily.retainedAmt}</td>
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
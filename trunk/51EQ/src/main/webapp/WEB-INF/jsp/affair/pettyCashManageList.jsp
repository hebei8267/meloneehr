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
			color: #006dcc;
			padding: 5px;
			font-weight:bold;
		}
		._warn2 {
			color: #f89406;
			padding: 5px;
			font-weight:bold;
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
				
				$("#auditBtn").click(function() {
					$("input[type='text'],textarea").each(function(i) {
						this.value = $.trim(this.value);
					});
		
					$("#listForm").attr("action", "${sc_ctx}/pettyCash/audit");
					$("#listForm").submit();
				});
				
				$("#exportBtn").click(function() {
					$("input[type='text'],textarea").each(function(i) {
						this.value = $.trim(this.value);
					});
		
					$("#listForm").attr("action", "${sc_ctx}/pettyCash/export");
					$("#listForm").submit();
				});
			});
			
			function examineFlg_btn_click(index){
				var _css = $("#ex_btn"+index).hasClass("btn-info");

				if(_css){
					$("#ex_btn"+index).removeClass("btn-info");
					$("#ex_btn"+index).addClass("btn-danger");
					$("#ex_btn"+index).val("错误");
					$("#examineFlg"+index).val("1");
				} else {
					$("#ex_btn"+index).removeClass("btn-danger");
					$("#ex_btn"+index).addClass("btn-info");
					$("#ex_btn"+index).val("正确");
					$("#examineFlg"+index).val("0");
				}
			}
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
						<button id="searchBtn" class="btn btn-primary" type="button">查询</button>
						<button	id="exportBtn" class="btn btn-warning" type="button">数据导出</button>
						
					</div>
	
					<div class="span12" style="margin-top: 10px;">
						<table class="table	table-striped table-bordered table-condensed mytable">
							<thead>
								<tr>
									<th width="45">UID</th>
									<th width="35" class="center">星期</th>
									<th width="90" class="center">业务日期</th>
									<th width="90" class="center">填写日期</th>
									<th width="130" class="right">支出/拨入(金额)</th>
									<th>支出/拨入(事项)</th>
									<th width="90" class="right">备用金余额</th>
									<th width="30">&nbsp;</th>
									<th width="30">账记对应</th>
									<th width="30">书写规范</th>
									<th width="30">摘要清晰</th>
									<th width="30">附件监督</th>
									<th width="30">记录序时</th>
									<th width="30">UID正确</th>
									<th width="30">装订正确</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${pettyCashList}" var="pettyCash" varStatus="status1">
									<tr>
										<c:if test="${pettyCash.optType == 0}">
										<input type="hidden" name="uuid" value="${pettyCash.uuid }">
										</c:if>
										
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
										<td class="right">
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
										<td class="right">${pettyCash.balanceAmt}</td>
										<td><a href="${sc_ctx}/pettyCash/view/${pettyCash.uuid}" target="_blank" class="btn" />查看</a></td>
										
										<c:if test="${pettyCash.optType == 0}">
										<td>
											<c:if test="${pettyCash.examineFlg1 == 0}">
											<input type="button" id="ex_btn1_${status1.index + 1}" onclick="examineFlg_btn_click('1_${status1.index + 1}')" class="btn btn-info" value="正确">
											</c:if>
											<c:if test="${pettyCash.examineFlg1 == 1}">
											<input type="button" id="ex_btn1_${status1.index + 1}" onclick="examineFlg_btn_click('1_${status1.index + 1}')" class="btn btn-danger" value="错误">
											</c:if>
											<input type="hidden" name="examineFlg1" id="examineFlg1_${status1.index + 1}" value="${pettyCash.examineFlg1 }">
										</td>
										
										<td>
											<c:if test="${pettyCash.examineFlg2 == 0}">
											<input type="button" id="ex_btn2_${status1.index + 1}" onclick="examineFlg_btn_click('2_${status1.index + 1}')" class="btn btn-info" value="正确">
											</c:if>
											<c:if test="${pettyCash.examineFlg2 == 1}">
											<input type="button" id="ex_btn2_${status1.index + 1}" onclick="examineFlg_btn_click('2_${status1.index + 1}')" class="btn btn-danger" value="错误">
											</c:if>
											<input type="hidden" name="examineFlg2" id="examineFlg2_${status1.index + 1}" value="${pettyCash.examineFlg2 }">
										</td>
										
										<td>
											<c:if test="${pettyCash.examineFlg3 == 0}">
											<input type="button" id="ex_btn3_${status1.index + 1}" onclick="examineFlg_btn_click('3_${status1.index + 1}')" class="btn btn-info" value="正确">
											</c:if>
											<c:if test="${pettyCash.examineFlg3 == 1}">
											<input type="button" id="ex_btn3_${status1.index + 1}" onclick="examineFlg_btn_click('3_${status1.index + 1}')" class="btn btn-danger" value="错误">
											</c:if>
											<input type="hidden" name="examineFlg3" id="examineFlg3_${status1.index + 1}" value="${pettyCash.examineFlg3 }">
										</td>
										
										<td>
											<c:if test="${pettyCash.examineFlg4 == 0}">
											<input type="button" id="ex_btn4_${status1.index + 1}" onclick="examineFlg_btn_click('4_${status1.index + 1}')" class="btn btn-info" value="正确">
											</c:if>
											<c:if test="${pettyCash.examineFlg4 == 1}">
											<input type="button" id="ex_btn4_${status1.index + 1}" onclick="examineFlg_btn_click('4_${status1.index + 1}')" class="btn btn-danger" value="错误">
											</c:if>
											<input type="hidden" name="examineFlg4" id="examineFlg4_${status1.index + 1}" value="${pettyCash.examineFlg4 }">
										</td>
										
										<td>
											<c:if test="${pettyCash.examineFlg5 == 0}">
											<input type="button" id="ex_btn5_${status1.index + 1}" onclick="examineFlg_btn_click('5_${status1.index + 1}')" class="btn btn-info" value="正确">
											</c:if>
											<c:if test="${pettyCash.examineFlg5 == 1}">
											<input type="button" id="ex_btn5_${status1.index + 1}" onclick="examineFlg_btn_click('5_${status1.index + 1}')" class="btn btn-danger" value="错误">
											</c:if>
											<input type="hidden" name="examineFlg5" id="examineFlg5_${status1.index + 1}" value="${pettyCash.examineFlg5 }">
										</td>
										
										<td>
											<c:if test="${pettyCash.examineFlg6 == 0}">
											<input type="button" id="ex_btn6_${status1.index + 1}" onclick="examineFlg_btn_click('6_${status1.index + 1}')" class="btn btn-info" value="正确">
											</c:if>
											<c:if test="${pettyCash.examineFlg6 == 1}">
											<input type="button" id="ex_btn6_${status1.index + 1}" onclick="examineFlg_btn_click('6_${status1.index + 1}')" class="btn btn-danger" value="错误">
											</c:if>
											<input type="hidden" name="examineFlg6" id="examineFlg6_${status1.index + 1}" value="${pettyCash.examineFlg6 }">
										</td>
										
										<td>
											<c:if test="${pettyCash.examineFlg7 == 0}">
											<input type="button" id="ex_btn7_${status1.index + 1}" onclick="examineFlg_btn_click('7_${status1.index + 1}')" class="btn btn-info" value="正确">
											</c:if>
											<c:if test="${pettyCash.examineFlg7 == 1}">
											<input type="button" id="ex_btn7_${status1.index + 1}" onclick="examineFlg_btn_click('7_${status1.index + 1}')" class="btn btn-danger" value="错误">
											</c:if>
											<input type="hidden" name="examineFlg7" id="examineFlg7_${status1.index + 1}" value="${pettyCash.examineFlg7 }">
										</td>
										</c:if>
										
										<c:if test="${pettyCash.optType == 1}">
										<td colspan="7"></td>
										</c:if>
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
					<c:if test="${!empty	pettyCashList}">
					<div class="span12" style="text-align: right;">
					<button id="auditBtn" class="btn btn-large btn-warning" type="button">审核</button>
					</div>
					</c:if>
				</div>
			</form>
		</div>
	</body>
</html>
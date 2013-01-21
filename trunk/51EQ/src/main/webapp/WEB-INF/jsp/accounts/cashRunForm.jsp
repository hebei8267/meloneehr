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
		<style type="text/css">
		.form-horizontal .control-label {
			width: 130px;
		}
		.form-horizontal .controls {
			margin-left: 145px;
		}
		</style>
		<script>
			$(function() {
				var oldDateVal = "";
				var oldBankIdVal = "";
				
				$('#optDateShow').datepicker({
					format : 'yyyy-mm-dd'
				});
				$('#optDateShow').datepicker().on('changeDate', function(ev){
					if(oldDateVal == $('#optDateShow').val()){
						return;
					}
					
					oldDateVal = $('#optDateShow').val();
					
					if("" != $('#optDateShow').val()){
						$.post("${sc_ctx}/cashRun/calInitAmt", {"optDateShow":$('#optDateShow').val()}, function(result){
							$('#_initAmt_label').html(result);
							$('#initAmt').val(result);
						});
					}
				});
				$('#bankId').change(function(){
					if(oldBankIdVal == $('#bankId').val()){
						return;
					}
					
					oldBankIdVal = $('#bankId').val();
					
					if("" != $('#bankId').val()){
						$.post("${sc_ctx}/cashRun/getBankCardNoList", {"bankId":$('#bankId').val()}, function(result){
							var optionstring = "<option value=''></option>";
							$.each(result, function(index, item){
								optionstring += "<option value=\""+ item.uuid +"\" >"+ item.bankCardNo +"</option>";
							});
							
							jQuery("#bankCardNo").html(optionstring);
						},"json");
					} else {
						jQuery("#bankCardNo").html("");
					}
			    });
				
				$("#inputForm").validate({
					rules: {
						optDateShow: {
							required: true,
							date: true,
							datelessThan : $("#_tomorrow_date").val()
						},
						jobType: {
							required: true
						},
						saleAmt: {
							required: true,
							money: true
						},
						cashAmt: {
							required: true,
							money: true
						},
						cardAmt: {
							required: true,
							money: true
						},
						cardAmtBw: {
							required: true,
							money: true
						},
						cardNum: {
							required: true,
							digits: true
						},
						depositAmt: {
							required: true,
							money: true
						},
						depositor: {
							myRequired: "#depositAmt",
							maxlength: 16
						},
						bankId: {
							myRequired: "#depositAmt"
						},
						bankCardNo: {
							myRequired: "#depositAmt"
						},
						descTxt: {
							maxlength: 255
						}
					}
				});
				
				$("#saveBtn").click(function() {
					$("input[type='text'],textarea").each(function(i){
						this.value = $.trim(this.value);
					});

					$("#inputForm").attr("action", "${sc_ctx}/cashRun/save");
					$("#inputForm").submit();
				});
			});
		</script>
	</head>
	<body>
		<%// 系统菜单  %>
		<page:applyDecorator name="menu" />

		<div class="container">
			<div class="row">
				<div class="span12">
					<legend>
						<h3>${sessionScope.__SESSION_USER_INFO.orgName}店 销售信息
						<c:if test="${empty	cashRun.uuid}">
							新增
						</c:if>
						<c:if test="${!empty cashRun.uuid}">
							编辑
						</c:if>
						</h3>
					</legend>
				</div>
				<input type="hidden" id="_tomorrow_date" value="<%=DateUtils.getNextDateFormatDate(1, "yyyy-MM-dd")%>">
				<div class="span12"	style="margin-top: 10px;">
					<form:form method="POST" class="form-horizontal" id="inputForm"	modelAttribute="cashRun">
						<form:hidden path="uuid"/>
						<div class="control-group">
							<label class="control-label">日期 :</label>
							<div class="controls">
								<form:input	path="optDateShow" />
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">上班类型 :</label>
							<div class="controls">
								<form:select path="jobType" items="${jobTypeList}"/>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">班前余额 :</label>
							<label class="left-control-label" id="_initAmt_label">${cashRun.initAmt}</label>
							<form:hidden path="initAmt"/>
						</div>
						<div class="control-group">
							<label class="control-label">当前销售 :</label>
							<div class="controls">
								<form:input	path="saleAmt" />
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">实际现金 :</label>
							<div class="controls">
								<form:input	path="cashAmt" />
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">刷卡金额(单据) :</label>
							<div class="controls">
								<form:input	path="cardAmt" />
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">刷卡金额(百威) :</label>
							<div class="controls">
								<form:input	path="cardAmtBw" />
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">刷卡笔数 :</label>
							<div class="controls">
								<form:input	path="cardNum" />
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">存款金额 :</label>
							<div class="controls">
								<form:input	path="depositAmt" />
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">存款人 :</label>
							<div class="controls">
								<form:input	path="depositor" />
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">存款银行 :</label>
							<div class="controls">
								<form:select path="bankId" items="${bankList}"/>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">卡号 :</label>
							<div class="controls">
								<form:select path="bankCardNo"/>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">留存金额 :</label>
							<label class="left-control-label">${cashRun.retainedAmt}</label>
						</div>
						<div class="control-group">
							<label class="control-label">备注 :</label>
							<div class="controls">
								<form:textarea path="descTxt" class="input-xlarge" rows="4"/>
							</div>
						</div>
						<div class="control-group">
							<div class="controls">
								<button	id="saveBtn" class="btn	btn-large btn-primary" type="submit">保存</button>
								&nbsp;<a href="${sc_ctx}/cashRun" class="btn btn-large">返回</a>
							</div>
						</div>
					</form:form>
				</div>
			</div>
		</div>
	</body>
</html>

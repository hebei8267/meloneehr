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
	        $(function() {
	        	$('#recordDateShow').datepicker({
					format : 'yyyy-mm-dd'
				});
	        	$('#intoDateShow').datepicker({
					format : 'yyyy-mm-dd'
				});
	        	
	        	$("#inputForm").validate({
					rules: {
						recordNo: {
							required: true,
							maxlength: 32
						},
						supplierBwId: {
							required: true
						},
						recordDateShow: {
							required: true,
							date: true,
							datelessThan : $("#_tomorrow_date").val()
						},
						intoDateShow: {
							required: true,
							date: true,
							datelessThan : $("#_tomorrow_date").val()
						},
						recordAmt: {
							required: true,
							money: true
						},
						optAmt: {
							required: true,
							money: true
						},
						optPerName: {
							required: true,
							maxlength: 32
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

					$("#inputForm").attr("action", "${sc_ctx}/storageRun/save");
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
                        <h3>${sessionScope.__SESSION_USER_INFO.orgName}店 刷卡情况
                        <c:if test="${empty	storageRun.uuid}">
                            新增
                        </c:if>
                        <c:if test="${!empty storageRun.uuid}">
                            编辑
                        </c:if></h3>
                    </legend>
                </div>
                <input type="hidden" id="_tomorrow_date" value="<%=DateUtils.getNextDateFormatDate(1, "yyyy-MM-dd")%>">
                <div class="span12"	style="margin-top: 10px;">
                    <form:form method="POST" class="form-horizontal" id="inputForm"	modelAttribute="storageRun">
                        <form:hidden path="uuid"/>
                        <div class="control-group">
                            <label class="control-label">入货单号 :</label>
                            <div class="controls">
                                <form:input	path="recordNo" />
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">供应商编号 :</label>
                            <div class="controls">
                            	<form:select path="supplierBwId" items="${supplier}"/>
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">开单日期 :</label>
                            <div class="controls">
                                <form:input	path="recordDateShow" />
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">入货日期 :</label>
                            <div class="controls">
                                <form:input	path="intoDateShow" />
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">开单金额	:</label>
                            <div class="controls">
                                <form:input	path="recordAmt" />
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">入库金额	:</label>
                            <div class="controls">
                                <form:input	path="optAmt" />
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">入库人	:</label>
                            <div class="controls">
                                <form:input	path="optPerName" />
                            </div>
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
                                &nbsp;<a href="${sc_ctx}/storageRun" class="btn btn-large">返回</a>
                            </div>
                        </div>
                    </form:form>
                </div>
            </div>
        </div>
    </body>
</html>

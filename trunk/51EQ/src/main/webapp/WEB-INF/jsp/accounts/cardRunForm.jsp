<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="page" uri="http://www.opensymphony.com/sitemesh/page"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<c:set var="sc_ctx">
    ${ctx}/sc
</c:set>
<!DOCTYPE html>
<html>
    <head>
        <script>
            $(function() {
                $('#optDateShow').datepicker({
                    format : 'yyyy-mm-dd'
                });
                
                $("#inputForm").validate({
					rules: {
						optDateShow: {
							required: true,
							date: true,
							datelessThan : $("#_tomorrow_date").val()
						},
						recordStatisAmt: {
							required: true,
							money: true
						},
						bwStatisAmt: {
							required: true,
							money: true
						},
						optNum: {
							required: true,
							digits1: true
						},
						certNo: {
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

					$("#inputForm").attr("action", "${sc_ctx}/cardRun/save");
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
                        <c:if test="${empty cardRun.uuid}">
                            新增
                        </c:if>
                        <c:if test="${!empty cardRun.uuid}">
                            编辑
                        </c:if>
                        </h3>
                    </legend>
                </div>
				<input type="hidden" id="_tomorrow_date" value="<%=com.tjhx.common.utils.DateUtils.getNextTimeFormatDate(1, "yyyy-MM-dd")%>">
                <div class="span12" style="margin-top: 10px;">
                    <form:form method="POST" class="form-horizontal" id="inputForm" modelAttribute="cardRun">
						<form:hidden path="uuid"/>
                        <div class="control-group">
                            <label class="control-label">日期 :</label>
                            <div class="controls">
                                <form:input path="optDateShow" />
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">单据统计 :</label>
                            <div class="controls">
                                <form:input path="recordStatisAmt" /> (元)
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">电脑统计 :</label>
                            <div class="controls">
                                <form:input path="bwStatisAmt" /> (元)
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">刷卡笔数 :</label>
                            <div class="controls">
                                <form:input path="optNum" /> (笔)
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">凭证号 :</label>
                            <div class="controls">
                                <form:input path="certNo" />
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
                                <button id="saveBtn" class="btn btn-large btn-primary" type="submit">保存</button>
                                &nbsp;<a href="${sc_ctx}/cardRun" class="btn btn-large">返回</a>
                            </div>
                        </div>
                    </form:form>
                </div>
            </div>
        </div>
    </body>
</html>

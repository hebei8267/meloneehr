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
    		$("#saveBtn").click(function() {
    			$('#__storageRun_confirm').modal({
					backdrop : true,
					keyboard : true,
					show : true
				});
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
                        <h3>${sessionScope.__SESSION_USER_INFO.orgName}店 入货信息 审核</h3>
                    </legend>
                </div>
                <div class="span12"	style="margin-top: 10px;">
                    <form:form method="POST" class="form-horizontal" id="inputForm"	modelAttribute="storageRun">
                        <form:hidden path="uuid"/>
                        <div class="control-group">
                           <label class="control-label">入货单号 :</label>
                           <label class="left-control-label">${storageRun.recordNo}</label>
                        </div>
                        <div class="control-group">
                            <label class="control-label">供应商编号 :</label>
                            <label class="left-control-label">${storageRun.supplierBwId}</label>
                        </div>
                        <div class="control-group">
                            <label class="control-label">开单日期 :</label>
                            <label class="left-control-label">${storageRun.recordDateShow}</label>
                        </div>
                        <div class="control-group">
                            <label class="control-label">入货日期 :</label>
                            <label class="left-control-label">${storageRun.intoDateShow}</label>
                        </div>
                        <div class="control-group">
                            <label class="control-label">开单金额	:</label>
                            <label class="left-control-label">${storageRun.recordAmt}</label>
                        </div>
                        <div class="control-group">
                            <label class="control-label">入库金额	:</label>
                            <label class="left-control-label">${storageRun.optAmt}</label>
                        </div>
                        <div class="control-group">
                            <label class="control-label">入库人	:</label>
                            <label class="left-control-label">${storageRun.optPerName}</label>
                        </div>
                        <div class="control-group">
                            <label class="control-label">备注 :</label>
                            <label class="left-control-label">${storageRun.descTxt}</label>
                        </div>
                        <div class="control-group">
                            <div class="controls">
                                <button	id="saveBtn" class="btn	btn-large btn-primary" type="button">审核</button>
                                &nbsp;<a href="${sc_ctx}/storageRun/auditList" class="btn btn-large">返回</a>
                            </div>
                        </div>
                    </form:form>
                </div>
            </div>
        </div>
        
        <div class="modal hide fade  __model37" id="__storageRun_confirm">
		    <div class="modal-header">
		        <a class="close" data-dismiss="modal">×</a>
		        <h4>系统消息</h4>
		    </div>
		    <div class="modal-body">
		        <center>
		        	<p class="error">审核通过该笔入货流水信息吗？</p>
		        </center>
		    </div>
		    <div class="modal-footer">
		    	<a href="${sc_ctx}/storageRun/auditConfirm/${storageRun.uuid}" class="btn btn-primary">确定</a>
		        <a href="#" class="btn" data-dismiss="modal">关闭</a>
		    </div>
		</div>
    </body>
</html>

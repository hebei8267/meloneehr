<%@	page contentType="text/html;charset=UTF-8"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@	taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@	taglib prefix="page" uri="http://www.opensymphony.com/sitemesh/page"%>
<%@	page import="com.tjhx.common.utils.DateUtils"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"	/>
<c:set var="sc_ctx">
    ${ctx}/sc
</c:set>
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
            	$('#optDateShow').datepicker({
                    format : 'yyyy-mm-dd'
                });
            	
            	$("#inputForm").validate({
                    rules : {
                    	optUid : {
                            length7 : true
                        },
                        optDateShow : {
                            required : true,
                            date : true
                        },
                        optAmtShow : {
                            required : true,
                            money : true
                        },
                        expReason : {
                            required : true,
                            maxlength : 255
                        },
                        descTxt : {
                            maxlength : 255
                        }
                    }
                });
            	
            	$("#saveBtn").click(function() {
                    $("input[type='text'],textarea").each(function(i) {
                        this.value = $.trim(this.value);
                    });

                    $("#inputForm").attr("action", "${sc_ctx}/pettyCash/save");
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
                        <h3>${sessionScope.__SESSION_USER_INFO.orgName} 门店备用金(支出)信息
                        <c:if test="${empty	pettyCash.uuid}">
                            新增
                        </c:if>
                        <c:if test="${!empty pettyCash.uuid}">
                            编辑
                        </c:if>
                        </h3>
                    </legend>
                </div>
                
                <div class="span12"	style="margin-top: 10px;">
                    <form:form method="POST" class="form-horizontal" id="inputForm"	modelAttribute="pettyCash">
                        <form:hidden path="uuid"/>
                        <%//操作类型 0-支出 1-拨入 %>
                        <input id="optType" name="optType" type="hidden" value="0"/>                        
                        <div class="control-group">
                            <label class="control-label">业务编号(UID) :</label>
                            <div class="controls">
                            	<c:if test="${empty	pettyCash.uuid}">
                          		<form:input	path="optUid" />
                          		</c:if>
                          		<c:if test="${!empty pettyCash.uuid}">
                                <label class="left-control-label">${pettyCash.optUid}</label>
                                <form:hidden path="optUid"/>
                            </c:if>
                          	</div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">业务日期 :</label>
                            <div class="controls">
                          		<form:input	path="optDateShow" />
                          	</div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">支出金额 :</label>
                            <div class="controls">
                          		<form:input	path="optAmtShow" />
                          	</div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">支出事项 :</label>
                            <div class="controls">
                          		<form:textarea path="expReason" class="input-xlarge" rows="4"/>
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
                                &nbsp;<a href="${sc_ctx}/pettyCash" class="btn btn-large">返回</a>
                            </div>
                        </div>
                    </form:form>
                </div>
            </div>
        </div>
    </body>
</html>
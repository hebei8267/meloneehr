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
    <head></head>
    <body>
        <%// 系统菜单  %>
        <page:applyDecorator name="menu" />

        <div class="container">
            <form class="form-horizontal" >

                <div class="row">
                    <div class="span12">
                        <legend>
                            <h3>${sessionScope.__SESSION_USER_INFO.orgName}店 刷卡情况
                            <c:if test="${empty cardRun.uuid}">
                                新增
                            </c:if>
                            <c:if test="${!empty cardRun.uuid}">
                                编辑
                            </c:if></h3>
                        </legend>
                    </div>

                    <div class="span12" style="margin-top: 10px;">
                        <form class="form-horizontal">
                            <div class="control-group">
                                <label class="control-label">日期 :</label>
                                <div class="controls">
                                    <input type="text">
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label">单据统计 :</label>
                                <div class="controls">
                                    <input type="text">
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label">电脑统计 :</label>
                                <div class="controls">
                                    <input type="text">
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label">刷卡笔数 :</label>
                                <div class="controls">
                                    <input type="text" class="input-mini">
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label">凭证号 :</label>
                                <div class="controls">
                                    <input type="text">
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label">备注 :</label>
                                <div class="controls">
                                    <textarea class="input-xlarge" id="textarea" rows="4"></textarea>
                                </div>
                            </div>
                            <div class="control-group">
                                <div class="controls">
                                    <button class="btn btn-large btn-primary" type="submit">保 存</button>
                                    &nbsp;<a href="${sc_ctx}/cardRun" class="btn btn-large">返 回</a>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </form>
        </div>
    </body>
</html>

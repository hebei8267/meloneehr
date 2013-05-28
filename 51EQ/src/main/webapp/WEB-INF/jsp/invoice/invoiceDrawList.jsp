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
    <head></head>
    <body>
        <%// 系统菜单  %>
        <page:applyDecorator name="menu" />
        
        <div class="container">
            <form method="post"	class="form-horizontal"	id="listForm">
                <div class="row">
                    <div class="span12">
                        <legend>
                            <h3>${sessionScope.__SESSION_USER_INFO.orgName} 发票开具信息</h3>
                        </legend>
                    </div>
                    <div class="span3">
                        <label class="control-label">申请机构 :</label>
                        <input id="recordDateShow" name="recordDateShow" type="text" class="input-medium" value="${recordDateShow }"/>
                    </div>
                    <div class="span3">
                        <label class="control-label">申请时间 :</label>
                        <input id="recordDateShow" name="recordDateShow" type="text" class="input-medium" value="${recordDateShow }"/>
                    </div>
                    <div class="span6">
                        <label class="control-label">发票号 :</label>
                        <input id="recordDateShow" name="recordDateShow" type="text" class="input-medium" value="${recordDateShow }"/>
                        <button	id="searchBtn" class="btn	btn-primary" type="button">查询</button>
                    </div>
                    <div class="span12"	style="margin-top: 10px;">
                        <table class="table	table-striped table-bordered table-condensed mytable">
                            <thead>
                                <tr>
                                    <th>
                                        申请机构
                                    </th>
                                    <th>
                                        申请时间
                                    </th>
                                    <th>
                                        申请人
                                    </th>
                                    <th>
                                        发票种类
                                    </th>
                                    <th>
                                        发票台头
                                    </th>
                                    <th>
                                        发票金额
                                    </th>
                                    <th width="80" class="center">
                                        邮寄客户
                                    </th>
                                    <th>
                                        发票号
                                    </th>
                                    <th	width="55">
                                        &nbsp;
                                    </th>
                                </tr>
                            </thead>
                            <tbody>
                            </tbody>
                            <c:if test="${empty	invoiceDrawList}" >
                                <tfoot>
                                    <tr>
                                        <td	colspan="9"	class="rounded-foot-left">
                                            无记录信息
                                        </td>
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
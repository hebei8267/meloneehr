<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="page" uri="http://www.opensymphony.com/sitemesh/page"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<c:set var="sc_ctx">${ctx}/sc</c:set>
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
                            <h3>${sessionScope.__SESSION_USER_INFO.orgName}店 刷卡情况</h3>
                        </legend>
                        <a href="${sc_ctx}/cardRun/new" class="btn btn-primary">新 增</a>
                    </div>
                    <div class="span12" style="margin-top: 10px;">
                        <table class="table table-striped table-bordered table-condensed mytable">
                            <thead>
                                <tr>
                                    <th width="10%">
                                        日期
                                    </th>
                                    <th width="10%">
                                        单据统计
                                    </th>
                                    <th width="12%">
                                        电脑统计(百威)
                                    </th>
                                    <th width="8%">
                                        刷卡笔数
                                    </th>
                                    <th width="12%">
                                        凭证号
                                    </th>
                                    <th>
                                        备注(盈亏原因)
                                    </th>
                                    <th width="13%">
                                        &nbsp;
                                    </th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:if test="${empty cardRunList}" >
                                    <tfoot>
                                        <tr>
                                            <td colspan="7" class="rounded-foot-left">
                                                无记录信息
                                            </td>
                                        </tr>
                                    </tfoot>
                                </c:if>
                                <c:forEach items="${cardRunList}" var="cardRun">
                                    <tr>
                                        <td>
                                            ${cardRun.optDateShow}
                                        </td>
                                        <td>
                                            ${cardRun.recordStatisAmt}
                                        </td>
                                        <td>
                                            ${cardRun.bwStatisAmt}
                                        </td>
                                        <td>
                                            ${cardRun.optNum}
                                        </td>
                                        <td>
                                            ${cardRun.certNo}
                                        </td>
                                        <td>
                                            ${cardRun.descTxt}
                                        </td>
                                        <td>
                                            &nbsp;
                                        </td>
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
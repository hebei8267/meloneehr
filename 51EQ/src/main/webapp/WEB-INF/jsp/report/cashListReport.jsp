<%@	page contentType="text/html;charset=UTF-8"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@	taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@	taglib prefix="page" uri="http://www.opensymphony.com/sitemesh/page"%>
<%@	page import="com.tjhx.common.utils.DateUtils"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"	/>
<c:set var="sc_ctx">
    ${ctx}/sc
</c:set>
<c:set var="DEFAULT_RETAINED_AMT">
    ${DEFAULT_RETAINED_AMT}
</c:set>
<!DOCTYPE html>
<html>
    <head>
        <script>
            $(function() {
                $('#optDateShow').datepicker({
                    format : 'yyyy-mm',
                    viewMode : 1,
                    minViewMode : 1
                });

                $("#searchBtn").click(function() {
                    $("input[type='text'],textarea").each(function(i) {
                        this.value = $.trim(this.value);
                    });

                    $("#listForm").attr("action", "${sc_ctx}/cashReport/search");
                    $("#listForm").submit();
                });
            });
        </script>
    </head>
    <body>
        <%// 系统菜单  %>
        <page:applyDecorator name="menu" />

        <div class="container">
            <form method="post"	class="form-inline" id="listForm">
                <div class="row">
                    <div class="span12">
                        <legend>
                            <h3>销售信息</h3>
                        </legend>
                    </div>
                    <div class="span3">
                        <label class="control-label">机构 :</label>
                        <select name="orgId" class="input-medium">
                            <c:forEach items="${orgList}" var="org">
                                <c:if test="${org.key == orgId}">
                                    <option value="${org.key }" selected>${org.value }</option>
                                </c:if>
                                <c:if test="${org.key != orgId}">
                                    <option value="${org.key }">${org.value }</option>
                                </c:if>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="span9">
                        <label class="control-label">销售日期 :</label>
                        <input id="optDateShow" name="optDateShow" type="text" class="input-medium" value="${optDateShow }"/>
                        <button	id="searchBtn" class="btn	btn-primary" type="button">查询</button>
                    </div>
                    <div class="span12"	style="margin-top: 10px;">
                        <table class="table	table-striped table-bordered table-condensed mytable">
                            <thead>
                                <tr>
                                    <th>
                                        机构
                                    </th>
                                    <th>
                                        日结日期
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
                                    <th	width="55">
                                        &nbsp;
                                    </th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${cashDailyList}" var="cashDaily">
                                    <tr>
                                        <td>
                                        	${cashDaily.orgName}
                                        </td>
                                        <td>
                                        	${cashDaily.optDateShow}
                                        </td>
                                        <td>
                                        	${cashDaily.initAmt}
                                        </td>
                                        <td>
                                        	${cashDaily.saleAmt}
                                        </td>
                                        <td>
                                        	${cashDaily.cashAmt}
                                        </td>
                                        <td <c:if test="${cashDaily.cardAmt != cashDaily.cardAmtBw}">style="background-color:#F89406;color:#FFFFFF"</c:if>>
                                        	${cashDaily.cardAmt}
                                        </td>
                                        <td <c:if test="${cashDaily.cardAmt != cashDaily.cardAmtBw}">style="background-color:#F89406;color:#FFFFFF"</c:if>>
                                        	${cashDaily.cardAmtBw}
                                        </td>
                                        <td>
                                        	${cashDaily.cardNum}
                                        </td>
                                        <td>
                                        	${cashDaily.depositAmt}
                                        </td>
                                        <td <c:if test="${cashDaily.retainedAmt > DEFAULT_RETAINED_AMT}">style="background-color:#FF6633;color:#FFFFFF"</c:if>>
                                        	${cashDaily.retainedAmt}
                                        </td>
                                        <td>
                                            <a href="${sc_ctx}/cashReport/detail/${cashDaily.optDate}/${cashDaily.orgId}" target="_blank" class="btn btn-warning"/>详细</a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                            <c:if test="${empty	cashDailyList}" >
                                <tfoot>
                                    <tr>
                                        <td	colspan="11"	class="rounded-foot-left">
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
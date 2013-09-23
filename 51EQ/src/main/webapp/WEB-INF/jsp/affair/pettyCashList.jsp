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
		<script>
		$(function() {
            $("#listForm").validate({
                rules : {
                    delBtn : {
                        requiredSelect : 'uuid'
                    }
                }
            });

            //-----------------------------------
            // 全选/全部选
            //-----------------------------------
            $("#checkAll").click(function() {
                $('input[name="uuid"]').attr("checked", this.checked);
            });
            var $subCheckBox = $("input[name='uuid']");
            $subCheckBox.click(function() {
                $("#checkAll").attr("checked", $subCheckBox.length == $("input[name='uuid']:checked").length ? true : false);
            });
            //-----------------------------------
            // 删除按钮点击
            //-----------------------------------
            $("#delBtn").click(function() {
                if ($("#listForm").valid()) {
                    $('#__del_confirm').modal({
                        backdrop : true,
                        keyboard : true,
                        show : true
                    });
                }
            });
        });
		
		//-----------------------------------
        // 删除
        //-----------------------------------
        function _del_confirm() {
            var $subCheckBox = $("input[name='uuid']");
            var uuids = "";
            $.each($subCheckBox, function(index, _checkBox) {
                if (_checkBox.checked) {
                    uuids += _checkBox.value + ",";
                }
            });
            if (uuids.length > 0) {
                uuids = uuids.substring(0, uuids.length - 1);
            }

            $("#uuids").val(uuids);
            $("#listForm").attr("action", "${sc_ctx}/pettyCash/del");
            $("#listForm").submit();
        }
		</script>
	</head>
	<body>
        <%// 系统菜单  %>
        <page:applyDecorator name="menu" />
        
        
        <div class="container">
            <form method="post"	class="form-horizontal"	id="listForm">
                <div class="row">
                    <div class="span12">
                        <legend>
                            <h3>${sessionScope.__SESSION_USER_INFO.orgName} 门店备用金信息</h3>
                        </legend>
                    </div>
                    <div class="span12">
                        <a href="${sc_ctx}/pettyCash/payNew"	class="btn btn-primary">支出</a>
                        <a href="${sc_ctx}/pettyCash/incomeNew"	class="btn btn-warning">拨入</a>
                        <input id="delBtn" name="delBtn" type="button" class="btn btn-danger" value="删除"/>
                    </div>
                    <div class="span12"	style="margin-top: 10px;">
                        <input type="hidden" name="uuids" id="uuids"/>
                        <table class="table	table-striped table-bordered table-condensed mytable">
                        	<thead>
                                <tr>
                                    <th	width="25" class="center">
                                        <input id="checkAll" type="checkbox" />
                                    </th>
                                    <th width="130">
                                        业务编号(UID)
                                    </th>
                                    <th width="130">
                                        业务日期
                                    </th>
                                    <th width="40" class="center">
                                    星期
                                    </th>
                                    <th>
                                        支出/拨入(金额)
                                    </th>
                                    <th>
                                        支出/拨入(事项)
                                    </th>
                                    <th width="110">
                                        备用金余额
                                    </th>
                                    <th	width="55">
                                        &nbsp;
                                    </th>
                                </tr>
                            </thead>
                            <tbody>
                            	<c:forEach items="${pettyCashList}" var="pettyCash">
                            	<tr>
                                        <td	class="center">
                                            <input type="checkbox" name="uuid" value="${pettyCash.uuid}">
                                        </td>
                                        <td>
                                            ${pettyCash.optUid}
                                        </td>
                                        <td>
                                            ${pettyCash.optDateShow}
                                        </td>
                                        <td class="center">
                                            ${pettyCash.week}
                                        </td>
                                        <td>
                                            ${pettyCash.optAmt}
                                        </td>
                                        <td>
                                            ${pettyCash.expReason}
                                        </td>
                                        <td>
                                            
                                        </td>
                                        <td>
                                            <a href="${sc_ctx}/pettyCash/edit/${pettyCash.uuid}" class="btn btn-warning"/>修改</a>
                                        </td>
                                    </tr>
                            	</c:forEach>
                            </tbody>
                            <c:if test="${empty	pettyCashList}" >
                                <tfoot>
                                    <tr>
                                        <td	colspan="8" class="rounded-foot-left">
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
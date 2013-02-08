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
			$().ready(function() {
				//-----------------------------------
				// 表单效验
				//-----------------------------------
				$("#listForm").validate({
					rules: {
						delBtn : {
							requiredSelect : 'uuid'
						}
					}
				});
				//-----------------------------------
				// 全选/全部选
				//-----------------------------------
				$("#checkAll").click(function()	{
					$('input[name="uuid"]').attr("checked",this.checked);
				});
				var	$subCheckBox = $("input[name='uuid']");
				$subCheckBox.click(function(){
					$("#checkAll").attr("checked",$subCheckBox.length == $("input[name='uuid']:checked").length	? true : false);
				});
				
				//-----------------------------------
				// 删除按钮点击
				//-----------------------------------
				$("#delBtn").click(function() {
					if($("#listForm").valid()){
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
			function _del_confirm(){
				var	$subCheckBox = $("input[name='uuid']");
				var	uuids =	"";
				$.each($subCheckBox, function(index, _checkBox)	{
					if(_checkBox.checked){
						uuids += _checkBox.value + ",";
					}
				});
				if(uuids.length	> 0){
					uuids =	uuids.substring(0, uuids.length	- 1);
				}
				
				$("#uuids").val(uuids);
				$("#listForm").attr("action", "${sc_ctx}/supplier/del");
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
                            <h3>供应商管理</h3>
                        </legend>
                    </div>
                    <div class="span12">
                        <a href="${sc_ctx}/supplier/new" class="btn btn-primary">新增</a>
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
                                    <th>
                                        供应商编号
                                    </th>
                                    <th>
                                        供应商名称
                                    </th>
                                    <th>
                                        付款方式
                                    </th>
                                    <th>
                                        所在区域             
                                    </th>
                                    <th	width="55">
                                        &nbsp;
                                    </th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:if test="${empty	supplierList}" >
                                    <tfoot>
                                        <tr>
                                            <td	colspan="6" class="rounded-foot-left">
                                                无记录信息
                                            </td>
                                        </tr>
                                    </tfoot>
                                </c:if>
                                <c:forEach items="${supplierList}" var="supplier">
                                    <tr>
                                        <td	class="center">
                                        	<input type="checkbox" name="uuid" value="${supplier.uuid}"></input>
                                        </td>
                                        <td>
                                            ${supplier.supplierBwId}
                                        </td>
                                        <td>
                                            ${supplier.name}
                                        </td>
                                        <td>
                                            <c:if test="${supplier.payType == '1'}">
												现款商户
                                            </c:if>
                                            <c:if test="${supplier.payType == '2'}">
												月结商户
                                            </c:if>
                                            <c:if test="${supplier.payType == '4'}">
												不定
                                            </c:if>
                                        </td>
                                        <td>
                                            ${supplier.region.name}
                                        </td>
                                        <td>
                                       		<a href="${sc_ctx}/supplier/edit/${supplier.uuid}" class="btn btn-warning"/>修改</a>
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
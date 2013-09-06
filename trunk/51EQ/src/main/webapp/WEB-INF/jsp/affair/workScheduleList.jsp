<%@	page contentType="text/html;charset=UTF-8"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@	taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@	taglib prefix="page" uri="http://www.opensymphony.com/sitemesh/page"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@	page import="com.tjhx.common.utils.DateUtils"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"	/>
<c:set var="sc_ctx">
    ${ctx}/sc
</c:set>
<!DOCTYPE html>
<html>
    <head>
    	<style type="text/css">
    		.myTable th, .myTable td {
				border: 0;
			}
			._warn1 {
				padding: 5px;
				background-color: #00DB00;
			}
	    	._warn2 {
	    		padding: 5px;
				background-color: #B15BFF;
			}
			._warn3 {
				padding: 5px;
				background-color: #FF9224;
			}
			._warn4 {
				padding: 5px;
				background-color: #B87070;
			}
    	</style>
    	<script>
    		function wtChangeTxt(index){
    			var selVal = $("#_wt"+index).val();
    			if(selVal != ""){
    				var cssNo = selVal % 16 ;
    				
    				if(cssNo == 1) {
    					$("#_wtTxt"+index).removeClass("_warn2");
    					$("#_wtTxt"+index).removeClass("_warn3");
    					$("#_wtTxt"+index).removeClass("_warn4");
    					
              			$("#_wtTxt"+index).addClass("_warn1");
    				} else if(cssNo == 2) {
    					$("#_wtTxt"+index).removeClass("_warn1");
    					$("#_wtTxt"+index).removeClass("_warn3");
    					$("#_wtTxt"+index).removeClass("_warn4");
    					
              			$("#_wtTxt"+index).addClass("_warn2");
    				} else if(cssNo == 3) {
    					$("#_wtTxt"+index).removeClass("_warn1");
    					$("#_wtTxt"+index).removeClass("_warn2");
    					$("#_wtTxt"+index).removeClass("_warn3");
    					
              			$("#_wtTxt"+index).addClass("_warn3");
    				} else {
    					$("#_wtTxt"+index).removeClass("_warn1");
    					$("#_wtTxt"+index).removeClass("_warn2");
    					$("#_wtTxt"+index).removeClass("_warn3");
    					
              			$("#_wtTxt"+index).addClass("_warn4");
    				}
    				
    			} else {
    				$("#_wtTxt"+index).removeClass("_warn1");
					$("#_wtTxt"+index).removeClass("_warn2");
					$("#_wtTxt"+index).removeClass("_warn3");
          			$("#_wtTxt"+index).removeClass("_warn4");
    			}
    			$("#_wtTxt"+index).html(_get_wt_data(selVal));
    		}
    		
    		var _wt_data_set = ${_wt_data_set};
    		
    		function _get_wt_data(key){
    			for(var p in _wt_data_set){
        		    if(key == p){
        		    	return _wt_data_set[p];
        		    }
        		}
    			return "";
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
                            <h3>${sessionScope.__SESSION_USER_INFO.orgName} 排班表 维护</h3>
                        </legend>
                    </div>
                    
                    <div class="span3">
                    	<span class="_warn1 center">早班</span>
						<span class="_warn2 center">晚班</span>
                 		<span class="_warn3 center">全天班</span>
                 		<span class="_warn4 center">其它</span>
                 	</div>
                </div>
                <div class="row">
                    <div class="span12"	style="margin-top: 10px;">
                    	<table class="table	table-striped table-bordered table-condensed mytable">
                            <thead>
                                <tr>
                                    <th width="100" class="center">
                                        日期
                                    </th>
                                    <th width="50" class="center">
                                        星期
                                    </th>
                                    <c:forEach items="${empList}" var="emp">
                                    <th class="center">
                                        ${emp.name}
                                    </th>
                                    </c:forEach>
                                </tr>
                            </thead>
                            <tbody>
	                            <c:forEach items="${wsList}" var="ws">
	                            <tr>
	                            	<c:if test="${ws.editFlg}">
	                            	<input type="hidden" name="scheduleDate" value="${ws.scheduleDate}">
	                            	</c:if>
	                            	
	                            	<td class="center">${ws.scheduleDate}</td>
	                            	<td class="center">${ws.week}</td>
	                            	<c:forEach items="${ws.scheduleList}" var="subSchedule" varStatus="status">
                            		<td class="center">
                            			<c:if test="${ws.editFlg}">
                            			<table class="table myTable" style="margin-bottom :0;border :0">
                            				<tr>
                            					<td class="center">
                            						<input type="hidden" name="empCode" value="${subSchedule.empCode}">
			                            			<select class="input-small2" id="_wt${status.index + 1}" onchange="wtChangeTxt(${status.index + 1})">
							                        	<c:forEach items="${wtList}" var="wt">
							                        		<option value="${wt.key }">${wt.value }</option>
							                            </c:forEach>
							                        </select>
                            					</td>
                            				</tr>
                            				<tr>
                            					<td class="center" id="_wtTxt${status.index + 1}">&nbsp;</td>
                            				</tr>
                            			</table>
                            			</c:if>
                            		</td>
                            		</c:forEach>
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
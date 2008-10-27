<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%// 用户密码修改 %>
<html>
	<head>
    	<%@ include file="/WEB-INF/jsp/base/PageHeader.jsp" %>
        <%@ include file="/WEB-INF/jsp/base/CommonCssJs.jsp" %>
        <%@ include file="/WEB-INF/jsp/base/ImportCommonPackage.jsp" %>
        <script type="text/javascript">
        <!--
        <%// 用户密码变更校验 %>
        function modPwdCheck(){
        	var msg = "";
        	if(isEmpty($F('oldPassword'))){
        		msg += getNeedInputMsg("原密码");
        	}
        	if(isEmpty($F('newPassword'))){
        		msg += getNeedInputMsg("新密码");
        	}
        	if(isEmpty($F('newPassword2'))){
        		msg += getNeedInputMsg("新密码(确认用)");
        	}
        	if(!isEmpty(msg)){
        		showMessageBox(msg);
        		return false;
        	}
        	
        	if($F('newPassword') != $F('newPassword2')){
        		msg += getNeedSameInputMsg("新密码", "新密码(确认用)");
        	}
        	if(!isEmpty(msg)){
        		showMessageBox(msg);
        		return false;
        	}
        	
        	return true;
        }
        <%// 用户密码变更 %>
        function modPwd(){
        	if(!modPwdCheck()){
        		return;
        	}
        	
        	Ext.Ajax.request({
				url : 'FD000S002AjaxViewAction_ModPwdAction.ajax',
				success : function(result, request) {
					var oResult = eval("(" + result.responseText + ")");
					
					if(oResult.processResult) {// 成功
						goHome();
					} else {//失败
						//Ajax系统定式
						if(!oResult.processResult && oResult.sessionTimeOut){
							$("sessionTimeOutForm").target = "_self";
							$("sessionTimeOutForm").submit();
							return;
						}
						showMessageBox(oResult.resultMsg);
						cleanInfo();
					}
				},
				failure : function(result, request) {
					showMessageBox("和服务通信发生错误,请稍候再试!")
				},
				params : {
					userId : $F('userId'),
					oldPassword : $F('oldPassword'),
					newPassword : $F('newPassword')
				}
			});
        }
        <%// 用户密码变更成功 %>
        function goHome(){
        	$("modPwdForm").action = "FD000S003JspViewAction_ShowPageAction.faces";
        	$("modPwdForm").submit();
        }
        <%// 信息清空 %>
        function cleanInfo(){
        	$("oldPassword").value = "";
        	$("newPassword").value = "";
        	$("newPassword2").value = "";
        }
        -->
        </script>
	</head>
	<body>
	<div class="defaultBody">
		<%@ include file="/WEB-INF/jsp/base/PageHeaderDate.jsp" %>
		<%@ include file="/WEB-INF/jsp/base/SysErrorFrom.jsp" %>
    	<br>
   	 	<div>
		    <table class="spiltLine">
		        <tr>
		            <td>
		                <%// 分割线 %>
		            </td>
		        </tr>
		    </table>
   			<br>
		  	<table class="appTitleTable">
		  		<tr>
		  			<td class="appTitle">
		  				用户密码变更
		          	</td>
		          	<td class="appScreenID">
		              - FD000S002 -
		          	</td>
		      	</tr>
		       	<tr>
		     		<td><br></td>
		       	</tr>
		        <tr>
		        	<td colspan="2" class="tip">
			            <table>
			                <tr>
			                    <td>
			                        <img src="images/tip.png">
			                    </td>
			                    <td>
			                        <span class="need">注意:[<img src="images/cube-red.png">]为必填项</span>
			                    </td>
			                </tr>
			                <tr>
			                    <td>
			                    </td>
			                    <td>
			                    	请输入您的原密码和新密码
			                    </td>
			                </tr>
			                <tr>
			                    <td>
			                    </td>
			                    <td>
			                    	如果想放弃修改,请点击<span style="font-weight: bold;">HOME</span>返回
			                    </td>
			                </tr>
			            </table>
		            </td>
		      	</tr>
			</table>
		   	<br>
		   	<%@ include file="/WEB-INF/jsp/base/PageMessageInfo.jsp" %>
		   	<br>
		   	<form:form id="modPwdForm" method="post" modelAttribute="FD000S002ViewObject">
		   		<table>
			    	<tr>
			        	<td width="60">
			            </td>
			            <td>
			            	<table>
			              		<tr>
			                    	<td colspan="2" class="itemTitle">
			                    		用户密码变更信息
			                        </td>
			               		</tr>
			                  	<tr>
			                		<td class="inputItemName" height="30" width="120">
			                               用户名
			                       	</td>
			               			<td class="inputItemCell" height="30" width="200">
			               				<c:out value="${userInfo.userName}"/>【<c:out value="${userInfo.userId}"/>】
			                      		<input type="hidden" id="userId" name="userId" value="${userInfo.userId}">
			                      	</td>
			                  	</tr>
			                 	<tr>
			                   		<td class="inputItemName" height="30" width="120">
			                       		<img src="images/cube-red.png">原密码
			                       	</td>
			                      	<td class="inputItemCell" height="30" width="200">
			                      		<form:password path="oldPassword" size="20" maxlength="20"/>
			                        </td>
			               		</tr>
			                  	<tr>
			                   		<td class="inputItemName" height="30" width="120">
			                      		<img src="images/cube-red.png">新密码
			                     	</td>
			                     	<td class="inputItemCell" height="30" width="200">
			                     		<form:password path="newPassword" size="20" maxlength="20"/>
			                     	</td>
			                  	</tr>
			                 	<tr>
			                  		<td class="inputItemName" height="30" width="120">
			                   			<img src="images/cube-red.png">新密码(确认用)
			                   		</td>
			                    	<td class="inputItemCell" height="30" width="200">
			                      		<form:password path="newPassword2" size="20" maxlength="20"/>
			                      	</td>
			              		</tr>
			          		</table>
			          		<br>
							<%// 按钮 START %>
							<table>
						  		<tr>
						     		<td height="30" width="140" class="">
						       			<input value="HOME" class="buttonResetLong" type="button" onclick="goHome();">
						     		</td>
						       		<td align="right">
						          		<input value="变  更" class="buttonSubmitLong" type="button" onclick="modPwd();">
						         	</td>
						           	<td width="20">
						        	</td>
						           	<td align="left">
						       			<input value="重  置" class="buttonResetLong" type="button" onclick="cleanInfo();">
									</td>
						     	</tr>
							</table>
							<%// 按钮 END %>
						</td>
					</tr>
				</table>
		   	</form:form>
		</div>
		<%@ include file="/WEB-INF/jsp/base/PageFooter.jsp" %>
	</div>
    </body>
</html>

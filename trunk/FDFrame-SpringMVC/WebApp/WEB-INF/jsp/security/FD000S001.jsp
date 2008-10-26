<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
    <head>
        <%@ include file="/WEB-INF/jsp/base/PageHeader.jsp" %>
        <%@ include file="/WEB-INF/jsp/base/CommonCssJs.jsp" %>
        <%@ include file="/WEB-INF/jsp/base/ImportCommonPackage.jsp" %>
        <script type="text/javascript">
        <!--
        <%// 用户登录校验 %>
        function loginCheck(){
        	var msg = "";
        	if(isEmpty($F('userId'))){
        		msg += getNeedInputMsg("用户名");
        	}
        	if(isEmpty($F('password'))){
        		msg += getNeedInputMsg("用户密码");
        	}
        	if(!isEmpty(msg)){
        		showMessageBox(msg);
        		return false;
        	}
        	return true;
        }
        <%// 用户登录 %>
        function login(){
        	if(!loginCheck()){
        		return;
        	}
        	
        	Ext.Ajax.request({
				url : 'FD000S001AjaxViewAction_LoginAction.ajax',
				success : function(result, request) {
					var oResult = eval("(" + result.responseText + ")");
					
					if(oResult.processResult) {// 成功
						loginSuccess();
					} else {//失败
						//Ajax系统定式
						if(!oResult.processResult && oResult.sessionTimeOut){
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
					password : $F('password')
				}
			});
        }
        <%// 用户登录成功 %>
        function loginSuccess(){
        	if($F("changePassword2") == "true"){//修改用户密码
        		$("loginForm").action = "FD000S002JspViewAction_ShowPageAction.faces";
        	}else{//工作区主界面
        		$("loginForm").action = "FD000S003JspViewAction_ShowPageAction.faces";
        	}
        	$("loginForm").submit();
        }
        <%// 信息清空 %>
        function cleanInfo(){
        	$("userId").value = "";
        	$("password").value = "";
        }
        -->
        </script>
    </head>
    <body>
    	<%@ include file="/WEB-INF/jsp/base/SysErrorFrom.jsp" %>
        <%// 标题栏 START %>
        <div>
            <center>
                <table class="titleTable">
                    <tr>
                        <td colspan="2" class="loginUser">
                            &nbsp;
                        </td>
                    </tr>
                    <tr>
                        <td class="loginDate">
                        	<%=systemDate.getNowDate()%>
                            <%=systemDate.getNowWeek()%>
                        </td>
                    </tr>
                </table>
            </center>
        </div>
        <%// 标题栏 END %>
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
                    	用户登录
                    </td>
                    <td class="appScreenID">
                        - FD000S001 -
                    </td>
                </tr>
                <tr>
                    <td>
                        <br>
                    </td>
                </tr>
                <tr>
                    <td colspan="2" class="opTip">
                        <%// 操作说明 %>
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
                                	请输入用户名称和密码,登录系统
                                </td>
                            </tr>
                            <tr>
                                <td>
                                </td>
                                <td>
                                	登录系统的同时,如果想修改密码,请选择[变更]选项
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
            </table>
            <br>
            <%@ include file="/WEB-INF/jsp/base/PageMessageInfo.jsp" %>
            <br>
            <form:form id="loginForm" method="post" modelAttribute="FD000S001ViewObject">
                <table>
                    <tr>
                        <td width="60">
                        </td>
                        <td>
                            <%// 操作区域 START %>
                            <table>
                                <tr>
                                    <td colspan="2" class="itemTitle">
                                    	用户登录信息
                                    </td>
                                </tr>
                                <tr>
                                    <td class="inputItemName" height="30" width="100">
                                        <img src="images/cube-red.png">用户名
                                    </td>
                                    <td class="inputItemCell" height="30" width="200">
                                    	<form:input path="userId" size="20" maxlength="20"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="inputItemName" height="30" width="100">
                                        <img src="images/cube-red.png">用户密码
                                    </td>
                                    <td class="inputItemCell" height="30" width="200">
                                    	<form:password path="password" size="20" maxlength="20"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="inputItemName" height="30" width="100">
                                    	变更密码
                                    </td>
                                    <td class="inputItemCell" height="30" width="200">
                                    	<form:radiobutton path="changePassword" value="false"/>
										<label>
                                        	不变更
                                        </label>
                                        <form:radiobutton path="changePassword" value="true" />
										<label>
                                        	变更
                                        </label>
                                    </td>
                                </tr>
                            </table>
                            <%// 操作区域 END %>
                            <br>
                            <%// 按钮 START %>
                            <table>
                                <tr>
                                    <td height="30" width="120" class="">
                                    </td>
                                    <td align="right">
                                        <input value="登  录" class="buttonSubmitLong" type="button" onclick="login();">
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
    </body>
</html>
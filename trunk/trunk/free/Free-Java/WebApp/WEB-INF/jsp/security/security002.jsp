<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ taglib prefix="extjs" uri="http://www.freedom.org/tags/form"%>

<html>
    <head>
        <%@ include file="/WEB-INF/jsp/base/pageHeader.jsp" %>
        <%@ include file="/WEB-INF/jsp/base/commonCssJs.jsp" %>
        <%@ include file="/WEB-INF/jsp/base/importCommonPackage.jsp" %>
        <script type="text/javascript">
        <!--
            // 用户密码变更
            function modPwd(){
                if(!formExtCmpValidate("modPwdForm")){
                    return;
                }
                
                Ext.Ajax.request({
                    url : '${pageContext.request.contextPath}/security/002/modPwdAction.ajax',
                    method: 'post',
                    failure : defaultAjaxRequestFailure,
                    success : function(result, request) {
                        var oResult = eval("(" + result.responseText + ")");
                        
                        if(oResult.processResult) {// 成功
                            showMessageBox("<br>密码变更成功!",goHome);
                        } else {// 失败
                            // Ajax系统定式 start
                            if(!oResult.processResult && oResult.sessionTimeOut){
                                $("systemErrorForm").target = "_top";
                                $("systemErrorForm").submit();
                                return;
                            }
                            showMessageBox(oResult.resultMsg);
                            // Ajax系统定式 end
                            
                            formReset();
                        }
                    },
                    params : {
                        userId : $F('userId'),
                        oldPassword : $F('oldPassword'),
                        newPassword : $F('newPassword')
                    }
                });
            }
            // 表单重置
            function formReset(){
                formExtCmpReset("modPwdForm");
            }
            // 新密码和新密码确认是否一样
            function checkNewPwd(){
                var _newPassword = Ext.getCmp("newPassword").getValue();
                var _confirmPassword = Ext.getCmp("confirmPassword").getValue();
                if(_newPassword != _confirmPassword) {
                    return getErrorMsg_AM002("新密码", "新密码(确认用)");
                } else {
                    return true;
                }
            }
            // 用户密码变更成功
            function goHome(){
                $("modPwdForm").action = "${pageContext.request.contextPath}/security/003/showPageAction.faces";
                $("modPwdForm").submit();
            }
        -->
        </script>
    </head>
    <body>
        <div class="defaultBody">
            <!-- 标题栏 -->
            <div>
                <%@ include file="/WEB-INF/jsp/base/pageHeaderDate.jsp" %>
                <table class="spiltLine">
                    <tr>
                        <td><!-- 分割线 --></td>
                    </tr>
                </table>
                <table class="appTitleTable">
                    <tr>
                        <td class="appTitle">
                        用户密码变更
                        </td>
                        <td class="appScreenID">
                        -SECURITY 002-
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2" class="operTip">
                            <!-- 操作说明 -->
                            <table>
                                <tr>
                                    <td><img src="${pageContext.request.contextPath}/images/tip.png"></td>
                                    <td><span class="need">注意:[<img src="${pageContext.request.contextPath}/images/need-input.gif">]为必填项</span></td>
                                </tr>
                                <tr>
                                    <td></td>
                                    <td>请输入您的原密码和新密码</td>
                                </tr>
                                <tr>
                                    <td></td>
                                    <td>如果想放弃修改,请点击<span style="font-weight: bold;">HOME</span>返回</td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                </table>
            </div>
            <br>
            <div>
                <form:form id="modPwdForm" method="post" modelAttribute="Security002ViewObject">
                    <table>
                        <tr>
                            <td width="35">
                            </td>
                            <td>
                                <!-- 操作区域 -->
                                <table>
                                    <tr>
                                        <td colspan="2" class="x-panel-header">
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
                                            <img src="${pageContext.request.contextPath}/images/need-input.gif">原密码
                                        </td>
                                        <td class="inputItemCell" height="30" width="200">
                                            <extjs:password path="oldPassword" allowBlank="false" maxLength="20" />
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="inputItemName" height="30" width="120">
                                            <img src="${pageContext.request.contextPath}/images/need-input.gif">新密码
                                        </td>
                                        <td class="inputItemCell" height="30" width="200">
                                            <extjs:password path="newPassword" allowBlank="false" maxLength="20" />
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="inputItemName" height="30" width="120">
                                            <img src="${pageContext.request.contextPath}/images/need-input.gif">新密码(确认用)
                                        </td>
                                        <td class="inputItemCell" height="30" width="200">
                                            <extjs:password path="confirmPassword" allowBlank="false" validator="checkNewPwd" maxLength="20" />
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>&nbsp;</td>
                                    </tr>
                                    <tr>
                                        <!-- 按钮 -->
                                        <td><input value="HOME" class="buttonResetLong" type="button" onclick="goHome();"></td>
                                        <td align="right">
                                            <table>
                                                <tr>
                                                    <td align="right">
                                                        <input value="变  更" class="buttonSubmitLong" type="button" onclick="modPwd();">
                                                    </td>
                                                    <td width="20">
                                                    </td>
                                                    <td align="right">
                                                        <input value="重  置" class="buttonResetLong" type="reset" onclick="formReset();">
                                                    </td>
                                                </tr>
                                            </table>
                                        </td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                    </table>
                </form:form>
            </div>
            <%@ include file="/WEB-INF/jsp/base/pageFooter.jsp" %>
            <%@ include file="/WEB-INF/jsp/base/sysErrorFrom.jsp" %>
        </div>
    </body>
</html>

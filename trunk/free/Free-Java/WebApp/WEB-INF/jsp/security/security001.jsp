<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ taglib prefix="extjs" uri="/WEB-INF/extjs-spring-form.tld" %>

<html>
    <head>
    	<%@ include file="/WEB-INF/jsp/base/pageHeader.jsp" %>
    	<%@ include file="/WEB-INF/jsp/base/commonCssJs.jsp" %>
	    <script type="text/javascript">
	    <!--
	    	<%// 用户登录 %>
	        function login(){
	        	if(Ext.getCmp('userId').isValid()
	        		&& Ext.getCmp('password').isValid()){
	        		$("loginForm").submit();
	        	}
	        }
	    -->
	    </script>
    </head>
    <body>
        <div class="defaultBody">
            <!-- 标题栏 -->
            <div>
                <center>
                    <table class="titleTable">
                        <tr>
                            <td colspan="2" class="loginUserInfo">
                                &nbsp;
                            </td>
                        </tr>
                        <tr>
                            <td class="loginDate">
                                2008/09/06 星期六
                            </td>
                        </tr>
                    </table>
                </center>
                <table class="spiltLine">
                    <tr>
                        <td><!-- 分割线 --></td>
                    </tr>
                </table>
                <table class="appTitleTable">
                    <tr>
                        <td class="appTitle">
                        用户登录
                        </td>
                        <td class="appScreenID">
                        - SECURITY 001 -
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2" class="operTip">
                            <!-- 操作说明 -->
                            <table>
                                <tr>
                                    <td><img src="images/tip.png"></td>
                                    <td><span class="need">注意:[<img src="images/need-input.gif">]为必填项</span></td>
                                </tr>
                                <tr>
                                    <td></td>
                                    <td>请输入用户名称和密码,登录系统</td>
                                </tr>
                                <tr>
                                    <td></td>
                                    <td>登录系统的同时,如果想修改密码,请选择[变更]选项</td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                </table>
            </div>
            <br>
            <div>
                <form:form id="loginForm" method="post" modelAttribute="Security001ViewObject">
                    <table>
                        <tr>
                            <td width="35">
                            </td>
                            <td>
                                <!-- 操作区域 -->
                                <table>
                                    <tr>
                                        <td colspan="2" class="x-panel-header">用户登录信息</td>
                                    </tr>
                                    <tr>
                                        <td class="inputItemName" height="30" width="100">
                                            <img src="images/need-input.gif">用户名
                                        </td>
                                        <td class="inputItemCell" height="30" width="200">
                                            <extjs:input path="userId" minLength="8" maxLength="8" allowBlank="false" />
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="inputItemName" height="30" width="100">
                                            <img src="images/need-input.gif">用户密码
                                        </td>
                                        <td class="inputItemCell" height="30" width="200">
                                            <extjs:password path="password" allowBlank="false" />
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="inputItemName" height="30" width="100">
                                            变更密码
                                        </td>
                                        <td class="inputItemCell" height="30" width="200">
                                            <form:radiobutton path="changePassword" value="false"/>
                                            <label>不变更</label>
                                            <form:radiobutton path="changePassword" value="true" />
                                            <label>变更</label>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>&nbsp;</td>
                                    </tr>
                                    <tr>
                                        <td></td>
                                        <td align="right">
                                            <!-- 按钮 -->
                                            <table>
                                                <tr>
                                                    <td align="right">
                                                        <input value="登  录" class="buttonSubmitLong" type="button" onclick="login();">
                                                    </td>
                                                    <td width="20">
                                                    </td>
                                                    <td align="right">
                                                        <input value="重  置" class="buttonResetLong" type="reset">
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
            <%@ include file="/WEB-INF/jsp/base/PageFooter.jsp" %>
        </div>
    </body>
</html>

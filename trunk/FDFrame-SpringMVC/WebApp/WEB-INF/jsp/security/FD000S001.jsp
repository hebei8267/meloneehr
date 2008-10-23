<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<HTML>
	<HEAD>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		<meta http-equiv="Pragma" CONTENT="no-cache"/>
		<meta http-equiv="Cache-Control" CONTENT="no-cache"/>
		<meta http-equiv="Expires" CONTENT="0"/>
			
		<title>Melone-eHR 人力资源系统</title>
		<link rel="stylesheet" type="text/css" href="css/default.css" />
		<link rel="stylesheet" type="text/css" href="extjs/resources/css/ext-all.css" />
		
		
	</HEAD>
	<BODY>
		<div>
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
                                2008/09/06 星期六
                            </td>
                        </tr>
                    </table>
                </center>
            </div>
            <%// 标题栏 END %>
			<br>
            <div>
                <form name="loginForm" method="post" action="#">
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
                                - SECURITY 001 -
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <br>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2" class="tip">
                                <%// 操作说明 %><img src="images/tip.png">请输入用户名称和密码,登录系统&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="need">注意:[<img src="images/cube-red.png">]为必填项</span>
                                <br>
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;登录系统的同时,如果想修改密码,请选择[变更]选项
                            </td>
                        </tr>
                    </table>
                    <br>
                    <br>
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
                                            <input type="text" size="20" maxlength="20" name="userid" class="inputText">
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="inputItemName" height="30" width="100">
                                            <img src="images/cube-red.png">用户密码
                                        </td>
                                        <td class="inputItemCell" height="30" width="200">
                                            <input type="password" size="20" maxlength="20" name="password" class="inputText">
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="inputItemName" height="30" width="100">
                                            变更密码
                                        </td>
                                        <td class="inputItemCell" height="30" width="200">
                                            <input checked="checked" name="change_password" value="no" id="change_password" type="radio">
                                            <label>
                                                不变更
                                            </label>
                                            <input name="change_password" value="yes" id="change_password" type="radio">
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
                                            <input value="重  置" class="buttonResetLong" type="reset">
                                        </td>
                                    </tr>
                                </table><%// 按钮 END %>
                            </td>
                        </tr>
                    </table>
                </form>
            </div>
            <br>
            <br>
            <br>
            <br>
            <div>
                <table class="appFooterTable">
                    <tr>
                        <td>
                            <center>
                                <a href="">Melone-eHR 人力资源管理</a>&nbsp;｜&nbsp;<a href="mailto:hebei198267@gmail.com">电子邮件</a>
                            </center>
                        </td>
                    </tr>
                </table>
            </div>
        </div>
	</BODY>
</HTML>

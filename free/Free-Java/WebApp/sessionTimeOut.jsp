<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ taglib prefix="extjs" uri="http://www.freedom.org/tags/form"%>

<html>
    <head>
        <script type="text/javascript" src="${pageContext.request.contextPath}/ext-lib/adapter/prototype/prototype.js"></script>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/default.css">
        <%@ include file="/WEB-INF/jsp/base/importCommonPackage.jsp" %>
        <script type="text/javascript">
        <!--
        function loginSystem(){
            $("systemErrorForm").target = "_top";
            $("systemErrorForm").action = "${pageContext.request.contextPath}/index.faces";
            $("systemErrorForm").submit();
            return;
        }
        -->
        </script>
    </head>
    <body>
        <div class="defaultBody">
            <div>
                <table class="titleTable">
                    <tr>
                        <td colspan="2" class="loginUserInfo">
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
                <br>
                <br>
                <br>
                <center>
                    <H1>您的链接已超时！请重新登录系统。</H1>
                    <br>
                    <br>
                    <br>
                    <table>
                        <tr>
                            <td>
                            <input value="登  录" class="bigButtonArea" type="button" onclick="loginSystem();">
                            </td>
                        </tr>
                    </table>
                </center>
                
            </div>
            <%@ include file="/WEB-INF/jsp/base/pageFooter.jsp" %>
            <%@ include file="/WEB-INF/jsp/base/sysErrorFrom.jsp" %>
        </div>
    </body>
</html>
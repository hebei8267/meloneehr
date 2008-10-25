<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%// 标题栏 START %>
<div>
    <center>
        <table class="titleTable">
            <tr>
                <td colspan="2" class="loginUser">
                	您好! <c:out value="${userInfo.userName}"/>
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
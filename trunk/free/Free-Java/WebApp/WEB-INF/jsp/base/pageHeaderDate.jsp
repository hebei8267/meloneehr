<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<center>
    <table class="titleTable">
        <tr>
            <td colspan="2" class="loginUserInfo">
                您好! <c:out value="${userInfo.userName}"/>
            </td>
        </tr>
        <tr>
            <td class="loginDate">
                <input type="button" value="退出系统" onclick="userLoginOutSystem();" class="buttonLoginOutLong">
                <%=systemDate.getNowDate()%>
                <%=systemDate.getNowWeek()%>
            </td>
        </tr>
    </table>
    <script type="text/javascript">
    <!--
    function userLoginOutSystem(){
        $("systemErrorForm").target = "_top";
        $("systemErrorForm").action = "${pageContext.request.contextPath}/security/002/exitSystemAction.faces";
        $("systemErrorForm").submit();
        return;
    }
    -->
    </script>
</center>
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
                	<input type="button" value="退出系统" onclick="userLoginOutSystem();" class="buttonLoginOutLong">
                	<%=systemDate.getNowDate()%>
                    <%=systemDate.getNowWeek()%>
                </td>
            </tr>
        </table>
    </center>
    <script type="text/javascript">
	<!--
	function userLoginOutSystem(){
		$("sessionTimeOutForm").target = "_self";
		$("sessionTimeOutForm").action = "UserLoginOutSystem.faces";
		$("sessionTimeOutForm").submit();
		return;
	}
	-->
	</script>
</div>
<%// 标题栏 END %>
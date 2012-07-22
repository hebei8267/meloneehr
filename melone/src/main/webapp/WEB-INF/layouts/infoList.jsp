<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.tjhx.globals.Constants" %>
<c:if test="${!empty __SESSION_TIP_MSG_LIST || !empty __SESSION_WARN_MSG_LIST || !empty __SESSION_ERR_MSG_LIST}" >
<script>		
$().ready(function() {
	$( "#__dialog-message" ).dialog({
		modal: true,
		buttons: {
			Ok: function() {
				$( this ).dialog( "close" );
			}
		}
	});
});
</script>
</c:if>

<div id="__dialog-message" title="系统消息">
	<c:if test="${!empty __SESSION_TIP_MSG_LIST}" >
		<c:forEach items="${__SESSION_TIP_MSG_LIST}" var="tipMsg">
	 		<p><c:out value="${tipMsg}" /></p>
		</c:forEach>
	</c:if>


	<c:if test="${!empty __SESSION_WARN_MSG_LIST}" >
		<c:forEach items="${__SESSION_WARN_MSG_LIST}" var="warnMsg">
	  		<p><c:out value="${warnMsg}" /></p>
	 	</c:forEach>
	</c:if>


	<c:if test="${!empty __SESSION_ERR_MSG_LIST}" >
		<c:forEach items="${__SESSION_ERR_MSG_LIST}" var="errMsg">
	   		<p><c:out value="${errMsg}" /></p>
	  	</c:forEach>
	</c:if>
</div>
<%@ page language="java" import="javax.servlet.ServletContext" %>
<%@ page language="java" import="org.springframework.web.context.WebApplicationContext" %>
<%@ page language="java" import="org.springframework.web.context.support.WebApplicationContextUtils" %>
<%@ page language="java" import="org.freedom.view.system.domain.SystemDate" %>
<%@ page language="java" import="org.freedom.view.system.utils.JspApplicationContext" %>
<%
SystemDate systemDate = (SystemDate) JspApplicationContext.getApplicationBean(request, "SystemDate");
%>
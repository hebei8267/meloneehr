<%@ page language="java" import="javax.servlet.ServletContext" %>
<%@ page language="java" import="org.springframework.web.context.WebApplicationContext" %>
<%@ page language="java" import="org.springframework.web.context.support.WebApplicationContextUtils" %>
<%@ page language="java" import="org.freedom.view.domain.system.SystemDateViewObject" %>
<%@ page language="java" import="org.freedom.core.util.WebApplicationContextUtil" %>
<%
SystemDateViewObject systemDate = (SystemDateViewObject) WebApplicationContextUtil.getApplicationBean(request, "systemDateViewObject");
%>
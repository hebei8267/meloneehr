<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<div id="leftbar" class="span2">
	<ul class="nav nav-pills nav-stacked">
		<li id="index-tab">
			<a href="${ctx}/sc/index">首页</a>
		</li>
		<li id="common-tab">
			<a href="${ctx}/sc/common/user/">CRUD演示</a>
		</li>
		<li id="webservice-tab">
			<a href="${ctx}/sc/story/webservice/">WebService演示</a>
		</li>
		<li id="log-tab">
			<a href="${ctx}/sc/log/console">MBean演示</a>
		</li>
		<li id="jmx-tab">
			<a href="${ctx}/sc/story/jmx/">JMX演示</a>
		</li>
		<li id="cache-tab">
			<a href="${ctx}/sc/story/cache/">Cache演示</a>
		</li>
		<li id="schedule-tab">
			<a href="${ctx}/sc/story/schedule/">定时任务演示</a>
		</li>
		<li id="web-tab">
			<a href="${ctx}/sc/story/web/">Web演示</a>
		</li>
		<li id="utilizes-tab">
			<a href="${ctx}/sc/story/utilizes/">工具类演示</a>
		</li>
		<li id="jms-tab">
			<a href="${ctx}/sc/story/jms/">JMS演示</a>
		</li>
		<li id="security-tab">
			<a href="${ctx}/sc/story/security/">安全演示</a>
		</li>		
	</ul>
</div>
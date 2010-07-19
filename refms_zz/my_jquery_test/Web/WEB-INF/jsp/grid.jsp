<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ taglib prefix="jQuery" uri="http://www.myjQuery.com/tags/form"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title></title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/reset.css" />
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/base.css" />
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css" />
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/easyui/default/easyui.css" />
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/ui/cupertino/jquery.ui.all.css" />
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.4.2.min.js">
        </script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.easyui.min.js">
        </script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-ui-1.8.custom.min.js">
        </script>
        
        <script>
	        function back(){
	        	$("#testForm").attr("action","${pageContext.request.contextPath}/grid/back.faces");
	        	$("#testForm").submit();
	    	}
        	function aa(){
            	$("#testForm").attr("action","${pageContext.request.contextPath}/grid/show.faces");
            	$("#testForm").submit();
        	}
        </script>
    </head>
    <body>
    	<form:form id="testForm" method="post" modelAttribute="gridViewObject">
    		<jQuery:calendar path="calendar" changeMonth="true" changeYear="true"/>
    		<button type="button" class="btn_submit" id="loginBtn" onclick="aa()">
                          提  交                          
            </button>
    		<button type="button" class="btn_submit" id="loginBtn" onclick="back()">
                                                   BACK
            </button>
            
            <table id="grid1">
            </table>
            <input type="text" name="xxx1" value="<%= Math.random()%>" id="xxx1">
            <input type="text" name="xxx2" value="<%= Math.random()%>" id="xxx2">
            
            
            <br/><br/>
                                标签生产表格
            <jQuery:grid id="tagGrid" height="300" width="600" dataUrl="grid/getGridData.ajax" 
            title="11111" pagination="true" queryParams="{'xxx1':$('#xxx1').val(),'xxx2':$('#xxx2').val()}">
            	<jQuery:gridColumn field="itemid" title="编号" width="80"/>
            	<jQuery:gridColumn field="productid" title="名称" width="80"/>
            	<jQuery:gridColumn field="status" title="状态" width="80" align="right" visible="false"/>
            </jQuery:grid>
            <br/><br/>
            
		</form:form>
    </body>
</html>
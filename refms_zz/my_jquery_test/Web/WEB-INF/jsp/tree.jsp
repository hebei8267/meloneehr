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
        	function treeNodeClick(node){
            	alert(node.id)
        	}
        	function formSubmit(){
            	$("#testForm").attr("action","${pageContext.request.contextPath}/tree/show.faces");
            	$("#testForm").submit();
        	}
        	
            function tree2Reload(){
            	$('#tree2').tree("reload",{id:'123'});
            }
            function back(){
            	$("#testForm").attr("action","${pageContext.request.contextPath}/tree/back.faces");
            	$("#testForm").submit();
        	}
        </script>
    </head>
    <body>
    	<form:form id="testForm" method="post">
    		<div><br><br>
	    		利用标签从指定文件加载树的数据
	    		<jQuery:tree id="myTestTree" dataUrl="11.json"/>
    		</div>
    		
    		<div><div><br><br>
	    		格式化HTML使其成为树结构
	    		<ul class="easyui-tree">
				    <li>
				        <span>江汉区维修资金管理中心</span>
				        <ul>
				            <li>
				                <span>会计课</span>
				            </li>
				            <li>
				                <span>归集课</span>
				            </li>
				            <li>
				                <span>稽查课</span>
				            </li>
				        </ul>
				    </li>
				</ul>
			</div>
			
			<div><br><br>
	    		利用标签从AJAX加载树的数据
	    		<jQuery:tree id="tree1" dataUrl="tree/getTreeData.ajax" clickFn="treeNodeClick"/>
    		</div>
    		
    		
    		<div><br><br>
	    		利用标签从AJAX加载树的数据(CheckBox Tree)
	    		<jQuery:tree id="tree2" dataUrl="tree/getTreeData2.ajax"/>
    		</div>
    		
    		<button type="button" class="btn_submit" onclick="formSubmit()">
    			提  交
            </button>
            <button type="button" class="btn_submit" onclick="tree2Reload()">
            	Tree2Reload
            </button>
            <button type="button" class="btn_submit" id="loginBtn" onclick="back()">
                                                   BACK
            </button>
		</form:form>	
    </body>
</html>
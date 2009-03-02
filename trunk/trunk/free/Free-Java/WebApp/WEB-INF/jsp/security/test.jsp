<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ taglib prefix="extjs" uri="http://www.freedom.org/tags/form"%>

<html>
    <head>
        <%@ include file="/WEB-INF/jsp/base/pageHeader.jsp" %>
        <%@ include file="/WEB-INF/jsp/base/commonCssJs.jsp" %>
        <!-- ExtJS CheckBox Tree -->
        <script type="text/javascript" src="${pageContext.request.contextPath}/ext-lib/chkTree/TreeCheckNodeUI.js"></script>
        <script type="text/javascript">
        <!--
        function xx(){
            //alert($F('nodeType'))
            alert($F('calendar'))
            return "ssss";
        }
        
        function showNode(node, event){
        	alert(node.id)
        }
        
        function getGridSelectData(sm, row, rec){
        	alert("Select    "+rec.data.id)
        }
        function getGridDeselectData(sm, row, rec){
        	alert("Deselect    "+rec.data.id)
        }
        -->
        </script>
    </head>
    <body>
        <form:form id="testForm" method="post" modelAttribute="testViewObject">
        <table>
            <tr>
                <td>
                    <extjs:input path="input" minLength="8" maxLength="8" allowBlank="false" />
                </td>
            </tr>
            <tr>
                <td>
                    <extjs:password path="password" allowBlank="false" />
                </td>
            </tr>
            <tr>
                <td>
                    <extjs:comboBox path="nodeType" items="${testViewObject.nodeTypeList}" 
                    itemValue="value" itemLabel="label" editable="true"/>
                </td>
            </tr>
            <tr>
                <td>
                    <extjs:calendar path="calendar" format="Ymd" validator="xx"/>
                </td>
            </tr>
            <tr>
                <td>
                    <extjs:textArea path="textArea" minLength="8" maxLength="8" allowBlank="false"/>
                </td>
            </tr>
            <tr>
                <td>
                    <extjs:tree path="myTree" width="200" height="200" rootNodeId="ROOT" dataUrl="index1.ajax"
                    title="111" useArrows="false" allExpand="false" clickFn="showNode" baseParams="{aaa:'111-1'}"
                    isCheckTree ="true"/>
                </td>
                <td>
                    <extjs:tree path="myTree2" width="200" height="200" rootNodeId="ROOT" dataUrl="index1.ajax"
                    title="222" useArrows="true" allExpand="true" clickFn="showNode" baseParams="{aaa:'222-2'}"
          			/>
                </td>
            </tr>
            <tr>
                <td>
                    <extjs:grid path="myGrid" dataUrl="index2.ajax" width="200" height="200"
                    title="111">
                    	<extjs:gridColumn id="name" header="编号" align="right" sortable="true"/>
                    	<extjs:gridColumn id="id" header="名称" />
                    </extjs:grid>
                </td>
                <td>
                    <extjs:grid path="myGrid2" dataUrl="index2.ajax" width="200" height="200" 
                    title="222" hasRowNumberer="true" singleSelect="false" 
                    rowselectFn="getGridSelectData"
                    rowdeselectFn="getGridDeselectData"
                    checkboxSelection="true">
                    	
                    	<extjs:gridColumn id="id" header="编号" width="40"/>
                    	<extjs:gridColumn id="name" header="名称" align="right"/>
                    </extjs:grid>
                </td>
            </tr>
        </table>
            
            
            
        </form:form>
    </body>
</html>
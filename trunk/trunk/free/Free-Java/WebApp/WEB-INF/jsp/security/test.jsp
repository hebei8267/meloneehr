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
        -->
        </script>
    </head>
    <body>
        <form:form id="testForm" method="post" modelAttribute="testViewObject">
        <table>
            <tr>
                <td width="30" rowspan="6"></td>
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
                    <extjs:tree path="myTree" width="300" height="300" rootNodeId="ROOT" dataUrl="index1.ajax"
                    title="1234567" useArrows="false" allExpand="false" click="showNode" baseParams="{aaa:'123'}"
                    isCheckTree ="true"/>
                </td>
            </tr>
        </table>
            
            
            
        </form:form>
    </body>
</html>
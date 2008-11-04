<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%// 添加菜单树节点 %>
<html> 
    <head> 
        <%@ include file="/WEB-INF/jsp/base/PageHeader.jsp" %>
        <%@ include file="/WEB-INF/jsp/base/CommonCssJs.jsp" %>
        <%@ include file="/WEB-INF/jsp/base/ImportCommonPackage.jsp" %>
        <script type="text/javascript">
        <!--
        -->
        </script>
    </head> 
    <body>
        <div class="defaultPopWindowBody">
        <br>
            <table class="appTitleTable"> 
                <tr> 
                    <td class="appTitle">
                        添加菜单树节点信息
                    </td> 
                    <td class="appScreenID"> 
                        - FD000S006 -
                    </td> 
                </tr> 
                <tr> 
                    <td> 
                        <br> 
                    </td> 
                </tr> 
                <tr> 
                    <table class="spiltLine"> 
                        <tr> 
                            <td> 
                                <%// 分割线 %> 
                            </td> 
                        </tr> 
                    </table> 
                </tr> 
                <tr> 
                    <td colspan="2" class="opTip">
                        <table>
                            <tr>
                                <td>
                                    <img src="images/tip.png">
                                </td>
                                <td>
                                    <span class="need">注意:[<img src="images/need-input.gif">]为必填项</span>
                                </td>
                            </tr>
                        </table>
                    </td> 
                </tr> 
            </table>
            <table>
                <form:form id="menuTreeForm" method="post" modelAttribute="FD000S006ViewObject">
                <tr height="10">
                </tr>
                <tr> 
                    <td style="padding-left: 20px"> 
                        &nbsp;
                    </td>
                    <td>
                        <table>
                            <tr> 
                                <td colspan="2" class="itemTitle">
                                    节点详细信息
                                </td>
                            </tr> 
                            <tr>
                                <td class="inputItemName" height="30" width="100">
                                    父节点名称
                                </td> 
                                <td class="inputItemCell" height="30" width="200">
                                    <form:hidden path="parentNodeId"/>
                                    <form:input path="parentNodeText" size="20" maxlength="20" cssClass="readonly" readonly="true"/>
                                </td>
                            </tr>
                            <tr>
                                <td class="inputItemName" height="30" width="100">
                                    <img src="images/need-input.gif">名称
                                </td> 
                                <td class="inputItemCell" height="30" width="200">
                                    <form:input path="nodeText" size="20" maxlength="20"/>
                                </td>
                            </tr>
                            <tr>
                                <td class="inputItemName" height="30" width="100">
                                    <img src="images/need-input.gif">类型
                                </td> 
                                <td class="inputItemCell" height="30" width="200">
                                    <form:select path="nodeType" items="${FD000S006ViewObject.nodeTypeList}" itemValue="value" itemLabel="label"/>
                                </td>
                            </tr>
                            <tr>
                                <td class="inputItemName" height="30" width="100">
                                    <img src="images/need-input.gif">访问限制
                                </td> 
                                <td class="inputItemCell" height="30" width="200">
                                    <form:radiobutton path="defaultPermit" value="true"/>
                                    <label>
                                        无限制
                                    </label>
                                    <form:radiobutton path="defaultPermit" value="false" />
                                    <label>
                                        有限制
                                    </label>
                                </td>
                            </tr>
                            <tr>
                                 <td class="inputItemName" height="30" width="100">
                                    <img src="images/need-input.gif">Action
                                 </td>
                                 <td class="inputItemCell" height="30" width="200">
                                    <form:input path="actionContent" size="20" maxlength="70" />
                                 </td> 
                            </tr>
                        </table>
                    </td>
                </tr>
                </form:form>
            </table>
        </div>
    </body> 
</html>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" import="org.freedom.entity.ui.MenuNodeType" %>
<%// 添加菜单树节点 %>
<html> 
    <head> 
        <%@ include file="/WEB-INF/jsp/base/PageHeader.jsp" %>
        <%@ include file="/WEB-INF/jsp/base/CommonCssJs.jsp" %>
        <%@ include file="/WEB-INF/jsp/base/ImportCommonPackage.jsp" %>
        <script type="text/javascript">
        <!--
        //添加角色
        function addMenuNode(){
            var msg = "";
            if(isEmpty($F('nodeText'))){
                msg += getNeedInputMsg("名称");
            }
            if(Ext.getCmp('nodeTypeExtCombo').getValue()=='<%=MenuNodeType.NONE_NODE_TYPE%>'){
                msg += getNeedInputMsg("类型");
            }
            if(isEmpty($F('actionContent'))){
                msg += getNeedInputMsg("Action URL");
            }
            if(!isEmpty(msg)){
                showMessageBox(msg);
                return false;
            }

            if(!isNumber($F('nodeIndex'))){
                msg += getNotNumberMsg("显示位置");
            }
            if(!isEmpty(msg)){
                showMessageBox(msg);
                return false;
            }

            var addObj = new Object();
            addObj.nodeText = $F('nodeText');
            addObj.nodeType = Ext.getCmp('nodeTypeExtCombo').getValue();
            addObj.defaultPermit = getDefaultPermitValue();
            addObj.actionContent = $F('actionContent');
            addObj.nodeIndex = $F('nodeIndex');
            //调用父窗口的回调函数
            window.opener.addMenuNodeCall(addObj);
            closeWin();
        }
        
        function getDefaultPermitValue() {
            var defaultPermit = document.getElementsByName("defaultPermit");
            for (var i=0;i<defaultPermit.length;i++){
                if(defaultPermit[i].checked){ 
                    return defaultPermit[i].value;
                }
            }
        }
        //关闭当前窗口
        function closeWin(){
            parent.close();
        }
        -->
        </script>
    </head> 
    <body>
        <div class="defaultPopWindowBody">
        <br>
            <table class="appTitleTable"> 
                <tr> 
                    <td class="appTitle">
                        添加节点信息
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
                                    <img src="images/need-input.gif">Action URL
                                </td>
                                <td class="inputItemCell" height="30" width="200">
                                    <form:input path="actionContent" size="20" maxlength="100" />
                                </td> 
                            </tr>
                            <tr>
                                 <td class="inputItemName" height="30" width="100">
                                    显示位置
                                 </td> 
                                 <td class="inputItemCell" height="30" width="200"> 
                                    <form:input path="nodeIndex" size="20" maxlength="20"/> 
                                 </td>
                            </tr>
                            <tr height="10">
                            </tr> 
                            <tr> 
                                <td colspan="4" align="right"> 
                                <!-- 按钮 START --> 
                                    <table> 
                                        <tr> 
                                            <td align="right"> 
                                                <input value="添  加" class="buttonSubmitLong" type="button" onclick="addMenuNode();"> 
                                            </td> 
                                            <td width="20"> 
                                            </td> 
                                            <td align="right"> 
                                                <input value="关  闭" class="buttonResetLong" type="button" onclick="closeWin();"> 
                                            </td> 
                                        </tr> 
                                    </table>
                                <!-- 按钮 END --> 
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
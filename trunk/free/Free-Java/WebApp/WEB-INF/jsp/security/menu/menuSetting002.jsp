<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ taglib prefix="extjs" uri="http://www.freedom.org/tags/form"%>

<html>
    <head>
        <%@ include file="/WEB-INF/jsp/base/pageHeader.jsp" %>
        <%@ include file="/WEB-INF/jsp/base/commonCssJs.jsp" %>
        <%@ include file="/WEB-INF/jsp/base/importCommonPackage.jsp" %>
        <script type="text/javascript">
        <!--
            //关闭当前窗口
            function closeWin(){
                parent.close();
            }
            
            function checkShowIndex(){
                if(Ext.getCmp("showIndex").getValue() != ""){
                    if(!isDigits(Ext.getCmp("showIndex").getValue())){
                        return getNumText();
                    }
                }
                return true;
            }
            //添加菜单
            function addMenuNode(){
            	if(!formExtCmpValidate("addMenuForm")){
                    return;
                }
                //表单提交简化版本
                formAjaxSubmit("${pageContext.request.contextPath}/security/menu/menuSetting/002/addMenuNodeInfoAction.ajax", 
                               {
                               parentNodeID: Ext.getCmp("parentNodeID").getValue(),
                               nodeTxt: Ext.getCmp("nodeTxt").getValue(),
                               nodeType: Ext.getCmp("nodeType").getValue(),
                               actionContent: Ext.getCmp("actionContent").getValue(),
                               defaultPermit: getRadioValueByName("defaultPermit"),
                               inheritFlg: getRadioValueByName("inheritFlg"),
                               index: Ext.getCmp("showIndex").getValue()},
                               addMenuNodeSuccess);
            }
            function addMenuNodeSuccess(){
                parent.window.opener.menuTreeReload();
                closeWin();
            }
        -->
        </script>
    </head>
    <body>
        <div class="defaultPopWindowBody">
            <table class="appTitleTable"> 
                <tr> 
                    <td class="appTitle">
                        添加树节点信息
                    </td> 
                    <td class="appScreenID"> 
                        - MENU SETTINGS 002 -
                    </td> 
                </tr>
                <tr> 
                    <table class="spiltLine"> 
                        <tr> 
                            <td><!-- 分割线 --></td>
                        </tr> 
                    </table> 
                </tr> 
                <tr> 
                    <td colspan="2" class="operTip">
                        <table>
                            <tr>
                                <td>
                                    <img src="${pageContext.request.contextPath}/images/tip.png">
                                </td>
                                <td>
                                    <span class="need">注意:[<img src="${pageContext.request.contextPath}/images/need-input.gif">]为必填项</span>
                                </td>
                            </tr>
                            <tr>
                                <td></td>
                                <td>选中访问限制[限制]选项,访问该菜单节点时将检查访问权限</td>
                            </tr>
                            <tr>
                                <td></td>
                                <td>选中访问权限[继承]选项,将拥有父菜单节点的所有访问权限</td>
                            </tr>
                        </table>
                    </td> 
                </tr> 
            </table>
            <form:form id="addMenuForm" method="post" modelAttribute="MenuSetting002ViewObject">
                <table>
                    <tr height="10">
                    </tr>
                    <tr>
                        <td style="padding-left: 30px">
                            &nbsp;
                        </td>
                        <td>
                            <table>
                                <tr> 
                                    <td colspan="2" class="x-panel-header">
                                        菜单树节点详细信息
                                    </td>
                                </tr>
                                <tr>
                                    <td class="inputItemName" height="30" width="100">
                                        父角色编号
                                    </td> 
                                    <td class="inputItemCell" height="30" width="200">
                                        <extjs:input path="parentNodeID" disabled="true" />
                                        <form:hidden path="parentNodeTypeID"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="inputItemName" height="30" width="100">
                                        父角色名称
                                    </td> 
                                    <td class="inputItemCell" height="30" width="200">
                                        <extjs:input path="parentNodeTxt" disabled="true" />
                                    </td>
                                </tr>
                                <tr>
                                    <td class="inputItemName" height="30" width="100">
                                        <img src="${pageContext.request.contextPath}/images/need-input.gif">名称
                                    </td> 
                                    <td class="inputItemCell" height="30" width="200">
                                        <extjs:input path="nodeTxt" allowBlank="false" maxLength="255"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="inputItemName" height="30" width="100">
                                        <img src="${pageContext.request.contextPath}/images/need-input.gif">类型
                                    </td> 
                                    <td class="inputItemCell" height="30" width="200">
                                        <extjs:comboBox path="nodeType" items="${MenuSetting002ViewObject.nodeTypeList}" 
                                        itemValue="value" itemLabel="label" />
                                    </td>
                                </tr>
                                <tr>
                                    <td class="inputItemName" height="30" width="100">
                                        <img src="${pageContext.request.contextPath}/images/need-input.gif">Action URL
                                    </td>
                                    <td class="inputItemCell" height="30" width="200">
                                        <extjs:input path="actionContent" allowBlank="false" maxLength="255"/>
                                    </td> 
                                </tr>
                                <tr>
                                    <td class="inputItemName" height="30" width="100">
                                        访问限制
                                    </td> 
                                    <td class="inputItemCell" height="30" width="200">
                                        <form:radiobutton path="defaultPermit" value="true"/>
                                        <label>无限制</label>
                                        <form:radiobutton path="defaultPermit" value="false" />
                                        <label>限制</label>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="inputItemName" height="30" width="100">
                                        访问权限
                                    </td> 
                                    <td class="inputItemCell" height="30" width="200">
                                        <form:radiobutton path="inheritFlg" value="false"/>
                                        <label>不继承</label>
                                        <form:radiobutton path="inheritFlg" value="true" />
                                        <label>继承</label>
                                    </td>
                                </tr>
                                <tr>
                                     <td class="inputItemName" height="30" width="100">
                                        显示位置
                                     </td> 
                                     <td class="inputItemCell" height="30" width="200"> 
                                        <extjs:input path="showIndex" validator="checkShowIndex" maxLength="20"/> 
                                     </td>
                                </tr>
                                <tr height="10">
                                </tr> 
                                <tr> 
                                    <td colspan="2" align="right"> 
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
                </table>
            </form:form>
            <%@ include file="/WEB-INF/jsp/base/sysErrorFrom.jsp" %>
        </div>
    </body>
</html>

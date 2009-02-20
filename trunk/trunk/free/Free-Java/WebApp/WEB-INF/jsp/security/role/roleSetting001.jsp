<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ taglib prefix="extjs" uri="http://www.freedom.org/tags/form"%>
<%@ page language="java" import="org.freedom.entity.common.Role" %>

<html>
    <head>
        <%@ include file="/WEB-INF/jsp/base/pageHeader.jsp" %>
        <%@ include file="/WEB-INF/jsp/base/commonCssJs.jsp" %>
        <%@ include file="/WEB-INF/jsp/base/importCommonPackage.jsp" %>
        <script type="text/javascript">
        <!--
            var subWin = null;
            
            Ext.onReady(function(){
                var tree = new Ext.tree.TreePanel({
                    el: 'roleTreeDiv',
                    id: 'roleTree',
                    title: '角色树信息',
                    //useArrows: true,
                    animate: true,
                    enableDD: false,
                    containerScroll: true,
                    bodyBorder: false,
                    autoScroll: true,
                    rootVisible: true,
                    height: 300,
                    width: 300
                });
                
                var root = new Ext.tree.AsyncTreeNode({
                    draggable: false,
                    id: 'root',
                    text: '角色树根节点',
                    icon: '${pageContext.request.contextPath}/images/root.gif',
                    loader: new Ext.tree.TreeLoader({
                        dataUrl:'${pageContext.request.contextPath}/security/role/roleSetting/001/getAllRoleInfoTreeAction.ajax',
                        requestMethod : 'post',
                        listeners : {
                            loadexception : defaultAjaxRequestFailure
                        }
                    })
                });
                tree.setRootNode(root);
                
                tree.render();
                root.expand();
                
                tree.on("click", function(node, event) {
                    if(($F("selectedMenuNode")!= node.id)){//选择其它节点时
                        $("selectedMenuNode").value = node.id;
                            
                        //关闭子窗口
                        closeSubWin();
                    }
                    if (node.id == 'root') {//根节点不做处理,清除详细信息
                        cleanHiddenItem();
                    } else {
                        $("parentNodeID").value = node.attributes.parentNodeID;
                        Ext.getCmp("parentNodeTxt").setValue(node.attributes.parentNodeText);
                        Ext.getCmp("nodeID").setValue(node.id);
                        Ext.getCmp("nodeTxt").setValue(node.text);
                        Ext.getCmp("nodeDetail").setValue(node.attributes.detail);
                    }
                })
                tree.expandAll();
            });
            //清除隐藏信息
            function cleanHiddenItem(){
                $("selectedMenuNode").value = "root";
                
                $("parentNodeID").value = "";
                Ext.getCmp("parentNodeTxt").setValue("");
                Ext.getCmp("nodeID").setValue("");
                Ext.getCmp("nodeTxt").setValue("");
                Ext.getCmp("nodeDetail").setValue("");
            }
            //校验角色节点名称
            function checkNodeTxt(){
                if(Ext.getCmp("nodeID").getValue() == ""){//未选中角色节点
                    if(Ext.getCmp("nodeTxt").getValue() != ""){
                        return getErrorMsg_EM002("角色树节点");
                    }
                } else {
                    if(Ext.getCmp("nodeTxt").getValue() == ""){
                        return getErrorMsg_EM001();
                    }
                }
                return true;
            }
            //校验角色节点详细
            function checkNodeDetail(){
                if(Ext.getCmp("nodeID").getValue() == ""){//未选中角色节点
                    if(Ext.getCmp("nodeDetail").getValue() != ""){
                        return getErrorMsg_EM002("角色树节点");
                    }
                }
                return true;
            }
            //重置选择的角色信息
            function selectedNodeReset(){
                if(Ext.getCmp("nodeID").getValue() != ""){//选中角色节点
                    var roleTree = Ext.getCmp("roleTree");
                    var roleNode = roleTree.getNodeById(Ext.getCmp("nodeID").getValue());
                    
                    Ext.getCmp("nodeTxt").setValue(roleNode.text);
                    Ext.getCmp("nodeDetail").setValue(roleNode.attributes.detail);
                } else {
                    showMessageBox(getErrorMsg_AM006("角色树节点", "树根节点无法重置操作"));
                }
            }
            //更新选择的角色信息
            function updateSelectedNode(){
                if(Ext.getCmp("nodeID").getValue() != ""){//选中角色节点
                    updateSelectedNodeAction();
                } else {
                    showMessageBox(getErrorMsg_AM006("角色树节点", "树根节点无法更新操作"));
                }
            }
            //更新选择的角色信息
            function updateSelectedNodeAction(){
                if(!formExtCmpValidate("roleCfgForm")){
                    return;
                }
                var roleTree = Ext.getCmp("roleTree");
                var roleNode = roleTree.getNodeById(Ext.getCmp("nodeID").getValue());

                if(Ext.getCmp("nodeTxt").getValue() == roleNode.attributes.text 
                    && Ext.getCmp("nodeDetail").getValue() == roleNode.attributes.detail){//角色详细未修改，不做后台提交
                    showMessageBox(getErrorMsg_AM003());
                    return;
                }

                Ext.Ajax.request({
                    url : '${pageContext.request.contextPath}/security/role/roleSetting/001/updateNodeInfoAction.ajax',
                    method: 'post',
                    failure : defaultAjaxRequestFailure,
                    success : function(result, request) {
                        var oResult = eval("(" + result.responseText + ")");
                        
                        if(oResult.processResult) {// 成功
                            roleTreeReload();
                        } else {// 失败
                            // Ajax系统定式 start
                            if(!oResult.processResult && oResult.sessionTimeOut){
                                $("systemErrorForm").target = "_top";
                                $("systemErrorForm").submit();
                                return;
                            }
                            showMessageBox(oResult.resultMsg);
                            // Ajax系统定式 end
                            
                            roleTreeReload();
                        }
                    },
                    params : {
                        version : Ext.getCmp("roleTree").getNodeById(Ext.getCmp("nodeID").getValue()).attributes.version,
                        id : Ext.getCmp("nodeID").getValue(),
                        name : Ext.getCmp("nodeTxt").getValue(),
                        detail : Ext.getCmp("nodeDetail").getValue()
                    }
                });
            }
            //角色树重新加载
            function roleTreeReload(){
                Ext.getCmp("roleTree").root.reload();
                Ext.getCmp("roleTree").expandAll();
                cleanHiddenItem();
            }
            //删除角色
            function delRole(){
                if(subWin != null){
                    subWin.close();
                }
                
                if($F("parentNodeID") == ""){
                    showMessageBox(getErrorMsg_AM006("要删除的角色树节点", "树根节点不能删除"));
                    return;
                }
                if("<%=Role.ADMIN_ROLE_ID%>" == Ext.getCmp("nodeID").getValue()){
                	showMessageBox(getErrorMsg_AM006("要删除的角色树节点","系统管理员树节点不能删除"));
                	return;
                }
                
                showConfirm(getTipMsg_AM001(), delRoleAction);
            }
            //删除角色
            function delRoleAction(btn){
                if (btn != 'yes') {
                    return;
                }
                //表单提交简化版本
                formAjaxSubmit("${pageContext.request.contextPath}/security/role/roleSetting/001/delNodeInfoAction.ajax", 
                               {dataVersion: Ext.getCmp("roleTree").getNodeById(Ext.getCmp("nodeID").getValue()).attributes.version,
                               roleID: Ext.getCmp("nodeID").getValue()},
                               roleTreeReload ,
                               roleTreeReload);
            }
            //添加角色
            function addRole(){
                if(subWin != null){
                    subWin.close();
                }

                if("<%=Role.ADMIN_ROLE_ID%>" == Ext.getCmp("nodeID").getValue()){
                	showMessageBox(getErrorMsg_AM006("父角色树节点","系统管理员不能包含子角色树节点"));
                	return;
                }
                $("hnodeID").value = Ext.getCmp("nodeID").getValue();
                
                var windowOption = "width=390,height=385,left=300,top=100,status=no,resizable=no";
                var windowName = "ADD_ROLE";
                subWin =  window.open("", windowName, windowOption);
                $("roleCfgForm").target = windowName;
                $("roleCfgForm").action = "${pageContext.request.contextPath}/security/role/roleSetting/002/showPageAction.faces";
                $("roleCfgForm").submit();
                return;
            }
            function closeSubWin(){
                if(subWin != null){
                    subWin.close();
                }
            }
        -->
        </script>
    </head>
    <body onunload="closeSubWin();">
        <div class="defaultBody">
            <table class="appTitleTable"> 
                <tr> 
                    <td class="appTitle">
                        角色设定
                    </td> 
                    <td class="appScreenID"> 
                        -ROLE SETTING 001-
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
                                <td><span style="font-weight: bold;">删除[角色]时,其[子角色]也将一同删除</span></td>
                            </tr>
                        </table>
                    </td> 
                </tr> 
            </table>
            <div>
                <form:form id="roleCfgForm" method="post" modelAttribute="RoleSetting001ViewObject">
                    <%// 选中菜单节点 %>
                    <input type="hidden" id="selectedMenuNode" name="selectedMenuNode" value="root">
                    <table>
                        <tr height="10">
                        </tr>
                        <tr>
                            <td style="padding-left: 20px"> 
                                &nbsp;
                            </td>
                            <td>
                                <table>
                                    <tr>
                                        <td>
                                            <table>
                                                <tr>
                                                    <!-- 添加角色 -->
                                                    <td align="left">
                                                        <input value="添  加" class="buttonSubmitLong" type="button" onclick="addRole();">
                                                    </td>
                                                    <td width="20"> 
                                                    </td>
                                                    <!-- 删除角色 -->
                                                    <td align="left">
                                                        <input value="删  除" class="buttonDeleteLong" type="button" onclick="delRole();"> 
                                                    </td>
                                                </tr>
                                            </table>
                                        </td>
                                    </tr>
                                    <tr height="10">
                                    </tr>
                                    <tr>
                                        <td> 
                                            <div id="roleTreeDiv"> 
                                            </div> 
                                        </td> 
                                        <td width="30">
                                        </td> 
                                        <td valign="top">
                                            <table>
                                                <tr> 
                                                    <td colspan="2" class="x-panel-header">
                                                        角色详细信息
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td class="inputItemName" height="30" width="100">
                                                        父角色名称
                                                    </td> 
                                                    <td class="inputItemCell" height="30" width="200">
                                                        <extjs:input path="parentNodeTxt" disabled="true" />
                                                        <input type="hidden" id="parentNodeID" name="parentNodeID" value="${RoleSetting001ViewObject.parentNodeID}">
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td class="inputItemName" height="30" width="100">
                                                        编号
                                                    </td> 
                                                    <td class="inputItemCell" height="30" width="200">
                                                        <extjs:input path="nodeID" disabled="true" />
                                                        <input type="hidden" id="hnodeID" name="hnodeID">
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td class="inputItemName" height="30" width="100">
                                                        <img src="${pageContext.request.contextPath}/images/need-input.gif">名称
                                                    </td> 
                                                    <td class="inputItemCell" height="30" width="200">
                                                        <extjs:input path="nodeTxt" validator="checkNodeTxt" maxLength="20"/>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td class="inputItemName" height="30" width="100" valign="top" style="padding-top: 5px;">
                                                        详细描述
                                                    </td>
                                                    <td class="inputItemCell" height="115" width="200">
                                                        <extjs:textArea path="nodeDetail" validator="checkNodeDetail" maxLength="255"/>
                                                    </td>
                                                </tr>
                                                <tr height="10">
                                                </tr>
                                                <tr>
                                                    <!-- 按钮 -->
                                                    <td colspan="2" align="right">
                                                        <table> 
                                                            <tr> 
                                                                <td align="right"> 
                                                                    <input value="更  新" class="buttonSubmitLong" type="button" onclick="updateSelectedNode();"> 
                                                                </td> 
                                                                <td width="20"> 
                                                                </td> 
                                                                <td align="right"> 
                                                                    <input value="重  置" class="buttonResetLong" type="button" onclick="selectedNodeReset();">
                                                                </td> 
                                                            </tr> 
                                                        </table>
                                                    </td> 
                                                </tr>
                                            </table>
                                        </td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                    </table>
                </form:form>
            </div>
            <%@ include file="/WEB-INF/jsp/base/pageFooter.jsp" %>
            <%@ include file="/WEB-INF/jsp/base/sysErrorFrom.jsp" %>
        </div>
    </body>
</html>

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
        <!-- ExtJS CheckBox Tree -->
        <script type="text/javascript" src="${pageContext.request.contextPath}/ext-lib/chkTree/TreeCheckNodeUI.js"></script>
        <%@ include file="/WEB-INF/jsp/base/importCommonPackage.jsp" %>
        <script type="text/javascript">
        <!--
            Ext.onReady(function(){
                //************************************************
                //角色树
                //************************************************
                var roleTree = new Ext.tree.TreePanel({
                    el: 'roleTreeDiv',
                    id: 'roleTree',
                    title: '角色树信息',
                    //useArrows: true,
                    animate: true,
                    enableDD: false,
                    containerScroll: true,
                    bodyBorder: false,
                    autoScroll: true,
                    rootVisible: false,
                    height: 300,
                    width: 300
                });
                
                var roleTreeRoot = new Ext.tree.AsyncTreeNode({
                    draggable: false,
                    id: 'root',
                    text: '角色树根节点',
                    icon: '${pageContext.request.contextPath}/images/root.gif',
                    loader: new Ext.tree.TreeLoader({
                        dataUrl:'${pageContext.request.contextPath}/security/role/roleMenuSetting/001/getAllRoleInfoTreeAction.ajax',
                        requestMethod : 'post',
                        listeners : {
                            loadexception : defaultAjaxRequestFailure
                        }
                    })
                });
                roleTree.setRootNode(roleTreeRoot);
                
                roleTree.render();
                roleTreeRoot.expand();
                roleTree.expandAll();
                
                roleTree.on("click", function(node, event) {
                    if(($F("selectedRoleNode")!= node.id)){//选择其它节点时
                        $("selectedRoleNode").value = node.id;
                        
                        //加载菜单树
                        loadMenuTree();
                    }
                });
                
                
                //************************************************
                //菜单树
                //************************************************
                var menuTreeLoader = new Ext.tree.TreeLoader({
                                        dataUrl:'${pageContext.request.contextPath}/security/role/roleMenuSetting/001/getAllMenuInfoTreeAction.ajax',
                                        baseParams :{roleId: $F("selectedRoleNode")},
                                        requestMethod : 'post',
                                        baseAttrs: {
                                            uiProvider: Ext.ux.TreeCheckNodeUI
                                        },
                                        listeners : {
                                            loadexception : defaultAjaxRequestFailure
                                        }
                                    });
                menuTreeLoader.on('beforeload',function(treeLoader,node){
                           this.baseParams.roleId = $F("selectedRoleNode");
                        }, menuTreeLoader);
                var menuTree = new Ext.tree.TreePanel({
                    el: 'menuTreeDiv',
                    id: 'menuTree',
                    title: '菜单树信息',
                    <%// ******************************** %>
                    <%// checkTree独有属性 %>
                    checkModel: 'cascade', //对树的级联多选
                    onlyLeafCheckable: false,//对树所有结点都可选
                    <%// ******************************** %>
                    animate: true,
                    enableDD: false,
                    containerScroll: true,
                    bodyBorder: false,
                    autoScroll: true,
                    rootVisible: false,
                    height: 300,
                    width: 300
                });
                
                var menuTreeRoot = new Ext.tree.AsyncTreeNode({
                    draggable: false,
                    id: 'root',
                    text: '菜单树根节点',
                    loader: menuTreeLoader
                });
                
                menuTree.setRootNode(menuTreeRoot);
                
                menuTree.render();
                menuTree.expandAll();
            });

            //更新角色树结点的可访问菜单列表
            function updateSelectedNode(){
                if($F('selectedRoleNode') == ""){//未选中角色节点
                    showMessageBox(getErrorMsg_AM006("角色树节点","系统管理员默认拥有所有权限"));
                    return;
                }
                if($F('selectedRoleNode') == "<%=Role.ADMIN_ROLE_ID%>"){//选中角色节点为系统管理员
                    showMessageBox(getErrorMsg_AM006("角色树节点","系统管理员默认拥有所有权限"));
                    return;
                }
                var menuArray = Ext.getCmp('menuTree').getChecked();
                var menuIDList = new Array();
                
                if(menuArray != null){
                    for (var i = 0; i < menuArray.length; i++) {
                        menuIDList[i] = menuArray[i].id;
                    }
                }
                
                //表单提交简化版本
                formAjaxSubmit("${pageContext.request.contextPath}//security/role/roleMenuSetting/001/updateMenuNodePermitInfoAction.ajax", 
                               {dataVersion: Ext.getCmp("roleTree").getNodeById($F("selectedRoleNode")).attributes.version,
                               roleId: $F("selectedRoleNode"),
                               menuIDList: menuIDList},
                               roleTreeReload ,
                               roleTreeReload);
            }
            //角色树重新加载
            function roleTreeReload(){
                //清空选中角色节点
                $("selectedRoleNode").value = "";
                //加载角色树
                var _tmpRoleTree = Ext.getCmp("roleTree");
                _tmpRoleTree.root.reload();
                _tmpRoleTree.expandAll();
                //加载菜单树
                loadMenuTree();
            }
            //重置选择的角色信息
            function selectedNodeReset(){
                if($F('selectedRoleNode') != ""){//选中角色节点
                    //加载菜单树
                    loadMenuTree();
                } else {
                    showMessageBox(getErrorMsg_AM005("角色树节点"));
                }
            }
            //加载菜单树
            function loadMenuTree(){
                var _tmpMenuTree = Ext.getCmp("menuTree");
                _tmpMenuTree.root.reload();
                _tmpMenuTree.expandAll();
            }
        -->
        </script>
    </head>
    <body>
        <div class="defaultBody">
            <table class="appTitleTable"> 
                <tr> 
                    <td class="appTitle">
                        角色&菜单树关联设定
                    </td> 
                    <td class="appScreenID"> 
                        -ROLE Menu SETTING 001-
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
                                    <img src="${pageContext.request.contextPath}//images/tip.png">
                                </td>
                                <td>
                                    菜单树节点状态为<span class="need">勾选</span>时,则表示选中[角色]有访问该[菜单]节点的[权限]
                                </td>
                            </tr>
                            <tr>
                                <td></td>
                                <td>访问[权限]修改以后,左边的[导航]不会更新(可以刷新整个页面使其更新)</td>
                            </tr>
                        </table>
                    </td> 
                </tr> 
            </table>
            <div>
                <form name="roleMenuCfgForm" method="post" action="#">
                    <%// 选中菜单节点 %>
                    <input type="hidden" id="selectedRoleNode" name="selectedRoleNode" value="">
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
                                            <div id="roleTreeDiv"> 
                                            </div>
                                        </td>
                                        <td width="30">
                                        </td>
                                        <td>
                                            <div id="menuTreeDiv"> 
                                            </div>
                                        </td>
                                    </tr>
                                    <tr height="10">
                                    </tr>
                                    <tr>
                                        <!-- 按钮 -->
                                        <td colspan="3" align="right">
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
                </form>
            </div>
            <%@ include file="/WEB-INF/jsp/base/pageFooter.jsp" %>
            <%@ include file="/WEB-INF/jsp/base/sysErrorFrom.jsp" %>
        </div>
    </body>
</html>
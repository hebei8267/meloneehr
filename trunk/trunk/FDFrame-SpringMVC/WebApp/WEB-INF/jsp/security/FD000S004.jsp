<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" import="org.freedom.entity.ui.MenuNode" %>
<%@ page language="java" import="org.freedom.entity.ui.MenuNodeType" %>
<%// 菜单树管理 %>
<html> 
    <head> 
        <%@ include file="/WEB-INF/jsp/base/PageHeader.jsp" %>
        <%@ include file="/WEB-INF/jsp/base/CommonCssJs.jsp" %>
        <%@ include file="/WEB-INF/jsp/base/ImportCommonPackage.jsp" %>
        <script type="text/javascript">
        <!--
        var subWin = null;
        Ext.onReady(function() {
            //-----------------------------------------------------------------------------
            //菜单树
            //-----------------------------------------------------------------------------
            var tree = new Ext.tree.TreePanel({
                el : 'menuTreeDiv',
                id : 'menuTree',
                title : '菜单树信息',
                useArrows : true,
                animate : true,
                enableDD : false,
                containerScroll : true,
                bodyBorder : false,
                autoScroll : true,
                height : 250,
                width : 307,
                rootVisible : true
            });

            var root = new Ext.tree.AsyncTreeNode({
                text : '系统菜单树根节点', 
                draggable : false,
                id : '<%=MenuNode.ROOT_ID%>',
                icon : 'images/root.gif',
                loader: new Ext.tree.TreeLoader(
                    {dataUrl:'FD000S004AjaxViewAction_GetAllMenuTreeInfoAction.ajax',
                     baseParams :{id:'<%=MenuNode.ROOT_ID%>'}})
            });
            tree.setRootNode(root);

            tree.render();
            root.expand();

            tree.on("click", function(node, event) {
                //设置选中节点信息
                $("nodeId").value = node.id;
                $("nodeText").value = node.attributes.text;
                if(node.attributes.uiNodeTypeName == null || node.attributes.uiNodeTypeName == "undefined"){
                    $("nodeType").value = "";
                }else{
                    $("nodeType").value = node.attributes.uiNodeTypeName;
                }
                if(node.attributes.actionContent == null || node.attributes.actionContent == "undefined"){
                    $("actionContent").value = "";
                }else{
                    $("actionContent").value = node.attributes.actionContent;
                }
                setNodeDefaultPermit(node.attributes.defaultPermit);
                
                if(node.attributes.uiNodeIndex == null || node.attributes.uiNodeIndex == "undefined"){
                    $("nodeIndex").value = "";
                }else{
                    $("nodeIndex").value = node.attributes.uiNodeIndex;
                }

                if((node.id!='<%=MenuNode.ROOT_ID%>') && ($F("selectedMenuNode")!= node.id)){
                    $("selectedMenuNode").value = node.id;
                    //加载列表
                    store.load({params : {menuNodeID : $F("nodeId")}});
                }else if(node.id=='<%=MenuNode.ROOT_ID%>'){//根节点
                    //清空角色列表内容
                    Ext.getCmp('roleGrid').getStore().removeAll();
                }
            })


            //-----------------------------------------------------------------------------
            //角色列表
            //-----------------------------------------------------------------------------
            var role = Ext.data.Record.create([
                {name: 'id'},
                {name: 'name'}
            ]);
            // create the data store
            var store = new Ext.data.Store({
                id : 'roleStore',
                proxy : new Ext.data.HttpProxy({
                    url : 'FD000S004AjaxViewAction_GetAccessMenuNodePermitRoleInfoListAction.ajax',
                    method: 'POST'
                }),
                reader : new Ext.data.JsonReader({
                    totalProperty: "totalProperty",
                    root: "dataList",
                    successProperty :'sessionTimeOut'
                }, role),
                listeners : {
                    loadexception : function(){
                        showMessageBox(getSystemCommunicationMsg());
                    }
                }
            });

            var sm = new Ext.grid.CheckboxSelectionModel({
                header : '',
                listeners : {
                    rowselect : function(sm, row, rec) {
                        // setFromData(rec);
                    },
                    rowdeselect : function(sm, row, rec) {
                        // cleanFromData();
                    }
                }
            });

            // create the Grid
            var grid = new Ext.grid.GridPanel({
                store : store,
                id : 'roleGrid',
                el : 'roleGridDiv',
                columns : [
                    sm, 
                    new Ext.grid.RowNumberer({
                        header : '序号',// 自动行号
                        width : 35
                    }), {
                        id : 'id',
                        header : "编号",
                        width : 85,
                        sortable : true,
                        dataIndex : 'id'
                    }, {
                        id : 'name',
                        header : "名称",
                        width : 161,
                        sortable : true,
                        dataIndex : 'name'
                }],
                stripeRows : true,
                height : 250,
                width : 307,
                title : '适用角色信息'
            });

            grid.render();
        });
        
        //设置菜单节点的默认权限
        function setNodeDefaultPermit(defaultPermit){  
            var objs = document.getElementsByName("defaultPermit");

            for(var i = 0; i < objs.length; i++){
                var _v1 = String(objs[i].value);
                var _v2 = String(defaultPermit);

                if(_v1 == _v2){
                    objs[i].checked = true;
                    break;  
                }  
            }  
        }
        //删除选中角色
        function doDelSelectedRole(btn){
            if (btn != 'yes') {
                return;
            }
            var selectObjs = Ext.getCmp('roleGrid').getSelections();
            
            
            var roleList = new Array();
            for(var i=0;i<selectObjs.length;i++){
                roleList[i] = selectObjs[i].json;
            }
            
            Ext.Ajax.request({
                url : 'FD000S004AjaxViewAction_DelMenuNodePermitAction.ajax',
                method: 'post',
                success : function(result, request) {
                    var oResult = eval("(" + result.responseText + ")");
                    
                    if(oResult.processResult) {
                        //成功时的业务处理
                        //加载列表
                        Ext.getCmp('roleGrid').getStore().load({params : {menuNodeID : $F("nodeId")}});
                        
                    } else {//失败
                        //-------------------------------------------------
                        //Ajax系统定式      start
                        //-------------------------------------------------
                        if(!oResult.processResult && oResult.sessionTimeOut){
                            $("systemErrorForm").target = "_top";
                            $("systemErrorForm").submit();
                            return;
                        }
                        
                        showMessageBox(oResult.resultMsg);
                        //-------------------------------------------------
                        //Ajax系统定式      end
                        //-------------------------------------------------

                    
                        //错误发生时的业务处理 
                        //加载列表
                        Ext.getCmp('roleGrid').getStore().load({params : {menuNodeID : $F("nodeId")}});
                    }
                },
                failure : function(result, request) {
                    showMessageBox(getSystemCommunicationMsg());
                },
                params : {
                    menuNodeID : $F("nodeId"),
                    roleList : Ext.util.JSON.encode(roleList)
                }
            });
        }
        //删除选中角色
        function delSelectedRole(){
            if($("nodeId").value=='<%=MenuNode.ROOT_ID%>'){//根节点
                showMessageBox(getDisabledSelectedMsg('菜单树根节点'));
                return;
            }
            var selectObjs = Ext.getCmp('roleGrid').getSelections();
            if(selectObjs == null || selectObjs.length == 0){//未选择删除角色
                showMessageBox(getNeedMinSelectedMsg('角色'));
                return;
            }else{
                showConfirm("确认要删除所选记录?", doDelSelectedRole);
                return;
            }
        }
        //添加可访问选中菜单节点的角色信息
        function addMenuNodeAccessRole(){
            if(subWin != null){
                subWin.close();
            }

            if($("nodeId").value==''){//未选择菜单树节点
                showMessageBox(getNeedSelectedMsg('菜单树节点'));
                return;
            }
            if($("nodeId").value=='<%=MenuNode.ROOT_ID%>'){//根节点
                showMessageBox(getDisabledSelectedMsg('菜单树根节点'));
                return;
            }
            var windowOption = "width=355,height=420,left=300,top=150,status=no,resizable=no";
            var windowName = "ROLE_LIST";
            subWin =  window.open("", windowName, windowOption);
            $("menuTreeForm").target = windowName;
            $("menuTreeForm").action = "FD000S005JspViewAction_ShowPageAction.faces";
            $("menuTreeForm").submit();
            return;
        }
        //添加角色(回调函数)
        function addRoleListCall(roleList){
            Ext.Ajax.request({
                url : 'FD000S004AjaxViewAction_AddMenuNodePermitAction.ajax',
                method: 'post',
                success : function(result, request) {
                    var oResult = eval("(" + result.responseText + ")");
                    
                    if(oResult.processResult) {
                        //成功时的业务处理
                        //加载列表
                        Ext.getCmp('roleGrid').getStore().load({params : {menuNodeID : $F("nodeId")}});
                        
                    } else {//失败
                        //-------------------------------------------------
                        //Ajax系统定式      start
                        //-------------------------------------------------
                        if(!oResult.processResult && oResult.sessionTimeOut){
                            $("systemErrorForm").target = "_top";
                            $("systemErrorForm").submit();
                            return;
                        }
                        
                        showMessageBox(oResult.resultMsg);
                        //-------------------------------------------------
                        //Ajax系统定式      end
                        //-------------------------------------------------

                    
                        //错误发生时的业务处理 
                        //加载列表
                        Ext.getCmp('roleGrid').getStore().load({params : {menuNodeID : $F("nodeId")}});
                    }
                },
                failure : function(result, request) {
                    showMessageBox(getSystemCommunicationMsg());
                },
                params : {
                    menuNodeID : $F("nodeId"),
                    roleList : Ext.util.JSON.encode(roleList)
                }
            });
        }
        //清空菜单节点详细信息区域
        function resetInputMenuNodeArea(){
            $("nodeId").value = "";
            $("nodeText").value = "";
            $("nodeType").value = "";
            $("actionContent").value = "";
            setNodeDefaultPermit(true);
            $("nodeIndex").value = "";
        }
        //删除选中菜单节点
        function doDelSelectedMenuNode(btn){
            if (btn != 'yes') {
                return;
            }
            Ext.Ajax.request({
                url : 'FD000S004AjaxViewAction_DelSelectedMenuNodeAction.ajax',
                method: 'post',
                success : function(result, request) {
                    var oResult = eval("(" + result.responseText + ")");
                    
                    if(oResult.processResult) {
                        //成功时的业务处理
                        //清空菜单节点详细信息区域
                        resetInputMenuNodeArea();
                        //重新加载菜单节点树
                        Ext.getCmp("menuTree").getRootNode().reload();
                        //清空角色列表内容
                        Ext.getCmp('roleGrid').getStore().removeAll();
                    } else {//失败
                        //-------------------------------------------------
                        //Ajax系统定式      start
                        //-------------------------------------------------
                        if(!oResult.processResult && oResult.sessionTimeOut){
                            $("systemErrorForm").target = "_top";
                            $("systemErrorForm").submit();
                            return;
                        }
                        
                        showMessageBox(oResult.resultMsg);
                        //-------------------------------------------------
                        //Ajax系统定式      end
                        //-------------------------------------------------

                    
                        //错误发生时的业务处理 
                        //清空菜单节点详细信息区域
                        resetInputMenuNodeArea();
                        //重新加载菜单节点树
                        Ext.getCmp("menuTree").getRootNode().reload();
                        //清空角色列表内容
                        Ext.getCmp('roleGrid').getStore().removeAll();
                    }
                },
                failure : function(result, request) {
                    showMessageBox(getSystemCommunicationMsg());
                },
                params : {
                    menuNodeID : $F("nodeId")
                }
            });
        }
        //删除选中菜单节点
        function delSelectedMenuNode(){
            if($("nodeId").value==''){//未选择菜单树节点
                showMessageBox(getNeedSelectedMsg('菜单树节点'));
                return;
            }
            if($("nodeId").value=='<%=MenuNode.ROOT_ID%>'){//根节点
                showMessageBox(getDisabledSelectedMsg('菜单树根节点'));
                return;
            }
            showConfirm("确认要删除所选记录?", doDelSelectedMenuNode);
            return;
        }
        //添加菜单树节点
        function addMenuNode(){
            if(subWin != null){
                subWin.close();
            }
            
            var windowOption = "width=375,height=420,left=300,top=150,status=no,resizable=no";
            var windowName = "ADD_MENU_NODE";
            subWin =  window.open("", windowName, windowOption);
            $("menuTreeForm").target = windowName;
            $("menuTreeForm").action = "FD000S006JspViewAction_ShowPageAction.faces";
            $("menuTreeForm").submit();
            return;
        }
        //添加菜单树节点(回调函数)
        function addMenuNodeCall(addObj){
            alert(addObj.defaultPermit)
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
        <%@ include file="/WEB-INF/jsp/base/SysErrorFrom.jsp" %>
        <div class="defaultBody">
            <br>
            <table class="appTitleTable"> 
                <tr> 
                    <td class="appTitle">
                        菜单树管理
                    </td> 
                    <td class="appScreenID"> 
                        - FD000S004 -
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
                            <tr>
                                <td>
                                </td>
                                <td>
                                    删除菜单树节点的[角色]&nbsp;(可多选&nbsp;--&nbsp;<span style="font-weight: bold;">按住Ctrl多选,Shift连续选择</span>)
                                </td>
                            </tr>
                        </table>
                    </td> 
                </tr> 
            </table>
            <form:form id="menuTreeForm" method="post" modelAttribute="FD000S004ViewObject">
            <%// 选中菜单节点 %>
            <input type="hidden" id="selectedMenuNode" name="selectedMenuNode" value="">
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
                                            <td align="left"><%// 添加菜单树节点 %>
                                                <input value="添  加" class="buttonSubmitLong" type="button" onclick="addMenuNode();"> 
                                            </td> 
                                            <td width="20"> 
                                            </td> 
                                            <td align="left"><%// 删除菜单树节点 %>
                                                <input value="删  除" class="buttonDeleteLong" type="button" onclick="delSelectedMenuNode();"> 
                                            </td>
                                        </tr>
                                    </table>
                                </td>
                                <td width="30"></td>
                                <td>
                                    <table>
                                        <tr>
                                            <td align="left"><%// 添加角色 %>
                                                <input value="添  加" class="buttonSubmitLong" type="button" onclick="addMenuNodeAccessRole();"> 
                                            </td> 
                                            <td width="20"> 
                                            </td> 
                                            <td align="left"><%// 删除角色 %> 
                                                <input value="删  除" class="buttonDeleteLong" type="button" onclick="delSelectedRole();"> 
                                            </td>
                                        </tr>
                                    </table>
                                </td>
                            </tr>
                            <tr height="10">
                            </tr>
                            <tr>
                                <td> 
                                    <div id="menuTreeDiv"> 
                                    </div> 
                                </td> 
                                <td width="30">
                                </td> 
                                <td>
                                    <div id="roleGridDiv"></div>
                                </td>
                            </tr>
                            <tr height="10">
                            </tr>
                            <tr>
                                <td valign="top" colspan="3"> 
                                    <table> 
                                        <tr> 
                                            <td colspan="4" class="itemTitle">
                                                节点详细信息
                                            </td> 
                                        </tr> 
                                        <tr> 
                                            <td class="inputItemName" height="30" width="100">
                                                编号
                                            </td> 
                                            <td class="inputItemCell" height="30" width="200">
                                                <form:input path="nodeId" size="20" maxlength="20" cssClass="readonly" readonly="true"/>
                                            </td>
                                            <td class="inputItemName" height="30" width="100"> 
                                                类型
                                            </td> 
                                            <td class="inputItemCell" height="30" width="200">
                                                <form:input path="nodeType" size="20" maxlength="20" cssClass="readonly" readonly="true"/>
                                            </td>
                                        </tr> 
                                        <tr>
                                            <td class="inputItemName" height="30" width="100"> 
                                                <img src="images/need-input.gif">名称
                                            </td> 
                                            <td class="inputItemCell" height="30" width="200"> 
                                                <form:input path="nodeText" size="20" maxlength="20"/>
                                            </td>
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
                                            <td class="inputItemCell" height="30" width="200" colspan="3"> 
                                                <form:input path="actionContent" size="20" maxlength="70" cssStyle="width: 469px;"/>
                                            </td> 
                                        </tr>
                                        <tr> 
                                            <td class="inputItemName" height="30" width="100">
                                                角色适用范围
                                            </td>
                                            <td class="inputItemCell" height="30" width="200"> 
                                                <form:radiobutton path="selfFlag" value="true"/>
                                                <label>
                                                    仅该节点
                                                </label>
                                                <form:radiobutton path="selfFlag" value="false" />
                                                <label>
                                                    包含所有子节点
                                                </label>
                                            </td>
                                            <td class="inputItemName" height="30" width="100">
                                                显示位置
                                            </td> 
                                            <td class="inputItemCell" height="30" width="200"> 
                                                <form:input path="nodeIndex" size="20" maxlength="100"/> 
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
                                                            <input value="更  新" class="buttonSubmitLong" type="button"> 
                                                        </td> 
                                                        <td width="20"> 
                                                        </td> 
                                                        <td align="right"> 
                                                            <input value="重  置" class="buttonResetLong" type="button"> 
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
                    </td>
                </tr>
            </table>
            </form:form>
            <%@ include file="/WEB-INF/jsp/base/PageFooter.jsp" %>
        </div> 
    </body> 
</html> 
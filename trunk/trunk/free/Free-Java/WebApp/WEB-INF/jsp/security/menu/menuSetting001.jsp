<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ taglib prefix="extjs" uri="http://www.freedom.org/tags/form"%>

<%@ page language="java" import="org.freedom.entity.ui.MenuNode" %>

<html>
    <head>
        <%@ include file="/WEB-INF/jsp/base/pageHeader.jsp" %>
        <%@ include file="/WEB-INF/jsp/base/commonCssJs.jsp" %>
        <%@ include file="/WEB-INF/jsp/base/importCommonPackage.jsp" %>
        <script type="text/javascript">
        <!--
        	var subWin = null;
        	
            Ext.onReady(function(){

			    var role = Ext.data.Record.create([
	                {name: 'id'},
	                {name: 'name'}
	            ]);
	            
	            // create the data store
	            var store = new Ext.data.Store({
	                id : 'roleStore',
	                proxy : new Ext.data.HttpProxy({
	                    url : '${pageContext.request.contextPath}/security/menu/menuSetting/001/getAccessMenuNodePermitRoleInfoListAction.ajax',
	                    method: 'POST'
	                }),
	                reader : new Ext.data.JsonReader({
	                    totalProperty: "totalProperty",
	                    root: "dataList",
	                    successProperty :'sessionTimeOut'
	                }, role),
	                listeners : {
	                    loadexception : function(){
	                        showMessageBox(getCommunicationErrorMsg());
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
			    
			    //************************************************
			    //************************************************
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
	                id : '<%=MenuNode.MENU_NODE_TREE_ROOT_ID%>',
	                icon : '${pageContext.request.contextPath}/images/root.gif',
	                loader: new Ext.tree.TreeLoader({dataUrl:'${pageContext.request.contextPath}/security/menu/menuSetting/001/getAllMenuInfoTreeAction.ajax'})
	            });
	            tree.setRootNode(root);
	
	            tree.render();
	            root.expand();
	            tree.on("click", function(node, event) {
	            	if (node.id == '<%=MenuNode.MENU_NODE_TREE_ROOT_ID%>') {//根节点不做处理,清除详细信息
	            		cleanHiddenItem();
	            		//清空角色列表内容
	            		cleanRoleInfoList();
	            	} else {
	            		//选中菜单节点
	            		menuNodeSelected(node);
	            		
	            		if(($F("selectedMenuNode")!= node.id)){//选择其它节点时
	            			$("selectedMenuNode").value = node.id;
	            			
	            			//加载角色列表
	            			roleListInfonLoad(node);
	            			//关闭子窗口
	            			closeSubWin();
	            		}
	            	}
	            });
	            tree.expandAll();
			});
			//清空角色列表内容
			function cleanRoleInfoList(){
				Ext.getCmp('roleGrid').getStore().removeAll();
			}
			//加载角色列表
			function roleListInfonLoad(node){
                //加载列表
            	Ext.getCmp('roleGrid').getStore().load({params : {selectedMenuNodeID : Ext.getCmp("nodeID").getValue()}});
			}
			//清除隐藏信息
            function cleanHiddenItem(){
            	Ext.getCmp("nodeID").setValue("");
				$("nodeTypeID").value = "";
				Ext.getCmp("nodeType").setValue("");
				Ext.getCmp("nodeTxt").setValue("");
				setRadioValueByName("defaultPermit", false);
				Ext.getCmp("actionContent").setValue("");
				setRadioValueByName("applyArea", true);
				Ext.getCmp("showIndex").setValue("");
            }
			//选中菜单节点
			function menuNodeSelected(node){
				Ext.getCmp("nodeID").setValue(node.id);
				$("nodeTypeID").value = node.attributes.uiNodeType;
				Ext.getCmp("nodeType").setValue(node.attributes.uiNodeTypeName);
				Ext.getCmp("nodeTxt").setValue(node.text);
				setRadioValueByName("defaultPermit", node.attributes.defaultPermit);
				Ext.getCmp("actionContent").setValue(node.attributes.actionContent);
				setRadioValueByName("applyArea", true);
				Ext.getCmp("showIndex").setValue(node.attributes.uiNodeIndex);
			}
			//校验菜单节点名称
            function checkNodeTxt(){
            	if(Ext.getCmp("nodeID").getValue() == ""){//未选中菜单节点
                    if(Ext.getCmp("nodeTxt").getValue() != ""){
                        return getNeedSelectedItem("菜单树节点");
                    }
                } else {
                    if(Ext.getCmp("nodeTxt").getValue() == ""){
                        return getBlankText();
                    }
                }
            	return true;
            }
            //校验Action URL
            function checkActionContent(){
            	if(Ext.getCmp("nodeID").getValue() == ""){//未选中菜单节点
                    if(Ext.getCmp("actionContent").getValue() != ""){
                        return getNeedSelectedItem("菜单树节点");
                    }
                } else {
                    if(Ext.getCmp("actionContent").getValue() == ""){
                        return getBlankText();
                    }
                }
            	return true;
            }
            //校验显示位置
            function checkShowIndex(){
            	if(Ext.getCmp("nodeID").getValue() == ""){//未选中菜单节点
                    if(Ext.getCmp("showIndex").getValue() != ""){
                        return getNeedSelectedItem("菜单树节点");
                    }
                } else {
                    if(Ext.getCmp("showIndex").getValue() != ""){
                    	if(!isDigits(Ext.getCmp("showIndex").getValue())){
                    		return getNumText();
                    	}
                    }
                }
            	return true;
            }
            function addMenuNode(){
                openSubWindow('addMenuNode.html',390,355);
            }
            //添加角色
            function addMenuNodeRole(){
            	if(subWin != null){
                    subWin.close();
                }
                
                if(Ext.getCmp("nodeID").getValue() == ""){//未选中菜单节点
               		showMessageBox(getNeedSelectedItemErrorMsg("要添加适用角色的菜单树节点", "树根节点不能添加适用角色"));
               		return;
                }
                
                var windowOption = "width=390,height=395,left=300,top=100,status=no,resizable=no";
                var windowName = "ADD_MENU_NODE_ROLE";
                subWin =  window.open("", windowName, windowOption);
                $("menuCfgForm").target = windowName;
                $("menuCfgForm").action = "${pageContext.request.contextPath}/security/menu/menuSetting/003/showPageAction.faces";
                $("menuCfgForm").submit();
                return;
            }
            //添加角色(回调函数)
        	function addRoleListCall(newAddRoleList){
        		var gridStore = Ext.getCmp('roleGrid').getStore(); 		
        		
        		var oldRoleList = new Array();
	            for(var i1=0;i1<gridStore.getCount();i1++){
	                oldRoleList[i1] = gridStore.getAt(i1);
	            }
        		
       			for(var i2=0; i2<newAddRoleList.length; i2++){
       				var _newAddData = newAddRoleList[i2];
       				
       				var addFlg = false;
       				for(var i3=0; i3<oldRoleList.length; i3++){
       					var _oldRoleData = oldRoleList[i3];
       					
        				if(_newAddData.data.id == _oldRoleData.data.id){
        					break;
        				} else {
        					if(!(i3+1<oldRoleList.length)){//最后一个对象也不相同时
        						addFlg = true;
        					}
        				}
       				}
       				if(oldRoleList.length == 0){//原对象列表中一个对象都没有
       					addFlg = true;
       				}
       				
       				if(addFlg){
       					gridStore.insert(0,_newAddData);
       				}
       			}
       			
       			Ext.getCmp('roleGrid').getView().refresh();
       			Ext.getCmp('roleGrid').getStore().sort("id", "ASC");
        	}
        	//删除角色
        	function delMenuNodeRole(){
        		if(subWin != null){
                    subWin.close();
                }
                
                var selectObjs = Ext.getCmp('roleGrid').getSelections();
	            if(selectObjs == null || selectObjs.length == 0){//未选择添加角色
	                showMessageBox(getNeedOneSelectedErrorMsg('角色'));
	                return;
	            }else{
	                showConfirm(getDelConfirmTipMsg(), delMenuNodeRoleAction);
	                return;
	            }
        	}
        	//删除角色
        	function delMenuNodeRoleAction(btn){
        		if (btn != 'yes') {
	                return;
	            }
	            
	            var selectObjs = Ext.getCmp('roleGrid').getSelections();
	            var gridStore = Ext.getCmp('roleGrid').getStore()

	            for(var i=0;i<selectObjs.length;i++){
	                gridStore.remove(selectObjs[i])
	            }
	            
	            Ext.getCmp('roleGrid').getView().refresh();
       			gridStore.sort("id", "ASC");
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
                        菜单树设定
                    </td> 
                    <td class="appScreenID"> 
                        - MENU SETTINGS 001 -
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
            <div>
            	<form:form id="menuCfgForm" method="post" modelAttribute="MenuSetting001ViewObject">
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
                                                    <!-- 添加菜单树节点 -->
                                                    <td align="left">
                                                        <input value="添  加" class="buttonSubmitLong" type="button" onclick="addMenuNode();">
                                                    </td>
                                                    <td width="20"> 
                                                    </td>
                                                    <!-- 删除菜单树节点 -->
                                                    <td align="left">
                                                        <input value="删  除" class="buttonDeleteLong" type="button" onclick="delSelectedMenuNode();"> 
                                                    </td>
                                                </tr>
                                            </table>
                                        </td>
                                        <td width="30"></td>
                                        <td>
                                            <table>
                                                <tr>
                                                    <!-- 添加角色 -->
                                                    <td align="left">
                                                        <input value="添  加" class="buttonSubmitLong" type="button" onclick="addMenuNodeRole();"> 
                                                    </td> 
                                                    <td width="20"> 
                                                    </td>
                                                    <!-- 删除角色 -->
                                                    <td align="left">
                                                        <input value="删  除" class="buttonDeleteLong" type="button" onclick="delMenuNodeRole();"> 
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
                                    <tr height="20">
                                    </tr>
                                    <tr>
                                        <td valign="top" colspan="3">
                                            <table>
                                                <tr> 
                                                    <td colspan="4" class="x-panel-header">
                                                        菜单树节点详细信息
                                                    </td> 
                                                </tr>
                                                <tr> 
                                                    <td class="inputItemName" height="30" width="100">
                                                        编号
                                                    </td> 
                                                    <td class="inputItemCell" height="30" width="200">
                                                    	<extjs:input path="nodeID" disabled="true"/>
                                                    </td>
                                                    <td class="inputItemName" height="30" width="100"> 
                                                        类型
                                                    </td> 
                                                    <td class="inputItemCell" height="30" width="200">
                                                        <extjs:input path="nodeType" disabled="true"/>
                                                        <form:hidden path="nodeTypeID"/>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td class="inputItemName" height="30" width="100"> 
                                                        <img src="${pageContext.request.contextPath}/images/need-input.gif">名称
                                                    </td> 
                                                    <td class="inputItemCell" height="30" width="200"> 
                                                        <extjs:input path="nodeTxt" validator="checkNodeTxt" maxLength="255"/>
                                                    </td>
                                                    <td class="inputItemName" height="30" width="100"> 
                                                        <img src="${pageContext.request.contextPath}/images/need-input.gif">访问限制
                                                    </td> 
                                                    <td class="inputItemCell" height="30" width="200">
                                                        <form:radiobutton path="defaultPermit" value="false"/>
			                                            <label>无限制</label>
			                                            <form:radiobutton path="defaultPermit" value="true" />
			                                            <label>有限制</label>
                                                    </td>
                                                </tr>
                                                <tr> 
                                                    <td class="inputItemName" height="30" width="100"> 
                                                        <img src="${pageContext.request.contextPath}/images/need-input.gif">Action URL
                                                    </td> 
                                                    <td class="inputItemCell" height="30" width="200" colspan="3"> 
                                                        <extjs:input path="actionContent" width="477" validator="checkActionContent" maxLength="255"/>
                                                    </td>
                                                </tr>
                                                <tr> 
                                                    <td class="inputItemName" height="30" width="100">
                                                        角色适用范围
                                                    </td>
                                                    <td class="inputItemCell" height="30" width="200">
                                                        <form:radiobutton path="applyArea" value="true"/>
			                                            <label>仅该节点</label>
			                                            <form:radiobutton path="applyArea" value="false" />
			                                            <label>包含所有子节点</label>
                                                    </td>
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
            </div>
            <%@ include file="/WEB-INF/jsp/base/pageFooter.jsp" %>
            <%@ include file="/WEB-INF/jsp/base/sysErrorFrom.jsp" %>
        </div>
    </body>
</html>

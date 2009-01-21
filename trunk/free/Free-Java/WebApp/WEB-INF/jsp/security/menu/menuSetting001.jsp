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
            Ext.onReady(function(){

			    var myData = [['00000001', '系统管理员'], ['00000002', '一般用户'], ['00000003', '行政人员']];
			    
			    // create the data store
			    var store = new Ext.data.SimpleStore({
			        fields: [{
			            name: 'roleID'
			        }, {
			            name: 'roleName'
			        }]
			    });
			    store.loadData(myData);
			    
			    var sm = new Ext.grid.CheckboxSelectionModel({
			        header: '',
			        singleSelect: true,
			        listeners: {
			            rowselect: function(sm, row, rec){
			                // setFromData(rec);
			            },
			            rowdeselect: function(sm, row, rec){
			                // cleanFromData();
			            }
			        }
			    });
			    
			    // create the Grid
			    var grid = new Ext.grid.GridPanel({
			        store: store,
			        id: 'roleGrid',
			        el: 'roleGridDiv',
			        columns: [sm, new Ext.grid.RowNumberer({
			            header: '序号',// 自动行号
			            width: 35
			        }), {
			            id: 'roleID',
			            header: "编号",
			            width: 80,
			            sortable: true,
			            dataIndex: 'roleID'
			        }, {
			            id: 'roleName',
			            header: "名称",
			            width: 150,
			            sortable: true,
			            dataIndex: 'roleName'
			        }],
			        stripeRows: true,
			        height: 250,
			        width: 307,
			        title: '适用角色信息'
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
	            	} else {
	            		menuNodeSelected(node);
	            	}
	            });
	            tree.expandAll();
			});
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
            function checkNodeTxt(){
            }
            function checkActionContent(){
            }
            function checkShowIndex(){
            }
            function addMenuNode(){
                openSubWindow('addMenuNode.html',390,355);
            }
            function addMenuNodeRole(){
                openSubWindow('addMenuNodeRole.html',390,395);
            }
        -->
        </script>
    </head>
    <body>
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

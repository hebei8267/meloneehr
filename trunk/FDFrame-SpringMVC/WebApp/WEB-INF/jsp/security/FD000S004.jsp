<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" import="org.freedom.entity.ui.MenuNode" %>
<%// 菜单树管理 %>
<html> 
    <head> 
        <%@ include file="/WEB-INF/jsp/base/PageHeader.jsp" %>
        <%@ include file="/WEB-INF/jsp/base/CommonCssJs.jsp" %>
        <%@ include file="/WEB-INF/jsp/base/ImportCommonPackage.jsp" %>
        <script type="text/javascript">
        <!--
        Ext.onReady(function() {
			//-----------------------------------------------------------------------------
			//菜单树
			//-----------------------------------------------------------------------------
        	var tree = new Ext.tree.TreePanel({
        		el : 'menuTreeDiv',
        		title : '菜单树信息',
        		useArrows : true,
				animate : true,
				enableDD : false,
				containerScroll : true,
				bodyBorder : false,
				autoScroll : true,
				height : 250,
        		width : 307,
				rootVisible : false
        	});

        	var root = new Ext.tree.AsyncTreeNode({
                text : '系统菜单树根节点', 
                draggable : false,
                id : '<%=MenuNode.ROOT_ID%>',
                loader: new Ext.tree.TreeLoader(
					{dataUrl:'FD000S004AjaxViewAction_GetAllTreeNodeInfoAction.ajax',
					 baseParams :{id:'<%=MenuNode.ROOT_ID%>'}})
            });
			tree.setRootNode(root);
		
			tree.render();
			root.expand();

			tree.on("click", function(node, event) {
				//设置选中节点信息
				$("id").value = node.id;
	            $("text").value = node.attributes.text;
	            Ext.getCmp('uiNodeTypeExtCombo').setValue(node.attributes.uiNodeType);
	            $("actionContent").value = node.attributes.actionContent;
	            setNodeType(node.attributes.defaultPermit);
	            $("uiNodeIndex").value = node.attributes.uiNodeIndex;
			})


			//-----------------------------------------------------------------------------
			//角色列表
			//-----------------------------------------------------------------------------
			var myData = [['CN', '中国', '中华人民共和国'], ['JP', '日本', '日本'],
			  			['US', '美国', '美利坚合众国']];

			// create the data store
			var store = new Ext.data.SimpleStore({
				fields : [{
			  		name : 'countryID'
			  	}, {
			  		name : 'countryName'
			 	}, {
			  		name : 'description'
			  	}]
			});
			store.loadData(myData);

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
			  			id : 'countryID',
			  			header : "编号",
			  			width : 80,
			  			sortable : true,
			  			dataIndex : 'countryID'
			  		}, {
			  			id : 'countryName',
			  			header : "名称",
			  			width : 150,
			  			sortable : true,
			  			dataIndex : 'countryName'
			  	}],
			  	stripeRows : true,
			  	height : 250,
			  	width : 307,
			  	title : '国家信息'
			});

			grid.render();
        });
		//设置菜单节点的默认权限
        function setNodeType(defaultPermit){  
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
        -->
        </script>
    </head> 
    <body> 
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
                    <td colspan="2" class="tip">
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
			                    	删除菜单节点的[适用角色]&nbsp;(可多选&nbsp;--&nbsp;<span style="font-weight: bold;">按住Ctrl多选,Shift连续选择</span>)
			                    </td>
			                </tr>
			            </table>
                    </td> 
                </tr> 
            </table>
            <form:form id="menuTreeForm" method="post" modelAttribute="FD000S004ViewObject">
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
	                    					<td align="left">
						                        <input value="添  加" class="buttonSubmitLong" type="button" onclick=""> 
						                    </td> 
						                    <td width="20"> 
						                    </td> 
						                    <td align="left"> 
						                        <input value="删  除" class="buttonDeleteLong" type="button"> 
						                    </td>
                    					</tr>
                    				</table>
                    			</td>
                    			<td width="30"></td>
                    			<td>
                    				<table>
                    					<tr>
                    						<td align="left">
						                        <input value="添  加" class="buttonSubmitLong" type="button" onclick=""> 
						                    </td> 
						                    <td width="20"> 
						                    </td> 
						                    <td align="left"> 
						                        <input value="删  除" class="buttonDeleteLong" type="button"> 
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
			                                <td class="inputItemCell" height="30" width="200" colspan="3">
			                                	<form:input path="id" size="20" maxlength="20" cssClass="readonly" readonly="true"/>
			                                </td> 
			                            </tr> 
			                            <tr> 
			                                <td class="inputItemName" height="30" width="100"> 
			                                    <img src="images/need-input.gif">名称
			                                </td> 
			                                <td class="inputItemCell" height="30" width="200"> 
			                                    <form:input path="text" size="20" maxlength="20"/>
			                                </td>
			                                <td class="inputItemName" height="30" width="100"> 
			                                    <img src="images/need-input.gif">类型
			                                </td> 
			                                <td class="inputItemCell" height="30" width="200">
										        <form:select path="uiNodeType" items="${FD000S004ViewObject.nodeTypeList}" itemValue="value" itemLabel="label"/>
			                                </td> 
			                            </tr>
			                            <tr> 
			                                <td class="inputItemName" height="30" width="100"> 
			                                    <img src="images/need-input.gif">Action
			                                </td> 
			                                <td class="inputItemCell" height="30" width="200" colspan="3"> 
			                                    <form:input path="actionContent" size="20" maxlength="70" cssStyle="width: 469px;"/>
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
			                                <td class="inputItemName" height="30" width="100">
			                                	显示位置
			                                </td> 
			                                <td class="inputItemCell" height="30" width="200"> 
			                                    <form:input path="uiNodeIndex" size="20" maxlength="20"/> 
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
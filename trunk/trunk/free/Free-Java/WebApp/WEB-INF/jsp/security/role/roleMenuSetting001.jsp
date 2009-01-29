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
			Ext.onReady(function(){
			    //************************************************
			    //角色树
			    //************************************************
			    var roleTree = new Ext.tree.TreePanel({
			        el: 'roleTreeDiv',
			        title: '角色树信息',
			        useArrows: true,
			        animate: true,
			        enableDD: false,
			        containerScroll: true,
			        bodyBorder: false,
			        autoScroll: true,
			        rootVisible: false,
			        height: 300,
			        width: 300,
			        loader: new Ext.tree.TreeLoader()
			    });
			    
			    var roleTreeRoot = new Ext.tree.AsyncTreeNode({
			        draggable: false,
			        id: 'root',
			        text: '角色树根节点',
			        icon: '${pageContext.request.contextPath}/images/root.gif',
			        children: [{
			            leaf: true,
			            text: '系统管理员',
			            icon: '${pageContext.request.contextPath}/images/role.gif',
			        }, {
			            leaf: false,
			            icon: '${pageContext.request.contextPath}/images/role.gif',
			            text: '一般用户',
			            children: [{
			                leaf: true,
			                text: '行政人员',
			                icon: '${pageContext.request.contextPath}/images/role.gif'
			            }]
			        }, {
			            leaf: true,
			            icon: '${pageContext.request.contextPath}/images/role.gif',
			            text: '临时用户'
			        }]
			    });
			    roleTree.setRootNode(roleTreeRoot);
			    
			    roleTree.render();
			    roleTreeRoot.expand();
			    
			    //************************************************
			    //菜单树
			    //************************************************
			    var menuTree = new Ext.tree.TreePanel({
			        el: 'menuTreeDiv',
			        id: 'menuTree',
			        title: '菜单树信息',
			        // ********************************
			        // checkTree独有属性
			        checkModel: 'cascade', //对树的级联多选
			        onlyLeafCheckable: false,//对树所有结点都可选
			        // ********************************
			        animate: true,
			        enableDD: false,
			        containerScroll: true,
			        bodyBorder: false,
			        autoScroll: true,
			        rootVisible: false,
			        height: 300,
			        width: 307,
			        loader: new Ext.tree.TreeLoader({
			            baseAttrs: {
			                uiProvider: Ext.ux.TreeCheckNodeUI
			            }
			        })
			    });
			    
			    var menuTreeRoot = new Ext.tree.AsyncTreeNode({
			        draggable: false,
			        id: 'root',
			        text: '菜单树根节点',
			        icon: '${pageContext.request.contextPath}/images/root.gif',
			        children: [{
			            leaf: false,
			            text: '系统设置',
			            icon: '${pageContext.request.contextPath}/images/area.gif',
			            children: [{
			                leaf: true,
			                text: '菜单树管理'
			            }, {
			                leaf: false,
			                text: '角色相关',
			                children: [{
			                    leaf: true,
			                    text: '角色设定'
			                }, {
			                    leaf: true,
			                    text: '角色&菜单树关联设定'
			                }]
			            }]
			        }, {
			            leaf: true,
			            icon: '${pageContext.request.contextPath}/images/area.gif',
			            text: '人事管理'
			        }, {
			            leaf: true,
			            icon: '${pageContext.request.contextPath}/images/area.gif',
			            text: '财务管理'
			        }]
			    });
			    menuTree.setRootNode(menuTreeRoot);
			    
			    menuTree.render();
			    menuTreeRoot.expand();
			});

			function showCheckNode(){
				var array = Ext.getCmp('menuTree').getChecked();
				var _text = '';
				if(array != null){
					for (var i = 0; i < array.length; i++) {
						_text += array[i].text + '\n';
					} 
					alert(_text);
				}
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
                        - ROLE Menu SETTINGS 001 -
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
                                    选择某个角色后,将在右面显示其所能访问的[菜单]项目,<span class="need">勾选</span>状态为有访问[权限]
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
					                               		<input value="更  新" class="buttonSubmitLong" type="button" onclick="showCheckNode();"> 
					                               	</td> 
					                          		<td width="20"> 
					                         		</td> 
					                              	<td align="right"> 
					                              		<input value="重  置" class="buttonResetLong" type="button">
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

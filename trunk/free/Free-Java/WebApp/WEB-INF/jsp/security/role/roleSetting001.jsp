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
				var tree = new Ext.tree.TreePanel({
			        el: 'roleTreeDiv',
			        title: '角色树信息',
			        useArrows: true,
			        animate: true,
			        enableDD: false,
			        containerScroll: true,
			        bodyBorder: false,
			        autoScroll: true,
			        rootVisible: true,
			        height: 300,
			        width: 300,
			        loader: new Ext.tree.TreeLoader()
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
			});
		-->
        </script>
	</head>
	<body>
		<div class="defaultBody">
			<table class="appTitleTable"> 
                <tr> 
                    <td class="appTitle">
                        角色设定
                    </td> 
                    <td class="appScreenID"> 
                        - ROLE SETTINGS 001 -
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
				<form name="roleCfgForm" method="post" action="#">
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
					                                    <input type="text" size="20" maxlength="20" class="x-form-text inputText readonly" readonly="true"/>
					                                </td>
												</tr>
												<tr>
													<td class="inputItemName" height="30" width="100">
					                                    编号
					                                </td> 
					                                <td class="inputItemCell" height="30" width="200">
					                                    <input type="text" size="20" maxlength="20" class="x-form-text inputText readonly" readonly="true"/>
					                                </td>
												</tr>
												<tr>
													<td class="inputItemName" height="30" width="100">
					                                    <img src="${pageContext.request.contextPath}/images/need-input.gif">名称
					                                </td> 
					                                <td class="inputItemCell" height="30" width="200">
					                                    <input type="text" size="20" maxlength="20" class="x-form-text inputText"/>
					                                </td>
												</tr>
												<tr>
					                                <td class="inputItemName" height="30" width="100" valign="top" style="padding-top: 5px;">
					                                    详细描述
					                                </td>
					                                <td class="inputItemCell" height="115" width="200">
					                                    <textarea rows="5" cols="20" class="inputTextarea"></textarea>
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
					                                                <input value="更  新" class="buttonSubmitLong" type="button"> 
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

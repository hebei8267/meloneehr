<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%// 菜单树管理 %>
<html> 
    <head> 
        <%@ include file="/WEB-INF/jsp/base/PageHeader.jsp" %>
        <%@ include file="/WEB-INF/jsp/base/CommonCssJs.jsp" %>
        <%@ include file="/WEB-INF/jsp/base/ImportCommonPackage.jsp" %>
        <script type="text/javascript">
        <!--
        Ext.onReady(function() {

        	var tree = new Ext.tree.TreePanel({
        		el : 'menuTreeDiv',
        		title : '籍贯信息',
        		useArrows : true,
        		animate : true,
        		enableDD : false,
        		containerScroll : true,
        		bodyBorder : false,
        		autoScroll : true,
        		rootVisible : true,
        		height : 300,
        		width : 300,
        		// loader : new Ext.tree.TreeLoader({
        		// dataUrl : 'http://extjs.com/deploy/dev/examples/tree/get-nodes.php'
        		// })
        		loader : new Ext.tree.TreeLoader()
        	});

        	var root = new Ext.tree.AsyncTreeNode({
        		draggable : false,
        		id : 'root',
        		text : '籍贯结构根节点',
        		children : [{
        			leaf : false,
        			text : '中国',
        			children : [{
        				leaf : true,
        				text : '北京'
        			}, {
        				leaf : true,
        				text : '上海'
        			}, {
        				leaf : false,
        				text : '湖北省',
        				children : [{
        					leaf : true,
        					text : '武汉市'
        				}, {
        					leaf : true,
        					text : '宜昌市'
        				}]
        			}, {
        				leaf : true,
        				text : '湖南省'
        			}]
        		}, {
        			leaf : true,
        			text : '日本'
        		}, {
        			leaf : true,
        			text : '美国'
        		}]
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
			                        <span class="need">注意:[<img src="images/cube-red.png">]为必填项</span>
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
            <table> 
                <tr> 
                    <td height="20"> 
                    </td> 
                </tr> 
                <tr> 
                    <td style="padding-left: 20px"> 
                        &nbsp;
                    </td> 
                    <td align="right"> 
                        <input value="添  加" class="buttonSubmitLong" type="button" onclick=""> 
                    </td> 
                    <td width="20"> 
                    </td> 
                    <td align="left"> 
                        <input value="删  除" class="buttonDeleteLong" type="button"> 
                    </td> 
                </tr> 
                <tr> 
                    <td height="10"> 
                    </td> 
                </tr> 
            </table>
            <form:form id="menuTreeForm" method="post" modelAttribute="FD000S004ViewObject">
            <table> 
                <tr> 
                    <td style="padding-left: 20px"> 
                        &nbsp;
                    </td> 
                    <td> 
                        <div id="menuTreeDiv"> 
                        </div> 
                    </td> 
                    <td width="30"> 
                    </td> 
                    <td valign="top"> 
                        <table> 
                            <tr> 
                                <td colspan="2" class="itemTitle">
                                	节点详细信息
                                </td> 
                            </tr> 
                            <tr> 
                                <td class="inputItemName" height="30" width="100">
                                	编号
                                </td> 
                                <td class="inputItemCell" height="30" width="200">
                                	<form:input path="id" size="20" maxlength="20" cssClass="readonly" readonly="true"/>
                                </td> 
                            </tr> 
                            <tr> 
                                <td class="inputItemName" height="30" width="100"> 
                                    <img src="images/cube-red.png">名称
                                </td> 
                                <td class="inputItemCell" height="30" width="200"> 
                                    <form:input path="nodeTxt" size="20" maxlength="20"/>
                                </td> 
                            </tr> 
                            <tr> 
                                <td class="inputItemName" height="30" width="100"> 
                                    <img src="images/cube-red.png">类型
                                </td> 
                                <td class="inputItemCell" height="30" width="200">
							        <form:select path="nodeType" items="${FD000S004ViewObject.nodeTypeList}" itemValue="value" itemLabel="label"/>
                                </td> 
                            </tr>
                            <tr> 
                                <td class="inputItemName" height="30" width="100"> 
                                    <img src="images/cube-red.png">Action
                                </td> 
                                <td class="inputItemCell" height="30" width="200"> 
                                    <form:input path="actionContent" size="20" maxlength="20"/>
                                </td> 
                            </tr>
                            <tr> 
                                <td class="inputItemName" height="30" width="100"> 
                                    <img src="images/cube-red.png">访问限制
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
                                	显示位置
                                </td> 
                                <td class="inputItemCell" height="30" width="200"> 
                                    <form:input path="index" size="20" maxlength="20"/> 
                                </td> 
                            </tr> 
                            <tr> 
			                    <td height="10"> 
			                    </td> 
			                </tr> 
                            <tr> 
                                <td colspan="2" align="right"> 
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
                                    </table><!-- 按钮 END --> 
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
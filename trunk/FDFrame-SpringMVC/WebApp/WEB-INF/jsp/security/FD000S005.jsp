<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html> 
    <head> 
        <%@ include file="/WEB-INF/jsp/base/PageHeader.jsp" %>
        <%@ include file="/WEB-INF/jsp/base/CommonCssJs.jsp" %>
        <%@ include file="/WEB-INF/jsp/base/ImportCommonPackage.jsp" %>
        <script type="text/javascript">
        <!--
        Ext.onReady(function() {
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
					url : 'FD000S005JspViewAction_GetAllRoleInfoListAction.ajax',
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
			store.load();
        });
        -->
        </script>
    </head> 
    <body>
    	<div class="defaultPopWindowBody">
    	<br>
    		<table class="appTitleTable"> 
                <tr> 
                    <td class="appTitle">
                    	角色信息
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
			                    	选中要添加的[角色]&nbsp;(可多选&nbsp;--&nbsp;<span style="font-weight: bold;">按住Ctrl多选,Shift连续选择</span>)
			                    </td>
			                </tr>
			            </table>
                    </td> 
                </tr> 
            </table>
            <table>
            	<tr height="10">
            	</tr>
                <tr> 
                    <td style="padding-left: 20px"> 
                        &nbsp;
                    </td>
                 	<td>
                 		<div id="roleGridDiv"></div>
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
	                                <input value="添  加" class="buttonSubmitLong" type="button"> 
	                            </td> 
	                            <td width="20"> 
	                            </td> 
	                            <td align="right"> 
	                                <input value="关  闭" class="buttonResetLong" type="button"> 
	                            </td> 
	                        </tr> 
	                    </table>
			           	<!-- 按钮 END --> 
                 	</td>
                 </tr>
        	</table>
    	</div>
    </body> 
</html>
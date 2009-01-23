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
            //关闭当前窗口
            function closeWin(){
                parent.close();
            }
            //添加角色
            function addRoleList(){
                var selectObjs = Ext.getCmp('roleGrid').getSelections();
                if(selectObjs == null || selectObjs.length == 0){//未选择添加角色
                    showMessageBox(getNeedOneSelectedErrorMsg('角色'));
                    return;
                }
                
                var roleList = new Array();
                for(var i=0;i<selectObjs.length;i++){
                    roleList[i] = selectObjs[i];
                }
                
                //调用父窗口的回调函数
                window.opener.addRoleListCall(roleList);
                closeWin();
            }
            Ext.onReady(function(){

                var role = Ext.data.Record.create([
                    {name: 'id'},
                    {name: 'name'}
                ]);
                // create the data store
                var store = new Ext.data.Store({
                    id : 'roleStore',
                    proxy : new Ext.data.HttpProxy({
                        url : '${pageContext.request.contextPath}/security/menu/menuSetting/003/getAllRoleInfoListAction.ajax',
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
                            width : 80,
                            sortable : true,
                            dataIndex : 'id'
                        }, {
                            id : 'name',
                            header : "名称",
                            width : 150,
                            sortable : true,
                            dataIndex : 'name'
                    }],
                    stripeRows : true,
                    height : 200,
                    width : 322,
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
            <table class="appTitleTable"> 
                <tr> 
                    <td class="appTitle">
                        添加角色信息
                    </td> 
                    <td class="appScreenID"> 
                        - MENU SETTINGS 003 -
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
                                    重复添加已存在的[角色]时,将忽视该[角色]
                                </td>
                            </tr>
                            <tr>
                                <td></td>
                                <td>
                                    选中要添加的[角色](可多选--<span style="font-weight: bold;">按住Ctrl多选,Shift连续选择</span>)
                                </td>
                            </tr>
                        </table>
                    </td> 
                </tr> 
            </table>
            <form:form id="addRoleForm" method="post" modelAttribute="MenuSetting003ViewObject">
                <table>
                    <tr height="10">
                    </tr>
                    <tr>
                        <td style="padding-left: 30px">
                            &nbsp;
                        </td>
                        <td>
                            <table>
                                <tr>
                                    <td class="inputItemName" height="30" width="100">
                                        菜单节点编号
                                    </td> 
                                    <td class="inputItemCell" height="30" width="200">
                                        <extjs:input path="nodeID" disabled="true" />
                                    </td>
                                </tr>
                                <tr>
                                    <td class="inputItemName" height="30" width="100">
                                        菜单节点名称
                                    </td> 
                                    <td class="inputItemCell" height="30" width="200"> 
                                        <extjs:input path="nodeTxt" disabled="true" />
                                    </td>
                                </tr>
                                <tr height="10">
                                </tr>
                                <tr>
                                    <td colspan="2"><div id="roleGridDiv"></div></td>
                                </tr>
                            </table>
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
                                        <input value="添  加" class="buttonSubmitLong" type="button" onclick="addRoleList();"> 
                                    </td> 
                                    <td width="20"> 
                                    </td> 
                                    <td align="right"> 
                                        <input value="关  闭" class="buttonResetLong" type="button" onclick="closeWin();"> 
                                    </td> 
                                </tr> 
                            </table>
                            <!-- 按钮 END --> 
                        </td>
                     </tr>
                </table>
            </form:form>
        </div>
    </body>
</html>

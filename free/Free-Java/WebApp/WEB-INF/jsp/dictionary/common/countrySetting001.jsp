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
            Ext.onReady(function() {
            
                var country = Ext.data.Record.create([
                    {name: 'id'},
                    {name: 'name'},
                    {name: 'detail'},
                    {name: 'version'}
                ]);
                
                // create the data store
                var store = new Ext.data.Store({
                    id : 'countryStore',
                    proxy : new Ext.data.HttpProxy({
                        url : '${pageContext.request.contextPath}/dictionary/common/countrySetting/001/getAllCountryInfoListAction.ajax',
                        method: 'POST'
                    }),
                    reader : new Ext.data.JsonReader({
                        totalProperty: "totalProperty",
                        root: "dataList",
                        successProperty :'sessionTimeOut'
                    }, country),
                    listeners : {
                        loadexception : function(){
                            showMessageBox(getErrorMsg_AM001());
                        }
                    }
                });
                
                var sm = new Ext.grid.CheckboxSelectionModel({
                    header : '',
                    singleSelect: true,
                    listeners: {
                        rowselect: function(sm, row, rec) {
                            setFromData(rec);
                        },
                        rowdeselect: function(sm, row, rec) {
                            cleanFromData();
                        }
                    }
                });
            
                // create the Grid
                var grid = new Ext.grid.GridPanel({
                    store : store,
                    id : 'countryInfoGrid',
                    el : 'countryInfoGridDiv',
                    sm : sm,
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
                            dataIndex : 'id'
                        }, {
                            id : 'countryName',
                            header : "名称",
                            width : 150,
                            sortable : true,
                            dataIndex : 'name'
                    }],
                    stripeRows : true,
                    height : 300,
                    width : 300,
                    title : '国家信息'
                });
            
                grid.render();
                store.load();
            });
            //设置详细表单数据
            function setFromData(rec){
            	//extjs Ext.data.Record id
                $("selectedCountryID").value = rec.id;

                Ext.getCmp("countryID").setValue(rec.data.id);
                Ext.getCmp("countryTxt").setValue(rec.data.name);
                Ext.getCmp("countryDetail").setValue(rec.data.detail);
                
            }
            //清除详细表单数据
            function cleanFromData(){
            	//extjs Ext.data.Record id
                $("selectedCountryID").value = "";
                
                Ext.getCmp("countryID").setValue("");
                Ext.getCmp("countryTxt").setValue("");
                Ext.getCmp("countryDetail").setValue("");
            }
            //重置选择的国家信息
            function selectedNodeReset(){
            	if($F("selectedCountryID") != ""){//选中国家信息
                    var country = Ext.getCmp("countryInfoGrid").getStore().getById($F("selectedCountryID"));
                    //设置详细表单数据
                    setFromData(country);
                } else {
                    showMessageBox(getErrorMsg_AM005("国家信息"));
                }
            }

            function checkCountryTxt(){
            	if($F("selectedCountryID") == ""){//未选中国家信息
                    if(Ext.getCmp("countryTxt").getValue() != ""){
                        return getErrorMsg_EM002("国家信息");
                    }
                } else {
                    if(Ext.getCmp("countryTxt").getValue() == ""){
                        return getErrorMsg_EM001();
                    }
                }
                return true;
            }
            function checkCountryDetail(){
            	if($F("selectedCountryID") == ""){//未选中国家信息
                    if(Ext.getCmp("countryDetail").getValue() != ""){
                        return getErrorMsg_EM002("国家信息");
                    }
                }
                return true;
            }
            //更新选择的国家信息
            function updateSelectedNode(){
                if($F("selectedCountryID") != ""){//选中角色节点
                    updateSelectedNodeAction();
                } else {
                    showMessageBox(getErrorMsg_AM005("国家信息"));
                }
            }
            //更新选择的国家信息
            function updateSelectedNodeAction(){
            	if(!formExtCmpValidate("countryCfgForm")){
                    return;
                }
                
                var country = Ext.getCmp("countryInfoGrid").getStore().getById($F("selectedCountryID"));
                
                if(Ext.getCmp("countryTxt").getValue() == country.data.name 
                    && Ext.getCmp("countryDetail").getValue() == country.data.detail){//国家详细未修改，不做后台提交
                    showMessageBox(getErrorMsg_AM003());
                    return;
                }

                //表单提交简化版本
                formAjaxSubmit("${pageContext.request.contextPath}/dictionary/common/countrySetting/001/updateCountryInfoAction.ajax", 
                               {version: country.data.version,
                               id: Ext.getCmp("countryID").getValue(),
                               name: Ext.getCmp("countryTxt").getValue(),
                               detail: Ext.getCmp("countryDetail").getValue()},
                               countryGridReload ,
                               countryGridReload);
            }
            //国家列表重新加载
            function countryGridReload(){
                Ext.getCmp("countryInfoGrid").getStore().reload()
                //清除详细表单数据
                cleanFromData();
            }
        -->
        </script>
    </head>
    <body>
        <div class="defaultBody">
            <table class="appTitleTable"> 
                <tr> 
                    <td class="appTitle">
                        国家
                    </td> 
                    <td class="appScreenID"> 
                        -COUNTRY SETTING 001-
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
                        </table>
                    </td> 
                </tr> 
            </table>
            <div>
                <form:form id="countryCfgForm" method="post" modelAttribute="CountrySetting001ViewObject">
                	<%// 选中国家信息ID %>
                    <input type="hidden" id="selectedCountryID" name="selectedCountryID" value="">
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
                                                    <!-- 添加国家 -->
                                                    <td align="left">
                                                        <input value="添  加" class="buttonSubmitLong" type="button" onclick="addRole();">
                                                    </td>
                                                    <td width="20"> 
                                                    </td>
                                                    <!-- 删除国家 -->
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
                                            <div id="countryInfoGridDiv"> 
                                            </div> 
                                        </td> 
                                        <td width="30">
                                        </td> 
                                        <td valign="top">
                                            <table>
                                                <tr> 
                                                    <td colspan="2" class="x-panel-header">
                                                        国家详细信息
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td class="inputItemName" height="30" width="100">
                                                        <img src="${pageContext.request.contextPath}/images/need-input.gif">编号
                                                    </td> 
                                                    <td class="inputItemCell" height="30" width="200">
                                                        <extjs:input path="countryID" disabled="true"/>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td class="inputItemName" height="30" width="100">
                                                        <img src="${pageContext.request.contextPath}/images/need-input.gif">名称
                                                    </td> 
                                                    <td class="inputItemCell" height="30" width="200">
                                                        <extjs:input path="countryTxt" validator="checkCountryTxt" maxLength="20"/>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td class="inputItemName" height="30" width="100" valign="top" style="padding-top: 5px;">
                                                        详细描述
                                                    </td>
                                                    <td class="inputItemCell" height="115" width="200">
                                                        <extjs:textArea path="countryDetail" validator="checkCountryDetail" maxLength="255"/>
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

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

                var viewport = new Ext.Viewport({
                    layout: 'border',
                    items: [new Ext.BoxComponent({
                        // 画面公共菜单部分
                        region: 'north',
                        id: 'toolBar',
                        el: 'toolBarDiv',
                        margins: '0 5 0 5',
                        height: 45
                    }), {
                        region: 'west',
                        id: 'menuTree',
                        el: 'menuTreeDiv',
                        title: '导航',
                        split: true,
                        width: 200,
                        minSize: 100,
                        maxSize: 300,
                        collapsible: true,
                        margins: '0 0 5 5',
                        layout: 'accordion',
                        layoutConfig: {
                            animate: true
                        }
                        <c:if test="${!empty Security003ViewObject.areaMenuNodeList}">
                        ,
                        items : [
                            <c:forEach items="${Security003ViewObject.areaMenuNodeList}" var="item" varStatus="status">
                            <c:if test="${status.index != '0'}">
                            ,
                            </c:if>
                            {
                                title : '${item.text}',
                                border : false,
                                html : '<div id="subTreeAreaDiv_${item.id}" style="overflow:auto;width:100%;height:100%"></div>'
                            }
                            </c:forEach>
                        ]
                        </c:if>
                    }, new Ext.Panel({
                        id: 'work',
                        el: 'workDiv',
                        region: 'center',
                        html: '<iframe id="workFrame" name="workFrame" src=""></iframe>',
                        margins: '0 5 5 0'
                    })]
                });
                
                /** 工作区区域尺寸调整 */
                doWorkFrameLayout();
                
                Ext.getCmp("work").on('bodyresize', function(){
                    Ext.getCmp("work").doLayout();
                    doWorkFrameLayout();
                }, this);
                
                <c:forEach items="${Security003ViewObject.areaMenuNodeList}" var="item" varStatus="status">
                    initMenuTree_${item.id}();
                </c:forEach>
            });
            
            <c:forEach items="${Security003ViewObject.areaMenuNodeList}" var="item" varStatus="status">
            // 菜单树
            function initMenuTree_${item.id}() {
                var tree = new Ext.tree.TreePanel({
                    el : 'subTreeAreaDiv_${item.id}',
                    useArrows : true,
                    animate : true,
                    enableDD : false,
                    containerScroll : true,
                    bodyBorder : false,
                    autoScroll : true,
                    autoWidth : true,
                    autoHeight : true,
                    rootVisible : false
                });
              
                // set the root node
                var root = new Ext.tree.AsyncTreeNode({
                    text : 'ROOT_${item.id}', 
                    draggable : false,
                    id : '${item.id}',
                    loader: new Ext.tree.TreeLoader({
                        dataUrl:'${pageContext.request.contextPath}/security/003/getAreaMenuNodeTreeAction.ajax',
                        baseParams :{nodeId:'${item.id}'},
                        requestMethod : 'post',
                        listeners : {
                            loadexception : defaultAjaxRequestFailure
                        }
                    })
                });
                tree.setRootNode(root);
            
                tree.render();
                root.expand();
    
                tree.on("click", function(node, event) {
                    if (node.isLeaf() && node.attributes.actionContent != "") {
                        // 检查用户访问菜单节点的权限
                        checkMenuNodePermit(node.id, node.attributes.actionContent, node.attributes.defaultPermit);
                    }
                })
            }
            </c:forEach>
            
            // 工作区区域尺寸调整
            function doWorkFrameLayout(){
                Ext.get("workFrame").setWidth(Ext.getCmp("work").getInnerWidth());
                Ext.get("workFrame").setHeight(Ext.getCmp("work").getInnerHeight());
            }
            // 检查用户访问菜单节点的权限
            function checkMenuNodePermit(menuNodeId, actionContent, defaultPermit){

                if(actionContent == "" || actionContent == "null"){
                    return;
                }
                if(defaultPermit == true){
                    updateWorkFrame(actionContent);
                    return;
                }
                Ext.Ajax.request({
                    url : '${pageContext.request.contextPath}/security/003/checkMenuNodePermitAction.ajax',
                    method: 'post',
                    failure : defaultAjaxRequestFailure,
                    success : function(result, request) {
                        var oResult = eval("(" + result.responseText + ")");
                        
                        if(oResult.processResult) {// 成功
                            updateWorkFrame(actionContent);
                        } else {// 失败
                            // Ajax系统定式 start
                            if(!oResult.processResult && oResult.sessionTimeOut){
                                $("systemErrorForm").target = "_top";
                                $("systemErrorForm").submit();
                                return;
                            }
                            showMessageBox(oResult.resultMsg);
                            // Ajax系统定式 end
                            
                            return;//无访问权限
                        }
                    },
                    params : {
                        nodeId : menuNodeId
                    }
                });
            }
            //更新工作区
            function updateWorkFrame(actionContent){
                $("systemErrorForm").target = "workFrame";
                $("systemErrorForm").action = "${pageContext.request.contextPath}" + actionContent + ".faces";
                $("systemErrorForm").submit();
            }
        -->
        </script>
    </head>
    <body>
        <div class="defaultBody">
            <!-- 画面公共菜单部分 -->
            <div id="toolBarDiv">
                <!-- 标题栏 START -->
                <div>
                    <%@ include file="/WEB-INF/jsp/base/pageHeaderDate.jsp" %>
                </div>
                <!-- 标题栏 END -->
            </div>
            <!-- 画面菜单树部分 -->
            <div id="menuTreeDiv">
            </div>
            <!-- 工作区部分 -->
            <div id="workDiv">
            </div>
        </div>
        <%@ include file="/WEB-INF/jsp/base/sysErrorFrom.jsp" %>
    </body>
</html>

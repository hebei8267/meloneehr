<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%// 程序主框架 %>
<html>
    <head>
        <%@ include file="/WEB-INF/jsp/base/PageHeader.jsp" %>
        <%@ include file="/WEB-INF/jsp/base/CommonCssJs.jsp" %>
        <%@ include file="/WEB-INF/jsp/base/ImportCommonPackage.jsp" %>
        <script type="text/javascript">
        <!--
        Ext.onReady(function() {
        	var viewport = new Ext.Viewport({
				layout : 'border',
				items : [new Ext.BoxComponent({
					// 画面公共菜单部分
					region : 'north',
					id : 'toolBar',
					el : 'toolBarDiv',
					margins : '0 5 0 5',
					height : 45
				}), {
					region : 'west',
					id : 'menuTree',
					el : 'menuTreeDiv',
					title : '导航',
					split : true,
					width : 250,
					minSize : 250,
					maxSize : 400,
					collapsible : true,
					margins : '0 0 5 5',
					layout : 'accordion',
					layoutConfig : {
						animate : true
					}
					<c:if test="${!empty FD000S003ViewObject.shipAreaList}">
					,
					items : [
						<c:forEach items="${FD000S003ViewObject.shipAreaList}" var="item" varStatus="status">
						<c:if test="${status.index != '0'}">
						,
						</c:if>
						{
							title : '${item.text}',
							border : false,
							html : '<div id="subTreeAreaDiv_${item.id}"></div>'
						}
						</c:forEach>
					]
					</c:if>
				}, new Ext.Panel({
					id : 'work',
					el : 'workDiv',
					region : 'center',
					html : '<iframe id="workFrame" name="workFrame" src="javascript:false;"></iframe>',
					margins : '0 5 5 0'
				})]
			});
		
			/** 工作区区域尺寸调整 */
			doWorkFrameLayout();
		
			Ext.getCmp("work").on('bodyresize', function() {
				Ext.getCmp("work").doLayout();
				doWorkFrameLayout();
			}, this);
			
			<c:forEach items="${FD000S003ViewObject.shipAreaList}" var="item" varStatus="status">
			initMenuTree_${item.id}();
			</c:forEach>
        });
        
        /** 工作区区域尺寸调整 */
		function doWorkFrameLayout() {
			Ext.get("workFrame").setWidth(Ext.getCmp("work").getInnerWidth());
			Ext.get("workFrame").setHeight(Ext.getCmp("work").getInnerHeight());
		}
        
        <c:forEach items="${FD000S003ViewObject.shipAreaList}" var="item" varStatus="status">
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
                loader: new Ext.tree.TreeLoader(
					{dataUrl:'FD000S003AjaxViewAction_GetTreeNodeInfoAction.ajax',
					 baseParams :{id:'${item.id}'}})
            });
			tree.setRootNode(root);
		
			tree.render();
			root.expand();

			tree.on("click", function(node, event) {
				if (node.isLeaf() && node.attributes.actionContent != "") {
					$("sessionTimeOutForm").target = "workFrame";
					$("sessionTimeOutForm").action = node.attributes.actionContent;
					$("sessionTimeOutForm").submit();
				}
			})
		}
		</c:forEach>
        -->
        </script>
    </head>
    <body>
    	<%// 画面公共菜单部分 %>
        <div id="toolBarDiv">
            <%@ include file="/WEB-INF/jsp/base/PageHeaderDate.jsp" %>
            <%@ include file="/WEB-INF/jsp/base/SysErrorFrom.jsp" %>
        </div>
        <%// 画面菜单树部分 %>
        <div id="menuTreeDiv">
        </div>
        <%// 工作区部分 %>
        <div id="workDiv">
        </div>
    </body>
</html>

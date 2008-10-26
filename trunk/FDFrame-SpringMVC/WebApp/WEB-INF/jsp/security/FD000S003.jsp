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
					},
					items : [
						{
							title : '人事管理',
							border : false,
							//autoScroll : true,
							html : '<div id="personnelTreeDiv"></div>'
						}, {
							title : '财务管理',
							border : false,
							autoScroll : true,
							html : '<div id="financialTreeDiv"></div>'
						}, {
							title : '系统设置',
							border : false,
							autoScroll : true,
							html : '<div id="configTreeDiv"></div>'
						}
					]
				}, new Ext.Panel({
					id : 'work',
					el : 'workDiv',
					region : 'center',
					html : '<iframe id="workFrame" name="workFrame" src=""></iframe>',
					margins : '0 5 5 0'
				})]
			});
		
			/** 工作区区域尺寸调整 */
			doWorkFrameLayout();
		
			Ext.getCmp("work").on('bodyresize', function() {
				Ext.getCmp("work").doLayout();
				doWorkFrameLayout();
			}, this);
			
			initMenuTree();
        });
        
        /** 工作区区域尺寸调整 */
		function doWorkFrameLayout() {
			Ext.get("workFrame").setWidth(Ext.getCmp("work").getInnerWidth());
			Ext.get("workFrame").setHeight(Ext.getCmp("work").getInnerHeight());
		}
        
        
        // 菜单树
		function initMenuTree() {
			var tree = new Ext.tree.TreePanel({
				el : 'personnelTreeDiv',
				useArrows : true,
				animate : true,
				enableDD : false,
				containerScroll : true,
				bodyBorder : false,
				autoScroll : true,
				autoWidth : true,
				autoHeight : true,
				rootVisible : false,
				// loader : new Ext.tree.TreeLoader({
				// dataUrl : 'http://extjs.com/deploy/dev/examples/tree/get-nodes.php'
				// })
				loader : new Ext.tree.TreeLoader()
			});
		
			var root = new Ext.tree.AsyncTreeNode({
				text : '人力资源菜单',
				draggable : false,
				id : 'root',
				children : [{
					leaf : true,
					text : '新增员工',
					hrefSrc : '../personnel/personInfoTabs.html'
				}, {
					leaf : true,
					text : '员工信息维护',
					hrefSrc : '../personnel/personSearch.html'
				}, {
					leaf : true,
					text : '合同管理',
					hrefSrc : '../personnel/contractSearch.html'
				}]
			});
			tree.setRootNode(root);
		
			tree.render();
			root.expand();
		
			tree.on("click", function(node, event) {
				if (node.isLeaf()) {
					document.getElementById("workFrame").src = node.attributes.hrefSrc;
				}
			})
		}
        -->
        </script>
    </head>
    <body>
    	<c:forEach items="${FD000S001ViewObject.msgList}" var="item" >
${item}<br>
</c:forEach>
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

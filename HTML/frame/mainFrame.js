Ext.onReady(function() {

	Ext.state.Manager.setProvider(new Ext.state.CookieProvider());

	var viewport = new Ext.Viewport({
		layout : 'border',
		items : [new Ext.BoxComponent({
			// 画面公共菜单部分
			region : 'north',
			id : 'toolBar',
			el : 'toolBarDiv',
			height : 32
		}), {
			region : 'west',
			id : 'menuTree',
			el : 'menuTreeDiv',
			title : '导航',
			split : true,
			width : 280,
			minSize : 280,
			maxSize : 400,
			collapsible : true,
			margins : '0 0 5 5',
			layout : 'accordion',
			layoutConfig : {
				animate : true
			},
			items : [{
				title : '系统设置',
				border : false,
				autoScroll : true,
				html : '<div id="configTreeDiv"></div>'
			}, {
				title : '菜单树',
				border : false
			}]
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

	initConfigTree();
});

// 系统设置菜单树
function initConfigTree() {
	var tree = new Ext.tree.TreePanel({
		el : 'configTreeDiv',
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
		text : '系统设置菜单树',
		draggable : false,
		id : 'root',
		children : [{
			leaf : false,
			text : '数据字典',
			children : [{
				leaf : true,
				text : '国家信息',
				hrefSrc : '../dictionary/communal/country.html'
			}]
		}, {
			leaf : true,
			text : '111',
			hrefSrc : '22222222222'
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

/** 工作区区域尺寸调整 */
function doWorkFrameLayout() {
	Ext.get("workFrame").setWidth(Ext.getCmp("work").getInnerWidth());
	Ext.get("workFrame").setHeight(Ext.getCmp("work").getInnerHeight());
}
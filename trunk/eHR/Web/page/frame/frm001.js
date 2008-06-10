Ext.onReady(function() {
	Ext.QuickTips.init();

	var viewport = new Ext.Viewport({
		layout : 'border',
		items : [new Ext.BoxComponent({
			id : 'toolBar',
			region : 'north',
			el : 'toolBarDiv',
			margins : '0 5 0 5',
			height : 32
		}), new Ext.Panel({
			id : 'menuTree',
			region : 'west',
			el : 'menuTreeDiv',
			contentEl : 'menuTreeContent',
			title : '菜单树',
			split : true,
			width : 250,
			minSize : 175,
			maxSize : 400,
			collapsible : true,
			margins : '0 0 5 5'
		}), new Ext.Panel({
			id : 'work',
			el : 'workDiv',
			region : 'center',
			margins : '0 5 5 0'
		})]
	});

});

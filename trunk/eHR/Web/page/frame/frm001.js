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
			margins : '0 0 5 5',
			animate : true,
			tools : [{
				id : 'refresh',
				qtip : '同  步',
				on : {
					click : synchMenuTree
				}
			}]
		}), new Ext.Panel({
			id : 'work',
			el : 'workDiv',
			region : 'center',
			html : '<iframe id="workFrame" name="workFrame" src=""></iframe>',
			margins : '0 5 5 0'
		})]
	});
	
	/** 菜单树区域尺寸调整 */
    doMenuTreeContentLayout();
    /** 工作区区域尺寸调整 */
    doWorkFrameLayout();
    
    Ext.getCmp("menuTree").on('bodyresize', function(){
        Ext.getCmp("menuTree").doLayout();
        doMenuTreeContentLayout();
    }, this);
    
    Ext.getCmp("work").on('bodyresize', function(){
        Ext.getCmp("work").doLayout();
        doWorkFrameLayout();
    }, this);

});

/** 菜单树的数据重新加载 */
function synchMenuTree() {
	menuTreeDivInit();
	document.forms['menuTreeForm'].elements['menuTreeForm:synchMenuTreeBtn']
			.onclick();
}

/** 菜单树区域尺寸调整 */
function doMenuTreeContentLayout(){
    Ext.get("menuTreeContent").setWidth(Ext.getCmp("menuTree").getInnerWidth());
    Ext.get("menuTreeContent").setHeight(Ext.getCmp("menuTree").getInnerHeight());
}

/** 工作区区域尺寸调整 */
function doWorkFrameLayout(){
    Ext.get("workFrame").setWidth(Ext.getCmp("work").getInnerWidth());
    Ext.get("workFrame").setHeight(Ext.getCmp("work").getInnerHeight());
}

/** 菜单树加载中 */
function menuTreeDivInit(){
    var menuTree = Ext.getCmp('menuTree');
    menuTree.body.mask('加载中...', 'x-mask-loading');
}

/** 菜单树加载完毕 */
function menuTreeDivInitFinish(){
    var menuTree = Ext.getCmp('menuTree');
    menuTree.body.unmask();
}

function setFromData(actionContent) {
	nodeSelected();
	
	document.forms['menuTreeForm'].elements['menuTreeForm:actionContent'].value = actionContent;
	
	document.forms['menuTreeForm'].elements['menuTreeForm:nodeSelected'].click();
}

function nodeSelected() {
	document.getElementsByClassName("nodeSelected").each(function(elm) {
		elm.removeClassName("nodeSelected");
	});;

	getEventSrcElement().addClassName("nodeSelected");
}

function getEventSrcElement() {
	var _evt = getEvent();
	var element = _evt.srcElement || _evt.target;
	return element;
}

function getEvent() { // 同时兼容IE和FF的写法
	if (document.all) {
		return window.event;
	}
	func = getEvent.caller;
	while (func != null) {
		var arg0 = func.arguments[0];
		if (arg0) {
			if ((arg0.constructor == Event || arg0.constructor == MouseEvent)
					|| (typeof(arg0) == "object" && arg0.preventDefault && arg0.stopPropagation)) {
				return arg0;
			}
		}
		func = func.caller;
	}
	return null;
}
/**
 * @author kaka
 */
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
            },
            items: [{
                title: '系统设定',
                border: false,
                autoScroll: true,
                html: '<div id="configTreeDiv" style="overflow:auto;width:100%;height:100%"></div>'
            }, {
                title: '财务管理',
                border: false,
                autoScroll: true,
                html: '<div id="financialTreeDiv" style="overflow:auto;width:100%;height:100%"></div>'
            }, {
                title: '人事管理',
                border: false,
                autoScroll: true,
                html: '<div id="personnelTreeDiv" style="overflow:auto;width:100%;height:100%"></div>'
            }]
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
    
    initConfigTree();
    //initHumanResTree();
    //initFinancialTree();
});

// 系统设置菜单
function initConfigTree(){
    var tree = new Ext.tree.TreePanel({
        el: 'configTreeDiv',
		useArrows: true,
        animate: true,
        enableDD: false,
        containerScroll: true,
        bodyBorder: false,
        autoScroll: true,
        rootVisible: false,
        // loader : new Ext.tree.TreeLoader({
        // dataUrl : 'http://extjs.com/deploy/dev/examples/tree/get-nodes.php'
        // })
        loader: new Ext.tree.TreeLoader()
    });
    
    var root = new Ext.tree.AsyncTreeNode({
        text: '系统设定',
        draggable: false,
        id: 'root',
        children: [{
            leaf: true,
            text: '菜单树设定',
			hrefSrc: '../privilege/menuNode.html'
        }, {
            leaf: false,
            text: '角色相关',
            children: [{
                leaf: true,
                text: '角色设定',
				hrefSrc: '../privilege/role/role.html'
            }, {
                leaf: true,
                text: '角色&菜单树关联设定',
				hrefSrc: '../privilege/role/role&Menu.html'
            }]
        },{
        	leaf: false,
            text: '数据字典',
            children: [{
                leaf: false,
                text: '公共相关',
				children: [{
					leaf: true,
	                text: '国家',
					hrefSrc: '../dictionary/common/country.html'
				}]
            },{
            	leaf: false,
                text: '组织相关',
				children: [{
					leaf: true,
	                text: '组织类型',
					hrefSrc: '../dictionary/organization/organizationType.html'
				}]
            }]
        }]
    });
    tree.setRootNode(root);
    
    tree.render();
    root.expand();
    
    tree.on("click", function(node, event){
        if (node.isLeaf()) {
            document.getElementById("workFrame").src = node.attributes.hrefSrc;
        }
    })
}

/** 工作区区域尺寸调整 */
function doWorkFrameLayout(){
    Ext.get("workFrame").setWidth(Ext.getCmp("work").getInnerWidth());
    Ext.get("workFrame").setHeight(Ext.getCmp("work").getInnerHeight());
}

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
                title: '系统设置',
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
        text: '系统设置',
        draggable: false,
        id: 'root',
        children: [{
            leaf: true,
            text: '菜单树管理',
			hrefSrc: '../privilege/menuNode.html'
        }, {
            leaf: false,
            text: '数据字典',
            children: [{
                leaf: false,
                text: '公共相关',
                children: [{
                    leaf: true,
                    text: '国家',
                    hrefSrc: '../setting/dictionary/common/country.html'
                }]
            }, {
                leaf: false,
                text: '雇佣相关',
                children: [{
                    leaf: true,
                    text: '雇佣类型',
                    hrefSrc: '../setting/dictionary/employment/employType.html'
                }, {
                    leaf: true,
                    text: '合同类型',
                    hrefSrc: '../setting/dictionary/employment/contractType.html'
                }, {
                    leaf: true,
                    text: '员工当前工作状态',
                    hrefSrc: '../setting/dictionary/employment/employeeWorkState.html'
                }]
            }, {
                leaf: false,
                text: '组织相关',
                children: [{
                    leaf: true,
                    text: '组织类型',
                    hrefSrc: '../setting/dictionary/organization/organizationType.html'
                }, {
                    leaf: true,
                    text: '职种(职务类型)',
                    hrefSrc: '../setting/dictionary/organization/jobPositionType.html'
                }]
            }, {
                leaf: false,
                text: '人员相关',
                children: [{
                    leaf: true,
                    text: '教育类型',
                    hrefSrc: '../setting/dictionary/personnel/educationType.html'
                }, {
                    leaf: true,
                    text: '教育专业',
                    hrefSrc: '../setting/dictionary/personnel/educateSpecialty.html'
                }, {
                    leaf: true,
                    text: '身份标识类型',
                    hrefSrc: '../setting/dictionary/personnel/identifieationType.html'
                }, {
                    leaf: true,
                    text: '民族',
                    hrefSrc: '../setting/dictionary/personnel/nation.html'
                }, {
                    leaf: true,
                    text: '籍贯',
                    hrefSrc: '../setting/dictionary/personnel/nativeplace.html'
                }]
            }, {
                leaf: false,
                text: '财务相关',
                children: [{
                    leaf: true,
                    text: '薪酬福利类型',
                    hrefSrc: '../setting/dictionary/financial/salaryType.html'
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

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
            width: 280,
            minSize: 280,
            maxSize: 400,
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
                html: '<div id="configTreeDiv"></div>'
            }, {
                title: '菜单',
                border: false,
                autoScroll: true
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
        autoWidth: true,
        autoHeight: true,
        rootVisible: false,
        // loader : new Ext.tree.TreeLoader({
        // dataUrl : 'http://extjs.com/deploy/dev/examples/tree/get-nodes.php'
        // })
        loader: new Ext.tree.TreeLoader()
    });
    
    var root = new Ext.tree.AsyncTreeNode({
        text: '系统设置菜单',
        draggable: false,
        id: 'root',
        children: [{
            leaf: false,
            text: '组织结构相关',
            children: [{
                leaf: true,
                text: '组织',
                hrefSrc: '../setting/organization/organization.html'
            }]
        }, {
            leaf: false,
            text: '数据字典',
            children: [{
                leaf: false,
                text: '公共相关',
                children: [{
                    leaf: true,
                    text: '国家',
                    hrefSrc: '../setting/common/country.html'
                }]
            }, {
                leaf: false,
                text: '雇佣相关',
                children: [{
                    leaf: true,
                    text: '雇佣类型',
                    hrefSrc: '../setting/employment/employType.html'
                }, {
                    leaf: true,
                    text: '合同类型',
                    hrefSrc: '../setting/employment/contractType.html'
                }, {
                    leaf: true,
                    text: '员工当前工作状态',
                    hrefSrc: '../setting/employment/employeeWorkState.html'
                }]
            }, {
                leaf: false,
                text: '组织相关',
                children: [{
                    leaf: true,
                    text: '组织类型',
                    hrefSrc: '../setting/organization/organizationType.html'
                }, {
                    leaf: true,
                    text: '职种(职务类型)',
                    hrefSrc: '../setting/organization/jobPositionType.html'
                }]
            }, {
                leaf: false,
                text: '人员相关',
                children: [{
                    leaf: true,
                    text: '学历类型',
                    hrefSrc: '../setting/personnel/educationType.html'
                }, {
                    leaf: true,
                    text: '教育专业',
                    hrefSrc: '../setting/personnel/educateSpecialty.html'
                }, {
                    leaf: true,
                    text: '身份标识类型',
                    hrefSrc: '../setting/personnel/identifieationType.html'
                }, {
                    leaf: true,
                    text: '民族',
                    hrefSrc: '../setting/personnel/nation.html'
                }, {
                    leaf: true,
                    text: '籍贯',
                    hrefSrc: '../setting/personnel/nativeplace.html'
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

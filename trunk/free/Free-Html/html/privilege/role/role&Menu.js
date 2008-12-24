/**
 * @author kaka
 */
Ext.onReady(function(){
    //************************************************
    //角色树
    //************************************************
    var roleTree = new Ext.tree.TreePanel({
        el: 'roleTreeDiv',
        title: '角色树信息',
        useArrows: true,
        animate: true,
        enableDD: false,
        containerScroll: true,
        bodyBorder: false,
        autoScroll: true,
        rootVisible: false,
        height: 300,
        width: 300,
        loader: new Ext.tree.TreeLoader()
    });
    
    var roleTreeRoot = new Ext.tree.AsyncTreeNode({
        draggable: false,
        id: 'root',
        text: '角色树根节点',
        icon: '../../../images/root.gif',
        children: [{
            leaf: true,
            text: '系统管理员',
            icon: '../../../images/role.gif',
        }, {
            leaf: false,
            icon: '../../../images/role.gif',
            text: '一般用户',
            children: [{
                leaf: true,
                text: '行政人员',
                icon: '../../../images/role.gif'
            }]
        }, {
            leaf: true,
            icon: '../../../images/role.gif',
            text: '临时用户'
        }]
    });
    roleTree.setRootNode(roleTreeRoot);
    
    roleTree.render();
    roleTreeRoot.expand();
    
    //************************************************
    //菜单树
    //************************************************
    var menuTree = new Ext.tree.TreePanel({
        el: 'menuTreeDiv',
        id: 'menuTree',
        title: '菜单树信息',
        // ********************************
        // checkTree独有属性
        checkModel: 'cascade', //对树的级联多选
        onlyLeafCheckable: false,//对树所有结点都可选
        // ********************************
        animate: true,
        enableDD: false,
        containerScroll: true,
        bodyBorder: false,
        autoScroll: true,
        rootVisible: false,
        height: 300,
        width: 307,
        loader: new Ext.tree.TreeLoader({
            baseAttrs: {
                uiProvider: Ext.ux.TreeCheckNodeUI
            }
        })
    });
    
    var menuTreeRoot = new Ext.tree.AsyncTreeNode({
        draggable: false,
        id: 'root',
        text: '菜单树根节点',
        icon: '../../../images/root.gif',
        children: [{
            leaf: false,
            text: '系统设置',
            icon: '../../../images/area.gif',
            children: [{
                leaf: true,
                text: '菜单树管理'
            }, {
                leaf: false,
                text: '角色相关',
                children: [{
                    leaf: true,
                    text: '角色设定'
                }, {
                    leaf: true,
                    text: '角色&菜单树关联设定'
                }]
            }]
        }, {
            leaf: true,
            icon: '../../../images/area.gif',
            text: '人事管理'
        }, {
            leaf: true,
            icon: '../../../images/area.gif',
            text: '财务管理'
        }]
    });
    menuTree.setRootNode(menuTreeRoot);
    
    menuTree.render();
    menuTreeRoot.expand();
});

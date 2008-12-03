/**
 * @author kaka
 */
Ext.onReady(function(){
    var tree = new Ext.tree.TreePanel({
        el: 'roleTreeDiv',
        title: '角色树信息',
        useArrows: true,
        animate: true,
        enableDD: false,
        containerScroll: true,
        bodyBorder: false,
        autoScroll: true,
        rootVisible: true,
        height: 300,
        width: 300,
        loader: new Ext.tree.TreeLoader()
    });
    
    var root = new Ext.tree.AsyncTreeNode({
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
    tree.setRootNode(root);
    
    tree.render();
    root.expand();
});

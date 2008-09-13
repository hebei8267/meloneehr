/**
 * @author kaka
 */
Ext.onReady(function(){

    var tree = new Ext.tree.TreePanel({
        el: 'jobPositionTypeInfoTreeDiv',
		title: '职种(职务类型)信息',
        useArrows: true,
        animate: true,
        enableDD: false,
        containerScroll: true,
        bodyBorder: false,
        autoScroll: true,
        rootVisible: false,
        height: 300,
        width: 300,
        // loader : new Ext.tree.TreeLoader({
        // dataUrl : 'http://extjs.com/deploy/dev/examples/tree/get-nodes.php'
        // })
        loader: new Ext.tree.TreeLoader()
    });
    
    var root = new Ext.tree.AsyncTreeNode({
        draggable: false,
        id: 'root',
        children: [{
            leaf: false,
            text: '管理类',
            children: [{
                leaf: true,
                text: '董事长'
            }, {
                leaf: true,
                text: '总经理'
            }, {
                leaf: true,
                text: '经理'
            }]
        }, {
            leaf: true,
            text: '人力资源类'
        }, {
            leaf: true,
            text: '营销类'
        }, {
            leaf: true,
            text: '财务类'
        }, {
            leaf: true,
            text: '生产类'
        }, {
            leaf: true,
            text: '行政类'
        }]
    });
    tree.setRootNode(root);
    
    tree.render();
    root.expand();
});

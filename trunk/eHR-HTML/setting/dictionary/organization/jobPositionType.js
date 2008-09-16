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
        rootVisible: true,
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
        text: '职种(职务类型)结构根节点',
        children: [{
            leaf: false,
            text: '管理类',
            children: [{
                leaf: true,
                text: '企业管理类'
            }, {
                leaf: true,
                text: '经营类'
            }, {
                leaf: true,
                text: '财务类'
            }, {
                leaf: true,
                text: '人力资源类'
            }]
        }, {
            leaf: true,
            text: '行政类'
        }, {
            leaf: false,
            text: '技术类',
            children: [{
                leaf: true,
                text: '研发类'
            }, {
                leaf: true,
                text: '工艺类'
            }]
        }, {
            leaf: false,
            text: '营销类',
            children: [{
                leaf: true,
                text: '销售类'
            }, {
                leaf: true,
                text: '销售支持类'
            }]
        }, {
            leaf: false,
            text: '作业类',
            children: [{
                leaf: true,
                text: '操作类'
            }, {
                leaf: true,
                text: '维修类'
            }]
        }]
    });
    tree.setRootNode(root);
    
    tree.render();
    root.expand();
});

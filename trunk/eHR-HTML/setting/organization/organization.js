/**
 * @author kaka
 */
Ext.onReady(function(){

    var tree = new Ext.tree.TreePanel({
        el: 'organizationInfoTreeDiv',
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
            text: '中国',
            children: [{
                leaf: true,
                text: '北京'
            }, {
                leaf: true,
                text: '上海'
            }, {
                leaf: false,
                text: '湖北省',
                children: [{
                    leaf: true,
                    text: '武汉市'
                }, {
                    leaf: true,
                    text: '宜昌市'
                }]
            }, {
                leaf: true,
                text: '湖南省'
            }]
        }, {
            leaf: true,
            text: '日本'
        }, {
            leaf: true,
            text: '美国'
        }]
    });
    tree.setRootNode(root);
    
    tree.render();
    root.expand();
});

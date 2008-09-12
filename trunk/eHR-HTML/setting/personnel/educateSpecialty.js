/**
 * @author kaka
 */
Ext.onReady(function(){

    var tree = new Ext.tree.TreePanel({
        el: 'educateSpecialtyInfoTreeDiv',
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
            leaf: true,
            text: '哲学'
        }, {
            leaf: true,
            text: '经济学'
        }, {
            leaf: true,
            text: '法学'
        }, {
            leaf: false,
            text: '理学',
            children: [{
                leaf: true,
                text: '数学类'
            }, {
                leaf: true,
                text: '物理学类'
            }, {
                leaf: true,
                text: '化学类'
            }, {
                leaf: false,
                text: '电子信息科学类',
                children: [{
                    leaf: true,
                    text: '电子信息科学与技术'
                }, {
                    leaf: true,
                    text: '光信息科学与技术'
                }]
            }]
        }]
    });
    tree.setRootNode(root);
    
    tree.render();
    root.expand();
});

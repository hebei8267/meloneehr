/**
 * @author kaka
 */
Ext.onReady(function(){

    var tree = new Ext.tree.TreePanel({
        el: 'organizationInfoTreeDiv',
        title: '组织信息',
        useArrows: true,
        animate: true,
        enableDD: false,
        containerScroll: true,
        bodyBorder: false,
        autoScroll: true,
        rootVisible: true,
        height: 620,
        width: 300,
        // loader : new Ext.tree.TreeLoader({
        // dataUrl : 'http://extjs.com/deploy/dev/examples/tree/get-nodes.php'
        // })
        loader: new Ext.tree.TreeLoader()
    });
    
    var root = new Ext.tree.AsyncTreeNode({
        draggable: false,
        id: 'root',
        text: '组织结构根节点',
        children: [{
            leaf: false,
            text: '＊＊软件公司',
            children: [{
                leaf: true,
                text: '综合业务部'
            }, {
                leaf: true,
                text: '质量管理部'
            }, {
                leaf: true,
                text: '开发一部'
            }, {
                leaf: true,
                text: '开发二部'
            }]
        }]
    });
    tree.setRootNode(root);
    
    tree.render();
    root.expand();
    
    // 设立时间
    var startDate = new Ext.form.DateField({
        name: 'register_date',
        width: 155,
        altFormats: 'Y-m-d',
        format: 'Y-m-d'
    });
    startDate.render('startDate'); // div-di
    
	// 撤销时间
	var endDate = new Ext.form.DateField({
        name: 'register_date',
        width: 155,
        altFormats: 'Y-m-d',
        format: 'Y-m-d'
    });
    endDate.render('endDate'); // div-di
});

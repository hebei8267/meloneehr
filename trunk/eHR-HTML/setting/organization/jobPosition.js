/**
 * @author kaka
 */
Ext.onReady(function(){
	var jobPositionTypeCombo = new Ext.form.ComboBox({
		triggerAction : 'all',
		width : 155,
		editable : false,
		transform : 'jobPositionType' // 转变表单元素ID
	});
	
    // ------------------------------------------------------------------------
    // ------------------------------------------------------------------------
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
        height: 250,
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
    
    
    // ------------------------------------------------------------------------
    // jobPosition grid
    // ------------------------------------------------------------------------
    var myData = [['A001', '董事长', '企业管理类'], 
	['A002', '经理', '企业管理类'], ['A003', '秘书', '行政类'], 
	['A004', '程序员', '研发类']];
    
    // create the data store
    var store = new Ext.data.SimpleStore({
        fields: [{
            name: 'jobPositionID'
        }, {
            name: 'jobPositionName'
        }, {
            name: 'jobPositionType'
        }]
    });
    store.loadData(myData);
    
    var sm = new Ext.grid.CheckboxSelectionModel({
        header: '',
        singleSelect: true,
        listeners: {
            rowselect: function(sm, row, rec){
                //	setFromData(rec);
            },
            rowdeselect: function(sm, row, rec){
                //		cleanFromData();
            }
        }
    });
    
    // create the Grid
    var grid = new Ext.grid.GridPanel({
        store: store,
        id: 'jobPositionInfoGrid',
        el: 'jobPositionInfoGridDiv',
        columns: [sm, new Ext.grid.RowNumberer({
            header: '序号',// 自动行号
            width: 35
        }), {
            id: 'jobPositionID',
            header: "职务编号",
            width: 80,
            sortable: true,
            dataIndex: 'jobPositionID'
        }, {
            id: 'jobPositionName',
            header: "职务名称",
            width: 90,
            sortable: true,
            dataIndex: 'jobPositionName'
        }, {
            id: 'jobPositionType',
            header: "职务类型",
            width: 90,
            sortable: true,
            dataIndex: 'jobPositionType'
        }],
        stripeRows: true,
        height: 250,
        width: 320,
        title: '职务信息'
    });
    
    grid.render();
});

/**
 * @author kaka
 */
Ext.onReady(function(){
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
        height: 780,
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
    // ------------------------------------------------------------------------
    // job grid
    // ------------------------------------------------------------------------
    var myData = [['A001', '董事长', '2008-09-15', '1', '否', '是', '2028-09-15'],
	 ['A002', '经理', '2008-09-15', '2', '否', '是', '2028-09-15'], 
	 ['A003', '秘书', '2008-09-15', '3', '否', '否', '2028-09-15'],
	  ['A004', '程序员', '2008-09-15', '10', '是', '否', '2028-09-15']];
    
    // create the data store
    var store = new Ext.data.SimpleStore({
        fields: [{
            name: 'jobID'
        }, {
            name: 'jobName'
        }, {
            name: 'startDate'
        }, {
            name: 'assignmentSize'
        }, {
            name: 'allowExceed'
        }, {
            name: 'isManager'
        }, {
            name: 'endDate'
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
        id: 'jobInfoGrid',
        el: 'jobInfoGridDiv',
        columns: [sm, new Ext.grid.RowNumberer({
            header: '序号',// 自动行号
            width: 35
        }), {
            id: 'jobID',
            header: "职位编号",
            width: 70,
            sortable: true,
            dataIndex: 'jobID'
        }, {
            id: 'jobName',
            header: "职位名称",
            width: 70,
            sortable: true,
            dataIndex: 'jobName'
        }, {
            id: 'startDate',
            header: "设立时间",
            width: 80,
            sortable: false,
            dataIndex: 'startDate'
        }, {
            id: 'assignmentSize',
            header: "编制人数(人)",
            width: 80,
            sortable: false,
            dataIndex: 'assignmentSize'
        }, {
            id: 'allowExceed',
            header: "允许超编",
            width: 70,
            sortable: true,
            dataIndex: 'allowExceed'
        }, {
            id: 'isManager',
            header: "组织负责人",
            width: 70,
            sortable: true,
            dataIndex: 'isManager'
        }, {
            id: 'endDate',
            header: "撤销时间",
            width: 80,
            sortable: false,
            dataIndex: 'endDate'
        }],
        stripeRows: true,
        height: 200,
        width: 320,
        title: '职位信息'
    });
    
    grid.render();
});

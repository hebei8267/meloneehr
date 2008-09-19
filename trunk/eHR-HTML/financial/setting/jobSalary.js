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
    // job grid
    // ------------------------------------------------------------------------
    var myData = [['A001', '董事长', '2008-09-15', '1', '否', '是', '2028-09-15'], ['A002', '经理', '2008-09-15', '2', '否', '是', '2028-09-15'], ['A003', '秘书', '2008-09-15', '3', '否', '否', '2028-09-15'], ['A004', '程序员', '2008-09-15', '10', '是', '否', '2028-09-15']];
    
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
            header: "编号",
            width: 70,
            sortable: true,
            dataIndex: 'jobID'
        }, {
            id: 'jobName',
            header: "名称",
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
        height: 250,
        width: 320,
        title: '职位信息'
    });
    
    grid.render();
    
    // ------------------------------------------------------------------------
    // jobSalary grid
    // ------------------------------------------------------------------------
    var myData1 = [['A001001', '基本工资1', 'A001', '基本工资', '2008-09-19', '1300', '', '2009-09-19', ''], ['A001002', '基本工资2', 'A001', '基本工资', '2008-09-19', '1400', '', '2009-09-19', ''], ['A001003', '基本工资3', 'A001', '基本工资', '2008-09-19', '1500', '', '2009-09-19', ''], ['A002001', '岗位工资1', 'A002', '岗位工资', '2008-09-19', '400', '', '2009-09-19', ''], ['A002002', '岗位工资2', 'A002', '岗位工资', '2008-09-19', '500', '', '2009-09-19', ''], ['A002003', '岗位工资3', 'A002', '岗位工资', '2008-09-19', '600', '', '2009-09-19', ''], ['A003001', '工龄工资1', 'A003', '工龄工资', '2008-09-19', '300', '', '2009-09-19', ''], ['A003002', '工龄工资2', 'A003', '工龄工资', '2008-09-19', '350', '', '2009-09-19', ''], ['A003003', '工龄工资3', 'A003', '工龄工资', '2008-09-19', '400', '', '2009-09-19', '']];
    
    var reader1 = new Ext.data.ArrayReader({}, [{
        name: 'salaryID'
    }, {
        name: 'salaryName'
    }, {
        name: 'salaryTypeID'
    }, {
        name: 'salaryTypeName'
    }, {
        name: 'startDate'
    }, {
        name: 'moneySum'
    }, {
        name: 'description'
    }, {
        name: 'endDate'
    }, {
        name: 'endDescription'
    }]);
    
    // create the data store
    var store1 = new Ext.data.GroupingStore({
        reader: reader1,
        sortInfo: {
            field: 'salaryID',
            direction: "ASC"
        },
        groupField: 'salaryTypeName'
    });
    store1.loadData(myData1);
    
    var sm1 = new Ext.grid.CheckboxSelectionModel({
        header: '',
        singleSelect: false,
        listeners: {
            rowselect: function(sm, row, rec){
                // setFromData(rec);
            },
            rowdeselect: function(sm, row, rec){
                // cleanFromData();
            }
        }
    });
    
    // create the Grid
    var grid1 = new Ext.grid.GridPanel({
        store: store1,
        id: 'jobSalaryInfoGrid',
        el: 'jobSalaryInfoGridDiv',
        columns: [sm1, new Ext.grid.RowNumberer({
            header: '序号',// 自动行号
            width: 35
        }), {
            id: 'salaryID',
            header: "编号",
            width: 100,
            sortable: true,
            dataIndex: 'salaryID'
        }, {
            id: 'salaryName',
            header: "名称",
            width: 120,
            sortable: true,
            dataIndex: 'salaryName'
        }, {
            id: 'salaryTypeName',
            header: "薪酬福利类型",
            hidden: true,
            hideable: false,
            menuDisabled: true,
            dataIndex: 'salaryTypeName'
        }, {
            id: 'startDate',
            header: "设立时间",
            width: 120,
            sortable: true,
            dataIndex: 'startDate'
        }, {
            id: 'moneySum',
            header: "数额(元)",
            width: 120,
            sortable: true,
            dataIndex: 'moneySum'
        }, {
            id: 'endDate',
            header: "撤销时间",
            width: 120,
            sortable: true,
            dataIndex: 'endDate'
        }],
        stripeRows: true,
        view: new Ext.grid.GroupingView({
            forceFit: true,
            enableGroupingMenu: false
        }),
        height: 250,
        width: 645,
        title: '薪酬福利信息'
    });
    
    grid1.render();
});

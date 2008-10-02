/**
 * @author kaka
 */
Ext.onReady(function(){
    var sexCombo = new Ext.form.ComboBox({
        triggerAction: 'all',
        width: 155,
        editable: false,
        transform: 'sex' // 转变表单元素ID
    });
    
    var countryCombo = new Ext.form.ComboBox({
        triggerAction: 'all',
        width: 155,
        editable: false,
        transform: 'country' // 转变表单元素ID
    });
    
    
    var myData = [['E01010', '420107198201012020', '张三', '软件公司', '总经理', 'zhangsan', '男', '中华人民共和国'],
	 ['E01011', '420107198201012021', '王武', '开发部', '程序员', 'wangwu', '男', '日本'],
	 ['E01012', '420107198201012022', '李小二', '销售部', '客户代表', 'lixiaoer', '女', '美利坚合众国']];
    
    // create the data store
    var store = new Ext.data.SimpleStore({
        fields: [{
            name: 'employeeID'
        }, {
            name: 'identifieationID'
        }, {
            name: 'employeeName'
        }, {
            name: 'orgName'
        }, {
            name: 'jobName'
        }, {
            name: 'employeeNamePY'
        }, {
            name: 'sex'
        }, {
            name: 'countryName'
        }]
    });
    store.loadData(myData);
    
    var pagingBar = new Ext.PagingToolbar({
        pageSize: 25,
        store: store,
        displayInfo: true
    });
    
    // create the Grid
    var grid = new Ext.grid.GridPanel({
        store: store,
        id: 'employeeInfoGrid',
        el: 'employeeInfoGridDiv',
        columns: [new Ext.grid.RowNumberer({
            header: '序号',// 自动行号
            width: 35
        }), {
            id: 'employeeID',
            header: "员工编号",
            width: 80,
            sortable: true,
            dataIndex: 'employeeID'
        }, {
            id: 'identifieationID',
            header: "证件编号",
            width: 150,
            sortable: true,
            dataIndex: 'identifieationID'
        }, {
            id: 'employeeName',
            header: "姓名",
            width: 60,
            sortable: true,
            dataIndex: 'employeeName'
        }, {
            id: 'orgName',
            header: "组织名称",
            width: 100,
            sortable: true,
            dataIndex: 'orgName'
        }, {
            id: 'jobName',
            header: "职位名称",
            width: 80,
            sortable: true,
            dataIndex: 'jobName'
        }, {
            id: 'employeeNamePY',
            header: "拼音",
            width: 80,
            sortable: true,
            dataIndex: 'employeeNamePY'
        }, {
            id: 'sex',
            header: "性别",
            width: 40,
            sortable: true,
            dataIndex: 'sex'
        }, {
            id: 'countryName',
            header: "国籍",
            width: 100,
            sortable: true,
            dataIndex: 'countryName'
        }],
        stripeRows: true,
        height: 300,
        width: 689,
        title: '员工信息',
        bbar: pagingBar
    });
    
    grid.render();
});

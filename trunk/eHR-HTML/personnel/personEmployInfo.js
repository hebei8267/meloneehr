/**
 * @author kaka
 */
Ext.onReady(function(){
    var employTypeCombo = new Ext.form.ComboBox({
        triggerAction: 'all',
        width: 155,
        editable: false,
        transform: 'employType' // 转变表单元素ID
    });
    
    var contractTypeCombo = new Ext.form.ComboBox({
        triggerAction: 'all',
        width: 155,
        editable: false,
        transform: 'contractType' // 转变表单元素ID
    });
    
    var myData = [['A001', '劳动合同', '劳动合同', '2008-7-1', '2008-7-3'], ['A002', '保密合同', '工作保密合同', '2008-7-5', '2008-7-7'], ['A003', '培训合同', '培训合同', '2008-7-9', '2008-7-11']];
    
    var reader = new Ext.data.ArrayReader({}, [{
        name: 'contractID'
    }, {
        name: 'contractName'
    }, {
        name: 'contractType'
    }, {
        name: 'startDate'
    }, {
        name: 'endDate'
    }]);
    
    // create the data store
    var store = new Ext.data.GroupingStore({
        reader: reader,
        sortInfo: {
            field: 'contractID',
            direction: "ASC"
        },
        groupField: 'contractType'
    });
    store.loadData(myData);
    
    var sm = new Ext.grid.CheckboxSelectionModel({
        header: '',
        singleSelect: true,
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
    var grid = new Ext.grid.GridPanel({
        store: store,
        id: 'contractInfoGrid',
        el: 'contractInfoGridDiv',
        columns: [sm, new Ext.grid.RowNumberer({
            header: '序号',// 自动行号
            width: 35
        }), {
            id: 'contractID',
            header: "编号",
            width: 150,
            sortable: true,
            dataIndex: 'contractID'
        }, {
            id: 'contractName',
            header: "合同名称",
            width: 100,
            sortable: true,
            dataIndex: 'contractName'
        }, {
            id: 'contractType',
            header: "合同类型",
            hidden: true,
            hideable: false,
            menuDisabled: true,
            dataIndex: 'contractType'
        }, {
            id: 'startDate',
            header: "生效时间",
            width: 110,
            sortable: true,
            dataIndex: 'startDate'
        }, {
            id: 'endDate',
            header: "失效时间",
            width: 100,
            sortable: true,
            dataIndex: 'endDate'
        }, {
            id: 'detailBtn',
            header: "合同附件",
            dataIndex: 'contractID',
            width: 80,
            renderer: function(contractID){
                if (contractID == "A001") {
                    return '<span><center><input type="button" class="buttonReset" value="查  看" onclick=""></center></span>';
                } else {
                    return '<span><center><input type="button" class="buttonReset" value="添  加" onclick=""></center></span>';
                }
                
            }
        }],
        stripeRows: true,
        view: new Ext.grid.GroupingView({
            enableGroupingMenu: false
        }),
        height: 200,
        width: 645,
        title: '合同信息'
    });
    
    grid.render();
    
    /////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////
    var myData1 = [['主', '＊＊软件公司', '董事长', '2008-7-1', '2010-7-1'], ['', '开发一部', '开发人员', '2008-9-5', '2008-9-30']];
    
    // create the data store
    var store1 = new Ext.data.SimpleStore({
        fields: [{
            name: 'isMainJob'
        }, {
            name: 'orgName'
        }, {
            name: 'jobName'
        }, {
            name: 'jobStartDate'
        }, {
            name: 'jobEndDate'
        }]
    });
    store1.loadData(myData1);
    
    var sm1 = new Ext.grid.CheckboxSelectionModel({
        header: '',
        singleSelect: true,
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
        id: 'jobInfoGrid',
        el: 'jobInfoGridDiv',
        columns: [sm1, new Ext.grid.RowNumberer({
            header: '序号',// 自动行号
            width: 35
        }), {
            id: 'isMainJob',
            header: "主／副",
            width: 45,
            sortable: true,
            dataIndex: 'isMainJob'
        }, {
            id: 'orgName',
            header: "组织名称",
            width: 220,
            sortable: true,
            dataIndex: 'orgName'
        }, {
            id: 'jobName',
            header: "职位名称",
            width: 100,
            sortable: true,
            dataIndex: 'jobName'
        }, {
            id: 'jobStartDate',
            header: "任职开始时间",
            width: 100,
            sortable: true,
            dataIndex: 'jobStartDate'
        }, {
            id: 'jobEndDate',
            header: "任职结束时间",
            width: 100,
            sortable: true,
            dataIndex: 'jobEndDate'
        }],
        stripeRows: true,
        height: 150,
        width: 645,
        title: '任职情况信息'
    });
    
    grid1.render();	
	

	var isMainJobCombo = new Ext.form.ComboBox({
        triggerAction: 'all',
        width: 155,
        editable: false,
        transform: 'isMainJob' // 转变表单元素ID
    });
	
	// 设立时间
	var jobStartDate = new Ext.form.DateField({
		name : 'jobStartDate',
		width : 155,
		altFormats : 'Y-m-d',
		format : 'Y-m-d'
	});
	jobStartDate.render('jobStartDate'); // div-di

	// 撤销时间
	var jobEndDate = new Ext.form.DateField({
		name : 'jobEndDate',
		width : 155,
		altFormats : 'Y-m-d',
		format : 'Y-m-d'
	});
	jobEndDate.render('jobEndDate'); // div-di
});

/**
 * @author kaka
 */
Ext.onReady(function() {

	var myData = [['A001001', '基本工资1', 'A001', '基本工资', '20080919', '1300', '', '20090919', ''],
			['A001002', '基本工资2', 'A001', '基本工资', '20080919', '1400', '', '20090919', ''],
			['A001003', '基本工资3', 'A001', '基本工资', '20080919', '1500', '', '20090919', ''],
			['A002001', '岗位工资1', 'A002', '岗位工资', '20080919', '400', '', '20090919', ''],
			['A002002', '岗位工资2', 'A002', '岗位工资', '20080919', '500', '', '20090919', ''],
			['A002003', '岗位工资3', 'A002', '岗位工资', '20080919', '600', '', '20090919', ''],
			['A003001', '工龄工资1', 'A003', '工龄工资', '20080919', '300', '', '20090919', ''],
			['A003002', '工龄工资2', 'A003', '工龄工资', '20080919', '350', '', '20090919', ''],
			['A003003', '工龄工资3', 'A003', '工龄工资', '20080919', '400', '', '20090919', '']];

	var reader = new Ext.data.ArrayReader({}, [{
		name : 'salaryID'
	}, {
		name : 'salaryName'
	}, {
		name : 'salaryTypeID'
	}, {
		name : 'salaryTypeName'
	}, {
		name : 'startDate'
	}, {
		name : 'moneySum'
	}, {
		name : 'description'
	}, {
		name : 'endDate'
	}, {
		name : 'endDescription'
	}]);

	// create the data store
	var store = new Ext.data.GroupingStore({
		reader : reader,
		sortInfo : {
			field : 'salaryID',
			direction : "ASC"
		},
		groupField : 'salaryTypeName'
	});
	store.loadData(myData);

	var sm = new Ext.grid.CheckboxSelectionModel({
		header : '',
		singleSelect : true,
		listeners : {
			rowselect : function(sm, row, rec) {
				// setFromData(rec);
			},
			rowdeselect : function(sm, row, rec) {
				// cleanFromData();
			}
		}
	});

	// create the Grid
	var grid = new Ext.grid.GridPanel({
		store : store,
		id : 'salaryInfoGrid',
		el : 'salaryInfoGridDiv',
		columns : [sm, new Ext.grid.RowNumberer({
			header : '序号',// 自动行号
			width : 35
		}), {
			id : 'salaryID',
			header : "编号",
			width : 100,
			sortable : true,
			dataIndex : 'salaryID'
		}, {
			id : 'salaryName',
			header : "名称",
			width : 120,
			sortable : true,
			dataIndex : 'salaryName'
		}, {
			id : 'salaryTypeName',
			header : "薪酬福利类型",
			hidden : true,
			dataIndex : 'salaryTypeName'
		}, {
			id : 'startDate',
			header : "设立时间",
			width : 120,
			sortable : true,
			dataIndex : 'startDate'
		}, {
			id : 'moneySum',
			header : "数额(元)",
			width : 120,
			sortable : true,
			dataIndex : 'moneySum'
		}, {
			id : 'endDate',
			header : "撤销时间",
			width : 120,
			sortable : true,
			dataIndex : 'endDate'
		}],
		stripeRows : true,
		view : new Ext.grid.GroupingView({
			forceFit : true,
			enableGroupingMenu : false
		}),
		height : 250,
		width : 645,
		title : '薪酬福利信息'
	});

	grid.render();

	// 设立时间
	var startDate = new Ext.form.DateField({
		name : 'register_date',
		width : 155,
		altFormats : 'Y-m-d',
		format : 'Y-m-d'
	});
	startDate.render('startDate'); // div-di

	// 撤销时间
	var endDate = new Ext.form.DateField({
		name : 'register_date',
		width : 155,
		altFormats : 'Y-m-d',
		format : 'Y-m-d'
	});
	endDate.render('endDate'); // div-di
});

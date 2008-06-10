Ext.onReady(function() {
	Ext.QuickTips.init();

	var store = new Ext.data.Store({
		id : 'store',
		proxy : new Ext.data.DWRProxy(
				Com001_DWR_View.getCountryInfoList_Action, false),
		reader : new Ext.data.DWRJsonReader({
			totalProperty : "totalProperty",
			root : "dataList"
		}, new Ext.data.Record.create([{
			name : 'id',
			mapping : 'id'
		}, {
			name : 'name',
			mapping : 'name'
		}, {
			name : 'shortName',
			mapping : 'shortName'
		}, {
			name : 'description',
			mapping : 'description'
		}]))
	});

	var cm = new Ext.grid.ColumnModel([new Ext.grid.RowNumberer({
		header : '序号',// 自动行号
		width : 32
	}), {
		id : 'id',
		header : "编号",
		dataIndex : 'id',
		width : 70
	}, {
		id : 'name',
		header : "名称",
		dataIndex : 'name',
		width : 70
	}, {
		id : 'shortName',
		header : "简称",
		dataIndex : 'shortName',
		width : 40
	}, {
		id : 'description',
		header : "详细描述",
		dataIndex : 'description',
		width : 45
	}]);

	cm.defaultSortable = true;

	var grid = new Ext.grid.GridPanel({
		id : 'countryInfoGrid',
		el : 'countryInfoGrid',
		width : 740,
		height : 330,
		title : '员工信息列表',
		store : store,
		cm : cm,
		stripeRows : true,
		loadMask : true
	});

	grid.render();
	store.load();
});
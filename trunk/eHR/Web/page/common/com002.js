Ext.onReady(function() {
	Ext.QuickTips.init();

	var store = new Ext.data.Store({
		id : 'store',
		// data:dummyData,
		proxy : new Ext.data.DWRProxy(
				Com001_DWR_View.getCountryInfoList_Action),
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
		width : 70
	}, {
		id : 'description',
		header : "详细描述",
		dataIndex : 'description',
		width : 70
	}]);

	var grid = new Ext.grid.GridPanel({
		store : store,
		cm : cm,
		width : 400,
		height : 250,
		el : 'countryInfoGrid'
	});

	grid.render();
	store.load();
});

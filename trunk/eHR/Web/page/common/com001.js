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

	var sm = new Ext.grid.CheckboxSelectionModel({
		header : '',
		singleSelect : true,
		listeners : {
			rowselect : function(sm, row, rec) {
				setFromData(rec);
			}
		}
	});

	var cm = new Ext.grid.ColumnModel([sm, new Ext.grid.RowNumberer({
		header : '序号',// 自动行号
		width : 40
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
		width : 50
	}, {
		id : 'description',
		header : "详细描述",
		dataIndex : 'description',
		width : 130
	}]);

	cm.defaultSortable = true;

	var grid = new Ext.grid.GridPanel({
		id : 'countryInfoGrid',
		el : 'countryInfoGrid',
		width : 385,
		height : 330,
		title : '国家信息列表',
		store : store,
		cm : cm,
		sm : sm,
		stripeRows : true,
		loadMask : true
	});

	grid.render();
	store.load();
});

function setFromData(rec) {
	document.forms['countryCfgForm'].elements['countryCfgForm:id'].value = rec.data.id;
	document.forms['countryCfgForm'].elements['countryCfgForm:name'].value = rec.data.name;
	document.forms['countryCfgForm'].elements['countryCfgForm:shortName'].value = rec.data.shortName;
	document.forms['countryCfgForm'].elements['countryCfgForm:description'].value = rec.data.description;
}
/**
 * @author kaka
 */
Ext.onReady(function() {

	var myData = [['00000001', '居民身份证', ''], ['00000002', '外国人签证', '']];

	// create the data store
	var store = new Ext.data.SimpleStore({
		fields : [{
			name : 'identifieationTypeID'
		}, {
			name : 'identifieationTypeName'
		}, {
			name : 'description'
		}]
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
		id : 'identifieationTypeInfoGrid',
		el : 'identifieationTypeInfoGridDiv',
		columns : [sm, new Ext.grid.RowNumberer({
			header : '序号',// 自动行号
			width : 35
		}), {
			id : 'identifieationTypeID',
			header : "编号",
			width : 80,
			sortable : true,
			dataIndex : 'identifieationTypeID'
		}, {
			id : 'identifieationTypeName',
			header : "名称",
			width : 150,
			sortable : true,
			dataIndex : 'identifieationTypeName'
		}],
		stripeRows : true,
		height : 300,
		width : 300,
		title : '身份标识类型信息'
	});

	grid.render();
});

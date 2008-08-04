Ext.onReady(function() {

	var bd = Ext.getBody();

	// Define the Grid data and create the Grid
	var myData = [['ASZ001', '中国', 'CN', '中华人民共和国'],
			['ASZ002', '日本', 'JP', ''], ['ASZ003', '美国', '', '']];

	var ds = new Ext.data.Store({
		reader : new Ext.data.ArrayReader({}, [{
			name : 'id'
		}, {
			name : 'name'
		}, {
			name : 'shortName'
		}, {
			name : 'description'
		}])
	});
	ds.loadData(myData);

	var colModel = new Ext.grid.ColumnModel([new Ext.grid.RowNumberer({
		header : '序号',// 自动行号
		width : 35
	}), {
		id : 'id',
		header : "编号",
		width : 160,
		sortable : true,
		dataIndex : 'id'
	}, {
		id : 'name',
		header : "名称",
		width : 75,
		sortable : true,
		dataIndex : 'name'
	}, {
		id : 'shortName',
		header : "简称",
		width : 75,
		sortable : true,
		dataIndex : 'shortName'
	}]);

	/*
	 * Here is where we create the Form
	 */
	var gridForm = new Ext.FormPanel({
		id : 'countryInfoForm',
		frame : true,
		labelAlign : 'left',
		title : '国家信息',
		// bodyStyle : 'padding:5px',
		layout : 'column',
		items : [{
			columnWidth : 0.6,
			layout : 'fit',
			items : {
				xtype : 'grid',
				ds : ds,
				cm : colModel,
				stripeRows : true,
				sm : new Ext.grid.RowSelectionModel({
					singleSelect : true,
					listeners : {
						rowselect : function(sm, row, rec) {
							Ext.getCmp("countryInfoForm").getForm()
									.loadRecord(rec);
						}
					}
				}),
				height : 350,
				border : true,
				listeners : {
					render : function(g) {
						g.getSelectionModel().selectRow(0);
					},
					delay : 10
				}
			}
		}, {
			columnWidth : 0.4,
			xtype : 'fieldset',
			labelWidth : 60,
			title : '详细信息',
			defaults : {
				width : 140
			},
			defaultType : 'textfield',
			autoHeight : true,
			border : false,
			style : {
				"margin-left" : "10px",
				"margin-right" : Ext.isIE6
						? (Ext.isStrict ? "-10px" : "-13px")
						: "0"
			},
			items : [{
				fieldLabel : '编号',
				name : 'id'
			}, {
				fieldLabel : '名称',
				name : 'name',
				allowBlank : false
			}, {
				fieldLabel : '简称',
				name : 'shortName',
				allowBlank : false
			}, {
				fieldLabel : '详细',
				name : 'description'
			}]
		}],
		renderTo : 'bodyDiv'
	});
});
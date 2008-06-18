var validCfgForm;
Ext.onReady(function() {
	Ext.QuickTips.init();

	var store = new Ext.data.Store({
		id : 'store',
		proxy : new Ext.data.DWRProxy(
				DDCom001_DWR_View.getCountryInfoList_Action, false),
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
			},
			rowdeselect : function(sm, row, rec) {
				cleanFromData();
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
	addValidation();
	document.forms['countryCfgForm'].elements['countryCfgForm:hid'].value = rec.data.id;
	document.forms['countryCfgForm'].elements['countryCfgForm:id'].value = rec.data.id;
	document.forms['countryCfgForm'].elements['countryCfgForm:name'].value = rec.data.name;
	document.forms['countryCfgForm'].elements['countryCfgForm:shortName'].value = rec.data.shortName;
	document.forms['countryCfgForm'].elements['countryCfgForm:description'].value = rec.data.description;

	validCfgForm.validate();
}

function addValidation() {
	validCfgForm = new Validation('countryCfgForm', {
		immediate : true
	});
	validCfgForm.validate();
}

// 更 新button check
function updateInfoCheck() {
	var grid = Ext.getCmp('countryInfoGrid');
	var gridSelects = grid.getSelections();

	if (gridSelects.length == 0) {
		Ext.Msg.show({
			title : '国家信息',
			msg : '请选择要更新的国家信息!',
			buttons : Ext.Msg.OK,
			minWidth : 200,
			icon : Ext.MessageBox.INFO
		});
		return false;
	} else {
		return true;
	}
}

function cleanFromData() {
	var grid = Ext.getCmp('countryInfoGrid');
	var gridSelects = grid.getSelections();

	if (gridSelects.length == 0) {
		document.forms['countryCfgForm'].elements['countryCfgForm:hid'].value = "";
		document.forms['countryCfgForm'].elements['countryCfgForm:id'].value = "";
		document.forms['countryCfgForm'].elements['countryCfgForm:name'].value = "";
		document.forms['countryCfgForm'].elements['countryCfgForm:shortName'].value = "";
		document.forms['countryCfgForm'].elements['countryCfgForm:description'].value = "";
	}
}

// 复原button
function resetFromData() {
	if (updateInfoCheck()) {
		var grid = Ext.getCmp('countryInfoGrid');
		var record = grid.getSelectionModel().getSelected();

		document.forms['countryCfgForm'].elements['countryCfgForm:hid'].value = record.data.id;
		document.forms['countryCfgForm'].elements['countryCfgForm:id'].value = record.data.id;
		document.forms['countryCfgForm'].elements['countryCfgForm:name'].value = record.data.name;
		document.forms['countryCfgForm'].elements['countryCfgForm:shortName'].value = record.data.shortName;
		document.forms['countryCfgForm'].elements['countryCfgForm:description'].value = record.data.description;
	}

	validCfgForm.validate();
}

// 删除button
function delInfoCheck() {
	var grid = Ext.getCmp('countryInfoGrid');
	var gridSelects = grid.getSelections();

	if (gridSelects.length == 0) {
		Ext.Msg.show({
			title : '国家信息',
			msg : '请选择要删除的国家信息!',
			buttons : Ext.Msg.OK,
			minWidth : 200,
			icon : Ext.MessageBox.INFO
		});
		return false;
	} else {
		Ext.Msg.show({
			title : '国家信息',
			msg : '确定要删除选中的国家信息吗?',
			buttons : Ext.Msg.OKCANCEL,
			minWidth : 200,
			fn : function(btn) {
				if (btn == 'ok') {
					document.forms['countryCfgForm'].elements['countryCfgForm:delCountryInfoBtn']
							.click();
				}
			},
			icon : Ext.MessageBox.QUESTION
		});
	}
}

/* 打开添加国家信息窗口 */
function openAddCountryInfoWindow() {
	document.forms['addCountryInfoWindow:countryAddForm'].elements['addCountryInfoWindow:countryAddForm:name'].value = '';
	document.forms['addCountryInfoWindow:countryAddForm'].elements['addCountryInfoWindow:countryAddForm:shortName'].value = '';
	document.forms['addCountryInfoWindow:countryAddForm'].elements['addCountryInfoWindow:countryAddForm:description'].value = '';
	if (validAddForm != null) {
		validAddForm.validate();
	}
	Richfaces.showModalPanel('addCountryInfoView');
	return false;
}

/* 关闭添加国家信息窗口 */
function closeAddCountryInfoWindow() {
	Richfaces.hideModalPanel('addCountryInfoView');
	return false;
}
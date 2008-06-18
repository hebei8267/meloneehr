var validCfgForm;

Ext.onReady(function() {
	Ext.QuickTips.init();

	var store = new Ext.data.Store({
		id : 'store',
		proxy : new Ext.data.DWRProxy(
				DDOrgType001_DWR_View.getOrganizationTypeInfoList_Action, false),
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
		id : 'description',
		header : "详细描述",
		dataIndex : 'description',
		width : 130
	}]);

	cm.defaultSortable = true;

	var grid = new Ext.grid.GridPanel({
		id : 'orgTypeInfoGrid',
		el : 'orgTypeInfoGrid',
		width : 335,
		height : 330,
		title : '组织类型信息列表',
		store : store,
		cm : cm,
		sm : sm,
		stripeRows : true,
		loadMask : true
	});

	grid.render();
	store.load();
});

function addValidation() {
	validCfgForm = new Validation('orgTypeCfgForm', {
		immediate : true
	});
	validCfgForm.validate();
}

function setFromData(rec) {
	addValidation();
	document.forms['orgTypeCfgForm'].elements['orgTypeCfgForm:hid'].value = rec.data.id;
	document.forms['orgTypeCfgForm'].elements['orgTypeCfgForm:id'].value = rec.data.id;
	document.forms['orgTypeCfgForm'].elements['orgTypeCfgForm:name'].value = rec.data.name;
	document.forms['orgTypeCfgForm'].elements['orgTypeCfgForm:description'].value = rec.data.description;

	validCfgForm.validate();
}

function cleanFromData() {
	var grid = Ext.getCmp('orgTypeInfoGrid');
	var gridSelects = grid.getSelections();

	if (gridSelects.length == 0) {
		document.forms['orgTypeCfgForm'].elements['orgTypeCfgForm:hid'].value = "";
		document.forms['orgTypeCfgForm'].elements['orgTypeCfgForm:id'].value = "";
		document.forms['orgTypeCfgForm'].elements['orgTypeCfgForm:name'].value = "";
		document.forms['orgTypeCfgForm'].elements['orgTypeCfgForm:description'].value = "";
	}
}

// 复原button
function resetFromData() {
	if (updateInfoCheck()) {
		var grid = Ext.getCmp('orgTypeInfoGrid');
		var record = grid.getSelectionModel().getSelected();

		document.forms['orgTypeCfgForm'].elements['orgTypeCfgForm:hid'].value = record.data.id;
		document.forms['orgTypeCfgForm'].elements['orgTypeCfgForm:id'].value = record.data.id;
		document.forms['orgTypeCfgForm'].elements['orgTypeCfgForm:name'].value = record.data.name;
		document.forms['orgTypeCfgForm'].elements['orgTypeCfgForm:description'].value = record.data.description;
	}

	validCfgForm.validate();
}

// 更 新button check
function updateInfoCheck() {
	var grid = Ext.getCmp('orgTypeInfoGrid');
	var gridSelects = grid.getSelections();

	if (gridSelects.length == 0) {
		Ext.Msg.show({
			title : '组织类型信息',
			msg : '请选择要更新的组织类型信息!',
			buttons : Ext.Msg.OK,
			minWidth : 200,
			icon : Ext.MessageBox.INFO
		});
		return false;
	} else {
		return true;
	}
}

// 删除button
function delInfoCheck() {
	var grid = Ext.getCmp('orgTypeInfoGrid');
	var gridSelects = grid.getSelections();

	if (gridSelects.length == 0) {
		Ext.Msg.show({
			title : '组织类型信息',
			msg : '请选择要删除的组织类型信息!',
			buttons : Ext.Msg.OK,
			minWidth : 200,
			icon : Ext.MessageBox.INFO
		});
		return false;
	} else {
		Ext.Msg.show({
			title : '组织类型信息',
			msg : '确定要删除选中的组织类型信息吗?',
			buttons : Ext.Msg.OKCANCEL,
			minWidth : 200,
			fn : function(btn) {
				if (btn == 'ok') {
					document.forms['orgTypeCfgForm'].elements['orgTypeCfgForm:delOrgTypeInfoBtn']
							.click();
				}
			},
			icon : Ext.MessageBox.QUESTION
		});
	}
}

function openAddOrgTypeInfoWindow() {
	document.forms['addOrgTypeInfoWindow:orgTypeAddForm'].elements['addOrgTypeInfoWindow:orgTypeAddForm:name'].value = '';
	document.forms['addOrgTypeInfoWindow:orgTypeAddForm'].elements['addOrgTypeInfoWindow:orgTypeAddForm:description'].value = '';
	validCfgForm.validate();
	Richfaces.showModalPanel('addOrgTypeInfoView');
	return false;
}

function closeAddOrgTypeInfoWindow() {
	Richfaces.hideModalPanel('addOrgTypeInfoView');
	return false;
}

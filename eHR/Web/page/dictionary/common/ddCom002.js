var validCfgForm;
var validAddForm;

Ext.onReady(function() {
	Ext.QuickTips.init();

	var store = new Ext.data.Store({
		id : 'store',
		proxy : new Ext.data.DWRProxy(
				DDCom002_DWR_View.getNationInfoList_Action, false),
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
		id : 'nationInfoGrid',
		el : 'nationInfoGrid',
		width : 335,
		height : 330,
		title : '民族信息列表',
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
	document.forms['nationCfgForm'].elements['nationCfgForm:hid'].value = rec.data.id;
	document.forms['nationCfgForm'].elements['nationCfgForm:id'].value = rec.data.id;
	document.forms['nationCfgForm'].elements['nationCfgForm:name'].value = rec.data.name;
	document.forms['nationCfgForm'].elements['nationCfgForm:description'].value = rec.data.description;

	validCfgForm.validate();
}

function addValidation() {
	validCfgForm = new Validation('nationCfgForm', {
		immediate : true
	});
	validCfgForm.validate();
}

// 更 新button check
function updateInfoCheck() {
	var grid = Ext.getCmp('nationInfoGrid');
	var gridSelects = grid.getSelections();

	if (gridSelects.length == 0) {
		Ext.Msg.show({
			title : '民族信息',
			msg : '请选择要更新的民族信息!',
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
	var grid = Ext.getCmp('nationInfoGrid');
	var gridSelects = grid.getSelections();

	if (gridSelects.length == 0) {
		document.forms['nationCfgForm'].elements['nationCfgForm:hid'].value = "";
		document.forms['nationCfgForm'].elements['nationCfgForm:id'].value = "";
		document.forms['nationCfgForm'].elements['nationCfgForm:name'].value = "";
		document.forms['nationCfgForm'].elements['nationCfgForm:description'].value = "";
	}
}

// 复原button
function resetFromData() {
	if (updateInfoCheck()) {
		var grid = Ext.getCmp('nationInfoGrid');
		var record = grid.getSelectionModel().getSelected();

		document.forms['nationCfgForm'].elements['nationCfgForm:hid'].value = record.data.id;
		document.forms['nationCfgForm'].elements['nationCfgForm:id'].value = record.data.id;
		document.forms['nationCfgForm'].elements['nationCfgForm:name'].value = record.data.name;
		document.forms['nationCfgForm'].elements['nationCfgForm:description'].value = record.data.description;
	}

	validCfgForm.validate();
}

// 删除button
function delInfoCheck() {
	var grid = Ext.getCmp('nationInfoGrid');
	var gridSelects = grid.getSelections();

	if (gridSelects.length == 0) {
		Ext.Msg.show({
			title : '民族信息',
			msg : '请选择要删除的民族信息!',
			buttons : Ext.Msg.OK,
			minWidth : 200,
			icon : Ext.MessageBox.INFO
		});
		return false;
	} else {
		Ext.Msg.show({
			title : '民族信息',
			msg : '确定要删除选中的民族信息吗?',
			buttons : Ext.Msg.OKCANCEL,
			minWidth : 200,
			fn : function(btn) {
				if (btn == 'ok') {
					document.forms['nationCfgForm'].elements['nationCfgForm:delNationInfoBtn']
							.click();
				}
			},
			icon : Ext.MessageBox.QUESTION
		});
	}
}

/* 打开添加民族信息窗口 */
function openAddNationInfoWindow() {
	document.forms['addNationInfoWindow:nationAddForm'].elements['addNationInfoWindow:nationAddForm:name'].value = '';
	document.forms['addNationInfoWindow:nationAddForm'].elements['addNationInfoWindow:nationAddForm:description'].value = '';

	if (validAddForm != null) {
		validAddForm.validate();
	}

	Richfaces.showModalPanel('addNationInfoView');
	return false;
}

/* 关闭添加民族信息窗口 */
function closeAddNationInfoWindow() {
	Richfaces.hideModalPanel('addNationInfoView');
	return false;
}
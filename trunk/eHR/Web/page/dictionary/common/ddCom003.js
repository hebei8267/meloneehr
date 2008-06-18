var validCfgForm;
var dataStore;

Ext.onReady(function() {
	initNativeplaceInfoTreeStyle();

	var treeNodeData = document.forms['nativeplaceCfgForm'].elements['nativeplaceCfgForm:jsonNodeData'].value;

	dataStore = new Ext.data.Store({
		id : 'ds',
		proxy : new Ext.data.MemoryProxy(JSON.parse(treeNodeData)),
		reader : new Ext.data.JsonReader({
			totalProperty : "totalProperty",
			root : "dataList",
			id : "id"
		}, new Ext.data.Record.create([{
			name : 'pid',
			mapping : 'pid'
		}, {
			name : 'pname',
			mapping : 'pname'
		}, {
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
	dataStore.load();
});

function needSelectedCheck() {
	if (document.forms['nativeplaceCfgForm'].elements['nativeplaceCfgForm:id'].value == "") {
		Ext.Msg.show({
			title : '籍贯信息',
			msg : '请选择要更新的籍贯信息!',
			buttons : Ext.Msg.OK,
			minWidth : 200,
			icon : Ext.MessageBox.INFO
		});
		return false;
	}
	return true;
}

function updateInfoCheck() {
	if (!needSelectedCheck()) {
		return false;
	}

	var _result = validCfgForm.validate();
	if (_result == false) {
		return false;
	} else {
		return true;
	}

}

function initNativeplaceInfoTreeStyle() {
	// title
	var titleDiv = Ext.get("treeTitleDiv");
	titleDiv.setWidth(340);
	// tree
	var treeDiv = Ext.get("nativeplaceInfoTreeDiv");
	treeDiv.setWidth(340);
	treeDiv.setHeight(330);
	treeDiv.setStyle("overflow", "auto");
}

function setFromData(id) {
	nodeSelected();

	addValidation();

	var record = dataStore.getById(id);

	document.forms['nativeplaceCfgForm'].elements['nativeplaceCfgForm:pid'].value = record.data.pid;
	document.forms['nativeplaceCfgForm'].elements['nativeplaceCfgForm:rpname'].value = record.data.pname;
	document.forms['nativeplaceCfgForm'].elements['nativeplaceCfgForm:pname'].value = record.data.pname;
	document.forms['nativeplaceCfgForm'].elements['nativeplaceCfgForm:rid'].value = record.data.id;
	document.forms['nativeplaceCfgForm'].elements['nativeplaceCfgForm:id'].value = record.data.id;
	document.forms['nativeplaceCfgForm'].elements['nativeplaceCfgForm:name'].value = record.data.name;
	document.forms['nativeplaceCfgForm'].elements['nativeplaceCfgForm:description'].value = record.data.description;

	document.forms['nativeplaceCfgForm'].elements['nativeplaceCfgForm:h_pid'].value = record.data.pid;
	document.forms['nativeplaceCfgForm'].elements['nativeplaceCfgForm:h_pname'].value = record.data.pname;
	document.forms['nativeplaceCfgForm'].elements['nativeplaceCfgForm:h_id'].value = record.data.id;
	document.forms['nativeplaceCfgForm'].elements['nativeplaceCfgForm:h_name'].value = record.data.name;
	document.forms['nativeplaceCfgForm'].elements['nativeplaceCfgForm:h_description'].value = record.data.description;
}

function addValidation() {
	validCfgForm = new Validation('nativeplaceCfgForm', {
		immediate : true
	});
}

function resetFromData() {
	if (!needSelectedCheck()) {
		return false;
	}

	document.forms['nativeplaceCfgForm'].elements['nativeplaceCfgForm:pid'].value = document.forms['nativeplaceCfgForm'].elements['nativeplaceCfgForm:h_pid'].value;
	document.forms['nativeplaceCfgForm'].elements['nativeplaceCfgForm:rpname'].value = document.forms['nativeplaceCfgForm'].elements['nativeplaceCfgForm:h_pname'].value;
	document.forms['nativeplaceCfgForm'].elements['nativeplaceCfgForm:pname'].value = document.forms['nativeplaceCfgForm'].elements['nativeplaceCfgForm:h_pname'].value;
	document.forms['nativeplaceCfgForm'].elements['nativeplaceCfgForm:rid'].value = document.forms['nativeplaceCfgForm'].elements['nativeplaceCfgForm:h_id'].value;
	document.forms['nativeplaceCfgForm'].elements['nativeplaceCfgForm:id'].value = document.forms['nativeplaceCfgForm'].elements['nativeplaceCfgForm:h_id'].value;
	document.forms['nativeplaceCfgForm'].elements['nativeplaceCfgForm:name'].value = document.forms['nativeplaceCfgForm'].elements['nativeplaceCfgForm:h_name'].value;
	document.forms['nativeplaceCfgForm'].elements['nativeplaceCfgForm:description'].value = document.forms['nativeplaceCfgForm'].elements['nativeplaceCfgForm:h_description'].value;

}

function delInfoCheck() {
	if (document.forms['nativeplaceCfgForm'].elements['nativeplaceCfgForm:id'].value == "") {
		Ext.Msg.show({
			title : '籍贯信息',
			msg : '请选择要删除的籍贯信息!',
			buttons : Ext.Msg.OK,
			minWidth : 200,
			icon : Ext.MessageBox.INFO
		});
		return false;
	}

	Ext.Msg.show({
		title : '籍贯信息',
		msg : '确定要删除选中的籍贯信息吗?',
		buttons : Ext.Msg.OKCANCEL,
		minWidth : 200,
		fn : function(btn) {
			if (btn == 'ok') {
				document.forms['nativeplaceCfgForm'].elements['nativeplaceCfgForm:delNativeplaceInfoBtn']
						.click();
			}
		},
		icon : Ext.MessageBox.QUESTION
	});
}

function nodeSelected() {
	document.getElementsByClassName("nodeSelected").each(function(elm) {
		elm.removeClassName("nodeSelected");
	});;

	getEventSrcElement().addClassName("nodeSelected");
}

function getEventSrcElement() {
	var _evt = getEvent();
	var element = _evt.srcElement || _evt.target;
	return element;
}

function getEvent() { // 同时兼容IE和FF的写法
	if (document.all) {
		return window.event;
	}
	func = getEvent.caller;
	while (func != null) {
		var arg0 = func.arguments[0];
		if (arg0) {
			if ((arg0.constructor == Event || arg0.constructor == MouseEvent)
					|| (typeof(arg0) == "object" && arg0.preventDefault && arg0.stopPropagation)) {
				return arg0;
			}
		}
		func = func.caller;
	}
	return null;
}

function openAddNativeplaceWindow() {
	if (document.forms['nativeplaceCfgForm'].elements['nativeplaceCfgForm:id'].value == "") {
		document.forms['addNativeplaceInfoWindow:nativeplaceAddForm'].elements['addNativeplaceInfoWindow:nativeplaceAddForm:pid'].value = '00000001';
		document.forms['addNativeplaceInfoWindow:nativeplaceAddForm'].elements['addNativeplaceInfoWindow:nativeplaceAddForm:rpname'].value = '籍贯信息';
	} else {
		document.forms['addNativeplaceInfoWindow:nativeplaceAddForm'].elements['addNativeplaceInfoWindow:nativeplaceAddForm:pid'].value = document.forms['nativeplaceCfgForm'].elements['nativeplaceCfgForm:h_id'].value;
		document.forms['addNativeplaceInfoWindow:nativeplaceAddForm'].elements['addNativeplaceInfoWindow:nativeplaceAddForm:rpname'].value = document.forms['nativeplaceCfgForm'].elements['nativeplaceCfgForm:h_name'].value;
	}

	document.forms['addNativeplaceInfoWindow:nativeplaceAddForm'].elements['addNativeplaceInfoWindow:nativeplaceAddForm:name'].value = '';
	document.forms['addNativeplaceInfoWindow:nativeplaceAddForm'].elements['addNativeplaceInfoWindow:nativeplaceAddForm:description'].value = '';

	Richfaces.showModalPanel('addNativeplaceInfoView');
	return false;
}

function closeAddNativeplaceInfoWindow() {
	Richfaces.hideModalPanel('addNativeplaceInfoView');
	return false;
}

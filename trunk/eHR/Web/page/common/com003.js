var validCfgForm;;

Ext.onReady(function() {
	initNativeplaceInfoTreeStyle();

	addValidation();
});

function addValidation() {
	validCfgForm = new Validation('nativeplaceCfgForm', {
		immediate : false,
		onSubmit : false
	});
}

function updateInfoCheck() {
	if(document.forms['nativeplaceCfgForm'].elements['nativeplaceCfgForm:id'].value == "NP00000000"){
		Ext.Msg.show({
			title : '籍贯信息',
			msg : '不能修改默认根节点信息!',
			buttons : Ext.Msg.OK,
			minWidth : 200,
			icon : Ext.MessageBox.ERROR
		});
		return false;
	}
	if (document.forms['nativeplaceCfgForm'].elements['nativeplaceCfgForm:id'].value == "") {
		Ext.Msg.show({
			title : '籍贯信息',
			msg : '请选择要更新的籍贯信息!',
			buttons : Ext.Msg.OK,
			minWidth : 200,
			icon : Ext.MessageBox.INFO
		});
		return false;
	} else {
		var _result = validCfgForm.validate();
		if (_result == false) {
			return false;
		} else {
			return true;
		}
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

function setFromData(pid, pname, id, name, description) {
	nodeSelected();

	document.forms['nativeplaceCfgForm'].elements['nativeplaceCfgForm:pid'].value = pid;
	document.forms['nativeplaceCfgForm'].elements['nativeplaceCfgForm:rpname'].value = pname;
	document.forms['nativeplaceCfgForm'].elements['nativeplaceCfgForm:pname'].value = pname;
	document.forms['nativeplaceCfgForm'].elements['nativeplaceCfgForm:rid'].value = id;
	document.forms['nativeplaceCfgForm'].elements['nativeplaceCfgForm:id'].value = id;
	document.forms['nativeplaceCfgForm'].elements['nativeplaceCfgForm:name'].value = name;
	document.forms['nativeplaceCfgForm'].elements['nativeplaceCfgForm:description'].value = description;

	document.forms['nativeplaceCfgForm'].elements['nativeplaceCfgForm:h_pid'].value = pid;
	document.forms['nativeplaceCfgForm'].elements['nativeplaceCfgForm:h_pname'].value = pname;
	document.forms['nativeplaceCfgForm'].elements['nativeplaceCfgForm:h_id'].value = id;
	document.forms['nativeplaceCfgForm'].elements['nativeplaceCfgForm:h_name'].value = name;
	document.forms['nativeplaceCfgForm'].elements['nativeplaceCfgForm:h_description'].value = description;
}

function resetFromData() {
	if (document.forms['nativeplaceCfgForm'].elements['nativeplaceCfgForm:id'].value == "") {
		Ext.Msg.show({
			title : '籍贯信息',
			msg : '请选择要更新的籍贯信息!',
			buttons : Ext.Msg.OK,
			minWidth : 200,
			icon : Ext.MessageBox.INFO
		});
	} else {
		document.forms['nativeplaceCfgForm'].elements['nativeplaceCfgForm:pid'].value = document.forms['nativeplaceCfgForm'].elements['nativeplaceCfgForm:h_pid'].value;
		document.forms['nativeplaceCfgForm'].elements['nativeplaceCfgForm:rpname'].value = document.forms['nativeplaceCfgForm'].elements['nativeplaceCfgForm:h_pname'].value;
		document.forms['nativeplaceCfgForm'].elements['nativeplaceCfgForm:pname'].value = document.forms['nativeplaceCfgForm'].elements['nativeplaceCfgForm:h_pname'].value;
		document.forms['nativeplaceCfgForm'].elements['nativeplaceCfgForm:rid'].value = document.forms['nativeplaceCfgForm'].elements['nativeplaceCfgForm:h_id'].value;
		document.forms['nativeplaceCfgForm'].elements['nativeplaceCfgForm:id'].value = document.forms['nativeplaceCfgForm'].elements['nativeplaceCfgForm:h_id'].value;
		document.forms['nativeplaceCfgForm'].elements['nativeplaceCfgForm:name'].value = document.forms['nativeplaceCfgForm'].elements['nativeplaceCfgForm:h_name'].value;
		document.forms['nativeplaceCfgForm'].elements['nativeplaceCfgForm:description'].value = document.forms['nativeplaceCfgForm'].elements['nativeplaceCfgForm:h_description'].value;
	}
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
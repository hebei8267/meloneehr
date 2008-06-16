Ext.onReady(function() {
	initNativeplaceInfoTreeStyle();
});

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
	document.forms['nativeplaceCfgForm'].elements['nativeplaceCfgForm:pid'].value = pid;
	document.forms['nativeplaceCfgForm'].elements['nativeplaceCfgForm:rpname'].value = pname;
	document.forms['nativeplaceCfgForm'].elements['nativeplaceCfgForm:pname'].value = pname;
	document.forms['nativeplaceCfgForm'].elements['nativeplaceCfgForm:rid'].value = id;
	document.forms['nativeplaceCfgForm'].elements['nativeplaceCfgForm:id'].value = id;
	document.forms['nativeplaceCfgForm'].elements['nativeplaceCfgForm:name'].value = name;
	document.forms['nativeplaceCfgForm'].elements['nativeplaceCfgForm:description'].value = description;
}

function nodeSelected() {
	var _id = getEventSrcElement().id;

	var _index = _id.indexOf("::");
	var nodeID = _id.substring(0, _index + 2) + "node";

	if (document.all) {
		document.getElementById(nodeID).click();
	} else {
		var evt = document.createEvent("MouseEvents");
		evt.initEvent("click", false, false);
		document.getElementById(nodeID).dispatchEvent(evt);
	} 
}

function getEventSrcElement() {
	var evt = getEvent();
	var element = evt.srcElement || evt.target;
	return element;
}

function getEvent() { // 同时兼容IE和FF的写法
	if (document.all)
		return window.event;
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
var validCfgForm;
var dataStore;

Ext.onReady(function() {
	initOrganizationInfoTreeStyle();

	var treeNodeData = document.forms['organizationCfgForm'].elements['organizationCfgForm:jsonNodeData'].value;

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
			name : 'startDate',
			mapping : 'startDate'
		}, {
			name : 'name',
			mapping : 'name'
		}, {
			name : 'shortName',
			mapping : 'shortName'
		}, {
			name : 'address',
			mapping : 'address'
		}, {
			name : 'telephone',
			mapping : 'telephone'
		}, {
			name : 'fax',
			mapping : 'fax'
		}, {
			name : 'description',
			mapping : 'description'
		}, {
			name : 'endDate',
			mapping : 'endDate'
		}, {
			name : 'endDesc',
			mapping : 'endDesc'
		}, {
			name : 'organizationTypeID',
			mapping : 'organizationTypeID'
		}, {
			name : 'countryID',
			mapping : 'countryID'
		}]))
	});
	dataStore.load();
});

function initOrganizationInfoTreeStyle() {
	// title
	var titleDiv = Ext.get("treeTitleDiv");
	titleDiv.setWidth(340);
	// tree
	var treeDiv = Ext.get("organizationInfoTreeDiv");
	treeDiv.setWidth(340);
	treeDiv.setHeight(330);
	treeDiv.setStyle("overflow", "auto");
}

function setFromData(id) {
	nodeSelected();

	addValidation();

	var record = dataStore.getById(id);

	document.forms['organizationCfgForm'].elements['organizationCfgForm:pid'].value = record.data.pid;
	document.forms['organizationCfgForm'].elements['organizationCfgForm:pname'].value = record.data.pname;
	document.forms['organizationCfgForm'].elements['organizationCfgForm:rid'].value = record.data.id;
	document.forms['organizationCfgForm'].elements['organizationCfgForm:id'].value = record.data.id;
	document.forms['organizationCfgForm'].elements['organizationCfgForm:name'].value = record.data.name;
	document.forms['organizationCfgForm'].elements['organizationCfgForm:description'].value = record.data.description;
}

function addValidation() {
	validCfgForm = new Validation('organizationCfgForm', {
		immediate : true
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
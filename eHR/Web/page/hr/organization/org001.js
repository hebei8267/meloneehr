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
			name : 'name',
			mapping : 'name'
		}, {
			name : 'description',
			mapping : 'description'
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
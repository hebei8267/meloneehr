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
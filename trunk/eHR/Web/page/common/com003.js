Ext.onReady(function(){
	initNativeplaceInfoTreeStyle();
});

function initNativeplaceInfoTreeStyle(){
    //tree
	var treeDiv = Ext.get("nativeplaceInfoTreeDiv");
    treeDiv.setWidth(340);
    treeDiv.setHeight(330);
    treeDiv.setStyle("overflow", "auto");
}
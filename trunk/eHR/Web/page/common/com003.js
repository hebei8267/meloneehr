Ext.onReady(function(){
	initNativeplaceInfoTreeStyle();
});

function initNativeplaceInfoTreeStyle(){
    //tree
	var treeDiv = Ext.get("nativeplaceInfoTreeDiv");
    treeDiv.setWidth(340);
    treeDiv.setHeight(330);
    treeDiv.setStyle("overflow", "auto");
    treeDiv.setStyle("border", "1px solid");
    treeDiv.setStyle("border-color", "#E6E6FA #A9A9A9 #A9A9A9 #E6E6FA");
}
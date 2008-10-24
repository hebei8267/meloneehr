var _space = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
var _newLine = "<br>"
function showMessageBox(msg){
	Ext.MessageBox.alert("Melone-eHR 人力资源管理", msg);
}

function getNeedInputMsg(itemName){
	return _newLine + _space + "请输入[<b><font color='red'>" + itemName + "</font></b>]!" + _space;
}
var _space = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
var _newLine = "<br>"
function showMessageBox(msg){
	Ext.MessageBox.alert("Melone-eHR 人力资源管理", msg);
}

function getNeedInputMsg(itemName){
	return _newLine + _space + "请输入[<b><font color='red'>" + itemName + "</font></b>]!" + _space;
}

function getNeedSameInputMsg(itemName1, itemName2){
	return _newLine + _space + "输入的[<b><font color='red'>" + itemName1 + "</font></b>]和[<b><font color='red'>" + itemName2 + "</font></b>]不一样!" + _space;
}

function getSystemCommunicationMsg(){
	return "和服务通信发生错误,请稍候再试!";
}
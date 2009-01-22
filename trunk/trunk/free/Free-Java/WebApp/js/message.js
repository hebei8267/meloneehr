function getCommunicationErrorMsg() {
	return "和服务通信时发生错误,请稍候再试！";
}

function getNeedSameInputErrorMsg(itemName1, itemName2) {
	return "输入的[" + itemName1 + "]和[" + itemName2 + "]不一样！";
}

function getNeedSelectedItemErrorMsg(itemName) {
	return "未选择[" + itemName + "]！";
}

function getNeedSelectedItemErrorMsg(itemName, detail) {
	return "未选择[" + itemName + "]！<br><br><b><font color='red'>注意: " + detail
			+ "</font></b>";
}

function getNoChangeErrorMsg() {
	return "未编辑任何数据！";
}
// ************************************************
// Extjs error message
// ************************************************
function getBlankText() {
	return "该输入项为必输项";
}
function getNeedSelectedItem(itemName) {
	return "未选择" + itemName;
}
function getNumText() {
	return "该输入项只能包数字";
}
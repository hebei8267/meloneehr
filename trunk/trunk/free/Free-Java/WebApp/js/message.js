// ************************************************
// tip message
// ************************************************
function getDelConfirmTipMsg() {
	return "确认要删除所选记录？";
}
// ************************************************
// error message
// ************************************************
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
	if (itemName.length >= detail.length) {
		var i1 = itemName.length - detail.length + 1;
		for (var i2 = 0; i2 < i1; i2++) {
			detail += "&nbsp;&nbsp;&nbsp;&nbsp;";
		}
	}
	return "未选择[" + itemName + "]！<br><br><b><font color='red'>注意: " + detail
			+ "</font></b>";
}

function getNoChangeErrorMsg() {
	return "未编辑任何数据！";
}

function getNeedOneSelectedErrorMsg(itemName) {
	return "最少要选择一条[" + itemName + "]记录！";
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
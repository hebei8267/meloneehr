function getCommunicationErrorMsg() {
	return "和服务通信时发生错误,请稍候再试!";
}

function getNeedSameInputErrorMsg(itemName1, itemName2){
	return "输入的[" + itemName1 + "]和[" + itemName2 + "]不一样!";
}

function getNeedSelectedItemErrorMsg(itemName){
	return "未选择[" + itemName + "]!";
}

function getBlankText(){
	return "该输入项为必输项";
}
function getNeedSelectedItem(itemName){
	return "未选择" + itemName;
}
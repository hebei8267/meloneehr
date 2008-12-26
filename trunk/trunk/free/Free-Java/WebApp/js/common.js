/**
 * 校验指定表单内的Extjs元素
 * 
 * @param formID
 *            表单ID
 */
function formExtCmpValidate(formID) {
	var elements = Form.getElements(formID);
	var valid = true;

	$A(elements).any(function(element) {
		var _extCmp = Ext.getCmp(element.id);
		if (_extCmp != null && !_extCmp.disabled) {
			if (!_extCmp.isValid()) {
				valid = false;
			}
		}
	});
	return valid;
}

/**
 * 重置指定表单内的Extjs元素
 * 
 * @param formID
 *            表单ID
 */
function formExtCmpReset(formID) {
	var elements = Form.getElements(formID);

	$A(elements).any(function(element) {
		var _extCmp = Ext.getCmp(element.id);
		if (_extCmp != null && !_extCmp.disabled) {
			_extCmp.reset();
		}
	});
}

/**
 * 根据指定名称取得Radio的选中值
 * 
 * @param radioName
 *            Radio名称
 */
function getRadioValueByName(radioName) {
	var _value = null;
	$$('input[type="radio"][name=' + radioName + ']').select(function(node) {
		return node.checked;
	}).each(function(node) {
		_value = node.value;
	});
	return _value;
}
/**
 * 默认ajax请求失败处理
 */
function defaultAjaxRequestFailure(result, request) {
	showMessageBox(getCommunicationErrorMsg());
}

var _title = "Freedom&Melon";
function showMessageBox(msg) {
	Ext.MessageBox.alert(_title, msg);
}
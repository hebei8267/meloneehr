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
 * 根据指定名称设置Radio的选中值
 * 
 * @param radioName
 *            Radio名称
 * @param radioValue
 *            Radio值
 */
function setRadioValueByName(radioName, radioValue) {
	var objs = document.getElementsByName(radioName);

	for (var i = 0; i < objs.length; i++) {
		var _v1 = String(objs[i].value);
		var _v2 = String(radioValue);

		if (_v1 == _v2) {
			objs[i].checked = true;
			break;
		}
	}
}

function formAjaxSubmit(_url, _params, _fnSuccess, _fnFailure) {
	Ext.Ajax.request({
		url : _url,
		method : 'post',
		failure : defaultAjaxRequestFailure,
		success : function(result, request) {
			var oResult = eval("(" + result.responseText + ")");

			if (oResult.processResult) {// 成功
				if (typeof _fnSuccess == "function") {
					_fnSuccess();
				}
			} else {// 失败
				// Ajax系统定式 start
				if (!oResult.processResult && oResult.sessionTimeOut) {
					$("systemErrorForm").target = "_top";
					$("systemErrorForm").submit();
					return;
				}
				showMessageBox(oResult.resultMsg);
				// Ajax系统定式 end

				if (typeof _fnFailure == "function") {
					_fnFailure();
				}
			}
		},
		params : _params
	});
}

/**
 * 默认ajax请求失败处理
 */
function defaultAjaxRequestFailure(result, request) {
	showMessageBox(getCommunicationErrorMsg());
}

var _title = "Freedom&Melon";
function showMessageBox(msgStr) {
	Ext.Msg.show({
		title : _title,
		msg : msgStr,
		buttons : Ext.Msg.OK,
		icon : Ext.MessageBox.INFO
	});
}

function showMessageBox(msgStr, fun) {
	Ext.Msg.show({
		title : _title,
		msg : msgStr,
		buttons : Ext.Msg.OK,
		fn : fun,
		icon : Ext.MessageBox.INFO
	});
}

function showConfirm(msgStr, fun) {
	Ext.MessageBox.confirm(_title, msgStr, fun);
}
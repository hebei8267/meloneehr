//--------------------------------------------------
// 销售按钮点击处理
//--------------------------------------------------
function btn1_click() {
	var _inputBarCode = $("#_inputBarCode").val();
	$("#_inputBarCode").val(_inputBarCode + 1);

	$("#_inputBarCode").focus();
	// $("#_inputBarCode").blur();
}
function btn2_click() {
	var _inputBarCode = $("#_inputBarCode").val();
	$("#_inputBarCode").val(_inputBarCode + 2);

	$("#_inputBarCode").focus();
	// $("#_inputBarCode").blur();
}
function btn3_click() {
	var _inputBarCode = $("#_inputBarCode").val();
	$("#_inputBarCode").val(_inputBarCode + 3);

	$("#_inputBarCode").focus();
	// $("#_inputBarCode").blur();
}
function btn4_click() {
	var _inputBarCode = $("#_inputBarCode").val();
	$("#_inputBarCode").val(_inputBarCode + 4);

	$("#_inputBarCode").focus();
	// $("#_inputBarCode").blur();
}
function btn5_click() {
	var _inputBarCode = $("#_inputBarCode").val();
	$("#_inputBarCode").val(_inputBarCode + 5);

	$("#_inputBarCode").focus();
	// $("#_inputBarCode").blur();
}
function btn6_click() {
	var _inputBarCode = $("#_inputBarCode").val();
	$("#_inputBarCode").val(_inputBarCode + 6);

	$("#_inputBarCode").focus();
	// $("#_inputBarCode").blur();
}
function btn7_click() {
	var _inputBarCode = $("#_inputBarCode").val();
	$("#_inputBarCode").val(_inputBarCode + 7);

	$("#_inputBarCode").focus();
	// $("#_inputBarCode").blur();
}
function btn8_click() {
	var _inputBarCode = $("#_inputBarCode").val();
	$("#_inputBarCode").val(_inputBarCode + 8);

	$("#_inputBarCode").focus();
	// $("#_inputBarCode").blur();
}
function btn9_click() {
	var _inputBarCode = $("#_inputBarCode").val();
	$("#_inputBarCode").val(_inputBarCode + 9);

	$("#_inputBarCode").focus();
	// $("#_inputBarCode").blur();
}
function btn0_click() {
	var _inputBarCode = $("#_inputBarCode").val();
	$("#_inputBarCode").val(_inputBarCode + 0);

	$("#_inputBarCode").focus();
	// $("#_inputBarCode").blur();
}
function btnDel_click() {
	var _inputBarCode = $("#_inputBarCode").val();
	if (_inputBarCode.length > 0) {
		$("#_inputBarCode").val(
				_inputBarCode.substring(0, _inputBarCode.length - 1));

		$("#_inputBarCode").focus();
		// $("#_inputBarCode").blur();
	}
}
// --------------------------------------------------
// 取得产品信息对象从product.json.js文件中
// --------------------------------------------------
function _getObjByKey(obj, key, val) {
	var reObj = false;
	$.each(obj, function() {
		var testObject = this;
		$.each(testObject, function(k, v) {
			if (val == v && k == key) {
				reObj = testObject;
			}
		});
	});
	return reObj;
}
// --------------------------------------------------
// 绘制商品细节
// --------------------------------------------------
function _processProductDetailTable() {
	var _product = _getObjByKey(_products, "barCode", $('#_inputBarCode').val());
	if (_product) {
		drawProductDetail(_product);
		$('#_productDetail').show();
		$("#_productNum").select();
		$("#_productNum").focus();
		return _product;
	} else {
		$('#_productDetail').hide();
		return null;
	}	
}
function numPlus() {
	var _num = $("#_productNum").val();
	_num++;
	$("#_productNum").val(_num);
	$("#_productNum").select();
	$("#_productNum").focus();
}
function numMinus() {
	var _num = $("#_productNum").val();
	if (_num > 0) {
		_num--;
	}
	$("#_productNum").val(_num);
	$("#_productNum").select();
	$("#_productNum").focus();
}

// 系统默认配置
Ext.onReady(function() {
//	Ext.util.CSS.swapStyleSheet("theme",
//			"../lib/ext/resources/css/xtheme-gray.css");

	// 使用表单提示
	Ext.QuickTips.init();
	Ext.form.Field.prototype.msgTarget = 'side';
});

// 处理中进度条
function commonLoading() {
	Ext.MessageBox.show({
		title : "请稍等",
		msg : "处理中...",
		progress : true,
		width : 300,
		closable : true
	});
	var f = function(v) {
		return function() {
			if (v == 12) {
				Ext.MessageBox.hide();
			} else {
				var i = v / 11;
				Ext.MessageBox.updateProgress(i);
			}
		}
	}
	for (var i = 1; i < 13; i++) {
		setTimeout(f(i), i * 500);
	}
}
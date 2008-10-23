// 用户登录
function userLogin() {

	Ext.Ajax.request({
		url : 'loginAction.ajax',
		success : function(result, request) {
			Ext.MessageBox.alert('Success', 'Data return from the server: '
					+ result.responseText);
		},
		failure : function(result, request) {
			var o1 = result.responseText;
			alert(o1)
			var o2 = request;
			alert("failure");
		},
		params : {
			userId : $F('loginForm:userId'),
			password : $F('loginForm:password')
		}
	});
}
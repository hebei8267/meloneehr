Ext.onReady(function() {

	// 定义表单
	var formPanel = new Ext.FormPanel({
		labelAlign : 'left',
		width : 300,
		labelWidth : 60,
		frame : true,
		title : '用户登陆',
		bodyStyle : 'padding:10px 20px 0',
		// 定义表单元素
		items : [{
			layout : 'column',
			items : [{
				columnWidth : 1,
				layout : 'form',
				border : false,
				items : [{
					xtype : 'textfield',
					name : 'userId',
					anchor : '93%',
					allowBlank : false,
					fieldLabel : '帐 户',
					blankText : '帐户不能为空'
				}]
			}, {
				columnWidth : 1,
				layout : 'form',
				border : false,
				items : [{
					xtype : 'textfield',
					inputType : 'password',
					name : 'pwd',
					anchor : '93%',
					allowBlank : false,
					fieldLabel : '密 码',
					blankText : '密码不能为空'
				}]
			}, {
				columnWidth : 1,
				layout : 'column',
				border : false,
				items : [{
					columnWidth : .65,
					layout : 'form',
					border : false,
					labelWidth : 80,
					items : [{
						style : 'margin-top:5px',
						xtype : 'radio',
						fieldLabel : '修改密码',
						boxLabel : '不修改',
						name : 'modPwd',
						checked : true,
						inputValue : 'LOGIN',
						anchor : '95%'
					}]
				}, {
					columnWidth : .35,
					layout : 'form',
					border : false,
					labelWidth : 5,
					items : [{
						labelSeparator : '',
						style : 'margin-top:5px',
						xtype : 'radio',
						boxLabel : '修改',
						name : 'modPwd',
						inputValue : 'MODPWD',
						anchor : '95%'
					}]
				}]
			}]
		}],

		// 定义button
		buttons : [{
			text : '登 陆',
			type : 'submit',
			// 定义表单提交事件
			handler : function() {
				if (formPanel.form.isValid()) {
					// 验证合法后使用加载进度条
					commonLoading();

					var opType = formPanel.getForm().findField('modPwd')
							.getGroupValue();

					if (opType == 'LOGIN') {// 登陆
						setTimeout(function() {
							location.href = "../frame/mainFrame.html";
						}, 4000);
					} else {// 修改密码
						setTimeout(function() {
							location.href = "modPwd.html";
						}, 4000);
					}

					// 提交到服务器操作
					// formPanel.form.doAction('submit', {
					// url : '123',// 文件路径
					// method : 'post',
					// params : '',
					// // 提交成功的回调函数
					// success : function(form, action) {
					// if (action.result.msg == 'ok') {
					// document.location = 'index.html';
					// } else {
					// Ext.Msg.alert('登陆错误', action.result.msg);
					// }
					// },
					// // 提交失败的回调函数
					// failure : function() {
					// Ext.Msg.alert('错误', '服务器出现错误请稍后再试！');
					// },
					// timeout : 5000
					// });
				}
			}
		}, {
			text : '取 消',
			handler : function() {
				formPanel.form.reset();// 重置表单
			}
		}]
	});

	formPanel.render(document.body);
	formPanel.getEl().center();

});

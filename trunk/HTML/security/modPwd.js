Ext.onReady(function() {

	// 定义表单
	var formPanel = new Ext.FormPanel({
		labelAlign : 'left',
		width : 300,
		labelWidth : 80,
		frame : true,
		title : '修改密码',
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
					disabled : true,
					fieldLabel : '帐 户',
					value : 'XZA2349112'
				}, {
					columnWidth : 1,
					layout : 'form',
					border : false,
					items : [{
						xtype : 'textfield',
						name : 'userName',
						anchor : '93%',
						disabled : true,
						fieldLabel : '用户名称',
						value : '张三'
					}]
				}, {
					columnWidth : 1,
					layout : 'form',
					border : false,
					items : [{
						xtype : 'textfield',
						inputType : 'password',
						name : 'oldPwd',
						anchor : '93%',
						allowBlank : false,
						fieldLabel : '原密码',
						blankText : '原密码不能为空'
					}]
				}, {
					columnWidth : 1,
					layout : 'form',
					border : false,
					items : [{
						xtype : 'textfield',
						inputType : 'password',
						name : 'newPwd1',
						anchor : '93%',
						allowBlank : false,
						fieldLabel : '新密码',
						blankText : '新密码不能为空'
					}]
				}, {
					columnWidth : 1,
					layout : 'form',
					border : false,
					items : [{
						xtype : 'textfield',
						inputType : 'password',
						name : 'newPwd2',
						anchor : '93%',
						allowBlank : false,
						fieldLabel : '新密码（校验）',
						blankText : '新密码（校验）不能为空'
					}]
				}]
			}]
		}],

		// 定义button
		buttons : [{
			text : '修 改',
			type : 'submit',
			// 定义表单提交事件
			handler : function() {
				if (formPanel.form.isValid()) {
					// 验证合法后使用加载进度条
					commonLoading();

					setTimeout(function() {
						location.href = "../frame/mainFrame.html";
					}, 4000);
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
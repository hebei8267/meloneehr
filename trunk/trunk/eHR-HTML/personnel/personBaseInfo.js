/**
 * @author kaka
 */
Ext.onReady(function() {
	var sexCombo = new Ext.form.ComboBox({
		triggerAction : 'all',
		width : 155,
		editable : false,
		transform : 'sex' // 转变表单元素ID
	});

	var countryCombo = new Ext.form.ComboBox({
		triggerAction : 'all',
		width : 155,
		editable : false,
		transform : 'country' // 转变表单元素ID
	});
	
	var identifieationTypeCombo = new Ext.form.ComboBox({
		triggerAction : 'all',
		width : 155,
		editable : false,
		transform : 'identifieationType' // 转变表单元素ID
	});
	
	var educationTypeCombo = new Ext.form.ComboBox({
		triggerAction : 'all',
		width : 155,
		editable : false,
		transform : 'educationType' // 转变表单元素ID
	});
	
	var marriageStateCombo = new Ext.form.ComboBox({
		triggerAction : 'all',
		width : 155,
		editable : false,
		transform : 'marriageState' // 转变表单元素ID
	});

	// 设立时间
	var startDate = new Ext.form.DateField({
		name : 'register_date',
		width : 155,
		altFormats : 'Y-m-d',
		format : 'Y-m-d'
	});
	startDate.render('birthday'); // div-di

	
});

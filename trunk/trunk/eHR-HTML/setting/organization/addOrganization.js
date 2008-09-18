/**
 * @author kaka
 */
Ext.onReady(function() {
	alert(123);
	var orgTypeCombo = new Ext.form.ComboBox({
		triggerAction : 'all',
		width : 155,
		editable : false,
		transform : 'orgType' // 转变表单元素ID
	});

	var countryCombo = new Ext.form.ComboBox({
		triggerAction : 'all',
		width : 155,
		editable : false,
		transform : 'country' // 转变表单元素ID
	});

	// 设立时间
	var startDate = new Ext.form.DateField({
		name : 'register_date',
		width : 155,
		altFormats : 'Y-m-d',
		format : 'Y-m-d'
	});
	startDate.render('startDate'); // div-di

	// 撤销时间
	var endDate = new Ext.form.DateField({
		name : 'register_date',
		width : 155,
		altFormats : 'Y-m-d',
		format : 'Y-m-d'
	});
	endDate.render('endDate'); // div-di
});

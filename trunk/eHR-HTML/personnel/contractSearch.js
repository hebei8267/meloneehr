/**
 * @author kaka
 */
Ext.onReady(function(){
	var contractTypeCombo = new Ext.form.ComboBox({
		triggerAction : 'all',
		width : 155,
		editable : false,
		transform : 'contractType' // 转变表单元素ID
	});
	
	var startDate = new Ext.form.DateField({
		name : 'startDate',
		width : 155,
		altFormats : 'Y-m-d',
		format : 'Y-m-d'
	});
	startDate.render('contractStart'); // div-di
	
	var endDate = new Ext.form.DateField({
		name : 'endDate',
		width : 155,
		altFormats : 'Y-m-d',
		format : 'Y-m-d'
	});
	endDate.render('contractEnd'); // div-di
});

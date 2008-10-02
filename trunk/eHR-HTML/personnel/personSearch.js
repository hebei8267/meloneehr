/**
 * @author kaka
 */
Ext.onReady(function(){
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
});
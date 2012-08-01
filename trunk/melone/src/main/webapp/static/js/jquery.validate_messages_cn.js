/*
 * Translated default messages for the jQuery validation plugin.
 * Locale: CN
 */
jQuery.extend(jQuery.validator.messages, {
        required: "必选字段",
		remote: "请修正该字段",
		email: "请输入正确格式的电子邮件",
		url: "请输入合法的网址",
		date: "请输入合法的日期",
		dateISO: "请输入合法的日期 (ISO).",
		number: "请输入合法的数字",
		digits: "只能输入整数",
		creditcard: "请输入合法的信用卡号",
		equalTo: "请再次输入相同的值",
		accept: "请输入拥有合法后缀名的字符串",
		maxlength: jQuery.validator.format("请输入一个长度最多是 {0} 的字符串"),
		minlength: jQuery.validator.format("请输入一个长度最少是 {0} 的字符串"),
		rangelength: jQuery.validator.format("请输入一个长度介于 {0} 和 {1} 之间的字符串"),
		range: jQuery.validator.format("请输入一个介于 {0} 和 {1} 之间的值"),
		max: jQuery.validator.format("请输入一个最大为 {0} 的值"),
		min: jQuery.validator.format("请输入一个最小为 {0} 的值"),
		requiredSelect: "请选择至少一个操作对象"
});

// 列表删除，必选
$.validator.methods.requiredSelect = function(value, element, param) {
	return $("input[name='"+ param +"']:checked").length > 0;
};

//手机号码验证       
jQuery.validator.addMethod("isMobile", function(value, element) {       
	var length = value.length;   
	var mobile = /^(\d{11})$/;
	return this.optional(element) || (mobile.test(value));       
}, "请输入正确的手机号码");
    
// 电话号码验证       
jQuery.validator.addMethod("isTel", function(value, element) {
	//电话号码格式010-12345678
	var tel = /^(\d{3,4}-?)?\d{7,9}$/g;  
	return this.optional(element) || (tel.test(value));       
}, "请输入正确的电话号码");
  
// 联系电话(手机/电话皆可)验证   
jQuery.validator.addMethod("isPhone", function(value,element) {   
	var length = value.length;
	var mobile = /^(\d{11})$/;
	var tel = /^(\d{3,4}-?)?\d{7,9}$/g;
	return this.optional(element) || (tel.test(value) || (mobile.test(value)));   
}, "请输入正确的电话号码");

//邮政编码验证 
jQuery.validator.addMethod("zipCode", function(value, element) { 
	var tel = /^[0-9]{6}$/; 
	return this.optional(element) || (tel.test(value)); 
}, "请输入正确的邮政编码");

//IP地址验证 
jQuery.validator.addMethod("ip", function(value, element) { 
	var ip = /^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$/; 
	return this.optional(element) || (ip.test(value) && (RegExp.$1 < 256 && RegExp.$2 < 256 && RegExp.$3 < 256 && RegExp.$4 < 256)); 
}, "请输入正确的IP地址");

//字母和数字的验证 
jQuery.validator.addMethod("chrNum", function(value, element) { 
	var chrNum = /^([a-zA-Z0-9]+)$/; 
	return this.optional(element) || (chrNum.test(value)); 
}, "只能输入数字和字母(字符A-Z, a-z, 0-9)");


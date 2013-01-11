/*
 * Translated default messages for the jQuery validation plugin.
 * Locale: CN
 */
jQuery.extend(jQuery.validator.messages, {
    required : "请填写此字段",
    remote : "请修正此字段",
    email : "请输入正确格式的电子邮件",
    url : "请输入合法的网址",
    date : "请输入合法的日期",
    dateISO : "请输入合法的日期 (ISO).",
    number : "请输入合法的数字",
    digits : "只能输入整数",
    creditcard : "请输入合法的信用卡号",
    equalTo : "请再次输入相同的值",
    accept : "请输入拥有合法后缀名的字符串",
    maxlength : jQuery.validator.format("输入长度最多为 {0} 的字符串"),
    minlength : jQuery.validator.format("输入长度最小为 {0} 的字符串"),
    rangelength : jQuery.validator.format("输入长度必须介于 {0} 和 {1} 之间的字符串"),
    range : jQuery.validator.format("输入值必须介于 {0} 和 {1} 之间的值"),
    max : jQuery.validator.format("输入值不能大于 {0} "),
    min : jQuery.validator.format("输入值不能小于 {0} "),
    requiredSelect : "请选择至少一个操作对象"
});

//手机号码验证
jQuery.validator.addMethod("isMobile", function(value, element) {
    var length = value.length;
    var mobile = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/;
    return this.optional(element) || (length == 11 && mobile.test(value));
}, "请正确填写您的手机号码");

//固定长度效验验证
jQuery.validator.addMethod("length4", function(value, element) {
    var length = value.length;
    return (length == 4);
}, "请输入长度为 4 的字符串");

//固定长度效验验证
jQuery.validator.addMethod("length6", function(value, element) {
    var length = value.length;
    return (length == 6);
}, "请输入长度为 6 的字符串");

//金额验证
jQuery.validator.addMethod("money", function(value, element) {
	return this.optional(element) || /^(([1-9]{1}\d*)|([0]{1}))(\.(\d){1,2})?$/.test(value);
}, "请输入正确的金额");

//大于零的整数验证
jQuery.validator.addMethod("digits1", function(value, element) {
	return this.optional(element) || /^([1-9]{1}\d*)?$/.test(value);
}, "请输入合法的数字");


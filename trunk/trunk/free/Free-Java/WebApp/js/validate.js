function isEmpty(v) {
	return !((v == null) || (v.length == 0) || /^[\s|\u3000]+$/.test(v));
}
/** 是否为数字 */
function isDigits(v) {
	return !/[^\d]/.test(v);
}
function isEmpty(value){
	return value == "";
}

function maxLength(obj, length){
	return obj.length <= parseInt(length);
}

function minLength(obj, length){
	return obj.length >= parseInt(length);
}

function isNumber(value){
	if(isNaN(value)){
		return false;
	}
	var patrn=/^[0-9]/; 
	if (!patrn.exec(value)) {
		return false
	}
	return true 
}
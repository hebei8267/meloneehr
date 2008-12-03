/**
 * @author kaka
 */
// 说明：文本框(input)或文本域(textarea)获取焦点(onfocus)时样式改变的实现方法
function focusInput() {
	var inputElements = document.getElementsByTagName('input');
	$A(inputElements).any(function(input) {
		if(input.type == 'text' || input.type == 'password'){
			Event.observe(input, 'blur', function(ev){
				$(input).removeClassName('x-form-focus');
			});
			Event.observe(input, 'focus', function(ev){
				$(input).addClassName('x-form-focus');
			});	
		}
	});
	
	var textareaElements = document.getElementsByTagName('textarea');
	$A(textareaElements).any(function(input) {
		Event.observe(input, 'blur', function(ev){
			$(input).removeClassName('x-form-focus');
		});
		Event.observe(input, 'focus', function(ev){
			$(input).addClassName('x-form-focus');
		});	
	});
}
//打开一个新窗口
function openSubWindow(url, width, height) {
	var win = null;
	win = window.open(url,
					  "actorSelect",
					  "toolbar=no,menubar=no,scrollbars=yes,resizable=no,status=no,location=no,width="
					  + width + ",height=" + height);
	win.focus();
	return win;
}
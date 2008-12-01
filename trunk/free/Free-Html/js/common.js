/**
 * @author kaka
 */
// 说明：文本框(input)获取焦点(onfocus)时样式改变的实现方法
function focusInput() {
	var elements = document.getElementsByTagName('input');
	$A(elements).any(function(input) {

		Event.observe(input, 'blur', function(ev){
			$(input).removeClassName('x-form-focus');
		});
		Event.observe(input, 'focus', function(ev){
			$(input).addClassName('x-form-focus');
		});			
	});
} 
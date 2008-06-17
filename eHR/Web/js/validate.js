var ValidationDefaultOptions = function() {
};

ValidationDefaultOptions.prototype = {
	onSubmit : true,
	stopOnFirst : false,
	immediate : true,
	focusOnError : true,
	useTitles : false,
	onFormValidate : function(result, form) {
	},
	onElementValidate : function(result, elm) {
	}
};

var ValidatorDefaultOptions = function() {
};

ValidatorDefaultOptions.prototype = {
	ignoreEmptyValue : true,
	depends : []
};

Validator = Class.create();
ValidationUtils = {
	getReferenceForm : function(elm) {
		while (elm && elm.tagName != 'BODY') {
			if (elm.tagName == 'FORM')
				return elm;
			elm = elm.parentNode;
		}
		return null;
	},
	getInputValue : function(elm) {
		var elm = $(elm);
		if (elm.type.toLowerCase() == 'file') {
			return elm.value;
		} else {
			return $F(elm);
		}
	},
	getElmID : function(elm) {
		return elm.id ? elm.id : elm.name;
	},
	format : function(str, args) {
		args = args || [];
		ValidationUtils.assert(args.constructor == Array, "ValidationUtils.format() arguement 'args' must is Array");
		var result = str;
		for (var i = 0;i < args.length; i++) {
			result = result.replace(/%s/, args[i]);
		}
		return result;
	},
	getArgumentsByClassName : function(prefix, className) {
		if (!className || !prefix)
			return [];
		var pattern = new RegExp(prefix + '-(\\S+)');
		var matchs = className.match(pattern);
		if (!matchs)
			return [];
		var results = [];
		results.singleArgument = matchs[1];
		var args = matchs[1].split('-');
		for (var i = 0;i < args.length; i++) {
			if (args[i] == '') {
				if (i + 1 < args.length)
					args[i + 1] = '-' + args[i + 1];
			} else {
				results.push(args[i]);
			}
		}
		return results;
	},
	assert : function(condition, message) {
		var errorMessage = message
				|| ("assert failed error,condition=" + condition);
		if (!condition) {
			alert(errorMessage);
			throw new Error(errorMessage);
		} else {
			return condition;
		}
	},
	isDate : function(v, dateFormat) {
		var MONTH = "MM";
		var DAY = "dd";
		var YEAR = "yyyy";
		var regex = '^'	+ dateFormat.replace(YEAR, '\\d{4}').replace(MONTH, '\\d{2}').replace(DAY, '\\d{2}') + '$';
		if (!new RegExp(regex).test(v))
			return false;
		var year = v.substr(dateFormat.indexOf(YEAR), 4);
		var month = v.substr(dateFormat.indexOf(MONTH), 2);
		var day = v.substr(dateFormat.indexOf(DAY), 2);
		var d = new Date(ValidationUtils.format('%s/%s/%s', [year, month, day]));
		return (parseInt(month, 10) == (1 + d.getMonth())) && (parseInt(day, 10) == d.getDate()) && (parseInt(year, 10) == d.getFullYear());
	},
	fireSubmit : function(form) {
		var form = $(form);
		if (form.fireEvent) {
			if (form.fireEvent('onsubmit'))
				form.submit();
		} else if (document.createEvent) {
			var evt = document.createEvent("HTMLEvents");
			evt.initEvent('submit', false, true);
			form.dispatchEvent(evt);
		}
	},
	getLanguage : function() {
		var lang = null;
		if (typeof navigator.userLanguage == 'undefined')
			lang = navigator.language.toLowerCase();
		else
			lang = navigator.userLanguage.toLowerCase();
		return lang;
	},
	getMessageSource : function() {
		var lang = ValidationUtils.getLanguage();
		var messageSource = Validator.messageSource['zh-cn'];
		if (Validator.messageSource[lang]) {
			messageSource = Validator.messageSource[lang];
		}
		return messageSource;
	}
};

Validator.messages = {
	'validation-failed' : '验证失败.',
	'required' : '请输入值.',
	'validate-number' : '请输入有效的数字.',
	'validate-digits' : '请输入数字.',
	'validate-alpha' : '请输入英文字母.',
	'validate-alphanum' : '请输入英文字母或是数字,其它字符是不允许的.',
	'validate-email' : '请输入有效的邮件地址,如 username@example.com.',
	'validate-url' : '请输入有效的URL地址.',
	'validate-currency-dollar' : 'Please enter a valid $ amount. For example $100.00 .',
	'validate-one-required' : '在上面选项至少选择一个.',
	'validate-integer' : '请输入正确的整数',
	'validate-pattern' : '输入的值不匹配',
	'validate-ip' : '请输入正确的IP地址',
	'min-value' : '最小值为%s',
	'max-value' : '最大值为%s',
	'min-length' : '最小长度为%s,当前长度为%s.',
	'max-length' : '最大长度为%s,当前长度为%s.',
	'int-range' : '输入值应该为 %s 至 %s 的整数',
	'float-range' : '输入值应该为 %s 至 %s 的数字',
	'length-range' : '输入值的长度应该在 %s 至 %s 之间,当前长度为%s',
	'equals' : '两次输入不一致,请重新输入',
	'less-than' : '请输入小于前面的值',
	'great-than' : '请输入大于前面的值',
	'validate-date' : '请输入有效的日期,格式为 %s. 例如:%s.',
	'validate-file' : function(v, elm, args, metadata) {return ValidationUtils.format("文件类型应该为[%s]其中之一", [args.join(',')]);},
	'validate-id-number' : '请输入合法的身份证号码',
	'validate-chinese' : '请输入中文',
	'validate-phone' : '请输入正确的电话号码,如:021-87654321,当前长度为%s.',
	'validate-mobile-phone' : '请输入正确的手机号码,当前长度为%s.',
	'validate-zip' : '请输入有效的邮政编码',
	'validate-qq' : '请输入有效的QQ号码.',
	'validate-richeditor' : '请输入值.',
	'validate-combobox' : '请输入值或选取前面的选项.'
};

Validator.prototype = {
	initialize : function(className, test, options) {
		this.options = Object.extend(new ValidatorDefaultOptions(), options || {});
		this._test = test ? test : function(v, elm) {
			return true;
		};
		this._error = Validator.messages[className] ? Validator.messages[className] : Validator.messages['validation-failed'];
		this.className = className;
		this._dependsTest = this._dependsTest.bind(this);
		this._getDependError = this._getDependError.bind(this);
	},
	_dependsTest : function(v, elm) {
		if (this.options.depends && this.options.depends.length > 0) {
			var dependsResult = $A(this.options.depends).all(function(depend) {
				return Validation.get(depend).test(v, elm);
			});
			return dependsResult;
		}
		return true;
	},
	test : function(v, elm) {
		if (!this._dependsTest(v, elm))
			return false;
		if (!elm)
			elm = {};
		return (this.options.ignoreEmptyValue && ((v == null) || (v.length == 0))) || this._test(v, elm, ValidationUtils.getArgumentsByClassName(this.className, elm.className), this);
	},
	_getDependError : function(v, elm, useTitle) {
		var dependError = null;
		$A(this.options.depends).any(function(depend) {
			var validation = Validation.get(depend);
			if (!validation.test(v, elm)) {
				dependError = validation.error(v, elm, useTitle);
				return true;
			}
			return false;
		});
		return dependError;
	},
	error : function(v, elm, useTitle) {
		var dependError = this._getDependError(v, elm, useTitle);
		if (dependError != null)
			return dependError;
		var args = ValidationUtils.getArgumentsByClassName(this.className,
				elm.className);
		var error = this._error;
		if (typeof error == 'string') {
			if (v) args.push(v.length);
			error = ValidationUtils.format(this._error, args);
		} else if (typeof error == 'function') {
			error = error(v, elm, args, this);
		} else {
			alert('property "_error" must type of string or function');
		}
		if (!useTitle)
			useTitle = elm.className.indexOf('useTitle') >= 0;
		return useTitle ? ((elm && elm.title) ? elm.title : error) : error;
	}
};

var Validation = Class.create();
Validation.prototype = {
	initialize : function(form, options) {
		this.options = Object.extend(new ValidationDefaultOptions(), options || {});
		this.form = $(form);
		//TODO hebei
		this.form.insert("<img src='/images/spinner.gif' style='display:none;' id='_form_spinner_'/>");
		var formId = ValidationUtils.getElmID($(form));
		Validation.validations[formId] = this;
		if (this.options.onSubmit)
			Event.observe(this.form, 'submit', this.onSubmit.bind(this), false);
		if (this.options.immediate) {
			var useTitles = this.options.useTitles;
			var callback = this.options.onElementValidate;
			Form.getElements(this.form).each(function(input) {
				Event.observe(input, 'blur', function(ev) {
					Validation.validateElement(Event.element(ev), {
						useTitle : useTitles,
						onElementValidate : callback
					});
				});
			});
		}
	},
	onSubmit : function(ev) {
		if (!this.validate()) {
			Event.stop(ev);
		} else {
			var submit_button = this.form.select('input.submit')[0];
			if (submit_button) {
				Element.insert(submit_button, {
					"after" : $('_form_spinner_').remove()
				});
				$('_form_spinner_').show();
			}
		}
	},
	validate : function() {
		var result = false;
		var useTitles = this.options.useTitles;
		var callback = this.options.onElementValidate;
		if (this.options.stopOnFirst) {
			result = Form.getElements(this.form).all(function(elm) {
				return Validation.validateElement(elm, {
					useTitle : useTitles,
					onElementValidate : callback
				});
			});
		} else {
			result = Form.getElements(this.form).collect(function(elm) {
				return Validation.validateElement(elm, {
					useTitle : useTitles,
					onElementValidate : callback
				});
			}).all();
		}
		if (!result && this.options.focusOnError) {
			var first = Form.getElements(this.form).findAll(function(elm) {
				return $(elm).hasClassName('validation-failed');
			}).first();
			if (first.select)
				first.select();
			first.focus();
		}
		this.options.onFormValidate(result, this.form);
		return result;
	},
	reset : function() {
		Form.getElements(this.form).each(function(elm) {
			Validation.hideErrorMsg(elm);
		});
	}
};
Object.extend(Validation, {
	validateElement : function(elm, options) {
		options = Object.extend( {
			useTitle : false,
			onElementValidate : function(result, elm) {
			}
		}, options || {});
		elm = $(elm);
		var cn = elm.classNames();
		return cn.all(function(value) {
			var test = Validation.test(value, elm, options.useTitle);
			options.onElementValidate(test, elm);
			return test;
		});
	},
	showErrorMsg : function(name, elm, errorMsg) {
		if (!elm.tooltip)
			elm.tooltip = new Tooltip(elm, {
				backgroundColor : "#FC9",
				borderColor : "#C96",
				textColor : "#000",
				textShadowColor : "#FFF"
			});
		elm.tooltip.content = errorMsg;
		elm.removeClassName('validation-passed');
		elm.addClassName('validation-failed');
	},
	showErrorMsgByValidator : function(name, elm, useTitle) {
		Validation.showErrorMsg(name, elm, Validation.get(name).error(
				ValidationUtils.getInputValue(elm), elm, useTitle));
	},
	hideErrorMsg : function(elm) {
		if (elm.tooltip) {
			elm.tooltip.stop();
			elm.tooltip = false;
		}
		elm.removeClassName('validation-failed');
		elm.addClassName('validation-passed');
	},
	test : function(name, elm, useTitle) {
		var v = Validation.get(name);
		if (!v.test(ValidationUtils.getInputValue(elm), elm)) {
			Validation.showErrorMsgByValidator(name, elm, useTitle);
			return false;
		} else {
			Validation.hideErrorMsg(elm);
			return true;
		}
	},
	getAdvice : function(name, elm) {
		return Try.these(function() {
			return $('advice-' + name + '-' + ValidationUtils.getElmID(elm));
		}, function() {
			return $('advice-' + ValidationUtils.getElmID(elm));
		});
	},
	add : function(className, test, options) {
		var nv = {};
		var testFun = test;
		if (test instanceof RegExp)
			testFun = function(v, elm, args, metadata) {
				return test.test(v);
			};
		nv[className] = new Validator(className, testFun, options);
		Object.extend(Validation.methods, nv);
	},
	addAllThese : function(validators) {
		$A(validators).each(function(value) {
			Validation.add(value[0], value[1], (value.length > 2 ? value[2] : {}));
		});
	},
	get : function(name) {
		var resultMethodName;
		for (var methodName in Validation.methods) {
			if (name == methodName) {
				resultMethodName = methodName;
				break;
			}
			if (name.indexOf(methodName) >= 0) {
				resultMethodName = methodName;
			}
		}
		return Validation.methods[resultMethodName] ? Validation.methods[resultMethodName] : new Validator();
	},
	$ : function(formId) {
		return Validation.validations[formId];
	},
	methods : {},
	validations : {}
});
Validation.addAllThese([
				['required', function(v) {
					return !((v == null) || (v.length == 0) || /^[\s|\u3000]+$/.test(v));
				}, {
					ignoreEmptyValue : false
				}],
				['validate-number', function(v) {
					return (!isNaN(v) && !/^\s+$/.test(v));
				}],
				['validate-digits', function(v) {
					return !/[^\d]/.test(v);
				}],
				['validate-alphanum', function(v) {
					return !/\W/.test(v);
				}],
				['validate-one-required', function(v, elm) {
					var p = elm.parentNode;
					var options = p.getElementsByTagName('INPUT');
					return $A(options).any(function(elm) {
						return $F(elm);
					});
				}, {
					ignoreEmptyValue : false
				}],
				['validate-digits', /^[\d]+$/],
				['validate-alphanum', /^[a-zA-Z0-9]+$/],
				['validate-alpha', /^[a-zA-Z]+$/],
				['validate-email', /\w{1,}[@][\w\-]{1,}([.]([\w\-]{1,})){1,3}$/],
				['validate-url', /^(http|https|ftp):\/\/(([A-Z0-9][A-Z0-9_-]*)(\.[A-Z0-9][A-Z0-9_-]*)+)(:(\d+))?\/?/i],
				['validate-currency-dollar', /^\$?\-?([1-9]{1}[0-9]{0,2}(\,[0-9]{3})*(\.[0-9]{0,2})?|[1-9]{1}\d*(\.[0-9]{0,2})?|0(\.[0-9]{0,2})?|(\.[0-9]{1,2})?)$/]]);

Validation.addAllThese([
				['equals', function(v, elm, args, metadata) {
					return $F(args[0]) == v;
				}, {
					ignoreEmptyValue : false
				}],
				['less-than', function(v, elm, args, metadata) {
					if (Validation.get('validate-number').test(v) && Validation.get('validate-number').test($F(args[0])))
						return parseFloat(v) < parseFloat($F(args[0]));
					return v < $F(args[0]);
				}],
				['great-than', function(v, elm, args, metadata) {
					if (Validation.get('validate-number').test(v) && Validation.get('validate-number').test($F(args[0])))
						return parseFloat(v) > parseFloat($F(args[0]));
					return v > $F(args[0]);
				}],
				['min-length', function(v, elm, args, metadata) {
					return v.length >= parseInt(args[0]);
				}],
				['max-length', function(v, elm, args, metadata) {
					return v.length <= parseInt(args[0]);
				}],
				['validate-file', function(v, elm, args, metadata) {
					return $A(args).any(function(extentionName) {
						return new RegExp('\\.' + extentionName + '$', 'i').test(v);
					});
				}],
				['float-range', function(v, elm, args, metadata) {
					return (parseFloat(v) >= parseFloat(args[0]) && parseFloat(v) <= parseFloat(args[1]));
				}, {
					depends : ['validate-number']
				}],
				['int-range', function(v, elm, args, metadata) {
					return (parseInt(v) >= parseInt(args[0]) && parseInt(v) <= parseInt(args[1]));
				}, {
					depends : ['validate-integer']
				}],
				['length-range', function(v, elm, args, metadata) {
					return (v.length >= parseInt(args[0]) && v.length <= parseInt(args[1]));
				}],
				['max-value', function(v, elm, args, metadata) {
					return parseFloat(v) <= parseFloat(args[0]);
				}, {
					depends : ['validate-number']
				}],
				['min-value', function(v, elm, args, metadata) {
					return parseFloat(v) >= parseFloat(args[0]);
				}, {
					depends : ['validate-number']
				}],
				['validate-pattern', function(v, elm, args, metadata) {
					return eval('(' + args.singleArgument + '.test(v))');
				}],
				['validate-ajax', function(v, elm, args, metadata) {
					var form = ValidationUtils.getReferenceForm(elm);
					var params = (form ? Form.serialize(form) : Form.Element.serialize(elm));
							params += ValidationUtils.format("&what=%s&value=%s", [elm.name, encodeURIComponent(v)]);
							var request = new Ajax.Request(args.singleArgument, {
										parameters : params,
										asynchronous : false,
										method : "post"
									});
							var responseText = request.transport.responseText;
							if ("" == responseText.strip())
								return true;
							metadata._error = responseText;
							return false;
						}],
				['validate-date', function(v, elm, args, metadata) {
					var dateFormat = args.singleArgument || 'yyyy-MM-dd';
					metadata._error = ValidationUtils.format(Validator.messages[metadata.className], [dateFormat, dateFormat.replace('yyyy', '2006').replace('MM', '03').replace('dd', '12')]);
					return ValidationUtils.isDate(v, dateFormat);
				}],
				['validate-integer', /^[-+]?[1-9]\d*$|^0$/],
				['validate-ip', /^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$/],
				['validate-id-number', function(v, elm, args, metadata) {
					if (!(/^\d{17}(\d|x)$/i.test(v) || /^\d{15}$/i.test(v)))
						return false;
					var provinceCode = parseInt(v.substr(0, 2));
					if ((provinceCode < 11) || (provinceCode > 91))
						return false;
					var forTestDate = v.length == 18 ? v : v.substr(0,6) + "19" + v.substr(6, 15);
					var birthday = forTestDate.substr(6, 8);
					if (!ValidationUtils.isDate(birthday, 'yyyyMMdd'))
						return false;
					if (v.length == 18) {
						v = v.replace(/x$/i, "a");
						var verifyCode = 0;
						for (var i = 17;i >= 0; i--)
							verifyCode += (Math.pow(2, i) % 11) * parseInt(v.charAt(17 - i), 11);
							if (verifyCode % 11 != 1)
								return false;
					}
					return true;
				}],
				['validate-chinese', /^[\u4e00-\u9fa5]+$/],
				['validate-phone', /^((0[1-9]{3})?(0[12][0-9])?[-])?\d{6,8}$/],
				['validate-mobile-phone', /(^0?[1][35][0-9]{9}$)/],
				['validate-zip', /^[1-9]\d{5}$/],
				['validate-qq', /^[1-9]\d{4,8}$/],
				['validate-richeditor', function(v, elm) {
					if (editor.mode == "rich") {
						v = tinyMCE.activeEditor.getContent();
						elm.value = v;
					}
					return !((v == null) || (v.length == 0) || /^[\s|\u3000]+$/.test(v));
				}, {
					ignoreEmptyValue : false
				}],
				['validate-combobox', function(v, elm, args) {
					var v = $F(args[0]);
					return Validation.get('required').test(elm.value) || Validation.get('required').test(v);
				}, {
					ignoreEmptyValue : false
				}]]);

//下面的代码备用				
function multiple_upload_attachment(ele, max_size) {
	Event.observe(ele, 'change', function() {
		addAttachmentDiv(ele, max_size);
	});
	if (multiple_upload_attachment_counter >= max_size)
		ele.disabled = true;
}
function addAttachmentDiv(ele, max_size) {
	if (!$A(['jpg', 'bmp', 'png', 'gif', 'rar', 'zip', 'tar', 'gz', 'jar',
			'war']).any(function(extName) {
		return new RegExp('\\.' + extName + '$', 'i').test(ele.value);
	})) {
		alert("文件类型错误，请重新挑选文件");
		return false;
	}
	multiple_upload_attachment_counter++;
	var div = new Element("div").update("<ul><li>文件: "
					+ ele.value
					+ " <a href='#' onclick='removeAttachmentDiv(this, \""
					+ ele.id
					+ "\");return false;'>删除</a></li><li>注解: <textarea name='attachments[][remark]' style='width:300px;height:80px;'></textarea></li></ul>");
	var new_input = new Element("input", {
		type : "file",
		name : ele.name,
		id : ele.id,
		disabled : multiple_upload_attachment_counter >= max_size
	});
	Event.observe(new_input, 'change', function() {
		addAttachmentDiv(new_input, max_size);
	});
	ele.insert( {
		before : new_input,
		after : div
	});
	ele.id = ele.id + multiple_upload_attachment_counter;
	ele.name = "attachments[][uploaded_data]";
	div.appendChild(ele.hide().remove());
}
function removeAttachmentDiv(link, eleId) {
	multiple_upload_attachment_counter--;
	link.parentNode.parentNode.parentNode.remove();
	$(eleId).disabled = false;
}
function multiple_upload_picture(ele, max_size, tags) {
	Event.observe(ele, 'change', function() {
		addPictureDiv(ele, max_size, tags);
	});
	if (multiple_upload_picture_counter >= max_size)
		ele.disabled = true;
}
function addPictureDiv(ele, max_size, tags) {
	if (!$A(['jpg', 'bmp', 'png', 'gif']).any(function(extName) {
		return new RegExp('\\.' + extName + '$', 'i').test(ele.value);
	})) {
		alert("文件类型错误，请重新挑选文件");
		return false;
	}
	multiple_upload_picture_counter++;
	var file_name = ele.value;
	try {
		file_name = ele.value.match(/(.*)[\/\\]([^\/\\]+)\.\w+$/)[2];
	} catch (e) {
	}
	var tag_select = "";
	if (tags.length > 0) {
		var tag_select = "<select onchange='Element.previous($(this)).value = this.value;'><option value=''>选择已有标签</option>";
		for (var i = 0;i < tags.length; i++)
			tag_select += "<option value='" + tags[i] + "'>" + tags[i]
					+ "</option>";
		tag_select += "</select>";
	}
	var div = new Element("div")
			.update("<ul><li>文件: "
					+ ele.value
					+ " <a href='#' onclick='removePictureDiv(this, \""
					+ ele.id
					+ "\");return false;'>删除</a></li><li>标签: <input type='text' name='pictures[][tag_list]' class='text'/>"
					+ tag_select
					+ " 小贴士: 多个标签可用半角逗号分开</li><li>名称: <input type='text' name='pictures[][name]' value='"
					+ file_name
					+ "' size='50' class='text'/></li><li>描述: <textarea name='pictures[][description]' style='width:400px;height:80px;'></textarea></li></ul>");
	var new_input = new Element("input", {
		type : "file",
		name : ele.name,
		id : ele.id,
		disabled : multiple_upload_picture_counter >= max_size
	});
	Event.observe(new_input, 'change', function() {
		addPictureDiv(new_input, max_size, tags);
	});
	ele.insert( {
		before : new_input,
		after : div
	});
	ele.id = ele.id + multiple_upload_picture_counter;
	ele.name = "pictures[][uploaded_data]";
	div.appendChild(ele.hide().remove());
}
function removePictureDiv(link, eleId) {
	multiple_upload_picture_counter--;
	link.parentNode.parentNode.parentNode.remove();
	$(eleId).disabled = false;
}
function move_messages(dest) {
	$('dest').value = dest;
	$('messages_form').submit();
}
function digg_news(news_id) {
	$$('#n' + news_id + ' a').each(function(a) {
		a.onclick = "return false;"
	});
	new Ajax.Request('/news/digg', {
		asynchronous : true,
		evalScripts : true,
		parameters : {
			"id" : news_id
		}
	});
}
function bury_news(news_id) {
	$$('#n' + news_id + ' a').each(function(a) {
		a.onclick = "return false;"
	});
	new Ajax.Request('/news/bury', {
		asynchronous : true,
		evalScripts : true,
		parameters : {
			"id" : news_id
		}
	});
}
function digg_blog(blog_id) {
	$$('#b' + blog_id + ' a').each(function(a) {
		a.onclick = "return false;"
	});
	new Ajax.Request('/blogs/digg', {
		asynchronous : true,
		evalScripts : true,
		parameters : {
			"id" : blog_id
		}
	});
}
function bury_blog(blog_id) {
	$$('#b' + blog_id + ' a').each(function(a) {
		a.onclick = "return false;"
	});
	new Ajax.Request('/blogs/bury', {
		asynchronous : true,
		evalScripts : true,
		parameters : {
			"id" : blog_id
		}
	});
}
function fix_image_size(images, maxW) {
	if (images.length > 0) {
		Event.observe(window, 'load', function() {
			images.each(function(img) {
				w = img.width;
				h = img.height;
				if (w > maxW) {
					f = 1 - ((w - maxW) / w);
					img.title = "点击查看原始大小图片";
					img.addClassName("magplus");
					img.onclick = function() {
						window.open(this.src);
					};
					img.width = w * f;
					img.height = h * f;
				}
			});
		});
	}
}
try {
	document.execCommand('BackgroundImageCache', false, true);
} catch (e) {
}
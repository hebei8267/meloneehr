//实现两个多选框的左右移动
function moveShowItems(srcObjId, discObjId) {
	var srcObj = document.getElementById(srcObjId);
	var discObj = document.getElementById(discObjId);
	if (srcObj != null && srcObj.options != null && discObj != null
			&& discObj.options != null) {
		var itemListOptions = srcObj.options;
		if (itemListOptions != null && itemListOptions.length > 0) {
			var values = new Array();
			for ( var i = itemListOptions.length - 1; i >= 0; i--) {
				if (itemListOptions[i].selected == true) {
					// new an Option
					var addrOption = document.createElement("OPTION");
					addrOption.value = itemListOptions[i].value;
					addrOption.text = itemListOptions[i].text;
					values.push(addrOption.value);
					// add Option
					discObj.options.add(addrOption);
					itemListOptions.remove(i);
				}
			}
		}
	}
}

//文件预览位置计算
function _clacImgZoomParam(maxWidth, maxHeight, width, height) {
	var param = {
		top : 0,
		left : 0,
		width : width,
		height : height
	};
	if (width > maxWidth || height > maxHeight) {
		rateWidth = width / maxWidth;
		rateHeight = height / maxHeight;

		if (rateWidth > rateHeight) {
			param.width = maxWidth;
			param.height = Math.round(height / rateWidth);
		} else {
			param.width = Math.round(width / rateHeight);
			param.height = maxHeight;
		}
	}

	param.left = Math.round((maxWidth - param.width) / 2);
	param.top = Math.round((maxHeight - param.height) / 2);
	return param;
}
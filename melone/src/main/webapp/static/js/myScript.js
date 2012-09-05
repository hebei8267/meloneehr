//实现两个多选框的左右移动
function moveShowItems(srcObjId, discObjId) {
	var srcObj = document.getElementById(srcObjId);
	var discObj = document.getElementById(discObjId);
	if(srcObj != null && srcObj.options != null && discObj != null && discObj.options != null) {
		var itemListOptions = srcObj.options;
		if(itemListOptions != null && itemListOptions.length > 0) {
			var values = new Array();
			for(var i = itemListOptions.length - 1; i >= 0; i--) {
				if(itemListOptions[i].selected == true) {
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


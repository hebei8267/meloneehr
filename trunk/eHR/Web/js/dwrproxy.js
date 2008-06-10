Ext.data.DWRProxy = function(dwrCall) {
	Ext.data.DWRProxy.superclass.constructor.call(this);
	this.dwrCall = dwrCall;
};

Ext.extend(Ext.data.DWRProxy, Ext.data.DataProxy, {
	load : function(params, reader, callback, scope, arg) {
		if (this.fireEvent("beforeload", this, params) !== false) {
			var delegate = this.loadResponse.createDelegate(this, [reader,
					callback, scope, arg], 1);
			var callParams = new Array();

			callParams.push(arg.params)
			callParams.push(delegate);
			this.dwrCall.apply(this, callParams);
		} else {
			callback.call(scope || this, null, arg, false);
		}
	},

	loadResponse : function(dwrResult, reader, callback, scope, arg) {
		var result;
		try {
			result = reader.read(dwrResult);
		} catch (e) {
			this.fireEvent("loadexception", this, null, dwrResult, e);
			callback.call(scope, null, arg, false);
			return;
		}
		callback.call(scope, result, arg, true);
	}
});

/*
 * DWR调用方法返回的格式json 参数调用格式与 Ext.data.JsonReader是一样的 请查看Ext的api文档
 */
Ext.data.DWRJsonReader = function(meta, recordType) {
	Ext.data.DWRJsonReader.superclass.constructor.call(this, meta, recordType);
};

Ext.extend(Ext.data.DWRJsonReader, Ext.data.JsonReader, {
	read : function(response) {
		alert(response);
		if (typeof response == 'object') {
			alert("object");
		}
		var o = eval("(" + response + ")");
		if (!o) {
			throw {
				message : "JsonReader.read: Json object not found"
			};
		}
		if (o.metaData) {
			delete this.ef;
			this.meta = o.metaData;
			this.recordType = Ext.data.Record.create(o.metaData.fields);
			this.onMetaChange(this.meta, this.recordType, o);
		}
		return this.readRecords(o);
	}
});

/*
 * 返回数组格式：[[xxx,yyy],[aaaa],[bbbb]] 参数调用格式与
 * Ext.data.ArrayReader是一样的，请查看Ext的api文档
 */
Ext.data.DWRArrayReader = function(meta, recordType) {
	Ext.data.DWRArrayReader.superclass.constructor.call(this, meta, recordType);
};

Ext.extend(Ext.data.DWRArrayReader, Ext.data.ArrayReader, {
	read : function(response) {
		var o = response;
		if (!o) {
			throw {
				message : "JsonReader.read: Json object not found"
			};
		}
		if (o.metaData) {
			delete this.ef;
			this.meta = o.metaData;
			this.recordType = Ext.data.Record.create(o.metaData.fields);
			this.onMetaChange(this.meta, this.recordType, o);
		}
		return this.readRecords(o);
	}
});

/*
 * DWR调用方法返回的格式XML 参数调用格式与 Ext.data.XmlReader是一样的 请查看Ext的api文档
 */
Ext.data.DWRXmlReader = function(meta, recordType) {
	Ext.data.DWRXmlReader.superclass.constructor.call(this, meta, recordType);
};

Ext.extend(Ext.data.DWRXmlReader, Ext.data.XmlReader, {
	read : function(response) {
		var doc;
		try {
			if (Ext.isIE || Ext.isIE7) {
				doc = new ActiveXObject("Microsoft.XMLDOM");
				doc.loadXML(response);
			} else {
				var oParser = new DOMParser();
				doc = oParser.parseFromString(response, "text/xml");
			}
		} catch (e) {
			alert(e);
		}
		if (!doc) {
			throw {
				message : "DWRXmlReader.read: XML Document not available"
			};
		}
		return this.readRecords(doc);
	}
});
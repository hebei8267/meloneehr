<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>我的网盘</title>
<link rel="stylesheet" type="text/css" href="${ctx}/uploadify/uploadify.css" />
<script type="text/javascript" src="${ctx}/uploadify/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="${ctx}/uploadify/swfobject.js"></script>
<script type="text/javascript" src="${ctx}/uploadify/jquery.uploadify.v2.1.4.js"></script>

<script type="text/javascript">
$(function(){
    
	<%// http://www.cnblogs.com/pinnasky/archive/2010/06/02/1750089.html %>
	$("#uploadify").uploadify({
		'uploader' : '${ctx}/uploadify/uploadify.swf',
		'script' : '${ctx}/uploadFile.action;jsessionid=2FDE2FEA8050F530E6090F6E9F771202',
		'scriptData' : {},
		'cancelImg' : '${ctx}/uploadify/cancel.png',
		'buttonText' : '',
		'folder' : 'UploadFile',
		'queueID' : 'fileQueue',
		'fileDataName' : 'file',
		'height' : 20,
		'width' : 67,
		'auto' : false,
		'multi' : true,
		'queueSizeLimit' : 999,
		'simUploadLimit' : 1,
		'displayData' : 'percentage', // Set to "speed" to show the upload speed in the default queue item
		'buttonImg' : '${ctx}/uploadify/browse.gif',
		'fileDesc' : '所有文件', //如果配置了以下的'fileExt'属性，那么这个属性是必须的 (.JPG, .GIF, .PNG)
		'fileExt' : '*.*', //允许的格式 *.jpg;*.gif;*.png;
		'onAllComplete' : function(event, data) {

		},
		'onComplete' : function(event, queueID, file,
				serverData, data) { //event、queueId、fileObj、response、data
			//serverData为服务器端返回的字符串值
			//var json = eval('(' + serverData + ')');

		},
		'onSelect' : function(event, queueId, fileObj) { //name、size、creationDate、modificationDate、type

		}
	});

	$('#upload_i').click(function() {
		$('#uploadify').uploadifyUpload();
	});
	

});
</script>
</head>
<body>

	<input type="file" id="uploadify" name="uploadify" multiple="true" style="height: 20px;">
	<img id="upload_i" style="height: 20px; margin: 0; padding: 0;" src="${ctx}/uploadify/upfile.gif" alt="上传">
	<a href="javascript:$('#uploadify').uploadifyClearQueue()">取消上传</a> 
	<div id="fileQueue"></div>

</body>
</html>
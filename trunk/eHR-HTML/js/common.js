function openSubWindow(url,width,height){
	var win = null;
	win = window.open(url,"actorSelect","toolbar=no,menubar=no,scrollbars=no,resizable=no,status=no,location=no,width=" + width + ",height=" + height );
	win.focus();
}
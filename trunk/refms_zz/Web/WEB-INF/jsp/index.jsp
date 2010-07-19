<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
<title></title>
</head>
<body>
index.jsp
<form action="${pageContext.request.contextPath}/index/show1.faces" method="post"><input type="submit"
	value="show1"></form>

<form action="${pageContext.request.contextPath}/index/show2.faces" method="post"><input type="submit"
	value="show2"></form>

<form action="${pageContext.request.contextPath}/index/toIndex2.faces" method="post"><input type="submit"
	value="to index2"></form>
	
<form action="${pageContext.request.contextPath}/tree/show.faces" method="post"><input type="submit"
	value="tree Test"></form>
<form action="${pageContext.request.contextPath}/grid/show.faces" method="post"><input type="submit"
	value="grid Test"></form>
</body>
</html>

<%@ page contentType="text/html" %>
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <title>Barcode4J Servlet</title>
  </head>
  <body>
  	code128
    <img src="<%=request.getContextPath() %>/gensvg?type=code128&msg=0123456789" />
    <br/>
    datamatrix
    <img height="180" width="180" src="<%=request.getContextPath() %>/gensvg?fmt=svg&type=datamatrix&msg=0123456789" />
    <br/>
    codabar
    <img height="180" width="180" src="<%=request.getContextPath() %>/gensvg?fmt=svg&type=codabar&msg=0123456789" />
  </body>
</html>

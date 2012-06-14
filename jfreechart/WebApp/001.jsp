<%@ page contentType="text/html;charset=utf-8"%>

<HTML>
	<HEAD>
		<META http-equiv=Content-Type content="text/html; charset=utf-8">
		<script src="${pageContext.request.contextPath}/js/overlib.js" type="text/javascript"></script>
		<TITLE>jfreechart</TITLE>
	</HEAD>
	<BODY>
		<map id="map0" name="map0">
			<area shape="poly" coords="214,54,232,51,250,50,250,138,250,138" onMouseOver="return overlib('博士: (1, 5%)');" onMouseOut="return nd();" href="http://gmail.com"/>
			<area shape="poly" coords="155,83,168,73,182,65,197,59,214,54,250,138,250,138" onMouseOver="return overlib('硕士: (2, 10%)');" onMouseOut="return nd();" href="http://www.csdn.net"/>
			<area shape="poly" coords="132,157,130,147,129,138,132,118,141,100,155,83,250,138,250,138" onMouseOver="return overlib('本科: (3, 14%)');" onMouseOut="return nd();" href="http://www.163.com"/>
			<area shape="poly" coords="232,225,214,222,197,217,182,210,168,202,156,193,145,182,138,170,132,157,250,138,250,138" onMouseOver="return overlib('大专: (4, 19%)');" onMouseOut="return nd();" href="http://www.baidu.com"/>
			<area shape="poly" coords="367,157,360,173,349,188,334,200,317,210,298,218,277,223,255,225,232,225,250,138,250,138" onMouseOver="return overlib('高中: (5, 24%)');" onMouseOut="return nd();" href="http://www.google.com"/>
			<area shape="poly" coords="250,50,276,52,302,59,324,69,344,83,358,100,367,118,370,138,369,147,367,157,250,138,250,138" onMouseOver="return overlib('高中以下: (6, 29%)');" onMouseOut="return nd();" href="http://www.livahu.com"/>
		</map>


		<p><h1>Use OverLib For ToolTips</h1></p>
		<P ALIGN="CENTER">
			<img src="001.jpg" width=500 height=300 border=0 usemap="#map0">
		</P>
		
		
		<map id="map1" name="map1">
			<area shape="poly" coords="214,54,232,51,250,50,250,138,250,138" title="博士: (1, 5%)" alt="" href="http://gmail.com"/>
			<area shape="poly" coords="155,83,168,73,182,65,197,59,214,54,250,138,250,138" title="硕士: (2, 10%)" alt="" href="http://www.csdn.net"/>
			<area shape="poly" coords="132,157,130,147,129,138,132,118,141,100,155,83,250,138,250,138" title="本科: (3, 14%)" alt="" href="http://www.163.com"/>
			<area shape="poly" coords="232,225,214,222,197,217,182,210,168,202,156,193,145,182,138,170,132,157,250,138,250,138" title="大专: (4, 19%)" alt="" href="http://www.baidu.com"/>
			<area shape="poly" coords="367,157,360,173,349,188,334,200,317,210,298,218,277,223,255,225,232,225,250,138,250,138" title="高中: (5, 24%)" alt="" href="http://www.google.com"/>
			<area shape="poly" coords="250,50,276,52,302,59,324,69,344,83,358,100,367,118,370,138,369,147,367,157,250,138,250,138" title="高中以下: (6, 29%)" alt="" href="http://www.livahu.com"/>
		</map>

		<p><h1>No Use OverLib For ToolTips</h1></p>
		<P ALIGN="CENTER">
			<img src="001.jpg" width=500 height=300 border=0 usemap="#map1">
		</P>
	</BODY>
</HTML>
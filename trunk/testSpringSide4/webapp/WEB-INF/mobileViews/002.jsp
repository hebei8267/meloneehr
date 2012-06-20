<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
<title>My Page 002</title>
</head>
<body>
	<%/*
	一个独立的html文件可以包含多个“pages”,在加载的时候这些data-role="page" 的div会一起堆栈加载。
	每一个page区块需要由一个独特的ID(id="foo")标记，通过 给内部链接地址定义为(herf="#foo"),互相跳转。
	当连接被点击时，JQmoblie会在 文档内寻找带有ID的page容器，然后通过页面转场显示。
	这是一个包含 2个“page”的站点的示例，通过每个页面的ID进行连接。注意每个 page容器的ID属性只是用来做
	内部连接导航的，如果每一个PAGE都是一个独立的 HTML文件的话，那这个ID属性就可有可无了。
	*/ %>
	<!-- Start of first page -->
	<div data-role="page" id="foo">
		<div data-role="header">
			<h1>Foo</h1>
		</div>
		<!-- header -->
		<div data-role="content">
			<p>I'm first in the source order so I'm shown as the page.</p>
			<p>
				View internal page called <a href="#bar">bar</a>
			</p>
		</div>
		<!-- /content -->
		<div data-role="footer">
			<h4>Foo Page Footer</h4>
		</div>
		<!-- /footer -->
	</div>
	<!-- /page -->
	
	
	<!-- Start of second page -->
	<div data-role="page" id="bar">
		<div data-role="header">
			<h1>Bar</h1>
		</div>
		<!-- /header -->
		<div data-role="content">
			<p>I'm first in the source order so I'm shown as the page.</p>
			<p>
				<a href="#foo">Back to foo</a>
			</p>
		</div>
		<!-- /content -->
		<div data-role="footer">
			<h4>Bar Page Footer</h4>
		</div>
		<!-- /footer -->
	</div>
	<!-- /page -->
</body>
</html>
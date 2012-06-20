<%@ page contentType="text/html;charset=UTF-8"%>
<html>
	<head>
		<title>My Page 001</title>
	</head>
	<body>
		<div data-role="page">
			<div data-role="header">
				<h1>mobile测试</h1>
			</div>
			<!-- /header -->
			<div data-role="content">
				<ul data-role="listview" data-inset="true" data-filter="true">
					<li>
						<a href="#">Acura</a>
					</li>
					<li>
						<a href="#">Audi</a>
					</li>
					<li>
						<a href="#">BMW</a>
					</li>
					<li>
						<a href="#">Cadillac</a>
					</li>
					<li>
						<a href="#">Ferrari</a>
					</li>
				</ul>
				<form>
					&nbsp;&nbsp;
					<input type="range" name="slider" id="slider-0" value="25" min="0" max="100" />
					<a href="#" data-role="button" data-icon="star" data-theme="a">Star button</a>
					<a href="#" data-role="button" data-icon="star" data-theme="b">Star button</a>
					<a href="#" data-role="button" data-icon="star" data-theme="c">Star button</a>
					<a href="#" data-role="button" data-icon="star" data-theme="d">Star button</a>
					<a href="#" data-role="button" data-icon="star" data-theme="e">Star button</a>
				</form>
			</div>
			<!-- /content -->
			<div data-role="footer">
				<h1>mobile测试</h1>
			</div>
		</div>
		<!-- /page -->
	</body>
</html>
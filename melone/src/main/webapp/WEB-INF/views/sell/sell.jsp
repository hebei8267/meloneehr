<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="page" uri="http://www.opensymphony.com/sitemesh/page"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<c:set var="sc_ctx">${ctx}/sc</c:set>
<c:set var="pop_sc_ctx">${ctx}/popsc</c:set>
<html>
	<head>
		<script>
		$().ready(function() {
			$("#btn1,#btn2,#btn3,#btn4,#btn5,#btn6,#btn7,#btn8,#btn9,#btn0,#btnDel,#btnEnter").button();
		});	
		</script>
	</head>
	<body>
		<table>
			<tr>
				<td>
					<table>
						<tr>
							<td><input type="text" class="text ui-widget-content ui-corner-all" style="width: 230px;" speech="speech" x-webkit-speech="x-webkit-speech" x-webkit-grammar="builtin:translate"/></td>
						</tr>
						<tr>
							<td>
								<table>
									<tr>
										<td><button id="btn1" type="button"><span class="btn_text">1</span></button></td>
										<td><button id="btn2" type="button"><span class="btn_text">2</span></button></td>
										<td><button id="btn3" type="button"><span class="btn_text">3</span></button></td>
									</tr>
									<tr>
										<td><button id="btn4" type="button"><span class="btn_text">4</span></button></td>
										<td><button id="btn5" type="button"><span class="btn_text">5</span></button></td>
										<td><button id="btn6" type="button"><span class="btn_text">6</span></button></td>
									</tr>
									<tr>
										<td><button id="btn7" type="button"><span class="btn_text">7</span></button></td>
										<td><button id="btn8" type="button"><span class="btn_text">8</span></button></td>
										<td><button id="btn9" type="button"><span class="btn_text">9</span></button></td>
									</tr>
									<tr>
										<td><button id="btnDel" type="button"><span class="btn_text">×</span></button></td>
										<td><button id="btn0" type="button"><span class="btn_text">0</span></button></td>
										<td><button id="btnEnter" type="button"><span class="btn_text">√</span></button></td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</td>
				<td>&nbsp;</td>
				<td>222</td>
			</tr>
		</table>
	</body>
</html>
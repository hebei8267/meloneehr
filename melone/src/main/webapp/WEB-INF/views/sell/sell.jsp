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
			
			$("#btn1").click(function() {
				var _inputBarCode = $("#_inputBarCode").val();
				$("#_inputBarCode").val(_inputBarCode + 1);
			});
			
			$("#btn2").click(function() {
				var _inputBarCode = $("#_inputBarCode").val();
				$("#_inputBarCode").val(_inputBarCode + 2);
			});
			
			$("#btn3").click(function() {
				var _inputBarCode = $("#_inputBarCode").val();
				$("#_inputBarCode").val(_inputBarCode + 3);
			});
			
			$("#btn4").click(function() {
				var _inputBarCode = $("#_inputBarCode").val();
				$("#_inputBarCode").val(_inputBarCode + 4);
			});
			
			$("#btn5").click(function() {
				var _inputBarCode = $("#_inputBarCode").val();
				$("#_inputBarCode").val(_inputBarCode + 5);
			});
			
			$("#btn6").click(function() {
				var _inputBarCode = $("#_inputBarCode").val();
				$("#_inputBarCode").val(_inputBarCode + 6);
			});
			
			$("#btn7").click(function() {
				var _inputBarCode = $("#_inputBarCode").val();
				$("#_inputBarCode").val(_inputBarCode + 7);
			});
			
			$("#btn8").click(function() {
				var _inputBarCode = $("#_inputBarCode").val();
				$("#_inputBarCode").val(_inputBarCode + 8);
			});
			
			$("#btn9").click(function() {
				var _inputBarCode = $("#_inputBarCode").val();
				$("#_inputBarCode").val(_inputBarCode + 9);
			});
			
			$("#btn0").click(function() {
				var _inputBarCode = $("#_inputBarCode").val();
				$("#_inputBarCode").val(_inputBarCode + 0);
			});
			
			$("#btnDel").click(function() {
				var _inputBarCode = $("#_inputBarCode").val();
				$("#_inputBarCode").val(_inputBarCode.substring(0, _inputBarCode.length - 1));
			});
			
			$("#btnEnter").click(function() {
				// ????????????????????????????????????????????????????????????????
			});
		});	
		</script>
	</head>
	<body>
		<table>
			<tr>
				<td>
					<table>
						<tr>
							<td class="item_name">货号/条形码:</td>
							<td><input type="text" id="_inputBarCode" class="text ui-widget-content ui-corner-all" style="width: 170px;" speech="speech" x-webkit-speech="x-webkit-speech" x-webkit-grammar="builtin:translate"/></td>
						</tr>
						<tr>
							<td colspan="2">货品信息</td>
						</tr>
						<tr>
							<td colspan="2">
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
				<td width="10px">&nbsp;</td>
				<td>
					<table>
						<tr>
							<td></td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</body>
</html>
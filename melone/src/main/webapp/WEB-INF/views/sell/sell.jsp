<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="page" uri="http://www.opensymphony.com/sitemesh/page"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<c:set var="sc_ctx">${ctx}/sc</c:set>
<c:set var="pop_sc_ctx">${ctx}/popsc</c:set>
<html>
	<head>
		<script type="text/javascript" src="${ctx}/static/js/jquery.tablescroll.js"></script>
		<script>
		function btn1_click() {
			var _inputBarCode = $("#_inputBarCode").val();
			$("#_inputBarCode").val(_inputBarCode + 1);
			
			$("#_inputBarCode").focus();
			$("#_inputBarCode").blur();
		}
		function btn2_click() {
			var _inputBarCode = $("#_inputBarCode").val();
			$("#_inputBarCode").val(_inputBarCode + 2);
			
			$("#_inputBarCode").focus();
			$("#_inputBarCode").blur();
		}
		function btn3_click() {
			var _inputBarCode = $("#_inputBarCode").val();
			$("#_inputBarCode").val(_inputBarCode + 3);
			
			$("#_inputBarCode").focus();
			$("#_inputBarCode").blur();
		}
		function btn4_click() {
			var _inputBarCode = $("#_inputBarCode").val();
			$("#_inputBarCode").val(_inputBarCode + 4);
			
			$("#_inputBarCode").focus();
			$("#_inputBarCode").blur();
		}
		function btn5_click() {
			var _inputBarCode = $("#_inputBarCode").val();
			$("#_inputBarCode").val(_inputBarCode + 5);
			
			$("#_inputBarCode").focus();
			$("#_inputBarCode").blur();
		}
		function btn6_click() {
			var _inputBarCode = $("#_inputBarCode").val();
			$("#_inputBarCode").val(_inputBarCode + 6);
			
			$("#_inputBarCode").focus();
			$("#_inputBarCode").blur();
		}
		function btn7_click() {
			var _inputBarCode = $("#_inputBarCode").val();
			$("#_inputBarCode").val(_inputBarCode + 7);
			
			$("#_inputBarCode").focus();
			$("#_inputBarCode").blur();
		}
		function btn8_click() {
			var _inputBarCode = $("#_inputBarCode").val();
			$("#_inputBarCode").val(_inputBarCode + 8);
			
			$("#_inputBarCode").focus();
			$("#_inputBarCode").blur();
		}
		function btn9_click() {
			var _inputBarCode = $("#_inputBarCode").val();
			$("#_inputBarCode").val(_inputBarCode + 9);
			
			$("#_inputBarCode").focus();
			$("#_inputBarCode").blur();
		}
		function btn0_click() {
			var _inputBarCode = $("#_inputBarCode").val();
			$("#_inputBarCode").val(_inputBarCode + 0);
			
			$("#_inputBarCode").focus();
			$("#_inputBarCode").blur();
		}
		function btnDel_click() {
			var _inputBarCode = $("#_inputBarCode").val();
			if(_inputBarCode.length > 0){
				$("#_inputBarCode").val(_inputBarCode.substring(0, _inputBarCode.length - 1));
				
				$("#_inputBarCode").focus();
				$("#_inputBarCode").blur();
			}			
		}
		function addOrderItem(item) {
			 var _len = $("#rounded-corner tr").length;
	         $("#rounded-corner").append('<tr>' +
										 '	<td width="30" class="first center">'+ (_len+1) +'</td>' +
										 '	<td width="230">'+ item.name +'</td>' +
										 '	<td width="60">'+ item.price +'</td>' +
										 '	<td width="100" class="center">' +
										 '		<img width="18px" height="18px" src="${ctx}/static/img/minus.ico">' +
										 '		<input type="text" value="'+ item.quantity +'" style="width: 20px;" class="text ui-widget-content ui-corner-all" />' +
										 '		<img width="18px" height="18px" src="${ctx}/static/img/add.ico">' +
										 '	</td>' +
										 '	<td width="80">123456.99</td>' +
										 '	<td width="35" class="center"><img width="18px" height="18px" src="${ctx}/static/img/delete.ico"></td>' +
										 '</tr>');
	         $('#rounded-corner').tableScroll({height:400});
		}
		var deltr = function delOrderItem() {
			
		}
		$().ready(function() {
			$("#btn99999").click(function() {
				addOrderItem({name:'123',price:98.65,quantity:123});
			});
		
			$('#rounded-corner').tableScroll({height:400});
			
			$("#inputForm").validate({
				rules: {
					_inputBarCode: {
						alNum: true,
						maxlength: 16
					}
				}
			});
			<%// 取消默认焦点选中%>
			$("input:text:first").blur();
			
			$("#btn1,#btn2,#btn3,#btn4,#btn5,#btn6,#btn7,#btn8,#btn9,#btn0,#btnDel,#btnEnter").button();
			
			//------------------------------
			var btn1ClickView;
			$("#btn1").click(function() {
				btn1_click();
			});
			$("#btn1").mousedown(function() {
				btn1ClickView = window.setInterval("btn1_click()", 200);
			});
			$("#btn1").mouseup(function() {
				window.clearInterval(btn1ClickView);
			});
			$("#btn1").mouseleave(function() {
				window.clearInterval(btn1ClickView);
			});
			//------------------------------
			var btn2ClickView;
			$("#btn2").click(function() {
				btn2_click();
			});
			$("#btn2").mousedown(function() {
				btn2ClickView = window.setInterval("btn2_click()", 200);
			});
			$("#btn2").mouseup(function() {
				window.clearInterval(btn2ClickView);
			});
			$("#btn2").mouseleave(function() {
				window.clearInterval(btn2ClickView);
			});
			//------------------------------
			var btn3ClickView;
			$("#btn3").click(function() {
				btn3_click();
			});
			$("#btn3").mousedown(function() {
				btn3ClickView = window.setInterval("btn3_click()", 200);
			});
			$("#btn3").mouseup(function() {
				window.clearInterval(btn3ClickView);
			});
			$("#btn3").mouseleave(function() {
				window.clearInterval(btn3ClickView);
			});
			//------------------------------
			var btn4ClickView;
			$("#btn4").click(function() {
				btn4_click();
			});
			$("#btn4").mousedown(function() {
				btn4ClickView = window.setInterval("btn4_click()", 200);
			});
			$("#btn4").mouseup(function() {
				window.clearInterval(btn4ClickView);
			});
			$("#btn4").mouseleave(function() {
				window.clearInterval(btn4ClickView);
			});
			//------------------------------
			var btn5ClickView;
			$("#btn5").click(function() {
				btn5_click();
			});
			$("#btn5").mousedown(function() {
				btn5ClickView = window.setInterval("btn5_click()", 200);
			});
			$("#btn5").mouseup(function() {
				window.clearInterval(btn5ClickView);
			});
			$("#btn5").mouseleave(function() {
				window.clearInterval(btn5ClickView);
			});
			//------------------------------
			var btn6ClickView;
			$("#btn6").click(function() {
				btn6_click();
			});
			$("#btn6").mousedown(function() {
				btn6ClickView = window.setInterval("btn6_click()", 200);
			});
			$("#btn6").mouseup(function() {
				window.clearInterval(btn6ClickView);
			});
			$("#btn6").mouseleave(function() {
				window.clearInterval(btn6ClickView);
			});
			//------------------------------
			var btn7ClickView;
			$("#btn7").click(function() {
				btn7_click();
			});
			$("#btn7").mousedown(function() {
				btn7ClickView = window.setInterval("btn7_click()", 200);
			});
			$("#btn7").mouseup(function() {
				window.clearInterval(btn7ClickView);
			});
			$("#btn7").mouseleave(function() {
				window.clearInterval(btn7ClickView);
			});
			//------------------------------
			var btn8ClickView;
			$("#btn8").click(function() {
				btn8_click();
			});
			$("#btn8").mousedown(function() {
				btn8ClickView = window.setInterval("btn8_click()", 200);
			});
			$("#btn8").mouseup(function() {
				window.clearInterval(btn8ClickView);
			});
			$("#btn8").mouseleave(function() {
				window.clearInterval(btn8ClickView);
			});
			//------------------------------
			var btn9ClickView;
			$("#btn9").click(function() {
				btn9_click();
			});
			$("#btn9").mousedown(function() {
				btn9ClickView = window.setInterval("btn9_click()", 200);
			});
			$("#btn9").mouseup(function() {
				window.clearInterval(btn9ClickView);
			});
			$("#btn9").mouseleave(function() {
				window.clearInterval(btn9ClickView);
			});
			//------------------------------
			var btn0ClickView;
			$("#btn0").click(function() {
				btn0_click();
			});
			$("#btn0").mousedown(function() {
				btn0ClickView = window.setInterval("btn0_click()", 200);
			});
			$("#btn0").mouseup(function() {
				window.clearInterval(btn0ClickView);
			});
			$("#btn0").mouseleave(function() {
				window.clearInterval(btn0ClickView);
			});
			//------------------------------
			var btnDelClickView;
			$("#btnDel").click(function() {
				btnDel_click();
			});
			$("#btnDel").mousedown(function() {
				btnDelClickView = window.setInterval("btnDel_click()", 200);
			});
			$("#btnDel").mouseup(function() {
				window.clearInterval(btnDelClickView);
			});
			$("#btnDel").mouseleave(function() {
				window.clearInterval(btnDelClickView);
			});
			//------------------------------
			$("#btnEnter").click(function() {
				// ????????????????????????????????????????????????????????????????
			});
			//------------------------------
		});	
		</script>
	</head>
	<body>
		<form:form method="POST" class="form cmxform" id="inputForm" >
		<table>
			<tr>
				<td>
					<table>
						<tr>
							<td class="item_name">货号/条形码:</td>
							<td><input type="text" id="_inputBarCode" name="_inputBarCode" class="text ui-widget-content ui-corner-all" style="width: 170px;text-align:right;" speech="speech" x-webkit-speech="x-webkit-speech" x-webkit-grammar="builtin:translate"/></td>
						</tr>
					<!-- 	<tr>
							<td colspan="2">货品信息</td>
						</tr> -->
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
				<td valign="top" style="padding-top: 6px;">
					<table id="rounded-corner">
						<thead>
							<tr>
								<th class="rounded-left" width="30">序号</th>
								<th width="230">名称</th>
								<th width="60">单价</th>
								<th width="100">数量</th>
								<th width="80">价格</th>
								<th width="40" class="rounded-right">操作</th>
							</tr>
						</thead>
						<tbody>
							
							
							
						</tbody>
					</table>
				</td>
			</tr>
		</table>
		</form:form>
		<button id="btn99999" type="button"><span class="btn_text">999999</span></button>
	</body>
</html>
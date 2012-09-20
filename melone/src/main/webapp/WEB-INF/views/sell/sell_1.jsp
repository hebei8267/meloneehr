<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="page" uri="http://www.opensymphony.com/sitemesh/page"%>
<%@ page import="com.tjhx.globals.Constants" %>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<c:set var="sc_ctx">${ctx}/sc</c:set>
<c:set var="pop_sc_ctx">${ctx}/popsc</c:set>
<html>
	<head>
		<script type="text/javascript" src="${ctx}/static/js/jquery.tablescroll.js"></script>
		<script type="text/javascript" src="${ctx}/static/js/product.json.js"></script>
		<script type="text/javascript" src="${ctx}/static/js/sell.js"></script>
		<script>
		<%// 绘制商品列表%>
		function addOrderItem(item, amount) {
			if(amount > 0) {
				var _len = $("#rounded-corner tr").length;
		        $("#rounded-corner").append('<tr>' +
		        							 '  <input type="hidden" name="barCode" value="' + item.barCode + '" />' +
		        							 '  <input type="hidden" name="amount" value="' + amount + '" />' +
											 '	<td width="30" class="first center _index">'+ (_len+1) +'</td>' +
											 '	<td width="270">'+ item.name +'</td>' +
											 '	<td width="60" class="right">'+ item.retailPrice +'</td>' +
											 '	<td width="40" class="right">' + amount +'</td>' +
											 '	<td width="100" class="right">' + (item.retailPrice*amount) + '</td>' +
											 '	<td width="35" class="center"><img class="itemDelBtn" width="18px" height="18px" src="${ctx}/static/img/delete.ico" style="cursor: pointer;" /></td>' +
											 '</tr>');
		        $('#rounded-corner').tableScroll({height:400});
			}
		    
		    $('#_productDetail').hide();
		    $('#_inputBarCode').val('');
		    $('#_inputBarCode').focus();
		}
		function drawProductDetail(product) {
			$("#_productName").text(product.name);
			$("#_productPrice").text(product.memberPrice);
			$("#_productNum").val(1);
			$("#img").attr("src", "${ctx}/static/img/product/" + product.photoName);
		}
		$().ready(function() {
			<%// 付款-结账%>
			$("#aaaa").click(function() {
				$.ajax({
					type : "post",
					url: "${sc_ctx}/sell/payment",
					data: $("#inputForm").serialize(),
					success:function(data){
						alert(data)
					}
				});
			});
		
			<%// 购买产品对象句柄%>
			var _selProduct = null;
			<%// 购买商品项删除%>
			$(".itemDelBtn").live("click", function(){
				$(this).parent("td").parent("tr").remove();
				
				var _index = 1;
				<%// 重绘制购买商品项的序号%>
				$("._index").each(function(){
					 $(this).text(_index++);
				});
				
				$('#_inputBarCode').val('');
			    $('#_inputBarCode').focus();
			});
			<%// 减少商品数量%>
			$("#_productMinusBtn").click(function() {numMinus();});
			$("#_productNum").keydown(function(event) {
				var _charCode = event.charCode ? event.charCode : event.keyCode;
				if(_charCode == 37 || _charCode == 40) {<%// 37-方向键左 40-方向键下%>
					numMinus();
					return false;
				}
				return true;
			});
			<%// 增加商品数量%>
			$("#_productPlusBtn").click(function() {numPlus();});
			$("#_productNum").keydown(function(event) {
				var _charCode = event.charCode ? event.charCode : event.keyCode;
				if(_charCode == 38 || _charCode == 39) {<%// 38-方向键上 39-方向键右%>
					numPlus();
					return false;
				}
				return true;
			});
			//------------------------------
			<%// 商品数量屏蔽非数字%>
			$("#_productNum").keypress(function(event) {
				var _charCode = event.charCode ? event.charCode : event.keyCode;
				if(_charCode < 48 || _charCode > 57) {
			        return false;
			    } else {
			    	var _productNum = $("#_productNum").val();
			    	if(_productNum.length > 3){
			    		return false;
			    	}
			        return true;
			    }
			});
			<%// 商品数量输入框回车事件%>
			$('#_productNum').bind('keyup', function(event){
				if (event.keyCode=="13"){<%// 回车处理程序%>
					addOrderItem(_selProduct, $('#_productNum').val());
				}
			});
			//------------------------------
			<%// 条形码输入框回车事件%>
			$('#_inputBarCode').bind('keyup', function(event){
				if (event.keyCode=="13"){<%// 回车处理程序%>
					_selProduct = _processProductDetailTable();<%// 绘制商品细节%>
				} else {
					_selProduct = null;
				}
			});
			//------------------------------
			<%// 列表滚动%>
			$('#rounded-corner').tableScroll({height:400});
			//------------------------------
			<%// 表单效验%>
			$("#inputForm").validate({
				rules: {
					_inputBarCode: {
						alNum: true,
						maxlength: 16
					}
				}
			});
			//------------------------------
			$("#btn1,#btn2,#btn3,#btn4,#btn5,#btn6,#btn7,#btn8,#btn9,#btn0,#btnDel,#btnEnter").button();
			//------------------------------
			var btn1ClickView;
			$("#btn1").click(function() {btn1_click();});
			$("#btn1").mousedown(function() {btn1ClickView = window.setInterval("btn1_click()", 200);});
			$("#btn1").mouseup(function() {window.clearInterval(btn1ClickView);});
			$("#btn1").mouseleave(function() {window.clearInterval(btn1ClickView);});
			//------------------------------
			var btn2ClickView;
			$("#btn2").click(function() {btn2_click();});
			$("#btn2").mousedown(function() {btn2ClickView = window.setInterval("btn2_click()", 200);});
			$("#btn2").mouseup(function() {window.clearInterval(btn2ClickView);});
			$("#btn2").mouseleave(function() {window.clearInterval(btn2ClickView);});
			//------------------------------
			var btn3ClickView;
			$("#btn3").click(function() {btn3_click();});
			$("#btn3").mousedown(function() {btn3ClickView = window.setInterval("btn3_click()", 200);});
			$("#btn3").mouseup(function() {window.clearInterval(btn3ClickView);});
			$("#btn3").mouseleave(function() {window.clearInterval(btn3ClickView);});
			//------------------------------
			var btn4ClickView;
			$("#btn4").click(function() {btn4_click();});
			$("#btn4").mousedown(function() {btn4ClickView = window.setInterval("btn4_click()", 200);});
			$("#btn4").mouseup(function() {window.clearInterval(btn4ClickView);});
			$("#btn4").mouseleave(function() {window.clearInterval(btn4ClickView);});
			//------------------------------
			var btn5ClickView;
			$("#btn5").click(function() {btn5_click();});
			$("#btn5").mousedown(function() {btn5ClickView = window.setInterval("btn5_click()", 200);});
			$("#btn5").mouseup(function() {window.clearInterval(btn5ClickView);});
			$("#btn5").mouseleave(function() {window.clearInterval(btn5ClickView);});
			//------------------------------
			var btn6ClickView;
			$("#btn6").click(function() {btn6_click();});
			$("#btn6").mousedown(function() {btn6ClickView = window.setInterval("btn6_click()", 200);});
			$("#btn6").mouseup(function() {window.clearInterval(btn6ClickView);});
			$("#btn6").mouseleave(function() {window.clearInterval(btn6ClickView);});
			//------------------------------
			var btn7ClickView;
			$("#btn7").click(function() {btn7_click();});
			$("#btn7").mousedown(function() {btn7ClickView = window.setInterval("btn7_click()", 200);});
			$("#btn7").mouseup(function() {window.clearInterval(btn7ClickView);
			});
			$("#btn7").mouseleave(function() {window.clearInterval(btn7ClickView);});
			//------------------------------
			var btn8ClickView;
			$("#btn8").click(function() {btn8_click();});
			$("#btn8").mousedown(function() {btn8ClickView = window.setInterval("btn8_click()", 200);});
			$("#btn8").mouseup(function() {window.clearInterval(btn8ClickView);});
			$("#btn8").mouseleave(function() {window.clearInterval(btn8ClickView);});
			//------------------------------
			var btn9ClickView;
			$("#btn9").click(function() {btn9_click();});
			$("#btn9").mousedown(function() {btn9ClickView = window.setInterval("btn9_click()", 200);});
			$("#btn9").mouseup(function() {window.clearInterval(btn9ClickView);});
			$("#btn9").mouseleave(function() {window.clearInterval(btn9ClickView);});
			//------------------------------
			var btn0ClickView;
			$("#btn0").click(function() {btn0_click();});
			$("#btn0").mousedown(function() {btn0ClickView = window.setInterval("btn0_click()", 200);});
			$("#btn0").mouseup(function() {window.clearInterval(btn0ClickView);});
			$("#btn0").mouseleave(function() {window.clearInterval(btn0ClickView);});
			//------------------------------
			var btnDelClickView;
			$("#btnDel").click(function() {btnDel_click();});
			$("#btnDel").mousedown(function() {btnDelClickView = window.setInterval("btnDel_click()", 200);});
			$("#btnDel").mouseup(function() {window.clearInterval(btnDelClickView);});
			$("#btnDel").mouseleave(function() {window.clearInterval(btnDelClickView);});
			//------------------------------
			$("#btnEnter").click(function() {
				_processProductDetailTable();<%// 绘制商品细节%>
			});
			//------------------------------
		});
		</script>
	</head>
	<body>
		<form:form method="POST" class="form cmxform" id="inputForm" ><button id="aaaa" type="button"><span class="btn_text">提交</span></button>
		<table>
			<tr>
				<td>
					<table>
						<tr>
							<td class="item_name">货号/条形码:</td>
							<td><input type="text" id="_inputBarCode" name="_inputBarCode" class="text ui-widget-content ui-corner-all" style="width: 170px;text-align:right;" speech="speech" x-webkit-speech="x-webkit-speech" x-webkit-grammar="builtin:translate"/></td>
						</tr>
						<tr>
							<td colspan="2" height="110px">
								<table id="_productDetail" style="display: none;">
									<tr>
										<td rowspan="3"><img id="img" height="<%=Constants.PHOTO_IMG_HEIGHT/2 %>px" width="<%=Constants.PHOTO_IMG_WIDTH/2 %>px" style="border: 1px;" /></td>
										<td>名称:</td>
										<td id="_productName"></td>
									</tr>
									<tr valign="top">
										<td>单价:</td>
										<td id="_productPrice"></td>
									</tr>
									<tr valign="top">
										<td>数量:</td>
										<td class="center">
											<img id="_productMinusBtn" width="18px" height="18px" src="${ctx}/static/img/minus.ico" style="cursor: pointer;" />
											<input id="_productNum" type="text" style="width: 30px;" class="text ui-widget-content ui-corner-all" />
											<img id="_productPlusBtn" width="18px" height="18px" src="${ctx}/static/img/add.ico" style="cursor: pointer;" />
										</td>
									</tr>
								</table>
							</td>
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
				<td valign="top" style="padding-top: 6px;">
					<table id="rounded-corner">
						<thead>
							<tr>
								<th class="rounded-left" width="30">序号</th>
								<th width="270">名称</th>
								<th width="60">单价</th>
								<th width="40">数量</th>
								<th width="100">价格</th>
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
	</body>
</html>
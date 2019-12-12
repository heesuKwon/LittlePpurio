<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>send page</title>
<script src="https://code.jquery.com/jquery-3.4.1.js"
	integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
	crossorigin="anonymous">
	
</script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/resources/css/send.css"
	type="text/css">
<script type="text/javascript">
	//전화번호 형식 셋팅
	function formatPhoneNumber(phoneNumberString) {
		var cleaned = ('' + phoneNumberString).replace(/\D/g, '')
		var match = cleaned.match(/^(\d{3})(\d{4})(\d{4})$/)
		if (match) {
			return match[1] + '-' + match[2] + '-' + match[3]
		}
		return null
	}

	//전화번호 리스트 생성 및 삭제
	function newElement() {
		var li = document.createElement("li");
		var inputValue = document.getElementById("sendReceiver").value;

		li.setAttribute("id", inputValue);
		inputValue = formatPhoneNumber(inputValue);

		var li_data = document.createTextNode(inputValue);
		li.appendChild(li_data);

		if (inputValue == null) {
			alert("전화번호를 입력해 주세요!");
		} else {
			document.getElementById("myUL").appendChild(li);
		}
		document.getElementById("sendReceiver").value = "";

		var span = document.createElement("SPAN");
		var txt = document.createTextNode("\u00D7");
		span.className = "close";
		span.appendChild(txt);
		li.appendChild(span);

		var close = document.getElementsByClassName("close");

		for (i = 0; i < close.length; i++) {
			close[i].onclick = function() {
				var div = this.parentElement;
				div.remove();
			}
		}

	}

	// 전화번호 리스트 전송
	function textSend() {

		var tempArray = new Array();
		$("ul#myUL li").each(function() {
			tempArray.push($(this).attr("id"));
		});

		var form = document.getElementById("Input");

		for (var i = 0; i < tempArray.length; i++) {
			var phlist = document.createElement("input");
			phlist.setAttribute("type", "hidden");
			phlist.setAttribute("name", "phoneList");
			phlist.setAttribute("value", tempArray[i]);
			form.appendChild(phlist);
		}

		form.submit();

	}
</script>
</head>
<body>
	<div class="contant">
		<div class="left">
			<h3 style="position: absolute; font-size: 40px; margin-left: 70px;">
				받는사람</h3>

			<textarea cols="30" rows="1" class="phonenum"
				placeholder="번호를 입력하세요('-'없이)" id="sendReceiver" name="sendReceiver"></textarea>

			<input class="addButton" type="button" value="+" id="addList"
				onclick="newElement()">
			<div class="list">
				<ul class="ul" id="myUL">
				</ul>
			</div>
		</div>
		<div class=right>
			<h1 id="title"
				style="color: rgba(41, 128, 185); font-size: 50px; margin-left: 70px;">
				Little Ppurio</h1>
			<h2
				style="position: absolute; top: 70px; font-size: 40px; margin-left: 70px;">
				문자 작성</h2>
			<button class="statisticButton">통계보기</button>
			<div class="phoneshape">
				<form name="Input" id="Input" method="post"
					action="/littleppurio/send">
					<input class="sender" type="text" placeholder="보내는 번호 입력">
					<textarea class="textbox" placeholder="문자를 입력하세요" id="sendMessage"
						name="sendMessage"></textarea>
					<div style="position: absolute; bottom: 30px; right: 850px">
						<input class="transferButton" type="button" value="전송" id="send"
							onclick="textSend()">
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>
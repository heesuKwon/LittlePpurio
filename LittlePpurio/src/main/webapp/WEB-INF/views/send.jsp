<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">
<head>
<meta charset="UTF-8">
<title>send page</title>

<script
	src="${pageContext.request.contextPath}/resources/js/jquery-3.4.1.js"></script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/resources/css/send.css"
	type="text/css">
<link href="https://fonts.googleapis.com/css?family=Jua&display=swap"
	rel="stylesheet">


<script type="text/javascript">

	//txt파일 선택
	function openTextFile() {
		var input = document.createElement("input");

		input.type = "file";
		input.accept = "text/plain";

		input.onchange = function(event) {
			processFile(event.target.files[0]);
		};

		input.click();
	}

	function processFile(file) {
		var reader = new FileReader();

		reader.onload = function() {

			var array = reader.result.toString().split("\n");

			for (i in array) {
				txtinput(array[i]);
			}
		};

		reader.readAsText(file, "euc-kr");
	}
	
	//전화번호 검사
	function numberValidation(match){
		
		var validator=['02','031','032','033','041','042','043',
			'044','051','052','053','054','055','061','062','063','064',
			'010']
		var check=0;
		for(i=0;i<validator.length;i++){
			if(match==validator[i]){
				check=1;	
			}
		}
		if(check==1)
			return true;
		else
			return null;
	}
	

	//전화번호 형식 셋팅
	function formatPhoneNumber(phoneNumberString) {
		var cleaned = ('' + phoneNumberString).replace(/\D/g, '');
		var match1 = cleaned.match(/^(\d{3})(\d{4})(\d{4})$/);
		var match2 = cleaned.match(/^(\d{3})(\d{3})(\d{4})$/);
		var match3 = cleaned.match(/^(\d{2})(\d{3})(\d{4})$/);
		var match4 = cleaned.match(/^(\d{2})(\d{4})(\d{4})$/);
		
		console.log("match2:"+match2);
		console.log("match3!!:"+match3);
		console.log("match4!!:"+match4);
		
		if (match1) {
			if(numberValidation(match1[1])==true){
				return match1[1] + '-' + match1[2] + '-' + match1[3];
			}else{
				return null;
			}
		}if(match4 && match4[1]=='02'){
			console.log("match4!!:"+match4)
			if(numberValidation(match4[1])==true){
				return match4[1] + '-' + match4[2] + '-' + match4[3];
			}else{
				return null;
			}
		}if (match2) {
			if(numberValidation(match2[1])==true){
				return match2[1] + '-' + match2[2] + '-' + match2[3];
			}else{
				return null;
			}
		}if(match3){
			if(numberValidation(match3[1])==true){
				return match3[1] + '-' + match3[2] + '-' + match3[3];
			}else{
				return null;
			}
		}
		return null;
	}
	


	//전화번호 리스트 생성 및 삭제
	function newElement() {
		var li = document.createElement("li");
		var inputValue = document.getElementById("sendReceiver").value;

		li.setAttribute("id", inputValue);
		li.setAttribute("class", "list-group-item");
		inputValue = formatPhoneNumber(inputValue);

		var li_data = document.createTextNode(inputValue);
		li.appendChild(li_data);

		if (inputValue == null) {
			alert("올바른 전화번호를 입력해 주세요!");
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

	//txt파일 리스트 추가
	function txtinput(telList) {
		var li = document.createElement("li");
		var inputValue = telList;

		li.setAttribute("id", inputValue);
		li.setAttribute("class", "list-group-item");
		inputValue = formatPhoneNumber(inputValue);

		var li_data = document.createTextNode(inputValue);
		li.appendChild(li_data);

		if (inputValue != null) {
			document.getElementById("myUL").appendChild(li);
		}
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

	//enter키
	function Enter_Check() {
		if (event.keyCode == 13) {
			newElement();
		}
	}

	// 전화번호 리스트 전송
	function textSend() {

		var msg = $("#sendMessage").val().trim();

		if (msg.length == 0) {
			alert("메세지를 입력해주세요!");
		} else {
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

			if (tempArray.length == 0) {
				alert("전화번호를 입력해 주세요!");
			} else {
				form.submit();
			}
		}
	}

	//바이트 체크
	function CheckByte(obj, maxByte) {
		var str = obj.value;
		var str_len = str.length;

		var rbyte = 0;
		var rlen = 0;
		var one_char = "";
		var str2 = "";

		for (var i = 0; i < str_len; i++) {
			one_char = str.charAt(i);
			if (escape(one_char).length > 4) {
				rbyte += 2; //한글2Byte
			} else {
				rbyte++; //영문 등 나머지 1Byte
			}

			if (rbyte <= maxByte) {
				rlen = i + 1; //return할 문자열 갯수
			}
		}

		if (rbyte > maxByte) {
			alert("메세지는 최대 " + maxByte + "byte를 초과할 수 없습니다.")
			str2 = str.substr(0, rlen); //문자열 자르기
			obj.value = str2;
			CheckByte(obj, maxByte);
		} else {
			document.getElementById('byteInfo').innerText = rbyte;
		}
	}
</script>

<script>

	window.onload = function sampleModalPopup(){
        // 팝업 호출 url
        var url = "${pageContext.request.contextPath}/send";
        
        if(window.location.pathname==url){        
	        // 팝업 호출
	        $("#result").modal("show"); 
        }
    }

</script>

</head>

<body>
	<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
		integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
		integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
		integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
		crossorigin="anonymous"></script>

	<div class="contant">
		<div class="left">
			<h2 class="receiveman"
				style="position: absolute; top: 9px; left: 10px">받는 사람</h2>
			<div class="form-group green-border-focus" style="height: 81px;" id="output">
				<input type="tel" class="form-control phonenum" rows="2"
					style="width: 69%; margin-left: 25px; line-height: 0.6;"
					placeholder="번호를 입력하세요" id="sendReceiver" name="sendReceiver"
					onkeydown="Enter_Check()">

				<button class="addButton btn btn-outline-warning" type="button"
					id="addList" onclick="newElement()" style="color: black;">+</button>
			<button onclick= "openTextFile()">txt파일 선택</button>
			</div>

			<div class="list">
				<ul id="myUL" class="list-group ul">

				</ul>
			</div>
		</div>
		<div class=right>
			<h1 class="title" id="title" style="position: absolute; top: -5px">Little
				Ppurio</h1>
			<h2 class="writesms" style="position: absolute; top: 100px">문자
				작성</h2>
			<input type="submit" class="statisticButton btn btn-blue"
				value="통계보기"
				onClick="location.href='${pageContext.request.contextPath}/result?sendNo=${sendNo}'">

			<div class="phoneshape">
				<form name="Input" id="Input" method="post"
					action="/littleppurio/send">
					<input class="sender form-control" style="width: 43%;" type="text"
						readonly value=01062531573 id="sender" name="sender">
					<textarea class="textbox form-control" placeholder="문자를 입력하세요"
						id="sendMessage" name="sendMessage"
						style="position: relative; top: 230px; height: 440px;"
						onKeyup="javascript:CheckByte(this,'90')"></textarea>
					<div class="Byte">
						<span id="byteInfo">0</span> / 90Bytes
					</div>
					<div>
						<button class="transferButton btn btn-blue" type="button"
							id="send" onclick="textSend()">전송</button>

						<!-- Modal -->
						<div class="modal fade" id="result" tabindex="-1" role="dialog"
							aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
							<div class="modal-dialog modal-dialog-centered" role="document">
								<div class="modal-content">
									<div class="modal-header">
										<h5 class="modal-title" id="exampleModalLongTitle">전송 결과</h5>
										<button type="button" class="close" data-dismiss="modal"
											aria-label="Close">
											<span aria-hidden="true">&times;</span>
										</button>
									</div>
									<c:choose>
										<c:when test="${sucs}">
											<div class="modal-body">전송에 성공하였습니다.</div>
										</c:when>
										<c:otherwise>
											<div class="modal-body">전송에 실패하였습니다.</div>
										</c:otherwise>
									</c:choose>
									<div class="modal-footer">
										<button type="button" class="btn btn-secondary"
											data-dismiss="modal">확인</button>
									</div>
								</div>
							</div>
						</div>


					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>
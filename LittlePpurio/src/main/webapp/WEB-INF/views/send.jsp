<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>send page</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" 
integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.4.1.js"
	integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
	crossorigin="anonymous">
	
</script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/resources/css/send.css"
	type="text/css">
<link href="https://fonts.googleapis.com/css?family=Jua&display=swap" rel="stylesheet">

<script type="text/javascript">
	//전화번호 형식 셋팅
	function formatPhoneNumber(phoneNumberString) {
		var cleaned = ('' + phoneNumberString).replace(/\D/g, '')
		var match1 = cleaned.match(/^(\d{3})(\d{4})(\d{4})$/);
		var match2 = cleaned.match(/^(\d{3})(\d{3})(\d{4})$/);
		if (match1) {
			return match1[1] + '-' + match1[2] + '-' + match1[3]
		}
		else if(match2){
			return match2[1] + '-' + match2[2] + '-' + match2[3]
		}
		return null
	}

	//전화번호 리스트 생성 및 삭제
	function newElement() {
		var li = document.createElement("li");
		var inputValue = document.getElementById("sendReceiver").value;

		li.setAttribute("id", inputValue);
		li.setAttribute("class","list-group-item");
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
	//enter키
	function Enter_Check() {
		if (event.keyCode == 13) {
			newElement();
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

	$('#Goresult').click(function() {
		document.location.href = "${pageContext.request.contextPath}/result";
	});
	
	function CheckByte(obj, maxByte)
	{
	    var str = obj.value;
	    var str_len = str.length;


	    var rbyte = 0;
	    var rlen = 0;
	    var one_char = "";
	    var str2 = "";


	    for(var i=0; i<str_len; i++)
	    {
	        one_char = str.charAt(i);
	        if(escape(one_char).length > 4)
	        {
	            rbyte += 2;                                         //한글2Byte
	        }
	        else
	        {
	            rbyte++;                                            //영문 등 나머지 1Byte
	        }


	        if(rbyte <= maxByte)
	        {
	            rlen = i+1;                                          //return할 문자열 갯수
	        }
	     }


	     if(rbyte > maxByte)
	     {
	  // alert("한글 "+(maxByte/2)+"자 / 영문 "+maxByte+"자를 초과 입력할 수 없습니다.");
	  alert("메세지는 최대 " + maxByte + "byte를 초과할 수 없습니다.")
	  str2 = str.substr(0,rlen);                                  //문자열 자르기
	  obj.value = str2;
	  CheckByte(obj, maxByte);
	     }
	     else
	     {
	        document.getElementById('byteInfo').innerText = rbyte;
	     }
	}
	
	
</script>
</head>
<body>
	<div class="contant">
		<div class="left">
			<h2 class ="receiveman" style="position:absolute; top:9px; left:10px">
				받는 사람</h2>
			<div class="form-group green-border-focus" style="height: 81px;">
			<input type="tel" class="form-control phonenum" rows="2" style="width: 69%; margin-left:25px; line-height:0.6;"
				placeholder="번호를 입력하세요('-'없이)" id="sendReceiver" name="sendReceiver" onkeydown="Enter_Check()" >
				
				
				
				
			<button class="addButton btn btn-outline-warning" type="button" id="addList" onclick="newElement()" style="color:black;">+</button>
			</div>

			<div class="list">
				<ul id="myUL" class="list-group ul">
				
				</ul>
			</div>
		</div>
		<div class=right>
			<h1 class="title" id="title" style="position: absolute; top: -5px">Little Ppurio</h1>
			<h2 class= "writesms" style="position: absolute; top: 100px">
				문자 작성</h2>
			<button class="statisticButton btn btn-blue" id="Goresult" name="Goresult">통계보기</button>
			<div class="phoneshape">
				<form name="Input" id="Input" method="post"
					action="/littleppurio/send">
					<input class="sender form-control" style="width:43%;" type="text" placeholder="발신 번호 입력" id="sender" name="sender">
					<textarea class="textbox form-control" placeholder="문자를 입력하세요" id="sendMessage"
						name="sendMessage" style="position: relative; top:230px; height: 440px;" onKeyup="javascript:CheckByte(this,'90')"></textarea>
						<div class="Byte"><span id="byteInfo">0</span> / 90Bytes</div>
					<div>
						<input class="transferButton btn btn-blue" type="button" value="전송" id="send"
							onclick="textSend()">
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>
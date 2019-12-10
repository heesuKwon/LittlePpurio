<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>send page</title>
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/send.css" type="text/css">
</head>
<body>
	<form name="Input" id="Input" method="post" action="/littleppurio/send">
	<div class="contant">
        <div class="left">
            <h3 style="position: absolute;  font-size: 40px; margin-left:70px;"> 받는사람 </h3>
<script>
 
 var myNodelist = document.getElementsByTagName("LI");
 var i;
 for (i = 0; i < myNodelist.length; i++) {
   var span = document.createElement("SPAN");
   var txt = document.createTextNode("\u00D7");
   span.className = "close";
   span.appendChild(txt);
   myNodelist[i].appendChild(span);
 }
 
 
 var close = document.getElementsByClassName("close");
 var i;
 for (i = 0; i < close.length; i++) {
   close[i].onclick = function() {
     var div = this.parentElement;
     div.style.display = "none";
   }
 }
 
 function formatPhoneNumber(phoneNumberString) {
	  var cleaned = ('' + phoneNumberString).replace(/\D/g, '')
	  var match = cleaned.match(/^(\d{3})(\d{4})(\d{4})$/)
	  if (match) {
	    return  match[1] + '-' + match[2] + '-' + match[3]
	  }
	  return null
	}
 
 
function newElement() {

	
	var phonelist=[];
	
  var li = document.createElement("li");
  var inputValue = document.getElementById("sendReceiver").value;
  phonelist.push(inputValue);
  inputValue=formatPhoneNumber(inputValue);
  var t = document.createTextNode(inputValue);
  li.appendChild(t);
  if (inputValue === null) {
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

  for (i = 0; i < close.length; i++) {
    close[i].onclick = function() {
      var div = this.parentElement;
      div.style.display = "none";
    }
  }
  
  console.log(phonelist)
  return phonelist
  
}
</script>
            
                   <textarea cols="30", rows="1.5" class ="phonenum" type="tel" placeholder="번호를 입력하세요('-'없이)" id="sendReceiver" name="sendReceiver"></textarea>
				
<!-- 				<span onclick="newElement()" class=addButton">+</span> -->

                 <input class="addButton" type="button" value="+" id="addList" onclick="newElement()" name="phoneList" id="phoneList">
                <div class="list">
                    <ul class="ul" id="myUL">
                        <li>
                        </li>
                    </ul>
                </div>
        	</div>
        <div class=right>
                <h1 id="title" style="color:rgba(41,128,185); font-size: 50px; margin-left:70px;"> Little Ppurio </h1>
                <h2 style="position: absolute; top:70px; font-size: 40px; margin-left:70px;"> 문자 작성 </h2>
                <div style = "position: relative; float: right; left:1000px">
                        <form action="/littleppurio/send">
                        <input class="statisticButton" type="button" value="통계보기">
                        </form>
                </div>
                <div class="phoneshape">

                    <input class= "sender" type="text" placeholder="보내는 번호 입력">
                    <textarea class="textbox" placeholder="문자를 입력하세요" id="sendMessage" name="sendMessage" ></textarea>
                    <div style="position: absolute; bottom: 50px; right:680px">
                        <input class="transferButton" type="submit" value="전송" id="send">
                    </div>                  
                </div>
        </div>
    </div>
    </form>
</body>
</html>
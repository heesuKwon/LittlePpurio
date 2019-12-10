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
                <input class ="phonenum" type="text" placeholder="번호를 입력하세요" id="sendReceiver" name="sendReceiver">
                <input class="addButton" type="button" value="+">
                <div class="list">
                    <ul class="ul">
                        <li>
                            010-8353-4553
                        </li>
                    </ul>


                </div>
        </div>
        <div class=right>
                <h1 id="title" style="color:rgba(41,128,185); font-size: 50px; margin-left:70px;"> Little Ppurio </h1>
                <h2 style="position: absolute; top:70px; font-size: 40px; margin-left:70px;"> 문자 작성 </h2>
                <div style = "position: relative; float: right; left:1000px">
                        <form action="/littleppurio/send">
                        <input class="statisticButton" type="button" value="통계보기" onClick="location.href=/littleppurio/result;">
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
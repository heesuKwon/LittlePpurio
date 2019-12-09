<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>statistic page</title>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.4.0/Chart.min.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/result.css" type="text/css">
</head>
<body>
<div lang="ko" style="background-color: rgb(255,255,255)">
	<h1 id="title" style="color:rgba(41,128,185); font-size: 50px; margin-left:70px;"> Little Ppurio </h1>
	<h2 style="position: relative; top:-20px; font-size: 40px; margin-left:70px;"> 통계 보기</h2>
	<div style = "position: relative; top: -10vh; float: right;">
		<form action="a.html">
		<input class="sendButton" type="button" value="발송하기">
	</div>
</div>
<div align="center" class="chart-container">
	
	<div class="bar-container">
		<canvas id="myChart" style="padding-top: 40px; padding-right: 30px"></canvas>
	</div>
	
	<div class="costText">
		<div align ="center" lang="ko" style= "margin-top: 0px; font-style: bold;">
			<p>총 전송량은 ${smsCnt} 입니다.</p>
			<p>이번달 요금은<br> </p>
			<p style="font-size:1.3em; font-style:bold; margin-left:60px; color:rgb(243,156,18); float:left">10,9000원&nbsp;</p> <p style="float:left; margin-top:50px;">입니다<br></p>
			<p style="float:left; margin-left:20px">지난달 대비&nbsp;</p> <p style="float: left; color:rgb(192,57,43)">1000원 증가</p>
		</div>
		
	</div>
	
	<div class="pie-container">
	<canvas id="pie"></canvas>
	</div>
	
	<div class="horizontal-container">
	<canvas id="horizontalBar" style="padding-top: 40px; padding-right:30px"></canvas>
	</div>
	
</div>
</body>
<script>
var barContext = document.getElementById("myChart").getContext('2d');
var myChart = new Chart(barContext, {
    type: 'bar',
    data: {
        labels: ["1월", "2월", "3월", "4월", "5월", "6월","7월","8월","9월","10월","11월","12월"],
        datasets: [{
            label: '월별 지출 현황',
            data: [109000, 119000, 300000, 150000, 200000, 130000, 123000, 144000, 113000, 219000, 172000, 169000],
            backgroundColor: [
                'rgba(255, 99, 132, 0.7)',
                'rgba(54, 162, 235, 0.7)',
                'rgba(255, 206, 86, 0.7)',
                'rgba(75, 192, 192, 0.7)',
                'rgba(153, 102, 255, 0.7)',
                'rgba(255, 159, 64, 0.7)',
				'rgba(255, 99, 132, 0.7)',
                'rgba(54, 162, 235, 0.7)',
                'rgba(255, 206, 86, 0.7)',
                'rgba(75, 192, 192, 0.7)',
                'rgba(153, 102, 255, 0.7)',
                'rgba(255, 159, 64, 0.7)'
            ],
            borderColor: [
                'rgba(255,99,132,1)',
                'rgba(54, 162, 235, 1)',
                'rgba(255, 206, 86, 1)',
                'rgba(75, 192, 192, 1)',
                'rgba(153, 102, 255, 1)',
                'rgba(255, 159, 64, 1)',
				'rgba(255,99,132,1)',
                'rgba(54, 162, 235, 1)',
                'rgba(255, 206, 86, 1)',
                'rgba(75, 192, 192, 1)',
                'rgba(153, 102, 255, 1)',
                'rgba(255, 159, 64, 1)'
            ],
            borderWidth: 1
        }]
    },
    options: {
        maintainAspectRatio: true, // default value. false일 경우 포함된 div의 크기에 맞춰서 그려짐.
        scales: {
			ticks:{
				display: false,
				maxTicksLimit:0,
				beginAtZero: false
			}
        }
    }
});

var pieContext = document.getElementById("pie").getContext('2d');
var pieChart = new Chart(pieContext,{
	type: 'doughnut',
	data: {
		labels: ["성공","실패"],
		datasets:[{
			label: '성공률',
			data: [95,5],
			backgroundColor:[
				'rgba(54,162,235,0.7)',
				'rgba(255, 99, 132, 0.7)'
				
			],
			borderColor: [
				'rgba(54,162,235,1)',
				'rgba(255,99,132,1)'
				
			],
			borderWidth: 1
		}]
	},
	options: {
		maintainAspectRatio: true,
		scales: {
			ticks:{
				display: false,
				maxTicksLimit:0,
				beginAtZero: false
			}
			
		}
	}
});

var horizontalContext = document.getElementById("horizontalBar").getContext('2d');
var horizontalChart = new Chart(horizontalContext,{
	type: 'horizontalBar',
	data: {
		labels: ["잘못된 \n전화번호", "형식 오류", "없는 번호", " 타임 아웃"],
		datasets: [{
			label: '실패 요인 통계',
			data: [10,2,4,7],
			backgroundColor:[
				'rgba(255,99,132,0.7)',
				'rgba(54,162,235,0.7)',
				'rgba(255, 206, 86, 0.7)',
                'rgba(75, 192, 192, 0.7)'
			],
			borderColor: [
				'rgba(255,99,132,1)',
				'rgba(54,162,235,1)',
				'rgba(255, 206, 86, 0.7)',
                'rgba(75, 192, 192, 0.7)'
			],
		
			borderWidth: 1
			}]
	},
	options: {
		maintainAspectRatio: true,
		scales: {
			ticks:{
				display: false,
				maxTicksLimit:0,
				beginAtZero: false
			}
		}
	}
});
</script>
</html>
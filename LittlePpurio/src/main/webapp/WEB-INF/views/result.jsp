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

<link rel="stylesheet"
	href="${pageContext.request.contextPath }/resources/css/result.css"
	type="text/css">

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">
	
<link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
  <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">
  
  <script
	src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.4.0/Chart.min.js"></script>

<script
	src="${pageContext.request.contextPath}/resources/js/jquery-3.4.1.js"></script>


<style>
.btn-blue {
	color: rgb(255, 255, 255);
	background-color: rgb(52, 152, 219);
}

.btn-blue:hover {
	color: rgb(52, 152, 219);
	border-color: rgb(52, 152, 219);
	background-color: white;
}
</style>

  <!-- Custom styles for this template-->
  <link href="css/sb-admin-2.min.css" rel="stylesheet">
  
    <div lang="ko" class="header" style="background-color: rgb(255, 255, 255);	padding: 20px;">
		<h1 id="title" 	style=" color: rgba(41, 128, 185); font-size: 50px;	margin: 10px 10px 10px 70px;" >Little Ppurio</h1>
		<h2 style="font-size: 35px;	margin-left: 70px;">통계 보기</h2>
		<div style="position: relative; top: -10vh; float: right;" id="output">
			<button class="btn btn-blue"
				onClick="location.href='${pageContext.request.contextPath}/'">문자보내기</button>
		</div>
	</div>
	</head>
  <body>

    <div class="container">
        <div class="row">
        <div class="col-md-3">
	<!-- Earnings (Monthly) Card Example -->
            <div class="mb-4">
              <div class="card border-left-primary shadow h-100 py-2">
                <div class="card-body">
                  <div class="row no-gutters align-items-center">
                    <div class="col mr-2">
                      <div class="text-xs font-weight-bold text-uppercase mb-1"style="color:rgb(52, 152, 219)" >총 발송건</div>
                      <div class="h5 mb-0 font-weight-bold text-gray-800">${result_t}</div>
                    </div>
                    <div class="col-auto">
                      <i class="fas fa-calendar fa-2x text-gray-300"></i>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            </div>

			<div class="col-md-3">
            <!-- Earnings (Monthly) Card Example -->
            <div class="mb-4">
              <div class="card border-left-success shadow h-100 py-2">
                <div class="card-body">
                  <div class="row no-gutters align-items-center">
                    <div class="col mr-2">
                      <div class="text-xs font-weight-bold text-success text-uppercase mb-1">발송성공</div>
                      <div class="h5 mb-0 font-weight-bold text-gray-800">${result_s}</div>
                    </div>
                    <div class="col-auto">
                      <i class="fas fa-dollar-sign fa-2x text-gray-300"></i>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            </div>
            
            <div class="col-md-3">
            <!-- Earnings (Monthly) Card Example -->
            <div class="mb-4">
              <div class="card border-left-info shadow h-100 py-2">
                <div class="card-body">
                  <div class="row no-gutters align-items-center">
                    <div class="col mr-2">
                      <div class="text-xs font-weight-bold text-info text-uppercase mb-1">발송중</div>
                      <div class="row no-gutters align-items-center">
                        <div class="col-auto">
                          <div class="h5 mb-0 mr-3 font-weight-bold text-gray-800">${result_i}</div>
                        </div>
                      </div>
                    </div>
                    <div class="col-auto">
                      <i class="fas fa-clipboard-list fa-2x text-gray-300"></i>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            </div>

			<div class="col-md-3">
            <!-- Pending Requests Card Example -->
         <div class="mb-4">
              <div class="card border-left-warning shadow h-100 py-2">
                <div class="card-body">
                  <div class="row no-gutters align-items-center">
                    <div class="col mr-2">
                      <div class="text-xs font-weight-bold text-warning text-uppercase mb-1">발송실패</div>
                      <div class="h5 mb-0 font-weight-bold text-gray-800">${result_f}</div>
                    </div>
                    <div class="col-auto">
                      <i class="fas fa-comments fa-2x text-gray-300"></i>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
          </div>
          
    
       <div class="row">
            <!-- Pie Chart -->
            <div class="col-xl-4 col-lg-5">
              <div class="card shadow mb-4">
                <!-- Card Header - Dropdown -->
                <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                  <h6 class="m-0 font-weight-bold"style="color:rgb(52, 152, 219)" >전체 성공률</h6>
                  <div class="dropdown no-arrow">
                  </div>
                </div>
                <!-- Card Body -->
                <div class="card-body">
                  <div class="chart-pie pt-4 pb-2">
                    <canvas id="myPieChart"></canvas>
                  </div>
                </div>
              </div>
            </div>
          <div class="col-md-6">
                <!-- Bar Chart -->
              <div class="card shadow mb-4" style="width:654px; margin-left:73px">
                <div class="card-header py-3">
                  <h6 class="m-0 font-weight-bold" style="color:rgb(52, 152, 219)">전체 실패 통계 차트</h6>
                </div>
                <div class="card-body">
                  <div class="chart-bar">
                    <canvas id="myBarChart"></canvas>
                  </div>
                  <hr>
                </div>
              </div>
             </div>
   		 </div>
   		 </div>
          <script>
          var pieContext = document.getElementById("myPieChart").getContext('2d');
          var pieChart = new Chart(pieContext,{
          	type: 'doughnut',
          	data: {
          		labels: ["성공","실패"],
          		datasets:[{
          			label: '성공률',
          			data: [${success},${fail}],
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
          
          var horizontalContext = document.getElementById("myBarChart").getContext('2d');
          var horizontalChart = new Chart(horizontalContext,{
          	type: 'horizontalBar',
          	data: {
          		labels: ["잘못된 \n전화번호", "형식 오류", "기타 에러", " 타임 아웃"],
          		datasets: [{
          			label: '실패 요인 통계',
          			data: [${w_4410},${w_4413},${w_4420},${w_4421}],
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
          			xAxes:[{
          				ticks:{
          					display: true,
          					maxTicksLimit:0,
          					beginAtZero: true
          				}
          			}]
          		}
          	}
          });
          </script>
          </body>

          
<!--<script
	src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.4.0/Chart.min.js"></script>

<script
	src="${pageContext.request.contextPath}/resources/js/jquery-3.4.1.js"></script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/resources/css/result.css"
	type="text/css">

</head>
<body>
	<div lang="ko" class="header">
		<h1 id="title">Little Ppurio</h1>
		<h2>통계 보기</h2>
		<div style="position: relative; top: -10vh; float: right;" id="output">
			<button class="btn btn-blue"
				onClick="location.href='${pageContext.request.contextPath}/'">문자보내기</button>
		</div>
	</div>
	<div align="center" class="chart-container">
	<input type="hidden" value="${param.sendNo}">


	<div class="top" style="position:relative">

			<table class="table">
				<thead class="thead-dark">
				<tr>
					<th scope="col">총 발송건</th>
				</tr>
				</thead>
				<tbody>
				<tr>
					<th scope="col">${result_t}</th>
				</tr>
				</tbody>
				</table>
				<table class="table">
				<thead class="thead-dark">
					<tr>
						<th scope="col">발송성공</th>
						<th scope="col">발송중</th>
						<th scope="col">발송실패</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>${result_s}</td>
						<td>${result_i}</td>
						<td>${result_f}</td>
					</tr>
				</tbody>
			</table>

		</div>

		<div class="middle">
			<div class="bar-container">
				<canvas id="myChart" style="padding-top: 40px; padding-right: 30px"></canvas>
			</div>!-->

			<!-- <div class="costText">
				<div align="center" lang="ko"
					style="margin-top: 108px; font-style: bold;">
					<p>총 전송량은 ${smsCnt} 건 입니다.</p>
					<p>
						이번달 요금은<br>
					</p>
					<p
						style="font-size: 1.3em; font-style: bold; color: rgb(243, 156, 18);">
						&nbsp;<br>${price}원&nbsp;<br>
					</p>
					<p style="margin-top: 50px;">
						입니다<br>
					</p>
					<p style="float: left; margin-left: 20px">지난달 대비&nbsp;</p>
					<p style="float: left; color: rgb(192, 57, 43)">1000원 증가</p>
				</div>

			</div> -->
	<!--	</div>

		<div class=bottom>
			<div class="pie-container">
				<canvas id="pie">
				<div>
				<p>성공률: ${success}</p>
				</div>
				</canvas>
			</div>

			<div class="horizontal-container">
				<canvas id="horizontalBar"
					style="padding-top: 40px; padding-right: 30px"></canvas>
			</div>
		</div>

	</div>
</body>
<script>
var lineContext = document.getElementById("myChart").getContext('2d');


var myChart = new Chart(lineContext, {
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
			data: [${success},${fail}],
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
		labels: ["잘못된 \n전화번호", "형식 오류", "기타 에러", " 타임 아웃"],
		datasets: [{
			label: '실패 요인 통계',
			data: [${w_4410},${w_4413},${w_4420},${w_4421}],
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
			xAxes:[{
				ticks:{
					display: true,
					maxTicksLimit:0,
					beginAtZero: true
				}
			}]
		}
	}
});
</script>!-->
</html>
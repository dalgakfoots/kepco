<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/com.css' />">
<!-- <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> -->

<title>SB Admin - Dashboard</title>
 <!-- Custom fonts for this template-->
  <link href="<c:url value='/css/egovframework/com/dash/vendor/fontawesome-free/css/all.min.css'/>" rel="stylesheet" type="text/css">
  <link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/com.css' />">
  <!-- Page level plugin CSS-->
  <link href="<c:url value='/css/egovframework/com/dash/vendor/datatables/dataTables.bootstrap4.css'/>" rel="stylesheet">

  <!-- Custom styles for this template-->
  <link href="<c:url value='/css/egovframework/com/dash/css/sb-admin.css'/>" rel="stylesheet">
  <script type="text/javaScript" language="javascript">
  var myLineChart = null;
  function loadChart(rslt) {

	  Chart.defaults.global.defaultFontFamily = '-apple-system,system-ui,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,sans-serif';
	  Chart.defaults.global.defaultFontColor = '#292b2c';
	  document.getElementById("myAreaChart").value = "";
	  var ctx = document.getElementById("myAreaChart");
	  

	  const backgroundColor = [
		  'rgba(255, 0, 0, 0)',
		  'rgba(255, 94, 0, 0)',
		  'rgba(255, 228, 0, 0)',
		  'rgba(0, 255, 0, 0)',
		  'rgba(0, 0, 255, 0)',
		  'rgba(5, 0, 153, 0)',
		  'rgba(255, 192, 203, 0)',
		  'rgba(204, 204, 204, 0)',
		  'rgba(153, 204, 153, 0)',
		  'rgba(000, 255, 255, 0)',
      ];
      const borderColor = [
    	  'rgba(255, 0, 0, 1)',
		  'rgba(255, 94, 0, 1)',
		  'rgba(255, 228, 0, 1)',
		  'rgba(0, 255, 0, 1)',
		  'rgba(0, 0, 255, 1)',
		  'rgba(5, 0, 153, 1)',
		  'rgba(255, 192, 203, 1)',
		  'rgba(204, 204, 204, 1)',
		  'rgba(153, 204, 153, 1)',
		  'rgba(000, 255, 255, 1)',
      ];
      
      var maxData = 0;
      
      rslt.graghList.forEach(team => {
    	  var data = team.data;
    	  if (maxData < data[data.length-1]) {
    		  maxData = data[data.length-1];
    	  }
      });
     
      /* var yLimit = 5000; */
     
     
     	var yLimit = (Math.trunc(maxData / 1000)+1) * 1000;
     
/*      switch (Math.trunc(maxData / 5000)){
     	case 0 : yLimit = 5000; break;
     	case 1 : yLimit = 10000; break;
     	case 2 : yLimit = 15000; break;
     	case 3 : yLimit = 20000; break;
     	case 4 : yLimit = 25000; break;
     	case 5 : yLimit = 30000; break;
     	case 6 : yLimit = 35000; break;
     	default : yLimit = 35000; break;
     }  */
       
     if (myLineChart) myLineChart.destroy();
	console.log()
   myLineChart = new Chart(ctx, {
	    type: 'line',
	    data: {
	      labels:rslt.dateList,
	      		/* rslt.dateList.filter((e, i) => {
		    		  console.log("i : ", i);
	    			  console.log("e : ", e);
	    			  console.log("rslt.dateList.length - rslt.dateList.length % 5  : " , rslt.dateList.length - (rslt.dateList.length-1) % 5 );
	    			  console.log("rslt.dateList.length: " , rslt.dateList.length);
	    			  console.log("rslt.dateList.length % 5  : " , rslt.dateList.length % 5 );
		    		  if (i%5 === 0 ) {
		    			  return e;
		    		  } else if (rslt.dateList.length - rslt.dateList.length % 5 < i) {
		    			  
		    			  return e;
		    		  }
		      		}), */
	      datasets: rslt.graghList.map( (e, index) => {
	    	  return {
	    		label: e.team_name,
	  	        lineTension: 0.2,
	  	        backgroundColor: backgroundColor[index],
	            borderColor: borderColor[index],
	            borderWidth: 2,
	  	        pointRadius: 1,
	  	      	pointHoverRadius: 3,
	  	      	
	  	        pointBackgroundColor: borderColor[index],
	  	        pointBorderColor: "rgba(255,255,255,0.8)",
	  	        pointHoverBackgroundColor: borderColor[index],
	  	        pointHitRadius: 50, 
	  	        pointBorderWidth: 0.5,
	  	        data: e.data,
	  	      /* data: e.data.filter((d, i) => {
	    		  if (i%5 === 0 ) {
	    			  return d;
	    		  } else if (e.data.length -e.data.length % 5 < i) {
	    			  return d;
	    		  }
	      		}), */
	    	  }
	      })
	    },
	    options: {
		     scales: {
		        xAxes: [{
		          time: {
		            unit: 'date'
		          },
		          gridLines: {
		            display: false
		          },
		          ticks: {
		            maxTicksLimit: 10
		          }
		        }],
		        yAxes: [{
		          ticks: {
		            min: 0,
		            max: yLimit,
		            maxTicksLimit: 10
		          },
		          gridLines: {
		            color: "rgba(0, 0, 0, .125)",
		          }
		        }],
		      },
		      plugins: {
			      legend: {
			    	  display: true,
			    	  position : "top",
			    	  align : "center",
			    	  labels: {
		                  color: 'rgb(255, 99, 132)',
		                  /* usePointStyle: true, */
		              }
			      }
		      }
	    } 
	  });
  }

  	function intervalTest() {
  		ajaxTest()
  		let timerId = setInterval(() => ajaxTest(), 1000 * 60);
  	}
 

  	function ajaxTest() {
	 		var trainingId = document.getElementById("trainingId").value;
	 		
  		$.ajax({
  			type:"get",
  			url:"<c:url value='/dash/Dashboard.do?trainingId="+trainingId+"'/>",
  			
  			dataType:'json',
  			success:function(returnData, status){
  				if(status == "success") {
  					
  					var updateTime = document.getElementById("updateTime"); 
  					updateTime.innerText = "Updated today at "+returnData.updateTime;
  					ajax_callback(returnData);
  				}else{ alert("ERROR!");return;} 
  			}
  		}); 
  	}
 
  	function ajax_callback(rslt){
  		loadChart(rslt);
  		var rankList = rslt.rankList;
  		
  		var table = document.getElementById("list_body");
  		var tr = table.getElementsByTagName("tr");
  		
  		var table = document.getElementById("tableLength").value;
   		if (parseInt(tr.length) >1) {
   			removeTable(rankList);
  		} else {
  			/* removeTableByNull(rankList); */
  		}
  	}
  	
  	/* 
 	function removeTableByNull(rankList) {
  		var table = document.getElementById("list_body");
  		var tr = table.getElementsByTagName("tr");
  		
 		if (tr.length > 0) {
 			table.deleteRow(0);	
 		}
 		if(rankList.length <1) {
 			addTableRowByNull(rankList);	
 		} else {
 			addTableRow(rankList);
 		} 
 		
  	}
 	 */
	/* function addTableRowByNull(rankList) {
			var table = document.getElementById("list_body");
			const newRow = table.insertRow();
  			const newCell1 = newRow.insertCell(0, 1, 2, 3, 4, 5, 6, 7);
  			newCell1.innerHtml = "<td valign=\"top\" colspan=\"8\" class=\"dataTables_empty\">No data available in table</td>";
			
	} */
  	
  	function removeTable(rankList) {
		var table = document.getElementById("list_body");
  		var tr = table.getElementsByTagName("tr");
 		if (tr.length > 0) {
 			var length = tr.length;
 			for(let i=0; i < length ; i++) {
 				table.deleteRow(0);
 			}
 		}
		addTableRow(rankList);
  	}

  	function addTableRow(rankList) {
  			var table = document.getElementById("list_body");
  			for (let i in rankList) {
  					const newRow = table.insertRow();
  	  	  			const newCell1 = newRow.insertCell(0);
  	  	  			const newCell2 = newRow.insertCell(1);
  	  	  			const newCell3 = newRow.insertCell(2);
  	  	  			const newCell4 = newRow.insertCell(3);
  	  	  			const newCell5 = newRow.insertCell(4);
  	  	  			const newCell6 = newRow.insertCell(5);
  	  	  			const newCell7 = newRow.insertCell(6);
  	  	  			const newCell8 = newRow.insertCell(7);
  	  	  			newCell1.innerText = parseInt(i)+1;
  	  	  			newCell2.innerText = rankList[i].team_name;
  	  	  			newCell3.innerText = rankList[i].type_1;
  	  	  			newCell4.innerText = rankList[i].type_2;
  	  	  			newCell5.innerText = rankList[i].type_3;
  	  	  			newCell6.innerText = rankList[i].type_4;
  	  	  			newCell7.innerText = rankList[i].type_5;
  	  	  			newCell8.innerText = rankList[i].total;
  			}
  	}
  	
  	
  </script>
</head>
<!-- <body onLoad="javascript:intervalTest()"> -->
<body>
<div class="board">
  <div id="wrapper">

    <div id="content-wrapper">

      <div class="container-fluid">

        <!-- Breadcrumbs-->
        <ol class="breadcrumb">
          <li class="breadcrumb-item">
            <a href="<c:url value='/dash/EgovDashboardTraining.do' />">Dashboard</a>
          </li>
        <li class="breadcrumb-item active"><c:out value="${training_name}"/></li>
        </ol>

        <!-- Area Chart Example-->
        <div class="card mb-3">
          <div class="card-header">
            <i class="fas fa-chart-area"></i>
            Area Chart</div>
          <div class="card-body">
            <canvas id="myAreaChart" width="100%" height="30"></canvas>
          </div>
          <div id="updateTime" class="card-footer small text-muted"></div>
        </div>

        <!-- DataTables Example -->
        <div class="card mb-3">
          <div class="card-header">
            <i class="fas fa-table"></i>
            실시간 훈련 순위</div>
            <!-- <div class="card-footer small text-muted">Updated yesterday at 11:59 PM</div> -->
          <div class="card-body">
            <div class="table-responsive">
              <table class="table table-bordered" width="100%" cellspacing="0">
                <thead>
                  <tr>
                    <th>순위</th>
                    <th>팀명</th>
                    <th>DDos</th>
                    <th>렌섬웨어</th>
                    <th>웹 해킹</th>
                    <th>APT01</th>
                    <th>APT02</th>
                    <th>총점</th>
                  </tr>
                </thead>
               
                <tbody id="list_body">
             
            	<c:forEach var="item" items="${rankList}" varStatus="status">
					<tr>
						<td><c:out value="${status.index+1}"/></td>
	                    <td><c:out value="${item.team_name}"/></td>
	                    <td><c:out value="${item.type_1}"/></td>
	                    <td><c:out value="${item.type_2}"/></td>
	                    <td><c:out value="${item.type_3}"/></td>
	                    <td><c:out value="${item.type_4}"/></td>
	                    <td><c:out value="${item.type_5}"/></td>
	                    <td><c:out value="${item.total}"/></td>
					</tr>
				</c:forEach>
                </tbody>
              </table>
            </div>
          </div>
          
        </div>

      </div>
      <!-- /.container-fluid -->


    </div>
    <!-- /.content-wrapper -->
    <input type="hidden" id="tableLength" value="<c:out value="${rankList}"/>">
	<input type="hidden" id="trainingId" value="<c:out value="${training_id}"/>">
  </div>

 </div>
<div>

  <!-- Bootstrap core JavaScript-->
  <script src="<c:url value='/css/egovframework/com/dash/vendor/jquery/jquery.min.js'/>"></script>
  <script src="<c:url value='/css/egovframework/com/dash/vendor/bootstrap/js/bootstrap.bundle.min.js'/>"></script>

  <!-- Core plugin JavaScript-->
  <script src="<c:url value='/css/egovframework/com/dash/vendor/jquery-easing/jquery.easing.min.js'/>"></script>

  <!-- Page level plugin JavaScript-->
  <script src="<c:url value='/css/egovframework/com/dash/vendor/chart.js/Chart.min.js'/>"></script>
  <script src="<c:url value='/css/egovframework/com/dash/vendor/datatables/jquery.dataTables.js'/>"></script>
  <script src="<c:url value='/css/egovframework/com/dash/vendor/datatables/dataTables.bootstrap4.js'/>"></script>

  <!-- Custom scripts for all pages-->
  <script src="<c:url value='/css/egovframework/com/dash/js/sb-admin.min.js'/>"></script>

  <!-- Demo scripts for this page-->
  <script src="<c:url value='/css/egovframework/com/dash/js/demo/datatables-demo.js'/>"></script>
  <script src="<c:url value='/css/egovframework/com/dash/css/sb-admin.css'/>"></script>
  

</body>
</html>
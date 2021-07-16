<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!-- <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> -->

<title>SB Admin - Dashboard</title>
 <!-- Custom fonts for this template-->
  <link href="<c:url value='/css/egovframework/com/dash/vendor/fontawesome-free/css/all.min.css'/>" rel="stylesheet" type="text/css">

  <!-- Page level plugin CSS-->
  <link href="<c:url value='/css/egovframework/com/dash/vendor/datatables/dataTables.bootstrap4.css'/>" rel="stylesheet">

  <!-- Custom styles for this template-->
  <link href="<c:url value='/css/egovframework/com/dash/css/sb-admin.css'/>" rel="stylesheet">
  <script type="text/javaScript" language="javascript">
   function loadChartFirst() {

	  Chart.defaults.global.defaultFontFamily = '-apple-system,system-ui,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,sans-serif';
	  Chart.defaults.global.defaultFontColor = '#292b2c';

	  // Area Chart Example
	  var ctx = document.getElementById("myAreaChart");
	    var myLineChart = new Chart(ctx, {
	    type: 'line',
	    data: {
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
	            max: 2000,
	            maxTicksLimit: 10
	          },
	          gridLines: {
	            color: "rgba(0, 0, 0, .125)",
	          }
	        }],
	      },
	      legend: {
	        display: false
	      }
	    }
	  });
  } 
  function loadChart(rslt) {

	  Chart.defaults.global.defaultFontFamily = '-apple-system,system-ui,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,sans-serif';
	  Chart.defaults.global.defaultFontColor = '#292b2c';
	  
	  var ctx = document.getElementById("myAreaChart");
	  
/* 	  'rgba(255, 0, 0, 0)',
	  'rgba(255, 94, 0, 0)',
	  'rgba(255, 228, 0, 0)',
	  'rgba(0, 255, 0, 0)',
	  'rgba(0, 0, 255, 0)',
	  'rgba(5, 0, 153, 0)',
	  'rgba(255, 192, 203, 0)',
	  'rgba(211, 211, 211, 0)',
	  'rgba(211, 211, 211, 0)', */
	  
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
/*       const pointBackgroundColor = [
          'rgba(255, 99, 132, 1)',
          'rgba(54, 162, 235, 1)',
          'rgba(255, 206, 86, 1)',
          'rgba(75, 192, 192, 1)',
          'rgba(153, 102, 255, 1)',
          'rgba(255, 159, 64, 1)',
          'rgba(255, 99, 132, 1)',
          'rgba(54, 162, 235, 1)',
          'rgba(255, 206, 86, 1)',
          'rgba(75, 192, 192, 1)'
      ];
      const pointBorderColor = [
          'rgba(255, 99, 132, 0.8)',
          'rgba(54, 162, 235, 0.8)',
          'rgba(255, 206, 86, 0.8)',
          'rgba(75, 192, 192, 0.8)',
          'rgba(153, 102, 255, 0.8)',
          'rgba(255, 159, 64, 0.8)',
          'rgba(255, 99, 132, 0.8)',
          'rgba(54, 162, 235, 0.8)',
          'rgba(255, 206, 86, 0.8)',
          'rgba(75, 192, 192, 0.8)'
      ];
	   */
	  
	  
	  
	  var myLineChart = new Chart(ctx, {
	    type: 'line',
	    data: {
	      labels: rslt.dateList,
	      datasets: rslt.graghList.map( (e, index) => {
	    	  return {
	    		label: e.team_name,
	  	        lineTension: 0.2,
	  	        backgroundColor: backgroundColor[index],
	            borderColor: borderColor[index],
	            borderWidth: 2,
	  	        pointRadius: 3,
	  	      	pointHoverRadius: 3,
	  	      	
	  	        pointBackgroundColor: borderColor[index],
	  	        pointBorderColor: "rgba(255,255,255,0.8)",
	  	        pointHoverBackgroundColor: borderColor[index],
	  	        pointHitRadius: 50,
	  	        pointBorderWidth: 0.5,
	  	        data: e.data,
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
	            max: 2500,
	            maxTicksLimit: 10
	          },
	          gridLines: {
	            color: "rgba(0, 0, 0, .125)",
	          }
	        }],
	      },
	   
	      legend: {
	    	  display: true,
	    	  position : "bottom",
	    	  align : "center",
	      }
	    } 
	  });
  }
  /* function getOrCreateTooltip(chart) {
	  let tooltipEl = chart.canvas.parentNode.querySelector('div');

	  if (!tooltipEl) {
	    tooltipEl = document.createElement('div');
	    tooltipEl.style.background = 'rgba(0, 0, 0, 0.7)';
	    tooltipEl.style.borderRadius = '3px';
	    tooltipEl.style.color = 'white';
	    tooltipEl.style.opacity = 1;
	    tooltipEl.style.pointerEvents = 'none';
	    tooltipEl.style.position = 'absolute';
	    tooltipEl.style.transform = 'translate(-50%, 0)';
	    tooltipEl.style.transition = 'all .1s ease';

	    const table = document.createElement('table');
	    table.style.margin = '0px';

	    tooltipEl.appendChild(table);
	    chart.canvas.parentNode.appendChild(tooltipEl);
	  }

	  return tooltipEl;
	};

	function externalTooltipHandler(context) {
	  // Tooltip Element
	  const {chart, tooltip} = context;
	  const tooltipEl = getOrCreateTooltip(chart);

	  // Hide if no tooltip
	  if (tooltip.opacity === 0) {
	    tooltipEl.style.opacity = 0;
	    return;
	  }

	  // Set Text
	  if (tooltip.body) {
	    const titleLines = tooltip.title || [];
	    const bodyLines = tooltip.body.map(b => b.lines);

	    const tableHead = document.createElement('thead');

	    titleLines.forEach(title => {
	      const tr = document.createElement('tr');
	      tr.style.borderWidth = 0;

	      const th = document.createElement('th');
	      th.style.borderWidth = 0;
	      const text = document.createTextNode(title);

	      th.appendChild(text);
	      tr.appendChild(th);
	      tableHead.appendChild(tr);
	    });

	    const tableBody = document.createElement('tbody');
	    bodyLines.forEach((body, i) => {
	      const colors = tooltip.labelColors[i];

	      const span = document.createElement('span');
	      span.style.background = colors.backgroundColor;
	      span.style.borderColor = colors.borderColor;
	      span.style.borderWidth = '2px';
	      span.style.marginRight = '10px';
	      span.style.height = '10px';
	      span.style.width = '10px';
	      span.style.display = 'inline-block';

	      const tr = document.createElement('tr');
	      tr.style.backgroundColor = 'inherit';
	      tr.style.borderWidth = 0;

	      const td = document.createElement('td');
	      td.style.borderWidth = 0;

	      const text = document.createTextNode(body);

	      td.appendChild(span);
	      td.appendChild(text);
	      tr.appendChild(td);
	      tableBody.appendChild(tr);
	    });

	    const tableRoot = tooltipEl.querySelector('table');

	    // Remove old children
	    while (tableRoot.firstChild) {
	      tableRoot.firstChild.remove();
	    }

	    // Add new children
	    tableRoot.appendChild(tableHead);
	    tableRoot.appendChild(tableBody);
	  }

	  const {offsetLeft: positionX, offsetTop: positionY} = chart.canvas;

	  // Display, position, and set styles for font
	  tooltipEl.style.opacity = 1;
	  tooltipEl.style.left = positionX + tooltip.caretX + 'px';
	  tooltipEl.style.top = positionY + tooltip.caretY + 'px';
	  tooltipEl.style.font = tooltip.options.bodyFont.string;
	  tooltipEl.style.padding = tooltip.options.padding + 'px ' + tooltip.options.padding + 'px';
	};
   */

  	function intervalTest() {
  		ajaxTest()
  		let timerId = setInterval(() => ajaxTest(), 1000 * 60 * 5);
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
  		console.log (" tr.length : ", tr.length);
 		if (tr.length > 0) {
 			for (var index in tr) {
 				console.log("index : ", index);
 				console.log("tr[index] : ", tr[index]);
 				table.deleteRow(0); 
 			}
 			table.deleteRow(0);	 
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
<body onLoad="javascript:intervalTest()">
 <nav class="navbar navbar-expand navbar-dark bg-dark static-top">
    <!-- <a class="navbar-brand mr-1" href="/dash/DashboardTraining.do">한국 전력 공사</a> -->
	<%-- <a class="navbar-brand mr-1" href="<c:url value='javascript:ajaxTest()' />">한국 전력 공사</a> --%>
	<a class="navbar-brand mr-1" href="<c:url value='/dash/test.do' />">한국 전력 공사</a>
    <button class="btn btn-link btn-sm text-white order-1 order-sm-0" id="sidebarToggle" href="#">
      <i class="fas fa-bars"></i>
    </button>
  </nav>

  <div id="wrapper">

    <!-- Sidebar -->
    <ul class="sidebar navbar-nav">
      <li class="nav-item active">
        <a class="nav-link" href="<c:url value='/dash/DashboardTraining.do' />">
          <i class="fas fa-fw fa-tachometer-alt"></i>
          <span>Dashboard</span>
        </a>
      </li>
    </ul>

    <div id="content-wrapper">

      <div class="container-fluid">

        <!-- Breadcrumbs-->
        <ol class="breadcrumb">
          <li class="breadcrumb-item">
            <a href="<c:url value='/dash/DashboardTraining.do' />">Dashboard</a>
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
              <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                <thead>
                  <tr>
                    <th>순위</th>
                    <th>팀명</th>
                    <th>예방보안</th>
                    <th>실시간대응</th>
                    <th>사후대응</th>
                    <th>VM복구</th>
                    <th>가용성</th>
                    <th>총점</th>
                  </tr>
                </thead>
                <tfoot>
                  <tr>
                    <th>순위</th>
                    <th>팀명</th>
                    <th>예방보안</th>
                    <th>실시간대응</th>
                    <th>사후대응</th>
                    <th>VM복구</th>
                    <th>가용성</th>
                    <th>총점</th>
                  </tr>
                </tfoot>
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

      <!-- Sticky Footer -->
      <footer class="sticky-footer">
        <div class="container my-auto">
          <div class="copyright text-center my-auto">
            <span>Copyright © Your Website 2021</span>
          </div>
        </div>
      </footer>

    </div>
    <!-- /.content-wrapper -->
    <input type="hidden" id="tableLength" value="<c:out value="${rankList}"/>">
	<input type="hidden" id="trainingId" value="<c:out value="${training_id}"/>">
  </div>
  <!-- /#wrapper -->

  <!-- Scroll to Top Button-->
 <!--  <a class="scroll-to-top rounded" href="#page-top">
    <i class="fas fa-angle-up"></i>
  </a> -->

  <!-- Logout Modal-->
<!--   <div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
          <button class="close" type="button" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">×</span>
          </button>
        </div>
        <div class="modal-body">Select "Logout" below if you are ready to end your current session.</div>
        <div class="modal-footer">
          <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
          <a class="btn btn-primary" href="login.html">Logout</a>
        </div>
      </div>
    </div>
  </div> -->

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
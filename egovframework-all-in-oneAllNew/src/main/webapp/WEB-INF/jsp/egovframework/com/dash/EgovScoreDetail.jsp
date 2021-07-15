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
  	
  	
  </script>
</head>
<body>

  <div id="wrapper">

    <div id="content-wrapper">

      <div class="container-fluid">

        <!-- Breadcrumbs-->
        <ol class="breadcrumb">
          <li class="breadcrumb-item">
            <a href="<c:url value='/dash/EgovDashboardTraining.do' />">점수조회</a>
          </li>
        <li class="breadcrumb-item active"><c:out value="${training_name}"/></li>
        </ol>

        <!-- Area Chart Example-->
        <!--  <div class="card mb-3"> -->
        <div class="card-header">
            <i class="fas fa-table"></i>
            점수 조회</div>
      		<table class="table table-bordered" width="100%" cellspacing="0">
                <thead>
                  <tr>
                    <th>팀명</th>
                    <th>예방보안</th>
                    <th>실시간대응</th>
                    <th>사후대응</th>
                    <th>vm복구</th>
                    <th>가용성</th>
                    <th>총점</th>
                  </tr>
                </thead>
                <tbody id="score_body">
					<tr>
						<td><c:out value="${currentScore.team_name}"/></td>
						<td><c:out value="${currentScore.type_1}"/></td>
						<td><c:out value="${currentScore.type_2}"/></td>
						<td><c:out value="${currentScore.type_3}"/></td>
						<td><c:out value="${currentScore.type_4}"/></td>
						<td><c:out value="${currentScore.type_5}"/></td>
						<td><c:out value="${currentScore.total}"/></td>
					</tr>
                </tbody>
              </table>
		<!-- </div> -->
 		<br/><br/>
        <!-- DataTables Example -->
        <div class="card mb-3">
          <div class="card-header">
            <i class="fas fa-table"></i>
            로그 조회</div>
          <div class="card-body">
            <div class="table-responsive">
              <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                <thead>
                  <tr>
                    <th>순번</th>
                    <th>날짜</th>
                    <th>팀명</th>
                    <th>내용</th>
                    <th>점수</th>
                    <!-- <th>상세보기</th> -->
                  </tr>
                </thead>
                <tfoot>
                  <tr>
                    <th>순번</th>
                    <th>날짜</th>
                    <th>팀명</th>
                    <th>내용</th>
                    <th>점수</th>
                    <!-- <th>상세보기</th> -->
                  </tr>
                </tfoot>
                <tbody id="list_body">
             
            	<c:forEach var="item" items="${scoreLogList}" varStatus="status">
					<tr>
						<td><c:out value="${status.index+1}"/></td>
	                    <td><c:out value="${item.created_datetime}"/></td>
	                    <td><c:out value="${item.team_name}"/></td>
	                    <td><c:out value="${item.training_type}"/> / <c:out value="${item.question_id}"/></td>
	                    <td><c:out value="${item.score}"/></td>
	                    <!-- <td>로그보기</td> -->
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
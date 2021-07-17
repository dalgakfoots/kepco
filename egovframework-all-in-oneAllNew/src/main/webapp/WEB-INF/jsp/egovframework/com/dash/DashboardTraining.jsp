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
	function logout() {
		$("#sessionInfo").hide();
		window.location.href = "<c:url value='/uat/uia/actionLogout.do'/>";
	}
  </script>
</head>
<body>

  <nav class="navbar navbar-expand navbar-dark bg-dark static-top">

    <!-- Navbar Brand-->
	    <!-- <a class="navbar-brand mr-1" href="/dash/DashboardTraining.do">한국 전력 공사</a> -->
		<%-- <a class="navbar-brand mr-1" href="<c:url value='javascript:ajaxTest()' />">한국 전력 공사</a> --%>
		<a class="navbar-brand mr-1" href="<c:url value='/dash/DashboardTraining.do' />">한국 전력 공사</a>

    <button class="btn btn-link btn-sm text-white order-1 order-sm-0" id="sidebarToggle" href="#">
      <i class="fas fa-bars"></i>
    </button>

    <!-- Navbar Search -->
    <form class="d-none d-md-inline-block form-inline ml-auto mr-0 mr-md-3 my-2 my-md-0">
      <div class="input-group">
        <div class="input-group-append">
        </div>
      </div>
    </form>

    <!-- Navbar -->
    <ul class="navbar-nav ml-auto ml-md-0">
      
      <li class="nav-item dropdown no-arrow">
      	<a class="nav-link" onclick="logout();return false;">Logout</a>
      	
      	<!-- 
      	<a class="dropdown-item" href="#" data-toggle="modal" data-target="#logoutModal">Logout</a>
      	<a class="btn02" href="#"  onclick="logout();return false;">Logout</a> -->
      </li>
    </ul> 

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
        </ol>

        <!-- DataTables Example -->
        <div class="card mb-3">
          <div class="card-header">
            <i class="fas fa-table"></i>
            훈련 리스트</div>
            <!-- <div class="card-footer small text-muted">Updated yesterday at 11:59 PM</div> -->
          <div class="card-body">
            <div class="table-responsive">
              <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                <thead>
                  <tr>
                  	<th>번호</th>
                    <th>훈련내용</th>
                    <th>훈련유형</th>
                    <th>훈련시작일</th>
                    <th>훈련종료일</th>
                    <th>등록일</th>
                  </tr>
                </thead>
                <tbody id="body_list">
             		<%-- <c:if test="${fn:length(resultList) == 0}">
						<tr>
							<td colspan="6"><spring:message code="common.nodata.msg" /></td>
						</tr>
					</c:if> --%>
					<c:forEach items="${resultList}" var="resultInfo" varStatus="status">
						<tr>
							<td><c:out value="${status.index+1}"/></td>
							<td class="left">
								<a href="<c:url value='/dash/DashboardView.do?trainingId=${resultInfo.eventId}'/>"> 
									<c:out value='${resultInfo.eventCn}'/>
								</a>
							</td>
							<td><c:out value='${resultInfo.eventTyCodeNm}'/></td>
							<td><c:out value='${resultInfo.eventSvcBeginDe}'/></td>
							<td><c:out value='${resultInfo.eventSvcEndDe}'/></td>
							<td><c:out value='${resultInfo.frstRegisterPnttm}'/></td>
						
							
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
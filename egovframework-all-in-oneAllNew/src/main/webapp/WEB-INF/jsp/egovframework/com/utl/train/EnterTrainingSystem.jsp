<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<title>훈련</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/com.css' />">

<script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/jquery.js'/>" ></script>
<script type="text/javaScript" language="javascript">
/* 	function windowOpen(url, index){
		
		$.ajax({
  			type:"get",
  			url:"<c:url value='/train/ticketUrl.do'/>",
  			dataType:'json',
  			success:function(returnData, status){
  				if(status == "success") {
  					
  					var ticketUrl = returnData.ticket + url;
  					console.log("ticketUrl : " , ticketUrl);
  				window.open(ticketUrl, index);
  					
  				}else{ console.log("error");return;} 
  			}
  		}); 
		
		
	}  */
	
	
	function movePage(url, vmId, vmName, index) {
		console.log("index : ", index);
		window.open("<c:url value='/train/ticketUrl.do' />?url="+url+"&vmId="+vmId+"&vmName="+vmName,  index, '_blank');
	}
	
	function questionDetail(faqId){
		var form = document.frm;
		form.action = "<c:url value='/train/selectQuestionDetail.do'/>"
		form.faqId.value = faqId;
		form.submit();
	}
</script>

</head>
<body>
<script>
	if("${msg}" != ""){
		alert("${msg}");	
	}
</script>
<div class="board">

	<c:choose>
	<c:when test="${trainType ne 'none' }">
		<%-- <form name="frm" action="<c:url value='/train/enterExam.do'/>" method="post">
			<button class="s_btn" type="submit">문제풀기</button>
			<input type="hidden" name="trainType" value="${trainType}">
		</form> --%>
		<c:choose>
		
			<c:when test="${trainType eq 'pst'}">
				<h1>${trainTypeName} 인프라 구성도</h1>
				<img src="<c:url value='/images/egovframework/com/sym/train/pst_infra.jpg' />" alt="예방보안인프라구성" / style="width:100%;">
			</c:when>
			<c:when test="${trainType eq 'mdt'}">
				<h1>${trainTypeName} 인프라 구성도</h1>
				<img src="<c:url value='/images/egovframework/com/sym/train/mdt_infra.jpg' />" alt="실시간대응(약성코드)인프라구성" / style="width:100%;">
			</c:when>
			<c:when test="${trainType eq 'wat'}">
				<h1>${trainTypeName} 인프라 구성도</h1>
				<img src="<c:url value='/images/egovframework/com/sym/train/wat_infra.jpg' />" alt="실시간대응(웹)인프라구성" / style="width:100%;">
			</c:when>
			<c:when test="${trainType eq 'ast'}">
				<h1>${trainTypeName} 인프라 구성도</h1>
				<img src="<c:url value='/images/egovframework/com/sym/train/ast_infra.jpg' />" alt="사후대응인프라구성" / style="width:100%;">
			</c:when>
			<c:otherwise>
				<!-- 별도의 훈련 타입이 추가 되어 인프라 구성도가 추가 될 시 추가할 것  -->
			</c:otherwise>
		
		</c:choose>
		<!-- PST~AST -->
		<c:if test="${trainType ne 'srg'}">
			<h1>${trainTypeName} 가상자원 목록</h1>
			<table class="board_list">
			<caption>${trainTypeName } VM</caption>
			<colgroup>
				<col style="width : 10%">
				<col style="width : ;">
				<col style="width : ;">
				<col style="width : ;">
				<col style="width : ;">
			</colgroup>
			<thead>
				<th>VM ID</th>
				<th>VM명</th>
				<th>OS</th>
				<th>IP</th>
				<th>계정정보</th>
				<th>콘솔</th>
			</thead>
			<tbody>
				<c:forEach var="item" items="${userVmLists}" varStatus="status">
					<tr>
						<td>${item.vm_group_id}-${item.vm_type_id}-${item.vm_group_type_id}</td>
						<td>${item.name }</td>
						<td>${item.type}</td>
						<td>${item.ip}</td>
						<td>${item.user_name } <c:out value="/"/> ${item.user_password }</td>
						<td><button class="s_btn" type="button" onclick="javascript:movePage('${item.url}', '${item.id}')">열기</button></td> 
						
						
					</tr>
				</c:forEach>
				<c:if test="${fn:length(userVmLists) == 0}">
					<tr>
						<td colspan="5">현재 소속된 그룹이 없거나, 그룹에 할당된 VM이 없습니다. 관리자에게 문의하세요.</td>
					</tr>
				</c:if>
			</tbody>
			</table>

<%-- 	<h1>${trainTypeName}</h1>
	<form name="frm" action="<c:url value='/train/enterExam.do'/>" method="post">
		<button class="s_btn" type="submit">문제풀기</button>
		<input type="hidden" name="trainType" value="${trainType}">
	</form>
	<table class="board_list">
	<caption>${trainTypeName } VM</caption>
	<colgroup>
		<col style="width : 10%">
		<col style="width : ;">
		<col style="width : ;">
		<col style="width : ;">
		<col style="width : ;">
	</colgroup>
	<thead>
		<th>VM ID</th>
		<th>VM명</th>
		<th>OS</th>
		<th>IP</th>
		<th>계정정보</th>
		<th>콘솔</th>
	</thead>
	<tbody>
		<c:forEach var="item" items="${userVmLists}" varStatus="status">
			<tr>
				<td>${item.vm_group_id}-${item.vm_type_id}-${item.vm_group_type_id}</td>
				<td>${item.name }</td>
				<td>${item.type}</td>
				<td>${item.ip}</td>
				<td>${item.user_name } <c:out value="/"/> ${item.user_password }</td>
				<td><button class="s_btn" type="button" onclick="javascript:movePage('${item.url}', '${item.id}', '${item.name}', '${status.index}')">열기</button></td> 
				
				
			</tr>
		</c:forEach>
		<c:if test="${fn:length(userVmLists) == 0}">
			<tr>
				<td colspan="5">현재 소속된 그룹이 없거나, 그룹에 할당된 VM이 없습니다. 관리자에게 문의하세요.</td>
			</tr> --%>

		</c:if>
		<!--  -->
		<br/>
		<h1>${trainTypeName} 문제 목록</h1>
		<table class="board_list">
					<caption>${trainTypeName} 문제목록</caption>
					<colgroup>
						<col style="width:5%;"> <!-- 번호  -->
						<col style="width:40%;"> <!-- 문제제목 -->
						<col style="width:17%;"> <!-- 제출횟수 -->
						<col style="width:;"> <!-- 비고 -->
					</colgroup>
					<thead>
						<th>번호</th>
						<th>문제제목</th>
						<th>제출횟수</th>
						<th>비고</th>
					</thead>
					<tbody>
						<c:forEach var="item" items="${examList}" varStatus="status">
						<c:choose>
							<c:when test="${trainType eq 'pst'}">
										<tr>
												<td>${status.count}</td>
												<td><a href="javascript:questionDetail('${item.faq_id}');" style="font-weight : bold;">${item.qestn_sj}</a></td>
												<td>
													<c:choose>
														<c:when test="${item.CNT eq '0'}">
															미제출
														</c:when>
														<c:otherwise>
															<c:out value="${item.CNT }"/>회 제출
														</c:otherwise>
													</c:choose>
												</td>
												<td></td>
											</tr>
							</c:when>
							<c:when test="${trainType eq 'mdt'}">
									<tr>
										<td>${status.count}</td>
										<td><c:if test="${item.PRE_EXAM_SOLVED_YN eq 'Y'}"><a href="javascript:questionDetail('${item.faq_id}');" style="font-weight : bold;"></c:if>${item.qestn_sj}</a></td>
										<td>
											<c:choose>
												<c:when test="${item.CNT eq '0' && item.type eq 'QUIZ'}">
													미제출
												</c:when>
												<c:when test="${item.CNT eq '0' && item.type eq 'QUESTION'}">
													에이전트 모니터링 방식
												</c:when>
												<c:otherwise>
													<c:out value="${item.CNT }"/>회 제출
												</c:otherwise>
											</c:choose>
										</td>
										<td>
											<c:if test="${not empty item.PRE_EXAM_SJ}">
											${item.PRE_EXAM_SJ} 문제의 정답 여부 확인 후,<br/>해당 문제가 활성화 됩니다.
											</c:if>
										</td>
									</tr>
							</c:when>
							<c:when test="${trainType eq 'wat'}">
									<tr>
										<td>${status.count}</td>
										<td><c:if test="${item.IS_EXAM_TIME_YN eq 'Y'}"><a href="javascript:questionDetail('${item.faq_id}');" style="font-weight : bold;"></c:if>${item.qestn_sj}</a></td>
										<td>
											<c:choose>
												<c:when test="${item.CNT eq '0' && item.type eq 'QUIZ'}">
													미제출
												</c:when>
												<c:when test="${item.CNT eq '0' && item.type eq 'QUESTION'}">
													에이전트 모니터링 방식
												</c:when>
												<c:otherwise>
													<c:out value="${item.CNT }"/>회 제출
												</c:otherwise>
											</c:choose>
										</td>
										<td>
										</td>
									</tr>
							</c:when>
							<c:otherwise>
										<tr>
											<td>${status.count}</td>
											<td><a href="javascript:questionDetail('${item.faq_id}');" style="font-weight : bold;">${item.qestn_sj}</a></td>
											<td>
												<c:choose>
													<c:when test="${item.CNT eq '0'}">
														미제출
													</c:when>
													<c:otherwise>
														<c:out value="${item.CNT }"/>회 제출
													</c:otherwise>
												</c:choose>
											</td>
											<td></td>
										</tr>
							</c:otherwise>
						</c:choose>
					</c:forEach>
			</tbody>
		</table>
		</c:when>
		<c:otherwise>
			<h1>훈련 시간이 아닙니다.</h1>
		</c:otherwise>
	</c:choose>
	
</div>
<form id="frm" name="frm" method="post">
<input type="hidden" id="faqId" name="faqId">
<input type="hidden" id="trainTypeName" name="trainTypeName" value="${trainTypeName }">
<input type="hidden" id="trainType" name="trainType" value="${trainType }">
</form>
</body>
</html>
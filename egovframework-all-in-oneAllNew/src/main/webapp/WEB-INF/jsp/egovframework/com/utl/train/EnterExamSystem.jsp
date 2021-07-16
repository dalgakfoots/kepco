<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/com.css' />">
<title>문제</title>
<script>
	
	function questionDetail(faqId){
		var form = document.frm;
		form.action = "<c:url value='/train/selectQuestionDetail.do'/>"
		form.faqId.value = faqId;
		form.submit();
	}
	
	function endTrain(){
		if(confirm('풀이완료 버튼을 누를 경우 훈련이 종료되어\n더 이상 문제를 풀 수 없습니다.\n정말 풀이완료 하시겠습니까?')){
			alert('fu')
		}
	}
	
</script>
</head>
<body>
<div class="board">
	<h1><c:out value="${trainTypeName}"/> 문제지</h1>
	<!-- <div class="search_box">
		<span style="font-weight:bold">풀이현황</span>
		풀이현황 구현 
	</div> -->
	
	<div class="search_box">
		<span style="font-weight:bold">문제목록</span>
	</div>
		<table class="board_list">
		<caption>${trainTypeName} 문제목록</caption>
		<colgroup>
			<col style="width:5%;"> <!-- 번호  -->
			<col style="width:60%;"> <!-- 문제제목 -->
			<col style="width:;"> <!-- 제출횟수 -->
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
					<td>비고 미구현</td>
				</tr>
			</c:forEach>
		</tbody>
		</table>
		<button type="button" onclick="javascript:endTrain()">풀이완료</button>
</div>
<form id="frm" name="frm" method="post">
<input type="hidden" id="faqId" name="faqId">
<input type="hidden" id="trainTypeName" name="trainTypeName" value="${trainTypeName }">
<input type="hidden" id="trainType" name="trainType" value="${trainType }">
</form>
</body>
</html>
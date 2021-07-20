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
	function submitAnswer(){
		
		if(confirm('풀이 제한이 있을 경우 답변 제출을 하면 \n이후에는 답변 제출을 못할 수도 있습니다.')){
			var form = document.frm;
			form.action = "<c:url value='/train/submitAnswer.do'/>"
			form.answer.value = document.getElementById("answer").value;
			form.submit();
		}
	}
	
	function finishQuestion(){
		
		if(confirm("문제풀이완료를 실행 할 경우 해당 문제를 더 이상 제출할 수 없습니다.\n진행하시겠습니까?")){
		var form = document.frm;
		form.action = "<c:url value='/train/finishQuestion.do'/>";
		form.submit();
		}
	}
	
	function examList(){
		
		var form = document.frm;
		form.action = "<c:url value='/train/enterExam.do'/>";
		form.submit();
		
	}
</script>
</head>
<body>
<div class="board">
	<h1><c:out value="${trainTypeName}"/> 문제지</h1>
	<table class="board_list">
		<thead>
			<th><span style="padding-right:45%;">문제 (<c:out value="${detail.QESTN_SJ}"/>)</span> <span>풀이제한 : ${submitCnt}/${detail.MAX_SUBMIT_CNT}</span> <span>배점 : ${detail.SCORE}</span> </th>
		</thead>
		<tbody>
			<tr>
				<td><b>문제 지문</b></td>
			</tr>
			<tr>
				<td>
				<textarea name="qestn" style="height:100px;" disabled>${detail.QESTN_CN}</textarea>
				</td>
			</tr>
			<tr>
				<td>
				<c:if test="${detail.TYPE eq 'QUIZ' }">
					<textarea id="answer" placeholder="답변 입력">${userAnswer}</textarea>
					<c:if test="${isFinish eq '' || isFinish eq 'N'}">
						<button type="button" onclick="javascript:submitAnswer()">답변제출</button>
						<button type="button" onclick="javascript:finishQuestion()">문제풀이완료</button>
					</c:if>
				</c:if>
				<button type="button" onclick="javascript:examList()">문제목록</button>
				</td>
			</tr>
		</tbody>
	</table>
</div>
<form name="frm" id="frm" method="post">
<input type="hidden" name="faqId" value="${detail.FAQ_ID}">
<input type="hidden" name="trainType" value="${frm.trainType}">
<input type="hidden" name="answer">
</form>
</body>
</html>
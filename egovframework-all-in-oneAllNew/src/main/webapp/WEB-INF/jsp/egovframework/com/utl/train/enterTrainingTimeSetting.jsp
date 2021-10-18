<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/com.css' />">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/cmm/jqueryui.css' />">

<script src="<c:url value='/js/egovframework/com/cmm/jquery.js' />"></script>
<script src="<c:url value='/js/egovframework/com/cmm/jqueryui.js' />"></script>
<meta charset="UTF-8">
<title>훈련시간관리</title>
</head>
<body>
	<div class="board">
	
		<h1>훈련시간관리</h1>
		<form name="frm" method="post" action="<c:url value='/train/setTrainingTimeSetting.do'/>">
		<div class="wTableFrm">
			<table class="wTable">
			<colgroup>
				<col style="width : 30%">
				<col style="width : 20%">
				<col style="width : 30%">
				<col style="width : 20%">
			</colgroup>
			<tbody>
				<tr>
					<th>훈련명</th>
					<td class="left">
						<input type="hidden" name="trainingId" value="${result.TRAINING_ID}">
						<c:out value="${result.EVENT_CN }"/>
					</td>
					<th>훈련날짜</th>
					<td class="left" >
						<input id="trainingDate" name="trainingDate" style="width:150px;" type="date" value="${result.TRAINING_DATE}">
					</td>
				</tr>
				<tr>
					<th>DDos 시작시간</th>
					<td class="left">
						<input id="ddosStartDatetime" name="ddosStartDatetime" style="width:100px;" type="time" value="${result.ddos_start_datetime}">
					</td>
					<th>DDos 종료시간</th>
					<td class="left">
						<input id="ddosEndDatetime" name="ddosEndDatetime" style="width:100px;" type="time" value="${result.ddos_end_datetime}">
					</td>
				</tr>
				<tr>
					<th>렌섬웨어 시작시간</th>
					<td class="left">
						<input id="ransomStartDatetime" name="ransomStartDatetime" style="width:100px;" type="time" value="${result.ransom_start_datetime}">
					</td>
					<th>렌섬웨어 종료시간</th>
					<td class="left">
						<input id="ransomEndDatetime" name="ransomEndDatetime" style="width:100px;" type="time" value="${result.ransom_end_datetime}">
					</td>
				</tr>
				<tr>
					<th>웹해킹 시작시간</th>
					<td class="left">
						<input id="whStartDatetime" name="whStartDatetime" style="width:100px;" type="time" value="${result.wh_start_datetime}">
					</td>
					<th>웹해킹 종료시간</th>
					<td class="left">
						<input id="whEndDatetime" name="whEndDatetime" style="width:100px;" type="time" value="${result.wh_end_datetime}">
					</td>
				</tr>
				<tr>
					<th>APT#01 시작시간</th>
					<td class="left">
						<input id="apt01StartDatetime" name="apt01StartDatetime" style="width:100px;" type="time" value="${result.apt01_start_datetime}">
					</td>
					<th>APT#01 종료시간</th>
					<td class="left">
						<input id="apt01EndDatetime" name="apt01EndDatetime" style="width:100px;" type="time" value="${result.apt01_end_datetime}">
					</td>
				</tr>
				<tr>
					<th>APT#02 시작시간</th>
					<td class="left">
						<input id="apt02StartDatetime" name="apt02StartDatetime" style="width:100px;" type="time" value="${result.apt02_start_datetime}">
					</td>
					<th>APT#02 종료시간</th>
					<td class="left">
						<input id="apt02EndDatetime" name="apt02EndDatetime" style="width:100px;" type="time" value="${result.apt02_end_datetime}">
					</td>
				</tr>
			</tbody>
			</table>
		</div>
		<div class="btn">
			<input type="submit" class="s_submit" value="저장"/>
		</div>
		</form>
	</div>
</body>
</html>
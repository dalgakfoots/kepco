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
			<tbody>
				<tr>
					<th>훈련명</th>
					<td class="left" colspan="3">
						<select name="trainingId">
							<c:forEach var="item" items="${trainingIds}">
								<option value="${item.EVENT_ID}">${item.EVENT_CN}</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<th>예방보안 시작시간</th>
					<td class="left" colspan="3">
						<input id="pstStartDatetime" name="pstStartDatetime" style="width:100px;" type="time" value="${result.PST_START_DATETIME}">
					</td>
				</tr>
				<tr>
					<th>예방보안 종료시간</th>
					<td class="left" colspan="3">
						<input id="pstEndDatetime" name="pstEndDatetime" style="width:100px;" type="time" value="${result.PST_END_DATETIME}">
					</td>
				</tr>
				<tr>
					<th>실시간대응(악성코드) 시작시간</th>
					<td class="left" colspan="3">
						<input id="mdtStartDatetime" name="mdtStartDatetime" style="width:100px;" type="time" value="${result.MDT_START_DATETIME}">
					</td>
				</tr>
				<tr>
					<th>실시간대응(악성코드) 종료시간</th>
					<td class="left" colspan="3">
						<input id="mdtEndDatetime" name="mdtEndDatetime" style="width:100px;" type="time" value="${result.MDT_END_DATETIME}">
					</td>
				</tr>
				<tr>
					<th>실시간대응(웹) 시작시간</th>
					<td class="left" colspan="3">
						<input id="watStartDatetime" name="watStartDatetime" style="width:100px;" type="time" value="${result.WAT_START_DATETIME}">
					</td>
				</tr>
				<tr>
					<th>실시간대응(웹) 종료시간</th>
					<td class="left" colspan="3">
						<input id="watEndDatetime" name="watEndDatetime" style="width:100px;" type="time" value="${result.WAT_END_DATETIME}">
					</td>
				</tr>
				<tr>
					<th>사후대응 시작시간</th>
					<td class="left" colspan="3">
						<input id="astStartDatetime" name="astStartDatetime" style="width:100px;" type="time" value="${result.AST_START_DATETIME}">
					</td>
				</tr>
				<tr>
					<th>사후대응 종료시간</th>
					<td class="left" colspan="3">
						<input id="astEndDatetime" name="astEndDatetime" style="width:100px;" type="time" value="${result.AST_END_DATETIME}">
					</td>
				</tr>
				<tr>
					<th>보안규정및지침 시작시간</th>
					<td class="left" colspan="3">
						<input id="srgStartDatetime" name="srgStartDatetime" style="width:100px;" type="time" value="${result.SRG_START_DATETIME}">
					</td>
				</tr>
				<tr>
					<th>보안규정및지침 종료시간</th>
					<td class="left" colspan="3">
						<input id="srgEndDatetime" name="srgEndDatetime" style="width:100px;" type="time" value="${result.SRG_END_DATETIME}">
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
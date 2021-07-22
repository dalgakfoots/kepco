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
</script>

</head>
<body>
<div class="board">
	<h1>${trainTypeName}</h1>
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
			</tr>
		</c:if>
	</tbody>
	</table>
</div>
</body>
</html>
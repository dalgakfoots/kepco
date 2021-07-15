<%
/**
 * @Class Name  : EgovAuthorInsert.java
 * @Description : EgovAuthorInsert jsp
 * @Modification Information
 * @
 * @  수정일         수정자          수정내용
 * @ -------    --------    ---------------------------
 * @ 2009.02.01    lee.m.j          최초 생성
 *   2016.06.13    장동한            표준프레임워크 v3.6 개선
 *
 *  @author lee.m.j
 *  @since 2009.03.11
 *  @version 1.0
 *  @see
 *
 */
 %>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<c:set var="pageTitle"><spring:message code="comCopSecGmt.title"/></c:set>
<!DOCTYPE html>
<html>
<head>
<title>${pageTitle} <spring:message code="title.create" /></title><!-- 그룹관리 등록 -->
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/com.css' />">

<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="groupManage" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javaScript" language="javascript">

function fncSelectGroupList() {
    var varFrom = document.getElementById("groupManage");
    console.log("varFrom : " , varFrom);
    /* varFrom.action = "<c:url value='/sec/gmt/EgovGroupList.do'/>";
    varFrom.submit(); */
}

function fncGroupInsert(form) {

    if(confirm("<spring:message code="common.regist.msg" />")){	//등록하시겠습니까?
        if(!validateGroupManage(form)){
            return false;
        }else{
        	form.submit();
        }
    }
}


function addVmTableRow() {
	
	var type = document.getElementById("type").value;
	var system = document.getElementById("system").value;
	var url = document.getElementById("url").value;
	var ip = document.getElementById("ip").value;
	var user = document.getElementById("user").value;
	var pw = document.getElementById("pw").value;
	
	
	console.log("type : ", type );
	console.log("system : ", system );
	console.log("url : ", url );
	console.log("ip : ", ip );
	console.log("user : ", user);
	console.log("pw : ", pw );
	
	
	
	
	if (type !== "" && url !== "" ) {
		var length  = document.getElementById("board_list_body").getElementsByTagName("tr").length;
		console.log("length : ", length );
		
		var table = document.getElementById("board_list");
		const newRow = table.insertRow();
		const newCell1 = newRow.insertCell(0);
		const newCell2 = newRow.insertCell(1);
		const newCell3 = newRow.insertCell(2);
		const newCell4 = newRow.insertCell(3);
		const newCell5 = newRow.insertCell(4);
		const newCell6 = newRow.insertCell(5);
		newCell1.innerText = type;
		newCell2.innerText = system;
		newCell3.innerText = url;
		newCell4.innerText = ip;
		newCell5.innerText = user + " / " + pw;
		newCell6.innerHTML = "<input type=\"button\" name=\""+length+"\" onclick=\"javascript:removeTableItems("+length+")\" value=\"X\">";		
		
		document.getElementById("type").value = "";
		document.getElementById("system").value = "";
		document.getElementById("url").value = "";
		document.getElementById("ip").value = "";
		document.getElementById("user").value = "";
		document.getElementById("pw").value = "";
		getTableValues();
	 }		
}

function getTableValues() {
	var vmGroupTypes = new Array();
	var vm = "";
	var tr = document.getElementById("board_list_body").getElementsByTagName("tr");
	for (var i=0; i<tr.length; i++) {
		if (vm !== "") vm+=">>>";
		var td = tr[i].getElementsByTagName("td");
		if (td.length > 0) {
			var userInfo = td[4].innerText.split("/");
			var obj = {
				type : td[0].innerText === "" ? "0" : td[0].innerText,	
				system : td[1].innerText === "" ? "0" : td[1].innerText,
				url : td[2].innerText === "" ? "0" : td[2].innerText,
				ip : td[3].innerText === "" ? "0" : td[3].innerText,
				userName : userInfo[0].trim() === "" ? "0" : userInfo[0].trim(),
				userPW : userInfo[1].trim() === "" ? "0" : userInfo[1].trim() ,
			}
		     for (let i in obj) {	 
		    	 if (i !== "type") vm += ","; 
		    	 vm += i+"<<<"+obj[i];
		     }
		}
	}
	document.getElementById("typeUrl").value = vm;	
}


function removeTableItems(index) {
	var tr =document.getElementsByName(index)[0].parentElement.parentNode.rowIndex;
 	var table = document.getElementById("board_list_body");
	const newRow = table.deleteRow(tr-1); 
	getTableValues();
}




</script>
</head>

<body>

<form:form commandName="groupManage" method="post" action="${pageContext.request.contextPath}/sec/vmt/EgovVmGroupInsert.do" onSubmit="fncGroupInsert(document.forms[0]); return false;"> 
<div class="wTableFrm">
	<!-- 타이틀 -->
	<h2>VM 그룹 <spring:message code="title.create" /></h2><!-- 그룹관리 등록 -->

	<!-- 등록폼 -->
	<table class="wTable" summary="<spring:message code="common.summary.list" arguments="${pageTitle}" />">
	<caption>${pageTitle} <spring:message code="title.create" /></caption>
	<colgroup>
		<col style="width: 16%;"><col style="width: ;">
	</colgroup>
	<tbody>
		<!-- 입력 -->
		<c:set var="inputTxt"><spring:message code="input.input" /></c:set>
		<!-- 그룹명 -->
		<c:set var="title"><%-- <spring:message code="comCopSecGmt.regist.groupNm" /> --%>그룹명 </c:set>
		<tr>
			<th>${title} <span class="pilsu">*</span></th>
			<td class="left">
				<form:input path="groupNm" title="${title} ${inputTxt}" size="40" maxlength="50" />
				<div><form:errors path="groupNm" cssClass="error" /></div> 
			</td>
		</tr>
		<!-- 설명 -->
		<c:set var="title"><spring:message code="comCopSecGmt.regist.groupDc" /></c:set>
		<tr>
			<th>${title}</th>
			<td class="left">
			    <form:textarea path="groupDc" title="${title} ${inputTxt}" cols="300" rows="10" />   
				<div><form:errors path="groupDc" cssClass="error" /></div> 
			</td>
		</tr>
		<c:set var="title">VM 타입</c:set>
		<tr>
			<th>${title}</th>
			<td class="left">
			    <c:set var="title">type</c:set>
				<c:set var="inputSelect">선택하세요</c:set>
				
		        <select id="type" title="${title} ${inputSelect}">
		            <option value="" label="${inputSelect}"/>
		            <c:forEach var="item" items="${typeList}" varStatus="status">
		            	<option id="${item.id}" value="${item.type}" label="${item.type}"/>
		            </c:forEach>
		        </select>
			</td>
		</tr>
		
		<c:set var="title">시스템명</c:set>
		<tr>
			<th>${title}</th>
			<td class="left">
			    <c:set var="title">url</c:set>
		        <input id="system" title="${title} ${inputTxt}" size="85%" style="height:20px; border: gray 1px solid;"/>
			</td>
		</tr>
		
		
		<c:set var="title">VM URL</c:set>
		<tr>
			<th>${title}</th>
			<td class="left">
			    <c:set var="title">url</c:set>
		        <input id="url" title="${title} ${inputTxt}" size="85%" style="height:20px; border: gray 1px solid;"/>
			</td>
		</tr>
		
		<c:set var="title">VM IP</c:set>
		<tr>
			<th>${title}</th>
			<td class="left">
			    <c:set var="title">url</c:set>
		        <input id="ip" title="${title} ${inputTxt}" size="85%" style="height:20px; border: gray 1px solid;"/>
			</td>
		</tr>
		
		<c:set var="title">User</c:set>
		<tr>
			<th>${title}</th>
			<td class="left">
			    <c:set var="title">url</c:set>
		        <input id="user" title="${title} ${inputTxt}" size="85%" style="height:20px; border: gray 1px solid;"/>
			</td>
		</tr>
		
		<c:set var="title">Password</c:set>
		<tr>
			<th>${title}</th>
			<td class="left">
			    <c:set var="title">url</c:set>
		        <input id="pw" title="${title} ${inputTxt}" size="85%" style="height:20px; border: gray 1px solid;"/>
		        <span class="btn_s"><a href="javascript:addVmTableRow()">추가</a></span>
			</td>
		</tr>
		
		<c:set var="title">VM</c:set>
		<tr>
			<th>${title}</th>
			<td>
				<table class="board_list" id="board_list">
					<colgroup>
						<col style="width: 15%;">
						<col style="width: 15%;">
						<col style="width: 35%;">
						<col style="width: 15%;">
						<col style="width: 15%;">
						<col style="width: 5%;">
					</colgroup>
					<thead>
						<tr>
							<th>VM 타입</th>
							<th>시스템명</th>
							<th>URL</th>
							<th>IP</th>
							<th>계정정보</th>
							<th>삭제</th>							
						</tr>
					</thead>
					<tbody id="board_list_body">
						<tr>
						</tr>
					</tbody>
				
				</table>
			</td>
		</tr>
		</tbody>
	</table>
		
		

	<!-- 하단 버튼 -->
	<div class="btn">
		
		<input type="hidden" name="typeUrl" id="typeUrl" value="">
		<input type="submit" class="s_submit" value="<spring:message code="button.create" />" title="<spring:message code="button.create" /> <spring:message code="input.button" />" /><!-- 등록 -->
		<span class="btn_s"><a href="<c:url value='/sec/vmt/EgovVmGroupList.do' />"  title="<spring:message code="button.list" />  <spring:message code="input.button" />"><spring:message code="button.list" /></a></span><!-- 목록 -->
	</div><div style="clear:both;"></div>
	
</div>
</form:form>	

</body>
</html>


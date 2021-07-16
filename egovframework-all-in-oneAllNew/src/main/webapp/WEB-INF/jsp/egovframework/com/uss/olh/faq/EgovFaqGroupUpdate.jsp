<%
/**
 * @Class Name  : EgovAuthorUpdate.java
 * @Description : EgovAuthorUpdate jsp
 * @Modification Information
 * @
 * @  수정일         수정자          수정내용
 * @ -------    --------    ---------------------------
 * @ 2009.02.01    lee.m.j          최초 생성
 *   2016.06.13    장동한             표준프레임워크 v3.6 개선
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
<title>훈련문제그룹 <spring:message code="title.update" /></title><!-- 그룹관리 등록 -->
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/com.css' />">
<%-- 
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="groupManage" staticJavascript="false" xhtml="true" cdata="false"/> --%>
<script type="text/javaScript" language="javascript">

/*TODO 자식창에서 호출 : 행 추가시킴*/
window.call = function(list){
	var board = document.querySelector("#board");
	for(var i = 0; i < list.length ; i++){
		var newRow = board.insertRow();
		newRow.insertCell(0).innerHTML = '<input type="hidden" class="userId" name="userId" value="'+list[i].faqId+'">'
		newRow.insertCell(1).innerHTML = '<input type="checkbox" class="chkYn" name="chkYn" checked="checked">';
		newRow.insertCell(2).innerHTML = list[i].faqId;
		newRow.insertCell(3).innerHTML = list[i].faqSubject;
	}
}

/*TODO 문제 등록 시 문제 추가할 수 있는 팝업화면 추가 */
function searchFaqs(){
	window.open("<c:url value='/uss/olh/faq/EgovFaqListPopup.do'/>", "문제검색", "width=800, height=700, toolbar=no, menubar=no, scrollbars=no, resizable=yes" );  
}

function fncSelectGroupList() {
    var varFrom = document.getElementById("groupManage");
    varFrom.action = "<c:url value='/sec/gmt/EgovGroupList.do'/>";
    varFrom.submit();
}

function fncGroupUpdate(form) {
	if(confirm("<spring:message code="common.save.msg" />")){ //저장하시겠습니까?
        
        fnSelectTeam();
        form.submit();
        
    }
}

function fncGroupDelete() {
    var varFrom = document.getElementById("frmIdDelete");
    varFrom.action = "<c:url value='/uss/olh/faq/EgovFaqGroupDelete.do'/>";
    if(confirm("<spring:message code="common.delete.msg" />")){	//삭제하시겠습니까?
    	varFrom.submit();
    }else{
    	return false;
    }
}

function fnSelectTeam() {
    var checkField = document.querySelector("#board").getElementsByClassName('chkYn');
    var id = document.querySelector("#board").getElementsByClassName('userId');
    var checkedIds = "";
    var checkedCount = 0;
    if(checkField) {
    	if(checkField.length > 0) {
            for(var i=0; i < checkField.length; i++) {
                if(checkField[i].checked) {
                	checkedIds += ((checkedCount==0? "" : ",") + id[i].value);
                    checkedCount++;
                }
            }
        } 
    }
    document.getElementById('groupManage').addedFaq.value = checkedIds;
}

</script>
</head>

<body>

<form id="groupManage" name="groupManage" method="post" action="${pageContext.request.contextPath}/uss/olh/faq/EgovFaqGroupUpdate.do" onSubmit="fncGroupUpdate(document.forms[0]); return false;"> 
<div class="wTableFrm">
	<!-- 타이틀 -->
	<h2>훈련문제그룹 <spring:message code="title.create" /></h2><!-- 그룹관리 등록 -->

	<!-- 등록폼 -->
	<table class="wTable" summary="<spring:message code="common.summary.list"/>">
	<caption>훈련문제그룹  <spring:message code="title.create" /></caption>
	<colgroup>
		<col style="width: 16%;"><col style="width: ;">
	</colgroup>
	<tbody>
		<!-- 입력 -->
		<c:set var="inputTxt"><spring:message code="input.input" /></c:set>
		<!-- 그룹아이디 -->
		<c:set var="title"><spring:message code="comCopSecGmt.regist.groupId" /></c:set>
		<tr>
			<th>${title}</th>
			<td class="left">
				${groupManage.faqGroupId}
			</td>
		</tr>
		
		<!-- 그룹명 -->
		<c:set var="title"><spring:message code="comCopSecGmt.regist.groupNm" /></c:set>
		<tr>
			<th>${title} <span class="pilsu">*</span></th>
			<td class="left">
				<input type="text" name="faqGroupNm" value="${groupManage.faqGroupNm}" size="40" maxlength="50" />
			</td>
		</tr>
		<!-- 설명 -->
		<c:set var="title"><spring:message code="comCopSecGmt.regist.groupDc" /></c:set>
		<tr>
			<th>${title}</th>
			<td class="left">
			    <textarea name="faqGroupDc" cols="300" rows="10">${groupManage.faqGroupDc}</textarea>
			</td>
		</tr>
		
		<!-- 타입  -->
		<c:set var="title">문제타입</c:set>
		<tr>
			<th>${title}</th>
			<td class="left">
				<input type="radio" id="pst" name="type" value="pst" <c:if test="${groupManage.type eq 'pst'}">checked</c:if> >
				<label for="pst">예방보안훈련</label>
				<input type="radio" id="mdt" name="type" value="mdt" <c:if test="${groupManage.type eq 'mdt'}">checked</c:if>>
				<label for="mdt">악성코드탐지대응훈련</label>
				<input type="radio" id="wat" name="type" value="wat" <c:if test="${groupManage.type eq 'wat'}">checked</c:if>>
				<label for="wat">웹공격대응훈련</label>
				<input type="radio" id="ast" name="type" value="ast" <c:if test="${groupManage.type eq 'ast'}">checked</c:if>>
				<label for="ast">사후대응훈련</label>
			</td>
		</tr>
		<!-- TODO 팀 - 회원 매칭 가능하도록 구성  -->
		
		<tr>
			<th>문제 목록</th>
			<td class="left">
				<div>
					<!-- <span class="btn_s2" name="add" onclick="javascript:searchNoTeamUsers()">추가</span>
					 -->
					<span class="btn_s2" name="add" onclick="javascript:searchFaqs()">추가</span>
					<table class="board_list" id = "board">
					<colgroup>
						<col style="width: 10%">
						<col style="width: 10%">
						<col style="width: 30%;">
						<col style="width: 30%;">
					</colgroup>
					<thead>
						<tr>
							<th></th>
							<th></th>
							<th>문제 ID</th>
							<th>문제제목</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="item" items="${selectFaq }">
							<tr>
								<td><input type="hidden" class="userId" name="userId" value="${item.FAQ_ID}"></td>
								<td><input type="checkbox" class="chkYn" name="chkYn" checked="checked"></td>
								<td>${item.FAQ_ID }</td>
								<td>${item.QESTN_SJ}</td>
							</tr>	
						</c:forEach>
					</tbody>	 
					</table>
				</div>
			</td>
		</tr>
	</tbody>
	</table>

	<!-- 하단 버튼 -->
	<!-- <span class="btn_s"><a href="#" onClick="fncGroupDelete(); return false;"  title="<spring:message code="button.delete" /> <spring:message code="input.button" />"><spring:message code="button.delete" /></a></span>  -->
	<div class="btn">
	<span class="btn_s"><a href="<c:url value='/uss/olh/faq/selectFaqGroupList.do' />"  title="<spring:message code="button.list" /> <spring:message code="input.button" />"><spring:message code="button.list" /></a></span><!-- 목록 -->
	<button class="btn_s2" onClick="fncGroupDelete();return false;" title="<spring:message code="button.delete" /> <spring:message code="input.button" />"><spring:message code="button.delete" /></button><!-- 삭제 -->
	<input type="submit" class="s_submit" value="<spring:message code="button.save" />" title="<spring:message code="button.save" /> <spring:message code="input.button" />" /><!-- 저장 -->
	</div><div style="clear:both;"></div>
	
</div>

<input type="hidden" name="faqGroupId" value="<c:out value='${groupManage.faqGroupId}'/>"/>
<%-- <input type="hidden" name="searchCondition" value="<c:out value='${groupManageVO.searchCondition}'/>"/>
<input type="hidden" name="searchKeyword" value="<c:out value='${groupManageVO.searchKeyword}'/>"/>
<input type="hidden" name="pageIndex" value="<c:out value='${groupManageVO.pageIndex}'/>"/>


<input type="hidden" name="deledUser" value=""/>--%>
<input type="hidden" name="addedFaq" value=""/>
</form>

<form id="frmIdDelete" name="frmDelete" method="post">
<input type="hidden" name="faqGroupId" value="<c:out value='${groupManage.faqGroupId}'/>"/>
<%-- <input type="hidden" name="searchCondition" value="<c:out value='${groupManageVO.searchCondition}'/>"/>
<input type="hidden" name="searchKeyword" value="<c:out value='${groupManageVO.searchKeyword}'/>"/>
<input type="hidden" name="pageIndex" value="<c:out value='${groupManageVO.pageIndex}'/>"/> --%>
</form>

</body>
</html>


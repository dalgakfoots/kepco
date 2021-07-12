<%
 /**
  * @Class Name : EgovFaqList.jsp
  * @Description : EgovFaqList 화면
  * @Modification Information
  * @
  * @  수정일             수정자                   수정내용
  * @ -------    --------    ---------------------------
  * @ 2009.02.01   박정규              최초 생성
  *   2016.06.13   김연호              표준프레임워크 v3.6 개선
  *  @author 공통서비스팀
  *  @since 2009.02.01
  *  @version 1.0
  *  @see
  *
  */
%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="pageTitle"><spring:message code="comUssOlhFaq.faqVO.title"/></c:set>
<!DOCTYPE html>
<html>
<head>
<title>${pageTitle} <spring:message code="title.list" /></title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/com.css' />">
<script type="text/javascript">
/*********************************************************
 * 초기화
 ******************************************************** */
function fn_egov_init(){
	// 첫 입력란에 포커스..
	document.faqForm.searchCnd.focus();
}

/*********************************************************
 * 페이징 처리 함수
 ******************************************************** */
function fn_egov_select_linkPage(pageNo){
	document.faqForm.pageIndex.value = pageNo;
	document.faqForm.action = "<c:url value='/uss/olh/faq/selectFaqList.do'/>";
   	document.faqForm.submit();
}
/*********************************************************
 * 조회 처리 함수
 ******************************************************** */
function fn_egov_search_faq(){
	document.faqForm.pageIndex.value = 1;
	document.faqForm.submit();
}
/* ********************************************************
 * 상세회면 처리 함수
 ******************************************************** */
function fn_egov_inquire_faqdetail(faqId) {
	// 사이트 키값(siteId) 셋팅.
	document.faqForm.faqId.value = faqId;
  	document.faqForm.action = "<c:url value='/uss/olh/faq/selectFaqDetail.do'/>";
  	document.faqForm.submit();
}

//

function fncCheckAll() {
    var checkField = document.faqForm.checkField;
    if(document.faqForm.checkAll.checked) {
        if(checkField) {
            if(checkField.length > 1) {
                for(var i=0; i < checkField.length; i++) {
                    checkField[i].checked = true;
                }
            } else {
                checkField.checked = true;
            }
        }
    } else {
        if(checkField) {
            if(checkField.length > 1) {
                for(var j=0; j < checkField.length; j++) {
                    checkField[j].checked = false;
                }
            } else {
                checkField.checked = false;
            }
        }
    }
}



function fnAddUserView(){
	var list = [];
	var chkList = document.getElementsByName('checkField');
	for(var i = 0 ; i < chkList.length ; i++){
		if (chkList[i].checked == true){
			var newObj = new Object();
			var temp = i + 1;
			var faqId = document.querySelector("body > form > div > table > tbody > tr:nth-child("+ (temp) + ") > td:nth-child(2) > input[type=hidden]:nth-child(2)").value;
			newObj.faqId = faqId;
			var faqSubject = document.querySelector("body > form > div > table > tbody > tr:nth-child("+ (temp) +") > td.left").innerText;
			newObj.faqSubject = faqSubject;
			list.push(newObj);
			
		}
	}
	
	console.log(list);
	opener.call(list);
	window.close();
} 

</script>
</head>
<body onload="fn_egov_init()">
<!-- javascript warning tag  -->
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>

<form name="faqForm" action="<c:url value='/uss/olh/faq/selectFaqList.do'/>" method="post" onSubmit="fn_egov_search_faq(); return false;"> 
<div class="board">
	<h1>사후대응훈련문제 <spring:message code="title.list" /></h1>
	<!-- 하단 버튼 -->
	<div class="search_box" title="<spring:message code="common.searchCondition.msg" />">
		<ul>
			<li>
				<select name="searchCnd" title="<spring:message code="title.searchCondition" /> <spring:message code="input.cSelect" />">
					<option value="0"  <c:if test="${searchVO.searchCnd == '0'}">selected="selected"</c:if> ><%-- <spring:message code="comUssOlhFaq.faqVO.qestnSj" /> --%>문제제목</option><!-- 질문제목 -->
				</select>
			</li>
			<!-- 검색키워드 및 조회버튼 -->
			<li>
				<input class="s_input" name="searchWrd" type="text"  size="35" title="<spring:message code="title.search" /> <spring:message code="input.input" />" value='<c:out value="${searchVO.searchWrd}"/>'  maxlength="155" >
				<input type="submit" class="s_btn" value="<spring:message code="button.inquire" />" title="<spring:message code="title.inquire" /> <spring:message code="input.button" />" />
				<span class="btn_b"><a href="javascript:fnAddUserView();"  title="<spring:message code="button.create" /> <spring:message code="input.button" />"><spring:message code="button.create" /></a></span>
			</li>
		</ul>
	</div>
	
	<!-- 목록영역 -->
	<table class="board_list" summary="<spring:message code="common.summary.list" arguments="${pageTitle}" />">
	<caption>${pageTitle}<spring:message code="title.list" /></caption>
	<colgroup>
		<col style="width: 9%;">
		<col style="width: 3%">
		<col style="width: 49%;">
		<col style="width: 13%;">
	</colgroup>
	<thead>
	<tr>
		<th><spring:message code="table.num" /></th><!-- 번호 -->
		<th><input type="checkbox" name="checkAll" class="check2" onclick="javascript:fncCheckAll()" title="<spring:message code="input.selectAll.title" />"></th><!-- 전체선택 -->
		<th class="board_th_link"><%-- <spring:message code="comUssOlhFaq.faqVO.qestnSj" /> --%>문제제목</th><!-- 질문제목 -->
		<th><spring:message code="table.regdate" /></th><!-- 등록일자 -->
	</tr>
	</thead>
	<tbody class="ov">
	<c:if test="${fn:length(resultList) == 0}">
	<tr>
		<td colspan="4"><spring:message code="common.nodata.msg" /></td>
	</tr>
	</c:if>
	<c:forEach items="${resultList}" var="resultInfo" varStatus="status">
	<tr>
		<td><c:out value="${(searchVO.pageIndex-1) * searchVO.pageSize + status.count}"/></td>
		<td>
	        <input name="checkField" title="checkField <c:out value="${status.count}"/>" type="checkbox"/>
	        <input name="checkId" type="hidden" value="<c:out value='${resultInfo.faqId}'/>"/>
	    </td>
		<td class="left"><c:out value='${fn:substring(resultInfo.qestnSj, 0, 40)}'/></td>
		<td><c:out value='${resultInfo.frstRegisterPnttm}'/></td>
	</tr>
	</c:forEach>
	</tbody>
	</table>
	
	<!-- paging navigation -->
	<div class="pagination">
		<ul>
		<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_egov_select_linkPage"/>
		</ul>
	</div>
	
	
</div>

<input name="faqId" type="hidden" value="<c:out value='${searchVO.faqId}'/>">
<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>">
</form>

</body>
</html>
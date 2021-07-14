<%
 /**
  * @Class Name : EgovFaqGroupManage.jsp
  * @Description : EgovFaqGroupManage List 화면
  * @Modification Information
  * @
  * @  수정일                     수정자               수정내용
  * @ ----------    --------    ---------------------------
  * @ 
  *  
  *  @version 1.0
  *  @see
  *
  */
%>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="pageTitle"><spring:message code="comCopSecGmt.title"/></c:set>
<!DOCTYPE html>
<html>
<head>
<title>${pageTitle} <spring:message code="title.list" /></title><!-- 그룹관리 목록 -->
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/com.css' />">
<script type="text/javaScript" language="javascript" defer="defer">
function fncCheckAll() {
    var checkField = document.groupManage.delYn;
    if(document.groupManage.checkAll.checked) {
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

function fncManageChecked() {

    var checkField = document.groupManage.delYn;
    var checkId = document.groupManage.checkId;
    var returnValue = "";
    var returnBoolean = false;
    var checkCount = 0;

    if(checkField) {
        if(checkField.length > 1) {
            for(var i=0; i<checkField.length; i++) {
                if(checkField[i].checked) {
                	checkCount++;
                    checkField[i].value = checkId[i].value;

                    if(returnValue == "")
                        returnValue = checkField[i].value;
                    else
                        returnValue = returnValue + ";" + checkField[i].value;
                }
            }
            if(checkCount > 0)
                returnBoolean = true;
            else {
                alert("<spring:message code="comCopSecGmt.validate.groupSelect" />");//선택된 그룹이 없습니다.
                returnBoolean = false;
            }
        } else {
        	 if(document.listForm.delYn.checked == false) {
                alert("<spring:message code="comCopSecGmt.validate.groupSelect" />");//선택된 그룹이 없습니다.
                returnBoolean = false;
            }
            else {
                returnValue = checkId.value;
                returnBoolean = true;
            }
        }
    } else {
        alert("<spring:message code="comCopSecGmt.validate.groupSelectResult" />");//조회된 결과가 없습니다.
    }

    document.groupManage.faqGroupIds.value = returnValue;
    return returnBoolean;
}
/* 
function fncSelectGroupList(pageNo){
    document.groupManage.searchCondition.value = "1";
    document.groupManage.pageIndex.value = pageNo;
    document.groupManage.action = "<c:url value='/sec/gmt/EgovGroupList.do'/>";
    document.groupManage.submit();
}

function fncSelectGroup(groupId) {
    document.groupManage.groupId.value = groupId;
    document.groupManage.action = "<c:url value='/sec/gmt/EgovGroup.do'/>";
    document.groupManage.submit();
} */

function fncAddGroupInsert() {
    location.replace("<c:url value='/sec/gmt/EgovGroupInsertView.do'/>");
}

function fncGroupListDelete() {
	if(fncManageChecked()) {
	    if(confirm("<spring:message code="comCopSecGmt.validate.confirm.delete" />")) {//삭제하시겠습니까?
            document.groupManage.action = "<c:url value='/uss/olh/faq/EgovFaqGroupListDelete.do'/>";
            document.groupManage.submit();
	    }
	}
}
/* 
function linkPage(pageNo){
    document.groupManage.searchCondition.value = "1";
    document.groupManage.pageIndex.value = pageNo;
    document.groupManage.action = "<c:url value='/sec/gmt/EgovGroupList.do'/>";
    document.groupManage.submit();
} */

function press() {

    if (event.keyCode==13) {
    	fncSelectGroupList('1');
    }
}
</script>

</head>

<body>
<!-- javascript warning tag  -->
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>
<form:form name="groupManage" action="${pageContext.request.contextPath}/uss/olh/faq/selectFaqGroupList.do" method="post">
<div class="board">
	<h1>훈련문제그룹 <spring:message code="title.list" /></h1><!-- 그룹관리 목록 -->
	<!-- 검색영역 -->
	<div class="search_box" title="<spring:message code="common.searchCondition.msg" />">
		<ul>
			<!-- 검색키워드 및 조회버튼 -->
			<li>
				<input type="submit" class="s_btn" value="<spring:message code="button.inquire" />" title="<spring:message code="title.inquire" /> <spring:message code="input.button" />" /><!-- 조회 -->
				<input type="button" class="s_btn" onClick="fncGroupListDelete()" value="<spring:message code="title.delete" />" title="<spring:message code="title.delete" /> <spring:message code="input.button" />" /><!-- 삭제 -->
				<span class="btn_b"><a href="<c:url value='/uss/olh/faq/insertFaqGroupView.do'/>" title="<spring:message code="button.create" /> <spring:message code="input.button" />"><spring:message code="button.create" /></a></span><!-- 등록 -->
			</li>
		</ul>
	</div>
	<!-- 목록영역 -->
	<table class="board_list" summary="<spring:message code="common.summary.list" arguments="${pageTitle}" />">
	<caption>훈련문제그룹 <spring:message code="title.list" /></caption>
	<colgroup>
		<col style="width: 3%;">
		<col style="width: 22%;">
		<col style="width: 26%;">
		<col style="width: ;">
		<col style="width: ;">
	</colgroup>
	<thead>
	<tr>
		<th><input type="checkbox" name="checkAll" class="check2" onclick="javascript:fncCheckAll()" title="<spring:message code="input.selectAll.title" />"></th><!-- 번호 -->
		<th class="board_th_link"><spring:message code="comCopSecGmt.list.groupId" /></th><!-- 그룹 ID -->
		<th><spring:message code="comCopSecGmt.list.groupNm" /></th><!-- 그룹 명 -->
		<th><spring:message code="comCopSecGmt.list.groupDc" /></th><!-- 설명 -->
		<th><spring:message code="table.regdate" /></th><!-- 등록일자 -->
	</tr>
	</thead>
	<tbody class="ov">
	<c:if test="${fn:length(resultList) == 0}">
	<tr>
		<td colspan="5"><spring:message code="common.nodata.msg" /></td>
	</tr>
	</c:if>
	<c:forEach var="item" items="${resultList}" varStatus="status">
	<tr>
	    <td><input type="checkbox" name="delYn" class="check2" title="선택"><input type="hidden" name="checkId" value="<c:out value="${item.FAQ_GROUP_ID}"/>" /></td>
	    <td><a href="<c:url value='/uss/olh/faq/selectFaqGroup.do?groupId=${item.FAQ_GROUP_ID}'/>"><c:out value="${item.FAQ_GROUP_ID}"/></a></td>
	    <td><c:out value="${item.FAQ_GROUP_NM}"/></td>
	    <td><c:out value="${item.FAQ_GROUP_DC}"/></td>
	    <td><c:out value="${fn:substring(item.CREATED_DATETIME,0,10)}"/></td>
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
	<%-- <c:if test="${!empty searchVO.pageIndex }">
		<!-- paging navigation -->
		<div class="pagination">
			<ul><ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="linkPage"/></ul>
		</div>
	</c:if> --%>


</div><!-- end div board -->

<input type="hidden" name="faqGroupId"/>
<input type="hidden" name="faqGroupIds"/>
<input type="hidden" name="pageIndex" value="<c:out value='${searchVO.pageIndex}'/>"/>
<input type="hidden" name="searchCondition"/>
</form:form>
</body>

</body>
</html>
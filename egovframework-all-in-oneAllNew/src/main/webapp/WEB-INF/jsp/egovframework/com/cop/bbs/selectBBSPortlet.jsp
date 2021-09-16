<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : EgovBBSListPortlet.jsp
  * @Description : 게시판 목록 조회 포틀릿화면
  * @Modification Information
  * @
  * @  수정일      수정자            수정내용
  * @ -------        --------    ---------------------------
  * @ 2009.04.07   이삼섭          최초 생성
  *	  2018.10.11      이정은          포털 메인화면 - 게시판 목록 조회 포틀릿추가
  *
  *  @author 공통서비스 개발팀 이삼섭
  *  @since 2009.04.07
  *  @version 1.0
  *  @see
  *
  */
%>

<script>
	/* function selectBBSMasterDetail(bbsOrder,param){
		console.log('bbsOrder, param : '+bbsOrder+ " , "+param);
		var options = 'top=10, left=10, width=760, height=600, status=no, menubar=no, toolbar=no, resizable=no';
		var num = 0;
		var newWin = window.open("about:blank","winName",options);
		var itName = 'blogForm'+bbsOrder;
		var form = document.getElementsByName(itName);
		var clone = form[param].cloneNode(true);
		clone.action = "<c:url value='/cop/bbs/selectArticleDetail.do'/>";
		clone.target="winName";
		clone.name="num"+ num++;
		document.body.appendChild(clone);
		clone.submit();
		document.body.removeChild(clone);
	} */
	function selectBBSMasterDetail(nttId , bbsId, bbsOrder){
		console.log("nttId , bbsId : "+ nttId+" , "+bbsId);
		var options = 'top=10, left=10, width=760, height=600, status=no, menubar=no, toolbar=no, resizable=no';
		var toGo = "<c:url value='/cop/bbs/selectArticleDetail.do?'/>"+"bbsId="+bbsId+"&nttId="+nttId;
		var newWin = window.open(toGo,"winName",options);
		
		
	}
</script>

<div class="board" style="width:100%;">
	<table class="board_list">
		<colgroup>
			<col style="width:10%;">
			<col style="width:40%;">
			<col style="width:10%;">
			<col style="width:20%;">
		</colgroup>
		<thead>
			<th>번호</th>
			<th>제목</th>
			<th>등록자</th>
			<th>등록일</th>
		</thead>
		<tbody>
		<c:forEach var="result" items="${resultList}" varStatus="status">
		   	<tr style="text-align:center;">
				<input type="hidden" name="nttId" value="<c:out value='${result.nttId}'/>"/>
				<input type="hidden" name="bbsId" value="<c:out value='${result.bbsId}'/>"/>
				<input type="hidden" name="bbsNm" value="<c:out value='${result.bbsNm}'/>"/>
				<%-- <input type="submit" value="<c:out value="${result.nttSj}"/>"/> --%>
				<td>${status.count}</td>
				<td style="text-align:left;"><a href="javascript:selectBBSMasterDetail('${result.nttId}' , '${result.bbsId}' , '${bbsOrder}')">${result.nttSj}</a>
				<c:if test="${result.cnt <= 0 }"><span style="color:red;">new</span></c:if></td>
				<td>${result.frstRegisterNm}</td>
				<td>${result.frstRegisterPnttm}</td>
			<tr>
		</c:forEach>
		</tbody>
	</table>
</div>

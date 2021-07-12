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
	function selectBBSMasterDetail(bbsOrder,param){
		var options = 'top=10, left=10, width=760, height=600, status=no, menubar=no, toolbar=no, resizable=no';
		var num = 0;
		var newWin = window.open("about:blank","winName",options);
		//var form = document.blogForm;
		var form = document.blogForm[bbsOrder];
		console.log(form);
		//var clone = form[param].cloneNode(true);
		var clone = form.cloneNode(true);
		console.log(clone);
		clone.action = "<c:url value='/cop/bbs/selectArticleDetail.do'/>";
		clone.target="winName";
		clone.name="num"+ num++;
		document.body.appendChild(clone);
		clone.submit();
		document.body.removeChild(clone);
	}
</script>
<ul>
<% int cnt = 0;
   request.setAttribute("cnt", cnt);
%>
<c:forEach var="result" items="${resultList}" varStatus="status">
   	<li style="padding-top:5px">
		<%-- <form name="blogForm" method="post"  action="<c:url value='/cop/bbs/selectBBSMasterDetail.do'/>" > --%>
		<form name="blogForm" method="post"  action="javascript:selectBBSMasterDetail(${bbsOrder },${cnt})" >
		<input type="hidden" name="nttId" value="<c:out value='${result.nttId}'/>"/>
		<input type="hidden" name="bbsId" value="<c:out value='${result.bbsId}'/>"/>
		<input type="hidden" name="bbsNm" value="<c:out value='${result.bbsNm}'/>"/>
		<input type="submit" value="<c:out value="${result.nttSj}"/>"/>
		</form>
	</li>
	<c:set var="cnt" value="${cnt + 1}"/>
</c:forEach>
</ul>

<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><spring:message code="comSymMnuMpm.mainView.mainViewTitle"/></title><!-- 행정안전부 공통서비스 테스트 사이트 -->
<script language="javascript" src="<c:url value='/js/egovframework/com/main.js' />"></script>
<script language="javascript">
function chk_all(val) {

	var arr_chk = document.getElementsByName("chk");

		if (val == "Y") {

			for(i=0;i< arr_chk.length; i++) {
				arr_chk[i].checked =true;
			}
		}
		else if(val == "N") {
			for(i=0;i< arr_chk.length; i++) {
				arr_chk[i].checked =false;
			}
		}
}

</script>
</head>

<body topmargin="0" leftmargin="0">

<!-- header -->
<c:import url="./head.jsp" />

<!-- contents -->
<div>
	
	<!-- 상단 -->
	<div class="mp_top">
		<div class="l"><!-- left layout -->
			
			<div class="boxStyle box1">
			<h3 class="m_tit01 mt15">사이버 위기 경보</h3>
				<c:import charEncoding="utf-8" url="/cop/bbs/selectBBSPortlet.do?bbsId=BBSMSTR_000000000021&bbsOrder=0" />
			</div>
			
			
			
			<div class="boxStyle box2">
			<h3 class="m_tit01 mt15">일반 알림문</h3>
				<c:import charEncoding="utf-8" url="/cop/bbs/selectBBSPortlet.do?bbsId=BBSMSTR_000000000032&bbsOrder=1"/>
			</div>
			
		</div>
		
		<div class="r"><!-- right layout -->
			<!-- 부서일정관리  -->
			<%-- <h3 class="m_tit01 mt15"><spring:message code="comSymMnuMpm.mainView.deptSchdulManageMainList"/></h3><!-- 부서일정관리 --> --%>
			
			<div class="boxStyle box3">
			<h3 class="m_tit01 mt15">사이버위협 정보 공유</h3>
				<c:import charEncoding="utf-8" url="/cop/bbs/selectBBSPortlet.do?bbsId=BBSMSTR_000000000031&bbsOrder=2" ></c:import>
			</div>
			
			
			
			<div class="boxStyle box4">
			<h3 class="m_tit01 mt15">사이버위기경보 단계</h3>
				<c:import charEncoding="utf-8" url="/sym/mnu/mpm/SelectCyberThreatAlert.do" ></c:import>
			</div>
		</div>
	</div>
	

<!-- bottom -->
<c:import url="./main_bottom.jsp" />
</div><!-- contents -->

</body>
</html>

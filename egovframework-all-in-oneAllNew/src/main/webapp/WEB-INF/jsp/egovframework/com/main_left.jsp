<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%
 /**
  * @Class Name : left.jsp
  * @Description :  좌측메뉴화면
  * @Modification Information
  * @
  * @  수정일         수정자                   수정내용
  * @ -------    --------    ---------------------------
  * @ 2009.03.10    이용          최초 생성
  *
  *  @author 공통서비스 개발팀 이용
  *  @since 2009.03.10
  *  @version 1.0
  *  @see
  *
  */

  /* Image Path 설정 */
  String imagePath_icon   = "/images/egovframework/com/sym/mnu/mpm/icon/";
  String imagePath_button = "/images/egovframework/com/sym/mnu/mpm/button/";
%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<link rel="stylesheet" href="<c:url value='/css/egovframework/com/sym/cal/cal.css' />" type="text/css" />


<title>메뉴정보등록</title>
<script type="text/javascript">
var imgpath = "<c:url value='/images/egovframework/com/cmm/utl/'/>";
var getContextPath = "${pageContext.request.contextPath}";
var path = "http://" + "${pageContext.request.serverName}" + ":" + "${pageContext.request.serverPort}";

function fn_main_leftPageMove(menuNo){
	
	document.menuListForm.menuNo.value=menuNo;
    document.menuListForm.action = "<c:url value='/sym/mnu/mpm/selectProgramUrl.do'/>";
    document.menuListForm.submit();
}

</script>
<script language="javascript1.2" src="<c:url value='/js/egovframework/com/sym/mnu/mpm/EgovMenuList.js' />" /></script>
</head>
<body leftmargin="0" topmargin="0" marginwidth="0" marginheight= "0">
<form name="menuListForm" action ="<c:url value='/sym/mnu/mpm/EgovMenuListSelect.do' />" method="get" target="main_right">
<input type="hidden" name="menuNo" value="test"/>

<DIV id="main" style="display:">

<table width="181" cellpadding="8">
  <tr>
    <td width="181" class="title_left" >
        <div style="width:0px; height:0px;">
        
        
<%-- 
		<c:forEach var="result" items="${list_menulist}" varStatus="status" >
			<input type="hidden" name="tmp_menuNm" value="${result.menuNo}|${result.upperMenuId}|${result.menuNm}|${result.relateImagePath}|${result.relateImageNm}|${pageContext.request.contextPath}/${result.chkURL}|"/>
		</c:forEach>
		
	
			<div class="tree" style="overflow: auto; position: absolute; z-index: 5; padding: 0pt 0pt 0pt 49px; width: 214px; height: 512px;">
 --%>
			<c:forEach var="result" items="${list_menulist}" varStatus="status" >
				<input type="hidden" name="tmp_menuNm" value="${result.menuNo}|${result.upperMenuId}|${result.menuNm}|${result.relateImagePath}|${result.relateImageNm}|${pageContext.request.contextPath}/${result.chkURL}|"/>
			</c:forEach>

			
			
			<div class="lnb">
				<div class="lnb-depth1-ul">
				<c:forEach var = "item" items = "${list_menulist}">
					<c:if test="${item.upperMenuId eq '0'}">
						<div class="lnb-depth1-li">
							<a class="lnb-depth1-title">${item.menuNm}</a>
							<div class="lnb-depth2-ul">
								<c:forEach var ="inner" items="${list_menulist}">
									<c:if test="${inner.upperMenuId eq item.menuNo }">
										<div class="lnb-depth2-li"><a href="javascript:fn_main_leftPageMove('${inner.menuNo}')">${inner.menuNm}</a>
										<c:if test="${inner.upperMenuId eq '13000000' }">
											<span style="color:red;"><c:if test="${inner.isNew eq 'Y' }">new</c:if></span>
										</c:if>
										</div>
									</c:if>
								</c:forEach>
							</div>
						</div>
					</c:if>
				</c:forEach>
				</div>
			</div> 
		</div>
		
   </td>
 </tr>
</table>
</DIV>
</form>
</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8">
    <link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/com.css' />">

    <script src="<c:url value='/js/egovframework/com/cmm/jquery.js' />"></script>
    <script src="<c:url value='/js/egovframework/com/cmm/jqueryui.js' />"></script>

    <title>메시지 목록</title>

    <script type="text/javascript">
        function fn_egov_select_linkPage(pageNo){
            document.searchForm.pageIndex.value = pageNo;
            document.searchForm.submit();
        }
    </script>
</head>
<body>
    <div class="board">
        <c:choose>
        <c:when test="${role.author_code == 'ROLE_ADMIN'}">
        <h1>전송된 메시지함</h1>
        <div class="search_box">
            <ul>
                <li>
                    <span class="btn_b">
                        <a href="<c:url value='/message/trainMessageWrite.do'/>">메시지작성</a>
                    </span>
                </li>
            </ul>
        </div>
        </c:when>
        <c:otherwise>
        <h1>메시지 목록</h1>
        </c:otherwise>
        </c:choose>
        <form name="searchForm" action="<c:url value='/message/trainMessageList.do'/>" method="post">
            <input type="hidden" name="pageIndex" value="1"/>
        </form>
            <table class="board_list">
                <colgroup>
                    <col width=";">
                    <col width=";">
                    <col width=";">
                    <col width=";">
                </colgroup>
                <thead>
                    <tr>
                        <th>수신자</th>
                        <th>메세지제목</th>
                        <th>발신자</th>
                        <th>전송시간</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${messageList}" var="message">
                        <tr>
                            <td>
                                <c:out value="${message.teamId}"/>
                            </td>
                            <td>
                                <form name="messageDetail" method="post" action="<c:url value='/message/trainMessageDetail.do'/>">
                                    <input type="hidden" name="messageId" value="${message.messageId}"/>
                                    <span class="link">
                                        <c:if test="${role.author_code ne 'ROLE_ADMIN'}">
                                            <c:if test="${message.readCount <= 0}">
                                                <span style="color:red;">new</span>
                                            </c:if>
                                        </c:if>
                                        <input type="submit" value="${fn:substring(message.messageTitle,0,40)}">
                                    </span>
                                </form>
                            </td>
                            <td>
                                <c:out value="${message.createdUserId}"/>
                            </td>
                            <td>
                                <c:out value="${message.createdDateTime}"/>
                            </td>
                        </tr>
                    </c:forEach>
                    <c:if test="${fn:length(messageList) == 0}">
                        <tr>
                            <td colspan="4"><c:out value="받은 메시지가 없습니다."/></td>
                        </tr>
                    </c:if>
                </tbody>
            </table>
        <div class="pagination">
            <ul>
                <ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_egov_select_linkPage"/>
            </ul>
        </div>
    </div>
</body>
</html>

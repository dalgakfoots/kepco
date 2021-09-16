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
    <title>메시지 상세</title>
    <script type="text/javascript">

    </script>
</head>
<body>
<div class="wTableFrm">
    <h2>메시지 확인</h2>
    <table class="wTable">
        <colgroup>
            <col width="20%;"/>
            <col width=";"/>
        </colgroup>
        <tbody>
            <tr>
                <th>메시지 제목</th>
                <td>
                    <c:out value="${messageVO.messageTitle}"/>
                </td>
            </tr>
            <tr>
                <th>메시지 내용</th>
                <td>
                    <c:out value="${messageVO.messageContent}" escapeXml="false"/>
                </td>
            </tr>
        </tbody>
    </table>
    <div class="search_box">

        <ul>
            <li>
                <span class="btn_b"><a href="<c:url value='/message/trainMessageList.do'/>">메시지목록</a></span>
            </li>
        </ul>
    </div>
</div>
</body>
</html>

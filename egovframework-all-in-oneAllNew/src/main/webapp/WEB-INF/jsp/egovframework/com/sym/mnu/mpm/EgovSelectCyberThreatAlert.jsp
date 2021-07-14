<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div>
	<c:choose>
		<c:when test='${searchResult.level eq "1"}'>
			<img src="<c:url value='/images/egovframework/com/sym/mpm/cyber_terror_level01.png' />" alt="정상" />
		</c:when>
		<c:when test='${searchResult.level eq "2"}'>
			<img src="<c:url value='/images/egovframework/com/sym/mpm/cyber_terror_level02.png' />" alt="관심" />
		</c:when>
		<c:when test='${searchResult.level eq "3"}'>
			<img src="<c:url value='/images/egovframework/com/sym/mpm/cyber_terror_level03.png' />" alt="주의" />
		</c:when>
		<c:when test='${searchResult.level eq "4"}'>
			<img src="<c:url value='/images/egovframework/com/sym/mpm/cyber_terror_level04.png' />" alt="경계" />
		</c:when>
		<c:when test='${searchResult.level eq "5"}'>
			<img src="<c:url value='/images/egovframework/com/sym/mpm/cyber_terror_level05.png' />" alt="심각" />
		</c:when>
		<c:otherwise>
		err
		</c:otherwise>
	</c:choose>
</div>
</body>
</html>
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
    <link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/utl/report/style.css'/>">

    <script src="<c:url value='/js/egovframework/com/cmm/jquery.js' />"></script>
    <script src="<c:url value='/js/egovframework/com/cmm/jqueryui.js' />"></script>
    <script src="<c:url value='/js/egovframework/com/utl/report/comboTreePlugin.js'/>"></script>

    <title>보고서 제출</title>

    <script type="text/javascript">

        window.onload = function (){

            var teamJSONData = [
                {
                    id: 'all',
                    title: '전체',
                    subs: [
                        <c:forEach var="teamId" items="${teamIdList}" varStatus="status">
                            <c:out value="{"/>
                            <c:out value='id:"${status.count}"' escapeXml="false"/>,
                            <c:out value='title:"${teamId.get("TEAM_ID")}"' escapeXml="false"/>
                            <c:out value="},"/>
                        </c:forEach>
                    ]
                }
            ]

            comboTree1 = $('#teamIds').comboTree({
                source:teamJSONData,
                isMultiple: true,
                cascadeSelect: true
            });

        }

        function fn_egov_select_linkPage(pageNo){
            document.searchForm.pageIndex.value = pageNo;
            document.searchForm.action = "<c:url value='/report/abbreviatedReportList.do'/>";
            document.searchForm.submit();
        }

    </script>
</head>
<body>
    <div class="board">
        <h1>제출된 보고서</h1>
        <form name="searchForm" action="<c:url value='/report/abbreviatedReportList.do'/>" method="post">
            <input type="hidden" name="pageIndex" value="1"/>
            <div class="search_box">
                <ul>
                    <c:if test="${role.get('author_code') eq 'ROLE_ADMIN'}">
                    <li>
                        <select name="type">
                            <option value="">보고서유형</option>
                            <option value="MDR" <c:if test="${searchVO.reportType eq 'MDR'}">selected</c:if> > 악성 코드 식별</option>
                            <option value="WDR" <c:if test="${searchVO.reportType eq 'WDR'}">selected</c:if> >웹 공격 식별</option>
                        </select>
                    </li>
                    <li style="width: 35%;">
                        <input class="s_input" name="title" type="text" maxlength="100" style="width: 100%;" value="${searchVO.reportTitle}">
                    </li>
                    <li>
                        <select name="status">
                            <option value="">상태</option>
                            <option value="100" <c:if test="${searchVO.reportStatus eq '100'}">selected</c:if> >작성중</option>
                            <option value="200" <c:if test="${searchVO.reportStatus eq '200'}">selected</c:if>>제출 후 관리자 검토 중</option>
                            <option value="300" <c:if test="${searchVO.reportStatus eq '300'}">selected</c:if>>관리자가 재검토 요청</option>
                            <option value="400" <c:if test="${searchVO.reportStatus eq '400'}">selected</c:if>>관리자 검토 완료</option>
                        </select>
                    </li>
                    <li style="width: 21%;">
                        <input type="text" id="teamIds" name="teamName" class="comboTreeInputBox" autocomplete="off" value="${searchVO.teamId}">
                    </li>
                    <li>
                        <input type="submit" class="s_btn" value="조회" /><!-- 조회 -->
                    </li>
                    </c:if>
                    <c:if test="${role.get('author_code') eq 'ROLE_USER'}">
                    <li>
                        <span class="btn_b">
                            <a href="<c:url value='/report/abbreviatedReportRegist.do'/>">보고서 작성</a>
                        </span>
                    </li>
                    </c:if>
                </ul>
            </div>
        </form>
        <table class="board_list">
            <colgroup>
                <col width="30%;">
                <col width="20%;">
                <col width=";">
            </colgroup>
            <thead>
                <tr>
                    <c:if test="${role.get('author_code') eq 'ROLE_ADMIN'}">
                    <th>팀명</th>
                    </c:if>
                    <th>보고서유형</th>
                    <th>상태</th>
                    <th>보고서제목</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="vo" items="${reportList}">
                    <tr>
                        <c:if test="${role.get('author_code') eq 'ROLE_ADMIN'}">
                        <td><c:out value="${vo.teamId}"/></td>
                        </c:if>
                        <td>
                            <c:choose>
                                <c:when test="${vo.reportType eq 'MDR'}">
                                    <c:out value="악성 코드 식별"/>
                                </c:when>
                                <c:when test="${vo.reportType eq 'WDR'}">
                                    <c:out value="웹 공격 식별"/>
                                </c:when>
                            </c:choose>
                        </td>
                        <td>
                            <c:choose>
                                <c:when test="${vo.reportStatus eq '100'}">
                                    <c:out value="작성중"/>
                                </c:when>
                                <c:when test="${vo.reportStatus eq '200'}">
                                    <c:out value="제출 후 관리자 검토 중"/>
                                </c:when>
                                <c:when test="${vo.reportStatus eq '300'}">
                                    <c:out value="관리자가 재검토 요청"/>
                                </c:when>
                                <c:when test="${vo.reportStatus eq '400'}">
                                    <c:out value="관리자 검토 완료"/>
                                </c:when>
                                <c:otherwise>
                                    <c:out value="에러발생. 관리자 문의"/>
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td>
                            <form name="reportVO" method="post" action="<c:url value='/report/abbreviatedReportDetail.do'/>">
                                <input type="hidden" name="trainId" value="<c:out value='${vo.trainId}'/>">
                                <input type="hidden" name="teamId" value="<c:out value='${vo.teamId}'/>">
                                <input type="hidden" name="reportId" value="<c:out value='${vo.reportId}'/>">
                                <span class="link">
                                    <input type="submit" value="${fn:substring(vo.reportTitle, 0, 40)}">
                                </span>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
                <c:if test="${fn:length(reportList) == 0}">
                    <tr>
                        <td colspan="3"><c:out value="자료가 없습니다."/></td>
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

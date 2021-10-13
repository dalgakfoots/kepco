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
    <title>보고서 제출</title>
    <script type="text/javascript">

        function abbreviatedReportSubmit(){
            if (confirm('제출하시겠습니까?')) {
                document.reportVO.action="<c:url value='/report/abbreviatedReportSubmit.do'/>";
                document.reportVO.submit();

            }
        }

        function abbreviatedReportUpdateView(){
            document.reportVO.action="<c:url value='/report/abbreviatedReportUpdateView.do'/>";
            document.reportVO.submit();
        }

        function abbreviatedReportDelete(){
            if(confirm('보고서를 삭제하시겠습니까?')){
                document.reportVO.action="<c:url value='/report/abbreviatedReportDelete.do'/>";
                document.reportVO.submit();
            }
        }

    </script>
</head>
<body>
<div class="wTableFrm">
    <h2>보고서 확인</h2>
    <table class="wTable">
        <tbody>
            <tr>
                <th>보고서 유형</th>
                <td>
                    <select style="width: 100%;" disabled>
                        <option value="MDR" <c:if test="${reportVO.reportType eq 'MDR'}">selected</c:if>> 악성 코드 식별 보고서</option>
                        <option value="WDR" <c:if test="${reportVO.reportType eq 'WDR'}">selected</c:if>>웹 공격 식별 보고서</option>
                    </select>
                </td>
            </tr>
            <tr>
                <th>보고서 제목</th>
                <td>
                    <c:out value="${reportVO.reportTitle}"/>
                </td>
            </tr>
            <tr>
                <th>보고서 내용</th>
                <td colspan="5" class="cnt" id="content">
                    <c:out value="${reportVO.reportContent}" escapeXml="false"/>
                </td>
            </tr>
            <tr>
                <th>채증된 파일</th>
                <td>
                    <c:choose>
                        <c:when test="${reportVO.attachFileId eq null}">
                            <c:out value="등록된 파일이 없습니다."/>
                        </c:when>
                        <c:otherwise>
                            <c:import url="/cmm/fms/selectFileInfs.do" charEncoding="utf-8">
                                <c:param name="param_atchFileId" value="${reportVO.attachFileId}" />
                            </c:import>
                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>
        </tbody>
    </table>
    <div class="search_box">

        <ul>
            <li>
                <span class="btn_b"><a href="<c:url value='/report/abbreviatedReportList.do'/>">보고서목록</a></span>
            </li>
            <c:choose>
            <c:when test="${reportVO.reportStatus eq '100' || reportVO.reportStatus eq '300'}"><%--100 : 작성중 , 300 : 관리자가 재검토 요청--%>
            <li>
                <span class="btn_b"><a href="javascript:abbreviatedReportDelete();">보고서삭제</a></span>
            </li>
            <li>
                <span class="btn_b"><a href="javascript:abbreviatedReportUpdateView();">보고서수정</a></span>
            </li>
            <li>
                <span class="btn_b"><a href="javascript:abbreviatedReportSubmit();">보고서제출</a></span>
            </li>
            </c:when>
            <c:when test="${reportVO.reportStatus eq '200'}">
                <c:if test="${role.get('author_code') eq 'ROLE_ADMIN'}">
                    <li>
                        <select id="reportScore">
                            <option value="10">10</option>
                            <option value="20">20</option>
                            <option value="30">30</option>
                            <option value="40">40</option>
                            <option value="50">50</option>
                            <option value="60">60</option>
                            <option value="70">70</option>
                            <option value="80">80</option>
                            <option value="90">90</option>
                            <option value="100">100</option>
                        </select>
                    </li>
                    <li>
                        <span class="btn_b"><a href="javascript:giveScoreToReport();">점수부여</a></span>
                    </li>
                    <li>
                        <span class="btn_b"><a href="javascript:rejectReport()">재검토요청</a></span>
                    </li>
                </c:if>
            </c:when>
            <c:when test="${reportVO.reportStatus eq '400'}">
                <c:if test="${role.get('author_code') eq 'ROLE_ADMIN'}">
                    <li>
                        <span class="btn_b"><a href="javascript:undoGiveScore()">점수부여취소</a></span>
                    </li>
                </c:if>
            </c:when>
            </c:choose>
        </ul>
    </div>
    <form name="reportVO" method="post">
        <input type="hidden" name="reportId" value="${reportVO.reportId}"/>
    </form>

    <c:if test="${role.get('author_code') eq 'ROLE_ADMIN'}">
    <form name="rejectReport" method="post" action="<c:url value='/report/abbreviatedReportReject.do'/>">
        <input type="hidden" name="reportId" value="${reportVO.reportId}"/>
        <input type="hidden" name="teamId" value="${reportVO.teamId}"/>
    </form>

    <form name="giveScoreToReport" method="post" action="<c:url value='/report/abbreviatedReportGiveScore.do'/>">
        <input type="hidden" name="trainId" value="${reportVO.trainId}"/>
        <input type="hidden" name="teamId" value="${reportVO.teamId}"/>
        <input type="hidden" name="reportId" value="${reportVO.reportId}"/>
        <input type="hidden" name="reportType" value="${reportVO.reportType}"/>
        <input type="hidden" name="score" value=""/>
    </form>

    <form name="undoGiveScore" method="post" action="<c:url value='/report/abbreviatedReportUndoGiveScore.do'/>">
        <input type="hidden" name="reportId" value="${reportVO.reportId}"/>
    </form>

    <script type="text/javascript">
        function giveScoreToReport(){
            if(confirm("점수부여 하시겠습니까?")) {
                let score = document.getElementById("reportScore").value;
                document.giveScoreToReport.score.value = score;
                document.giveScoreToReport.submit();
            }
        }

        function undoGiveScore(){
            if (confirm("점수부여취소 하시겠습니까?")) {
                document.undoGiveScore.submit();
            }
        }

        function rejectReport() {
            if (confirm("재검토요청 하시겠습니까?")) {
                document.rejectReport.submit();
            }
        }
    </script>
    </c:if>
</div>
</body>
</html>

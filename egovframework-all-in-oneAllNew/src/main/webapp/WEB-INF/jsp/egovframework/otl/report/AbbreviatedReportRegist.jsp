<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8">
    <link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/com.css' />">
    <link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/com.css' />">
    <link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/cmm/jqueryui.css' />">
    <%-- <script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/fms/EgovMultiFile.js'/>" ></script> --%>
    <script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/fms/EgovMultiFiles.js'/>" ></script>
    <script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/utl/EgovCmmUtl.js'/>" ></script>
    <script type="text/javascript" src="<c:url value='/html/egovframework/com/cmm/utl/ckeditor/ckeditor.js?t=B37D54V'/>" ></script>
    <title>보고서 제출</title>

    <script type="text/javascript">

        window.onload = function () {

            var editor = CKEDITOR.replace('reportContent', {extraPlugins: 'notification'});
            editor.on( 'required' ,
                function (evt) {
                    editor.showNotification("보고서내용 을 작성하세요. \n 보고서 양식을 다시 불러오려면, 보고서 유형을 다시 선택해주세요.",'info');
                    evt.cancel();
                }
            );

        };

        function selectBoxOnChange(obj){
            let param = obj.value;
            changeReportContentTemplate(param);
        }

        function changeReportContentTemplate(param) {

            if(param == "MDR"){
                CKEDITOR.instances.reportContent.setData("<p>악성 파일 식별 : </p> <p>악성 파일 유입 경로 : </p> <p>공격자 도메인 : </p>");
            }else if (param == "WDR") {
                CKEDITOR.instances.reportContent.setData("<p>웹 공격 탐지 테스트1 : </p> <p>웹 공격 탐지 테스트2 : </p> <p>웹 공격 탐지 테스트3 : </p>");
            }

        }

    </script>

</head>
<body>
<form name="reportVO" enctype="multipart/form-data" onsubmit="return confirm('저장하시겠습니까?')" method="post" action="<c:url value="/report/abbreviatedReportInsert.do" />">
    <div class="wTableFrm">
        <h2>보고서 작성</h2>
        <table class="wTable">
            <tbody>
                <tr>
                    <th>보고서 유형</th>
                    <td>
                        <select name="reportType" style="width: 100%;" onchange="selectBoxOnChange(this)">
                            <option value="MDR" selected>악성 코드 식별 보고서</option> <%--Malware Detection Report--%>
                            <option value="WDR">웹 공격 식별 보고서</option> <%--Web-attack Detection Report--%>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th>보고서 제목</th>
                    <td>
                        <input type="text" name="reportTitle" required>
                    </td>
                </tr>
                <tr>
                    <th>보고서 내용</th>
                    <td>
                       <textarea id="reportContent" name="reportContent" cols="300" rows="20" required>
                           <c:out value="<p>악성 파일 식별 : </p> <p>악성 파일 유입 경로 : </p> <p>공격자 도메인 : </p>"/>
                       </textarea>
                    </td>
                </tr>
                <tr>
                    <th>채증된 파일</th>
                    <td>
                        <input name="file_1" id="egovComFileUploader" type="file" multiple/><!-- 첨부파일 -->
                        <div id="egovComFileList"></div>
                    </td>
                </tr>
            </tbody>
        </table>
        <div class="btn">
            <input type="submit" class="s_submit" value="저장"/>
            <span class="btn_s"><a href="<c:url value='/report/abbreviatedReportList.do'/>">목록</a></span>
        </div>
    </div>

</form>
</body>
</html>

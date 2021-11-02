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

            if(param == "APT1"){
                CKEDITOR.instances.reportContent.setData(
                    "<p>□ 악성 행위를 수행하는 페이지 식별 <br>- 악성 페이지 판단 근거 : <br>- 악성 페이지 URL : </p> " +
                    "<p>□ 최초로 실행된 악성 파일 식별 <br>- 악성 파일 판단 근거 : <br>- 악성 파일의 MD 해시 : </p> " +
                    "<p>□ 지속성을 유지하기 위해 사용된 방법 분석 <br>- 지속성 유지 방법 분석 내용 : <br>- 플래그 : </p>" +
                    "<p>□ 권한상승에 사용된 방법 분석 <br>- 권한상승에 사용된 방법 분석 내용 : <br>- 권한상승에 사용된 레지스트리 키(HKLM\SAM\SAM) : </p>" +
                    "<p>□ 정보 수집을 위해 사용되는 악성코드 식별 <br>- 악성코드 식별 판단 근거 : <br>- 악성 프로세스의 이름 : <br>- 유출된 정보(입력데이터)가 저장된 파일의 이름 : </p>"
                );
            }else if (param == "APT2") {
                CKEDITOR.instances.reportContent.setData(
                    "<p>□ 악성 이메일 식별 <br>- 악성 이메일 판단 근거 : <br>- 악성 이메일 제목 : </p> " +
                    "<p>□ 악성 파일 식별 <br>- 악성 문서 판단 근거 : <br>- 악성 문서 제목 : </p> " +
                    "<p>□ 지속성을 유지하기 위해 사용된 방법 분석 <br>- 지속성 유지 방법 분석 내용 : <br>- 지속성을 유지시키는 이벤트의 이름 : </p>" +
                    "<p>□ 권한상승에 사용된 방법 분석 <br>- 권한상승에 사용된 방법 분석 내용 :  <br>- 권한상승을 위해 사용된 파일:  : </p>"
                );
            }else if (param == "RANSOM") {
                CKEDITOR.instances.reportContent.setData(
                    "<p>□ 악성 파일 식별 <br>- 악성 문서 판단 근거 : <br>- 악성 문서의 MD 해시 : </p> " +
                    "<p>□ 유입 경로 식별 <br>- 악성 파일(랜섬웨어)가 실행된 PC 판단 근거 : <br>- 악성 파일(랜섬웨어)가 실행된 PC의 IP 주소 : </p> " +
                    "<p>□ 파일 복원 <br>- 복원한 파일 중 'C:\Share Folder\개발팀 신입 자료\저장 매체 관리.pdf'의 md5 값 : </p>" +
                    "<p>□ C2 도메인 식별 <br>- C2 도메인 판단 근거 :  <br>- C2 도메인 주소 : </p>"
                );
            }else if (param == "WEB") {
                CKEDITOR.instances.reportContent.setData(
                    "<p>□ Session BruteForce에 취약한 페이지 식별 및 원인 파악 <br>- Session BruteForce에 취약한 페이지 판단 근거 : <br>- 취약한 함수의 이름 :  </p> " +
                    "<p>□ 공격자가 악성 페이지를 정상적인 페이지에 삽입하기 위해 사용된 취약점 식별 및 원익 파악 <br>- 취약점이 존재하는 페이지 : <br>- 취약한 함수의 이름 :  </p> "
                );
            }else if (param == "DDOS") {
                CKEDITOR.instances.reportContent.setData(
                    "<p>□ 공격 유형 식별 <br>- 공격 유형 판단 근거 : <br>- 공격 유형 :  </p> " +
                    "<p>□ 공격 대상 식별 <br>- 공격 대상 식별 근거 : <br>- 공격 대상의 IP : </p> "
                );
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
                            <option value="APT1" selected>APT#1 보고서</option>
                            <option value="APT2">APT#2 보고서</option>
                            <option value="RANSOM">랜섬웨어 보고서</option>
                            <option value="WEB">웹해킹 보고서</option>
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
                           <c:out value=
                          "<p>□ 악성 행위를 수행하는 페이지 식별 <br>- 악성 페이지 판단 근거 : <br>- 악성 페이지 URL : </p>
                           <p>□ 최초로 실행된 악성 파일 식별 <br>- 악성 파일 판단 근거 : <br>- 악성 파일의 MD 해시 : </p>
                           <p>□ 지속성을 유지하기 위해 사용된 방법 분석 <br>- 지속성 유지 방법 분석 내용 : <br>- 플래그 : </p>
                           <p>□ 권한상승에 사용된 방법 분석 <br>- 권한상승에 사용된 방법 분석 내용 : <br>- 권한상승에 사용된 레지스트리 키(HKLM\SAM\SAM) : </p>
                           <p>□ 정보 수집을 위해 사용되는 악성코드 식별 <br>- 악성코드 식별 판단 근거 : <br>- 악성 프로세스의 이름 : <br>- 유출된 정보(입력데이터)가 저장된 파일의 이름 : </p>"/>
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

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
    <script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/fms/EgovMultiFiles.js'/>" ></script>
    <script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/utl/EgovCmmUtl.js'/>" ></script>
    <script type="text/javascript" src="<c:url value='/html/egovframework/com/cmm/utl/ckeditor/ckeditor.js?t=B37D54V'/>" ></script>
    <title>보고서 제출</title>

    <script type="text/javascript">

        window.onload = function () {

            var editor = CKEDITOR.replace('messageContent', {extraPlugins: 'notification'});
            editor.on( 'required' ,
                function (evt) {
                    editor.showNotification("메시지를 작성하세요.",'info');
                    evt.cancel();
                }
            );

        };

        function messageVOSubmit() {
            if(confirm("메시지를 전송하시겠습니까?")){
                sendMessage();
                document.messageVO.submit();
            }
        }

    </script>

</head>
<body>
<form name="messageVO" onsubmit="return confirm('메시지를 전송하시겠습니까?')" method="post" action="<c:url value="/message/trainMessageSubmit.do" />">
    <c:if test="${selectedTeamId ne null and selectedTeamId ne ''}">
        <input type="hidden" id="teamId" name="teamId" value="${selectedTeamId}"/>
    </c:if>
    <div class="wTableFrm">
        <h2>메시지 작성</h2>
        <table class="wTable">
            <tbody>
                <tr>
                    <th>메시지<br/>수신팀</th>
                    <td>
                        <select name="teamId" style="width: 100%;" <c:if test="${selectedTeamId ne null and selectedTeamId ne ''}">disabled</c:if>>
                            <c:forEach items="${teamList}" var="team">
                                <option value="${team.TEAM_ID}" <c:if test="${selectedTeamId eq team.TEAM_ID}">selected</c:if> >
                                        ${team.TEAM_NM}
                                </option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th>메시지 제목</th>
                    <td>
                        <input type="text" name="messageTitle" required>
                    </td>
                </tr>
                <tr>
                    <th>메시지 내용</th>
                    <td>
                       <textarea id="messageContent" name="messageContent" cols="300" rows="20" required>
                       </textarea>
                    </td>
                </tr>
            </tbody>
        </table>
        <div class="search_box">
            <ul>
                <li>
                    <span class="btn_b"><a href="javascript:messageVOSubmit();">메시지전송</a></span>
                </li>
                <li>
                    <span class="btn_b"><a href="<c:url value='/message/trainMessageList.do'/>">목록</a></span>
                </li>
                <c:if test="${selectedTeamId ne null and selectedTeamId != ''}">
                <li>
                    <span class="btn_b"><a href="<c:url value='/report/abbreviatedReportList.do'/>">보고서목록</a></span>
                </li>
                </c:if>
            </ul>
        </div>
    </div>

</form>
<script type="text/javascript">
    var webSocket = new WebSocket("ws://localhost:8080/trainMessageSender");

    webSocket.onopen = function(message){console.log("messageSender open")};

    webSocket.onclose = function(message) {};

    function sendMessage(){
        let message = document.messageVO.teamId.value;
        <c:if test="${selectedTeamId ne null and selectedTeamId ne ''}">
            message = document.getElementById("teamId").value;
        </c:if>
        console.log("message = "+message);
        webSocket.send(message);

    }
</script>

</body>
</html>

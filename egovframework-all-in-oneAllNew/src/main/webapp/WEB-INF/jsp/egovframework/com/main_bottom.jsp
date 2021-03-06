<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/main_portal.css' />">
<title><spring:message code="comSymMnuMpm.main_bottom.mainBottomTitle"/></title><!-- 아래메인 -->
<style type="text/css">

.toast-bottom-center {
	display : flex;
}

#snackbar {
  visibility: hidden; /* Hidden by default. Visible on click */
  min-width: 500px; /* Set a default minimum width */
  margin-left: -250px; /* Divide value of min-width by 2 */
  background-color: #0d68b0; /* Black background color */
  color: #fff; /* White text color */
  text-align: center; /* Centered text */
  border-radius: 2px; /* Rounded borders */
  padding: 5px; /* Padding */
  position: fixed; /* Sit on top of the screen */
  z-index: 1; /* Add a z-index if needed */
  left: 45%; /* Center the snackbar */
  bottom: 0px; /* 30px from the bottom */
}

/* Show the snackbar when clicking on a button (class added with JavaScript) */
#snackbar.show {
  visibility: visible; /* Show the snackbar */
  /* Add animation: Take 0.5 seconds to fade in and out the snackbar.
  However, delay the fade out process for 2.5 seconds
  -webkit-animation: fadein 0.5s, fadeout 0.5s 2.5s;
  animation: fadein 0.5s, fadeout 0.5s 2.5s;
  -webkit-animation: fadein 0.5s 2.5s;
  animation: fadein 0.5s 2.5s; */ 
}

/* Animations to fade the snackbar in and out */
@-webkit-keyframes fadein {
  from {bottom: 0; opacity: 0;}
  to {bottom: 0px; opacity: 1;}
}

@keyframes fadein {
  from {bottom: 0; opacity: 0;}
  to {bottom: 0px; opacity: 1;}
}

@-webkit-keyframes fadeout {
  from {bottom: 0px; opacity: 1;}
  to {bottom: 0; opacity: 0;}
}

@keyframes fadeout {
  from {bottom: 0px; opacity: 1;}
  to {bottom: 0; opacity: 0;}
}

</style>
</head>
<body leftmargin="0" topmargin="0" marginwidth="0" marginheight= "0">
    <div id="new_footer">
    	<%-- <ul>
        	<li style="float:left"><img src="<c:url value='/images/egovframework/com/cmm/main/bottom_logo.png' />" alt="안전행전부" /></li>
    		<li style="font-size:11px; float:left; margin: 10px"><spring:message code="comSymMnuMpm.main_bottom.address"/></p>
    		© Ministry of the Interior and Safety. All rights reserved.</li>
    	</ul>
    	 --%>
    	<ul>
        	<li><img src="<c:url value='/images/egovframework/com/cmm/main/kepco.jpeg' />" alt="한국전력공사" width="100", height="57"/></li>
    		<li class="ft_info">
    		(우) 58322 전라남도 나주시 전력로 55 (빛가람동 120) Copyright@2019 KEPCO. All Rights Reserved.
			<br/>
			안내번호 : 061-345-3114 당직상황실(야간 및 주말,공휴일) : 061-345-3203
			</li>
    	</ul>
    </div>
    <div id ="snackbar"></div>
    <script src="<c:url value='/js/egovframework/com/cmm/jquery.js' />"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.js"></script>
    <link href="//cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.css" rel="stylesheet"/>
    <script type="text/javascript">
    	/* TODO 실서버 올릴 시 관리자문의 채팅과 함께 반드시 url 변경할 것.*/
	    var webSocket = new WebSocket("ws://192.168.11.142:8080/newArticleAlarmReceiver");
	    webSocket.onopen = function(message) {console.log("alarmReceiver open")};
	    webSocket.onclose = function(message) {};
	    webSocket.onerror = function(message) {console.log('it is error')};
	    // 서버로 부터 메시지가 오면
	    webSocket.onmessage = function(message) {
	      	var bbsId = message.data;
	      	var bbsNm = "";
	      	console.log('bbsId : '+bbsId);
	      	
	      	if(bbsId == 'BBSMSTR_000000000021'){
	      		bbsNm = "사이버위기경보";
	      	}else if(bbsId == 'BBSMSTR_000000000031'){
	      		bbsNm = "보안권고문";
	      	}else if(bbsId == 'BBSMSTR_000000000032'){
	      		bbsNm = "일반알림문"
	      	}
	      	
	      	if(bbsNm != ""){
	      		/* console.log("새로운 "+bbsNm+" 이(가) 등록되었습니다."); */
	      		toastr.options = {
	      			  "closeButton": true,
	      			  "debug": false,
	      			  "newestOnTop": true,
	      			  "progressBar": false,
	      			  "positionClass": "toast-bottom-center",
	      			  "preventDuplicates": false,
	      			  "onclick": null,
	      			  "showDuration": "300",
	      			  "hideDuration": "1000",
	      			  "timeOut": "0",
	      			  "extendedTimeOut": "0",
	      			  "showEasing": "swing",
	      			  "hideEasing": "linear",
	      			  "showMethod": "fadeIn",
	      			  "hideMethod": "fadeOut"
	      		}
	      		
	      		toastr["info"]("새로운 "+bbsNm+" 이(가) 등록되었습니다.", "서버로부터 알림이 왔습니다.");
	      		
	      		
	      		//showToast(bbsNm);
	      	}
	      	
	    };
    </script>
	<script type="text/javascript">
		/* TODO 실서버 올릴 시 관리자문의 채팅과 함께 반드시 url 변경할 것.*/
		var webSocket = new WebSocket("ws://192.168.11.142:8080/trainMessageReceiver");
		webSocket.onopen = function(message) {console.log("messageReceiver open")};
		webSocket.onclose = function(message) {};
		webSocket.onerror = function(message) {console.log('it is error(from messageReceiver)')};
		// 서버로 부터 메시지가 오면
		webSocket.onmessage = function(message) {
			var messageTeamId = message.data;
			var userTeamId = "<c:out value='${userTeamId}' escapeXml="false"/>";

			console.log(messageTeamId);
			console.log(userTeamId);

			if(messageTeamId == userTeamId){
				toastr.options = {
					"closeButton": true,
					"debug": false,
					"newestOnTop": true,
					"progressBar": false,
					"positionClass": "toast-bottom-center",
					"preventDuplicates": false,
					"onclick": null,
					"showDuration": "300",
					"hideDuration": "1000",
					"timeOut": "0",
					"extendedTimeOut": "0",
					"showEasing": "swing",
					"hideEasing": "linear",
					"showMethod": "fadeIn",
					"hideMethod": "fadeOut"
				}

				toastr["warning"]("메시지 메뉴에서 확인해주세요.", "운영자로부터 메시지가 왔습니다.");

			}

		};
	</script>
</body>
</html>
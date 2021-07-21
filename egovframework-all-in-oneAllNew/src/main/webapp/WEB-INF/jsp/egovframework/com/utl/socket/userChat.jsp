<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<!DOCTYPE html>
<html>
<head>
<title>관리자문의</title>
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/com.css' />">
<style type='text/css'>
	
* {
  box-sizing: border-box;
  padding: 0;
  margin: 0;
}

body {
  /* background-color: #f4f4f4; */
  font-family: 'Roboto', sans-serif;
}

ul { list-style: none; } 

h1 {
  text-transform: uppercase;
  margin: 0 auto;
  padding: 20px;
  text-align: center;
  color: #3c3c3e;
}

/* chatbox */
.chat {
  max-width: 400px;
  min-height: 400px;
  background-color: #fff; 
  padding-right: 15px;
  padding-left: 15px;
  margin: 20px auto;
  border-radius: 1rem;
}

/* messages */
.messages {
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  height: 500px;
}

.message-list {
  overflow-y: scroll;
  max-height: 500px;
}

.message-item {
  padding: 20px;
  border-radius: 0.75rem;
  margin: 20px 0;
}

.message-item:last-child {
  margin-bottom: 0;
}

.item-primary {
  width: 40%;
  margin-right:auto;
  background-color: #f6f7f8;
  color: #3c3c3e;
}

.item-secondary {
  width: 40%;
  margin-left:auto;
  background-color: #0d68b0;
  color: #fff;
}

/* messages input */
.message-input {
  display: flex;
  padding: 20px 0;
}

.message-input input {
  width: 90%;
  padding: 10px;
  border-radius: 2rem;
  border: 1px solid #a5a5a5;
}

.message-input button {
  width: 10%;
  padding: 10px;
  margin-left: 10px;
  border-radius: 5px;
  border: none;
  cursor: pointer;
}
	
</style>

</head>
<body>

<div class="wTableFrm"> 
  <!-- 서버와 메시지를 주고 받는 콘솔 텍스트 영역 -->
  <div class="messages">
      <ul class="message-list" id="message-list">
        
      </ul>
  </div>
  
  
  <!-- <textarea id="messageTextArea" rows="10" cols="50" disabled="disabled"></textarea> -->
  <script type="text/javascript">
    // 서버의 broadsocket의 서블릿으로 웹 소켓을 한다.

    var webSocket = new WebSocket("ws://localhost:8081/egovframework-all-in-one/broadsocket/<c:out value='${result.GROUP_ID}'/>");
    
    // 콘솔 텍스트 영역
    /* var messageTextArea = document.getElementById("messageTextArea"); */
    
    // ul 테스트
    var ulTest = document.getElementById("message-list");
    
    // 접속이 완료되면
    webSocket.onopen = function(message) {
      // 콘솔에 메시지를 남긴다.
      /* messageTextArea.value += "Server connect...\n"; */
      // ul테스트
      var liTest = document.createElement('li')
	  liTest.classList.add('message-item', 'item-primary')
	  liTest.innerHTML = "Server connect"
	  ulTest.appendChild(liTest)
	  ulTest.scrollTop = ulTest.scrollHeight;
      
    };
    
    // 접속이 끝기는 경우는 브라우저를 닫는 경우이기 떄문에 이 이벤트는 의미가 없음.
    webSocket.onclose = function(message) { };
    // 에러가 발생하면
    webSocket.onerror = function(message) {
      // 콘솔에 메시지를 남긴다.
      /* messageTextArea.value += "error...\n"; */
      
      // ul테스트
      var liTest = document.createElement('li')
	  liTest.classList.add('message-item', 'item-primary')
	  liTest.innerHTML = "error"
	  ulTest.appendChild(liTest)
	  ulTest.scrollTop = ulTest.scrollHeight;
      
    };
    // 서버로부터 메시지가 도착하면 콘솔 화면에 메시지를 남긴다.
    webSocket.onmessage = function(message) {
      /* messageTextArea.value += "(operator) => " + message.data + "\n"; */
      
      let now = new Date();
      let h = now.getHours();
      let m = now.getMinutes();
      let sec = now.getSeconds(); 
   	  let nowTime = h+":"+m+":"+sec
      
      // ul테스트
      var liTest = document.createElement('li')
 	  liTest.classList.add('message-item', 'item-primary')
 	  liTest.innerHTML = "(관리자) "+message.data + "<p>" + nowTime + "</p>"
 	  ulTest.appendChild(liTest)
 	  ulTest.scrollTop = ulTest.scrollHeight;
      
    };
    // 서버로 메시지를 발송하는 함수
    // Send 버튼을 누르거나 텍스트 박스에서 엔터를 치면 실행
    function sendMessage() {
      // 텍스트 박스의 객체를 가져옴
      let message = document.getElementById("textMessage");
      
      // 콘솔에 메세지를 남긴다.
      /* messageTextArea.value += "(me) => " + message.value + "\n"; */
      
   	  // ul테스트
   	  
   	  let now = new Date();
      let h = now.getHours();
      let m = now.getMinutes();
      let sec = now.getSeconds(); 
   	  let nowTime = h+":"+m+":"+sec
      
      var liTest = document.createElement('li')
 	  liTest.classList.add('message-item', 'item-secondary')
 	  liTest.innerHTML = message.value + "<p style='color:#fafafa'>" + nowTime + "</p>"
 	  ulTest.appendChild(liTest)
 	  ulTest.scrollTop = ulTest.scrollHeight;
      
      // 소켓으로 보낸다.
      webSocket.send(message.value);
      
      // 텍스트 박스 추기화
      message.value = "";
    }
    // 텍스트 박스에서 엔터를 누르면
    function enter() {
      // keyCode 13은 엔터이다.
      if(event.keyCode === 13) {
        // 서버로 메시지 전송
        sendMessage();
        // form에 의해 자동 submit을 막는다.
        return false;
      }
      return true;
    }
  </script>
  <form>
    <!-- 채팅 영역 -->  
    <br />
    <!-- 텍스트 박스에 채팅의 내용을 작성한다. -->
    <div class="message-input">
	    <input id="textMessage" type="text" onkeydown="return enter()">
	    <!-- 서버로 메시지를 전송하는 버튼 -->
	    <button onclick="sendMessage()" class="btn" value="Send" type="button">전송</button>
    </div>
  </form>
  </div>
</body>
</html>
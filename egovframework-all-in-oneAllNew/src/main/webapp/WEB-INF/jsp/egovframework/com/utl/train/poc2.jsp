<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!--
/* **********************************************************
 * Copyright (C) 2015-2017 VMware, Inc.  All Rights Reserved.
 * SPDX-License-Identifier: MIT
 * **********************************************************/
 *
 * wmks-sdk-example.html
 *      Sample HTML demonstrating how to use the WebMKS SDK on a web page.
-->

<!DOCTYPE html>
<html>
<head>
  <meta content="text/html; charset=utf-8" http-equiv="Content-Type">
  <title>VMware WMKS</title>
  <meta content="IE=edge" http-equiv="X-UA-Compatible">
  <meta content="yes" name="apple-mobile-web-app-capable">
  <meta content="white" name="apple-mobile-web-app-status-bar-style">
  <meta content="VMware, Inc." name="author">
  <meta content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  <link href="<c:url value='/css/egovframework/com/utl/train/wmks-all.css'/>" rel="stylesheet" type="text/css">
  
  <script src="http://code.jquery.com/jquery-1.8.3.min.js" type="text/javascript"></script>
  <script src="http://code.jquery.com/ui/1.8.16/jquery-ui.min.js" type="text/javascript"></script>
  <script src="<c:url value='/css/egovframework/com/utl/train/wmks.min.js'/>" type="text/javascript"></script>
  
  <script>
          // the percentage of the wmks in the whole wndow
          var wmksHeightPercent = 0.8;

          function updateSize() {
            var winW = window.innerWidth, winH = window.innerHeight;
            var floatW = winW - $("#control").width() - 30;
            $("#wmksContainer").width(floatW);
            $("#wmksContainer").height(winH * wmksHeightPercent);
            $("#msgBox").width(floatW);
            $("#msgBox").height(400);
          }
          function initStatus() {
            $("#rescale").attr("checked", true);
            $("#changeResolution").attr("checked", true);
          }
          $(function() {
            updateSize();
            initStatus();
          });
          $(window).on('resize', function(e) {
            updateSize();
            updateScreen();
          });
  </script>
</head>
<body>
  <div id="fullscreen">
    <table>
      <tr>
        <td><input onclick="enterFullScreen()" type="button" value="전체화면"></td>
      </tr>
    </table>
  </div>
  <div style="float:right;">
    <div id="wmksContainer" style="border: solid 2px blue;"></div>
    <div id="msgBox" style="overflow: auto;"></div>
  </div>

    <div id="lifecycle">
      <table>
	    <td><input type="hidden" id="changeResolution" type="checkbox"></td>
        <td><input type="hidden" id="rescale" type="checkbox"></td>
        <tr>
		<!---->
          <td><input type="hidden" id="host" type="text"  value="wss://console-vmware.ncloud.com:443/"></td>
        </tr>
        <tr>
          <td><input type="hidden" id="port" type="text" value="902;"></td>
        </tr>
        <tr>
          <!-- added row for MKS ticket -->
          <!-- <td>
	          <input type="hidden" id="ticket" type="text" 
	          value="cst-oFNHPrSaFZVduzGvqG2bOjwI6z4dnU4b6scGAV4FqRmUQqVeHhjlw0Dd084qYr3Rw5eYuRXDWHwhiP8iBUb4Ek0RRMwGP2IfGYormTtFiMohg/q9NPJrSKdcsPsl8a1y0s39N3DUN4NToATBOak4kKtW8+uKHeg619Dlf6GhO2JvlyU9iyDiltwtB3gaIZ1Dbpq5gjjSQE8z+mq7prIrU4greeboylZKCj6Z5glpUNKPKqHacpsNS5l+DfryowuqGPUfpLygVz6gO731cCLuWWzyFXivVS0viLcVuE9RG6w=-h5471ytyDEiPkZnKtU2LB+563gdOcEd742LygA==--tp-06:08:1A:AC:D7:54:8A:22:D6:51:42:25:91:D2:E1:EB:3B:F5:68:EA--">
          </td> -->
        <%--   
          <td>
	          <input type="hidden" id="ticket" type="text" 
	          value="<c:out value='${ticket}'/>">
          </td>
          <td>
	          <input type="hidden" id="url" type="text" 
	          value="<c:out value='${url}'/>">
          </td> --%>
          
          
          <td>
	          <input type="hidden" id="ticket" type="text" 
	          value="<c:out value='cst-gCFpH5hIsW9jROe+R50GGP9x6PljEJ4LX79hqxRnATjIXCA5XNn/WMfO6/o03V/c9I+iX24RCTk9G7YnvSG+JoDX6D2FT9AEr+ORyP+bPTtEe3+VYTLXwQv8v1psET9kRRjZrtr1KrpHnKBQ4mxNYLKe/dytu5yn0LkcWU7oziyhgrIPVQNz8lKZJ8MN02fXOg9dfLS+HqZTnK+ZCBJ6u0Gjuj+7OJkDIkJs2XyAinmHtyAHE0Uh+YxB+jXmZh+0k/7icYmxthAOqdW5t74cNg==-JLPCxUcTNBVYpUB3SKeHFsnzOY+drlablODI5Q==--tp-06:08:1A:AC:D7:54:8A:22:D6:51:42:25:91:D2:E1:EB:3B:F5:68:EA--'/>">
          </td>
          
          <td>
	          <input type="hidden" id="url" type="text" 
	          value="<c:out value="/vmfs/volumes/f1081d9c-1034bb33/WindowsIR-0l8x/WindowsIR-0l8x.vmx"/>">
          </td> 
        </tr>
        <tr>
          <td><input type="hidden" onclick="connect()" type="button" value="connect"></td>
          <td><input type="hidden" onclick="disconnect()" type="button" value="disconnect">
              <input type="hidden" onclick="destroy()" type="button" value="destroy"></td>
        </tr>
      </table>
    </div>

  <hr>
  <div id="display">
    <table>
      <tr>
        <td><input type="hidden" onclick="getRemoteScreenSize()" type="button" value="getRemoteScreenSize"></td>
      </tr>
      <tr>
        <td><input type="hidden" id="width" style="width:40px;" type="text" value="600"></td>
        <td><input type="hidden" id="height" style="width:40px;" type="text" value="400"></td>
        <td><input type="hidden" onclick="setRemoteScreenSize()" type="button" value="setRemoteScreenSize"></td>
      </tr>
      <tr>
        <td></td>
        <td></td>
        <td><input type="hidden" onclick="updateScreen()" type="button" value="updateScreen"></td>
      </tr>
    </table>
  </div>





<script>
/* **********************************************************
 * Copyright (C) 2015-2017 VMware, Inc.  All Rights Reserved.
 * SPDX-License-Identifier: MIT
 * **********************************************************/

/*
 * wmks-sdk-example.js --
 *
 *      This file implements a set of JavaScript functions which,
 *      in conjunction with the vmrc-embed-example.html sample page,
 *      demonstrate how to use the WebMKS SDK API to embed a VMware
 *      Remote Console in a web page.
 */

/*
 * Globals
 */

var wmks = null;

/*
 *----------------------------------------------------------------------------
 *
 * value() --
 *
 *      Helper functions to simply look up the value of document elements
 *
 * Results:
 *      Document element, value
 *
 *----------------------------------------------------------------------------
 */
function value(id) {
  return document.getElementById(id).value;
}

/*
 *----------------------------------------------------------------------------
 *
 * log(msg) --
 *
 *      Print message to logging box.
 *
 * Results:
 *      None.
 *
 * Side effects:
 *      Appends message text to log box.
 *
 *----------------------------------------------------------------------------
 */
function log(text) {
}

/*
 *----------------------------------------------------------------------------
 *
 * This is the jQuery shorthand for document ready function,
 * called after all the DOM elements of the page are ready for use:
 *
 * $(document).ready(function() { ... });
 *
 *----------------------------------------------------------------------------
 */
$(function() {
  $('#selectLanguage').change(function(){
    if (!wmks) return;
    var keyboardLayoutId = $(this).find(":selected").val();

    wmks.setOption('keyboardLayoutId', keyboardLayoutId);
    log('keyboardLayoutId : ' + keyboardLayoutId);
  })
});

/*
 *----------------------------------------------------------------------------
 *
 * onConnectionStateChangeHandler --
 * onScreenSizeChangeHandler --
 * onFullscreenChangeHandler --
 * onErrorHandler --
 *
 *      Event handlers for the corresponding WMKS SDK generated events
 *
 * Results:
 *      None.
 *
 * Side effects:
 *      Display log message boxes with specific event details.
 *
 *----------------------------------------------------------------------------
 */
function onConnectionStateChangeHandler(event, data) {
  log('onConnectionStateChange - connectionState: ' + data.state);
  if (data.state == WMKS.CONST.ConnectionState.DISCONNECTED) {
    log("reason is " + data.reason + ", code is" + data.code);
  }
}

function onScreenSizeChangeHandler(event, data) {
  log('onScreenSizeChange - width: ' + data.width + ', height: ' + data.height);
}

function onFullscreenChangeHandler(event, data) {
  log('onFullscreenChange - fullscreen: ' + data.isFullScreen);
}

function onErrorHandler(event, data) {
  log('onErrorHandler - error type ' + data.errorType);
}

function onLedChangeHandler(event, data) {
  log('onLEDChange , key is ' + data);
}

function onHeartbeatHandler(event, data) {
  log('on Heartbeat Event, interval is ' + data);
}

/*
 *----------------------------------------------------------------------------
 *
 * registerEvents() --
 *
 *      register events callback for the WMKS core object wmks
 *
 * Results:
 *      None
 *
 *----------------------------------------------------------------------------
 */
function registerEvents() {
  if (!wmks) return;
  var constEvent = WMKS.CONST.Events;
  wmks.register(constEvent.CONNECTION_STATE_CHANGE, onConnectionStateChangeHandler)
    .register(constEvent.REMOTE_SCREEN_SIZE_CHANGE, onScreenSizeChangeHandler)
    .register(constEvent.FULL_SCREEN_CHANGE, onFullscreenChangeHandler)
    .register(constEvent.ERROR, onErrorHandler)
    .register(constEvent.KEYBOARD_LEDS_CHANGE, onLedChangeHandler)
    .register(constEvent.HEARTBEAT, onHeartbeatHandler);
}

/*
 *----------------------------------------------------------------------------
 *
 * createWMKS() --
 *
 *      Invoked by the sample page in response to clicking the 'createWMKS' button.
 *      Calls the WMKS.createWMKS() method with the user specific options value.
 *      After createWMKS, call registerEvents to register events callback for wmks.
 *
 * Results:
 *      None.
 *
 *----------------------------------------------------------------------------
 */

function createWMKS() {

  var rescale = $("#rescale")[0].checked;
  var changeResolution = $("#changeResolution")[0].checked;
  var position = WMKS.CONST.Position.CENTER;

  var options = {
    rescale: rescale,
    changeResolution: changeResolution,
    position: position
  };

  //wmks = WMKS.createWMKS("wmksContainer", options);
  var url = document.getElementById("url").value;
  console.log("url : ", url);
  console.log("VCDProxyHandshakeVmxPath : ", VCDProxyHandshakeVmxPath);
  wmks = WMKS.createWMKS("wmksContainer",{VCDProxyHandshakeVmxPath : url, enableUint8Utf8:true});
  // here for demo to make the console float on the right, set the canvas position as relative
  $("#mainCanvas").css("position", "relative");
  registerEvents();
  log("createWMKS successfully!");
  

}


/*
 *----------------------------------------------------------------------------
 *
 * destroy() --
 *
 *      Invoked by the sample page when clicking the destroy button.
 *      Destroy the wmks core object.
 *
 * Results:
 *      None.
 *
 * Side effects:
 *      Invokes destroy() method, disconnect the WMKS connection (if active)
 *      and remove the widget from the associated element.
 *
 *----------------------------------------------------------------------------
 */
function destroy() {
  try {
    wmks.destroy();
  } catch (err) {
    log('destroy call failed: ' + err.description);
    return;
  }
  log('destroy call returned successfully');
}

/*
 *----------------------------------------------------------------------------
 *
 * getVersion() --
 *
 * Invoked by the sample page when clicking the 'getVersion' button.
 *
 * Results:
 *      the version of current WMKS SDK.
 *
 *
 *----------------------------------------------------------------------------
 */
function getVersion() {
  if (!wmks) return;
  log('getVersion returned "' + wmks.getVersion() + '"');
}

/*
 *----------------------------------------------------------------------------
 *
 * connect() --
 *
 *      Invoked by the sample page when clicking the 'connect' button.
 *
 * Results:
 *      None.
 *
 * Side effects:
 *      Invokes WMKS SDK connect() method, passing in the specified parameters
 *      from the sample page. Displays return value in log box.
 *
 *----------------------------------------------------------------------------
 */
function connect() {
  if (!wmks) {
    log("Please createWMKS first!");
    return;
  }
  var host = $("#host")[0].value;
  var port = $("#port")[0].value;
  var ticket = $("#ticket")[0].value;
  var url = host+port+ticket

  try {
    wmks.connect(url);
    log('connect succeeded');
  } catch (err) {
    log('connect failed: ' + err);
  }
}

/*
 *----------------------------------------------------------------------------
 *
 * disconnect() --
 *
 *      Invoked by sample page when clicking the 'disconnect' button.
 *
 * Results:
 *      None.
 *
 * Side effects:
 *      Invokes WMKS SDK disconnect() method, terminates any related
 *      connection-specific child processes.
 *
 *----------------------------------------------------------------------------
 */
function disconnect() {
  try {
    wmks.disconnect();
    log('disconnect succeeded');
  } catch (err) {
    log('disconnect failed: ' + err);
  }
}

/*
 *----------------------------------------------------------------------------
 *
 * getConnectionState() --
 * setRemoteScreenSize() --
 * getRemoteScreenSize() --
 * updateScreen() --
 * canFullScreen() --
 * isFullScreen() --
 * enterFullScreen() --
 * exitFullScreen() --
 * sendInputString() --
 * sendCAD() --
 * enableKeyboard() --
 * disableKeyboard() --
 * enableExtendedKeyboard() --
 * disableExtendedKeyboard() --
 * enableTrackpad() --
 * disableTrackpad() --
 * showKeyboard() --
 * hideKeyboard() --
 * toggleExtendedKeypad() --
 * toggleTrackpad() --
 *
 *      Common WMKS SDK API methods
 *
 *
 *----------------------------------------------------------------------------
 */

function getConnectionState() {
  if (!wmks) return;
  log("getConnectionState returned : " + wmks.getConnectionState());
}

function setRemoteScreenSize() {
  if (!wmks) return;
  var width = value("width");
  var height = value("height");
  wmks.setRemoteScreenSize(width, height);
}

function getRemoteScreenSize() {
  if (!wmks) return;
  var size = wmks.getRemoteScreenSize();
  $("#lableW")[0].innerHTML = "Width: " + size.width;
  $("#lableH")[0].innerHTML = "Height: " + size.height;
}

function updateScreen() {
  if (!wmks) return;
  wmks.updateScreen();
  log("updateScreen!!");
}

function canFullScreen() {
  if (!wmks) return;
  log("canFullScreen : " + wmks.canFullScreen());
}

function isFullScreen() {
  if (!wmks) return;
  log("isFullScreen : " + wmks.isFullScreen());
}

function enterFullScreen() {
  if (!wmks) return;
  wmks.enterFullScreen();
}

function exitFullScreen() {
  if (!wmks) return;
  wmks.exitFullScreen();
}

function sendInputString() {
  if (!wmks) return;
  var text = value("sendStringText");
  wmks.sendInputString(text);
}

function sendKeyCodes() {
  if (!wmks) return;
  wmks.sendKeyCodes([13]);
  log("send Enter");
}

function sendCAD() {
  if (!wmks) return;
  wmks.sendCAD();
  log("send Ctrl + Alt + Delete");
}

function enableKeyboard() {
  if (!wmks) return;
  wmks.enableInputDevice(WMKS.CONST.InputDeviceType.KEYBOARD);
  log("enableKeyboard !");
}

function disableKeyboard() {
  if (!wmks) return;
  wmks.disableInputDevice(WMKS.CONST.InputDeviceType.KEYBOARD);
  log("disableKeyboard !");
}

function showKeyboard() {
  if (!wmks) return;
  wmks.showKeyboard();
  log("showKeyboard !");
}

function hideKeyboard() {
  if (!wmks) return;
  wmks.hideKeyboard();
  log("hideKeyboard !");
}

function toggleExtendedKeypad() {
  if (!wmks) return;
  wmks.toggleExtendedKeypad();
  log("toggleExtendedKeypad !");
}

function toggleTrackpad() {
  if (!wmks) return;
  wmks.toggleTrackpad();
  log("toggleTrackpad!!");
}

function toggleRelativePad() {
  if (!wmks) return;
  wmks.toggleRelativePad();
  log("toggleRelativePad!!");
}

createWMKS();
connect();
</script>
</body>
</html>

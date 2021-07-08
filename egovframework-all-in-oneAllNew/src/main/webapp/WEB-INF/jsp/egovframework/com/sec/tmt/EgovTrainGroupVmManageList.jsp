<%
 /**
  * @Class Name : EgovTrainGroupVmManageList.jsp
  * @Description : 훈련 - 회원그룹 - VM그룹 매핑 페이지 JSP
  * @Modification Information
  * @
  * @  수정일         수정자                   수정내용
  * @ -------    --------    ---------------------------
  * @ 
  *
  *  @author 함승우
  *  @since 
  *  @version 1.0
  *  @see
  *  
  */
%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/com.css' />">
<script type="text/javaScript" language="javascript" defer="defer">

//기존 있던 행의 값이 변경 될 시, 상태값 U로 변경
function updatedRowSelect(param){
	var rowidx = param.parentElement.parentElement.rowIndex;
	var boardRows = document.getElementById('board').rows;
	var selectedRow = boardRows[rowidx]
	
	var cellsInSelectedRow = selectedRow.getElementsByTagName("td");
	cellsInSelectedRow[1].firstElementChild.checked = 'checked'; // 1:체크박스 위치. 체크 해제
	if(cellsInSelectedRow[2].innerText == 'I'){
		
	}else{
		cellsInSelectedRow[2].innerText = 'U'; // 2:상태값 위치. 상태값 U로 변경
	}
}

// 체크박스 uncheck 될 시, 상태값 없앰
function uncheckedTest(param){
	if(!param.checked){
		
		var rowidx = param.parentElement.parentElement.rowIndex;
		var boardRows = document.getElementById('board').rows;
		var selectedRow = boardRows[rowidx]
		var cellsInSelectedRow = selectedRow.getElementsByTagName("td");
		/*TODO 상태값 I 인 경우 행을 그냥 날려버림*/
		if(cellsInSelectedRow[2].innerText == 'I'){
			document.getElementById('board').deleteRow(rowidx);
		}
		cellsInSelectedRow[2].innerText = ''; 
	}
}

// 행삭제
function deleteRow(){
	var board = document.getElementById('board');
	var chkboxArr = board.getElementsByClassName('chk');
	for(var i = 0 ; i < chkboxArr.length ; i++){
		if(chkboxArr[i].checked){
			var rowidx = chkboxArr[i].parentElement.parentElement.rowIndex;
			var boardRows = document.getElementById('board').rows;
			var cellInSelectedRow = boardRows[rowidx].getElementsByTagName("td");
			if(cellInSelectedRow[2].innerText == 'I'){
				
			}else{
				cellInSelectedRow[2].innerText = 'D';
			}
		}
	}
}

//행추가(이게 첫행임)
function insertRow(){
	/* var board = document.getElementById('board');
	var rowsLength = board.rows.length;
	
	if(rowsLength > 1){
		insertRowCopy();
		return;
	}else{ */
	var tbody = board.getElementsByTagName('tbody')[0];
	var rowToInsert = tbody.insertRow(-1);
	cell0 = rowToInsert.insertCell();
	cell0.innerHTML = "1"
		
	cell1 = rowToInsert.insertCell();
	cell1.innerHTML = "<input type='checkbox' class='chk' name='checkField' onchange='javascript:uncheckedTest(this)' checked='checked' />"
		
	cell2 = rowToInsert.insertCell();
	cell2.innerText = "I"
	
	cellExtra = rowToInsert.insertCell();
	cellExtra.innerHTML = "<label></label>"
	
	cell3 = rowToInsert.insertCell();
	cell3.innerHTML = "<input type='hidden' name='mappingId' value=''/>"
		
		
	/* 훈련명 , 훈련ID 설정  */
	var map = new Map();
	<c:forEach var = 'item' items ='${trainNameList}'> 
		map.set('${item.EVENT_ID}','${item.EVENT_CN}');
	</c:forEach>
		
	var keySets = map.keys();
	var list = new Array(); // 키값들을 담아둘 list
		
	cell4 = rowToInsert.insertCell();
	var temp = "<select name='trainId' onchange='javascript:updatedRowSelect(this)'>";
		
	for(var item of keySets)
	{
		list.push(item);
		temp += ("<option value='"+item+"'>"+map.get(item)+"</option>");
	}
	temp += "</select>";
	cell4.innerHTML = temp;
	/* 훈련명 , 훈련ID 설정  끝*/
		
	/* 사용자그룹명, 사용자그룹ID 설정  */
	var map = new Map();
	<c:forEach var = 'item' items ='${teamNameList}'> 
		map.set('${item.GROUP_ID}','${item.GROUP_NM}');
	</c:forEach>
	
	var keySets = map.keys();
	var list = new Array(); // 키값들을 담아둘 list
		
	cell6 = rowToInsert.insertCell();
	var temp = "<select name='userGroupId' onchange='javascript:updatedRowSelect(this)'>";
		
	for(var item of keySets)
	{
		list.push(item);
		temp += ("<option value='"+item+"'>"+map.get(item)+"</option>");
	}
	temp += "</select>";
	cell6.innerHTML = temp;
	/* 사용자그룹명, 사용자그룹ID 설정  끝*/
		
	/* VM그룹명, VM그룹ID 설정  */
	var map = new Map();
	<c:forEach var = 'item' items ='${vmGroupNameList}'> 
		map.set('${item.ID}','${item.NAME}');
	</c:forEach>
		
	var keySets = map.keys();
	var list = new Array(); // 키값들을 담아둘 list
		
	cell8 = rowToInsert.insertCell();
	//var list = new Array();
	var temp = "<select name='vmGroupId' onchange='javascript:updatedRowSelect(this)'>";
		
	for(var item of keySets)
	{
		list.push(item);
		temp += ("<option value='"+item+"'>"+map.get(item)+"</option>");
	}
	temp += "</select>";
	cell8.innerHTML = temp;
	/* VM그룹명, VM그룹ID 설정 끝 */
}


//행추가(위의 행 복사)
function insertRowCopy(){
	var board = document.getElementById('board');
	var rowsLength = board.rows.length;
	var rowToClone = board.rows[rowsLength - 1];
	var cellsToClone = rowToClone.cells;
	
	var rowToInsert = board.insertRow(-1);
	for(var i = 0 ; i < cellsToClone.length; i++){
		cellToClone = cellsToClone[i];
		cellToInsert = rowToInsert.insertCell();
		cellToInsert.innerHTML = cellToClone.innerHTML;
	}
	
	afterInsertRow()
}

//행추가 이후 체크박스 체크 및 상태값 I로 변경
function afterInsertRow(){
	var board = document.getElementById('board');
	var rowsLength = board.rows.length;
	var rowidx = rowsLength - 1;
	
	var boardRows = document.getElementById('board').rows;
	var selectedRow = boardRows[rowidx]
	
	var cellsInSelectedRow = selectedRow.getElementsByTagName("td");
	cellsInSelectedRow[1].firstElementChild.checked = 'checked'; // 1:체크박스 위치. 체크 해제
	cellsInSelectedRow[2].innerText = 'I'; // 2:상태값 위치. 상태값 U로 변경
}

/* 페이지네이션용 함수  */
function linkPage(pageNo){
    document.listForm.pageIndex.value = pageNo;
    document.listForm.action = "<c:url value='/sec/tmt/EgovTrainGroupVmManage.do'/>";
    document.listForm.submit();
}
 
function beforeSave(){
	
	var updateRow = "";
	var deleteRow = "";
	var insertRow = "";
	
	// form 내 체크박스 체크된 행들만 들고온다. 
	var board = document.getElementById('board');
	var chkboxArr = board.getElementsByClassName('chk');
	for(var i = 0 ; i < chkboxArr.length ; i++){
		if(chkboxArr[i].checked){
			
			var rowidx = chkboxArr[i].parentElement.parentElement.rowIndex;
			var boardRows = document.getElementById('board').rows;
			var cellInSelectedRow = boardRows[rowidx].getElementsByTagName("td");
			
			var temp = "";
			if(cellInSelectedRow[2].innerText == "U"){
				for(var j = 4 ; j < cellInSelectedRow.length ; j++){
					if(j == (cellInSelectedRow.length - 1)){
						updateRow += (cellInSelectedRow[j].firstElementChild.value+"/");
					}else{
						updateRow += (cellInSelectedRow[j].firstElementChild.value+";");
					}
				}
			}else if(cellInSelectedRow[2].innerText == "I"){
				for(var j = 4 ; j < cellInSelectedRow.length ; j++){
					if(j == (cellInSelectedRow.length - 1)){
						insertRow += (cellInSelectedRow[j].firstElementChild.value+"/");
					}else{
						insertRow += (cellInSelectedRow[j].firstElementChild.value+";");
					}
				}
			}else if(cellInSelectedRow[2].innerText == "D"){
				for(var j = 4 ; j < cellInSelectedRow.length ; j++){
					if(j == (cellInSelectedRow.length - 1)){
						deleteRow += (cellInSelectedRow[j].firstElementChild.value+"/");
					}else{
						deleteRow += (cellInSelectedRow[j].firstElementChild.value+";");
					}
				}
			}
		}
	}
	save(insertRow, updateRow, deleteRow);
}



function save(insertRow , updateRow, deleteRow){
	document.listForm.insertRow.value = insertRow;
	document.listForm.updateRow.value = updateRow;
	document.listForm.deleteRow.value = deleteRow;
	
	document.listForm.action = "<c:url value = '/sec/tmt/EgovTrainGroupVmManageSave.do' />"
	document.listForm.submit();
}
</script>
</head>
<body>
<form:form name="listForm" action="${pageContext.request.contextPath}/sec/tmt/EgovTrainGroupVmManage.do" method="post">
	<div class="board">
		<h1>훈련매핑 목록</h1>
		<div class="search_box" title="훈련매핑 목록">
			<ul>
				<li>
					<input type="button" class="s_btn" value="조회" />
				</li>
				<li>
					<input type="button" class="s_btn" value="저장" onclick="javascript:beforeSave()"/>
				</li>
			</ul>
		</div>
		<span class="btn_s" style="text-align:center;"" onclick="javascript:insertRow();">행추가</span>
		<span class="btn_s" style="text-align:center;" onclick="javascript:deleteRow();">행삭제</span>
		<table id="board" class="board_list">
			<colgroup>
				<col style="width: 5%;">
				<col style="width: 3%;">
				<col style="width: 5%;">
				<col style="width: 20%;">
				<col style="width: 1%;">
				<col style="width: 15%;">
				<col style="width: 20%;">
				<col style="width: 20%;">
				<%-- <col style="width: 13%;">
				<col style="width: 10%;">
				<col style="width: ;"> --%>
			</colgroup>
			<thead>
				<tr>
					<th>번호</th>
					<th></th> <!-- 체크박스  -->
					<th></th> <!-- 상태값  -->
					<th>ID</th>
					<th></th> <!-- 훈련매핑 아이디 -->
					<th>훈련명</th> <!-- selectbox  -->
					<th>사용자그룹명</th>
					<th>VM그룹명</th>
					<!-- <th>임시1</th>
					<th>임시2</th>
					<th>임시3</th> -->
				</tr>
			</thead>
			<tbody class="ov" name="tbody">
				<c:forEach var="item" items="${mappingList }">
					<tr>
						<td><!-- 번호  -->1</td>
						<td><!-- 체크박스  --><input type='checkbox' class='chk' name='checkField' onchange='javascript:uncheckedTest(this)'/></td>
						<td><!-- 상태값  --></td>
						<td><!-- 매핑ID 라벨  --><label>${item.ID}</label></td>
						<td><!-- 매핑ID  --><input type='hidden' name='mappingId' value='${item.ID}'/></td>
						<td><!-- 훈련명 -->
							<select name='trainId' onchange='javascript:updatedRowSelect(this)'>
								<c:forEach var="train" items="${trainNameList}">
									<option value="${train.EVENT_ID}" <c:if test="${item.TRAINING_ID eq train.EVENT_ID}"> selected="selected" </c:if> >${train.EVENT_CN}</option>
								</c:forEach>
							</select>
						</td>
						<td><!-- 사용자그룹명  -->
							<select name='userGroupId' onchange='javascript:updatedRowSelect(this)'>
								<c:forEach var="team" items="${teamNameList}">
									<option value="${team.GROUP_ID}" <c:if test="${item.TEAM_ID eq team.GROUP_ID}"> selected="selected" </c:if> >${team.GROUP_NM}</option>
								</c:forEach>
							</select>
						</td>
						<td>
							<select name='vmGroupId' onchange='javascript:updatedRowSelect(this)'>
								<c:forEach var="vmGroup" items="${vmGroupNameList}">
									<option value="${vmGroup.ID}" <c:if test="${item.VM_ID eq vmGroup.ID}"> selected="selected" </c:if> >${vmGroup.NAME}</option>
								</c:forEach>
							</select>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<%-- <c:if test="${!empty trainGroupVmVO.pageIndex }">
			
		</c:if> --%>
		<!-- paging navigation -->
		<div class="pagination">
			<ul><ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="linkPage"/></ul>
		</div>
	</div>
	
	<input type="hidden" name="pageIndex" value="<c:out value='${groupManageVO.pageIndex}'/>"/>
	<input type="hidden" name="searchCondition"/>
	<input type="hidden" name="insertRow"/>
	<input type="hidden" name="updateRow"/>
	<input type="hidden" name="deleteRow"/>
	</form:form>
</body>
</html>
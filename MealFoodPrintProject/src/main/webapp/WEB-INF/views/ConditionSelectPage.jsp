<%@page import="java.util.List"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@include file ="header.jsp" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

<style type="text/css">
h3 {
	text-align: center;
}

table {
	margin-left: auto;
	margin-right: auto;
}

body { 
	text-align: center;
} 
</style>

<script type="text/javascript">
	//체크함수:체크박스 선택 시 enddate는 readonly로 변경 
	function AutoCheck(box) {
		const startDate = document.getElementById("startDate").value;
		const endDate = document.getElementById("endDate").value;
		var result = document.getElementById("chkBox");

		if (box.checked == true) {
			alert('기간자동설정');
			document.getElementById("endDate").readOnly = true;
		} else if (box.checked == false) {
			alert('기간자동설정해제');
			document.getElementById("endDate").readOnly = false;
		}
	}

	//시작일 알림 함수:체크박스가 선택되어 있다면, 시작일 설정 시 자동으로 종료일이 선택된다.
	function StartDate() {

		if (document.getElementById("endDate").readOnly == true) {
			OutoDate();

		} else if (document.getElementById("endDate").readOnly == false) {
			EndDate();
			return;
		}

	}

	function Reset() {
		document.getElementById("startDate").value = "";
		document.getElementById("endDate").value = "";
	}

	function EndDate() {
		const startDate = document.getElementById("startDate").value;
		const endDate = document.getElementById("endDate").value;
		var ymddate = new Date(startDate);
		var ymd1date = new Date(endDate);

		if ((ymd1date.getDate()) - (ymddate.getDate()) >= 6) {
			alert("유효성검사");
			Reset();

		}

		else if (ymddate.getDate() > ymd1date.getDate()) {
			alert("유효성검사");
			Reset();

			return;
		}
	}

	function OutoDate() {

		var startDate = document.getElementById("startDate").value;
		var ymdgetdate = document.getElementById("startDate").value;
		var endDate = document.getElementById("endDate").value;
		var ymddate = new Date(startDate);
		var ymd1date = new Date(startDate);
		var ymdgetdate = new Date(startDate);
		const getday = ymddate.getDay();
		var getdaycal = 7 - getday;

		ymddate.setDate((ymddate.getDate() - getday) + 1);
		ymd1date.setDate(ymdgetdate.getDate() + getdaycal);

		document.getElementById("startDate").value = ymddate.toISOString()
				.substring(0, 10);
		document.getElementById("endDate").value = ymd1date.toISOString()
				.substring(0, 10);
	}
</script> 

<body>
	<h3>Inquiry Page</h3>
	<form action="PrintWeeklyMenu.do" method="get" >
<br></br>  
		<table>
			<tr>
				<th>식당</th>
				<th>식사구분</th>
				<th>조회년월</th>
			</tr> 
			<tr>
				<td>
					<div>
						<select name="restCode">
							<option value="R01">여민관</option>
							<option value="R02">춘추관</option>
						</select>
					</div>
				</td>
				<td>
					<div>
						<select name="mealTypeCode">
							<option value="M01">조식</option>
							<option value="M02">중식</option>
							<option value="M03">석식</option>
							<option value="M04">간식</option>
							<option value="M00">전체선택</option>
						</select>
					</div>
				</td>
				<td>
					<div>
						<input name="startDate" id="startDate" type="date" value=""
							onchange="StartDate()">
						<text> ~ </text>
						<input name="endDate" id="endDate" type="date" value=""
							onchange="EndDate()">

					</div>
				</td>
				<td>
					<div>
						<input type="checkbox" name="chkBox" id="chkBox" value="checked"
							onchange="AutoCheck(this)"> 주간자동선택
					</div>
				</td>
				<td>
					<div>
						<button type="submit">조회</button>
					</div>
				</td>
			</tr>
		</table>
	</form>
</html>
   
<%@page import="menu.com.project.service.WeeklyMenu"%>
<%@page import="menu.com.project.vo.CookFoodVo"%>
<%@page import="menu.com.project.service.MealSectionMenu"%>
<%@page import="menu.com.project.service.DailyFoodIngredients"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" 
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@include file="header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<%
WeeklyMenu sortedWeeklyMenu = (WeeklyMenu) request.getAttribute("sortedWeeklyMenu");
Date nowTime = new Date();
SimpleDateFormat sf = new SimpleDateFormat("yyyy년 MM월 dd 현재");
%>
<meta charset="UTF-8">


</head>

<script>
	var initBodyHtml;

	function printPage() {
		window.print();
	}

	function beforePrint() {
		initBodyHtml = document.body.innerHTML;
		document.body.innerHTML = document.getElementById('printbody').innerHTML;
	}
	function afterPrint() {
		document.body.innerHTML = initBodyHtml;
	}

	window.onbeforeprint = beforePrint;
	window.onafterprint = afterPrint;
</script>

<style type="text/css">
li {
	list-style: none;
	padding-left: 0px;
}

table, li, ul {
	table-layout: auto;
}

table {
	width: 80%;
	position: relative
}

th {
	vertical-align: top;
}

#mealId {
	text-align: center;
	vertical-align: center;
}

body {
	text-align: center;
	vertical-align: center;
	-webkit-print-color-adjust: exact;
}
</style>


<%
if (sortedWeeklyMenu.getRestaurantName().equals("R01")) {
	sortedWeeklyMenu.setRestaurantName("여민관");
} else {
	sortedWeeklyMenu.setRestaurantName("춘추관");
}
%>


<body id="printbody">      

	<h3>Result of inquiry Page</h3>
	<table>


		<h5><%=sf.format(nowTime)%></h5>
		</br>
		<br>

	</table>


	<table border="1" style="margin-left: auto; margin-right: auto;">


		<th>식당명 : <%=sortedWeeklyMenu.getRestaurantName()%> / 급식기간 : <%=sortedWeeklyMenu.getStartDate()%>
			~ <%=sortedWeeklyMenu.getEndDate()%> / 총 기간 : <%=sortedWeeklyMenu.getSelectedDailyFoodIngredientsCount()%>
			일
		</th>

	</table>

	<table border="1" border="1"
		style="margin-left: auto; margin-right: auto;">


		<tr>
			<td>구분</td>

			<c:forEach items="${sortedWeeklyMenu.calSelectedDaysForWeek}"
				var="list">
				<td>${list}</td>
			</c:forEach>
		</tr>




		<c:forEach items="${sortedWeeklyMenu.mealSections}" var="table">

			<tr>

				<td id="mealId" style="vertical-align: center">

					${table.mealTypeName} <c:forEach
						items="${sortedWeeklyMenu.calSelectedDaysForSort}" var="days">

						<td style="vertical-align: top"><c:forEach
								items="${table.dailyMenus}" var="dayList">

								<c:if test="${days eq dayList.date}">

									<c:set var="oldFoodName" value="null" />


									<c:forEach items="${dayList.foodIngredientsList}" var="day">
										<c:if test="${oldFoodName ne day.foodName}">
											<c:set var="oldFoodName" value="${day.foodName}" />
											<li style="background-color: bisque"
												style="text-align: center"><c:out
													value="${day.foodName}" /></li>
										</c:if>
										<%--재료명 --%>
										<li style="text-align: left"><c:if
												test="${day.foodIngredientsDataType eq 'ingredients'}">
												<c:out value="${day.ingredientsName}" />
											</c:if></li>


									</c:forEach>

								</c:if>

							</c:forEach></td>

					</c:forEach>
			</tr>
		</c:forEach>


	</table>
  

	<br></br>




 
</body>  

  
<button onclick="return window.print();">프린터출력</button>   
<br></br>
</html>


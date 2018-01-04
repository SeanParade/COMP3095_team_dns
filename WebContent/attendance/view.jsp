<%@page import="java.util.TreeMap"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="../includes/authentication.jsp"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<% boolean noBox = false; %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<link rel="stylesheet" href="/COMP3095_TEAM_DNS/css/main.css" />
<title>View Attendance</title>
</head>
<body>
	<jsp:include page="../includes/navigation.jsp" />
	
	<%-- dropdown with departments  --%> 
	<div class="container">
		<form action="/COMP3095_TEAM_DNS/attendance/ViewAttendance"
			method="post">
			Department: <select name="department">
				<c:forEach items="${departments}" var="department">
					<option value="${department.departmentId}"><c:out
							value="${department.departmentName}" />
				</c:forEach>
			</select> <br> <input type="submit" value="Search">

			<!-- //displays selected Department for user clarity -->
			<h1>
				<c:out value="${selectedDep}" />
			</h1>
			<table style="margin-right: auto; margin-left: 0px" border="1">
				<tr>
					<th>Last Name</th>
					<th>First Name</th>
					<th>Employee Number</th>
					<c:forEach var="date" items="${dates}">
						<th><c:out value="${date}" /></th>
					</c:forEach>
				</tr>
				
				<!--loops through each employee in the list, then for each date in the Unique date list, checks the date and employee ID against the TreeMap -->
				<!-- displays a checked box if the employee was present, an unchecked box if they arent  -->
				
				<c:forEach var="employee" items="${employees}">
					<tr>
						<td><c:out value="${employee.lastName}" /></td>
						<td><c:out value="${employee.firstName}" /></td>
						<td><c:out value="${employee.employeeId}" /></td>
						<c:forEach var="date" items="${dates}">
						<c:set var="noBox" value="true" />
							<td>
								<c:forEach var="attendance" items="${map}">
								<c:if test="${attendance.key.date == date && attendance.value.employeeId == employee.employeeId}">
								   <input type="checkbox" checked ><c:set var="noBox" value="false" />
								</c:if>
							</c:forEach>
							    <c:if test="${noBox}">
							        <input type="checkbox"/>
							    </c:if>
							</td>
						</c:forEach>
					</tr>
				</c:forEach>


			</table>
		</form>
	</div>
</body>
</html>
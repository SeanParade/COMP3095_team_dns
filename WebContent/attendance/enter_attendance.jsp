<%@ page  language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>	
<%@ include file="../includes/authentication.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"/>
<link rel="stylesheet" href="/COMP3095_TEAM_DNS/css/main.css" />
<title>Enter Attendance</title>
</head>
<body>
<jsp:include page="../includes/navigation.jsp" />
	<div class="container">
	<form action="/COMP3095_TEAM_DNS/attendance/Attendance" method="post">
		Department: 
		<select name="department">
			<c:forEach items="${departments}" var ="department">
				<option value="${department.departmentId}"><c:out value="${department.departmentName}"/>
			</c:forEach>
		</select>
		<input type="submit" value="Search">
		
	</form>
	</div>
	<hr>
	<div class="container">
		<form action="/COMP3095_TEAM_DNS/attendance/EnterAttendance" method="post">
			Date:
			<input type="date" name="date">
			<table style="margin-right: auto;margin-left:0px" border="1">
				<tr>
					<th>Last Name</th>
					<th>First Name</th>
					<th>Employee Number</th>
					<th>Present</th>
				</tr>
				<c:forEach var="employee" items="${employees}">
				<tr>
					<td><c:out value="${employee.lastName}" /></td>
					<td><c:out value="${employee.firstName}" /></td>
					<td><c:out value="${employee.employeeId}" /></td>
					<td><input type="checkbox" name="selected" value="${employee.employeeId}"/></td>
				</tr>
			</c:forEach>			
			</table>
			<input type="submit" value="Enter">
		</form>
		
	</div>
	
</body>
</html>
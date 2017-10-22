<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="/COMP3095_TEAM_DNS/css/main.css" />
<title>Group Entry</title>
</head>
<body>
	<div class="header">
		<ul>
			<li><a href="/COMP3095_TEAM_DNS/department/index.jsp">Departments</a></li>
			<li><a href="/COMP3095_TEAM_DNS/employee/index.jsp">Employees</a></li>
			<li><a href="/COMP3095_TEAM_DNS/group/index.jsp">Group</a></li>
			<li><a href="/COMP3095_TEAM_DNS/reports/index.jsp">Reports</a></li>
			<li><a href="/COMP3095_TEAM_DNS/attendance/index.jsp">Attendance</a></li>
		</ul>
		<ul id="logout">
		<li>Welcome, ~user~</li>
		<li>Logout</li>
		</ul>
	</div>
<div class="container">
	<h2>Group Entry</h2>
	<br />
	<form action="GroupHandler" method="post">
		Department: 
		<select name="department" id="ddDepartment">
			<c:forEach items="${departments}" var ="department">
				<option value="${department.departmentId}"><c:out value="${department.departmentName}"/>
			</c:forEach>
		</select><br />
		Group Name: 
		<input type="text" name="groupname"/><br />
		Employee 1: <select name="employee1" id="ddEmployee1">
				<c:forEach items="${employees}" var="employee">
				<option value="${employee.employeeId}"><c:out value="${employee}"/>
				</c:forEach>
		</select>
		Employee 2: <select name="employee2" id="ddEmployee2"></select>
		Employee 3: <select name="employee3" id="ddEmployee3"></select>
		<br />
		Employee 4: <select name="employee4" id="ddEmployee4"></select>
		Employee 5: <select name="employee5" id="ddEmployee5"></select>
		Employee 6: <select name="employee6" id="ddEmployee6"></select>
		<br />
		<input type="submit" />
		<input type="reset" value="Cancel"  />
		
	</form>
	<div>
		<div>${message}</div>
		<div>${empMessage }</div>
	</div>
</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="/COMP3095_TEAM_DNS/css/main.css" />
<title>Department Entry</title>
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
	<h2>Department Entry</h2>
	<br />
	<form action="DepartmentHandler" method="post">
		Department Name: 
		<input type="text" name="depname" /><br />
		Department Location/Floor: 
		<input type="text" name="deploc"/><br />
		<input type="submit" />
		<input type="button" value="Cancel">
	</form>
	<div> ${result}</div>
</div>
</body>
</html>
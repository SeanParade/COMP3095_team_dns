<%@ page import="classes.User, utilities.HelperUtility" language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" %>
	
<% User user = (User) session.getAttribute("user"); %>	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="/COMP3095_TEAM_DNS/css/main.css" />
<title>Employee Entry</title>
</head>
<body>
<%= HelperUtility.popNav(user) %>

<div class="container">
	<h2>Employee Entry</h2>
	<br />
	<form action="EmployeeHandler" method="post">
		First Name: 
		<input type="text" name="fname" /><br />
		Last Name: 
		<input type="text" name="lname"/><br />
		Employee #:
		<input type="text" name="empnumber" /><br />
		Email:
		<input type="text" name="email" /><br />
		<select name="hireyear" id="ddHire">
			<option value="">Hire Year</option>
		</select><br /><div id="hireError"></div><br />
		<!--- department selection?
		<select name="department" id="ddDepartment">
			<option value="">Department</option>
		</select><br />
		--->
		<select name="position" id="ddPosition">
			<option value="">Job Position</option>
		</select><br />
		<div id="positionError"></div><br />
		<input type="submit" />
		<input type="reset" value="Cancel"  />
		
	</form>
	<div>${result}</div>
</div>
</body>
</html>
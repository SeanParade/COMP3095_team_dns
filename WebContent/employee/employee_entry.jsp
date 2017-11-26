<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ include file="../includes/authentication.jsp" %>	

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="/COMP3095_TEAM_DNS/css/main.css" />
<title>Employee Entry</title>
</head>
<body>
<jsp:include page="../includes/navigation.jsp" />

<div class="container">
	<h2>Employee Entry</h2>
	<br />
		<div>${error}</div>
	<form action="EmployeeHandler" method="post">
		First Name: 
		<input type="text" name="First name" /><br />
		Last Name: 
		<input type="text" name="Last name"/><br />
		Employee #:
		<input type="text" name="Employee number" /><br />
		Email:
		<input type="text" name="Email" /><br />
		<select name="Hire year" id="ddHire">
			<option value="" disabled selected>Select Hire Year</option>
			<option value="2017">2017</option>
		</select><br />
		<select name="Position" id="ddPosition">
			<option value="" disabled selected>Select Position</option>
			<option value="Accountant">Accountant</option>
			<option value="Receptionist">Receptionist</option>
			<option value="Sales">Sales</option>
			<option value="Network Admin">Network Admin</option>
			<option value="Database Admin">Database Admin</option>
			<option value="HR Manager">HR Manager</option>
			<option value="HR Advisor">HR Advisor</option>
		</select><br />
		<input type="submit" />
		<input type="reset" value="Cancel"  />
		
	</form>

</div>
</body>
</html>
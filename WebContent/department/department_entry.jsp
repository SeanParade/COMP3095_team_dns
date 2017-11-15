<%@ page import="classes.User, utilities.HelperUtility, utilities.DatabaseAccess" language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" %>
	
<% 
// Credential check and user population
if (session.getAttribute("user") == null){
	String token = HelperUtility.tokenCheck(request);
	if (token != "fail"){
		session.setAttribute("user", DatabaseAccess.getUserByToken(token));
	}else{
		response.sendRedirect("/COMP3095_TEAM_DNS/Logout");
		return;
	}
}
User user = (User) session.getAttribute("user");
%>	

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="/COMP3095_TEAM_DNS/css/main.css" />
<title>Department Entry</title>
</head>
<body>
<%= HelperUtility.popNav(user) %>

<div class="container">
	<h2>Department Entry</h2>
	<br />
	<div> ${error} </div>
	<br>
	<form action="DepartmentHandler" method="post">
		Department Name: 
		<input type="text" name="Department name" /><br />
		Department Location/Floor: 
		<input type="text" name="Department location"/><br />
		<input type="submit" />
		<input type="button" value="Cancel">
	</form>
	
</div>
</body>
</html>
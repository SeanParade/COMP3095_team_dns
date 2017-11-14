<%@ page import="classes.User, utilities.HelperUtility" language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" %>
	
<% User user = (User) session.getAttribute("user");
	if (session.getAttribute("table") == null) {
		response.sendRedirect("/COMP3095_TEAM_DNS/home.jsp");
	}
%>	

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="/COMP3095_TEAM_DNS/css/main.css" />
<title>Home</title>
</head>
<body>
<%= HelperUtility.popNav(user) %>
	<div class="container">
		<h1><% session.getAttribute("table"); %> successfully added!</h1>
		<p><% session.getAttribute("table"); session.getAttribute("name"); %> has been added to the system</p>
	</div>

</body>
</html>
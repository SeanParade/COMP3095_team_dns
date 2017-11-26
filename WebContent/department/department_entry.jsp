<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ include file="../includes/authentication.jsp" %>	

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="/COMP3095_TEAM_DNS/css/main.css" />
<title>Department Entry</title>
</head>
<body>
<jsp:include page="../includes/navigation.jsp" />

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
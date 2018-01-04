<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" %>
	
<%@ include file="includes/authentication.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="/COMP3095_TEAM_DNS/css/main.css" />
<title>Home</title>
</head>
<body>
<jsp:include page="includes/navigation.jsp" />
	<div class="container">
		<h1>${table} successfully ${action}!</h1>
		<p style="text-align: center;">${table} ${name} has been ${action} to the system</p>
	</div>

</body>
</html>
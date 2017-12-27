<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="../includes/authentication.jsp" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View Departments</title>
</head>
<body>
<jsp:include page="../includes/navigation.jsp" />
<table border="1">
<tr><th>Department Name</th><th>Location</th></tr>

<c:forEach var="department" items="${departmentList}">
<tr><td><c:out value="${department.departmentName}"/></td><td><c:out value="${department.departmentLocation}"/></td> </tr>
</c:forEach>
</table>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View Employees</title>
</head>
<body>
<h2>View Employees</h2>
<jsp:include page="../includes/dropdown.jsp" />

<table border="1">
<tr>
<th>Last Name</th><th>First Name</th><th>Employee #</th><th>Hire Year</th><th>Email</th><th>Job Position</th>
</tr>
<c:forEach var="employee" items="${employeeList}">
<tr>
<td><c:out value="${employee.lastName}"/></td>
<td><c:out value="${employee.firstName}"/></td> 
<td><c:out value="${employee.employeeId}"/></td>
<td><c:out value="${employee.hireYear}"/></td>
<td><c:out value="${employee.email}"/></td>
<td><c:out value="${employee.role}"/></td>
</tr>
</c:forEach>
</table>
<p>
${employeeList}
${selected }
</p>
</body>
</html>
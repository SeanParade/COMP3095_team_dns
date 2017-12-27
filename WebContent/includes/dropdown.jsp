<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@page import="java.util.ArrayList" %>
<%@page import="utilities.DatabaseAccess" %>
<%@page import="classes.Department" %>
<form method="POST" action="${action}">
<select name="departments" onchange="this.form.submit()">
<option value="">${placeholder}</option>
	<c:forEach var="department" items="${departmentList}">
	<option value="${department.departmentId}" 
		<c:if test="${department.departmentId == selected}"> "selected" </c:if>>
		<c:out value="${department.departmentName}"/></option>
	</c:forEach>
</select>
</form>
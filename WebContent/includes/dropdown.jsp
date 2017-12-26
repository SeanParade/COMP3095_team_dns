<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<form method="POST" action="${action} }">
<select name="departments" onchange="this.form.submit()">
	<c:forEach var="department" items="${sessionScope.departmentsList}">
	<option><c:out value="${department.departmentName}"/></option>
	</c:forEach>
</select>
</form>
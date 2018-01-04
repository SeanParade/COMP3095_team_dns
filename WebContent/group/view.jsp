<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="../includes/authentication.jsp"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<link rel="stylesheet" href="/COMP3095_TEAM_DNS/css/main.css" />
<title>View Groups</title>
</head>
<body>
	<jsp:include page="../includes/navigation.jsp" />
	<div class="container">
		<h1>View Groups</h1>
		
		<form action="/COMP3095_TEAM_DNS/ViewGroup" method="post">
		<!-- department dropdown list -->
			Department: <select name="department" id="ddDepartment"
				onchange="self.location = '//' + location.host + location.pathname + '?dep=' + this.selectedIndex">
				<c:forEach items="${departments}" var="department">
					<option value="${department.departmentId}"><c:out
							value="${department.departmentName}" />
				</c:forEach>
			
			<!-- finds all group for the selected department -->
			</select> <br> Group: <select name="group">
				<c:forEach items="${groups}" var="group">
					<option value="${group.groupName}"><c:out
							value="${group.groupName }" />
				</c:forEach>
			</select> <br> <input type="submit">
		</form>

		<table border="1">
			<tr>
				<th>Department</th>
				<th>Group</th>		
				<th>Last Name</th>
				<th>First Name</th>
				<th>Employee #</th>
				<th>Hire Year</th>
				<th>Email</th>
				<th>Job Position</th>
			</tr>
			
			<!-- loops through list of employees and displays their group name and their personal information -->
			<c:forEach var="employee" items="${employeesList}">
				<tr>
					<td><c:out value="${depName}"/></td>
					<td><c:out value="${grpName}"/></td>
					<td><c:out value="${employee.lastName}" /></td>
					<td><c:out value="${employee.firstName}" /></td>
					<td><c:out value="${employee.employeeId}" /></td>
					<td><c:out value="${employee.hireYear}" /></td>
					<td><c:out value="${employee.email}" /></td>
					<td><c:out value="${employee.role}" /></td>
				</tr>
			</c:forEach>
		</table>
	</div>
	
	<!-- keeps correct placeholder for department dropdown list -->
	<script type="text/javascript">
var ddlDepartment = document.getElementById('ddDepartment');

function updateFormOnSelection(){
	self.location = '//' + location.host + location.pathname + '?dep=' + this.selectedIndex;
}

ddlDepartment.getElementsByTagName('option')[${selected}].selected='selected';	
</script>
</body>
</html>
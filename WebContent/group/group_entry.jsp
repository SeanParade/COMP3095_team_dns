<%@ page import="classes.User, utilities.HelperUtility" language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% User user = (User) session.getAttribute("user"); %>	

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="/COMP3095_TEAM_DNS/css/main.css" />
<title>Group Entry</title>
</head>
<body>
<%= HelperUtility.popNav(user) %>



<div class="container">
	<h2>Group Entry</h2>
	<br />
	<form action="/COMP3095_TEAM_DNS/Group" method="post">
		Department: 
		<select name="department" id="ddDepartment" 
		onchange="self.location = '//' + location.host + location.pathname + '?dep=' + this.selectedIndex">
			<c:forEach items="${departments}" var ="department">
				<option value="${department.departmentId}"><c:out value="${department.departmentName}"/>
			</c:forEach>
		</select><br />
		Group Name: 
		<input type="text" name="groupname" placeholder="Please enter a Name"/><br />
		
		<c:forEach items="${employees}" var="employee" varStatus="loop">
		Employee ${loop.index+1}: 
		        <select name="employee">
					<option value="">
						<c:forEach items="${employees}" var="employee" varStatus="loop">
							<option value="${employee.employeeId}"><c:out value="${employee}" />
						</c:forEach>
				</select>
	    </c:forEach>
	    
		<br/>
		<input type="submit" />
		<input type="reset" value="Cancel"  />
		
	</form>
	<div>
		<div>${message}</div>
		<div>${empMessage}</div>
	</div>
</div>

<script type="text/javascript">
var ddlDepartment = document.getElementById('ddDepartment');

function updateFormOnSelection(){
	self.location = '//' + location.host + location.pathname + '?dep=' + this.selectedIndex;
}

ddlDepartment.getElementsByTagName('option')[${selected}].selected='selected';	
</script>

</body>
</html>
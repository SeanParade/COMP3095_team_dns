<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	
<%@ include file="../includes/authentication.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="/COMP3095_TEAM_DNS/css/main.css" />
<title>Group Entry</title>
</head>
<body>
<jsp:include page="../includes/navigation.jsp" />



<div class="container">
	<h2>Group Entry</h2>
	<br />
		<div>
		<div>${error}</div>
	</div>
	<!-- form for adding Employees to groups -->
	<form action="/COMP3095_TEAM_DNS/Group" method="post">
		Department: 
		<select name="department" id="ddDepartment" 
		onchange="self.location = '//' + location.host + location.pathname + '?dep=' + this.selectedIndex">
			<c:forEach items="${departments}" var ="department">
				<option value="${department.departmentId}"><c:out value="${department.departmentName}"/>
			</c:forEach>
		</select><br />
		Group Name: 
		<input type="text" name="Group name" placeholder="Please enter a Name"/><br />
		
		<!-- loops through employees for two different dropdown boxes -->
		<c:forEach items="${employees}" var="employee" varStatus="loop">
		Employee ${loop.index+1}: 
		        <select name="Employee name">
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

</div>
<!-- gives the department dropdown the correct list item  -->
<script type="text/javascript">
var ddlDepartment = document.getElementById('ddDepartment');

function updateFormOnSelection(){
	self.location = '//' + location.host + location.pathname + '?dep=' + this.selectedIndex;
}

ddlDepartment.getElementsByTagName('option')[${selected}].selected='selected';	
</script>

</body>
</html>
<%@ page  language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>	
<%@ include file="../includes/authentication.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"/>
<link rel="stylesheet" href="/COMP3095_TEAM_DNS/css/main.css" />
<title>View Groups</title>
</head>
<body>
<jsp:include page="../includes/navigation.jsp" />
	<div class="container">
	<h1>View Groups</h1>
<form action="/COMP3095_TEAM_DNS/ViewGroup" method="post">
		Department: 
		<select name="department" id="ddDepartment" 
		onchange="self.location = '//' + location.host + location.pathname + '?dep=' + this.selectedIndex">
			<c:forEach items="${departments}" var ="department">
				<option value="${department.departmentId}"><c:out value="${department.departmentName}"/>
			</c:forEach>
		</select>
		<br>
		Group: <select name="group" >
		<c:forEach items="${groups}" var="group">
		<option value="${group.groupName}"><c:out value="${group.groupName }"/>
		</c:forEach>
		</select>
		<br>
		<input type="submit">
		</form>
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
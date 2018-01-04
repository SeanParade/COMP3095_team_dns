<%@ page  language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>	
<%@ include file="../includes/authentication.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"/>
<link rel="stylesheet" href="/COMP3095_TEAM_DNS/css/main.css" />
<title>Select Report Template</title>
</head>
<body>
<jsp:include page="../includes/navigation.jsp" />
	<div class="container">
		<h4>Please select a report template from your department</h4>
		<br />
		<form action="EnterReport" method="POST">
		
		<!-- Populates a ddl with report templates that match a users departmentId -->
		<label>Templates: </label>
			<select	name="template" id="ddlTemplate">
					<c:forEach items="${reportTemplates}" var="reportTemplates">
						<option value="${reportTemplates.id}">
							<c:out value="${reportTemplates.templateName}" />
						</option>
					</c:forEach>
			</select>
			<input type="submit" value="Select" />
		</form>
	</div>
</body>
</html>
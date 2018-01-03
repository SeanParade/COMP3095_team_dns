<%@ page  language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>	
<%@ include file="../includes/authentication.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"/>
<link rel="stylesheet" href="/COMP3095_TEAM_DNS/css/main.css" />
<title>View Reports</title>
</head>
<body>
<jsp:include page="../includes/navigation.jsp" />
	<div class="container">
	<h1>View Report</h1>
	<!-- needs form action to servlet -->
	<form action="" method="POST">
	<h2>Filter</h2>
	<select name="templateId" id="ddlTemplate" onchange="enable(this)">
		<c:forEach items="${templates}" var="template">
			<option value="${template.templateName}" 
				<c:if test="${template.templateName == selectedTemplate}"> "selected" </c:if>>
					<c:out value="${template.templateName}" />
					<!-- preserve selected template name -->
			</option>
		</c:forEach>
	</select>
	&nbsp;&nbsp;&nbsp;
	<select name="departmentId" id="ddlDepartment" onchange="enable(this)" disabled>
		<c:forEach items="${departments}" var="department">
			<option value="${department.departmentId}" 
				<c:if test="${department.departmentName == selectedDepartment}"> "selected" </c:if>>
				<c:out value="${department.departmentName}"  />
					<!-- preserve selected department name -->
			</option>
		</c:forEach>
	</select>
	&nbsp;&nbsp;&nbsp;
	<select name="reportId" id="ddlReport" onchange="enable(this)" disabled>
		<c:forEach items="${reports}" var="report">
			<option value="${report.templateId}" 
				<c:if test="${report.reportTitle == selectedReport}"> "selected" </c:if>>
					<c:out value="${report.reportTitle}" /> 
				<!-- preserve selected report title -->
			</option>
		</c:forEach>
	</select>
	<br>
	<input type="submit" name="RequestType" value="View"> &nbsp;&nbsp;&nbsp; 
	<input type="submit" name="RequestType" value="Cancel">
	<!-- in servlet: test whether requestType equals cancel or submit; 
	reset selected values attributes; 
	reset selected Report attribute-->
	</form>
	
	<hr>
	
	<!-- report here -->
	<!-- need to set selected template id when servlet redirects to page -->
	<div class="container report_container" id="report_div">
		
			<!-- if selectedReport attribute is not set: display placeholder text -->
		<c:if test="${empty selectedReport}">
		<h1>VIEW REPORT HERE</h1>
		</c:if>
			<!-- if selectedReport attribute is set: include report jsp -->
		<c:if test="${not empty selectedReport}">
			<!-- include report.jsp -->
			<jsp:include page="../includes/report.jsp" />
		</c:if>
		
	</div>
	
	
	</div>

	
	
	<!-- javascript functions -->
	<script>
		//function for enabling drop down lists
		function enable(elem)
		{
			if(elem.selectedIndex =-1)
				{
					//do nothing
				}
			else if(elem.id = "ddlTemplate")
				{
					document.getElementById("ddlDepartment").disabled = false;
				}
			else
				{
					document.getElementById("ddlReport").disabled = false;
				}
		}
		
	</script>
</body>
</html>
<%@ page  language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>	
<%@ include file="../includes/authentication.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="classes.ReportTemplate" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"/>
<link rel="stylesheet" href="/COMP3095_TEAM_DNS/css/main.css" />
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<title>View Reports</title>

</head>
<!--  onload functions: disable and enable dropdown boxes -->
<c:if test="${sessionScope.edit == 'true'}"><body onload="disable('ddlTemplate'); disable('ddlDepartment'); disable('ddlReport');"></c:if>
 <c:if test="${not empty sessionScope.selectedDepartment}"><body onload=" enable('ddlReport');"></c:if> 
<c:if test="${not empty sessionScope.selectedTemplate}"> <body onload="enable('ddlDepartment');"></c:if> 
<c:if test="${empty sessionScope.selectedTemplate}"> <body onload="enable('ddlTemplate');"></c:if>

<jsp:include page="../includes/navigation.jsp" />
	<div class="container">
	<h1>View Report</h1>
	<div class="error">${sessionScope.error}</div>

	
	<!-- search templates -->
	<form action="ViewReport" id="viewForm" method="POST">
	<h2>Filter</h2>
	<select name="templateId" id="ddlTemplate" onchange="this.form.submit()" disabled>
		<option value="">Template Name</option>
		<c:forEach items="${sessionScope.templates}" var="template">
			<option value="${template.id}" 
				<c:if test="${template.id == sessionScope.selectedTemplate}"> selected </c:if>>
					<c:out value="${template.templateName}" />
					<!-- preserve selected template name -->
			</option>
		</c:forEach>
	</select>

	<!-- search departments -->
	<select name="departmentId" id="ddlDepartment" onchange="this.form.submit()" disabled>
		<option value="">Department</option>
			<option value="${sessionScope.department.departmentId}" 
				<c:if test="${sessionScope.department.departmentId == sessionScope.selectedDepartment}"> selected </c:if>>
				<c:out value="${sessionScope.department.departmentName}"  />
					<!-- preserve selected department name -->
			</option>
	</select>
	
	<!-- search reports -->
	<select name="reportId" id="ddlReport" disabled>
	<option value="">Report Title</option>
		<c:forEach items="${sessionScope.reports}" var="report">
			<option value="${report.reportId}" 
				<c:if test="${report.reportId == sessionScope.selectedReport}"> selected </c:if>>
					<c:out value="${report.reportTitle}" /> 
				<!-- preserve selected report title -->
			</option>
		</c:forEach>
	</select>
	<br>
	
	<!-- submit -->
	<input type="submit" value="View">
	<!-- submit GET(), reset session attributes -->
	<button onclick="changeMethod();this.form.submit();">Cancel</button>
	</form>
	
	
	<hr>
	
	<!-- report here -->
	<div class="container report_container" id="report_div">
		
			<!-- if selectedReport attribute is not set: display placeholder text -->
		<c:if test="${empty selectedReport}">
		<h1>VIEW REPORT HERE</h1>
		</c:if>
			<!-- if selectedReport attribute is set: include report jsp -->
		<c:if test="${not empty selectedReport}">
			<!-- include report.jsp -->
		<div id="report">	
			<div class="report_header" id="reportHeader">
				<%@include file="../includes/report_header.jsp"%> <!-- report header jsp -->
			</div>
			<div class="report_data" id="reportData">
				<%@include file="../includes/report_data.jsp"%> <!--  report data jsp -->
			</div>
		</div>
		</c:if>
		
	</div>	
	</div>

	<!-- javascript functions -->
	<script>
		//function for enabling drop down lists
		function enable(id)
		{
			
			document.getElementById(id).removeAttribute('disabled');
		
		}
		function disable(id)
		{
			document.getElementById(id).addAttribute('disabled');
		}
		
		//function to toggle showing div
		function toggleDiv()
		{
			var div = document.getElementById("report");
			if(div.style.display == "none")
				{
					div.style.display="block";
				}
			else
				{
					div.style.display="none";
				}
			
		}
		
		function changeMethod() {
		    $("#viewForm").attr("method", "get");
		}
		
		
	</script>
</body>
</html>
<%@ page  language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>	
<%@ include file="../includes/authentication.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"/>
		<link rel="stylesheet" href="/COMP3095_TEAM_DNS/css/main.css" />
		<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/smoothness/jquery-ui.css">
		<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
		<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
	    <script>
	    	// field for datepicker
	    	$(function(){$("#datepicker").datepicker();});
	    </script>
	    <title>Enter Report</title>
	</head>
<body>
<jsp:useBean id="date" class="java.util.Date" />
	<jsp:include page="../includes/navigation.jsp" />
	<div class="container">
		<h1>Enter Report</h1>
		<form action="InsertReport" method="POST">
		
		<!-- hidden form values to be passed to servlet on submit -->
		<input type="hidden" name="templateId" value="${template.id}" />
		<input type="hidden" name="departmentId" value="${template.departmentId}" />
	    <c:forEach var="s1m" items="${section1Map}" >
			<input type="hidden" name="s1criteria" value="${s1m.key}" />
		</c:forEach>
		<c:forEach var="s2m" items="${section2Map}" >
			<input type="hidden" name="s2criteria" value="${s2m.key}" />
		</c:forEach>
		<c:forEach var="s3m" items="${section3Map}" >
			<input type="hidden" name="s3criteria" value="${s3m.key}" />
		</c:forEach>
		
		<!-- Details section. Date is populated with the current system date. Report object defaults groupId and employeeId to -1 if not submitted -->
			<fieldset>
				<legend class="left-label">1. Details: </legend>
				<label>Report Template: </label> 
				<input type="text"	name="templateName" value="${template.templateName}" disabled/>
				<label>Report Title: </label> 
				<input type="text" name="reportTitle"/> 
				<label>Date: </label> 
				<input type="text" name="reportDate" id="datepicker" value="<fmt:formatDate value='${date}' pattern='MM/dd/yyyy'/>"/> 
				<br /> 
				<label>Department: </label> 
				<select	name="departmentId" id="ddlDepartment" disabled>
					<option value="" selected>
						${departmentName}
					</option>		
				</select>
				<br/>
				<label>Report-Type:</label>
				<fieldset>
					<input type="radio" name="reportType" id="groupType" value="group" onclick="disable(this)"/>
					<label for="groupType">Group</label>
					<input type="radio" name="reportType" id="employeeType" value="employee" onclick="disable(this)" checked/>
					<label for="employeeType">Employee</label>
				</fieldset>
				<select	name="groupId" id="ddlGroup" disabled>
					<c:forEach items="${groups}" var="groups">
						<option value="${groups.groupId}">
							<c:out value="${groups.groupName}" />
						</option>
					</c:forEach>
				</select>
				
				<select	name="employeeId" id="ddlEmployee">
					<c:forEach items="${employees}" var="employees" varStatus="loop">
						<option value="${employees.employeeId}"	<c:if test="${loop.index == 0}">selected</c:if> > 
							${employees}
						</option>
					</c:forEach>
			    </select>
				<hr />
				
				<!-- Criteria Section 1. Populated with a treemap of parameters created by csv's from the report template table of the database.
				     The first evaluation option for each criteria is tagged as selected in the print loop -->
				<legend class="left-label">	2.Section I: 
					<input type="text" name="sec1Title" value="${template.sec1Title}" disabled/>
				</legend>
				<fieldset>  
					<c:forEach var="s1m" items="${section1Map}" >
						<input type="text" name="s1criteria" value="${s1m.key}" disabled/>
						<select name="s1eval" class="evaluation" onchange="calculateSum()">
							<c:forEach var="i" begin="1" end="${s1m.value}">
							    <option value="${i}" <c:if test="${i == 1}">selected</c:if> >
							    	${i}
							    </option>
							</c:forEach>
						</select>
						<br/>
				   </c:forEach>
				   <textarea name="s1comment" id="s1comment" cols="30" rows="10" placeholder="Please enter a comment here"></textarea>
				</fieldset>
				<hr />
				
				<!-- Criteria Section 2. Populated with a treemap of parameters created by csv's from the report template table of the database.
				     The first evaluation option for each criteria is tagged as selected in the print loop -->
				<legend class="left-label">	3.Section II: 
					<input type="text" name="sec2Title" value="${template.sec2Title}" disabled/>
				</legend>
				<fieldset>  
					<c:forEach var="s2m" items="${section2Map}" >
						<input type="text" name="s2criteria" value="${s2m.key}" disabled/>
						<select name="s2eval" class="evaluation" onchange="calculateSum()">
							<c:forEach var="i" begin="1" end="${s2m.value}">
							    <option value="${i}" <c:if test="${i == 1}">selected</c:if>>
							    	${i}
							    </option>
							</c:forEach>
						</select>
						<br/>
					</c:forEach>
					<textarea name="s2comment" id="s2comment" cols="30" rows="10" placeholder="Please enter a comment here"></textarea>
				</fieldset>
				<hr />

				<!-- Criteria Section 3. Populated with a treemap of parameters created by csv's from the report template table of the database.
				     The first evaluation option for each criteria is tagged as selected in the print loop -->
				<legend> 3.Section III: 
					<input type="text" name="sec3Title" value="${template.sec3Title}" disabled/>
				</legend>
				<fieldset>  
					<c:forEach var="s3m" items="${section3Map}" >
						<input type="text" name="s3criteria" value="${s3m.key}" disabled/>
						<select name="s3eval" class="evaluation" onchange="calculateSum()">
							<c:forEach var="i" begin="1" end="${s3m.value}">
							    <option value="${i}" <c:if test="${i == 1}">selected</c:if> >
							    	${i}
							    </option>
							</c:forEach>
						</select>
						<br/>
					</c:forEach>
					<textarea name="s3comment" id="s3comment" cols="30" rows="10" placeholder="Please enter a comment here"></textarea>
				</fieldset>
				<hr />
				
				<input type="submit" value="Submit" /> <input type="reset"	value="Cancel" />
			</fieldset>
			<input type="text" id="sumBox" value="0" disabled></input><p> / ${evaluationMaximum}</p>
			<input type="hidden" id="sumBoxHidden" value="0" name="evaluationTotal"></input>
		</form>
		
		<h3>${error}</h3>
	</div>
	<script>	    	
       //function for getting sum of evaluations and populating the evaluation sum field
       function calculateSum()
       {
    	   var sum = 0;
    	   var x = document.getElementsByClassName("evaluation");
    	   for (var i=0; i<x.length; i++)
    		   {
    		   		sum = sum + eval(x[i].value);
    		   }
    	   document.getElementById("sumBox").value = sum;
    	   document.getElementById("sumBoxHidden").value = sum;
       }
       
       // disable drop down list based on selection of employee rb or group rb
       function disable(element){
    	if(element.id == "groupType") 
    		{
    			//disable employee dropdown
				 document.getElementById("ddlEmployee").disabled = true;   		
    			//enable group dropdown
    			document.getElementById("ddlGroup").disabled = false;
    		}
    	if(element.id == "employeeType")
    		{
    			//disable group dropdown
    			document.getElementById("ddlGroup").disabled = true;   		
				//enable employee dropdown
				document.getElementById("ddlEmployee").disabled = false;
    		}
       }
	</script>
</body>
</html>
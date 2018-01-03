<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<h4>Details</h4>
<table>
<tr><th>Report</th><td>${report.templateName}</td></tr>
<tr><th>Report Title</th><td>${report.reportTitle}</td></tr>
<tr><th>Date Created</th><td>${report.date}</td></tr>
<tr><th>${report.reportType}</th><td> 
<!-- test report type to display either employee name or group name -->
	<c:if test='${report.reportType == "Employee"}'>
	<!-- display employee name -->
	${employee}
	</c:if>
	<c:if test='${report.reportType == "Group"}'>
	<!-- display employee name -->
	${group.groupName}
	</c:if>
</td></tr>
</table>

<!-- report body will go here with editable text -->
<!-- needs action to servlet -->
<form action ="" method="POST">

<jsp:include page="../includes/report_data.jsp" />

<input type="button" id="edit" value="Edit Report" onclick="edit(); toggleButtons()">
<input type="submit" id="update" value="Update Report">
<input type="reset" id="reset" value="Back">
</form>

<!-- javascript functions -->
<script>
	
	//function to disable element
	function disable(elem)
	{
		document.getElementById(elem).disabled = true;
	
	}
	document.getElementById("ddlDepartment").disabled = true;
	document.getElementById("groupType").disabled = true;
	document.getElementById("employeeType").disabled = true;
	document.getElementById("ddlEmployee").disabled = true;
	document.getElementsByClass("evaluation").disabled = true;
	document.getElementsByClass("comments").readonly = true;
	//function to disable all elems
	function disableAll()
	{
		disable("ddlDepartment");
		disable("groupType");
		disable("employeeType");
		disable("ddlEmployee");
		var evals = document.getElementsByClass("evaluations");
		for(var i=0; i<evals.length; i++)
			{
				evals[i].disabled = true;
			}		
		var comments = document.getElementsByClass("comments");
		for(var i=0; i<comments.length; i++)
			{
				comments[i].readonly = true;
			}
	}
	//function to toggle edit/update buttons showing
	function toggleButtons()
	{
			document.getElementById("update").style.display = "block";
			document.getElementById("edit").style.display = "none";
		
	}
	//function to remove readonly attribute
	function removeReadOnly(elem)
	{
		elem.removeAttribute('readonly');
	}
	//function make all desired text boxes editable
	function edit()
	{
		var evals = document.getElementsByClass("evaluations");
		var comments = document.getElementsByClass("comments");
		
		for(var i=0; i<evals.length; i++)
			evals[i].disabled = false;
		for(var i=0; i<comments.length; i++)
			removeReadOnly(comments[i]);
	}
	
</script>
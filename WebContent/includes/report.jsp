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



</form>

<!-- javascript functions -->
<script>
	
	//function to disable all dropdowns on load
	function disable()
	{
		
	}
	//function to toggle edit/update buttons showing
	function toggleButtons()
	{
		
	}
	//function to remove readonly attribute
	function removeReadOnly(elem)
	{
		elem.removeAttribute('readonly');
	}
	//function make all desired text boxes editable
	function edit()
	{
		
	}
	
</script>
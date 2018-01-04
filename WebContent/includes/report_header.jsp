<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<h4>Details</h4>
<table>
<tr><th>Report</th><td>${sessionScope.template.templateName}</td></tr>
<tr><th>Report Title</th><td>${sessionScope.report.title}</td></tr>
<tr><th>Date Created</th><td>${sessionScope.report.date}</td></tr>
<tr><th>${sessionScope.report.reportType}</th><td> 
<!-- test report type to display either employee name or group name -->
	<c:if test='${sessionScope.report.reportType == "employee"}'>
	<!-- display employee name -->
	${sessionScope.employee}
	</c:if>
	<c:if test='${sessionScope.report.reportType == "group"}'>
	<!-- display employee name -->
	${sessionScope.group.groupName}
	</c:if>
</td></tr>
</table>
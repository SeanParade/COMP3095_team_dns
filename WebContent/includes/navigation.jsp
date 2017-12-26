<%@ page import="classes.Employee"%>
<% Employee user = (Employee) session.getAttribute("user");%>

	<div class="header">
		<ul> 
		    <li><a href="/COMP3095_TEAM_DNS/department/index.jsp">Departments</a></li>
		    <li><a href="/COMP3095_TEAM_DNS/employee/index.jsp">Employees</a></li>
		    <li><a href="/COMP3095_TEAM_DNS/group/index.jsp">Group</a></li>
		    <li><a href="/COMP3095_TEAM_DNS/reports/index.jsp">Reports</a></li>
		    <li><a href="/COMP3095_TEAM_DNS/attendance/index.jsp">Attendance</a></li>
	    </ul>	
	    <ul id="logout" class="rightinfo">
		    <li>Welcome,  <%=user.getFirstName()%>  </li> 
 		    <li><a href="/COMP3095_TEAM_DNS/Logout">Logout</a></li>
	    </ul>
    </div>
		

	
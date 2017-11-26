<%@ page import="utilities.HelperUtility, utilities.DatabaseAccess" %>

<% 
// Credential check and user population
if (session.getAttribute("user") == null){
	String token = HelperUtility.tokenCheck(request);
	if (token != "fail"){
		session.setAttribute("user", DatabaseAccess.getUserByToken(token));
	}else{
		response.sendRedirect("/COMP3095_TEAM_DNS/Logout");
		return;
	}
}
%>
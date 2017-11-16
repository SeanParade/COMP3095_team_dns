package utilities;

import java.io.IOException;
import java.util.Random;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import classes.User;

public class HelperUtility {

	public static boolean isMissing(String s) {
		if (s == null || s.trim().length() <= 0)
			return true;
		return false;
	}

	public static boolean isInteger(String s) {
		return isInteger(s, 10);
	}

	public static boolean isInteger(String s, int radix) {
		if (s.isEmpty())
			return false;
		for (int i = 0; i < s.length(); i++) {
			if (i == 0 && s.charAt(i) == '-') {
				if (s.length() == 1)
					return false;
				else
					continue;
			}
			if (Character.digit(s.charAt(i), radix) < 0)
				return false;
		}
		return true;
	}

	public static boolean isDouble(String str) {
		double x = Double.parseDouble(str);
		if (x == (int) x)
			return false;
		return true;
	}

	public static boolean isNumber(String s) {

		if (isInteger(s) || isDouble(s))
			return true;
		return false;

	}

	public static int getRandomNumberInRange(int min, int max) {

		if (min >= max) {
			throw new IllegalArgumentException("max must be greater than min");
		}

		Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
	}
	
	public static String popNav(User user) 
	/**
	 * Takes the currently logged in user and populates a navigation with a
	 * welcome message for the user. This method is mostly used to hide this ugly
	 * block html for the navigation. 
	 * 
	 * @param   user   The current user that is logged in.
	 * @return         A navigation html string that contains a welcome message 
	 *                 for the current user.
	 */
	{

		String nav = "<div class=\"header\">" +
		"<ul>" +
		"<li><a href=\"/COMP3095_TEAM_DNS/department/index.jsp\">Departments</a></li>" +
		"<li><a href=\"/COMP3095_TEAM_DNS/employee/index.jsp\">Employees</a></li>" +
		"<li><a href=\"/COMP3095_TEAM_DNS/group/index.jsp\">Group</a></li>" +
		"<li><a href=\"/COMP3095_TEAM_DNS/reports/index.jsp\">Reports</a></li>" +
		"<li><a href=\"/COMP3095_TEAM_DNS/attendance/index.jsp\">Attendance</a></li>" +
	    "</ul>	<ul id=\"logout\" class=\"rightinfo\">" +
		"<li>Welcome, " + user.getFirstName() + "</li>" +
		"<li><a href=\"/COMP3095_TEAM_DNS/Logout\">Logout</a></li>" +
	    "</ul>" +
	    "</div>";
		
		return nav;
	}
	

	public static String emptyFieldsCheck(String[] fieldNames, HttpServletRequest request)
	/**
	 * Checks String array fieldNames against request parameters, returns missing 
	 * parameters name error message
	 * 
	 * @param  fieldNames  Array of parameters passed by a form.
	 * @param  request     Servlet request object used to get parameters   
	 * @return             String with a formatted error message for all form 
	 *                     parameters missing
	 */           
	{
		String errorMessage = "";
		for(int i=0; i< fieldNames.length; i++)
		{
			if(HelperUtility.isMissing(request.getParameter(fieldNames[i])))
			{
				errorMessage += fieldNames[i] + " is required <br>";
			}
		}
		return errorMessage; 
	}

	
	public static String tokenCheck(HttpServletRequest request) throws IOException
	/**
	 * Takes a request to access cookies. Checks cookies for a user token to
	 * see whether or not the user has a user token. Returns the user token.
	 * 
	 * @param  request  Servlet request object used to access cookies.
	 * @return          Either a string with a user's token or "fail"
	 *                  which is used in checking expressions
	 */
	{
		Cookie[] cookies = request.getCookies();

		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("userToken")) {
					if(DatabaseAccess.recordExists("user", "token", cookie.getValue())) {
						return cookie.getValue();
					}
				}
			}
			return "fail";
		}
		else {
			return "fail";
		}
	}
	
}



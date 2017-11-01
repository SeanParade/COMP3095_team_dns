package utilities;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import javax.servlet.http.HttpServletResponse;

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
	
	public static String popNav(User user)  {

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

}

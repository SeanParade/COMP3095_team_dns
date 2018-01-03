package utilities;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/************************************************************************
 * Project: COMP3095_team_dns
 * Assignment: Assignment #1
 * Authors: Sergio Santilli, Dylan Roberts, Nooran El-Sherif, Sean Price
 * Student Numbers: 100727526, 100695733, 101015020
 * Date: 20/11/2017
 * Description: HelperUtility - Methods to help with miscellaneous things. Checks if a string is empty,
 * checks if a user has a token, etc.
 ***********************************************************************/

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
				errorMessage += fieldNames[i] + " is required. <br>";
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
					if(DatabaseAccess.tokenExists(cookie.getValue())) {
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
	
	public static String parseTemplateCriteria(String[] secCrit, String[] secEval)
	/**
	 * Takes the Criteria and Maximum Evaluation from report template input and returns 
	 * a CSV string that alternates between the Criteria and the Max Evaluation while
	 * throwing out empty inputs and unmatched evaluation numbers
	 * 
	 * @param secCrit  String array containing criteria input from the report template creation form
	 * @param secEval  String array containing evaluation input from the report template creation form
	 * @return         Properly formatted csv string with alternating data from the two arrays to be 
	 *                 stored into the database.
	 */
	{
	    String critString = "";
	    ArrayList<String> secCritList = new ArrayList<String>(Arrays.asList(secCrit));
	    //remove empty Criteria values
	    secCritList.removeAll(Arrays.asList("",null));
	    
	    for(int i = 0; i < secCritList.size(); i++) {
	        critString += secCritList.get(i) + "," + secEval[i];
	        //as long as not the last iteration add trailing comma
	        if(i < secCritList.size()-1)
	            critString += ",";
	    }	    
	    return critString;
	}
	

}



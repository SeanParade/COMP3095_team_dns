package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utilities.HelperUtility;

/**
 * Servlet implementation class Employee
 */
@WebServlet("/Employee")
public class Employee extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		
		String firstName = request.getParameter("firstname");
		String lastName = request.getParameter("lname");
		String employeeNo = request.getParameter("empnumber");
		String email = request.getParameter("email");
		String hireYear = request.getParameter("hireyear");
		String position = request.getParameter("position");
		
		if (HelperUtility.isMissing(firstName) || HelperUtility.isMissing(lastName) 
				|| HelperUtility.isMissing(employeeNo) || HelperUtility.isMissing(email) 
				|| HelperUtility.isMissing(hireYear) || HelperUtility.isMissing(position)) {
			//add to DB logic here
		}else {
			
			//error logic here
			
		}
		
		
	}

}

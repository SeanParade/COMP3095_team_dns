package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import classes.Employee;
import utilities.DatabaseAccess;
import utilities.HelperUtility;
import utilities.RegularExpressionValidator;

/**
 * Servlet implementation class EmployeeHandler
 */
@WebServlet("/employee/EmployeeHandler")
public class EmployeeHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		
		String firstName = request.getParameter("fname");
		String lastName = request.getParameter("lname");
		String employeeNo = request.getParameter("empnumber");
		String email = request.getParameter("email");
		//temporarily commented out until dropdown is populated
		String hireYear = "1990"; //request.getParameter("hireyear");
		String position = "receptionist";//request.getParameter("position");
		RegularExpressionValidator regExValidator = new RegularExpressionValidator();
		
		String message;
		//run through validation
		if (HelperUtility.isMissing(firstName)|| regExValidator.validateAlphabetic(firstName))
			message = "Invalid first name";
		if(HelperUtility.isMissing(lastName) || regExValidator.validateAlphabetic(lastName))
			message = "Invalid last name";
		if(HelperUtility.isMissing(employeeNo) || !HelperUtility.isInteger(employeeNo))
			message = "Invalid employee number";
		if(HelperUtility.isMissing(email))//|| regExValidator.validateEmail(email)) regex not working properly?
			message = "Invalid email address";
		/*temporarily commented out until dropdown is populated
		 * if(HelperUtility.isMissing(hireYear))
			message = "Invalid year selection";
		if (HelperUtility.isMissing(position))
			message = "Invalid position selection";
			*/
		
		else {
			//add to DB logic here
			
			Employee emp = new Employee(Integer.parseInt(employeeNo), firstName, lastName, email, hireYear, position);
			
			message = DatabaseAccess.insertEmployee(emp);
		}
		request.setAttribute("result", message);
		request.getRequestDispatcher("/employee/employee_entry.jsp").forward(request, response);
		
		
	}

}

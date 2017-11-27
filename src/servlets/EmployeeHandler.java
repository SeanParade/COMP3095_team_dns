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
/**********************************************************************
 * Project: COMP3095_team_dns
 * Assignment: Assignment #1
 * Authors: Dylan Roberts, Nooran El-Sherif, Sean Price
 * Student Numbers: 100727526, 100695733, 101015020
 * Date: 20/11/2017
 * Description: EmployeeHandler - Creates Employees in the database if the form validation is successful
 ***********************************************************************/

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
	 * Employee Entry:
	 * Takes all form information and checks if any of the form entries are missing. 
	 * If nothing is missing all the parameters are grabbed. The first name, last name
	 * employee number are checked for validity and then checked against the DB if the
	 * employee already exists. Any errors are redirected to the employee entry page
	 * and the errors are displayed. If there are no errors the employee is added to 
	 * the DB and user is sent to a confirmation page.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		
		String[] employeeFields = {"First name", "Last name", "Employee number", 
				"Email", "Hire year", "Position"};

		String error = HelperUtility.emptyFieldsCheck(employeeFields, request);
		if(HelperUtility.isMissing(error))
		{
			String firstName = request.getParameter("First name");
			String lastName = request.getParameter("Last name");
			String employeeNo = request.getParameter("Employee number");
			String email = request.getParameter("Email");
		
			String hireYear = request.getParameter("Hire year");
			String position = request.getParameter("Position");
			//get department selection
			RegularExpressionValidator regExValidator = new RegularExpressionValidator();
			
			if(!regExValidator.validateAlphabetic(firstName))
			{
				request.setAttribute("error", "Invalid first name");
			}
			else if (!regExValidator.validateAlphabetic(lastName))
			{
				request.setAttribute("error", "Invalid last name");
			}
			else if (!HelperUtility.isInteger(employeeNo))
			{
				request.setAttribute("error", "Invalid employee number");
			}
			else
			{
				if(DatabaseAccess.employeeExists(firstName, lastName))
				{
					request.setAttribute("error", "There is already an employee with the same name");
				}
				else
				{
					Employee emp = new Employee(Integer.parseInt(employeeNo), firstName, lastName, email, hireYear, position);
					String result = DatabaseAccess.insertEmployee(emp);
					if(result.equals("success"))
					{
						//set attributes with "Employee" and employee name
						request.setAttribute("error", "");
						request.setAttribute("table", "Employee");
						request.setAttribute("name", firstName + " " + lastName);
						request.getRequestDispatcher("/confirmation.jsp").forward(request, response);
						return;
						
					}
					else
					{
						request.setAttribute("error", "Database error");
					}
				}
			}
		}
		else
		{
			request.setAttribute("error", error);
		}
		
		request.getRequestDispatcher("/employee/employee_entry.jsp").forward(request, response);
		
		
	}

}

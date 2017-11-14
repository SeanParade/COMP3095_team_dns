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
		
		String[] employeeParams = {"First name", "Last name", "Employee number", 
				"Email"};

		String error = HelperUtility.errorMessage(employeeParams, request);
		if(HelperUtility.isMissing(error))
		{
			String firstName = request.getParameter("First name");
			String lastName = request.getParameter("Last name");
			String employeeNo = request.getParameter("Employee number");
			String email = request.getParameter("Email");
			
			//temporarily commented out until dropdown is po pulated
			String hireYear = request.getParameter("Hire year");
			String position = request.getParameter("Position");
			//get department selection
			RegularExpressionValidator regExValidator = new RegularExpressionValidator();
			
			if(regExValidator.validateAlphabetic(firstName))
			{
				error = "Invalid first name";
			}
			else if (regExValidator.validateAlphabetic(lastName))
			{
				error = "Invalid last name";
			}
			else if (!HelperUtility.isInteger(employeeNo))
			{
				error = "Invalid employee number";
			}
			else
			{
				if(DatabaseAccess.employeeExists(firstName, lastName))
				{
					request.setAttribute(error, "There is already an employee with the same name");
				}
				else
				{
					Employee emp = new Employee(Integer.parseInt(employeeNo), firstName, lastName, email, hireYear, position);
					String result = DatabaseAccess.insertEmployee(emp);
					if(result.equals("success"))
					{
						//set attribles with "Employee" and employee name
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

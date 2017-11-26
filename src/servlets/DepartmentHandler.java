package servlets;
import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import classes.Department;
import utilities.DatabaseAccess;
import utilities.HelperUtility;
/**********************************************************************
 * Project: COMP3095_team_dns
 * Assignment: Assignment #1
 * Authors: Dylan Roberts, Nooran El-Sherif, Sean Price
 * Student Numbers: 100727526, 100695733, 101015020
 * Date: 20/11/2017
 * Description: DepartmentHandler - Servlet that handles the processes on the Department Entry page.
 * Inserts Department information is form is filled out properly and unique
 ***********************************************************************/

/**
 * Servlet implementation class DepartmentHandler
 */
@WebServlet("/department/DepartmentHandler")
public class DepartmentHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DepartmentHandler() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * Department:
	 * 
	 * 
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String [] departmentFields = {"Department name", "Department location"};
		
		String error = HelperUtility.emptyFieldsCheck(departmentFields, request);
		
		if(HelperUtility.isMissing(error))
		{
			String departmentName = request.getParameter("Department name");
			String departmentLocation = request.getParameter("Department location");
			//check if department name already exists
			if(DatabaseAccess.departmentExists(departmentName))
			{
				request.setAttribute("error", "There is already a department with that name"); 
			}
			
			else
			{
				Department dep = new Department(departmentName, departmentLocation);			
					//check if result is success
				String result = DatabaseAccess.insertDepartment(dep);
				if(result.equals("success"))
				{
					//set attributes with "Department", departmentName
					 request.setAttribute("table", "Department");
					 request.setAttribute("name", departmentName);
					//redirect to confirmation page
					 //HttpSession session = request.getSession();
					 //session.setAttribute("confirmTable", "Department");
					 //session.setAttribute("confirmName", departmentName);
					request.getRequestDispatcher("/confirmation.jsp").forward(request, response);
					return;
				}
				else
				{
				//set error message with general database error
				 request.setAttribute("error", "Database error");
				}
				}
			}
		else{
			request.setAttribute("error", error);
		}		
		request.getRequestDispatcher("/department/department_entry.jsp").forward(request, response);
	}
		
		
	}



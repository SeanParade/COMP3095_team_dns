package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import classes.Department;
import classes.Employee;
import classes.Group;
import utilities.DatabaseAccess;
import utilities.HelperUtility;
/************************************************************************
 * Project: COMP3095_team_dns
 * Assignment: Assignment #1
 * Authors: Dylan Roberts, Nooran El-Sherif, Sean Price
 * Student Numbers: 100727526, 100695733, 101015020
 * Date: 20/11/2017
 * Description: GroupHandler - Servlet that handles the processes on the Group Entry page.
 * Inserts Department information is form is filled out properly and unique
 ***********************************************************************/

/**
 * Servlet implementation class GroupHandler
 */
@WebServlet(name = "Group", urlPatterns = { "/Group" })
public class GroupHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GroupHandler() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ArrayList<Department> departmentList = DatabaseAccess.selectDepartments();
		String selectedDep = request.getParameter("dep");
		try {
			// Load with defaults on original GET request
			if (!departmentList.isEmpty() && selectedDep == null) {
				request.setAttribute("departments", departmentList);
				ArrayList<Employee> employeeList = DatabaseAccess.selectEmployeesByDepartment(1);
				request.setAttribute("employees", employeeList);
			} // reload with correct department on department selection change
			else if (!departmentList.isEmpty() && selectedDep != null) {
				request.setAttribute("departments", departmentList);
				ArrayList<Employee> employeeList = DatabaseAccess
						.selectEmployeesByDepartment(Integer.parseInt(selectedDep) + 1);
				request.setAttribute("employees", employeeList);
				request.setAttribute("selected", selectedDep);
			} else {
				request.setAttribute("error", "empty list");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("/group/group_entry.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// if the submission has selected employees then it must be a group;
		// check that name was filled
		String [] employeeFields = {"Group name", "Employee name"};
		String error = HelperUtility.emptyFieldsCheck(employeeFields, request);
		
		if(HelperUtility.isMissing(error))
		{
			List<String> employeeIDs = Arrays.asList(request.getParameterValues("Employee name"));
			String groupName = request.getParameter("Group name");
			
			if(DatabaseAccess.recordExists("egroup", "groupName", groupName))
			{
				request.setAttribute("error", "There is already a group with that name");
			}
			else
			{
				Group newGroup = new Group(groupName);
				if(DatabaseAccess.insertGroup(newGroup).equals("success"))
					{
					request.setAttribute("table", "Group");
					request.setAttribute("name", groupName);
					
					// Employee update loop					
					for (String employeeID : employeeIDs) {
						if (!HelperUtility.isMissing(employeeID)) {
						
							try {
								String result = DatabaseAccess.updateEmployeeGroupByName(Integer.parseInt(employeeID), groupName);
								if(!result.equals("success")){
									request.setAttribute("error", result);
								}
							} catch (SQLException e) {
								request.setAttribute("error", "Database Error: Employee Insert");
							  }							 
						}
					}
					request.getRequestDispatcher("/confirmation.jsp").forward(request, response);
					return;
				}
				else{
					request.setAttribute("error", "Database error: Group Insert");
				}
			}
		}
		else{
			request.setAttribute("error", error);
		}
		

			ArrayList<Department> departmentList = DatabaseAccess.selectDepartments();
			String selectedDep = request.getParameter("dep");
				// Load with defaults
				if (!departmentList.isEmpty() && selectedDep == null) {
					request.setAttribute("departments", departmentList);
					ArrayList<Employee> employeeList = DatabaseAccess.selectEmployeesByDepartment(1);
					request.setAttribute("employees", employeeList);
				}
				else if (!departmentList.isEmpty() && selectedDep != null) {
					request.setAttribute("departments", departmentList);
					ArrayList<Employee> employeeList = DatabaseAccess
							.selectEmployeesByDepartment(Integer.parseInt(selectedDep) + 1);
					request.setAttribute("employees", employeeList);
					request.setAttribute("selected", selectedDep);
				}			
			request.getRequestDispatcher("/group/group_entry.jsp").forward(request, response);
	}

}

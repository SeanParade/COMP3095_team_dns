package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;

import classes.Department;
import classes.Employee;
import classes.Group;
import utilities.DatabaseAccess;

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
				request.setAttribute("message", "empty list");
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

		int departmentId;

		// if the submission has selected employees then it must be a group;
		// check that name was filled
		List<String> employeeIDs = Arrays.asList(request.getParameterValues("employee"));
		String department = request.getParameter("department");
		String groupName = request.getParameter("groupname");

		try {

			if (!employeeIDs.isEmpty() && groupName != null && department != null) {
				// Check if there is a group named groupName; create if not.
				if (DatabaseAccess.groupExists(groupName)) {
					Group newGroup = new Group(groupName);
					DatabaseAccess.insertGroup(newGroup);
					// Employee update loop
					for (String employeeID : employeeIDs) {
						if (employeeID.trim().length() > 1) {
							DatabaseAccess.updateEmployeeGroupByName(Integer.parseInt(employeeID), groupName);
						}
					}
				} else {
					request.setAttribute("message", "There is already a group with that name");
				}
			}else {

			if (request.getParameter("department") != null) {
				departmentId = Integer.parseInt(department);
				request.setAttribute("empMessage", department);
				ArrayList<Employee> employeeList = DatabaseAccess.selectEmployeesByDepartment(departmentId);

				request.setAttribute("employees", employeeList);
			}

			else {
				ArrayList<Department> departmentList = DatabaseAccess.selectDepartments();

				if (!departmentList.isEmpty()) {
					request.setAttribute("departments", departmentList);
					request.setAttribute("message", "not empty");
				} else {
					request.setAttribute("message", "empty list");
				}

			}
			
			request.setAttribute("empMessage", department);
			
			}
			
			request.getRequestDispatcher("/group/group_entry.jsp").forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import classes.Department;
import classes.Employee;
import classes.Group;
import utilities.DatabaseAccess;

/**
 * Servlet implementation class ViewGroupsHandler
 */
@WebServlet(name = "ViewGroup", urlPatterns = { "/ViewGroup" })
public class ViewGroupsHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewGroupsHandler() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ArrayList<Department> departmentList = DatabaseAccess.selectDepartments();
		String selectedDep = request.getParameter("dep");
		
		try {
			// Load with defaults on original GET request
			if (!departmentList.isEmpty() && selectedDep == null) 
			{
				request.setAttribute("departments", departmentList);
				
				ArrayList<Group> groupList = DatabaseAccess.selectGroupsByDepartment(1);
				request.setAttribute("groups", groupList);
			} 
			// reload with correct department on department selection change
			else if (!departmentList.isEmpty() && selectedDep != null) 
			{
				request.setAttribute("departments", departmentList);
				
				ArrayList<Group> groupList = DatabaseAccess
						.selectGroupsByDepartment(Integer.parseInt(selectedDep) + 1);
				
				request.setAttribute("groups", groupList);
				request.setAttribute("selected", selectedDep);
			} 
			else 
			{
				request.setAttribute("error", "empty list");
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		request.getRequestDispatcher("/group/view.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		String groupName = request.getParameter("group");	
		int depId = Integer.parseInt(request.getParameter("department"));
		Department department = DatabaseAccess.selectDepartmentById(depId);
		int groupId = DatabaseAccess.getGroupIdByGroupName(groupName);
		
		ArrayList<Employee> employeesList = DatabaseAccess.selectEmployeesByGroupId(groupId);
		session.setAttribute("employeesList", employeesList);
		session.setAttribute("depName", department.getDepartmentName());
		session.setAttribute("grpName", groupName);
		doGet(request, response);
		request.getRequestDispatcher("/group/view.jsp").forward(request, response);
		
		
		
	}

}

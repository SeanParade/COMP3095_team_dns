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
import utilities.DatabaseAccess;

/**
 * Servlet implementation class GroupHandler
 */
@WebServlet("/group/GroupHandler")
public class GroupHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GroupHandler() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	int departmentId;
		
		//HttpSession session = request.getSession(true);
		if(request.getParameter("department")!= null)
		{
			departmentId = Integer.parseInt(request.getParameter("department"));
			request.setAttribute("empMessage", request.getParameter("department"));
			ArrayList<Employee> employeeList = DatabaseAccess.selectEmployees(departmentId);
			
				request.setAttribute("employees", employeeList);
		}
			
		else{ArrayList<Department> departmentList = DatabaseAccess.selectDepartments();
		
			if(!departmentList.isEmpty())
			{
				request.setAttribute("departments", departmentList);
				request.setAttribute("message", "not empty");
			}
			else
			{
				request.setAttribute("message", "empty list");
			}
	
		}
		request.setAttribute("empMessage", request.getParameter("department"));
		request.getRequestDispatcher("/group/group_entry.jsp").forward(request, response);
		// TODO Auto-generated method stub
		String department = request.getParameter("department");
		String groupName = request.getParameter("groupname");
		String employee1 = request.getParameter("employee1");
		String empolyee2 = request.getParameter("employee2");
		String employee3 = request.getParameter("employee3");
		String employee4 = request.getParameter("employee4");
		String employee5 = request.getParameter("employee5");
		String employee6 = request.getParameter("employee6");
	}

}

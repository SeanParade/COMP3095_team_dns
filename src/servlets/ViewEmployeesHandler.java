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
 * Servlet implementation class ViewEmployeesHandler
 */
@WebServlet("/ViewEmployeesHandler")
public class ViewEmployeesHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ArrayList<Employee> employees;

    public ViewEmployeesHandler() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Department> departments = DatabaseAccess.selectDepartments();
		HttpSession session = request.getSession();
		session.setAttribute("action", "/COMP3095_TEAM_DNS/ViewEmployeesHandler");
		session.setAttribute("placeholder", "Select Department");
		session.setAttribute("departmentList", departments);
		request.getRequestDispatcher("/employee/view.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String department = request.getParameter("departments");
		try{
			int id = Integer.parseInt(department);
			employees = DatabaseAccess.selectEmployeesByDepartment(id);
			session.setAttribute("employeeList", employees);
		}
		catch(Exception e)
		{
			
		}
		session.setAttribute("selected", department);
		
		request.getRequestDispatcher("/employee/view.jsp").forward(request, response);
		
	}

}

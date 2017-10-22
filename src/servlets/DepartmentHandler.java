package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspWriter;

import classes.Department;
import utilities.DatabaseAccess;

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
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String departmentName = request.getParameter("depname");
		String departmentLocation = request.getParameter("deploc");
		
		Department dep = new Department(departmentName, departmentLocation);
		
		String result = DatabaseAccess.insertDepartment(dep);
		
		request.setAttribute("result", result);
		request.getRequestDispatcher("/department/department_entry.jsp").forward(request, response);
	}

}

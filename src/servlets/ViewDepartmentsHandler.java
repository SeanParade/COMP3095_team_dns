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
import utilities.DatabaseAccess;

/**
 * Servlet implementation class ViewDepartmentsHandler
 */
@WebServlet("/ViewDepartmentsHandler")
public class ViewDepartmentsHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewDepartmentsHandler() {
        super();
       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ArrayList<Department> departments = DatabaseAccess.selectDepartments();
		HttpSession session = request.getSession();
		session.setAttribute("departmentList", departments);
		request.getRequestDispatcher("/department/view_departments.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

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

/************************************************************************
 * Project: COMP3095_team_dns
 * Assignment: Assignment #2
 * Authors: Sergio Santilli, Dylan Roberts, Nooran El-Sherif, Sean Price
 * Student Numbers: 100727526, 100695733, 101015020
 * Date: 03/01/2017
 * Description: ViewEmployeesHandler - Handles requests from 
 * employees/view.jsp
 ***********************************************************************/
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

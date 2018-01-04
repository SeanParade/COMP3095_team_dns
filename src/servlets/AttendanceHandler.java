package servlets;

/**********************************************************************
 * Project: COMP3095_team_dns
 * Assignment: Assignment #2
 * Authors: Dylan Roberts, Nooran El-Sherif, Sean Price
 * Student Numbers: 100727526, 100695733, 101015020
 * Date: 01/01/2018
 * Description: AttendanceHandler - handles requests to view attendance
 ***********************************************************************/

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


@WebServlet(name = "Attendance", urlPatterns = { "/attendance/Attendance" })
public class AttendanceHandler extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    public AttendanceHandler() {
        super();

    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		ArrayList<Department> departmentList = DatabaseAccess.selectDepartments();
		session.setAttribute("departments", departmentList);
		request.getRequestDispatcher("/attendance/enter_attendance.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		int depId = Integer.parseInt(request.getParameter("department"));
		ArrayList<Employee> employeesList = DatabaseAccess.selectEmployeesByDepartment(depId);
		session.setAttribute("employees", employeesList);
		request.getRequestDispatcher("/attendance/enter_attendance.jsp").forward(request, response);
	}

}

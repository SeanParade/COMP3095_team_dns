package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import classes.Attendance;
import classes.Department;
import classes.Employee;
import utilities.DatabaseAccess;

/************************************************************************
 * Project: COMP3095_team_dns
 * Assignment: Assignment #2
 * Authors:  Dylan Roberts, Nooran El-Sherif, Sean Price
 * Student Numbers: 100727526, 100695733, 101015020
 * Date: 02/01/2018
 * Description: ViewAttendanceHelper - gets employee attendance from 
 * database and forwards to jsp for viewing
 ***********************************************************************/
@WebServlet("/attendance/ViewAttendance")
public class ViewAttendanceHelper extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public ViewAttendanceHelper() {
        super();
        
    }

		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//displays departments in a drop down list
		HttpSession session = request.getSession();
		ArrayList<Department> departmentList = DatabaseAccess.selectDepartments();
		session.setAttribute("departments", departmentList);
		request.getRequestDispatcher("/attendance/view.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//Grabs all employees from the selected department in the dropdown. Grabs all unique dates in the attendance table
		//Grabs all the employee and date information in the attendance table. For each Attendance object the employee's information
		//(like first name and last name) is pulled. The attendance objects create Keys for a TreeMap and the Values are the Employees
		//personal information
		TreeMap<Attendance, Employee> attendanceMap = new TreeMap<>();
		HttpSession session = request.getSession();
		ArrayList<Department> departmentList = DatabaseAccess.selectDepartments();
		int depId = Integer.parseInt(request.getParameter("department"));
		
		ArrayList<java.sql.Date> dateList = DatabaseAccess.selectUniqueDates();
		ArrayList<Employee> employeesList = DatabaseAccess.selectEmployeesByDepartment(depId);
		
		ArrayList<Attendance> attendanceList = DatabaseAccess.selectAllAttendance();
		for (Attendance attendance: attendanceList)
		{
			Employee employee  = DatabaseAccess.selectEmployeeById(attendance.getEmployeeId());
			
			attendanceMap.put(attendance, employee );
		
		}
		
		session.setAttribute("selectedDep", departmentList.get(depId-1));
		session.setAttribute("dates", dateList);
		session.setAttribute("map", attendanceMap);
		session.setAttribute("employees", employeesList);
		request.getRequestDispatcher("/attendance/view.jsp").forward(request, response);
	}

}

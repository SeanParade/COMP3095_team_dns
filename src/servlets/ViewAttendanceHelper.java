package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
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

/**
 * Servlet implementation class ViewAttendanceHelper
 */
@WebServlet("/attendance/ViewAttendance")
public class ViewAttendanceHelper extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewAttendanceHelper() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		ArrayList<Department> departmentList = DatabaseAccess.selectDepartments();
		session.setAttribute("departments", departmentList);
		request.getRequestDispatcher("/attendance/view.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		TreeMap<Attendance, Employee> attendanceMap = new TreeMap<>();
		HttpSession session = request.getSession();
		int depId = Integer.parseInt(request.getParameter("department"));
		
		ArrayList<java.sql.Date> dateList = DatabaseAccess.selectUniqueDates();
		ArrayList<Employee> employeesList = DatabaseAccess.selectEmployeesByDepartment(depId);
		
		ArrayList<Attendance> attendanceList = DatabaseAccess.selectAllAttendance();
		for (Attendance attendance: attendanceList)
		{
			Employee employee  = DatabaseAccess.selectEmployeeById(attendance.getEmployeeId());
			
			attendanceMap.put(attendance, employee );
		
		}
		session.setAttribute("dates", dateList);
		session.setAttribute("map", attendanceMap);
		session.setAttribute("employees", employeesList);
		request.getRequestDispatcher("/attendance/view.jsp").forward(request, response);
	}

}

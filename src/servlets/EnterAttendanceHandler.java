package servlets;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utilities.DatabaseAccess;

/**
 * Servlet implementation class EnterAttendanceHandler
 */
@WebServlet("/attendance/EnterAttendance")
public class EnterAttendanceHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EnterAttendanceHandler() {
        super();

    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Grabs selected employees and the date. Parses the date and inserts it into the attendance table along with employee information
		Date date = null;
		request.removeAttribute("error");
		String[] selectedEmployeeIds = request.getParameterValues("selected");
		String dateStr = request.getParameter("date");
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		try {
			date = sdf.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		//checks if there were no employees selected
		if (selectedEmployeeIds == null || selectedEmployeeIds.length == 0) {
			request.setAttribute("error", "Please choose an employee");
			request.getRequestDispatcher("enter_attendance.jsp").forward(request, response);
		}
		
		
		
		//checks if date was entered correctly
		if (date != null) {
		java.sql.Date sqlDate = new java.sql.Date(date.getTime());
		
		//inserting attendance into the table. redirect to confirmation page
		for(String employeeId:selectedEmployeeIds)
		{
			DatabaseAccess.insertEmployeeAttendance(Integer.parseInt(employeeId), sqlDate);
			request.setAttribute("table", "Employee attendance");
			request.setAttribute("action", "added");
			request.getRequestDispatcher("/confirmation.jsp").forward(request, response);
		}
			}else {
			request.setAttribute("error", "Please enter a valid date");
			request.getRequestDispatcher("enter_attendance.jsp").forward(request, response);;
		}	
		}
	}

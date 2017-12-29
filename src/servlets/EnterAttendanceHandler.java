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
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Date date = null;
		String[] selectedEmployeeIds = request.getParameterValues("selected");
		String dateStr = request.getParameter("date");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			date = sdf.parse(dateStr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		java.sql.Date sqlDate = new java.sql.Date(date.getTime());
		System.out.println(Arrays.toString(selectedEmployeeIds));
		
		for(String employeeId:selectedEmployeeIds)
		{
			DatabaseAccess.insertEmployeeAttendance(Integer.parseInt(employeeId), sqlDate);
			request.setAttribute("table", "Employee attendance");
			request.getRequestDispatcher("/confirmation.jsp").forward(request, response);
		}
	}

}

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

/************************************************************************
 * Project: COMP3095_team_dns
 * Assignment: Assignment #1
 * Authors: Sergio Santilli, Dylan Roberts, Nooran El-Sherif, Sean Price
 * Student Numbers: 100727526, 100695733, 101015020
 * Date: 30/12/2017
 * Description: ViewDepartmentsHandler - Handles requests from 
 * departments/view.jsp
 ***********************************************************************/
@WebServlet("/ViewDepartmentsHandler")
public class ViewDepartmentsHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public ViewDepartmentsHandler() {
        super();
       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//get list of departments from database
		ArrayList<Department> departments = DatabaseAccess.selectDepartments();
		
		//store list in session
		HttpSession session = request.getSession();
		session.setAttribute("departmentList", departments);
		
		//forward request back to jsp
		request.getRequestDispatcher("/department/view.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

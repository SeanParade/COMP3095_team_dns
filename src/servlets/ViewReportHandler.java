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
import classes.Group;
import classes.ReportTemplate;
import classes.Report;
import utilities.DatabaseAccess;
import utilities.HelperUtility;

/**
 * Servlet implementation class ViewReportHandler
 */
@WebServlet("/reports/ViewReport")
public class ViewReportHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public ViewReportHandler() {
        super();
    
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//first gets directed to this from view report link
		HttpSession session = request.getSession();
		System.out.println("GET");
		try {
			//get a list of templates to populate drop down box
			ArrayList<ReportTemplate> templates =
						DatabaseAccess.getAllReportTemplates();
			session.setAttribute("templates", templates);

			if(session.getAttribute("selectedTemplate")!=null)
			    session.removeAttribute("selectedTemplate");
			if(session.getAttribute("selectedDepartment")!=null)
			    session.removeAttribute("selectedDeparment");
			if(session.getAttribute("selectedReport")!=null)
			    session.removeAttribute("selectedReport");
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		//dispatch to view.jsp
		request.getRequestDispatcher("/reports/view.jsp").forward(request,response);
		

	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    
		//when dropdown selection changes = submits to here
	    System.out.println("POST");
		HttpSession session = request.getSession();
		
		//get the selected template from dropdown
		String selectedTemplate = request.getParameter("templateId");
		int templateId = 0;
		if(session.getAttribute("selectedTemplate")!=null)
		{
			templateId = (Integer)session.getAttribute("selectedTemplate");
		}
		else{
			
			try{
				templateId = Integer.parseInt(selectedTemplate);
				session.setAttribute("selectedTemplate", templateId);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		
		//check if selected department is populated
		String selectedDepartment = request.getParameter("departmentId");
		int departmentId =0;
		if(session.getAttribute("selectedDepartment")!=null)//if departmentID is saved in session
		{
			departmentId = (Integer)session.getAttribute("selectedDepartment");
		}
		else if(selectedDepartment!= null)
		{
			try{
			departmentId = Integer.parseInt(selectedDepartment);
			session.setAttribute("selectedDepartment", departmentId);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		else //populate department list for dropdown
		{
			
			Department department = new Department();
			try {
				//get the department from the template dept id
				ReportTemplate choiceTemplate = DatabaseAccess.getReportTemplateById(templateId);
				department = DatabaseAccess.selectDepartmentById(choiceTemplate.getDepartmentId());
				//add list to session
				session.setAttribute("department", department);
				//send back to the view.jsp
				request.getRequestDispatcher("/reports/view.jsp").forward(request, response);
				return;
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		}
		String selectedReport = request.getParameter("reportId");
		int reportId = 0;
		if(session.getAttribute("selectedReport")!=null)
		{
			reportId = (Integer)session.getAttribute("selectedReport");
			Report report;
			ReportTemplate template;
			Employee employee;
			Group group;
			try {
				report = DatabaseAccess.getReportById(reportId);
				if(report.getReportType().equals("employee"))
				{
					employee = DatabaseAccess.selectEmployeeById(report.getEmployeeId());
					session.setAttribute("employee", employee);
				}
				else
				{
					group = DatabaseAccess.getGroupById(report.getGroupId());
					session.setAttribute("group", group);
				}
				session.setAttribute("report", report);
			} catch (Exception e) {
				e.printStackTrace();
			}
			try
			{
				template = DatabaseAccess.getReportTemplateById(templateId);
				session.setAttribute("template", template);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		else if (selectedReport!= null)
		{
			try{
				reportId = Integer.parseInt(selectedReport);
				session.setAttribute("selectedReport", reportId);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		else{
			try
			{
				//populate dropdown of reports
				ArrayList<Report> reports = DatabaseAccess.getReportsByTemplateId(templateId);
				session.setAttribute("reports", reports);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		//forward to the view.jsp
		request.getRequestDispatcher("/reports/view.jsp").forward(request, response);
		
		
	}
}


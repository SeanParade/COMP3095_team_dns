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


/************************************************************************
 * Project: COMP3095_team_dns
 * Assignment: Assignment #2
 * Authors: Dylan Roberts, Nooran El-Sherif, Sean Price
 * Student Numbers: 100727526, 100695733, 101015020
 * Date: 02/01/2018
 * Description: ViewReportHandler - Queries database for templates,
 * department, report, sends info to reports/view.jsp
 ***********************************************************************/

@WebServlet("/reports/ViewReport")
public class ViewReportHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public ViewReportHandler() {
        super();
    
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//first gets directed to this from view report link
		try {
			//get a list of templates to populate drop down box
			ArrayList<ReportTemplate> templates =
						DatabaseAccess.getAllReportTemplates();
			request.getSession().setAttribute("templates", templates);

			//remove previous session attributes for this page
			if(request.getSession().getAttribute("selectedTemplate")!=null)
			    request.getSession().removeAttribute("selectedTemplate");
			if(request.getSession().getAttribute("selectedDepartment")!=null)
			    request.getSession().removeAttribute("selectedDepartment");
			if(request.getSession().getAttribute("selectedReport")!=null)
			    request.getSession().removeAttribute("selectedReport");
		
			//reset edit attribute to false
			request.getSession().setAttribute("edit", "false");
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		//forward to view
		request.getRequestDispatcher("/reports/view.jsp").forward(request,response);
		

	}
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //when dropdown selection changes = submits to here
        HttpSession session = request.getSession();
        
        //get the selected template from dropdown
        String selectedTemplate = request.getParameter("templateId");
        int templateId = 0;
        if(session.getAttribute("selectedTemplate")!=null) //selected template id has been saved in session
        {
            templateId = (Integer)session.getAttribute("selectedTemplate"); //set session attribute
        }
        else{ //template has been selected, not saved in session
            
            try{
                templateId = Integer.parseInt(selectedTemplate); //get template id
                session.setAttribute("selectedTemplate", templateId); //set session attribute
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
            departmentId = (Integer)session.getAttribute("selectedDepartment"); //get departmentId from session
        }
        else if(selectedDepartment!= null) //departmentId not saved in session
        {
            try{
            departmentId = Integer.parseInt(selectedDepartment); //get department id from dropdown
            session.setAttribute("selectedDepartment", departmentId); //set session attribute
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
                return; //don't go through anymore code below
                
            } catch (Exception e) {
                e.printStackTrace();
            }
        
        }
        //if it gets to here: template and department have been selected previously
        String selectedReport = request.getParameter("reportId"); //get selected report
		int reportId = 0;
		
		 if (selectedReport!= null) //if report is selected
		{
			try{
				reportId = Integer.parseInt(selectedReport);       //get reportId
				session.setAttribute("selectedReport", reportId); //save to session
				
				reportId = (Integer)session.getAttribute("selectedReport"); //get reportId
				
				//declare objects needed
				Report report;
				ReportTemplate template;
				Employee employee;
				Group group;
				
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
					
					
					 // Map report evaluations as attributes
		            session.setAttribute("section1Map", report.getS1Map());
		            session.setAttribute("section2Map", report.getS2Map());
		            session.setAttribute("section3Map", report.getS3Map());
		            
		            
		            
		            template = DatabaseAccess.getReportTemplateById(templateId);
		            
		            //map template criteria as attributes
		            session.setAttribute("section1MapT", template.getS1Map());
		            session.setAttribute("section2MapT", template.getS2Map());
		            session.setAttribute("section3MapT", template.getS3Map());
		            
					int maxEval = template.getEvaluation();
					session.setAttribute("template", template);
					session.setAttribute("maxEvaluation", maxEval);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		else //reports have not been populated
		{
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


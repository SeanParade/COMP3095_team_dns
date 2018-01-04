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
		try {
			//get a list of templates to populate drop down box
			ArrayList<ReportTemplate> templates =
						DatabaseAccess.getAllReportTemplates();
			request.getSession().setAttribute("templates", templates);

			if(request.getSession().getAttribute("selectedTemplate")!=null)
			    request.getSession().removeAttribute("selectedTemplate");
			if(request.getSession().getAttribute("selectedDepartment")!=null)
			    request.getSession().removeAttribute("selectedDepartment");
			if(request.getSession().getAttribute("selectedReport")!=null)
			    request.getSession().removeAttribute("selectedReport");
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		//dispatch to view.jsp
		request.getRequestDispatcher("/reports/view.jsp").forward(request,response);
		

	}
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //when dropdown selection changes = submits to here
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
		
		 if (selectedReport!= null)
		{
			try{
				reportId = Integer.parseInt(selectedReport);
				session.setAttribute("selectedReport", reportId);
				
				reportId = (Integer)session.getAttribute("selectedReport");
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
		            request.setAttribute("section1Map", report.getS1Map());
		            request.setAttribute("section2Map", report.getS2Map());
		            request.setAttribute("section3Map", report.getS3Map());
		            
		            
		            
		            template = DatabaseAccess.getReportTemplateById(templateId);
		            
		            //map template criteria as attributes
		            request.setAttribute("section1MapT", template.getS1Map());
		            request.setAttribute("section2MapT", template.getS2Map());
		            request.setAttribute("section3MapT", template.getS3Map());
		            
					int maxEval = template.getEvaluation();
					session.setAttribute("template", template);
					session.setAttribute("maxEvaluation", maxEval);
				} catch (Exception e) {
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


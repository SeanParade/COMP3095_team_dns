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
import utilities.DatabaseAccess;

/**
 * Servlet implementation class EnterReportHandler
 */
@WebServlet("/reports/EnterReport")
public class EnterReportHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EnterReportHandler() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	// checks for a template selection. if it doesn't exist, department id is pulled from the user object in the session and 
	// is used to get a list of templates matching that department id from the database. Then passes the user to template selection form.
	{   
		
		if(request.getParameter("template") == null) 
		{
		    try 
		    {
		        HttpSession session = request.getSession();
		        Employee user = (Employee) session.getAttribute("user");
                ArrayList<ReportTemplate> templates = 
                        DatabaseAccess.getReportTemplatesByDepId(user.getDepartmentId());
                request.setAttribute("reportTemplates", templates);
            } 
		    catch (Exception e) 
		    {
                e.printStackTrace();
            }
		}
		
		request.getRequestDispatcher("/reports/select_template.jsp").forward(request, response);		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 	
	{
	    String templateChoice = request.getParameter("template");
	    // if a report has not been selected, send user back to selection
	    if(templateChoice == null) {
		    doGet(request,response);  
	    }
	    else
	    {
	        try
	        {
	            int templateId = Integer.parseInt(templateChoice);
	            ReportTemplate selectedTemplate = DatabaseAccess.getReportTemplateById(templateId);
	            // Map criteria and evaluations as attributes
	            request.setAttribute("section1Map", selectedTemplate.getS1Map());
	            request.setAttribute("section2Map", selectedTemplate.getS2Map());
	            request.setAttribute("section3Map", selectedTemplate.getS3Map());
	            
	            // Template object as attribute
	            request.setAttribute("template", selectedTemplate);
	            // Department name as attribute
	            Department d = DatabaseAccess.selectDepartmentById(selectedTemplate.getDepartmentId());
	            request.setAttribute("departmentName", d.getDepartmentName());
	            // Employees of that department as list attribute
	            ArrayList<Employee> depEmployees = DatabaseAccess.selectEmployeesByDepartment(selectedTemplate.getDepartmentId());
	            request.setAttribute("employees", depEmployees);
	            // Groups of that department as attribute
	            ArrayList<Group> depGroups = DatabaseAccess.selectGroupsByDepartment(selectedTemplate.getDepartmentId());
	            request.setAttribute("groups", depGroups);	            
	            // maximum evaluation as attribute
	            request.setAttribute("evaluationMaximum", selectedTemplate.getEvaluation());
	                      
	            request.getRequestDispatcher("/reports/enter_report.jsp").forward(request, response);   
	        } catch(Exception e) {
	            e.getMessage();
	        }	        
	    }
	}    
}

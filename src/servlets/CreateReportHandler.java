package servlets;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import classes.Department;
import classes.ReportTemplate;
import utilities.DatabaseAccess;
import utilities.HelperUtility;

/**
 * Servlet implementation class CreateReportHandler
 */
@WebServlet("/reports/CreateReport")
public class CreateReportHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateReportHandler() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	/**
	 * Used by select_template jsp to populate available templates based on the users departmentId
	 */
	{
		ArrayList<Department> departmentList = DatabaseAccess.selectDepartments();
		
		try{ request.setAttribute("departments", departmentList); }
		catch (Exception e){ e.getMessage(); }
		
		request.getRequestDispatcher("/reports/create_report.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * Create Report:
	 * Check the form for empty required values. Takes the form data and uses it to populate a ReportTemplate
	 * object to be stored in the db. Criteria values are passed to a CSV generator that alternates each sections
	 * criteria and maximum evaluation so they may be stored in the server.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
	        throws ServletException, IOException 
	{
	   
	   ArrayList<Department> departmentList = DatabaseAccess.selectDepartments();
	        
	   try{ 
	       request.setAttribute("departments", departmentList); 
	   } catch (Exception e){
	       e.getMessage();
	   } 
	   
	   String errorMsg = "";
	    // Check if all fields were entered
	    errorMsg += HelperUtility.emptyFieldsCheck(new String[] {
	            "templateName", "departmentId", "sec1Title", "sec2Title",
	            "sec3Title", "s1criteria", "s2criteria", "s3criteria",
	    }, request);
	    
	    if(errorMsg.equals("")) {
	        try
	        {
	            String templateName = request.getParameter("templateName");
	            int departmentId = 0;

	            departmentId = Integer.parseInt(request.getParameter("departmentId"));

	            String sec1Title = request.getParameter("sec1Title");
	            String sec2Title = request.getParameter("sec2Title");
	            String sec3Title = request.getParameter("sec3Title");

	            String sec1Criteria = HelperUtility.parseTemplateCriteria(
	                    request.getParameterValues("s1criteria"), 
	                    request.getParameterValues("s1eval"));
	            String sec2Criteria = HelperUtility.parseTemplateCriteria(
	                    request.getParameterValues("s2criteria"), 
	                    request.getParameterValues("s2eval"));
	            String sec3Criteria = HelperUtility.parseTemplateCriteria(
	                    request.getParameterValues("s3criteria"), 
	                    request.getParameterValues("s3eval"));

	            // build report template object from form input and insert to db
	            ReportTemplate template = new ReportTemplate
	                    (templateName, departmentId, sec1Title, sec2Title, sec3Title,
	                            sec1Criteria, sec2Criteria, sec3Criteria);

	            DatabaseAccess.insertReportTemplate(template);
	            request.getRequestDispatcher("/reports/index.jsp").forward(request, response);
	            return;
	        } 
	        catch (Exception e) 
	        {
	            errorMsg = "Database Error. Please check that your information is valid and that a report template of that name"
	                    + "doesn't already exist for the selected department.";
	        }
	    }
	    else 
	    {
	        errorMsg = "All details, all Section Titles, and at least "
	                + "one criteria in each section is required.";
	    }
	    
        request.setAttribute("error", errorMsg);
        doGet(request,response);       
	}

}

package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.tribes.util.Arrays;

import classes.Department;
import classes.ReportTemplate;
import utilities.DatabaseAccess;

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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Department> departmentList = DatabaseAccess.selectDepartments();
		
		try{ request.setAttribute("departments", departmentList); }
		catch (Exception e){ e.getMessage(); }
		
		request.getRequestDispatcher("/reports/create_report.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * Create Report:
	 * 
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
	    String errorMsg = "";
	    String templateName = request.getParameter("templateName");
	    int departmentId = Integer.parseInt(request.getParameter("departmentId"));
	    String sec1Title = request.getParameter("sec1Title");
	    String sec2Title = request.getParameter("sec2Title");
	    String sec3Title = request.getParameter("sec3Title");
	    // create csv for criteria to store in database 
	    String sec1Criteria = String.join(",", request.getParameterValues("s1criteria"));
	    String sec2Criteria = String.join(",", request.getParameterValues("s2criteria"));
	    String sec3Criteria = String.join(",", request.getParameterValues("s3criteria"));
	    
	    boolean invalid = false;
	    // form validity rules
	    boolean[] rules = {
	            templateName.isEmpty(),
	            sec1Title.isEmpty(),
	            sec2Title.isEmpty(),
	            sec3Title.isEmpty(),
	            sec1Criteria.isEmpty(),
	            sec2Criteria.isEmpty(),
	            sec3Criteria.isEmpty()
	    };
	    // loop rules. if a rule is broken, set the invalid flag to true
	    for(boolean rule:rules) {
	        if(rule)
	            invalid = true;
	    }
	    
	    if(!invalid) {
	    // build report object from form input
	        try 
	        {
	            ReportTemplate template = new ReportTemplate
                    (templateName, departmentId, sec1Title, sec2Title, sec3Title,
                            sec1Criteria, sec2Criteria, sec3Criteria);
            
                DatabaseAccess.insertReportTemplate(template);
	        } catch (Exception e) {
	            errorMsg = "Database Error. Please check that your information is valid.";
	            e.printStackTrace();
        }
	    }else {
	        errorMsg = "All details, all Section Titles, and at least "
	                + "one criteria in each section is required.";
	        request.setAttribute("error", errorMsg);
	    }
	    if(errorMsg.equals(""))
	        request.getRequestDispatcher("/reports/index.jsp").forward(request, response);    
	    else
	        request.getRequestDispatcher("/reports/create_report.jsp").forward(request, response);
	        
	}

}

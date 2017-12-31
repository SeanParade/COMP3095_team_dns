package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import classes.Employee;
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
	    String templateChoice = request.getParameter("template");
		
		if(templateChoice == null) 
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
		    request.getRequestDispatcher("/reports/select_template.jsp").forward(request, response);
		} else 
		{
		    doPost(request,response);
		}
		
		
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
	            request.setAttribute("section1Map", 
	                    generateReportSection(selectedTemplate.getSec1Criteria()));
	            request.setAttribute("section2Map", 
	                    generateReportSection(selectedTemplate.getSec2Criteria()));
	            request.setAttribute("section3Map", 
	                    generateReportSection(selectedTemplate.getSec3Criteria()));
	            request.setAttribute("template", selectedTemplate);
	            request.getRequestDispatcher("/reports/enter_report.jsp").forward(request, response);
	            
	        } catch(Exception e)
	        {
	            e.getMessage();
	        }
	        
	    }
	}
	
   protected static Map<String,Integer> generateReportSection(String sectionCSV)
    // converts the csv for a section stored into the database to a map usable 
    // in generating the report form
    {
        Map<String,Integer> sectionMap = new TreeMap<String,Integer>();
        try 
        {
            String[] sectionArray = sectionCSV.split(",");

            for(int i = 0; i < sectionArray.length; i+=2) {
                sectionMap.put(sectionArray[i],
                        Integer.parseInt(sectionArray[i+1]));
            }
        }       
        catch(Exception e) {
            e.printStackTrace();
        }
        return sectionMap;      
    }
    
}

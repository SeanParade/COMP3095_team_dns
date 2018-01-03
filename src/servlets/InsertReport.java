package servlets;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import classes.Report;
import classes.ReportTemplate;
import utilities.DatabaseAccess;
import utilities.HelperUtility;

/**
 * Servlet implementation class InsertReport
 */
@WebServlet(description = "Servlet for entering report data into the database", urlPatterns = { "/reports/InsertReport" })
public class InsertReport extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertReport() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    response.sendRedirect("/reports/EnterReport");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String errorMsg = "";
	    // check fields for empty values
	    errorMsg += HelperUtility.emptyFieldsCheck(new String[] {"templateId", "reportTitle", "reportDate",
	            "departmentId", "reportType", "s1eval", "s2eval", "s3eval"}, request);
	    
	    if(errorMsg.equals("")) {
	        try
	        {
	            // inst template object using the id hidden in the enter report form
	            ReportTemplate template = DatabaseAccess.getReportTemplateById(Integer.parseInt(request.getParameter("templateId")));
                String sec1Criteria = HelperUtility.parseTemplateCriteria(
                        request.getParameterValues("s1criteria"), request.getParameterValues("s1eval"));
                String sec2Criteria = HelperUtility.parseTemplateCriteria(
                        request.getParameterValues("s2criteria"), request.getParameterValues("s2eval"));
                String sec3Criteria = HelperUtility.parseTemplateCriteria(
                        request.getParameterValues("s3criteria"), request.getParameterValues("s3eval"));
                SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
                Date rDate = (Date) formatter.parse(request.getParameter("reportDate"));
                Report rep = new Report(
                        template.getTemplateName(),
                        template.getDepartmentId(),
                        template.getSec1Title(),
                        template.getSec2Title(),
                        template.getSec3Title(),
                        sec1Criteria,
                        sec2Criteria,
                        sec3Criteria,
                        template.getId(),
                        request.getParameter("reportTitle"),
                        request.getParameter("reportType"),
                        template.getMaximumEvaluation(),
                        Integer.parseInt(request.getParameter("evaluationTotal")),
                        rDate );

	            
	            
	        }catch(Exception e) {
	            e.printStackTrace();
	        }
	    }
	    
	}

}

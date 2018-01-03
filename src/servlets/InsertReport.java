package servlets;

import java.io.IOException;
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
	    String servletDestination = "/reports/index.jsp";
	    // check fields for empty values
	    errorMsg += HelperUtility.emptyFieldsCheck(new String[] {"templateId", "reportTitle", "reportDate",
	            "departmentId", "reportType", "s1eval", "s2eval", "s3eval"}, request);
	    
	    if(errorMsg.equals("")) {
	        try
	        {
	            // inst template object using the id hidden in the enter report form
	            String templateId = request.getParameter("templateId");
	            ReportTemplate template = DatabaseAccess.getReportTemplateById(Integer.parseInt(templateId));
                request.setAttribute("template", templateId);
	            
	            String sec1Criteria = HelperUtility.parseTemplateCriteria(
                        request.getParameterValues("s1criteria"), request.getParameterValues("s1eval"));
                String sec2Criteria = HelperUtility.parseTemplateCriteria(
                        request.getParameterValues("s2criteria"), request.getParameterValues("s2eval"));
                String sec3Criteria = HelperUtility.parseTemplateCriteria(
                        request.getParameterValues("s3criteria"), request.getParameterValues("s3eval"));
                
                SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
                java.util.Date rDate = (java.util.Date) formatter.parse(request.getParameter("reportDate"));
                java.sql.Date sqlDate = new java.sql.Date(rDate.getTime());
                String reportTitle = request.getParameter("reportTitle");
                String reportType = request.getParameter("reportType");
                int evalTotal = Integer.parseInt(request.getParameter("evaluationTotal"));                
                
                Report rep = new Report(
                        template.getTemplateName(), template.getDepartmentId(),
                        template.getSec1Title(), template.getSec2Title(),
                        template.getSec3Title(), sec1Criteria,
                        sec2Criteria, sec3Criteria,
                        template.getId(), reportTitle,
                        reportType, template.getMaximumEvaluation(),
                        evalTotal, sqlDate );
                
                if(request.getParameter("s1comment") != null) {
                    rep.setComment1(request.getParameter("s1comment"));    
                }
                if(request.getParameter("s2comment") != null) {
                    rep.setComment2(request.getParameter("s2comment"));    
                }
                if(request.getParameter("s3comment") != null) {
                    rep.setComment3(request.getParameter("s3comment"));    
                }
                
                if(rep.getReportType().equals("group")) {
                    int groupId = Integer.parseInt(request.getParameter("groupId"));
                    rep.setGroupId(groupId);
                }
                if(rep.getReportType().equals("employee")) {
                    int employeeId = Integer.parseInt(request.getParameter("employeeId"));
                    rep.setEmployeeId(employeeId);
                }
                
                DatabaseAccess.insertReport(rep);
                
	        }catch(Exception e) {
	            e.printStackTrace();
	        }
	        }
	        else {
	            request.setAttribute("error", errorMsg);
	            servletDestination = "/reports/EnterReport";
	        }
	        
	    request.getRequestDispatcher(servletDestination).forward(request,response);
	}

}

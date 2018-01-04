package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import classes.Report;
import classes.ReportTemplate;
import utilities.DatabaseAccess;
import utilities.HelperUtility;
/************************************************************************
 * Project: COMP3095_team_dns
 * Assignment: Assignment #1
 * Authors: Sergio Santilli, Dylan Roberts, Nooran El-Sherif, Sean Price
 * Student Numbers: 100727526, 100695733, 101015020
 * Date: 20/11/2017
 * Description: EditReportHandler - Handles report update in database
 ***********************************************************************/

@WebServlet("/reports/EditReport")
public class EditReportHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public EditReportHandler() {
        super();
       
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String servletDestination = "/reports/view.jsp";
				
		HttpSession session = request.getSession();
		//get report from session
		Report report = (Report)session.getAttribute("report");
		//get template from session
		ReportTemplate template = (ReportTemplate)session.getAttribute("template");
		
		//get all edited values
		 String sec1Criteria = HelperUtility.parseTemplateCriteria(
                 request.getParameterValues("s1criteria"), request.getParameterValues("s1eval"));
         String sec2Criteria = HelperUtility.parseTemplateCriteria(
                 request.getParameterValues("s2criteria"), request.getParameterValues("s2eval"));
         String sec3Criteria = HelperUtility.parseTemplateCriteria(
                 request.getParameterValues("s3criteria"), request.getParameterValues("s3eval"));
         
         String comment1 = request.getParameter("comment1");
         String comment2 = request.getParameter("comment2");
         String comment3 = request.getParameter("comment3");
         
         int eval = Integer.parseInt(request.getParameter("evaluationTotal"));
		
         //update report object with new values
         report.setSec1Criteria(sec1Criteria);
         report.setSec2Criteria(sec2Criteria);
         report.setSec3Criteria(sec3Criteria);
         report.setComment1(comment1);
         report.setComment2(comment2);
         report.setComment3(comment3);
         report.setEvaluation(eval);
         
		//call database access and update report
         String message = DatabaseAccess.updateReport(report);
         
		//send to confirmation page
		if(message.contains("success"))
		{
			//redirect to success page
			request.setAttribute("table", "Report");
			request.setAttribute("action", "updated");
			servletDestination = "/confirmation.jsp";
		}
		else
		{
			//set error message
			request.setAttribute("error", "Unable to update report");
			
		}
		request.getRequestDispatcher(servletDestination).forward(request,response);
		
	}

}

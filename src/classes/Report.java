package classes;

import java.util.ArrayList;
import java.util.Date;

public class Report {
	
	private int reportId;
	private String template;
	private String title;
	private Date date;
	private String reportType;
	
	private int departmentId;
	private int employeeId;
	private int groupId;
	
	private ArrayList<ReportItem> items;
	private int totalEvaluation;
	
	//default constructor
	public Report()
	{
		
	}
	public Report(int reportId, String template, String title, Date date, String reportType,
			int departmentId, ArrayList<ReportItem> items)
	{
		this.reportId = reportId;
		this.template = template;
		this.title = title;
		this.date = date;
		this.reportType = reportType;
		this.departmentId = departmentId;
		this.items = items;
	}
	public Report(String template, String title, Date date, String reportType, 
			int departmentId, ArrayList<ReportItem> items)
	{
		this.template = template;
		this.title = title;
		this.date = date;
		this.reportType = reportType;
		this.departmentId = departmentId;
		this.items = items;
	}
	//getters and setters
	public int getReportId() {
		return reportId;
	}
	public void setReportId(int reportId) {
		this.reportId = reportId;
	}
	public String getTemplate() {
		return template;
	}
	public void setTemplate(String template) {
		this.template = template;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getReportType() {
		return reportType;
	}
	public void setReportType(String reportType) {
		this.reportType = reportType;
	}
	public int getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	public int getGroupId() {
		return groupId;
	}
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
	public ArrayList<ReportItem> getItems() {
		return items;
	}
	public void setItems(ArrayList<ReportItem> items) {
		this.items = items;
	}
	public int getTotalEvaluation() {
		return totalEvaluation;
	}
	public void setTotalEvaluation(int totalEvaluation) {
		this.totalEvaluation = totalEvaluation;
	}
	//calculate total evaluation
	public int calculateTotalEvaluation()
	{
		int total = 0;
		for(ReportItem item  : this.items)
		{
			total+= item.getEvaluation();
		}
		return total;
	}
	
}


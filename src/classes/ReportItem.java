package classes;

public class ReportItem {

	private int itemId;
	private String subTitle;
	private String description;
	private int evaluation;
	private int reportId;
	
	
	//default constructor
	public ReportItem() 
	{
	
	}
	//overloaded constructor
	public ReportItem(int itemId, String subTitle, String description, int evaluation, int reportId)
	{
		this.itemId = itemId;
		this.subTitle = subTitle;
		this.description = description;
		this.evaluation = evaluation;
		this.reportId = reportId;
	}
	//constructor without item id
	public ReportItem(String subTitle, String description, int evaluation, int reportId)
	{
		this.subTitle = subTitle;
		this.description = description;
		this.evaluation = evaluation;
		this.reportId = reportId;
	}
	//getters and setters
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public String getSubTitle() {
		return subTitle;
	}
	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getEvaluation() {
		return evaluation;
	}
	public void setEvaluation(int evaluation) {
		this.evaluation = evaluation;
	}
	public int getReportId() {
		return reportId;
	}
	public void setReportId(int reportId) {
		this.reportId = reportId;
	}
	
	
}

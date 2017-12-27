package classes;

public class ReportItem {

	private int itemId;
	private String sectionTitle;
	private String crit1;
	private String crit2;
	private String crit3;
	private String crit4;
	private String crit5;
	private int eval1;
	private int eval2;
	private int eval3;
	private int eval4;
	private int eval5;
	private String comment;
	private int reportId;
	
	
	//default constructor
	public ReportItem() 
	{
	
	}
	public String getSectionTitle() {
        return sectionTitle;
    }
    public void setSectionTitle(String sectionTitle) {
        this.sectionTitle = sectionTitle;
    }
    public String getCrit1() {
        return crit1;
    }
    public void setCrit1(String crit1) {
        this.crit1 = crit1;
    }
    public String getCrit2() {
        return crit2;
    }
    public void setCrit2(String crit2) {
        this.crit2 = crit2;
    }
    public String getCrit3() {
        return crit3;
    }
    public void setCrit3(String crit3) {
        this.crit3 = crit3;
    }
    public String getCrit4() {
        return crit4;
    }
    public void setCrit4(String crit4) {
        this.crit4 = crit4;
    }
    public String getCrit5() {
        return crit5;
    }
    public void setCrit5(String crit5) {
        this.crit5 = crit5;
    }
    public int getEval1() {
        return eval1;
    }
    public void setEval1(int eval1) {
        this.eval1 = eval1;
    }
    public int getEval2() {
        return eval2;
    }
    public void setEval2(int eval2) {
        this.eval2 = eval2;
    }
    public int getEval3() {
        return eval3;
    }
    public void setEval3(int eval3) {
        this.eval3 = eval3;
    }
    public int getEval4() {
        return eval4;
    }
    public void setEval4(int eval4) {
        this.eval4 = eval4;
    }
    public int getEval5() {
        return eval5;
    }
    public void setEval5(int eval5) {
        this.eval5 = eval5;
    }
    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }
    //getters and setters
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public int getReportId() {
		return reportId;
	}
	public void setReportId(int reportId) {
		this.reportId = reportId;
	}
	
	
}

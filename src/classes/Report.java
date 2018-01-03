package classes;

import java.sql.Date;

public class Report extends ReportTemplate {
    private int templateId;
    private int groupId = -1;
    private int employeeId = -1;
    private String reportTitle;
    private String reportType;
    private int evaluationMax;
    private int evaluation;
    private Date date;
    private String comment1 = "No comments given";
    private String comment2 = "No comments given";
    private String comment3 = "No comments given";
    
    public Report() {}

    public Report(String templateName, int departmentId, String sec1Title, String sec2Title, String sec3Title,
            String sec1Criteria, String sec2Criteria, String sec3Criteria, int templateId, String reportTitle, 
            String reportType, int evaluationMax, int evaluation, Date date) {
        super(templateName, departmentId, sec1Title, sec2Title, sec3Title, sec1Criteria, sec2Criteria, sec3Criteria);
        this.templateId = templateId;
        this.reportTitle = reportTitle;
        this.reportType = reportType;
        this.evaluation = evaluation;
        this.date = date;
    }
    
    public int getTemplateId() {
        return templateId;
    }

    public void setTemplateId(int templateId) {
        this.templateId = templateId;
    }

    public String getReportTitle() {
        return reportTitle;
    }

    public void setReportTitle(String reportTitle) {
        this.reportTitle = reportTitle;
    }

    public String getReportType() {
        return reportType;
    }

    public void setReportType(String reportType) {
        this.reportType = reportType;
    }

    public int getEvaluationMax() {
        return evaluationMax;
    }

    public void setEvaluationMax(int evaluationMax) {
        this.evaluationMax = evaluationMax;
    }

    public int getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(int evaluation) {
        this.evaluation = evaluation;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getComment1() {
        return comment1;
    }

    public void setComment1(String comment1) {
        this.comment1 = comment1;
    }

    public String getComment2() {
        return comment2;
    }

    public void setComment2(String comment2) {
        this.comment2 = comment2;
    }

    public String getComment3() {
        return comment3;
    }

    public void setComment3(String comment3) {
        this.comment3 = comment3;
    }
}

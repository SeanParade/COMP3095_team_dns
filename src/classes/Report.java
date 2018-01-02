package classes;

import java.sql.Date;

public class Report extends ReportTemplate {
    private String reportTitle;
    private String reportType;
    private int evaluationMax;
    private int evaluation;
    private Date date;
    
    public Report() {}

    public Report(String templateName, int departmentId, String sec1Title, String sec2Title, String sec3Title,
            String sec1Criteria, String sec2Criteria, String sec3Criteria, String reportTitle, 
            String reportType, int evaluationMax, int evaluation, Date date) {
        super(templateName, departmentId, sec1Title, sec2Title, sec3Title, sec1Criteria, sec2Criteria, sec3Criteria);
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
    
    
}

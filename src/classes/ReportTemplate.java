package classes;

public class ReportTemplate {
    private int id;
    private String templateName;
    private int departmentId;
    private String sec1Title;
    private String sec2Title;
    private String sec3Title;
    private String sec1Criteria;
    private String sec2Criteria;
    private String sec3Criteria;
    
    public ReportTemplate() {}
    
    public ReportTemplate(String templateName, int departmentId, String sec1Title, String sec2Title,
            String sec3Title, String sec1Criteria, String sec2Criteria, String sec3Criteria) {
        this.templateName = templateName;
        this.departmentId = departmentId;
        this.sec1Title = sec1Title;
        this.sec2Title = sec2Title;
        this.sec3Title = sec3Title;
        this.sec1Criteria = sec1Criteria;
        this.sec2Criteria = sec2Criteria;
        this.sec3Criteria = sec3Criteria;
    }
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getTemplateName() {
        return templateName;
    }
    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }
    public int getDepartmentId() {
        return departmentId;
    }
    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }
    public String getSec1Title() {
        return sec1Title;
    }
    public void setSec1Title(String sec1Title) {
        this.sec1Title = sec1Title;
    }
    public String getSec2Title() {
        return sec2Title;
    }
    public void setSec2Title(String sec2Title) {
        this.sec2Title = sec2Title;
    }
    public String getSec3Title() {
        return sec3Title;
    }
    public void setSec3Title(String sec3Title) {
        this.sec3Title = sec3Title;
    }
    public String getSec1Criteria() {
        return sec1Criteria;
    }
    public void setSec1Criteria(String sec1Criteria) {
        this.sec1Criteria = sec1Criteria;
    }
    public String getSec2Criteria() {
        return sec2Criteria;
    }
    public void setSec2Criteria(String sec2Criteria) {
        this.sec2Criteria = sec2Criteria;
    }
    public String getSec3Criteria() {
        return sec3Criteria;
    }
    public void setSec3Criteria(String sec3Criteria) {
        this.sec3Criteria = sec3Criteria;
    }
}

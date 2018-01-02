package classes;

import java.util.TreeMap;

public class ReportTemplate {
    private int id;
    private String templateName;
    private int departmentId;
    private String sec1Title;
    private String sec2Title;
    private String sec3Title;
    private String sec1CriteriaCSV;
    private String sec2CriteriaCSV;
    private String sec3CriteriaCSV;
    
    public ReportTemplate() {}
    
    public ReportTemplate(String templateName, int departmentId, String sec1Title, String sec2Title,
            String sec3Title, String sec1Criteria, String sec2Criteria, String sec3Criteria) {
        this.templateName = templateName;
        this.departmentId = departmentId;
        this.sec1Title = sec1Title;
        this.sec2Title = sec2Title;
        this.sec3Title = sec3Title;
        this.sec1CriteriaCSV = sec1Criteria;
        this.sec2CriteriaCSV = sec2Criteria;
        this.sec3CriteriaCSV = sec3Criteria;
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
        return sec1CriteriaCSV;
    }
    public void setSec1Criteria(String sec1Criteria) {
        this.sec1CriteriaCSV = sec1Criteria;
    }
    public String getSec2Criteria() {
        return sec2CriteriaCSV;
    }
    public void setSec2Criteria(String sec2Criteria) {
        this.sec2CriteriaCSV = sec2Criteria;
    }
    public String getSec3Criteria() {
        return sec3CriteriaCSV;
    }
    public void setSec3Criteria(String sec3Criteria) {
        this.sec3CriteriaCSV = sec3Criteria;
    }
    public int getMaximumEvaluation() {
        int sum = 0;
        try{
            String[][] criteria = { 
                    this.sec1CriteriaCSV.split(","), 
                    this.sec2CriteriaCSV.split(","),
                    this.sec3CriteriaCSV.split(",")};            
            
            for(int i = 0; i < criteria.length; i++) 
                for(int j = 0; j < criteria[i].length; j+=2) 
                    sum += Integer.parseInt(criteria[i][j+1]);
        }       
        catch(Exception e) {
            e.printStackTrace();
        }
        return sum;
    }    
    public TreeMap<String,Integer> getS1Map(){
        return generateReportSection(this.sec1CriteriaCSV);
    }
    public TreeMap<String,Integer> getS2Map(){
        return generateReportSection(this.sec2CriteriaCSV);
    }
    public TreeMap<String,Integer> getS3Map(){
        return generateReportSection(this.sec3CriteriaCSV);
    }
    
    
    protected TreeMap<String,Integer> generateReportSection(String sectionCSV)
    // converts the csv for a section stored into the database to a map usable 
    // in generating the report form and totals the max evaluation score a report may have
    {
        TreeMap<String,Integer> sectionMap = new TreeMap<String,Integer>();
        try {
            String[] sectionArray = sectionCSV.split(",");
            for(int i = 0; i < sectionArray.length; i+=2)
                sectionMap.put(sectionArray[i], Integer.parseInt(sectionArray[i+1]));
        }       
        catch(Exception e) {
            e.printStackTrace();
        }
        return sectionMap;      
    }
}

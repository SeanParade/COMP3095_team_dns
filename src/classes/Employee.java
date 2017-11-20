package classes;

/************************************************************************
 * Project: COMP3095_team_dns
 * Assignment: Assignment #1
 * Authors: Dylan Roberts, Nooran El-Sherif, Sean Price
 * Student Numbers: 100727526, 100695733, 101015020
 * Date: 20/11/2017
 * Description: Employee -  Class for the Employee object. Stores Employee information
 ***********************************************************************/
public class Employee {
	private int employeeId; //primary key
	private String firstName;
	private String lastName;
	private String email;
	private String hireYear;
	private String position;
	private int departmentId; //foreign key
	private int groupId; //foreign key
	
	//public constructor without userId
	public Employee(int eId, String first, String last, String email, String hireYear,
			String position){
		this.employeeId = eId;
		this.firstName = first;
		this.lastName = last;
		this.email = email;
		this.hireYear = hireYear;
		this.position = position;
		
	}
	//public constructor with departmentId
	public Employee(int eId, String first, String last, String email, String hireYear,
			String position, int departmentId)
	{
		this.employeeId = eId;
		this.firstName = first;
		this.lastName = last;
		this.email = email;
		this.hireYear = hireYear;
		this.position = position;
		this.departmentId = departmentId;

	}
	//public constructor with groupId
	public Employee(int eId, String first, String last, String email, String hireYear,
			String position, int departmentId, int groupId)
	{
		this.employeeId = eId;
		this.firstName = first;
		this.lastName = last;
		this.email = email;
		this.hireYear = hireYear;
		this.position = position;
		this.departmentId = departmentId;
		this.groupId = groupId;
	}
	//getters and setters
	//possibly delete setters
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getHireYear() {
		return hireYear;
	}
	public void setHireYear(String hireYear) {
		this.hireYear = hireYear;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public int getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}
	public int getGroupId(){
		return groupId;
	}
	public void setGroupId(int groupId){
		this.groupId = groupId;
	}
	@Override
	public String toString() {
		return firstName + " " + lastName;
	}
	
	
}

package classes;

import java.util.Random;

import org.apache.tomcat.util.codec.binary.Base64;

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
	private String role;
	private String username;
	private String password;
	private String token;
	private int departmentId; //foreign key
	private int groupId; //foreign key
	
	public Employee() {}
	
	//public constructor without userId
	public Employee(int eId, String first, String last, String email, String hireYear,
			String position){
		this.employeeId = eId;
		this.firstName = first;
		this.lastName = last;
		this.email = email;
		this.hireYear = hireYear;
		this.role = position;
		
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
		this.role = position;
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
		this.role = position;
		this.departmentId = departmentId;
		this.groupId = groupId;
	}
	
	//public constructor with userid - for select from database
	public Employee(int id, String first, String last, String email,
			String role, String username, String password)
	{
		this.employeeId = id;
		this.firstName = first;
		this.lastName = last;
		this.email = email;
		this.role = role;
		this.username = username;
		this.password= password;
	}
	
	public Employee(int id, String first, String last, String email, String role,
			String username, String password, String token) {
		this.employeeId = id;
		this.firstName = first;
		this.lastName = last;
		this.email = email;
		this.role = role;
		this.username = username;
		this.password = password;
		this.token = token;
	}
	
	public Employee(String first, String last, String email,
			String role, String username, String password, int employeeId)
	{
		this.firstName = first;
		this.lastName = last;
		this.email = email;
		this.role = role;
		this.username = username;
		this.password= password;
		this.employeeId = employeeId;
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
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void createToken() 
	{
		Random r = new Random();
		byte[] a = new byte[32];
		r.nextBytes(a);
		
		this.token = Base64.encodeBase64String(a);		
    }
	public void setToken(String token) {
		this.token = token;
	}
	public String getToken() {
		return this.token;
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

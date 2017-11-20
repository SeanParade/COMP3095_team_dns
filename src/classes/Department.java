package classes;

/************************************************************************
 * Project: COMP3095_team_dns
 * Assignment: Assignment #1
 * Authors: Dylan Roberts, Nooran El-Sherif, Sean Price
 * Student Numbers: 100727526, 100695733, 101015020
 * Date: 20/11/2017
 * Description: Department - Class for the Department object, holds Department ID, Name, and Location.
 ***********************************************************************/
public class Department {
	private int departmentId; //primary key
	private String departmentName;
	private String departmentLocation;
	
	public String getDepartmentLocation() {
		return departmentLocation;
	}
	public void setDepartmentLocation(String departmentLocation) {
		this.departmentLocation = departmentLocation;
	}
	public int getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	//public constructor for insert into database
	public Department(String departmentName, String location)
	{
		this.departmentName = departmentName;
		this.departmentLocation = location;
	}
	//public constructor for select from database
	public Department(int id, String name, String location)
	{
		this.departmentId = id;
		this.departmentName = name;
		this.departmentLocation = location;
	}

	@Override
	public String toString() {
		return departmentName;
	}
	

}

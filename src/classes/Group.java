package classes;
/************************************************************************
 * Project: COMP3095_team_dns
 * Assignment: Assignment #1
 * Authors: Dylan Roberts, Nooran El-Sherif, Sean Price
 * Student Numbers: 100727526, 100695733, 101015020
 * Date: 20/11/2017
 * Description: Group - Class for Group object. Stores Group information.
 ***********************************************************************/
public class Group {
	private int groupId; //primary key
	private String groupName;
	private int departmentId;
	
	public Group(){}
	
	public Group(int groupId, String groupName, int departmentId)
	{
		this.groupId = groupId;
		this.groupName = groupName;
		this.departmentId = departmentId;
	}
	public Group(String groupName, int departmentId)
	{
		this.groupName = groupName;
		this.departmentId = departmentId;
	}
	public int getGroupId() {
		return groupId;
	}
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	@Override
	public String toString() {
		return groupName;
	}
	public int getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}

}

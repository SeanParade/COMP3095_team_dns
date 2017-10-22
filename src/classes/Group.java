package classes;

public class Group {
	private int groupId; //primary key
	private String groupName;
	private int departmentId; //foreign key
	
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
	public int getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}
	@Override
	public String toString() {
		return groupName;
	}
	
	
}

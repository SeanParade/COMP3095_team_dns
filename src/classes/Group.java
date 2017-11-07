package classes;

public class Group {
	private int groupId; //primary key
	private String groupName;
	
	
	public Group(int groupId, String groupName)
	{
		this.groupId = groupId;
		this.groupName = groupName;
	}
	public Group(String groupName)
	{
		this.groupName = groupName;
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
	
	
}

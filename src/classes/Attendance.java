package classes;

import java.util.Date;

public class Attendance {

	private int attendanceId;
	private Date date;
	private int employeeId;
	private boolean present;
	
	//default constructor
	public Attendance(){}
	
	//overloaded constructor
	public Attendance(int attendanceId, Date date, int employeeId, boolean present)
	{
		this.attendanceId = attendanceId;
		this.date = date;
		this.employeeId = employeeId;
		this.present = present;
	}
	//constructor without attendance id
	public Attendance(Date date, int employeeId, boolean present)
	{
		this.date = date;
		this.employeeId = employeeId;
		this.present = present;
	}

	//getters and setters
	public int getAttendanceId() {
		return attendanceId;
	}

	public void setAttendanceId(int attendanceId) {
		this.attendanceId = attendanceId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public boolean isPresent() {
		return present;
	}

	public void setPresent(boolean present) {
		this.present = present;
	}
	
	
}

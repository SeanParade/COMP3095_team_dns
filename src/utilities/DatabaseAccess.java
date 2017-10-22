package utilities;

/****************************************************************************************************
* Description: DatabaseAccess - Example provides access to database
****************************************************************************************************/
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;

import classes.Department;
import classes.Employee;
import classes.Group;
import classes.User;

import java.sql.*;

	
public class DatabaseAccess {
	
	private static String username = "admin";
	private static String password = "admin";
	private static String database = "COMP3095";
	
	private static Connection conn;
	private static PreparedStatement stmt = null;
	private static String sql;
	private static ResultSet rs;

	  private static Connection connect = null;
	  
	  //connect to database
	  public static Connection connectDataBase() throws Exception {
	    try {
	      //register the jdbc driver
	      Class.forName("com.mysql.jdbc.Driver");
	      //open the connection
	      connect = DriverManager
		          .getConnection("jdbc:mysql://localhost:3306/"+database+"?"
		              + "user="+username+"&password="+password);
	      return connect;
	    } catch (Exception e) {
	      throw e;
	    } 
	  }
	  //insert into department into database
	  public static String insertDepartment(Department dep)
	  {
		  
		  sql = "INSERT INTO DEPARTMENT(departmentName, location)" +
					"VALUES( ?, ?);";
		  

		  try {
			conn = connectDataBase();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, dep.getDepartmentName());
			stmt.setString(2,dep.getDepartmentLocation());
			
			stmt.execute();
			
			conn.close();
			return "success";
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return "failed: " +  e.getMessage();
		}
		  
	  }
	  //insert employee into database
	  public static String insertEmployee(Employee emp)
	  {
		  
		  sql = "INSERT INTO EMPLOYEE(id, firstName, lastName, email, hireYear, position)"
		  		+ "VALUES(?,?,?,?,?,?);";
		  try {
				conn = connectDataBase();
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, emp.getEmployeeId());
				stmt.setString(2, emp.getFirstName());
				stmt.setString(3, emp.getLastName());
				stmt.setString(4,  emp.getEmail());
				stmt.setString(5, emp.getHireYear());
				stmt.setString(6, emp.getPosition());
				
				stmt.execute();
				
				conn.close();
				return "success";
				
				
				
			} catch (Exception e) {
				//for testing - make more user friendly
				return "failed: " +  e.getMessage();
			}
	  }
	  //insert group into database
	  public static String insertGroup(Group group)
	  {
		  sql = "INSERT INTO EGROUP(groupName, departmentId) VALUES(?,?);";
		  
		  try{
			  conn = connectDataBase();
			  stmt = conn.prepareStatement(sql);
			  stmt.setString(1, group.getGroupName());
			  stmt.setInt(2,  group.getDepartmentId());
			  
			  stmt.execute();
			  conn.close();
			  return "success";
			  
		  }
		  catch(Exception e)
		  {
			  //change to be more user friendly
			  return "failed: " + e.getMessage();
		  }
	  }
	  //select user with username from database
	  public static User selectUser(String userName)
	  {
		  User user = new User();
		  sql = "SELECT * FROM USER WHERE username = ?;";
		  
		  try{
			  conn = connectDataBase();
			  stmt = conn.prepareStatement(sql);
			  stmt.setString(1, userName);
			  
			  rs = stmt.executeQuery();
			  
			  while(rs.next())
			  {
				  int userId = rs.getInt("id");
				  String first = rs.getString("firstName");
				  String last = rs.getString("lastName");
				  String email = rs.getString("email");
				  String password = rs.getString("password");
				  
				  user.setUserId(userId);
				  user.setFirstName(first);
				  user.setLastName(last);
				  user.setEmail(email);
				  user.setUsername(userName);
				  user.setPassword(password);
			  }
			  conn.close();
			  return user;	  
		  }
		  catch(Exception e)
		  {
			  return user;
		  }
	  }
	  //select from employee where department id = value
	  public static ArrayList<Employee> selectEmployees(int departmentId)
	  {
		  ArrayList<Employee> emps = new ArrayList<Employee>();
		  sql = "SELECT * FROM EMPLOYEE WHERE departmentId = ?;";
		  try{
			  conn = connectDataBase();
			  stmt = conn.prepareStatement(sql);
			  stmt.setInt(1, departmentId);
			  
			  rs = stmt.executeQuery();
			  
			  while(rs.next())
			  {
				  int employeeId = rs.getInt("id");
				  String first = rs.getString("firstName");
				  String last = rs.getString("lastName");
				  String email = rs.getString("email");
				  String hireYear = rs.getString("hireYear");
				  String position = rs.getString("position");
				  
				  Employee emp = new Employee(employeeId, first, last, email,
						  hireYear, position, departmentId);
				  
				  emps.add(emp);
						  
			  }
			  
			  conn.close();
			  return emps;
			 
			  }
		  catch(Exception e)
		  {
			  return emps;
		  }
		  
	  }
	 //select all departments from database
	 public static ArrayList<Department> selectDepartments()
	 {
		 ArrayList<Department> deps = new ArrayList<Department>();
		 
		 sql = "SELECT * FROM DEPARTMENT;";
		 
		try{
			conn = connectDataBase();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			
			while(rs.next())
			{
				int departmentId = rs.getInt("id");
				String departmentName = rs.getString("departmentName");
				String location = rs.getString("location");
				
				Department dep = new Department(departmentId, departmentName, location);
				deps.add(dep);
			}
			
			conn.close();
			return deps;
			
		}
		catch(Exception e)
		{
			//return empty arraylist
			return deps;
		}
	 }
	 //update employee with groupId
	 public static String updateEmployee(int employeeId, int groupId)
	 {
		 sql = "UPDATE EMPLOYEE SET groupId = ? WHERE id = ?;";
		 try{
			 conn = connectDataBase();
			 stmt = conn.prepareStatement(sql);
			 stmt.setInt(1, groupId);
			 stmt.setInt(2, employeeId);
			 stmt.execute();
			 
			 conn.close();
			 
			 return "success message here";
		 }
		 catch(Exception e)
		 {
			 //replace this with something nicer
			 return "failed " + e.getMessage();
		 }
		 
	 }
}

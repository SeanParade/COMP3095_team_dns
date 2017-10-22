package utilities;

/****************************************************************************************************
* Description: DatabaseAccess - Example provides access to database
****************************************************************************************************/
import java.sql.Connection;
import java.sql.DriverManager;

import classes.Department;
import classes.Employee;

import java.sql.*;



public class DatabaseAccess {
	
	private static String username = "admin";
	private static String password = "admin";
	private static String database = "COMP3095";

	  private static Connection connect = null;
	  
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
	  //insert into department
	  public static String insertDepartment(Department dep)
	  {
		  PreparedStatement insertDep = null;
		  
		  String sql = "INSERT INTO DEPARTMENT(departmentName, location)" +
					"VALUES( ?, ?);";
		  

		  try {
			Connection conn = connectDataBase();
			insertDep = conn.prepareStatement(sql);
			insertDep.setString(1, dep.getDepartmentName());
			insertDep.setString(2,dep.getDepartmentLocation());
			
			insertDep.execute();
			
			return "success";
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return "failed: " +  e.getMessage();
		}
		  
	  }
	  public static String insertEmployee(Employee emp)
	  {
		  PreparedStatement insertEmp = null;
		  
		  String sql = "INSERT INTO EMPLOYEE(id, firstName, lastName, email, hireYear, position)"
		  		+ "VALUES(?,?,?,?,?,?);";
		  try {
				Connection conn = connectDataBase();
				insertEmp = conn.prepareStatement(sql);
				insertEmp.setInt(1, emp.getEmployeeId());
				insertEmp.setString(2, emp.getFirstName());
				insertEmp.setString(3, emp.getLastName());
				insertEmp.setString(4,  emp.getEmail());
				insertEmp.setString(5, emp.getHireYear());
				insertEmp.setString(6, emp.getPosition());
				
				insertEmp.execute();
				
				return "success";
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				return "failed: " +  e.getMessage();
			}
	  }
	  //test statement
	 public static String selectUsers()
	 {
		 String sql = "SELECT * FROM USER;";
		 String result="";
				 
			 try {
				Connection conn = connectDataBase();
				Statement stmt = conn.createStatement();
				
				ResultSet rs = stmt.executeQuery(sql);
				ResultSetMetaData rsmd = rs.getMetaData();
				int columnsNumber = rsmd.getColumnCount();
				while(rs.next())
				{
					for(int i=0; i<= columnsNumber; i++)
					{
						String value = rs.getString(i);
						result += value + "\n";
					}
				}
				conn.close();
			
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				return e.getMessage();
			}
				return result;
		 
	 }
}

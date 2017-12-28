package utilities;

import java.util.ArrayList;

import classes.Department;
import classes.Employee;
import classes.Group;
import classes.Report;
import classes.ReportItem;
import classes.ReportTemplate;

import java.sql.*;

/************************************************************************
 * Project: COMP3095_team_dns
 * Assignment: Assignment #1
 * Authors: Sergio Santilli, Dylan Roberts, Nooran El-Sherif, Sean Price
 * Student Numbers: 100727526, 100695733, 101015020
 * Date: 20/11/2017
 * Description: DatabaseAccess - Provides access to database
 ***********************************************************************/

public class DatabaseAccess {

	private static String username = "admin";
	private static String password = "admin";
	private static String databaseName = "COMP3095";
	private static String databaseAddress = "jdbc:mysql://localhost:3306/";

	private static Connection conn;
	private static PreparedStatement stmt = null;
	private static String sql;
	private static ResultSet rs;
	private static Connection connect = null;

	public static Connection connectDatabase() throws Exception 
	/**
	 * Dynamically loads a mysql driver then uses databaseAddress, databaseName, username, and password. 
	 * Returns a connection object that various functions can be ran off of.
	 * 
	 * @return      Connection object
	 */
	{
		try {
			// register the jdbc driver
			Class.forName("com.mysql.jdbc.Driver");
			// open the connection
			connect = DriverManager.getConnection(
					databaseAddress + databaseName + "?" + "user=" + username + "&password=" + password);
			return connect;
		} catch (Exception e) {
			throw e;
		}
	}

	public static boolean userCredentialCheck(String usrName, String pass) 
	/**
	 * User Login credential check:
	 * Takes username and password and checks against the employee table for a
	 * row with match credentials. Returns true if the credentials match a employee
	 * in the table. Returns false if not.
	 * 
	 * @param  usrName  Username string
	 * @param  pass     Password string
	 * @return          Boolean based on whether or not a employee exists with
	 *                  that username and password.
	 */
	{
		boolean exists = false;
		try {
			conn = connectDatabase();
			sql = "SELECT * FROM Employee WHERE username = ? AND password = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, usrName.toLowerCase());
			stmt.setString(2, pass);
			rs = stmt.executeQuery();
			exists = rs.next();

			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return exists;
	}


	public static boolean updateAuthToken(Employee user) 
	/**
	 * Authentication Token Update:
	 * Takes a employee object to grab a username and an auth token. Updates the
	 * the token column of a employee row in the database based on the username. Returns
	 * a failure/success flag.
	 * 
	 * @param  user  Employee object used to get username and token attributes.
	 * @return       Success/Failure boolean.
	 */
	{
		boolean result;
		sql = "UPDATE Employee SET token = ? WHERE username = ?";
		try {
			conn = connectDatabase();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, user.getToken());
			stmt.setString(2, user.getUsername());
			result = stmt.execute();

			conn.close();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public static String insertDepartment(Department dep) 
	/**
	 * Inserts a new department into the department table of the database.
	 * 
	 * @param  dep  Department object containing all the column data for a department row
	 * @return      Success or Failed( + error message) string
	 */
	{
		sql = "INSERT INTO DEPARTMENT(departmentName, location)" + "VALUES( ?, ?);";
		try {
			conn = connectDatabase();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, dep.getDepartmentName());
			stmt.setString(2, dep.getDepartmentLocation());
			stmt.execute();

			conn.close();
			return "success";
		} catch (Exception e) {
			return "failed: " + e.getMessage();
		}
	}

	public static String insertEmployee(Employee emp) 
	/**
	 * Inserts a new employee into the employee table of the database.
	 * 
	 * @param  emp  Employee object containing all the column data for a employee row
	 * @return      Success or Failed( + error message) string
	 */
	{
		sql = "INSERT INTO EMPLOYEE(id, firstName, lastName, email, hireYear, role)" + "VALUES(?,?,?,?,?,?);";
		try {
			conn = connectDatabase();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, emp.getEmployeeId());
			stmt.setString(2, emp.getFirstName());
			stmt.setString(3, emp.getLastName());
			stmt.setString(4, emp.getEmail());
			stmt.setString(5, emp.getHireYear());
			stmt.setString(6, emp.getRole());
			stmt.execute();

			conn.close();
			return "success";
		} catch (Exception e) {
			return "failed: " + e.getMessage();
		}
	}

	public static String insertGroup(Group group) 
	/**
	 * Inserts a new group into the group table of the database.
	 * 
	 * @param  group  Group object containing all the column data for a group row
	 * @return        Success or Failed( + error message) string
	 */
	{
		sql = "INSERT INTO EGROUP(groupName, departmentId) VALUES(?, ?);";
		try {
			conn = connectDatabase();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, group.getGroupName());
			stmt.setInt(2,  group.getDepartmentId());
			stmt.execute();
			
			conn.close();
			return "success";
		} catch (Exception e) {
			return "failed: " + e.getMessage();
		}
	}
	
	public static String insertReportTemplate(ReportTemplate template) throws Exception
	{
	    sql = "INSERT INTO REPORT_TEMPLATE(templateName, departmentId, "
	            + "sec1_title, sec2_title, sec3_title, sec1_criteria, "
	            + "sec2_criteria, sec3_criteria) VALUES(?,?,?,?,?,?,?,?);";
	    try {
	        conn = connectDatabase();
	        stmt = conn.prepareStatement(sql);
	        stmt.setString(1, template.getTemplateName());
	        stmt.setInt(2, template.getDepartmentId());
	        stmt.setString(3, template.getSec1Title());
	        stmt.setString(4, template.getSec2Title());
	        stmt.setString(5, template.getSec3Title());
	        stmt.setString(6, template.getSec3Title());
	        stmt.setString(7, template.getSec3Title());
	        stmt.setString(8, template.getSec3Title());
	        stmt.execute();
	        
	        conn.close();
	        return "success";
	    }catch(Exception e) {
	        throw e;
	    }
	}
	
	public static ReportTemplate getReportTemplateByNameDepId(String templateName, int departmentId)
	throws Exception
	{
	    ReportTemplate template = new ReportTemplate();
	    sql = "SELECT * FROM REPORT_TEMPLATE WHERE templateName = ? AND departmentId = ?;";
	    try
	    {
	        conn = connectDatabase();
	        stmt = conn.prepareStatement(sql);
	        stmt.setString(1, templateName);
	        stmt.setInt(2, departmentId);
	        rs = stmt.executeQuery();
	        
	        while(rs.next()) 
	        {
	            template.setId(rs.getInt("id"));
	            template.setTemplateName(rs.getString("templateName"));
	            template.setDepartmentId(rs.getInt("departmentId"));
	            template.setSec1Title(rs.getString("sec1_title"));
	            template.setSec2Title(rs.getString("sec2_title"));
	            template.setSec3Title(rs.getString("sec3_title"));
	            template.setSec1Criteria(rs.getString("sec1_criteria"));
	            template.setSec2Criteria(rs.getString("sec2_criteria"));
	            template.setSec3Criteria(rs.getString("sec3_criteria"));
	        }
	        conn.close();
	        return template;
	    }
	    catch(Exception e) { throw e; }	    
	}
	
    public static ArrayList<String> getReportTemplatesByDepId(int departmentId)
    throws Exception
    {
        ArrayList<String> templateNames = new ArrayList<>();
        sql = "SELECT templateName FROM REPORT_TEMPLATE WHERE departmentId = ?;";
        
        try
        {
            conn = connectDatabase();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, departmentId);
            rs = stmt.executeQuery();
            
            while(rs.next()) 
            {
                String name = rs.getString("templateName");
                templateNames.add(name);
            }
            conn.close();
            return templateNames;
        }
        catch(Exception e) { throw e; }     
    }	
	
	
	/*
	public static int insertReport(Report report)
	{
		int generatedKey = 0;
		sql = "INSERT INTO REPORT(templateName, title, date, departmentId, groupId, employeeId, totalEvaluation)" +
	"VALUES (?, ?, ?, ?, ?, ?, ?);";
		
		try{
			conn = connectDatabase();
			stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, report.getTemplate());
			stmt.setString(2,  report.getTitle());
			stmt.setDate(3,  (Date) report.getDate());
			stmt.setInt(4,  report.getDepartmentId());
			if(report.getGroupId() == 0)
			{
				stmt.setNull(5, java.sql.Types.INTEGER);
			}
			else
			{
				stmt.setInt(5,  report.getGroupId());
			}
			if(report.getEmployeeId()==0)
			{
				stmt.setNull(6, java.sql.Types.INTEGER);
			}
			else
			{
				stmt.setInt(6, report.getEmployeeId());
			}
			stmt.setInt(7,  report.getTotalEvaluation());
			
			stmt.execute();
	
			ResultSet rs = stmt.getGeneratedKeys();
			if(rs.next())
			{
				generatedKey = rs.getInt(1);
			}
			
			conn.close();
			return generatedKey;
		}
		catch(Exception e)
		{
			return generatedKey;
		}
	
	}
	public static String insertReportItems(ArrayList<ReportItem> reportItems)
	{
		sql = "INSERT INTO REPORT_ITEM(subTitle, evaluation, description, reportId)" +
	"VALUES(?, ?, ?, ?);";
		try
		{
			conn = connectDatabase();
			stmt = conn.prepareStatement(sql);
			
			for(ReportItem item : reportItems)
			{
				if(item.getSubTitle() == null)
				{
					stmt.setNull(1, java.sql.Types.VARCHAR);
				}
				else
				{
					stmt.setString(1, item.getSubTitle());
				}
				stmt.setInt(2, item.getEvaluation());
				if(item.getDescription() == null)
				{
					stmt.setNull(3,  java.sql.Types.VARCHAR);
				}
				else
				{
					stmt.setString(3,  item.getDescription());
				}
				stmt.setInt(4, item.getReportId());
				
				stmt.execute();	
			}
			conn.close();
			return "success";
		}
		catch(Exception e)
		{
			return "failed " + e.getMessage();
		}
	}
	
	   public static String updateReportItem(ReportItem item)
    {
        
        sql = "UPDATE report_item SET evaluation= ?, description=? WHERE ID= ?;";
        
        try
        {
            conn = connectDatabase();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1,  item.getEvaluation());
            stmt.setString(2, item.getDescription());
            stmt.setInt(3, item.getReportId());
            
            stmt.execute();
            conn.close();
            
            return "success";
        }
        catch(Exception e)
        {
            return "failed " + e.getMessage();
        }
    }*/
	public static Employee getUser(String userName, String password) 
	/**
	 * Populates a employee object with column data from a row matching the given
	 * username and password. If no row matches the given credentials, an empty 
	 * employee object is returned.
	 * 
	 * @param  username  Username string.
	 * @param  password  Password string.
	 * @return           Fully populated employee object if matching credentials
	 *                   exists. Empty employee object if not.
	 */
	{
		Employee user = new Employee();
		sql = "SELECT * FROM Employee WHERE username = ? AND password = ?;";
		try {
			conn = connectDatabase();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, userName);
			stmt.setString(2, password);
			rs = stmt.executeQuery();

			while (rs.next()) {
				int userId = rs.getInt("id");
				String first = rs.getString("firstName");
				String last = rs.getString("lastName");
				String email = rs.getString("email");

				user.setEmployeeId(userId);
				user.setFirstName(first);
				user.setLastName(last);
				user.setEmail(email);
				user.setUsername(userName);
				user.setPassword(password);
			}
			conn.close();
			return user;
		} catch (Exception e) {
			return user;
		}
	}
	
	public static Employee getUserByToken(String token) 
	/**
	 * Populates a employee object with column data from a row matching the given
	 * auth token. If no row matches the given token, an empty employee object
	 * is returned. A password is not returned for security reasons 
	 * as no password is given.
	 * 
	 * @param  token  32-byte base64-encoded authorization token.
	 * @return        Partially populated employee object (no password) if matching credentials
	 *                exists. Empty employee object if not.
	 */
	{
		Employee user = new Employee();
		sql = "SELECT * FROM Employee WHERE token = ?;";
		try {
			conn = connectDatabase();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, token);			
			rs = stmt.executeQuery();
			
			// Instantiate employee
			while (rs.next()) {
				int userId = rs.getInt("id");
				String first = rs.getString("firstName");
				String last = rs.getString("lastName");
				String email = rs.getString("email");
				String userName = rs.getString("username");
				user.setEmployeeId(userId);
				user.setFirstName(first);
				user.setLastName(last);
				user.setEmail(email);
				user.setUsername(userName);
				user.setPassword("");
				user.setToken(token);
			}
			conn.close();
			return user;
		} catch (Exception e) {
			return user;
		}
	}
	
	
	public static ArrayList<Employee> selectEmployeesByDepartment(int departmentId) 
	/**
	 * Fetches all the employees in a given department and returns an array of 
	 * employee objects instantiated from employee rows in the database that
	 * have a matching department id
	 * 
	 * @param  departmentId  Unique department identification number
	 * @return               Collection of employee objects that belong to a 
	 *                       given department.
	 */
	{
		ArrayList<Employee> emps = new ArrayList<Employee>();
		sql = "SELECT * FROM EMPLOYEE WHERE departmentId = ?;";
		try {
			conn = connectDatabase();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, departmentId);
			rs = stmt.executeQuery();

			// Instantiate employee and add to emps arraylist
			while (rs.next()) {
				int employeeId = rs.getInt("id");
				String first = rs.getString("firstName");
				String last = rs.getString("lastName");
				String email = rs.getString("email");
				String hireYear = rs.getString("hireYear");
				String role = rs.getString("role");

				Employee emp = new Employee(employeeId, first, last, email, hireYear, role, departmentId);
				emps.add(emp);
			}
			conn.close();
			return emps;
		} catch (Exception e) {
			return emps;
		}

	}

	public static ArrayList<Department> selectDepartments() 
	/**
	 * Fetches every department from the department table in the database.
	 * Then instantiates Department object with the department details and
	 * adds them to a collection
	 * 
	 *  @return      An array of department objects instantiated from all 
	 *               departments in the database.
	 */
	{
		ArrayList<Department> deps = new ArrayList<Department>();
		sql = "SELECT * FROM DEPARTMENT;";
		try {
			conn = connectDatabase();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();

			while (rs.next()) {
				int departmentId = rs.getInt("id");
				String departmentName = rs.getString("departmentName");
				String location = rs.getString("location");

				Department dep = new Department(departmentId, departmentName, location);
				deps.add(dep);
			}
			conn.close();
			return deps;
		} catch (Exception e) {
			return deps;
		}
	}

	public static Department selectDepartmentById(int id)
	{
		Department department = new Department();
		sql = "SELECT * FROM DEPARTMENT WHERE id = ?;";
		try {
			conn = connectDatabase();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			while (rs.next()) {
				int depId = rs.getInt("id");
				String depName = rs.getString("departmentName");
				String location = rs.getString("location");
				department.setDepartmentId(depId);
				department.setDepartmentName(depName);
				department.setDepartmentLocation(location);
			}
			conn.close();
			return department;
		}catch (Exception e) {
			return department;
		}

		
	}
	/**
	 * Fetches all the groups in a given department and returns an array of 
	 * employee objects instantiated from group rows in the database that
	 * have a matching department id
	 * 
	 * @param  departmentId  Unique department identification number
	 * @return               Collection of group objects that belong to a 
	 *                       given department.
	 */
	
	public static ArrayList<Group> selectGroupsByDepartment(int departmentId) 
	{
		ArrayList<Group> groups = new ArrayList<Group>();
		sql = "SELECT * FROM EGROUP WHERE departmentId = ?;";
		try {
			conn = connectDatabase();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, departmentId);
			rs = stmt.executeQuery();

			// Instantiate employee and add to emps arraylist
			while (rs.next()) {
				int groupId = rs.getInt("id");
				String groupName = rs.getString("groupName");
				int depId = rs.getInt("departmentId");
				
				Group group  = new Group(groupId, groupName, depId);
				groups.add(group);
			}
			conn.close();
			return groups;
		} catch (Exception e) {
			return groups;
		}

	}
	
	public static int getGroupIdByGroupName(String groupName)
	{
		int groupId = 0;
		sql = "SELECT id FROM EGROUP WHERE groupName = ?;";
		try {
			conn = connectDatabase();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, groupName);
			rs = stmt.executeQuery();
			while (rs.next()) {
				groupId = rs.getInt("id");
			}
			conn.close();
			return groupId;
		}catch (Exception e) {
			return groupId;
		}
			
	}
	
	public static ArrayList<Employee> selectEmployeesByGroupId(int groupId)
	{
		ArrayList<Employee> emps = new ArrayList<Employee>();
		sql = "SELECT * FROM EMPLOYEE WHERE groupId = ?;";
		try {
			conn = connectDatabase();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, groupId);
			rs = stmt.executeQuery();

			// Instantiate employee and add to emps arraylist
			while (rs.next()) {
				int employeeId = rs.getInt("id");
				String first = rs.getString("firstName");
				String last = rs.getString("lastName");
				String email = rs.getString("email");
				String hireYear = rs.getString("hireYear");
				String role = rs.getString("role");

				Employee emp = new Employee(employeeId, first, last, email, hireYear, role);
				emps.add(emp);
			}
			conn.close();
			return emps;
		} catch (Exception e) {
			return emps;
		}
	}

	public static String updateEmployeeGroupById(int employeeId, int groupId) 
	/**
	 * Update an employee column with the passed group id.
	 * 
	 * @param  employeeId  Unique employee identification number
	 * @param  groupId     Unique group identification number.
	 * @return
	 */
	{
		sql = "UPDATE EMPLOYEE SET groupId = ? WHERE id = ?;";
		try {
			conn = connectDatabase();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, groupId);
			stmt.setInt(2, employeeId);
			stmt.execute();

			conn.close();
			return "success";
		} catch (Exception e) {
			return "failed " + e.getMessage();
		}
	}


	public static String updateEmployeeGroupByName(int employeeId, String groupName) throws SQLException 
	/**
	 * Fetches a group id from the groups table based on a passed group name,
	 * then updateEmployeeGroupById is called with the passed employee id and
	 * the group id that was fetched.
	 * 
	 * @param  employeeId  Integer that represents an employee id number
	 *                     used to update an employee's group column.
	 * @param  groupName   Group name used to lookup a groupid.
	 * @return             "success" or "failed" used for checking expressions.
	 */
	{
		int groupID;
		sql = "SELECT id FROM egroup WHERE groupName = ?;";
		try {
			conn = connectDatabase();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, groupName);
			rs = stmt.executeQuery();
			if (rs.next()) {
				groupID = rs.getInt(1);
				// if group exists update an employee's groupID
				if (groupID != 0) {
					updateEmployeeGroupById(employeeId, groupID);
					return "success";
				}
			}
		} catch (Exception e) {
			return "failed";
		} finally {
			conn.close();
		}
		return "failed";
	}

	public static boolean departmentExists(String value) 
	/**
	 * Department lookup to check if it exists in the database. Returns a boolean
	 * stating whether or not it does.
	 * 
	 * @param  value       String of the desired value the function is checking for
	 * @return             Boolean for whether or not the value exists in the
	 *                     database. 
	 */
	{
		sql = "SELECT * FROM department WHERE departmentName = ?;";
		try{
			conn = connectDatabase();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, value);
			rs = stmt.executeQuery();
			
			if(rs.next()){
				conn.close();
				return true;
			}
			conn.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}
	
	public static boolean groupExists(String value) 
	/**
	 * Group lookup by name to check if a group exists in the database. Returns a boolean
	 * stating whether or not it does.
	 * 
	 * @param  value       String of the desired value the function is checking for
	 * @return             Boolean for whether or not the value exists in the
	 *                     database. 
	 */
	{
		sql = "SELECT * FROM egroup WHERE groupName = ?;";
		try{
			conn = connectDatabase();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, value);
			rs = stmt.executeQuery();
			
			if(rs.next()){
				conn.close();
				return true;
			}
			conn.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}
	
	public static boolean tokenExists(String value) 
	/**
	 * Token lookup to check if it exists in the database. Returns a boolean
	 * stating whether or not it does.
	 * @param  value       String of the desired value the function is checking for
	 * @return             Boolean for whether or not the value exists in the
	 *                     database. 
	 */
	{
		sql = "SELECT * FROM Employee WHERE token = ?;";
		try{
			conn = connectDatabase();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, value);
			rs = stmt.executeQuery();
			
			if(rs.next()){
				conn.close();
				return true;
			}
			conn.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}
	
	public static boolean employeeExists(String firstName, String lastName) 
	/**
	 * Checks whether an employee with the given firstName and lastName
	 * exists in the employee table of the database. Returns a 
	 * boolean.
	 * 
	 * @param  firstName  Potential employee's first name string
	 * @param  lastName   Potential employee's last name string
	 * @return            Employee already exists boolean
	 */
	{
		sql = "SELECT * FROM employee WHERE firstName = ? AND lastName= ?;";
		try{
			conn = connectDatabase();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, firstName);
			stmt.setString(2, lastName);
			rs = stmt.executeQuery();
			
			if(rs.next()){
				conn.close();
				return true;
			}
			conn.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}
}

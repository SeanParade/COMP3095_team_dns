package utilities;

import java.util.ArrayList;

import classes.Department;
import classes.Employee;
import classes.Group;
import classes.User;

/****************************************************************************************************
* Description: DatabaseAccess - Example provides access to database
****************************************************************************************************/
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

	// connect to database
	public static Connection connectDataBase() throws Exception {
		try {
			// register the jdbc driver
			Class.forName("com.mysql.jdbc.Driver");
			// open the connection
			connect = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/" + database + "?" + "user=" + username + "&password=" + password);
			return connect;
		} catch (Exception e) {
			throw e;
		}
	}

	// User login
	// Return true/false based on if credentials exist
	public static boolean userCredentialCheck(String usrName, String pass) {
		boolean exists = false;
		try {
			conn = connectDataBase();
			sql = "SELECT * FROM user WHERE username = ? AND password = ?";
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

	// Update Token
	// insert a users auth token into the DB, return success flag
	public static boolean updateAuthToken(User user) {
		boolean result;
		sql = "UPDATE user SET token = ? WHERE username = ?";

		try {
			conn = connectDataBase();
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

	// insert into department into database
	public static String insertDepartment(Department dep) {
		sql = "INSERT INTO DEPARTMENT(departmentName, location)" + "VALUES( ?, ?);";

		try {
			conn = connectDataBase();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, dep.getDepartmentName());
			stmt.setString(2, dep.getDepartmentLocation());

			stmt.execute();

			conn.close();
			return "success";

		} catch (Exception e) {
			// TODO Auto-generated catch block
			return "failed: " + e.getMessage();
		}

	}

	// insert employee into database
	public static String insertEmployee(Employee emp) {

		sql = "INSERT INTO EMPLOYEE(id, firstName, lastName, email, hireYear, position)" + "VALUES(?,?,?,?,?,?);";
		try {
			conn = connectDataBase();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, emp.getEmployeeId());
			stmt.setString(2, emp.getFirstName());
			stmt.setString(3, emp.getLastName());
			stmt.setString(4, emp.getEmail());
			stmt.setString(5, emp.getHireYear());
			stmt.setString(6, emp.getPosition());

			stmt.execute();

			conn.close();
			return "success";

		} catch (Exception e) {
			// for testing - make more user friendly
			return "failed: " + e.getMessage();
		}
	}

	// insert group into database
	public static String insertGroup(Group group) {
		sql = "INSERT INTO EGROUP(groupName) VALUES(?);";

		try {
			conn = connectDataBase();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, group.getGroupName());

			stmt.execute();
			conn.close();
			return "success";

		} catch (Exception e) {
			// change to be more user friendly
			return "failed: " + e.getMessage();
		}
	}

	// select user by username from database
	public static User getUser(String userName, String password) {
		User user = new User();
		sql = "SELECT * FROM USER WHERE username = ? AND password = ?;";

		try {
			conn = connectDataBase();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, userName);
			stmt.setString(2, password);

			rs = stmt.executeQuery();

			while (rs.next()) {
				int userId = rs.getInt("id");
				String first = rs.getString("firstName");
				String last = rs.getString("lastName");
				String email = rs.getString("email");

				user.setUserId(userId);
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

	// select from employee where department id = value
	public static ArrayList<Employee> selectEmployeesByDepartment(int departmentId) {
		ArrayList<Employee> emps = new ArrayList<Employee>();
		sql = "SELECT * FROM EMPLOYEE WHERE departmentId = ?;";
		try {
			conn = connectDataBase();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, departmentId);

			rs = stmt.executeQuery();

			while (rs.next()) {
				int employeeId = rs.getInt("id");
				String first = rs.getString("firstName");
				String last = rs.getString("lastName");
				String email = rs.getString("email");
				String hireYear = rs.getString("hireYear");
				String position = rs.getString("position");

				Employee emp = new Employee(employeeId, first, last, email, hireYear, position, departmentId);

				emps.add(emp);
			}

			conn.close();
			return emps;

		} catch (Exception e) {
			return emps;
		}

	}

	// select all departments from database
	public static ArrayList<Department> selectDepartments() {
		ArrayList<Department> deps = new ArrayList<Department>();

		sql = "SELECT * FROM DEPARTMENT;";

		try {
			conn = connectDataBase();
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
			// return empty arraylist
			return deps;
		}
	}

	// update employee with groupId
	public static String updateEmployeeGroupById(int employeeId, int groupId) {
		sql = "UPDATE EMPLOYEE SET groupId = ? WHERE id = ?;";
		try {
			conn = connectDataBase();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, groupId);
			stmt.setInt(2, employeeId);
			stmt.execute();

			conn.close();

			return "Successful updating employee group";
		} catch (Exception e) {
			// replace this with something nicer
			return "failed " + e.getMessage();
		}

	}

	// update employee with group by group name

	public static String updateEmployeeGroupByName(int employeeId, String groupName) throws SQLException {

		int groupID;
		sql = "SELECT id FROM egroup WHERE groupName = ?";
		try {
			conn = connectDataBase();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, groupName);
			rs = stmt.executeQuery();
			if (rs.next()) {
				groupID = rs.getInt(1);
				// if group exists update an employee's groupID
				if (groupID != 0) {
					updateEmployeeGroupById(employeeId, groupID);
					return "Success";
				}
			}
		} catch (Exception e) {
			return "failed: " + e.getMessage();
		} finally {
			conn.close();
		}

		return "Something went wrong! Employee " + employeeId + " was not updated. ";
	}

	// Check to see if a group exists by a name and return true if it does; false if not
	public static boolean groupExists(String groupName) throws SQLException {
		sql = "SELECT * FROM egroup WHERE groupName = ?";
		try {
			conn = connectDataBase();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, groupName);
			rs = stmt.executeQuery();
			// if the result set has anything, return true
			if (rs.next()) {
				conn.close();
				return true;
			}
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}

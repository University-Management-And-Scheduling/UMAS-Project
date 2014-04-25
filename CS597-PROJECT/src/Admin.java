import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author Akshay
 * 
 */

/*************** ADMIN.JAVA CLASS WAS WRITTEN BY AKSHAY THIRKATEH ********************************/

public class Admin extends Employee {

	public @interface DBAnnotation {
		
		String[] variable() default "";
		String[] table() default "";
		String[] column() default "";
		boolean[] isSource() default false;
	
	}

	/* this constructor calls the employee class constructor by UIN */
	public Admin(int UIN) throws PersonDoesNotExistException {
		super(UIN);
		// TODO Auto-generated constructor stub
	}

	/*
	 * addAdmin function takes in the inputs of the name of the employee and the
	 * department object.
	 * 
	 * checks if the department object is not null
	 * 
	 * calls the add into the database which returns(int) the added UIN.
	 * 
	 * with the retrieved UIN, it is then added into the employee table.
	 * 
	 * if returns true then it is successfully added
	 * 
	 * else the function returns false
	 */
	public static boolean addAdmin(String name, Department dept)
			throws loginDetailsnotAdded {

		boolean isAdded = false;

		if (dept == null) {

			return false;
		}

		int addedUIN = Employee.addIntoDatabase(name, dept, 1);

		boolean isAddedtoEmp = Employee.addEmployee(addedUIN);

		if (isAddedtoEmp)
			isAdded = true;

		return isAdded;
	}

	/*
	 * getAllAdmin function returns an arraylist of the admins from the
	 * database.
	 * 
	 * It retrieves the data from the people table.
	 */
	public static ArrayList<Admin> getAllAdmin() {
		// if(Professor == null)
		// throw new NullPointerException();

		ArrayList<Admin> getAllAdmin = new ArrayList<Admin>();

		try {
			Connection conn = Database.getConnection();

			try {
				if (conn != null) {

					@DBAnnotation(variable = "retreivedAdminUIN", table = "people", column = "UIN", isSource = true)
					// Retrieve all the professors from one department
					String adminSelect = "Select *" + " FROM university.people"
							+ " WHERE PositionID=1";
					PreparedStatement statement = conn
							.prepareStatement(adminSelect);
					ResultSet rs = statement.executeQuery();

					while (rs.next()) {

						int retreivedAdminUIN = rs.getInt("UIN");
						// System.out.println(retreivedProfUserNames);
						Admin admins;
						try {
							admins = new Admin(retreivedAdminUIN);
							getAllAdmin.add(admins);
							System.out.println(admins.getUserName());
						} 
						catch (PersonDoesNotExistException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}

				}

			}

			catch (SQLException e) {
				System.out.println("Error fetching all the professors");
				System.out.println(e.getMessage());
				e.printStackTrace();

			}

			finally {
				// Database.commitTransaction(conn);
			}

			return getAllAdmin;
		}

		finally {
		}

	}

	/*
	 * updateAdminUserName function takes in the inputs of the new user name of
	 * the admin 
	 * calls the boolean function update username from the people class with the new passed new username and the initialized objects
	 * username.
	 * 
	 * if updated returns true
	 * 
	 * 
	 * else the function returns false
	 */
	public boolean updateAdminUserName(String userName) {

		boolean isUpdated = false;

		try {
			Connection conn = Database.getConnection();

			try {

				boolean ifAddedInLogin = People.updateUserNameIntoLoginTable(
						userName, this.getUserName());
				if (ifAddedInLogin)
					isUpdated = true;

			}

			catch (Exception e) {
				System.out.println("Error adding/updating to database");
				e.printStackTrace();
				System.out.println(e);
			}

		}

		catch (Exception e) {
			System.out.println("Connection failed");
			e.printStackTrace();
			System.out.println(e);

		}

		finally {

			// System.out.println("retrieved");
		}

		return isUpdated;

	}

	/*
	 * updateAdminName function takes in the inputs of the new name of the admin
	 * 
	 * calls the boolean function update name from the people class with the
	 * passed new name and the initialized objects UIN.
	 * 
	 * if updated returns true
	 * 
	 * 
	 * else the function returns false
	 */
	public boolean updateAdminName(String name) {

		boolean isUpdated = false;

		try {
			Connection conn = Database.getConnection();

			try {

				boolean ifUpdatedInLogin = People.updateNameIntoPeopleTable(
						name, this.getUIN());
				if (ifUpdatedInLogin)
					isUpdated = true;

			}

			catch (Exception e) {
				System.out.println("Error adding/updating to database");
				e.printStackTrace();
				System.out.println(e);
			}

		}

		catch (Exception e) {
			System.out.println("Connection failed");
			e.printStackTrace();
			System.out.println(e);

		}

		finally {

			// System.out.println("retrieved");
		}

		return isUpdated;

	}

	/*
	 * updateAdminDept function takes in the inputs of the newdept of the admin
	 * * calls the boolean function update dept from the people class with the
	 * passed new name and the initialized objects UIN.
	 * 
	 * if updated returns true
	 * 
	 * 
	 * else the function returns false
	 */

	public boolean updateAdminDept(int deptID) {

		boolean isUpdated = false;

		try {
			Connection conn = Database.getConnection();

			try {

				boolean ifUpdatedInPeople = People.updateDeptIntoPeopleTable(
						deptID, this.getUIN());
				if (ifUpdatedInPeople)
					isUpdated = true;

			}

			catch (Exception e) {
				System.out.println("Error adding/updating to database");
				e.printStackTrace();
				System.out.println(e);
			}

		}

		catch (Exception e) {
			System.out.println("Connection failed");
			e.printStackTrace();
			System.out.println(e);

		}

		finally {

			// System.out.println("retrieved");
		}

		return isUpdated;

	}

	/*
	 * All admin functions add, update, retrieve are specified in the this class
	 * 
	 * local main class is used for testing functions and specific executions
	 */

	public static void main(String[] args) {

		// getAllAdmin();

		// Admin ad = new Admin(1);
		//
		// ad.updateAdminDept(1);

	}

}

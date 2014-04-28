
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
	public static boolean addAdmin(String name, Department dept) throws loginDetailsnotAdded {

		boolean isAdded = false; //create a boolean value for returning. set it to false

		if (dept == null) {
			//if the dept object is null then return false
			return false;
		}

		int addedUIN = Employee.addIntoDatabase(name, dept, 1);//add the admin into the database with the name, dept and position ID

		boolean isAddedtoEmp = Employee.addEmployee(addedUIN);//returns true if added

		if (isAddedtoEmp)
			isAdded = true;//return true

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

		ArrayList<Admin> getAllAdmin = new ArrayList<Admin>();//declare a arraylist

		try {
			Connection conn = Database.getConnection();//establish a connection

			try {
				if (conn != null) {

					// Retrieve all the professors from one department
					String adminSelect = "Select *" + " FROM university.people"
							+ " WHERE PositionID=1";
					PreparedStatement statement = conn.prepareStatement(adminSelect);
					ResultSet rs = statement.executeQuery();//execute the query

					while (rs.next()) {
						
						DBAnnotation.annoate("retreivedAdminUIN","people","UIN",true);
						int retreivedAdminUIN = rs.getInt("UIN");//get the UIN and store it in a variable
						Admin admins;
						try {
							admins = new Admin(retreivedAdminUIN);//put the retrieved UIN and put it in the admin object
							getAllAdmin.add(admins);//add it to the arraylist
							
							DBAnnotation.annoate("adminUserName", "People", "Username", true);
							String adminUserName=admins.getUserName();	
							
							System.out.println(adminUserName);
							
						} 
						//catch the person does not exist exception
						catch (PersonDoesNotExistException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}

				}

			}
			//catch the SQl exception
			catch (SQLException e) {
				System.out.println("Error fetching all the professors");
				System.out.println(e.getMessage());
				e.printStackTrace();

			}
			/*The code thats placed in the finally block gets executed no matter what. But 
														here the finally block does not contain any general statements*/
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

		boolean isUpdated = false;//create a boolean value for returning. set it to false


		try {
			Connection conn = Database.getConnection();//establish the connection

			try {

				boolean ifAddedInLogin = People.updateUserNameIntoLoginTable(userName, this.getUserName());//update the username inthe login table
				if (ifAddedInLogin)
					isUpdated = true;//if updated set true

			}
			//catch block
			catch (Exception e) {
				System.out.println("Error adding/updating to database");
				e.printStackTrace();
				System.out.println(e);
			}

		}
		//catch block
		catch (Exception e) {
			System.out.println("Connection failed");
			e.printStackTrace();
			System.out.println(e);

		}
		/*The code thats placed in the finally block gets executed no matter what. But 
														here the finally block does not contain any general statements*/
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
			Connection conn = Database.getConnection();//create a boolean value for returning. set it to false

			try {

				boolean ifUpdatedInPeople = People.updateNameIntoPeopleTable(name, this.getUIN());//update name in the people table
				if (ifUpdatedInPeople)
					isUpdated = true;//if added then set true

			}
			//catch block for the exception
			catch (Exception e) {
				System.out.println("Error adding/updating to database");
				e.printStackTrace();
				System.out.println(e);
			}

		}
		//catch block
		catch (Exception e) {
			System.out.println("Connection failed");
			e.printStackTrace();
			System.out.println(e);

		}
		
		/*The code thats placed in the finally block gets executed no matter what. But 
													here the finally block does not contain any general statements*/
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

		boolean isUpdated = false;//create a boolean value for returning. set it to false


		try {
			Connection conn = Database.getConnection();//establish a connection

			try {

				boolean ifUpdatedInPeople = People.updateDeptIntoPeopleTable(deptID, this.getUIN());//update dept in people table
				if (ifUpdatedInPeople)
					isUpdated = true;//set the return type to true

			}
			//catch block
			catch (Exception e) {
				System.out.println("Error adding/updating to database");
				e.printStackTrace();
				System.out.println(e);
			}

		}
		//catch block
		catch (Exception e) {
			System.out.println("Connection failed");
			e.printStackTrace();
			System.out.println(e);

		}

		/*The code thats placed in the finally block gets executed no matter what. But 
														here the finally block does not contain any general statements*/
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


	}

}

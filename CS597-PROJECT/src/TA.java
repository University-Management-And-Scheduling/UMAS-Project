import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import com.mysql.jdbc.Statement;


/**
 * @author Akshay
 * 
 */

/*************** ADMIN.JAVA CLASS WAS WRITTEN BY AKSHAY THIRKATEH ********************************/

public class TA extends Student {
	
	@Target({ElementType.LOCAL_VARIABLE})
	@Retention(RetentionPolicy.RUNTIME)
	public @interface DBAnnotation {
	 String[] variable () default "";
	 String[] table () default "";
	 String[] column () default "";
	 boolean[] isSource () default false; 
	}

	public TA(int UIN) {
		super(UIN);
		// TODO Auto-generated constructor stub
	}

	/*
	 * addTA function takes in the inputs of the UIN of the TA and the
	 * offer ID of the course
	 * 
	 * Before adding into the TA table it needs to be added as an employee
	 * 
	 * Calls the addtoEmployee function which returns a boolean value
	 * 
	 * if the UIN is added then it adds into the TA table 
	 * 
	 * if returns true then it is successfully added
	 * 
	 * else the function returns false
	 */
	public static boolean addTAToEmployee(int UIN, int offerID)
			throws AlreadyExistsInEmployeeException {

		boolean isAdded = false;
		double salary = 40000.00;
		String office_address = "to be decided";
		String office_hours = "to be decided";

		try {
			Connection conn = Database.getConnection();

			try {

				boolean alreadyExists = addTAToEmployeeCheck(UIN, offerID);

				if (alreadyExists) {

					throw new AlreadyExistsInEmployeeException();

				}

				else {
					
					

					System.out.println("Adding new data into the database");
					
					@DBAnnotation (
							variable = {"UIN","salary","office_address","office_hours"}, table = "employee", 
							column = {"UIN","Salary","OfficeAddress","OfficeHours"}, 
							isSource = false)
					
					String SQLPeopleInsert = "Insert into employee (UIN, Salary, OfficeAddress, OfficeHours) Values (?,?,?,?);";
					
					PreparedStatement stmt = conn
							.prepareStatement(SQLPeopleInsert);
					stmt = conn.prepareStatement(SQLPeopleInsert);
					stmt.setInt(1, UIN);
					stmt.setDouble(2, salary);
					stmt.setString(3, office_address);
					stmt.setString(4, office_hours);
					System.out.println(stmt);
					int i = stmt.executeUpdate();
					System.out.println(i);
					System.out.println("Inserted");

					isAdded = addTAtoTAtable(UIN, offerID);

					if (isAdded)
						isAdded = true;
				}

			}

			catch (SQLException e) {
				System.out.println("Error adding/updating to database");
				e.printStackTrace();
				System.out.println(e);
			}

			catch (CourseOffered.CourseOfferingNotCurrentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			finally {
				// System.out.println("retrieved");
				// Database.closeConnection(conn);
			}
		}

		finally {

			// System.out.println("retrieved");
		}


		return isAdded;

	}

	/*This functions checks if the passed offer ID is a valid and current
	 * 
	 * if true it then checks if there exists an employee with the same UIN
	 * 
	 * if an employee exists it returns true
	 * 
	 * else it returns false
	 * 
	 * */
	public static boolean addTAToEmployeeCheck(int UIN, int offerID)
			throws CourseOffered.CourseOfferingNotCurrentException {

		boolean isExisting = false;

		CourseOffered offerIDcheck;
		boolean isCurrent = false;

		try {
			offerIDcheck = new CourseOffered(offerID);
			isCurrent = offerIDcheck.checkIfCurrent();
		} catch (Course.CourseDoesNotExistException
				| CourseOffered.CourseOfferingDoesNotExistException e1) {
			return false;
		}

		if (isCurrent) {
			try {
				Connection conn = Database.getConnection();
				String SQLPeopleSelect = "";

				try {

					SQLPeopleSelect = "Select UIN From employee where UIN=?;";
					PreparedStatement stmt = conn
							.prepareStatement(SQLPeopleSelect);
					stmt.setInt(1, UIN);
					ResultSet rs = stmt.executeQuery();
					System.out.println(stmt);

					if (rs.first()) {

						System.out.println(UIN + "already exists");
						return true;
					}

				}

				catch (SQLException e) {
					System.out.println("Error adding/updating to database");
					e.printStackTrace();
					System.out.println(e);
				}

				finally {
					// System.out.println("retrieved");
					// Database.closeConnection(conn);
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

		}

		else {
			throw new CourseOffered.CourseOfferingNotCurrentException();
		}

		return isExisting;

	}

	
	/*This functions checks if the passed offer ID is a valid and current
	 * 
	 * if true it then checks if there exists an employee with the same UIN
	 * 
	 * if an employee exists it returns true
	 * 
	 * if the addToTATableCheck function does not return true
	 * 
	 * then it adds it to the Ta table with the TaUIN and Offer ID
	 * 
	 * */	
	public static boolean addTAtoTAtable(int UIN, int offerID) {

		boolean isAdded = false;

		try {
			Connection conn = Database.getConnection();

			try {

				boolean ifExists = addTAtoTAtableCheck(UIN, offerID);

				if (ifExists) {
					return false;
					// Insert a update query to update the values of the
					// database....NOT ADD
				}

				else {

					System.out.println("Adding new data into the database");
					
					@DBAnnotation (
							variable = {"UIN","offerID"}, table = "teachingassistant", 
							column = {"TaUIN","OfferID"}, 
							isSource = false)
					
					String SQLPeopleInsert = "Insert into teachingassistant (TaUIN, OfferID) Values (?,?);";
					PreparedStatement stmt = conn
							.prepareStatement(SQLPeopleInsert);
					stmt.setInt(1, UIN);
					stmt.setInt(2, offerID);
					int i = stmt.executeUpdate();
					System.out.println(i);
					System.out.println("Inserted");
					isAdded = true;

					Database.commitTransaction(conn);

				}

			}

			catch (SQLException e) {
				System.out.println("Error adding/updating to database");
				e.printStackTrace();
				System.out.println(e);
			}

			finally {
				// System.out.println("retrieved");
				// Database.closeConnection(conn);
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

		return isAdded;

	}

	
	/*This functions checks if the passed offer ID is a valid and current
	 * 
	 * if true it then checks if there exists an TA with the same UIN
	 * 
	 * if an TA exists it returns true
	 * 
	 * else it returns false
	 * 
	 * */	
	public static boolean addTAtoTAtableCheck(int UIN, int offerID) {

		boolean isExisting = false;

		try {
			Connection conn = Database.getConnection();
			String SQLPeopleSelect = "";

			try {

				SQLPeopleSelect = "Select TaUIN From teachingassistant where OfferID=?;";
				PreparedStatement stmt = conn.prepareStatement(SQLPeopleSelect);
				stmt.setInt(1, UIN);
				ResultSet rs = stmt.executeQuery();

				if (rs.first()) {
					System.out.println(UIN
							+ "already exists as a TA for the offer ID: "
							+ offerID);
					return true;
				}

			}

			catch (SQLException e) {
				System.out.println("Error adding/updating to database");
				e.printStackTrace();
				System.out.println(e);
			}

			finally {
				// System.out.println("retrieved");
				// Database.closeConnection(conn);
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

		return isExisting;

	}

	/*
	 * getAllAdmin function returns an arraylist of the admins from the
	 * database.
	 * 
	 * It retrieves the data from the people table.
	 */
	public static ArrayList<TA> getAllTAs() {


		ArrayList<TA> getAllTAs = new ArrayList<TA>();

		try {
			Connection conn = Database.getConnection();

			try {
				if (conn != null) {

					// Retrieve all the professors from one department
					String ProfessorSelect = "Select *"
							+ " FROM university.people" + " WHERE PositionID=4";
					PreparedStatement statement = conn
							.prepareStatement(ProfessorSelect);
					ResultSet rs = statement.executeQuery();

					while (rs.next()) {

						int retreivedTAUIN = rs.getInt("UIN");
						// System.out.println(retreivedProfUserNames);
						TA teachingAssistant = new TA(retreivedTAUIN);
						getAllTAs.add(teachingAssistant);
						System.out.println(teachingAssistant.getUserName());
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

		}

		finally {
		}

		return getAllTAs;

	}

	/*
	 * updateTAUserName function takes in the inputs of the new user name of
	 * the TA
	 * calls the boolean function update username from the people class with the new passed new username and the initialized objects
	 * username.
	 * 
	 * if updated returns true
	 * 
	 * 
	 * else the function returns false
	 */
	public boolean updateTAUserName(String userName) {

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
	 * updateTAName function takes in the inputs of the new name of the TA
	 * 
	 * calls the boolean function update name from the people class with the
	 * passed new name and the initialized objects UIN.
	 * 
	 * if updated returns true
	 * 
	 * 
	 * else the function returns false
	 */

	public boolean updateTAName(String name) {

		boolean isUpdated = false;

		try {
			Connection conn = Database.getConnection();

			try {

				boolean ifAddedInPeople = People.updateNameIntoPeopleTable(
						name, this.getUIN());
				if (ifAddedInPeople)
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
	 * updateTADept function takes in the inputs of the newdept of the TA
	 * * calls the boolean function update dept from the people class with the
	 * passed new name and the initialized objects UIN.
	 * 
	 * if updated returns true
	 * 
	 * 
	 * else the function returns false
	 */

	public boolean updateTADept(int deptID) {

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

	static class AlreadyExistsInEmployeeException extends Exception {
		private static final long serialVersionUID = 1L;
		private String message = null;

		public AlreadyExistsInEmployeeException() {
			super();
			this.message = "Employee not Added ";
		}

		public AlreadyExistsInEmployeeException(String message) {
			super();
			this.message = message;
		}

		@Override
		public String toString() {
			return message;
		}

		@Override
		public String getMessage() {
			return message;
		}
	}

	/*
	 * All TA functions add, update, retrieve are specified in the this class
	 * 
	 * local main class is used for testing functions and specific executions
	 */
	public static void main(String[] args) {


//		TA ta = new TA(4);
//
//		ta.updateTADept(16);

	}

}

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author Akshay
 * 
 */

/*************** PROFESSOR.JAVA CLASS WAS WRITTEN BY AKSHAY THIRKATEH ********************************/

public class Professor extends Employee {

	// prof constructor takes in name, username and dept ID
	public Professor(String name, String userName, int deptID) {
		super(name, userName, deptID, 2);
	}

	//this is a professor class constructor which passes the UIN and initializes the details
	public Professor(int UIN) throws Student.AccessDeniedException, PersonDoesNotExistException {
		super(UIN);//calls the super class
		boolean check = checkIfProfessor(UIN);//checking if the UIN is a professor 

		if (!check) {
			throw new Student.AccessDeniedException();//if its not a professor then throw an exception
		}

	}
	/*calls the professor constructor with the username*/
	public Professor(String userName) {
		super(userName);
	}

	/*prof adding to the database
	 * 
	 * pass the name and the dept to the function
	 * 
	 * return type is boolean
	 * 
	 * */
	public static boolean addProfToDb(String name, Department dept) {

		boolean isAdded = false;
		int returnedUIN;

		if (dept == null) {
			throw new NullPointerException();//if the object is null then throw an exception
		}

		if (name.equals("")) {
			throw new NullPointerException();//if the name is empty then throw an exception
		}

		try {
			returnedUIN = addIntoDatabase(name, dept, 2);//add into the database
			if (returnedUIN != -1) {//check the returned UIN

				System.out.println(returnedUIN);

				isAdded = Employee.addEmployee(returnedUIN);//add to employee

				Connection conn = Database.getConnection();
				Database.commitTransaction(conn);//commit
			}
		}
		//catch
		catch (People.loginDetailsnotAdded e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return isAdded;

	}

	// CHECKING IF THE PERSON WHOS UIN IS INPUT IS A PROFESSOR OR NOT
	public static boolean checkIfProfessor(int UIN) {
		boolean ifProfessor = false;
		try {
			Connection conn = Database.getConnection();//connection
			String SQLPeopleSelect = "";
			try {

				if (conn != null) {

					SQLPeopleSelect = "Select PositionID From People where UIN=?;";//write query

				}

				PreparedStatement stmtForSelect = conn.prepareStatement(SQLPeopleSelect);
				stmtForSelect.setInt(1, UIN);//set the values

				ResultSet rs = stmtForSelect.executeQuery();//execute the query

				if (rs.first()) {
					//if the resultset exists
					DBAnnotation.annoate("peopleRetrievedPositionID", "people", "PositionID", true);
					int peopleRetrievedPositionID = rs.getInt("PositionID");
					
					System.out.println("UIN:" + UIN + " Position ID:"+ peopleRetrievedPositionID);

					if (peopleRetrievedPositionID == 2) {//check the position ID
						System.out.println("Professor UIN exists");
						ifProfessor = true;
					}

					else {
						System.out.println("UIN exists, but it is not a professor");
						ifProfessor = false;

					}

				}

				else {
					System.out.println("UIN does not exist");
					ifProfessor = false;

				}

			}
			//catch block
			catch (SQLException e) {
				System.out.println(e);
				return ifProfessor;
			}

		}
		//catch blcok
		catch (Exception e) {
			System.out.println(e);

		}
		/*The code thats placed in the finally block gets executed no matter what. But 
		here the finally block does not contain any general statements*/
		finally {
		}

		return ifProfessor;
	}
	/*this function mainly checks if the username beongs to the professor or not*/
	public static boolean checkIfProfessor(String userName) {

		try {
			Connection conn = Database.getConnection();// get the connection
			String SQLProfSelect = "";
			try {

				if (conn != null) {

					SQLProfSelect = "Select PositionID From People where Username=?;";//write the query
				}

				PreparedStatement stmtForSelect = conn.prepareStatement(SQLProfSelect);
				stmtForSelect.setString(1, userName);//set the values

				ResultSet rs = stmtForSelect.executeQuery();//execute the query

				if (rs.first()) {
					
					DBAnnotation.annoate("peopleRetrievedPositionID", "people", "PositionID", true);
					int peopleRetrievedPositionID = rs.getInt("PositionID");//retreive the position ID
					
					System.out.println("Username:" + userName + " Position ID:"+ peopleRetrievedPositionID);
					/*
					 * Checking if the position ID id of a professor i.e 2,
					 * UIN exists for students professors, admins TA and
					 * virtually every person existing in the university Check
					 * if the position ID of the passed UIN is of a professor.
					 */

					if (peopleRetrievedPositionID == 2) {
						return true;
					} else {
						return false;
					}

				}

				else {

					System.out.println("username does not exist");
					return false;

				}

			}
			//catch block 
			catch (SQLException e) {
				System.out.println(e);

			}

		}

		catch (Exception e) {
			System.out.println(e);

		}
		/*The code thats placed in the finally block gets executed no matter what. But 
														here the finally block does not contain any general statements*/
		finally {

			// System.out.println("retrieved");
		}

		return false;
	}

	
	// retrieval of the details of the professor by UIN
	public static Professor retrieveProfDetailsByUIN(int UIN) {

		boolean check = checkIfProfessor(UIN);//checking if the UIN is a professor

		if (check == true) {
			// retireveDetailsByUIN(12);
			Professor professor;//set the class object
			try {
				professor = new Professor(UIN);//send the UIN to the constructor
				return professor;
			} catch (PersonDoesNotExistException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Student.AccessDeniedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		else {
			System.out.println("There exists no professor with that UIN");
			return null;
		}

		return null;
	}

	// retrieve the details of the professor when a username is passed
	public static Professor retrieveProfDetailsByUserName(String userName) {

		boolean check = checkIfProfessor(userName);//check if its a professor

		if (check == true) {
			Professor professor = new Professor(userName);//set it as a object
			// System.out.println(professor.getUIN());
			return professor;

		} else {
			System.out.println("There exists no professor with that username");
			return null;
		}

	}

	// Delete a professor by passing a uin
	public static void deleteProfFromDbUsingUIN(int UIN) {

		boolean check = checkIfProfessor(UIN);//CHECK IF ITS A PROFESSOR

		if (check == true) {
			deleteFromDatabaseByUIN(UIN);//delete from database in the people table
			Employee.deleteFromDatabaseByUIN(UIN);//delete from the employee table too

			Connection conn = Database.getConnection();
			Database.commitTransaction(conn);
		} else {
			System.out.println("There exists no professor with that UIN");
		}

	}

	// prof deletion by username
	public static boolean deleteProfFromDbUsingUserName(String userName) {

		boolean isDeleted = false;

		boolean check = checkIfProfessor(userName);//CHECK IF ITS A PROFESSOR

		if (check == true) {
			deleteFromDatabaseByUserName(userName);//delete from database in the people table
			isDeleted = Employee.deleteFromDatabaseByUserName(userName);//delete from the employee table too
		} else {
			System.out.println("There exists no professor with that username");
		}

		return isDeleted;
	}

	public String toString() {

		return getUIN() + " " + getUserName() + " " + getName() + " "
				+ getDeptID() + " " + getPositionID();

	}
	
	/*getting all the professors in a department when passed a dept ID*/
	public static ArrayList<Professor> getAllProfInADept(int departmentID)
			throws ProfessorDoesNotExistException {
		// if(Professor == null)
		// throw new NullPointerException();

		ArrayList<Professor> ProfOfOneDept = new ArrayList<Professor>();//initialize the prof arraylist

		try {
			Connection conn = Database.getConnection();//get the connection

			try {
				if (conn != null) {

					Department dept = new Department(departmentID);//get the dept object

					// Retrieve all the professors from one department
					String ProfessorSelect = "Select *"
							+ " FROM people"
							+ " WHERE DepartmentID= ? and PositionID=2";
					PreparedStatement statement = conn.prepareStatement(ProfessorSelect);
					statement.setInt(1, departmentID);//set the dept ID
					ResultSet rs = statement.executeQuery();

					while (rs.next()) {

						DBAnnotation.annoate("retreivedProfUserNames", "people", "Username", true);
						String retreivedProfUserNames = rs.getString("Username");
						
						Professor prof = new Professor(retreivedProfUserNames);
						ProfOfOneDept.add(prof);//add it to the arraylist
						System.out.println(prof.toString());
					}

				}

			}
			//catch block
			catch (SQLException e) {
				System.out.println("Error fetching all the professors of the department ");
				System.out.println(e.getMessage());
				e.printStackTrace();

			}
			//catch block
			catch (Department.DepartmentDoesNotExistException e) {
				System.out.println("Error fetching the department ");
				System.out.println(e.getMessage());
				e.printStackTrace();

			}


			return ProfOfOneDept;
		}
		/*The code thats placed in the finally block gets executed no matter what. But 
															here the finally block does not contain any general statements*/
		finally {
		}

	}

	/*get all professors in a dept when passed a dept name*/
	public static ArrayList<Professor> getAllProfInADept(String DepartmentName)
			throws ProfessorDoesNotExistException {
		// if(Professor == null)
		// throw new NullPointerException();

		ArrayList<Professor> ProfOfOneDept = new ArrayList<Professor>();//initialize the araaylist

		try {
			Connection conn = Database.getConnection();//get the connection

			try {
				if (conn != null) {

					int retreivedDepartmentID = 0;

					try {
						String getDeptID = "Select DepartmentID"
								+ " FROM department"
								+ " WHERE DepartmentName= ?";

						PreparedStatement statement = conn.prepareStatement(getDeptID);
						statement.setString(1, DepartmentName);//set the dept ID
						ResultSet rs1 = statement.executeQuery();

						if (rs1.first()) {

							DBAnnotation.annoate("retreivedDepartmentID", "department", "DepartmentID", true);
							retreivedDepartmentID = rs1.getInt("DepartmentID");//retrieve the dept ID

						} else {

							throw new Department.DepartmentDoesNotExistException();//throw an exception
						}

					}
					//catch block
					catch (SQLException e) {
						System.out
								.println("Error finding the department name ");
						System.out.println(e.getMessage());
						e.printStackTrace();

					}
					//catch block
					catch (Department.DepartmentDoesNotExistException e) {
						System.out.println("Error fetching the department ");
						System.out.println(e.getMessage());
						e.printStackTrace();

					}

					// Retrieve all the professors from one department
					String SemesterSelect = "Select *"
							+ " FROM people"
							+ " WHERE DepartmentID= ? and PositionID=2";
					PreparedStatement statement1 = conn
							.prepareStatement(SemesterSelect);
					statement1.setInt(1, retreivedDepartmentID);
					ResultSet rs = statement1.executeQuery();

					while (rs.next()) {

						DBAnnotation.annoate("retreivedProfUserNames", "people", "Username", true);
						String retreivedProfUserNames = rs.getString("Username");

						Professor prof = new Professor(retreivedProfUserNames);
						ProfOfOneDept.add(prof);//add the professor objects to te arraylist
						System.out.println(prof.toString());
					}

				}

			}
			//catch the SQL exception
			catch (SQLException e) {
				System.out.println("Error fetching all the professors of the department ");
				System.out.println(e.getMessage());
				e.printStackTrace();

			}

			return ProfOfOneDept;
		}

		/*The code thats placed in the finally block gets executed no matter what. But 
														here the finally block does not contain any general statements*/
		finally {
		}

	}

	/*get all professors from the people table*/
	public static ArrayList<Professor> getAllProf() {

		ArrayList<Professor> allProfs = new ArrayList<Professor>();//initialize the arraylist

		try {
			Connection conn = Database.getConnection();//get the connection

			try {
				if (conn != null) {

					// Retrieve all the professors from one department
					String ProfessorSelect = "Select *"
							+ " FROM people" + " WHERE PositionID=2";
					PreparedStatement statement = conn.prepareStatement(ProfessorSelect);
					ResultSet rs = statement.executeQuery();//execute the query

					while (rs.next()) {

						DBAnnotation.annoate("retreivedProfUserNames", "people", "Username", true);
						String retreivedProfUserNames = rs.getString("Username");//retieve the username
						
						Professor prof = new Professor(retreivedProfUserNames);
						allProfs.add(prof);//add it to the arraylist the professor objects
						System.out.println(prof.getUserName());
					}

				}

			}
			//catch block
			catch (SQLException e) {
				System.out.println("Error fetching all the professors");
				System.out.println(e.getMessage());
				e.printStackTrace();

			}


			return allProfs;
		}
		/*The code thats placed in the finally block gets executed no matter what. But 
														here the finally block does not contain any general statements*/
		finally {
		}

	}

	//update the professor username
	public boolean updateProfUserName(String userName) {

		boolean isUpdated = false;

		try {
			Connection conn = Database.getConnection();//get the connection

			try {

				boolean ifAddedInLogin = People.updateUserNameIntoLoginTable(userName, this.getUserName());//update in login table
				if (ifAddedInLogin)
					isUpdated = true;

			}
			//catch the exception
			catch (Exception e) {
				System.out.println("Error adding/updating to database");
				e.printStackTrace();
				System.out.println(e);
			}

		}
		//catch the exception
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
	
	//update the professor name
	public boolean updateProfName(String name) {

		boolean isUpdated = false;

		try {
			Connection conn = Database.getConnection();//get the connection

			try {

				boolean ifUpdatedInPeople = People.updateNameIntoPeopleTable(name, this.getUIN());//update into the people table
				if (ifUpdatedInPeople)
					isUpdated = true;

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

	//update the prof dept
	public boolean updateProfDept(int deptID) {

		// how to check if the dept ID is not existing
		boolean isUpdated = false;

		try {
			Connection conn = Database.getConnection();//get the connection

			try {

				boolean ifUpdatedInPeople = People.updateDeptIntoPeopleTable(deptID, this.getUIN());//update the dept
				if (ifUpdatedInPeople)
					isUpdated = true;

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

		finally {

			// System.out.println("retrieved");
		}

		return isUpdated;

	}

	//new exceptions added that can be thrown 
	public static class ProfessorDoesNotExistException extends Exception {
		private static final long serialVersionUID = 1L;
		private String message = null;

		public ProfessorDoesNotExistException() {
			super();
			this.message = "Professor does not exist";
		}

		public ProfessorDoesNotExistException(String message) {
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
	 * All professor functions add, update, retrieve are specified in the this class
	 * 
	 * local main class is used for testing functions and specific executions
	 */
	public static void main(String[] args) {

	}

}

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Professor extends Employee {

	/*
	 * Due to some unknown error some functions and some updates have been
	 * deleted. I am writing them and updating them as i remember
	 * 
	 * 
	 * PLease let me know if you think of any other functions, write the
	 * function signature so that i will know what to do
	 */

	// This is a professor class

	// prof constructor
	public Professor(String name, String userName, int deptID) {
		super(name, userName, deptID, 2);
	}

	public Professor(int UIN) {
		super(UIN);
	}

	public Professor(String userName) {
		super(userName);
	}

	// prof adding
	public static boolean addProfToDb(String name, Department dept) {

		boolean isAdded = false;
		int returnedUIN;

		if (dept == null) {
			throw new NullPointerException();
		}

		try {
			returnedUIN = addIntoDatabase(name, dept, 2);
			if (returnedUIN != -1) {

				System.out.println(returnedUIN);

				isAdded = Employee.addEmployee(returnedUIN);

				Connection conn = Database.getConnection();
				Database.commitTransaction(conn);
			}
		}

		catch (People.loginDetailsnotAdded e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return isAdded;

	}

	// CHECKING IF THE PERSON WHOS UIN IS INPUT IS A PROFESSOR OR NOT
	public static boolean checkIfProfessor(int UIN) {

		try {
			Connection conn = Database.getConnection();
			String SQLPeopleSelect = "";
			try {

				if (conn != null) {

					SQLPeopleSelect = "Select PositionID From People where UIN=?;";
				}

				PreparedStatement stmtForSelect = conn
						.prepareStatement(SQLPeopleSelect);
				stmtForSelect.setInt(1, UIN);

				ResultSet rs = stmtForSelect.executeQuery();

				if (rs.first()) {

					int peopleRetrievedPositionID = rs.getInt("PositionID");
					System.out.println("UIN:" + UIN + " Position ID:"
							+ peopleRetrievedPositionID);

					if (peopleRetrievedPositionID == 2) {
						System.out.println("Professor UIN exists");
						return true;
					} else {
						System.out
								.println("UIN exists, but it is not a professor");
						return false;

					}

				}

				else {

					System.out.println("UIN does not exist");
					return false;

				}

			}

			catch (SQLException e) {
				System.out.println(e);

			}

			finally {

				// System.out.println("retrieved");
			}
		}

		catch (Exception e) {
			System.out.println(e);

		}

		finally {

			// System.out.println("retrieved");
		}

		return false;
	}

	public static boolean checkIfProfessor(String userName) {

		try {
			Connection conn = Database.getConnection();
			String SQLProfSelect = "";
			try {

				if (conn != null) {

					SQLProfSelect = "Select PositionID From People where Username=?;";
				}

				PreparedStatement stmtForSelect = conn
						.prepareStatement(SQLProfSelect);
				stmtForSelect.setString(1, userName);

				ResultSet rs = stmtForSelect.executeQuery();

				if (rs.first()) {

					int peopleRetrievedPositionID = rs.getInt("PositionID");
					System.out.println("Username:" + userName + " Position ID:"
							+ peopleRetrievedPositionID);
					/*
					 * Check here if the position ID id of a professor i.e 2,
					 * UIN exists for students professors, admins TA and
					 * virtually every person existing in the university Check
					 * if the position ID of the passed UIN is of a professor.
					 */

					if (peopleRetrievedPositionID == 2) {
						return true;
					} else {
						return false;
					}
					// System.out.println("Professor UIN exists");

				}

				else {

					System.out.println("username does not exist");
					return false;

				}

			}

			catch (SQLException e) {
				System.out.println(e);

			}

			finally {

				// System.out.println("retrieved");
			}
		}

		catch (Exception e) {
			System.out.println(e);

		}

		finally {

			// System.out.println("retrieved");
		}

		return false;
	}

	// SAME APPLIES TO THE NAME RETRIEVAL AND OTHER FUNCTIONS

	// prof retrieval by UIN
	public static Professor retrieveProfDetailsByUIN(int UIN) {

		boolean check = checkIfProfessor(UIN);

		if (check == true) {
			// retireveDetailsByUIN(12);
			Professor professor = new Professor(UIN);
			return professor;
		}

		else {
			System.out.println("There exists no professor with that UIN");
			return null;
		}

	}

	// prof retrieval by userName
	public static Professor retrieveProfDetailsByUserName(String userName) {

		boolean check = checkIfProfessor(userName);

		if (check == true) {
			Professor professor = new Professor(userName);
			// System.out.println(professor.getUIN());
			return professor;

		} else {
			System.out.println("There exists no professor with that username");
			return null;
		}

	}

	// prof deletion by UIN
	public static void deleteProfFromDbUsingUIN(int UIN) {

		boolean check = checkIfProfessor(UIN);

		if (check == true) {
			deleteFromDatabaseByUIN(UIN);
			Employee.deleteFromDatabaseByUIN(UIN);

			Connection conn = Database.getConnection();
			Database.commitTransaction(conn);
		} else {
			System.out.println("There exists no professor with that UIN");
		}

	}

	// prof deletion by username
	public static boolean deleteProfFromDbUsingUserName(String userName) {

		boolean isDeleted = false;

		boolean check = checkIfProfessor(userName);

		if (check == true) {
			deleteFromDatabaseByUserName(userName);
			isDeleted = Employee.deleteFromDatabaseByUserName(userName);
		} else {
			System.out.println("There exists no professor with that username");
		}

		return isDeleted;
	}

	public String toString() {

		return getUIN() + " " + getUserName() + " " + getName() + " "
				+ getDeptID() + " " + getPositionID();

	}

	public static ArrayList<Professor> getAllProfInADept(int departmentID)
			throws ProfessorDoesNotExistException {
		// if(Professor == null)
		// throw new NullPointerException();

		ArrayList<Professor> ProfOfOneDept = new ArrayList<Professor>();

		try {
			Connection conn = Database.getConnection();

			try {
				if (conn != null) {

					Department dept = new Department(departmentID);

					// Retrieve all the professors from one department
					String ProfessorSelect = "Select *"
							+ " FROM university.people"
							+ " WHERE DepartmentID= ? and PositionID=2";
					PreparedStatement statement = conn
							.prepareStatement(ProfessorSelect);
					statement.setInt(1, departmentID);
					ResultSet rs = statement.executeQuery();

					while (rs.next()) {

						String retreivedProfUserNames = rs
								.getString("Username");
						// System.out.println(retreivedProfUserNames);
						Professor prof = new Professor(retreivedProfUserNames);
						ProfOfOneDept.add(prof);
						System.out.println(prof.toString());
					}

				}

			}

			catch (SQLException e) {
				System.out
						.println("Error fetching all the professors of the department ");
				System.out.println(e.getMessage());
				e.printStackTrace();

			}

			catch (Department.DepartmentDoesNotExistException e) {
				System.out.println("Error fetching the department ");
				System.out.println(e.getMessage());
				e.printStackTrace();

			}

			finally {
				// Database.commitTransaction(conn);
			}

			return ProfOfOneDept;
		}

		finally {
		}

	}

	public static ArrayList<Professor> getAllProfInADept(String DepartmentName)
			throws ProfessorDoesNotExistException {
		// if(Professor == null)
		// throw new NullPointerException();

		ArrayList<Professor> ProfOfOneDept = new ArrayList<Professor>();

		try {
			Connection conn = Database.getConnection();

			try {
				if (conn != null) {

					int retreivedDepartmentID = 0;

					try {
						String getDeptID = "Select DepartmentID"
								+ " FROM university.department"
								+ " WHERE DepartmentName= ?";

						PreparedStatement statement = conn
								.prepareStatement(getDeptID);
						statement.setString(1, DepartmentName);
						ResultSet rs1 = statement.executeQuery();

						if (rs1.first()) {

							retreivedDepartmentID = rs1.getInt("DepartmentID");

						} else {

							throw new Department.DepartmentDoesNotExistException();
						}

					}

					catch (SQLException e) {
						System.out
								.println("Error finding the department name ");
						System.out.println(e.getMessage());
						e.printStackTrace();

					}

					catch (Department.DepartmentDoesNotExistException e) {
						System.out.println("Error fetching the department ");
						System.out.println(e.getMessage());
						e.printStackTrace();

					}

					// Retrieve all the professors from one department
					String SemesterSelect = "Select *"
							+ " FROM university.people"
							+ " WHERE DepartmentID= ? and PositionID=2";
					PreparedStatement statement1 = conn
							.prepareStatement(SemesterSelect);
					statement1.setInt(1, retreivedDepartmentID);
					ResultSet rs = statement1.executeQuery();

					while (rs.next()) {

						String retreivedProfUserNames = rs
								.getString("Username");
						// System.out.println(retreivedProfUserNames);
						Professor prof = new Professor(retreivedProfUserNames);
						ProfOfOneDept.add(prof);
						System.out.println(prof.toString());
					}

				}

			}

			catch (SQLException e) {
				System.out
						.println("Error fetching all the professors of the department ");
				System.out.println(e.getMessage());
				e.printStackTrace();

			}

			finally {
				// Database.commitTransaction(conn);
			}

			return ProfOfOneDept;
		}

		finally {
		}

	}

	public static ArrayList<Professor> getAllProf() {
		// if(Professor == null)
		// throw new NullPointerException();

		ArrayList<Professor> allProfs = new ArrayList<Professor>();

		try {
			Connection conn = Database.getConnection();

			try {
				if (conn != null) {

					// Retrieve all the professors from one department
					String ProfessorSelect = "Select *"
							+ " FROM university.people" + " WHERE PositionID=2";
					PreparedStatement statement = conn
							.prepareStatement(ProfessorSelect);
					ResultSet rs = statement.executeQuery();

					while (rs.next()) {

						String retreivedProfUserNames = rs
								.getString("Username");
						// System.out.println(retreivedProfUserNames);
						Professor prof = new Professor(retreivedProfUserNames);
						allProfs.add(prof);
						System.out.println(prof.getUserName());
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

			return allProfs;
		}

		finally {
		}

	}

	public boolean updateProfUserName(String userName) {

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

	public boolean updateProfName(String name) {

		boolean isUpdated = false;

		try {
			Connection conn = Database.getConnection();

			try {

				boolean ifUpdatedInPeople = People.updateNameIntoPeopleTable(
						name, this.getUIN());
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

	public boolean updateProfDept(int deptID) {

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

	// add files

	// get all course taken by the professor

	// add exams

	// grade exams

	static class ProfessorDoesNotExistException extends Exception {
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

	public static void main(String[] args) {

		// try {
		// Department dept=new Department(2);
		// addProfToDb("Simant", dept);
		// } catch (Department.DepartmentDoesNotExistException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		//

		// Professor x = new Professor("priyanka", "maravapa", 2);

		// x.addProfToDb();

		// retrieveProfDetailsByUserName("maravapa");

		// try {
		// getAllProfInADept("aksh");
		// } catch (ProfessorDoesNotExistException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

		Professor prof = new Professor(272);

		prof.updateProfDept(16);

		// People.

	}

}

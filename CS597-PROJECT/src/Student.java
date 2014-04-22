import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.hamcrest.core.IsEqual;

public class Student extends People {

	double GPA;
	int level;
	JobApplication jobApplication = null;

	public Student(int UIN) {
		super(UIN);

		try {
			Connection conn = Database.getConnection();
			String SQLStudentGPASelect = "";
			try {

				if (conn != null) {

					SQLStudentGPASelect = "Select * From university.student where UIN=?;";
				}

				PreparedStatement stmtForSelect = conn
						.prepareStatement(SQLStudentGPASelect);
				stmtForSelect.setInt(1, UIN);

				ResultSet rs = stmtForSelect.executeQuery();

				if (rs.first()) {
					int retrievedStudentUIN = rs.getInt("UIN");
					double retrievedStudentGPA = rs.getDouble("GPA");
					int retrievedStudentLevel = rs.getInt("Level");

					this.UIN = retrievedStudentUIN;
					this.GPA = retrievedStudentGPA;
					this.level = retrievedStudentLevel;
					this.jobApplication = new JobApplication(
							retrievedStudentUIN);

					// System.out.println(peopleRetrievedUIN);
					// System.out.println(peopleRetrievedName);
					// System.out.println(peopleRetrieveduserName);
					// System.out.println(peopleRetrievedDeptID);
					// System.out.println(peopleRetrievedPositionID);
				}

				else {

					System.out
							.println("UIN does not exist in the student table");

				}

			}

			catch (SQLException e) {
				System.out.print("SQL exception in student const");
				System.out.println(e);
				e.printStackTrace();

			}

			finally {

				// System.out.println("retrieved");
			}
		}

		catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();

		}

		finally {

			// System.out.println("retrieved");
		}

	}

	// TODO Auto-generated constructor stub

	public Student(String name, String userName, int deptID, int positionID) {
		super(name, userName, deptID, 3);
		// TODO Auto-generated constructor stub
	}

	public double getGPA() {
		return GPA;
	}

	public void setGPA(double gPA) {
		GPA = gPA;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public Student(String userName) {
		super(userName);
		// TODO Auto-generated constructor stub
	}

	public static boolean addStudentToDb(String name, Department dept, int level) {

		boolean isAdded = false;

		if (dept == null) {
			throw new NullPointerException();
		}

		int addedUIN;
		try {
			addedUIN = addIntoDatabase(name, dept, 3);
			if (addedUIN != -1) {

				System.out.println(addedUIN);
				System.out.println(level);

				try {
					isAdded = addIntoStudentTable(addedUIN, level);

				} catch (levelNotExistException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				Connection conn = Database.getConnection();
				Database.commitTransaction(conn);

			}
		} catch (loginDetailsnotAdded e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		return isAdded;

	}

	private static boolean addIntoStudentTable(int UIN, int level)
			throws levelNotExistException {

		boolean isAdded = false;
		if (level > 3 || level < 1) {
			throw new levelNotExistException();
		}

		try {
			Connection conn = Database.getConnection();
			String SQLPeopleSelect = "";

			try {

				SQLPeopleSelect = "Select UIN From student where UIN=?;";
				PreparedStatement stmt = conn.prepareStatement(SQLPeopleSelect);
				stmt.setInt(1, UIN);
				ResultSet rs = stmt.executeQuery();

				if (rs.first()) {
					System.out.println(UIN + "already exists");
					// Insert a update query to update the values of the
					// database....NOT ADD
				}

				else {

					System.out.println("Adding new data into the database");
					String SQLPeopleInsert = "Insert into student (UIN, GPA, Level) Values (?,?,?);";
					stmt = conn.prepareStatement(SQLPeopleInsert);
					stmt.setInt(1, UIN);
					stmt.setFloat(2, (float) 4.0);
					stmt.setInt(3, level);
					System.out.println(stmt);
					int i = stmt.executeUpdate();
					System.out.println(i);
					System.out.println("Inserted");
					isAdded = true;

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

	public static void deleteStudent(int UIN) {
	}

	public static void deleteStudent(String userName) {
	}

	public static boolean checkIfStudent(int UIN) {

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

					if (peopleRetrievedPositionID == 3) {
						System.out.println("Student UIN exists");
						return true;
					} else {
						System.out
								.println("UIN exists, but it is not a student");
						return false;

					}

				}

				else {

					System.out.println("UIN does not exist");
					return false;

				}

			}

			catch (SQLException e) {
				e.printStackTrace();
				System.out.println(e);

			}

			finally {

				// System.out.println("retrieved");
				Database.closeConnection(conn);
			}
		}

		catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();

		}

		finally {

			// System.out.println("retrieved");
		}

		return false;
	}

	public static boolean updateGPA(int UIN, double newGPA)
			throws GPAnotValidException {

		// if(level>3 || level<1){
		// throw new levelNotExistException();
		// }
		boolean updateGPA = false;

		if (newGPA > 4.00 || newGPA < 1.0)

			throw new GPAnotValidException();

		try {
			Connection conn = Database.getConnection();

			try {

				boolean ifExisting = updateGPACheck(UIN);

				if (ifExisting) {

					// Insert a update query to update the values of the
					// database....NOT ADD
					System.out.println("Updating GPA into the database");
					String SQLupdateGPA = "UPDATE student SET GPA=? where UIN=?;";
					PreparedStatement stmt = conn
							.prepareStatement(SQLupdateGPA);
					stmt.setDouble(1, newGPA);
					stmt.setInt(2, UIN);
					System.out.println(stmt);
					int i = stmt.executeUpdate();
					System.out.println(i);
					System.out.println("Updated");
					updateGPA = true;

					// Connection conn=Database.getConnection();
					// Database.commitTransaction(conn);

				}

				else {
					System.out.println(UIN + "already exists");

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

		return updateGPA;
	}

	public static boolean updateGPACheck(int UIN) {

		boolean isExisting = false;

		try {
			Connection conn = Database.getConnection();
			String SQLPeopleSelect = "";

			try {

				SQLPeopleSelect = "Select UIN From student where UIN=?;";
				PreparedStatement stmt = conn.prepareStatement(SQLPeopleSelect);
				stmt.setInt(1, UIN);
				ResultSet rs = stmt.executeQuery();

				if (rs.first()) {

					return true;

				}

				else {
					System.out.println(UIN + "already exists");

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

	public static ArrayList<Student> getAllStudents() {
		// if(Professor == null)
		// throw new NullPointerException();

		ArrayList<Student> allStudents = new ArrayList<Student>();

		try {
			Connection conn = Database.getConnection();

			try {
				if (conn != null) {

					// Retrieve all the professors from one department
					String ProfessorSelect = "Select *"
							+ " FROM university.people" + " WHERE PositionID=3";
					PreparedStatement statement = conn
							.prepareStatement(ProfessorSelect);
					ResultSet rs = statement.executeQuery();

					while (rs.next()) {

						String retreivedStudentUserNames = rs
								.getString("Username");
						// System.out.println(retreivedProfUserNames);
						Student stud = new Student(retreivedStudentUserNames);
						allStudents.add(stud);
						System.out.println(stud.getUserName());
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

			return allStudents;
		}

		finally {
		}

	}

	public LinkedHashMap<Integer, CourseOffered> getStudentCourses()
			throws Course.CourseDoesNotExistException,
			CourseOffered.CourseOfferingDoesNotExistException {

		LinkedHashMap<Integer, CourseOffered> studentCourses = new LinkedHashMap<Integer, CourseOffered>();

		try {
			Connection conn = Database.getConnection();

			try {
				if (conn != null) {

					// Retrieve the current semester ID
					String SemesterSelect = "Select *"
							+ " FROM university.studentenrollment"
							+ " WHERE UIN= ?";
					PreparedStatement statement = conn
							.prepareStatement(SemesterSelect);
					statement.setInt(1, this.getUIN());
					ResultSet rs = statement.executeQuery();

					while (rs.next()) {
						int offerID = rs.getInt(3);
						CourseOffered course = new CourseOffered(offerID);
						studentCourses.put(course.getOfferID(), course);
					}

				}

			}

			catch (SQLException e) {
				System.out.println("Error addind course offering");
				System.out.println(e.getMessage());
				e.printStackTrace();

			}

			finally {
				// Database.commitTransaction(conn);
			}

			return studentCourses;
		}

		finally {
		}

	}

	public boolean updateStudentUserName(String userName) {

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

	public boolean updateStudentName(String name) {

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

	public boolean updateStudentDept(int deptID) {

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

	static class levelNotExistException extends Exception {
		private static final long serialVersionUID = 1L;
		private String message = null;

		public levelNotExistException() {
			super();
			this.message = "level does not exist";
		}

		public levelNotExistException(String message) {
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

	static class GPAnotValidException extends Exception {
		private static final long serialVersionUID = 1L;
		private String message = null;

		public GPAnotValidException() {
			super();
			this.message = "GPA is not valid";
		}

		public GPAnotValidException(String message) {
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
		// addStudentToDb("arihant", dept,1);
		// } catch (Department.DepartmentDoesNotExistException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

		// try {
		// updateGPA(28, 3.7);
		// } catch (GPAnotValidException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

		// getAllStudents();

		Student student = new Student(451);

		student.updateStudentDept(16);

	}

}

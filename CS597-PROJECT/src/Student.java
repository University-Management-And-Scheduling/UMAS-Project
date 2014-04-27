import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * @author Akshay
 * 
 */

/*************** STUDENT.JAVA CLASS WAS WRITTEN BY AKSHAY THIRKATEH ********************************/

public class Student extends People {

	// class variables that are specific to student class
	protected double GPA;
	protected int level;
	public JobApplication jobApplication = null;

	/*student class constructor with the UIN passed 
	 * 
	 * it throws an exception
	 * */
	public Student(int UIN) throws PersonDoesNotExistException {
		super(UIN);

		try {
			Connection conn = Database.getConnection();//get the connection
			String SQLStudentGPASelect = "";
			try {

				if (conn != null) {

					SQLStudentGPASelect = "Select * From university.student where UIN=?;";//write query
				}

				PreparedStatement stmtForSelect = conn.prepareStatement(SQLStudentGPASelect);
				stmtForSelect.setInt(1, UIN);//set the UIN

				ResultSet rs = stmtForSelect.executeQuery();//execute the query

				if (rs.first()) {
					int retrievedStudentUIN = rs.getInt("UIN");//retrieve the values
					double retrievedStudentGPA = rs.getDouble("GPA");//retrieve the values
					int retrievedStudentLevel = rs.getInt("Level");//retrieve the values

					//set the class variables to UIN specific 
					
					this.UIN = retrievedStudentUIN;
					this.GPA = retrievedStudentGPA;
					this.level = retrievedStudentLevel;
					this.jobApplication = new JobApplication(retrievedStudentUIN);

				}

				else {

					System.out.println("UIN does not exist in the student table");
					throw new PersonDoesNotExistException();//throw the exception

				}

			}
			//catch block
			catch (SQLException e) {
				System.out.print("SQL exception in student const");
				System.out.println(e);
				e.printStackTrace();
			}
		}
		//catch block
		catch (PersonDoesNotExistException e) {
			System.out.println(e);
			e.printStackTrace();
			throw new PersonDoesNotExistException();

		}

		finally {

			// System.out.println("retrieved");
		}

	}

	// TODO Auto-generated constructor stub
	
	//student construtor with name, username and dpet ID and positionID
	public Student(String name, String userName, int deptID, int positionID) {
		super(name, userName, deptID, 3);
		// TODO Auto-generated constructor stub
	}

	/*-------------------------GETTERS AND SETTERS START----------------------------------*/
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

	/*-------------------------GETTERS AND SETTERS END----------------------------------*/
	
	public static boolean addStudentToDb(String name, Department dept, int level)
			throws levelNotExistException {

		boolean isAdded = false;

		if (dept == null) {
			throw new NullPointerException();//if dept is null then throw an exception
		}

		if (level < 0 || level > 4) {
			throw new levelNotExistException();//throw an exception
		}

		int addedUIN;
		try {
			addedUIN = addIntoDatabase(name, dept, 3);//add into database
			if (addedUIN != -1) {

				System.out.println(addedUIN);
				System.out.println(level);

				try {
					isAdded = addIntoStudentTable(addedUIN, level);//add into the student table

				} catch (levelNotExistException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				Connection conn = Database.getConnection();
				Database.commitTransaction(conn);//commit transaction

			}
		} catch (loginDetailsnotAdded e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		return isAdded;

	}
	
	/*add into the student table with the UIN and level passed
	 * 
	 * the return type is boolean 
	 * 
	 * it throws an exception
	 * */
	private static boolean addIntoStudentTable(int UIN, int level) throws levelNotExistException {

		boolean isAdded = false;

		if (level > 3 || level < 1) {
			throw new levelNotExistException();//level not exists exception
		}

		try {
			Connection conn = Database.getConnection();//get the connection
			String SQLPeopleSelect = "";

			try {

				SQLPeopleSelect = "Select UIN From student where UIN=?;";
				PreparedStatement stmt = conn.prepareStatement(SQLPeopleSelect);
				stmt.setInt(1, UIN);//set the UIN
				ResultSet rs = stmt.executeQuery();//execute the query

				if (rs.first()) {
					System.out.println(UIN + "already exists");
				}

				else {
					//insert new data into the student table
					System.out.println("Adding new data into the database");
					String SQLPeopleInsert = "Insert into student (UIN, GPA, Level) Values (?,?,?);";
					stmt = conn.prepareStatement(SQLPeopleInsert);
					stmt.setInt(1, UIN);//set the values
					stmt.setFloat(2, (float) 4.0);//set the values
					stmt.setInt(3, level);//set the values
					System.out.println(stmt);
					int i = stmt.executeUpdate();//execute the query
					System.out.println(i);
					System.out.println("Inserted");
					isAdded = true;

				}

			}
			//catch block 
			catch (SQLException e) {
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
		return isAdded;

	}
	//delete student function
	public static void deleteStudent(int UIN) {
	}

	//delete student function
	public static void deleteStudent(String userName) {
	}

	//check if student with the UIN passed
	public static boolean checkIfStudent(int UIN) {

		try {
			Connection conn = Database.getConnection();//get the connection
			String SQLPeopleSelect = "";
			try {

				if (conn != null) {

					SQLPeopleSelect = "Select PositionID From People where UIN=?;";//write query
				}

				PreparedStatement stmtForSelect = conn.prepareStatement(SQLPeopleSelect);
				stmtForSelect.setInt(1, UIN);//set the values

				ResultSet rs = stmtForSelect.executeQuery();//execute the query

				if (rs.first()) {

					int peopleRetrievedPositionID = rs.getInt("PositionID");//retrieve the values
					System.out.println("UIN:" + UIN + " Position ID:"+ peopleRetrievedPositionID);

					//check the position ID
					if (peopleRetrievedPositionID == 3 || peopleRetrievedPositionID == 4) {
						System.out.println("Student UIN exists");
						return true;
					} else {
						System.out.println("UIN exists, but it is not a student");
						return false;

					}

				}

				else {

					System.out.println("UIN does not exist");
					return false;

				}

			}
			//catch block
			catch (SQLException e) {
				e.printStackTrace();
				System.out.println(e);

			}


		}
		//catch block
		catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();

		}
		/*The code thats placed in the finally block gets executed no matter what. But 
														here the finally block does not contain any general statements*/
		finally {

			// System.out.println("retrieved");
		}

		return false;
	}

	//update GPA for the UIN with the new GPA
	public static boolean updateGPA(int UIN, double newGPA) throws GPAnotValidException {

		// if(level>3 || level<1){
		// throw new levelNotExistException();
		// }
		boolean updateGPA = false;

		if (newGPA > 4.00 || newGPA < 1.0)

			throw new GPAnotValidException();//throw an exception

		try {
			Connection conn = Database.getConnection();//get the connection

			try {

				boolean ifExisting = updateGPACheck(UIN);//check if the UIN exists

				if (ifExisting) {

					System.out.println("Updating GPA into the database");
					String SQLupdateGPA = "UPDATE student SET GPA=? where UIN=?;";
					PreparedStatement stmt = conn.prepareStatement(SQLupdateGPA);
					stmt.setDouble(1, newGPA);//set the values
					stmt.setInt(2, UIN);//set the values
					System.out.println(stmt);
					int i = stmt.executeUpdate();//execute the query
					System.out.println(i);
					System.out.println("Updated");
					updateGPA = true;

					// Connection conn=Database.getConnection();
					Database.commitTransaction(conn);//commit the transaction

				}

				else {
					System.out.println(UIN + "already exists");

				}

			}
			//catch block
			catch (SQLException e) {
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

		return updateGPA;
	}
	
	/*this function checks that if the UIN exists in the student Table*/
	public static boolean updateGPACheck(int UIN) {

		boolean isExisting = false;

		try {
			Connection conn = Database.getConnection();//get the connection
			String SQLPeopleSelect = "";

			try {

				SQLPeopleSelect = "Select UIN From student where UIN=?;";
				PreparedStatement stmt = conn.prepareStatement(SQLPeopleSelect);
				stmt.setInt(1, UIN);//set the UIN
				ResultSet rs = stmt.executeQuery();//query fire

				if (rs.first()) {
					//if exists then return true
					return true;

				}

				else {
					System.out.println(UIN + "already exists");

				}

			}
			//catch block
			catch (SQLException e) {
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

		return isExisting;
	}

	/*get all students*/
	public static ArrayList<Student> getAllStudents() {


		ArrayList<Student> allStudents = new ArrayList<Student>();//initialize the arraylist

		try {
			Connection conn = Database.getConnection();//get the connection

			try {
				if (conn != null) {

					// Retrieve all the professors from one department
					String ProfessorSelect = "Select *"
							+ " FROM university.people" + " WHERE PositionID=3";
					PreparedStatement statement = conn.prepareStatement(ProfessorSelect);
					ResultSet rs = statement.executeQuery();//execute the query

					while (rs.next()) {
						int retreivedStudentUserUIN = rs.getInt("UIN");//retrieve the UIN
						Student stud = new Student(retreivedStudentUserUIN);
						allStudents.add(stud);//add it to the arraylist
						System.out.println(stud.getUserName());
					}

				}

			}
			//catch block
			catch (SQLException e) {
				System.out.println("Error fetching all the professors");
				System.out.println(e.getMessage());
				e.printStackTrace();

			} 
			//catch block
			catch (People.PersonDoesNotExistException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


			return allStudents;
		}
		/*The code thats placed in the finally block gets executed no matter what. But 
													here the finally block does not contain any general statements*/
		finally {
		}

	}

	/*get all student courses*/
	public LinkedHashMap<Integer, CourseOffered> getStudentCourses() throws Course.CourseDoesNotExistException, CourseOffered.CourseOfferingDoesNotExistException {

		LinkedHashMap<Integer, CourseOffered> studentCourses = new LinkedHashMap<Integer, CourseOffered>();//initilaize the hashmap

		try {
			Connection conn = Database.getConnection();//get the connection

			try {
				if (conn != null) {

					// Retrieve the current semester ID
					String SemesterSelect = "Select *"
							+ " FROM university.studentenrollment"
							+ " WHERE UIN= ?";
					PreparedStatement statement = conn.prepareStatement(SemesterSelect);
					statement.setInt(1, this.getUIN());//set the UIN
					ResultSet rs = statement.executeQuery();

					while (rs.next()) {
						int offerID = rs.getInt(3);
						CourseOffered course = new CourseOffered(offerID);//set the object
						studentCourses.put(course.getOfferID(), course);//add it to the hashmap
					}

				}

			}

			catch (SQLException e) {
				System.out.println("Error addind course offering");
				System.out.println(e.getMessage());
				e.printStackTrace();

			}

			return studentCourses;
		}
		/*The code thats placed in the finally block gets executed no matter what. But 
										here the finally block does not contain any general statements*/
		finally {
		}

	}
	
	/*update student name when the username is passed*/
	public boolean updateStudentUserName(String userName) {

		if (userName.equals("")) {
			throw new NullPointerException();//check if its null
		}
		boolean isUpdated = false;

		try {
			Connection conn = Database.getConnection();//get the connection

			try {

				boolean ifAddedInLogin = People.updateUserNameIntoLoginTable(userName, this.getUserName());//update in the login table
				if (ifAddedInLogin)
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

	/*update the student name which passed the new name to this function*/
	public boolean updateStudentName(String name) {

		if (name.equals("")) {
			throw new NullPointerException();//throw an exception
		}

		boolean isUpdated = false;

		try {
			Connection conn = Database.getConnection();//get the connection

			try {

				boolean ifUpdatedInPeople = People.updateNameIntoPeopleTable(name, this.getUIN());//update in people table
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

	/*update the student Dept when it is passed to it the new dpet ID*/
	public boolean updateStudentDept(int deptID) {

		// ask simant how to check for the dept

		boolean isUpdated = false;

		try {
			Connection conn = Database.getConnection();//get the connection 

			try {

				boolean ifUpdatedInPeople = People.updateDeptIntoPeopleTable(deptID, this.getUIN());//update in the people table
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
		//CATCH block
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

	/*calculates the GPA from the grades it has for the passed UIN*/
	public static void calculateGPA(int UIN) {

		ArrayList<String> getGrades = new ArrayList<String>();//get the grades
		double finalGrade = 0.0;
		int count = 0;
		int maxLevel = GradeSystem.getMaxGradeLevel();//get the max grade level

		try {
			Connection conn = Database.getConnection();//get the connection
			String SQLStudentGPASelect = "";
			try {

				if (conn != null) {

					SQLStudentGPASelect = "Select * From university.studentenrollment natural join university.gradingsystem where UIN=?;";
				}

				PreparedStatement stmtForSelect = conn.prepareStatement(SQLStudentGPASelect);
				stmtForSelect.setInt(1, UIN);//pass the UIN

				ResultSet rs = stmtForSelect.executeQuery();//execute the query

				while (rs.next()) {

					//calculate the final GPA
					double retrievedStudentGradeLevel = rs.getDouble("GradeLevel");//get the grade
					double normalizedLevel = maxLevel- retrievedStudentGradeLevel + 1;
					retrievedStudentGradeLevel = (normalizedLevel * 4.0) / 7.0;
					finalGrade += retrievedStudentGradeLevel;
					count++;

				}

				//calculate the GPA
				if (count > 0) {
					finalGrade = finalGrade / count;
					updateGPA(UIN, finalGrade);//update the new GPA
				}

				else {
					updateGPA(UIN, 4.0);//update the gpa
				}

			}
			//catch block
			catch (SQLException e) {
				System.out.print("SQL exception in student const");
				System.out.println(e);
				e.printStackTrace();

			}

		}
		//catch block
		catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();

		}
		
		/*The code thats placed in the finally block gets executed no matter what. But 
														here the finally block does not contain any general statements*/
		finally {

			// System.out.println("retrieved");
		}

	}
	
	//new exceptions added that can be thrown 
	public static class levelNotExistException extends Exception {
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

	//new exceptions added that can be thrown 
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

	//new exceptions added that can be thrown 
	public static class AccessDeniedException extends Exception {
		private static final long serialVersionUID = 1L;
		private String message = null;

		public AccessDeniedException() {
			super();
			this.message = "You do not have the access";
		}

		public AccessDeniedException(String message) {
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

	@Override
	public boolean equals(Object arg0) {
		// TODO Auto-generated method stub
		if (arg0 instanceof Student) {
			Student temp = (Student) arg0;
			// ask sim to add a return stmt here
		}
		return super.equals(arg0);

	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}

	/*
	 * All student functions add, update, retrieve are specified in the this class
	 * 
	 * local main class is used for testing functions and specific executions
	 */
	public static void main(String[] args) {

	}

}

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;

/**
* @author Akshay
* 
*/

/*************** JOBAPPLICATION.JAVA CLASS WAS WRITTEN BY AKSHAY THIRKATEH ********************************/

public class JobApplication {

	// class variables mainly uses by the job application class
	
	protected int ApplicationID;
	protected int UIN;
	protected double workEx;
	protected boolean skill1;
	protected boolean skill2;
	protected boolean skill3;
	protected boolean skill4;
	protected boolean skill5;
	protected float scaledScore;
	String SQLPeopleSelect = "";
	String SQLPeopleReSelect = "";

	/*-----------------------------------GETTERS AND SETTERS START-----------------------------------*/
	public int getApplicationID() {
		return ApplicationID;
	}

	public void setApplicationID(int applicationID) {
		ApplicationID = applicationID;
	}

	public int getUIN() {
		return UIN;
	}

	public void setUIN(int uIN) {
		UIN = uIN;
	}

	public double getWorkEx() {
		return workEx;
	}

	public void setWorkEx(double workEx) {
		this.workEx = workEx;
	}

	public boolean isSkill1() {
		return skill1;
	}

	public void setSkill1(boolean skill1) {
		this.skill1 = skill1;
	}

	public boolean isSkill2() {
		return skill2;
	}

	public void setSkill2(boolean skill2) {
		this.skill2 = skill2;
	}

	public boolean isSkill3() {
		return skill3;
	}

	public void setSkill3(boolean skill3) {
		this.skill3 = skill3;
	}

	public boolean isSkill4() {
		return skill4;
	}

	public void setSkill4(boolean skill4) {
		this.skill4 = skill4;
	}

	public boolean isSkill5() {
		return skill5;
	}

	public void setSkill5(boolean skill5) {
		this.skill5 = skill5;
	}

	public float getScaledScore() {
		return scaledScore;
	}

	public void setScaledScore(float scaledScore) {
		this.scaledScore = scaledScore;
	}

	public String getSQLPeopleSelect() {
		return SQLPeopleSelect;
	}

	public void setSQLPeopleSelect(String sQLPeopleSelect) {
		SQLPeopleSelect = sQLPeopleSelect;
	}

	public String getSQLPeopleReSelect() {
		return SQLPeopleReSelect;
	}

	public void setSQLPeopleReSelect(String sQLPeopleReSelect) {
		SQLPeopleReSelect = sQLPeopleReSelect;
	}

	/*-----------------------------------GETTERS AND SETTERS END-----------------------------------*/
	
	/*job application class constructor takes in the UIN
	 * 
	 * initializes all the other variables that can be used by the class*/
	public JobApplication(int UIN) throws People.PersonDoesNotExistException {

		boolean check = Student.checkIfStudent(UIN);// checking if the UIn passed is the student
		System.out.println(check);
		if (!check) {
			throw new People.PersonDoesNotExistException();//if the UIN is not students then an exception is thrown
		}

		try {
			Connection conn = Database.getConnection();//get connection

			try {

				if (conn != null) {

					SQLPeopleSelect = "Select * From applicationdetails where ApplicantUIN=?;";//write the query
				}

				PreparedStatement stmtForSelect = conn.prepareStatement(SQLPeopleSelect);
				stmtForSelect.setInt(1, UIN);//set the UIN

				ResultSet rs = stmtForSelect.executeQuery();//execute the query

				if (rs.first()) {

					//if the resultset is not empty then 
					
					int RetrievedUIN = rs.getInt("ApplicantUIN");//---retreive the UIN
					Double RetrievedWorkEx = rs.getDouble("WorkExperience");//---retreive the work Ex
					boolean retreivedSkill1 = rs.getBoolean("Skillset1");//---retreive the skills
					boolean retreivedSkill2 = rs.getBoolean("Skillset2");//---retreive the skills
					boolean retreivedSkill3 = rs.getBoolean("Skillset3");//---retreive the skills
					boolean retreivedSkill4 = rs.getBoolean("Skillset4");//---retreive the skills
					boolean retreivedSkill5 = rs.getBoolean("Skillset5");//---retreive the skills
					float retreivedScaledScore = rs.getFloat("Scaledscore");//---retreive the scaled score

					//initilaize the class variables to the UIN specific
					
					this.ApplicationID = rs.getInt("ApplicationID");
					this.UIN = RetrievedUIN;
					this.workEx = RetrievedWorkEx;
					this.skill1 = retreivedSkill1;
					this.skill2 = retreivedSkill2;
					this.skill3 = retreivedSkill3;
					this.skill4 = retreivedSkill4;
					this.skill5 = retreivedSkill5;
					this.scaledScore = retreivedScaledScore;
				}

				else {
					//no job application exists
					System.out.println("job application for " + UIN+ " does not exist");

				}

			}
			//catch block
			catch (SQLException e) {
				e.printStackTrace();
				System.out.println(e);

			}

			finally {

				// System.out.println("retrieved");
			}
		}
		//catch block
		catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);

		}
		/*The code thats placed in the finally block gets executed no matter what. But 
															here the finally block does not contain any general statements*/
		finally {

			// System.out.println("retrieved");
		}

	}
	
	/*add application details ADDS if the application details of the student with their required credentials
	 * 
	 *  the return type is boolean 
	 *  
	 *  throws the person does not exist exception*/
	public static boolean addApplicationDetails(int UIN, double workExp, boolean skill1, boolean skill2, boolean skill3, boolean skill4, boolean skill5) throws People.PersonDoesNotExistException {

		boolean check = Student.checkIfStudent(UIN);//checks if student
		if (!check) {
			throw new People.PersonDoesNotExistException();//thorw the exception
		}
		boolean isAdded = false;
		try {
			Connection conn = Database.getConnection();//get the connection

			try {

				boolean isExisting = addApplicationDetailsCheck(UIN);//if the application detaisl already exist

				if (isExisting) {
					//if the application details exist then return false
					return false;

				}

				else {
					//calculate the scaled score by passing the work ex and the skills score
					double getScore = calculateScaledScore(UIN, workExp,skill1, skill2, skill3, skill4, skill5);

					//insert into the database with the calculated scaled score
					System.out.println("Adding new data into the database");
					String SQLPeopleInsert = "Insert into applicationdetails (applicantUIN, WorkExperience, Skillset1, Skillset2, Skillset3, Skillset4, Skillset5, Scaledscore) Values (?,?,?,?,?,?,?,?);";
					PreparedStatement stmt = conn.prepareStatement(SQLPeopleInsert);
					
					//set the values to be put into the query
					stmt.setInt(1, UIN);
					stmt.setDouble(2, workExp);
					stmt.setBoolean(3, skill1);
					stmt.setBoolean(4, skill2);
					stmt.setBoolean(5, skill3);
					stmt.setBoolean(6, skill4);
					stmt.setBoolean(7, skill5);
					stmt.setDouble(8, getScore);
					System.out.println(stmt);
					int i = stmt.executeUpdate();//execute the query
					System.out.println(i);
					System.out.println("Inserted");

					Database.commitTransaction(conn);//commit the transaction
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

	/*this function mainly check if the passed UIn exists the job application table or not
	 * 
	 * the return type is boolean 
	 * 
	 * the UIN is passed for checking*/
	public static boolean addApplicationDetailsCheck(int UIN) {

		boolean isExisting = false;//create a boolean value for returning. set it to false


		try {
			Connection conn = Database.getConnection();//get the conn
			String SQLAppSelect = "";

			try {

				SQLAppSelect = "Select ApplicationID From applicationdetails where ApplicantUIN=?;";//write the query
				PreparedStatement stmt = conn.prepareStatement(SQLAppSelect);
				stmt.setInt(1, UIN);//set the UIN
				ResultSet rs = stmt.executeQuery();//execute the query

				if (rs.first()) {
					//if the application exists for the UIN return true
					return true;
				}

			}
			//catch SQL exception block
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
		return isExisting;

	}

	/*this function mainly calculates the scaled score with the credentials passed for ewach student who fills the application
	 * 
	 * the return tpye is double
	 * 
	 * */
	public static double calculateScaledScore(int UIN, double workExp, boolean skill1, boolean skill2, boolean skill3, boolean skill4, boolean skill5) {

		//declare the scores and initiliaize to 0;
		int skillScore1 = 0;
		int skillScore2 = 0;
		int skillScore3 = 0;
		int skillScore4 = 0;
		int skillScore5 = 0;
		double scaledScore = 0;
		double retreivedGPA = 0.0;
		
		//set the sclaed score for each skill that is selected
		if (skill1)
			skillScore1 = 1;
		if (skill2)
			skillScore2 = 1;
		if (skill3)
			skillScore3 = 1;
		if (skill4)
			skillScore4 = 1;
		if (skill5)
			skillScore5 = 1;

		scaledScore = skillScore1 + skillScore2 + skillScore3 + skillScore4
				+ skillScore5;

		//if the work ex is persent then add the it to the scaled score
		if (workExp >= 1.0 && workExp <= 1.5)
			scaledScore = scaledScore + 0.25;

		if (workExp >= 1.51 && workExp <= 2.0)
			scaledScore = scaledScore + 0.50;

		if (workExp >= 2.01 && workExp <= 2.50)
			scaledScore = scaledScore + 0.75;

		if (workExp > 2.51)
			scaledScore = scaledScore + 1.0;

		// get the GPA factor here

		Student getStudentGPA;
		try {
			getStudentGPA = new Student(UIN);
			retreivedGPA = getStudentGPA.getGPA();
		} catch (People.PersonDoesNotExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//add the retrieved GPA to the scaled score
		
		if (retreivedGPA >= 3.70 && retreivedGPA <= 4.0)
			scaledScore = scaledScore + 4;

		if (retreivedGPA >= 3.60 && retreivedGPA <= 3.69)
			scaledScore = scaledScore + 3.5;

		if (retreivedGPA >= 3.50 && retreivedGPA <= 3.59)
			scaledScore = scaledScore + 3.3;

		if (retreivedGPA >= 3.30 && retreivedGPA <= 3.49)
			scaledScore = scaledScore + 3.15;

		if (retreivedGPA >= 3.00 && retreivedGPA <= 3.29)
			scaledScore = scaledScore + 3.0;

		if (retreivedGPA >= 2.90 && retreivedGPA <= 2.99)
			scaledScore = scaledScore + 2.75;

		if (retreivedGPA >= 2.80 && retreivedGPA <= 2.89)
			scaledScore = scaledScore + 2.55;

		if (retreivedGPA >= 2.70 && retreivedGPA <= 2.79)
			scaledScore = scaledScore + 2.30;

		System.out.println("Scaled score of student is: " + scaledScore);

		return scaledScore;

	}

	/*retreive matching details of the surdent for any new job posted
	 * 
	 * passed are the credentials for the job
	 * 
	 * the return type is a linkedhashmap*/
	public static LinkedHashMap<Integer, Student> retreiveMatchingStudents(double GPA, double workExp, boolean skill1, boolean skill2, boolean skill3, boolean skill4, boolean skill5) {

		LinkedHashMap<Integer, Student> selectedStudents = new LinkedHashMap<Integer, Student>();//initialize a hashmap
		int counter = 0;

		try {
			Connection conn = Database.getConnection();//get the connection
			String SQLPeopleSelect = "";
			try {

				if (conn != null) {
					// set the limit to 10 student retrievals
					SQLPeopleSelect = "Select applicationdetails.ApplicantUIN "
							+ "From university.applicationdetails inner join university.student on "
							+ "student.UIN=applicationdetails.ApplicantUIN where student.GPA>=? and "
							+ "applicationdetails.WorkExperience>=? and applicationdetails.SkillSet1=? and "
							+ "applicationdetails.SkillSet2=? and applicationdetails.SkillSet3=? and "
							+ "applicationdetails.SkillSet4=? and applicationdetails.SkillSet5=? "
							+ "and applicationdetails.ApplicantUIN not in "
							+ "(select UIN from university.employee where employee.UIN=applicationdetails.ApplicantUIN)"
							+ "ORDER BY "
							+ "applicationdetails.Scaledscore DESC LIMIT 10;";
				}

				PreparedStatement stmtForSelect = conn.prepareStatement(SQLPeopleSelect);
				
				// set the values for the query
				
				stmtForSelect.setDouble(1, GPA);
				stmtForSelect.setDouble(2, workExp);
				stmtForSelect.setBoolean(3, skill1);
				stmtForSelect.setBoolean(4, skill2);
				stmtForSelect.setBoolean(5, skill3);
				stmtForSelect.setBoolean(6, skill4);
				stmtForSelect.setBoolean(7, skill5);

				ResultSet rs = stmtForSelect.executeQuery();

				while (rs.next()) {
					//retreive the UINS
					int selectedUIN = rs.getInt("ApplicantUIN");
					counter++;

					Student chosenStudents = new Student(selectedUIN);//pass the UIN to the student constructor to get the details in the object
					selectedStudents.put(chosenStudents.getUIN(),chosenStudents);//add it to the linkedhashmap
					System.out.println(chosenStudents.getUIN());

				}

			}
			//catch block for SQL exception
			catch (SQLException e) {
				System.out.println(e);

			}

		}
		//catch block
		catch (Exception e) {
			System.out.println(e);

		}
		/*The code thats placed in the finally block gets executed no matter what. But 
																	here the finally block does not contain any general statements*/
		finally {

			// System.out.println("retrieved");
		}

		return selectedStudents;
	}

	/*retreive matching details of the student for any new job posted, in this the students who had been previously selected and sent the email will be neglected
	 * 
	 * passed are the credentials for the job
	 * 
	 * the return type is a linkedhashmap*/
	public static LinkedHashMap<Integer, Student> rePost(double GPA, double workExp, boolean skill1, boolean skill2, boolean skill3, boolean skill4, boolean skill5, Job job) {

		LinkedHashMap<Integer, Student> selectedStudents = new LinkedHashMap<Integer, Student>();//initialize a hashmap
		int counter = 0;

		try {
			Connection conn = Database.getConnection();//get the connection
			String SQLPeopleSelect = "";
			try {

				if (conn != null) {

					// set the limit to 10
					SQLPeopleSelect = "Select applicationdetails.ApplicantUIN "
							+ "From university.applicationdetails inner join university.student on "
							+ "student.UIN=applicationdetails.ApplicantUIN where student.GPA>=? and "
							+ "applicationdetails.WorkExperience>=? and applicationdetails.SkillSet1=? and "
							+ "applicationdetails.SkillSet2=? and applicationdetails.SkillSet3=? and "
							+ "applicationdetails.SkillSet4=? and applicationdetails.SkillSet5=? "
							+ "and applicationdetails.ApplicantUIN not in "
							+ "(select UIN from university.employee where employee.UIN=applicationdetails.ApplicantUIN)"
							+ " and applicationdetails.ApplicantUIN not in "
							+ "(select UIN from university.jobroster where applicationdetails.ApplicantUIN=jobroster.UIN and JobID=?)"
							+ "ORDER BY "
							+ "applicationdetails.Scaledscore DESC LIMIT 2;";
				}

				PreparedStatement stmtForSelect = conn.prepareStatement(SQLPeopleSelect);
				
				//set the values for the query
				stmtForSelect.setDouble(1, GPA);
				stmtForSelect.setDouble(2, workExp);
				stmtForSelect.setBoolean(3, skill1);
				stmtForSelect.setBoolean(4, skill2);
				stmtForSelect.setBoolean(5, skill3);
				stmtForSelect.setBoolean(6, skill4);
				stmtForSelect.setBoolean(7, skill5);
				stmtForSelect.setInt(8, job.getJobID());
				System.out.println("++++++++++");

				ResultSet rs = stmtForSelect.executeQuery();//execute the query
				System.out.println("++++++++++");

				while (rs.next()) {
					//retrieve the UIN
					int selectedUIN = rs.getInt("ApplicantUIN");
					counter++;
					Student chosenStudents = new Student(selectedUIN);//pass it to the constructor to get the object details
					selectedStudents.put(chosenStudents.getUIN(),chosenStudents);//add it to the hashmap
					System.out.println(chosenStudents.getUIN());

				}

			}
			//catch block for SQL exception
			catch (SQLException e) {
				System.out.println(e);

			}

		}
		//catch block
		catch (Exception e) {
			System.out.println(e);

		}
		/*The code thats placed in the finally block gets executed no matter what. But 
														here the finally block does not contain any general statements*/
		finally {

			// System.out.println("retrieved");
		}

		return selectedStudents;
	}

	/*the student can update the application details when and when he feels if he has acquired new skills
	 * 
	 * the return type is boolean
	 * 
	 * the passed parameters are the credentials of the job application
	 * 
	 * this also throws an exception*/
	public static boolean updateApplication(int UIN, double workExp, boolean skill1, boolean skill2, boolean skill3, boolean skill4, boolean skill5) throws People.PersonDoesNotExistException {

		boolean check = Student.checkIfStudent(UIN);//check if its a student
		if (!check) {
			throw new People.PersonDoesNotExistException();//throw the exception
		}

		boolean isUpdated = false;

		try {
			Connection conn = Database.getConnection();//get the connection
			String SQLPeopleSelect = "";

			try {
				//write the query
				SQLPeopleSelect = "Select ApplicantUIN From applicationdetails where ApplicantUIN=?;";
				PreparedStatement stmt = conn.prepareStatement(SQLPeopleSelect);
				stmt.setInt(1, UIN);
				ResultSet rs = stmt.executeQuery();//execute the query

				if (rs.first()) {

					double newScaledScore = calculateScaledScore(UIN, workExp, skill1, skill2, skill3, skill4, skill5);//call the scaled score function for reevaluating

					//update query to update the values of the job application

					System.out.println("Updating the application details in the database");
					String SQLupdateAppdetails = "UPDATE university.applicationdetails SET WorkExperience=?, Skillset1=?, Skillset2=?, Skillset3=?, Skillset4=?, Skillset5=?, Scaledscore=? where ApplicantUIN=?;";
					stmt = conn.prepareStatement(SQLupdateAppdetails);
					
					//set the values
					stmt.setDouble(1, workExp);
					stmt.setBoolean(2, skill1);
					stmt.setBoolean(3, skill2);
					stmt.setBoolean(4, skill3);
					stmt.setBoolean(5, skill4);
					stmt.setBoolean(6, skill5);
					stmt.setDouble(7, newScaledScore);
					stmt.setInt(8, UIN);
					System.out.println(stmt);
					int i = stmt.executeUpdate();//execute the query
					System.out.println(i);
					System.out.println("Updated");
					isUpdated = true;

					Database.commitTransaction(conn);//commit the transaction

				}

				else {
					//else the UIN does not exist for updating
					System.out.println(UIN + " does not exist");
				}

			}
			//catch block for SQL exception
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

		return isUpdated;
	}

	/*this function checks if the UIN has an existing application for updating
	 * 
	 * the return type is boolean 
	 * 
	 * if the application exists then return true*/
	public static boolean updateApplicationCheck(int UIN) {

		boolean isExisting = false;

		try {
			Connection conn = Database.getConnection();//get the connection
			String SQLPeopleSelect = "";

			try {
				//write the query
				SQLPeopleSelect = "Select ApplicantUIN From applicationdetails where ApplicantUIN=?;";
				PreparedStatement stmt = conn.prepareStatement(SQLPeopleSelect);
				stmt.setInt(1, UIN);//set the UIN
				ResultSet rs = stmt.executeQuery();//execute the query

				if (rs.first()) {
					//if the application then return true
					return true;
				}

				else {
					//else the UIN application does not exist
					System.out.println(UIN + " application does not exist");

				}

			}
			//catc the SQL exception
			catch (SQLException e) {
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

		return isExisting;
	}

	/*
	 * All job application class functions are run and tested locally in the main class for specific executions
	 */
	public static void main(String[] args) {

	}

}

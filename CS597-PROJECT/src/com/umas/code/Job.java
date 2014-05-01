package com.umas.code;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import com.mysql.jdbc.Statement;

/**
* @author Akshay
* 
*/

/*************** JOB.JAVA CLASS WAS WRITTEN BY AKSHAY THIRKATEH ********************************/

public class Job {
	
	//CLASS VARIABLES USED BY JOB CLASS	
	
	int jobID;
	int postedByUIN;
	private Professor professor;
	String jobInDepartment;
	double reqdMinimumGPA;
	double reqdMinimumWorkExperience;
	boolean skillset1;
	boolean skillset2;
	boolean skillset3;
	boolean skillset4;
	boolean skillset5;

	/* the constructor takes the job ID and then checks for it in the database and the iniitializes all the retrieved values for the jobID
	 * 
	 *it sets the class variable names to the specifics 
	 *
	 *else it displays the job posting not found
	 * */
	public Job(int JobID) {

		try {
			Connection conn = Database.getConnection();//establish the connection to the database
			String SQLPeopleSelect = "";
			try {

				if (conn != null) {

					SQLPeopleSelect = "Select * From jobpostings where JobID=?;";//write the query
				}

				PreparedStatement stmtForSelect = conn.prepareStatement(SQLPeopleSelect);
				stmtForSelect.setInt(1, JobID);//set the job ID

				ResultSet rs = stmtForSelect.executeQuery();//execute the query

				if (rs.first()) {

					DBAnnotation.annoate("retreivedjobID", "jobpostings", "JobID", true);
					int retreivedjobID = rs.getInt("JobID");//--------retrieve the jobID and store it in a local variable
					
					DBAnnotation.annoate("whoPostedUIN", "jobpostings", "PostedByUIN", true);
					int whoPostedUIN = rs.getInt("PostedByUIN");//--------retrieve the posted by UIN and store it in a local variable
					
					DBAnnotation.annoate("jobInDept", "jobpostings", "JobInDepartment", true);
					String jobInDept = rs.getString("JobInDepartment");//--------retrieve the job in dept and store it in a local variable
					
					DBAnnotation.annoate("minimumReqdGPA", "jobpostings", "ReqdMinimumGPA", true);
					double minimumReqdGPA = rs.getDouble("ReqdMinimumGPA");//--------retrieve the reqd gpa and store it in a local variable
					
					DBAnnotation.annoate("minimumReqdWorkExp", "jobpostings", "ReqdMinimumWorkExperience", true);
					double minimumReqdWorkExp = rs.getDouble("ReqdMinimumWorkExperience");//--------retrieve the work ex and store it in a local variable
					
					DBAnnotation.annoate("retreivedSkill1", "jobpostings", "ReqdSkillset1", true);
					boolean retreivedSkill1 = rs.getBoolean("ReqdSkillset1");//--------retrieve the skill1 and store it in a local variable
					
					DBAnnotation.annoate("retreivedSkill2", "jobpostings", "ReqdSkillset2", true);
					boolean retreivedSkill2 = rs.getBoolean("ReqdSkillset2");//--------retrieve the skill2 and store it in a local variable
					
					DBAnnotation.annoate("retreivedSkill3", "jobpostings", "ReqdSkillset3", true);
					boolean retreivedSkill3 = rs.getBoolean("ReqdSkillset3");//--------retrieve the skill3 and store it in a local variable
					
					DBAnnotation.annoate("retreivedSkill4", "jobpostings", "ReqdSkillset4", true);
					boolean retreivedSkill4 = rs.getBoolean("ReqdSkillset4");//--------retrieve the skill4 and store it in a local variable
					
					DBAnnotation.annoate("retreivedSkill5", "jobpostings", "ReqdSkillset5", true);
					boolean retreivedSkill5 = rs.getBoolean("ReqdSkillset5");//--------retrieve the skill5 and store it in a local variable

					/*initialize the retrieved variables as the class variables*/
					
					this.jobID = retreivedjobID;
					this.postedByUIN = whoPostedUIN;
					this.jobInDepartment = jobInDept;
					this.reqdMinimumGPA = minimumReqdGPA;
					this.reqdMinimumWorkExperience = minimumReqdWorkExp;
					this.skillset1 = retreivedSkill1;
					this.skillset2 = retreivedSkill2;
					this.skillset3 = retreivedSkill3;
					this.skillset4 = retreivedSkill4;
					this.skillset5 = retreivedSkill5;
					this.professor = new Professor(this.postedByUIN);
				}

				else {
					//else the job ID does not exist
					System.out.println("Job ID does not exist");

				}

			}
			//The inner catch block catches the SQL exception.
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

	/*post job takes the posted by UIN and the required job posting credentials and then adds it to the job posting table*/
	public static int postJob(int postedByUIN, int jobInDepartment, double reqdMinimumGPA, double reqdMinimumWorkExperience, boolean skillset1, boolean skillset2, boolean skillset3,boolean skillset4, boolean skillset5) throws NoPermissionException {

		// check the level of the person who is posting it
		// ---------> if not prof, then return level exception

		int retreivedJobID = -1;

		boolean checkLevel = checkEligibility(postedByUIN);//check if its prof posting the job

		if (checkLevel) {

			try {
				Connection conn = Database.getConnection();//get the connection
				String SQLJobInsert = "";
				try {

					if (conn != null) {
						//write the query
						SQLJobInsert = "Insert into jobpostings (PostedByUIN,JobInDepartment,ReqdMinimumGPA,"
								+ "ReqdMinimumWorkExperience,"
								+ "ReqdSkillset1,ReqdSkillset2,ReqdSkillset3,ReqdSkillset4,ReqdSkillset5) "
								+ "values (?,?,?,?,?,?,?,?,?);";

						PreparedStatement stmtForSelect = conn.prepareStatement(SQLJobInsert,Statement.RETURN_GENERATED_KEYS);
						
						//set the values for the query 
						
						stmtForSelect.setInt(1, postedByUIN);
						stmtForSelect.setInt(2, jobInDepartment);
						stmtForSelect.setDouble(3, reqdMinimumGPA);
						stmtForSelect.setDouble(4, reqdMinimumWorkExperience);
						stmtForSelect.setBoolean(5, skillset1);
						stmtForSelect.setBoolean(6, skillset2);
						stmtForSelect.setBoolean(7, skillset3);
						stmtForSelect.setBoolean(8, skillset4);
						stmtForSelect.setBoolean(9, skillset5);

						System.out.println(stmtForSelect);
						int i = stmtForSelect.executeUpdate();//execute the query
																		
						DBAnnotation.annoate("postedByUIN", "jobpostings", "PostedByUIN", false);												
						DBAnnotation.annoate("jobInDepartment", "jobpostings", "JobInDepartment", false);												
						DBAnnotation.annoate("reqdMinimumGPA", "jobpostings", "ReqdMinimumGPA", false);												
						DBAnnotation.annoate("reqdMinimumWorkExperience", "jobpostings", "ReqdMinimumWorkExperience", false);						
						DBAnnotation.annoate("skillset1", "jobpostings", "ReqdSkillset1", false);						
						DBAnnotation.annoate("skillset2", "jobpostings", "ReqdSkillset2", false);						
						DBAnnotation.annoate("skillset3", "jobpostings", "ReqdSkillset3", false);												
						DBAnnotation.annoate("skillset4", "jobpostings", "ReqdSkillset4", false);						
						DBAnnotation.annoate("skillset5", "jobpostings", "ReqdSkillset5", false);						
						
						System.out.println(i);

						ResultSet rs = stmtForSelect.getGeneratedKeys();
						if (rs.first()) {
							retreivedJobID = rs.getInt(1);
							System.out.println(retreivedJobID);//retrieve the added Job ID
							System.out.println(i);
							System.out.println("Inserted");
							
							Database.commitTransaction(conn);//commit the transaction
						}

					}

				}
				//catch block for SQL exception
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

		else {
			//if the permision does not peromit then throw this exception
			throw new NoPermissionException();
		}

		// added the job posting to the db

		return retreivedJobID;

	}

	public static boolean updateJob(int postedByUIN, int jobInDepartment, double reqdMinimumGPA, double reqdMinimumWorkExperience, boolean skillset1, boolean skillset2, boolean skillset3, boolean skillset4, boolean skillset5) throws NoPermissionException {

		// check the level of the person who is posting it
		// ---------> if not prof, then return level exception

		boolean isAdded = false;
		int retreivedJobID = -1;

		boolean checkLevel = checkEligibility(postedByUIN);//check whos updaing the job 

		if (checkLevel) {

			try {
				Connection conn = Database.getConnection();//get the conn
				String SQLJobInsert = "";
				try {

					if (conn != null) {
						//write the query
						SQLJobInsert = "UPDATE jobpostings SET ReqdMinimumGPA=?,"
								+ "ReqdMinimumWorkExperience=?,"
								+ "ReqdSkillset1=?,ReqdSkillset2=?,ReqdSkillset3=?,ReqdSkillset4=?,ReqdSkillset5=? "
								+ "where PostedByUIN=?;";

						PreparedStatement stmtForSelect = conn.prepareStatement(SQLJobInsert,Statement.RETURN_GENERATED_KEYS);
						
						//set the values for the query
						stmtForSelect.setDouble(1, reqdMinimumGPA);
						stmtForSelect.setDouble(2, reqdMinimumWorkExperience);
						stmtForSelect.setBoolean(3, skillset1);
						stmtForSelect.setBoolean(4, skillset2);
						stmtForSelect.setBoolean(5, skillset3);
						stmtForSelect.setBoolean(6, skillset4);
						stmtForSelect.setBoolean(7, skillset5);
						stmtForSelect.setInt(8, postedByUIN);

						System.out.println(stmtForSelect);
						int i = stmtForSelect.executeUpdate();//execute the query
																		
						DBAnnotation.annoate("reqdMinimumGPA", "jobpostings", "ReqdMinimumGPA", false);												
						DBAnnotation.annoate("reqdMinimumWorkExperience", "jobpostings", "ReqdMinimumWorkExperience", false);						
						DBAnnotation.annoate("skillset1", "jobpostings", "ReqdSkillset1", false);						
						DBAnnotation.annoate("skillset2", "jobpostings", "ReqdSkillset2", false);						
						DBAnnotation.annoate("skillset3", "jobpostings", "ReqdSkillset3", false);												
						DBAnnotation.annoate("skillset4", "jobpostings", "ReqdSkillset4", false);						
						DBAnnotation.annoate("skillset5", "jobpostings", "ReqdSkillset5", false);	
						DBAnnotation.annoate("postedByUIN", "jobpostings", "PostedByUIN", false);	
						
						
						System.out.println(i);
						ResultSet rs = stmtForSelect.getGeneratedKeys();
						if (rs.first())
							retreivedJobID = rs.getInt(1);//retrieve the added UIN
						System.out.println(retreivedJobID);
						System.out.println(i);
						System.out.println("Updated");
						isAdded = true;

						Database.commitTransaction(conn);//add it to the UIN

						// get the students and put it in the linked hash map

						LinkedHashMap<Integer, Student> retrievedList = JobApplication.retreiveMatchingStudents(reqdMinimumGPA, reqdMinimumWorkExperience, skillset1, skillset2, skillset3, skillset4, skillset5);

						for (Integer k : retrievedList.keySet()) {
							Student s = retrievedList.get(k);
							System.out.println(s.getUIN());


							Database.commitTransaction(conn);//commit the transaction


						}

					}

				}
				//catch thre sql exception
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

		else {

			throw new NoPermissionException();
		}

		// add the job posting to the db

		return isAdded;

	}

	// Checks the eligibility to post--should be an employee to post

	public static boolean checkEligibility(int UIN) {

		boolean check = false;//set the boolean to false

		try {
			Connection conn = Database.getConnection();
			String SQLPeopleSelect = "";
			try {

				if (conn != null) {

					SQLPeopleSelect = "Select PositionID From people where UIN=?;";//write the query
				}

				PreparedStatement stmtForSelect = conn.prepareStatement(SQLPeopleSelect);
				stmtForSelect.setInt(1, UIN);//set the UIN

				ResultSet rs = stmtForSelect.executeQuery();//execute the query

				if (rs.first()) {

					DBAnnotation.annoate("retreivedpositionID", "people", "PositionID", true);
					int retreivedpositionID = rs.getInt("PositionID");
					
					if (retreivedpositionID <= 2 ||retreivedpositionID==5) {
						//if positionID is more than 2 or less than 5 then its not an employee
						check = true;
					}

				}

				else {
					//no UIN exists
					System.out.println("UIN does not exist");

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

		finally {

			// System.out.println("retrieved");
		}
		return check;
	}

	// add to jobRoster adds the students to the job roster table who have already been selected ofr an job and an email has been to.
	//it is added to job roster if the student is being considered, with the offer iD
	public static boolean addToJobRoster(int UIN, int jobiD) {

		boolean isAdded = false;

		try {
			Connection conn = Database.getConnection();//get the connection
			String SQLJobInsert = "";
			try {

				if (conn != null) {

					SQLJobInsert = "Insert into jobRoster (JobID, UIN) values (?,?);";//write the query

					PreparedStatement stmtForSelect = conn.prepareStatement(SQLJobInsert);
					stmtForSelect.setInt(1, jobiD);//set the job iD
					stmtForSelect.setInt(2, UIN);//set the UIN

					System.out.println(stmtForSelect);
					int i = stmtForSelect.executeUpdate();//execute the query
					
					DBAnnotation.annoate("jobiD", "jobroster", "JobID", false);
					DBAnnotation.annoate("UIN", "jobroster", "UIN", false);
					
					System.out.println(i);
					System.out.println("Inserted");

					isAdded = true;
					Database.commitTransaction(conn);//commit the transaction

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

		return isAdded;

	}
	
	//if a student matches the job requirment then send an email to him at his email ID with the job ID and the details
	public static boolean sendEmail(int jobID, Student student) {

		if (student != null) {

			String studentUserName = student.getUserName();//get the user name

			String studentEmail = studentUserName + "@xyz.com";//get the email ID

			//build the email instance
			Email email = Email.getInstance("umas.uic@gmail.com","cs597project");
			String body = "You match our requirments. Please contact us at (xxx) xxx-xxxx for further interview steps";
			String subject = "Job Match";
			String receipients = "" + studentEmail + "";
			boolean ifSent = email.sendEmail(receipients, subject, body);//send the email with the required text

			if (ifSent) {
				boolean addedToJobRoster = addToJobRoster(student.getUIN(),jobID);//add him to the job roster
				if (addedToJobRoster) {
					return true;//if added return true
				} else {
					return false;
				}
			}

		}

		return false;
	}
	/*retrieve all the jobs posted by one single professor
	 * 
	 * the return type is a linked hash map of the job object 
	 * 
	 * the prof object is passed to it*/
	public static LinkedHashMap<Integer, Job> getAllJobsBySingleProfessor(Professor prof) {

		LinkedHashMap<Integer, Job> getAllJobs = new LinkedHashMap<Integer, Job>();//initiaLIZE the job linked hash map

		if (prof == null) {

			throw new NullPointerException();//if the object passed is null then throw exception
		}

		try {
			Connection conn = Database.getConnection();//get the connection
			String SQLPeopleSelect = "";
			try {

				if (conn != null) {

					SQLPeopleSelect = "Select JobID From jobpostings where PostedByUIN=?;";//write the query
				}

				PreparedStatement stmtForSelect = conn.prepareStatement(SQLPeopleSelect);
				stmtForSelect.setInt(1, prof.getUIN());//set the UIN of the prof

				ResultSet rs = stmtForSelect.executeQuery();//execute the query

				while (rs.next()) {

					DBAnnotation.annoate("retreivedjobID", "jobpostings", "JobID", true);
					int retreivedjobID = rs.getInt("JobID");//retrieve the job ID
					
					Job jobs = new Job(retreivedjobID);//send it to the constructor
					
					DBAnnotation.annoate("getJobIDs", "jobpostings", "JobID", true);
					int getJobIDs=jobs.getJobID();

					getAllJobs.put(getJobIDs, jobs);//put it into the linked hash map

				}

			}
			//catch block ofr sql exception
			catch (SQLException e) {
				e.printStackTrace();
				System.out.println(e);

			}

			finally {

				// System.out.println("retrieved");
			}
		}
		//catch block for the exception
		catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);

		}
		/*The code thats placed in the finally block gets executed no matter what. But 
											here the finally block does not contain any general statements*/
		finally {

			// System.out.println("retrieved");
		}

		return getAllJobs;
	}
	
	/*--------------------------------Getters and setters start--------------------------------------------------*/

	public int getJobID() {
		return jobID;
	}

	public void setJobID(int jobID) {
		this.jobID = jobID;
	}

	public int getPostedByUIN() {
		return postedByUIN;
	}

	public void setPostedByUIN(int postedByUIN) {
		this.postedByUIN = postedByUIN;
	}

	public String getJobInDepartment() {
		return jobInDepartment;
	}

	public void setJobInDepartment(String jobInDepartment) {
		this.jobInDepartment = jobInDepartment;
	}

	public double getReqdMinimumGPA() {
		return reqdMinimumGPA;
	}

	public void setReqdMinimumGPA(double reqdMinimumGPA) {
		this.reqdMinimumGPA = reqdMinimumGPA;
	}

	public double getReqdMinimumWorkExperience() {
		return reqdMinimumWorkExperience;
	}

	public void setReqdMinimumWorkExperience(double reqdMinimumWorkExperience) {
		this.reqdMinimumWorkExperience = reqdMinimumWorkExperience;
	}

	public boolean isSkillset1() {
		return skillset1;
	}

	public void setSkillset1(boolean skillset1) {
		this.skillset1 = skillset1;
	}

	public boolean isSkillset2() {
		return skillset2;
	}

	public void setSkillset2(boolean skillset2) {
		this.skillset2 = skillset2;
	}

	public boolean isSkillset3() {
		return skillset3;
	}

	public void setSkillset3(boolean skillset3) {
		this.skillset3 = skillset3;
	}

	public boolean isSkillset4() {
		return skillset4;
	}

	public void setSkillset4(boolean skillset4) {
		this.skillset4 = skillset4;
	}

	public boolean isSkillset5() {
		return skillset5;
	}

	public void setSkillset5(boolean skillset5) {
		this.skillset5 = skillset5;
	}

	/*--------------------------------Getters and setters start--------------------------------------------------*/
	
	
	//new exceptions added for appropriate usage
	public static class NoPermissionException extends Exception {
		private static final long serialVersionUID = 1L;
		private String message = null;

		public NoPermissionException() {
			super();
			this.message = "You do not have the permission ";
		}

		public NoPermissionException(String message) {
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

	//new exceptions added for appropriate usage
	static class NotAddedToJobRosterException extends Exception {
		private static final long serialVersionUID = 1L;
		private String message = null;

		public NotAddedToJobRosterException() {
			super();
			this.message = "Not Added to job roster ";
		}

		public NotAddedToJobRosterException(String message) {
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
	 * All job functions add, update, retrieve are specified in the this class
	 * 
	 * local main class is used for testing functions and specific executions
	 */
	public static void main(String[] args) {



	}

}

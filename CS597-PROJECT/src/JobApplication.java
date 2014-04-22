import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.List;

import javax.xml.crypto.Data;

public class JobApplication {

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

	public JobApplication(int UIN) {

		try {
			Connection conn = Database.getConnection();

			try {

				if (conn != null) {

					SQLPeopleSelect = "Select * From applicationdetails where ApplicantUIN=?;";
				}

				PreparedStatement stmtForSelect = conn
						.prepareStatement(SQLPeopleSelect);
				stmtForSelect.setInt(1, UIN);

				ResultSet rs = stmtForSelect.executeQuery();

				if (rs.first()) {

					int RetrievedUIN = rs.getInt("ApplicantUIN");
					Double RetrievedWorkEx = rs.getDouble("WorkExperience");
					boolean retreivedSkill1 = rs.getBoolean("Skillset1");
					boolean retreivedSkill2 = rs.getBoolean("Skillset2");
					boolean retreivedSkill3 = rs.getBoolean("Skillset3");
					boolean retreivedSkill4 = rs.getBoolean("Skillset4");
					boolean retreivedSkill5 = rs.getBoolean("Skillset5");
					float retreivedScaledScore = rs.getFloat("Scaledscore");

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

					System.out.println("job application for " + UIN
							+ " does not exist");

				}

			}

			catch (SQLException e) {
				e.printStackTrace();
				System.out.println(e);

			}

			finally {

				// System.out.println("retrieved");
			}
		}

		catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);

		}

		finally {

			// System.out.println("retrieved");
		}

	}

	public static boolean addApplicationDetails(int UIN, double workExp,
			boolean skill1, boolean skill2, boolean skill3, boolean skill4,
			boolean skill5) {

		boolean isAdded = false;
		try {
			Connection conn = Database.getConnection();

			try {

				boolean isExisting = addApplicationDetailsCheck(UIN);

				if (isExisting) {
					return false;
					// Insert a update query to update the values of the
					// database....NOT ADD
				}

				else {
					double getScore = calculateScaledScore(UIN, workExp,
							skill1, skill2, skill3, skill4, skill5);

					System.out.println("Adding new data into the database");
					String SQLPeopleInsert = "Insert into applicationdetails (applicantUIN, WorkExperience, Skillset1, Skillset2, Skillset3, Skillset4, Skillset5, Scaledscore) Values (?,?,?,?,?,?,?,?);";
					PreparedStatement stmt = conn
							.prepareStatement(SQLPeopleInsert);
					stmt.setInt(1, UIN);
					stmt.setDouble(2, workExp);
					stmt.setBoolean(3, skill1);
					stmt.setBoolean(4, skill2);
					stmt.setBoolean(5, skill3);
					stmt.setBoolean(6, skill4);
					stmt.setBoolean(7, skill5);
					stmt.setDouble(8, getScore);
					System.out.println(stmt);
					int i = stmt.executeUpdate();
					System.out.println(i);
					System.out.println("Inserted");

					Database.commitTransaction(conn);
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

	public static boolean addApplicationDetailsCheck(int UIN) {

		boolean isExisting = false;

		try {
			Connection conn = Database.getConnection();
			String SQLAppSelect = "";

			try {

				SQLAppSelect = "Select ApplicationID From applicationdetails where ApplicantUIN=?;";
				PreparedStatement stmt = conn.prepareStatement(SQLAppSelect);
				stmt.setInt(1, UIN);
				ResultSet rs = stmt.executeQuery();

				if (rs.first()) {

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

	public static double calculateScaledScore(int UIN, double workExp,
			boolean skill1, boolean skill2, boolean skill3, boolean skill4,
			boolean skill5) {

		int skillScore1 = 0;
		int skillScore2 = 0;
		int skillScore3 = 0;
		int skillScore4 = 0;
		int skillScore5 = 0;
		double scaledScore = 0;

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

		if (workExp >= 1.0 && workExp <= 1.5)
			scaledScore = scaledScore + 0.25;

		if (workExp >= 1.51 && workExp <= 2.0)
			scaledScore = scaledScore + 0.50;

		if (workExp >= 2.01 && workExp <= 2.50)
			scaledScore = scaledScore + 0.75;

		if (workExp > 2.51)
			scaledScore = scaledScore + 1.0;

		// get the GPA factor here

		Student getStudentGPA = new Student(UIN);
		double retreivedGPA = getStudentGPA.getGPA();

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

	// retreive matching details
	public static LinkedHashMap<Integer, Student> retreiveMatchingStudents(
			double GPA, double workExp, boolean skill1, boolean skill2,
			boolean skill3, boolean skill4, boolean skill5) {

		LinkedHashMap<Integer, Student> selectedStudents = new LinkedHashMap<Integer, Student>();
		// Hashtable addSelectedStudents = new Hashtable();
		int counter = 0;

		try {
			Connection conn = Database.getConnection();
			String SQLPeopleSelect = "";
			try {

				if (conn != null) {
					// change the limit to 10
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

				PreparedStatement stmtForSelect = conn
						.prepareStatement(SQLPeopleSelect);
				stmtForSelect.setDouble(1, GPA);
				stmtForSelect.setDouble(2, workExp);
				stmtForSelect.setBoolean(3, skill1);
				stmtForSelect.setBoolean(4, skill2);
				stmtForSelect.setBoolean(5, skill3);
				stmtForSelect.setBoolean(6, skill4);
				stmtForSelect.setBoolean(7, skill5);

				ResultSet rs = stmtForSelect.executeQuery();

				while (rs.next()) {

					int selectedUIN = rs.getInt("ApplicantUIN");
					// double scaledScore=rs.getDouble("Scaledscore");
					counter++;

					Student chosenStudents = new Student(selectedUIN);
					selectedStudents.put(chosenStudents.getUIN(),
							chosenStudents);
					System.out.println(chosenStudents.getUIN());

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

		return selectedStudents;
	}

	public static LinkedHashMap<Integer, Student> rePost(double GPA,
			double workExp, boolean skill1, boolean skill2, boolean skill3,
			boolean skill4, boolean skill5, Job job) {

		LinkedHashMap<Integer, Student> selectedStudents = new LinkedHashMap<Integer, Student>();
		// Hashtable addSelectedStudents = new Hashtable();
		int counter = 0;

		try {
			Connection conn = Database.getConnection();
			String SQLPeopleSelect = "";
			try {

				if (conn != null) {

					System.out.println("++++++++++");
					// change the limit to 10
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

				PreparedStatement stmtForSelect = conn
						.prepareStatement(SQLPeopleSelect);
				stmtForSelect.setDouble(1, GPA);
				stmtForSelect.setDouble(2, workExp);
				stmtForSelect.setBoolean(3, skill1);
				stmtForSelect.setBoolean(4, skill2);
				stmtForSelect.setBoolean(5, skill3);
				stmtForSelect.setBoolean(6, skill4);
				stmtForSelect.setBoolean(7, skill5);
				stmtForSelect.setInt(8, job.getJobID());
				System.out.println("++++++++++");

				ResultSet rs = stmtForSelect.executeQuery();
				System.out.println("++++++++++");

				while (rs.next()) {

					int selectedUIN = rs.getInt("ApplicantUIN");
					// System.out.println(selectedUIN);
					// double scaledScore=rs.getDouble("Scaledscore");
					counter++;

					Student chosenStudents = new Student(selectedUIN);
					selectedStudents.put(chosenStudents.getUIN(),
							chosenStudents);
					System.out.println(chosenStudents.getUIN());

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

		return selectedStudents;
	}

	public static boolean updateApplication(int UIN, double workExp,
			boolean skill1, boolean skill2, boolean skill3, boolean skill4,
			boolean skill5) {

		boolean isUpdated = false;

		try {
			Connection conn = Database.getConnection();
			String SQLPeopleSelect = "";

			try {

				SQLPeopleSelect = "Select ApplicantUIN From applicationdetails where ApplicantUIN=?;";
				PreparedStatement stmt = conn.prepareStatement(SQLPeopleSelect);
				stmt.setInt(1, UIN);
				ResultSet rs = stmt.executeQuery();

				if (rs.first()) {

					double newScaledScore = calculateScaledScore(UIN, workExp,
							skill1, skill2, skill3, skill4, skill5);

					// Insert a update query to update the values of the
					// database....NOT ADD
					System.out
							.println("Updating the application details in the database");
					String SQLupdateAppdetails = "UPDATE university.applicationdetails SET WorkExperience=?, Skillset1=?, Skillset2=?, Skillset3=?, Skillset4=?, Skillset5=?, Scaledscore=? where ApplicantUIN=?;";
					stmt = conn.prepareStatement(SQLupdateAppdetails);
					stmt.setDouble(1, workExp);
					stmt.setBoolean(2, skill1);
					stmt.setBoolean(3, skill2);
					stmt.setBoolean(4, skill3);
					stmt.setBoolean(5, skill4);
					stmt.setBoolean(6, skill5);
					stmt.setDouble(7, newScaledScore);
					stmt.setInt(8, UIN);
					System.out.println(stmt);
					int i = stmt.executeUpdate();
					System.out.println(i);
					System.out.println("Updated");
					isUpdated = true;

					// Connection conn=Database.getConnection();
					Database.commitTransaction(conn);

				}

				else {
					System.out.println(UIN + " does not exist");

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

		return isUpdated;
	}

	public static boolean updateApplicationCheck(int UIN) {

		boolean isExisting = false;

		try {
			Connection conn = Database.getConnection();
			String SQLPeopleSelect = "";

			try {

				SQLPeopleSelect = "Select ApplicantUIN From applicationdetails where ApplicantUIN=?;";
				PreparedStatement stmt = conn.prepareStatement(SQLPeopleSelect);
				stmt.setInt(1, UIN);
				ResultSet rs = stmt.executeQuery();

				if (rs.first()) {
					return true;

				}

				else {
					System.out.println(UIN + " does not exist");

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

	public static void main(String[] args) {

		// Job job=new Job(22);
		// updateApplication(520, 4.0, true, true, true, true, false);
		// rePost(1.5, 1.5, false, true, true, true, false, job);

		// addApplicationDetails(519, 2.5, true, false, true, true, false);
		// addApplicationDetails(520, 3.0, true, true, true, true, false);
		// addApplicationDetails(521, 2.5, false, false, true, false, false);
		// addApplicationDetails(522, 2.5, true, true, false, true, false);
		// addApplicationDetails(523, 2.5, false, false, true, true, false);
		// addApplicationDetails(524, 2.5, true, true, true, true, false);
		// addApplicationDetails(525, 2.5, true, true, false, true, false);
		// addApplicationDetails(526, 2.5, false, false, true, true, false);
		// addApplicationDetails(527, 2.5, true, true, false, true, false);
		// addApplicationDetails(528, 2.5, false, true, true, true, false);

		JobApplication app = new JobApplication(520);
		System.out.println(app.getWorkEx());

	}

}

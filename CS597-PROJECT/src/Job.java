import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import com.mysql.jdbc.Statement;


public class Job {
	
	int jobID;
	int postedByUIN;
	String jobInDepartment;
	double reqdMinimumGPA;
	double reqdMinimumWorkExperience;
	boolean skillset1;
	boolean skillset2;
	boolean skillset3;
	boolean skillset4;
	boolean skillset5;
	
	
	public Job(int JobID){
		
		try{
			Connection conn = Database.getConnection();
			String SQLPeopleSelect="";
			try{
			
				if(conn != null){
					
					SQLPeopleSelect = "Select * From jobpostings where JobID=?;";
				}
				
				
				
				PreparedStatement stmtForSelect = conn.prepareStatement(SQLPeopleSelect);
				stmtForSelect.setInt(1, JobID);
				
				ResultSet rs =  stmtForSelect.executeQuery();
					
					if(rs.first())
					{
						
						 int retreivedjobID = rs.getInt("JobID"); 
						 int whoPostedUIN = rs.getInt("PostedByUIN");
				         String jobInDept = rs.getString("JobInDepartment");
				         double minimumReqdGPA=rs.getDouble("ReqdMinimumGPA");
				         double minimumReqdWorkExp=rs.getDouble("ReqdMinimumWorkExperience");
				         boolean retreivedSkill1=rs.getBoolean("ReqdSkillset1");
				         boolean retreivedSkill2=rs.getBoolean("ReqdSkillset2");
				         boolean retreivedSkill3=rs.getBoolean("ReqdSkillset3");
				         boolean retreivedSkill4=rs.getBoolean("ReqdSkillset4");
				         boolean retreivedSkill5=rs.getBoolean("ReqdSkillset5");
				        
				     
						
				         this.jobID=retreivedjobID;
				         this.postedByUIN=whoPostedUIN;
				         this.jobInDepartment=jobInDept;
				         this.reqdMinimumGPA=minimumReqdGPA;
				         this.reqdMinimumWorkExperience=minimumReqdWorkExp;
				         this.skillset1=retreivedSkill1;
				         this.skillset2=retreivedSkill2;
				         this.skillset3=retreivedSkill3;
				         this.skillset4=retreivedSkill4;
				         this.skillset5=retreivedSkill5;
					}
					
					else
					{
						
						System.out.println("Job ID does not exist");

					}
					
				
			
		
	}
			
			catch(SQLException e){
				e.printStackTrace();
				System.out.println(e);
				
			}
			
			finally{
				
				//System.out.println("retrieved");
			}
		}
		
		catch(Exception e){
			e.printStackTrace();
			System.out.println(e);
			
		}
		
		finally{
			
			//System.out.println("retrieved");
		}
		
		
		
	}
	
	public static int postJob(int postedByUIN, int jobInDepartment, double reqdMinimumGPA, double reqdMinimumWorkExperience, 
			boolean skillset1, boolean skillset2, boolean skillset3, boolean skillset4, boolean skillset5) throws NoPermissionException{
		
		//check the level of the person who is posting it
		//---------> if not prof, then return level exception
		
		boolean isAdded=false;
		int retreivedJobID=-1;
		ArrayList<Student> listOfEmails=new ArrayList<>();
		
		boolean checkLevel=checkEligibility(postedByUIN);
		
		if(checkLevel){
			
			try{
				Connection conn = Database.getConnection();
				String SQLJobInsert="";
				try{
				
					if(conn != null){
						
						SQLJobInsert = "Insert into jobpostings (PostedByUIN,JobInDepartment,ReqdMinimumGPA," +
								"ReqdMinimumWorkExperience," +
								"ReqdSkillset1,ReqdSkillset2,ReqdSkillset3,ReqdSkillset4,ReqdSkillset5) " +
								"values (?,?,?,?,?,?,?,?,?);";
					
					
					
					
					PreparedStatement stmtForSelect = conn.prepareStatement(SQLJobInsert, Statement.RETURN_GENERATED_KEYS);
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
					int i = stmtForSelect.executeUpdate();
					System.out.println(i);
					
					ResultSet rs = stmtForSelect.getGeneratedKeys();
					if(rs.first()){
						retreivedJobID=rs.getInt(1);
						System.out.println(retreivedJobID);
						System.out.println(i);
						System.out.println("Inserted");
						isAdded=true;	
						Database.commitTransaction(conn);
					}
											
					}
		
		}
				
				catch(SQLException e){
					e.printStackTrace();
					System.out.println(e);
					
				}
				
				finally{
					
					//System.out.println("retrieved");
				}
			}
			
			catch(Exception e){
				e.printStackTrace();
				System.out.println(e);
				
			}
			
			finally{
				
				//System.out.println("retrieved");
			}
			
			
		
			
		}
		
		else{
			
			throw new NoPermissionException();
		}
		
		
		
		// add the job posting to the db
		
		//retreive the students----call the function here.
		
		return retreivedJobID;
		
		
	}
	
	public static boolean updateJob(int postedByUIN, int jobInDepartment, double reqdMinimumGPA, double reqdMinimumWorkExperience, 
			boolean skillset1, boolean skillset2, boolean skillset3, boolean skillset4, boolean skillset5) throws NoPermissionException{
		
		//check the level of the person who is posting it
		//---------> if not prof, then return level exception
		
		boolean isAdded=false;
		int retreivedJobID=-1;
		ArrayList<Student> listOfEmails=new ArrayList<>();
		
		boolean checkLevel=checkEligibility(postedByUIN);
		
		if(checkLevel){
			
			try{
				Connection conn = Database.getConnection();
				String SQLJobInsert="";
				try{
				
					if(conn != null){
						
						SQLJobInsert = "UPDATE jobpostings SET ReqdMinimumGPA=?," +
								"ReqdMinimumWorkExperience=?," +
								"ReqdSkillset1=?,ReqdSkillset2=?,ReqdSkillset3=?,ReqdSkillset4=?,ReqdSkillset5=? " +
								"where PostedByUIN=?;";
					
					
					
					
					PreparedStatement stmtForSelect = conn.prepareStatement(SQLJobInsert, Statement.RETURN_GENERATED_KEYS);
					stmtForSelect.setDouble(1, reqdMinimumGPA);
					stmtForSelect.setDouble(2, reqdMinimumWorkExperience);
					stmtForSelect.setBoolean(3, skillset1);
					stmtForSelect.setBoolean(4, skillset2);
					stmtForSelect.setBoolean(5, skillset3);
					stmtForSelect.setBoolean(6, skillset4);
					stmtForSelect.setBoolean(7, skillset5);
					stmtForSelect.setInt(8, postedByUIN);
					
					System.out.println(stmtForSelect);
					int i = stmtForSelect.executeUpdate();
					System.out.println(i);
					ResultSet rs = stmtForSelect.getGeneratedKeys();
					if(rs.first())
						retreivedJobID=rs.getInt(1);
					System.out.println(retreivedJobID);
					System.out.println(i);
					System.out.println("Updated");
					isAdded=true;
					
					Database.commitTransaction(conn);

					
					
					
					// get the students and put it in the job roster
					
					LinkedHashMap<Integer,Student> retrievedList = JobApplication.retreiveMatchingStudents(reqdMinimumGPA, reqdMinimumWorkExperience, skillset1, skillset2, skillset3, skillset4, skillset5);
					
					for(Integer k:retrievedList.keySet()){
						Student s=retrievedList.get(k);
						System.out.println(s.getUIN());
						
						//listOfEmails.add(student);

						// add to job roster only if email is sent.
						
						//boolean ifadded=addToJobRoster(s.getUIN(),retreivedJobID);
						Database.commitTransaction(conn);
						
						//if(ifadded){
							//System.out.println("added to job roster");
					//	}
						//else{
							
							//throw new NotAddedToJobRosterException();
							
						//}
						
					}
					
					}	
					
					
					
					
					
					
		}
				
				catch(SQLException e){
					e.printStackTrace();
					System.out.println(e);
					
				}
				
				finally{
					
					//System.out.println("retrieved");
				}
			}
			
			catch(Exception e){
				e.printStackTrace();
				System.out.println(e);
				
			}
			
			finally{
				
				//System.out.println("retrieved");
			}
			
			
		
			
		}
		
		else{
			
			throw new NoPermissionException();
		}
		
		
		
		// add the job posting to the db
		
		//retreive the students----call the function here.
		
		return isAdded;	
		
		
	}
	
	// Checks the eligibility to post
	
	public static boolean checkEligibility(int UIN){
		
		boolean check=false;
		
		try{
			Connection conn = Database.getConnection();
			String SQLPeopleSelect="";
			try{
			
				if(conn != null){
					
					SQLPeopleSelect = "Select PositionID From people where UIN=?;";
				}
				
				
				
				PreparedStatement stmtForSelect = conn.prepareStatement(SQLPeopleSelect);
				stmtForSelect.setInt(1, UIN);
				
				ResultSet rs =  stmtForSelect.executeQuery();
					
					if(rs.first())
					{
						
						 int retreivedpositionID = rs.getInt("PositionID"); 
						 
						 if(retreivedpositionID<=2){
							 
							 check=true;
						 }
						 
				        
					}
					
					else
					{
						
						System.out.println("UIN does not exist");

					}
					
				
			
		
	}
			
			catch(SQLException e){
				e.printStackTrace();
				System.out.println(e);
				
			}
			
			finally{
				
				//System.out.println("retrieved");
			}
		}
		
		catch(Exception e){
			e.printStackTrace();
			System.out.println(e);
			
		}
		
		finally{
			
			//System.out.println("retrieved");
		}
		return check;
	}
	
	// add to jobRoster
	
	public static boolean addToJobRoster(int UIN,int jobiD){
		
		boolean isAdded=false;
		
		try{
			Connection conn = Database.getConnection();
			String SQLJobInsert="";
			try{
			
				if(conn != null){
					
					SQLJobInsert = "Insert into jobRoster (JobID, UIN) values (?,?);";
				
				
				
				
				PreparedStatement stmtForSelect = conn.prepareStatement(SQLJobInsert);
				stmtForSelect.setInt(1, jobiD);
				stmtForSelect.setInt(2, UIN);
				
				System.out.println(stmtForSelect);
				int i = stmtForSelect.executeUpdate();
				System.out.println(i);
				System.out.println("Inserted");
				
				isAdded=true;
				Database.commitTransaction(conn);

					
				}
			}
				

			
			catch(SQLException e){
				e.printStackTrace();
				System.out.println(e);
				
			}
			
			finally{
				
				//System.out.println("retrieved");
			}
		}
		
		catch(Exception e){
			e.printStackTrace();
			System.out.println(e);
			
		}
		
		finally{
			
			//System.out.println("retrieved");
		}

		
		
		return isAdded;

	}
	
	
	public static boolean sendEmail(int jobID, Student student){
		
		if(student!=null){
		
		String studentUserName=student.getUserName();
		
		String studentEmail=studentUserName+"@xyz.com";
		
		Email email = Email.getInstance("umas.uic@gmail.com", "cs597project");
		String body = "You match our requirments. Please contact us at (xxx) xxx-xxxx for further interview steps";
		String subject = "Job Match";
		String receipients = ""+studentEmail+"";
		boolean ifSent=email.sendEmail(receipients, subject, body);
		
		if(ifSent){
			boolean addedToJobRoster=addToJobRoster(student.getUIN(), jobID);
				if(addedToJobRoster){
					return true;
				}
				else{
					return false;
				}
		}
				
	}
		
		
		return false;
		}
	
	public static LinkedHashMap<Integer,Job> getAllJobsBySingleProfessor(Professor prof){
		
		LinkedHashMap<Integer, Job> getAllJobs=new LinkedHashMap<Integer, Job>();
		
		if(prof==null){
			
			throw new NullPointerException();
		}
		
		try{
			Connection conn = Database.getConnection();
			String SQLPeopleSelect="";
			try{
			
				if(conn != null){
					
					SQLPeopleSelect = "Select JobID From jobpostings where PostedByUIN=?;";
				}
				
				
				
				PreparedStatement stmtForSelect = conn.prepareStatement(SQLPeopleSelect);
				stmtForSelect.setInt(1, prof.getUIN());
				
				ResultSet rs =  stmtForSelect.executeQuery();
					
					while(rs.next())
					{
						
						 int retreivedjobID = rs.getInt("JobID"); 
						 Job jobs=new Job(retreivedjobID);
						 
						 getAllJobs.put(jobs.getJobID(), jobs);
					
	
				    
					}
					
				
			
		
	}
			
			catch(SQLException e){
				e.printStackTrace();
				System.out.println(e);
				
			}
			
			finally{
				
				//System.out.println("retrieved");
			}
		}
		
		catch(Exception e){
			e.printStackTrace();
			System.out.println(e);
			
		}
		
		finally{
			
			//System.out.println("retrieved");
		}
		
		
		
		
		
	
		
		return getAllJobs;
	}
	
	
	
	
	
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





	public static class NoPermissionException extends Exception{
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
	
	static class NotAddedToJobRosterException extends Exception{
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
	
	
		
	public static void main(String[] args){
		
		//try {
//			postJob(272, 1, 2.0, 1.0, true, true, true, true, false);
//			postJob(272, 1, 2.5, 1.0, true, true, true, true, false);
//			postJob(272, 2, 2.0, 1.0, false, true, false, true, true);
//			postJob(273, 2, 2.5, 1.5, true, true, true, true, true);
//			postJob(274, 3, 2.0, 1.0, true, false, true, true, true);
//			postJob(274, 3, 2.5, 1.0, false, true, false, true, true);
//			postJob(275, 4, 3.3, 3.0, true, false, true, true, true);
//			postJob(276, 4, 2.5, 3.0, false, true, true, true, true);
//			postJob(275, 5, 3.0, 1.0, false, true, false, true, true);
//			postJob(274, 8, 1.5, 3.0, true, false, true, true, true);
//			postJob(272, 10, 1.0, 2.0, false, true, true, true, false);
			
			
			
			
			
			
			
			
			
			
			
			
//		} catch (NoPermissionException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
	}
		
		
		
		
		
	
	

}

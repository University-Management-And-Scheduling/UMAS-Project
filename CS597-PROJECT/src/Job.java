import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
	
	public static boolean postJob(int postedByUIN, int jobInDepartment, double reqdMinimumGPA, double reqdMinimumWorkExperience, 
			boolean skillset1, boolean skillset2, boolean skillset3, boolean skillset4, boolean skillset5) throws NoPermissionException{
		
		//check the level of the person who is posting it
		//---------> if not prof, then return level exception
		
		boolean isAdded=false;
		int retreivedJobID=-1;
		
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
					if(rs.first())
						retreivedJobID=rs.getInt(1);
					System.out.println(retreivedJobID);
					System.out.println(i);
					System.out.println("Inserted");
					isAdded=true;

					
					
					
					// get the students and put it in the job roster
					
					ArrayList<Student> retrievedList = JobApplication.retreiveMatchingStudents(reqdMinimumGPA, reqdMinimumWorkExperience, skillset1, skillset2, skillset3, skillset4, skillset5);
					
					for(Student student:retrievedList){
						System.out.println(student.getUIN());
						
						boolean ifadded=addToJobRoster(student.getUIN(),retreivedJobID);
						Database.commitTransaction(conn);
						
						if(ifadded){
							System.out.println("added to job roster");
						}
						else{
							
							throw new NotAddedToJobRosterException();
							
						}
						
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
	
	
	
	static class NoPermissionException extends Exception{
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
		
		try {
			postJob(11, 2, 2.0, 1.0, true, true, true, true, true);
		} catch (NoPermissionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
		
		
		
		
		
	
	

}

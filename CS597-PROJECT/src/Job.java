import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Job {
	
	int JobID;
	int PostedByUIN;
	String JobInDepartment;
	double ReqdMinimumGPA;
	double reqdMinimumWorkExperiance;
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
					
					SQLPeopleSelect = "Select ApplicantUIN, WorkExperience, Skillset1, Skillset2, Skillset3, Skillset4, Skillset5, Scaledscore From applicationdetails where ApplicantUIN=?;";
				}
				
				
				
				PreparedStatement stmtForSelect = conn.prepareStatement(SQLPeopleSelect);
				stmtForSelect.setInt(1, UIN);
				
				ResultSet rs =  stmtForSelect.executeQuery();
					
					if(rs.first())
					{
						
						 int RetrievedUIN = rs.getInt("ApplicantUIN");
				         int RetrievedWorkEx = rs.getInt("WorkExperience");
				         boolean retreivedSkill1=rs.getBoolean("Skillset1");
				         boolean retreivedSkill2=rs.getBoolean("Skillset2");
				         boolean retreivedSkill3=rs.getBoolean("Skillset3");
				         boolean retreivedSkill4=rs.getBoolean("Skillset4");
				         boolean retreivedSkill5=rs.getBoolean("Skillset5");
				         float retreivedScaledScore=rs.getFloat("Scaledscore");
				     
						
				         this.UIN=RetrievedUIN;
				         this.workEx=RetrievedWorkEx;
				         this.skill1=retreivedSkill1;
				         this.skill2=retreivedSkill2;
				         this.skill3=retreivedSkill3;
				         this.skill4=retreivedSkill4;
				         this.skill5=retreivedSkill5;
				         this.scaledScore=retreivedScaledScore;
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
		
		
		
	}

}

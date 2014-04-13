import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class JobApplication {
	
	protected int ApplicationID;
	protected int UIN;
	protected int workEx;
	protected boolean skill1;
	protected boolean skill2;
	protected boolean skill3;
	protected boolean skill4;
	protected boolean skill5;
	protected float scaledScore;
	
	public JobApplication(int UIN){
	
		try{
			Connection conn = Database.getConnection();
			String SQLPeopleSelect="";
			try{
			
				if(conn != null){
					
					SQLPeopleSelect = "Select ApplicantUIN, WorkExperience, Skillset1, Skillset2, Skillset3, Skillset4, Skillset5, Scaledscore From applicationdetails where UIN=?;";
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
	
	public static void addApplicationDetails(int UIN, int workExp, boolean skill1, boolean skill2, boolean skill3, boolean skill4, boolean skill5){
		

		try{
			Connection conn = Database.getConnection();
			String SQLPeopleSelect="";
			
			try{
				
				SQLPeopleSelect = "Select UIN From applicationdetails where Username=?;";
				PreparedStatement stmt = conn.prepareStatement(SQLPeopleSelect);
				stmt.setInt(1, UIN);
				ResultSet rs =  stmt.executeQuery();
				
					if(rs.first()){
				         System.out.println(UIN+"already exists. Please choose another user name");
				         //Insert a update query to update the values of the database....NOT ADD
					}
					
					else
					{
						
						System.out.println("Adding new data into the database");
						String SQLPeopleInsert= "Insert into applicationdetails (applicantUIN, WorkExperience, Skillset1, Skillset1, Skillset1, Skillset1, Skillset1, Scaledscore) Values (?,?,?,?,?,?,?,?);";
						stmt = conn.prepareStatement(SQLPeopleInsert);
						stmt.setInt(1,UIN );
						stmt.setInt(1,workExp );
						stmt.setBoolean(3, skill1);
						stmt.setBoolean(4, skill2);
						stmt.setBoolean(5, skill3);
						stmt.setBoolean(6, skill4);
						stmt.setBoolean(7, skill5);
						stmt.setFloat(8, (float) 0.0);
						System.out.println(stmt);
						int i = stmt.executeUpdate();
						System.out.println(i);
						System.out.println("Inserted");
						
					}
					
			}
			
			catch(SQLException e){
				System.out.println("Error adding/updating to database");
				e.printStackTrace();
				System.out.println(e);	
			}
			
			finally{
				//System.out.println("retrieved");
				//Database.closeConnection(conn);
			}
		}
		
		catch(Exception e){
			System.out.println("Connection failed");
			e.printStackTrace();
			System.out.println(e);
			
		}
		
		finally{
			
			//System.out.println("retrieved");
		}
		
		
	}

}


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.xml.crypto.Data;


public class Student extends People {

	public Student(int UIN) {
		super(UIN);
		// TODO Auto-generated constructor stub
	}

	public Student(String name, String userName, int deptID, int positionID) {
		super(name, userName, deptID, 3);
		// TODO Auto-generated constructor stub
	}

	public Student(String userName) {
		super(userName);
		// TODO Auto-generated constructor stub
	}
	

	public void addStudentToDb(){
		
		
		try{
			Connection conn = new Database().getConnection();
			String SQLPeopleSelect="";
			
			try{
				
				SQLPeopleSelect = "Select UIN, Name, Username, DepartmentID, PositionID From People where Username=?;";
				PreparedStatement stmt = conn.prepareStatement(SQLPeopleSelect);
				stmt.setString(1, this.getUserName());
				ResultSet rs =  stmt.executeQuery();
				
					if(rs.first()){
				         System.out.println(this.getUserName()+"already exists. Please choose another user name");
				         //Insert a update query to update the values of the database....NOT ADD
					}
					
					else
					{
						
						System.out.println("Adding new data into the database");
						String SQLPeopleInsert= "Insert into People (Name, Username, DepartmentID, PositionID) Values (?,?,?,?);";
						stmt = conn.prepareStatement(SQLPeopleInsert);
						stmt.setString(1, this.getName());
						stmt.setString(2, this.getUserName());
						stmt.setInt(3, this.getDeptID());
						stmt.setInt(4, this.getPositionID());
						System.out.println(stmt);
						int i = stmt.executeUpdate();
						System.out.println(i);
						
						//The UIN returned is 0. execute the select function here to get his UIN
						System.out.println("Inserted: "+getUIN()+" "+getName()+" "+getUserName()+" "+getDeptID()+" "+getPositionID());
						
					}
					
			}
			
			catch(SQLException e){
				System.out.println("Error adding/updating to database");
				System.out.println(e);	
			}
			
			finally{
				//System.out.println("retrieved");
				Database.closeConnection(conn);
			}
		}
		
		catch(Exception e){
			System.out.println("Connection failed");
			System.out.println(e);
			
		}
		
		finally{
			
			//System.out.println("retrieved");
		}
		
		
	}

	public static boolean checkIfStudent(int UIN){
		
		try{
			Connection conn = new Database().getConnection();
			String SQLPeopleSelect="";
			try{
			
				if(conn != null){
					
					SQLPeopleSelect = "Select PositionID From People where UIN=?;";
				}
				
				
				
				PreparedStatement stmtForSelect = conn.prepareStatement(SQLPeopleSelect);
				stmtForSelect.setInt(1, UIN);
				
				ResultSet rs =  stmtForSelect.executeQuery();
					
					if(rs.first())
					{
						
				         int peopleRetrievedPositionID = rs.getInt("PositionID");
				         System.out.println("UIN:"+UIN+" Position ID:"+peopleRetrievedPositionID);
				         
				         
				         if(peopleRetrievedPositionID == 3){
				        	 System.out.println("Student UIN exists");
				        	 return true;
				         }
				         else 
				         {
				        	 System.out.println("UIN exists, but it is not a student");
					return false;
						
				         }
				         

				         
					}
					
					else
					{
						
						System.out.println("UIN does not exist");
						return false;

					}
					
				
			
		
	}
			
			catch(SQLException e){
				System.out.println(e);
				
			}
			
			finally{
				
				//System.out.println("retrieved");
				Database.closeConnection(conn);
			}
		}
		
		catch(Exception e){
			System.out.println(e);
			
		}
		
		finally{
			
			//System.out.println("retrieved");
		}		
		
		return false;
	}

}

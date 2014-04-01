import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Professor extends People {
	
	
	//prof constructor
	public Professor(String name, String userName, int deptID){
		super(name, userName, deptID, 2);	
	}
	
	public Professor(int UIN){
		super(UIN);
	}
		
	//prof adding
	public void addProfToDb(){
		
		
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

	
	//AKSHAY PLEASE CHECK IF THE PERSON WHOS UIN IS INPUT IS A PROFESSOR OR NOR
	public static boolean checkIfProfessor(int UIN){
		
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
				         System.out.println("Professor UIN exists");
				         return true;

				         
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
	
	public static boolean checkIfProfessor(String userName){
		
		try{
			Connection conn = new Database().getConnection();
			String SQLPeopleSelect="";
			try{
			
				if(conn != null){
					
					SQLPeopleSelect = "Select PositionID From People where Username=?;";
				}
				
				
				
				PreparedStatement stmtForSelect = conn.prepareStatement(SQLPeopleSelect);
				stmtForSelect.setString(1, userName);
				
				ResultSet rs =  stmtForSelect.executeQuery();
					
					if(rs.first())
					{
						
				         int peopleRetrievedPositionID = rs.getInt("PositionID");
				         System.out.println("Professor username exists");
				         return true;

				         
					}
					
					else
					{
						
						System.out.println("username does not exist");
						return false;

					}
					
				
			
		
	}
			
			catch(SQLException e){
				System.out.println(e);
				
			}
			
			finally{
				
				//System.out.println("retrieved");
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
	
	//SAME APPLIES TO THE NAME RETRIEVAL AND OTHER FUNCTIONS
	
	//prof retrieval by UIN
	public static void retrieveProfDetailsByUIN(int UIN){
		
		boolean check=checkIfProfessor(UIN);
		
		if(check==true)
		{
		retireveDetailsByUIN(12);
		}
		
		else
		{
			System.out.println("There exists no professor with that UIN");
		}
		
	}
	
	//prof retrieval by userName
	public static void retrieveProfDetailsByUserName(String userName){
		
		boolean check=checkIfProfessor(userName);
		
		if(check==true)
		{
		retireveDetailsByuserName(userName);
		}
		else
		{
			System.out.println("There exists no professor with that username");
		}
		
		
	}
	
	//prof deletion	by UIN
	public static void deleteProfFromDbUsingUIN(int UIN){
		
		boolean check=checkIfProfessor(UIN);
		
		if(check==true)
		{
		deleteFromDatabaseByUIN(UIN);
		}
		else
		{
			System.out.println("There exists no professor with that UIN");	
		}
		
		
	}
	
	//prof deletion	by username	
	public static void deleteProfFromDbUsingUserName(String userName){
		
boolean check=checkIfProfessor(userName);
		
		if(check==true)
		{
		deleteFromDatabaseByUserName(userName);
		}
		else
		{
			System.out.println("There exists no professor with that username");
		}
	}
		
	//add files
	
	//add exams
	
	//grade exams
	
	public static void main(String[] args){
		
		//Professor x = new Professor("priyanka", "maravapa", 2);
		
		//x.addProfToDb();
		
		//checkIfProfessor("maravapa");
		
		
		//People.
		
		
	}

}


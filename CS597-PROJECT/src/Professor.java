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

	//prof retrieval by UIN
	//AKSHAY PLEASE CHECK IF THE PERSON WHOS UIN IS INPUT IS A PROFESSOR OR NOR
	//SAME APPLIES TO THE NAME RETRIEVAL AND OTHER FUNCTIONS
	public static void retrieveProfDetailsByUIN(int UIN){
		
		retireveDetailsByUIN(12);
		
	}
	
	//prof retrieval by userName
	public static void retrieveProfDetailsByUserName(String userName){
		
		retireveDetailsByuserName(userName);
		
	}
	
	//prof deletion	by UIN
	public static void deleteProfFromDbUsingUIN(int UIN){
		
		deleteFromDatabaseByUIN(UIN);
	}
	
	//prof deletion	by username	
	public static void deleteProfFromDbUsingUserName(String userName){
		
		deleteFromDatabaseByUserName(userName);
	}
		
	//add files
	
	//add exams
	
	//grade exams
	
	public static void main(String[] args){
		
		//Professor x = new Professor("priyanka", "maravapa", 2);
		
		//x.addProfToDb();
		
		
		//People.
		
		
	}

}

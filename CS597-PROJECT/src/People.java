import java.io.ObjectInputStream.GetField;
import java.rmi.server.UID;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;

//import com.mysql.jdbc.Connection;




public class People {
	
	protected int UIN;
	protected String name;
	protected String userName;
	protected int deptID;
	protected int positionID;
	
	public People(String name, String userName, int deptID, int positionID)
	{
		this.name=name;
		this.userName=userName;
		this.deptID=deptID;
		this.positionID=positionID;
				
	}
	
	public People(int UIN){
		
		try{
			Connection conn = new Database().getConnection();
			String SQLPeopleSelect="";
			try{
			
				if(conn != null){
					
					SQLPeopleSelect = "Select UIN, Name, Username, DepartmentID, PositionID From People where UIN=?;";
				}
				
				
				
				PreparedStatement stmtForSelect = conn.prepareStatement(SQLPeopleSelect);
				stmtForSelect.setInt(1, UIN);
				
				ResultSet rs =  stmtForSelect.executeQuery();
					
					if(rs.first())
					{
						
						int peopleRetrievedUIN = rs.getInt("UIN");
				         String peopleRetrievedName = rs.getString("Name");
				         String peopleRetrieveduserName = rs.getString("UserName");
				         int peopleRetrievedDeptID = rs.getInt("DepartmentID");
				         int peopleRetrievedPositionID = rs.getInt("PositionID");
				     
						
				         this.UIN=peopleRetrievedUIN;
				         this.name=peopleRetrievedName;
				         this.userName=peopleRetrieveduserName;
				         this.deptID=peopleRetrievedDeptID;
				         this.positionID=peopleRetrievedPositionID;
				         
				         System.out.println(peopleRetrievedUIN);
				         System.out.println(peopleRetrievedName);
				         System.out.println(peopleRetrieveduserName);
				         System.out.println(peopleRetrievedDeptID);
				         System.out.println(peopleRetrievedPositionID);
					}
					
					else
					{
						
						System.out.println("UIN does not exist");

					}
					
				
			
		
	}
			
			catch(SQLException e){
				System.out.println(e);
				
			}
			
			finally{
				
				System.out.println("retrieved");
			}
		}
		
		catch(Exception e){
			System.out.println(e);
			
		}
		
		finally{
			
			System.out.println("retrieved");
		}
		
}
	
	public People(String userName){
	
	try{
		Connection conn = new Database().getConnection();
		String SQLPeopleSelect="";
		try{
		
			if(conn != null){
				
				SQLPeopleSelect = "Select UIN, Name, Username, DepartmentID, PositionID From People where Username=?;";
			}
			
			
			
			PreparedStatement stmtForSelect = conn.prepareStatement(SQLPeopleSelect);
			stmtForSelect.setString(1, userName);
			
			ResultSet rs =  stmtForSelect.executeQuery();
				
				if(rs.first())
				{
					
					int peopleRetrievedUIN = rs.getInt("UIN");
			         String peopleRetrievedName = rs.getString("Name");
			         String peopleRetrieveduserName = rs.getString("UserName");
			         int peopleRetrievedDeptID = rs.getInt("DepartmentID");
			         int peopleRetrievedPositionID = rs.getInt("PositionID");
			     
					
			         this.UIN=peopleRetrievedUIN;
			         this.name=peopleRetrievedName;
			         this.userName=peopleRetrieveduserName;
			         this.deptID=peopleRetrievedDeptID;
			         this.positionID=peopleRetrievedPositionID;
			         
			         System.out.println(peopleRetrievedUIN);
			         System.out.println(peopleRetrievedName);
			         System.out.println(peopleRetrieveduserName);
			         System.out.println(peopleRetrievedDeptID);
			         System.out.println(peopleRetrievedPositionID);
				}
				
				else
				{
					
					System.out.println("UIN does not exist");

				}
				
			
		
	
}
		
		catch(SQLException e){
			System.out.println(e);
			
		}
		
		finally{
			
			System.out.println("retrieved");
		}
	}
	
	catch(Exception e){
		System.out.println(e);
		
	}
	
	finally{
		
		System.out.println("retrieved");
	}
	
}

	public int getUIN() {
		return UIN;
	}

	public void setUIN(int uIN) {
		UIN = uIN;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getDeptID() {
		return deptID;
	}

	public void setDeptID(int deptID) {
		this.deptID = deptID;
	}

	public int getPositionID() {
		return positionID;
	}

	public void setPositionID(int positionID) {
		this.positionID = positionID;
	}
		
	public void addIntoDatabase()
	{
		
		
		try{
			Connection conn = new Database().getConnection();
			String SQLPeopleSelect="";
			
			try{
				
				SQLPeopleSelect = "Select UIN, Name, Username, DepartmentID, PositionID From People where Username=?;";
				PreparedStatement stmt = conn.prepareStatement(SQLPeopleSelect);
				stmt.setString(1, this.userName);
				ResultSet rs =  stmt.executeQuery();
				
					if(rs.first()){
				         System.out.println(this.userName+"already exists. Please choose another user name");
				         //Insert a update query to update the values of the database....NOT ADD
					}
					
					else
					{
						
						System.out.println("Adding new data into the database");
						String SQLPeopleInsert= "Insert into People (Name, Username, DepartmentID, PositionID) Values (?,?,?,?);";
						stmt = conn.prepareStatement(SQLPeopleInsert);
						//statement.setInt(1, 2);
						stmt.setString(1, this.getName());
						stmt.setString(2, this.getUserName());
						stmt.setInt(3, this.getDeptID());
						stmt.setInt(4, this.getPositionID());
						System.out.println(stmt);
						int i = stmt.executeUpdate();
						System.out.println(i);
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
	
	public static void deleteFromDatabaseByUIN(int UIN){
		
		try{
			Connection conn = new Database().getConnection();
			String SQLPeopleSelect="";
			String SQLPeopleDelete="";
			try{
				if(conn != null){	
				SQLPeopleSelect = "Select UIN, Name, Username, DepartmentID, PositionID From People where UIN=?;";
				PreparedStatement stmt = conn.prepareStatement(SQLPeopleSelect);
				stmt.setInt(1, UIN);
				ResultSet rs =  stmt.executeQuery();
				
				

					if(rs.first())
					{	
				         //this.UIN=peopleRetrievedUIN;
				         //this.name=peopleRetrievedName;
				         //this.userName=peopleRetrieveduserName;
				         //this.deptID=peopleRetrievedDeptID;
				         //this.positionID=peopleRetrievedPositionID;
						
						int peopleRetrievedUIN = rs.getInt("UIN");
						String peopleRetrievedName = rs.getString("Name");
						String peopleRetrieveduserName = rs.getString("UserName");
						int peopleRetrievedDeptID = rs.getInt("DepartmentID");
						int peopleRetrievedPositionID = rs.getInt("PositionID");
				         
//				         System.out.println(peopleRetrievedUIN);
//				         System.out.println(peopleRetrievedName);
//				         System.out.println(peopleRetrieveduserName);
//				         System.out.println(peopleRetrievedDeptID);
//				         System.out.println(peopleRetrievedPositionID);
						
						SQLPeopleDelete = "Delete From People where UIN=?;";
						stmt = conn.prepareStatement(SQLPeopleDelete);
						stmt.setInt(1, UIN);
						int rs1=stmt.executeUpdate();
						System.out.println(peopleRetrievedUIN+ " is deleted");
						//System.out.println(rs1);
						
					}
					
					else
					{
						
						System.out.println("UIN does not exist");

					}
				}
					
}
			
			catch(SQLException e){
				System.out.println("Error trying to access the database");
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
	
	public static void deleteFromDatabaseByUserName(String userName){
		
		try{
			Connection conn = new Database().getConnection();
			String SQLPeopleSelect="";
			String SQLPeopleDelete="";
			try{
				if(conn != null){	
				SQLPeopleSelect = "Select UIN, Name, Username, DepartmentID, PositionID From People where Username=?;";
				PreparedStatement stmt = conn.prepareStatement(SQLPeopleSelect);
				stmt.setString(1, userName);
				ResultSet rs =  stmt.executeQuery();
				
				

					if(rs.first())
					{	
				         //this.UIN=peopleRetrievedUIN;
				         //this.name=peopleRetrievedName;
				         //this.userName=peopleRetrieveduserName;
				         //this.deptID=peopleRetrievedDeptID;
				         //this.positionID=peopleRetrievedPositionID;
						
						int peopleRetrievedUIN = rs.getInt("UIN");
						String peopleRetrievedName = rs.getString("Name");
						String peopleRetrieveduserName = rs.getString("UserName");
						int peopleRetrievedDeptID = rs.getInt("DepartmentID");
						int peopleRetrievedPositionID = rs.getInt("PositionID");
				         
//				         System.out.println(peopleRetrievedUIN);
//				         System.out.println(peopleRetrievedName);
//				         System.out.println(peopleRetrieveduserName);
//				         System.out.println(peopleRetrievedDeptID);
//				         System.out.println(peopleRetrievedPositionID);
						
						SQLPeopleDelete = "Delete From People where Username=?;";
						stmt = conn.prepareStatement(SQLPeopleDelete);
						stmt.setString(1, userName);
						int rs1=stmt.executeUpdate();
						System.out.println(peopleRetrievedUIN+ " is deleted");
						//System.out.println(rs1);
						
					}
					
					else
					{
						
						System.out.println("UIN does not exist");

					}
				}
					
}
			
			catch(SQLException e){
				System.out.println("Error trying to access the database");
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

	
	/*DONT JUST PRINT THE RETREIVAL DATA. PLEASE SET THE INSTANCE VALUES IN THE OBJECT OF THE CLASS AND IF POSSIBLE
	RETURN THE OBJECT SO THAT WE CAN JUST PASS THE NAME AND GET A OBJECT FOR US TO USE*/
	/*ALSO I WOULD SUGGEST ADDING EXCEPTIONS AND THROWING THEM WHEN PEOPLE BEING SEARCHED FOR IS NOT FOUND*/
	public static void retireveDetailsByUIN(int UIN){
		
		try{
			Connection conn = new Database().getConnection();
			String SQLPeopleSelect="";
			try{
			
				if(conn != null){
					
					SQLPeopleSelect = "Select UIN, Name, Username, DepartmentID, PositionID From People where UIN=?;";
				}
				
				
				
				PreparedStatement stmtForSelect = conn.prepareStatement(SQLPeopleSelect);
				stmtForSelect.setInt(1, UIN);
				
				ResultSet rs =  stmtForSelect.executeQuery();
					
					if(rs.first())
					{
						
						int peopleRetrievedUIN = rs.getInt("UIN");
				         String peopleRetrievedName = rs.getString("Name");
				         String peopleRetrieveduserName = rs.getString("UserName");
				         int peopleRetrievedDeptID = rs.getInt("DepartmentID");
				         int peopleRetrievedPositionID = rs.getInt("PositionID");
				         
				         System.out.println(peopleRetrievedUIN);
				         System.out.println(peopleRetrievedName);
				         System.out.println(peopleRetrieveduserName);
				         System.out.println(peopleRetrievedDeptID);
				         System.out.println(peopleRetrievedPositionID);
					}
					
					else
					{
						
						System.out.println("UIN does not exist");

					}
					
				
			
		
	}
			
			catch(SQLException e){
				System.out.println(e);
				
			}
			
			finally{
				
				System.out.println("retrieved");
			}
		}
		
		catch(Exception e){
			System.out.println(e);
			
		}
		
		finally{
			
			System.out.println("retrieved");
		}
		

		
	}
	
	public static void retireveDetailsByuserName(String userName){
		
		try{
			Connection conn = new Database().getConnection();
			String SQLPeopleSelect="";
			try{
			
				if(conn != null){
					
					SQLPeopleSelect = "Select UIN, Name, Username, DepartmentID, PositionID From People where Username=?;";
				}
				
				
				
				PreparedStatement stmtForSelect = conn.prepareStatement(SQLPeopleSelect);
				stmtForSelect.setString(1, userName);
				
				ResultSet rs =  stmtForSelect.executeQuery();
					
					if(rs.first())
					{
						
						int peopleRetrievedUIN = rs.getInt("UIN");
				         String peopleRetrievedName = rs.getString("Name");
				         String peopleRetrieveduserName = rs.getString("UserName");
				         int peopleRetrievedDeptID = rs.getInt("DepartmentID");
				         int peopleRetrievedPositionID = rs.getInt("PositionID");
				         
				         System.out.println(peopleRetrievedUIN);
				         System.out.println(peopleRetrievedName);
				         System.out.println(peopleRetrieveduserName);
				         System.out.println(peopleRetrievedDeptID);
				         System.out.println(peopleRetrievedPositionID);
					}
					
					else
					{
						
						System.out.println("UIN does not exist");

					}
					
				
			
		
	}
			
			catch(SQLException e){
				System.out.println(e);
				
			}
			
			finally{
				
				System.out.println("retrieved");
			}
		}
		
		catch(Exception e){
			System.out.println(e);
			
		}
		
		finally{
			
			System.out.println("retrieved");
		}
		

		
	}

	public static void main(String[] args)
	{
		//People peopleObj=new People("akshay", "athirk2", 2, 1);
		//People peopleObj1=new People("mrinal", "mrinal", 2, 1);
		//peopleObj1.addIntoDatabase();
		
		//People peopleobj3=new People();
		//People peopleObj2=new People(3);
		
		
		//People.deleteFromDatabase(5);
		//People.deleteFromDatabase(6);
		
		People.retireveDetailsByuserName("maravapa");
		
	}
	
	
}
			
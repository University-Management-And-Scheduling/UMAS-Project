import java.io.ObjectInputStream.GetField;
import java.rmi.server.UID;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.util.Random;

import com.mysql.jdbc.Statement;

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
			Connection conn = Database.getConnection();
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
				         
				         //System.out.println(peopleRetrievedUIN);
				         //System.out.println(peopleRetrievedName);
				         //System.out.println(peopleRetrieveduserName);
				         //System.out.println(peopleRetrievedDeptID);
				         //System.out.println(peopleRetrievedPositionID);
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
				
				//System.out.println("retrieved");
			}
		}
		
		catch(Exception e){
			System.out.println(e);
			
		}
		
		finally{
			
			//System.out.println("retrieved");
		}
		
}
	
	public People(String userName){
	
	try{
		Connection conn = Database.getConnection();
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
			         
			         //System.out.println(peopleRetrievedUIN);
			         //System.out.println(peopleRetrievedName);
			         //System.out.println(peopleRetrieveduserName);
			         //System.out.println(peopleRetrievedDeptID);
			         //System.out.println(peopleRetrievedPositionID);
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
			
			//System.out.println("retrieved");
		}
	}
	
	catch(Exception e){
		System.out.println(e);
		
	}
	
	finally{
		
		//System.out.println("retrieved");
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
	
	//generate random passwords
	public static String generatePassword(int length){
		String alphabet = 
		        new String("0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz"); //9
		int n = alphabet.length(); //10

		String result = new String(); 
		Random r = new Random(); //11

		for (int i=0; i<length; i++) //12
		    result = result + alphabet.charAt(r.nextInt(n)); //13

		return result;
		}
	
	
	// checking if auto generated username already exists function
	public static boolean checkIfUserNameExists(String userName){
		
		try{
			Connection conn = Database.getConnection();
			String SQLPeopleSelect="";
			try{
				if(conn != null){	
				SQLPeopleSelect = "Select Username From People where Username=?;";
				PreparedStatement stmt = conn.prepareStatement(SQLPeopleSelect);
				stmt.setString(1, userName);
				ResultSet rs =  stmt.executeQuery();
				
				

					if(rs.first())
					{	
						
						String peopleRetrieveduserName = rs.getString("UserName");
						System.out.println(peopleRetrieveduserName);
						return true;
						
					}
					
					else
					{
						
						System.out.println("Username does not exist");
						return false;

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
		
	return false;
	}
	
	// adding the user to the login table with auto generated username and password
	public static void addUserDetailsIntoLoginTable(String userName, String Password){
		
		
		try{
			Connection conn = Database.getConnection();
			String SQLPeopleSelect="";
			
			try{
				
				SQLPeopleSelect = "Select Username From logindetails where Username=?;";
				PreparedStatement stmt = conn.prepareStatement(SQLPeopleSelect);
				stmt.setString(1, userName);
				ResultSet rs =  stmt.executeQuery();
				
					if(rs.first()){
				         System.out.println(userName+"already exists. Please choose another user name");
				         //Insert a update query to update the values of the database....NOT ADD
					}
					
					else
					{
						
						System.out.println("Adding new data into the database");
						String SQLPeopleInsert= "Insert into logindetails (Username, Password) Values (?,?);";
						stmt = conn.prepareStatement(SQLPeopleInsert);
						stmt.setString(1, userName);
						stmt.setString(2, Password);
						System.out.println(stmt);
						int i = stmt.executeUpdate();
						System.out.println(i);
						System.out.println("Inserted");
						
					}
					
			}
			
			catch(SQLException e){
				System.out.println("Error adding/updating to database");
				System.out.println(e);	
			}
			
			finally{
				//System.out.println("retrieved");
				//Database.closeConnection(conn);
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
	
		
	public static int addIntoDatabase(String name, Department dept, int positionId)
	{
		
		//generate a user name from name
		String userName="";
		int addedUIN=0;
		int lengthOfName=name.length();
		if(lengthOfName<=5){
			name=userName;
			
		}
		else
			{
				userName=name.substring(0, 5);
			}
		
				//check if it already exists 
		
				//put in a while loop and generate new
		
		while(checkIfUserNameExists(userName))
		{
			Random randomNumber=new Random();
			userName=userName+randomNumber.nextInt(99);
		}
		
		
		//generate a random password
		String randomPassword=generatePassword(8);		
		
		//add into login details
		addUserDetailsIntoLoginTable(userName, randomPassword);
		
		//add into people table 
		
		//change the return type
		
		
		
		try{
			Connection conn = Database.getConnection();
			String SQLPeopleSelect="";
			
			try{
				
				SQLPeopleSelect = "Select UIN, Name, Username, DepartmentID, PositionID From People where Username=?;";
				PreparedStatement stmt = conn.prepareStatement(SQLPeopleSelect);
				stmt.setString(1, userName);
				ResultSet rs =  stmt.executeQuery();
				
				
					if(rs.first()){
				         System.out.println(userName+"already exists. Please choose another user name");
				         //Insert a update query to update the values of the database....NOT ADD
					}
					
					else
					{
						
						System.out.println("Adding new data into the database");
						String SQLPeopleInsert= "Insert into People (Name, Username, DepartmentID, PositionID) Values (?,?,?,?);";
						stmt = conn.prepareStatement(SQLPeopleInsert, Statement.RETURN_GENERATED_KEYS);
						//statement.setInt(1, 2);
						stmt.setString(1, name);
						stmt.setString(2, userName);
						stmt.setInt(3, dept.getDepartmentID());
						stmt.setInt(4, positionId);
						System.out.println(stmt);
						int i = stmt.executeUpdate();
						rs = stmt.getGeneratedKeys();
						if(rs.first())
							addedUIN=rs.getInt(1);
						System.out.println(rs.getInt(1));
						System.out.println(i);
						//Database.commitTransaction(conn);
					}
					
			}
			
			catch(SQLException e){
				System.out.println("Error adding/updating to database");
				e.printStackTrace();
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
		
	return addedUIN;
		
	}
	
	public static void deleteFromDatabaseByUIN(int UIN){
		
		try{
			Connection conn = Database.getConnection();
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
			Connection conn = Database.getConnection();
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

	
	/* SETTING THE INSTANCE VALUES IN THE OBJECT OF THE CLASS
	RETURNING THE OBJECT SO THAT WE CAN JUST PASS THE NAME AND GET A OBJECT FOR US TO USE*/
	/* ADDING EXCEPTIONS AND THROWING THEM WHEN PEOPLE BEING SEARCHED FOR IS NOT FOUND*/
	public static People retireveDetailsByUIN(int UIN) throws PersonDoesNotExistException{
		
		try{
			Connection conn = Database.getConnection();
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
						
						//int peopleRetrievedUIN = rs.getInt("UIN");
				         //String peopleRetrievedName = rs.getString("Name");
				         //String peopleRetrieveduserName = rs.getString("UserName");
				         //int peopleRetrievedDeptID = rs.getInt("DepartmentID");
				         //int peopleRetrievedPositionID = rs.getInt("PositionID");
						People peopleDetails=new People(UIN);
						return peopleDetails;
				         
				         //System.out.println(peopleRetrievedUIN);
				         //System.out.println(peopleRetrievedName);
				         //System.out.println(peopleRetrieveduserName);
				         //System.out.println(peopleRetrievedDeptID);
				         //System.out.println(peopleRetrievedPositionID);
					}
					
					else
					{
						
						System.out.println("UIN does not exist");
						return null;

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
		

		return null;
	}
	
	public static People retireveDetailsByuserName(String userName){
		
		try{
			Connection conn = Database.getConnection();
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
						
//						int peopleRetrievedUIN = rs.getInt("UIN");
//				         String peopleRetrievedName = rs.getString("Name");
//				         String peopleRetrieveduserName = rs.getString("UserName");
//				         int peopleRetrievedDeptID = rs.getInt("DepartmentID");
//				         int peopleRetrievedPositionID = rs.getInt("PositionID");
//				         
//				         System.out.println(peopleRetrievedUIN);
//				         System.out.println(peopleRetrievedName);
//				         System.out.println(peopleRetrieveduserName);
//				         System.out.println(peopleRetrievedDeptID);
//				         System.out.println(peopleRetrievedPositionID);
						
						People peopleDetails=new People(userName);
						return peopleDetails;
						
					}
					
					else
					{
						
						System.out.println("UIN does not exist");
						return null;

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
		
		
		return null;
		
	}

	static class PersonDoesNotExistException extends Exception{
		private static final long serialVersionUID = 1L;
		private String message = null;
		 
	    public PersonDoesNotExistException() {
	        super();
	        this.message = "Person does not exist";
	    }
	    
	    public PersonDoesNotExistException(String message) {
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
	
	
	public static void main(String[] args)
	{
		//People peopleObj=new People("akshay", "athirk2", 2, 1);
		//People peopleObj1=new People("pooja", "mrina1", 2, 1);
		//peopleObj1.addIntoDatabase();
		
		//People peopleobj3=new People();
		//People peopleObj2=new People(3);
		
		
		//People.deleteFromDatabase(5);
		//People.deleteFromDatabase(6);
		
		//People.retireveDetailsByuserName("maravapa");
		
	}
	
	
}
			
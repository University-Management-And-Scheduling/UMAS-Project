
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
	

	public static void addStudentToDb(String name, Department dept,int level){
		
		if(dept==null){
			throw new NullPointerException();
		}
		
		int addedUIN= addIntoDatabase(name, dept, 3);
		System.out.println(addedUIN);
		System.out.println(level);

		try {
			addIntoStudentTable(addedUIN, level);
		} catch (levelNotExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		Connection conn = Database.getConnection();
		Database.commitTransaction(conn);
		
	
		
		
	}
	
	public static void addIntoStudentTable(int UIN, int level) throws levelNotExistException{
		
		if(level>3 || level<0){
			throw new levelNotExistException();
		}
		
		try{
			Connection conn = Database.getConnection();
			String SQLPeopleSelect="";
			
			try{
				
				SQLPeopleSelect = "Select UIN From student where UIN=?;";
				PreparedStatement stmt = conn.prepareStatement(SQLPeopleSelect);
				stmt.setInt(1, UIN);
				ResultSet rs =  stmt.executeQuery();
				
					if(rs.first()){
				         System.out.println(UIN+"already exists");
				         //Insert a update query to update the values of the database....NOT ADD
					}
					
					else
					{
						
						System.out.println("Adding new data into the database");
						String SQLPeopleInsert= "Insert into student (UIN, GPA, Level) Values (?,?,?);";
						stmt = conn.prepareStatement(SQLPeopleInsert);
						stmt.setInt(1, UIN);
						stmt.setFloat(2, (float) 4.0);
						stmt.setInt(3, level);
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
		
	public static void deleteStudent(int UIN){
		
		
		
		
	}
	
	public static void deleteStudent(String userName){
		
		
		
		
	}
	
	
	
	public static boolean checkIfStudent(int UIN){
		
		try{
			Connection conn = Database.getConnection();
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
				e.printStackTrace();
				System.out.println(e);
				
			}
			
			finally{
				
				//System.out.println("retrieved");
				Database.closeConnection(conn);
			}
		}
		
		catch(Exception e){
			System.out.println(e);
			e.printStackTrace();
			
		}
		
		finally{
			
			//System.out.println("retrieved");
		}		
		
		return false;
	}
	
	static class levelNotExistException extends Exception{
		private static final long serialVersionUID = 1L;
		private String message = null;
		 
	    public levelNotExistException() {
	        super();
	        this.message = "level does not exist";
	    }
	    
	    public levelNotExistException(String message) {
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
			Department dept=new Department(2);
			addStudentToDb("arihant", dept,1);
		} catch (Department.DepartmentDoesNotExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}



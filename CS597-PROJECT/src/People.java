import java.io.ObjectInputStream.GetField;
import java.rmi.server.UID;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;

//import com.mysql.jdbc.Connection;


public class People {
	
	private int UIN;
	private String name;
	private String userName;
	private int deptID;
	private int positionID;
	
	public People(int UIN, String name, String userName, int deptID, int positionID)
	{
		
		this.UIN=UIN;
		this.name=name;
		this.userName=userName;
		this.deptID=deptID;
		this.positionID=positionID;
		
		addIntoDatabase();
				
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
			Connection conn = Database.getConnection();
			try{
			
				if(conn != null){
					String SQLPeopleInsert= "Insert into Department Values (?,?);";
					String SQLPeopleSelect= "Select UIN, Name, Username, DepartmentID, PositionID From People;";
					
					// For SQLPeopleInsert
					
					PreparedStatement statement = conn.prepareStatement(SQLPeopleInsert);
					statement.setInt(1, UIN);
					statement.setString(2, name);
//					statement.setString(3, userName);
//					statement.setInt(4, deptID);
//					statement.setInt(5, positionID);
					
					System.out.println(statement);
					statement = conn.prepareStatement(SQLPeopleInsert);
					statement.executeUpdate();
					Database.commitTransaction(conn);
					
					// For SQLPeopleSelect
		
//					statement = conn.prepareStatement(SQLPeopleSelect);
//					ResultSet rs =  statement.executeQuery();
//					while(rs.next()){
//				         //Retrieve by column name
//				         int PeopleRetrievedUIN = rs.getInt("UIN");
//				         String PeopleRetrievedName = rs.getString("Name");
//				         String PeopleRetrieveduserName = rs.getString("UserName");
//				         int peopleRetrievedDeptID = rs.getInt("DepartmentID");
//				         int peopleRetrievedPositionID = rs.getInt("PositionID");
//				         
//				         System.out.println(PeopleRetrievedUIN);
//				         System.out.println(PeopleRetrievedName);
//				         System.out.println(PeopleRetrieveduserName);
//				         System.out.println(peopleRetrievedDeptID);
//				         System.out.println(peopleRetrievedPositionID);
				         //Student student = new Student(studentRetrievedUIN, studentRetrievedName,studentRetrievedDeptID);
				         //studentList.add(student);
					//}      
				}
			} 	catch(SQLException e){
				System.out.println(e);
				//Database.rollBackTransaction(conn);
			}
			
			finally{
				//Database.closeConnection(conn);
			}
		
	} catch(Exception e){
		System.out.println(e);
		
	}
	
	finally{
		//Database.closeConnection(conn);
	}
	
}
	

	public static void main(String[] args)
	{
		People peopleObj=new People(1, "akshay", "athirk2", 001, 100);
		
		//peopleObj.addIntoDatabase();
	}
	
	
}
			
		 
	
		
	
	
	
	//1. add students to the database
	



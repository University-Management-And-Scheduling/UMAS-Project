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
	
	public People(String name, String userName, int deptID, int positionID)
	{
		
		this.UIN=UIN;
		this.name=name;
		this.userName=userName;
		this.deptID=deptID;
		this.positionID=positionID;
		
		addIntoDatabase();
				
	}
	
	public People(int UIN){
		
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
			
			try{
			
				if(conn != null){
					String SQLPeopleInsert= "Insert into People (Name, Username, DepartmentID, PositionID) Values (?,?,?,?);";
					String SQLPeopleSelect= "Select UIN, Name, Username, DepartmentID, PositionID From People;";
					
					// For SQLPeopleInsert
					
					PreparedStatement statement = conn.prepareStatement(SQLPeopleInsert);
					//statement.setInt(1, 2);
					statement.setString(1, name);
					statement.setString(2, userName);
					statement.setInt(3, deptID);
					statement.setInt(4, positionID);
					
					System.out.println(statement);
					int i = statement.executeUpdate();
					System.out.println(i);
					//Database.commitTransaction(conn);
					
					// For SQLPeopleSelect
		
					statement = conn.prepareStatement(SQLPeopleSelect);
					ResultSet rs =  statement.executeQuery();
					while(rs.next()){
				         //Retrieve by column name
				         int PeopleRetrievedUIN = rs.getInt("UIN");
				         String PeopleRetrievedName = rs.getString("Name");
				         String PeopleRetrieveduserName = rs.getString("UserName");
				         int peopleRetrievedDeptID = rs.getInt("DepartmentID");
				         int peopleRetrievedPositionID = rs.getInt("PositionID");
				         
				         System.out.println(PeopleRetrievedUIN);
				         System.out.println(PeopleRetrievedName);
				         System.out.println(PeopleRetrieveduserName);
				         System.out.println(peopleRetrievedDeptID);
				         System.out.println(peopleRetrievedPositionID);
				         //Student student = new Student(studentRetrievedUIN, studentRetrievedName,studentRetrievedDeptID);
				         //studentList.add(student);
					}      
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
		People peopleObj=new People("akshay", "athirk2", 2, 1);
		People peopleObj1=new People("simant", "spuroh6", 2, 1);
		
		
		//peopleObj.addIntoDatabase();
	}
	
	
}
			
		 
	
		
	
	
	
	//1. add students to the database
	



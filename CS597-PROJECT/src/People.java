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
		
//	public void addIntoDatabase()
//	{
//		
//		try{
//			Connection conn = Database.getConnection();
//			try{
//			
//				if(conn != null){
//					String SQLPeopleInsert= "Insert into People Values (?,?,?,?,?);";
//					String SQLPeopleSelect= "Select studentUIN, studentName, departmentID From Student;";
//					
//					// For SQLStudentInsert
//					
//					PreparedStatement statement = conn.prepareStatement(SQLStudentInsert);
//					statement.setInt(1, studentUIN);
//					statement.setString(2, studentName);
//					statement.setInt(3, departmentID);
//					
//					// For SQLStudentSelect
//		
//					statement = conn.prepareStatement(SQLStudentSelect);
//					ResultSet rs =  statement.executeQuery();
//					while(rs.next()){
//				         //Retrieve by column name
//				         int studentRetrievedUIN = rs.getInt("studentUIN");
//				         String studentRetrievedName = rs.getString("studentName");
//				         int studentRetrievedDeptID = rs.getInt("departmentID");
//				         
//				         Student student = new Student(studentRetrievedUIN, studentRetrievedName,studentRetrievedDeptID);
//				         studentList.add(student);
//					}      
//				}
//			} 
//			catch(SQLException e){
//				System.out.println(e);
//				Database.rollBackTransaction(conn);
//			}
//			finally{
//				Database.closeConnection(conn);
//			}
//			
//		} catch(SQLException e){
//			System.out.println(e);
//		  }
//	}
//		
//	}
	
	
	//1. add students to the database
	

}

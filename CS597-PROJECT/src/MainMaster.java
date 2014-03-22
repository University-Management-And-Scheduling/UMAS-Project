=import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class MainMaster {
	
	public static void main(String[] args) {
		ArrayList<Student> studentList = new ArrayList<Student>();
		int studentUIN = 1234;
		String studentName = "XYZ";
		int departmentID = 5678;
		
		try{
			Connection conn = Database.getConnection();
			try{
			
				if(conn != null){
					String SQLStudentInsert= "Insert into Student Values (?,?,?);";
					String SQLStudentSelect= "Select studentUIN, studentName, departmentID From Student;";
					
					// For SQLStudentInsert
					
					PreparedStatement statement = conn.prepareStatement(SQLStudentInsert);
					statement.setInt(1, studentUIN);
					statement.setString(2, studentName);
					statement.setInt(3, departmentID);
					
					// For SQLStudentSelect
		
					statement = conn.prepareStatement(SQLStudentSelect);
					ResultSet rs =  statement.executeQuery();
					while(rs.next()){
				         //Retrieve by column name
				         int studentRetrievedUIN = rs.getInt("studentUIN");
				         String studentRetrievedName = rs.getString("studentName");
				         int studentRetrievedDeptID = rs.getInt("departmentID");
				         
				         Student student = new Student(studentRetrievedUIN, studentRetrievedName,studentRetrievedDeptID);
				         studentList.add(student);
					}      
				}
			} 
			catch(SQLException e){
				System.out.println(e);
				Database.rollBackTransaction(conn);
			}
			finally{
				Database.closeConnection(conn);
			}
			
		} catch(SQLException e){
			System.out.println(e);
		  }
	}
}

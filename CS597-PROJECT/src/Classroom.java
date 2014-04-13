import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Classroom {
	private ClassroomName classroomName;
	private ClassroomLocation classroomLocation;
	private int classroomCapacity;
	
	/**
	 * @return the classroomName
	 */
	public ClassroomName getClassroomName() {
		return classroomName;
	}

	/**
	 * @return the classroomLocation
	 */
	public ClassroomLocation getClassroomLocation() {
		return classroomLocation;
	}

	/**
	 * @return the classroomCapacity
	 */
	public int getClassroomCapacity() {
		return classroomCapacity;
	}

	public static void addNewClassroom(ClassroomName classroomName, ClassroomLocation classroomLocation, int capacity){
		try{
			Connection conn = Database.getConnection();
			String name = classroomName.toString();
			String location = classroomLocation.toString();
			
			try{
				if(conn != null){
					
					//Retrieve the current semester ID
					String classroomSelect = "Select ClassroomName"
							+ " FROM university.classroom"
							+ " WHERE ClassroomName= ? AND ClassroomLocation= ?";
					PreparedStatement statement = conn.prepareStatement(classroomSelect);
					statement.setString(1, name);
					statement.setString(2, location);
					ResultSet rs = statement.executeQuery();
					
					if(rs.first()){
						System.out.println("Class room already exists");
					}
					
					else{
						String classroomInsert = "Insert into university.classroom"
								+ " (ClassroomName, ClassroomLocation, ClassroomCapacity)"
								+ " Values(?,?,?)";
						statement = conn.prepareStatement(classroomInsert);
						statement.setString(1, name);
						statement.setString(2, location);
						statement.setInt(3, capacity);
						statement.executeUpdate();
						Database.commitTransaction(conn);
					}
				}
			}
			
			catch(SQLException e){
				System.out.println("Error adding classroom:"+name+" "+location);
				System.out.println(e.getMessage());
				//e.printStackTrace();
			}
			
			finally{
				//Database.commitTransaction(conn);
			}
			
		}
		
		finally{
		}
	}
	
	public static void main(String[] args){
		for(ClassroomLocation loc: ClassroomLocation.values()){
			for(ClassroomName name: ClassroomName.values()){
				Classroom.addNewClassroom(name, loc, 50);
			}
		}
		
	}
}

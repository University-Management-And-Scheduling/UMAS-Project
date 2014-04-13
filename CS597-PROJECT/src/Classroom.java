import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Classroom {
	private ClassroomName classroomName;
	private ClassroomLocation classroomLocation;
	private int classroomCapacity;
	private int classroomID;
	
	/**
	 * @return the classroomID
	 */
	public int getClassroomID() {
		return classroomID;
	}

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


	public Classroom(int classroomID){
		this.classroomID = classroomID;
		try{
			Connection conn = Database.getConnection();
			
			try{
				if(conn != null){
					
					//Retrieve the current semester ID
					String ClassroomSelect = "Select *"
							+ " FROM university.classroom"
							+ " WHERE classroomID= ?";
					PreparedStatement statement = conn.prepareStatement(ClassroomSelect);
					statement.setInt(1, classroomID);
					ResultSet rs = statement.executeQuery();
					
					if(rs.first()){
						this.classroomCapacity = rs.getInt(4);
						this.classroomName = ClassroomName.valueOf(rs.getString(2));
						this.classroomLocation = ClassroomLocation.valueOf(rs.getString(3));
						System.out.println("Retreived:");
						System.out.println("ID:"+this.classroomID+" Name:"+this.classroomName.toString()+" Location:"+this.classroomLocation.toString());
					}
					
					else{
						throw new IllegalArgumentException("classroom does ot exist");
					}
										
					
				}
			}
			
			catch(SQLException e){
				System.out.println("Error retreiving classroom");
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
			
			finally{
				//Database.closeConnection(conn);
			}
			
		}
		
		finally{
		}

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
	
	public static ArrayList<Timeslots> findOpenSlotsForClassroom(Classroom classroom, int timeSlotType){
		if(classroom == null)
			return null;
		if(timeSlotType < 1 || timeSlotType > 2)
			return null;
		
		ArrayList<Timeslots> timeslots = new ArrayList<Timeslots>();
		int classroomID = classroom.getClassroomID();
		try{
			Connection conn = Database.getConnection();
			
			try{
				if(conn != null){
					
					String ClassroomSelect = "SELECT TimeslotID FROM university.courseschedule natural join university.timeslots "
							+ "where ClassroomID = ? and TimeslotType = ?";
					PreparedStatement statement = conn.prepareStatement(ClassroomSelect);
					statement.setInt(1, classroomID);
					statement.setInt(2, timeSlotType);
					ResultSet rs = statement.executeQuery();
					ArrayList<Timeslots> occupiedTimeslots = new ArrayList<Timeslots>();
					System.out.println("Printing occupied slots:");
					while(rs.next()){
						int timeslotID = rs.getInt(1);
						Timeslots t = new Timeslots(timeslotID);
						System.out.println("Slot:"+t.getTimeSlotID()+" start:"+t.getStartHour()+" end:"+t.getEndHour());
						occupiedTimeslots.add(t);
					}
					
					String timeSlotSelect = "SELECT TimeslotID "
							+ "From university.timeslots "
							+ "where TimeslotType = ?";
					statement = conn.prepareStatement(timeSlotSelect);
					statement.setInt(1, timeSlotType);
					rs = statement.executeQuery();
					
					while(rs.next()){
						Timeslots t = new Timeslots(rs.getInt(1));
						boolean conflict = false;
						for(Timeslots slot:occupiedTimeslots){
							if(Timeslots.isConflict(t, slot)){
								conflict = true;
								break;
							}
						}
						
						if(!conflict){
							timeslots.add(t);
						}
					}
					
				}
			}
			
			catch(SQLException e){
				System.out.println("Error retreiving classroom");
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
			
			finally{
				//Database.closeConnection(conn);
			}
			
		}
		
		finally{
		}
		
		return timeslots;
	}
	
	public static void main(String[] args){

		Classroom c = new Classroom(1);
		ArrayList<Timeslots> slots = findOpenSlotsForClassroom(c, 2);
		System.out.println("Printing available slots");
		//printing available slots from calculated slots
		if(slots != null){
			for(Timeslots slot:slots){
				System.out.println("Slot:"+slot.getTimeSlotID()+" start:"+slot.getStartHour()+" end:"+slot.getEndHour());
			}
		}
		
		
	}
}

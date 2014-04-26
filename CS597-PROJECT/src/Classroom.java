
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;

public class Classroom {
	private ClassroomName classroomName;
	private ClassroomLocation classroomLocation;
	private int classroomCapacity;
	private int classroomID;
	
	
	@Target({ElementType.LOCAL_VARIABLE})
	@Retention(RetentionPolicy.RUNTIME)
	public @interface DBAnnotation {
		 String variable () default "";
		 String table () default "";
		 String column () default "";
		 boolean isSource () default false; 
	}
	
	
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
					
					
					String ClassroomSelect = "Select *"
							+ " FROM university.classroom"
							+ " WHERE classroomID= ?";
					PreparedStatement statement = conn.prepareStatement(ClassroomSelect);
					statement.setInt(1, classroomID);
					ResultSet rs = statement.executeQuery();
					
					
					if(rs.first()){

						@DBAnnotation(variable = "classroomCapacity",table = "classroom", column="ClassroomCapacity", isSource = true)
						int classroomCapacity = rs.getInt("ClassroomCapacity");
						
						@DBAnnotation(variable = "classroomName",table = "classroom", column="ClassroomName", isSource = true)
						String classroomName = rs.getString("ClassroomName"); 
						
						@DBAnnotation(variable = "classroomLocation",table = "classroom", column="ClassroomLocation", isSource = true)
						String classroomLocation = rs.getString("ClassroomLocation");
						
						this.classroomCapacity = classroomCapacity;
						this.classroomName = ClassroomName.valueOf(classroomName);
						this.classroomLocation = ClassroomLocation.valueOf(classroomLocation);
					}
					
					else{
						throw new IllegalArgumentException("Classroom does ot exist");
					}
										
					
				}
			}
			
			catch(SQLException e){
				System.out.println("Error retreiving classroom");
				System.out.println(e.getMessage());
				e.printStackTrace();
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
			
		}
		
		finally{
		}
	}
	
	
	public Timeslots getEmptySlot(int timeSlotType){
		if(!checkTimeSlotType(timeSlotType)){
			System.out.println("Timeslot type is incorrect");
			return null;
		}
		
		
		ArrayList<Timeslots> emptySlots = findOpenSlotsForClassroom(timeSlotType);
		int size = emptySlots.size();
		if(size>0){
			System.out.println("--------------Found and empty time slot---------------");
			Collections.shuffle(emptySlots);
			return emptySlots.get(0);
		}
		
		else return null;
	}
	
	
	
	public static boolean checkTimeSlotType(int timeSlotType){
		return (timeSlotType == 1 || timeSlotType == 2);
	}
	
	
	public static Classroom getEmptyClassroom(ClassroomLocation location, int timeSlotType, int expectedCapacity){
		System.out.println("xxxxxxxxxxxxxxxxINSIDE getEmptyCLassroom FUNCTIONxxxxxxxxxxxxxx");
		ArrayList<ClassroomName> names = new ArrayList<ClassroomName>(Arrays.asList(ClassroomName.values()));
		Collections.shuffle(names);
		Classroom c = null;
		ArrayList<Timeslots> times = null;
		for(ClassroomName name:names){
			int classID = getClassID(name, location);
			if(classID != -1){
				c = new Classroom(classID);
				if(c!=null){
					//System.out.println("Call findEmptySlotsForClassroom for just checking. Not retreiving");
					if(c.getClassroomCapacity() >= expectedCapacity){
						times = c.findOpenSlotsForClassroom(timeSlotType);
						if(times.size()>0){
							System.out.println("Found a classroom with empty time slots:"+c.getClassroomName().toString()+" "
									+ ""+ c.getClassroomLocation().toString());
							break;
						}
					}
					
					c = null;
				}
			}
		}
		
		return c;
	}

	
	public static int getClassID(ClassroomName name, ClassroomLocation location){
		String classroomName = name.toString();
		String classroomLocation = location.toString();
		int id = -1;
		try{
			Connection conn = Database.getConnection();
			
			try{
				if(conn != null){
					
					//Retrieve the current semester ID
					String ClassroomSelect = "Select *"
							+ " FROM university.classroom"
							+ " WHERE classroomName= ? and classroomLocation= ?";
					PreparedStatement statement = conn.prepareStatement(ClassroomSelect);
					statement.setString(1, classroomName);
					statement.setString(2, classroomLocation);
					ResultSet rs = statement.executeQuery();
					
					if(rs.first()){
						@DBAnnotation(variable = "classID",table = "classroom", column="ClassroomID", isSource = true)
						int classID = rs.getInt("ClassroomID");
						id = classID;
					}
					
					else{
						throw new IllegalArgumentException();
					}
										
					
				}
				
				else{
					throw new SQLException();
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
		
		return id;
	}
	
	
	public static LinkedHashMap<Integer, Classroom> getAllEmptyClassroom(ClassroomLocation location, int timeSlotType, int expectedCapacity){
		ArrayList<ClassroomName> names = new ArrayList<ClassroomName>(Arrays.asList(ClassroomName.values()));
		LinkedHashMap<Integer, Classroom> classrooms = new LinkedHashMap<Integer, Classroom>();
		Collections.shuffle(names);
		Classroom c = null;
		ArrayList<Timeslots> times = null;
		for(ClassroomName name:names){
			c = null;
			int classID = getClassID(name, location);
			if(classID != -1){
				c = new Classroom(classID);
				if(c!=null){
					//System.out.println("Call findEmptySlotsForClassroom for just checking. Not retreiving");
					if(c.getClassroomCapacity() >= expectedCapacity){
						times = c.findOpenSlotsForClassroom(timeSlotType);
						if(times.size()>0){
							System.out.println("Found a classroom with empty time slots:"+c.getClassroomName().toString()+" "
									+ ""+ c.getClassroomLocation().toString());
							classrooms.put(c.getClassroomID(), c);
						}
					}
				}
			}
		}
		
		return classrooms;
	}
	
	
	public ArrayList<Timeslots> findOpenSlotsForClassroom(int timeSlotType){
		if(timeSlotType < 1 || timeSlotType > 2)
			return null;
		
		System.out.println("Looking for open time slots in classroom:"+this.getClassroomName().toString()+" at location:"+this.getClassroomLocation().toString());
		
		ArrayList<Timeslots> timeslots = new ArrayList<Timeslots>();
		int classroomID = this.getClassroomID();
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
					//System.out.println("-----Printing occupied slots---------");
					while(rs.next()){
						@DBAnnotation(variable = "timeslotID",table = "timeslots", column="TimeSlotID", isSource = true)
						int timeslotID = rs.getInt("TimeSlotID");
						Timeslots t = new Timeslots(timeslotID);
						//System.out.println("Slot:"+t.getTimeSlotID()+" start:"+t.getStartHour()+" end:"+t.getEndHour());
						occupiedTimeslots.add(t);
					}
					
					String timeSlotSelect = "SELECT TimeslotID "
							+ "From university.timeslots "
							+ "where TimeslotType = ?";
					statement = conn.prepareStatement(timeSlotSelect);
					statement.setInt(1, timeSlotType);
					rs = statement.executeQuery();
					
					System.out.println("-------------Looking for time conflicts-------------");
					while(rs.next()){
						@DBAnnotation(variable = "timeslotID",table = "timeslots", column="TimeSlotID", isSource = true)
						int timeslotID = rs.getInt("TimeSlotID");
						
						Timeslots t = new Timeslots(timeslotID);
						boolean conflict = false;
						for(Timeslots slot:occupiedTimeslots){
							if(Timeslots.isConflict(t, slot)){
								//System.out.println("Conflict detect");
								conflict = true;
								break;
							}
							
							//System.out.println("No conflicts, its an open time slot!");
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
	
	
	public static boolean isEmpty(Classroom classroom, Timeslots t){
		int classroomID = classroom.getClassroomID();
		int timeslotID = t.getTimeSlotID();
		boolean isEmpty= false;
		try{
			Connection conn = Database.getConnection();
			
			try{
				if(conn != null){
					String ClassroomSelect = "Select *"
							+ " FROM university.courseschedule"
							+ " WHERE classroomID= ? and TimeSlotID= ?";
					PreparedStatement statement = conn.prepareStatement(ClassroomSelect);
					statement.setInt(1, classroomID);
					statement.setInt(2, timeslotID);
					ResultSet rs = statement.executeQuery();
					
					if(rs.first()){
						isEmpty = false;
					}
					
					else{
						isEmpty = true;
					}
										
					
				}
			}
			
			catch(SQLException e){
				System.out.println("Error");
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
		}
		
		finally{
			
		}
		
		return isEmpty;
		
	}
	
	
	public String toString(){
		String toReturn = "";
		toReturn+= "Classroom Location:"+this.getClassroomLocation().toString();
		toReturn+= "\nClassroom Name:"+this.getClassroomName().toString();
		toReturn+= "\nClassroomID:"+this.getClassroomID();
		return toReturn;
	}
		
	//to be implemented
		

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Classroom){
			Classroom c = (Classroom)obj;
			if(this.getClassroomID() == c.getClassroomID())
				return true;
			else return false;
		}
		
		else return false;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return (this.getClassroomID()*31);
	}
	
	
}

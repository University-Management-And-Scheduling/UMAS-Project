import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;



public class CourseSchedule {
	private int offerID;
	private int classroomID;
	private int timeSlotID;
	private Timeslots timeslot;
	private Classroom classroom;
	
	/**
	 * @return the offerID
	 */
	public int getOfferID() {
		return offerID;
	}

	/**
	 * @param offerID the offerID to set
	 */
	public void setOfferID(int offerID) {
		this.offerID = offerID;
	}

	/**
	 * @return the classroomID
	 */
	public int getClassroomID() {
		return classroomID;
	}

	/**
	 * @param classroomID the classroomID to set
	 */
	public void setClassroomID(int classroomID) {
		this.classroomID = classroomID;
	}

	/**
	 * @return the timeSlotID
	 */
	public int getTimeSlotID() {
		return timeSlotID;
	}

	/**
	 * @param timeSlotID the timeSlotID to set
	 */
	public void setTimeSlotID(int timeSlotID) {
		this.timeSlotID = timeSlotID;
	}

	/**
	 * @return the timeslot
	 */
	public Timeslots getTimeslot() {
		return timeslot;
	}

	/**
	 * @param timeslot the timeslot to set
	 */
	public void setTimeslot(Timeslots timeslot) {
		if(timeslot == null)
			throw new NullPointerException("Timeslot is null");
		this.timeslot = timeslot;
	}

	/**
	 * @return the classroom
	 */
	public Classroom getClassroom() {
		return classroom;
	}

	/**
	 * @param classroom the classroom to set
	 */
	public void setClassroom(Classroom classroom) {
		if(classroom == null)
			throw new NullPointerException("Classroom object is null");
		this.classroom = classroom;
	}

	public CourseSchedule(int offerID){
		
		
		try{
			Connection conn = Database.getConnection();
			
			try{
				if(conn != null && CourseOffered.checkIfExists(offerID)){
					
					//Retrieve the current semester ID
					String courseSelect = "Select *"
							+ " FROM university.courseschedule"
							+ " WHERE offerID= ?";
					PreparedStatement statement = conn.prepareStatement(courseSelect);
					statement.setInt(1, offerID);
					ResultSet rs = statement.executeQuery();
					
					if(rs.first()){
						int offID = rs.getInt("OfferID");
						int classroomID = rs.getInt("ClassroomID");
						int timeSlotID = rs.getInt("TImeSlotID");
						Timeslots timeslot = new Timeslots(timeSlotID);
						Classroom classroom = new Classroom(classroomID);
						setClassroom(classroom);
						setClassroomID(classroomID);
						setOfferID(offID);
						setTimeslot(timeslot);
						setTimeSlotID(timeSlotID);
						
					}
					
					else{
						System.out.println("Course with offerID:"+offerID+" is NOT scheduled");
						//throw new IllegalArgumentException();
					}
										
					
				}
			}
			
			catch(SQLException e){
				System.out.println("Error retreiving course");
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
		
	public static void updateCourseSchedule(CourseOffered courseoffered, Classroom classroom, Timeslots timeslots) throws CourseOffered.CourseOfferingNotCurrentException{
		if(courseoffered == null || classroom == null || timeslots == null){
			return;
		}
				
		//Check if the course offering is already scheduled
		boolean isAlreadyScheduled = courseoffered.checkIfScheduled();
		boolean isEmpty = Classroom.isEmpty(classroom, timeslots);
		if(isAlreadyScheduled && isEmpty){
			try{
				Connection conn = Database.getConnection();
				
				try{
					if(conn != null){
						String scheduleInsert = "UPDATE university.courseschedule "
								+ "SET TimeSlotID= ? , ClassroomID=? "
								+ "WHERE OfferID= ?";
						PreparedStatement statement = conn.prepareStatement(scheduleInsert, ResultSet.CONCUR_UPDATABLE);
						statement.setInt(3, courseoffered.getOfferID());
						statement.setInt(1, timeslots.getTimeSlotID());
						statement.setInt(2, classroom.getClassroomID());
						statement.executeUpdate();
						Database.commitTransaction(conn);
					}
				}
				
				catch(SQLException e){
					System.out.println("Error updating schedule");
					System.out.println(e.getMessage());
					//e.printStackTrace();
				}
				
			}
			
			finally{
			}
			
		}
		
		//If none of the above steps throw an error schedule the course
	}
	
	public static HashMap<Integer, CourseSchedule> getHaspMapForSchedule(){
		HashMap<Integer, CourseSchedule> cs = new HashMap<Integer, CourseSchedule>();
		for(CourseSchedule c: getAllScheduledCourses()){
			cs.put(c.getOfferID(), c);
		}
		
		return cs;
	}
	
	public static ArrayList<CourseSchedule> getAllScheduledCourses(){
		ArrayList<CourseSchedule> courseSchedule = new ArrayList<CourseSchedule>();
		try{
			Connection conn = Database.getConnection();
			
			try{
				if(conn != null){
					
					String SQLSelect= "Select *"
							+ " FROM university.courseschedule natural join university.coursesoffered "
							+ "ORDER BY TotalCapacity";
					PreparedStatement statement = conn.prepareStatement(SQLSelect);
					ResultSet rs =  statement.executeQuery();
					
					while(rs.next()){
						CourseSchedule cs = new CourseSchedule(rs.getInt("OfferID"));
						courseSchedule.add(cs);
					}
					
				}
			}
			
			catch(SQLException e){
				System.out.println("Error getting");
				System.out.println(e.getMessage());
			}
			
		}
		
		finally{
		}
		
		return courseSchedule;
	}
	
	public static ArrayList<CourseSchedule> getAllScheduledCourses(Department department){
		ArrayList<CourseSchedule> courseSchedule = new ArrayList<CourseSchedule>();
		if(department == null)
			return courseSchedule;
		
		String departmentName = department.getDepartmentName();
		try{
			Connection conn = Database.getConnection();
			
			try{
				if(conn != null){
					
					String SQLSelect= "Select *"
							+ " FROM university.courseschedule natural join university.coursesoffered "
							+ "ORDER BY TotalCapacity";
					PreparedStatement statement = conn.prepareStatement(SQLSelect);
					ResultSet rs =  statement.executeQuery();
					
					while(rs.next()){
						CourseOffered co = new CourseOffered(rs.getInt("OfferID"));
						if(co.getDepartmentName().equals(departmentName)){
							courseSchedule.add(new CourseSchedule(co.getOfferID()));
						}
					}
					
				}
			}
			
			catch(SQLException e){
				System.out.println("Error getting schedule for department");
				System.out.println(e.getMessage());
			} catch (Course.CourseDoesNotExistException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (CourseOffered.CourseOfferingDoesNotExistException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		finally{
		}
		
		return courseSchedule;
	}
	
	public static void scheduleCourse(CourseOffered courseOffered){
		//Check if the course is already scheduled
		System.out.println("xxxxxxxxxxxxxxxxINSIDE SCHEDULE COURSE FUNCTIONxxxxxxxxxxxxxx");
		Classroom c = null;
		Timeslots t = null;
		int timeSlotType = 1;
		
		if(isScheduled(courseOffered.getOfferID())){
			System.out.println("Course is already scheduled");
			return;
		}
		
		//Find a classroom with empty slot
		classroomFind:while(timeSlotType<=2){
			System.out.println("--------------------------------------------------------------"
					+ "\n LOOKING FOR TIMESLOTS WITH TYPE:"+timeSlotType);
			
			for(ClassroomLocation location:ClassroomLocation.values()){
				System.out.println("-----------------------------------------------------------"
						+ "\n LOOKING AT LOCATION:"+location.toString());
				
				c = Classroom.getEmptyClassroom(location, timeSlotType, courseOffered.getTotalCapacity());
				//System.out.println("Got classroom:"+c.getClassroomName().toString()+" at location:"+location.toString()+" repeat:"+c.getClassroomLocation().toString());
				if(c!=null){
					System.out.println("Returning classroom:"+c.getClassroomName().toString()+" at location:"+location.toString()+" repeat:"+c.getClassroomLocation().toString());
					break classroomFind;
				}
			}
			
			timeSlotType++;
		}
		
		if(c==null)
			System.out.println("Cannot schedule this course, no empty class found");
		
		if(c!=null){
			t = c.getEmptySlot(timeSlotType);				
			//Schedule the course in the empty slot
			int offerID = courseOffered.getOfferID();
			int classroomID = c.getClassroomID();
			int timeslotID = t.getTimeSlotID();
			addSchedule(offerID, classroomID, timeslotID);
		}
		
		
		//Commit the schedule
	}

	public static boolean scheduleCourseUsingID(int offerID, int capacity){
		Classroom c = null;
		Timeslots t = null;
		int timeSlotType = 1;
		boolean addFlag = false;
		
		if(isScheduled(offerID)){
			System.out.println("Course is already scheduled");
			return false;
		}
		
		//Find a classroom with empty slot
		classroomFind:while(timeSlotType<=2){
			System.out.println("--------------------------------------------------------------"
					+ "\n LOOKING FOR TIMESLOTS WITH TYPE:"+timeSlotType);
			
			for(ClassroomLocation location:ClassroomLocation.values()){
				System.out.println("-----------------------------------------------------------"
						+ "\n LOOKING AT LOCATION:"+location.toString());
				
				c = Classroom.getEmptyClassroom(location, timeSlotType, capacity);
				//System.out.println("Got classroom:"+c.getClassroomName().toString()+" at location:"+location.toString()+" repeat:"+c.getClassroomLocation().toString());
				if(c!=null){
					System.out.println("Returning classroom:"+c.getClassroomName().toString()+" at location:"+location.toString()+" repeat:"+c.getClassroomLocation().toString());
					break classroomFind;
				}
			}
			
			timeSlotType++;
		}
		
		if(c==null)
			System.out.println("Cannot schedule this course, no empty classroom found");
		
		if(c!=null){
			t = c.getEmptySlot(timeSlotType);				
			//Schedule the course in the empty slot
			int classroomID = c.getClassroomID();
			int timeslotID = t.getTimeSlotID();
			addFlag = addSchedule(offerID, classroomID, timeslotID);
		}
		
		return addFlag;
	}
	
	public static boolean isScheduled(int offerID){
		boolean isScheduled = false;
		try{
			Connection conn = Database.getConnection();
			
			try{
				if(conn != null){
					
					//Retrieve the current semester ID
					String courseSelect = "Select *"
							+ " FROM university.courseschedule"
							+ " WHERE offerID= ?";
					PreparedStatement statement = conn.prepareStatement(courseSelect);
					statement.setInt(1, offerID);
					ResultSet rs = statement.executeQuery();
					
					if(rs.first()){
						isScheduled = true;
						System.out.println("Course with offerID:"+offerID+" is already scheduled");
					}
					
					else{
						isScheduled = false;
						System.out.println("Course with offerID:"+offerID+" is NOT scheduled");
					}
										
					
				}
			}
			
			catch(SQLException e){
				System.out.println("Error retreiving course");
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
			
			finally{
				//Database.closeConnection(conn);
			}
			
		}
		
		finally{
		}
		
		return isScheduled;
	}
	
	private static boolean addSchedule(int offerID, int classroomID, int timeslotID){
		boolean addFlag = false;
		
		try{
			Connection conn = Database.getConnection();
			
			try{
				if(conn != null){
					String scheduleInsert = "Insert into university.courseschedule"
							+ " (OfferID, TimeSlotID, ClassroomID)"
							+ " Values(?,?,?)";
					PreparedStatement statement = conn.prepareStatement(scheduleInsert);
					statement.setInt(1, offerID);
					statement.setInt(2, timeslotID);
					statement.setInt(3, classroomID);
					statement.executeUpdate();
					System.out.println("Adding course schedule with offerID:"+offerID+" ClassroomID:"+classroomID+" TimeslotID:"+timeslotID);
					//Database.commitTransaction(conn);
					addFlag = true;
				}
			}
			
			catch(SQLException e){
				System.out.println("Error adding schedule");
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
			
		}
		
		finally{
		}
		
		return addFlag;
	}
	
	public static void scheduleAllCurrentCourses(){
		//Remove all the scheduled courses
		deleteAllCourseSchedule();
		//Get all the current course offerings
		//Pick up one courseOffering at random
		//Find a classroom and empty timeslot for the offering
		//Schedule the courseOffering
		//Repeat the steps with other offerings
		
		ArrayList<CourseOffered> allCourses = CourseOffered.getAllCurrentlyOfferedCourses();
		Collections.shuffle(allCourses);
		for(CourseOffered co:allCourses){
			System.out.println("\n\n\nxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"
					+ "\n--------------------------------------------------------------------------------------------------------------");
			
			scheduleCourse(co);
			
			System.out.println("\n\n\n-----------------------------------------------------------------------------------------------------------"
					+ "\nxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
		}
		
		Database.commitTransaction(Database.getConnection());
	}
	
	public static void scheduleAllCurrentCourses(Department department){
		//Remove all the scheduled courses in the dept
		
		deleteAllCourseSchedule(department);		
		
		ArrayList<CourseOffered> allCourses = department.getDepartmentCourseOffered();
		for(CourseOffered co:allCourses){
			System.out.println("\n\n\nxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"
					+ "\n--------------------------------------------------------------------------------------------------------------");
			
			scheduleCourse(co);
			
			System.out.println("\n\n\n-----------------------------------------------------------------------------------------------------------"
					+ "\nxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
		}
		
		Database.commitTransaction(Database.getConnection());
	}
	
	static void deleteAllCourseSchedule(){
		try{
			Connection conn = Database.getConnection();
			
			try{
				if(conn != null){
					
					//Retrieve the current semester ID
					String courseScheduleDelete = "Delete"
							+ " FROM university.courseschedule";
					PreparedStatement statement = conn.prepareStatement(courseScheduleDelete, ResultSet.CONCUR_UPDATABLE);
				    int isDeleted= statement.executeUpdate();
				    System.out.println("Deletd:"+isDeleted);
				    if(isDeleted > 0){
				    	Database.commitTransaction(conn);
				    }
				    
									
				}
			}
			
			catch(SQLException e){
				System.out.println("Error deleting schedule");
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
						
		}
		
		finally{
		}
	}
	
	public static void deleteAllCourseSchedule(Department department){
		ArrayList<CourseOffered> deptCoursesOffering = department.getDepartmentCourseOffered();
		
		for(CourseOffered co:deptCoursesOffering){
			deleteSingleSchedule(co.getOfferID());
		}
	}
	
	private static void deleteSingleSchedule(int offerID){
		try{
			Connection conn = Database.getConnection();
			
			try{
				if(conn != null){
					
					//Retrieve the current semester ID
					String courseScheduleDelete = "Delete"
							+ " FROM university.courseschedule "
							+ "WHERE OfferID = ?";
					PreparedStatement statement = conn.prepareStatement(courseScheduleDelete, ResultSet.CONCUR_UPDATABLE);
					statement.setInt(1, offerID);
				    int isDeleted= statement.executeUpdate();
				    System.out.println("Deletd:"+isDeleted);
				    if(isDeleted > 0){
				    	Database.commitTransaction(conn);
				    }
				    
									
				}
			}
			
			catch(SQLException e){
				System.out.println("Error deleting schedule");
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
						
		}
		
		finally{
		}
	}
	
	public static boolean isAnotherCourseSchedulable(int courseCapacity){
		int timeSlotType = 1;
		Classroom c = null;
		while(timeSlotType<=2){
			for(ClassroomLocation location:ClassroomLocation.values()){
				c = Classroom.getEmptyClassroom(location, timeSlotType, courseCapacity);
				if(c!=null){
					return true;
				}
			}
			
			timeSlotType++;
		}
		
		return false;
		
	}
	
	public static void main(String args[]) throws Course.CourseDoesNotExistException, CourseOffered.CourseOfferingDoesNotExistException{
		//scheduleAllCurrentCourses();
		try {
			updateCourseSchedule(new CourseOffered(295), new Classroom(10), new Timeslots(31));
		} catch (CourseOffered.CourseOfferingNotCurrentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

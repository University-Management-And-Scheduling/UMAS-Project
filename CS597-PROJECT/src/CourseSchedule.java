import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;




public class CourseSchedule {
	private int courseScheduleId;
	private CourseOffered courseOffered;
	private Timeslots timeslot;
	private Classroom classroom;
	
	/**
	 * @return the courseScheduleId
	 */
	public int getCourseScheduleId() {
		return courseScheduleId;
	}
	/**
	 * @param courseScheduleId the courseScheduleId to set
	 */
	public void setCourseScheduleId(int courseScheduleId) {
		this.courseScheduleId = courseScheduleId;
	}
	/**
	 * @return the courseOffered
	 */
	public CourseOffered getCourseOffered() {
		return courseOffered;
	}
	/**
	 * @param courseOffered the courseOffered to set
	 */
	public void setCourseOffered(CourseOffered courseOffered) {
		this.courseOffered = courseOffered;
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
		this.classroom = classroom;
	}
	
	public CourseSchedule(int courseScheduleId){
		
	}
		
	public static void addCourseSchedule(CourseOffered courseoffered, Classroom classroom, Timeslots timeslots){
		//Check if the course offering is already scheduled
		//Check if the course offering can be scheduled in the classroom mentioned including timeslot type etc
		//If none of the above steps throw an error schedule the course
	}
	
	public static void scheduleCourse(CourseOffered courseOffered){
		//Check if the course is already scheduled
		System.out.println("xxxxxxxxxxxxxxxxINSIDE SCHEDULE COURSE FUNCTIONxxxxxxxxxxxxxx");
		Classroom c = null;
		Timeslots t = null;
		int timeSlotType = 1;
		if(isScheduled(courseOffered)){
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
				
				c = Classroom.getEmptyClassroom(location, timeSlotType);
				System.out.println("Got classroom:"+c.getClassroomName().toString()+" at location:"+location.toString()+" repeat:"+c.getClassroomLocation().toString());
				if(c!=null){
					System.out.println("Returning classroom:"+c.getClassroomName().toString()+" at location:"+location.toString()+" repeat:"+c.getClassroomLocation().toString());
					break classroomFind;
				}
			}
			
			timeSlotType++;
		}
		
		if(c!=null){
			t = Classroom.getEmptySlot(c, timeSlotType);				
			//Schedule the course in the empty slot
			int offerID = courseOffered.getOfferID();
			int classroomID = c.getClassroomID();
			int timeslotID = t.getTimeSlotID();
			addSchedule(offerID, classroomID, timeslotID);
		}
		
		
		//Commit the schedule
	}
	
	public static boolean isScheduled(CourseOffered courseOffered){
		int offerID = courseOffered.getOfferID();
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
	
	private static void addSchedule(int offerID, int classroomID, int timeslotID){
		
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
				}
			}
			
			catch(SQLException e){
				System.out.println("Error adding schedule");
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
	
	public static void scheduleAllCurrentCourses(){
		//Remove all the scheduled courses
		//Get all the current course offerings
		//Pick up one courseOffering at random
		//Find a classroom and empty timeslot for the offering
		//Schedule the courseOffering
		//Repeat the steps with other offerings
	}
	
	public void updateCourseSchedule(Classroom classroom, Timeslots timeslots){
		
	}
	
	public void deleteCourseSchedule(){
		
	}
		
	public static boolean hasConflict(){
		return false;
	}
	
	public static boolean isAnotherCourseSchedulable(){
		//go to first location
		//go to the first classroom in the location
		//check already scheduled courses in the current classroom
		//check if the any course can be accommodated inside the current schedule
		//if not then go to next class,location
		
		
		return false;
	}
	
	public static void main(String args[]) throws Course.CourseDoesNotExistException, CourseOffered.CourseOfferingDoesNotExistException{
//		ArrayList<CourseOffered> allCourses = CourseOffered.getAllCurrentlyOfferedCourses();
//		for(CourseOffered co:allCourses){
//			System.out.println("\n\n\nxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"
//					+ "\n--------------------------------------------------------------------------------------------------------------");
//			scheduleCourse(co);
//			System.out.println("\n\n\n-----------------------------------------------------------------------------------------------------------"
//					+ "\nxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
//			Database.commitTransaction(Database.getConnection());
//		}

		
		
	}
}

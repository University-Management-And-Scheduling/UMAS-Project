
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
		
	}
	
	public void updateCourseSchedule(Classroom classroom, Timeslots timeslots){
		
	}
	
	public void deleteCourseSchedule(){
		
	}
	
	public static void scheduleAllCurrentCourses(){
		
	}
	
	public static boolean hasConflict(){
		return false;
	}
	
	
	
	
}

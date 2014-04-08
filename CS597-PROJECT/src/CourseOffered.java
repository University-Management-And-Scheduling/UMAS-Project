import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CourseOffered {
	private int offerID;
	private Course course;
	private CourseSchedule courseSchedule;
	private CourseFiles courseFiles;
	private Professor professor;
	private int SemesterID;
	private int totalCapacity;
	private int currentlyFilled;	
	
	/**
	 * @return the offerID
	 */
	public int getOfferID() {
		return offerID;
	}

	
	/**
	 * @return the course
	 */
	public Course getCourse() {
		return course;
	}


	/**
	 * @param course the course to set
	 */
	public void setCourse(Course course) {
		this.course = course;
	}


	/**
	 * @return the courseSchedule
	 */
	public CourseSchedule getCourseSchedule() {
		return courseSchedule;
	}


	/**
	 * @param courseSchedule the courseSchedule to set
	 */
	public void setCourseSchedule(CourseSchedule courseSchedule) {
		this.courseSchedule = courseSchedule;
	}


	/**
	 * @return the courseFiles
	 */
	public CourseFiles getCourseFiles() {
		return courseFiles;
	}


	/**
	 * @param courseFiles the courseFiles to set
	 */
	public void setCourseFiles(CourseFiles courseFiles) {
		this.courseFiles = courseFiles;
	}


	/**
	 * @return the professor
	 */
	public Professor getProfessor() {
		return professor;
	}


	/**
	 * @param professor the professor to set
	 */
	public void setProfessor(Professor professor) {
		this.professor = professor;
	}


	/**
	 * @return the semesterID
	 */
	public int getSemesterID() {
		return SemesterID;
	}


	/**
	 * @param semesterID the semesterID to set
	 */
	public void setSemesterID(int semesterID) {
		SemesterID = semesterID;
	}


	/**
	 * @return the totalCapacity
	 */
	public int getTotalCapacity() {
		return totalCapacity;
	}


	/**
	 * @param totalCapacity the totalCapacity to set
	 */
	public void setTotalCapacity(int totalCapacity) {
		this.totalCapacity = totalCapacity;
	}


	/**
	 * @return the currentlyFilled
	 */
	public int getCurrentlyFilled() {
		return currentlyFilled;
	}


	/**
	 * @param currentlyFilled the currentlyFilled to set
	 */
	public void setCurrentlyFilled(int currentlyFilled) {
		this.currentlyFilled = currentlyFilled;
	}

	
	
	
	/**
	 * @param offerID
	 * @param course
	 * @param courseSchedule
	 * @param courseFiles
	 * @param professor
	 * @param semesterID
	 * @param totalCapacity
	 * @param currentlyFilled
	 */
	public CourseOffered(int offerID, Course course,
			CourseSchedule courseSchedule, CourseFiles courseFiles,
			Professor professor, int semesterID, int totalCapacity,
			int currentlyFilled) {
		this.offerID = offerID;
		this.course = course;
		this.courseSchedule = courseSchedule;
		this.courseFiles = courseFiles;
		this.professor = professor;
		SemesterID = semesterID;
		this.totalCapacity = totalCapacity;
		this.currentlyFilled = currentlyFilled;
	}


	//get course offering by ID
	public static CourseOffered getCourseOfferingByID(int courseOfferingID){
		return null;
	}
	
	//get all current current course offerings
	public static ArrayList<CourseOffered> getAllCurrentlyOfferedCourses(){
		return null;
	}
	
	//get all present and past courses
	public static ArrayList<CourseOffered> getAllOfferedCourses(){
		return null;
	}
	
	//Add the courseOffered object to the database
	public static void addCourseOfferingToDatabase(Course course,  Professor professor, int capacity) throws CourseOfferingAlreadyExistsException{
		int profID = professor.getUIN();
		int courseID = course.getCourseID();
		int totalCap = capacity;
		
		//Check if the same professor is teaching the same course in the current semester
		//if yes then add the course offering in the table
		//then retrieve the same course in a result set
		try{
			Connection conn = new Database().getConnection();
			
			try{
				if(conn != null){
					
					//Retrieve the current semester ID
					String SemesterSelect = "Select SemesterID"
							+ " FROM university.semester"
							+ " WHERE IsCurrent= ?";
					PreparedStatement statement = conn.prepareStatement(SemesterSelect);
					statement.setInt(1, 1);
					ResultSet rs = statement.executeQuery();
					rs.first();
					int semesterID = rs.getInt(1);
					
					
					String SQLSelect= "Select OfferID"
							+ " FROM university.coursesoffered"
							+ " WHERE courseID= ? and TaughtBy= ? and SemesterID= ?";
					statement = conn.prepareStatement(SQLSelect);
					statement.setInt(1, courseID);
					statement.setInt(2, profID);
					statement.setInt(3, semesterID);
					rs =  statement.executeQuery();
					
					if(rs.first()){
						//course offerings with the same courses exist
						throw new CourseOfferingAlreadyExistsException();
					}
					
					else{
						//add the object data to the courseOffered table
						String SQLInsert = "Insert into university.coursesoffered"
								+ "(CourseID,SemesterID,TotalCapacity,SeatsFilled,TaughtBy)"
								+ "Values(?,?,?,?,?);";
						statement = conn.prepareStatement(SQLInsert,ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
						statement.setInt(1, courseID);
						statement.setInt(2, semesterID);
						statement.setInt(3, totalCap);
						statement.setInt(4, 0);
						statement.setInt(5, profID);
						statement.executeUpdate();
						
														
					}
				}
			}
			
			catch(SQLException e){
				System.out.println("Error addind course offering");
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
			
			finally{
				Database.closeConnection(conn);
			}
			
		}
		
		finally{
		}
		
	}
	

	//Remove courseOffered from database
	//Will be removed only if it is current
	public static void removeCourseOffering(CourseOffered courseOffered) throws CourseOfferingDoesNotExistException{		
		
		try{
			Connection conn = new Database().getConnection();
			
			try{
				if(conn != null){
					
					//Retrieve the current semester ID
					String SemesterSelect = "Select SemesterID"
							+ " FROM university.semester"
							+ " WHERE IsCurrent= ?";
					PreparedStatement statement = conn.prepareStatement(SemesterSelect, ResultSet.CONCUR_UPDATABLE);
					statement.setInt(1, 1);
					ResultSet rs = statement.executeQuery();
					int currentSemID = rs.getInt(1);
					
					if(currentSemID == courseOffered.getSemesterID()){
						//delete the course offering
						String deleteCourseQuery = "Delete "
								+ "From university.coursesoffered"
								+ "Where OfferID= ?";
						statement = conn.prepareStatement(deleteCourseQuery, ResultSet.CONCUR_UPDATABLE);
						statement.setInt(1, courseOffered.getOfferID());
						rs = statement.executeQuery();
					}
					
					else{
						throw new CourseOfferingDoesNotExistException();									
					}
				}
			}
			
			catch(SQLException e){
				System.out.println("Error deleting course");
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
			
			finally{
				Database.closeConnection(conn);
			}
			
		}
		
		finally{
		}

		
	}
	
	//get all courses of the student passed
	public static ArrayList<CourseOffered> getStudentCourses(Student student){
		return null;
	}
	
	//get all students in the current course offering object
	//Non-static, requires initialization of Class object
	public ArrayList<Student> getAllStudents(){
		return null;
	}
	
	
	//CourseDoesnotExist Exception
	static class CourseOfferingDoesNotExistException extends Exception{
		private static final long serialVersionUID = 1L;
		private String message = null;
		 
	    public CourseOfferingDoesNotExistException() {
	        super();
	        this.message = "Course does not exist";
	    }
	    
	    public CourseOfferingDoesNotExistException(String message) {
	        super();
	        this.message = message;
	    }
	 
	    @Override
	    public String toString() {
	        return message;
	    }
	 
	    @Override
	    public String getMessage() {
	        return message;
	    }
	}

	
	//CourseOfferingAlreadyExistsException
	static class CourseOfferingAlreadyExistsException extends Exception{
		private static final long serialVersionUID = 1L;
		private String message = null;
		 
	    public CourseOfferingAlreadyExistsException() {
	        super();
	        this.message = "Course offering already exists with the same parameters";
	    }
	    
	    public CourseOfferingAlreadyExistsException(String message) {
	        super();
	        this.message = message;
	    }
	 
	    @Override
	    public String toString() {
	        return message;
	    }
	 
	    @Override
	    public String getMessage() {
	        return message;
	    }
	}

	
	public static void main(String[] args){
		try {
			CourseOffered.addCourseOfferingToDatabase(new Course(1), Professor.retrieveProfDetailsByUIN(1), 50);
		} 
		
		catch (CourseOfferingAlreadyExistsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Course.CourseDoesNotExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

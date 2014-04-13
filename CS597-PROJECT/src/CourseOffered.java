import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class CourseOffered {
	private int offerID;
	private Course course;
	private CourseSchedule courseSchedule;
	private ArrayList<File> files;
	private Professor professor;
	private int SemesterID;
	private int totalCapacity;
	private int currentlyFilled;	
	
	
	public CourseOffered(final int offerID) throws Course.CourseDoesNotExistException, CourseOfferingDoesNotExistException{
		this.offerID = offerID;
		try{
			Connection conn = Database.getConnection();
			
			try{
				if(conn != null){
					
					//Retrieve the current semester ID
					String SemesterSelect = "Select *"
							+ " FROM university.coursesoffered"
							+ " WHERE OfferID= ?";
					PreparedStatement statement = conn.prepareStatement(SemesterSelect);
					statement.setInt(1, offerID);
					ResultSet rs = statement.executeQuery();
					
					if(rs.first()){
						//course offering exists
						Course course = new Course(rs.getInt(2));
						CourseSchedule courseSchedule = new CourseSchedule(offerID);
						ArrayList<File> files = File.getFiles(offerID);
						Professor professor = new Professor(rs.getInt(6));
						this.SemesterID = rs.getInt(3);
						this.totalCapacity = rs.getInt(4);
						this.currentlyFilled = rs.getInt(5);
						this.professor = professor;
						this.course = course;
						this.courseSchedule = courseSchedule;
						this.files = files;
						
					}
					
					else{
						throw new CourseOfferingDoesNotExistException();
					}
										
					
				}
			}
			
			catch(SQLException e){
				System.out.println("Error  course offering");
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
	
	
	/**
	 * @return the offerID
	 */
	public final int getOfferID() {
		return offerID;
	}

	
	/**
	 * @return the course
	 */
	public final Course getCourse() {
		return course;
	}


	/**
	 * @param course the course to set
	 */
	public final void setCourse(final Course course) {
		this.course = course;
	}


	/**
	 * @return the courseSchedule
	 */
	public final CourseSchedule getCourseSchedule() {
		return courseSchedule;
	}


	/**
	 * @param courseSchedule the courseSchedule to set
	 */
	public final void setCourseSchedule(final CourseSchedule courseSchedule) {
		this.courseSchedule = courseSchedule;
	}

	
	/**
	 * @return the files
	 */
	public final ArrayList<File> getFiles() {
		return files;
	}
	

	/**
	 * @param files the files to set
	 */
	public final void setFiles(final ArrayList<File> files) {
		this.files = files;
	}

	/**
	 * @return the professor
	 */
	public final Professor getProfessor() {
		return professor;
	}


	/**
	 * @param professor the professor to set
	 */
	public final void setProfessor(final Professor professor) {
		this.professor = professor;
	}


	/**
	 * @return the semesterID
	 */
	public final int getSemesterID() {
		return SemesterID;
	}


	/**
	 * @param semesterID the semesterID to set
	 */
	public final void setSemesterID(final int semesterID) {
		SemesterID = semesterID;
	}


	/**
	 * @return the totalCapacity
	 */
	public final int getTotalCapacity() {
		return totalCapacity;
	}


	/**
	 * @param totalCapacity the totalCapacity to set
	 */
	public final void setTotalCapacity(final int totalCapacity) {
		this.totalCapacity = totalCapacity;
	}


	/**
	 * @return the currentlyFilled
	 */
	public final int getCurrentlyFilled() {
		return currentlyFilled;
	}


	/**
	 * @param currentlyFilled the currentlyFilled to set
	 */
	public final void setCurrentlyFilled(final int currentlyFilled) {
		this.currentlyFilled = currentlyFilled;
	}

	
	//get course offering by ID
	public static CourseOffered getCourseOfferingByID(final int courseOfferingID){
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
	public static void addCourseOfferingToDatabase(final Course course,  final Professor professor, final int capacity) throws CourseOfferingAlreadyExistsException{
		int profID = professor.getUIN();
		int courseID = course.getCourseID();
		int totalCap = capacity;
		
		//Check if the same professor is teaching the same course in the current semester
		//if yes then add the course offering in the table
		//then retrieve the same course in a result set
		try{
			Connection conn = Database.getConnection();
			
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
						Database.commitTransaction(conn);
						
														
					}
				}
			}
			
			catch(SQLException e){
				System.out.println("Error addind course offering");
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
			
			finally{
				//Database.closeConnection(conn);
				//Database.commitTransaction(conn);
			}
			
		}
		
		finally{
		}
		
	}

	//Remove courseOffered from database
	//Will be removed only if it is current
	public static void removeCourseOffering(final CourseOffered courseOffered) throws CourseOfferingDoesNotExistException{		
		
		try{
			Connection conn = Database.getConnection();
			
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
				//Database.closeConnection(conn);
			}
			
		}
		
		finally{
		}

		
	}
	
	//get all courses of the student passed
	public static ArrayList<CourseOffered> getStudentCourses(final Student student) throws Course.CourseDoesNotExistException, CourseOfferingDoesNotExistException{
		if(student == null) {
			throw new NullPointerException();
		}
		
		ArrayList<CourseOffered> studentCourses = new ArrayList<CourseOffered>();
		
		try{
			Connection conn = Database.getConnection();
			
			try{
				if(conn != null){
					
					//Retrieve the current semester ID
					String SemesterSelect = "Select *"
							+ " FROM university.studentenrollment"
							+ " WHERE UIN= ?";
					PreparedStatement statement = conn.prepareStatement(SemesterSelect);
					statement.setInt(1, student.getUIN());
					ResultSet rs = statement.executeQuery();
					
					while(rs.next()){
						int offerID = rs.getInt(3);
						CourseOffered course = new CourseOffered(offerID);
						studentCourses.add(course);
					}
					
				}
					
			}
			
			catch(SQLException e){
				System.out.println("Error addind course offering");
				System.out.println(e.getMessage());
				e.printStackTrace();
				
			}
				
			finally{
				//Database.commitTransaction(conn);
			}
			
		
			return studentCourses;
		}
		
		finally{
		}
		
	}
	
	//get all students in the current course offering object
	public static ArrayList<Student> getAllStudentsInCourse(final CourseOffered courseOffered){
		if(courseOffered == null) {
			throw new NullPointerException();
		}
		ArrayList<Student> students = new ArrayList<Student>();
		
		/*Code goes here*/
		
		return students;
		
	}
	
	public static boolean isCourseFull(final CourseOffered courseOffered) throws CourseOfferingDoesNotExistException{
		if(courseOffered == null) {
			throw new CourseOfferingDoesNotExistException();
		}
		
		return ((courseOffered.getTotalCapacity() - courseOffered.getCurrentlyFilled()) <= 0);
	}
	
	public static void addOneSeatFilledToCourseOffered(final CourseOffered courseOffered) throws CourseOfferingDoesNotExistException{
		
		try{
			Connection conn = Database.getConnection();
			
			try{
				if(conn != null){
					
					//Retrieve the current semester ID
					String SemesterSelect = "Select *"
							+ " FROM university.coursesoffered"
							+ " WHERE OfferID= ?";
					PreparedStatement statement = conn.prepareStatement(SemesterSelect);
					statement.setInt(1, courseOffered.getOfferID());
					ResultSet rs = statement.executeQuery();
					
					if(rs.first()){
						int currentlyFilled = rs.getInt(5);
						currentlyFilled += 1;
						rs.updateInt(5, currentlyFilled);
					}
					
				}
					
					
				else{
					throw new CourseOfferingDoesNotExistException();
					
				}
										
					
				
			}
			
			catch(Exception e){
				System.out.println("Error addind course offering");
				System.out.println(e.getMessage());
				e.printStackTrace();
				
			}
				
			finally{
				Database.commitTransaction(conn);
			}
			
		}
		
		
		
		finally{
		}
			
	}
	
	//CourseDoesnotExist Exception
	static class CourseOfferingDoesNotExistException extends Exception{
		private static final long serialVersionUID = 1L;
		private String message = null;
		 
	    public CourseOfferingDoesNotExistException() {
	        super();
	        this.message = "Course does not exist";
	    }
	    
	    public CourseOfferingDoesNotExistException(final String message) {
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
	    
	    public CourseOfferingAlreadyExistsException(final String message) {
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

	
	public static void main(final String[] args){
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

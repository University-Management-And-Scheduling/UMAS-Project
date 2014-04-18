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
						Course course = new Course(rs.getInt("CourseID"));
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

	public String getCourseName(){
		Course c = getCourse();
		return c.getCourseName();
	}
	
	public String getProfessorName(){
		Professor p = getProfessor();
		return p.getName();
	}
	
	public String getDepartmentName(){
		Course c = getCourse();
		Department d = c.getDepartment();
		return d.getDepartmentName();
	}
	
	public String getClassRoomName(){
		CourseSchedule cs = getCourseSchedule();
		Classroom cr = cs.getClassroom();
		ClassroomName cn = cr.getClassroomName();
		return cn.toString();
	}
	
	public String getClassRoomLocation(){
		CourseSchedule cs = getCourseSchedule();
		Classroom cr = cs.getClassroom();
		ClassroomLocation cl = cr.getClassroomLocation();
		return cl.toString();
	}
	
	public String getTiming(){
		CourseSchedule cs = getCourseSchedule();
		Timeslots ts = cs.getTimeslot();
		String times = "";
		times += ts.getStartHour()+"00 TO ";
		times += ts.getEndHour()+"00";
		return times;
		
	}
	//get all current current course offerings
	public static ArrayList<CourseOffered> getAllCurrentlyOfferedCourses(){
		ArrayList<CourseOffered> currentOffering = new ArrayList<CourseOffered>();
		int currentSemID = getCurrentSemesterID();
		
		try{
			Connection conn = Database.getConnection();
			
			try{
				if(conn != null){
					
					//Retrieve the current semester ID
					String SemesterSelect = "Select *"
							+ " FROM university.coursesoffered"
							+ " WHERE SemesterID= ?";
					PreparedStatement statement = conn.prepareStatement(SemesterSelect);
					statement.setInt(1, currentSemID);
					ResultSet rs = statement.executeQuery();
					
					while(rs.next()){
						CourseOffered c = new CourseOffered(rs.getInt("OfferID"));
						currentOffering.add(c);
					}
										
				}
			}
			
			catch(SQLException e){
				System.out.println("Error  course offering");
				System.out.println(e.getMessage());
				e.printStackTrace();
			} catch (Course.CourseDoesNotExistException e) {
				e.printStackTrace();
			} catch (CourseOfferingDoesNotExistException e) {
				e.printStackTrace();
			}			
		}
		
		finally{
		}
		
		return currentOffering;
	}
	
	//gets all current and previously offered courses
	//get all present and past courses
	public static ArrayList<CourseOffered> getAllOfferedCourses(){
		ArrayList<CourseOffered> currentOffering = new ArrayList<CourseOffered>();		
		try{
			Connection conn = Database.getConnection();
			
			try{
				if(conn != null){
					
					//Retrieve the current semester ID
					String SemesterSelect = "Select *"
							+ " FROM university.coursesoffered";
					PreparedStatement statement = conn.prepareStatement(SemesterSelect);
					ResultSet rs = statement.executeQuery();
					
					while(rs.next()){
						CourseOffered c = new CourseOffered(rs.getInt("OfferID"));
						currentOffering.add(c);
					}
										
				}
			}
			
			catch(SQLException e){
				System.out.println("Error  course offering");
				System.out.println(e.getMessage());
				e.printStackTrace();
			} catch (Course.CourseDoesNotExistException e) {
				e.printStackTrace();
			} catch (CourseOfferingDoesNotExistException e) {
				e.printStackTrace();
			}			
		}
		
		finally{
		}
		
		return currentOffering;
	}
	
	//function revision pending
	//to be added functionality to check if this course can be scheduled if offered
	//also schedule the course on a available slot after adding
	//Add the courseOffered object to the database
	public static void addCourseOfferingToDatabase(final Course course,  final Professor professor, final int capacity) throws CourseOfferingAlreadyExistsException, CourseOfferingNotSchedulable{
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
					
//					//Retrieve the current semester ID
//					String SemesterSelect = "Select SemesterID"
//							+ " FROM university.semester"
//							+ " WHERE IsCurrent= ?";
//					PreparedStatement statement = conn.prepareStatement(SemesterSelect);
//					statement.setInt(1, 1);
//					ResultSet rs = statement.executeQuery();
//					rs.first();
					int semesterID = getCurrentSemesterID();
					
					
					String SQLSelect= "Select OfferID"
							+ " FROM university.coursesoffered"
							+ " WHERE courseID= ? and TaughtBy= ? and SemesterID= ?";
					PreparedStatement statement = conn.prepareStatement(SQLSelect);
					statement.setInt(1, courseID);
					statement.setInt(2, profID);
					statement.setInt(3, semesterID);
					ResultSet rs =  statement.executeQuery();
					
					if(rs.first()){
						//course offerings with the same courses exist
						throw new CourseOfferingAlreadyExistsException();
					}
					
					else{
						if(!CourseSchedule.isAnotherCourseSchedulable(capacity))
							throw new CourseOfferingNotSchedulable();
						
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

	//Not to be used, functionality not complete
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
	
	//complete
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
		
		try{
			Connection conn = Database.getConnection();
			
			try{
				if(conn != null){
					
					//Retrieve the current semester ID
					String studentSelect = "Select *"
							+ " FROM university.studentenrollment"
							+ " WHERE OfferID=?";
					PreparedStatement statement = conn.prepareStatement(studentSelect);
					statement.setInt(1, courseOffered.getOfferID());
					ResultSet rs = statement.executeQuery();
					
					while(rs.next()){
						int UIN = rs.getInt("UIN");
						Student s = new Student(UIN);
						if(s!=null)
							students.add(s);
					}
					
				}
					
					
				else{
					throw new CourseOfferingDoesNotExistException();
				}
										
					
				
			}
			
			catch(Exception e){
				System.out.println(e.getMessage());
				e.printStackTrace();
				
			}			
		}
				
		finally{
		}
		
		return students;
		
	}
	
	//complete
	public static ArrayList<CourseOffered> getCurrentProfessorCourses(Professor professor){
		if(professor == null)
			return null;
		
		ArrayList<CourseOffered> profCourses = new ArrayList<CourseOffered>();
		int professorID = professor.getUIN();
		int currentSemesterID = getCurrentSemesterID();
		
		try{
			Connection conn = Database.getConnection();
			
			try{
				if(conn != null){
					
					//Retrieve the current semester ID
					String SemesterSelect = "Select *"
							+ " FROM university.coursesoffered"
							+ " WHERE TaughtBy=? and SemesterID= ?";
					PreparedStatement statement = conn.prepareStatement(SemesterSelect);
					statement.setInt(1, professorID);
					statement.setInt(2, currentSemesterID);
					ResultSet rs = statement.executeQuery();
					
					while(rs.next()){
						CourseOffered c = new CourseOffered(rs.getInt("OfferID"));
						profCourses.add(c);
					}
					
				}
					
					
				else{
					throw new IllegalAccessException("Professor deos not exist");
				}
										
					
				
			}
			
			catch(Exception e){
				System.out.println(e.getMessage());
				e.printStackTrace();
				
			}
			
		}
		finally{
		}
		
		return profCourses;
	}
	
	//incomplete
	public static ArrayList<CourseOffered> getAllTACourses(TA ta){
		return null;
	}
	
	//complete
	public static boolean isCourseFull(final CourseOffered courseOffered) throws CourseOfferingDoesNotExistException{
		if(courseOffered == null) {
			throw new CourseOfferingDoesNotExistException();
		}
		
		return ((courseOffered.getTotalCapacity() - courseOffered.getCurrentlyFilled()) <= 0);
	}
	
	//complete
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
	
	//complete
	//check if the current course object is scheduled
	//check first if the course offering is current
	//if not current, throw courseOffering not current exception
	public boolean checkIfScheduled() throws CourseOfferingNotCurrentException{
		if(!checkIfCurrent()){
			throw new CourseOfferingNotCurrentException("This course is not a currently offered course");
		}
		
		boolean doesExist = false;
		
		try{
			Connection conn = Database.getConnection();
			
			try{
				if(conn != null){
					
					//Retrieve the current semester ID
					String scheduleSelect = "Select *"
							+ " FROM university.courseschedule"
							+ " WHERE OfferID= ?";
					PreparedStatement statement = conn.prepareStatement(scheduleSelect);
					statement.setInt(1, this.getOfferID());
					ResultSet rs = statement.executeQuery();
					
					if(rs.first()){
						doesExist = true;	
					}							
					
				}
			}
			
			catch(SQLException e){
				System.out.println("Error in SQL");
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
						
		}
		
		finally{
		}
		
		return doesExist;
	}
	
	public static boolean checkIfExists(int offerID){
		boolean doesExist = false;
		
		try{
			Connection conn = Database.getConnection();
			
			try{
				if(conn != null){
					
					//Retrieve the current semester ID
					String scheduleSelect = "Select *"
							+ " FROM university.coursesoffered"
							+ " WHERE OfferID= ?";
					PreparedStatement statement = conn.prepareStatement(scheduleSelect);
					statement.setInt(1, offerID);
					ResultSet rs = statement.executeQuery();
					
					if(rs.first()){
						doesExist = true;	
					}							
					
				}
			}
			
			catch(SQLException e){
				System.out.println("Error in SQL");
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
						
		}
		
		finally{
		}
		
		return doesExist;
	}
	//complete
	//return current semesterID
	public static int getCurrentSemesterID(){
		int current = -1;
		try{
			Connection conn = Database.getConnection();
			
			try{
				if(conn != null){
					
					//Retrieve the current semester ID
					String semSelect = "Select *"
							+ " FROM university.semester"
							+ " WHERE isCurrent= ?";
					PreparedStatement statement = conn.prepareStatement(semSelect);
					statement.setInt(1, 1);
					ResultSet rs = statement.executeQuery();
					
					if(rs.first()){
						current = rs.getInt("IsCurrent");
					}							
					
				}
			}
			
			catch(SQLException e){
				System.out.println("Error in SQL");
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
			
			return current;
						
		}
		
		finally{
		}
	}
	
	//complete
	//check if the current courseOffering is current
	public boolean checkIfCurrent(){
		int semID = this.getSemesterID();
		return (semID == getCurrentSemesterID());
	}
	
	//to check if the course can be registered by a student
	public static boolean isCourseRegistrableBy(Student student, int offerID){
		
		//check if the student is already registered
		if(WaitList.isStudentRegistered(student, offerID)){
			return false;
		}
		
		//check if the course is full
		try {
			if(isCourseFull(new CourseOffered(offerID))){
				return false;
			}
		} catch (CourseOfferingDoesNotExistException
				| Course.CourseDoesNotExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//check if the student is on the waitList
		if(WaitList.isStudentOnWaitList(student, offerID)){
			return false;
		}
		
		if(WaitList.isStudentEmailed(student, offerID)){
			return true;
		}
		
		if(!WaitList.isWaitListEmpty(offerID)){
			return false;
		}
				
		return true;
		
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

	//CourseOfferingNotCurrent Exception
	static class CourseOfferingNotCurrentException extends Exception{
		private static final long serialVersionUID = 1L;
		private String message = null;
		 
	    public CourseOfferingNotCurrentException() {
	        super();
	        this.message = "Course not current";
	    }
	    
	    public CourseOfferingNotCurrentException(final String message) {
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

	//CourseOfferingNotSchedulable Exception
	static class CourseOfferingNotSchedulable extends Exception{
			private static final long serialVersionUID = 1L;
			private String message = null;
			 
		    public CourseOfferingNotSchedulable() {
		        super();
		        this.message = "Course offering not added as it is not schedulable";
		    }
		    
		    public CourseOfferingNotSchedulable(final String message) {
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
//		try {
//			CourseOffered.addCourseOfferingToDatabase(new Course(1), Professor.retrieveProfDetailsByUIN(1), 50);
//		} 
//		
//		catch (CourseOfferingAlreadyExistsException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (Course.CourseDoesNotExistException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (CourseOfferingNotSchedulable e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}

}

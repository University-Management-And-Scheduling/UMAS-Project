
/****************@author Simant Purohit*********************************/

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import com.mysql.jdbc.Statement;


public class CourseOffered {
	private int offerID;
	private Course course;
	private CourseSchedule courseSchedule;
	private ArrayList<File> files;
	private Professor professor;
	private int SemesterID;
	private int totalCapacity;
	private int currentlyFilled;	
	
	/*
	 * Initialize a course offered object using the offer id
	 * Throws a course offering does not exist exception if the offering does not exist and
	 * throws a course does not exist exception if the course itself is non existent 
	 */
	public CourseOffered(int offerID) throws Course.CourseDoesNotExistException, CourseOfferingDoesNotExistException{
		this.offerID = offerID;
		try{
			Connection conn = Database.getConnection();
			
			try{
				if(conn != null){
					
					//Retrieve the current semester ID
					String SemesterSelect = "Select *"
							+ " FROM coursesoffered"
							+ " WHERE OfferID= ?";
					PreparedStatement statement = conn.prepareStatement(SemesterSelect);
					statement.setInt(1, offerID);
					ResultSet rs = statement.executeQuery();
					
					if(rs.first()){
						/*
						 * course offering exists
						 */
						DBAnnotation.annoate("courseID", "coursesoffered", "CourseID", true);
						int courseID = rs.getInt("CourseID");
						Course course = new Course(courseID);
						CourseSchedule courseSchedule = new CourseSchedule(offerID);
						ArrayList<File> files = File.getFiles(offerID);
						
						DBAnnotation.annoate("taughtBy", "coursesoffered", "TaughtBy", true);
						int taughtBy = rs.getInt("TaughtBy");
						Professor professor = new Professor(taughtBy);
						
						DBAnnotation.annoate("semID", "coursesoffered", "SemesterID", true);
						int semID = rs.getInt("SemesterID");
						this.SemesterID = semID;
						
						DBAnnotation.annoate("totalCapacity", "coursesoffered", "TotalCapacity", true);
						int totalCapacity = rs.getInt("TotalCapacity");
						this.totalCapacity = totalCapacity;
						
						DBAnnotation.annoate("seatsFilled", "coursesoffered", "SeatsFilled", true);
						int seatsFilled = rs.getInt("SeatsFilled");
						this.currentlyFilled = seatsFilled;
						
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
				System.out.println("Error retrieving course offering");
				System.out.println(e.getMessage());
				e.printStackTrace();
			} catch (Student.AccessDeniedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (People.PersonDoesNotExistException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
						
		}
		
		finally{
		}

		
	}
	
	
	/*
	 * Getters and setters start*****************************************************
	 */
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
		if(ts.getTimeslotType()==1){
			times+="M-W-F -> ";
			times += ts.getStartHour()+"00 TO ";
			times += ts.getEndHour()+"00";
		}
		
		if(ts.getTimeslotType()==2){
			times+="T-TH -> ";
			times += ts.getStartHour()+"00 TO ";
			times += ts.getEndHour()+"00";
		}
		
		return times;
		
	}
	
	/*
	 * Getters and settes end********************************************************
	 */
	
	
	/*
	 * Returns all the current offerings as a ArrayList of CourseOffered objects
	 */
	public static ArrayList<CourseOffered> getAllCurrentlyOfferedCourses(){
		ArrayList<CourseOffered> currentOffering = new ArrayList<CourseOffered>();
		int currentSemID = getCurrentSemesterID();
		
		try{
			Connection conn = Database.getConnection();
			
			try{
				if(conn != null){
					
					//Retrieve the current semester ID
					String SQLSelect = "Select *"
							+ " FROM coursesoffered"
							+ " WHERE SemesterID= ?";
					PreparedStatement statement = conn.prepareStatement(SQLSelect);
					statement.setInt(1, currentSemID);
					ResultSet rs = statement.executeQuery();
					
					while(rs.next()){
						DBAnnotation.annoate("offerID", "coursesoffered", "OfferID", true);
						int offerID = rs.getInt("OfferID");
						CourseOffered c = new CourseOffered(offerID);
						currentOffering.add(c);
					}
										
				}
			}
			
			catch(SQLException e){
				System.out.println("Error retrieving course offering");
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
	
	/*
	 * Returns a HashMap of OfferID and CourseOffered for all the currently offered courses
	 */
	public static HashMap<Integer,CourseOffered> getAllOfferedIDAndCourseOffered(){
		HashMap<Integer, CourseOffered> offerdCourses = new HashMap<Integer, CourseOffered>();
		int currentSemID = getCurrentSemesterID();
		
		try{
			Connection conn = Database.getConnection();
			
			try{
				if(conn != null){
					
					//Retrieve the current semester ID
					String SemesterSelect = "Select *"
							+ " FROM coursesoffered"
							+ " WHERE SemesterID= ?";
					PreparedStatement statement = conn.prepareStatement(SemesterSelect);
					statement.setInt(1, currentSemID);
					ResultSet rs = statement.executeQuery();
					
					while(rs.next()){
						DBAnnotation.annoate("offerID", "coursesoffered", "OfferID", true);
						int offerID = rs.getInt("OfferID");
						CourseOffered c = new CourseOffered(offerID);
						offerdCourses.put(offerID, c);
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
		
		return offerdCourses;
	}
	
	/*
	 * Returns all the current and previous offerings as a ArrayList of CourseOffered objects
	 */
	public static ArrayList<CourseOffered> getAllOfferedCourses(){
		ArrayList<CourseOffered> currentOffering = new ArrayList<CourseOffered>();		
		try{
			Connection conn = Database.getConnection();
			
			try{
				if(conn != null){
					
					//Retrieve the current semester ID
					String SemesterSelect = "Select *"
							+ " FROM coursesoffered";
					PreparedStatement statement = conn.prepareStatement(SemesterSelect);
					ResultSet rs = statement.executeQuery();
					
					while(rs.next()){
						DBAnnotation.annoate("offerID", "coursesoffered", "OfferID", true);
						int offerID = rs.getInt("OfferID");
						CourseOffered c = new CourseOffered(offerID);
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
	
	/*
	 * Add the course offering as a current offering to the database for the specified course, professor and capacity combo
	 * The functions tries to see if the course can be scheduled in any classroom or not and schedules it if possible
	 * The function also creates a default information file for the course offering  and adds the file to the course folder
	 * Class the function in CourseExam to create a table to store all the course exam and test details
	 * Throws a CourseOffering already exists exception if the offering already exists in the database for the current semester
	 * Throws a COurseOffering not schedulable exception is the course offering cannot be scheduled in any classroom
	 */
	public static boolean addCourseOfferingToDatabase(final Course course,  final Professor professor, final int capacity) throws CourseOfferingAlreadyExistsException, CourseOfferingNotSchedulable{
		if(course == null || professor == null || capacity <=0)
			return false;
		
		
		int profID = professor.getUIN();
		int courseID = course.getCourseID();
		int totalCap = capacity;
		int minCap = 0;
		boolean addFlag = false;
		
		//Check if the same professor is teaching the same course in the current semester
		//if yes then add the course offering in the table
		//then retrieve the same course in a result set
		try{
			Connection conn = Database.getConnection();
			
			try{
				if(conn != null){
					
					int semesterID = getCurrentSemesterID();
					String SQLSelect= "Select OfferID"
							+ " FROM coursesoffered"
							+ " WHERE courseID= ? and TaughtBy= ? and SemesterID= ?";
					PreparedStatement statement = conn.prepareStatement(SQLSelect);
					statement.setInt(1, courseID);
					statement.setInt(2, profID);
					statement.setInt(3, semesterID);
					ResultSet rs =  statement.executeQuery();
					
					if(rs.first()){
						/*
						 * course offerings with the same courses exist
						 */
						throw new CourseOfferingAlreadyExistsException();
					}
					
					else{
						/*
						 * Check if the course offering is schedulable
						 */
						if(!CourseSchedule.isAnotherCourseSchedulable(capacity))
							throw new CourseOfferingNotSchedulable();
						
						/*
						 * Add the object data to the courseOffered table
						 */
						
						/*
						 * Annotating the insert statement here
						 */
						
						DBAnnotation.annoate("courseID", "coursesoffered", "CourseID", false);
						DBAnnotation.annoate("semesterID", "coursesoffered", "SemesterID", false);
						DBAnnotation.annoate("totalCap", "coursesoffered", "TotalCapacity", false);
						DBAnnotation.annoate("minCap", "coursesoffered", "SeatsFilled", false);
						DBAnnotation.annoate("profID", "coursesoffered", "TaughtBy", false);
						
						String SQLInsert = "Insert into coursesoffered"
								+ "(CourseID,SemesterID,TotalCapacity,SeatsFilled,TaughtBy)"
								+ "Values(?,?,?,?,?);";
						statement = conn.prepareStatement(SQLInsert, Statement.RETURN_GENERATED_KEYS);
						statement.setInt(1, courseID);
						statement.setInt(2, semesterID);
						statement.setInt(3, totalCap);
						statement.setInt(4, minCap);
						statement.setInt(5, profID);
						statement.executeUpdate();
						ResultSet generatedSet = statement.getGeneratedKeys();
						
						int generatedID = -1;
						
						if(generatedSet.first())
							generatedID = generatedSet.getInt(1);
						else
							return false;
						/*
						 * flag to see if the table for exams was created
						 */
						boolean tableFlag = CourseExams.createCourseExamMarksTable(course.getCourseName(), generatedID, getCurrentSemesterID());
						/*
						 * flag to see if the course was successfully scheduled
						 */
						boolean flag = CourseSchedule.scheduleCourseUsingID(generatedID, totalCap);
						/*
						 * flag to see if the default file with course details was created to be added to this course
						 */
						boolean fileFlag = makeDefaultCourseFile(generatedID, course.getCourseName(), professor.getName());
						
						/*
						 * Checks if the file was created and adds it to the database
						 */
						if(fileFlag){
							String currentPath = System.getProperty("user.dir");
							String fileDir = currentPath+"/Files/"+course.getCourseName()+"-"+generatedID;
							String fileName = course.getCourseName()+generatedID+"-details.txt";
							fileFlag = File.addFileToDB(fileName, fileDir, generatedID);
						}
						
						/*
						 * If the scheduling, file creation, exam table creation and the file addition to database is successful
						 * commit the whole transaction to the database
						 */
						if(flag && tableFlag && fileFlag){		
							Database.commitTransaction(conn);
							System.out.println("Added file------------------------");
							addFlag = true;
						}
																				
					}
				}
			}
			
			catch(SQLException e){
				System.out.println("Error addind course offering");
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
			
		}
		
		finally{
		}
		
		return addFlag;
		
	}

	/*
	 * Function to update the professor teaching the course currently
	 */
	public boolean updateCourseOffering(Professor professor) throws CourseOfferingDoesNotExistException{		
		if(!checkIfCurrent()){
			return false;
		}
		
		boolean isUpdated = false;	
		try{
			Connection conn = Database.getConnection();
			
			try{
				if(conn != null){
					
					DBAnnotation.annoate("profID", "coursesoffered", "TaughtBy", false);
					DBAnnotation.annoate("offerID", "coursesoffered", "OfferID", false);
					String SQLUpdate = "UPDATE coursesoffered "
							+ "SET Taughtby= ? "
							+ "WHERE offerID= ?";
					PreparedStatement statement = conn.prepareStatement(SQLUpdate, ResultSet.CONCUR_UPDATABLE);
					int profID = professor.getUIN();
					int offerID = this.getOfferID();
					statement.setInt(1, profID);
					statement.setInt(2, offerID);
					statement.executeUpdate();
					Database.commitTransaction(conn);
					isUpdated = true;
				}
			}
			
			catch(SQLException e){
				System.out.println("Error updating course offering");
				e.printStackTrace();
			}
			
		}
		
		finally{
		}
		
		return isUpdated;
		
	}

	/*
	 * Makes a folder in the Files folder (present in the course folder) for storing all the course related files
	 */
	private static boolean makeDefaultCourseFile(int offerID, String courseName, String Professor){
		try{
			String currentPath = System.getProperty("user.dir");
			String fileDir = currentPath+"/Files/"+courseName+"-"+offerID;
			boolean dir = new java.io.File(fileDir).mkdirs();
			
			java.io.File newFile = new java.io.File(fileDir+"/"+courseName+offerID+"-details.txt");
			FileWriter writer = new FileWriter(newFile);
			writer.write("Course Details:\n Course name:"+courseName+"\nTaught by:"+Professor+"\nPlease check your"
					+ " courses tab for more details");
			writer.close();
			return true;
		}
		
		catch(IOException e){
			System.out.println("IO error Not formed"+ e.getMessage());
			e.printStackTrace();
			return false;
		}
		
		catch(Exception e){
			System.out.println("Not formed");
			return false;
		}
		
		
	}
	
	
	/*
	 * Returns an array list of all the current CourseOffered objects for the specified student 
	 */
	public static ArrayList<CourseOffered> getStudentCourses(Student student) throws Course.CourseDoesNotExistException, CourseOfferingDoesNotExistException{
		if(student == null) {
			throw new NullPointerException();
		}
		
		ArrayList<CourseOffered> studentCourses = new ArrayList<CourseOffered>();
		
		try{
			Connection conn = Database.getConnection();
			
			try{
				if(conn != null){
					
					//Retrieve the current semester ID
					String SQLSelect = "Select *"
							+ " FROM studentenrollment"
							+ " WHERE UIN= ?";
					PreparedStatement statement = conn.prepareStatement(SQLSelect);
					statement.setInt(1, student.getUIN());
					ResultSet rs = statement.executeQuery();
					
					while(rs.next()){
						DBAnnotation.annoate("offerID", "studentenrollment", "OfferID", true);
						int offerID = rs.getInt("OfferID");
						CourseOffered course = new CourseOffered(offerID);
						studentCourses.add(course);
					}
					
				}
					
			}
			
			catch(SQLException e){
				System.out.println("Error retrieving course offering");
				System.out.println(e.getMessage());
				e.printStackTrace();
				
			}
				
			finally{
			}
			
		
			return studentCourses;
		}
		
		finally{
		}
		
	}
	
	/*
	 * returns all the students who have registered for the course offering 
	 */
	public static ArrayList<Student> getAllStudentsInCourse(CourseOffered courseOffered){
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
							+ " FROM studentenrollment"
							+ " WHERE OfferID=?";
					PreparedStatement statement = conn.prepareStatement(studentSelect);
					statement.setInt(1, courseOffered.getOfferID());
					ResultSet rs = statement.executeQuery();
					
					while(rs.next()){
						DBAnnotation.annoate("UIN", "studentenrollment", "UIN", true);
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
	
	/*
	 * Returns all the current courses that the specified professor is teaching
	 */
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
					String SQLSelect = "Select *"
							+ " FROM coursesoffered"
							+ " WHERE TaughtBy=? and SemesterID= ?";
					PreparedStatement statement = conn.prepareStatement(SQLSelect);
					statement.setInt(1, professorID);
					statement.setInt(2, currentSemesterID);
					ResultSet rs = statement.executeQuery();
					
					while(rs.next()){
						DBAnnotation.annoate("offerID", "coursesoffered", "OfferID", true);
						int offerID = rs.getInt("OfferID");
						CourseOffered c = new CourseOffered(offerID);
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
	
	/*
	 * Returns all the courses that are being currently TAed by the TA
	 */
	public static ArrayList<CourseOffered> getAllCurrentCoursesTAedBy(TA ta){
		if(ta == null)
			return null;
		
		ArrayList<CourseOffered> taCourses = new ArrayList<CourseOffered>();
		int TAID = ta.getUIN();
		int currentSemesterID = getCurrentSemesterID();
		
		try{
			Connection conn = Database.getConnection();
			
			try{
				if(conn != null){
					
					//Retrieve the current semester ID
					String SemesterSelect = "Select *"
							+ " FROM coursesoffered as c join teachingassistant as t"
							+ " WHERE c.SemesterID= ? and t.OfferID = c.OfferID and TaUIN= ?";
					PreparedStatement statement = conn.prepareStatement(SemesterSelect);
					statement.setInt(1, currentSemesterID);
					statement.setInt(2, TAID);
					ResultSet rs = statement.executeQuery();
					
					while(rs.next()){
						DBAnnotation.annoate("offerID", "coursesoffered", "OfferID", true);
						int offerID = rs.getInt("OfferID");
						CourseOffered c = new CourseOffered(offerID);
						taCourses.add(c);
					}
					
				}
					
					
				else{
					throw new IllegalAccessException("TA does not exits - CoursesOffered.java");
				}
										
					
				
			}
			
			catch(Exception e){
				System.out.println(e.getMessage());
				e.printStackTrace();
				
			}
			
		}
		finally{
		}
		
		return taCourses;
	}

	/*
	 * Returns all the courses that the TA is currently enrolled in
	 */
	public static ArrayList<CourseOffered> getAllCurrentCoursesTakenBy(TA ta){
		if(ta == null) {
			throw new NullPointerException();
		}
		
		ArrayList<CourseOffered> studentCourses = new ArrayList<CourseOffered>();
		
		try{
			Connection conn = Database.getConnection();
			
			try{
				if(conn != null){
					
					//Retrieve the current semester ID
					String SemesterSelect = "Select *"
							+ " FROM studentenrollment"
							+ " WHERE UIN= ?";
					PreparedStatement statement = conn.prepareStatement(SemesterSelect);
					statement.setInt(1, ta.getUIN());
					ResultSet rs = statement.executeQuery();
					
					while(rs.next()){
						DBAnnotation.annoate("offerID", "studentenrollment", "OfferID", true);
						int offerID = rs.getInt("OfferID");
						CourseOffered course = new CourseOffered(offerID);
						studentCourses.add(course);
					}
					
				}
					
			}
			
			catch(Exception e){
				System.out.println(e.getMessage());
				e.printStackTrace();
				
			}
					
		}
		
		finally{
		}
		
		return studentCourses;
	}
	
	
	/*
	 * Returns if the course offering is full or not 
	 */
	public boolean isCourseFull() throws CourseOfferingDoesNotExistException{
		return ((this.getTotalCapacity() - this.getCurrentlyFilled()) <= 0);
	}
	
	/*
	 * Adds one to the seats filled for the course offering
	 * Called after a student registers for a course
	 */
	public boolean addOneSeatFilledToCourseOffered() throws CourseOfferingNotCurrentException{
		boolean success = false;
		int currentlyFilled = 0;
		if(!checkIfCurrent()){
			throw new CourseOfferingNotCurrentException("This course offering is not current");
		}
		
		try{
			Connection conn = Database.getConnection();
			
			try{
				
				if(isCourseFull()){
					return false;
				}
				
				if(conn != null){
					String courseOfferSelect = "Select *"
							+ " FROM coursesoffered"
							+ " WHERE OfferID= ?";
					PreparedStatement statement = conn.prepareStatement(courseOfferSelect, ResultSet.CONCUR_UPDATABLE);
					statement.setInt(1, this.getOfferID());
					ResultSet rs = statement.executeQuery();
					
					if(rs.first()){
						DBAnnotation.annoate("seatsFilled", "coursesoffered", "SeatsFilled", true);
						int seatsFilled = rs.getInt("SeatsFilled");
						currentlyFilled = seatsFilled;
						currentlyFilled += 1;
					}
					
					else{
						throw new CourseOfferingDoesNotExistException();
					}
					
					DBAnnotation.annoate("currentlyFilled", "coursesoffered", "SeatsFilled", false);
					DBAnnotation.annoate("offerID", "coursesoffered", "OfferID", false);
					
					String updateStatement = "UPDATE coursesoffered "
							+ "SET SeatsFilled= ? "
							+ "WHERE OfferID= ? ;";
					statement = conn.prepareStatement(updateStatement, ResultSet.CONCUR_UPDATABLE);
					statement.setInt(1, currentlyFilled);
					int offerID = this.getOfferID();
					statement.setInt(2, offerID);
					statement.executeUpdate();
					Database.commitTransaction(conn);
					success = true;
										
				}					
				
			}
			
			catch(SQLException e){
				System.out.println("Error in adding one seat");
				System.out.println(e.getMessage());
				e.printStackTrace();	
			} catch (CourseOfferingDoesNotExistException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		
		
		finally{
		}
		
		return success;
	}
	
	/*
	 * Subtracts one seat from the course offering after the student unregisters
	 */
	public boolean removeOneSeatFromCourseOffered() throws CourseOffered.CourseOfferingDoesNotExistException{
		boolean seatRemoved = false;
		int offerID = this.getOfferID();
		try{
			Connection conn = Database.getConnection();
			try{
				if(conn != null){
					String SQLcoursesOfferedSelect = "Select * FROM coursesoffered WHERE OfferID= ?;";
					PreparedStatement statement = conn.prepareStatement(SQLcoursesOfferedSelect,ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
					statement.setInt(1, offerID);
					ResultSet rs = statement.executeQuery();
					if(rs.first()){
						DBAnnotation.annoate("currentlyFilled", "coursesoffered", "SeatsFilled", true);
						int currentlyFilled = rs.getInt("SeatsFilled");
						if(currentlyFilled<=0)
							return false;
						currentlyFilled -= 1;
						rs.updateInt(5, currentlyFilled);
						Database.commitTransaction(conn);
						seatRemoved = true;
					}
					else{
						throw new CourseOffered.CourseOfferingDoesNotExistException();
					}
					
				}						
					
			} catch(SQLException e){
				System.out.println("Error addind course offering");
				System.out.println(e.getMessage());
				e.printStackTrace();
				Database.rollBackTransaction(conn);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
			
		return seatRemoved ;
	}
	
	/*
	 * Checks if the current course offering is scheduled or not
	 * Basically it acts as a safety check for some of the functions
	 */
	public boolean checkIfScheduled() throws CourseOfferingNotCurrentException{
		if(!checkIfCurrent()){
			return false;
		}
		
		boolean doesExist = false;
		
		try{
			Connection conn = Database.getConnection();
			
			try{
				if(conn != null){
					
					//Retrieve the current semester ID
					String scheduleSelect = "Select *"
							+ " FROM courseschedule"
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
	
	/*
	 * Checks of the offer id exists in the database 
	 */
	public static boolean checkIfExists(int offerID){
		boolean doesExist = false;
		
		try{
			Connection conn = Database.getConnection();
			
			try{
				if(conn != null){
					
					//Retrieve the current semester ID
					String scheduleSelect = "Select *"
							+ " FROM coursesoffered"
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
	
	/*
	 * Returns the semester id of teh current on going semester
	 */
	public static int getCurrentSemesterID(){
		int current = -1;
		try{
			Connection conn = Database.getConnection();
			
			try{
				if(conn != null){
					
					//Retrieve the current semester ID
					String semSelect = "Select *"
							+ " FROM semester"
							+ " WHERE isCurrent= ?";
					PreparedStatement statement = conn.prepareStatement(semSelect);
					statement.setInt(1, 1);
					ResultSet rs = statement.executeQuery();
					
					if(rs.first()){
						DBAnnotation.annoate("current", "semester", "SemesterID", true);
						current = rs.getInt("SemesterID");
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
	
	/*
	 * Checks of the course offering is current or if it was a previously offered course
	 */
	public boolean checkIfCurrent(){
		int semID = this.getSemesterID();
		return (semID == getCurrentSemesterID());
	}
	
	/*
	 * Checks if the course is register-able by the student passed
	 */
	public boolean isCourseRegistrableBy(Student student){
		
		//check if the student is already registered
		if(WaitList.isStudentRegistered(student, offerID)){
			return false;
		}
		
		//check if the course is full
		try {
			if(isCourseFull()){
				return false;
			}
		} catch (CourseOfferingDoesNotExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		if(WaitList.isStudentEmailed(student, offerID)){
			return true;
		}
		
		//check if the student is on the waitList
		if(WaitList.isStudentOnWaitList(student, offerID)){
			return false;
		}
		
		if(!WaitList.isWaitListEmpty(offerID)){
			return false;
		}
				
		return true;
		
	}
	
	/*
	 * Sends all the course files to the student who is passed as a parameter
	 */
	public boolean sendCourseFilesToStudent(Student s){
		ArrayList<File> file = this.getFiles();
		
		String [] attachments = new String[file.size()];
		for(int i=0;i<file.size();i++){
			File f = file.get(i);
			attachments[i] = f.getFileLocation()+"\\"+f.getFileName();
		}
		
		Email email = Email.getInstance("umas.uic@gmail.com", "cs597project");
		boolean isSent = email.sendEmailWithAttachments(s.getUserName()+"@umasuic.edu", "Course files for course "+this.getCourseName(), ""
				+ "Find attachments", attachments);
		
		return isSent;
	}
	
	/*
	 * CourseDoesnotExist Exception
	 */
	public static class CourseOfferingDoesNotExistException extends Exception{
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

	/*
	 * CourseOfferingAlreadyExistsException
	 */
	public static class CourseOfferingAlreadyExistsException extends Exception{
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

	/*
	 * CourseOfferingNotCurrent Exception
	 */
	public static class CourseOfferingNotCurrentException extends Exception{
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

	/*
	 * CourseOfferingNotSchedulable Exception
	 */
	public static class CourseOfferingNotSchedulable extends Exception{
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

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object arg0) {
		if(arg0 instanceof CourseOffered){
			CourseOffered temp = (CourseOffered)arg0;
			return(temp.getOfferID() == this.getOfferID());
		}
		
		else return false;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return (this.offerID*31);
	}

}

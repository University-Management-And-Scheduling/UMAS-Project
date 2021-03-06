package com.umas.code;


//import java.lang.annotation.ElementType;
//import java.lang.annotation.Retention;
//import java.lang.annotation.RetentionPolicy;
//import java.lang.annotation.Target;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;


public class StudentEnrollment {
	int enrollmentID; // Unique id per enrollment
	int UIN;		// Student UIN
	int offerID; //OfferID of course offered in a sem
	String grade; //Student Grade = 'A', 'B','C', 'D' and 'F'
	private Student stdnt;
	private CourseOffered cOffered;
	
	
//	@Target({ElementType.LOCAL_VARIABLE})
//	@Retention(RetentionPolicy.RUNTIME)
//	public @interface DBAnnotation {
//	 String[] variable () default "";
//	 String[] table () default "";
//	 String[] column () default "";
//	 boolean[] isSource () default false; 
//	}
	

	public int getEnrollmentID() {
		return enrollmentID;
	}

	// Constructor to populate object
	public StudentEnrollment(int enrollmentID, int UIN, int offerID, String grade) {
		super();
		this.enrollmentID = enrollmentID;
		this.UIN = UIN;
		this.offerID = offerID;
		this.grade = grade;
	}
	
	// Constructor to populate object
	public StudentEnrollment(int UIN, int offerID, String grade) {
		super();
		this.offerID = offerID;
		this.grade = grade;
		this.UIN = UIN;
	}
	
	// Constructor to create objects of studentenrollment before inserting into 
	// the database for the first time
	public StudentEnrollment(int offerID, int UIN) {
		super();
		this.offerID = offerID;
		this.grade = "A"; // Default grade at the time of enrollment
		this.UIN = UIN;
	}
	
	public int getUIN() {
		return UIN;
	}

	public void setUIN(int uIN) {
		UIN = uIN;
	}

	public int getOfferID() {
		return offerID;
	}

	public void setEnrollmentID(int enrollmentID) {
		this.enrollmentID = enrollmentID;
	}

	public void setOfferID(int offerID) {
		this.offerID = offerID;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}
	
	// Given a student this function returns all the grades of that student
	public static HashMap<CourseOffered, String> getAllGradesOfStudent (Student student) {
		int UIN = student.getUIN();
		HashMap<CourseOffered, String> courseGrade = new HashMap<CourseOffered, String>();
		
//		@DBAnnotation (
//				variable = {"UIN"},  
//				table = "studentenrollment", 
//				column = {"UIN"}, 
//				isSource = true)
		
		String SQLGradeSelect = "Select OfferID, Grade FROM studentenrollment WHERE UIN = ?;";
		try{
			Connection conn = Database.getConnection();
			
			try{
			
				if(conn != null){
					
					PreparedStatement statement = conn.prepareStatement(SQLGradeSelect);
					DBAnnotation.annoate("UIN", "studentenrollment", "UIN", false);
					statement.setInt(1, UIN);
					ResultSet rs = statement.executeQuery();
				
					while(rs.next()){
						//Retrieve by column name
						DBAnnotation.annoate("offerID", "studentenrollment", "OfferID", true);
				        int offerID = rs.getInt("OfferID");
				        DBAnnotation.annoate("grade", "studentenrollment", "Grade", true);
				        String grade = rs.getString("Grade");
				        CourseOffered studentCourse = null;
				 		
				 		try {
				 			studentCourse = new CourseOffered(offerID);
				 		} catch (Course.CourseDoesNotExistException e1) {
				 			e1.printStackTrace();
				 		} catch (CourseOffered.CourseOfferingDoesNotExistException e1) {
				 			e1.printStackTrace();
				 		}
				         
				         courseGrade.put(studentCourse, grade);
//				         Item item = new Item(itemID, itemName,itemQuantity, itemCost);
//				         items.add(item);
					}      
				}
			}
			catch(SQLException e){
				System.out.println(e);
			}
				
		}
		catch(Exception e){
			System.out.println(e);
		}


		return courseGrade;
	
	}

	// Given a student this function returns all the courses taken by that student till date 
	public static ArrayList<CourseOffered> getStudentsAllCourses(Student student){
		ArrayList<CourseOffered> coursesTaken = new ArrayList<CourseOffered>();
		
		int UIN = student.getUIN();
		
//		@DBAnnotation (
//				variable = {"UIN"},  
//				table = "studentenrollment", 
//				column = {"UIN"}, 
//				isSource = true)
		
		String SQLGradeSelect = "Select OfferID FROM studentenrollment WHERE UIN = ?;";
		try{
			Connection conn = Database.getConnection();
			
			try{
			
				if(conn != null){
					
					PreparedStatement statement = conn.prepareStatement(SQLGradeSelect);
					DBAnnotation.annoate("UIN", "studentenrollment", "UIN", false);
					statement.setInt(1, UIN);
					ResultSet rs = statement.executeQuery();
				
					while(rs.next()){
						//Retrieve by column name
						DBAnnotation.annoate("offerID", "studentenrollment", "OfferID", true);
				        int offerID = rs.getInt("OfferID");
				        CourseOffered studentCourse = null;
				 		
				 		try {
				 			studentCourse = new CourseOffered(offerID);
				 		} catch (Course.CourseDoesNotExistException e1) {
				 			e1.printStackTrace();
				 		} catch (CourseOffered.CourseOfferingDoesNotExistException e1) {
				 			e1.printStackTrace();
				 		}
				         
				 		coursesTaken.add(studentCourse);
					}      
				}
			} catch(SQLException e){
				System.out.println(e);
			}
				
		} catch(Exception e){
			System.out.println(e);
		}
		
		return coursesTaken;
	}
	
	// Given a course, this function retrives all the students in a particular course
 	public static ArrayList<Student> getStudentsInCourse(CourseOffered courseOffered) {
		ArrayList<Student> enrolledStudents = new ArrayList<Student>();
		
		int offerID = courseOffered.getOfferID();
		
//		@DBAnnotation (
//				variable = {"offerID"},  
//				table = "studentenrollment", 
//				column = {"OfferID"}, 
//				isSource = true)
		
		String SQLGradeSelect = "Select UIN FROM studentenrollment WHERE OfferID = ?;";
		try{
			Connection conn = Database.getConnection();
			
			try{
			
				if(conn != null){
					
					PreparedStatement statement = conn.prepareStatement(SQLGradeSelect);
					DBAnnotation.annoate("offerID", "studentenrollment", "OfferID", false);
					statement.setInt(1, offerID);
					ResultSet rs = statement.executeQuery();
				
					while(rs.next()){
						//Retrieve by column name
						DBAnnotation.annoate("UIN", "studentenrollment", "UIN", true);
				        int UIN = rs.getInt("UIN");
				        Student student = new Student(UIN);
						enrolledStudents.add(student);
					}      
				}
			} catch(SQLException e){
				System.out.println(e);
			}
				
		} catch(Exception e){
			System.out.println(e);
		}
		return enrolledStudents;
	}
	
 	// Given a student this function returns all the current courses taken by that student
	public static ArrayList<CourseOffered> getCurrentCoursesOfStudent (Student student) {
		ArrayList<CourseOffered> enrolledCourses = new ArrayList<CourseOffered>();
		
		int UIN = student.getUIN();
		
//		@DBAnnotation (
//				variable = {"UIN"},  
//				table = "studentenrollment", 
//				column = {"UIN"}, 
//				isSource = true)
		
		String SQLGradeSelect = "SELECT studentenrollment.OfferID " +  
								"FROM university.studentenrollment JOIN coursesoffered JOIN semester " +
								"Where studentenrollment.UIN = ? " +
								"AND studentenrollment.OfferID = coursesoffered.OfferID " + 
								"AND coursesoffered.SemesterID = semester.SemesterID " +
								"AND semester.IsCurrent = 1;";
		try{
			Connection conn = Database.getConnection();
			
			try{
			
				if(conn != null){
					
					PreparedStatement statement = conn.prepareStatement(SQLGradeSelect);
					DBAnnotation.annoate("UIN", "studentenrollment", "UIN", false);
					statement.setInt(1, UIN);
					ResultSet rs = statement.executeQuery();
				
					while(rs.next()){
						//Retrieve by column name
						DBAnnotation.annoate("offerID", "studentenrollment", "OfferID", true);
				        int offerID = rs.getInt("OfferID");
				        CourseOffered studentCourse = null;
				 		
				 		try {
				 			studentCourse = new CourseOffered(offerID);
				 		} catch (Course.CourseDoesNotExistException e1) {
				 			e1.printStackTrace();
				 		} catch (CourseOffered.CourseOfferingDoesNotExistException e1) {
				 			e1.printStackTrace();
				 		}
				         
				 		enrolledCourses.add(studentCourse);
					}      
				}
			} catch(SQLException e){
				System.out.println(e);
			}
				
		} catch(Exception e){
			System.out.println(e);
		}
		
		return enrolledCourses;
	}

	// A synchronized function to enroll students in a course in a Firct Come First Served basis.
	// This ensure the database integrity that values(like seats remaining) read are the same while enrolling
	// and do not change in(i.e. the course is not filled up) in the middle of an enrollment.
	public synchronized boolean enrollStudents(){
		boolean isStudentEnrolled = false;
		int UIN = this.getUIN();
		int offerID = this.getOfferID();
		String grade = this.getGrade();
		
		// Step 1: Check if student is already enrolled for this course
		boolean isStudentCurrentlyEnrolled = this.isStudentEnrolled(UIN, offerID);
		if(isStudentCurrentlyEnrolled == true){
			System.out.println("The student is already enrolled");
		} else{
			// Step 2: if student is not enrolled, check whether there are any seats left.
			boolean isSeatAvailable = this.isSeatAvailable();
			if(isSeatAvailable == false){
				System.out.println("Seats not available.");
			} else{
				
				// Step 3: If student is not enrolled currently AND 
				// if a seat is available, Enroll the student
				
				CourseOffered offeredCourse = null;
				
				try {
					offeredCourse = new CourseOffered(offerID);
				} catch (Course.CourseDoesNotExistException e1) {
					e1.printStackTrace();
				} catch (CourseOffered.CourseOfferingDoesNotExistException e1) {
					e1.printStackTrace();
				}
				
				Course course = offeredCourse.getCourse();
				String courseName = course.getCourseName();
				int semID = offeredCourse.getSemesterID();
				
				String tableName = courseName + Integer.toString(offerID) + Integer.toString(semID); 
			
				
//				@DBAnnotation (
//						variable = {"UIN","offerID"},  
//						table = "studentenrollment", 
//						column = {"UIN","OfferID"}, 
//						isSource = false)
				
				String SQLCourseExamsInsert = "INSERT INTO %s (StudentUIN,StudentEnrollmentID) VALUES(?,?) ;";
				SQLCourseExamsInsert = String.format(SQLCourseExamsInsert, tableName);
				String SQLStudentEnrollInsert = "INSERT INTO studentenrollment(UIN,OfferID,Grade) VALUES(?,?,?) ;";
				
				
				
				try {
					Connection conn = Database.getConnection();
					try {
						if (conn != null) {
						 
							String key[] = { "EnrollmentID" };
							PreparedStatement statement = conn.prepareStatement(SQLStudentEnrollInsert,key);
							DBAnnotation.annoate("UIN", "studentenrollment", "UIN", false);
							statement.setInt(1, UIN);
							DBAnnotation.annoate("offerID", "studentenrollment", "OfferID", false);
							statement.setInt(2, offerID);
							DBAnnotation.annoate("grade", "studentenrollment", "Grade", false);
							statement.setString(3, grade);
							
							statement.executeUpdate();
							
							ResultSet rs = statement.getGeneratedKeys();
							int generatedEnrollmentID = 0;
							// To get the database auto-generated EnrollmentID of
							// the student enrollment just inserted
							if (rs.next()) {
								DBAnnotation.annoate("generatedEnrollmentID", "studentenrollment", "EnrollmentID", true);
								generatedEnrollmentID= rs.getInt(1);
							}
							
							statement = conn.prepareStatement(SQLCourseExamsInsert);
							// statement.setString(1, tableName);
							DBAnnotation.annoate("UIN", "studentenrollment", "UIN", false);
							statement.setInt(1, UIN);
							DBAnnotation.annoate("generatedEnrollmentID", "studentenrollment", "EnrollmentID", false);
							statement.setInt(2, generatedEnrollmentID);
							statement.executeUpdate();
							
							boolean seatAdded = offeredCourse.addOneSeatFilledToCourseOffered();
							if(seatAdded = true){
								Database.commitTransaction(conn);
								isStudentEnrolled = true;
							} else {
								Database.rollBackTransaction(conn);
							}
							
						}	
					} catch (SQLException e) {
						System.out.println(e);
						Database.rollBackTransaction(conn);
					}

				} catch (Exception e) {
					System.out.println(e);
				}
			}	
		}
		return isStudentEnrolled;
	}
	
	// Updates the grades of all the students in a course
	public static boolean updateAllStudentGrade(HashMap<Student,String> studentGrades, CourseOffered offeredCourse){
		boolean isGradeUpdated = false;
		
		int offerID = offeredCourse.getOfferID();
		Set<Student> keys = studentGrades.keySet();
		Iterator<Student> keyIterator = keys.iterator();
		while (keyIterator.hasNext()) {
			Student student = keyIterator.next();
			int UIN = student.getUIN();
			String grade = studentGrades.get(student);
			GradeSystem gradesys = new GradeSystem(grade);
			boolean isgradePresent = gradesys.isGradePresent();
			if (isgradePresent == false){
				System.out.println("Grade Not present");
			} else {
				StudentEnrollment enrollStudent = new StudentEnrollment(UIN,offerID,grade);
				boolean updateStudentGrade = enrollStudent.updateStudentGrade();
				
				if(updateStudentGrade == false){
					System.out.println("Student " + student.getName() + "'s grade not updated");
				} else {
					isGradeUpdated = true;
				}
			
			}
		}
		
		return isGradeUpdated;
	}
	
	// Updates the grade of a single student
	public boolean updateStudentGrade(){
		boolean isGradeUpdated = false;
		int UIN = this.getUIN();
		int offerID = this.getOfferID();
		String grade = this.getGrade();
		GradeSystem gradesys = new GradeSystem(grade);
		boolean isgradePresent = gradesys.isGradePresent();
		if (isgradePresent == false){
			System.out.println("Grade Not present");
		} else {
			
		
		// Step 1: Check if student is already enrolled for this course
		boolean isStudentCurrentlyEnrolled = this.isStudentEnrolled(UIN, offerID);
		if(isStudentCurrentlyEnrolled == false){
			System.out.println("The student is not enrolled");
		} else{
			
			int enrollmentID = this.getStudentEnrollmentID();	
			// Step 2: If student is enrolled currently, update their grade.
			
//			@DBAnnotation (
//					variable = {"grade","enrollmentID"},  
//					table = "studentenrollment", 
//					column = {"Grade","EnrollmentID"}, 
//					isSource = false)
			String SQLStudentEnrollInsert = "UPDATE `studentenrollment` SET `Grade`=? WHERE `EnrollmentID`=?;";
			
			try {
				Connection conn = Database.getConnection();
				try {
					if (conn != null) {
					 
						PreparedStatement statement = conn.prepareStatement(SQLStudentEnrollInsert);
						DBAnnotation.annoate("grade", "studentenrollment", "Grade", false);
						statement.setString(1, grade);
						DBAnnotation.annoate("enrollmentID", "studentenrollment", "EnrollmentID", false);
						statement.setInt(2, enrollmentID);
						statement.executeUpdate();
						Database.commitTransaction(conn);
						isGradeUpdated = true;
					}	
				} catch (SQLException e) {
					System.out.println(e);
					Database.rollBackTransaction(conn);
				}

			} catch (Exception e) {
				System.out.println(e);
			}
		}
		}
	
		return isGradeUpdated;
	}
	
	// To retrive the enrollment id for a student
	private int getStudentEnrollmentID() {
		int enrollmentID = 0;
		int UIN = this.getUIN();
		int offerID = this.getOfferID();
		
//		@DBAnnotation (
//				variable = {"UIN","offerID","enrollmentID"},  
//				table = "studentenrollment", 
//				column = {"UIN","OfferID","EnrollmentID"}, 
//				isSource = true)
		
		String SQLStudentEnrollSelect = "Select EnrollmentID FROM studentenrollment WHERE UIN = ? AND OfferID = ?;";
		
		try{
			Connection conn = Database.getConnection();
			
			try{
			
				if(conn != null){
					
					PreparedStatement statement = conn.prepareStatement(SQLStudentEnrollSelect);
					DBAnnotation.annoate("UIN", "studentenrollment", "UIN", false);
					statement.setInt(1, UIN);
					DBAnnotation.annoate("offerID", "studentenrollment", "OfferID", false);
					statement.setInt(2, offerID);
					
					ResultSet rs = statement.executeQuery();
				
					while(rs.next()){
						DBAnnotation.annoate("enrollmentID", "studentenrollment", "EnrollmentID", true);
				        enrollmentID = rs.getInt("EnrollmentID");
					}      
					this.setEnrollmentID(enrollmentID);
				}
			}
			catch(SQLException e){
				System.out.println(e);
			}
				
		}
		catch(Exception e){
			System.out.println(e);
		}
		
		return enrollmentID;
	}

	// Unregisters a student from the student enrollment table
	public boolean unregisterStudent(){
		boolean studentUnregistered = false;
		
		int UIN = this.getUIN();
		int offerID = this.getOfferID();
		
		// Step 1: Check if student is enrolled for this course
		boolean isStudentCurrentlyEnrolled = this.isStudentEnrolled(UIN, offerID);
		if(isStudentCurrentlyEnrolled == false){
			System.out.println("The student is not enrolled");
		} else{
			
			// Step 2: If enroll, delete the student record
			int enrollmentID = this.getStudentEnrollmentID();
			
			CourseOffered offeredCourse = null;
			
			try {
				offeredCourse = new CourseOffered(offerID);
			} catch (Course.CourseDoesNotExistException e1) {
				e1.printStackTrace();
			} catch (CourseOffered.CourseOfferingDoesNotExistException e1) {
				e1.printStackTrace();
			}
			
			Course course = offeredCourse.getCourse();
			String courseName = course.getCourseName();
			int semID = offeredCourse.getSemesterID();
			
			String tableName = courseName + Integer.toString(offerID) + Integer.toString(semID); 
		
			
//			@DBAnnotation (
//					variable = {"EnrollmentID","UIN"},  
//					table = {"studentenrollment","tableName"}, 
//					column = {"UIN","OfferID","All"}, 
//					isSource = true)
			
			String SQLCourseExamsDelete = "DELETE FROM %s WHERE `StudentUIN`=?;";
			SQLCourseExamsDelete = String.format(SQLCourseExamsDelete, tableName);
			String SQLStudentEnrollDelete = "DELETE FROM `studentenrollment` WHERE `EnrollmentID`=?;";
			
			try {
				Connection conn = Database.getConnection();
				try {
					if (conn != null) {
					 
						PreparedStatement statement = conn.prepareStatement(SQLCourseExamsDelete);
						//statement.setString(1, tableName);
						DBAnnotation.annoate("UIN", "studentenrollment", "UIN", false);
						statement.setInt(1, UIN);
						statement.executeUpdate();
						
						statement = conn.prepareStatement(SQLStudentEnrollDelete);
						DBAnnotation.annoate("enrollmentID", "studentenrollment", "EnrollmentID", false);
						statement.setInt(1, enrollmentID);
						statement.executeUpdate();
						boolean removed = this.removeOneSeatFromCourseOffered(offeredCourse);
						if(removed == true){
							Database.commitTransaction(conn);
							studentUnregistered = true;
						} else {
							Database.rollBackTransaction(conn);
						}
						
					}	
				} catch (SQLException e) {
					System.out.println(e);
					Database.rollBackTransaction(conn);
				}

			} catch (Exception e) {
				System.out.println(e);
			}
		}
		
		return studentUnregistered;
	}
	
	// Checks whether the student is enrolled or not
	private boolean isStudentEnrolled(int UIN,int offerID){
		boolean isStudentEnrolled = false;
		
//		@DBAnnotation (
//				variable = {"UIN","offerID"},  
//				table = "studentenrollment", 
//				column = {"UIN","OfferID"}, 
//				isSource = true)
		
		String SQLStudentEnrollSelect = "Select UIN FROM studentenrollment WHERE UIN = ? AND OfferID = ?;";
		
		try{
			Connection conn = Database.getConnection();
			
			try{
			
				if(conn != null){
					
					PreparedStatement statement = conn.prepareStatement(SQLStudentEnrollSelect);
					DBAnnotation.annoate("UIN", "studentenrollment", "UIN", false);
					statement.setInt(1, UIN);
					DBAnnotation.annoate("offerID", "studentenrollment", "OfferID", false);
					statement.setInt(2, offerID);
					
					ResultSet rs = statement.executeQuery();
				
					while(rs.next()){
						//Retrieve by column name
						DBAnnotation.annoate("UIN", "studentenrollment", "UIN", true);
				        int tableUIN = rs.getInt("UIN");
				         
				        if (UIN == tableUIN){
				        	isStudentEnrolled = true;
				        }
					}      
				}
			}
			catch(SQLException e){
				System.out.println(e);
			}
				
		}
		catch(Exception e){
			System.out.println(e);
		}
		
		
		return isStudentEnrolled;
	}
	
	// Checks whether a seat is available for the course or not
	private boolean isSeatAvailable(){
		boolean isSeatAvailable = false;
		
		int offerID = this.getOfferID();
		
//		@DBAnnotation (
//				variable = {"offerID"},  
//				table = "coursesoffered", 
//				column = {"OfferID"}, 
//				isSource = true)
		
		String SQLStudentEnrollSelect = "Select OfferID FROM coursesoffered " +
										"WHERE SeatsFilled < TotalCapacity " +
										"AND OfferID = ?;";
		
		try{
			Connection conn = Database.getConnection();
			
			try{
			
				if(conn != null){
					
					PreparedStatement statement = conn.prepareStatement(SQLStudentEnrollSelect);
					DBAnnotation.annoate("offerID", "studentenrollment", "OfferID", false);
					statement.setInt(1, offerID);
					
					ResultSet rs = statement.executeQuery();
				
					while(rs.next()){
						//Retrieve by column name
						DBAnnotation.annoate("tableOfferID", "studentenrollment", "OfferID", true);
				        int tableOfferID = rs.getInt("OfferID");
				         
				        if (offerID == tableOfferID){
				        	isSeatAvailable = true;
				        }
					}      
				}
			}
			catch(SQLException e){
				System.out.println(e);
			}
				
		}
		catch(Exception e){
			System.out.println(e);
		}
		
		
		return isSeatAvailable;
	}
	
	// To update the number of seats for the mentioned course in the course offered table
	// by increasing the number by one when a student is unregistered.
	private  boolean removeOneSeatFromCourseOffered(CourseOffered courseOffered) throws CourseOffered.CourseOfferingDoesNotExistException{
		boolean seatRemoved = false;
		
		int offerID = this.getOfferID();
		try{
			Connection conn = Database.getConnection();
			try{
				if(conn != null){
					int currentlyFilled = -1;
//					@DBAnnotation (
//							variable = {"offerID"},  
//							table = "coursesoffered", 
//							column = {"OfferID"}, 
//							isSource = true)
					String SQLcoursesOfferedSelect = "Select * FROM coursesoffered WHERE OfferID= ?;";
					PreparedStatement statement = conn.prepareStatement(SQLcoursesOfferedSelect);
					DBAnnotation.annoate("offerID", "coursesoffered", "OfferID", false);
					statement.setInt(1, offerID);
					
					ResultSet rs = statement.executeQuery();
					if(rs.next()){
						DBAnnotation.annoate("offerID", "coursesoffered", "OfferID", true);
						currentlyFilled = rs.getInt("SeatsFilled");
						currentlyFilled -= 1;
//						rs.updateInt(5, currentlyFilled);
//						Database.commitTransaction(conn);
//						seatRemoved = true;
						
					}
					else{
						throw new CourseOffered.CourseOfferingDoesNotExistException();
					}
					
					String updateStatement = "UPDATE university.coursesoffered "
							+ "SET SeatsFilled= ? "
							+ "WHERE OfferID= ? ;";
					statement = conn.prepareStatement(updateStatement, ResultSet.CONCUR_UPDATABLE);
					
					DBAnnotation.annoate("currentlyFilled", "coursesoffered", "CurrentlyFilled", false);
					statement.setInt(1, currentlyFilled);
					DBAnnotation.annoate("offerID", "coursesoffered", "OfferID", false);
					statement.setInt(2, this.getOfferID());
					statement.executeUpdate();
					Database.commitTransaction(conn);
					seatRemoved = true;
				}						
					
			} catch(SQLException e){
				System.out.println("Error adding course offering");
				System.out.println(e.getMessage());
				e.printStackTrace();
				Database.rollBackTransaction(conn);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
			
		return seatRemoved ;
	}
	
	public static void main(String[] args){
		// Test getAllGradesOfStudent function
//		try {
//			Student student = new Student(1);
//		} catch (People.PersonDoesNotExistException e) {
//			e.printStackTrace();
//		}
//		StudentEnrollment enrolledStud = new StudentEnrollment(345678,1);
//		HashMap<CourseOffered, String> grades = StudentEnrollment.getAllGradesOfStudent(student);
//		for(CourseOffered offeredCourse: grades.keySet() ){
//			int offerID = offeredCourse.getOfferID();
//			String grade = grades.get(offeredCourse);
//			System.out.println("Course: "+ offerID + " Grade: " + grade);
//		}
//		
//		// Test getStudentsAllCourses function
//		ArrayList<CourseOffered> courses = StudentEnrollment.getStudentsAllCourses(student);
//		for(CourseOffered offeredCourse: courses){
//			int offerID = offeredCourse.getOfferID();
//			System.out.println("Course: "+ offerID);			
//		}
		
		// Test getStudentsInCourse function
//		int offerID = 345678;
//		CourseOffered offeredCourse = null;
//		try {
//			offeredCourse = new CourseOffered(offerID);
//		} catch (Course.CourseDoesNotExistException
//				| CourseOffered.CourseOfferingDoesNotExistException e) {
//			e.printStackTrace();
//		}
//		
//		// Test getStudentsInCourse function
//		ArrayList<Student> students = StudentEnrollment.getStudentsInCourse(offeredCourse);
//		for(Student stud: students){
//			int UIN = stud.getUIN();
//			System.out.println("Student: "+ UIN);			
//		}
		
		// Test getStudentsAllCourses function
//		ArrayList<CourseOffered> courses = StudentEnrollment.getCurrentCoursesOfStudent(student);
//		for(CourseOffered offeredCourse: courses){
//			int offerID = offeredCourse.getOfferID();
//			System.out.println("Course: "+ offerID);			
//		}
		
		// To enroll student
//		StudentEnrollment enrolledStud = new StudentEnrollment(345678,4);
//		boolean enrolled =  enrolledStud.enrollStudents();
//		if (enrolled == true){
//			System.out.println("Student Enrolled");
//		} else {
//			System.out.println("Student Not Enrolled");
//		}
		
		// To unregister students
//		StudentEnrollment enrolledStud = new StudentEnrollment(345678,4);
//		boolean enrolled =  enrolledStud.unregisterStudent();
//		if (enrolled == true){
//			System.out.println("Student Unregistered");
//		} else {
//			System.out.println("Student Still Registered");
//		}
	
		// Update Grades
//		StudentEnrollment enrolledStud = new StudentEnrollment(2, 123456, "C");
//		boolean updated =  enrolledStud.updateStudentGrade();
//		if (updated == true){
//			System.out.println("Student Grade Updated");
//		} else {
//			System.out.println("Student Grade Not Updated");
//		}
		
		
		// To update multiple student's grades
//		int offerID = 345678;
//		CourseOffered offeredCourse = null;
//		try {
//			offeredCourse = new CourseOffered(offerID);
//		} catch (Course.CourseDoesNotExistException
//				| CourseOffered.CourseOfferingDoesNotExistException e) {
//			e.printStackTrace();
//		}
//		HashMap<Student,String> studentGrades = new HashMap<Student,String>();
//		student = new Student(1);
//		studentGrades.put(student, "B");
//		student = new Student(2);
//		studentGrades.put(student, "A");
//		
//		boolean updated =  StudentEnrollment.updateAllStudentGrade(studentGrades, offeredCourse);
//		if (updated == true){
//			System.out.println("Students Grades Updated");
//		} else {
//			System.out.println("Students Grades Not Updated");
//		}
		
	}
	
}

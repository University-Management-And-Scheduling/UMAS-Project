import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;


public class CourseExams {

	int offerID;
	String examName;
	HashMap examMarks = new HashMap<Student,Double>(); 
	
	@Target({ElementType.LOCAL_VARIABLE})
	@Retention(RetentionPolicy.RUNTIME)
	public @interface DBAnnotation {
	 String[] variable () default "";
	 String[] table () default "";
	 String[] column () default "";
	 boolean[] isSource () default false; 
	}
	
	public CourseExams(int offerID, String examName, HashMap examMarks) {
		super();
		this.offerID = offerID;
		this.examName = examName;
		this.examMarks = examMarks;
	}

	public CourseExams(int offerID) {
		super();
		this.offerID = offerID;
		this.examName = examName;
	}

	public int getOfferID() {
		return offerID;
	}

	public void setOfferID(int offerID) {
		this.offerID = offerID;
	}

	public String getExamName() {
		return examName;
	}

	public void setExamName(String examName) {
		this.examName = examName;
	}
	
	public HashMap getExamMarks() {
		return examMarks;
	}

	public void setExamMarks(HashMap examMarks) {
		this.examMarks = examMarks;
	}

	public static void createCourseExamMarksTable(CourseOffered offeredCourse){
		
		Course course = offeredCourse.getCourse();
		String courseName = course.getCourseName();
		int offerID= offeredCourse.getOfferID();
		int semID = offeredCourse.getSemesterID();
		
		
		String tableName = courseName + Integer.toString(offerID) + Integer.toString(semID); 
		String studentIDConstraint = tableName + "studentID";
		String studentEnrollmentIDConstraint = tableName + "studentEnrollmentID";
		
//		@DBAnnotation (
//				variable = {"tableName"},  
//				table = "courseExamStructureTable", 
//				column = {"Username","Password"}, 
//				isSource = false)
		String SQLExamCreate = "CREATE TABLE ? (`StudentUIN` int(12) NOT NULL,`StudentEnrollmentID` int(12) NOT NULL, " +
				" PRIMARY KEY (`StudentUIN`), KEY `StudentID_idx` (`StudentUIN`),  " + 
				"KEY `StudentEnrollmentID_idx` (`StudentEnrollmentID`), " +
				"CONSTRAINT `?` FOREIGN KEY (`StudentEnrollmentID`) REFERENCES `studentenrollment` (`EnrollmentID`) ON DELETE NO ACTION ON UPDATE NO ACTION," +
				"CONSTRAINT `?` FOREIGN KEY (`StudentUIN`) REFERENCES `student` (`UIN`) ON DELETE CASCADE ON UPDATE CASCADE;" ;

		
		try {
			Connection conn = Database.getConnection();
			try {
				if (conn != null) {
				 
					PreparedStatement statement = conn.prepareStatement(SQLExamCreate);
					statement.setString(1, tableName);
					statement.setString(2, studentIDConstraint);
					statement.setString(3, studentEnrollmentIDConstraint);
					statement.executeUpdate();
					CourseExamStructure.createCourseExamStructureTable(offeredCourse);
					Database.commitTransaction(conn);
					
				}	
			} catch (SQLException e) {
				System.out.println(e);
				Database.rollBackTransaction(conn);
			}

		} catch (Exception e) {
			System.out.println(e);
		}
		
	}
		
	public static boolean addNewExamColumn(CourseExamStructure courseExamStructure){
		boolean examAdded = false;
		
		CourseOffered offeredCourse = courseExamStructure.getOfferedCourse();
		Course course = offeredCourse.getCourse();
		String courseName = course.getCourseName();
		int offerID= offeredCourse.getOfferID();
		int semID = offeredCourse.getSemesterID();
		
		
		String tableName = courseName + Integer.toString(offerID) + Integer.toString(semID); 
	
		String examName = courseExamStructure.getExamName();
	
		boolean isExamPresent = isExamPresent(tableName,examName);
		if (isExamPresent == true){
			System.out.println("Exam already present. Please try again.");
		} else {
			
		@DBAnnotation (
				variable = {"examName",},  
				table = "tableName", 
				column = {"ExamName"}, 
				isSource = false)
		String SQLExamAlter = "ALTER TABLE ? ADD COLUMN `?` DECIMAL(4,1) NULL DEFAULT NULL ;";
		
		try {
			Connection conn = Database.getConnection();
			try {
				if (conn != null) {
				 
					PreparedStatement statement = conn.prepareStatement(SQLExamAlter);
					statement.setString(1, tableName);
					statement.setString(2, examName);
					statement.executeUpdate();					
					examAdded = true;
				}	
			} catch (SQLException e) {
				System.out.println(e);
				Database.rollBackTransaction(conn);
			}

		} catch (Exception e) {
			System.out.println(e);
		}
		} // End of Else
		return examAdded;
	}
	
	private static boolean isExamPresent(String tableName, String examName){
		boolean isExamPresent = false;
		
		@DBAnnotation (
				variable = {"tableName",},  
				table = "INFORMATION_SCHEMA.COLUMNS", 
				column = {"column_name"}, 
				isSource = true)
		String INFORMATION_SCHEMA_COLUMNS_Select = "SELECT ISC.column_name FROM INFORMATION_SCHEMA.COLUMNS ISC WHERE ISC.table_name = '?';";
		
		try {
			Connection conn = Database.getConnection();
			try {
				if (conn != null) {
				 
					PreparedStatement statement = conn.prepareStatement(INFORMATION_SCHEMA_COLUMNS_Select);
					statement.setString(1, tableName);
					ResultSet rs = statement.executeQuery();
					while(rs.next()){
						String tableExamName = rs.getString("column_name");
						if(examName == tableExamName){
							isExamPresent = true;
							break;
						}
					}
				}	
			} catch (SQLException e) {
				System.out.println(e);
			}

		} catch (Exception e) {
			System.out.println(e);
		}
		
		return isExamPresent;
		
	}
	
	public static boolean modifyExistingExamColumnName(CourseExamStructure courseExamStructure, String newExamName){
		boolean modifiedColumn = false;
		
		CourseOffered offeredCourse = courseExamStructure.getOfferedCourse();
		Course course = offeredCourse.getCourse();
		String courseName = course.getCourseName();
		int offerID= offeredCourse.getOfferID();
		int semID = offeredCourse.getSemesterID();
		
		
		String tableName = courseName + Integer.toString(offerID) + Integer.toString(semID); 
	
		String examName = courseExamStructure.getExamName();
	
		boolean isExamPresent = isExamPresent(tableName,examName);
		boolean isNewExamPresent = isExamPresent(tableName,newExamName);
		if ((isExamPresent == true) && (isNewExamPresent== false) ){
						
		@DBAnnotation (
				variable = {"tableName","examName","newExamName"},  
				table = "tableName", 
				column = {"ExamName"}, 
				isSource = false)
		String SQLExamAlter = "ALTER TABLE `?` CHANGE COLUMN `?` `?` DECIMAL(4,1) NULL;";
		
		try {
			Connection conn = Database.getConnection();
			try {
				if (conn != null) {
				 
					PreparedStatement statement = conn.prepareStatement(SQLExamAlter);
					statement.setString(1, tableName);
					statement.setString(2, examName);
					statement.setString(3, newExamName);
					statement.executeUpdate();					
					modifiedColumn = true;
				}	
			} catch (SQLException e) {
				System.out.println(e);
				Database.rollBackTransaction(conn);
			}

		} catch (Exception e) {
			System.out.println(e);
		} 
		
		} else {
			System.out.println("Old exam name not present or New exam name already present. Please try again.");
		} 
		
		return modifiedColumn;
	}	
	
	public static boolean deleteExistingExamColumn(CourseExamStructure courseExamStructure){
		boolean examDeleted = false;
		
		CourseOffered offeredCourse = courseExamStructure.getOfferedCourse();
		Course course = offeredCourse.getCourse();
		String courseName = course.getCourseName();
		int offerID= offeredCourse.getOfferID();
		int semID = offeredCourse.getSemesterID();
		
		String tableName = courseName + Integer.toString(offerID) + Integer.toString(semID);
		
		String examName = courseExamStructure.examName;
		
		boolean isExamPresent = isExamPresent(tableName,examName);
		if (isExamPresent == true){
						
		@DBAnnotation (
				variable = {"examName"},  
				table = "tableName", 
				column = {"ExamName"}, 
				isSource = false)
		String SQLExamDelete = "ALTER TABLE `?` DROP COLUMN `?` ;";
		
		try {
			Connection conn = Database.getConnection();
			try {
				if (conn != null) {
					PreparedStatement statement = conn.prepareStatement(SQLExamDelete);
					statement.setString(1, tableName);
					statement.setString(2, examName);
					statement.executeUpdate();
					examDeleted = true;
				}	
			} catch (SQLException e) {
				System.out.println(e);
				Database.rollBackTransaction(conn);
			}

		} catch (Exception e) {
			System.out.println(e);
		}
		} else {
			System.out.println("Exam not present. Please try again.");
		} // End of Else
		return examDeleted;
	}

	public boolean addStudentMarks(){
		boolean studentsMarksAdded = false;
		
		int offerID = this.getOfferID();
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
	
		String examName = this.getExamName();
		HashMap examMarks = this.examMarks;
		Set<Student> keys = examMarks.keySet();
		Iterator<Student> keyIterator = keys.iterator();
		while (keyIterator.hasNext()) {
			Student student = keyIterator.next();
			int UIN = student.getUIN();
			
			double marks = (double) examMarks.get(student); 
			
			// Step 1: Check if the student is enrolled for this course
			boolean isStudentEnrolled = isStudentEnrolled(student,offerID);
			
			// Step 2: Get student Enrollment ID
			int enrollmentID = StudentEnrollment.getStudentEnrollmentID(student,offerID);
			
			// If enrolled, add his marks
			if (isStudentEnrolled == true){
				
				@DBAnnotation (
						variable = {"marks","UIN"},  
						table = "tableName", 
						column = {"ExamName","StudentUIN"}, 
						isSource = false)
				String SQLExamUpdate = "UPDATE `?` SET `?`='?' WHERE `StudentUIN`='?';";
				
				try {
					Connection conn = Database.getConnection();
					try {
						if (conn != null) {
						 
							PreparedStatement statement = conn.prepareStatement(SQLExamUpdate);
							statement.setString(1, tableName);
							statement.setString(2, examName);
							statement.setDouble(3, marks);
							statement.setInt(3, UIN);
							statement.executeUpdate();					
							Database.commitTransaction(conn);
							studentsMarksAdded = true;
						}	
					} catch (SQLException e) {
						System.out.println(e);
						Database.rollBackTransaction(conn);
					}

				} catch (Exception e) {
					System.out.println(e);
				}
				
			} else {
				System.out.println("The student " + student.getName() +" is not enrolled.");
			}
			
		}
		return studentsMarksAdded;
	}
	
	private boolean isStudentEnrolled(Student student, int offerID) {
		boolean isStudentEnrolled = false;
		
		ArrayList<CourseOffered> coursesTaken = StudentEnrollment.getStudentsCourses(student);
		
		for(CourseOffered course : coursesTaken){
			int courseOfferID = course.getOfferID();
			if ( courseOfferID == offerID ){
				isStudentEnrolled = true;
				break;
			}
		}
		
		return isStudentEnrolled;
	}

	public  CourseExams getStudentMarks(){
		int offerID = this.getOfferID();
		HashMap<Student, Double> examMarks = new HashMap<Student,Double>();
		
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
	
		
		int UIN = 0;
		double studentTotalMarks = 0.0;
		
		@DBAnnotation (
				variable = {"examName"},  
				table = "tableName", 
				column = {"ExamName"}, 
				isSource = true)
		String SQLExamSelect = "SELECT * FROM ? ;";
		
		try {
			Connection conn = Database.getConnection();
			try {
				if (conn != null) {
					PreparedStatement statement = conn.prepareStatement(SQLExamSelect);
					statement.setString(1, tableName);
					ResultSet rs =  statement.executeQuery();
					ArrayList<String> allExams = this.viewAllExams();
					if(allExams.isEmpty()){
						System.out.println("No exams entered yet");
					} else {
					
						while(rs.next()){
							UIN = rs.getInt("StudentUIN");
							for(String examName:allExams){
								studentTotalMarks = studentTotalMarks + rs.getDouble(examName);
							}
							Student student = new Student(UIN);
							examMarks.put(student, studentTotalMarks);
							
						}
					}
				}	
			} catch (SQLException e) {
				System.out.println(e);
			}

		} catch (Exception e) {
			System.out.println(e);
		}
		
		this.setExamMarks(examMarks);
		// CourseExams classExamMarks = new CourseExams(offerID,examName,examMarks);
		// return CourseExams;
		return this;
	}

	public boolean modifyStudentMarks(){
		boolean studentsMarksModified = false;
		
		int offerID = this.getOfferID();
		String examName = this.getExamName();
		HashMap examMarks = this.getExamMarks();

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
	
		Set<Student> keys = examMarks.keySet();
		Iterator<Student> keyIterator = keys.iterator();
		while (keyIterator.hasNext()) {
			Student student = keyIterator.next();
			int UIN = student.getUIN();
			
			double marks = (double) examMarks.get(student); 
			
			// Step 1: Check if the student is enrolled for this course
			boolean isStudentEnrolled = isStudentEnrolled(student,offerID);
			
			
			// If enrolled, add his marks
			if (isStudentEnrolled == true){
				
				@DBAnnotation (
						variable = {"marks","UIN"},  
						table = "tableName", 
						column = {"ExamName","StudentUIN"}, 
						isSource = false)
				String SQLExamUpdate = "UPDATE `?` SET `?`='?' WHERE `StudentUIN`='?';";
				
				try {
					Connection conn = Database.getConnection();
					try {
						if (conn != null) {
						 
							PreparedStatement statement = conn.prepareStatement(SQLExamUpdate);
							statement.setString(1, tableName);
							statement.setString(2, examName);
							statement.setDouble(3, marks);
							statement.setInt(3, UIN);
							statement.executeUpdate();					
							Database.commitTransaction(conn);
							studentsMarksModified = true;
						}	
					} catch (SQLException e) {
						System.out.println(e);
						Database.rollBackTransaction(conn);
					}

				} catch (Exception e) {
					System.out.println(e);
				}
				
			} else {
				System.out.println("The student " + student.getName() +" is not enrolled.");
			}
			
		}
		return studentsMarksModified;
		
	}

	public ArrayList<String> viewAllExams(){
		ArrayList<String> allExams = null;
		
		int offerID = this.getOfferID();
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
		
		String tableName = courseName + Integer.toString(offerID) + Integer.toString(semID) + "Structure"; 
	
		@DBAnnotation (
				variable = {"examName"},  
				table = "tableName", 
				column = {"ExamName"}, 
				isSource = true)
		String SQLExamSelect = "SELECT ExamName FROM ? ;";
		
		try {
			Connection conn = Database.getConnection();
			try {
				if (conn != null) {
					PreparedStatement statement = conn.prepareStatement(SQLExamSelect);
					statement.setString(1, tableName);
					ResultSet rs =  statement.executeQuery();
									
					while(rs.next()){
						String examName = rs.getString("ExamName");
						if(examName != null)
							allExams.add(examName);
					}
					
				}	
			} catch (SQLException e) {
				System.out.println(e);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return allExams;
	}
}

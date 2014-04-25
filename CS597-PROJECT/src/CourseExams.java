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
import java.util.Set;

public class CourseExams {

	int offerID;
	String examName;
	HashMap<Student,Double> examMarks = new HashMap<Student,Double>(); 
	
	@Target({ElementType.LOCAL_VARIABLE})
	@Retention(RetentionPolicy.RUNTIME)
	public @interface DBAnnotation {
	 String[] variable () default "";
	 String[] table () default "";
	 String[] column () default "";
	 boolean[] isSource () default false; 
	}
	
	public CourseExams(int offerID, String examName, HashMap<Student,Double> examMarks) {
		super();
		this.offerID = offerID;
		this.examName = examName;
		this.examMarks = examMarks;
	}

	public CourseExams(int offerID) {
		super();
		this.offerID = offerID;
		//this.examName = examName;
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
	
	public HashMap<Student,Double> getExamMarks() {
		return examMarks;
	}

	public void setExamMarks(HashMap<Student,Double> examMarks) {
		this.examMarks = examMarks;
	}

	public static boolean createCourseExamMarksTable(String courseName, int offerID,int semID){
		boolean tableAdded = false;
//		Course course = offeredCourse.getCourse();
//		String courseName = course.getCourseName();
//		int offerID= offeredCourse.getOfferID();
//		int semID = offeredCourse.getSemesterID();
		
		
		String tableName = courseName + Integer.toString(offerID) + Integer.toString(semID); 
		String studentIDConstraint = tableName + "studentID";
		String studentEnrollmentIDConstraint = tableName + "studentEnrollmentID";
		
//		@DBAnnotation (
//				variable = {"tableName"},  
//				table = "courseExamStructureTable", 
//				column = {"Username","Password"}, 
//				isSource = false)
		String SQLExamCreate = "CREATE TABLE %s (`StudentUIN` int(12) NOT NULL,`StudentEnrollmentID` int(12) NOT NULL, " +
				"PRIMARY KEY (`StudentUIN`), KEY `StudentID_idx` (`StudentUIN`),  " + 
				"KEY `StudentEnrollmentID_idx` (`StudentEnrollmentID`), " +
				"CONSTRAINT %s FOREIGN KEY (`StudentEnrollmentID`) REFERENCES `studentenrollment` (`EnrollmentID`) ON DELETE NO ACTION ON UPDATE NO ACTION," +
				"CONSTRAINT %s FOREIGN KEY (`StudentUIN`) REFERENCES `student` (`UIN`) ON DELETE CASCADE ON UPDATE CASCADE);" ;

		//String SQLExamCreate = "CREATE TABLE %s (`StudentUIN` int(12), `StudentEnrollmentID` int(12), PRIMARY KEY (StudentUIN))";
		SQLExamCreate = String.format(SQLExamCreate, tableName,studentEnrollmentIDConstraint,studentIDConstraint);
		try {
			Connection conn = Database.getConnection();
			try {
				if (conn != null) {
					
					PreparedStatement statement = conn.prepareStatement(SQLExamCreate);
//					statement.setString(1, tableName);
//					statement.setString(1, studentEnrollmentIDConstraint);
//					statement.setString(2, studentIDConstraint);

					System.out.println("Before exam create");
					statement.executeUpdate();
					boolean isCourseAdded = CourseExamStructure.createCourseExamStructureTable(courseName, offerID,semID);
					if(isCourseAdded == true){
						Database.commitTransaction(conn);
						tableAdded = true;
						System.out.println("After exam create");
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
	
		return tableAdded;
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
		String SQLExamAlter = "ALTER TABLE %s ADD COLUMN %s DECIMAL(4,1) Null DEFAULT 0 ;";
		SQLExamAlter = String.format(SQLExamAlter, tableName,examName);
		try {
			Connection conn = Database.getConnection();
			try {
				if (conn != null) {
					PreparedStatement statement = conn.prepareStatement(SQLExamAlter);
//					statement.setString(1, tableName);
//					statement.setString(2, examName);
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
		String INFORMATION_SCHEMA_COLUMNS_Select = "SELECT ISC.column_name FROM INFORMATION_SCHEMA.COLUMNS ISC WHERE ISC.table_name = ?;";
		
		try {
			Connection conn = Database.getConnection();
			try {
				if (conn != null) {
				 
					PreparedStatement statement = conn.prepareStatement(INFORMATION_SCHEMA_COLUMNS_Select);
					statement.setString(1, tableName);
					ResultSet rs = statement.executeQuery();
					while(rs.next()){
						String tableExamName = rs.getString("column_name");
						if(examName.equals(tableExamName)){
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
		String SQLExamAlter = "ALTER TABLE %s CHANGE COLUMN %s %s DECIMAL(4,1) NULL;";
		SQLExamAlter = String.format(SQLExamAlter, tableName, examName,newExamName);
		
		try {
			Connection conn = Database.getConnection();
			try {
				if (conn != null) {
				 
					PreparedStatement statement = conn.prepareStatement(SQLExamAlter);
//					statement.setString(1, tableName);
//					statement.setString(2, examName);
//					statement.setString(3, newExamName);
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
		
		String examName = courseExamStructure.getExamName();
		
		boolean isExamPresent = isExamPresent(tableName,examName);
		if (isExamPresent == true){
						
		@DBAnnotation (
				variable = {"examName"},  
				table = "tableName", 
				column = {"ExamName"}, 
				isSource = false)
		String SQLExamDelete = "ALTER TABLE %s DROP COLUMN %s ;";
		SQLExamDelete = String.format(SQLExamDelete, tableName,examName);
		try {
			Connection conn = Database.getConnection();
			try {
				if (conn != null) {
					PreparedStatement statement = conn.prepareStatement(SQLExamDelete);
//					statement.setString(1, tableName);
//					statement.setString(2, examName);
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
		HashMap<Student,Double> examMarks = this.examMarks;
		Set<Student> keys = examMarks.keySet();
		Iterator<Student> keyIterator = keys.iterator();
		while (keyIterator.hasNext()) {
			Student student = keyIterator.next();
			int UIN = student.getUIN();
			
			double marks = (double) examMarks.get(student); 
			
			// Step 1: Check if the student is enrolled for this course
			boolean isStudentEnrolled = isStudentEnrolled(student,offerID);
			
			// Step 2: Get student Enrollment ID
			//int enrollmentID = StudentEnrollment.getStudentEnrollmentID(student,offerID);
			
			// If enrolled, add his marks
			if (isStudentEnrolled == true){
				
				@DBAnnotation (
						variable = {"marks","UIN"},  
						table = "tableName", 
						column = {"ExamName","StudentUIN"}, 
						isSource = false)
				String SQLExamUpdate = "UPDATE %s SET %s = ? WHERE `StudentUIN`=?;";
				SQLExamUpdate = String.format(SQLExamUpdate, tableName,examName);
				try {
					Connection conn = Database.getConnection();
					try {
						if (conn != null) {
						 
							PreparedStatement statement = conn.prepareStatement(SQLExamUpdate);
//							statement.setString(1, tableName);
//							statement.setString(2, examName);
							statement.setDouble(1, marks);
							statement.setInt(2, UIN);
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
		
		ArrayList<CourseOffered> coursesTaken = StudentEnrollment.getStudentsAllCourses(student);
		
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
		String SQLExamSelect = "SELECT * FROM %s ;";
		SQLExamSelect = String.format(SQLExamSelect, tableName);

		try {
			Connection conn = Database.getConnection();
			try {
				if (conn != null) {
					PreparedStatement statement = conn.prepareStatement(SQLExamSelect);
//					statement.setString(1, tableName);
					ResultSet rs =  statement.executeQuery();
					//System.out.println(rs.getDouble());
					ArrayList<String> allExams = this.viewAllExams();
					if(allExams.isEmpty()){
						System.out.println("No exams entered yet");
					} else {
					
						while(rs.next()){
							UIN = rs.getInt("StudentUIN");
							studentTotalMarks = 0.0;
							System.out.println("-*-");
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

	public static HashMap<String,Double> getStudentMarks(CourseOffered offeredCourse, Student student){
		HashMap<String,Double> studentExamAndMarks = new HashMap<String,Double>();
		int offerID = offeredCourse.getOfferID();
		Course course = offeredCourse.getCourse();
		String courseName = course.getCourseName();
		int semID = offeredCourse.getSemesterID();
		
		String tableName = courseName + Integer.toString(offerID) + Integer.toString(semID); 
		
		int UIN = student.getUIN();
		double studentMarks = 0.0;
		
		CourseExams exam = new CourseExams(offerID);
		ArrayList<String> allExams = exam.viewAllExams();
		if(allExams.isEmpty()){
			System.out.println("No exams entered yet");
		} else {
			for(String oneExam: allExams){
				
				@DBAnnotation (
						variable = {"examName"},  
						table = "tableName", 
						column = {"ExamName"}, 
						isSource = true)
				String SQLExamSelect = "SELECT %s FROM %s WHERE StudentUIN = ?;";
				SQLExamSelect = String.format(SQLExamSelect, oneExam, tableName);

				try {
					Connection conn = Database.getConnection();
					try {
						if (conn != null) {
							PreparedStatement statement = conn.prepareStatement(SQLExamSelect);
							// statement.setString(1, oneExam);
							statement.setInt(1, UIN);
							ResultSet rs =  statement.executeQuery();
							//System.out.println(rs.getDouble());
								if(rs.next()){
									studentMarks = rs.getDouble(oneExam);
									studentExamAndMarks.put(oneExam, studentMarks);
								}
							}
							
					} catch (SQLException e) {
						System.out.println(e);
					}

				} catch (Exception e) {
					System.out.println(e);
				}

			}
		}

		return studentExamAndMarks;
	}
	
	public static HashMap<Integer,Double> getStudents(CourseOffered offeredCourse, String examName){
		HashMap<Integer,Double> studentsMarks = new HashMap<Integer,Double>();
		
		int offerID = offeredCourse.getOfferID();
		Course course = offeredCourse.getCourse();
		String courseName = course.getCourseName();
		int semID = offeredCourse.getSemesterID();
		
		String tableName = courseName + Integer.toString(offerID) + Integer.toString(semID); 
		
		boolean isExamPresent = isExamPresent(tableName, examName);
			if(isExamPresent == false){
				System.out.println("Exam Absent");
			} else {
				@DBAnnotation (
						variable = {"examName"},  
						table = "tableName", 
						column = {"ExamName"}, 
						isSource = true)
				String SQLExamSelect = "SELECT StudentUIN, %s FROM %s;";
				SQLExamSelect = String.format(SQLExamSelect, examName, tableName);

				try {
					Connection conn = Database.getConnection();
					try {
						if (conn != null) {
							PreparedStatement statement = conn.prepareStatement(SQLExamSelect);
//							statement.setInt(1, UIN);
//							statement.setDouble(1, examName);
							
							ResultSet rs =  statement.executeQuery();
							//System.out.println(rs.getDouble());
								while(rs.next()){
									int UIN = rs.getInt(1);
									double marks = rs.getDouble(2); 
									studentsMarks.put(UIN, marks);
								}
							}
							
					} catch (SQLException e) {
						System.out.println(e);
					}

				} catch (Exception e) {
					System.out.println(e);
				}

			}

		return studentsMarks;
	}
	

 	public boolean modifyStudentMarks(){
		boolean studentsMarksModified = false;
		
		int offerID = this.getOfferID();
		String examName = this.getExamName();
		HashMap<Student,Double> examMarks = this.getExamMarks();

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
				String SQLExamUpdate = "UPDATE %s SET %s = ? WHERE `StudentUIN`=?;";
				SQLExamUpdate = String.format(SQLExamUpdate, tableName,examName);
				try {
					Connection conn = Database.getConnection();
					try {
						if (conn != null) {
						 
							PreparedStatement statement = conn.prepareStatement(SQLExamUpdate);
//							statement.setString(1, tableName);
//							statement.setString(2, examName);
							statement.setDouble(1, marks);
							statement.setInt(2, UIN);
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
		ArrayList<String> allExams = new ArrayList<String>() ;
		
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
		String SQLExamSelect = "SELECT ExamName FROM %s ;";
		SQLExamSelect = String.format(SQLExamSelect, tableName);
		
		try {
			Connection conn = Database.getConnection();
			try {
				if (conn != null) {
					//System.out.println(tableName);
					PreparedStatement statement = conn.prepareStatement(SQLExamSelect);
//					statement.setString(1, tableName);
					ResultSet rs =  statement.executeQuery();
									
					while(rs.next()){
						String examName = rs.getString("ExamName");
						System.out.println(examName);
						if(examName != null)
							allExams.add(examName);
						System.out.println(allExams.get(0));
							
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

	
	
//	public static void main(String[] args){

//		int offerID = 345678;
//		@SuppressWarnings("unused")
//		CourseOffered offeredCourse = null;
//		try {
//			offeredCourse = new CourseOffered(offerID);
//		} catch (Course.CourseDoesNotExistException e) {
//			e.printStackTrace();
//		} catch (CourseOffered.CourseOfferingDoesNotExistException e) {
//			e.printStackTrace();
//		}
		
//		Test to add an exam
//		boolean courseAdded = CourseExams.createCourseExamMarksTable(offeredCourse);
//		if(courseAdded == true){
//			System.out.println("Course Added");
//		} else {
//			System.out.println("Course Not Added");
//		}
		
		// To add mks for a student
//		String examName = "Assgn1";
//		int UIN = 1;
//		Student student = new Student(UIN);
//		double marks = 8.5; 
//		HashMap<Student,Double> examMarks = new HashMap<Student,Double>();
//		examMarks.put(student, marks);
//		UIN = 2;
//		marks = 7.5;
//		student = new Student(UIN);
//		examMarks.put(student, marks);
//		CourseExams exams = new CourseExams(offerID,examName,examMarks);
//		
//		boolean marksAdded = exams.addStudentMarks();
//		if(marksAdded == true){
//			System.out.println("Mks Added");
//		} else {
//			System.out.println("Mks Not Added");
//		}
		
		// To get student's marks
//		CourseExams exams = new CourseExams(offerID);
//		CourseExams marks = exams.getStudentMarks();
//		HashMap<Student,Double> examMarks = marks.getExamMarks();
//		Set<Student> keys = examMarks.keySet();
//		Iterator<Student> keyIterator = keys.iterator();
//		while (keyIterator.hasNext()) {
//			Student student = keyIterator.next();
//			int UIN = student.getUIN();
//			double studeMarks = (double) examMarks.get(student);
//			System.out.println("UIN: " + UIN + " TotalMarks: " + studeMarks);
//		}	
		
		
		// To modify student's marks
//		int UIN = 1;
//		Student student = new Student(UIN);
//		double marks = 9; 
//		HashMap<Student,Double> examMarks = new HashMap<Student,Double>();
//		examMarks.put(student, marks);
//		UIN = 2;
//		marks = 3;
//		student = new Student(UIN);
//		examMarks.put(student, marks);
//		CourseExams exams = new CourseExams(offerID,examName,examMarks);
//		
//		boolean marksModified = exams.modifyStudentMarks();
//		if(marksModified == true){
//			System.out.println("Mks Modified");
//		} else {
//			System.out.println("Mks Not Modified");
//		}
		
//	}
}

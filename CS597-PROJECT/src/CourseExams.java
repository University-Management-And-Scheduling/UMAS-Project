import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;



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

	public CourseExams(int offerID, String examName) {
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
		
		// DB code to modify name of the exam column in CourseExam table
		
		return modifiedColumn;
	}	
	
	public static void deleteExistingExamColumn(){
		
		// DB code to delete existing column for the exam in courseExam Table
	}
	
	public void addStudentMarks(){
		HashMap examMarks = this.examMarks;
		
		// DB code to add Student's mks in the CourseExam Table
		
	}
	
	public CourseExams getStudentMarks(){
		int OfferID = this.offerID;
		String examName = this.examName;
		
		int UIN = 0;
		double studentMarks = 0.0;
		
		// DB code to get Student's mks from the CourseExam Table
		
		Student student = new Student(UIN);
		examMarks.put(student, studentMarks);
		
		CourseExams classExamMarks = new CourseExams(offerID,examName,examMarks);
		return classExamMarks;
	}

	public void modifyStudentMarks(Student student, Float newMarks){
		int OfferID = this.offerID;
		String examName = this.examName;
		int UIN = student.getUIN();
		
		// DB code to access CourseExam table and modify the examMarks  
		
		
	}



}

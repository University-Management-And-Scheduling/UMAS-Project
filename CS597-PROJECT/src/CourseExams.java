import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;


public class CourseExams {

	int offerID;
	String examName;
	int examTotal;
	
	HashMap examMarks = new HashMap<Student,Double>();
	
	
	public CourseExams(int offerID, String examName, HashMap examMarks) {
		super();
		this.offerID = offerID;
		this.examName = examName;
		this.examMarks = examMarks;
	}

	public CourseExams(int offerID, String examName, int examTotal) {
		super();
		this.offerID = offerID;
		this.examName = examName;
		this.examTotal = examTotal;
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

	public int getExamTotal() {
		return examTotal;
	}

	public void setExamTotal(int examTotal) {
		this.examTotal = examTotal;
	}

	public static void createCourseExamMarksTable(CourseOffered offeredCourse){
		
		Course course = offeredCourse.getCourse();
		String courseName = course.getCourseName();
		int offerID= offeredCourse.getOfferID();
		int semID = offeredCourse.getSemesterID();
		
		String tableName = courseName + Integer.toString(offerID) + Integer.toString(semID); 
		
//		@DBAnnotation (
//				variable = {"tableName"},  
//				table = "courseExamStructureTable", 
//				column = {"Username","Password"}, 
//				isSource = false)
		String SQLExamCreate = "CREATE TABLE tableName (`StudentUIN` int(12) NOT NULL,`StudentEnrollmentID` int(12) NOT NULL, " +
				" PRIMARY KEY (`StudentUIN`), KEY `StudentID_idx` (`StudentUIN`),  " + 
				"KEY `StudentEnrollmentID_idx` (`StudentEnrollmentID`), " +
				"CONSTRAINT `StudentEnrollmentID` FOREIGN KEY (`StudentEnrollmentID`) REFERENCES `studentenrollment` (`EnrollmentID`) ON DELETE NO ACTION ON UPDATE NO ACTION," +
				"CONSTRAINT `StudentID` FOREIGN KEY (`StudentUIN`) REFERENCES `student` (`UIN`) ON DELETE CASCADE ON UPDATE CASCADE;" ;

		
		try {
			Connection conn = Database.getConnection();
			try {
				if (conn != null) {
				 
					PreparedStatement statement = conn.prepareStatement(SQLExamCreate);
					statement.setString(1, tableName);
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
		
	public static void addNewExamColumn(){
		
		// DB code to add new column for the exam in courseExam Table
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

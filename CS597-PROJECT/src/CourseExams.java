import java.util.HashMap;


public class CourseExams {

	int offerID;
	String examName;
	int examTotal;
	
	HashMap examMarks = new HashMap<Student,Float>();
	
	
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

	public static void createCourseExamMarksTable(CourseOffered course){
		
		// DB code to create table to maintain students' exam mks
		
		
		createCourseExamStructureTable(course);
	}
	
	private static void createCourseExamStructureTable(CourseOffered course){
		
		// DB code to create ExamStructure Table that has the exam structure.
		
	}
	
	public void addNewExam(){
		int OfferID = this.offerID;
		String examName = this.examName;
		int examTotal = this.examTotal;
		
		// DB code to add exam in ExamStructure Table
		
		this.addNewExamColumn();
	}
	
	private void addNewExamColumn(){
		
		// DB code to add new column for the exam in courseExam Table
	}
	
	public void modifyExistingExamName(String newExamName){
		CourseExams exam = null;
		
		// DB code to modify name in ExamStructure table
		
		exam.modifyExistingExamColumnName(newExamName);
	}	
	
	private void modifyExistingExamColumnName(String newExamName){
		
		// DB code to modify name of the exam column in CourseExam table
		
	}	
	
	public void modifyExistingExamTotalMarks(Float newMarks){
		
		// DB code to modify total marks in ExamStructure table
	}

	public void deleteExistingExam(){
		int OfferID = this.offerID;
		String examName = this.examName;
		
		// DB code to delete exam in ExamStructure Table
		
		
		
	}
	
	private void deleteExistingExamColumn(){
		
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
		Float studentMarks = null;
		
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

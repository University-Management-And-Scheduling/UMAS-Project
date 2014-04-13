
public class CourseExamStructure {
	int offerID;
	String examName;
	int examTotal;
	
	public CourseExamStructure(int offerID, String examName, int examTotal) {
		this.offerID = offerID;
		this.examName = examName;
		this.examTotal = examTotal;
	}
	
	public static void createCourseExamStructureTable(CourseOffered course){
		
		// DB code to create ExamStructure Table that has the exam structure.
		
	}
	
	public void addNewExam(){
		int OfferID = this.offerID;
		String examName = this.examName;
		int examTotal = this.examTotal;
		
		// DB code to add exam in ExamStructure Table
		
		CourseExams.addNewExamColumn();
	}
		
	public void modifyExistingExamName(String newExamName){
		CourseExams exam = null;
		
		// DB code to modify name in ExamStructure table
		
		exam.modifyExistingExamColumnName(newExamName);
	}	

	public void modifyExistingExamTotalMarks(Float newMarks){
		
		// DB code to modify total marks in ExamStructure table
	}

	public void deleteExistingExam(){
		int OfferID = this.offerID;
		String examName = this.examName;
		CourseExams exam = null;
		// DB code to delete exam in ExamStructure Table
		
		exam.deleteExistingExamColumn();
	}

}

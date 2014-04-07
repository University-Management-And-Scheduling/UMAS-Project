import java.util.HashMap;


public class CourseCurve {

	int offerID;
	HashMap<String, Integer> curvingCriteria;
	HashMap<Student,String> courseCurve;
	
	
	public CourseCurve(int offerID, HashMap<String, Integer> curvingCriteria) {
		super();
		this.offerID = offerID;
		this.curvingCriteria = curvingCriteria;
	}
	


	public int getOfferID() {
		return offerID;
	}
	


	public void setOfferID(int offerID) {
		this.offerID = offerID;
	}
	


	public HashMap<String, Integer> getCurvingCriteria() {
		return curvingCriteria;
	}
	


	public void setCurvingCriteria(HashMap<String, Integer> curvingCriteria) {
		this.curvingCriteria = curvingCriteria;
	}
	


	public HashMap<Student, String> getCourseCurve() {
		return courseCurve;
	}
	


	public void setCourseCurve(HashMap<Student, String> courseCurve) {
		this.courseCurve = courseCurve;
	}
	
	private int getTotalCourseMarks(){
		int totalCourseMarks = 0;
		
		// DB code to get total CourseMarks from CourseStructure Table
		
		return totalCourseMarks;
		
	}
	
	private HashMap<Student,Double> getStudentTotalMarks(){
		HashMap<Student,Double> studentTotalMarks = new HashMap<Student,Double>();
		
		// DB code to get Student-TotalMarks from CourseExams' Table
		
		return studentTotalMarks;
		
	}
	
	
	// 3 ways to Calculate the Curve

	// HashMap<String,Integer> = HashMap<Grade,Percentage of students in the grade>
	// Example: <'A',30> = Top 30% students would be given 'A' grade
	// <'B',40> = Next 40% students would be given 'B' grade
	public static CourseCurve calculatePercentageCurve(int offerID, HashMap<String,Integer> curvingCriteria){
		CourseCurve curve = new CourseCurve(offerID,curvingCriteria);
		
		// STEP 1: Get total CourseMarks from CourseStructure Table
		int totalCourseMarks = curve.getTotalCourseMarks();
		
		// STEP 2: get totalMarks for each student from CourseExams' Table
		HashMap<Student,Double> studentTotalMarks = curve.getStudentTotalMarks();
		
		// STEP 3: Calculate curve for the courseOffered based on
		// the curvingCriteria selected by the professor 
		HashMap<Student,String> courseCurve = null;
		
		// Algo to calculate curve
		
		curve.setCourseCurve(courseCurve);
		return curve;
	}
	
	// HashMap<String,Integer> = HashMap<Grade,CutofPercentage>
	// Example: <'A',90> = Students with total mks at or above 90-100% would be given 'A' grade
	// <'B',75> = Students with total mks between 75-89% would be given 'B' grade
	public static CourseCurve calculateAbsoluteCurve(int offerID, HashMap<String,Integer> curvingCriteria){
		CourseCurve curve = new CourseCurve(offerID,curvingCriteria);
		
		// STEP 1: Get total CourseMarks from CourseStructure Table
		int totalCourseMarks = curve.getTotalCourseMarks();
		
		// STEP 2: get totalMarks for each student from CourseExams' Table
		HashMap<Student,Double> studentTotalMarks = curve.getStudentTotalMarks();
		
		// STEP 3: Calculate curve for the courseOffered based on
		// the curvingCriteria selected by the professor 
		HashMap<Student,String> courseCurve = null;
		
		// Algo to calculate curve
		
		curve.setCourseCurve(courseCurve);
		return curve;
	}
	
	
	// HashMap<String,Integer> = HashMap<Grade,Minimum Number of students in that grade>
	// Example: <'A',10> = Atleast top 10 students get 'A' grade 
	// <'B',10> = After all 'A' grades, atleast 10 students will be given 'B' grade
	public static CourseCurve calculateMaxGapCurve(int offerID, HashMap<String,Integer> curvingCriteria){
		CourseCurve curve = new CourseCurve(offerID,curvingCriteria);
		
		// STEP 1: Get total CourseMarks from CourseStructure Table
		int totalCourseMarks = curve.getTotalCourseMarks();
		
		// STEP 2: get totalMarks for each student from CourseExams' Table
		HashMap<Student,Double> studentTotalMarks = curve.getStudentTotalMarks();
		
		// STEP 3: Calculate curve for the courseOffered based on
		// the curvingCriteria selected by the professor 
		HashMap<Student,String> courseCurve = null;
		
		// Algo to calculate curve
		// 1. Sort students in decreasing order of total mks
		// 2. Skip over minimum students and give them the first grade
		// 3. Keep track of difference between total marks of students students
		// 4. Find 1st instance where difference increases. 
		// 5. That is the cut-off point.
		// 6. Move on to the next grade
		
		curve.setCourseCurve(courseCurve);
		return curve;
	}
	
	
}

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.Map.Entry;


public class CourseCurve {

	int offerID;
	List<Integer> curvingCriteria;
	HashMap<Student,String> courseCurve;
	
	@Target({ElementType.LOCAL_VARIABLE})
	@Retention(RetentionPolicy.RUNTIME)
	public @interface DBAnnotation {
	 String[] variable () default "";
	 String[] table () default "";
	 String[] column () default "";
	 boolean[] isSource () default false; 
	}
	
	public CourseCurve(int offerID, List<Integer> curvingCriteria) {
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

	public List<Integer> getCurvingCriteria() {
		return curvingCriteria;
	}

	public void setCurvingCriteria(List<Integer> curvingCriteria) {
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
				variable = {""},  
				table = "tableName", 
				column = {"TotalMarks"}, 
				isSource = false)
		String SQLExamStructureSelect = "Select sum(TotalMarks) TotalMarks FROM ?;";
		try {
			Connection conn = Database.getConnection();
			try {
				if (conn != null) {
				 
					PreparedStatement statement = conn.prepareStatement(SQLExamStructureSelect);
					statement.setString(1, tableName);
					ResultSet rs = statement.executeQuery();
					
					while(rs.next()){
						totalCourseMarks = rs.getInt("TotalMarks");
					}
				}	
			} catch (SQLException e) {
				System.out.println(e);
			}

		} catch (Exception e) {
			System.out.println(e);
		}
		
		return totalCourseMarks;
		
	}
	
	private HashMap<Student,Double> getStudentTotalMarks(){
		HashMap<Student,Double> studentTotalMarks = new HashMap<Student,Double>();
		
		int offerID = this.getOfferID();
		CourseExams exams = new CourseExams(offerID);
		
		studentTotalMarks = exams.getStudentMarks().getExamMarks();
		
		return studentTotalMarks;
		
	}
	
	public double getLastStudentPercent(){
		double percent = 0.0;
		HashMap<Student,Double> studentTotalMarks = new HashMap<Student,Double>();
		
		int offerID = this.getOfferID();
		CourseExams exams = new CourseExams(offerID);
		
		int totalCourseMarks = this.getTotalCourseMarks();
		studentTotalMarks = exams.getStudentMarks().getExamMarks();
		
		HashMap<Student,Double> sortedstudentTotalMarks = sortHashMap(studentTotalMarks);
		
		@SuppressWarnings("unchecked")
		List<Student> keys = (List<Student>) sortedstudentTotalMarks.keySet();
		Student lastKey = keys.get(keys.size()-1);
		double totalMarks = sortedstudentTotalMarks.get(lastKey);
		
		percent = (totalMarks / totalCourseMarks) * 100;
		
		return percent;
	}
	
	
	// 3 ways to Calculate the Curve

	// HashMap<String,Integer> = HashMap<Grade,Percentage of students in the grade>
	// Example: <30,40,30> = Top 30% students would be given grade at level 1.
	// Next 40% students would be given grade  at level 2. 
	// Last 30% students would be given grade  at level 3.
	// Grade levels are stored in the gradingsystem table.
	@SuppressWarnings("null")
	public static CourseCurve calculatePercentageCurve(int offerID, List<Integer> curvingCriteria){
		CourseCurve curve = new CourseCurve(offerID,curvingCriteria);
		
		// STEP 1: Get total CourseMarks from CourseStructure Table
		// int totalCourseMarks = curve.getTotalCourseMarks();
		
		// STEP 2: get totalMarks for each student from CourseExams' Table
		HashMap<Student,Double> studentTotalMarks = curve.getStudentTotalMarks();
		
		// STEP 3: Sort the hashmap in descending order
		HashMap<Student,Double> sortedstudentTotalMarks = sortHashMap(studentTotalMarks);
		
		// STEP 4: Calculate curve for the courseOffered based on
		// the curvingCriteria selected by the professor 
		//(30,40,30) = (9,12,9) / 30
		HashMap<Student,String> courseCurve = null;
		int numberOfStudents = sortedstudentTotalMarks.size();
		int numberOfGrades = curvingCriteria.size();
		int studentsLeft = numberOfStudents;
		
		Set<Student> keys = sortedstudentTotalMarks.keySet();
		Iterator<Student> keyIterator = keys.iterator();
		
		for (int gradeLevel=1; gradeLevel<= numberOfGrades;gradeLevel++){
			GradeSystem grade = GradeSystem.getGradeForGradeLevel(gradeLevel);
			String studentGrade = grade.getGrade();
			
			int percentStudents = curvingCriteria.get(gradeLevel);
		
			int students =  (int) Math.ceil((percentStudents/100) * numberOfStudents);
			
			// For roundoff error
			if(students > studentsLeft){
				students = studentsLeft;
			}
			
			for(int studentsAdded = 0; studentsAdded<=students;studentsAdded++){
				Student student = keyIterator.next();
				courseCurve.put(student, studentGrade);
				studentsLeft--;
			}
		}
			
		curve.setCourseCurve(courseCurve);
		return curve;
	}

	// HashMap<String,Integer> = HashMap<Grade,CutofPercentage>
	// Example: <90,75,60> = Students with total mks at or above 90-100% would be given grade at level 1
	// Students with total mks between 75-89% would be given grade at level 2
	// Students with total mks between 60-74% would be given grade at level 3
	@SuppressWarnings("null")
	public static CourseCurve calculateAbsoluteCurve(int offerID, List<Integer> curvingCriteria){
		CourseCurve curve = new CourseCurve(offerID,curvingCriteria);
		
		// STEP 1: If the criteria is <90,75,60> and there is a student with 
		// less than 60% then that student will not get a grade. 
		// This step checks whether the last criteria is less than the percent of the 
		// last student in class
		
		int size = curvingCriteria.size();
		int lastCriteria = curvingCriteria.get(size-1);
		double lastStudentPercent = curve.getLastStudentPercent();
		int floorlastStudentPercent= (int) Math.floor(lastStudentPercent);
		if(floorlastStudentPercent < lastCriteria ){
			System.out.println("There are students below the last criteria. " + 
								"Reduce the last criteria or add one more with value less than " + floorlastStudentPercent);
		} else{
		
		// STEP 2: Get total CourseMarks from CourseStructure Table
		int totalCourseMarks = curve.getTotalCourseMarks();
		
		// STEP 3: get totalMarks for each student from CourseExams' Table
		HashMap<Student,Double> studentTotalMarks = curve.getStudentTotalMarks();
		
		// STEP 4: Sort the hashmap in descending order
		HashMap<Student,Double> sortedstudentTotalMarks = sortHashMap(studentTotalMarks);
		
		// STEP 5: Calculate curve for the courseOffered based on
		// the curvingCriteria selected by the professor 
		HashMap<Student,String> courseCurve = null;
		
		// int numberOfStudents = sortedstudentTotalMarks.size();
		int numberOfGrades = curvingCriteria.size();
		// int studentsLeft = numberOfStudents;
		
				
		Set<Student> keys = sortedstudentTotalMarks.keySet();
		Iterator<Student> keyIterator = keys.iterator();
		
		for (int gradeLevel=1; gradeLevel<= numberOfGrades;gradeLevel++){
			GradeSystem grade = GradeSystem.getGradeForGradeLevel(gradeLevel);
			String studentGrade = grade.getGrade();
		
			int cutOffPercent = curvingCriteria.get(gradeLevel);
			while (keyIterator.hasNext()) {
				Student student = keyIterator.next();
				//int UIN = student.getUIN();
				double marks = (double) sortedstudentTotalMarks.get(student);
				
				double studentPercentage = (marks/ totalCourseMarks) * 100;
		
				if(studentPercentage >= cutOffPercent){
					courseCurve.put(student, studentGrade);
				} else {
					break;
				}
			}	
		}
		
		curve.setCourseCurve(courseCurve);
		
		} // Else ends here
		return curve;
		
	}
	
	
	// HashMap<String,Integer> = HashMap<Grade,Minimum Number of students in that grade>
	// Example: <10,10,10> = Atleast top 10 students get grade at level 1 
	// After all level 1 grades, atleast 10 students will be given grade at level 2
	@SuppressWarnings("null")
	public static CourseCurve calculateMaxGapCurve(int offerID, List<Integer> curvingCriteria){
		CourseCurve curve = new CourseCurve(offerID,curvingCriteria);
		
		// STEP 1: Get total CourseMarks from CourseStructure Table
		//int totalCourseMarks = curve.getTotalCourseMarks();
		
		// STEP 2: get totalMarks for each student from CourseExams' Table
		HashMap<Student,Double> studentTotalMarks = curve.getStudentTotalMarks();
		
		// STEP 3: Sort the hashmap in descending order
		HashMap<Student,Double> sortedstudentTotalMarks = sortHashMap(studentTotalMarks);
		
		// STEP 4: Calculate curve for the courseOffered based on
		// the curvingCriteria selected by the professor 
		HashMap<Student,String> courseCurve = null;
		
		// Algo to calculate curve
		// 1. Sort students in decreasing order of total mks -- Done in step 3
		// 2. Skip over minimum students and give them the first grade
		// 3. Keep track of difference between total marks of students students
		// 4. Find 1st instance where difference increases. 
		// 5. That is the cut-off point.
		// 6. Move on to the next grade
		
		//int numberOfStudents = sortedstudentTotalMarks.size();
		int numberOfGrades = curvingCriteria.size();
		
		Set<Student> keys = sortedstudentTotalMarks.keySet();
		Iterator<Student> keyIterator = keys.iterator();
		
		boolean nextGraded = false;
		for (int gradeLevel=1; gradeLevel <= numberOfGrades;gradeLevel++){
			GradeSystem grade = GradeSystem.getGradeForGradeLevel(gradeLevel);
			String studentGrade = grade.getGrade();
			int gradedStudents = 0;
			int minimumPeople = curvingCriteria.get(gradeLevel);
			
			if(nextGraded==true){
				minimumPeople-=2;
				nextGraded = false;
			}
			
			@SuppressWarnings("unused")
			int UIN = 0;
			double marks = 0.0;
			while ((keyIterator.hasNext()) && gradedStudents <= minimumPeople ) {
				Student student = keyIterator.next();
				UIN = student.getUIN();
				marks = (double) sortedstudentTotalMarks.get(student);
				courseCurve.put(student, studentGrade);
				gradedStudents++;
			}
			
			Student student = keyIterator.next();
			@SuppressWarnings("unused")
			int nextUIN = student.getUIN();
			double nextMarks = (double) sortedstudentTotalMarks.get(student);
			
			double difference = nextMarks - marks;
			
			while(keyIterator.hasNext() && (gradeLevel< numberOfGrades)) {
				Student nextstudent = keyIterator.next();
				nextUIN = student.getUIN();
				nextMarks = (double) sortedstudentTotalMarks.get(student);
				double nextDifference = nextMarks - marks;
				if(nextDifference > difference){
					courseCurve.put(student, studentGrade);
					
				} else if (nextDifference < difference){
					GradeSystem nextGrade = GradeSystem.getGradeForGradeLevel(gradeLevel+1);
					String nextStudentGrade = nextGrade.getGrade();
					courseCurve.put(student, nextStudentGrade);
					courseCurve.put(nextstudent, nextStudentGrade);
					nextGraded = true;
				}
				
			} 
		}
		curve.setCourseCurve(courseCurve);
		return curve;
	}
	
	public boolean UpdateGrades(){
		boolean gradesUpdated = false;
		
		int offerID = this.getOfferID();
		CourseOffered offeredCourse = null;
		try {
			offeredCourse = new CourseOffered(offerID);
		} catch (Course.CourseDoesNotExistException
				| CourseOffered.CourseOfferingDoesNotExistException e) {
			e.printStackTrace();
		}
		HashMap<Student,String> studentGrades = this.getCourseCurve();
		
		gradesUpdated = StudentEnrollment.updateAllStudentGrade(studentGrades, offeredCourse);
		
		return gradesUpdated;
	}
	
	private static HashMap<Student, Double> sortHashMap(HashMap<Student, Double> unsortedStudentTotalMarks)
    {

        List<Entry<Student, Double>> list = new LinkedList<Entry<Student, Double>>(unsortedStudentTotalMarks.entrySet());

        // Sorting the list based on values
        Collections.sort(list, new Comparator<Entry<Student, Double>>()
        {
            public int compare(Entry<Student, Double> studentTotalMarks1, Entry<Student, Double> studentTotalMarks2) {
                return studentTotalMarks2.getValue().compareTo(studentTotalMarks1.getValue());
            }
        });

        // Maintaining insertion order with the help of LinkedList
        HashMap<Student, Double> sortedstudentTotalMarks = new LinkedHashMap<Student, Double>();
        for (Entry<Student, Double> entry : list) {
        	sortedstudentTotalMarks.put(entry.getKey(), entry.getValue());
        }

        return sortedstudentTotalMarks;
    }

	public static void main(String[] args){
		
	}


}

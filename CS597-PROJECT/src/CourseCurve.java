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
import java.util.Map;
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
	
	// 3 ways to Calculate the Curve

	// HashMap<String,Integer> = HashMap<Grade,Percentage of students in the grade>
	// Example: <'A',30> = Top 30% students would be given 'A' grade
	// <'B',40> = Next 40% students would be given 'B' grade
	public static CourseCurve calculatePercentageCurve(int offerID, List<Integer> curvingCriteria){
		CourseCurve curve = new CourseCurve(offerID,curvingCriteria);
		
		// STEP 1: Get total CourseMarks from CourseStructure Table
		int totalCourseMarks = curve.getTotalCourseMarks();
		
		// STEP 2: get totalMarks for each student from CourseExams' Table
		HashMap<Student,Double> studentTotalMarks = curve.getStudentTotalMarks();
		
		// STEP 3: Sort the hashmap in descending order
		HashMap<Student,Double> sortedstudentTotalMarks = sortHashMap(studentTotalMarks);
		
		// STEP 4: Calculate curve for the courseOffered based on
		// the curvingCriteria selected by the professor 
		//(30,40,30) = (9,12,9) / 30
		HashMap<Student,String> courseCurve = null;
		int initialStudents = 0;
		//int thirtypercent = (int) Math.ceil(0.7 * 30);
		int numberOfStudents = sortedstudentTotalMarks.size();
		int numberOfGrades = curvingCriteria.size();
		
		for (int percentStudents: curvingCriteria) {
			
			//int percentStudents = curvingCriteria.get(initialStudents);
			int students =  (int) Math.ceil((percentStudents/100) * numberOfStudents);
			
			for(int studentsAdded = 0; studentsAdded<=students;studentsAdded++){
				// Skip
				int UIN = 0;
				double marks = 0.0;
				Set<Student> keys = sortedstudentTotalMarks.keySet();
				Iterator<Student> keyIterator = keys.iterator();
				for(int i = 0;i<=initialStudents;i++){
					if (keyIterator.hasNext()) {
						Student student = keyIterator.next();
					}
				}
				Student student = keyIterator.next();
				
				initialStudents++;
				
			}
			
		}
		curve.setCourseCurve(courseCurve);
		return curve;
	}

	// HashMap<String,Integer> = HashMap<Grade,CutofPercentage>
	// Example: <'A',90> = Students with total mks at or above 90-100% would be given 'A' grade
	// <'B',75> = Students with total mks between 75-89% would be given 'B' grade
	public static CourseCurve calculateAbsoluteCurve(int offerID, List<Integer> curvingCriteria){
		CourseCurve curve = new CourseCurve(offerID,curvingCriteria);
		
		// STEP 1: Get total CourseMarks from CourseStructure Table
		int totalCourseMarks = curve.getTotalCourseMarks();
		
		// STEP 2: get totalMarks for each student from CourseExams' Table
		HashMap<Student,Double> studentTotalMarks = curve.getStudentTotalMarks();
		
		// STEP 3: Sort the hashmap in descending order
		HashMap<Student,Double> sortedstudentTotalMarks = sortHashMap(studentTotalMarks);
		
		// STEP 4: Calculate curve for the courseOffered based on
		// the curvingCriteria selected by the professor 
		HashMap<Student,String> courseCurve = null;
		
		Set<Student> keys = sortedstudentTotalMarks.keySet();
		Iterator<Student> keyIterator = keys.iterator();
		while (keyIterator.hasNext()) {
			Student student = keyIterator.next();
			int UIN = student.getUIN();
			double marks = (double) sortedstudentTotalMarks.get(student);
			
		}
		
		curve.setCourseCurve(courseCurve);
		return curve;
	}
	
	
	// HashMap<String,Integer> = HashMap<Grade,Minimum Number of students in that grade>
	// Example: <'A',10> = Atleast top 10 students get 'A' grade 
	// <'B',10> = After all 'A' grades, atleast 10 students will be given 'B' grade
	public static CourseCurve calculateMaxGapCurve(int offerID, List<Integer> curvingCriteria){
		CourseCurve curve = new CourseCurve(offerID,curvingCriteria);
		
		// STEP 1: Get total CourseMarks from CourseStructure Table
		int totalCourseMarks = curve.getTotalCourseMarks();
		
		// STEP 2: get totalMarks for each student from CourseExams' Table
		HashMap<Student,Double> studentTotalMarks = curve.getStudentTotalMarks();
		
		// STEP 3: Sort the hashmap in descending order
		HashMap<Student,Double> sortedstudentTotalMarks = sortHashMap(studentTotalMarks);
		
		// STEP 4: Calculate curve for the courseOffered based on
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
}


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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

	int offerID; // CourseOffered offerID
	List<Integer> curvingCriteria; // <10,20,10>
	HashMap<Student,String> courseCurve; // <Student,Grade>
	
	@Target({ElementType.LOCAL_VARIABLE})
	@Retention(RetentionPolicy.RUNTIME)
	public @interface DBAnnotation {
	 String[] variable () default "";
	 String[] table () default "";
	 String[] column () default "";
	 boolean[] isSource () default false; 
	}
	
	// Constructor. Takes offerID and curvingCriteria to create CourseCurve
	// object used to calculate curve
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
	
	// To retrieve the total marks for the course
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
		String SQLExamStructureSelect = "Select sum(TotalMarks) As TotalMarks2 FROM %s;";
			SQLExamStructureSelect = String.format(SQLExamStructureSelect, tableName);
		try {
			Connection conn = Database.getConnection();
			try {
				if (conn != null) {
				 
					PreparedStatement statement = conn.prepareStatement(SQLExamStructureSelect);
					//statement.setString(1, tableName);
					ResultSet rs = statement.executeQuery();
					
					while(rs.next()){
						totalCourseMarks = rs.getInt(1);
						System.out.println("---------------------");
						System.out.println("totalCourseMarks: " + totalCourseMarks);
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
	
	// Get the total marks of all the exams for all students 
	// Example: <Student, 87>
	private HashMap<Student,Double> getStudentTotalMarks(){
		HashMap<Student,Double> studentTotalMarks = new HashMap<Student,Double>();
		
		int offerID = this.getOfferID();
		CourseExams exams = new CourseExams(offerID);
		
		studentTotalMarks = exams.getStudentMarks().getExamMarks();
		
		return studentTotalMarks;
		
	}
	
	// Gets the percentage of the last student in the course
	public double getLastStudentPercent(){
		double percent = 0.0;
		HashMap<Student,Double> studentTotalMarks = new HashMap<Student,Double>();
		
		int offerID = this.getOfferID();
		CourseExams exams = new CourseExams(offerID);
		
		int totalCourseMarks = this.getTotalCourseMarks();
		studentTotalMarks = exams.getStudentMarks().getExamMarks();
		
		LinkedHashMap<Student,Double> sortedstudentTotalMarks = (LinkedHashMap<Student, Double>) sortHashMap(studentTotalMarks);
		Set<Entry<Student,Double>> mapValues = sortedstudentTotalMarks.entrySet();
		int mapLength = mapValues.size();
		Entry<Student,Double>[] entry = new Entry[mapLength];
		mapValues.toArray(entry);
		double totalmks = entry[mapLength-1].getValue();
		System.out.println("Mks: " + totalmks + "totalCourseMarks: " + totalCourseMarks);
		
//		sortedstudentTotalMarks.entrySet().iterator().next();
//		@SuppressWarnings("unchecked")
//		List<Student> keys = (List<Student>) sortedstudentTotalMarks.keySet();
//		Student lastKey = keys.get(keys.size()-1);
//		double totalMarks = sortedstudentTotalMarks.get(lastKey);
//		
		percent = (totalmks / totalCourseMarks) * 100;
		
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
		HashMap<Student,String> courseCurve = new HashMap<Student,String>();
		int numberOfStudents = sortedstudentTotalMarks.size();
		int numberOfGrades = curvingCriteria.size();
		int studentsLeft = numberOfStudents;
		
//		System.out.println("numberOfStudents: " + numberOfStudents + " numberOfGrades: " + numberOfGrades + " studentsLeft: " + studentsLeft );
		
		
		Set<Student> keys = sortedstudentTotalMarks.keySet();
		Iterator<Student> keyIterator = keys.iterator();
		
		for (int gradeLevel=1; gradeLevel<= numberOfGrades;gradeLevel++){
			GradeSystem grade = GradeSystem.getGradeForGradeLevel(gradeLevel);
			String studentGrade = grade.getGrade();
			double percentStudents = curvingCriteria.get(gradeLevel-1);
//			System.out.println("percentStudents " + percentStudents);
//			System.out.println("GradeLevel: " + gradeLevel);
			double value =  ((percentStudents/100) * numberOfStudents);
			// System.out.println("Value: " + value);
			int students = (int)(Math.ceil(value));
//			System.out.println("Students: " + students);
			// For roundoff error
			if(students > studentsLeft){
				students = studentsLeft;
//				System.out.println("StudentsLeft-: " + studentsLeft);
			}
			
			for(int studentsAdded = 0; studentsAdded<students;studentsAdded++){
				if(keyIterator.hasNext()){
					Student student = keyIterator.next();
//					System.out.println("StudentUIN: " + student.getUIN());
					courseCurve.put(student, studentGrade);
					studentsLeft--;
//					System.out.println("StudentsLeft: " + studentsLeft);
				}
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
//		System.out.println("lastCriteria: "+lastCriteria+" lastStudentPercent: " + lastStudentPercent + " \nfloorlastStudentPercent: " + floorlastStudentPercent);
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
		HashMap<Student,String> courseCurve = new HashMap<Student,String>();
		
		// int numberOfStudents = sortedstudentTotalMarks.size();
		int numberOfGrades = curvingCriteria.size();
		// int studentsLeft = numberOfStudents;
		
//		System.out.println(" numberOfGrades: " + numberOfGrades + "totalCourseMarks: " + totalCourseMarks);	
		Set<Student> keys = sortedstudentTotalMarks.keySet();
		Iterator<Student> keyIterator = keys.iterator();
		
		for (int gradeLevel=1; gradeLevel<= numberOfGrades;gradeLevel++){
			GradeSystem grade = GradeSystem.getGradeForGradeLevel(gradeLevel);
			String studentGrade = grade.getGrade();
		
			int cutOffPercent = curvingCriteria.get(gradeLevel-1);
//			System.out.println("cutOffPercent: " + cutOffPercent);
			while (keyIterator.hasNext()) {
				Student student = keyIterator.next();
				//int UIN = student.getUIN();
				double marks = (double) sortedstudentTotalMarks.get(student);
				double studentPercentage = (marks/ totalCourseMarks) * 100;
//				System.out.println("studentPercentage "+studentPercentage);
				if(studentPercentage >= cutOffPercent){
//					System.out.println("--");
					courseCurve.put(student, studentGrade);
				} else {
					grade = GradeSystem.getGradeForGradeLevel(gradeLevel+1);
					studentGrade = grade.getGrade();
					courseCurve.put(student, studentGrade);
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
		HashMap<Student,String> courseCurve = new HashMap<Student,String>();
		
		// Algo to calculate curve
		// 1. Sort students in decreasing order of total mks -- Done in step 3
		// 2. Skip over minimum students and give them the first grade
		// 3. Keep track of difference between total marks of students students
		// 4. Find 1st instance where difference increases. 
		// 5. That is the cut-off point.
		// 6. Move on to the next grade
		
		int numberOfStudents = sortedstudentTotalMarks.size();
		int numberOfGrades = curvingCriteria.size();
		int studentsLeft = numberOfStudents;
		Set<Student> keys = sortedstudentTotalMarks.keySet();
		Iterator<Student> keyIterator = keys.iterator();
		
		boolean nextGraded = false;
		for (int gradeLevel=1; gradeLevel <= numberOfGrades;gradeLevel++){
			GradeSystem grade = GradeSystem.getGradeForGradeLevel(gradeLevel);
			String studentGrade = grade.getGrade();
			int gradedStudents = 0;
			int minimumPeople = curvingCriteria.get(gradeLevel-1);
			System.out.println("numberOfGrades: " + numberOfGrades + " minimumPeople:" + minimumPeople);
			if(nextGraded==true){
				minimumPeople-=2;
				nextGraded = false;
			}
			
			@SuppressWarnings("unused")
			int UIN = 0;
			double marks = 0.0;
			while ((keyIterator.hasNext()) && gradedStudents < minimumPeople ) {
				Student student = keyIterator.next();
				UIN = student.getUIN();
				marks = (double) sortedstudentTotalMarks.get(student);
				System.out.println("UIN-: " + UIN + " Grade-: " + studentGrade);
				courseCurve.put(student, studentGrade);
				gradedStudents++;
				studentsLeft--;
				System.out.println("Graded Students: " + gradedStudents + " studentsLeft: " + studentsLeft);
			}
			if(keyIterator.hasNext()){
				boolean added = false;
				Student student = keyIterator.next();
				@SuppressWarnings("unused")
				int nextUIN = student.getUIN();
				double nextMarks = (double) sortedstudentTotalMarks.get(student);
				
				System.out.println("nextUIN: "+nextUIN+" nextMarks: " + nextMarks +" mks: "+ marks);
				
				double difference = marks-nextMarks;
				
				while(keyIterator.hasNext() && (gradeLevel< numberOfGrades)) {
					added = true;
					marks = nextMarks;
					Student nextstudent = keyIterator.next();
					int nextUIN2 = nextstudent.getUIN();
					nextMarks = (double) sortedstudentTotalMarks.get(nextstudent);
					double nextDifference = marks - nextMarks;
					System.out.println("nextUIN2: " + nextUIN2 + " nextMarks-: " + nextMarks);
					if(nextDifference >= difference){
						//System.out.println("UIN: " + nextUIN + " Grade: " + studentGrade);
						courseCurve.put(student, studentGrade);
						gradedStudents++;
						studentsLeft--;
						System.out.println("Graded Students: " + gradedStudents + " studentsLeft: " + studentsLeft);
						grade = GradeSystem.getGradeForGradeLevel((gradeLevel+1));
						studentGrade = grade.getGrade();
						System.out.println("NEXTUINinIF: " + nextUIN2 + " Grade: " + studentGrade);
						courseCurve.put(nextstudent, studentGrade);
						gradedStudents++;
						studentsLeft--;
						System.out.println("Graded Students: " + gradedStudents + " studentsLeft: " + studentsLeft);
						added = false;
						break;
					} else if (nextDifference < difference){
						gradeLevel++;
						GradeSystem nextGrade = GradeSystem.getGradeForGradeLevel(gradeLevel);
						String nextStudentGrade = nextGrade.getGrade();
						System.out.println("UIN: " + UIN + " Grade: " + studentGrade);
						System.out.println("NEXTUIN: " + nextUIN + " Grade: " + nextStudentGrade);
						courseCurve.put(student, nextStudentGrade);
						gradedStudents++;
						studentsLeft--;
						System.out.println("Graded Students: " + gradedStudents + " studentsLeft: " + studentsLeft);
						courseCurve.put(nextstudent, nextStudentGrade);
						gradedStudents++;
						studentsLeft--;
						System.out.println("Graded Students: " + gradedStudents + " studentsLeft: " + studentsLeft);
						added = false;
						//nextGraded = true;
						break;
					}
					
					
					
				} 
				System.out.println("Added: " +added);
				if (added==false){
					System.out.println("Graded Students--: " + gradedStudents + " studentsLeft: " + studentsLeft);
					System.out.println("gradelevel: "+gradeLevel + " numberOfGrades: " +numberOfGrades);
					if(studentsLeft > 0 && (gradeLevel == numberOfGrades-1) ){
						//Student student = keyIterator.next();
						GradeSystem grade2 = GradeSystem.getGradeForGradeLevel(gradeLevel);
						String studentGrade2 = grade.getGrade();
						courseCurve.put(student, studentGrade2);
						studentsLeft--;
						System.out.println("Graded Students: " + gradedStudents + " studentsLeft: " + studentsLeft);
						if(keyIterator.hasNext()){
							student = keyIterator.next();
							grade2 = GradeSystem.getGradeForGradeLevel(gradeLevel+1);
							studentGrade2 = grade.getGrade();
							courseCurve.put(student, studentGrade2);
							studentsLeft--;
							System.out.println("Graded Students: " + gradedStudents + " studentsLeft: " + studentsLeft);
						}
						
					}
				}
			
				
			
			}
			
		}
		curve.setCourseCurve(courseCurve);
		return curve;
	}
	
	// Function to update the final grades in the StudentEnrollment table
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
	
	// To sort a HashMap
	// [1] http://stackoverflow.com/questions/8119366/sorting-hashmap-by-values
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
		
		// To test percentage curve
//		int offerID = 345678;
//		List<Integer> curvingCriteria = new ArrayList<Integer>();
//		curvingCriteria.add(30);
//		curvingCriteria.add(40);
//		curvingCriteria.add(30);
//				
//		CourseCurve curve = CourseCurve.calculatePercentageCurve(offerID, curvingCriteria);
//		
//		HashMap<Student,String> courseCurve = curve.getCourseCurve();
//		System.out.println("----------------------------------");
//		for(Student students:courseCurve.keySet()){
//			int UIN = students.getUIN();
//			String grade = courseCurve.get(students);
//			
//			System.out.println("UIN: " + UIN + " Grade: " + grade);
//		
//		}
//		CourseOffered offered = null;
//		try {
//			offered = new CourseOffered(offerID);
//		} catch (Course.CourseDoesNotExistException
//				| CourseOffered.CourseOfferingDoesNotExistException e) {
//			e.printStackTrace();
//		}
//		
//		StudentEnrollment.updateAllStudentGrade(courseCurve, offered);
		
		// To test absolute curve
//		int offerID = 345678;
//		List<Integer> curvingCriteria = new ArrayList<Integer>();
//		curvingCriteria.add(65);
//		curvingCriteria.add(50);
//		curvingCriteria.add(45);
//				
//		CourseCurve curve = CourseCurve.calculateAbsoluteCurve(offerID, curvingCriteria);
//		
//		HashMap<Student,String> courseCurve = curve.getCourseCurve();
//		System.out.println("----------------------------------");
//		if(courseCurve != null){
//			for(Student students:courseCurve.keySet()){
//				int UIN = students.getUIN();
//				String grade = courseCurve.get(students);
//				
//				System.out.println("UIN: " + UIN + " Grade: " + grade);
//			}
//		}
		
		
		// To test max gap curve
//		int offerID = 345678;
//		List<Integer> curvingCriteria = new ArrayList<Integer>();
//		curvingCriteria.add(3);
//		curvingCriteria.add(1);
//		curvingCriteria.add(1);
//				
//		CourseCurve curve = CourseCurve.calculateMaxGapCurve(offerID, curvingCriteria);
//		
//		HashMap<Student,String> courseCurve = curve.getCourseCurve();
//		System.out.println("----------------------------------");
//		if(courseCurve != null){
//			for(Student students:courseCurve.keySet()){
//				int UIN = students.getUIN();
//				String grade = courseCurve.get(students);
//				
//				System.out.println("UIN: " + UIN + " Grade: " + grade);
//			}
//		}
		
		
		
	}


}

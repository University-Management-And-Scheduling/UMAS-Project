import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;


public class StudentEnrollment {
	int enrollmentID; // Unique id per enrollment
	int offerID; //OfferID of course offered in a sem
	char grade; //Student Grade = 'A', 'B','C', 'D' and 'F'
	int UIN;
	
	
	// Stud obbj
	//CREATE HashMap OF hashmap 3 nodes enrollmentID, offerID,grade
	
	// Constructor to populate object

	public int getEnrollmentID() {
		return enrollmentID;
	}

	public StudentEnrollment(int enrollmentID, int offerID, char grade, int UIN) {
		super();
		this.enrollmentID = enrollmentID;
		this.offerID = offerID;
		this.grade = grade;
		this.UIN = UIN;
	}

	public int getOfferID() {
		return offerID;
	}

	public char getStudentgrade() {
		return grade;
	}

	public void setEnrollmentID(int enrollmentID) {
		this.enrollmentID = enrollmentID;
	}

	public void setOfferID(int offerID) {
		this.offerID = offerID;
	}

	public void setStudentGrade(char grade) {
		this.grade = grade;
	}
	
	public HashMap<CourseOffered, String> getAllGradesOfStudent (Student student) {
		int UIN = student.getUIN();
		HashMap<CourseOffered, String> courseGrade = new HashMap<CourseOffered, String>();
		String SQLGradeSelect = "Select offerID, grade FROM studentenrollment WHERE UIN = ?;";
		try{
			Connection conn = Database.getConnection();
			
			try{
			
				if(conn != null){
					
					PreparedStatement statement = conn.prepareStatement(SQLGradeSelect);
					statement.setInt(1, UIN);
					ResultSet rs = null;
					rs =  statement.executeQuery();
				
					while(rs.next()){
				         //Retrieve by column name
				         int offerID = rs.getInt("offerID");
				         String grade = rs.getString("grade");
				         CourseOffered studentCourse = new CourseOffered(offerID); 
				         courseGrade.put(studentCourse, grade);
//				         Item item = new Item(itemID, itemName,itemQuantity, itemCost);
//				         items.add(item);
					}      
				}
			}
			catch(SQLException e){
				System.out.println(e);
				conn.rollback();
			}
		
			finally{
				// conn.close();
			}   
		
		}
		catch(SQLException e){
			System.out.println(e);
		}


		return courseGrade;
	
	}

//	public String getGradeforCourse (CourseOffered courseOffered) {
//
//		String grade = "A";
//		
//		//DB Code
//		
//		return grade;
//	}
	
	public static ArrayList<CourseOffered> getStudentsCourses(Student student){
		ArrayList<CourseOffered> coursesTaken = new ArrayList<CourseOffered>();
		
		
		return coursesTaken;
		
	}
	
 	public ArrayList<Student> getStudentsInCourse(CourseOffered courseOffered) {
		ArrayList<Student> enrolledStudents = new ArrayList<Student>();
		
		// DB Code
		
		Student student = new Student();
		enrolledStudents.add(student);
		return enrolledStudents;
		
	}
	
	public ArrayList<CourseOffered> getAllCoursesOfStudent (Student student) {
		ArrayList<CourseOffered> enrolledCourses = new ArrayList<CourseOffered>();
		// DB COde
		
		
		
		
		
	//	CourseOffered courseEnrolled = new CourseOffered(Course course, CourseSchedule courseSchedule,
	//			CourseFiles courseFiles, int offerID, String semester, Calendar year,
	//			int totalCapacity, int currentlyFilled);
	//	enrolledCourses.add(courseEnrolled);
		return enrolledCourses;
	}

	
	public static int getStudentEnrollmentID(Student student, int offerID) {
		// TODO Auto-generated method stub
		return 0;
	}
}

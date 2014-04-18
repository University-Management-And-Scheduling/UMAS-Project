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

public class StudentEnrollment {
	int enrollmentID; // Unique id per enrollment
	int UIN;		// Student UIN
	int offerID; //OfferID of course offered in a sem
	char grade; //Student Grade = 'A', 'B','C', 'D' and 'F'
	
	
	@Target({ElementType.LOCAL_VARIABLE})
	@Retention(RetentionPolicy.RUNTIME)
	public @interface DBAnnotation {
	 String[] variable () default "";
	 String[] table () default "";
	 String[] column () default "";
	 boolean[] isSource () default false; 
	}
	
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

	public StudentEnrollment(int offerID, char grade, int UIN) {
		super();
		this.offerID = offerID;
		this.grade = grade;
		this.UIN = UIN;
	}
	
	// Constructor to create objects of studentenrollment before inserting into 
	// the database for the first time
	public StudentEnrollment(int offerID, int UIN) {
		super();
		this.offerID = offerID;
		this.grade = 'A'; // Default grade at the time of enrollment
		this.UIN = UIN;
	}
	
	public int getUIN() {
		return UIN;
	}

	public void setUIN(int uIN) {
		UIN = uIN;
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

	public char getGrade() {
		return grade;
	}

	public void setGrade(char grade) {
		this.grade = grade;
	}

	
	public HashMap<CourseOffered, String> getAllGradesOfStudent (Student student) {
		int UIN = student.getUIN();
		HashMap<CourseOffered, String> courseGrade = new HashMap<CourseOffered, String>();
		
		@DBAnnotation (
				variable = {"UIN"},  
				table = "studentenrollment", 
				column = {"UIN"}, 
				isSource = true)
		
		String SQLGradeSelect = "Select offerID, grade FROM studentenrollment WHERE UIN = ?;";
		try{
			Connection conn = Database.getConnection();
			
			try{
			
				if(conn != null){
					
					PreparedStatement statement = conn.prepareStatement(SQLGradeSelect);
					statement.setInt(1, UIN);
					ResultSet rs = statement.executeQuery();
				
					while(rs.next()){
						//Retrieve by column name
				        int offerID = rs.getInt("offerID");
				        String grade = rs.getString("grade");
				        CourseOffered studentCourse = null;
				 		
				 		try {
				 			studentCourse = new CourseOffered(offerID);
				 		} catch (Course.CourseDoesNotExistException e1) {
				 			e1.printStackTrace();
				 		} catch (CourseOffered.CourseOfferingDoesNotExistException e1) {
				 			e1.printStackTrace();
				 		}
				         
				         courseGrade.put(studentCourse, grade);
//				         Item item = new Item(itemID, itemName,itemQuantity, itemCost);
//				         items.add(item);
					}      
				}
			}
			catch(SQLException e){
				System.out.println(e);
			}
				
		}
		catch(Exception e){
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
	
	public static ArrayList<CourseOffered> getStudentsAllCourses(Student student){
		ArrayList<CourseOffered> coursesTaken = new ArrayList<CourseOffered>();
		
		int UIN = student.getUIN();
		
		@DBAnnotation (
				variable = {"UIN"},  
				table = "studentenrollment", 
				column = {"UIN"}, 
				isSource = true)
		
		String SQLGradeSelect = "Select offerID FROM studentenrollment WHERE UIN = ?;";
		try{
			Connection conn = Database.getConnection();
			
			try{
			
				if(conn != null){
					
					PreparedStatement statement = conn.prepareStatement(SQLGradeSelect);
					statement.setInt(1, UIN);
					ResultSet rs = statement.executeQuery();
				
					while(rs.next()){
						//Retrieve by column name
				        int offerID = rs.getInt("OfferID");
				        CourseOffered studentCourse = null;
				 		
				 		try {
				 			studentCourse = new CourseOffered(offerID);
				 		} catch (Course.CourseDoesNotExistException e1) {
				 			e1.printStackTrace();
				 		} catch (CourseOffered.CourseOfferingDoesNotExistException e1) {
				 			e1.printStackTrace();
				 		}
				         
				 		coursesTaken.add(studentCourse);
					}      
				}
			} catch(SQLException e){
				System.out.println(e);
			}
				
		} catch(Exception e){
			System.out.println(e);
		}
		
		return coursesTaken;
	}
	
 	public ArrayList<Student> getStudentsInCourse(CourseOffered courseOffered) {
		ArrayList<Student> enrolledStudents = new ArrayList<Student>();
		
		int offerID = courseOffered.getOfferID();
		
		@DBAnnotation (
				variable = {"offerID"},  
				table = "studentenrollment", 
				column = {"OfferID"}, 
				isSource = true)
		
		String SQLGradeSelect = "Select UIN FROM studentenrollment WHERE OfferID = ?;";
		try{
			Connection conn = Database.getConnection();
			
			try{
			
				if(conn != null){
					
					PreparedStatement statement = conn.prepareStatement(SQLGradeSelect);
					statement.setInt(1, offerID);
					ResultSet rs = statement.executeQuery();
				
					while(rs.next()){
						//Retrieve by column name
				        int UIN = rs.getInt("UIN");
				        Student student = new Student(UIN);
						enrolledStudents.add(student);
					}      
				}
			} catch(SQLException e){
				System.out.println(e);
			}
				
		} catch(Exception e){
			System.out.println(e);
		}
		return enrolledStudents;
	}
	
	public ArrayList<CourseOffered> getCurrentCoursesOfStudent (Student student) {
		ArrayList<CourseOffered> enrolledCourses = new ArrayList<CourseOffered>();
		
		int UIN = student.getUIN();
		
		@DBAnnotation (
				variable = {"UIN"},  
				table = "studentenrollment", 
				column = {"UIN"}, 
				isSource = true)
		
		String SQLGradeSelect = "SELECT studentenrollment.OfferID " +  
								"FROM university.studentenrollment JOIN coursesoffered JOIN semester " +
								"Where studentenrollment.UIN = ? " +
								"AND studentenrollment.OfferID = coursesoffered.OfferID " + 
								"AND coursesoffered.SemesterID = semester.SemesterID " +
								"AND semester.IsCurrent = 1;";
		try{
			Connection conn = Database.getConnection();
			
			try{
			
				if(conn != null){
					
					PreparedStatement statement = conn.prepareStatement(SQLGradeSelect);
					statement.setInt(1, UIN);
					ResultSet rs = statement.executeQuery();
				
					while(rs.next()){
						//Retrieve by column name
				        int offerID = rs.getInt("OfferID");
				        CourseOffered studentCourse = null;
				 		
				 		try {
				 			studentCourse = new CourseOffered(offerID);
				 		} catch (Course.CourseDoesNotExistException e1) {
				 			e1.printStackTrace();
				 		} catch (CourseOffered.CourseOfferingDoesNotExistException e1) {
				 			e1.printStackTrace();
				 		}
				         
				 		enrolledCourses.add(studentCourse);
					}      
				}
			} catch(SQLException e){
				System.out.println(e);
			}
				
		} catch(Exception e){
			System.out.println(e);
		}
		
		return enrolledCourses;
	}

	public synchronized boolean enrollStudents(){
		boolean isStudentEnrolled = false;
		int UIN = this.getUIN();
		int offerID = this.getOfferID();
		
		// Step 1: Check if student is already enrolled for this course
		boolean isStudentCurrentlyEnrolled = this.isStudentEnrolled(UIN, offerID);
		if(isStudentCurrentlyEnrolled == true){
			System.out.println("The student is already enrolled");
		} else{
			// Step 2: if student is not enrolled, check whether there are any seats left.
			boolean isSeatAvailable = this.isSeatAvailable();
			if(isSeatAvailable == false){
				System.out.println("Seats not available.");
			} else{
				
				// Step 3: If student is not enrolled currently AND 
				// if a seat is available, Enroll the student
			
				
				@DBAnnotation (
						variable = {"UIN","offerID"},  
						table = "studentenrollment", 
						column = {"UIN","OfferID"}, 
						isSource = false)
				String SQLStudentEnrollInsert = "INSERT INTO studentenrollment VALUES(?,?,A) ;";
				
				try {
					Connection conn = Database.getConnection();
					try {
						if (conn != null) {
						 
							PreparedStatement statement = conn.prepareStatement(SQLStudentEnrollInsert);
							statement.setInt(1, UIN);
							statement.setInt(2, offerID);
							statement.executeUpdate();
							Database.commitTransaction(conn);
							isStudentEnrolled = true;
						}	
					} catch (SQLException e) {
						System.out.println(e);
						Database.rollBackTransaction(conn);
					}

				} catch (Exception e) {
					System.out.println(e);
				}
			}	
		}
		return isStudentEnrolled;
	}
	
	public boolean updateStudentGrade(){
		boolean isGradeUpdated = false;
		int UIN = this.getUIN();
		int offerID = this.getOfferID();
		
		// Step 1: Check if student is already enrolled for this course
		boolean isStudentCurrentlyEnrolled = this.isStudentEnrolled(UIN, offerID);
		if(isStudentCurrentlyEnrolled == false){
			System.out.println("The student is not enrolled");
		} else{
			
			int enrollmentID = this.getStudentEnrollmentID();	
			// Step 2: If student is enrolled currently, update their grade.
			
			@DBAnnotation (
					variable = {"UIN","offerID"},  
					table = "studentenrollment", 
					column = {"UIN","OfferID"}, 
					isSource = false)
			String SQLStudentEnrollInsert = "UPDATE `studentenrollment` SET `Grade`='?' WHERE `EnrollmentID`='?';";
			
			try {
				Connection conn = Database.getConnection();
				try {
					if (conn != null) {
					 
						PreparedStatement statement = conn.prepareStatement(SQLStudentEnrollInsert);
						statement.setInt(1, UIN);
						statement.setInt(2, offerID);
						statement.executeUpdate();
						Database.commitTransaction(conn);
						isGradeUpdated = true;
					}	
				} catch (SQLException e) {
					System.out.println(e);
					Database.rollBackTransaction(conn);
				}

			} catch (Exception e) {
				System.out.println(e);
			}
		}
		
		return isGradeUpdated;
	}
	
	private int getStudentEnrollmentID() {
		int enrollmentID = 0;
		int UIN = this.getUIN();
		int offerID = this.getOfferID();
		
		@DBAnnotation (
				variable = {"UIN","offerID","enrollmentID"},  
				table = "studentenrollment", 
				column = {"UIN","OfferID","EnrollmentID"}, 
				isSource = true)
		
		String SQLStudentEnrollSelect = "Select EnrollmentID FROM studentenrollment WHERE UIN = ? AND OfferID = ?;";
		
		try{
			Connection conn = Database.getConnection();
			
			try{
			
				if(conn != null){
					
					PreparedStatement statement = conn.prepareStatement(SQLStudentEnrollSelect);
					statement.setInt(1, UIN);
					statement.setInt(2, offerID);
					
					ResultSet rs = statement.executeQuery();
				
					while(rs.next()){
				        enrollmentID = rs.getInt("EnrollmentID");
					}      
					this.setEnrollmentID(enrollmentID);
				}
			}
			catch(SQLException e){
				System.out.println(e);
			}
				
		}
		catch(Exception e){
			System.out.println(e);
		}
		
		return enrollmentID;
	}

	private boolean isStudentEnrolled(int UIN,int offerID){
		boolean isStudentEnrolled = false;
		
		@DBAnnotation (
				variable = {"UIN","offerID"},  
				table = "studentenrollment", 
				column = {"UIN","OfferID"}, 
				isSource = true)
		
		String SQLStudentEnrollSelect = "Select UIN FROM studentenrollment WHERE UIN = ? AND OfferID = ?;";
		
		try{
			Connection conn = Database.getConnection();
			
			try{
			
				if(conn != null){
					
					PreparedStatement statement = conn.prepareStatement(SQLStudentEnrollSelect);
					statement.setInt(1, UIN);
					statement.setInt(2, offerID);
					
					ResultSet rs = statement.executeQuery();
				
					while(rs.next()){
						//Retrieve by column name
				        int tableUIN = rs.getInt("UIN");
				         
				        if (UIN == tableUIN){
				        	isStudentEnrolled = true;
				        }
					}      
				}
			}
			catch(SQLException e){
				System.out.println(e);
			}
				
		}
		catch(Exception e){
			System.out.println(e);
		}
		
		
		return isStudentEnrolled;
	}
		
	private boolean isSeatAvailable(){
		boolean isSeatAvailable = false;
		
		int offerID = this.getOfferID();
		
		@DBAnnotation (
				variable = {"offerID"},  
				table = "coursesoffered", 
				column = {"OfferID"}, 
				isSource = true)
		
		String SQLStudentEnrollSelect = "Select OfferID FROM coursesoffered " +
										"WHERE SeatsFilled < TotalCapacity " +
										"AND OfferID = ?;";
		
		try{
			Connection conn = Database.getConnection();
			
			try{
			
				if(conn != null){
					
					PreparedStatement statement = conn.prepareStatement(SQLStudentEnrollSelect);
					statement.setInt(1, offerID);
					
					ResultSet rs = statement.executeQuery();
				
					while(rs.next()){
						//Retrieve by column name
				        int tableOfferID = rs.getInt("OfferID");
				         
				        if (offerID == tableOfferID){
				        	isSeatAvailable = true;
				        }
					}      
				}
			}
			catch(SQLException e){
				System.out.println(e);
			}
				
		}
		catch(Exception e){
			System.out.println(e);
		}
		
		
		return isSeatAvailable;
	}
	
	
	
}

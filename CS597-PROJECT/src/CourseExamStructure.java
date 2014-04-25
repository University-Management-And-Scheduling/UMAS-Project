import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class CourseExamStructure {
	CourseOffered offeredCourse;
	String examName;
	int examTotal;
	
	@Target({ElementType.LOCAL_VARIABLE})
	@Retention(RetentionPolicy.RUNTIME)
	public @interface DBAnnotation {
	 String[] variable () default "";
	 String[] table () default "";
	 String[] column () default "";
	 boolean[] isSource () default false; 
	}
	
	public CourseOffered getOfferedCourse() {
		return offeredCourse;
	}

	public void setOfferedCourse(CourseOffered offeredCourse) {
		this.offeredCourse = offeredCourse;
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
	
	public CourseExamStructure() {
		super();
	}

	public CourseExamStructure(CourseOffered offeredCourse, String examName, int examTotal) {
		this.offeredCourse = offeredCourse;
		this.examName = examName;
		this.examTotal = examTotal;
	}
	
	public CourseExamStructure(CourseOffered offeredCourse, String examName) {
		this.offeredCourse = offeredCourse;
		this.examName = examName;
		// this.examTotal = getTotalMarksForExam(examName);
		
		Course course = offeredCourse.getCourse();
		String courseName = course.getCourseName();
		int offerID= offeredCourse.getOfferID();
		int semID = offeredCourse.getSemesterID();
		String tableName = courseName + Integer.toString(offerID) + Integer.toString(semID) + "Structure";
		
		boolean isExamPresent = isExamPresent(tableName,examName);
		if (isExamPresent == false){
			System.out.println("Exam Not Present. Please try again.");
		} else {
		
			String SQLExamStructureSelect = "Select TotalMarks FROM %s WHERE ExamName = ?;";
			SQLExamStructureSelect = String.format(SQLExamStructureSelect, tableName);
			try {
				Connection conn = Database.getConnection();
				try {
					if (conn != null) {
					 
						PreparedStatement statement = conn.prepareStatement(SQLExamStructureSelect);
	//					statement.setString(1, tableName);
						statement.setString(1, examName);
						ResultSet rs = statement.executeQuery();
						while(rs.next()){
							this.examTotal = rs.getInt("TotalMarks");
						}
					}	
				} catch (SQLException e) {
					System.out.println(e);
				}
	
			} catch (Exception e) {
				System.out.println(e);
			}
		
		}
		
	}
	
	public static boolean createCourseExamStructureTable(String courseName, int offerID,int semID){
		boolean tableAdded = false;
//		Course course = offeredCourse.getCourse();
//		String courseName = course.getCourseName();
//		int offerID= offeredCourse.getOfferID();
//		int semID = offeredCourse.getSemesterID();
		
		String tableName = courseName + Integer.toString(offerID) + Integer.toString(semID) + "Structure"; 
		
//		@DBAnnotation (
//				variable = {"tableName"},  
//				table = "courseExamStructureTable", 
//				column = {"Username","Password"}, 
//				isSource = false)
		String SQLExamStructureCreate = "CREATE TABLE %s (ExamName varchar(20), TotalMarks int(12),PRIMARY KEY (ExamName)) ;";
		SQLExamStructureCreate = String.format(SQLExamStructureCreate, tableName);
		try {
			Connection conn = Database.getConnection();
			try {
				if (conn != null) {
					System.out.println("Before struct create");
					PreparedStatement statement = conn.prepareStatement(SQLExamStructureCreate);
					//statement.setString(1, tableName);
					statement.executeUpdate();
					tableAdded = true;
					System.out.println("After struct create");
				}	
			} catch (SQLException e) {
				System.out.println(e);
				Database.rollBackTransaction(conn);
			}

		} catch (Exception e) {
			System.out.println(e);
		}
	 
		return tableAdded;
	}
	
	public static int getTotalMarksForExam(String examName){
		int totalMarks=0;
		
		return totalMarks;
	}
	
	public boolean addNewExam(){
		boolean examAdded = false;
		
		CourseOffered offeredCourse = this.offeredCourse;
		Course course = offeredCourse.getCourse();
		String courseName = course.getCourseName();
		int offerID= offeredCourse.getOfferID();
		int semID = offeredCourse.getSemesterID();
		
		String tableName = courseName + Integer.toString(offerID) + Integer.toString(semID) + "Structure";
		
		String examName = this.examName;
		int examTotal = this.examTotal;
		
		boolean isExamPresent = isExamPresent(tableName,examName);
		if (isExamPresent == true){
			System.out.println("Exam already present. Please try again.");
		} else {
			
		@DBAnnotation (
				variable = {"examName","examTotal"},  
				table = "tableName", 
				column = {"ExamName","TotalMarks"}, 
				isSource = false)
		String SQLExamStructureCreate = "INSERT INTO %s (ExamName,TotalMarks) VALUES(?,?) ;";
		SQLExamStructureCreate = String.format(SQLExamStructureCreate, tableName);
		
		try {
			Connection conn = Database.getConnection();
			try {
				if (conn != null) {
				 
					PreparedStatement statement = conn.prepareStatement(SQLExamStructureCreate);
//					statement.setString(1, tableName);
					statement.setString(1, examName);
					statement.setInt(2, examTotal);
					statement.executeUpdate();
					examAdded = CourseExams.addNewExamColumn(this);
					if (examAdded == true){
						Database.commitTransaction(conn);
					}
					else{
						Database.rollBackTransaction(conn);
					}
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
		
	private boolean isExamPresent(String tableName, String examName) {
		boolean isExamPresent = false;
//		
//		CourseOffered offeredCourse = this.offeredCourse;
//		Course course = offeredCourse.getCourse();
//		String courseName = course.getCourseName();
//		int offerID= offeredCourse.getOfferID();
//		int semID = offeredCourse.getSemesterID();
//		
//		String tableName = courseName + Integer.toString(offerID) + Integer.toString(semID) + "Structure";
//	
		
		@DBAnnotation (
				variable = {"tableExamName"},  
				table = "tableName", 
				column = {"ExamName"}, 
				isSource = true)
		String SQLExamStructureSelect = "Select ExamName FROM %s ;";
		SQLExamStructureSelect = String.format(SQLExamStructureSelect, tableName);
		try {
			Connection conn = Database.getConnection();
			try {
				if (conn != null) {
				 
					PreparedStatement statement = conn.prepareStatement(SQLExamStructureSelect);
//					statement.setString(1, tableName);
					ResultSet rs = statement.executeQuery();
					while(rs.next()){
						String tableExamName = rs.getString("ExamName");
						if (tableExamName.equals(examName)){
							isExamPresent = true;
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

	public boolean modifyExistingExamName(String newExamName){
		boolean nameModified = false;
		
		CourseOffered offeredCourse = this.offeredCourse;
		Course course = offeredCourse.getCourse();
		String courseName = course.getCourseName();
		int offerID= offeredCourse.getOfferID();
		int semID = offeredCourse.getSemesterID();
		
		String tableName = courseName + Integer.toString(offerID) + Integer.toString(semID) + "Structure";
		
		String examName = this.examName;
		
		boolean isExamPresent = isExamPresent(tableName,examName);
		boolean isNewExamPresent = isExamPresent(tableName,newExamName);
		
		if ((isExamPresent == true) && (isNewExamPresent == false)){
			
		@DBAnnotation (
				variable = {"newExamName"},  
				table = "tableName", 
				column = {"ExamName"}, 
				isSource = false)
		String SQLExamStructureUpdate = "UPDATE %s SET ExamName = ? WHERE ExamName = ? ;";
		SQLExamStructureUpdate = String.format(SQLExamStructureUpdate, tableName);
		try {
			Connection conn = Database.getConnection();
			try {
				if (conn != null) {
				 
					PreparedStatement statement = conn.prepareStatement(SQLExamStructureUpdate);
//					statement.setString(1, tableName);
					statement.setString(1, newExamName);
					statement.setString(2, examName);
					statement.executeUpdate();
					boolean modifiedColumn = CourseExams.modifyExistingExamColumnName(this, newExamName);
					if (modifiedColumn == true){
						Database.commitTransaction(conn);
						nameModified = true;
					}
					
				}	
			} catch (SQLException e) {
				System.out.println(e);
				Database.rollBackTransaction(conn);
			}

		} catch (Exception e) {
			System.out.println(e);
		}
		
		} else {
			System.out.println("Old Exam name not present or New exam name already present. Please try again.");
		} // End of Else
		return nameModified;

	}	

	public boolean modifyExistingExamTotalMarks(int newTotalMarks){
		boolean marksModified = false;
		
		CourseOffered offeredCourse = this.offeredCourse;
		Course course = offeredCourse.getCourse();
		String courseName = course.getCourseName();
		int offerID= offeredCourse.getOfferID();
		int semID = offeredCourse.getSemesterID();
		
		String tableName = courseName + Integer.toString(offerID) + Integer.toString(semID) + "Structure";
		
		String examName = this.examName;
		//int examTotal = this.examTotal;
		
		boolean isExamPresent = this.isExamPresent(tableName,examName);
		if (isExamPresent == true){
			
		@DBAnnotation (
				variable = {"newTotalMarks"},  
				table = "tableName", 
				column = {"TotalMarks"}, 
				isSource = false)
		String SQLExamStructureUpdate = "UPDATE %s SET TotalMarks = ? WHERE ExamName = ? ;";
		SQLExamStructureUpdate = String.format(SQLExamStructureUpdate, tableName);
		
		try {
			Connection conn = Database.getConnection();
			try {
				if (conn != null) {
				 
					PreparedStatement statement = conn.prepareStatement(SQLExamStructureUpdate);
					//statement.setString(1, tableName);
					statement.setInt(1, newTotalMarks);
					statement.setString(2, examName);
					statement.executeUpdate();
					Database.commitTransaction(conn);
					marksModified = true;
				}	
			} catch (SQLException e) {
				System.out.println(e);
				Database.rollBackTransaction(conn);
			}

		} catch (Exception e) {
			System.out.println(e);
		}
		
		} else {
			System.out.println("Exam not present. Please try again.");
		} // End of Else
		return marksModified;
	}

	public boolean deleteExistingExam(){
		boolean examDeleted = false;
		
		CourseOffered offeredCourse = this.offeredCourse;
		Course course = offeredCourse.getCourse();
		String courseName = course.getCourseName();
		int offerID= offeredCourse.getOfferID();
		int semID = offeredCourse.getSemesterID();
		
		String tableName = courseName + Integer.toString(offerID) + Integer.toString(semID) + "Structure";
		
		String examName = this.examName;
		//int examTotal = this.examTotal;
		
		boolean isExamPresent = this.isExamPresent(tableName,examName);
		if (isExamPresent == true){
						
		@DBAnnotation (
				variable = {"examName"},  
				table = "tableName", 
				column = {"ExamName"}, 
				isSource = false)
		String SQLExamStructureDelete = "DELETE FROM %s WHERE ExamName = ?  ;";
		SQLExamStructureDelete = String.format(SQLExamStructureDelete, tableName);
		
		try {
			Connection conn = Database.getConnection();
			try {
				if (conn != null) {
					PreparedStatement statement = conn.prepareStatement(SQLExamStructureDelete);
//					statement.setString(1, tableName);
					statement.setString(1, examName);
					statement.executeUpdate();
					boolean examColumnDeleted = CourseExams.deleteExistingExamColumn(this);
					if(examColumnDeleted == true){
						Database.commitTransaction(conn);
						examDeleted = true;
					}
					else{
						Database.rollBackTransaction(conn);
					}
				}	
			} catch (SQLException e) {
				System.out.println(e);
				Database.rollBackTransaction(conn);
			}

		} catch (Exception e) {
			System.out.println(e);
		}
		} else {
			System.out.println("Exam not present. Please try again.");
		} // End of Else
		return examDeleted;
	}

	public static HashMap<String,Integer> viewExams(CourseOffered courseoffered){
		HashMap<String,Integer> allExams = new HashMap<String,Integer>();
		int offerID = courseoffered.getOfferID();
		
//		try {
//			offeredCourse = new CourseOffered(offerID);
//		} catch (Course.CourseDoesNotExistException e1) {
//			e1.printStackTrace();
//		} catch (CourseOffered.CourseOfferingDoesNotExistException e1) {
//			e1.printStackTrace();
//		}
		
		Course course = courseoffered.getCourse();
		String courseName = course.getCourseName();
		int semID = courseoffered.getSemesterID();
		
		String tableName = courseName + Integer.toString(offerID) + Integer.toString(semID) + "Structure"; 
	
		@DBAnnotation (
				variable = {"examName","totalMarks"},  
				table = "tableName", 
				column = {"ExamName","TotalMarks"}, 
				isSource = true)
		String SQLExamSelect = "SELECT ExamName,TotalMarks FROM %s ;";
		SQLExamSelect = String.format(SQLExamSelect, tableName);
		
		try {
			Connection conn = Database.getConnection();
			try {
				if (conn != null) {
					//System.out.println(tableName);
					PreparedStatement statement = conn.prepareStatement(SQLExamSelect);
//					statement.setString(1, tableName);
					ResultSet rs =  statement.executeQuery();
									
					while(rs.next()){
						String examName = rs.getString("ExamName");
						int totalMarks = rs.getInt("TotalMarks");
						System.out.println(examName);
						if(examName != null)
							allExams.put(examName,totalMarks);
					}
				}	
			} catch (SQLException e) {
				System.out.println(e);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return allExams;
	}
	
	public static void main(String[] args){
	
		int offerID = 345678;
		String examName = "Assgn1";
		
		int totalMarks = 10; 
		CourseOffered offeredCourse = null;
		try {
			offeredCourse = new CourseOffered(offerID);
		} catch (Course.CourseDoesNotExistException e) {
			e.printStackTrace();
		} catch (CourseOffered.CourseOfferingDoesNotExistException e) {
			e.printStackTrace();
		}
//		
		@SuppressWarnings("unused")
		CourseExamStructure examStruct = new CourseExamStructure(offeredCourse,examName,totalMarks);
//		
		// To add a new exam
//		boolean examAdded = examStruct.addNewExam();
//		if(examAdded == true){
//			System.out.println("Exam Added");
//		} else {
//			System.out.println("Exam Not Added");
//		}
		
//		To test modifying the exam name
//		String newExamName = "Hw2";
//		boolean nameModified = examStruct.modifyExistingExamName(newExamName);
//		if(nameModified == true){
//			System.out.println("Exam Name Modified");
//		} else {
//			System.out.println("Exam Name Not Modified");
//		}
		
//		To test deleting an exam 
//		boolean examDeleted = examStruct.deleteExistingExam();
//		if(examDeleted == true){
//			System.out.println("Exam Deleted");
//		} else {
//			System.out.println("Exam Not Deleted");
//		}
		
//		To test modifying the exam mks
//		int newExamMks = 20;
//		boolean mksModified = examStruct.modifyExistingExamTotalMarks(newExamMks);
//		if(mksModified == true){
//			System.out.println("Exam Mks Modified");
//		} else {
//			System.out.println("Exam Mks Not Modified");
//		}
		
	}
}

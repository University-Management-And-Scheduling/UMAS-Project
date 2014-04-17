import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
	
	public static void createCourseExamStructureTable(CourseOffered offeredCourse){
		Course course = offeredCourse.getCourse();
		String courseName = course.getCourseName();
		int offerID= offeredCourse.getOfferID();
		int semID = offeredCourse.getSemesterID();
		
		String tableName = courseName + Integer.toString(offerID) + Integer.toString(semID) + "Structure"; 
		
//		@DBAnnotation (
//				variable = {"tableName"},  
//				table = "courseExamStructureTable", 
//				column = {"Username","Password"}, 
//				isSource = false)
		String SQLExamStructureCreate = "CREATE TABLE `?` (String `ExamName` varchar(20), int TotalMarks int(12),PRIMARY KEY (`ExamName`)) ;";
		
		try {
			Connection conn = Database.getConnection();
			try {
				if (conn != null) {
				 
					PreparedStatement statement = conn.prepareStatement(SQLExamStructureCreate);
					statement.setString(1, tableName);
					statement.executeUpdate();
				}	
			} catch (SQLException e) {
				System.out.println(e);
				Database.rollBackTransaction(conn);
			}

		} catch (Exception e) {
			System.out.println(e);
		}
	
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
		String SQLExamStructureCreate = "INSERT INTO ? VALUES(?,?) ;";
		
		try {
			Connection conn = Database.getConnection();
			try {
				if (conn != null) {
				 
					PreparedStatement statement = conn.prepareStatement(SQLExamStructureCreate);
					statement.setString(1, tableName);
					statement.setString(2, examName);
					statement.setInt(3, examTotal);
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
		String SQLExamStructureSelect = "Select ExamName FROM ? ;";
		
		try {
			Connection conn = Database.getConnection();
			try {
				if (conn != null) {
				 
					PreparedStatement statement = conn.prepareStatement(SQLExamStructureSelect);
					statement.setString(1, tableName);
					ResultSet rs = statement.executeQuery();
					while(rs.next()){
						String tableExamName = rs.getString("ExamName");
						if (tableExamName == examName){
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
		String SQLExamStructureUpdate = "UPDATE ? SET ExamName = ? WHERE ExamName = ? ;";
		
		try {
			Connection conn = Database.getConnection();
			try {
				if (conn != null) {
				 
					PreparedStatement statement = conn.prepareStatement(SQLExamStructureUpdate);
					statement.setString(1, tableName);
					statement.setString(2, newExamName);
					statement.setString(3, examName);
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
		String SQLExamStructureUpdate = "UPDATE ? SET TotalMarks = ? WHERE ExamName = ? ;";
		
		try {
			Connection conn = Database.getConnection();
			try {
				if (conn != null) {
				 
					PreparedStatement statement = conn.prepareStatement(SQLExamStructureUpdate);
					statement.setString(1, tableName);
					statement.setInt(2, newTotalMarks);
					statement.setString(3, examName);
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
		int examTotal = this.examTotal;
		
		boolean isExamPresent = this.isExamPresent(tableName,examName);
		if (isExamPresent == true){
						
		@DBAnnotation (
				variable = {"examName"},  
				table = "tableName", 
				column = {"ExamName"}, 
				isSource = false)
		String SQLExamStructureDelete = "DELETE FROM ? WHERE ExamName = ?  ;";
		
		try {
			Connection conn = Database.getConnection();
			try {
				if (conn != null) {
					PreparedStatement statement = conn.prepareStatement(SQLExamStructureDelete);
					statement.setString(1, tableName);
					statement.setString(2, examName);
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

}

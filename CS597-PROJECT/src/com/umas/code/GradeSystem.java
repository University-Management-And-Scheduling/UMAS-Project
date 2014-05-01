package com.umas.code;


//import java.lang.annotation.ElementType;
//import java.lang.annotation.Retention;
//import java.lang.annotation.RetentionPolicy;
//import java.lang.annotation.Target;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GradeSystem {

	String grade; // A+, A, A-, B+, B, B-, C
	int gradeLevel; // 1, 2, 3, 4, 5, 6, 7

//	@Target({ElementType.LOCAL_VARIABLE})
//	@Retention(RetentionPolicy.RUNTIME)
//	public @interface DBAnnotation {
//	 String[] variable () default "";
//	 String[] table () default "";
//	 String[] column () default "";
//	 boolean[] isSource () default false; 
//	}
//	
	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	
	public int getGradeLevel() {
		return gradeLevel;
	}

	public void setGradeLevel(int gradeLevel) {
		this.gradeLevel = gradeLevel;
	}

	// Constructor
	public GradeSystem(String grade) {
		super();
		this.grade = grade;
	}
	
	// Constructor
	public GradeSystem(String grade,int gradeLevel) {
		super();
		this.grade = grade;
		this.gradeLevel = gradeLevel;
	}
	
	// To insert a new typr of grade to the grade system 
	public boolean insertNewGrade(){
		boolean newGradeInserted = false;
		
		String grade = this.getGrade();
		int gradeLevel = this.getGradeLevel();
		
		boolean isGradePresent = this.isGradePresent();
		if(isGradePresent == true){
			System.out.println("This grade is already present");
		} else{
//			@DBAnnotation (
//					variable = {"grade","gradeLevel"},  
//					table = "gradingsystem", 
//					column = {"Grade","GradeLevel"}, 
//					isSource = false)
			
			String SQLGradeInsert = "INSERT INTO gradingsystem VALUES(?,?);";
			
			try {
				Connection conn = Database.getConnection();
				try {
					if (conn != null) {
						PreparedStatement statement = conn.prepareStatement(SQLGradeInsert);
						DBAnnotation.annoate("grade", "gradingsystem", "Grade", false);
						statement.setString(1, grade);
						DBAnnotation.annoate("gradeLevel", "gradingsystem", "GradeLevel", false);
						statement.setInt(2, gradeLevel);
						
						statement.executeUpdate();
						Database.commitTransaction(conn);
						newGradeInserted = true;
					}	
				} catch (SQLException e) {
					System.out.println(e);
					Database.rollBackTransaction(conn);
				}

			} catch (Exception e) {
				System.out.println(e);
			}
		}
		
				
		
		return newGradeInserted;
	}
	
	// This function checks whether a grade is present or not
	public boolean isGradePresent(){
		boolean isGradePresent = false;
		
		String grade = this.getGrade();
//		@DBAnnotation (
//				variable = "tableGrade",  
//				table = "gradingsystem", 
//				column = "Grade", 
//				isSource = true)
		
		String SQLGradeSelect = "SELECT Grade FROM gradingsystem;";
		
		try {
			Connection conn = Database.getConnection();
			try {
				if (conn != null) {
					PreparedStatement statement = conn.prepareStatement(SQLGradeSelect);
					ResultSet rs = statement.executeQuery();
					while (rs.next()) {
						DBAnnotation.annoate("tableGrade", "gradingsystem", "Grade", true);
						String tableGrade = rs.getString("Grade");
						if(tableGrade.equals(grade)){
							isGradePresent = true;
							break;
						}
					}
				}	
			} catch (SQLException e) {
				System.out.println(e);
			}

		} catch (Exception e) {
			System.out.println(e);
		}
		
		return isGradePresent;
	}
	
	// Checks whether a grade level is present or not
	public static boolean isGradeLevelPresent(int gradeLevel){
		boolean isGradeLevelPresent = false;
		
		//int gradeLevel = this.getGradeLevel();
//		@DBAnnotation (
//				variable = "tableGradeLevel",  
//				table = "gradingsystem", 
//				column = "GradeLevel", 
//				isSource = true)
		
		String SQLGradeSelect = "SELECT GradeLevel FROM gradingsystem;";
		
		try {
			Connection conn = Database.getConnection();
			try {
				if (conn != null) {
					PreparedStatement statement = conn.prepareStatement(SQLGradeSelect);
					ResultSet rs = statement.executeQuery();
					while (rs.next()) {
						DBAnnotation.annoate("tableGradeLevel", "gradingsystem", "GradeLevel", true);
						int tableGradeLevel = rs.getInt("GradeLevel");
						if(tableGradeLevel == gradeLevel){
							isGradeLevelPresent = true;
							break;
						}
					}
				}	
			} catch (SQLException e) {
				System.out.println(e);
			}

		} catch (Exception e) {
			System.out.println(e);
		}
		
		return isGradeLevelPresent;
	}

	// Deletes a grade from the gradesystem table
	public boolean deleteGrade(){
		boolean isGradeDeleted = false;
		
		String grade = this.getGrade();
		
		boolean isGradePresent = this.isGradePresent();
		if(isGradePresent == false){
			System.out.println("This grade is not present");
		} else{
//			@DBAnnotation (
//					variable = "grade",  
//					table = "gradingsystem", 
//					column = "Grade", 
//					isSource = false)
			
			String SQLGradedDelete = "DELETE FROM gradingsystem WHERE Grade = ?;";
			
			try {
				Connection conn = Database.getConnection();
				try {
					if (conn != null) {
						PreparedStatement statement = conn.prepareStatement(SQLGradedDelete);
						DBAnnotation.annoate("grade", "gradingsystem", "Grade", false);
						statement.setString(1, grade);
						statement.executeUpdate();
						Database.commitTransaction(conn);
						isGradeDeleted = true;
					}	
				} catch (SQLException e) {
					System.out.println(e);
					Database.rollBackTransaction(conn);
				}

			} catch (Exception e) {
				System.out.println(e);
			}
		}

		
		return isGradeDeleted;
	}
	
	// Modifies a grade in the gradesystem table
	public boolean modifyGrade(String newGrade){
		boolean isGradeModified = false;
		
		String grade = this.getGrade();
		
		boolean isGradePresent = this.isGradePresent();
		if(isGradePresent == false){
			System.out.println("This grade is not present");
		} else{
//			@DBAnnotation (
//					variable = "newGrade",  
//					table = "gradingsystem", 
//					column = "Grade", 
//					isSource = false)
			
			String SQLGradeUpdate = "UPDATE gradingsystem SET Grade = ? WHERE Grade = ? ;";
			
			try {
				Connection conn = Database.getConnection();
				try {
					if (conn != null) {
						PreparedStatement statement = conn.prepareStatement(SQLGradeUpdate);
						DBAnnotation.annoate("grade", "gradingsystem", "Grade", false);
						statement.setString(1, grade);
						DBAnnotation.annoate("newGrade", "gradingsystem", "Grade", false);
						statement.setString(2, newGrade);
						statement.executeUpdate();
						Database.commitTransaction(conn);
						isGradeModified = true;
					}	
				} catch (SQLException e) {
					System.out.println(e);
					Database.rollBackTransaction(conn);
				}

			} catch (Exception e) {
				System.out.println(e);
			}
		}

		return isGradeModified;
	}
	
	// Modifies a grade level in the gradesystem table
	public boolean modifyGradeLevel(int newGradeLevel){
		boolean isGradeLevelModified = false;
		
		String grade = this.getGrade();
		int gradeLevel = this.gradeLevel;
		boolean isOldGradeLevelPresent = isGradeLevelPresent(gradeLevel);
		boolean isNewGradeLevelPresent = isGradeLevelPresent(newGradeLevel);
		if(isOldGradeLevelPresent == false){
			System.out.println("This Old Grade Level is not present");
		} else{
			if(isNewGradeLevelPresent == true){
				System.out.println(" new Grade Level is not present");
			} else {
//				@DBAnnotation (
//						variable = "newGradeLevel",  
//						table = "gradingsystem", 
//						column = "GradeLevel", 
//						isSource = false)
				
				String SQLGradeUpdate = "UPDATE gradingsystem SET GradeLevel = ? WHERE GradeLevel = ? ;";
				
				try {
					Connection conn = Database.getConnection();
					try {
						if (conn != null) {
							PreparedStatement statement = conn.prepareStatement(SQLGradeUpdate);
							
							statement.setInt(1, gradeLevel);
							statement.setInt(1, newGradeLevel);
							statement.executeUpdate();
							Database.commitTransaction(conn);
							isGradeLevelModified = true;
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
			
		return isGradeLevelModified;
	}

	// Get the grade level when given a grade
	public GradeSystem getGradeLevelForGrade(){
		String grade = this.getGrade();
		
		boolean isGradePresent = this.isGradePresent(); 
		if(isGradePresent == false){
			System.out.println("Grade " + grade + " is not present");
		} else {
//			@DBAnnotation (
//					variable = "grade",  
//					table = "gradingsystem", 
//					column = "Grade", 
//					isSource = true)
			
			String SQLGradeSelect = "SELECT GradeLevel FROM gradingsystem WHERE Grade = ?;";
			
			try {
				Connection conn = Database.getConnection();
				try {
					if (conn != null) {
						PreparedStatement statement = conn.prepareStatement(SQLGradeSelect);
						DBAnnotation.annoate("grade", "gradingsystem", "Grade", false);
						statement.setString(1, grade);
						ResultSet rs = statement.executeQuery();
						while (rs.next()) {
							DBAnnotation.annoate("gradeLevel", "gradingsystem", "GradeLevel", true);
							int gradeLevel = rs.getInt("GradeLevel");
							this.setGradeLevel(gradeLevel);
						}
					}	
				} catch (SQLException e) {
					System.out.println(e);
				}

			} catch (Exception e) {
				System.out.println(e);
			}

		}
		
		return this;
	}

	// Get the grade when given a grade level 
	public static GradeSystem getGradeForGradeLevel(int gradeLevel){
		GradeSystem gradeObject = null;
		boolean isGradeLevelPresent = isGradeLevelPresent(gradeLevel); 
		if(isGradeLevelPresent == false){
			System.out.println("Grade Level " + gradeLevel + " is not present");
		} else {
//			@DBAnnotation (
//					variable = "gradeLevel",  
//					table = "gradingsystem", 
//					column = "GradeLevel", 
//					isSource = true)
			
			String SQLGradeSelect = "SELECT Grade FROM gradingsystem WHERE GradeLevel = ?;";
			
			try {
				Connection conn = Database.getConnection();
				try {
					if (conn != null) {
						PreparedStatement statement = conn.prepareStatement(SQLGradeSelect);
						DBAnnotation.annoate("gradeLevel", "gradingsystem", "GradeLevel", false);
						statement.setInt(1, gradeLevel);
						ResultSet rs = statement.executeQuery();
						while (rs.next()) {
							DBAnnotation.annoate("grade", "gradingsystem", "Grade", true);
							String grade = rs.getString("Grade");
							gradeObject = new GradeSystem(grade,gradeLevel);
						}
					}	
				} catch (SQLException e) {
					System.out.println(e);
				}

			} catch (Exception e) {
				System.out.println(e);
			}

		}
		
		return gradeObject;
	}

	// Get the maximum grade level in the university grading system
	public static int getMaxGradeLevel(){
		int maxGradeLevel = 0;
		
//		@DBAnnotation (
//				variable = "gradeLevel",  
//				table = "gradingsystem", 
//				column = "GradeLevel", 
//				isSource = true)
		
		String SQLGradeSelect = "SELECT max(GradeLevel) FROM gradingsystem;";
		
		try {
			Connection conn = Database.getConnection();
			try {
				if (conn != null) {
					PreparedStatement statement = conn.prepareStatement(SQLGradeSelect);
					ResultSet rs = statement.executeQuery();
					while (rs.next()) {
						DBAnnotation.annoate("maxGradeLevel", "gradingsystem", "max(GradeLevel)", true);
						maxGradeLevel = rs.getInt(1);
					}
				}	
			} catch (SQLException e) {
				System.out.println(e);
			}

		} catch (Exception e) {
			System.out.println(e);
		}

		return maxGradeLevel;
	}
	
	public static void main(String[] args){
		
	}

}

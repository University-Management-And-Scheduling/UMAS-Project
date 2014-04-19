import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GradeSystem {

	String grade;
	int gradeLevel;

	@Target({ElementType.LOCAL_VARIABLE})
	@Retention(RetentionPolicy.RUNTIME)
	public @interface DBAnnotation {
	 String[] variable () default "";
	 String[] table () default "";
	 String[] column () default "";
	 boolean[] isSource () default false; 
	}
	
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

	public GradeSystem(String grade) {
		super();
		this.grade = grade;
	}
	
	public GradeSystem(String grade,int gradeLevel) {
		super();
		this.grade = grade;
		this.gradeLevel = gradeLevel;
	}
	
	public boolean insertNewGrade(){
		boolean newGradeInserted = false;
		
		String grade = this.getGrade();
		int gradeLevel = this.getGradeLevel();
		
		boolean isGradePresent = this.isGradePresent();
		if(isGradePresent == true){
			System.out.println("This grade is already present");
		} else{
			@DBAnnotation (
					variable = {"grade","gradeLevel"},  
					table = "gradingsystem", 
					column = {"Grade","GradeLevel"}, 
					isSource = false)
			
			String SQLGradeInsert = "INSERT INTO gradingsystem VALUES(?,?);";
			
			try {
				Connection conn = Database.getConnection();
				try {
					if (conn != null) {
						PreparedStatement statement = conn.prepareStatement(SQLGradeInsert);
						statement.setString(1, grade);
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
	
	public boolean isGradePresent(){
		boolean isGradePresent = false;
		
		String grade = this.getGrade();
		@DBAnnotation (
				variable = "tableGrade",  
				table = "gradingsystem", 
				column = "Grade", 
				isSource = true)
		
		String SQLGradeSelect = "SELECT Grade FROM gradingsystem;";
		
		try {
			Connection conn = Database.getConnection();
			try {
				if (conn != null) {
					PreparedStatement statement = conn.prepareStatement(SQLGradeSelect);
					ResultSet rs = statement.executeQuery();
					while (rs.next()) {
						String tableGrade = rs.getString("Grade");
						if(tableGrade == grade){
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
	
	public boolean isGradeLevelPresent(){
		boolean isGradeLevelPresent = false;
		
		int gradeLevel = this.getGradeLevel();
		@DBAnnotation (
				variable = "tableGradeLevel",  
				table = "gradingsystem", 
				column = "GradeLevel", 
				isSource = true)
		
		String SQLGradeSelect = "SELECT GradeLevel FROM gradingsystem;";
		
		try {
			Connection conn = Database.getConnection();
			try {
				if (conn != null) {
					PreparedStatement statement = conn.prepareStatement(SQLGradeSelect);
					ResultSet rs = statement.executeQuery();
					while (rs.next()) {
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

	public boolean deleteGrade(){
		boolean isGradeDeleted = false;
		
		String grade = this.getGrade();
		
		boolean isGradePresent = this.isGradePresent();
		if(isGradePresent == false){
			System.out.println("This grade is not present");
		} else{
			@DBAnnotation (
					variable = "grade",  
					table = "gradingsystem", 
					column = "Grade", 
					isSource = false)
			
			String SQLGradedDelete = "DELETE FROM gradingsystem WHERE Grade = ?;";
			
			try {
				Connection conn = Database.getConnection();
				try {
					if (conn != null) {
						PreparedStatement statement = conn.prepareStatement(SQLGradedDelete);
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
	
	public boolean modifyGrade(String newGrade){
		boolean isGradeModified = false;
		
		String grade = this.getGrade();
		
		boolean isGradePresent = this.isGradePresent();
		if(isGradePresent == false){
			System.out.println("This grade is not present");
		} else{
			@DBAnnotation (
					variable = "newGrade",  
					table = "gradingsystem", 
					column = "Grade", 
					isSource = false)
			
			String SQLGradeUpdate = "UPDATE gradingsystem SET Grade = ? WHERE Grade = ? ;";
			
			try {
				Connection conn = Database.getConnection();
				try {
					if (conn != null) {
						PreparedStatement statement = conn.prepareStatement(SQLGradeUpdate);
						statement.setString(1, grade);
						statement.setString(1, newGrade);
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
	
	public boolean modifyGradeLevel(int newGradeLevel){
		boolean isGradeLevelModified = false;
		
		String grade = this.getGrade();
		
		boolean isGradeLevelPresent = this.isGradeLevelPresent();
		if(isGradeLevelPresent == false){
			System.out.println("This gradeLevel is not present");
		} else{
			@DBAnnotation (
					variable = "newGradeLevel",  
					table = "gradingsystem", 
					column = "GradeLevel", 
					isSource = false)
			
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

		return isGradeLevelModified;
	}

	public GradeSystem getGradeLevelForGrade(){
		String grade = this.getGrade();
		
		boolean isGradePresent = this.isGradePresent(); 
		if(isGradePresent == false){
			System.out.println("Grade " + grade + " is not present");
		} else {
			@DBAnnotation (
					variable = "grade",  
					table = "gradingsystem", 
					column = "Grade", 
					isSource = true)
			
			String SQLGradeSelect = "SELECT GradeLevel FROM gradingsystem WHERE Grade = ?;";
			
			try {
				Connection conn = Database.getConnection();
				try {
					if (conn != null) {
						PreparedStatement statement = conn.prepareStatement(SQLGradeSelect);
						statement.setString(1, grade);
						ResultSet rs = statement.executeQuery();
						while (rs.next()) {
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

	public GradeSystem getGradeForGradeLevel(int gradeLevel){
		GradeSystem gradeObject = null;
		boolean isGradeLevelPresent = this.isGradeLevelPresent(); 
		if(isGradeLevelPresent == false){
			System.out.println("Grade Level " + gradeLevel + " is not present");
		} else {
			@DBAnnotation (
					variable = "gradeLevel",  
					table = "gradingsystem", 
					column = "GradeLevel", 
					isSource = true)
			
			String SQLGradeSelect = "SELECT Grade FROM gradingsystem WHERE GradeLevel = ?;";
			
			try {
				Connection conn = Database.getConnection();
				try {
					if (conn != null) {
						PreparedStatement statement = conn.prepareStatement(SQLGradeSelect);
						statement.setInt(1, gradeLevel);
						ResultSet rs = statement.executeQuery();
						while (rs.next()) {
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
}

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Course {
	Department department;
	int courseID;
	String courseName;
	
	/**
	 * @param department
	 * @param courseID
	 * @param courseName
	 */
	public Course(int courseID, Department department, String courseName) {
		super();
		this.courseID = courseID;
		this.department = department;
		this.courseName = courseName;
	}
	
	//retrieve course using ID
	public Course(int cID) throws CourseDoesNotExistException {
		
		System.out.println("Searching for course with ID:"+cID);
		
		try{
			Connection conn = Database.getConnection();
			
			try{
				if(conn != null){
					String SQLSelect= "Select CourseID, CourseName, DepartmentID"
							+ " FROM university.courses"
							+ " WHERE CourseID= ?";
					PreparedStatement statement = conn.prepareStatement(SQLSelect);
					statement.setInt(1, cID);
					ResultSet rs =  statement.executeQuery();
					
					if(rs.first()){
						//The object with the CourseName already exists
						//Just update the current object with new values
						//Storing the current values to execute update
						System.out.println("Retreiving the Course");
						int courseID = rs.getInt(1);
						String courseName = rs.getString(2);
						this.courseID = courseID;
						this.courseName = courseName;
						Department courseDept = new Department(rs.getInt(3));
						this.department = courseDept;
					}
					
					else{
						throw new CourseDoesNotExistException();
					}
				}
			}
			
			catch(SQLException e){
				System.out.println("Error updating/adding");
				System.out.println(e.getMessage());
			} catch (Department.DepartmentDoesNotExistException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			finally{
				//Database.closeConnection(conn);
			}
			
		}
		
		finally{
		}
	}

	/**
	 * @return the department
	 */
	public Department getDepartment() {
		return department;
	}
	
	/**
	 * @param department the department to set
	 */
	public void setDepartment(Department department) {
		this.department = department;
	}
	
	/**
	 * @return the courseID
	 */
	public int getCourseID() {
		return courseID;
	}
		
	/**
	 * @return the courseName
	 */
	public String getCourseName() {
		return courseName;
	}
	
	/**
	 * @param courseName the courseName to set
	 */
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	
	//Add a course to the database
	public static void addCourse(String courseName, Department department) throws CourseAlreadyExistsException{
		if(department == null || courseName == null )
			return;
		
		//Check if the course with the same name exists
		System.out.println("Searching for course with name:"+courseName);
		
		try{
			Connection conn = Database.getConnection();
			
			try{
				if(conn != null){
					
					String SQLSelect= "Select CourseID, CourseName, DepartmentID"
							+ " FROM university.courses"
							+ " WHERE CourseName= ?";
					PreparedStatement statement = conn.prepareStatement(SQLSelect);
					statement.setString(1, courseName);
					ResultSet rs =  statement.executeQuery();
					
					if(rs.first()){
						System.out.println("Course Already exists");
						throw new CourseAlreadyExistsException();
					}
					
					else{
						//add the data to the course table
						System.out.println("Inserting new course");
						String SQLInsert= "Insert into university.courses (CourseName, DepartmentID) Values (?,?);";
						statement.close();
						statement = conn.prepareStatement(SQLInsert);
						statement.setString(1, courseName);
						statement.setInt(2, department.getDepartmentID());
						statement.execute();
						Database.commitTransaction(conn);
					}
				}
			}
			
			catch(SQLException e){
				System.out.println("Error updating/adding");
				System.out.println(e.getMessage());
			}
			
			finally{
				//Database.closeConnection(conn);
			}
			
		}
		
		finally{
		}
	}

	//Remove a course from the course table, also trigger removing the courses from courses-offered table related to this course
	public static void removeCourse(int courseID){
		//check if the course to be removed exists
		//Remove the courses-offered related to this course
		try{
			Connection conn = Database.getConnection();
			
			try{
				if(conn != null){
					
					String SQLSelect= "Select CourseID, CourseName, DepartmentID"
							+ " FROM university.courses"
							+ " WHERE CourseID= ?";
					PreparedStatement statement = conn.prepareStatement(SQLSelect,ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
					statement.setInt(1, courseID);
					ResultSet rs =  statement.executeQuery();
					
					if(rs.first()){
						System.out.println("Deleting course:"+rs.getString(2));
						rs.deleteRow();						
					}
					
					else{
						//Course not in table
						System.out.println("Course does not exist");
						
					}
				}
			}
			
			catch(SQLException e){
				System.out.println("Error updating/adding");
				System.out.println(e.getMessage());
			}
			
			finally{
				//Database.closeConnection(conn);
			}
			
		}
		
		finally{
		}
		
	}
	
	//CourseDoesnotExist Exception
	static class CourseDoesNotExistException extends Exception{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private String message = null;
		 
	    public  CourseDoesNotExistException() {
	        super();
	        this.message = "Course does not exist";
	    }
	    
	    public  CourseDoesNotExistException(String message) {
	        super();
	        this.message = message;
	    }
	 
	    @Override
	    public String toString() {
	        return message;
	    }
	 
	    @Override
	    public String getMessage() {
	        return message;
	    }
	}

	//CourseDoesnotExist Exception
	static class CourseAlreadyExistsException extends Exception{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private String message = null;
		 
	    public  CourseAlreadyExistsException() {
	        super();
	        this.message = "Course already exists exist";
	    }
	    
	    public  CourseAlreadyExistsException(String message) {
	        super();
	        this.message = message;
	    }
	 
	    @Override
	    public String toString() {
	        return message;
	    }
	 
	    @Override
	    public String getMessage() {
	        return message;
	    }
	}
}

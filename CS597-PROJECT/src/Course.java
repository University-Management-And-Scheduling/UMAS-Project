import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;


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

	public Course(String courseName) throws CourseDoesNotExistException{
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
						//The object with the CourseName already exists
						System.out.println("Retreiving the Course");
						int courseID = rs.getInt("CourseID");
						String cName = rs.getString("CourseName");
						this.courseID = courseID;
						this.courseName = cName;
						Department courseDept = new Department(rs.getInt("DepartmentID"));
						this.department = courseDept;
					}
					
					else{
						throw new CourseDoesNotExistException();
					}
				}
			}
			
			catch(SQLException e){
				System.out.println("Error getting");
				System.out.println(e.getMessage());
			} catch (Department.DepartmentDoesNotExistException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
	public boolean addCourse(String courseName, Department department) throws CourseAlreadyExistsException{
		boolean isAdded = false;
		
		if(department == null || courseName.length()<1 || courseName == null)
			return isAdded;
		
		//Check if the course with the same name exists
		System.out.println("Searching for course with name:"+courseName);
		
		if(isExists(courseName, department))
			throw new CourseAlreadyExistsException();
		
		try{
			Connection conn = Database.getConnection();
			
			try{
				if(conn != null){
					//add the data to the course table
					System.out.println("Inserting new course");
					String SQLInsert= "Insert into university.courses (CourseName, DepartmentID) Values (?,?);";
					PreparedStatement statement;
					statement = conn.prepareStatement(SQLInsert);
					statement.setString(1, courseName);
					statement.setInt(2, department.getDepartmentID());
					statement.execute();
					Database.commitTransaction(conn);
					isAdded = true;
				}
			}
			
			catch(SQLException e){
				System.out.println("Error adding");
				System.out.println(e.getMessage());
			}
						
		}
		
		finally{
		}
		
		return isAdded; 
	}

	private boolean isExists(String courseName, Department department){
		boolean isExists = false;
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
						isExists = true;
					}
				}
			}
			
			catch(SQLException e){
				System.out.println("Error retrieving");
				System.out.println(e.getMessage());
			}			
		}
		
		finally{
		}
		
		return isExists;
	}
	
	@SuppressWarnings("unused")
	private static void removeCourse(int courseID){
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
	
	public static LinkedHashMap<Integer,Course> getAllCourses(){
		LinkedHashMap<Integer,Course> courses = new LinkedHashMap<Integer,Course>();
		try{
			Connection conn = Database.getConnection();
			
			try{
				if(conn != null){
					
					String SQLSelect= "Select *"
							+ " FROM university.courses";
					PreparedStatement statement = conn.prepareStatement(SQLSelect);
					ResultSet rs =  statement.executeQuery();
					
					while(rs.next()){
						Course c = new Course(rs.getInt("CourseID"));
						courses.put(c.getCourseID(), c);
					}
					
				}
			}
			
			catch(SQLException e){
				System.out.println("Error getting courses");
				System.out.println(e.getMessage());
			} catch (CourseDoesNotExistException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		finally{
		}
		
		return courses;
	}
	
	public static ArrayList<Course> getCoursesOfDepartment(Department d){
		int deptID = d.getDepartmentID();
		ArrayList<Course> deptCourses = new ArrayList<Course>();
		try{
			Connection conn = Database.getConnection();
			
			try{
				if(conn != null){
					
					String SQLSelect= "Select *"
							+ " FROM university.courses "
							+ "WHERE DepartmentID= ?";
					PreparedStatement statement = conn.prepareStatement(SQLSelect);
					statement.setInt(1,deptID);
					ResultSet rs =  statement.executeQuery();
					
					while(rs.next()){
						Course c = new Course(rs.getInt("CourseID"));
						deptCourses.add(c);
					}
					
				}
			}
			
			catch(SQLException e){
				System.out.println("Error getting courses");
				System.out.println(e.getMessage());
			} catch (CourseDoesNotExistException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		finally{
		}
		
		return deptCourses;
	
		
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

	public String toString(){
		String toReturn = "";
		toReturn+="\nCourse Name:"+this.getCourseName();
		toReturn+=this.getDepartment().toString();
		return toReturn;
		
	}
}

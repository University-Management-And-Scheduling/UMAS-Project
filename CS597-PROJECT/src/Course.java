
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
	
	/*Basic constructor when all the initialization values are known*/
	public Course(int courseID, Department department, String courseName) {
		super();
		this.courseID = courseID;
		this.department = department;
		this.courseName = courseName;
	}
	

	/*This constructor takes the course id as an input and retrieves and initializes all the
	 * course fields for the object.
	 * Throws an CourseDoesNotExistException if the course id passed is not existing in the database.
	 */
	
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
						/*The course with the specified id is found.
						 * Initialize the object instance variables with the values retrieved from the database
						 */
						//System.out.println("Retreiving the Course");
						int courseID = rs.getInt(1);
						String courseName = rs.getString(2);
						this.courseID = courseID;
						this.courseName = courseName;
						Department courseDept = new Department(rs.getInt(3));
						this.department = courseDept;
					}
					
					else{
						/*
						 * Throw an exception as the course with the id does not exist
						 */
						throw new CourseDoesNotExistException();
					}
				}
			}
			
			catch(SQLException e){
				System.out.println("Error retrieving course");
				System.out.println(e.getMessage());
			} catch (Department.DepartmentDoesNotExistException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		
		finally{
		}
	}

	/*
	 * Constructor to initialize a course object using the course name
	 */
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
						/*
						 * The object with the CourseName exists
						 * Initialize the instance variables 
						 */
						System.out.println("Retreiving the Course");
						int courseID = rs.getInt("CourseID");
						String cName = rs.getString("CourseName");
						this.courseID = courseID;
						this.courseName = cName;
						Department courseDept = new Department(rs.getInt("DepartmentID"));
						this.department = courseDept;
					}
					
					else{
						/*
						 * Throw the exception as course with the course name does not exist
						 */
						throw new CourseDoesNotExistException();
					}
				}
			}
			
			catch(SQLException e){
				System.out.println("Error getting course");
				System.out.println(e.getMessage());
			} catch (Department.DepartmentDoesNotExistException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		finally{
		}
	}

	
	/*
	 * Getters and Setters************************************************
	 */
	
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
	
	

	/*
	 * Add a course to the database using a course name and a department object
	 */
	public static boolean addCourse(String courseName, Department department) throws CourseAlreadyExistsException{
		boolean isAdded = false;
		
		if(department == null || courseName.length()<1 || courseName == null)
			return isAdded;
		
		/*
		 * Check if the course with the same name exists
		 * If yes, then throw Course already exists exception
		 */
		if(isExists(courseName, department))
			throw new CourseAlreadyExistsException();
		
		try{
			Connection conn = Database.getConnection();
			
			try{
				if(conn != null){
					/*
					 * Add the course data to the course table
					 */
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
				System.out.println("Error adding course to the table, check your sql query");
				System.out.println(e.getMessage());
			}
						
		}
		
		finally{
		}
		
		/*
		 * returns if the course was successfully added to the database table
		 */
		return isAdded; 
	}

	/*
	 * Update the course to the specified department and name
	 */
	public boolean updateCourse(String courseName, Department department){
		boolean isUpdated = false;
		/*
		 * Null checks
		 */
		if(department == null || courseName.length()<1 || courseName == null)
			return isUpdated;
		
		/*
		 * Checks if the course exists
		 * Doesn't update if the target is not present
		 */
		if(!isExists(courseName, department))
			return isUpdated;
		
		try{
			Connection conn = Database.getConnection();
			
			try{
				if(conn != null){
					/*
					 * update the data in the course table
					 */
					System.out.println("Updating course");
					String SQLupdate= "UPDATE university.courses "
							+ "SET CourseName= ?, DepartmentID= ? "
							+ "WHERE CourseID= ?";
					PreparedStatement statement;
					statement = conn.prepareStatement(SQLupdate, ResultSet.CONCUR_UPDATABLE);
					statement.setString(1, courseName);
					statement.setInt(2, department.getDepartmentID());
					statement.setInt(3, this.getCourseID());
					statement.executeUpdate();
					this.courseName = courseName;
					this.department = department;
					Database.commitTransaction(conn);
					isUpdated = true;
				}
			}
			
			catch(SQLException e){
				System.out.println("Error updating");
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
						
		}
		
		finally{
		}
		
		return isUpdated;

	}
	
	/*
	 * Checks if the specified course exists in the specified department
	 */
	private static boolean isExists(String courseName, Department department){
		boolean isExists = false;
		int deptID = department.getDepartmentID();
		try{
			Connection conn = Database.getConnection();
			
			try{
				if(conn != null){
					
					String SQLSelect= "Select CourseID, CourseName, DepartmentID"
							+ " FROM university.courses"
							+ " WHERE CourseName= ? and DepartmentID=?";
					PreparedStatement statement = conn.prepareStatement(SQLSelect);
					statement.setString(1, courseName);
					statement.setInt(2, deptID);
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
		/*
		 * check if the course to be removed exists
		 * Remove the courses-offered related to this course
		 */

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
	
	/*
	 * Returns a Map of all the courses with their course id as keys for the map
	 */
	public static LinkedHashMap<Integer,Course> getAllCourses(){
		LinkedHashMap<Integer,Course> courses = new LinkedHashMap<Integer,Course>();
		try{
			Connection conn = Database.getConnection();
			
			try{
				if(conn != null){
					/*
					 * Retrieve all the courses
					 */
					String SQLSelect= "Select *"
							+ " FROM university.courses";
					PreparedStatement statement = conn.prepareStatement(SQLSelect);
					ResultSet rs =  statement.executeQuery();
					
					/*
					 * Add all the course and the ids to the hashmap
					 */
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
	
	/*
	 * Returns a Map of all the course offerings for the course offered during the course of time
	 */
	public LinkedHashMap<Integer, CourseOffered> getCurrentOfferings(){
		LinkedHashMap<Integer, CourseOffered> courseOfferings = new LinkedHashMap<Integer, CourseOffered>();
		
		try{
			Connection conn = Database.getConnection();
			
			try{
				if(conn != null){
					/*
					 * Retrieve all the courses
					 */
					String SQLSelect= "Select *"
							+ " FROM university.coursesoffered natural join university.courseschedule"
							+ " WHERE coursesoffered.CourseID= ?";
					PreparedStatement statement = conn.prepareStatement(SQLSelect);
					statement.setInt(1, this.courseID);
					ResultSet rs =  statement.executeQuery();
					
					/*
					 * Add all the retrieved courses to hashMap
					 */
					while(rs.next()){
						System.out.println("Retreiving the Course");
						int offerID = rs.getInt("OfferID");
						CourseOffered co = new CourseOffered(offerID);
						courseOfferings.put(offerID, co);						
					}
				}
			}
			
			catch(SQLException e){
				System.out.println("Error getting courses");
				System.out.println(e.getMessage());
			} catch (CourseDoesNotExistException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (CourseOffered.CourseOfferingDoesNotExistException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		finally{
		}
		
		return courseOfferings;
	}
	
	/*
	 * Returns a List of all the courses in the specified department
	 */
 	public static ArrayList<Course> getCoursesOfDepartment(Department d){
		int deptID = d.getDepartmentID();
		ArrayList<Course> deptCourses = new ArrayList<Course>();
		try{
			Connection conn = Database.getConnection();
			
			try{
				if(conn != null){
					/*
					 * Select all the courses of the specified department
					 */
					String SQLSelect= "Select *"
							+ " FROM university.courses "
							+ "WHERE DepartmentID= ?";
					PreparedStatement statement = conn.prepareStatement(SQLSelect);
					statement.setInt(1,deptID);
					ResultSet rs =  statement.executeQuery();
					
					/*
					 * Add all the retrieved courses to the ArrayList
					 */
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
	
 	
 	/*
 	 * CourseDoesnotExist Exception is thrown when an object is tried to be initialized which does not exist
 	 */
	static class CourseDoesNotExistException extends Exception{
		
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

	
	/*
	 * CourseAlreadyExist Exception when a course which already exists is tried to be added
	 */
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

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		String toReturn = "";
		toReturn+="\nCourse Name:"+this.getCourseName();
		toReturn+=this.getDepartment().toString();
		return toReturn;
		
	}

}

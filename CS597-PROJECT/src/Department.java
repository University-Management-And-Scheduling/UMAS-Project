
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Department {
	private String departmentName;
	private int departmentID;
	/**
	 * @return the departmentName
	 */
	public String getDepartmentName() {
		return departmentName;
	}
	/**
	 * @param departmentName the departmentName to set
	 */
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	/**
	 * @return the departmentID
	 */
	public int getDepartmentID() {
		return departmentID;
	}
		
	
	/*
	 * Retrieve department from the database using the department ID;
	 */
	public Department(int departmentID) throws DepartmentDoesNotExistException{
		
		try{
			Connection conn = Database.getConnection();
			
			try{
				if(conn != null){
					/*
					 * Try to look for the department in the department table
					 */
					String SQLSelect= "Select DepartmentID, DepartmentName"
							+ " FROM university.department"
							+ " WHERE DepartmentID= ?";
					PreparedStatement statement = conn.prepareStatement(SQLSelect);
					statement.setInt(1, departmentID);
					ResultSet rs =  statement.executeQuery();
					
					/*
					 * If he department is found in the database
					 * Initialize the values of the object
					 */
					if(rs.first()){
						/*
						 * The object with the DepartmentName already exists
						 * Just initialize the current object with new values
						 */
						int dID = rs.getInt(1);
						String dName = rs.getString(2);
						this.departmentID = dID;
						this.departmentName = dName;
					}
					
					else{
						/*
						 * Throw department does not exists exception
						 */
						throw new DepartmentDoesNotExistException();
					}
				}
			}
			
			catch(SQLException e){
				System.out.println("Error updating/adding");
				System.out.println(e.getMessage());
			}
						
		}
		
		finally{
		}
	}

	/*
	 * Retrieve department from the database using the department name, 
	 * Initialize the object if it does, else values remain null
	 */
	public Department(String departmentName) throws DepartmentDoesNotExistException{
			
			System.out.println("Searching for department with Name:"+departmentName);
			
			try{
				Connection conn = Database.getConnection();
				
				try{
					if(conn != null){
						/*
						 * Try to retrieve the department
						 */
						String SQLSelect= "Select DepartmentID, DepartmentName"
								+ " FROM university.department"
								+ " WHERE DepartmentName= ?";
						PreparedStatement statement = conn.prepareStatement(SQLSelect);
						statement.setString(1, departmentName);
						ResultSet rs =  statement.executeQuery();
						
						/*
						 * If the department is found, initialize the object with the retireved values
						 */
						if(rs.first()){
							/*
							 * The object with the DepartmentName already exist
							 * Just initialize the current object with new values
							 */
							int dID = rs.getInt(1);
							String dName = rs.getString(2);
							this.departmentID = dID;
							this.departmentName = dName;
						}
						
						else{
							/*
							 * Throw exception
							 */
							System.out.println("----DEPARTMENT DOES NOT EXIST----");
							throw new DepartmentDoesNotExistException();
						}
					}
				}
				
				catch(SQLException e){
					System.out.println("Error updating/adding");
					System.out.println(e.getMessage());
				}
								
			}
			
			finally{
			}
		}
		
	
	/*
	 * Add a new department to the database
	 */
	public static void addNewDepartment(String departmentName) throws DepartmentAlreadyExistsException{
		try{
			Connection conn = Database.getConnection();
			
			try{
				if(conn != null){
					/*
					 * Check to see if teh department with the same name exists already
					 */
					String SQLSelect= "Select DepartmentName"
							+ " FROM university.department"
							+ " WHERE DepartmentName= ?";
					PreparedStatement statement = conn.prepareStatement(SQLSelect);
					statement.setString(1, departmentName);
					ResultSet rs =  statement.executeQuery();
					
					/*
					 * If the department exists, throw exception
					 */
					if(rs.first()){
						//The object with the DepartmentName already exists
						throw new DepartmentAlreadyExistsException();
					}
					
					else{
						/*
						 * Add the object data to the department table
						 */
						String SQLInsert= "Insert into university.department (DepartmentName) Values (?);";
						statement.close();
						statement = conn.prepareStatement(SQLInsert);
						statement.setString(1, departmentName);
						statement.execute();
						Database.commitTransaction(conn);
					}
				}
			}
			
			catch(SQLException e){
				System.out.println("Error adding department");
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
	 * Delete specified department
	 */
	@SuppressWarnings("unused")
	private static void deleteDepartment(String departmentName) throws DepartmentDoesNotExistException{
		boolean isDeleteSuccessfull = false;
		try{
			Connection conn = Database.getConnection();
			try{
				if(conn != null){
					String SQLSelect= "Select DepartmentID, DepartmentName"
							+ " FROM university.department"
							+ " WHERE DepartmentName= ?";
					PreparedStatement statement = conn.prepareStatement(SQLSelect, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
					statement.setString(1, departmentName);
					ResultSet rs =  statement.executeQuery();
					
					
					if(rs.first()){
						//The object with the DepartmentName already exists
						System.out.println("Deleting the department:"+departmentName);
						/*Remove all the courses related to this department*/
						int departmentID = rs.getInt(1);
						String courseSelect= "Select CourseID, CourseName, DepartmentID"
								+ " FROM university.courses"
								+ " WHERE DepartmentID= ?";
						PreparedStatement statementForCourse = conn.prepareStatement(courseSelect, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
						statementForCourse.setInt(1, departmentID);
						ResultSet courseSetToDelete = statementForCourse.executeQuery();
						while(courseSetToDelete.next()){
							System.out.println("Deleting course:"+courseSetToDelete.getString(2));
							Course c = new Course(courseSetToDelete.getInt("CourseID"));
						}
						
						rs.deleteRow();	
						isDeleteSuccessfull = true;
					}
					
					else{
						isDeleteSuccessfull = false;
					}
					
				}
				
				if(!isDeleteSuccessfull)
					throw new DepartmentDoesNotExistException();

			}
			
			catch(SQLException e){
				System.out.println("Error updating");
				System.out.println(e.getMessage());
			} catch (Course.CourseDoesNotExistException e) {
				e.printStackTrace();
			}
			
		}
		
		finally{
			
		}
	}
	
	/*
	 * Update the existing department using the object values to update the database
	 * It will update the department with the values of the instance variables
	 */
	public boolean updateDepartment() throws DepartmentDoesNotExistException, DepartmentAlreadyExistsException{
		
		/*
		 * Null checks
		 */
		if(this.getDepartmentName() == null || this.getDepartmentID() == 0)
			throw new DepartmentDoesNotExistException("Un-initialized object");
		
		/*
		 * Check if the department name is acceptable and does not conflict with other department names
		 */
		if(isNameExisting(this.departmentName)){
			throw new DepartmentAlreadyExistsException();
		}
		
		try{
			Connection conn = Database.getConnection();
			try{
				if(conn != null){
					/*
					 * Retrieve the department row
					 */
					String SQLSelect= "Select DepartmentID, DepartmentName"
							+ " FROM university.department"
							+ " WHERE DepartmentID= ?";
					PreparedStatement statement = conn.prepareStatement(SQLSelect, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
					statement.setInt(1, this.getDepartmentID());
					ResultSet rs =  statement.executeQuery();
					
					/*
					 * Update the department row with new values and commit the update
					 */
					if(rs.first()){
						/*
						 * Update with new values from the object instance variables
						 */
						System.out.println("Updating the department with new values");
						rs.updateInt(1, this.getDepartmentID());
						rs.updateString(2, this.getDepartmentName());
						rs.updateRow();	
						Database.commitTransaction(conn);
						return true;
					}
					
				}
			}
			
			catch(SQLException e){
				System.out.println("Error updating");
				System.out.println(e.getMessage());
				return false;
			}
						
		}
		
		finally{
		}
		return false;
	}
	
	
	public static boolean isNameExisting(String name){
		try{
			Connection conn = Database.getConnection();
			
			try{
				if(conn != null){
					/*
					 * Try to retrieve the department
					 */
					String SQLSelect= "Select DepartmentName"
							+ " FROM university.department"
							+ " WHERE DepartmentName= ?";
					PreparedStatement statement = conn.prepareStatement(SQLSelect);
					statement.setString(1, name);
					ResultSet rs =  statement.executeQuery();
					
					/*
					 * If the department is found, return true
					 */
					if(rs.first()){
						return true;
					}
					
					else{
						return false;
					}
				}
			}
			
			catch(SQLException e){
				System.out.println("Error updating/adding");
				System.out.println(e.getMessage());
				return false;
			}
							
		}
		
		finally{
		}
		
		return false;
	}
	
	@Override
	public String toString(){
		String toReturn = " ";
		toReturn+="\nDepartment name:"+this.getDepartmentName();
		toReturn+="\nDepartment ID:"+this.getDepartmentName();
		return toReturn;
	}
	

	/*
	 * Method to retrieve a list of all the departments in a list
	 */
	public static ArrayList<Department> getAllDepartments(){
		ArrayList<Department> departments = new ArrayList<Department>();
		try{
			Connection conn = Database.getConnection();
			
			try{
				if(conn != null){
					/*
					 * retrieve all the departments
					 */
					String SQLSelect= "Select *"
							+ " FROM university.department";
					PreparedStatement statement = conn.prepareStatement(SQLSelect);
					ResultSet rs =  statement.executeQuery();
					/*
					 * Add all the retrieved department to the List
					 */
					while(rs.next()){
						try {
							Department d = new Department(rs.getInt("DepartmentID"));
							departments.add(d);
						} catch (DepartmentDoesNotExistException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
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
		
		return departments;
	}
	
	/*
	 * DepartmentDoesnotExist Exception
	 */
	static class DepartmentDoesNotExistException extends Exception{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private String message = null;
		 
	    public  DepartmentDoesNotExistException() {
	        super();
	        this.message = "Department does not exist";
	    }
	    
	    public  DepartmentDoesNotExistException(String message) {
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
	 * DepartmentDoesnotExist Exception
	 */
	static class DepartmentAlreadyExistsException extends Exception{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private String message = null;
		 
	    public  DepartmentAlreadyExistsException() {
	        super();
	        this.message = "Department already exists";
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
	 * Method to retrieve all the courses in the department
	 */
	public ArrayList<Course> getDepartmentCourses(){
		ArrayList<Course> deptCourses = new ArrayList<Course>();
		
		try{
			Connection conn = Database.getConnection();
			
			try{
				if(conn != null){
					/*
					 * Retrieve all courses by filtering using the department id
					 */
					String SQLSelect= "Select CourseID"
							+ " FROM university.department natural join university.courses"
							+ " WHERE DepartmentID= ?";
					PreparedStatement statement = conn.prepareStatement(SQLSelect);
					statement.setInt(1, this.getDepartmentID());
					ResultSet rs =  statement.executeQuery();
					/*
					 * Add all the retrieved courses to the List
					 */
					while(rs.next()){
						Course c = new Course(rs.getInt("CourseID"));
						deptCourses.add(c);
					}
				}
			}
			
			catch(SQLException e){
				System.out.println("Error updating/adding");
				System.out.println(e.getMessage());
			} catch (Course.CourseDoesNotExistException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			finally{
				//Database.closeConnection(conn);
			}
			
		}
		
		finally{
		}
		
		return deptCourses;
	}
	
	/*
	 * Retrieve all the courses in the department that are currently offered
	 */
	public ArrayList<CourseOffered> getDepartmentCourseOffered(){
		
		ArrayList<CourseOffered> deptCourses = new ArrayList<CourseOffered>();
		int currentSemester = CourseOffered.getCurrentSemesterID();
		try{
			Connection conn = Database.getConnection();
			
			try{
				if(conn != null){
					/*
					 * Filter all the course offerings using the specified department
					 */
					String SQLSelect= "Select CourseID, OfferID"
							+ " FROM university.coursesoffered natural join university.courses"
							+ " WHERE DepartmentID= ? and SemesterID = ?";
					PreparedStatement statement = conn.prepareStatement(SQLSelect);
					statement.setInt(1, this.getDepartmentID());
					statement.setInt(2, currentSemester);
					ResultSet rs =  statement.executeQuery();
					
					/*
					 * Add all the retrieved CoursesOffered to the list
					 */
					while(rs.next()){
						CourseOffered co = new CourseOffered(rs.getInt("OfferID"));
						deptCourses.add(co);
					}
				}
			}
			
			catch(SQLException e){
				System.out.println("Error retrieving");
				System.out.println(e.getMessage());
			} catch (Course.CourseDoesNotExistException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (CourseOffered.CourseOfferingDoesNotExistException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		finally{
		}
		
		return deptCourses;
	}
	
	public static void main(String[] args){
		
	}

}

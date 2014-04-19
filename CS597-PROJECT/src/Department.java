import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

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
		
	
	//Retrieve department from the database using the department ID;
	public Department(int departmentID) throws DepartmentDoesNotExistException{
		
		System.out.println("Searching for department with ID:"+departmentID);
		
		try{
			Connection conn = Database.getConnection();
			
			try{
				if(conn != null){
					String SQLSelect= "Select DepartmentID, DepartmentName"
							+ " FROM university.department"
							+ " WHERE DepartmentID= ?";
					PreparedStatement statement = conn.prepareStatement(SQLSelect);
					statement.setInt(1, departmentID);
					ResultSet rs =  statement.executeQuery();
					
					if(rs.first()){
						//The object with the DepartmentName already exists
						//Just update the current object with new values
						
						//Storing the current values to execute update
						System.out.println("Retreiving the department");
						int dID = rs.getInt(1);
						String dName = rs.getString(2);
						this.departmentID = dID;
						this.departmentName = dName;
					}
					
					else{
						//add the object data to the courseOffered table
						System.out.println("----EXCEPTION:DEPARTMENT DOES NOT EXIST----");
						throw new DepartmentDoesNotExistException();
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

	//Retrieve department from the database using the department name, initialize the object if it does, else values remain null
	public Department(String departmentName) throws DepartmentDoesNotExistException{
			
			System.out.println("Searching for department with Name:"+departmentName);
			
			try{
				Connection conn = Database.getConnection();
				
				try{
					if(conn != null){
						String SQLSelect= "Select DepartmentID, DepartmentName"
								+ " FROM university.department"
								+ " WHERE DepartmentName= ?";
						PreparedStatement statement = conn.prepareStatement(SQLSelect);
						statement.setString(1, departmentName);
						ResultSet rs =  statement.executeQuery();
						
						if(rs.first()){
							//The object with the DepartmentName already exists
							//Just update the current object with new values
							
							//Storing the current values to execute update
							System.out.println("Retreiving the department");
							int dID = rs.getInt(1);
							String dName = rs.getString(2);
							this.departmentID = dID;
							this.departmentName = dName;
						}
						
						else{
							//add the object data to the courseOffered table
							System.out.println("----DEPARTMENT DOES NOT EXIST----");
							throw new DepartmentDoesNotExistException();
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
		
	//Add a new department, static method, can be accessed via class name only
	public static void addNewDepartment(String departmentName) throws DepartmentAlreadyExistsException{
		try{
			Connection conn = Database.getConnection();
			
			try{
				if(conn != null){
					String SQLSelect= "Select DepartmentName"
							+ " FROM university.department"
							+ " WHERE DepartmentName= ?";
					PreparedStatement statement = conn.prepareStatement(SQLSelect);
					statement.setString(1, departmentName);
					ResultSet rs =  statement.executeQuery();
					
					if(rs.first()){
						//The object with the DepartmentName already exists
						System.out.println("Department already exists, use a different name");
						throw new DepartmentAlreadyExistsException();
					}
					
					else{
						//add the object data to the courseOffered table
						System.out.println("Inserting new department");
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
				System.out.println("Error adding");
				System.out.println(e.getMessage());
			}
			
			finally{
				//Database.closeConnection(conn);
			}
			
		}
		
		finally{
		}

	}
	
	//Delete specified department
	public static void deleteDepartment(String departmentName) throws DepartmentDoesNotExistException{
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
							Course.removeCourse(courseSetToDelete.getInt(1));
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
			}
			
			finally{
				//Database.closeConnection(conn);
			}
			
		}
		
		finally{
			
		}
	}
	
	//Update the existing department uses the object values to update the database
	public void updateDepartment() throws DepartmentDoesNotExistException{
		
		if(this.getDepartmentName() == null || this.getDepartmentID() == 0)
			throw new DepartmentDoesNotExistException("Un-initialized object");
		
		try{
			Connection conn = Database.getConnection();
			try{
				if(conn != null){
					String SQLSelect= "Select DepartmentID, DepartmentName"
							+ " FROM university.department"
							+ " WHERE DepartmentID= ?";
					PreparedStatement statement = conn.prepareStatement(SQLSelect, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
					statement.setInt(1, this.getDepartmentID());
					ResultSet rs =  statement.executeQuery();
					
					if(rs.first()){
						//The object with the DepartmentName already exists
						System.out.println("Updating the department with new values");
						rs.updateInt(1, this.getDepartmentID());
						rs.updateString(2, this.getDepartmentName());
						rs.updateRow();	
						Database.commitTransaction(conn);
					}
					
				}
			}
			
			catch(SQLException e){
				System.out.println("Error updating");
				System.out.println(e.getMessage());
			}
			
			finally{
				//Database.closeConnection(conn);
			}
			
		}
		
		finally{
		}
	}
	
	@Override
	public String toString(){
		String toReturn = " ";
		toReturn+="\nDepartment name:"+this.getDepartmentName();
		toReturn+="\nDepartment ID:"+this.getDepartmentName();
		return toReturn;
	}
	

	public static ArrayList<Department> getAllDepartments(){
		ArrayList<Department> departments = new ArrayList<Department>();
		try{
			Connection conn = Database.getConnection();
			
			try{
				if(conn != null){
					String SQLSelect= "Select *"
							+ " FROM university.department";
					PreparedStatement statement = conn.prepareStatement(SQLSelect);
					ResultSet rs =  statement.executeQuery();
					
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
				System.out.println("Error updating/adding");
				System.out.println(e.getMessage());
			}			
		}
		
		finally{
		}
		
		return departments;
	}
	
	//DepartmentDoesnotExist Exception
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

	//DepartmentDoesnotExist Exception
	private static class DepartmentAlreadyExistsException extends Exception{
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
	
	//to be implemented
	public ArrayList<Course> getDepartmentCourses(){
		return null;
	}
	
	public ArrayList<CourseOffered> getDepartmentCourseOffered(){
		return null;
	}
	
	public static void main(String[] args){
		
	}

}

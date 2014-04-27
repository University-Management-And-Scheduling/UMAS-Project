
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;



/**
 * @author Akshay
 * 
 */

/*************** TA.JAVA CLASS WAS WRITTEN BY AKSHAY THIRKATEH ********************************/

public class TA extends Student {
	


	@Target({ElementType.LOCAL_VARIABLE})
	@Retention(RetentionPolicy.RUNTIME)
	public @interface DBAnnotation {
	 String[] variable () default "";
	 String[] table () default "";
	 String[] column () default "";
	 boolean[] isSource () default false; 
	}

	public TA(int UIN) throws PersonDoesNotExistException {
		super(UIN);
		
		try{
			Connection conn = Database.getConnection();
			String SQLStudentTASelect="";
			try{
			
				if(conn != null){
					
					SQLStudentTASelect = "Select * From university.teachingassistant where TaUIN=?;";
				}
				
				PreparedStatement stmtForSelect = conn.prepareStatement(SQLStudentTASelect);
				stmtForSelect.setInt(1, UIN);
				
				ResultSet rs =  stmtForSelect.executeQuery();
					
					if(rs.first())
					{
						
				        int retrievedTAOfferID = rs.getInt("OfferID");
				        String retrievedTAOfficeHours = rs.getString("TaOfficeHours");
				        String retrievedTAOfficeAddress = rs.getString("TaOfficeLocation");



					}
					
					else
					{
						
						System.out.println("UIN does not exist in the TA table");
						throw new PersonDoesNotExistException();

					}
					
				
			
		
	}
			
			catch(SQLException e){
				System.out.print("SQL exception in student const");
				System.out.println(e);
				e.printStackTrace();	
			}
		}
		
		catch(PersonDoesNotExistException e){
			System.out.println(e);
			e.printStackTrace();
			throw new PersonDoesNotExistException();
			
		}
		
		finally{
			
			//System.out.println("retrieved");
		}
		// TODO Auto-generated constructor stub
	}

	/*
//	 * addTA function takes in the inputs of the UIN of the TA and the
//	 * offer ID of the course
//	 * 
//	 * Before adding into the TA table it needs to be added as an employee
//	 * 
//	 * Calls the addtoEmployee function which returns a boolean value
//	 * 
//	 * if the UIN is added then it adds into the TA table 
//	 * 
//	 * if returns true then it is successfully added
//	 * 
//	 * else the function returns false
//	 */

	public static boolean updateTaOfficeAddress(int UIN, int offerID, String newOfficeAddress){
		
		boolean isUpdated=false; 
		
		if(newOfficeAddress==null){
			return false;
			
		}
		
		if(newOfficeAddress.length()==0){
			return false;
		}
		
		
		boolean check=addTAtoTAtableCheck(UIN, offerID);
		if(!check){
			return false;
		}
		
		try{
			Connection conn = Database.getConnection();
			
			try{
				
				CourseOffered c=new CourseOffered(offerID);
						
						System.out.println("Updating data in the database");
						String SQLPeopleInsert= "UPDATE teachingassistant SET TaOfficeLocation= ? where TaUIN=? and OfferID=? ;";
						PreparedStatement stmt = conn.prepareStatement(SQLPeopleInsert);
						stmt.setString(1, newOfficeAddress);
						stmt.setInt(2, UIN);
						stmt.setInt(3, offerID);
						System.out.println(stmt);
						int i = stmt.executeUpdate();
						System.out.println(i);
						System.out.println("Updated");
						isUpdated=true;
						
						Database.commitTransaction(conn);
						
					
					
			}
			
			catch(SQLException e){
				System.out.println("Error adding/updating to database");
				System.out.println(e);	
			}
			
		}
		
		catch (Course.CourseDoesNotExistException
				| CourseOffered.CourseOfferingDoesNotExistException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		catch(Exception e){
			System.out.println("Connection failed");
			System.out.println(e);
			
		}
	
		
		finally{
			
			//System.out.println("retrieved");
		}
		
	return isUpdated;
		
	}
	
	public static boolean updateTaOfficeHours(int UIN, int offerID, String newOfficeHours){
		
		boolean isUpdated=false; 
		
		if(newOfficeHours==null){
			return false;
			
		}
		
		if(newOfficeHours.length()==0){
			return false;
		}
		
		
		boolean check=addTAtoTAtableCheck(UIN, offerID);
		if(!check){
			return false;
		}
		
		
		try{
			Connection conn = Database.getConnection();
			
			try{
				
				    CourseOffered c=new CourseOffered(offerID);
						
						System.out.println("Updating data in the database");
						String SQLPeopleInsert= "UPDATE teachingassistant SET TaOfficeHours= ? where TaUIN=? and OfferID=? ;";
						PreparedStatement stmt = conn.prepareStatement(SQLPeopleInsert);
						stmt.setString(1, newOfficeHours);
						stmt.setInt(2, UIN);
						stmt.setInt(3, offerID);
						System.out.println(stmt);
						int i = stmt.executeUpdate();
						System.out.println(i);
						System.out.println("Updated");
						isUpdated=true;
						
						Database.commitTransaction(conn);
						
					
					
			}
			
			catch(SQLException e){
				System.out.println("Error adding/updating to database");
				System.out.println(e);	
			}
			
		}
		
		catch(Exception e){
			System.out.println("Connection failed");
			System.out.println(e);
			
		}
		
		finally{
			
			//System.out.println("retrieved");
		}
		
	return isUpdated;
		
	}
	
	public static String getTAOfficeAddress(int UIN, int offerID){
		
		
		try{
			Connection conn = Database.getConnection();
			
			try{
				
				    CourseOffered c=new CourseOffered(offerID);
						
						System.out.println("selecting TA s office location");
						String SQLTASelect= "select * from teachingassistant where TaUIN=? and OfferID=? ;";
						PreparedStatement stmt = conn.prepareStatement(SQLTASelect);
						stmt.setInt(1, UIN);
						stmt.setInt(2, offerID);
						System.out.println(stmt);
						ResultSet rs=stmt.executeQuery();
						System.out.println("Retreived");
						
						if(rs.first()){
							
							String getTAOfficeLocation=rs.getString("TaOfficeLocation");
							return getTAOfficeLocation;
						}
					
			}
			
			catch(SQLException e){
				System.out.println("Error adding/updating to database");
				System.out.println(e);	
			}
			
		}
		
		catch(Exception e){
			System.out.println("Connection failed");
			System.out.println(e);
			
		}
		
		finally{
			
			//System.out.println("retrieved");
		}
		
	return null;
		
	}
	
	public static String getTAOfficeHours(int UIN, int offerID){
		
		
		try{
			Connection conn = Database.getConnection();
			
			try{
				
				CourseOffered c=new CourseOffered(offerID);
						
						System.out.println("selecting TA s office hours");
						String SQLTASelect= "select * from teachingassistant where TaUIN=? and OfferID=? ;";
						PreparedStatement stmt = conn.prepareStatement(SQLTASelect);
						stmt.setInt(1, UIN);
						stmt.setInt(2, offerID);
						System.out.println(stmt);
						ResultSet rs=stmt.executeQuery();
						System.out.println("Retreived");
						
						if(rs.first()){
							
							String getTAOfficeHours=rs.getString("TaOfficeHours");
							return getTAOfficeHours;
						}
					
			}
			
			catch(SQLException e){
				System.out.println("Error adding/updating to database");
				System.out.println(e);	
			}
			
		}
		
		catch(Exception e){
			System.out.println("Connection failed");
			System.out.println(e);
			
		}
		
		finally{
			
			//System.out.println("retrieved");
		}
		
	return null;
		
	}
	
	
	
	/*This functions checks if the passed offer ID is a valid and current
	 * 
	 * if true it then checks if there exists an employee with the same UIN
	 * 
	 * if an employee exists it returns true
	 * 
	 * if the addToTATableCheck function does not return true
	 * 
	 * then it adds it to the Ta table with the TaUIN and Offer ID
	 * 
	 * */	
	public static boolean addTAtoTAtable(int UIN, int offerID) throws AlreadyExistsInTAException{

				
		boolean isAdded = false;

		try {
			Connection conn = Database.getConnection();

				try{
					Student stud=new Student(UIN);
					CourseOffered c=new CourseOffered(offerID);
				}
				catch (Student.PersonDoesNotExistException e) {
					System.out.println("Not a student");
					e.printStackTrace();
					System.out.println(e);
					return false;

				} catch (Course.CourseDoesNotExistException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (CourseOffered.CourseOfferingDoesNotExistException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				boolean ifExists = addTAtoTAtableCheck(UIN, offerID);

				if (ifExists) {
					throw new AlreadyExistsInTAException();
				}

				else {

					System.out.println("Adding new data into the database");
					
					@DBAnnotation (
							variable = {"UIN","offerID"}, table = "teachingassistant", 
							column = {"TaUIN","OfferID"}, 
							isSource = false)
					
					String SQLPeopleInsert = "Insert into teachingassistant (TaUIN, OfferID) Values (?,?);";
					PreparedStatement stmt = conn
							.prepareStatement(SQLPeopleInsert);
					stmt.setInt(1, UIN);
					stmt.setInt(2, offerID);
					int i = stmt.executeUpdate();
					System.out.println(i);
					System.out.println("Inserted");
					isAdded = true;
					updateStudentToTA(UIN);

				}

			}

			catch (SQLException e) {
				System.out.println("Error adding/updating to database");
				e.printStackTrace();
				System.out.println(e);
			}

		

		catch (AlreadyExistsInTAException e) {
			System.out.println("Error");
			e.printStackTrace();
			System.out.println(e);
			throw new AlreadyExistsInTAException();

		}

		finally {

			// System.out.println("retrieved");
		}

		return isAdded;

	}

	
	/*This functions checks if the passed offer ID is a valid and current
	 * 
	 * if true it then checks if there exists an TA with the same UIN
	 * 
	 * if an TA exists it returns true
	 * 
	 * else it returns false
	 * 
	 * */	
	public static boolean addTAtoTAtableCheck(int UIN, int offerID) {

		boolean isExisting = false;

		try {
			Connection conn = Database.getConnection();
			String SQLPeopleSelect = "";

			try {

				SQLPeopleSelect = "Select TaUIN From teachingassistant where OfferID=? and TaUIN= ?;";
				PreparedStatement stmt = conn.prepareStatement(SQLPeopleSelect);
				stmt.setInt(1, offerID);
				stmt.setInt(2, UIN);
				ResultSet rs = stmt.executeQuery();

				if (rs.first()) {
					System.out.println(UIN+ "already exists as a TA");
					return true;
				}

			}

			catch (SQLException e) {
				System.out.println("Error adding/updating to database");
				e.printStackTrace();
				System.out.println(e);
			}

			finally {
				// System.out.println("retrieved");
				// Database.closeConnection(conn);
			}
		}

		catch (Exception e) {
			System.out.println("Connection failed");
			e.printStackTrace();
			System.out.println(e);

		}

		finally {

			// System.out.println("retrieved");
		}

		return isExisting;

	}

	/*
	 * getAllAdmin function returns an arraylist of the admins from the
	 * database.
	 * 
	 * It retrieves the data from the people table.
	 */
	public static ArrayList<TA> getAllTAs() {


		ArrayList<TA> getAllTAs = new ArrayList<TA>();

		try {
			Connection conn = Database.getConnection();

			try {
				if (conn != null) {

					// Retrieve all the professors from one department
					String ProfessorSelect = "Select *"
							+ " FROM university.people" + " WHERE PositionID=4";
					PreparedStatement statement = conn
							.prepareStatement(ProfessorSelect);
					ResultSet rs = statement.executeQuery();

					while (rs.next()) {

						int retreivedTAUIN = rs.getInt("UIN");
						// System.out.println(retreivedProfUserNames);
						TA teachingAssistant;
						try {
							teachingAssistant = new TA(retreivedTAUIN);
							getAllTAs.add(teachingAssistant);
							System.out.println(teachingAssistant.getUserName());
						} catch (PersonDoesNotExistException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}

				}

			}

			catch (SQLException e) {
				System.out.println("Error fetching all the professors");
				System.out.println(e.getMessage());
				e.printStackTrace();

			}

			finally {
				// Database.commitTransaction(conn);
			}

		}

		finally {
		}

		return getAllTAs;

	}

	/*
	 * updateTAUserName function takes in the inputs of the new user name of
	 * the TA
	 * calls the boolean function update username from the people class with the new passed new username and the initialized objects
	 * username.
	 * 
	 * if updated returns true
	 * 
	 * 
	 * else the function returns false
	 */
	public boolean updateTAUserName(String userName) {

		if(userName==null){
			return false;
			
		}
		
		if(userName.length()==0){
			return false;
		}
		
		
		boolean isUpdated = false;

		try {
			Connection conn = Database.getConnection();

			try {

				boolean ifAddedInLogin = People.updateUserNameIntoLoginTable(
						userName, this.getUserName());
				if (ifAddedInLogin)
					isUpdated = true;

			}

			catch (Exception e) {
				System.out.println("Error adding/updating to database");
				e.printStackTrace();
				System.out.println(e);
			}

		}

		catch (Exception e) {
			System.out.println("Connection failed");
			e.printStackTrace();
			System.out.println(e);

		}

		finally {

			// System.out.println("retrieved");
		}

		return isUpdated;

	}
	
	/*
	 * updateTAName function takes in the inputs of the new name of the TA
	 * 
	 * calls the boolean function update name from the people class with the
	 * passed new name and the initialized objects UIN.
	 * 
	 * if updated returns true
	 * 
	 * 
	 * else the function returns false
	 */

	public boolean updateTAName(String name) {

		boolean isUpdated = false;
		
		if(name==null){
			return false;
			
		}
		
		if(name.length()==0){
			return false;
		}
		

		try {
			Connection conn = Database.getConnection();

			try {

				boolean ifAddedInPeople = People.updateNameIntoPeopleTable(
						name, this.getUIN());
				if (ifAddedInPeople)
					isUpdated = true;

			}

			catch (Exception e) {
				System.out.println("Error adding/updating to database");
				e.printStackTrace();
				System.out.println(e);
			}

		}

		catch (Exception e) {
			System.out.println("Connection failed");
			e.printStackTrace();
			System.out.println(e);

		}

		finally {

			// System.out.println("retrieved");
		}

		return isUpdated;

	}
	
	/*
	 * updateTADept function takes in the inputs of the newdept of the TA
	 * * calls the boolean function update dept from the people class with the
	 * passed new name and the initialized objects UIN.
	 * 
	 * if updated returns true
	 * 
	 * 
	 * else the function returns false
	 */

	public boolean updateTADept(int deptID) {
		
		try {
			Department d=new Department(deptID);
		} catch (Department.DepartmentDoesNotExistException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		boolean isUpdated = false;

		try {
			Connection conn = Database.getConnection();

			try {

				boolean ifUpdatedInPeople = People.updateDeptIntoPeopleTable(
						deptID, this.getUIN());
				if (ifUpdatedInPeople)
					isUpdated = true;

			}

			catch (Exception e) {
				System.out.println("Error adding/updating to database");
				e.printStackTrace();
				System.out.println(e);
			}

		}

		catch (Exception e) {
			System.out.println("Connection failed");
			e.printStackTrace();
			System.out.println(e);

		}

		finally {

			// System.out.println("retrieved");
		}

		return isUpdated;

	}

	public static boolean updateStudentToTA(int UIN){
		
		boolean isUpdated=false;
		
		try{
			Connection conn = Database.getConnection();
			
			try{
				
				    
						
						System.out.println("Updating data in the database");
						String SQLDeptUpdate= "UPDATE people SET PositionID= ? where UIN=?;";
						PreparedStatement stmt = conn.prepareStatement(SQLDeptUpdate);
						stmt.setInt(1, 4);
						stmt.setInt(2, UIN);
						System.out.println(stmt);
						int i = stmt.executeUpdate();
						System.out.println(i);
						System.out.println("Inserted");
						isUpdated=true;
						Database.commitTransaction(conn);
						
					
					
			}
			
			catch(SQLException e){
				System.out.println("Error adding/updating to database");
				System.out.println(e);	
				e.printStackTrace();
			}
			
			finally{
				//System.out.println("retrieved");
				//Database.closeConnection(conn);
			}
		}
		
		catch(Exception e){
			System.out.println("Connection failed");
			System.out.println(e);
			e.printStackTrace();
			
		}
		
		finally{
			
			//System.out.println("retrieved");
		}
		
	return isUpdated;
	}
		

	public static boolean updateTAtoStudent(int UIN){
		
		boolean isUpdated=false;
		
		
		try{
			Connection conn = Database.getConnection();
			
			try{
				System.out.println("Updating data in the database");
				String SQLDeptUpdate= "UPDATE people SET PositionID= ? where UIN=?;";
				PreparedStatement stmt = conn.prepareStatement(SQLDeptUpdate);
				stmt.setInt(1, 3);
				stmt.setInt(2, UIN);
				System.out.println(stmt);
				int i = stmt.executeUpdate();
				System.out.println(i);
				System.out.println("Inserted");
				isUpdated=true;
				if(isUpdated)
					Database.commitTransaction(conn);
				
				else
					Database.rollBackTransaction(conn);
				
					
					
			}
			
			catch(SQLException e){
				System.out.println("Error adding/updating to database");
				System.out.println(e);	
				e.printStackTrace();
			}
			
		}
		
		catch(Exception e){
			System.out.println("Connection failed");
			System.out.println(e);
			e.printStackTrace();
			
		}
		
		finally{
			
			//System.out.println("retrieved");
		}
		
	return isUpdated;
	}

	
	public static class AlreadyExistsInTAException extends Exception {
		private static final long serialVersionUID = 1L;
		private String message = null;

		public AlreadyExistsInTAException() {
			super();
			this.message = "TA is already existing for this course offering";
		}

		public AlreadyExistsInTAException(String message) {
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
	 * All TA functions add, update, retrieve are specified in the this class
	 * 
	 * local main class is used for testing functions and specific executions
	 */
	public static void main(String[] args) {

		

	}

}

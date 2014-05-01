package com.umas.code;


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
	

	//TA class constructor takes in the UIN and then sets the values to the UIn specific 
	public TA(int UIN) throws PersonDoesNotExistException {
		super(UIN);//calls the super class
		
		try{
			Connection conn = Database.getConnection();//get the connection
			String SQLStudentTASelect="";
			try{
			
				if(conn != null){
					
					SQLStudentTASelect = "Select * From teachingassistant where TaUIN=?;";//write the query
				}
				
				PreparedStatement stmtForSelect = conn.prepareStatement(SQLStudentTASelect);
				stmtForSelect.setInt(1, UIN);//set the UIN
				
				ResultSet rs =  stmtForSelect.executeQuery();//execute the query
					
					if(rs.first())
					{
						//if the resultset exists then get the values
						
						DBAnnotation.annoate("retrievedTAOfferID", "teachingassistant", "OfferID", true);
				        int retrievedTAOfferID = rs.getInt("OfferID");
				        
				        DBAnnotation.annoate("retrievedTAOfficeHours", "teachingassistant", "TaOfficeHours", true);
				        String retrievedTAOfficeHours = rs.getString("TaOfficeHours");
				        
				        DBAnnotation.annoate("retrievedTAOfficeAddress", "teachingassistant", "TaOfficeLocation", true);
				        String retrievedTAOfficeAddress = rs.getString("TaOfficeLocation");



					}
					
					else
					{
						//else the UIN does not exist
						System.out.println("UIN does not exist in the TA table");
						throw new PersonDoesNotExistException();

					}
					
				
			
		
	}
			//catch the SQL exception
			catch(SQLException e){
				System.out.print("SQL exception in student const");
				System.out.println(e);
				e.printStackTrace();	
			}
		}
			
		//catch the person deos not exist exception
		catch(PersonDoesNotExistException e){
			System.out.println(e);
			e.printStackTrace();
			throw new PersonDoesNotExistException();
			
		}
		
		//finally block
		finally{
			
			//System.out.println("retrieved");
		}
		// TODO Auto-generated constructor stub
	}

	/*
	 * updateTA office sddress function takes in the inputs of the UIN of the TA and the
	 * offer ID of the course and the new office address
	 * 
	 * Calls the addtoEmployee function which returns a boolean value 
	 * 
	 * it updated it returns true
	 * 
	 * else the function returns false
	 */

	public static boolean updateTaOfficeAddress(int UIN, int offerID, String newOfficeAddress){
		
		boolean isUpdated=false; 
		
		//check for null
		if(newOfficeAddress==null){
			return false;
			
		}
		//check the length
		if(newOfficeAddress.length()==0){
			return false;
		}
		
		
		boolean check=addTAtoTAtableCheck(UIN, offerID);//check if TA exists
		if(!check){
			return false;
		}
		
		try{
			Connection conn = Database.getConnection();//get the connection
			
			try{
				
				CourseOffered c=new CourseOffered(offerID);
						
						System.out.println("Updating data in the database");
						String SQLPeopleInsert= "UPDATE teachingassistant SET TaOfficeLocation= ? where TaUIN=? and OfferID=? ;";
						PreparedStatement stmt = conn.prepareStatement(SQLPeopleInsert);
						stmt.setString(1, newOfficeAddress);//set the values
						stmt.setInt(2, UIN);//set the values
						stmt.setInt(3, offerID);//set the values
						System.out.println(stmt);
						int i = stmt.executeUpdate();//execute the query
						
						DBAnnotation.annoate("newOfficeAddress", "teachingassistant", "TaOfficeLocation", false);
						DBAnnotation.annoate("UIN", "teachingassistant", "TaUIN", false);
						DBAnnotation.annoate("offerID", "teachingassistant", "OfferID", false);
						
						System.out.println(i);
						System.out.println("Updated");
						isUpdated=true;
						
						Database.commitTransaction(conn);
						
					
					
			}
			//catch block for SQL exception
			catch(SQLException e){
				System.out.println("Error adding/updating to database");
				System.out.println(e);	
			}
			
		}
		//outer catch block for other exceptions
		catch (Course.CourseDoesNotExistException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		catch (CourseOffered.CourseOfferingDoesNotExistException e1) {
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
	
	
	/*
	 * updateTA office hours function takes in the inputs of the UIN of the TA and the
	 * offer ID of the course and the new office hours
	 * 
	 * Calls the addtoEmployee function which returns a boolean value 
	 * 
	 * it updated it returns true
	 * 
	 * else the function returns false
	 */
	public static boolean updateTaOfficeHours(int UIN, int offerID, String newOfficeHours){
		
		boolean isUpdated=false; //set to false
		
		//check for null
		if(newOfficeHours==null){
			return false;
			
		}
		//check the length
		if(newOfficeHours.length()==0){
			return false;
		}
		
		
		boolean check=addTAtoTAtableCheck(UIN, offerID);//check if the Uin exists
		if(!check){
			return false;
		}
		
		
		try{
			Connection conn = Database.getConnection();//get the connection
			
			try{
				
				    CourseOffered c=new CourseOffered(offerID);
						
						System.out.println("Updating data in the database");
						String SQLPeopleInsert= "UPDATE teachingassistant SET TaOfficeHours= ? where TaUIN=? and OfferID=? ;";
						PreparedStatement stmt = conn.prepareStatement(SQLPeopleInsert);
						stmt.setString(1, newOfficeHours);//set the values
						stmt.setInt(2, UIN);//set the values
						stmt.setInt(3, offerID);//set the values
						System.out.println(stmt);
						int i = stmt.executeUpdate();//execute the query
						
						DBAnnotation.annoate("newOfficeHours", "teachingassistant", "TaOfficeHours", false);
						DBAnnotation.annoate("UIN", "teachingassistant", "TaUIN", false);
						DBAnnotation.annoate("offerID", "teachingassistant", "OfferID", false);
						
						System.out.println(i);
						System.out.println("Updated");
						isUpdated=true;
						
						Database.commitTransaction(conn);
						
					
					
			}
			//catch block for SQL query
			catch(SQLException e){
				System.out.println("Error adding/updating to database");
				System.out.println(e);	
			}
			
		}
		//catch block 
		catch(Exception e){
			System.out.println("Connection failed");
			System.out.println(e);
			
		}
		//finally block
		finally{
			
			//System.out.println("retrieved");
		}
		
	return isUpdated;
		
	}
	
	/*this function is to retrieve the office address of the Ta
	 * 
	 * passed is the offer ID and the UIN
	 * 
	 * it returns a string */
	public static String getTAOfficeAddress(int UIN, int offerID){
		
		
		try{
			Connection conn = Database.getConnection();//get the connection
			
			try{
				
				    CourseOffered c=new CourseOffered(offerID);
						
						System.out.println("selecting TA s office location");
						String SQLTASelect= "select * from teachingassistant where TaUIN=? and OfferID=? ;";//write the query
						PreparedStatement stmt = conn.prepareStatement(SQLTASelect);
						stmt.setInt(1, UIN);//set the values
						stmt.setInt(2, offerID);//set the values
						System.out.println(stmt);//set the values
						ResultSet rs=stmt.executeQuery();//execute the queries
						System.out.println("Retreived");
						
						if(rs.first()){
							
							DBAnnotation.annoate("getTAOfficeLocation", "teachingassistant", "TaOfficeLocation", true);
							String getTAOfficeLocation=rs.getString("TaOfficeLocation");//get the values
							return getTAOfficeLocation;
						}
					
			}
			//catch block
			catch(SQLException e){
				System.out.println("Error adding/updating to database");
				System.out.println(e);	
			}
			
		}
		//catch block 
		catch(Exception e){
			System.out.println("Connection failed");
			System.out.println(e);
			
		}
		//finally block 
		finally{
			
			//System.out.println("retrieved");
		}
		
	return null;
		
	}
	
	
	/*this function is to retrieve the office hours of the Ta
	 * 
	 * passed is the offer ID and the UIN
	 * 
	 * it returns a string */
	public static String getTAOfficeHours(int UIN, int offerID){
		
		
		try{
			Connection conn = Database.getConnection();//get the connection
			
			try{
				
				CourseOffered c=new CourseOffered(offerID);
						
						System.out.println("selecting TA s office hours");
						String SQLTASelect= "select * from teachingassistant where TaUIN=? and OfferID=? ;";//write the query
						PreparedStatement stmt = conn.prepareStatement(SQLTASelect);
						stmt.setInt(1, UIN);//set the values
						stmt.setInt(2, offerID);//set the values
						System.out.println(stmt);
						ResultSet rs=stmt.executeQuery();
						System.out.println("Retreived");
						
						if(rs.first()){
							//if the resultset exists
							DBAnnotation.annoate("getTAOfficeHours", "teachingassistant", "TaOfficeHours", true);
							String getTAOfficeHours=rs.getString("TaOfficeHours");
							return getTAOfficeHours;
						}
					
			}
			//catch the SQL exception
			catch(SQLException e){
				System.out.println("Error adding/updating to database");
				System.out.println(e);	
			}
			
		}
		//catch block 
		catch(Exception e){
			System.out.println("Connection failed");
			System.out.println(e);
			
		}
		//finally block
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

				
		boolean isAdded = false;//set it to false

		try {
			Connection conn = Database.getConnection();//get the connection

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

				boolean ifExists = addTAtoTAtableCheck(UIN, offerID);//check if it exists

				if (ifExists) {
					throw new AlreadyExistsInTAException();
				}

				else {

					System.out.println("Adding new data into the database");
					
					
					String SQLPeopleInsert = "Insert into teachingassistant (TaUIN, OfferID) Values (?,?);";//write the query
					PreparedStatement stmt = conn.prepareStatement(SQLPeopleInsert);
					stmt.setInt(1, UIN);//set the values
					stmt.setInt(2, offerID);//set the values
					int i = stmt.executeUpdate();//execute the query
					
					DBAnnotation.annoate("UIN", "teachingassistant", "TaUIN", false);
					DBAnnotation.annoate("offerID", "teachingassistant", "OfferID", false);
					
					System.out.println(i);
					System.out.println("Inserted");
					isAdded = true;
					updateStudentToTA(UIN);//call the update function

				}

			}
			//catch block 
			catch (SQLException e) {
				System.out.println("Error adding/updating to database");
				e.printStackTrace();
				System.out.println(e);
			}

		
		//catch the already exists exception
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

		boolean isExisting = false;//set it to false

		try {
			Connection conn = Database.getConnection();//get the connection
			String SQLPeopleSelect = "";

			try {

				SQLPeopleSelect = "Select TaUIN From teachingassistant where OfferID=? and TaUIN= ?;";//write the query
				PreparedStatement stmt = conn.prepareStatement(SQLPeopleSelect);
				stmt.setInt(1, offerID);//set the values
				stmt.setInt(2, UIN);//set the values
				ResultSet rs = stmt.executeQuery();//execute the query

				if (rs.first()) {
					System.out.println(UIN+ "already exists as a TA");
					return true;
				}

			}
			//catch the exception
			catch (SQLException e) {
				System.out.println("Error adding/updating to database");
				e.printStackTrace();
				System.out.println(e);
			}
			
		}
		//catch block
		catch (Exception e) {
			System.out.println("Connection failed");
			e.printStackTrace();
			System.out.println(e);

		}
		//finally
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


		ArrayList<TA> getAllTAs = new ArrayList<TA>();//initialize an arraylist

		try {
			Connection conn = Database.getConnection();//get the connection

			try {
				if (conn != null) {

					// Retrieve all the professors from one department
					String ProfessorSelect = "Select *"
							+ " FROM people" + " WHERE PositionID=4";
					PreparedStatement statement = conn.prepareStatement(ProfessorSelect);
					ResultSet rs = statement.executeQuery();//execute the query

					while (rs.next()) {

						DBAnnotation.annoate("retreivedTAUIN", "people", "UIN", true);
						int retreivedTAUIN = rs.getInt("UIN");
						// System.out.println(retreivedProfUserNames);
						TA teachingAssistant;
						try {
							teachingAssistant = new TA(retreivedTAUIN);
							getAllTAs.add(teachingAssistant);//add the retreived TA objects to the arraylist
							
							DBAnnotation.annoate("getTaUserName", "people", "Username", true);
							String getTaUserName=teachingAssistant.getUserName();
							System.out.println(getTaUserName);
							
						} catch (PersonDoesNotExistException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}

				}

			}
			//catch the SQl exception
			catch (SQLException e) {
				System.out.println("Error fetching all the professors");
				System.out.println(e.getMessage());
				e.printStackTrace();

			}
			//finally block
			finally {
				// Database.commitTransaction(conn);
			}

		}
		//finally block 
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

		//check for null
		if(userName==null){
			return false;
			
		}
		//check for empty string
		if(userName.length()==0){
			return false;
		}
		
		
		boolean isUpdated = false;//set to false to be returned if it does not exist in login table

		try {
			Connection conn = Database.getConnection();//get the connection

			try {

				boolean ifUpdatedInLogin = People.updateUserNameIntoLoginTable(userName, this.getUserName());//check if it is updated in the login table
				if (ifUpdatedInLogin)
					isUpdated = true;

			}
			//catch block
			catch (Exception e) {
				System.out.println("Error adding/updating to database");
				e.printStackTrace();
				System.out.println(e);
			}

		}
		//catch the exception
		catch (Exception e) {
			System.out.println("Connection failed");
			e.printStackTrace();
			System.out.println(e);

		}
		//finally block
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
		
		//check for null
		if(name==null){
			return false;
			
		}
		//check if the length of the string is 0
		if(name.length()==0){
			return false;
		}
		

		try {
			Connection conn = Database.getConnection();//get the connection

			try {

				boolean ifUpdatedInPeople = People.updateNameIntoPeopleTable(name, this.getUIN());//update in the people table
				if (ifUpdatedInPeople)
					isUpdated = true;

			}
			//catch block
			catch (Exception e) {
				System.out.println("Error adding/updating to database");
				e.printStackTrace();
				System.out.println(e);
			}

		}
		//catch block
		catch (Exception e) {
			System.out.println("Connection failed");
			e.printStackTrace();
			System.out.println(e);

		}
		//finally block
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

		boolean isUpdated = false;//set the value to be returned as false

		try {
			Connection conn = Database.getConnection();//get the connection

			try {

				boolean ifUpdatedInPeople = People.updateDeptIntoPeopleTable(deptID, this.getUIN());//update in people
				if (ifUpdatedInPeople)
					isUpdated = true;

			}
			//catch block
			catch (Exception e) {
				System.out.println("Error adding/updating to database");
				e.printStackTrace();
				System.out.println(e);
			}

		}
		//catch block
		catch (Exception e) {
			System.out.println("Connection failed");
			e.printStackTrace();
			System.out.println(e);

		}
		//finally block
		finally {

			// System.out.println("retrieved");
		}

		return isUpdated;

	}

	/*if a student is added as a TA the nhis position ID is changed to TA
	 * 
	 * the parameter passed is UIN
	 * 
	 * the return type is boolean*/
	public static boolean updateStudentToTA(int UIN){
		
		boolean isUpdated=false;//set the return value to false
		int setPosition=4;
		
		try{
			Connection conn = Database.getConnection();//get the connection
			
			try{
				
				    
						
						System.out.println("Updating data in the database");
						String SQLDeptUpdate= "UPDATE people SET PositionID= ? where UIN=?;";
						PreparedStatement stmt = conn.prepareStatement(SQLDeptUpdate);
						stmt.setInt(1,setPosition);//set the values
						stmt.setInt(2, UIN);//set the values
						System.out.println(stmt);
						int i = stmt.executeUpdate();//execute the query
						
						DBAnnotation.annoate("setPosition", "people", "PositionID", false);
						DBAnnotation.annoate("UIN", "people", "UIN", false);
						
						System.out.println(i);
						System.out.println("Inserted");
						isUpdated=true;
						Database.commitTransaction(conn);
						
					
					
			}
			//catch the SQL exception
			catch(SQLException e){
				System.out.println("Error adding/updating to database");
				System.out.println(e);	
				e.printStackTrace();
			}
		}
		//outer catch block 
		catch(Exception e){
			System.out.println("Connection failed");
			System.out.println(e);
			e.printStackTrace();
			
		}
		//finally block
		finally{
			
			//System.out.println("retrieved");
		}
		
	return isUpdated;
	}
		
	/*if a TA is relieved of his Ta ship then his position ID is changed 3 as all students
	 * 
	 * the parameter passed is UIN
	 * 
	 * the return type is boolean*/
	public static boolean updateTAtoStudent(int UIN){
		
		boolean isUpdated=false;//set the return value to be false
		int setPosition=3;
		
		
		try{
			Connection conn = Database.getConnection();//get the connection
			
			try{
				System.out.println("Updating data in the database");
				String SQLDeptUpdate= "UPDATE people SET PositionID= ? where UIN=?;";//write the query
				PreparedStatement stmt = conn.prepareStatement(SQLDeptUpdate);
				stmt.setInt(1, setPosition);//set the values
				stmt.setInt(2, UIN);//set the values
				System.out.println(stmt);
				int i = stmt.executeUpdate();//execute the query
				
				DBAnnotation.annoate("setPosition", "people", "PositionID", false);
				DBAnnotation.annoate("UIN", "people", "UIN", false);
				
				System.out.println(i);
				System.out.println("Inserted");
				isUpdated=true;
				if(isUpdated)
					Database.commitTransaction(conn);
				
				else
					Database.rollBackTransaction(conn);
				
					
					
			}
			//catch block for SQL exception
			catch(SQLException e){
				System.out.println("Error adding/updating to database");
				System.out.println(e);	
				e.printStackTrace();
			}
			
		}
		//catch block
		catch(Exception e){
			System.out.println("Connection failed");
			System.out.println(e);
			e.printStackTrace();
			
		}
		//finally block
		finally{
			
			//System.out.println("retrieved");
		}
		
	return isUpdated;
	}

	//new added exceptions that can be thrown
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

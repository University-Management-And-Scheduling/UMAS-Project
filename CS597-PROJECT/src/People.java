import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.util.Random;

import com.mysql.jdbc.Statement;

/**
 * @author Akshay
 * 
 */

/*************** PEOPLE.JAVA CLASS WAS WRITTEN BY AKSHAY THIRKATEH ********************************/


public class People {

	// class variables mainly uses by the people class
	protected int UIN;
	protected String name;
	protected String userName;
	protected int deptID;
	protected int positionID;

	/*constructor for people class with name, username, dept ID and positionID*/
	public People(String name, String userName, int deptID, int positionID) {
		this.name = name;//---------initializing People class variable "Name"
		this.userName = userName;//---------initializing People class variable "username"
		this.deptID = deptID;//---------initializing People class variable "Dept ID"
		this.positionID = positionID;//---------initializing People class variable "position ID"

	}

	/*This constructor for the people class uses only the UIN to initialize the people object*/
	public People(int UIN) throws PersonDoesNotExistException {

		try {
			Connection conn = Database.getConnection();// make a connection to the database.
			/*calling the function in the database class to make the connection to the database*/
			
			String SQLPeopleSelect = "";
			try {
				/*check if the conn is  successful then run the query*/
				if (conn != null) {
					/*Query to select all the details from the people table for a particular UIN*/
					SQLPeopleSelect = "Select UIN, Name, Username, DepartmentID, PositionID From People where UIN=?;";
				}
				/*Creating a prepared statement for executing the query*/
				PreparedStatement stmtForSelect = conn.prepareStatement(SQLPeopleSelect);
				//Insert the passed UIN.
				stmtForSelect.setInt(1, UIN);

				ResultSet rs = stmtForSelect.executeQuery();// Execute the query.

				//if rs.first means that there is a resultset that exists.
				if (rs.first()) {

					//DB annotation
					//For a selected resultset we store the retrieved details in these local variables
					
					DBAnnotation.annoate("peopleRetrievedUIN", "people", "UIN", true);
					int peopleRetrievedUIN = rs.getInt("UIN");//--------> get UIN from UIN column from database
					
					DBAnnotation.annoate("peopleRetrievedName", "people", "Name", true);
					String peopleRetrievedName = rs.getString("Name");//--------> get name from name column from database
					
					DBAnnotation.annoate("peopleRetrieveduserName", "people", "UserName", true);
					String peopleRetrieveduserName = rs.getString("UserName");//--------> get username from username column from database
					
					DBAnnotation.annoate("peopleRetrievedDeptID", "people", "DepartmentID", true);
					int peopleRetrievedDeptID = rs.getInt("DepartmentID");//--------> get dept ID from dept ID UIN column from database
					
					DBAnnotation.annoate("peopleRetrievedPositionID", "people", "PositionID", true);
					int peopleRetrievedPositionID = rs.getInt("PositionID");//--------> get position ID from position ID column from database

					// initializing the class variables with the retrieved values
					this.UIN = peopleRetrievedUIN;
					this.name = peopleRetrievedName;
					this.userName = peopleRetrieveduserName;
					this.deptID = peopleRetrievedDeptID;
					this.positionID = peopleRetrievedPositionID;

					//This sets the class variables to the details of the UIN specific person. This can be used by all the class methods.
				}

				else {
					/*This means that there exists no person in the database for the passed UIN*/
					System.out.println("UIN does not exist in the people table");
					/*Here we throw a person does not exist exception for anyone who's using this constructor to catch*/
					throw new PersonDoesNotExistException();

				}

			}
			/*Catches the inner try blocks SQL exception*/
			catch (SQLException e) {
				System.out.println(e);

			}
			/*The code thats placed in the finally block gets executed no matter what. But 
														here the finally block does not contain any general statements*/
			finally {

				// System.out.println("retrieved");
			}
		}
		/*Catches the outer try blocks general exception*/
		catch (Exception e) {
			System.out.println(e);

		}
		/*The code thats placed in the finally block gets executed no matter what. But 
														here the finally block does not contain any general statements*/
		finally {

			// System.out.println("retrieved");
		}

	}
	/*This constructor for the people class uses only the username to initialize the people object*/
	public People(String userName) {

		try {
			Connection conn = Database.getConnection();// make a connection to the database.
			/*calling the function in the database class to make the connection to the database*/
			String SQLPeopleSelect = "";
			try {
				//If the connection is successful
				if (conn != null) {

					/*Query to select all the details from the people table for the passed username*/
					SQLPeopleSelect = "Select UIN, Name, Username, DepartmentID, PositionID From People where Username=?;";
				}

				/*Creating a prepared statement for executing the query*/
				PreparedStatement stmtForSelect = conn.prepareStatement(SQLPeopleSelect);
				//set the username in the where clause
				stmtForSelect.setString(1, userName);
				
				ResultSet rs = stmtForSelect.executeQuery();// Execute the query using the resultset

				if (rs.first()) {

					// if the query is successful and returns an row, the details are then retrieved and stored in variables
					
					DBAnnotation.annoate("peopleRetrievedUIN", "people", "UIN", true);
					int peopleRetrievedUIN = rs.getInt("UIN");//--------> get UIN from UIN column from database
					
					DBAnnotation.annoate("peopleRetrievedName", "people", "Name", true);
					String peopleRetrievedName = rs.getString("Name");//--------> get name from name column from database
					
					DBAnnotation.annoate("peopleRetrieveduserName", "people", "UserName", true);
					String peopleRetrieveduserName = rs.getString("UserName");//--------> get username from username column from database
					
					DBAnnotation.annoate("peopleRetrievedDeptID", "people", "DepartmentID", true);
					int peopleRetrievedDeptID = rs.getInt("DepartmentID");//--------> get dept ID from dept ID column from database
					
					DBAnnotation.annoate("peopleRetrievedPositionID", "people", "PositionID", true);
					int peopleRetrievedPositionID = rs.getInt("PositionID");//--------> get positionID from positionID column from database

					// initializing the class variables with the retrieved values
					
					this.UIN = peopleRetrievedUIN;
					this.name = peopleRetrievedName;
					this.userName = peopleRetrieveduserName;
					this.deptID = peopleRetrievedDeptID;
					this.positionID = peopleRetrievedPositionID;

					/*This sets the class variables to the details of the UIN specific person. This can be used by all the class methods.*/
					
				}

				else {
					/*if the query does not return any row then the else clause throws a person does not exist exception.*/
					System.out.println("UIN does not exist");
					// throw the person does not exist exception
					throw new PersonDoesNotExistException();

				}

			}
			/*Catches the inner try block SQL exception*/
			catch (SQLException e) {
				System.out.println(e);

			}
			/*The code thats placed in the finally block gets executed no matter what. But 
												here the finally block does not contain any general statements*/
			finally {

				// System.out.println("retrieved");
			}
		}
		/*Catches the outer try blocks general exception*/
		catch (Exception e) {
			System.out.println(e);

		}
		/*The code thats placed in the finally block gets executed no matter what. But 
											here the finally block does not contain any general statements*/
		finally {

			// System.out.println("retrieved");
		}

	}

	/*getter and setter for UIN*/
	public int getUIN() {
		return UIN;
	}

	public void setUIN(int uIN) {
		UIN = uIN;
	}
	
	/*getter and setter for name*/
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/*getter and setter for username*/
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	/*getter and setter for dept ID*/
	public int getDeptID() {
		return deptID;
	}

	public void setDeptID(int deptID) {
		this.deptID = deptID;
	}

	/*getter and setter for position ID*/
	public int getPositionID() {
		return positionID;
	}

	public void setPositionID(int positionID) {
		this.positionID = positionID;
	}

	/*This function is to generate a random password of the required passed length. This function 
	  														*returns the generated password as a string */
	public static String generatePassword(int length) {
		
		//make a new sriung that takes in all the digits, alphabets(upper case and lower case)
		String alphabet = new String(
				"0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz"); // all the possible characters and numbers
		int n = alphabet.length(); // get its length

		String result = new String(); // result is the string that needs to be returned, containing password.
		Random r = new Random(); // r takes in a random number

		/*the for loop runs for the number of times a specified no of times we say the length should be */
		for (int i = 0; i < length; i++)
			// 12
			result = result + alphabet.charAt(r.nextInt(n)); // append the random character to the result string.

		return result;
	}

	/* checking if auto generated user name already exists in the people table in the database when a username is passed.
	 												* The return type is boolean */
	public static boolean checkIfUserNameExists(String userName) {

		try {
			Connection conn = Database.getConnection();// get a connection to the database.
			String SQLPeopleSelect = "";
			try {
				if (conn != null) {//if the connection is successful
					
					/*select the username from people table where the username is passed*/
					SQLPeopleSelect = "Select Username From People where Username=?;";
					
					PreparedStatement stmt = conn.prepareStatement(SQLPeopleSelect);
					stmt.setString(1, userName);// set the passed username in the where clause
					ResultSet rs = stmt.executeQuery();

					//if the query returns a successful resultset
					if (rs.first()) {

						DBAnnotation.annoate("peopleRetrieveduserName", "people", "UserName", true);
						String peopleRetrieveduserName = rs.getString("UserName");//get the username from the username column in the database
						System.out.println(peopleRetrieveduserName);
						return true;

					}

					else {
						//if the query does not return a resultset 
						System.out.println("Username does not exist");
						return false;

					}
				}

			}
			/*The inner catch clause catches the SQL exception*/
			catch (SQLException e) {
				System.out.println("Error trying to access the database");
				System.out.println(e);
			}

			/*The code thats placed in the finally block gets executed no matter what. But 
												here the finally block does not contain any general statements*/
			finally {
				// System.out.println("retrieved");
			}
		}
		/*The outer catch clause catches the general exception*/
		catch (Exception e) {
			System.out.println("Connection failed");
			System.out.println(e);

		}

		/*The code thats placed in the finally block gets executed no matter what. But 
										here the finally block does not contain any general statements*/
		finally {

			// System.out.println("retrieved");
		}

		return false;
	}

	/*Adding the user to the login table with auto generated username and password*/
	public static boolean addUserDetailsIntoLoginTable(String userName,String Password) {

		boolean isAdded = false;// create a boolean variable for reference and returning

		try {
			Connection conn = Database.getConnection();//establish a connection to the database

			try {
				
				/*before adding call the function that checks if the username already exists in the database. 
				 * if its exists it returns true*/
				boolean ifExists = addUserDetailsIntoLoginTableCheck(userName);

				if (ifExists) {
					return false;// return false if the username already exists.
				}

				else {
					//if the username does not exist add the username and password to the login details table
					System.out.println("Adding new data into the database");
					String SQLPeopleInsert = "Insert into logindetails (Username, Password) Values (?,?);";
					PreparedStatement stmt = conn.prepareStatement(SQLPeopleInsert);
					stmt.setString(1, userName);//set the username 
					stmt.setString(2, Password);//set the password
					System.out.println(stmt);
					int i = stmt.executeUpdate();//execute the query
					
					DBAnnotation.annoate("userName", "logindetails", "Username", false);
					DBAnnotation.annoate("Password", "logindetails", "Password", false);
					
					System.out.println(i);
					System.out.println("Inserted");
					isAdded = true;
					//set the boolean variable to true
				}

			}

			//the inner catch block catches the sql exception
			catch (SQLException e) {
				System.out.println("Error adding/updating to database");
				System.out.println(e);
			}
			
			/*The code thats placed in the finally block gets executed no matter what. But 
											here the finally block does not contain any general statements*/
			finally {
				// System.out.println("retrieved");
				// Database.closeConnection(conn);
			}
		}
		//the outer catch block catch the general exception
		catch (Exception e) {
			System.out.println("Connection failed");
			System.out.println(e);

		}

		/*The code thats placed in the finally block gets executed no matter what. But 
										here the finally block does not contain any general statements*/
		finally {

			// System.out.println("retrieved");
		}

		return isAdded;
	}

	/*Updating the user name in the login details table
	 * The parameters passed are the old username and the new username
	 * The return type is boolean 
	 * */
	public static boolean updateUserNameIntoLoginTable(String newUserName,String oldUserName) {

		boolean isUpdated = false;//create a boolean value for returning. set it to false

		try {
			Connection conn = Database.getConnection();//establish a connection to the database

			try {
				//query to update the old username to the new username .
				System.out.println("Updating data in the database");
				String SQLPeopleInsert = "UPDATE logindetails SET Username= ? where Username=?;";
				PreparedStatement stmt = conn.prepareStatement(SQLPeopleInsert);
				stmt.setString(1, newUserName);//set the new user name
				stmt.setString(2, oldUserName);//where the old user name is 
				System.out.println(stmt);
				int i = stmt.executeUpdate();//execute the query
				
				DBAnnotation.annoate("newUserName", "logindetails", "Username", false);
				DBAnnotation.annoate("oldUserName", "logindetails", "Username", false);
				
				System.out.println(i);
				System.out.println("Inserted");
				isUpdated = true;

				Database.commitTransaction(conn);//the transaction is committed.

			}
			//The inner catch block catches the SQL exception.
			catch (SQLException e) {
				System.out.println("Error adding/updating to database");
				System.out.println(e);
			}
			
			/*The code thats placed in the finally block gets executed no matter what. But 
											here the finally block does not contain any general statements*/
			finally {
				// System.out.println("retrieved");
				// Database.closeConnection(conn);
			}
		}
		//the outer catch block catches the general exception
		catch (Exception e) {
			System.out.println("Connection failed");
			System.out.println(e);

		}

		/*The code thats placed in the finally block gets executed no matter what. But 
											here the finally block does not contain any general statements*/
		finally {

			// System.out.println("retrieved");
		}

		return isUpdated;
	}

	/*Updating the name in the people table
	 * The parameters passed are the new username and the UIN
	 * The return type is boolean 
	 * */ 
	public static boolean updateNameIntoPeopleTable(String newName, int UIN) {

		boolean isUpdated = false;//create a boolean value for returning. set it to false

		try {
			Connection conn = Database.getConnection();//establish a connection 

			try {
				//updating the name to the new name for the specfic UIN
				System.out.println("Updating data in the database");
				String SQLPeopleInsert = "UPDATE people SET Name= ? where UIN=?;";
				PreparedStatement stmt = conn.prepareStatement(SQLPeopleInsert);
				stmt.setString(1, newName);//setting the new name
				stmt.setInt(2, UIN);//for the given UIN
				System.out.println(stmt);
				int i = stmt.executeUpdate();//execute the query
				
				DBAnnotation.annoate("newName", "people", "Name", false);
				DBAnnotation.annoate("UIN", "people", "UIN", false);
				
				System.out.println(i);
				System.out.println("Inserted");
				isUpdated = true;

				Database.commitTransaction(conn);//commit the transaction

			}

			//The inner catch block catches the SQL exception.
			catch (SQLException e) {
				System.out.println("Error adding/updating to database");
				System.out.println(e);
			}

			/*The code thats placed in the finally block gets executed no matter what. But 
												here the finally block does not contain any general statements*/
			finally {
				// System.out.println("retrieved");
				// Database.closeConnection(conn);
			}
		}

		//the outer catch block catches the generla exception
		catch (Exception e) {
			System.out.println("Connection failed");
			System.out.println(e);

		}
		/*The code thats placed in the finally block gets executed no matter what. But 
											here the finally block does not contain any general statements*/
		finally {

			// System.out.println("retrieved");
		}

		return isUpdated;
	}

	/*Updating the department in the people table
	 * The parameters passed are the department ID and the UIN
	 * The return type is boolean 
	 * */

	public static boolean updateDeptIntoPeopleTable(int deptID, int UIN) {

		boolean isUpdated = false;//create a boolean value for returning. set it to false

		try {
			Connection conn = Database.getConnection();//get the connection

			try {
				//update the department of the passed UIN in the people table
				System.out.println("Updating data in the database");
				String SQLDeptUpdate = "UPDATE people SET DepartmentID= ? where UIN=?;";
				PreparedStatement stmt = conn.prepareStatement(SQLDeptUpdate);
				stmt.setInt(1, deptID);//set the new Dept
				stmt.setInt(2, UIN);//for this UIN
				System.out.println(stmt);
				int i = stmt.executeUpdate();//execute the query.
				
				DBAnnotation.annoate("deptID", "people", "DepartmentID", false);
				DBAnnotation.annoate("UIN", "people", "UIN", false);
				
				System.out.println(i);
				System.out.println("Inserted");
				isUpdated = true;

				Database.commitTransaction(conn);//commit the transaction 

			}

			//The inner catch block catches the SQL exception.
			catch (SQLException e) {
				System.out.println("Error adding/updating to database");
				System.out.println(e);
			}


			/*The code thats placed in the finally block gets executed no matter what. But 
										here the finally block does not contain any general statements*/
			finally {
				// System.out.println("retrieved");
				// Database.closeConnection(conn);
			}
		}
		//the outer catch block catches the generla exception
		catch (Exception e) {
			System.out.println("Connection failed");
			System.out.println(e);

		}

		/*The code thats placed in the finally block gets executed no matter what. But 
											here the finally block does not contain any general statements*/

		finally {

			// System.out.println("retrieved");
		}

		return isUpdated;
	}

	/*The function is mainly used for checking if the user name exists in the login details table
	 * The parameter passed is the username that is to be checked
	 * The return type is boolean 
	 * */
	public static boolean addUserDetailsIntoLoginTableCheck(String userName) {

		boolean isExisting = false;//create a boolean value for returning. set it to false

		try {
			Connection conn = Database.getConnection();//establish a connection
			String SQLPeopleSelect = "";

			try {

				//select the username from the login table where the passed username is the new username
				SQLPeopleSelect = "Select Username From logindetails where Username=?;";
				PreparedStatement stmt = conn.prepareStatement(SQLPeopleSelect);
				stmt.setString(1, userName);
				ResultSet rs = stmt.executeQuery();//execute the query.

				if (rs.first()) {
					//if there exists a successful resultset then return true
					return true;
				}

			}
			//The inner catch block catches the SQL exception.
			catch (SQLException e) {
				System.out.println("Error adding/updating to database");
				System.out.println(e);
			}
			/*The code thats placed in the finally block gets executed no matter what. But 
												here the finally block does not contain any general statements*/
			finally {
				// System.out.println("retrieved");
				// Database.closeConnection(conn);
			}
		}
		//The outer catch block catches the general  exception.
		catch (Exception e) {
			System.out.println("Connection failed");
			System.out.println(e);

		}
		/*The code thats placed in the finally block gets executed no matter what. But 
												here the finally block does not contain any general statements*/
		finally {

			// System.out.println("retrieved");
		}

		return isExisting;
	}

	/*add the user into database which generates username and password and adds into people table
	 * for the name passed generate a unique username
	 * generate a random password
	 * then add into the login table and then add into the people table
	 * return the UIN of the added person
	*/
	public static int addIntoDatabase(String name, Department dept,int positionId) throws loginDetailsnotAdded {

		// generate a user name from name
		String userName = "";
		int addedUIN = -1;
		int lengthOfName = name.length();//get the length of the name
		if (lengthOfName <= 5) {
			userName = name;// if the length of the name is <=5 then name is the same as username

		} else {
			userName = name.substring(0, 5);//else get a substring 
		}

		// check if it already exists

		// put in a while loop and generate new

		while (checkIfUserNameExists(userName)) {// the while loop checks if the username exists already
			Random randomNumber = new Random();
			userName = (userName + randomNumber.nextInt(99));//append a random number to the username

		}

		// generate a random password
		String randomPassword = generatePassword(8);//returns a random password

		// add into login details
		userName = userName.toLowerCase();//converting the username to lower case
		boolean isAdded = addUserDetailsIntoLoginTable(userName, randomPassword);//returns a boolean true if added to the login table

		if (isAdded) {// add into people table


			try {
				Connection conn = Database.getConnection();//get a connection

				try {

					boolean isExisting = addIntoDatabaseCheck(userName);//check if the username already exists in the people table

					if (isExisting) {
						return -1;// this returns a -1, that indicates that the person was not added
					}

					else {
						//add the new person into the people table
						int getDeptID=dept.getDepartmentID();
						System.out.println("Adding new data into the database");
						
						String SQLPeopleInsert = "Insert into People (Name, Username, DepartmentID, PositionID) Values (?,?,?,?);";
						PreparedStatement stmt = conn.prepareStatement(SQLPeopleInsert,Statement.RETURN_GENERATED_KEYS);
						stmt.setString(1, name);//set the name 
						stmt.setString(2, userName);//set the username
						stmt.setInt(3, getDeptID);//set the department
						stmt.setInt(4, positionId);//set the position ID
						System.out.println(stmt);
						int i = stmt.executeUpdate();//execute the query
						
						DBAnnotation.annoate("name", "people", "Name", false);
						DBAnnotation.annoate("userName", "people", "Username", false);
						DBAnnotation.annoate("getDeptID", "people", "DepartmentID", false);
						DBAnnotation.annoate("positionId", "people", "PositionID", false);
						
						ResultSet rs = stmt.getGeneratedKeys();
						if (rs.first())
							addedUIN = rs.getInt(1);// this is to retrieve the latest added UIN
						System.out.println(rs.getInt(1));
						System.out.println(i);
						// Database.commitTransaction(conn);
					}

				}

				//The inner catch block catches the SQL exception.
				catch (SQLException e) {
					System.out.println("Error adding/updating to database");
					e.printStackTrace();
					System.out.println(e);
				}
				/*The code thats placed in the finally block gets executed no matter what. But 
													here the finally block does not contain any general statements*/
				finally {
					// System.out.println("retrieved");
				}
			}
			//the outer catch block catches the generla exception
			catch (Exception e) {
				System.out.println("Connection failed");
				System.out.println(e);

			}
			/*The code thats placed in the finally block gets executed no matter what. But 
													here the finally block does not contain any general statements*/
			finally {

				// System.out.println("retrieved");
			}

			return addedUIN;

		}

		else {
			//if the login is not added then login not added exception is thrown. 
			throw new loginDetailsnotAdded();
		}

	}
	//this function is to mainly check if the passed username exists in the table or not
	public static boolean addIntoDatabaseCheck(String userName) throws loginDetailsnotAdded {
		
		boolean isExisting = false;//create a boolean value for returning. set it to false

		try {
			Connection conn = Database.getConnection();//establish a connection
			String SQLPeopleSelect = "";

			try {
				//select the person with the username passed
				SQLPeopleSelect = "Select UIN, Name, Username, DepartmentID, PositionID From People where Username=?;";
				PreparedStatement stmt = conn.prepareStatement(SQLPeopleSelect);
				stmt.setString(1, userName);
				ResultSet rs = stmt.executeQuery();//execute the query

				if (rs.first()) {
					//if the resultset exists then return true
					return true;
				}

			}
			//catch the SQl exception
			catch (SQLException e) {
				System.out.println("Error adding/updating to database");
				e.printStackTrace();
				System.out.println(e);
			}

			finally {
				// System.out.println("retrieved");
			}
		}
		//catch the exception
		catch (Exception e) {
			System.out.println("Connection failed");
			System.out.println(e);

		}

		/*The code thats placed in the finally block gets executed no matter what. But 
												here the finally block does not contain any general statements*/
		finally {

			// System.out.println("retrieved");
		}

		return isExisting;

	}
	
	/*The delete function takes in the UIN of the user
	The return type is boolean*/
	public static boolean deleteFromDatabaseByUIN(int UIN) {

		boolean isDeleted = false;//create a boolean value for returning. set it to false
		try {
			Connection conn = Database.getConnection();//establish a connection
			String SQLPeopleSelect = "";
			String SQLPeopleDelete = "";
			try {
				if (conn != null) {
					//select the details where the UIN is the passed UIN specific
					SQLPeopleSelect = "Select UIN, Name, Username, DepartmentID, PositionID From People where UIN=?;";
					PreparedStatement stmt = conn.prepareStatement(SQLPeopleSelect);
					stmt.setInt(1, UIN);//set the UIN
					ResultSet rs = stmt.executeQuery();//execute the query

					if (rs.first()) {

						DBAnnotation.annoate("peopleRetrievedUIN", "people", "UIN", true);
						int peopleRetrievedUIN = rs.getInt("UIN");//get the UIN and use this to delete
						
						DBAnnotation.annoate("peopleRetrievedName", "people", "Name", true);
						String peopleRetrievedName = rs.getString("Name");
						
						DBAnnotation.annoate("peopleRetrieveduserName", "people", "UserName", true);
						String peopleRetrieveduserName = rs.getString("UserName");
						
						DBAnnotation.annoate("peopleRetrievedDeptID", "people", "DepartmentID", true);
						int peopleRetrievedDeptID = rs.getInt("DepartmentID");
						
						DBAnnotation.annoate("peopleRetrievedPositionID", "people", "PositionID", true);
						int peopleRetrievedPositionID = rs.getInt("PositionID");

						//Delete function to delete it from the people table
						SQLPeopleDelete = "Delete From People where UIN=?;";
						stmt = conn.prepareStatement(SQLPeopleDelete);
						stmt.setInt(1, UIN);
						int rs1 = stmt.executeUpdate();//execute the query
						
						DBAnnotation.annoate("UIN", "people", "UIN", false);
						
						System.out.println(peopleRetrievedUIN + " is deleted");
						isDeleted = true;


					}

					else {
						//if the UIN is not found then UIN does not exist in the table
						System.out.println("UIN does not exist");

					}
				}

			}
			//catch the SQL exception
			catch (SQLException e) {
				System.out.println("Error trying to access the database");
				System.out.println(e);
			}

			finally {
				// System.out.println("retrieved");
			}
		}
		//catch block
		catch (Exception e) {
			System.out.println("Connection failed");
			System.out.println(e);

		}
		/*The code thats placed in the finally block gets executed no matter what. But 
													here the finally block does not contain any general statements*/
		finally {

			// System.out.println("retrieved");
		}

		return isDeleted;

	}

	/*The delete function takes in the username of the person when passed a username
	The return type is boolean*/
	public static boolean deleteFromDatabaseByUserName(String userName) {

		boolean isDeleted = false;

		try {
			Connection conn = Database.getConnection();//establish a connection
			String SQLPeopleDelete = "";
			ResultSet rs;
			try {

				boolean ifExisting = deleteFromDatabaseByUserNameCheck(userName);

				if (ifExisting) {
					//it the name exists then only delete
					SQLPeopleDelete = "Delete From People where Username=?;";
					PreparedStatement stmt = conn.prepareStatement(SQLPeopleDelete);
					stmt.setString(1, userName);//set the username
					int rs1 = stmt.executeUpdate();//execute the query
					
					DBAnnotation.annoate("userName", "people", "Username", false);
					
					System.out.println(userName + " is deleted");
					isDeleted = true;
				}

				else {
					//else the UIN does not exist
					System.out.println("UIN does not exist");

				}

			}

			catch (SQLException e) {
				System.out.println("Error trying to access the database");
				System.out.println(e);
			}

			finally {
				// System.out.println("retrieved");
			}
		}

		catch (Exception e) {
			System.out.println("Connection failed");
			System.out.println(e);

		}

		finally {

			// System.out.println("retrieved");
		}

		return isDeleted;

	}
	/*The function is mainly check if the username passed exists in the talbe or not
	 * 
	 * the return type is boolean
	 * */
	public static boolean deleteFromDatabaseByUserNameCheck(String userName) {

		boolean isExisting = false;

		try {
			Connection conn = Database.getConnection();//establish a connection
			String SQLPeopleSelect = "";
			try {
				if (conn != null) {
					//select query to check if it exists in the table
					SQLPeopleSelect = "Select UIN, Name, Username, DepartmentID, PositionID From People where Username=?;";
					PreparedStatement stmt = conn.prepareStatement(SQLPeopleSelect);
					stmt.setString(1, userName);
					ResultSet rs = stmt.executeQuery();//execute the query

					if (rs.first()) {
						//if thw resultset exists then return true
						return true;

					}

					else {
						//if the resultset does not return anything then it does not exist
						System.out.println("UIN does not exist");

					}
				}

			}
			//catch the SQL exception
			catch (SQLException e) {
				System.out.println("Error trying to access the database");
				System.out.println(e);
			}

			finally {
				// System.out.println("retrieved");
			}
		}
		//catch the exception
		catch (Exception e) {
			System.out.println("Connection failed");
			System.out.println(e);

		}
		
		/*The code thats placed in the finally block gets executed no matter what. But 
												here the finally block does not contain any general statements*/
		finally {

			// System.out.println("retrieved");
		}

		return isExisting;

	}
	
	/*The function is mainly check if the UIN passed exists in the table or not
	 * 
	 * the return type is boolean
	 * */
	public static boolean deleteFromDatabaseByUINCheck(int UIN) {

		boolean isExisting = false;

		try {
			Connection conn = Database.getConnection();//establish a connection
			String SQLPeopleSelect = "";
			try {
				if (conn != null) {
					//select the username if it exists in the table
					SQLPeopleSelect = "Select UIN, Name, Username, DepartmentID, PositionID From People where UIN=?;";
					PreparedStatement stmt = conn.prepareStatement(SQLPeopleSelect);
					stmt.setInt(1, UIN);
					ResultSet rs = stmt.executeQuery();//execute the query

					if (rs.first()) {
						//if the resultset exists then return true
						return true;

					}

					else {
						//else the UIN does not exist
						System.out.println("UIN does not exist");

					}
				}

			}
			//carch the SQL exception
			catch (SQLException e) {
				System.out.println("Error trying to access the database");
				System.out.println(e);
			}
			
			//finally block
			finally {
				// System.out.println("retrieved");
			}
		}
		//catch the general exception
		catch (Exception e) {
			System.out.println("Connection failed");
			System.out.println(e);

		}
		/*The code thats placed in the finally block gets executed no matter what. But 
												here the finally block does not contain any general statements*/
		finally {

			// System.out.println("retrieved");
		}

		return isExisting;

	}

	/*retrieving the details of the user and initializing them 
	 * The parameter passed is the UIN 
	 * The return type is the People object
	 * */
	public static People retireveDetailsByUIN(int UIN) throws PersonDoesNotExistException {

		try {
			Connection conn = Database.getConnection();//establish a connection
			String SQLPeopleSelect = "";
			try {

				if (conn != null) {
					/*query to select all the user details for the passed UIN*/
					SQLPeopleSelect = "Select UIN, Name, Username, DepartmentID, PositionID From People where UIN=?;";
				}

				PreparedStatement stmtForSelect = conn.prepareStatement(SQLPeopleSelect);
				stmtForSelect.setInt(1, UIN);//set the UIN

				ResultSet rs = stmtForSelect.executeQuery();//exectute the query

				if (rs.first()) {

					DBAnnotation.annoate("retrievedUIN", "people", "UIN", true);
					int retrievedUIN= rs.getInt("UIN");
							
					People peopleDetails = new People(retrievedUIN);//setting the object
					return peopleDetails;//return the object

				}

				else {
					//If the query does not have anything in the resultset then return null
					System.out.println("UIN does not exist");
					return null;

				}

			}

			//The inner catch block catches the SQL exception.
			catch (SQLException e) {
				System.out.println(e);

			}
			
			finally {

				// System.out.println("retrieved");
			}
		}
		//the outer catch block catches the generla exception
		catch (Exception e) {
			System.out.println(e);

		}
		/*The code thats placed in the finally block gets executed no matter what. But 
															here the finally block does not contain any general statements*/
		finally {

			// System.out.println("retrieved");
		}

		return null;
	}

	/*retrieving the details of the user and initializing them 
	 * The parameter passed is the username 
	 * The return type is the People object
	 * */
	public static People retireveDetailsByuserName(String userName) {

		try {
			Connection conn = Database.getConnection();//establish the connection
			String SQLPeopleSelect = "";
			try {

				if (conn != null) {

					/*this query selects all the details ofr the passed username*/
					SQLPeopleSelect = "Select UIN, Name, Username, DepartmentID, PositionID From People where Username=?;";
				}

				PreparedStatement stmtForSelect = conn.prepareStatement(SQLPeopleSelect);
				stmtForSelect.setString(1, userName);

				ResultSet rs = stmtForSelect.executeQuery();//execute the query

				if (rs.first()) {

					/*if the resultset exists then set the People object with the username passed*/
					DBAnnotation.annoate("retrievedUserName", "people", "Username", true);
					String retrievedUserName=rs.getString("Username");
					
					People peopleDetails = new People(retrievedUserName);
					return peopleDetails;

				}

				else {
					//if the query does not retreive anything then the null is returned
					System.out.println("UIN does not exist");
					return null;

				}

			}
			//The inner catch block catches the SQL exception.
			catch (SQLException e) {
				System.out.println(e);

			}
			/*The code thats placed in the finally block gets executed no matter what. But 
										here the finally block does not contain any general statements*/

			finally {

				// System.out.println("retrieved");
			}
		}
		
		catch (Exception e) {
			System.out.println(e);

		}
		//the outer catch block catches the generla exception
		finally {

			// System.out.println("retrieved");
		}

		return null;

	}

	/*creating our own exceptions to be thrown and handled */
	public static class PersonDoesNotExistException extends Exception {
		private static final long serialVersionUID = 1L;
		private String message = null;

		public PersonDoesNotExistException() {
			super();
			this.message = "Person does not exist";
		}

		public PersonDoesNotExistException(String message) {
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
	
	/*creating our own exceptions to be thrown and handled */
	public static class loginDetailsnotAdded extends Exception {
		private static final long serialVersionUID = 1L;
		private String message = null;

		public loginDetailsnotAdded() {
			super();
			this.message = "login Details not Added ";
		}

		public loginDetailsnotAdded(String message) {
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

	/*creating our own exceptions to be thrown and handled */
	static class PersonDoesNotExist extends Exception {
		private static final long serialVersionUID = 1L;
		private String message = null;

		public PersonDoesNotExist() {
			super();
			this.message = "Person does not Added ";
		}

		public PersonDoesNotExist(String message) {
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
	 * All people functions are run and tested locally in the main class for specific executions
	 */
	public static void main(String[] args) {

	}

}

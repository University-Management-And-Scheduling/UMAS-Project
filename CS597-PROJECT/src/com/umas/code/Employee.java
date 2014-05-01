package com.umas.code;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author Akshay
 * 
 */

/*************** EMPLOYEE.JAVA CLASS WAS WRITTEN BY AKSHAY THIRKATEH ********************************/

public class Employee extends People {

	/*class variables for employee specific use*/
	protected double salary;
	protected String officeAddress;
	protected String officeHours;

	
	/*----------------GETTERS AND SETTERS START------------------------------------*/
	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public String getOfficeAddress() {
		return officeAddress;
	}

	public void setOfficeAddress(String officeAddress) {
		this.officeAddress = officeAddress;
	}

	public String getOfficeHours() {
		return officeHours;
	}

	public void setOfficeHours(String officeHours) {
		this.officeHours = officeHours;
	}

	/*----------------GETTERS AND SETTERS END------------------------------------*/
	
	/*the constructor takes in the UIN and then initializes the employee specific variables
	 * 
	 * it calls in super people class and sets the people class variables too
	 * 
	 * it thorws person does not exist exception */
	public Employee(int UIN) throws PersonDoesNotExistException {
		super(UIN);

		try {
			Connection conn = Database.getConnection();//establish a connection
			String SQLPeopleSelect = "";

			try {
				//select employee details from employee table where for passed UIN
				SQLPeopleSelect = "Select * From employee where UIN=?;";
				PreparedStatement stmt = conn.prepareStatement(SQLPeopleSelect);
				stmt.setInt(1, UIN);//set the UIN
				ResultSet rs = stmt.executeQuery();//execute the query

				if (rs.first()) {
					//if exists then initialize the variables
					System.out.println(UIN + "already exists");
					
					DBAnnotation.annoate("retrievedEmployeeUIN", "employee", "UIN", true);
					int retrievedEmployeeUIN = rs.getInt("UIN");//retrieve the UIN 
					
					DBAnnotation.annoate("retrievedEmployeeSalary", "employee", "Salary", true);
					double retrievedEmployeeSalary = rs.getDouble("Salary");//retrieve the salary 
					
					DBAnnotation.annoate("retrievedOfficeAddress", "employee", "OfficeAddress", true);
					String retrievedOfficeAddress = rs.getString("OfficeAddress");//retrieve the office address
					
					DBAnnotation.annoate("retrievedOfficeHours", "employee", "OfficeHours", true);
					String retrievedOfficeHours = rs.getString("OfficeHours");//retrieve the office hours

					//set the class variables to the UIN specific
					
					this.UIN = retrievedEmployeeUIN;
					this.salary = retrievedEmployeeSalary;
					this.officeAddress = retrievedOfficeAddress;
					this.officeHours = retrievedOfficeHours;

				}

				else {
					//if the resultset is empty then return false and throw an exception
					System.out.println(UIN + "does not exist");
					throw new PersonDoesNotExistException();

				}

			}

			//The inner catch block catches the SQL exception.
			catch (SQLException e) {
				System.out.println("Error adding/updating to database");
				e.printStackTrace();
				System.out.println(e);
			}

		}
		/*The code thats placed in the finally block gets executed no matter what. But 
													here the finally block does not contain any general statements*/
		finally {

			// System.out.println("retrieved");
		}

		// TODO Auto-generated constructor stub
	}

	//constructor calls the super class
	public Employee(String name, String userName, int deptID, int positionID) {
		super(name, userName, deptID, positionID);
		// TODO Auto-generated constructor stub
	}

	//construtor calls the super class
	public Employee(String userName){
		super(userName);
		// TODO Auto-generated constructor stub
	}

	/*the add employee to the database takes in the UIN and adds him to the employee table
	 * 
	 * the return type is boolean 
	 * 
	 * */
	public static boolean addEmployee(int UIN) {

		boolean isAdded = false;//create a boolean value for returning. set it to false
		double salary = 40000.00;//this is a default value
		String Office_address = "to be decided";//this is a default value
		String office_hours = "to be decided";//this is a default value

		try {
			Connection conn = Database.getConnection();//establish a connection

			try {

				boolean isExisting = addEmployeeCheck(UIN);//check if its existing 

				if (isExisting) {
					//return false it the UIN already exists in the employee table
					return false;
					
				}

				else {
					//insert new employee in the database
					System.out.println("Adding new data into the database");
					String SQLEmployeeInsert = "Insert into employee (UIN, Salary, OfficeAddress, OfficeHours) Values (?,?,?,?);";
					PreparedStatement stmt = conn.prepareStatement(SQLEmployeeInsert);
					stmt.setInt(1, UIN);//set the UIN
					stmt.setDouble(2, salary);//set the salary
					stmt.setString(3, Office_address);//set the office address
					stmt.setString(4, office_hours);//set the office hours					
					System.out.println(stmt);
					int i = stmt.executeUpdate();//execute query
					
					DBAnnotation.annoate("UIN", "employee", "UIN", false);
					DBAnnotation.annoate("salary", "employee", "Salary", false);
					DBAnnotation.annoate("office_address", "employee", "OfficeAddress", false);
					DBAnnotation.annoate("office_hours", "employee", "OfficeHours", false);
					
					System.out.println(i);
					System.out.println("Inserted");
					isAdded = true;//set the return value to true

				}

			}

			//The inner catch block catches the SQL exception.
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
		//the outer catch block catches the general exception
		catch (Exception e) {
			System.out.println("Connection failed");
			e.printStackTrace();
			System.out.println(e);

		}

		/*The code thats placed in the finally block gets executed no matter what. But 
														here the finally block does not contain any general statements*/
		finally {

			// System.out.println("retrieved");
		}

		return isAdded;

	}
	/*this function mainly is to check whether the employee already exists in the employee table
	 * 
	 *  the return type is boolean */
	public static boolean addEmployeeCheck(int UIN) {

		boolean isExisting = false;//create a boolean value for returning. set it to false

		try {
			Connection conn = Database.getConnection();//establish a connection
			String SQLEmployeeSelect = "";

			try {
				//select the UIN
				SQLEmployeeSelect = "Select UIN From employee where UIN=?;";
				PreparedStatement stmt = conn.prepareStatement(SQLEmployeeSelect);
				stmt.setInt(1, UIN);//set the UIN
				ResultSet rs = stmt.executeQuery();//execute the query

				if (rs.first()) {
					//if the resultset exists then return true
					System.out.println(UIN + "already exists");
					return true;
				}

			}
			//The inner catch block catches the SQL exception.
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
		//the outer catch block catches the general exception
		catch (Exception e) {
			System.out.println("Connection failed");
			e.printStackTrace();
			System.out.println(e);

		}
		/*The code thats placed in the finally block gets executed no matter what. But 
															here the finally block does not contain any general statements*/
		finally {

			// System.out.println("retrieved");
		}

		return isExisting;

	}


	/*
	 * updateEmployeeDetauls function takes in the inputs of the the new office address and new office hours
	 * 
	 * checks if it is an employee
	 * 
	 * if updated returns true
	 * 
	 * 
	 * else the function returns false
	 */
	public static boolean updateEmpDetails(int UIN, String officeAddress,String officeHours) throws Student.AccessDeniedException {

		
		if(officeAddress==null){
			return false;
		}
		
		if(officeHours==null){
			return false;
		}
		
		if(officeAddress.length()==0){
			return false;
		}
		
		if(officeHours.length()==0){
			return false;
		}
		
		boolean check = checkIfEmployee(UIN);//check if the UIN passed is an employee
		if (!check) {
			throw new Student.AccessDeniedException();//else access is denied
		}

		boolean isUpdated = false;
		try {
			Connection conn = Database.getConnection();//establish a connection 

			try {

				boolean isExisting = updateEmpDetailscheck(UIN);//check if the employee details already exist

				if (isExisting) {

					// updating the employee details
					System.out.println("Updating the emp details in the database");
					String SQLPeopleInsert = "UPDATE employee SET OfficeAddress=?, OfficeHours=? where UIN=?;";
					PreparedStatement stmt = conn.prepareStatement(SQLPeopleInsert);
					stmt.setString(1, officeAddress);//set the values
					stmt.setString(2, officeHours);
					stmt.setInt(3, UIN);//set the UIN
					System.out.println(stmt);
					int i = stmt.executeUpdate();//execute the query
					
					DBAnnotation.annoate("officeAddress", "employee", "OfficeAddress", false);
					DBAnnotation.annoate("officeHours", "employee", "OfficeHours", false);
					DBAnnotation.annoate("UIN", "employee", "UIN", false);
					
					System.out.println(i);
					System.out.println("Updated");

					Database.commitTransaction(conn);//commit the transaction
					isUpdated = true;
				}

				else {
					//if the resultset doesnt return anything then its not an employee
					System.out.println(UIN + " is not an employee");

				}

			}
			//The inner catch block catches the SQL exception.
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
		//catch block
		catch (Exception e) {
			System.out.println("Connection failed");
			e.printStackTrace();
			System.out.println(e);

		}
		/*The code thats placed in the finally block gets executed no matter what. But 
															here the finally block does not contain any general statements*/
		finally {

			// System.out.println("retrieved");
		}

		return isUpdated;
	}
	
	/*the function is to mainly check if the employee exists in the table
	 * 
	 * the parameter passed is the UIN
	 * 
	 *  the return type is boolean */
	public static boolean updateEmpDetailscheck(int UIN) {

		boolean isUpdated = false;
		try {
			Connection conn = Database.getConnection();//establish connection
			String SQLselectEmp = "";

			try {

				SQLselectEmp = "Select UIN From employee where UIN=?;";
				PreparedStatement stmt = conn.prepareStatement(SQLselectEmp);
				stmt.setInt(1, UIN);
				ResultSet rs = stmt.executeQuery();//execute query

				if (rs.first()) {
					//if exists the return true
					return true;
				}

				else {
					
					System.out.println(UIN + " is not an employee");

				}

			}
			//catch block
			catch (SQLException e) {
				System.out.println("Error adding/updating to database");
				e.printStackTrace();
				System.out.println(e);
			}
			
			//finally block
			finally {
				// System.out.println("retrieved");
				// Database.closeConnection(conn);
			}
		}
		//catch the outer try s general exception
		catch (Exception e) {
			System.out.println("Connection failed");
			e.printStackTrace();
			System.out.println(e);

		}

		/*The code thats placed in the finally block gets executed no matter what. But 
														here the finally block does not contain any general statements*/
		finally {

			// System.out.println("retrieved");
		}

		return isUpdated;
	}

	/*to give a bonus to any employee then the percentage and the UIN is passed
	 * 
	 *  the return type is boolean */
	public static boolean giveBonus(int UIN, double bonusPercent) throws bonusNotValidException, Student.AccessDeniedException {

		boolean check = checkIfEmployee(UIN);//check it its an employee
		if (!check) {
			throw new Student.AccessDeniedException();//throw an exception if its not an employee
		}

		boolean giveBonus = false;
		//if the bonus percent is not appropriate then throw an exception
		if (bonusPercent < 5.0 || bonusPercent > 30.0) {
			throw new bonusNotValidException();
		}

		try {
			Connection conn = Database.getConnection();//get the connection

			try {

				boolean isExisting = giveBonusCheck(UIN);//checking if the UIn exists in the batabase

				if (isExisting) {

					double retreivedSalaryForBonus = getsalary(UIN);//get the salary of the employee

					if (retreivedSalaryForBonus != -1) {
						//calculate the new salary
						double newSalary = (retreivedSalaryForBonus + ((retreivedSalaryForBonus * bonusPercent) / 100));


						System.out.println("Updating the emp details in the database");
						String SQLPeopleInsert = "UPDATE employee SET Salary=? where UIN=?;";
						PreparedStatement stmt = conn.prepareStatement(SQLPeopleInsert);
						stmt.setDouble(1, newSalary);//set the new salary
						stmt.setInt(2, UIN);//for the UIN
						System.out.println(stmt);
						int i = stmt.executeUpdate();//execute the query
						
						DBAnnotation.annoate("newSalary", "employee", "Salary", false);
						DBAnnotation.annoate("UIN", "employee", "UIN", false);
						
						System.out.println(i);
						System.out.println("Updated");

						Database.commitTransaction(conn);
						giveBonus = true;
					}

				}

				else {
					//if the resultset is empty then not an employee
					System.out.println(UIN + " is not an employee");

				}

			}
			//The inner catch block catches the SQL exception.
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
		//the outer catch block catches the general exception
		catch (Exception e) {
			System.out.println("Connection failed");
			e.printStackTrace();
			System.out.println(e);

		}
		/*The code thats placed in the finally block gets executed no matter what. But 
														here the finally block does not contain any general statements*/
		finally {

			// System.out.println("retrieved");
		}

		return giveBonus;
	}
	
	/*to give a bonus to any employee then the salary should be retrieved then this function is called
	 * 
	 * the UIN is passed
	 * 
	 *  the return type is double */
	public static double getsalary(int UIN) {

		try {
			Connection conn = Database.getConnection();//establish the salary
			String SQLselectEmp = "";

			try {

				SQLselectEmp = "Select Salary From employee where UIN=?;";
				PreparedStatement stmt = conn.prepareStatement(SQLselectEmp);
				stmt.setInt(1, UIN);//set the UIN
				ResultSet rs = stmt.executeQuery();//execute the query

				if (rs.first()) {
					//retrieve the salary
					DBAnnotation.annoate("retreivedSalary", "employee", "Salary", true);
					double retreivedSalary = rs.getDouble("Salary");
					
					return retreivedSalary;

				}

				else {
					//not an employee
					System.out.println(UIN + " is not an employee");

				}

			}
			//The inner catch block catches the SQL exception.
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
		//the outer catch block catches the general exception
		catch (Exception e) {
			System.out.println("Connection failed");
			e.printStackTrace();
			System.out.println(e);

		}
		/*The code thats placed in the finally block gets executed no matter what. But 
														here the finally block does not contain any general statements*/
		finally {

			// System.out.println("retrieved");
		}

		return -1;
	}

	/*the function is to mainly check if the employee exists in the table
	 * 
	 * the parameter passed is the UIN
	 * 
	 *  the return type is boolean */
	public static boolean giveBonusCheck(int UIN) {

		boolean giveBonusCheck = false;

		try {
			Connection conn = Database.getConnection();//establish a connection
			String SQLselectEmp = "";

			try {

				SQLselectEmp = "Select * From employee where UIN=?;";
				PreparedStatement stmt = conn.prepareStatement(SQLselectEmp);
				stmt.setInt(1, UIN);//set the UIN
				ResultSet rs = stmt.executeQuery();

				if (rs.first()) {
					//if exists then return true
					return true;
				}

				else {

					System.out.println(UIN + " is not an employee");

				}

			}

			//The inner catch block catches the SQL exception.
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
		//the outer catch block catches the general exception
		catch (Exception e) {
			System.out.println("Connection failed");
			e.printStackTrace();
			System.out.println(e);

		}
		/*The code thats placed in the finally block gets executed no matter what. But 
														here the finally block does not contain any general statements*/
		finally {

			// System.out.println("retrieved");
		}

		return giveBonusCheck;
	}
	
	/*The delete function takes in the UIN of the user
	*
	*The return type is boolean*/
	public static boolean deleteFromEmployeeByUIN(int UIN) {

		boolean isDeleted = false;//create a boolean value for returning. set it to false

		try {
			Connection conn = Database.getConnection();//establish a connection
			String SQLPeopleDelete = "";
			try {
				if (conn != null) {

					boolean ifExisting = deleteFromEmployeeByUINCheck(UIN);//check if the emp exists

					if (ifExisting) {

						SQLPeopleDelete = "Delete From employee where UIN=?;";
						PreparedStatement stmt = conn.prepareStatement(SQLPeopleDelete);
						stmt.setInt(1, UIN);//set the UIN
						int rs1 = stmt.executeUpdate();//execute the query
						DBAnnotation.annoate("UIN", "employee", "UIN", false);
						
						System.out.println(rs1);
						System.out.println(UIN + " is deleted");
						isDeleted = true;

					}

					else {
						//emp does not exist
						System.out.println("UIN does not exist");

					}
				}

			}
			//The inner catch block catches the SQL exception.
			catch (SQLException e) {
				System.out.println("Error trying to access the database");
				e.printStackTrace();
				System.out.println(e);
			}

			finally {
				// System.out.println("retrieved");
			}
		}

		//the outer catch block catches the general exception
		catch (Exception e) {
			System.out.println("Connection failed");
			e.printStackTrace();
			System.out.println(e);

		}

		/*The code thats placed in the finally block gets executed no matter what. But 
														here the finally block does not contain any general statements*/
		finally {

			// System.out.println("retrieved");
		}

		return isDeleted;

	}
	/*The function is mainly check if th UIN passed exists in the table or not
	 * 
	 * the return type is boolean
	 * */
	public static boolean deleteFromEmployeeByUINCheck(int UIN) {

		boolean isExisting = false;

		try {
			Connection conn = Database.getConnection();//get connection to db
			String SQLEmployeeSelect = "";
			try {
				if (conn != null) {
					SQLEmployeeSelect = "Select UIN From employee where UIN=?;";
					PreparedStatement stmt = conn.prepareStatement(SQLEmployeeSelect);
					stmt.setInt(1, UIN);//set the UIN
					ResultSet rs = stmt.executeQuery();//execute the query

					if (rs.first()) {
						//if exists then return true
						return true;

					}

					else {
						//else empl does not exist
						System.out.println("UIN does not exist in the employee table");

					}
				}

			}
			//The inner catch block catches the SQL exception.
			catch (SQLException e) {
				System.out.println("Error trying to access the database");
				e.printStackTrace();
				System.out.println(e);
			}

			finally {
				// System.out.println("retrieved");
			}
		}
		
		//the outer catch block catches the general exception
		catch (Exception e) {
			System.out.println("Connection failed");
			e.printStackTrace();
			System.out.println(e);

		}
		
		/*The code thats placed in the finally block gets executed no matter what. But 
																here the finally block does not contain any general statements*/
		finally {

			// System.out.println("retrieved");
		}

		return isExisting;

	}

	/*The delete function takes in the Username of the user
	*
	*The return type is boolean*/
	public static boolean deleteFromEmployeeByUserName(String userName) {

		boolean isDeleted = false;//create a boolean value for returning. set it to false
		try {
			Connection conn = Database.getConnection();//get the connection
			String SQLPeopleDelete = "";
			try {
				if (conn != null) {

					boolean isExisting = deleteFromEmployeeByUserNameCheck(userName);//check if the emp exists

					if (isExisting) {
						int getUINtoDelete = getEmployeeUIN(userName);//retreive the UIN

						SQLPeopleDelete = "Delete From employee where UIN=?;";
						PreparedStatement stmt = conn.prepareStatement(SQLPeopleDelete);
						stmt.setInt(1, getUINtoDelete);//set the UIN
						int rs1 = stmt.executeUpdate();//execute the query
						DBAnnotation.annoate("getUINtoDelete", "employee", "UIN", false);
						
						System.out.println(rs1);
						System.out.println(getUINtoDelete + " is deleted");
						isDeleted = true;//set the return value to be true

					}

					else {
						//the emp does not exist to delete
						System.out.println("UIN does not exist as a employee");

					}
				}

			}
			//The inner catch block catches the SQL exception.
			catch (SQLException e) {
				System.out.println("Error trying to access the database");
				e.printStackTrace();
				System.out.println(e);
			}
			
			finally {
				// System.out.println("retrieved");
			}
		}
		
		//the outer catch block catches the general exception
		catch (Exception e) {
			System.out.println("Connection failed");
			e.printStackTrace();
			System.out.println(e);

		}
		
		/*The code thats placed in the finally block gets executed no matter what. But 
															here the finally block does not contain any general statements*/
		finally {

			// System.out.println("retrieved");
		}

		return isDeleted;
	}

	//get the UIN if the username if the employee is passed
	public static int getEmployeeUIN(String userName) {

		try {
			Connection conn = Database.getConnection();//create a connection
			String SQLUINSelect = "";

			try {
				if (conn != null) {
					SQLUINSelect = "Select UIN From people where Username=?;";
					PreparedStatement stmt = conn.prepareStatement(SQLUINSelect);
					stmt.setString(1, userName);//set the employee
					ResultSet rs = stmt.executeQuery();//execute the query

					if (rs.first()) {

						DBAnnotation.annoate("peopleRetrievedUIN", "people", "UIN", true);
						int peopleRetrievedUIN = rs.getInt("UIN");//retrieve the query

						return peopleRetrievedUIN;//return the UIN

					}

					else {
						//not exists in the emp table
						System.out.println("UIN does not exist as a employee");

					}
				}

			}
			
			//The inner catch block catches the SQL exception.
			catch (SQLException e) {
				System.out.println("Error trying to access the database");
				e.printStackTrace();
				System.out.println(e);
			}

			finally {
				// System.out.println("retrieved");
			}
		}
		//CATCH BLOCK
		catch (Exception e) {
			System.out.println("Connection failed");
			e.printStackTrace();
			System.out.println(e);

		}
		
		/*The code thats placed in the finally block gets executed no matter what. But 
																here the finally block does not contain any general statements*/
		finally {

			// System.out.println("retrieved");
		}

		return -1;

	}
	
	/*this function is to mainly check if the username exists in the table
	 * 
	 *  return true if exists
	 *  
	 *  */
	public static boolean deleteFromEmployeeByUserNameCheck(String userName) {

		boolean isExisting = false;
		try {
			Connection conn = Database.getConnection();//establish a connection
			String SQLPeopleSelect = "";
			try {
				if (conn != null) {
					SQLPeopleSelect = "Select UIN From people where Username=?;";//write the query
					PreparedStatement stmt = conn.prepareStatement(SQLPeopleSelect);
					stmt.setString(1, userName);//set the uIN
					ResultSet rs = stmt.executeQuery();//execute the query

					if (rs.first()) {
						//if exists then return true
						return true;

					}

					else {

						System.out.println("UIN does not exist as a employee");

					}
				}

			}
			//The inner catch block catches the SQL exception.
			catch (SQLException e) {
				System.out.println("Error trying to access the database");
				e.printStackTrace();
				System.out.println(e);
			}

			finally {
				// System.out.println("retrieved");
			}
		}
		

		//the outer catch block catches the general exception
		catch (Exception e) {
			System.out.println("Connection failed");
			e.printStackTrace();
			System.out.println(e);

		}
		/*The code thats placed in the finally block gets executed no matter what. But 
															here the finally block does not contain any general statements*/
		finally {

			// System.out.println("retrieved");
		}

		return isExisting;

	}

	/*retrieve all the employees that belong in a department 
	 * 
	 * when a dept name is passed as a parameter
	 * 
	 * return type is an arraylist of employee objects
	 * 
	 * this thorws an dept does not exist exception*/
	public static ArrayList<Employee> getAllEmployeesByDepartment(String deptName) throws Department.DepartmentDoesNotExistException {

		ArrayList<Employee> getAllEmpDept = new ArrayList<Employee>();//declare the arraylist and initialize it
		try {
			Connection conn = Database.getConnection();//get the conn

			try {
				if (conn != null) {


					String getDeptID = "Select Username"
							+ " FROM employee natural join people natural join department"
							+ " WHERE DepartmentName= ?";

					PreparedStatement statement = conn.prepareStatement(getDeptID);
					statement.setString(1, deptName);//set the dept name
					ResultSet rs = statement.executeQuery();

					while (rs.next()) {

						DBAnnotation.annoate("retreivedProfUserNames", "people", "Username", true);
						String retreivedProfUserNames = rs.getString("Username");//retrieve the usernames
						
						Employee emps = new Employee(retreivedProfUserNames);//pass it to the emp constructor
						getAllEmpDept.add(emps);//add the objects to the array
						
						DBAnnotation.annoate("empUserName", "people", "Name", true);
						String empUserName=emps.getName();
						
						System.out.println(empUserName);

					}
				}
			}
			//catch block
			catch (SQLException e) {
				System.out.println("Error finding the department name ");
				System.out.println(e.getMessage());
				e.printStackTrace();

			}
			//finally block
			finally {
				// Database.commitTransaction(conn);
			}

		}
		//catch block 
		catch (Exception e) {
			System.out.println("Error fetching all the professors of the department ");
			System.out.println(e.getMessage());
			e.printStackTrace();

		}
		
		/*The code thats placed in the finally block gets executed no matter what. But 
														here the finally block does not contain any general statements*/
		finally {
		}

		return getAllEmpDept;
	}

	/*this function is to mainly check if its employee or not
	 * 
	 * passed is a UIN
	 * 
	 * return type is a boolean*/
	public static boolean checkIfEmployee(int UIN) {

		try {
			Connection conn = Database.getConnection();//set the connection
			String SQLEmpSelect = "";
			try {

				if (conn != null) {

					SQLEmpSelect = "Select PositionID From People where UIN=?;";//write the query
				}

				PreparedStatement stmtForSelect = conn.prepareStatement(SQLEmpSelect);
				stmtForSelect.setInt(1, UIN);//set the UIN

				ResultSet rs = stmtForSelect.executeQuery();//Execute the query

				if (rs.first()) {

					DBAnnotation.annoate("peopleRetrievedPositionID", "people", "PositionID", true);
					int peopleRetrievedPositionID = rs.getInt("PositionID");//get the position ID
					
					System.out.println("UIN:" + UIN + " Position ID:"+ peopleRetrievedPositionID);

					if (peopleRetrievedPositionID <= 2 || peopleRetrievedPositionID == 5) 
					//if the position ID is <=2 or 5 then its an employee
					{
						System.out.println("UIN is an employee");
						return true;
					} else {//else not an employee
						System.out.println("UIN exists, but it is not a Employee");
						return false;

					}

				}

				else {
					//else the UIN does not exist in the emplloyee table
					System.out.println("UIN does not exist");
					return false;

				}

			}

			catch (SQLException e) {
				System.out.println(e);

			}

			finally {

				// System.out.println("retrieved");
			}
		}

		catch (Exception e) {
			System.out.println(e);

		}

		finally {

			// System.out.println("retrieved");
		}

		return false;
	}

	/*this function is to mainly check if its employee or not
	 * 
	 * passed is a Username
	 * 
	 * return type is a boolean*/
	public static boolean checkIfEmployee(String userName) {


		if(userName==null){
			return false;
		}
		
		if(userName.length()==0){
			return false;
		}
		
		try {
			Connection conn = Database.getConnection();//get the connection
			String SQLEmpSelect = "";
			try {

				if (conn != null) {

					SQLEmpSelect = "Select PositionID From People where Username=?;";//fire the query
				}

				PreparedStatement stmtForSelect = conn.prepareStatement(SQLEmpSelect);
				stmtForSelect.setString(1, userName);//set the query

				ResultSet rs = stmtForSelect.executeQuery();//execute the query

				if (rs.first()) {

					DBAnnotation.annoate("peopleRetrievedPositionID", "people", "PositionID", true);
					int peopleRetrievedPositionID = rs.getInt("PositionID");
					
					System.out.println("Username:" + userName + " Position ID:"+ peopleRetrievedPositionID);
					/*
					 * Checking here if the position ID id of a professor i.e 2,
					 * UIN exists for students professors, admins TA and
					 * virtually every person existing in the university Check
					 * if the position ID of the passed UIN is of a professor, employee or super admin.
					 */

					if (peopleRetrievedPositionID <= 2|| peopleRetrievedPositionID == 5) {
						System.out.println("UIN is an employee");
						return true;
					} else {
						//else not an employee
						System.out.println("UIN is not an employee");
						return false;

					}
					// System.out.println("Professor UIN exists");

				}

				else {
					//Username is not found
					System.out.println("username does not exist");
					return false;

				}

			}
			//catch sql block
			catch (SQLException e) {
				System.out.println(e);

			}

			finally {

				// System.out.println("retrieved");
			}
		}
		//catch block
		catch (Exception e) {
			System.out.println(e);

		}
		
		/*The code thats placed in the finally block gets executed no matter what. But 
															here the finally block does not contain any general statements*/
		finally {

			// System.out.println("retrieved");
		}

		return false;
	}

	//new bonus not valid added exceptions
	public static class bonusNotValidException extends Exception {
		private static final long serialVersionUID = 1L;
		private String message = null;

		public bonusNotValidException() {
			super();
			this.message = "bonus is not valid";
		}

		public bonusNotValidException(String message) {
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
	 * All employee functions add, update, retrieve are specified in the this class
	 * 
	 * local main class is used for testing functions and specific executions
	 */
	public static void main(String[] args) {

	}
}

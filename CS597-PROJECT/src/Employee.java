
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import javax.xml.crypto.Data;



public class Employee extends People {
	
	protected double salary;
	protected String officeAddress;
	protected String officeHours;

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


	public Employee(int UIN) throws PersonDoesNotExistException {
		super(UIN);
		
		
		try{
			Connection conn = Database.getConnection();
			String SQLPeopleSelect="";
			
			try{
				
				SQLPeopleSelect = "Select * From employee where UIN=?;";
				PreparedStatement stmt = conn.prepareStatement(SQLPeopleSelect);
				stmt.setInt(1, UIN);
				ResultSet rs =  stmt.executeQuery();
				
					if(rs.first()){
				         System.out.println(UIN+"already exists");
				         
				         	int retrievedEmployeeUIN = rs.getInt("UIN");
					        double retrievedEmployeeSalary = rs.getDouble("Salary");
					        String retrievedOfficeAddress = rs.getString("OfficeAddress");
					        String retrievedOfficeHours = rs.getString("OfficeHours");
					     
							
					         this.UIN=retrievedEmployeeUIN;
					         this.salary=retrievedEmployeeSalary;
					         this.officeAddress=retrievedOfficeAddress;
					         this.officeHours=retrievedOfficeHours;
				         
				         
				         
					}
					
					else
					{
						
						System.out.println(UIN+"does not exist");
						throw new PersonDoesNotExistException();
						
					}
					
			}
			
			catch(SQLException e){
				System.out.println("Error adding/updating to database");
				e.printStackTrace();
				System.out.println(e);	
			}
			
		}
		
		catch(Exception e){
			System.out.println("Some Error");
			e.printStackTrace();
			System.out.println(e);
			
		}
		
		finally{
			
			//System.out.println("retrieved");
		}
		
		// TODO Auto-generated constructor stub
	}
	

	public Employee(String name, String userName, int deptID, int positionID) {
		super(name, userName, deptID, positionID);
		// TODO Auto-generated constructor stub
	}

	public Employee(String userName) {
		super(userName);
		// TODO Auto-generated constructor stub
	}

	public static boolean addEmployee(int UIN){
		
		boolean isAdded=false;
		double salary=40000.00;
		String Office_address="to be decided";
		String office_hours="to be decided";
		
		try{
			Connection conn = Database.getConnection();
			
			
			try{
				
				boolean isExisting=addEmployeeCheck(UIN);
				
				if(isExisting){
					return false;
					
					}
					
					else
					{
						
						System.out.println("Adding new data into the database");
						String SQLEmployeeInsert= "Insert into employee (UIN, Salary, OfficeAddress, OfficeHours) Values (?,?,?,?);";
						PreparedStatement stmt = conn.prepareStatement(SQLEmployeeInsert);
						stmt.setInt(1, UIN);
						stmt.setDouble(2, salary);
						stmt.setString(3, Office_address);
						stmt.setString(4, office_hours);
						System.out.println(stmt);
						int i = stmt.executeUpdate();
						System.out.println(i);
						System.out.println("Inserted");
						isAdded=true;
						
					}
					
			}
			
			catch(SQLException e){
				System.out.println("Error adding/updating to database");
				e.printStackTrace();
				System.out.println(e);	
			}
			
			finally{
				//System.out.println("retrieved");
				//Database.closeConnection(conn);
			}
		}
		
		catch(Exception e){
			System.out.println("Connection failed");
			e.printStackTrace();
			System.out.println(e);
			
		}
		
		finally{
			
			//System.out.println("retrieved");
		}
		
		return isAdded;
		
	}
	
	public static boolean addEmployeeCheck(int UIN){
		
		boolean isExisting=false;
		
		try{
			Connection conn = Database.getConnection();
			String SQLEmployeeSelect="";
			
			try{
				
				SQLEmployeeSelect = "Select UIN From employee where UIN=?;";
				PreparedStatement stmt = conn.prepareStatement(SQLEmployeeSelect);
				stmt.setInt(1, UIN);
				ResultSet rs =  stmt.executeQuery();
				
					if(rs.first()){
				         System.out.println(UIN+"already exists");
				         return true;
				         //Insert a update query to update the values of the database....NOT ADD
					}

			}
			
			catch(SQLException e){
				System.out.println("Error adding/updating to database");
				e.printStackTrace();
				System.out.println(e);	
			}
			
			finally{
				//System.out.println("retrieved");
				//Database.closeConnection(conn);
			}
		}
		
		catch(Exception e){
			System.out.println("Connection failed");
			e.printStackTrace();
			System.out.println(e);
			
		}
		
		finally{
			
			//System.out.println("retrieved");
		}
		
		return isExisting;
		
	}
	
	public static boolean updateEmpDetails(int UIN, String officeAddress, String officeHours) throws Student.AccessDeniedException{
		
		boolean check=checkIfEmployee(UIN);
		if(!check){
			throw new Student.AccessDeniedException();
		}
		
		boolean  isUpdated=false;
		try{
			Connection conn = Database.getConnection();
			
			try{
				
				boolean isExisting=updateEmpDetailscheck(UIN);
				
					if(isExisting){
				        
				         //Insert a update query to update the values of the database....NOT ADD
						System.out.println("Updating the emp details in the database");
						String SQLPeopleInsert= "UPDATE employee SET OfficeAddress=?, OfficeHours=? where UIN=?;";
						PreparedStatement stmt = conn.prepareStatement(SQLPeopleInsert);
						stmt.setString(1, officeAddress);
						stmt.setString(2, officeHours);
						stmt.setInt(3, UIN);
						System.out.println(stmt);
						int i = stmt.executeUpdate();
						System.out.println(i);
						System.out.println("Updated");

						Database.commitTransaction(conn);
						isUpdated=true;
					}
					
					else
					{
						
						System.out.println(UIN+" is not an employee");
						
						
					}
					
			}
			
			catch(SQLException e){
				System.out.println("Error adding/updating to database");
				e.printStackTrace();
				System.out.println(e);	
			}
			
			finally{
				//System.out.println("retrieved");
				//Database.closeConnection(conn);
			}
		}
		
		catch(Exception e){
			System.out.println("Connection failed");
			e.printStackTrace();
			System.out.println(e);
			
		}
		
		finally{
			
			//System.out.println("retrieved");
		}
		
		return isUpdated;
	}

	public static boolean updateEmpDetailscheck(int UIN){
		
		boolean  isUpdated=false;
		try{
			Connection conn = Database.getConnection();
			String SQLselectEmp="";
			
			try{
				
				SQLselectEmp = "Select UIN From employee where UIN=?;";
				PreparedStatement stmt = conn.prepareStatement(SQLselectEmp);
				stmt.setInt(1, UIN);
				ResultSet rs =  stmt.executeQuery();
				
					if(rs.first()){
				        
				        return true;
					}
					
					else
					{
						
						System.out.println(UIN+" is not an employee");
						
						
					}
					
			}
			
			catch(SQLException e){
				System.out.println("Error adding/updating to database");
				e.printStackTrace();
				System.out.println(e);	
			}
			
			finally{
				//System.out.println("retrieved");
				//Database.closeConnection(conn);
			}
		}
		
		catch(Exception e){
			System.out.println("Connection failed");
			e.printStackTrace();
			System.out.println(e);
			
		}
		
		finally{
			
			//System.out.println("retrieved");
		}
		
		return isUpdated;
	}
	
	public static boolean giveBonus(int UIN, double bonusPercent) throws bonusNotValidException, Student.AccessDeniedException{
		
		boolean check=checkIfEmployee(UIN);
		if(!check){
			throw new Student.AccessDeniedException();
		}
		
		boolean giveBonus=false;
		
		if(bonusPercent<5.0 || bonusPercent>30.0)
			{
			 throw new bonusNotValidException();			
			 }

		
		try{
			Connection conn = Database.getConnection();
			String SQLselectEmp="";
			
			try{
				
//				SQLselectEmp = "Select Salary From employee where UIN=?;";
//				PreparedStatement stmt = conn.prepareStatement(SQLselectEmp);
//				stmt.setInt(1, UIN);
//				ResultSet rs =  stmt.executeQuery();
				boolean isExisting=giveBonusCheck(UIN);
				
				if(isExisting){
				
						double retreivedSalaryForBonus=getsalary(UIN);
						
					if(retreivedSalaryForBonus!=-1){
						
						
						double newSalary=(retreivedSalaryForBonus+((retreivedSalaryForBonus*bonusPercent)/100));

				         //Insert a update query to update the values of the database....NOT ADD
						System.out.println("Updating the emp details in the database");
						String SQLPeopleInsert= "UPDATE employee SET Salary=? where UIN=?;";
						PreparedStatement stmt = conn.prepareStatement(SQLPeopleInsert);
						stmt.setDouble(1, newSalary);
						stmt.setInt(2,UIN);
						System.out.println(stmt);
						int i = stmt.executeUpdate();
						System.out.println(i);
						System.out.println("Updated");
						
						Database.commitTransaction(conn);
						giveBonus=true;
					}
						
				}
				
				
					
					else
					{
						
						System.out.println(UIN+" is not an employee");
						
						
					}
					
			}
			
			catch(SQLException e){
				System.out.println("Error adding/updating to database");
				e.printStackTrace();
				System.out.println(e);	
			}
			
			finally{
				//System.out.println("retrieved");
				//Database.closeConnection(conn);
			}
		}
		
		catch(Exception e){
			System.out.println("Connection failed");
			e.printStackTrace();
			System.out.println(e);
			
		}
		
		finally{
			
			//System.out.println("retrieved");
		}
		
		return giveBonus;
	}
	
	public static double getsalary(int UIN){
		
		
		try{
			Connection conn = Database.getConnection();
			String SQLselectEmp="";
			
			try{
				
				SQLselectEmp = "Select Salary From employee where UIN=?;";
				PreparedStatement stmt = conn.prepareStatement(SQLselectEmp);
				stmt.setInt(1, UIN);
				ResultSet rs =  stmt.executeQuery();
				
					if(rs.first()){
						
						double retreivedSalary=rs.getDouble("Salary");
						return retreivedSalary;

					}
					
					else
					{
						
						System.out.println(UIN+" is not an employee");
						
						
					}
					
			}
			
			catch(SQLException e){
				System.out.println("Error adding/updating to database");
				e.printStackTrace();
				System.out.println(e);	
			}
			
			finally{
				//System.out.println("retrieved");
				//Database.closeConnection(conn);
			}
		}
		
		catch(Exception e){
			System.out.println("Connection failed");
			e.printStackTrace();
			System.out.println(e);
			
		}
		
		finally{
			
			//System.out.println("retrieved");
		}
		
		return -1;
	}

	public static boolean giveBonusCheck(int UIN){
		
		boolean giveBonusCheck=false;
		
		try{
			Connection conn = Database.getConnection();
			String SQLselectEmp="";
			
			try{
				
				SQLselectEmp = "Select * From employee where UIN=?;";
				PreparedStatement stmt = conn.prepareStatement(SQLselectEmp);
				stmt.setInt(1, UIN);
				ResultSet rs =  stmt.executeQuery();
				
					if(rs.first()){
						
						return true;
					}
					
					else
					{
						
						System.out.println(UIN+" is not an employee");
						
						
					}
					
			}
			
			catch(SQLException e){
				System.out.println("Error adding/updating to database");
				e.printStackTrace();
				System.out.println(e);	
			}
			
			finally{
				//System.out.println("retrieved");
				//Database.closeConnection(conn);
			}
		}
		
		catch(Exception e){
			System.out.println("Connection failed");
			e.printStackTrace();
			System.out.println(e);
			
		}
		
		finally{
			
			//System.out.println("retrieved");
		}
		
		return giveBonusCheck;
	}

	public static boolean deleteFromEmployeeByUIN(int UIN){
		
		boolean isDeleted=false;
		
		try{
			Connection conn = Database.getConnection();
			String SQLPeopleDelete="";
			try{
				if(conn != null){	
				
					boolean ifExisting=deleteFromEmployeeByUINCheck(UIN);
				
				

					if(ifExisting)
					{	
				        
						SQLPeopleDelete = "Delete From employee where UIN=?;";
						PreparedStatement stmt = conn.prepareStatement(SQLPeopleDelete);
						stmt.setInt(1, UIN);
						int rs1=stmt.executeUpdate();
						System.out.println(rs1);
						System.out.println(UIN+ " is deleted");
						isDeleted=true;
						
						
					}
					
					else
					{
						
						System.out.println("UIN does not exist");

					}
				}
					
}
			
			catch(SQLException e){
				System.out.println("Error trying to access the database");
				e.printStackTrace();
				System.out.println(e);	
			}
			
			finally{
				//System.out.println("retrieved");
			}
		}
		
		catch(Exception e){
			System.out.println("Connection failed");
			e.printStackTrace();
			System.out.println(e);
			
		}
		
		finally{
			
			//System.out.println("retrieved");
		}
		
			
	return isDeleted;	
					
	}

	public static boolean deleteFromEmployeeByUINCheck(int UIN){
		
		boolean isExisting=false;
		
		try{
			Connection conn = Database.getConnection();
			String SQLEmployeeSelect="";
			try{
				if(conn != null){	
				SQLEmployeeSelect = "Select UIN From employee where UIN=?;";
				PreparedStatement stmt = conn.prepareStatement(SQLEmployeeSelect);
				stmt.setInt(1, UIN);
				ResultSet rs =  stmt.executeQuery();
				
				

					if(rs.first())
					{	
				       return true; 
						
						
					}
					
					else
					{
						
						System.out.println("UIN does not exist in the employee table");

					}
				}
					
}
			
			catch(SQLException e){
				System.out.println("Error trying to access the database");
				e.printStackTrace();
				System.out.println(e);	
			}
			
			finally{
				//System.out.println("retrieved");
			}
		}
		
		catch(Exception e){
			System.out.println("Connection failed");
			e.printStackTrace();
			System.out.println(e);
			
		}
		
		finally{
			
			//System.out.println("retrieved");
		}
		
			
	return isExisting;	
					
	}

	public static boolean deleteFromEmployeeByUserName(String userName){
	
	boolean isDeleted=false;	
	try{
		Connection conn = Database.getConnection();
		String SQLPeopleDelete="";
		try{
			if(conn != null){	
			
				boolean isExisting=deleteFromEmployeeByUserNameCheck(userName);
			
				if(isExisting)
				{	
					int getUINtoDelete=getEmployeeUIN(userName);
					
					
					SQLPeopleDelete = "Delete From employee where UIN=?;";
					PreparedStatement stmt = conn.prepareStatement(SQLPeopleDelete);
					stmt.setInt(1, getUINtoDelete);
					int rs1=stmt.executeUpdate();
					System.out.println(rs1);
					System.out.println(getUINtoDelete+ " is deleted");
					isDeleted=true;
					
				}
				
				else
				{
					
					System.out.println("UIN does not exist as a employee");

				}
			}
				
}
		
		catch(SQLException e){
			System.out.println("Error trying to access the database");
			e.printStackTrace();
			System.out.println(e);	
		}
		
		finally{
			//System.out.println("retrieved");
		}
	}
	
	catch(Exception e){
		System.out.println("Connection failed");
		e.printStackTrace();
		System.out.println(e);
		
	}
	
	finally{
		
		//System.out.println("retrieved");
	}
	
	return isDeleted;
	}
	
	public static int getEmployeeUIN(String userName){
		
			
		try{
			Connection conn = Database.getConnection();
			String SQLUINSelect="";
			
			try{
				if(conn != null){	
				SQLUINSelect = "Select UIN From people where Username=?;";
				PreparedStatement stmt = conn.prepareStatement(SQLUINSelect);
				stmt.setString(1, userName);
				ResultSet rs =  stmt.executeQuery();
				
				

					if(rs.first())
					{	
						
						int peopleRetrievedUIN = rs.getInt("UIN");	
						
						return peopleRetrievedUIN;
						
					}
					
					else
					{
						
						System.out.println("UIN does not exist as a employee");

					}
				}
					
	}
			
			catch(SQLException e){
				System.out.println("Error trying to access the database");
				e.printStackTrace();
				System.out.println(e);	
			}
			
			finally{
				//System.out.println("retrieved");
			}
		}
		
		catch(Exception e){
			System.out.println("Connection failed");
			e.printStackTrace();
			System.out.println(e);
			
		}
		
		finally{
			
			//System.out.println("retrieved");
		}
	
		
	return -1;
				
	}
	
	public static boolean deleteFromEmployeeByUserNameCheck(String userName){
		
		boolean isExisting=false;	
		try{
			Connection conn = Database.getConnection();
			String SQLPeopleSelect="";
			try{
				if(conn != null){	
				SQLPeopleSelect = "Select UIN From people where Username=?;";
				PreparedStatement stmt = conn.prepareStatement(SQLPeopleSelect);
				stmt.setString(1, userName);
				ResultSet rs =  stmt.executeQuery();
				
				

					if(rs.first())
					{	
						
						return true;
						
					}
					
					else
					{
						
						System.out.println("UIN does not exist as a employee");

					}
				}
					
	}
			
			catch(SQLException e){
				System.out.println("Error trying to access the database");
				e.printStackTrace();
				System.out.println(e);	
			}
			
			finally{
				//System.out.println("retrieved");
			}
		}
		
		catch(Exception e){
			System.out.println("Connection failed");
			e.printStackTrace();
			System.out.println(e);
			
		}
		
		finally{
			
			//System.out.println("retrieved");
		}
		
			
		return isExisting;
					
		}
	
	public static ArrayList<Employee> getAllEmployeesByDepartment(String deptName) throws Department.DepartmentDoesNotExistException{
	
		ArrayList<Employee> getAllEmpDept=new ArrayList<Employee>();
		try{
			Connection conn = Database.getConnection();
			
			try{
				if(conn != null){
					
					int retreivedDepartmentID=0;
					
						String getDeptID = "Select Username"
								+ " FROM university.employee natural join university.people natural join university.department"
								+ " WHERE DepartmentName= ?";
						
						PreparedStatement statement = conn.prepareStatement(getDeptID);
						statement.setString(1, deptName);
						ResultSet rs = statement.executeQuery();
						
						while(rs.next()){
							
							String retreivedProfUserNames=rs.getString("Username");
							//System.out.println(retreivedProfUserNames);
							Employee emps=new Employee(retreivedProfUserNames);
							getAllEmpDept.add(emps);
							System.out.println(emps.getName());
							
							}
					}
			}
					
					catch(SQLException e){
						System.out.println("Error finding the department name ");
						System.out.println(e.getMessage());
						e.printStackTrace();
	
					}
					
					
					
					finally{
						//Database.commitTransaction(conn);
					}
					
			}
			
			catch(Exception e){
				System.out.println("Error fetching all the professors of the department ");
				System.out.println(e.getMessage());
				e.printStackTrace();
				
			}
				
		
		
		finally{
		}
		
	
		return getAllEmpDept;
	}
	
	public static boolean checkIfEmployee(int UIN){
		
		try{
			Connection conn = Database.getConnection();
			String SQLPeopleSelect="";
			try{
			
				if(conn != null){
					
					SQLPeopleSelect = "Select PositionID From People where UIN=?;";
				}
				
				
				
				PreparedStatement stmtForSelect = conn.prepareStatement(SQLPeopleSelect);
				stmtForSelect.setInt(1, UIN);
				
				ResultSet rs =  stmtForSelect.executeQuery();
					
					if(rs.first())
					{
						
				         int peopleRetrievedPositionID = rs.getInt("PositionID");
				         System.out.println("UIN:"+UIN+" Position ID:"+peopleRetrievedPositionID);
				         
				         
				         if(peopleRetrievedPositionID <=2 || peopleRetrievedPositionID == 5){
				        	 System.out.println("UIN is an employee");
				        	 return true;
				         }
				         else 
				         {
				        	 System.out.println("UIN exists, but it is not a Employee");
				        	 return false;
						
				         }
				         

				         
					}
					
					else
					{
						
						System.out.println("UIN does not exist");
						return false;

					}
					
				
			
		
	}
			
			catch(SQLException e){
				System.out.println(e);
				
			}
			
			finally{
				
				//System.out.println("retrieved");
			}
		}
		
		catch(Exception e){
			System.out.println(e);
			
		}
		
		finally{
			
			//System.out.println("retrieved");
		}		
		
		return false;
	}
	
	public static boolean checkIfEmployee(String userName){
		
		try{
			Connection conn = Database.getConnection();
			String SQLProfSelect="";
			try{
			
				if(conn != null){
					
					SQLProfSelect = "Select PositionID From People where Username=?;";
				}
				
				
				
				PreparedStatement stmtForSelect = conn.prepareStatement(SQLProfSelect);
				stmtForSelect.setString(1, userName);
				
				ResultSet rs =  stmtForSelect.executeQuery();
					
					if(rs.first())
					{
						
						 int peopleRetrievedPositionID = rs.getInt("PositionID");
				         System.out.println("Username:"+userName+" Position ID:"+peopleRetrievedPositionID);
				         /*Check here if the position ID id of a professor i.e 2, UIN exists for students
				          * professors, admins TA and virtually every person existing in the university
				          * Check if the position ID of the passed UIN is of a professor.
				          */
				         
				         if(peopleRetrievedPositionID <=2 || peopleRetrievedPositionID == 5)
				         {
				        	 System.out.println("UIN is an employee");
				        	 return true;
				         }
				         else {
				        	 System.out.println("UIN is not an employee");
				        	 return false;
				        	 
				         }
				         //System.out.println("Professor UIN exists");


				         
					}
					
					else
					{
						
						System.out.println("username does not exist");
						return false;

					}
					
				
			
		
	}
			
			catch(SQLException e){
				System.out.println(e);
				
			}
			
			finally{
				
				//System.out.println("retrieved");
			}
		}
		
		catch(Exception e){
			System.out.println(e);
			
		}
		
		finally{
			
			//System.out.println("retrieved");
		}		
		
		return false;
	}
	
	
	static class bonusNotValidException extends Exception{
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
	
	
	public static void main(String[] args){
		
//		try {
//			giveBonus(354, 30.0);
//		} catch (bonusNotValidException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		//updateEmpDetails(354, "sim", "sim");
		
		//deleteFromDatabaseByUserName("arihant");

//		try {
//			ArrayList<Employee> emp=Employee.getAllEmployeesByDepartment("Duis A LLP");
//		} catch (Department.DepartmentDoesNotExistException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		boolean c=checkIfEmployee(451);
		System.out.println(c);
		
		try {
			boolean check=updateEmpDetails(451, "ss", "ss");
		} catch (Student.AccessDeniedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
	
	


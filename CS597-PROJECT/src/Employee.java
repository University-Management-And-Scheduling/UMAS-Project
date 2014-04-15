import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;




public class Employee extends People implements EmployeeInterface {

	public Employee(int UIN) {
		super(UIN);
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

	public static void addEmployee(int UIN){
		
		double salary=40000.00;
		String Office_address="to be decided";
		String office_hours="to be decided";
		
		try{
			Connection conn = Database.getConnection();
			String SQLPeopleSelect="";
			
			try{
				
				SQLPeopleSelect = "Select UIN From employee where UIN=?;";
				PreparedStatement stmt = conn.prepareStatement(SQLPeopleSelect);
				stmt.setInt(1, UIN);
				ResultSet rs =  stmt.executeQuery();
				
					if(rs.first()){
				         System.out.println(UIN+"already exists. Please choose another UIN");
				         //Insert a update query to update the values of the database....NOT ADD
					}
					
					else
					{
						
						System.out.println("Adding new data into the database");
						String SQLPeopleInsert= "Insert into employee (UIN, Salary, OfficeAddress, OfficeHours) Values (?,?,?,?);";
						stmt = conn.prepareStatement(SQLPeopleInsert);
						stmt.setInt(1, UIN);
						stmt.setDouble(2, salary);
						stmt.setString(3, Office_address);
						stmt.setString(4, office_hours);
						System.out.println(stmt);
						int i = stmt.executeUpdate();
						System.out.println(i);
						System.out.println("Inserted");
						
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
		
		
	}
	
public static void deleteFromDatabaseByUIN(int UIN){
		
		try{
			Connection conn = Database.getConnection();
			String SQLPeopleSelect="";
			String SQLPeopleDelete="";
			try{
				if(conn != null){	
				SQLPeopleSelect = "Select UIN From employee where UIN=?;";
				PreparedStatement stmt = conn.prepareStatement(SQLPeopleSelect);
				stmt.setInt(1, UIN);
				ResultSet rs =  stmt.executeQuery();
				
				

					if(rs.first())
					{	
				         //this.UIN=peopleRetrievedUIN;
				         //this.name=peopleRetrievedName;
				         //this.userName=peopleRetrieveduserName;
				         //this.deptID=peopleRetrievedDeptID;
				         //this.positionID=peopleRetrievedPositionID;
						
						int peopleRetrievedUIN = rs.getInt("UIN");
//						String peopleRetrievedName = rs.getString("Name");
//						String peopleRetrieveduserName = rs.getString("UserName");
//						int peopleRetrievedDeptID = rs.getInt("DepartmentID");
//						int peopleRetrievedPositionID = rs.getInt("PositionID");
				         
//				         System.out.println(peopleRetrievedUIN);
//				         System.out.println(peopleRetrievedName);
//				         System.out.println(peopleRetrieveduserName);
//				         System.out.println(peopleRetrievedDeptID);
//				         System.out.println(peopleRetrievedPositionID);
						
						SQLPeopleDelete = "Delete From employee where UIN=?;";
						stmt = conn.prepareStatement(SQLPeopleDelete);
						stmt.setInt(1, UIN);
						int rs1=stmt.executeUpdate();
						System.out.println(rs1);
						System.out.println(peopleRetrievedUIN+ " is deleted");
						
						
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
		
			
		
					
	}

	public static void deleteFromDatabaseByUIN(String userName){
	
	try{
		Connection conn = Database.getConnection();
		String SQLPeopleSelect="";
		String SQLPeopleDelete="";
		try{
			if(conn != null){	
			SQLPeopleSelect = "Select UIN From people where Username=?;";
			PreparedStatement stmt = conn.prepareStatement(SQLPeopleSelect);
			stmt.setString(1, userName);
			ResultSet rs =  stmt.executeQuery();
			
			

				if(rs.first())
				{	
					
					int peopleRetrievedUIN = rs.getInt("UIN");	
					
					SQLPeopleDelete = "Delete From employee where UIN=?;";
					stmt = conn.prepareStatement(SQLPeopleDelete);
					stmt.setInt(1, peopleRetrievedUIN);
					int rs1=stmt.executeUpdate();
					System.out.println(rs1);
					System.out.println(peopleRetrievedUIN+ " is deleted");
					
					
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
	
		
	
				
	}
	
		
}
	
	


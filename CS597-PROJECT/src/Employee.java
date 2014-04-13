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
		
	}
	
	


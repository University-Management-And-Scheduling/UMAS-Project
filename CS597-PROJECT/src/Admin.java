import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;



public class Admin extends Employee {

	public Admin(int UIN) {
		super(UIN);
		// TODO Auto-generated constructor stub
	}
	
	public static boolean addAdmin(String name, Department dept) throws loginDetailsnotAdded{
		
		boolean isAdded=false;
		
		if(dept==null){
			
			return false;
		}
		
		int addedUIN=Employee.addIntoDatabase(name, dept, 1);
		
		
		boolean isAddedtoEmp=Employee.addEmployee(addedUIN);
		
		if(isAddedtoEmp)
			isAdded=true;
			
		
		
		
		
	return isAdded;	
	}
	
	public static ArrayList<Admin> getAllAdmin() {
		//if(Professor == null)
			//throw new NullPointerException();
		
		ArrayList<Admin> getAllAdmin = new ArrayList<Admin>();
		
		try{
			Connection conn = Database.getConnection();
			
			try{
				if(conn != null){
					
					
					
					//Retrieve all the professors from one department
					String adminSelect = "Select *"
							+ " FROM university.people"
							+ " WHERE PositionID=1";
					PreparedStatement statement = conn.prepareStatement(adminSelect);
					ResultSet rs = statement.executeQuery();
					
					while(rs.next()){
						
						
						int retreivedAdminUIN=rs.getInt("UIN");
						//System.out.println(retreivedProfUserNames);
						Admin admins=new Admin(retreivedAdminUIN);
						getAllAdmin.add(admins);
						System.out.println(admins.getUserName());						
					}
					
				}
					
			}
			
			catch(SQLException e){
				System.out.println("Error fetching all the professors");
				System.out.println(e.getMessage());
				e.printStackTrace();
				
			}
			
				
			finally{
				//Database.commitTransaction(conn);
			}
			
		
			return getAllAdmin;
		}
		
		finally{
		}
		
	}
	

	public boolean updateAdminUserName(String userName){
		
		boolean isUpdated=false;
		
		try{
			Connection conn = Database.getConnection();
			
			try{
				
				boolean ifAddedInLogin=People.updateUserNameIntoLoginTable(userName, this.getUserName());
				if(ifAddedInLogin)
					isUpdated=true;
					
			}
			
			catch(Exception e){
				System.out.println("Error adding/updating to database");
				e.printStackTrace();
				System.out.println(e);	
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
	

	public boolean updateAdminName(String name){
		
		boolean isUpdated=false;
		
		try{
			Connection conn = Database.getConnection();
			
			try{
				
				boolean ifUpdatedInLogin=People.updateNameIntoPeopleTable(name, this.getUIN());
				if(ifUpdatedInLogin)
					isUpdated=true;
				
					
			}
			
			catch(Exception e){
				System.out.println("Error adding/updating to database");
				e.printStackTrace();
				System.out.println(e);	
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
	
public boolean updateAdminDept(int deptID){
		
		boolean isUpdated=false;
		
		try{
			Connection conn = Database.getConnection();
			
			try{
				
				boolean ifUpdatedInPeople=People.updateDeptIntoPeopleTable(deptID, this.getUIN());
				if(ifUpdatedInPeople)
					isUpdated=true;
				
					
			}
			
			catch(Exception e){
				System.out.println("Error adding/updating to database");
				e.printStackTrace();
				System.out.println(e);	
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
	
	public static void main(String[] args){
		
		
		//getAllAdmin();
		
		Admin ad= new Admin(1);
		
		ad.updateAdminDept(1);
		
		
	}
	

}

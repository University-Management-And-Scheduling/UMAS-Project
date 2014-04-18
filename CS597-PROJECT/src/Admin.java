import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



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
	

}

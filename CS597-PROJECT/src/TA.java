import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Statement;





public class TA extends Student {
	
	
	public TA(int UIN) {
		super(UIN);
		// TODO Auto-generated constructor stub
	}
	
	public static boolean addTAToEmployee(int UIN, int offerID) throws AlreadyExistsInEmployeeException{
		
		boolean isAdded=false;
		double salary=40000.00;
		String Office_address="to be decided";
		String office_hours="to be decided";
		//boolean isCurrent=false;
		
		
		
		//CourseOffered offerIDcheck;
		
		//try {
			//offerIDcheck = new CourseOffered(offerID);
			//isCurrent=offerIDcheck.checkIfCurrent();
		//} catch (Course.CourseDoesNotExistException
			//	| CourseOffered.CourseOfferingDoesNotExistException e1) {
		//	return false;
	//	}
		
		//if(isCurrent){			
			try{
				Connection conn = Database.getConnection();
				
				
				try{
					
					boolean alreadyExists=addTAToEmployeeCheck(UIN, offerID);
					
					if(alreadyExists){
						
						throw new AlreadyExistsInEmployeeException();

					         
						}
						
						else
						{
							
							System.out.println("Adding new data into the database");
							String SQLPeopleInsert= "Insert into employee (UIN, Salary, OfficeAddress, OfficeHours) Values (?,?,?,?);";
							PreparedStatement stmt = conn.prepareStatement(SQLPeopleInsert);
							stmt = conn.prepareStatement(SQLPeopleInsert);
							stmt.setInt(1, UIN);
							stmt.setDouble(2, salary);
							stmt.setString(3, Office_address);
							stmt.setString(4, office_hours);
							System.out.println(stmt);
							int i = stmt.executeUpdate();
							System.out.println(i);
							System.out.println("Inserted");
							
							
							isAdded=addTAtoTAtable(UIN, offerID);
							
							if(isAdded)
								isAdded=true;
						}
						
				}
				
				catch(SQLException e){
					System.out.println("Error adding/updating to database");
					e.printStackTrace();
					System.out.println(e);	
				} 
				
				
				catch (CourseOffered.CourseOfferingNotCurrentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				finally{
					//System.out.println("retrieved");
					//Database.closeConnection(conn);
				}
			}
			
			
			
			finally{
				
				//System.out.println("retrieved");
			}

	//	}
		
	//	else{
		//	throw new CourseOffered.CourseOfferingNotCurrentException();
	//	}
		
		return isAdded;
		
	}
	
	
	public static boolean addTAToEmployeeCheck(int UIN, int offerID) throws CourseOffered.CourseOfferingNotCurrentException{
		
		boolean isExisting=false;
		
		
		CourseOffered offerIDcheck;
		boolean isCurrent=false;
		
		try {
			offerIDcheck = new CourseOffered(offerID);
			isCurrent=offerIDcheck.checkIfCurrent();
		} catch (Course.CourseDoesNotExistException
				| CourseOffered.CourseOfferingDoesNotExistException e1) {
			return false;
		}
		
		if(isCurrent){			
			try{
				Connection conn = Database.getConnection();
				String SQLPeopleSelect="";
				
				try{
					
					SQLPeopleSelect = "Select UIN From employee where UIN=?;";
					PreparedStatement stmt = conn.prepareStatement(SQLPeopleSelect);
					stmt.setInt(1, UIN);
					ResultSet rs =  stmt.executeQuery();
					System.out.println(stmt);
					
						if(rs.first()){
							
							//int retreivedPositionID=rs.getInt("PositionID");
							//System.out.println("Position ID: "+retreivedPositionID);
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

		}
		
		else{
			throw new CourseOffered.CourseOfferingNotCurrentException();
		}
		
		return isExisting;
		
	}
	
	public static boolean addTAtoTAtable(int UIN, int offerID){
		
		boolean isAdded=false;
		
		
		try{
			Connection conn = Database.getConnection();
			
			try{
				
				boolean ifExists=addTAtoTAtableCheck(UIN, offerID);
				
					if(ifExists){
				        return false;
				         //Insert a update query to update the values of the database....NOT ADD
					}
					
					else
					{
						
						System.out.println("Adding new data into the database");
						String SQLPeopleInsert= "Insert into teachingassistant (TaUIN, OfferID) Values (?,?);";
						PreparedStatement stmt = conn.prepareStatement(SQLPeopleInsert);
						stmt.setInt(1, UIN);
						stmt.setInt(2, offerID);
						int i = stmt.executeUpdate();
						System.out.println(i);
						System.out.println("Inserted");
						isAdded=true;
						
						Database.commitTransaction(conn);
						
						
						
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

	public static boolean addTAtoTAtableCheck(int UIN, int offerID){
		
		boolean isExisting=false;
		
		
		try{
			Connection conn = Database.getConnection();
			String SQLPeopleSelect="";
			
			try{
				
				SQLPeopleSelect = "Select TaUIN From teachingassistant where OfferID=?;";
				PreparedStatement stmt = conn.prepareStatement(SQLPeopleSelect);
				stmt.setInt(1, UIN);
				ResultSet rs =  stmt.executeQuery();
				
					if(rs.first()){
				         System.out.println(UIN+"already exists as a TA for the offer ID: "+offerID);
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

	public static ArrayList<TA> getAllTAs() {
		//if(Professor == null)
			//throw new NullPointerException();
		
		ArrayList<TA> getAllTAs = new ArrayList<TA>();
		
		try{
			Connection conn = Database.getConnection();
			
			try{
				if(conn != null){
					
					
					
					//Retrieve all the professors from one department
					String ProfessorSelect = "Select *"
							+ " FROM university.people"
							+ " WHERE PositionID=4";
					PreparedStatement statement = conn.prepareStatement(ProfessorSelect);
					ResultSet rs = statement.executeQuery();
					
					while(rs.next()){
						
						
						int retreivedTAUIN=rs.getInt("UIN");
						//System.out.println(retreivedProfUserNames);
						TA teachingAssistant=new TA(retreivedTAUIN);
						getAllTAs.add(teachingAssistant);
						System.out.println(teachingAssistant.getUserName());						
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
			
		
			
		}
		
		finally{
		}
		
		return getAllTAs;
		
	}
	
	

	public boolean updateTAUserName(String userName){
		
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

	public boolean updateTAName(String name){
		
		boolean isUpdated=false;
		
		try{
			Connection conn = Database.getConnection();
			
			try{
				
				boolean ifAddedInPeople=People.updateNameIntoPeopleTable(name, this.getUIN());
				if(ifAddedInPeople)
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
	

	public boolean updateTADept(int deptID){
		
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
	
	static class AlreadyExistsInEmployeeException extends Exception{
		private static final long serialVersionUID = 1L;
		private String message = null;
		 
	    public AlreadyExistsInEmployeeException () {
	        super();
	        this.message = "Employee not Added ";
	    }
	    
	    public AlreadyExistsInEmployeeException (String message) {
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
//			addTAToEmployee(449, 300);
//		} catch (AlreadyExistsInEmployeeException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		//getAllTAs();
		
		TA ta=new TA(4);
		
		ta.updateTADept(16);
		
	}

}

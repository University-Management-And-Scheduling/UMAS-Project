import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;



public class Timeslots {
	private int timeSlotID;
	private int startHour;
	private int endHour;
	
	
		
	public static void addTimeSlot(int startHour, int endHour){
		if(!checkHours(startHour, endHour))
			throw new IllegalArgumentException();
		
		
		try{
			Connection conn = Database.getConnection();
			
			try{
				if(conn != null){
					
					//query to retrieve all time slots and check if the time slot already exists
					String findString = "Select TimeSlotID "
							+ "FROM university.timeslots "
							+ "WHERE starthour = ? and endhour = ?";
					PreparedStatement statement = conn.prepareStatement(findString);
					statement.setInt(1, startHour);
					statement.setInt(2, endHour);
					ResultSet rs = statement.executeQuery();
					
					if(rs.first())
						throw new IllegalArgumentException();
					
					else{
						//the new time slot can be added
						System.out.println("Adding new timeslot:"+startHour+" to "+endHour);
						String addString = "INSERT INTO university.timeslots "
								+ "(StartHour, EndHour) "
								+ "Values(?,?);";
						statement = conn.prepareStatement(addString);
						statement.setInt(1, startHour);
						statement.setInt(2, endHour);
						statement.executeUpdate();
						Database.commitTransaction(conn);
					}
				}
			}
			
			catch(Exception e){
				System.out.println("Time slot existing");
				//System.out.println(e.getMessage());
				//e.printStackTrace();
			}
			
			finally{
				//Database.closeConnection(conn);
			}
			
		}
		
		finally{
		}

	}
	
	public static boolean checkHours(int startHour, int endHour){
		if(startHour < 6 || startHour > 20)
			return false;
		if(endHour>21 || endHour < 7)
			return false;
		if((endHour - startHour) < 1)
			return false;
		if((endHour - startHour) > 2)
			return false;
		else 
			return true;
	}
	
	public static void main(String args[]){
		
	}
	
}



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class Timeslots {
	private int timeSlotID;
	private int startHour;
	private int endHour;
	private int timeslotType;
	
	/**
	 * @return the timeSlotID
	 */
	public int getTimeSlotID() {
		return timeSlotID;
	}

	/**
	 * @return the startHour
	 */
	public int getStartHour() {
		return startHour;
	}

	/**
	 * @return the endHour
	 */
	public int getEndHour() {
		return endHour;
	}

	/**
	 * @return the timeslotType
	 */
	public int getTimeslotType() {
		return timeslotType;
	}

	
	public Timeslots(int timeSlotID){
		try{
			Connection conn = Database.getConnection();
			
			try{
				if(conn != null){
					
					//query to retrieve all time slots and check if the time slot already exists
					String findString = "Select TimeSlotID "
							+ "FROM university.timeslots "
							+ "WHERE TimeSlotID= ?";
					PreparedStatement statement = conn.prepareStatement(findString);
					statement.setInt(1, timeSlotID);
					ResultSet rs = statement.executeQuery();
					
					if(rs.first()){
						this.timeSlotID = timeSlotID;
						this.startHour = rs.getInt(2);
						this.endHour = rs.getInt(3);
						this.timeslotType = rs.getInt(4);
					}
					
					else{
						//the timeslot doesnt exist
						throw new IllegalArgumentException("Timeslot does not exist");
					}
				}
			}
			
			catch(Exception e){
				//System.out.println("Error adding timeslot");
				System.out.println(e.getMessage());
				//e.printStackTrace();
			}
			
			finally{
				//Database.closeConnection(conn);
			}
			
		}
		
		finally{
		}
	}

	public Timeslots(int startHour, int endHour){
		try{
			Connection conn = Database.getConnection();
			
			try{
				if(conn != null){
					
					String findString = "Select TimeSlotID "
							+ "FROM university.timeslots "
							+ "WHERE starthour = ? and endhour = ?";
					PreparedStatement statement = conn.prepareStatement(findString);
					statement.setInt(1, startHour);
					statement.setInt(2, endHour);
					ResultSet rs = statement.executeQuery();
					
					if(rs.first()){
						this.timeSlotID = rs.getInt(1);
						this.startHour = rs.getInt(2);
						this.endHour = rs.getInt(3);
						this.timeslotType = rs.getInt(4);
					}
					
					else{
						//the timeslot doesnt exist
						throw new IllegalArgumentException("Timeslot does not exist");
					}
				}
			}
			
			catch(Exception e){
				//System.out.println("Error adding timeslot");
				System.out.println(e.getMessage());
				//e.printStackTrace();
			}
			
			finally{
				//Database.closeConnection(conn);
			}
			
		}
		
		finally{
		}
	}
	
	public static void addTimeSlot(int startHour, int endHour, int type) throws IllegalArgumentException{
		if(!areHoursCorrect(startHour, endHour) || !isTypeCorrect(startHour,endHour,type))
			throw new IllegalArgumentException("Arguments incorrect");
		
		
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
						throw new IllegalArgumentException("Already existing timeslot");
					
					else{
						//the new time slot can be added
						System.out.println("Adding new timeslot:"+startHour+" to "+endHour);
						String addString = "INSERT INTO university.timeslots "
								+ "(StartHour, EndHour, TimeslotType) "
								+ "Values(?,?,?);";
						statement = conn.prepareStatement(addString);
						statement.setInt(1, startHour);
						statement.setInt(2, endHour);
						statement.setInt(3, type);
						statement.executeUpdate();
						Database.commitTransaction(conn);
					}
				}
			}
			
			catch(Exception e){
				//System.out.println("Error adding timeslot");
				System.out.println(e.getMessage());
				//e.printStackTrace();
			}
			
			finally{
				//Database.closeConnection(conn);
			}
			
		}
		
		finally{
		}

	}
	
	public static boolean areHoursCorrect(int startHour, int endHour){
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
	
	public static boolean isTypeCorrect(int startHour, int endHour, int type){
		boolean flag = false;
		
		if(type != 1 && type != 2)
			flag = false;
		
		if(type == 1){
			if(endHour - startHour != 1)
				flag = false;
			else 
				flag = true;
		}
		
		if(type == 2){
			if(endHour - startHour != 2)
				flag = false;
			else 
				flag = true;
		}
		
		return flag;
	}
	
	public static void main(String args[]){
	}
	
}



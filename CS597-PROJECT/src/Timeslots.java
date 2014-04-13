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
					String findString = "Select * "
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
	
	public static boolean isConflict(Timeslots t1, Timeslots t2){
		int t1Type = t1.getTimeslotType();
		int t2Type = t2.getTimeslotType();
		
		if(t1Type != t2Type){
			return false;
		}
		
		int t1s = t1.getStartHour();
		int t1e = t1.getEndHour();
		int t2s = t2.getStartHour();
		int t2e = t2.getEndHour();
		
		if(t1s == t2s || t1e == t2e)
			return true;
		if(isInBetween(t1s, t1e, t2s))
			return true;
		if(isInBetween(t1s, t1e, t2e))
			return true;
		if(isInBetween(t2s, t2e, t1s))
			return true;
		if(isInBetween(t2s, t2e, t1e))
			return true;
		
		
		return false;
	}
	
	public static boolean isInBetween(int start, int end, int toCheck){
		if(toCheck<end && toCheck>start){
			return true;
		}
		
		else
			return false;
		
		
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
		Timeslots t1 = new Timeslots(31);
		Timeslots t2 = new Timeslots(38);
		System.out.println(isConflict(t1, t2));
	}
	
}



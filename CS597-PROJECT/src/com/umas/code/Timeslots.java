package com.umas.code;


/****************@author Simant Purohit*********************************/

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


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

	/*
	 * Initializes the timeslot object using the timeslot id
	 * Throws a illegal argument exception if the timeslot with the passed id does not exist 
	 */
	public Timeslots(int timeSlotID){
		try{
			Connection conn = Database.getConnection();
			
			try{
				if(conn != null){
					
					/*
					 * query to retrieve all time slots and check if the time slot already exists
					 */
					String findString = "Select * "
							+ "FROM timeslots "
							+ "WHERE TimeSlotID= ?";
					PreparedStatement statement = conn.prepareStatement(findString);
					statement.setInt(1, timeSlotID);
					ResultSet rs = statement.executeQuery();
					
					/*
					 * TIme slot exists, initialize all the instance variables
					 */
					if(rs.first()){
						DBAnnotation.annoate("startHour", "timeslots", "StartHour", true);
						int startHour = rs.getInt("StartHour");
						
						DBAnnotation.annoate("endHour", "timeslots", "EndHour", true);
						int endHour = rs.getInt("EndHour");
						
						DBAnnotation.annoate("timeSlotType", "timeslots", "TimeslotType", true);
						int timeSlotType = rs.getInt("TimeslotType");
						
						this.timeSlotID = timeSlotID;
						this.startHour = startHour;
						this.endHour = endHour;
						this.timeslotType = timeSlotType;
					}
					
					else{
						/*
						 * the time slot doesn't exist
						 */
						throw new IllegalArgumentException("Timeslot does not exist");
					}
				}
			}
			
			catch(SQLException e){
				/*
				 * Catches any exception related to the sql query
				 */
				System.out.println(e.getMessage());
			}
						
		}
		
		finally{
		}
	}
	
	/*
	 * Initialize a time sllot by using the start and end hour arguments
	 * If the timeslot combination does not exist, a exception is thrown else the object is initialized
	 * 
	 */
	public Timeslots(int startHour, int endHour){
		try{
			Connection conn = Database.getConnection();
			
			try{
				if(conn != null){
					
					String findString = "Select *"
							+ "FROM timeslots "
							+ "WHERE starthour = ? and endhour = ?";
					PreparedStatement statement = conn.prepareStatement(findString);
					statement.setInt(1, startHour);
					statement.setInt(2, endHour);
					ResultSet rs = statement.executeQuery();
					
					/*
					 * if the time slot exists, initialize the instance variables
					 */
					if(rs.first()){
						DBAnnotation.annoate("startHour", "timeslots", "StartHour", true);
						int timeslotID = rs.getInt("TimeslotID");
						
						DBAnnotation.annoate("startHour", "timeslots", "StartHour", true);
						int sHour = rs.getInt("StartHour");
						
						DBAnnotation.annoate("endHour", "timeslots", "EndHour", true);
						int eHour = rs.getInt("EndHour");
						
						DBAnnotation.annoate("timeSlotType", "timeslots", "TimeslotType", true);
						int timeSlotType = rs.getInt("TimeslotType");
						
						this.timeSlotID = timeslotID;
						this.startHour = sHour;
						this.endHour = eHour;
						this.timeslotType = timeSlotType;
					}
					
					else{
						/*
						 * the timeslot doesnt exist
						 */
						throw new IllegalArgumentException("Timeslot does not exist");
					}
				}
			}
			
			catch(SQLException e){
				System.out.println(e.getMessage());
			}
			
			
		}
		
		finally{
		}
	}
	
	/*
	 * Add the time slot with the specified start hor and end hour and the time slot type to the database
	 */
	public static boolean addTimeSlot(int startHour, int endHour, int type) throws IllegalArgumentException{
		/*
		 * Checks the format and the length of hours and type of time slot along with the hours
		 * Time slot type 1 is a one hour long time slot an type 2 is a 2 hour long time slots
		 * If the checks are passed, the program proceeds with adding the time slots to the database
		 */
		if(!areHoursCorrect(startHour, endHour) || !isTypeCorrect(startHour,endHour,type))
			throw new IllegalArgumentException("Arguments incorrect");
		
		boolean isAdded = true;
		
		try{
			Connection conn = Database.getConnection();
			
			try{
				if(conn != null){
					
					//query to retrieve all time slots and check if the time slot already exists
					String findString = "Select * "
							+ "FROM timeslots "
							+ "WHERE starthour = ? and endhour = ?";
					PreparedStatement statement = conn.prepareStatement(findString);
					statement.setInt(1, startHour);
					statement.setInt(2, endHour);
					ResultSet rs = statement.executeQuery();
					
					if(rs.first()){
						/*
						 * time slot with similar parameters already exists, hence the exception
						 */
						throw new IllegalArgumentException("Already existing timeslot");
					}
					
					else{
						/*
						 * the new time slot can be added as all the pre-conditions are satisfied
						 */
						System.out.println("Adding new timeslot:"+startHour+" to "+endHour);
												
						String addString = "INSERT INTO timeslots "
								+ "(StartHour, EndHour, TimeslotType) "
								+ "Values(?,?,?);";
						statement = conn.prepareStatement(addString);
						statement.setInt(1, startHour);
						statement.setInt(2, endHour);
						statement.setInt(3, type);
						statement.executeUpdate();
						
						DBAnnotation.annoate("startHour", "timeslots", "StartHour", false);
						DBAnnotation.annoate("endHour", "timeslots", "EndHour", false);
						DBAnnotation.annoate("type", "timeslots", "TimeslotType", false);
						
						Database.commitTransaction(conn);
						isAdded = true;
					}
				}
			}
			
			catch(SQLException e){
				System.out.println(e.getMessage());
				
			}
			
		}
		
		finally{
		}
		
		return isAdded;

	}
	
	/*
	 * Checks if the start and end hours mentioned are correct
	 */
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
	
	/*
	 * Checks if any time conflict exists between two specified time slot objects
	 */
	public static boolean isConflict(Timeslots t1, Timeslots t2){
		int t1Type = t1.getTimeslotType();
		int t2Type = t2.getTimeslotType();
		
		//System.out.println("Looking for conflict between timeslots "+t1+" and:"+t2);
		
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
	
	/*
	 * Checks if the specified time slot (tocheck) falls between the start and end hours
	 */
	public static boolean isInBetween(int start, int end, int toCheck){
		if(toCheck<end && toCheck>start){
			return true;
		}
		
		else
			return false;
		
		
	}

	/*
	 * Checks if the type of time slot mentioned is either 1 or two
	 * Also checks if the time slot type 1 is one hour time slot and time slot 2 is a two hour time slot
	 */
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
	
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		String toReturn="";
		toReturn+="\nStart Hour:"+this.getStartHour();
		toReturn+="\nEnd Hour:"+this.getEndHour();
		toReturn+="\nTimeslot ID:"+this.getTimeSlotID();
		toReturn+="\nTimeslot Type:"+this.getTimeslotType();
		return toReturn;
	}
		
}



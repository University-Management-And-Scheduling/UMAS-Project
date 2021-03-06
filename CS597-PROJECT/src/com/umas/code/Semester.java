package com.umas.code;


/****************@author Simant Purohit*********************************/

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;

public class Semester {
	private int SemesterID;
	private String SemesterName;
	private Timestamp SemesterYear;
	private int isCurrent;
	/**
	 * @return the semesterID
	 */
	public int getSemesterID() {
		return SemesterID;
	}
	/**
	 * @param semesterID the semesterID to set
	 */
	public void setSemesterID(int semesterID) {
		SemesterID = semesterID;
	}
	/**
	 * @return the semesterName
	 */
	public String getSemesterName() {
		return SemesterName;
	}
	/**
	 * @param semesterName the semesterName to set
	 */
	public void setSemesterName(String semesterName) {
		SemesterName = semesterName;
	}
	/**
	 * @return the semesterYear
	 */
	public Timestamp getSemesterYear() {
		return SemesterYear;
	}
	/**
	 * @param semesterYear the semesterYear to set
	 */
	public void setSemesterYear(Timestamp semesterYear) {
		SemesterYear = semesterYear;
	}
	/**
	 * @return the isCurrent
	 */
	public int getIsCurrent() {
		return isCurrent;
	}
	/**
	 * @param isCurrent the isCurrent to set
	 */
	public void setIsCurrent(int isCurrent) {
		this.isCurrent = isCurrent;
	}
	
	/*
	 * Initializes the Semester object to the current semester values
	 */
	public Semester(){
		try{
			Connection conn = Database.getConnection();
			
			try{
				if(conn != null){
					
					//Retrieve the current semester ID
					String semSelect = "Select *"
							+ " FROM semester"
							+ " WHERE isCurrent= ?";
					PreparedStatement statement = conn.prepareStatement(semSelect);
					statement.setInt(1, 1);
					ResultSet rs = statement.executeQuery();
					
					if(rs.first()){
						DBAnnotation.annoate("semID", "semester", "SemesterID", true);
						int semID = rs.getInt("SemesterID");
						this.SemesterID = semID;;
						
						DBAnnotation.annoate("semName", "semester", "SemesterName", true);
						String semName = rs.getString("SemesterName");
						this.SemesterName = semName;
						
						DBAnnotation.annoate("semYear", "semester", "SemesterYear", true);
						Timestamp semYear =  rs.getTimestamp("SemesterYear");
						this.SemesterYear = semYear;
						
						DBAnnotation.annoate("usCur", "semester", "IsCurrent", true);
						int isCur = rs.getInt("IsCurrent");
						this.isCurrent = isCur;
					}							
					
				}
			}
			
			catch(SQLException e){
				System.out.println("Error in SQL");
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
						
		}
		
		finally{
		}
	}

	/*
	 * Starts the process for initializing the next semester
	 * This function calculates the next semester values
	 * Empties the wait and the email lists
	 * Deletes the previous semester course schedule as the new semester is being started
	 * Commits the whole transaction
	 */
	public boolean goToNextSemester(){
		calculateNextSemester();
		System.out.println(this.SemesterName+" "+this.SemesterYear);
		WaitList.emptyWaitAndEmailList();
		CourseSchedule.deleteAllCourseSchedule();
		updateIsCurrent();
		commitNextSemester();
		Database.commitTransaction(Database.getConnection());
		return true;
		
	}
	
	/*
	 * Calculates the new semester values
	 */
	private void calculateNextSemester(){
		if(this.getSemesterName().equals("SPRING")){
			this.setSemesterName("SUMMER");
			return;
		}
		
		if(this.getSemesterName().equals("SUMMER")){
			this.setSemesterName("FALL");
			return;
		}
		
		if(this.getSemesterName().equals("FALL")){
			long timestamp = this.getSemesterYear().getTime();
			Calendar cal = Calendar.getInstance();
			cal.setTimeInMillis(timestamp);
			cal.add(Calendar.YEAR, 1);
			this.setSemesterYear(new Timestamp(cal.getTimeInMillis()));
			this.setSemesterName("SPRING");
			return;
		}
		
	}
	
	/*
	 * Inserts the new semester values in the database
	 * Marks this new semester as current
	 */
	public void commitNextSemester(){
		try{
			Connection conn = Database.getConnection();
			
			try{
				if(conn != null){

					DBAnnotation.annoate("semName", "semester", "SemesterName", true);
					String semName = this.getSemesterName();
					
					DBAnnotation.annoate("t", "semester", "SemesterYear", true);
					Timestamp t = this.getSemesterYear();
					
					//Retrieve the current semester ID
					String semAdd = "Insert into semester"
							+ " (SemesterName, SemesterYear, isCurrent)"
							+ " Values(?,?,?)";
					PreparedStatement statement = conn.prepareStatement(semAdd);
					statement.setString(1, semName);
					statement.setTimestamp(2, t);
					statement.setInt(3, 1);
					statement.executeUpdate();
					
					//Database.commitTransaction(conn);
				}
			}
			
			catch(SQLException e){
				System.out.println("Error in SQL");
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
						
		}
		
		finally{
		}
	}
	
	/*
	 * Makes all the previous semesters non current after the new semester is added
	 */
	public void updateIsCurrent(){
		try{
			Connection conn = Database.getConnection();
			
			try{
				if(conn != null){
					
					String semAdd = "UPDATE semester"
							+ " SET IsCurrent= ?";
					PreparedStatement statement = conn.prepareStatement(semAdd);
					statement.setInt(1, 0);
					statement.executeUpdate();			
					Database.commitTransaction(conn);
				}
			}
			
			catch(SQLException e){
				System.out.println("Error in SQL");
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
						
		}
		
		finally{
		}		
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		long timeStamp = this.getSemesterYear().getTime();
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(timeStamp);
		int year = c.get(Calendar.YEAR);
		return this.getSemesterName()+" "+year;
	}
	
}

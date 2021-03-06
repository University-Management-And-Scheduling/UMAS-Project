package com.umas.code;


/****************@author Simant Purohit*********************************/

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;



public class CourseSchedule {
	private int offerID;
	private int classroomID;
	private int timeSlotID;
	private Timeslots timeslot;
	private Classroom classroom;
	
	/**
	 * @return the offerID
	 */
	public int getOfferID() {
		return offerID;
	}

	/**
	 * @param offerID the offerID to set
	 */
	public void setOfferID(int offerID) {
		this.offerID = offerID;
	}

	/**
	 * @return the classroomID
	 */
	public int getClassroomID() {
		return classroomID;
	}

	/**
	 * @param classroomID the classroomID to set
	 */
	public void setClassroomID(int classroomID) {
		this.classroomID = classroomID;
	}

	/**
	 * @return the timeSlotID
	 */
	public int getTimeSlotID() {
		return timeSlotID;
	}

	/**
	 * @param timeSlotID the timeSlotID to set
	 */
	public void setTimeSlotID(int timeSlotID) {
		this.timeSlotID = timeSlotID;
	}

	/**
	 * @return the timeslot
	 */
	public Timeslots getTimeslot() {
		return timeslot;
	}

	/**
	 * @param timeslot the timeslot to set
	 */
	public void setTimeslot(Timeslots timeslot) {
		if(timeslot == null)
			throw new NullPointerException("Timeslot is null");
		this.timeslot = timeslot;
	}

	/**
	 * @return the classroom
	 */
	public Classroom getClassroom() {
		return classroom;
	}

	/**
	 * @param classroom the classroom to set
	 */
	public void setClassroom(Classroom classroom) {
		if(classroom == null)
			throw new NullPointerException("Classroom object is null");
		this.classroom = classroom;
	}

	/*
	 * Retrieves the course schedule for the specified course offer id
	 * If the courseOffering is not current, it doesn't initialize the any value
	 * Doesn't throw any exceptions as non-current course offerings do not have a schedule and that is acceptable 
	 */
	public CourseSchedule(int offerID){		
		try{
			Connection conn = Database.getConnection();
			
			try{
				if(conn != null && CourseOffered.checkIfExists(offerID)){
					
					//Retrieve the current semester ID
					String scheduleSelect = "Select *"
							+ " FROM courseschedule"
							+ " WHERE offerID= ?";
					PreparedStatement statement = conn.prepareStatement(scheduleSelect);
					statement.setInt(1, offerID);
					ResultSet rs = statement.executeQuery();
					
					if(rs.first()){
						DBAnnotation.annoate("offID", "courseschedule", "OfferID", true);
						int offID = rs.getInt("OfferID");
						DBAnnotation.annoate("classroomID", "courseschedule", "ClassroomID", true);
						int classroomID = rs.getInt("ClassroomID");
						DBAnnotation.annoate("timeSlotID", "courseschedule", "TimeSlotID", true);
						int timeSlotID = rs.getInt("TImeSlotID");
						
						Timeslots timeslot = new Timeslots(timeSlotID);
						Classroom classroom = new Classroom(classroomID);
						setClassroom(classroom);
						setClassroomID(classroomID);
						setOfferID(offID);
						setTimeslot(timeslot);
						setTimeSlotID(timeSlotID);
						
					}
					
					else{
						System.out.println("Course with offerID:"+offerID+" is NOT scheduled");
						//throw new IllegalArgumentException();
					}
										
					
				}
			}
			
			catch(SQLException e){
				System.out.println("Error retreiving schedule");
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
						
		}
		
		finally{
		}
	}
	
	/*
	 * Update the course schedule of the passed course offering, with the passed classroom and the passed time slot
	 * Checks if the course offering is already scheduled, if not, it will not update the course schedule
	 * Also checks if the classroom and the time slot requested are available, otherwise the updating will fail
	 * Throws a course offering not current exception if the courseOffered passed is not a currently offered course
	 * 
	 */
	public static boolean updateCourseSchedule(CourseOffered courseoffered, Classroom classroom, Timeslots timeslots) throws CourseOffered.CourseOfferingNotCurrentException{
		if(courseoffered == null || classroom == null || timeslots == null){
			return false;
		}
				
		//Check if the course offering is already scheduled
		boolean isAlreadyScheduled = courseoffered.checkIfScheduled();
		boolean isEmpty = Classroom.isEmpty(classroom, timeslots);
		if(isAlreadyScheduled && isEmpty){
			try{
				Connection conn = Database.getConnection();
				
				try{
					if(conn != null){
						DBAnnotation.annoate("timeSlotID", "courseschedule", "TimeSlotID", false);
						int timeSlotID = timeslots.getTimeSlotID();
						
						DBAnnotation.annoate("classroomID", "courseschedule", "ClassroomID", false);
						int classroomID = classroom.getClassroomID();
						
						DBAnnotation.annoate("offerID", "courseschedule", "OfferID", false);						
						int offerID = courseoffered.getOfferID();
						
						String scheduleInsert = "UPDATE courseschedule "
								+ "SET TimeSlotID= ? , ClassroomID=? "
								+ "WHERE OfferID= ?";
						PreparedStatement statement = conn.prepareStatement(scheduleInsert, ResultSet.CONCUR_UPDATABLE);
						statement.setInt(1, timeSlotID);
						statement.setInt(2, classroomID);
						statement.setInt(3, offerID);
						statement.executeUpdate();
						Database.commitTransaction(conn);
						return true;
					}
				}
				
				catch(SQLException e){
					System.out.println("Error updating schedule");
					System.out.println(e.getMessage());
					//e.printStackTrace();
					return false;
				}
				
			}
			
			finally{
			}
			
		}
		
		return false;
	}
	
	/*
	 * Returns a Map of Course schedule id and the course schedule object for all the currently scheduled courses
	 */
	public static HashMap<Integer, CourseSchedule> getHaspMapForSchedule(){
		HashMap<Integer, CourseSchedule> cs = new HashMap<Integer, CourseSchedule>();
		for(CourseSchedule c: getAllScheduledCourses()){
			cs.put(c.getOfferID(), c);
		}
		
		return cs;
	}
	
	/*
	 * Returns a ArrayList of Course schedule object for all the currently scheduled course
	 */
	public static ArrayList<CourseSchedule> getAllScheduledCourses(){
		ArrayList<CourseSchedule> courseSchedule = new ArrayList<CourseSchedule>();
		try{
			Connection conn = Database.getConnection();
			
			try{
				if(conn != null){
					
					String SQLSelect= "Select *"
							+ " FROM courseschedule natural join coursesoffered "
							+ "ORDER BY TotalCapacity";
					PreparedStatement statement = conn.prepareStatement(SQLSelect);
					ResultSet rs =  statement.executeQuery();
					
					while(rs.next()){
						DBAnnotation.annoate("offerID", "coursesoffered", "OfferID", true);
						int offerID = rs.getInt("OfferID");
						CourseSchedule cs = new CourseSchedule(offerID);
						courseSchedule.add(cs);
					}
					
				}
			}
			
			catch(SQLException e){
				System.out.println("Error getting");
				System.out.println(e.getMessage());
			}
			
		}
		
		finally{
		}
		
		return courseSchedule;
	}
	
	/*
	 * Returns a ArrayList of all the scheduled courses in the specified department
	 */
	public static ArrayList<CourseSchedule> getAllScheduledCourses(Department department){
		ArrayList<CourseSchedule> courseSchedule = new ArrayList<CourseSchedule>();
		if(department == null)
			return courseSchedule;
		
		String departmentName = department.getDepartmentName();
		try{
			Connection conn = Database.getConnection();
			
			try{
				if(conn != null){
					
					String SQLSelect= "Select *"
							+ " FROM courseschedule natural join coursesoffered "
							+ "ORDER BY TotalCapacity";
					PreparedStatement statement = conn.prepareStatement(SQLSelect);
					ResultSet rs =  statement.executeQuery();
					
					while(rs.next()){
						DBAnnotation.annoate("offerID", "coursesoffered", "OfferID", true);
						int offerID = rs.getInt("OfferID");
						CourseOffered co = new CourseOffered(offerID);
						
						DBAnnotation.annoate("deptName", "department", "DepartmentName", true);
						String deptName = co.getDepartmentName();
						if(deptName.equals(departmentName)){
							courseSchedule.add(new CourseSchedule(offerID));
						}
					}
					
				}
			}
			
			catch(SQLException e){
				System.out.println("Error getting schedule for department");
				System.out.println(e.getMessage());
			} catch (Course.CourseDoesNotExistException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (CourseOffered.CourseOfferingDoesNotExistException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		finally{
		}
		
		return courseSchedule;
	}
	
	/*
	 * Schedule the passed courseOffered course
	 * The algorithm looks for a classroom with a empty time slot and schedules the course
	 * If no class room is found, it will return false, indicating the course was not scheduled
	 * Else it will schedule the course offering in the first empty classroom found
	 */
	public static boolean scheduleCourse(CourseOffered courseOffered){
		//Check if the course is already scheduled
		System.out.println("xxxxxxxxxxxxxxxxINSIDE SCHEDULE COURSE FUNCTIONxxxxxxxxxxxxxx");
		Classroom c = null;
		Timeslots t = null;
		int timeSlotType = 1;
		boolean isScheduled = false;
		
		if(isScheduled(courseOffered.getOfferID())){
			System.out.println("Course is already scheduled");
			return isScheduled;
		}
		
		//Find a classroom with empty slot
		classroomFind:while(timeSlotType<=2){
			System.out.println("--------------------------------------------------------------"
					+ "\n LOOKING FOR TIMESLOTS WITH TYPE:"+timeSlotType);
			
			for(ClassroomLocation location:ClassroomLocation.values()){
				System.out.println("-----------------------------------------------------------"
						+ "\n LOOKING AT LOCATION:"+location.toString());
				
				c = Classroom.getEmptyClassroom(location, timeSlotType, courseOffered.getTotalCapacity());
				//System.out.println("Got classroom:"+c.getClassroomName().toString()+" at location:"+location.toString()+" repeat:"+c.getClassroomLocation().toString());
				if(c!=null){
					System.out.println("Returning classroom:"+c.getClassroomName().toString()+" at location:"+location.toString()+" repeat:"+c.getClassroomLocation().toString());
					break classroomFind;
				}
			}
			
			timeSlotType++;
		}
		
		if(c==null){
			System.out.println("Cannot schedule this course, no empty class found");
			return isScheduled;
		}
		
		if(c!=null){
			t = c.getEmptySlot(timeSlotType);				
			//Schedule the course in the empty slot
			int offerID = courseOffered.getOfferID();
			int classroomID = c.getClassroomID();
			int timeslotID = t.getTimeSlotID();
			isScheduled = addSchedule(offerID, classroomID, timeslotID);
		}
		
		return isScheduled;
	}

	/*
	 * Similar to above function only input parameters are different
	 */
	public static boolean scheduleCourseUsingID(int offerID, int capacity){
		Classroom c = null;
		Timeslots t = null;
		int timeSlotType = 1;
		boolean addFlag = false;
		
		if(isScheduled(offerID)){
			System.out.println("Course is already scheduled");
			return false;
		}
		
		//Find a classroom with empty slot
		classroomFind:while(timeSlotType<=2){
			System.out.println("--------------------------------------------------------------"
					+ "\n LOOKING FOR TIMESLOTS WITH TYPE:"+timeSlotType);
			
			for(ClassroomLocation location:ClassroomLocation.values()){
				System.out.println("-----------------------------------------------------------"
						+ "\n LOOKING AT LOCATION:"+location.toString());
				
				c = Classroom.getEmptyClassroom(location, timeSlotType, capacity);
				//System.out.println("Got classroom:"+c.getClassroomName().toString()+" at location:"+location.toString()+" repeat:"+c.getClassroomLocation().toString());
				if(c!=null){
					System.out.println("Returning classroom:"+c.getClassroomName().toString()+" at location:"+location.toString()+" repeat:"+c.getClassroomLocation().toString());
					break classroomFind;
				}
			}
			
			timeSlotType++;
		}
		
		if(c==null){
			System.out.println("Cannot schedule this course, no empty classroom found");
			return false;
		}
		if(c!=null){
			t = c.getEmptySlot(timeSlotType);				
			//Schedule the course in the empty slot
			int classroomID = c.getClassroomID();
			int timeslotID = t.getTimeSlotID();
			addFlag = addSchedule(offerID, classroomID, timeslotID);
		}
		
		return addFlag;
	}
	
	/*
	 * Checks if the course offering is scheduled or not	
	 */
	public static boolean isScheduled(int offerID){
		boolean isScheduled = false;
		try{
			Connection conn = Database.getConnection();
			
			try{
				if(conn != null){
					
					//Retrieve the current semester ID
					String SQLSelect = "Select *"
							+ " FROM courseschedule"
							+ " WHERE offerID= ?";
					PreparedStatement statement = conn.prepareStatement(SQLSelect);
					statement.setInt(1, offerID);
					ResultSet rs = statement.executeQuery();
					
					if(rs.first()){
						isScheduled = true;
						System.out.println("Course with offerID:"+offerID+" is already scheduled");
					}
					
					else{
						isScheduled = false;
						System.out.println("Course with offerID:"+offerID+" is NOT scheduled");
					}
										
					
				}
			}
			
			catch(SQLException e){
				System.out.println("Error retreiving schedule");
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
						
		}
		
		finally{
		}
		
		return isScheduled;
	}

	/*
	 * This function is called by the earlier functions to schedule the coure offering
	 */
	private static boolean addSchedule(int offerID, int classroomID, int timeslotID){
		boolean addFlag = false;
		
		try{
			Connection conn = Database.getConnection();
			
			try{
				if(conn != null){
					DBAnnotation.annoate("offID", "courseschedule", "OfferID", false);
					int offID = offerID;
					
					DBAnnotation.annoate("classID", "courseschedule", "ClassroomID", false);
					int classID = classroomID;
					
					DBAnnotation.annoate("timeID", "courseschedule", "TimeslotID", false);
					int timeID = timeslotID;
					
					String scheduleInsert = "Insert into courseschedule"
							+ " (OfferID, TimeSlotID, ClassroomID)"
							+ " Values(?,?,?)";
					PreparedStatement statement = conn.prepareStatement(scheduleInsert);
					statement.setInt(1, offID);
					statement.setInt(2, timeID);
					statement.setInt(3, classID);
					statement.executeUpdate();
					System.out.println("Adding course schedule with offerID:"+offerID+" ClassroomID:"+classroomID+" TimeslotID:"+timeslotID);
					addFlag = true;
				}
			}
			
			catch(SQLException e){
				System.out.println("Error adding schedule");
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
			
		}
		
		finally{
		}
		
		return addFlag;
	}
	
	
	/*
	 * Schedules/reschedules all the current course offerings
	 */
	public static void scheduleAllCurrentCourses(){
		/*
		 * Remove all the scheduled courses
		 */
		deleteAllCourseSchedule();
		
		/*
		 * Get all the current course offerings
		 * Pick up one courseOffering at random
		 * Find a classroom and an empty time slot for the offering
		 * Schedule the courseOffering
		 * Repeat the steps with other offerings
		 */
		
		ArrayList<CourseOffered> allCourses = CourseOffered.getAllCurrentlyOfferedCourses();
		Collections.shuffle(allCourses);
		for(CourseOffered co:allCourses){
			System.out.println("\n\n\nxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"
					+ "\n--------------------------------------------------------------------------------------------------------------");
			
			scheduleCourse(co);
			
			System.out.println("\n\n\n-----------------------------------------------------------------------------------------------------------"
					+ "\nxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
		}
		
		Database.commitTransaction(Database.getConnection());
	}
	
	
	/*
	 * Reschedule all the current course offerings for the specified department
	 */
	public static void scheduleAllCurrentCourses(Department department){
		//Remove all the scheduled courses in the dept
		
		deleteAllCourseSchedule(department);		
		
		ArrayList<CourseOffered> allCourses = department.getDepartmentCourseOffered();
		for(CourseOffered co:allCourses){
			System.out.println("\n\n\nxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"
					+ "\n--------------------------------------------------------------------------------------------------------------");
			
			scheduleCourse(co);
			
			System.out.println("\n\n\n-----------------------------------------------------------------------------------------------------------"
					+ "\nxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
		}
		
		Database.commitTransaction(Database.getConnection());
	}
	
	/*
	 * Deletes all the course schedule
	 */
	public static void deleteAllCourseSchedule(){
		try{
			Connection conn = Database.getConnection();
			
			try{
				if(conn != null){
					
					//Retrieve the current semester ID
					String courseScheduleDelete = "Delete"
							+ " FROM courseschedule";
					PreparedStatement statement = conn.prepareStatement(courseScheduleDelete, ResultSet.CONCUR_UPDATABLE);
				    int isDeleted= statement.executeUpdate();
				    System.out.println("Deletd:"+isDeleted);
				    if(isDeleted > 0){
				    	Database.commitTransaction(conn);
				    }
				    
									
				}
			}
			
			catch(SQLException e){
				System.out.println("Error deleting schedule");
				System.out.println(e.getMessage());
				Database.rollBackTransaction(conn);
				e.printStackTrace();
			}
						
		}
		
		finally{
		}
	}
	
	/*
	 * Deletes all the current course schedule for the course offering for the specified department 
	 */
	public static void deleteAllCourseSchedule(Department department){
		ArrayList<CourseOffered> deptCoursesOffering = department.getDepartmentCourseOffered();
		
		for(CourseOffered co:deptCoursesOffering){
			deleteSingleSchedule(co.getOfferID());
		}
	}
	
	/*
	 * This function is used by deleteAlCourseSchedule function to delete course offerings one at a time
	 */
	private static void deleteSingleSchedule(int offerID){
		try{
			Connection conn = Database.getConnection();
			
			try{
				if(conn != null){
					
					/*
					 * Retrieve the current semester ID
					 */
					
					/*
					 * Delete from table annotation
					 */
					DBAnnotation.annoate("offerID", "courseschedule", "OfferID", false);
					String courseScheduleDelete = "Delete"
							+ " FROM courseschedule "
							+ "WHERE OfferID = ?";
					PreparedStatement statement = conn.prepareStatement(courseScheduleDelete, ResultSet.CONCUR_UPDATABLE);
					statement.setInt(1, offerID);
				    int isDeleted= statement.executeUpdate();
				    System.out.println("Deletd:"+isDeleted);
				    if(isDeleted > 0){
				    	Database.commitTransaction(conn);
				    }
				    
									
				}
			}
			
			catch(SQLException e){
				System.out.println("Error deleting schedule");
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
						
		}
		
		finally{
		}
	}
	
	/*
	 * Checks of another course is schedulable for the capacity mentioned
	 */
	public static boolean isAnotherCourseSchedulable(int courseCapacity){
		int timeSlotType = 1;
		Classroom c = null;
		while(timeSlotType<=2){
			for(ClassroomLocation location:ClassroomLocation.values()){
				c = Classroom.getEmptyClassroom(location, timeSlotType, courseCapacity);
				if(c!=null){
					return true;
				}
			}
			
			timeSlotType++;
		}
		
		return false;
		
	}
	
	
//	public static void main(String args[]) throws Course.CourseDoesNotExistException, CourseOffered.CourseOfferingDoesNotExistException{
//		//scheduleAllCurrentCourses();
//		try {
//			updateCourseSchedule(new CourseOffered(295), new Classroom(10), new Timeslots(31));
//		} catch (CourseOffered.CourseOfferingNotCurrentException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
}

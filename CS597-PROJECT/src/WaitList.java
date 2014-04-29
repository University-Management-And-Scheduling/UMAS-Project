
/****************@author Simant Purohit*********************************/

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;

import org.joda.time.Period;




public class WaitList {
	int offerID;
	int UIN;
	int queuePos;
	CourseOffered courseoffered;
	
	private WaitList(){
		//Wait List class cannot be initialized directly
	}
	
	
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
	 * @return the uIN
	 */
	public int getUIN() {
		return UIN;
	}
	/**
	 * @param uIN the uIN to set
	 */
	public void setUIN(int uIN) {
		UIN = uIN;
	}
	/**
	 * @return the queuePos
	 */
	public int getQueuePos() {
		return queuePos;
	}
	/**
	 * @param queuePos the queuePos to set
	 */
	public void setQueuePos(int queuePos) {
		this.queuePos = queuePos;
	}

	/*
	 * Adds the specified student to the wait list for the offerid mentioned
	 */
	public static boolean addStudentToWaitList(Student student, int offerID) throws Course.CourseDoesNotExistException, CourseOffered.CourseOfferingDoesNotExistException{
		/*
		 * check if the student is eligible to be added to the wait list
		 */
		boolean isAdded = false;
		if(canBeAddedToWaitList(student, offerID)){
			/*
			 * get the queue position for the addition
			 */
			int queuePos = getLastQueuePos(offerID) + 1;
			int UIN = student.getUIN();
			
			try{
				Connection conn = Database.getConnection();
				
				try{
					if(conn != null){
						/*
						 * Insert the student in the waitlist
						 */
						System.out.println("Inserting student in wait list");
						String WaitListInsert = "INSERT INTO waitlist "
								+ "(UIN, OfferID, QueuePos) "
								+ "Values(?,?,?)";
						PreparedStatement statement = conn.prepareStatement(WaitListInsert, ResultSet.CONCUR_UPDATABLE);
						statement.setInt(1, UIN);
						statement.setInt(2, offerID);
						statement.setInt(3, queuePos);
						statement.executeUpdate();
						
						DBAnnotation.annoate("UIN", "waitlist", "UIN", false);
						DBAnnotation.annoate("offerID", "waitlist", "OfferID", false);
						DBAnnotation.annoate("queuePos", "waitlist", "QueuePos", false);	
						
						Database.commitTransaction(conn);
						isAdded = true;
					}
				}
				
				catch(SQLException e){
					System.out.println(e.getMessage());
					e.printStackTrace();
				}			
			}
			
			finally{
			}
			
		}
		return isAdded;
	}
	
	/*
	 * returns the last queue position number for the specified offer id
	 */
	private static int getLastQueuePos(int OfferID){
		int queuePos = 0;
		try{
			Connection conn = Database.getConnection();
			
			try{
				if(conn != null){
					/*
					 * Retrieve the max queue number for the offer id
					 */
					String SemesterSelect = "Select max(QueuePos) as QueuePos"
							+ " FROM waitlist"
							+ " WHERE offerID= ?";
					PreparedStatement statement = conn.prepareStatement(SemesterSelect);
					statement.setInt(1, OfferID);
					ResultSet rs = statement.executeQuery();
					/*
					 * if found, return the found queue number
					 * else zero (0) is returned as no student was found on the wait list
					 */
					if(rs.first()){
						DBAnnotation.annoate("queuePos", "waitlist", "QueuePos", true);
						queuePos = rs.getInt("QueuePos");
					}					
				}
			}
			
			catch(SQLException e){
				System.out.println(e.getMessage());
				e.printStackTrace();
			}			
		}
		
		finally{
		}
		
		return queuePos;
		
	}
	
	/*
	 * Works same as the above function, just gives the minimum queue position
	 */
	private static int getFirstQueuePosition(int OfferID){
		int queuePos = 0;
		try{
			Connection conn = Database.getConnection();
			
			try{
				if(conn != null){
					String SemesterSelect = "Select min(QueuePos) as QueuePos"
							+ " FROM waitlist"
							+ " WHERE offerID= ?";
					PreparedStatement statement = conn.prepareStatement(SemesterSelect);
					statement.setInt(1, OfferID);
					ResultSet rs = statement.executeQuery();
					if(rs.first()){
						DBAnnotation.annoate("queuePos", "waitlist", "QueuePos", true);
						queuePos = rs.getInt("QueuePos");
					}					
				}
			}
			
			catch(SQLException e){
				System.out.println(e.getMessage());
				e.printStackTrace();
			}			
		}
		
		finally{
		}
		
		return queuePos;
	}
	
	/*
	 * Retrieves all the students who are emailed and allowed to register for the specified offerid
	 */
	public static ArrayList<Student> getStudentsOnEmailList(int offerID){
		ArrayList<Student> students = new ArrayList<Student>();
		
		try{
			Connection conn = Database.getConnection();
			
			try{
				if(conn != null){
					/*
					 * Get all the emailed students for the offer id
					 */
					String SemesterSelect = "Select StudentUIN"
							+ " FROM emailedwaitlist"
							+ " WHERE offerID= ?";
					PreparedStatement statement = conn.prepareStatement(SemesterSelect);
					statement.setInt(1, offerID);
					ResultSet rs = statement.executeQuery();
					
					/*
					 * Add all student to array list
					 */
					while(rs.next()){
						DBAnnotation.annoate("sUIN", "emailedwaitlist", "StudentUIN", true);
						int sUIN = rs.getInt("StudentUIN");
						students.add(new Student(sUIN));
					}
				}
			}
			
			catch(SQLException e){
				System.out.println(e.getMessage());
				e.printStackTrace();
			} catch (People.PersonDoesNotExistException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}
		
		finally{
		}
		
		return students;
	}
	
	/*
	 * Get all wait list student for the specified offer id
	 */
	public static ArrayList<Student> getStudentsOnWaitList(int offerID){
		ArrayList<Student> students = new ArrayList<Student>();
		if(isWaitListEmpty(offerID)){
			return students;
		}
		
		try{
			Connection conn = Database.getConnection();
			
			try{
				if(conn != null){
					/*
					 * Select all wait list students for the offer id
					 */
					String SemesterSelect = "Select *"
							+ " FROM waitlist"
							+ " WHERE offerID= ?"
							+ " ORDER BY QueuePos";
					PreparedStatement statement = conn.prepareStatement(SemesterSelect);
					statement.setInt(1, offerID);
					ResultSet rs = statement.executeQuery();
					/*
					 * add all retrieved students to wait list
					 */
					while(rs.next()){
						DBAnnotation.annoate("sUIN", "waitlist", "UIN", true);
						int sUIN = rs.getInt("UIN");
						students.add(new Student(sUIN));
					}
				}
			}
			
			catch(SQLException e){
				System.out.println(e.getMessage());
				e.printStackTrace();
			} catch (People.PersonDoesNotExistException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}
		
		finally{
		}
		
		return students;
	}
	
	/*
	 * Get all wait list courses of the student specified
	 */
	public static ArrayList<CourseOffered> getWaitListCoursesOfStudent(Student s){
		ArrayList<CourseOffered> waitListCourses = new ArrayList<CourseOffered>();
		try{
			Connection conn = Database.getConnection();
			
			try{
				if(conn != null){
					String SQLSelect = "Select *"
							+ " FROM waitlist"
							+ " WHERE UIN= ?"
							+ " ORDER BY QueuePos";
					PreparedStatement statement = conn.prepareStatement(SQLSelect);
					statement.setInt(1, s.getUIN());
					ResultSet rs = statement.executeQuery();
					while(rs.next()){
						DBAnnotation.annoate("offerID", "waitlist", "OfferID", true);
						int offerID = rs.getInt("OfferID");
						waitListCourses.add(new CourseOffered(offerID));
					}
				}
			}
			
			catch(SQLException e){
				System.out.println(e.getMessage());
				e.printStackTrace();
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
		
		return waitListCourses;
	}
	
	/*
	 * Checks if the course can accommodate a new student if the student was moved from wait list 
	 * to email list ad allowed to register
	 */
	private static boolean canCourseAccomodateNewStudentFromWaitList(int offerID){
		CourseOffered c = null;
		/*
		 * mathematical calculations to check if the course can accommodate a new student
		 */
		try {
			c = new CourseOffered(offerID);
			int seatRem = c.getTotalCapacity() - c.getCurrentlyFilled();
			int studentsEmailed = getStudentsOnEmailList(offerID).size();
			if(studentsEmailed+1 <= seatRem)
				return true;
			else
				return false;
		} catch (Course.CourseDoesNotExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (CourseOffered.CourseOfferingDoesNotExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		
		
	}
	
	/*
	 * Checks if the student is eligible to be put on waitlist
	 */
 	public static boolean canBeAddedToWaitList(Student student, int offerID) throws Course.CourseDoesNotExistException, CourseOffered.CourseOfferingDoesNotExistException{
		boolean canBeAdded = false;
		CourseOffered courseOffered = new CourseOffered(offerID);
		if(courseOffered.isCourseRegistrableBy(student)){
			canBeAdded = false;
			return canBeAdded;
		}
		
		if(!isStudentRegistered(student, offerID)){
			if(!isStudentOnWaitList(student, offerID)){
				if(!isStudentEmailed(student, offerID)){
					canBeAdded = true;
					return canBeAdded;
				}
			}
		}
		
		return canBeAdded;
	}
	
 	/*
 	 * Removes the specified student from the waitlist of the offer id
 	 * this can be called directly by the automatic scanner which scans for students
 	 * who are eligible to be put from wait to email list ad allowed to register
 	 */
	public static boolean removeFromWaitList(Student student, int offerID){
		int UIN = student.getUIN();
		boolean isRemoved = false;
		try{
			Connection conn = Database.getConnection();
			
			try{
				if(conn != null){
					System.out.println("Deleting student from wait list");
					String WaitListInsert = "DELETE FROM waitlist "
							+ "WHERE UIN= ? and OfferID= ?";
					PreparedStatement statement = conn.prepareStatement(WaitListInsert, ResultSet.CONCUR_UPDATABLE, ResultSet.TYPE_FORWARD_ONLY);
					statement.setInt(1, UIN);
					statement.setInt(2, offerID);
					statement.executeUpdate();
					DBAnnotation.annoate("UIN", "waitlist", "UIN", false);
					DBAnnotation.annoate("offerID", "waitlist", "OfferID", false);
					isRemoved = true;
					//Database.commitTransaction(conn);											
				}
			}
			
			catch(SQLException e){
				System.out.println(e.getMessage());
				e.printStackTrace();
			}			
		}
		
		finally{
		}
		
		return isRemoved;
	}
	
	/*
	 * this function is externally called when a student removes self from waitlist
	 */
	public static boolean removeFromWaitListAndCommit(Student student, int offerID){
		int UIN = student.getUIN();
		boolean isRemoved = false;
		try{
			Connection conn = Database.getConnection();
			
			try{
				if(conn != null){
					System.out.println("Deleting student from wait list");
					String WaitListInsert = "DELETE FROM waitlist "
							+ "WHERE UIN= ? and OfferID= ?";
					PreparedStatement statement = conn.prepareStatement(WaitListInsert, ResultSet.CONCUR_UPDATABLE, ResultSet.TYPE_FORWARD_ONLY);
					statement.setInt(1, UIN);
					statement.setInt(2, offerID);
					DBAnnotation.annoate("UIN", "waitlist", "UIN", false);
					DBAnnotation.annoate("offerID", "waitlist", "OfferID", false);
					statement.executeUpdate();
					isRemoved = true;
					Database.commitTransaction(conn);											
				}
			}
			
			catch(SQLException e){
				System.out.println(e.getMessage());
				e.printStackTrace();
			}			
		}
		
		finally{
		}
		
		return isRemoved;
	}

	/*
	 * This function removes the eligibility of the students to register for the course because of the timeout in registration
	 */
	public static void removeFromEmailedList(int UIN, int offerID){
		try{
			Connection conn = Database.getConnection();
			
			try{
				if(conn != null){
					System.out.println("Deleting student from email wait list");
					String WaitListInsert = "DELETE FROM emailedwaitlist "
							+ "WHERE StudentUIN= ? and OfferID= ?";
					PreparedStatement statement = conn.prepareStatement(WaitListInsert, ResultSet.CONCUR_UPDATABLE);
					statement.setInt(1, UIN);
					statement.setInt(2, offerID);
					DBAnnotation.annoate("UIN", "emailedwaitlist", "UIN", false);
					DBAnnotation.annoate("offerID", "emailedwaitlist", "OfferID", false);
					statement.executeUpdate();
					//Database.commitTransaction(conn);											
				}
			}
			
			catch(SQLException e){
				System.out.println(e.getMessage());
				e.printStackTrace();
			}			
		}
		
		finally{
		}
	}
	
	/*
	 * Checks for the first student on the queue of the wait list for the specified offer
	 * Removes the student from wait list
	 * Adds to email list
	 * allows the student to register
	 * sends an intimation email to student
	 */
	public static void emailFirstStudentOnWaitList(int offerID){
		if(!canCourseAccomodateNewStudentFromWaitList(offerID)){
			System.out.println("Course cannot accomodate new student, not sending email");
			return;
		}
		
		Student student = getStudentFirstOnWaitList(offerID);
		
		if(student!=null){
			//add the student to emailed list
			try{
				Connection conn = Database.getConnection();
				
				try{
					if(conn != null){
						System.out.println("Inserting student in wait list");
						String WaitListInsert = "INSERT INTO emailedwaitlist "
								+ "(StudentUIN, OfferID, TimeEmailed) "
								+ "Values(?,?,?)";
						PreparedStatement statement = conn.prepareStatement(WaitListInsert, ResultSet.CONCUR_UPDATABLE);
						int studentUIN = student.getUIN();
						statement.setInt(1, studentUIN);
						statement.setInt(2, offerID);
						long timeStamp = System.currentTimeMillis();
						Timestamp t = new Timestamp(timeStamp);
						statement.setTimestamp(3, t);
						statement.executeUpdate();											
						DBAnnotation.annoate("studentUIN", "emailedwaitlist", "StudentUIN", false);
						DBAnnotation.annoate("offerID", "emailedwaitlist", "OfferID", false);
						DBAnnotation.annoate("t", "emailedwaitlist", "TimeEmailed", false);
					}
				}
				
				catch(SQLException e){
					System.out.println(e.getMessage());
					e.printStackTrace();
				}			
			}
			
			finally{
			}
			//remove the student from wait list
			removeFromWaitList(student, offerID);
			
			//email the student using the username
			Email email = Email.getInstance("umas.uic@gmail.com", "cs597project");
			String recepient = student.getUserName()+"@umas.edu";
			String subject = "Registration open";
			String body = "You can now register for course:"+offerID+". \nYou have a 12 hour time limit for registratoin";
			if(email.sendEmail(recepient, subject, body)){
				System.out.println("Email sent!");
				Database.commitTransaction(Database.getConnection());
			}
			
		}
	}
	
	/*
	 * Retrieves the first student from the wait list of the specified course
	 */
	private static Student getStudentFirstOnWaitList(int offerID){
		Student student = null;
		int queuePos = getFirstQueuePosition(offerID);
		
		if(!isWaitListEmpty(offerID)){
			try{
				Connection conn = Database.getConnection();
				
				try{
					if(conn != null){
						String SemesterSelect = "Select UIN"
								+ " FROM waitlist"
								+ " WHERE offerID= ? and QueuePos= ?";
						PreparedStatement statement = conn.prepareStatement(SemesterSelect);
						statement.setInt(1, offerID);
						statement.setInt(2, queuePos);
						ResultSet rs = statement.executeQuery();
						if(rs.first()){
							//return the student
							DBAnnotation.annoate("UIN", "waitlist", "UIN", true);
							int UIN = rs.getInt("UIN");
							student = new Student(UIN);
						}
																	
					}
				}
				
				catch(SQLException e){
					System.out.println(e.getMessage());
					e.printStackTrace();
				} catch (People.PersonDoesNotExistException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}			
			}
			
			finally{
				
			}
			
		}
		
		return student;
	}
	
	/*
	 * Checks if the student is already registered for the course
	 */
	public static boolean isStudentRegistered(Student student, int offerID){
		boolean isRegistered = false;
		try{
			Connection conn = Database.getConnection();
			
			try{
				if(conn != null){
					String SemesterSelect = "Select *"
							+ " FROM studentenrollment"
							+ " WHERE offerID= ? and UIN= ?";
					PreparedStatement statement = conn.prepareStatement(SemesterSelect);
					statement.setInt(1, offerID);
					statement.setInt(2, student.getUIN());
					ResultSet rs = statement.executeQuery();
					if(rs.first()){
						isRegistered = true;
					}
					
					else{
						isRegistered = false;
					}
										
				}
			}
			
			catch(SQLException e){
				System.out.println(e.getMessage());
				e.printStackTrace();
			}			
		}
		
		finally{
		}
		
		return isRegistered;
	}
	
	/*
	 * checks if the student is on the wait list
	 */
	public static boolean isStudentOnWaitList(Student student, int offerID){
		boolean isOnWaitList = false;
		try{
			Connection conn = Database.getConnection();
			
			try{
				if(conn != null){
					String SemesterSelect = "Select *"
							+ " FROM waitlist"
							+ " WHERE offerID= ? and UIN= ?";
					PreparedStatement statement = conn.prepareStatement(SemesterSelect);
					statement.setInt(1, offerID);
					statement.setInt(2, student.getUIN());
					ResultSet rs = statement.executeQuery();
					if(rs.first()){
						isOnWaitList = true;
					}
					
					else{
						isOnWaitList = false;
					}
										
				}
			}
			
			catch(SQLException e){
				System.out.println(e.getMessage());
				e.printStackTrace();
			}			
		}
		
		finally{
		}
		
		return isOnWaitList;
	}

	/*
	 * checks if the wait list is empty
	 */
	public static boolean isWaitListEmpty(int offerID){
		boolean isEmpty = false;
		try{
			Connection conn = Database.getConnection();
			
			try{
				if(conn != null){
					String SemesterSelect = "Select *"
							+ " FROM waitlist"
							+ " WHERE offerID= ?";
					PreparedStatement statement = conn.prepareStatement(SemesterSelect);
					statement.setInt(1, offerID);
					ResultSet rs = statement.executeQuery();
					if(rs.first()){
						isEmpty = false;
					}
					
					else{
						isEmpty = true;
					}
										
				}
			}
			
			catch(SQLException e){
				System.out.println(e.getMessage());
				e.printStackTrace();
			}			
		}
		
		finally{
		}
		
		return isEmpty;
	}
	
	/*
	 * empty the wait and email lists for initialization of the new semester
	 */
	public static boolean emptyWaitAndEmailList(){
		try{
			Connection conn = Database.getConnection();
			
			try{
				if(conn != null){
					String listDelete = "Delete from waitlist";
					PreparedStatement statement = conn.prepareStatement(listDelete);
					statement.executeUpdate();
					
					listDelete = "Delete from emailedwaitlist";
					statement = conn.prepareStatement(listDelete);
					statement.executeUpdate();
					
					return true;
				}
			}
			
			catch(SQLException e){
				System.out.println(e.getMessage());
				e.printStackTrace();
			}			
		}
		
		finally{
		}
		
		return false;
	}
	
	/*
	 * checks if the student has already been sent email to register
	 */
	public static boolean isStudentEmailed(Student student, int offerID){
		boolean isEmailed = false;
		try{
			Connection conn = Database.getConnection();
			
			try{
				if(conn != null){
					String SemesterSelect = "Select *"
							+ " FROM emailedwaitlist"
							+ " WHERE offerID= ? and StudentUIN= ?";
					PreparedStatement statement = conn.prepareStatement(SemesterSelect);
					statement.setInt(1, offerID);
					statement.setInt(2, student.getUIN());
					ResultSet rs = statement.executeQuery();
					
					if(rs.first()){
						isEmailed = true;
					}
					
					else{
						isEmailed = false;
					}
										
				}
			}
			
			catch(SQLException e){
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
		}
		
		finally{
		}
		
		return isEmailed;
	}
	
	/*
	 *checks the registration ticket expiration status of the emailed students and thus allows new students to register 
	 */
	private static void checkTheStatusOfEmailedStudents(){
		try{
			Connection conn = Database.getConnection();
			
			try{
				if(conn != null){
					String waitListSelect = "Select *"
							+ " FROM emailedwaitlist";
					PreparedStatement statement = conn.prepareStatement(waitListSelect);
					ResultSet rs = statement.executeQuery();
					
					while(rs.next()){
						DBAnnotation.annoate("t1", "emailedwaitlist", "TimeEmailed", true);
						Timestamp t1 = rs.getTimestamp("TimeEmailed");;
						
						Timestamp t2 = new Timestamp(System.currentTimeMillis());
						long hoursElapsed = findTimeDifference(t1, t2);
						
						DBAnnotation.annoate("studentUIN", "emailedwaitlist", "StudentUIN", true);
						int studentUIN = rs.getInt("StudentUIN");
						Student s = new Student(studentUIN);
						
						DBAnnotation.annoate("offerID", "emailedwaitlist", "OfferID", true);
						int offerID = rs.getInt("offerID");
						
						
						if(isStudentRegistered(s, offerID)){
							removeFromEmailedList(studentUIN, offerID);
							Email email = Email.getInstance("umas.uic@gmail.com", "cs597project");
							email.sendEmail(s.getUserName()+"@umas.edu", "Your registered for the course", "You registrated for course:"+offerID+" after waitlist");
							//email new student for the same offer id
							emailFirstStudentOnWaitList(offerID);
							Database.commitTransaction(Database.getConnection());
						}
						
						else if(hoursElapsed >= 1){
							//remove student from e-mailed list and email the student
							int UIN = s.getUIN();
							System.out.println("Removing student from email list:"+UIN);
							removeFromEmailedList(UIN, offerID);
							Email email = Email.getInstance("umas.uic@gmail.com", "cs597project");
							email.sendEmail(s.getUserName()+"@umas.edu", "Your regitration ticket expired", "Your registration ticket for course:"+offerID+" has expired");
							//email new student for the same offer id
							emailFirstStudentOnWaitList(offerID);
							Database.commitTransaction(Database.getConnection());
						}
						
						
						else{
							continue;
						}
					}
										
				}
			}
			
			catch(SQLException e){
				System.out.println(e.getMessage());
				e.printStackTrace();
			} catch (People.PersonDoesNotExistException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		finally{
		}
	}
	
	/*
	 * find time difference between the time the student was emailed to register and the current time
	 */
	private static long findTimeDifference(Timestamp t1, Timestamp t2){
		Period p = new Period(t1.getTime(),t2.getTime());
		System.out.println(t1);
		System.out.println(t2);
		System.out.println("Hour difference:"+p.getHours());
		return p.getHours();
	}
	
	/*
	 * performs a completer scan of the wait list and adds, update deletes student from  waitlist and email list
	 * as and when it is necessary
	 */
	public static void scanWaitList(){
		HashMap<Integer, CourseSchedule> allScheduledCourses = CourseSchedule.getHaspMapForSchedule();
		checkTheStatusOfEmailedStudents();
		for(Integer i:allScheduledCourses.keySet()){
			int wailtListStudents = getStudentsOnWaitList(i).size();
			if(wailtListStudents <= 0)
				continue;
			
			try {
				CourseOffered co = new CourseOffered(i);
				int emailedStudents = getStudentsOnEmailList(i).size();
				int totalCap = co.getTotalCapacity();
				int filled = co.getCurrentlyFilled();
				
				int numberOfNewStudentsToBeAccomodated = (totalCap-filled) - emailedStudents;
				
				if(numberOfNewStudentsToBeAccomodated > 0){
					emailFirstStudentOnWaitList(i);
					Thread.sleep(2000);
				}
				
				
			} catch (Course.CourseDoesNotExistException e) {
				e.printStackTrace();
			} catch (CourseOffered.CourseOfferingDoesNotExistException e) {
				e.printStackTrace();
			}catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}
	
	public static void main(String[] args){
	}
}

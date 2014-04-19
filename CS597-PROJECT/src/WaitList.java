import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import org.joda.time.Period;

public class WaitList {
	int offerID;
	int UIN;
	int queuePos;
	
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

	public static void addStudentToWaitList(Student student, int offerID) throws Course.CourseDoesNotExistException, CourseOffered.CourseOfferingDoesNotExistException{
		if(canBeAddedToWaitList(student, offerID)){
			//add to wait list
			int queuePos = getLastQueuePos(offerID) + 1;
			int UIN = student.getUIN();
			
			try{
				Connection conn = Database.getConnection();
				
				try{
					if(conn != null){
						System.out.println("Inserting student in wait list");
						String WaitListInsert = "INSERT INTO university.waitlist "
								+ "(UIN, OfferID, QueuePos) "
								+ "Values(?,?,?)";
						PreparedStatement statement = conn.prepareStatement(WaitListInsert, ResultSet.CONCUR_UPDATABLE);
						statement.setInt(1, UIN);
						statement.setInt(2, offerID);
						statement.setInt(3, queuePos);
						statement.executeUpdate();
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
			
		}
	}
	
	private static int getLastQueuePos(int OfferID){
		int queuePos = 0;
		try{
			Connection conn = Database.getConnection();
			
			try{
				if(conn != null){
					String SemesterSelect = "Select max(QueuePos) as QueuePos"
							+ " FROM university.waitlist"
							+ " WHERE offerID= ?";
					PreparedStatement statement = conn.prepareStatement(SemesterSelect);
					statement.setInt(1, OfferID);
					ResultSet rs = statement.executeQuery();
					if(rs.first()){
						queuePos = rs.getInt(1);
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
	
	private static int getFirstQueuePosition(int OfferID){
		int queuePos = 0;
		try{
			Connection conn = Database.getConnection();
			
			try{
				if(conn != null){
					String SemesterSelect = "Select min(QueuePos) as QueuePos"
							+ " FROM university.waitlist"
							+ " WHERE offerID= ?";
					PreparedStatement statement = conn.prepareStatement(SemesterSelect);
					statement.setInt(1, OfferID);
					ResultSet rs = statement.executeQuery();
					if(rs.first()){
						queuePos = rs.getInt(1);
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
	
	public static ArrayList<Student> getStudentsOnEmailList(int offerID){
		ArrayList<Student> students = new ArrayList<Student>();
		
		try{
			Connection conn = Database.getConnection();
			
			try{
				if(conn != null){
					String SemesterSelect = "Select StudentUIN"
							+ " FROM university.emailedwaitlist"
							+ " WHERE offerID= ?";
					PreparedStatement statement = conn.prepareStatement(SemesterSelect);
					statement.setInt(1, offerID);
					ResultSet rs = statement.executeQuery();
					while(rs.next()){
						students.add(new Student(rs.getInt("StudentUIN")));
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
		
		return students;
	}
	
	public static ArrayList<Student> getStudentsOnWaitList(int offerID){
		ArrayList<Student> students = new ArrayList<Student>();
		if(isWaitListEmpty(offerID)){
			return students;
		}
		
		try{
			Connection conn = Database.getConnection();
			
			try{
				if(conn != null){
					String SemesterSelect = "Select UIN"
							+ " FROM university.waitlist"
							+ " WHERE offerID= ?";
					PreparedStatement statement = conn.prepareStatement(SemesterSelect);
					statement.setInt(1, offerID);
					ResultSet rs = statement.executeQuery();
					while(rs.next()){
						students.add(new Student(rs.getInt("UIN")));
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
		
		return students;
	}
	
	private static boolean canCourseAccomodateNewStudentFromWaitList(int offerID){
		CourseOffered c = null;
		
		try {
			c = new CourseOffered(offerID);
			int seatRem = c.getTotalCapacity() - c.getCurrentlyFilled();
			int studentsEmailed = getStudentsOnEmailList(offerID).size();
			if(studentsEmailed+1 <= seatRem)
				return true;
			else
				return false;
		} catch (Course.CourseDoesNotExistException
				| CourseOffered.CourseOfferingDoesNotExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		
		
	}
	
 	public static boolean canBeAddedToWaitList(Student student, int offerID) throws Course.CourseDoesNotExistException, CourseOffered.CourseOfferingDoesNotExistException{
		boolean canBeAdded = false;
		CourseOffered courseOffered = new CourseOffered(offerID);
		if(courseOffered.isCourseRegistrableBy(student)){
			canBeAdded = false;
		}
		
		if(!isStudentRegistered(student, offerID)){
			if(!isStudentOnWaitList(student, offerID)){
				if(!isStudentEmailed(student, offerID)){
					canBeAdded = true;
				}
			}
		}
		
		return canBeAdded;
	}
			
	public static void removeFromWaitList(Student student, int offerID){
		int UIN = student.getUIN();
		try{
			Connection conn = Database.getConnection();
			
			try{
				if(conn != null){
					System.out.println("Deleting student from wait list");
					String WaitListInsert = "DELETE FROM university.waitlist "
							+ "WHERE UIN= ? and OfferID= ?";
					PreparedStatement statement = conn.prepareStatement(WaitListInsert, ResultSet.CONCUR_UPDATABLE);
					statement.setInt(1, UIN);
					statement.setInt(2, offerID);
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

	public static void removeFromEmailedList(int UIN, int offerID){
		try{
			Connection conn = Database.getConnection();
			
			try{
				if(conn != null){
					System.out.println("Deleting student from email wait list");
					String WaitListInsert = "DELETE FROM university.emailedwaitlist "
							+ "WHERE StudentUIN= ? and OfferID= ?";
					PreparedStatement statement = conn.prepareStatement(WaitListInsert, ResultSet.CONCUR_UPDATABLE);
					statement.setInt(1, UIN);
					statement.setInt(2, offerID);
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
						String WaitListInsert = "INSERT INTO university.emailedwaitlist "
								+ "(StudentUIN, OfferID, TimeEmailed) "
								+ "Values(?,?,?)";
						PreparedStatement statement = conn.prepareStatement(WaitListInsert, ResultSet.CONCUR_UPDATABLE);
						statement.setInt(1, student.getUIN());
						statement.setInt(2, offerID);
						statement.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
						statement.executeUpdate();											
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
	
	private static Student getStudentFirstOnWaitList(int offerID){
		Student student = null;
		int queuePos = getFirstQueuePosition(offerID);
		
		if(!isWaitListEmpty(offerID)){
			try{
				Connection conn = Database.getConnection();
				
				try{
					if(conn != null){
						String SemesterSelect = "Select UIN"
								+ " FROM university.waitlist"
								+ " WHERE offerID= ? and QueuePos= ?";
						PreparedStatement statement = conn.prepareStatement(SemesterSelect);
						statement.setInt(1, offerID);
						statement.setInt(2, queuePos);
						ResultSet rs = statement.executeQuery();
						if(rs.first()){
							//return the student
							student = new Student(rs.getInt("UIN"));
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
			
		}
		
		return student;
	}
	
	public static boolean isStudentRegistered(Student student, int offerID){
		boolean isRegistered = false;
		try{
			Connection conn = Database.getConnection();
			
			try{
				if(conn != null){
					String SemesterSelect = "Select *"
							+ " FROM university.studentenrollment"
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
	
	public static boolean isStudentOnWaitList(Student student, int offerID){
		boolean isOnWaitList = false;
		try{
			Connection conn = Database.getConnection();
			
			try{
				if(conn != null){
					String SemesterSelect = "Select *"
							+ " FROM university.waitlist"
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

	public static boolean isWaitListEmpty(int offerID){
		boolean isEmpty = false;
		try{
			Connection conn = Database.getConnection();
			
			try{
				if(conn != null){
					String SemesterSelect = "Select *"
							+ " FROM university.waitlist"
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
	
	//complete
	public static boolean isStudentEmailed(Student student, int offerID){
		boolean isEmailed = false;
		try{
			Connection conn = Database.getConnection();
			
			try{
				if(conn != null){
					String SemesterSelect = "Select *"
							+ " FROM university.emailedwaitlist"
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
	
	private static void checkTheStatusOfEmailedStudents(){
		try{
			Connection conn = Database.getConnection();
			
			try{
				if(conn != null){
					String waitListSelect = "Select *"
							+ " FROM university.emailedwaitlist";
					PreparedStatement statement = conn.prepareStatement(waitListSelect);
					ResultSet rs = statement.executeQuery();
					
					while(rs.next()){
						Timestamp t1 = rs.getTimestamp("TimeEmailed");;
						Timestamp t2 = new Timestamp(System.currentTimeMillis());
						long hoursElapsed = findTimeDifference(t1, t2);
						if(hoursElapsed >= 1){
							//remove student from e-mailed list and email the student
							int UIN = rs.getInt("StudentUIN");
							int offerID = rs.getInt("offerID");
							Student s = new Student(UIN);
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
			}
		}
		
		finally{
		}
	}
	
	private static long findTimeDifference(Timestamp t1, Timestamp t2){
		Period p = new Period(t1.getTime(),t2.getTime());
		System.out.println(t1);
		System.out.println(t2);
		System.out.println("Hour difference:"+p.getHours());
		return p.getHours();
	}
	
	public static void main(String[] args){
//		int UIN = 452;
//		System.out.println("On wait list:"+isStudentOnWaitList(new Student(UIN), 295));
//		System.out.println("Is registrabe:"+CourseOffered.isCourseRegistrableBy(new Student(UIN), 295));
//		System.out.println("Can be added to wait list:"+canBeAddedToWaitList(new Student(UIN), 295));
		//emailFirstStudentOnWaitList(295);
		checkTheStatusOfEmailedStudents();
	}
}

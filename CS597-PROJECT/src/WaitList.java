import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class WaitList {
	int offerID;
	int UIN;
	int queuePos;
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

	public static void addStudentToWaitList(Student student, CourseOffered courseOffered){
		
	}
			
	public static void removeFromWaitList(Student student, CourseOffered courseOffered){
		
	}
	
	public static void emailFirstStudentOnWaitList(int offerID){
		Student student = getStudentFirstOnWaitList(offerID);
		
		if(student!=null){
			//add the student to emailed list
			//remove the student from wait list
			//email the student using the username
		}
	}
	
	private static Student getStudentFirstOnWaitList(int offerID){
		Student student = null;
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
						statement.setInt(2, 1);
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
	
	//incomplete
	public static boolean isStudentEmailed(Student student, int offerID){
		boolean isEmailed = false;
		try{
			Connection conn = Database.getConnection();
			
			try{
				if(conn != null){
					String SemesterSelect = "Select *"
							+ " FROM university.emailedwaitlist"
							+ " WHERE offerID= ? and UIN= ?";
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
		
	}
	
	
}

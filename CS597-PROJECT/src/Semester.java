
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
	
	public Semester(){
		try{
			Connection conn = Database.getConnection();
			
			try{
				if(conn != null){
					
					//Retrieve the current semester ID
					String semSelect = "Select *"
							+ " FROM university.semester"
							+ " WHERE isCurrent= ?";
					PreparedStatement statement = conn.prepareStatement(semSelect);
					statement.setInt(1, 1);
					ResultSet rs = statement.executeQuery();
					
					if(rs.first()){
						this.SemesterID = rs.getInt("SemesterID");
						this.SemesterName = rs.getString("SemesterName");
						this.SemesterYear = rs.getTimestamp("SemesterYear");
						this.isCurrent = rs.getInt("IsCurrent");
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
	
	public void commitNextSemester(){
		try{
			Connection conn = Database.getConnection();
			
			try{
				if(conn != null){
					
					//Retrieve the current semester ID
					String semAdd = "Insert into university.semester"
							+ " (SemesterName, SemesterYear, isCurrent)"
							+ " Values(?,?,?)";
					PreparedStatement statement = conn.prepareStatement(semAdd);
					statement.setString(1, this.getSemesterName());
					statement.setTimestamp(2, this.getSemesterYear());
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
	
	public void updateIsCurrent(){
		try{
			Connection conn = Database.getConnection();
			
			try{
				if(conn != null){
					
					String semAdd = "UPDATE university.semester"
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
	
	public String toString(){
		long timeStamp = this.getSemesterYear().getTime();
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(timeStamp);
		int year = c.get(Calendar.YEAR);
		return this.getSemesterName()+" "+year;
	}
	
	public static void main(String[] args){
		Semester s = new Semester();
		s.goToNextSemester();
	}
	
	

}

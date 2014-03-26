import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;


public class CourseOffered {
	private Course course;
	private CourseSchedule courseSchedule;
	private CourseFiles courseFiles;
	private int offerID;
	private String semester;
	private Calendar year;
	private int totalCapacity;
	private int currentlyFilled;
	
	/**
	 * @return the course
	 */
	public Course getCourse() {
		return course;
	}
	/**
	 * @param coure the course to set
	 */
	public void setCourse(Course course) {
		this.course = course;
	}
	/**
	 * @return the courseSchedule
	 */
	public CourseSchedule getCourseSchedule() {
		return courseSchedule;
	}
	/**
	 * @param courseSchedule the courseSchedule to set
	 */
	public void setCourseSchedule(CourseSchedule courseSchedule) {
		this.courseSchedule = courseSchedule;
	}
	/**
	 * @return the courseFiles
	 */
	public CourseFiles getCourseFiles() {
		return courseFiles;
	}
	/**
	 * @param courseFiles the courseFiles to set
	 */
	public void setCourseFiles(CourseFiles courseFiles) {
		this.courseFiles = courseFiles;
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
	 * @return the semester
	 */
	public String getSemester() {
		return semester;
	}
	/**
	 * @param semester the semester to set
	 */
	public void setSemester(String semester) {
		this.semester = semester;
	}
	/**
	 * @return the year
	 */
	public Calendar getYear() {
		return year;
	}
	/**
	 * @param year the year to set
	 */
	public void setYear(Calendar year) {
		this.year = year;
	}
		
	/**
	 * @return the totalCapacity
	 */
	public int getTotalCapacity() {
		return totalCapacity;
	}
	/**
	 * @param totalCapacity the totalCapacity to set
	 */
	public void setTotalCapacity(int totalCapacity) {
		this.totalCapacity = totalCapacity;
	}
	/**
	 * @return the currentlyFilled
	 */
	public int getCurrentlyFilled() {
		return currentlyFilled;
	}
	/**
	 * @param currentlyFilled the currentlyFilled to set
	 */
	public void setCurrentlyFilled(int currentlyFilled) {
		this.currentlyFilled = currentlyFilled;
	}
	/**
	 * @param course
	 * @param courseSchedule
	 * @param courseFiles
	 * @param offerID
	 * @param semester
	 * @param year
	 * @param totalCapacity
	 * @param currentlyFilled
	 */
	public CourseOffered(Course course, CourseSchedule courseSchedule,
			CourseFiles courseFiles, int offerID, String semester, Calendar year,
			int totalCapacity, int currentlyFilled) {
		super();
		this.course = course;
		this.courseSchedule = courseSchedule;
		this.courseFiles = courseFiles;
		this.offerID = offerID;
		this.semester = semester;
		this.year = year;
		this.totalCapacity = totalCapacity;
		this.currentlyFilled = currentlyFilled;
	}
	
	//Initialize a CourseOffered object by looking up the offerID passed into the system.
	public CourseOffered(int offerID){
		setOfferID(offerID);
		try{
			Connection conn = new Database().getConnection();
			
			try{
				if(conn != null){
					String SQLSelect= "Select OfferID, CourseID, Semester, Year, TotalCapacity, SeatsFilled, FileID"
							+ " FROM university.coursesoffered"
							+ " WHERE offerID="+offerID+";";
					PreparedStatement statement = conn.prepareStatement(SQLSelect);					
					
					statement = conn.prepareStatement(SQLSelect);
					ResultSet rs =  statement.executeQuery();
					
					if(rs.first()){
				         //Retrieve by column name
				         int oID = rs.getInt("OfferID");
				         int cID = rs.getInt("CourseID");
				         String semester = rs.getString("Semester");
				         
				         int tCap = rs.getInt("TotalCapacity");
				         int sFld = rs.getInt("SeatsFilled");
				         //int file = rs.getInt("FileID");
				         
				         this.course = new Course(cID);
				         System.out.println(this.getCourse().getCourseID());
				 		 this.courseSchedule = new CourseSchedule(offerID);
				 		 this.courseFiles = new CourseFiles(offerID);
				 		 this.offerID = oID;
				 		 this.semester = semester;
				 		 //this.year = S
				 		 this.totalCapacity = tCap;
				 		 this.currentlyFilled = sFld;
				 		 
				 		 System.out.println(offerID+" "+cID+" "+semester+" "+year.toString()+" "+tCap+" "+sFld);
					}
					
					else{
							        
				        this.course = null;
				 		this.courseSchedule = null;
				 		this.courseFiles = null;
				 		this.offerID = -1;
				 		this.semester = null;
				 		this.year = null;
				 		this.totalCapacity = -1;
				 		this.currentlyFilled = -1;
					}
				}
			}
			
			catch(SQLException e){
				System.out.println("Error is data");
				System.out.println(e);
				Database.rollBackTransaction(conn);
			}
			
			finally{
				Database.closeConnection(conn);
			}
		}
		
		finally{
			System.out.println("Done");
		}
	}

	
	//Add the courseOffered object to the database
	@SuppressWarnings("deprecation")
	public void addCourseOfferingToDatabase(){
		
		try{
			Connection conn = new Database().getConnection();
			
			try{
				if(conn != null){
					String SQLSelect= "Select OfferID, CourseID, Semester, Year, TotalCapacity, SeatsFilled, FileID"
							+ " FROM university.coursesoffered"
							+ " WHERE offerID="+this.offerID+";";
					PreparedStatement statement = conn.prepareStatement(SQLSelect);
					statement = conn.prepareStatement(SQLSelect);
					ResultSet rs =  statement.executeQuery();
					
					if(rs.first()){
						//The object with the offerID already exists
						//Just update the current object with new values
						System.out.println("Updating");
						SQLSelect= "Update university.coursesoffered"
								+ " Set Semester= ?, TotalCapacity= ?,"
								+ " SeatsFilled= ?, CourseID= ?, FileID= ?, Year= ?"
								+ " Where OfferID="+this.offerID+";";

//						SQLSelect= "UPDATE university.coursesoffered"
//								+ " Set SeatsFilled= ?"
//								+ " WHERE OfferID="+this.offerID+";";
						
						statement = conn.prepareStatement(SQLSelect);
						statement.setString(1, this.semester);
						statement.setInt(2, this.totalCapacity);
						statement.setInt(3, this.currentlyFilled);
						statement.setInt(4, this.getCourse().getCourseID());
						statement.setInt(5, this.getCourseFiles().fileID);
						//statement.setDate(6, this.year);
						statement.toString();
						statement.executeUpdate();
					}
					
					else{
						//add the object data to the courseOffered table
						System.out.println("Not Updating");
					}
				}
			}
			
			catch(SQLException e){
				System.out.println("Error updating");
				System.out.println(e.getMessage());
			}
			
			finally{
				Database.closeConnection(conn);
			}
			
		}
		
		finally{
			
		}
	}
	
	
	//get all the students enrolled in the current course offering.
//	public ArrayList<Student> getStudentsInCourse(int OfferId){
//		try{
//			Connection conn = new Database().getConnection();
//			
//			try{
//				if(conn != null){
//					String SQLSelect= "Select StudentUIN"
//							+ " FROM university.coursesoffered"
//							+ " WHERE offerID="+offerID+";";
//					PreparedStatement statement = conn.prepareStatement(SQLSelect);					
//					// For SQLStudentSelect
//					statement = conn.prepareStatement(SQLSelect);
//					ResultSet rs =  statement.executeQuery();
//			}
//		}
//		
//		finally{
//			
//		
//		}
//	
//		}
//
//	}


}

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class CourseOffered {
	private Course course;
	private CourseSchedule courseSchedule;
	private CourseFiles courseFiles;
	private int offerID;
	private String semester;
	private Date year;
	
	/**
	 * @return the course
	 */
	public Course getCoure() {
		return course;
	}
	/**
	 * @param coure the course to set
	 */
	public void setCoure(Course course) {
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
	public Date getYear() {
		return year;
	}
	/**
	 * @param year the year to set
	 */
	public void setYear(Date year) {
		this.year = year;
	}
	/**
	 * @param course
	 * @param courseSchedule
	 * @param courseFiles
	 * @param offerID
	 * @param semester
	 * @param year
	 */
	public CourseOffered(Course course, CourseSchedule courseSchedule,
			CourseFiles courseFiles, int offerID, String semester,
			Date year) {
		super();
		this.course = course;
		this.courseSchedule = courseSchedule;
		this.courseFiles = courseFiles;
		this.offerID = offerID;
		this.semester = semester;
		this.year = year;
	}
	
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
					// For SQLStudentSelect
					statement = conn.prepareStatement(SQLSelect);
					ResultSet rs =  statement.executeQuery();
					
					if(rs.first()){
				         //Retrieve by column name
				         int oID = rs.getInt("OfferID");
				         int cID = rs.getInt("CourseID");
				         String semester = rs.getString("Semester");
				         Date year = rs.getDate("Year");
				         int tCap = rs.getInt("TotalCapacity");
				         int sFld = rs.getInt("SeatsFilled");
				         int file = rs.getInt("FileID");
				         
				         this.course = new Course(cID);
				 		 this.courseSchedule = new CourseSchedule(offerID);
				 		 this.courseFiles = new CourseFiles(offerID);
				 		 this.offerID = oID;
				 		 this.semester = semester;
				 		 this.year = year;
				 		 
				 		 System.out.println(offerID+" "+cID+" "+semester+" "+year.toString()+" "+tCap+" "+sFld);
					}
					
					else{
						int oID = -1;
				        int cID = -1;
				        String semester = null;
				        int year = -1;
				        int tCap = -1;
				        int sFld = -1;
				        int file = -1;
				        
				        this.course = null;
				 		this.courseSchedule = null;
				 		this.courseFiles = null;
				 		this.offerID = offerID;
				 		this.semester = null;
				 		this.year = null;
					}
				}
			}
			
			catch(SQLException e){
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

}

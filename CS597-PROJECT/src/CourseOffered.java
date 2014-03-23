import java.sql.Date;
import java.util.ArrayList;


public class CourseOffered {
	private Course course;
	private CourseSchedule courseSchedule;
	private ArrayList<CourseFiles> courseFiles;
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
	public ArrayList<CourseFiles> getCourseFiles() {
		return courseFiles;
	}
	/**
	 * @param courseFiles the courseFiles to set
	 */
	public void setCourseFiles(ArrayList<CourseFiles> courseFiles) {
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
			ArrayList<CourseFiles> courseFiles, int offerID, String semester,
			Date year) {
		super();
		this.course = course;
		this.courseSchedule = courseSchedule;
		this.courseFiles = courseFiles;
		this.offerID = offerID;
		this.semester = semester;
		this.year = year;
	}
	
	public CourseOffered getCourseOfferedFromID(int OfferID){
		CourseOffered courseoffered = null;
		return courseoffered;
	}
	
	
	
}

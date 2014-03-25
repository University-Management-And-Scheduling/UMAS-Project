
public class Course {
	Department department;
	int courseID;
	String courseName;
	
	public Course(int cID) {
		this.courseID = cID;
	}

	/**
	 * @return the department
	 */
	public Department getDepartment() {
		return department;
	}
	
	/**
	 * @param department the department to set
	 */
	public void setDepartment(Department department) {
		this.department = department;
	}
	
	/**
	 * @return the courseID
	 */
	public int getCourseID() {
		return courseID;
	}
	
	/**
	 * @param courseID the courseID to set
	 */
	public void setCourseID(int courseID) {
		this.courseID = courseID;
	}
	
	/**
	 * @return the courseName
	 */
	public String getCourseName() {
		return courseName;
	}
	
	/**
	 * @param courseName the courseName to set
	 */
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	/**
	 * @param department
	 * @param courseID
	 * @param courseName
	 */
	public Course(Department department, int courseID, String courseName) {
		super();
		this.department = department;
		this.courseID = courseID;
		this.courseName = courseName;
	}
	
	
}

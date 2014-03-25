
public class Department {
	private String departmentName;
	private String departmentID;
	private String departmentAcronym;
	/**
	 * @return the departmentAcronym
	 */
	public String getDepartmentAcronym() {
		return departmentAcronym;
	}
	/**
	 * @param departmentAcronym the departmentAcronym to set
	 */
	public void setDepartmentAcronym(String departmentAcronym) {
		this.departmentAcronym = departmentAcronym;
	}
	/**
	 * @return the departmentName
	 */
	public String getDepartmentName() {
		return departmentName;
	}
	/**
	 * @param departmentName the departmentName to set
	 */
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	/**
	 * @return the departmentID
	 */
	public String getDepartmentID() {
		return departmentID;
	}
	/**
	 * @param departmentID the departmentID to set
	 */
	public void setDepartmentID(String departmentID) {
		this.departmentID = departmentID;
	}
	/**
	 * @param departmentName
	 * @param departmentID
	 * @param departmentAcronym
	 */
	public Department(String departmentName, String departmentID,
			String departmentAcronym) {
		super();
		this.departmentName = departmentName;
		this.departmentID = departmentID;
		this.departmentAcronym = departmentAcronym;
	}
	
	
}

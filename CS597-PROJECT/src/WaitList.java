
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
	
	public static void emailNextStudentForCourse(CourseOffered courseOffered){
		
	}
	
	public static void closeWaitList(CourseOffered courseOffered){
		
	}
	
	public static void removeFromWaitList(Student student, CourseOffered courseOffered){
		
	}
	
	private static boolean isStudentFirstOnWaitList(Student student, CourseOffered courseOffered){
		return false;
	}
	
	private static boolean isStudentRegistered(Student student, CourseOffered courseOffered){
		return false;
	}
	
	private static void checkTheStatusOfEmailedStudent(){
		
	}
	
	
}

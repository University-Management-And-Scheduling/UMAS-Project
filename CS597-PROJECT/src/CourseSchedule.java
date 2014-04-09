
public class CourseSchedule {
	private int OfferID;
	private int classRoomID;
	private int timeSlotID;
	/**
	 * @param offerID
	 * @param classRoomID
	 * @param timeSlotID
	 */
	public CourseSchedule(int offerID, int classRoomID, int timeSlotID) {
		super();
		OfferID = offerID;
		this.classRoomID = classRoomID;
		this.timeSlotID = timeSlotID;
	}
	public CourseSchedule(int offerID) {
		//retrieve course schedule
		
	}
	/**
	 * @return the offerID
	 */
	public int getOfferID() {
		return OfferID;
	}
	/**
	 * @param offerID the offerID to set
	 */
	public void setOfferID(int offerID) {
		OfferID = offerID;
	}
	/**
	 * @return the classRoomID
	 */
	public int getClassRoomID() {
		return classRoomID;
	}
	/**
	 * @param classRoomID the classRoomID to set
	 */
	public void setClassRoomID(int classRoomID) {
		this.classRoomID = classRoomID;
	}
	/**
	 * @return the timeSlotID
	 */
	public int getTimeSlotID() {
		return timeSlotID;
	}
	/**
	 * @param timeSlotID the timeSlotID to set
	 */
	public void setTimeSlotID(int timeSlotID) {
		this.timeSlotID = timeSlotID;
	}
	
}

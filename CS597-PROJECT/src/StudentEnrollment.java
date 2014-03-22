
public class StudentEnrollment {
	int enrollmentID; // Unique id per enrollment
	int UIN; // Student UIN
	int offerID; //OfferID of course offered in a sem
	String studentStatus; //Student Level = Grad or Undergrad
	
	// Constructor to populate of
	public StudentEnrollment(int enrollmentID, int UIN, int offerID, String studentStatus){
		this.enrollmentID = enrollmentID;
		this.UIN = UIN;
		this.offerID = offerID;
		this.studentStatus = studentStatus;
	}
	
	
	
	
	
	
}

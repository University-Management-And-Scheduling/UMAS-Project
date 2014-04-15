
public interface StudentInterface {
	
	public boolean checkIfStudent(int UIN);
	
	public void deleteStudent(int UIN);
	
	public void deleteStudent(String userName);
	
	public void addStudentToDb(String name, Department dept,int level);
	
	public  void addIntoStudentTable(int UIN, int level);
	
	
	

	
}

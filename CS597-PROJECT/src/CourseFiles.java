import java.nio.file.Paths;
import java.util.ArrayList;

public class CourseFiles {
	int fileID;
	ArrayList<Paths> paths;
	/**
	 * @return the fileID
	 */
	public int getFileID() {
		return fileID;
	}
	/**
	 * @param fileID the fileID to set
	 */
	public void setFileID(int fileID) {
		this.fileID = fileID;
	}
	/**
	 * @return the path
	 */
	public Paths getPath() {
		return getPath();
	}
	/**
	 * @param path the path to set
	 */
	public void setPath(ArrayList<Paths> path) {
		this.paths = path;
	}
	/**
	 * @param fileID
	 * @param path
	 */
	public CourseFiles(int fileID, ArrayList<Paths> paths) {
		super();
		this.fileID = fileID;
		this.paths = paths;
	}
	
	public CourseFiles(int offerID){
		
	}
	
}

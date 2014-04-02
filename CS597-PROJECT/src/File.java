import java.util.ArrayList;
import java.util.List;


public class File {
int fileID;
String fileLocation;
int offerID;


public File(int fileID, String fileLocation, int offerID) {
	this.fileID = fileID;
	this.fileLocation = fileLocation;
	this.offerID = offerID;
}

public int getFileID() {
	return fileID;
}

public void setFileID(int fileID) {
	this.fileID = fileID;
}

public String getFileLocation() {
	return fileLocation;
}

public void setFileLocation(String fileLocation) {
	this.fileLocation = fileLocation;
}


// Add a new file to file table in the database
public static void addFileToDB(File file){
	// Add File to database
}


// Get a list of files for a single course
public static List<File> getFiles(int offerID){
	ArrayList<File> courseFiles = new ArrayList<File>();
	int fileID = 0;
	String fileLocation = null;
	
	// DB Code
	
	File file = new File(fileID, fileLocation, offerID);
	courseFiles.add(file);
	
	return courseFiles;
	
}

}

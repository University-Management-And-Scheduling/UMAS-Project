import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;


public class File {
int fileID;
String fileName;
String fileLocation;
int offerID;

@Target({ElementType.LOCAL_VARIABLE})
@Retention(RetentionPolicy.RUNTIME)
public @interface DBAnnotation {
 String[] variable () default "";
 String[] table () default "";
 String[] column () default "";
 boolean[] isSource () default false; 
}

public File(int fileID, String fileName, String fileLocation, int offerID) {
	this.fileID = fileID;
	this.fileName = fileName;
	this.fileLocation = fileLocation;
	this.offerID = offerID;
}

public int getFileID() {
	return fileID;
}

public void setFileID(int fileID) {
	this.fileID = fileID;
}

public String getFileName() {
	return fileName;
}

public void setFileName(String fileName) {
	this.fileName = fileName;
}

public String getFileLocation() {
	return fileLocation;
}

public void setFileLocation(String fileLocation) {
	this.fileLocation = fileLocation;
}

public int getOfferID() {
	return offerID;
}

public void setOfferID(int offerID) {
	this.offerID = offerID;
}

// Add a new file to file table in the database
public static boolean addFileToDB(String fileName, String fileLocation, int offerID){
	boolean fileAdded = false;
	
	String newFileLocation = fileLocation.replace("/","//");
	@DBAnnotation (
			variable = {"fileName","newfileLocation", "offerID"},  
			table = "files", 
			column = {"FileName","FileLocation", "OfferID"}, 
			isSource = false)
	
	String SQLFileInsert = "INSERT INTO files (FileName,FileLocation,OfferID) VALUES(?,?,?);";

	// Step 1: Check if file is already present
	try {
		Connection conn = Database.getConnection();
		try {
			if (conn != null) {
				
				// Check if file is already present. 
				
				boolean isFilePresent = isFilePresent(fileName, newFileLocation, offerID);
				
				// If present, confirm whether it needs to be replaced
				String addFileToDB = "yes";
				
				if(isFilePresent = true){
					Scanner in = new Scanner(System.in);
					System.out.println("File already Present. Do you want to Replace it? Yes/No: ");
					addFileToDB = in.next();
				}
					
				// Add file in database
				if(addFileToDB.toLowerCase() == "yes"){
					PreparedStatement statement = conn.prepareStatement(SQLFileInsert);
					statement.setString(1, fileName);
					statement.setString(2, newFileLocation);
					statement.setInt(3, offerID);
					statement.executeUpdate();
					Database.commitTransaction(conn);
					fileAdded = true;
				}		
			}
		} catch (SQLException e) {
			System.out.println(e);
			Database.rollBackTransaction(conn);;
		}

	} catch (Exception e) {
		System.out.println(e);
	}
	
	return fileAdded;
		
}

private static boolean isFilePresent(String fileName, String fileLocation, int offerID){
	boolean isFilePresent = false;
	
	@DBAnnotation (
			variable = "fileName",  
			table = "files", 
			column = "FileName", 
			isSource = true)
	
	String SQLFileSelect = "SELECT FileName FROM files WHERE OfferID = ? AND FileLocation = ?;";
	
	try {
		Connection conn = Database.getConnection();
		try {
			if (conn != null) {
				
				// Check if file is already present. 
				PreparedStatement statement = conn.prepareStatement(SQLFileSelect);
				statement.setInt(1, offerID);
				statement.setString(2, fileLocation);
				ResultSet rs = statement.executeQuery();
				while (rs.next()) {
					// Retrieve by column name
					String tableFileName = rs.getString("FileName");
					if(tableFileName == fileName){
						isFilePresent = true;
						break;
					}
				}
			}	
		} catch (SQLException e) {
			System.out.println(e);
		}

	} catch (Exception e) {
		System.out.println(e);
	}
	
	return isFilePresent;
}

public boolean deleteFileFromDB(){
	boolean fileDeleted = false;
	
	int fileID = this.getFileID();
	String fileName = this.getFileName();
	String fileLocation = this.getFileLocation();
	int offerID = this.getOfferID();
	
	boolean isFilePresent = isFilePresent(fileName,fileLocation,offerID);
	if(isFilePresent == false){
		System.out.println("The file is not present");
	} else {
		@DBAnnotation (
				variable = {"fileID"},  
				table = "files", 
				column = {"FileID"}, 
				isSource = false)
		
		String SQLFileSelect = "DELETE FROM files WHERE FileID = ?;";
		try {
			Connection conn = Database.getConnection();
			try {
				if (conn != null) {
					PreparedStatement statement = conn.prepareStatement(SQLFileSelect);
					statement.setInt(1, fileID);				
					statement.executeUpdate();
					Database.closeConnection(conn);
					fileDeleted = true;
				}
			} catch (SQLException e) {
				System.out.println(e);
				Database.rollBackTransaction(conn);
			}

		} catch (Exception e) {
			System.out.println(e);
		}

	}
	
	return fileDeleted;
}

public boolean modifyFileLocation(String newFileLocation){
	boolean fileLocationModified = false;
	
	int fileID = this.getFileID();
	
	String fileName = this.getFileName();
	String fileLocation = this.getFileLocation();
	int offerID = this.getOfferID();
	boolean isFilePresent = isFilePresent(fileName,fileLocation,offerID);
	if(isFilePresent == false){
		System.out.println("The file is not present");
	} else {
		@DBAnnotation (
				variable = {"fileID","newFileLocation"},  
				table = "files", 
				column = {"FileID","FileLocation"}, 
				isSource = false)
		
		String SQLFileSelect = "UPDATE files SET `FileLocation`= ? WHERE `FileID`= ? ;";
		try {
			Connection conn = Database.getConnection();
			try {
				if (conn != null) {
					PreparedStatement statement = conn.prepareStatement(SQLFileSelect);
					statement.setString(1, newFileLocation);
					statement.setInt(2, fileID);				
					statement.executeUpdate();
					Database.closeConnection(conn);
					fileLocationModified = true;
				}
			} catch (SQLException e) {
				System.out.println(e);
				Database.rollBackTransaction(conn);
			}

		} catch (Exception e) {
			System.out.println(e);
		}

	}

	return fileLocationModified;
}

public int getFileIDFromDB(String fileName,String fileLocation){
	int fileID = 0;
	
	@DBAnnotation (
			variable = {"fileID", "fileName","fileLocation"},  
			table = "files", 
			column = {"FileID","FileName","FileLocation"}, 
			isSource = {true})
	
	String SQLFileSelect = "SELECT FileID FROM files WHERE FileName = ?, FileLocation = ?;";
	try {
		Connection conn = Database.getConnection();
		try {
			if (conn != null) {
				PreparedStatement statement = conn.prepareStatement(SQLFileSelect);
				statement.setString(1, fileName);
				statement.setString(1, fileLocation);
				
				ResultSet rs = statement.executeQuery();
				
				while(rs.next()){
					fileID = rs.getInt("FileID");
				}
			}
		} catch (SQLException e) {
			System.out.println(e);
		}

	} catch (Exception e) {
		System.out.println(e);
	}
	
	return fileID;
}

// Get a list of files for a single course
public static ArrayList<File> getFiles(int offerID){
	
	@DBAnnotation (
			variable = {"fileID", "fileName","fileLocation", "offerID"},  
			table = "files", 
			column = {"FileID","FileName","FileLocation", "OfferID"}, 
			isSource = {false,false,false,true})
	
	ArrayList<File> courseFiles = new ArrayList<File>();
	int fileID = 0;
	String fileName = null;
	String fileLocation = null;
	
	String SQLFileSelect = "SELECT FileID, FileName, FileLocation FROM files WHERE OfferID = ?;";
	try {
		Connection conn = Database.getConnection();
		try {
			if (conn != null) {
				PreparedStatement statement = conn.prepareStatement(SQLFileSelect);
				statement.setInt(1, offerID);
				
				ResultSet rs = statement.executeQuery();
				
				while(rs.next()){
					fileID = rs.getInt("FileID");
					fileName = rs.getString("FileName");
					fileLocation = rs.getString("FileLocation");
					File file = new File(fileID, fileName, fileLocation, offerID);
					courseFiles.add(file);
				}
			}
		} catch (SQLException e) {
			System.out.println(e);
		}

	} catch (Exception e) {
		System.out.println(e);
	}
	
	return courseFiles;
	
}

}


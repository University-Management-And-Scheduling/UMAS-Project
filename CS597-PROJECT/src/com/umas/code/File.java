package com.umas.code;
//import java.lang.annotation.ElementType;
//import java.lang.annotation.Retention;
//import java.lang.annotation.RetentionPolicy;
//import java.lang.annotation.Target;
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
	
//	@Target({ElementType.LOCAL_VARIABLE})
//	@Retention(RetentionPolicy.RUNTIME)
//	public @interface DBAnnotation {
//	 String[] variable () default "";
//	 String[] table () default "";
//	 String[] column () default "";
//	 boolean[] isSource () default false; 
//	}
	
	
	// Constructor
	public File(int fileID, String fileName, String fileLocation, int offerID) {
		super();
		this.fileID = fileID;
		this.fileName = fileName;
		this.fileLocation = fileLocation;
		this.offerID = offerID;
	}

	// Constructor
	public File(String fileName, String fileLocation, int offerID) {
		
		boolean isFilePresent = isFilePresent(fileName, fileLocation, offerID);
		if (isFilePresent == true ){
			int fileID = File.getFileIDFromDB(fileName, fileLocation);
			this.fileID = fileID;
			
		} else{
			this.fileID = 0;
		}
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
		// String newFileLocation = fileLocation.replace("/","//");
//		@DBAnnotation (
//				variable = {"fileName","newfileLocation", "offerID"},  
//				table = "files", 
//				column = {"FileName","FileLocation", "OfferID"}, 
//				isSource = false)
		
		String SQLFileInsert = "INSERT INTO files (FileName,FileLocation,OfferID) VALUES(?,?,?);";
		
		
		// Step 1: Check if file is already present
		try {
			Connection conn = Database.getConnection();
			try {
				if (conn != null) {
					
					// Check if file is already present. 
					
					boolean isFilePresent = isFilePresent(fileName, fileLocation, offerID);
					
					// If present, confirm whether it needs to be replaced
					String addFileToDB = "yes";
					
					if(isFilePresent == true){
//						Scanner in = new Scanner(System.in);
//						System.out.println("File already Present. Do you want to Replace it? Yes/No: ");
//						addFileToDB = in.next();
//						in.close();
						fileAdded = false;
					}
				
						
					// Add file in database
					
					// if(addFileToDB.toLowerCase().equals("yes")){
					else{	
						File file = new File(fileName, fileLocation, offerID);
						file.deleteFileFromDB();
						PreparedStatement statement = conn.prepareStatement(SQLFileInsert);
						DBAnnotation.annoate("fileName", "files", "FileName", false);
						statement.setString(1, fileName);
						DBAnnotation.annoate("fileLocation", "files", "FileLocation", false);
						statement.setString(2, fileLocation);
						DBAnnotation.annoate("offerID", "files", "OfferID", false);
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
	
	// Checks whether the file is already present in the same file location and for the same course 
	private static boolean isFilePresent(String fileName, String fileLocation, int offerID){
		boolean isFilePresent = false;
//		String newFileLocation = fileLocation.replace("/","//");
//		@DBAnnotation (
//				variable = "fileName",  
//				table = "files", 
//				column = "FileName", 
//				isSource = true)
		
		String SQLFileSelect = "SELECT FileName FROM files WHERE OfferID = ? AND FileName = ? AND FileLocation = ?;";
		
		try {
			Connection conn = Database.getConnection();
			try {
				if (conn != null) {
					
					// Check if file is already present. 
					PreparedStatement statement = conn.prepareStatement(SQLFileSelect);
					DBAnnotation.annoate("offerID", "files", "OfferID", false);
					statement.setInt(1, offerID);
					DBAnnotation.annoate("fileName", "files", "FileName", false);
					statement.setString(2, fileName);
					DBAnnotation.annoate("fileLocation", "files", "FileLocation", false);
					statement.setString(3, fileLocation);
					ResultSet rs = statement.executeQuery();
					while (rs.next()) {
						// Retrieve by column name
						DBAnnotation.annoate("fileName", "files", "FileName", true);
						String tableFileName = rs.getString("FileName");
						if(tableFileName.equals(fileName)){
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
	
	// Deletes the file information from the file table 
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
//			@DBAnnotation (
//					variable = {"fileID"},  
//					table = "files", 
//					column = {"FileID"}, 
//					isSource = false)
			
			String SQLFileSelect = "DELETE FROM files WHERE FileID = ?;";
			try {
				Connection conn = Database.getConnection();
				try {
					if (conn != null) {
						PreparedStatement statement = conn.prepareStatement(SQLFileSelect);
						DBAnnotation.annoate("fileID", "files", "FileID", false);
						statement.setInt(1, fileID);				
						statement.executeUpdate();
						Database.commitTransaction(conn);
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
	
	// This function modifes the file location of an existing file to the new location
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
//			@DBAnnotation (
//					variable = {"fileID","fileLocation"},  
//					table = "files", 
//					column = {"FileID","FileLocation"}, 
//					isSource = false)
			
			String SQLFileSelect = "UPDATE files SET `FileLocation`= ? WHERE `FileID`= ? ;";
			try {
				Connection conn = Database.getConnection();
				try {
					if (conn != null) {
						PreparedStatement statement = conn.prepareStatement(SQLFileSelect);
						DBAnnotation.annoate("newFileLocation", "files", "FileLocation", false);
						statement.setString(1, newFileLocation);
						DBAnnotation.annoate("fileID", "files", "FileID", false);
						statement.setInt(2, fileID);				
						statement.executeUpdate();
						Database.commitTransaction(conn);
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
	
	// This function retrives the file details from the File table
	public static int getFileIDFromDB(String fileName,String fileLocation){
		int fileID = 0;
		
//		@DBAnnotation (
//				variable = {"fileID", "fileName","fileLocation"},  
//				table = "files", 
//				column = {"FileID","FileName","FileLocation"}, 
//				isSource = {true})
		
		String SQLFileSelect = "SELECT FileID FROM files WHERE FileName = ? AND FileLocation = ?;";
		try {
			Connection conn = Database.getConnection();
			try {
				if (conn != null) {
					PreparedStatement statement = conn.prepareStatement(SQLFileSelect);
					DBAnnotation.annoate("fileName", "files", "FileName", false);
					statement.setString(1, fileName);
					DBAnnotation.annoate("fileLocation", "files", "FileLocation", false);
					statement.setString(2, fileLocation);
					
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
//		
//		@DBAnnotation (
//				variable = {"fileID", "fileName","fileLocation", "offerID"},  
//				table = "files", 
//				column = {"FileID","FileName","FileLocation", "OfferID"}, 
//				isSource = {false,false,false,true})
		
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
					DBAnnotation.annoate("offerID", "files", "OfferID", false);
					statement.setInt(1, offerID);
					
					ResultSet rs = statement.executeQuery();
					
					while(rs.next()){
						DBAnnotation.annoate("fileID", "files", "FileID", false);
						fileID = rs.getInt("FileID");
						DBAnnotation.annoate("fileName", "files", "FileName", false);
						fileName = rs.getString("FileName");
						DBAnnotation.annoate("fileLocation", "files", "FileLocation", false);
						fileLocation = rs.getString("FileLocation");
						// String newFileLocation = fileLocation.replace("/","//");
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
	
	public static void main(String[] args){
//		String fileName = "CS442Syllabus";
//		String fileLocation = "C:/CS442";
//		int offerID = 123456;
//		
//		File file = new File(fileName,fileLocation,offerID);
//		
//		boolean fileAdded = File.addFileToDB(fileName, fileLocation, offerID);
//		
//		if(fileAdded == true){
//			System.out.println("File Added");
//		} else {
//			System.out.println("File Not Added");
//		}
		
		
		// To test delete function
//		boolean fileDeleted = file.deleteFileFromDB();
//		
//		if(fileDeleted == true){
//			System.out.println("File Deleted");
//		} else {
//			System.out.println("File Not Deleted");
//		}
		
		// To test modify location
//		String newFileLocation = "C:/Courses/CS442";
//		boolean fileLocationModified = file.modifyFileLocation(newFileLocation); 
//				
//		
//		if(fileLocationModified == true){
//			System.out.println("File Added");
//		} else {
//			System.out.println("File Not Added");
//		}
		
		// To test get files
//		 ArrayList<File> files = File.getFiles(offerID); 
//				
//		
//		for(File file2: files){
//			System.out.println(file2.getFileName());
//		}
		
	}
	
	}
	

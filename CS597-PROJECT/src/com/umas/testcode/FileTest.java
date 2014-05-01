package com.umas.testcode;
import com.umas.code.*;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class FileTest {

	// OfferID must be present in the CourseOffered Table in the db
	@Test
	public void testAddFileToDB() {
		String fileName = "CS442Syllabus";
		String fileLocation = "C:/CS410";
		int offerID = 410;
		
		File file = new File(fileName,fileLocation,offerID);
//		file.deleteFileFromDB();
		boolean fileAdded = File.addFileToDB(fileName, fileLocation, offerID);
		
		assertEquals(true,fileAdded);
	}
	
	// File already present 
	@Test
	public void testAddFileToFail() {
		String fileName = "CS442Syllabus";
		String fileLocation = "C:/CS410";
		int offerID = 410;
		
		File file = new File(fileName,fileLocation,offerID);
		
		boolean fileAdded = File.addFileToDB(fileName, fileLocation, offerID);
		
		assertEquals(false,fileAdded);
	}

	@Test
	public void testModifyFileLocation() {
		String fileName = "CS442Syllabus";
		String fileLocation = "C:/CS410";
		int offerID = 410;
		File file = new File(fileName,fileLocation,offerID);
		boolean fileDeleted = file.modifyFileLocation("C:/Syllabus/CS410") ;
		assertEquals(true,fileDeleted);
		
	}
	
	// File must be already present to be deleted
	@Test
	public void testDeleteFileFromDB() {
		String fileName = "CS442Syllabus";
		String fileLocation = "C:/Syllabus/CS410";
		int offerID = 410;
		
		File file = new File(fileName,fileLocation,offerID);
		boolean fileDeleted = file.deleteFileFromDB() ;
		assertEquals(true,fileDeleted);

	}


	@Test
	public void testGetFiles() {
		 ArrayList<File> files = File.getFiles(410); 
			
		for(File file2: files){
			System.out.println(file2.getFileName());
		}
		assertNotNull(files);
	}

}

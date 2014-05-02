package com.umas.testcode;
import com.umas.code.*;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.After;
import org.junit.Test;


public class CourseExamStructureTest {

	
	@Test
	public void testAddNewExam() {
		int offerID = 410;
		String examName = "Assgn3";
		int totalMarks = 20; 
		CourseOffered offeredCourse = null;
		try {
			offeredCourse = offeredCourse = new CourseOffered(offerID);
		} catch (Course.CourseDoesNotExistException e1) {
			e1.printStackTrace();
		} catch (CourseOffered.CourseOfferingDoesNotExistException e1) {
			e1.printStackTrace();
		}

		CourseExamStructure examStruct = new CourseExamStructure(offeredCourse,examName,totalMarks);
		examStruct.deleteExistingExam();
		boolean examAdded = examStruct.addNewExam();
		assertEquals(true,examAdded);
	}

	
	@Test
	public void testModifyExistingExamName() {
		System.out.println("-----testModifyExistingExamName----");
		int offerID = 410;
		String examName = "Assgn3";
		
		int totalMarks = 20; 
		CourseOffered offeredCourse = null;
		try {
			offeredCourse = offeredCourse = new CourseOffered(offerID);
		} catch (Course.CourseDoesNotExistException e1) {
			e1.printStackTrace();
		} catch (CourseOffered.CourseOfferingDoesNotExistException e1) {
			e1.printStackTrace();
		}
		
		CourseExamStructure examStruct = new CourseExamStructure(offeredCourse,examName,totalMarks);
		examStruct.deleteExistingExam();
		examStruct.addNewExam();
		boolean examModified = examStruct.modifyExistingExamName("Assgn4");
		examStruct = new CourseExamStructure(offeredCourse,"Assgn4",totalMarks);
		examStruct.modifyExistingExamName("Assgn3");
		System.out.println("-----testModifyExistingExamName----");
		assertEquals(true,examModified);
		
	}
	
//	@After
//	public void reinititalizeModify(){
//		int offerID = 410;
//		String examName = "Assgn4";
//		
//		int totalMarks = 20; 
//		CourseOffered offeredCourse = null;
//		try {
//			offeredCourse = offeredCourse = new CourseOffered(offerID);
//		} catch (CourseDoesNotExistException e1) {
//			e1.printStackTrace();
//		} catch (CourseOfferingDoesNotExistException e1) {
//			e1.printStackTrace();
//		}
//		
//		CourseExamStructure examStruct = new CourseExamStructure(offeredCourse,examName,totalMarks);
//		
//		boolean examModified = examStruct.modifyExistingExamName("Assgn3");
//	}

	@Test
	public void testModifyExistingExamTotalMarks() {
		System.out.println("-----testModifyExistingExamTotal----");
		int offerID = 410;
		String examName = "Assgn3";
		int totalMarks = 20; 
		CourseOffered offeredCourse = null;
		try {
			offeredCourse = offeredCourse = new CourseOffered(offerID);
		} catch (Course.CourseDoesNotExistException e1) {
			e1.printStackTrace();
		} catch (CourseOffered.CourseOfferingDoesNotExistException e1) {
			e1.printStackTrace();
		}

		@SuppressWarnings("unused")
		CourseExamStructure examStruct = new CourseExamStructure(offeredCourse,examName,totalMarks);
		System.out.println("-----testModifyExistingExamTotal----");
		boolean examModified = examStruct.modifyExistingExamTotalMarks(30);
		
		assertEquals(true,examModified);
	}


	
	@Test
	public void testDeleteExistingExam() {
		int offerID = 410;
		String examName = "Assgn4";
		int totalMarks = 30; 
		CourseOffered offeredCourse = null;
		try {
			offeredCourse = offeredCourse = new CourseOffered(offerID);
		} catch (Course.CourseDoesNotExistException e1) {
			e1.printStackTrace();
		} catch (CourseOffered.CourseOfferingDoesNotExistException e1) {
			e1.printStackTrace();
		}
		
		CourseExamStructure examStruct = new CourseExamStructure(offeredCourse,examName,totalMarks);
		examStruct.addNewExam();
		boolean examDeleted = examStruct.deleteExistingExam();
		assertEquals(true,examDeleted);
	}
	
	@Test
	public void testDeleteExistingExamFail() {
		int offerID = 410;
		String examName = "Assgn4";
		int totalMarks = 30; 
		CourseOffered offeredCourse = null;
		try {
			offeredCourse = offeredCourse = new CourseOffered(offerID);
		} catch (Course.CourseDoesNotExistException e1) {
			e1.printStackTrace();
		} catch (CourseOffered.CourseOfferingDoesNotExistException e1) {
			e1.printStackTrace();
		}

		@SuppressWarnings("unused")
		CourseExamStructure examStruct = new CourseExamStructure(offeredCourse,examName,totalMarks);
		
		boolean examDeleted = examStruct.deleteExistingExam();
		assertEquals(false,examDeleted);
	}

	@Test
	public void testViewExams() {
		int offerID = 410; 
		CourseOffered offeredCourse = null;
		try {
			offeredCourse = offeredCourse = new CourseOffered(offerID);
		} catch (Course.CourseDoesNotExistException e1) {
			e1.printStackTrace();
		} catch (CourseOffered.CourseOfferingDoesNotExistException e1) {
			e1.printStackTrace();
		}
		
		HashMap<String, Integer> exams = CourseExamStructure.viewExams(offeredCourse);
		
		assertNotNull(exams);
		
		
	}

}

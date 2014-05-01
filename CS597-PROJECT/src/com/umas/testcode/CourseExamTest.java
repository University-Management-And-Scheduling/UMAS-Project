package com.umas.testcode;
import com.umas.code.*;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import org.junit.Test;


public class CourseExamTest {

	
//	To add mks for a student
	@Test
	public void testAddStudentMarks() {
		
		int offerID = 410;
		CourseOffered offeredCourse = null;
		try {
			offeredCourse = new CourseOffered(offerID);
		} catch (Course.CourseDoesNotExistException e) {
			e.printStackTrace();
		} catch (CourseOffered.CourseOfferingDoesNotExistException e) {
			e.printStackTrace();
		}
		String examName = "Assgn3";
		int UIN = 584;
		Student student=null;
		try {
			student = new Student(UIN);
		} catch (People.PersonDoesNotExistException e) {
			e.printStackTrace();
		}
		double marks = 8.5; 
		HashMap<Student,Double> examMarks = new HashMap<Student,Double>();
		examMarks.put(student, marks);
		UIN = 589;
		marks = 7.5;
		try {
			student = new Student(UIN);
		} catch (People.PersonDoesNotExistException e) {
			e.printStackTrace();
		}
		examMarks.put(student, marks);
		CourseExams exams = new CourseExams(offerID,examName,examMarks);
		
		boolean marksAdded = exams.addStudentMarks();
		assertEquals(true,marksAdded);
	}
	
	
	@Test
	public void testGetStudentMarks() {
		int offerID = 410;
		CourseOffered offeredCourse = null;
		try {
			offeredCourse = new CourseOffered(offerID);
		} catch (Course.CourseDoesNotExistException e) {
			e.printStackTrace();
		} catch (CourseOffered.CourseOfferingDoesNotExistException e) {
			e.printStackTrace();
		}
		CourseExams exams = new CourseExams(offerID);
		CourseExams marks = exams.getStudentMarks();
		HashMap<Student,Double> examMarks = marks.getExamMarks();
		Set<Student> keys = examMarks.keySet();
		Iterator<Student> keyIterator = keys.iterator();
		while (keyIterator.hasNext()) {
			Student student = keyIterator.next();
			int UIN = student.getUIN();
			double studeMarks = (double) examMarks.get(student);
			System.out.println("UIN: " + UIN + " TotalMarks: " + studeMarks);
		}
		
		assertNotNull(examMarks);
	}

	@Test
	public void testGetStudentMarksCourseOfferedStudent() {
		int offerID = 410;
		CourseOffered offeredCourse = null;
		try {
			offeredCourse = new CourseOffered(offerID);
		} catch (Course.CourseDoesNotExistException e) {
			e.printStackTrace();
		} catch (CourseOffered.CourseOfferingDoesNotExistException e) {
			e.printStackTrace();
		}
		int UIN = 584;
		Student student=null;
		try {
			student = new Student(UIN);
		} catch (People.PersonDoesNotExistException e) {
			e.printStackTrace();
		}
		HashMap<String, Double> examMarks = CourseExams.getStudentMarks(offeredCourse,student);
		Set<String> keys = examMarks.keySet();
		for(String exam:keys){
			System.out.println("Exam: " + exam);
			System.out.println("Marks: " + examMarks.get(exam));
		}
		assertNotNull(examMarks);
	}

	@Test
	public void testGetStudents() {
		int offerID = 410;
		CourseOffered offeredCourse = null;
		try {
			offeredCourse = new CourseOffered(offerID);
		} catch (Course.CourseDoesNotExistException e) {
			e.printStackTrace();
		} catch (CourseOffered.CourseOfferingDoesNotExistException e) {
			e.printStackTrace();
		}
		
		HashMap<Integer, Double> studentMarks = CourseExams.getStudents(offeredCourse,"Assign2");
		Set<Integer> keys = studentMarks.keySet();
		System.out.println("-----------------------");
		for(Integer studentUIN:keys){
			System.out.println("UIN: " + studentUIN);
			System.out.println("Marks: " + studentMarks.get(studentUIN));
		}
		assertNotNull(studentMarks);
	}
	
	@Test
	public void testGetStudentsFail() {
		int offerID = 410;
		CourseOffered offeredCourse = null;
		try {
			offeredCourse = new CourseOffered(offerID);
		} catch (Course.CourseDoesNotExistException e) {
			e.printStackTrace();
		} catch (CourseOffered.CourseOfferingDoesNotExistException e) {
			e.printStackTrace();
		}
		
		HashMap<Integer, Double> studentMarks = CourseExams.getStudents(offeredCourse,"Assign4");
		Set<Integer> keys = studentMarks.keySet();
		System.out.println("-----------------------");
		for(Integer studentUIN:keys){
			System.out.println("UIN: " + studentUIN);
			System.out.println("Marks: " + studentMarks.get(studentUIN));
		}
		assertEquals(0,studentMarks.size());
	}

	@Test
	public void testModifyStudentMarks() {
		int offerID = 410;
		String examName = "Assgn3";
		
		int UIN = 584;
		Student student=null;
		try {
			student = new Student(UIN);
		} catch (People.PersonDoesNotExistException e) {
			e.printStackTrace();
		}
		double marks = 9; 
		HashMap<Student,Double> examMarks = new HashMap<Student,Double>();
		examMarks.put(student, marks);
		CourseExams courseExams = new CourseExams(offerID,examName,examMarks);
		
		boolean marksModified = courseExams.modifyStudentMarks();
		assertEquals(true,marksModified);
	}

	@Test
	public void testViewAllExams() {
		int offerID = 410;
		CourseOffered offeredCourse = null;
		try {
			offeredCourse = new CourseOffered(offerID);
		} catch (Course.CourseDoesNotExistException e) {
			e.printStackTrace();
		} catch (CourseOffered.CourseOfferingDoesNotExistException e) {
			e.printStackTrace();
		}
		CourseExams exams = new CourseExams(offerID);
		ArrayList<String> allExams = exams.viewAllExams();
		assertNotNull(allExams);
		
	}

}

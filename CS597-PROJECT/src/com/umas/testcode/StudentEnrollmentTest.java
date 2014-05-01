package com.umas.testcode;
import com.umas.code.*;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Test;


public class StudentEnrollmentTest {

	@Test
	public void testGetAllGradesOfStudent() {
		int UIN = 600;
		int offerID = 410;
		Student student = null;
		try {
			student = new Student(UIN);
		} catch (People.PersonDoesNotExistException e) {
			e.printStackTrace();
		}
		
		HashMap<CourseOffered, String> grades = StudentEnrollment.getAllGradesOfStudent(student);
		for(CourseOffered offeredCourse: grades.keySet() ){
			offerID = offeredCourse.getOfferID();
			String grade = grades.get(offeredCourse);
			System.out.println("Course: "+ offerID + " Grade: " + grade);
		}
		
		assertNotNull(grades);
	}

	@Test
	public void testGetStudentsAllCourses() {
		int UIN = 600;
		Student student = null;
		try {
			student = new Student(UIN);
		} catch (People.PersonDoesNotExistException e) {
			e.printStackTrace();
		}
		ArrayList<CourseOffered> courses = StudentEnrollment.getStudentsAllCourses(student);
		for(CourseOffered offeredCourse: courses){
			int offerID = offeredCourse.getOfferID();
			System.out.println("Course: "+ offerID);			
		}
		
		assertNotNull(courses);
	}

	@Test
	public void testGetStudentsInCourse() {
		int offerID = 410;
		CourseOffered offeredCourse = null;
		try {
			offeredCourse = new CourseOffered(offerID);
		} catch (Course.CourseDoesNotExistException
				| CourseOffered.CourseOfferingDoesNotExistException e) {
			e.printStackTrace();
		}
		
		// Test getStudentsInCourse function
		ArrayList<Student> students = StudentEnrollment.getStudentsInCourse(offeredCourse);
		for(Student stud: students){
			int UIN = stud.getUIN();
			System.out.println("Student: "+ UIN);			
		}
		
		assertNotNull(students);
	}

	@Test
	public void testGetCurrentCoursesOfStudent() {
		int UIN = 600;
		Student student = null;
		try {
			student = new Student(UIN);
		} catch (People.PersonDoesNotExistException e) {
			e.printStackTrace();
		}
		System.out.println("---------------------------");
		ArrayList<CourseOffered> courses = StudentEnrollment.getCurrentCoursesOfStudent(student);
		for(CourseOffered offeredCourse: courses){
			int offerID = offeredCourse.getOfferID();
			System.out.println("Course: "+ offerID);			
		}
		System.out.println("---------------------------");
		assertNotNull(courses);
	}

	@Test
	public void testUpdateAllStudentGrade() {
		int UIN = 600;
		int offerID = 414;
		CourseOffered course = null;
		try {
			course = new CourseOffered(offerID);
		} catch (Course.CourseDoesNotExistException e) {
			e.printStackTrace();
		} catch (CourseOffered.CourseOfferingDoesNotExistException e) {
			e.printStackTrace();
		}
		Student student = null;
		try {
			student = new Student(UIN);
		} catch (People.PersonDoesNotExistException e) {
			e.printStackTrace();
		}
		HashMap<Student,String> courseCurve = new HashMap<Student,String>();
		courseCurve.put(student, "C");
		StudentEnrollment.updateAllStudentGrade(courseCurve, course);
	}
	

	@Test
	public void testEnrollStudents() {
		int UIN = 601;
		int offerID = 414;
		StudentEnrollment enrolledStud = new StudentEnrollment(offerID,UIN);
		boolean enrolled =  enrolledStud.enrollStudents();
		assertEquals(true,enrolled);
	}

	@Test
	public void testUnregisterStudent() {
		int UIN = 601;
		int offerID = 414;
		StudentEnrollment enrolledStud = new StudentEnrollment(offerID,UIN);
		boolean unenrolled =  enrolledStud.unregisterStudent();
		assertEquals(true,unenrolled);

	}	
}

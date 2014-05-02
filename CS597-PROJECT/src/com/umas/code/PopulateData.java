package com.umas.code;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;

import com.umas.code.Course.CourseAlreadyExistsException;


public class PopulateData {

		
	public boolean populateDepartments(){
		
		boolean done=false;
		try{
			
			Connection conn=Database.getConnection();
			
			try{
				if(conn != null){
					
					//Retrieve the current semester ID
					String SemesterSelect = "Select * FROM namesdept";
					PreparedStatement statement = conn.prepareStatement(SemesterSelect);
					ResultSet rs = statement.executeQuery();
					
					while(rs.next()){
						
						try{
							
								System.out.println("Adding new dept");
								String deptName=rs.getString("names");
								Department.addNewDepartment(deptName);
								System.out.println(deptName);
								Thread.sleep(10);
								done=true;
								
							
						}
						
						catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (Department.DepartmentAlreadyExistsException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
				}
				
			}
			
			catch(SQLException e){
				System.out.println(e.getMessage());
				e.printStackTrace();
			} 
				
			finally{
				//Database.commitTransaction(conn);
			}
		}
		
		finally{
		}
		return done;	
	}
		
	
	//50 administrators will be added
	public boolean populateAdmins(){
		
		boolean done=false;
		try{
			
			Connection conn=Database.getConnection();
			
			try{
				if(conn != null){
					
					ArrayList<Department> getAllDepts=Department.getAllDepartments();
					//Retrieve the current semester ID
					String SemesterSelect = "Select * FROM names3 order by rand() LIMIT 50";
					PreparedStatement statement = conn.prepareStatement(SemesterSelect);
					ResultSet rs = statement.executeQuery();
							
					while(rs.next()){
						int size=getAllDepts.size();
						int rand=(int)(Math.random()*size);
						Department d=getAllDepts.get(rand);
						String name = rs.getString(1);
						
						try{
							
								System.out.println("Adding new admin");
								Admin.addAdmin(name, d);
								System.out.println(d.getDepartmentName()+"-------"+name);
								Thread.sleep(10);
								done=true;
							
						}
						
						catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (People.loginDetailsnotAdded e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
				}
					
			}
			
			catch(SQLException e){
				System.out.println(e.getMessage());
				e.printStackTrace();
			} 
				
			finally{
				//Database.commitTransaction(conn);
			}
		}
		
		finally{
		}
		
		return done;
	}
	
	public boolean populateProfessors(){
		
		boolean done=false;
		try{
			
			Connection conn=Database.getConnection();
			
			try{
				if(conn != null){
					
					ArrayList<Department> getAllDepts=Department.getAllDepartments();
					//Retrieve the current semester ID
					String SemesterSelect = "Select * FROM names3";
					PreparedStatement statement = conn.prepareStatement(SemesterSelect);
					ResultSet rs = statement.executeQuery();
					
					while(rs.next()){
						
						int size=getAllDepts.size();
						int rand=(int)(Math.random()*size);
						Department d=getAllDepts.get(rand);
						String name = rs.getString(1);
						
						try{
								System.out.println("Adding new professor");
								Professor.addProfToDb(name, d);
								System.out.println(d.getDepartmentName()+"------"+name);
								Thread.sleep(10);	
								done=true;
						}
						
						catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
				}
					
			}
			
			catch(SQLException e){
				System.out.println(e.getMessage());
				e.printStackTrace();
			} 
				
			finally{
				//Database.commitTransaction(conn);
			}
		}
		
		finally{
		}
		
		return done;
	}
	
	public boolean populateStudents(){
		
		boolean done=false;
		try{
			
			Connection conn=Database.getConnection();
			
			try{
				if(conn != null){
					
					ArrayList<Department> getAllDepts=Department.getAllDepartments();
					//Retrieve the current semester ID
					String SemesterSelect = "Select * FROM names2";
					PreparedStatement statement = conn.prepareStatement(SemesterSelect);
					ResultSet rs = statement.executeQuery();
					
					while(rs.next()){
						
						String name = rs.getString(1);
						int size=getAllDepts.size();
						int rand=(int)(Math.random()*size);
						Department d=getAllDepts.get(rand);
						int level=0;
						
						while(level==0 || level == 4){
							level = (int)(Math.random()*4);
						}
						
						
						try{
							
								System.out.println("Adding new students");
								Student.addStudentToDb(name, d, level);
								System.out.println("Level:"+level);
								System.out.println(d.getDepartmentName()+"---------"+name);
								System.out.println(name);
								Thread.sleep(10);
								done=true;
							
						}
						
						catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} 
						catch (Student.levelNotExistException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
				}
					
			}
			
			catch(SQLException e){
				System.out.println(e.getMessage());
				e.printStackTrace();
			} 
				
			finally{
				//Database.commitTransaction(conn);
			}
		}
		
		finally{
		}
		
		return done;
	}
	
	public boolean populateCourses(){
		
		boolean done=false;
		try{
			
			Connection conn=Database.getConnection();
			
			try{
				if(conn != null){
					
					ArrayList<Department> departments = Department.getAllDepartments();
					//Retrieve the current semester ID
					String SemesterSelect = "Select * FROM names1";
					PreparedStatement statement = conn.prepareStatement(SemesterSelect);
					ResultSet rs = statement.executeQuery();
					
					while(rs.next()){
						String name = rs.getString(1);
						Collections.shuffle(departments);
						Department d = departments.get(0);
						Course.addCourse(name, d);
						Thread.sleep(10);
						done=true;
					}
					
				}
					
			}
			
			catch(SQLException e){
				System.out.println(e.getMessage());
				e.printStackTrace();
			} catch (Course.CourseAlreadyExistsException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
				
			finally{
				//Database.commitTransaction(conn);
			}
		}
		
		finally{
		}
		
		return done;
	}
		
	public boolean populateCoursesOffered(){
		
		boolean done=false;
		try{
			
			Connection conn=Database.getConnection();
			
			try{
				if(conn != null){
					
					ArrayList<Department> departments = Department.getAllDepartments();
					int count = 0;
					for(Department d:departments){
						ArrayList<Course> courses = Course.getCoursesOfDepartment(d);
						ArrayList<Professor> profs = Professor.getAllProfInADept(d.getDepartmentID());
						int[] capacity = {5,8,10};
						
						for(Course course:courses){
							Collections.shuffle(profs);
							Professor p = profs.get(0);
							int cap = (int)(Math.random()*3);
							System.out.println("Course:"+course.getCourseName()+"--------------"+p.getName());							
							CourseOffered.addCourseOfferingToDatabase(course, p, capacity[cap]);
							Thread.sleep(10);
							done = true;
						}
					}
				}
					
			} catch (Professor.ProfessorDoesNotExistException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (CourseOffered.CourseOfferingAlreadyExistsException e) {
				System.out.println("------------------------------Offering already exists");
			} catch (CourseOffered.CourseOfferingNotSchedulable e) {
				System.out.println("*******************************Offering not schedulable");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
				
			finally{
				//Database.commitTransaction(conn);
			}
		}
		
		finally{
		}
		return done;
	}
	
	public boolean populateApplicationDetails(){
		
		boolean done=false;
		try{
			
			Connection conn=Database.getConnection();
			
			try{
				if(conn != null){
					
					ArrayList<Student> getAllStudents=Student.getAllStudents();
					
					
					for(int i=0;i<150;i++){
						Collections.shuffle(getAllStudents);
						Student s = getAllStudents.remove(0);
						
						boolean[] skills=new boolean[5];
						for(int j=0;j<5;j++){
							int x = (int)(Math.random()*2);
							if(x==0)
								skills[j]=false;
							else if(x==1)
								skills[j]=true;
						}
						
						double[] workEx = {1.0,1.5,2.0,2.5,3,3.5,4};
						
						double workExSelected = workEx[(int)(Math.random()*7)];
						
						System.out.println(" "+skills[0]+skills[1]+skills[2]+skills[3]+skills[4]);
						System.out.println(workExSelected);
						
						boolean x = JobApplication.addApplicationDetails(s.getUIN(), workExSelected,skills[0], skills[1], skills[2], skills[3], skills[4]);
						if(!x)
							System.out.println("*****************************Application exists:"+s.getUIN());
						Thread.sleep(10);
						done=true;
						
					}
					
				}
					
			} 
			catch (People.PersonDoesNotExistException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
				
			finally{
				//Database.commitTransaction(conn);
			}
		}
		
		finally{
		}
		
		return done;
	}
		
	public boolean populateJobPostings(){
		
		boolean done=false;
		try{
			
			Connection conn=Database.getConnection();
			
			try{
				if(conn != null){
					
					ArrayList<Professor> getAllProfessors=Professor.getAllProf();
					
					
					for(int i=0;i<50;i++){
						Collections.shuffle(getAllProfessors);
						Professor p = getAllProfessors.remove(0);
						
						boolean[] skills=new boolean[5];
						for(int j=0;j<5;j++){
							int x = (int)(Math.random()*2);
							if(x==0)
								skills[j]=false;
							else if(x==1)
								skills[j]=true;
						}
						
						double[] gPA = {2.5, 3, 3.5, 3.6, 3.7, 3.8, 4};
						double gPASelected = gPA[(int)(Math.random()*7)];
						
						double[] workEx = {1.0,1.5,2.0,2.5,3,3.5,4};
						double workExSelected = workEx[(int)(Math.random()*7)];
						
						System.out.println(" "+skills[0]+skills[1]+skills[2]+skills[3]+skills[4]);
						System.out.println(workExSelected);
						System.out.println(gPASelected);
						
						int x=Job.postJob(p.getUIN(), p.getDeptID(), gPASelected, workExSelected,skills[0], skills[1], skills[2], skills[3], skills[4]);
						System.out.println("Posted job is: "+x);
						Thread.sleep(10);
						done=true;
						
					}
					
				}
					
			} 
			catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			catch (Job.NoPermissionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
				
			finally{
				//Database.commitTransaction(conn);
			}
		}
		
		finally{
		}
		return done;
	}
		
	public boolean populateTAs(){
		
		boolean done=false;
		try{
			
			Connection conn=Database.getConnection();
			
			try{
				if(conn != null){
					
					ArrayList<CourseOffered> getAllCoursesOffered=CourseOffered.getAllCurrentlyOfferedCourses();
					ArrayList<Student> getAllStudents=Student.getAllStudents();
					
					
					for(int i=0;i<100;i++){
						Collections.shuffle(getAllCoursesOffered);
						Collections.shuffle(getAllStudents);

						CourseOffered c=getAllCoursesOffered.remove(0);
						Student s=getAllStudents.remove(0);
						
						
						System.out.println(s.getUIN()+"--------"+c.getOfferID());
						boolean check=TA.addTAtoTAtable(s.getUIN(), c.getOfferID());
						if(!check){
							System.out.println("---------------------TA already exists"+s.getUIN()+"------"+c.getOfferID());
						}
						Thread.sleep(10);
						done=true;
						
					}
					
				}
					
			} 
			catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (TA.AlreadyExistsInTAException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
				
			finally{
				//Database.commitTransaction(conn);
			}
		}
		
		finally{
		}
		return done;
	}
		
	public boolean populateExams(){
		
		boolean done=false;
		try{
			
			Connection conn=Database.getConnection();
			
			try{
				if(conn != null){
					
					ArrayList<CourseOffered> getAllOfferedCourses=CourseOffered.getAllCurrentlyOfferedCourses();
					
					

					for(CourseOffered c:getAllOfferedCourses){
						
						int[] marks = {20,30,40,50};
						int getMarks = marks[(int)(Math.random()*4)];
						String name="assign";
						
						for(int i=0;i<3;i++){
							
							CourseExamStructure examAddition=new CourseExamStructure(c, name+""+(i+1), getMarks);
							boolean check=examAddition.addNewExam();
							if(check)
								System.out.println(c.getOfferID()+"-------"+name+""+(i+1)+"-----------"+getMarks);
							else{
								System.out.println("------------------exam not added----------------------");
							}
						
						}
						
						

						Thread.sleep(10);
						done=true;
					}
					}
					
				
					
			} 
			catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
				
			finally{
				//Database.commitTransaction(conn);
			}
		}
		
		finally{
		}
		
		return done;
	}
	
	public boolean populateStudentsToCourses(){
		
		boolean done=false;
		try{
			
			Connection conn=Database.getConnection();
			
			try{
				if(conn != null){
					
					ArrayList<CourseOffered> getAllOfferedCourses=CourseOffered.getAllCurrentlyOfferedCourses();				
					ArrayList<Student> getAllStudents=Student.getAllStudents();

					for(CourseOffered c:getAllOfferedCourses){
						System.out.println("--------------------------------------------------------------------");
						if(getAllStudents.size()<=10){
							getAllStudents = Student.getAllStudents();
						}
						
						for(int i=0;i<5;i++){
							
							Collections.shuffle(getAllStudents);
							Student s=getAllStudents.remove(0);

							StudentEnrollment se=new StudentEnrollment(c.getOfferID(), s.getUIN());
							boolean check=se.enrollStudents();
							if(check){
								System.out.println(""+c.getOfferID()+"-----------"+s.getUIN());
								Thread.sleep(10);
								done=true;
							}
							else{
								System.out.println("-------------------no capacity-----------------------------");
							}
					}
						
					}
				}
			}
					 
			catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
				
			finally{
				//Database.commitTransaction(conn);
			}
		}
		
		finally{
		}
		return done;
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		boolean checkDept=false;
		boolean checkAdmin=false;
		boolean checkProf=false;
		boolean checkStud=false;
		boolean checkCourses=false; 
		boolean checkCourseOffered=false;
		boolean checkAppDetails =false;
		boolean checkJobPostings=false;
		boolean checkTAs=false;
		boolean checkExams=false;
		boolean checkStudentsToCourse=false;
		
		PopulateData p=new PopulateData();
		
		
		checkDept=p.populateDepartments();
		
		if(checkDept){
			 checkAdmin= p.populateAdmins();
			}
		else{
			System.out.println("dept not added---stopped");
		}
		
		if(checkAdmin){
			 checkProf= p.populateProfessors();
			}
		else{
			System.out.println("Admin not added---stopped");
		}
		
		if(checkProf){
			 checkStud = p.populateStudents();
			}
		else{
			System.out.println("Prof not added---stopped");
		}

		if(checkStud){
			 checkCourses = p.populateCourses();
			}
		else{
			System.out.println("Students not added---stopped");
		}
		
		if(checkCourses){
			 checkCourseOffered = p.populateCoursesOffered();
			}
		else{
			System.out.println("Courses not added---stopped");
		}
		
		if(checkCourseOffered){
			 checkAppDetails = p.populateApplicationDetails();
			}
		else{
			System.out.println("Course offered not added---stopped");
		}
		
		if(checkAppDetails){
			 checkJobPostings = p.populateJobPostings();
			}
		else{
			System.out.println("Applications not added---stopped");
		}
		
		if(checkJobPostings){
			 checkTAs = p.populateTAs();
			}
		else{
			System.out.println("Postings not added---stopped");
		}
		
		if(checkTAs){
			checkExams = p.populateExams();
			}
		else{
			System.out.println("TAs not added---stopped");
		}
		
		if(checkExams){
			checkStudentsToCourse = p.populateStudentsToCourses();
			}
		else{
			System.out.println("exams not added---stopped");
		}
		
		if(checkStudentsToCourse){
			System.out.println("All done");
			}
		else{
			System.out.println("Students to courses not added---stopped");
		}

	}

}

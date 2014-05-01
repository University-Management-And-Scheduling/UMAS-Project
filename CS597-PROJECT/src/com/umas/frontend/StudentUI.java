package com.umas.frontend;


/****************@author Simant Purohit*********************************/

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;



import com.umas.code.Course;
import com.umas.code.CourseOffered;
import com.umas.code.DBAnnotation;
import com.umas.code.Department;
import com.umas.code.People;
import com.umas.code.Student;
import com.umas.code.StudentEnrollment;
import com.umas.code.WaitList;
import com.umas.code.Course.CourseDoesNotExistException;
import com.umas.code.CourseOffered.CourseOfferingDoesNotExistException;
import com.umas.code.Department.DepartmentDoesNotExistException;
import com.umas.code.People.PersonDoesNotExistException;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;


public class StudentUI extends JPanel {

	/**
	 * 
	 */
	private static Student student;
	private static StudentUI studentCourseUI;
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JComboBox<String> selectDepartmentCombo;
	private JComboBox<String> selectCourseCombo;
	private JComboBox<Integer> selectOfferingCombo;
	private JTextArea txtrCourseDetails;
	private JButton btnRegister;
	private JButton btnAddToWait;
	private static JTabbedPane currentCoursesTab;
	private static JTabbedPane previousCourses;
	private static JTabbedPane waitListTab;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentUI frame = new StudentUI(new Student(451));
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	public static StudentUI getInstance(Student s){
		studentCourseUI = new StudentUI(s);		
		return studentCourseUI;
	}
	
	private StudentUI(final Student s) {
		student = s;
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		//contentPane = new JPanel();
		setBorder(new EmptyBorder(5, 5, 5, 5));
		//setContentPane(contentPane);
		setLayout(new BorderLayout(0, 0));
		
		//------------------Course------------------//
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		add(tabbedPane, BorderLayout.CENTER);
		
		JPanel courseRegistration = new JPanel();
		tabbedPane.addTab("Course Registration", null, courseRegistration, null);
		courseRegistration.setLayout(null);
		
		selectDepartmentCombo = new JComboBox<String>();
		selectDepartmentCombo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ArrayList<Course> departmentCourses = new ArrayList<Course>();
				LinkedHashMap<Integer, CourseOffered> courseOfferings = new LinkedHashMap<Integer, CourseOffered>();
				String departmentName = (String)selectDepartmentCombo.getSelectedItem();
				Department dept;
				btnRegister.setEnabled(false);
				btnAddToWait.setEnabled(false);
				try {
					dept = new Department(departmentName);
					departmentCourses = dept.getDepartmentCourses();
					
					if(departmentCourses.size()>0){
						courseOfferings = departmentCourses.get(0).getCurrentOfferings();
					}
					
					DefaultComboBoxModel<String> coursesModel = new DefaultComboBoxModel<String>();
					DefaultComboBoxModel<Integer> offerModel = new DefaultComboBoxModel<Integer>();
					
					
					for(Course c:departmentCourses){
						coursesModel.addElement(c.getCourseName());
					}
					
					for(Integer i:courseOfferings.keySet()){
						offerModel.addElement(i);
					}
					
					selectCourseCombo.setModel(coursesModel);
					selectOfferingCombo.setModel(offerModel);
					
					if(offerModel.getSize()>0){
						selectOfferingCombo.setSelectedIndex(0);
						setCourseDetailsTextBoxInRegistrationsTab((Integer)selectOfferingCombo.getSelectedItem());
						try {
							CourseOffered courseOffered = courseOfferings.get((Integer)selectOfferingCombo.getSelectedItem());
							boolean isRegisterable = courseOffered.isCourseRegistrableBy(student);
							boolean isWaitListEligible = WaitList.canBeAddedToWaitList(student, courseOffered.getOfferID());
							if(isRegisterable){
								btnRegister.setEnabled(true);
							}
							if(isWaitListEligible){
								btnAddToWait.setEnabled(true);
							}
							
						} catch (Course.CourseDoesNotExistException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (CourseOffered.CourseOfferingDoesNotExistException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					else{
						txtrCourseDetails.setText("Course details not available");
						btnRegister.setEnabled(false);
						btnAddToWait.setEnabled(false);
					}

				} catch (Department.DepartmentDoesNotExistException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}			
			}
		});
		selectDepartmentCombo.setBounds(10, 69, 115, 20);
		courseRegistration.add(selectDepartmentCombo);
		
		selectCourseCombo = new JComboBox<String>();
		selectCourseCombo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LinkedHashMap<Integer, CourseOffered> courseOfferings = new LinkedHashMap<Integer, CourseOffered>();
				String courseName = (String)selectCourseCombo.getSelectedItem();
				Course course;
				btnRegister.setEnabled(false);
				btnAddToWait.setEnabled(false);
				try {
					course = new Course(courseName);
					courseOfferings = course.getCurrentOfferings();
					
					DefaultComboBoxModel<Integer> offerModel = new DefaultComboBoxModel<Integer>();
					
					for(Integer i:courseOfferings.keySet()){
						offerModel.addElement(i);
					}
					
					selectOfferingCombo.setModel(offerModel);
					
					if(offerModel.getSize()>0){
						selectOfferingCombo.setSelectedIndex(0);
						setCourseDetailsTextBoxInRegistrationsTab((Integer)selectOfferingCombo.getSelectedItem());
						try {
							CourseOffered courseOffered = courseOfferings.get((Integer)selectOfferingCombo.getSelectedItem());
							boolean isRegisterable = courseOffered.isCourseRegistrableBy(student);
							boolean isWaitListEligible = WaitList.canBeAddedToWaitList(student, courseOffered.getOfferID());
							if(isRegisterable){
								btnRegister.setEnabled(true);
							}
							if(isWaitListEligible){
								btnAddToWait.setEnabled(true);
							}
							
						} catch (Course.CourseDoesNotExistException ex) {
							// TODO Auto-generated catch block
							ex.printStackTrace();
						} catch (CourseOffered.CourseOfferingDoesNotExistException ex) {
							// TODO Auto-generated catch block
							ex.printStackTrace();
						}
					}
					else{
						txtrCourseDetails.setText("Course details not available");
						btnRegister.setEnabled(false);
						btnAddToWait.setEnabled(false);
					}

					
					
				}
				
				catch(Exception ex){
					ex.printStackTrace();
				}
			}
		});
		selectCourseCombo.setBounds(190, 69, 115, 20);
		courseRegistration.add(selectCourseCombo);
		
		selectOfferingCombo = new JComboBox<Integer>();
		selectOfferingCombo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnRegister.setEnabled(false);
				btnAddToWait.setEnabled(false);
				if(selectOfferingCombo.getModel().getSize()>0){
					setCourseDetailsTextBoxInRegistrationsTab((Integer)selectOfferingCombo.getSelectedItem());
					try {
						CourseOffered courseOffered = new CourseOffered((Integer)selectOfferingCombo.getSelectedItem());
						boolean isRegisterable = courseOffered.isCourseRegistrableBy(student);
						boolean isWaitListEligible = WaitList.canBeAddedToWaitList(student, courseOffered.getOfferID());
						if(isRegisterable){
							btnRegister.setEnabled(true);
						}
						if(isWaitListEligible){
							btnAddToWait.setEnabled(true);
						}
						
					} catch (Course.CourseDoesNotExistException ex) {
						// TODO Auto-generated catch block
						ex.printStackTrace();
					} catch (CourseOffered.CourseOfferingDoesNotExistException ex) {
						// TODO Auto-generated catch block
						ex.printStackTrace();
					}
				}
				else{
					txtrCourseDetails.setText("Course details not available");
					btnRegister.setEnabled(false);
					btnAddToWait.setEnabled(false);
				}
			}
		});
		selectOfferingCombo.setBounds(362, 69, 115, 20);
		courseRegistration.add(selectOfferingCombo);
		
		btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int offerID = (Integer)selectOfferingCombo.getSelectedItem();
				
				DBAnnotation.annoate("uin", "student", "UIN", true);
				int uin = student.getUIN();
				StudentEnrollment se = new StudentEnrollment(offerID, uin);
				boolean flag = se.enrollStudents();
				if(flag){
					showMessage("Successfully registered", "Success");
					try {
						DBAnnotation.annoate("uin2", "student", "UIN", true);
						int uin2 = student.getUIN();
						StudentUI.student = new Student(uin2);
						emptyTabs();
					} catch (People.PersonDoesNotExistException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				else
					showMessage("Unable to register", "Failure");
				
				initializeAllComboBoxesInRegistrationTab();
			}
		});
		btnRegister.setEnabled(false);
		btnRegister.setBounds(10, 363, 115, 23);
		courseRegistration.add(btnRegister);
		
		btnAddToWait = new JButton("Add to wait list");
		btnAddToWait.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int offerID = (Integer)selectOfferingCombo.getSelectedItem();
				try {
					WaitList.addStudentToWaitList(student, offerID);
					showMessage("Added successfully to wait list", "Success");
					DBAnnotation.annoate("uin", "student", "UIN", true);
					int uin = student.getUIN();
					StudentUI.student = new Student(uin);
					initializeAllComboBoxesInRegistrationTab();
					emptyTabs();
				} catch (Course.CourseDoesNotExistException e1) {
					showMessage("Failed to add to wait list", "Failure");
					e1.printStackTrace();
				} catch (CourseOffered.CourseOfferingDoesNotExistException e1) {
					showMessage("Failed to add to wait list", "Failure");
					e1.printStackTrace();
				} catch (People.PersonDoesNotExistException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnAddToWait.setEnabled(false);
		btnAddToWait.setBounds(190, 363, 115, 23);
		courseRegistration.add(btnAddToWait);
		
		JLabel lblSelectDepartment = new JLabel("Select Department");
		lblSelectDepartment.setBounds(10, 44, 115, 14);
		courseRegistration.add(lblSelectDepartment);
		
		JLabel lblSelectCourse = new JLabel("Select Course");
		lblSelectCourse.setBounds(190, 44, 115, 14);
		courseRegistration.add(lblSelectCourse);
		
		txtrCourseDetails = new JTextArea();
		txtrCourseDetails.setFont(new Font("Times New Roman", Font.BOLD, 14));
		txtrCourseDetails.setLineWrap(true);
		txtrCourseDetails.setForeground(Color.WHITE);
		txtrCourseDetails.setBackground(Color.BLACK);
		txtrCourseDetails.setEditable(false);
		txtrCourseDetails.setText("Course Offering Details");
		txtrCourseDetails.setBounds(10, 119, 467, 189);
		courseRegistration.add(txtrCourseDetails);
		
		JLabel lblSelectOffering = new JLabel("Select Offering");
		lblSelectOffering.setBounds(362, 44, 115, 14);
		courseRegistration.add(lblSelectOffering);
		
		currentCoursesTab = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.addTab("My Current Courses", null, currentCoursesTab, null);
		
		previousCourses = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.addTab("Previous courses", null, previousCourses, null);
		
		waitListTab = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.addTab("Wait List Courses", null, waitListTab, null);
		
		
		//initialization calls
		initializeAllComboBoxesInRegistrationTab();
		initializeCurrentCourses();
		
		Thread waitListMonitor = new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(true){
					int size = waitListTab.getTabCount();
					ArrayList<CourseOffered> studentCourses= WaitList.getWaitListCoursesOfStudent(student);
					if(size!=studentCourses.size()){
						emptyTabs();
					}
					try {
						Thread.sleep(10000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		
		waitListMonitor.start();
	}
	
	private void initializeAllComboBoxesInRegistrationTab(){
		ArrayList<Department> departments = Department.getAllDepartments();
		ArrayList<Course> departmentCourses = new ArrayList<Course>();
		
		LinkedHashMap<Integer, CourseOffered> courseOfferings = new LinkedHashMap<Integer, CourseOffered>();
		
		if(departments.size()>0){
			departmentCourses = departments.get(0).getDepartmentCourses();
			
			if(departmentCourses.size()>0){
				courseOfferings = departmentCourses.get(0).getCurrentOfferings();
			}
		}
		
		DefaultComboBoxModel<String> deptModel = new DefaultComboBoxModel<String>();
		DefaultComboBoxModel<String> coursesModel = new DefaultComboBoxModel<String>();
		DefaultComboBoxModel<Integer> offerModel = new DefaultComboBoxModel<Integer>();
		
		for(Department d:departments){
			deptModel.addElement(d.getDepartmentName());
		}
		
		for(Course c:departmentCourses){
			coursesModel.addElement(c.getCourseName());
		}
		
		for(Integer i:courseOfferings.keySet()){
			offerModel.addElement(i);
		}
		
		selectDepartmentCombo.setModel(deptModel);
		selectCourseCombo.setModel(coursesModel);
		selectOfferingCombo.setModel(offerModel);
		btnRegister.setEnabled(false);
		btnAddToWait.setEnabled(false);
		if(offerModel.getSize()>0){
			selectOfferingCombo.setSelectedIndex(0);
			setCourseDetailsTextBoxInRegistrationsTab((Integer)selectOfferingCombo.getSelectedItem());
			try {
				CourseOffered courseOffered = courseOfferings.get((Integer)selectOfferingCombo.getSelectedItem());
				boolean isRegisterable = courseOffered.isCourseRegistrableBy(student);
				boolean isWaitListEligible = WaitList.canBeAddedToWaitList(student, courseOffered.getOfferID());
				if(isRegisterable){
					btnRegister.setEnabled(true);
				}
				if(isWaitListEligible){
					btnAddToWait.setEnabled(true);
				}
				
			} catch (Course.CourseDoesNotExistException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (CourseOffered.CourseOfferingDoesNotExistException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else{
			txtrCourseDetails.setText("Course details not available");
			btnRegister.setEnabled(false);
			btnAddToWait.setEnabled(false);
		}
		
		
	}
	
	private void setCourseDetailsTextBoxInRegistrationsTab(int offerID){
		CourseOffered courseOffered;
		String details = "";
		try {
			courseOffered = new CourseOffered(offerID);
			details += "Course Name: "+courseOffered.getCourseName();
			details += "\nTaught by Professor: "+courseOffered.getProfessorName();
			details += "\nClassroom Location: "+courseOffered.getClassRoomLocation();
			details += "\nClassroom Name: "+courseOffered.getClassRoomName();
			details += "\nTimings: "+courseOffered.getTiming();
			details += "\nTotal Capacity: "+courseOffered.getTotalCapacity();
			details += "\nAvailable Seats: "+(courseOffered.getTotalCapacity() - courseOffered.getCurrentlyFilled());
			if(WaitList.isStudentRegistered(student, offerID)){
				details+= "\n\n YOU ARE ALREADY REGISTERED FOR THIS COURSE";
			}
			
			if(WaitList.isStudentOnWaitList(student, offerID)){
				details+= "\n\n YOU ARE ALREADY ON WAIT LIST FOR THIS COURSE";
			}
			
			txtrCourseDetails.setText(details);
		} catch (Course.CourseDoesNotExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			txtrCourseDetails.setText(details);
		}catch (CourseOffered.CourseOfferingDoesNotExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			txtrCourseDetails.setText(details);
		}
	}

	private static void initializeCurrentCourses(){
		try {			
			ArrayList<CourseOffered> studentCourses = CourseOffered.getStudentCourses(student);
			for(CourseOffered c:studentCourses){
				if(c.checkIfCurrent()){
					currentCoursesTab.add(c.getCourseName(),new singleCoursePanel(student, c));
				}
				
				else{
					previousCourses.add(c.getCourseName(), new singleCoursePanel(student, c));
				}
			}
			
			studentCourses = WaitList.getWaitListCoursesOfStudent(student);
			for(CourseOffered c:studentCourses){
				if(c.checkIfCurrent()){
					waitListTab.add(c.getCourseName(),new singleCoursePanel(student, c));
				}
				
			}
			
		} catch (Course.CourseDoesNotExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CourseOffered.CourseOfferingDoesNotExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void emptyTabs(){
		currentCoursesTab.removeAll();
		previousCourses.removeAll();
		waitListTab.removeAll();
		initializeCurrentCourses();
	}
	
	public void showMessage(String message, String title)
    {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
    }
}


/****************@author Simant Purohit*********************************/

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;




public class manageDeptCourses extends JTabbedPane {
	private static Admin admin;
	private static Department adminDepartment;
	private static manageDeptCourses deptCoursesTab;
	
	
	private static final long serialVersionUID = 1L;
	private JTextField textField;
	
	//-----update COURSE variables-------------//
	private JTextField textField_1;
	private JComboBox<String> allCoursesCombo;
	private JButton btnConfirmUpdate;
	
	//-------------UPDATE COURSE OFFERING VARIABLES------------------//
	private JComboBox<Integer> updateOfferIDCombo;
	private JComboBox<String> updateOfferProfessorCombo;
	private JButton btnUpdateOffer;
	private ArrayList<CourseSchedule> allDeptCoursesOffered;
	

	public static manageDeptCourses getInstance(Admin a){
		try {
			deptCoursesTab = new manageDeptCourses(a);
			return deptCoursesTab;
		} catch (Department.DepartmentDoesNotExistException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private manageDeptCourses(Admin a) throws Department.DepartmentDoesNotExistException {
		
		//-------------ADD A COURSE CODE----------//
		manageDeptCourses.admin = a;
		manageDeptCourses.adminDepartment = new Department(admin.getDeptID());
		JPanel addACourse = new JPanel();
		addTab("Add a Course", null, addACourse, null);
		addACourse.setLayout(new GridLayout(3, 2, 0, 0));
		
		JLabel lblCourseName = new JLabel("Course Name");
		addACourse.add(lblCourseName);
		
		textField = new JTextField();
		addACourse.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Course Department");
		addACourse.add(lblNewLabel);
		
		JLabel lblDepartment = new JLabel(adminDepartment.getDepartmentName());
		addACourse.add(lblDepartment);
		
		JLabel lblNewLabel_1 = new JLabel("Confirm Changes");
		addACourse.add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("Add course");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addCourse();
				textField.setText("");
			}
		});
		addACourse.add(btnNewButton);
		//-------------END ADD COURSE-------------//
		
		
		//------------UPDATE COURSE CODE---------//
		JPanel updateCourse = new JPanel();
		addTab("Update a course", null, updateCourse, null);
		updateCourse.setLayout(new GridLayout(4, 2, 0, 0));
		
		JLabel lblSelectCourse = new JLabel("Select Course");
		updateCourse.add(lblSelectCourse);
		
		allCoursesCombo = new JComboBox<String>();
		allCoursesCombo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textField_1.setEnabled(true);
				btnConfirmUpdate.setEnabled(true);
				
				try {
					Course courseSelected = new Course((String)allCoursesCombo.getSelectedItem());
					textField_1.setText(courseSelected.getCourseName());
					
				} catch (Course.CourseDoesNotExistException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		updateCourse.add(allCoursesCombo);
		
		JLabel lblSelectedCourseName = new JLabel("Selected Course Name");
		updateCourse.add(lblSelectedCourseName);
		
		textField_1 = new JTextField();
		textField_1.setEnabled(false);
		updateCourse.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblSelectedCourseDepartment = new JLabel("Selected Course Department");
		updateCourse.add(lblSelectedCourseDepartment);
		
		JLabel lblDepartment_1 = new JLabel(adminDepartment.getDepartmentName());
		updateCourse.add(lblDepartment_1);
		
		JLabel lblUpdateWithAbove = new JLabel("Update with above values");
		updateCourse.add(lblUpdateWithAbove);
		
		btnConfirmUpdate = new JButton("Confirm update");
		btnConfirmUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				updateCourse();
				DepartmentAdminUI.initializeAllTabs();
				textField_1.setEnabled(false);
				btnConfirmUpdate.setEnabled(false);
			}
		});
		btnConfirmUpdate.setEnabled(false);
		updateCourse.add(btnConfirmUpdate);
		
		
		//--------------UPDATE COURSE OFFERING CODE----------------//
		JPanel updateCourseOffering = new JPanel();
		addTab("Update Course Offering", null, updateCourseOffering, null);
		updateCourseOffering.setLayout(new GridLayout(3, 2, 0, 0));
		
		JLabel lblSelectOfferId = new JLabel("Select Offer ID");
		updateCourseOffering.add(lblSelectOfferId);
		
		updateOfferIDCombo = new JComboBox<Integer>();
		updateOfferIDCombo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				updateOfferProfessorCombo.setEnabled(true);
				btnUpdateOffer.setEnabled(true);
				
				try {
					CourseOffered co = new CourseOffered((Integer)updateOfferIDCombo.getSelectedItem());
					DefaultComboBoxModel<String> profModel = new DefaultComboBoxModel<String>();
					ArrayList<Professor> deptProfessor = Professor.getAllProfInADept(adminDepartment.getDepartmentID());
					for(Professor p:deptProfessor){
						profModel.addElement(p.getUserName());
					}
					updateOfferProfessorCombo.setModel(profModel);
					String professorName = co.getProfessor().getUserName();
					updateOfferProfessorCombo.setSelectedIndex(getProfessorIndex(professorName));
					
				} catch (Professor.ProfessorDoesNotExistException e) {
					System.out.println("Failed to initialize list");
					e.printStackTrace();
				} catch (Course.CourseDoesNotExistException e) {
					System.out.println("Failed to initialize list");
					e.printStackTrace();
				} catch (CourseOffered.CourseOfferingDoesNotExistException e) {
					System.out.println("Failed to initialize list");
					e.printStackTrace();
				}
								
			}
		});
		updateCourseOffering.add(updateOfferIDCombo);
		
		JLabel lblProfessor = new JLabel("Professor");
		updateCourseOffering.add(lblProfessor);
		
		updateOfferProfessorCombo = new JComboBox<String>();
		updateOfferProfessorCombo.setEnabled(false);
		updateCourseOffering.add(updateOfferProfessorCombo);
		
		JLabel lblLabel = new JLabel("Confirm update");
		updateCourseOffering.add(lblLabel);
		
		btnUpdateOffer = new JButton("Update CourseOffering");
		btnUpdateOffer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					CourseOffered co = new CourseOffered((Integer)updateOfferIDCombo.getSelectedItem());
					Professor professor = new Professor((String)updateOfferProfessorCombo.getSelectedItem());
					boolean updated = co.updateCourseOffering(professor);
					if(updated){
						showMessage("Update is successfull", "Success");
						DepartmentAdminUI.initializeAllTabs();
						
					}
					else
						showMessage("Update was unsuccessfull", "Failure");
					
				} catch (Course.CourseDoesNotExistException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (CourseOffered.CourseOfferingDoesNotExistException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnUpdateOffer.setEnabled(false);
		updateCourseOffering.add(btnUpdateOffer);
		
		//initialization functions
		initializeCourseList();
		initializeCourseOfferingUpdateTab();

	}
	
	private void addCourse(){
		String courseName = textField.getText();
		if(!checkStringForCourseName(courseName)){
			showMessage("Please enter course name with two or three uppercase character followed by a number"
					+ " greater than 100 less than 1000", "Error in course name");
			return;
		}
		
		
		try {
			Department d = adminDepartment;
			if(Course.addCourse(courseName, d)){
				showMessage("Course added successfully", "Success");
			}
			else{
				showMessage("Course not added, unknown error occurred", "Failure");
			}
		} catch (Course.CourseAlreadyExistsException e) {
			showMessage("Course with the same name and department already exists", "Duplicate course");
			return;
		}
		
		DepartmentAdminUI.initializeAllTabs();
	}

	private void updateCourse(){
		String courseNameNew = textField_1.getText();
		//showMessage(courseNameNew, "New");
		String courseNameOld = (String)allCoursesCombo.getSelectedItem();
		try {
			Course courseOld = new Course(courseNameOld);
			
			if(!checkStringForCourseName(courseNameNew)){
				showMessage("Please enter course name with two or three uppercase character followed by a number"
						+ " greater than 100 less than 1000", "Error in course name");
				return;
			}
			
			Department d = adminDepartment;
			if(courseOld.updateCourse(courseNameNew.toString(), d)){
				showMessage("Course updated successfully", "Success");
			}
			else{
				showMessage("Course not updated, unknown error occurred", "Failure");
			}
			
		} catch (Course.CourseDoesNotExistException e1) {
			showMessage("Course deos not exist", "Failure");
			e1.printStackTrace();
		}
	}
	
	public void initializeData(){
		initializeCourseList();
		initializeCourseOfferingUpdateTab();
	}
	
	private void initializeCourseList(){
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
		ArrayList<Course> courses = Course.getCoursesOfDepartment(adminDepartment);
		for(Course c : courses){
			model.addElement(c.getCourseName());
		}
		allCoursesCombo.setModel(model);
		allCoursesCombo.setSelectedIndex(0);
	}
	
	private void initializeCourseOfferingUpdateTab(){
		DefaultComboBoxModel<Integer> model = new DefaultComboBoxModel<Integer>();
		allDeptCoursesOffered = CourseSchedule.getAllScheduledCourses(adminDepartment);
		for(CourseSchedule cs:allDeptCoursesOffered){
			model.addElement(cs.getOfferID());
		}
		updateOfferIDCombo.setModel(model);
		
		if(allDeptCoursesOffered.size()>0){
			try {
				CourseOffered co = new CourseOffered(allDeptCoursesOffered.get(0).getOfferID());
				DefaultComboBoxModel<String> profModel = new DefaultComboBoxModel<String>();
				ArrayList<Professor> deptProfessor = Professor.getAllProfInADept(adminDepartment.getDepartmentID());
				for(Professor p:deptProfessor){
					profModel.addElement(p.getUserName());
				}
				updateOfferProfessorCombo.setModel(profModel);
				String professorName = co.getProfessor().getUserName();
				updateOfferProfessorCombo.setSelectedIndex(getProfessorIndex(professorName));
				
				
			} catch (Professor.ProfessorDoesNotExistException e) {
				System.out.println("Failed to initialize professor list");
				e.printStackTrace();
			} catch (Course.CourseDoesNotExistException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (CourseOffered.CourseOfferingDoesNotExistException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	private int getProfessorIndex(String professorName){
		for(int i=0; i<updateOfferProfessorCombo.getItemCount() ;i++){
			if(professorName.equals((String)updateOfferProfessorCombo.getItemAt(i))){
				return i;
			}
		}
		
		return 0;
	}
	
	public void showMessage(String message, String title)
    {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.ERROR_MESSAGE);
    }
	
	private  boolean checkStringForCourseName(String s){
		return s.matches("[A-Z]{2,3}[1-9][0-9]{2}");
	}
		
}

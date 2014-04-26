
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;




public class manageCourse extends JTabbedPane {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JComboBox<String> allDepartmentCombo;
	private static manageCourse manageCourseInstance;
	
	//-----update COURSE variables-------------//
	private JTextField textField_1;
	private JComboBox<String> allCoursesCombo;
	private JComboBox<String> updateCourseDeptCombo;
	private JButton btnConfirmUpdate;
	
	//-------------UPDATE COURSE OFFERING VARIABLES------------------//
	private JComboBox<Integer> updateOfferIDCombo;
	private JComboBox<String> updateOfferProfessorCombo;
	private JButton btnUpdateOffer;
	
	
	public static manageCourse getInstance(){
		if(manageCourseInstance == null){
			manageCourseInstance = new manageCourse();
		}
		
		return manageCourseInstance;
	}

	/**
	 * Create the panel.
	 */
	private manageCourse() {
		
		//-------------ADD A COURSE CODE----------//
		JPanel addACourse = new JPanel();
		addTab("Add a Course", null, addACourse, null);
		addACourse.setLayout(new GridLayout(3, 2, 0, 0));
		
		JLabel lblCourseName = new JLabel("Course Name");
		addACourse.add(lblCourseName);
		
		textField = new JTextField();
		addACourse.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Select Department");
		addACourse.add(lblNewLabel);
		
		allDepartmentCombo = new JComboBox<String>();
		addACourse.add(allDepartmentCombo);
		
		JLabel lblNewLabel_1 = new JLabel("Confirm Changes");
		addACourse.add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("Add course");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addCourse();
				textField.setText("");
				initializeDepartments();
			}
		});
		addACourse.add(btnNewButton);
		
		//------------UPDATE COURSE CODE-----------------//
		JPanel updateCourse = new JPanel();
		addTab("Update a course", null, updateCourse, null);
		updateCourse.setLayout(new GridLayout(4, 2, 0, 0));
		
		JLabel lblSelectCourse = new JLabel("Select Course");
		updateCourse.add(lblSelectCourse);
		
		allCoursesCombo = new JComboBox<String>();
		allCoursesCombo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				updateCourseDeptCombo.setEnabled(true);
				textField_1.setEnabled(true);
				btnConfirmUpdate.setEnabled(true);
				
				try {
					Course courseSelected = new Course((String)allCoursesCombo.getSelectedItem());
					textField_1.setText(courseSelected.getCourseName());
					String courseDepartment = courseSelected.getDepartment().getDepartmentName();
					int i = getDepartmentIndex(courseDepartment);
					
					if(i==-1){
						showMessage("Error retrieving the department", "Failure");
						updateCourseDeptCombo.setSelectedIndex(i);
						return;
					}
					
					updateCourseDeptCombo.setSelectedIndex(i);
					
					
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
		
		updateCourseDeptCombo = new JComboBox<String>();
		updateCourseDeptCombo.setEnabled(false);
		updateCourse.add(updateCourseDeptCombo);
		
		JLabel lblUpdateWithAbove = new JLabel("Update with above values");
		updateCourse.add(lblUpdateWithAbove);
		
		btnConfirmUpdate = new JButton("Confirm update");
		btnConfirmUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				updateCourse();
				initializeDepartments();
				initializeCourseList();
				initializeCourseOfferingUpdateTab();
				textField_1.setEnabled(false);
				updateCourseDeptCombo.setEnabled(false);
				btnConfirmUpdate.setEnabled(false);
				AdminUI adminUI = AdminUI.getInstance();
				adminUI.initializeEveryThing();
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
					ArrayList<Professor> deptProfessor = Professor.getAllProfInADept(new Department(co.getDepartmentName()).getDepartmentID());
					for(Professor p:deptProfessor){
						profModel.addElement(p.getUserName());
					}
					updateOfferProfessorCombo.setModel(profModel);
					String professorName = co.getProfessor().getUserName();
					updateOfferProfessorCombo.setSelectedIndex(getProfessorIndex(professorName));
					
				} catch (Professor.ProfessorDoesNotExistException
						| Department.DepartmentDoesNotExistException | Course.CourseDoesNotExistException | CourseOffered.CourseOfferingDoesNotExistException e) {
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
						initializeDepartments();
						initializeCourseList();
						initializeCourseOfferingUpdateTab();
						AdminUI adminUI = AdminUI.getInstance();
						adminUI.initializeEveryThing();
						
					}
					else
						showMessage("Update was unsuccessfull", "Failure");
					
				} catch (Course.CourseDoesNotExistException
						| CourseOffered.CourseOfferingDoesNotExistException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnUpdateOffer.setEnabled(false);
		updateCourseOffering.add(btnUpdateOffer);
		
		//initialization functions
		initializeDepartments();
		initializeCourseList();
		initializeCourseOfferingUpdateTab();

	}
	
	private void addCourse(){
		String courseName = textField.getText();
		String department = null;
		if(!checkStringForCourseName(courseName)){
			showMessage("Please enter course name with two or three uppercase character followed by a number"
					+ " greater than 100 less than 1000", "Error in course name");
			return;
		}
		
		int indexDept = allDepartmentCombo.getSelectedIndex();
		
		if(indexDept<0){
			showMessage("Error retrieving the department", "Department does not exist");
			return;
		}
		
		department = (String)allDepartmentCombo.getSelectedItem();
		try {
			Department d = new Department(department);
			if(Course.addCourse(courseName, d)){
				showMessage("Course added successfully", "Success");
			}
			else{
				showMessage("Course not added, unknown error occurred", "Failure");
			}
		} catch (Department.DepartmentDoesNotExistException e) {
			showMessage("Error retrieving the department", "Department does not exist");
			return;
		} catch (Course.CourseAlreadyExistsException e) {
			showMessage("Course offering with the same name and department already exists", "Duplicate course");
			return;
		}
	}

	private void updateCourse(){
		String courseNameNew = textField_1.getText();
		showMessage(courseNameNew, "New");
		String courseNameOld = (String)allCoursesCombo.getSelectedItem();
		try {
			Course courseOld = new Course(courseNameOld);
			String department = null;
			if(!checkStringForCourseName(courseNameNew)){
				showMessage("Please enter course name with two or three uppercase character followed by a number"
						+ " greater than 100 less than 1000", "Error in course name");
				return;
			}
			
			int indexDept = updateCourseDeptCombo.getSelectedIndex();
			
			if(indexDept<0){
				showMessage("Error retrieving the department", "Department does not exist");
				return;
			}
			
			department = (String)updateCourseDeptCombo.getSelectedItem();
			
			try {
				Department d = new Department(department);
				if(courseOld.updateCourse(courseNameNew.toString(), d)){
					showMessage("Course updated successfully", "Success");
				}
				else{
					showMessage("Course not updated, unknown error occurred", "Failure");
				}
			} catch (Department.DepartmentDoesNotExistException e) {
				showMessage("Error retrieving the department", "Department does not exist");
				return;
			}
			
		} catch (Course.CourseDoesNotExistException e1) {
			showMessage("Course deos not exist", "Failure");
			e1.printStackTrace();
		}
	}
	
	private void initializeDepartments(){
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
		ArrayList<Department> departments = Department.getAllDepartments();
		for(Department d:departments){
			model.addElement(d.getDepartmentName());
		}
		allDepartmentCombo.setModel(model);
		updateCourseDeptCombo.setModel(model);
	}
	
	private void initializeCourseList(){
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
		LinkedHashMap<Integer, Course> courses = Course.getAllCourses();
		for(Integer i : courses.keySet()){
			model.addElement(courses.get(i).getCourseName());
		}
		allCoursesCombo.setModel(model);
		allCoursesCombo.setSelectedIndex(0);
	}
	
	private void initializeCourseOfferingUpdateTab(){
		DefaultComboBoxModel<Integer> model = new DefaultComboBoxModel<Integer>();
		HashMap<Integer, CourseOffered> courseOffered = CourseOffered.getAllOfferedIDAndCourseOffered();
		for(Integer i:courseOffered.keySet()){
			model.addElement(i);
		}
		updateOfferIDCombo.setModel(model);
		
		if(model.getSize()>0){
			CourseOffered co = courseOffered.get(model.getElementAt(0));
			DefaultComboBoxModel<String> profModel = new DefaultComboBoxModel<String>();
			try {
				ArrayList<Professor> deptProfessor = Professor.getAllProfInADept(new Department(co.getDepartmentName()).getDepartmentID());
				for(Professor p:deptProfessor){
					profModel.addElement(p.getUserName());
				}
				updateOfferProfessorCombo.setModel(profModel);
				
			} catch (Professor.ProfessorDoesNotExistException
					| Department.DepartmentDoesNotExistException e) {
				System.out.println("Failed to initialize professor list");
				e.printStackTrace();
			}
			
			String professorName = co.getProfessor().getUserName();
			updateOfferProfessorCombo.setSelectedIndex(getProfessorIndex(professorName));
		}
		
	}
	
	public void initializeAll(){
		initializeCourseList();
		initializeDepartments();
		initializeCourseOfferingUpdateTab();
	}
	
	private int getProfessorIndex(String professorName){
		for(int i=0; i<updateOfferProfessorCombo.getItemCount() ;i++){
			if(professorName.equals((String)updateOfferProfessorCombo.getItemAt(i))){
				return i;
			}
		}
		
		return 0;
	}
	
	private int getDepartmentIndex(String departmentName){
		for(int i=0; i<updateCourseDeptCombo.getItemCount() ;i++){
			if(departmentName.equals((String)updateCourseDeptCombo.getItemAt(i))){
				return i;
			}
		}
		
		return -1;
	}
	
	public void showMessage(String message, String title)
    {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.ERROR_MESSAGE);
    }
	
	private  boolean checkStringForCourseName(String s){
		return s.matches("[A-Z]{2,3}[1-9][0-9]{2}");
	}
		
}

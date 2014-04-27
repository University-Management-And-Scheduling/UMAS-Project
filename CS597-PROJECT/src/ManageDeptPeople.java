

/****************@author Simant Purohit*********************************/

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;


public class ManageDeptPeople extends JPanel {

	private static final long serialVersionUID = 1L;
	private Admin admin;
	private Department adminDepartment;
	private static ManageDeptPeople managePeople;

	private JTextField studentNameText;
	private JComboBox<Integer> levelSelectStudent;
	private JTextField professorNameText;
	private JTextField txtToBeDecided;
	private JTextField txtToBeDecided_1;
	private ArrayList<Student> students = new ArrayList<Student>();
	private ArrayList<Integer> courseOffered = new ArrayList<Integer>();
	private JComboBox<Integer> taComboBox;
	private JComboBox<Integer> courseOfferIDComboBox;
	private JTextArea txtrStudentDetails;
	private JTextArea txtrCourseDetails;

	public static ManageDeptPeople getInstance(Admin a) {
		try {
			managePeople = new ManageDeptPeople(a);
		} catch (Department.DepartmentDoesNotExistException e) {
			e.printStackTrace();
		}
		return managePeople;
	}

	private ManageDeptPeople(Admin a)
			throws Department.DepartmentDoesNotExistException {
		admin = a;
		adminDepartment = new Department(admin.getDeptID());
		setLayout(null);

		JTabbedPane adminTabs = new JTabbedPane(JTabbedPane.TOP);
		adminTabs.setBounds(5, 5, 700, 500);
		add(adminTabs);
		setBounds(10, 10, 800, 600);

		JPanel addNewStudent = new JPanel();
		addNewStudent.setLayout(new GridLayout(4, 2, 0, 0));
		adminTabs.addTab("Add A Student", null, addNewStudent, null);

		JLabel studentNameLabel = new JLabel("Enter student name");
		addNewStudent.add(studentNameLabel);

		studentNameText = new JTextField();
		addNewStudent.add(studentNameText);
		studentNameText.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Select Department");
		addNewStudent.add(lblNewLabel_1);

		JLabel lblDepartmentName = new JLabel(
				adminDepartment.getDepartmentName());
		addNewStudent.add(lblDepartmentName);

		JLabel lblNewLabel = new JLabel("Select Level");
		addNewStudent.add(lblNewLabel);

		levelSelectStudent = new JComboBox<Integer>();
		levelSelectStudent.setModel(new DefaultComboBoxModel<Integer>(
				new Integer[] { 1, 2, 3 }));
		addNewStudent.add(levelSelectStudent);

		JLabel lblNewLabel_2 = new JLabel("Confirm changes");
		addNewStudent.add(lblNewLabel_2);

		JButton addStudentButton = new JButton("Add student");
		addStudentButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				boolean add = addStudent();
				if (add) {
					showMessage("Student added successfully", "Add successfull");
					DepartmentAdminUI.initializeAllTabs();
				} else {
					showMessage("Student not added", "Add unsuccessfull");
				}
			}
		});
		addNewStudent.add(addStudentButton);

		JPanel addNewProfessor = new JPanel();
		adminTabs.addTab("Add a professor", null, addNewProfessor, null);
		addNewProfessor.setLayout(new GridLayout(0, 2, 0, 0));

		JLabel lblProfessorName = new JLabel("Professor name");
		addNewProfessor.add(lblProfessorName);

		professorNameText = new JTextField();
		addNewProfessor.add(professorNameText);
		professorNameText.setColumns(10);

		JLabel lblSelectDepartment = new JLabel("Select Department");
		addNewProfessor.add(lblSelectDepartment);

		JLabel lblDepartmentName_1 = new JLabel(
				adminDepartment.getDepartmentName());
		addNewProfessor.add(lblDepartmentName_1);

		JLabel lblOfficeLocation = new JLabel("Office Location");
		addNewProfessor.add(lblOfficeLocation);

		txtToBeDecided = new JTextField();
		txtToBeDecided.setEditable(false);
		txtToBeDecided.setText("To be decided later");
		addNewProfessor.add(txtToBeDecided);
		txtToBeDecided.setColumns(10);

		JLabel lblOfficeHours = new JLabel("Office Hours");
		addNewProfessor.add(lblOfficeHours);

		txtToBeDecided_1 = new JTextField();
		txtToBeDecided_1.setEditable(false);
		txtToBeDecided_1.setText("To be decided later");
		addNewProfessor.add(txtToBeDecided_1);
		txtToBeDecided_1.setColumns(10);

		JLabel lblConfirm = new JLabel("Confirm");
		addNewProfessor.add(lblConfirm);

		JButton addProfessorButton = new JButton("Add Professor");
		addProfessorButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				boolean add = addProfessor();
				if (add) {
					showMessage("Professor added successfully",
							"Add successfull");
					DepartmentAdminUI.initializeAllTabs();
				} else {
					showMessage("Professor not added", "Add unsuccessfull");
				}
			}
		});
		addNewProfessor.add(addProfessorButton);

		// -------------------Add a teaching assistant
		// course-------------------//
		JPanel addTeachingAssistant = new JPanel();
		adminTabs.addTab("Add a TA", null, addTeachingAssistant, null);
		addTeachingAssistant.setLayout(null);

		taComboBox = new JComboBox<Integer>();
		taComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Student s;
				try {
					s = new Student(taComboBox.getItemAt(taComboBox.getSelectedIndex()));
					String details = "Name: "+s.getName();
					details+= "\nGPA: "+s.getGPA();
					txtrStudentDetails.setText(details);
				} catch (People.PersonDoesNotExistException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		taComboBox.setBounds(79, 47, 162, 20);
		addTeachingAssistant.add(taComboBox);

		courseOfferIDComboBox = new JComboBox<Integer>();
		courseOfferIDComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CourseOffered c;
				try {
					c = new CourseOffered(courseOfferIDComboBox.getItemAt(courseOfferIDComboBox.getSelectedIndex()));
					String details = "Course Name: "+c.getCourseName();
					details += "\nProfessor: "+c.getProfessorName();
					txtrCourseDetails.setText(details);
				} catch (Course.CourseDoesNotExistException
						| CourseOffered.CourseOfferingDoesNotExistException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		courseOfferIDComboBox.setBounds(416, 47, 162, 20);
		addTeachingAssistant.add(courseOfferIDComboBox);

		JButton btnAddTa = new JButton("Add TA");
		btnAddTa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Student s = new Student((Integer) taComboBox.getSelectedItem());
					TA.addTAtoTAtable(s.getUIN(),	(Integer) courseOfferIDComboBox.getSelectedItem());
					showMessage("Added a TA successfully", "Success");
					DepartmentAdminUI.initializeAllTabs();
					
				} catch (TA.AlreadyExistsInTAException e) {
					showMessage("TA already exists", "Failure");
					e.printStackTrace();
				} catch (People.PersonDoesNotExistException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnAddTa.setBounds(261, 283, 136, 40);
		addTeachingAssistant.add(btnAddTa);
		
		txtrStudentDetails = new JTextArea();
		txtrStudentDetails.setEditable(false);
		txtrStudentDetails.setText("Student Details");
		txtrStudentDetails.setBounds(79, 87, 162, 149);
		addTeachingAssistant.add(txtrStudentDetails);
		
		JLabel lblSelectStudent = new JLabel("Select student");
		lblSelectStudent.setBounds(129, 22, 130, 14);
		addTeachingAssistant.add(lblSelectStudent);
		
		JLabel lblSelectCourse = new JLabel("Select Course");
		lblSelectCourse.setBounds(448, 22, 111, 14);
		addTeachingAssistant.add(lblSelectCourse);
		
		txtrCourseDetails = new JTextArea();
		txtrCourseDetails.setText("Course Details");
		txtrCourseDetails.setBounds(416, 87, 162, 149);
		addTeachingAssistant.add(txtrCourseDetails);
		
		initializeValues();
	}

	public boolean checkStringForName(String s) {
		return s.matches("[a-zA-Z]+( [a-zA-Z]+)?");
	}

	public void showMessage(String message, String title) {
		JOptionPane.showMessageDialog(null, message, title,
				JOptionPane.INFORMATION_MESSAGE);
	}

	public boolean addStudent() {
		boolean isAdded = false;
		if (!checkStringForName(studentNameText.getText())) {
			showMessage("Student name is in incorrect format",
					"Error in student name");
			return false;
		}
		Department dept;
		dept = adminDepartment;
		int level = levelSelectStudent.getSelectedIndex() + 1;
		try {
			isAdded = Student
					.addStudentToDb(studentNameText.getText(), dept, level);
		} catch (Student.levelNotExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return isAdded;
	}

	public boolean addProfessor() {
		boolean isAdded = false;
		if (!checkStringForName(professorNameText.getText())) {
			showMessage("Professor name is in incorrect format",
					"Error in professor name");
			return false;
		}
		Department dept;
		dept = adminDepartment;
		isAdded = Professor.addProfToDb(professorNameText.getText(), dept);
		return isAdded;

	}

	public void initializeValues() {

		ArrayList<Student> allStudents = Student.getAllStudents();
		students = new ArrayList<Student>();
		for (Student s : allStudents) {
			if (s.getDeptID() == adminDepartment.getDepartmentID()) {
				students.add(s);
			}
		}
		
		DefaultComboBoxModel<Integer> allStudentsModel = new DefaultComboBoxModel<Integer>();
		for (Student s : students) {
			allStudentsModel.addElement(s.getUIN());
		}
		
		ArrayList<TA> tas = TA.getAllTAs();
		for (Student s : tas) {
			allStudentsModel.addElement(s.getUIN());
		}
		taComboBox.setModel(allStudentsModel);
		
		if(allStudentsModel.getSize()>0)
			taComboBox.setSelectedIndex(0);

		ArrayList<CourseSchedule> allCoursesScheduled = CourseSchedule.getAllScheduledCourses(adminDepartment);
		courseOffered = new ArrayList<Integer>();
		for (CourseSchedule cs : allCoursesScheduled) {
			courseOffered.add(cs.getOfferID());
		}
		
		DefaultComboBoxModel<Integer> allIDsModel = new DefaultComboBoxModel<Integer>();
		for (Integer i : courseOffered) {
			allIDsModel.addElement(i);
		}
		courseOfferIDComboBox.setModel(allIDsModel);
		if(allIDsModel.getSize()>0)
			courseOfferIDComboBox.setSelectedIndex(0);
	}
}

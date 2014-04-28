

/****************@author Simant Purohit*********************************/

import java.awt.EventQueue;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JList;
import javax.swing.JTextPane;

import java.awt.Color;
import java.awt.Font;



public class AdminUI extends JPanel {

	private static AdminUI adminUI = null;
	static HashMap<Integer, CourseOffered> coursesOffered;
	static ArrayList<Department> departments;
	static LinkedHashMap<Integer,Course> courses;
	static ArrayList<Student> students;
	static ArrayList<Professor> professors;
	static ArrayList<Admin> admins;
	static ArrayList<TA> tas;
	static HashMap<Integer, CourseSchedule> courseSchedule;
	
	//Hashmaps for retrieved empty class and timeslots
	static LinkedHashMap<Classroom, ArrayList<Timeslots>> classroomAndTimeslots = new LinkedHashMap<Classroom, ArrayList<Timeslots>>(); 
	private static final long serialVersionUID = 1L;
	private JTextField studentNameText;
	private JTextPane courseScheduleTextPane;
	private JComboBox<String> departmentSelectStudent;
	private JComboBox<String> departmentSelectProfessor;
	private JComboBox<Integer> levelSelectStudent;
	private JTextField professorNameText;
	private JTextField txtToBeDecided;
	private JTextField txtToBeDecided_1;
	private JList<String> courseNameList;
	private JList<Integer> courseOfferIDList;
	private JList<String> professorNameList;
	private JList<String> classLocationList;
	private JList<String> classNameList;
	private JList<String> classTimingList;
	private JComboBox<Integer> courseScheduledCombo;
	private JComboBox<String> timingAvailableCombo;
	private JComboBox<String> classRoomCombo;
	private JComboBox<String> classLocationCombo;
	private JComboBox<String> timeSlotTypeCombo;
	final JComboBox<Integer> courseOfferIDComboBox;
	final JComboBox<Integer> taComboBox;
	
	//variables used in offer Tab
	private final JComboBox<String> allDepartmentsCombo;
	private final JComboBox<String> allCoursesCombo;
	private final JComboBox<String> allProfessorCombo;
	private JComboBox<Integer> classCapacity;
	
	
	//variables used in waitlist monitor
	private JScrollPane waitListStudents;
	private JScrollPane emailedStudents;
	private JComboBox<Integer> courseOfferSelectForWaitListCombo;
	private JList<String> waitListStudentsJList;
	private JList<String> emailedStudentsJList;
	private JTextField adminName;
	private JComboBox<String> adminDeptCombo;
	private String[] departmentNameArray;
	private JTextField txtDepartmentName;
	
	private static manageCourse manageCourseInstance;
	private JTextField txtDepartmentnamenew;
	private JComboBox<String> updateDeptCombo;
	private JTextPane courseDetailsTextPane;
	private JTextPane taCourseDetails;
	private JTextPane taStudentDetails;
	private GiveBonusUI giveBonusPanel;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminUI frame = initializeAdminUI(new Admin(1));
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public static AdminUI getInstance(Admin admin){
		try {
			adminUI = new AdminUI(admin);
		} catch (Department.DepartmentDoesNotExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return adminUI;
	}
	
	private AdminUI(Admin admin) throws Department.DepartmentDoesNotExistException {
		initializeBackgroundData();
		setBounds(100, 100, 1024, 768);
		setBorder(new EmptyBorder(5, 5, 5, 5)); 
		departments = Department.getAllDepartments();
		
		for(int i=0;i<departmentNameArray.length;i++){
			departmentNameArray[i] = departments.get(i).getDepartmentName();
		}
		setLayout(null);
		
		JTabbedPane managePeople = new JTabbedPane(JTabbedPane.TOP);
		managePeople.setBounds(0, 5, 923, 483);
		add(managePeople);
		
		JTabbedPane adminTabs = new JTabbedPane(JTabbedPane.TOP);
		managePeople.addTab("Manage People", null, adminTabs, null);
		
		JPanel addNewStudent = new JPanel();
		adminTabs.addTab("Add A Student", null, addNewStudent, null);
		addNewStudent.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel studentNameLabel = new JLabel("Enter student name");
		addNewStudent.add(studentNameLabel);
		
		studentNameText = new JTextField();
		addNewStudent.add(studentNameText);
		studentNameText.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Select Department");
		addNewStudent.add(lblNewLabel_1);
		
		departmentSelectStudent = new JComboBox<String>();
		departmentSelectStudent.setModel(new DefaultComboBoxModel<String>(departmentNameArray));
		addNewStudent.add(departmentSelectStudent);
		
		JLabel lblNewLabel = new JLabel("Select Level");
		addNewStudent.add(lblNewLabel);
		
		levelSelectStudent = new JComboBox<Integer>();
		levelSelectStudent.setModel(new DefaultComboBoxModel<Integer>(new Integer[] {1, 2, 3}));
		addNewStudent.add(levelSelectStudent);
		
		JLabel lblNewLabel_2 = new JLabel("Confirm changes");
		addNewStudent.add(lblNewLabel_2);
		
		JButton addStudentButton = new JButton("Add student");
		addStudentButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				boolean add = addStudent();
				if(add){
					showMessage("Student added successfully", "Add successfull");
					initializeEveryThing();
				}
				else{
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
		
		departmentSelectProfessor = new JComboBox<String>(new DefaultComboBoxModel<String>(departmentNameArray));
		addNewProfessor.add(departmentSelectProfessor);
		
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
				if(add){
					showMessage("Professor added successfully", "Add successfull");
					initializeEveryThing();
				}
				else{
					showMessage("Professor not added", "Add unsuccessfull");
				}
			}
		});
		
		addNewProfessor.add(addProfessorButton);
		
		
		//-------------------Add a teaching assistant course-------------------//
		JPanel addTeachingAssistant = new JPanel();
		adminTabs.addTab("Add a TA", null, addTeachingAssistant, null);
		addTeachingAssistant.setLayout(null);
		
		taComboBox = new JComboBox<Integer>();
		taComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(taComboBox.getSelectedIndex()<0){
					showMessage("Select a valid student", "Error");
				}
				
				else{
					int UIN = taComboBox.getItemAt(taComboBox.getSelectedIndex());
					Student student;
					try {
						student = new Student(UIN);
						String s = "";
						
						DBAnnotation.annoate("name", "people", "Name", true);
						String name = student.getName();
						
						DBAnnotation.annoate("gpa", "student", "GPA", true);
						double gpa = student.getGPA();
						
						s+="Student Name:"+name;
						s+="\nGPA:"+gpa;
						taStudentDetails.setText(s);
						
					} catch (Student.PersonDoesNotExistException e2) {
						showMessage("Not a student", "Error");
					}
					
				}
				
			}
		});
		DefaultComboBoxModel<Integer> allStudentsModel = new DefaultComboBoxModel<Integer>();
		for(Student s:students){
			allStudentsModel.addElement(s.getUIN());
		}
		for(Student s:tas){
			allStudentsModel.addElement(s.getUIN());
		}
		taComboBox.setModel(allStudentsModel);
		taComboBox.setBounds(373, 47, 166, 20);
		addTeachingAssistant.add(taComboBox);
		
		courseOfferIDComboBox = new JComboBox<Integer>();
		courseOfferIDComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(courseOfferIDComboBox.getSelectedIndex()<0){
					showMessage("Select a valid course", courseOfferIDComboBox.getSelectedIndex()+"");
				}
				
				else{
					int offerID = courseOfferIDComboBox.getItemAt(courseOfferIDComboBox.getSelectedIndex());
					CourseOffered co;
					try {
						co = new CourseOffered(offerID);
						String s = "";
						
						DBAnnotation.annoate("courseName", "courses", "CourseName", true);
						String courseName = co.getCourseName();
						s+="Course Name:"+courseName;
						
						DBAnnotation.annoate("deptName", "department", "DepartmentName", true);
						String deptName = co.getDepartmentName();
						s+="\nDepartment:"+deptName;
						
						DBAnnotation.annoate("classLoc", "classroom", "ClassroomLocation", true);
						String classLoc = co.getClassRoomLocation();
						s+="\nClassroom Location:"+classLoc;
						
						DBAnnotation.annoate("className", "classroom", "ClassroomName", true);
						String className = co.getClassRoomName();
						s+="\nClasstroom Name:"+ className;
						
						s+="\nTimings:"+co.getTiming();
						
						DBAnnotation.annoate("prof", "people", "Name", true);
						String prof = co.getProfessorName();
						s+="\nTaught by professor:"+prof;
						
						taCourseDetails.setText(s);
						
					} catch (Course.CourseDoesNotExistException e1) {
						showMessage("Select a valid course", courseOfferIDComboBox.getSelectedIndex()+" "+offerID);
					} catch (CourseOffered.CourseOfferingDoesNotExistException e1) {
						showMessage("Select a valid course", courseOfferIDComboBox.getSelectedIndex()+" "+offerID);
					}
					
				}
			}
		});
		DefaultComboBoxModel<Integer> allIDsModel = new DefaultComboBoxModel<Integer>();
		for(Integer i:coursesOffered.keySet()){
			allIDsModel.addElement(i);
		}
		courseOfferIDComboBox.setModel(allIDsModel);
		courseOfferIDComboBox.setBounds(72, 47, 166, 20);
		addTeachingAssistant.add(courseOfferIDComboBox);
		
		JButton btnAddTa = new JButton("Add TA");
		btnAddTa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Student s = new Student((Integer) taComboBox.getSelectedItem());
					
					DBAnnotation.annoate("UIN", "student", "UIN", true);
					int UIN = s.getUIN();
					
					TA.addTAtoTAtable(UIN,	(Integer) courseOfferIDComboBox.getSelectedItem());
					showMessage("Added a TA successfully", "Success");
					//DepartmentAdminUI.initializeAllTabs();
					initializeEveryThing();
					
				} catch (TA.AlreadyExistsInTAException e) {
					showMessage("TA already exists", "Failure");
					e.printStackTrace();
				} catch (People.PersonDoesNotExistException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnAddTa.setBounds(257, 299, 111, 63);
		addTeachingAssistant.add(btnAddTa);
		
		JLabel lblSelectCourseOffering = new JLabel("Select Course Offering");
		lblSelectCourseOffering.setBounds(97, 22, 113, 14);
		addTeachingAssistant.add(lblSelectCourseOffering);
		
		JLabel lblSelectStudent = new JLabel("Select student");
		lblSelectStudent.setBounds(407, 22, 111, 14);
		addTeachingAssistant.add(lblSelectStudent);
		
		taCourseDetails = new JTextPane();
		taCourseDetails.setForeground(Color.WHITE);
		taCourseDetails.setBackground(Color.BLACK);
		taCourseDetails.setBounds(41, 104, 223, 165);
		addTeachingAssistant.add(taCourseDetails);
		
		taStudentDetails = new JTextPane();
		taStudentDetails.setBackground(Color.BLACK);
		taStudentDetails.setForeground(Color.WHITE);
		taStudentDetails.setBounds(356, 104, 212, 165);
		addTeachingAssistant.add(taStudentDetails);
		
		JPanel addAdminPanel = new JPanel();
		adminTabs.addTab("Add Admin", null, addAdminPanel, null);
		addAdminPanel.setLayout(new GridLayout(5, 2, 0, 0));
		
		JLabel lblAdminName = new JLabel("Admin Name");
		addAdminPanel.add(lblAdminName);
		
		adminName = new JTextField();
		addAdminPanel.add(adminName);
		adminName.setColumns(10);
		
		JLabel lblAdminDepartment = new JLabel("Admin Department");
		addAdminPanel.add(lblAdminDepartment);
		
		adminDeptCombo = new JComboBox<String>(new DefaultComboBoxModel<String>(departmentNameArray));
		addAdminPanel.add(adminDeptCombo);
		
		JLabel lblOfficehours = new JLabel("OfficeHours");
		addAdminPanel.add(lblOfficehours);
		
		JLabel lblToBeDecided = new JLabel("To Be Decided");
		addAdminPanel.add(lblToBeDecided);
		
		JLabel lblOfficeLocation_1 = new JLabel("Office Location");
		addAdminPanel.add(lblOfficeLocation_1);
		
		JLabel lblToBeDecided_1 = new JLabel("To Be Decided");
		addAdminPanel.add(lblToBeDecided_1);
		
		JLabel lblConfirmChanges = new JLabel("Confirm Changes");
		addAdminPanel.add(lblConfirmChanges);
		
		JButton btnAddAdmin = new JButton("Add Admin");
		btnAddAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				boolean flag = addAdmin();
				if(flag){
					showMessage("Added a adming successfully", "Success");
					initializeEveryThing();
				}
				
				else{
					showMessage("Not added admin", "Failure");
				}
				
				
				
			}
		});
		addAdminPanel.add(btnAddAdmin);
		
		
		
		
		final JTabbedPane courseSchedule = new JTabbedPane(JTabbedPane.TOP);
		managePeople.addTab("Course Scheduling", null, courseSchedule, null);
		
		JPanel currentScheduleTable = new JPanel();
		currentScheduleTable.setSize(HEIGHT, WIDTH);
		courseSchedule.addTab("Current Schedule", null, currentScheduleTable, null);
		courseSchedule.setEnabledAt(0, true);
		currentScheduleTable.setLayout(null);
		
		JLabel lblCourseName = new JLabel("Course Name");
		lblCourseName.setBounds(2, 0, 125, 72);
		currentScheduleTable.add(lblCourseName);
		
		JLabel lblCourseOfferid = new JLabel("Course OfferID");
		lblCourseOfferid.setBounds(137, 0, 125, 72);
		currentScheduleTable.add(lblCourseOfferid);
		
		JLabel lblTaughtBy = new JLabel("Taught By");
		lblTaughtBy.setBounds(286, 0, 125, 72);
		currentScheduleTable.add(lblTaughtBy);
		
		JLabel lblClassLocation = new JLabel("Class location");
		lblClassLocation.setBounds(437, 0, 125, 72);
		currentScheduleTable.add(lblClassLocation);
		
		JLabel lblClassName = new JLabel("Class name");
		lblClassName.setBounds(592, 0, 125, 72);
		currentScheduleTable.add(lblClassName);
		
		JLabel lblTiming = new JLabel("Timing");
		lblTiming.setBounds(778, 0, 125, 72);
		currentScheduleTable.add(lblTiming);
		
		JScrollPane courseNameScrollPane = new JScrollPane();
		courseNameList  = new JList<String>();
		courseNameList.setSelectedIndex(0);
		courseNameScrollPane.getViewport().setView(courseNameList);
		courseNameScrollPane.setBounds(2, 74, 125, 211);
		currentScheduleTable.add(courseNameScrollPane);
		
		JScrollPane courseOfferIDScrollPane = new JScrollPane();
		courseOfferIDList = new JList<Integer>();
		courseOfferIDList.setSelectedIndex(0);
		courseOfferIDScrollPane.getViewport().setView(courseOfferIDList);
		courseOfferIDScrollPane.setBounds(139, 74, 125, 211);
		currentScheduleTable.add(courseOfferIDScrollPane);
		
		JScrollPane taughtByScrollPane = new JScrollPane();
		professorNameList = new JList<String>();
		professorNameList.setSelectedIndex(0);
		taughtByScrollPane.getViewport().setView(professorNameList);
		taughtByScrollPane.setBounds(286, 74, 125, 211);
		currentScheduleTable.add(taughtByScrollPane);
		
		JScrollPane classLocationScrollPane = new JScrollPane();
		classLocationList = new JList<String>();
		classLocationList.setSelectedIndex(0);
		classLocationScrollPane.getViewport().setView(classLocationList);
		classLocationScrollPane.setBounds(437, 74, 125, 211);
		currentScheduleTable.add(classLocationScrollPane);
		
		JScrollPane classNameScrollPane = new JScrollPane();
		classNameList = new JList<String>();
		classNameList.setSelectedIndex(0);
		classNameScrollPane.getViewport().setView(classNameList);
		classNameScrollPane.setBounds(592, 74, 125, 211);
		currentScheduleTable.add(classNameScrollPane);
		
		JScrollPane timingScrollPane = new JScrollPane();
		classTimingList = new JList<String>();
		classTimingList.setSelectedIndex(0);
		timingScrollPane.getViewport().setView(classTimingList);
		timingScrollPane.setBounds(737, 74, 166, 211);
		currentScheduleTable.add(timingScrollPane);
		
		final JButton btnNewButton = new JButton("Reschedule all courses");
		btnNewButton.setBounds(214, 305, 356, 41);
		currentScheduleTable.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnNewButton.setText("Refreshing and rescheduling");
				JDialog jd = new JDialog();
				jd.setTitle("Rescheduling, please wait");
				jd.setBounds(getBounds());
				jd.setVisible(true);				
				courseSchedule.setEnabled(false);
				CourseSchedule.scheduleAllCurrentCourses();
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				initializeBackgroundData();
				initializeJListsForCourseSchedule();
				if(courseScheduledCombo.getModel().getSize()>0){
					initilizeSingleRescheduleTab((Integer)courseScheduledCombo.getSelectedItem());
					ClassroomLocation l = ClassroomLocation.valueOf((String)classLocationCombo.getSelectedItem());
					int timeSlotType = timeSlotTypeCombo.getSelectedIndex()+1;
					CourseOffered co = coursesOffered.get((Integer)courseScheduledCombo.getSelectedItem());
					int cap = co.getTotalCapacity();
					initializeEmptyClassAndTimeSlot(l, timeSlotType, cap);
				}
				
				jd.setVisible(false);
				btnNewButton.setEnabled(true);
				btnNewButton.setText("Reschedule all courses");
				courseSchedule.setEnabled(true);
				
			}
		});
		
		JPanel rescheduleIndividual = new JPanel();
		courseSchedule.addTab("Reschedule Single Course", null, rescheduleIndividual, null);
		rescheduleIndividual.setLayout(null);
		
		courseScheduledCombo = new JComboBox<Integer>();		
		courseScheduledCombo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(courseScheduledCombo.getModel().getSize()>0)
					initilizeSingleRescheduleTab((Integer)courseScheduledCombo.getSelectedItem());
				//Add available locations, classrooms, 
			}
		});
		courseScheduledCombo.setBounds(30, 38, 159, 29);
		rescheduleIndividual.add(courseScheduledCombo);
		
		timeSlotTypeCombo = new JComboBox<String>(new DefaultComboBoxModel<String>(new String[] {"M-W-F", "T-Th"}));
		timeSlotTypeCombo.setBounds(587, 84, 164, 31);
		rescheduleIndividual.add(timeSlotTypeCombo);
		
		classLocationCombo = new JComboBox<String>(new DefaultComboBoxModel<String>(ClassroomLocation.getAllLocations()));
		classLocationCombo.setBounds(587, 131, 164, 29);
		rescheduleIndividual.add(classLocationCombo);
		
		classRoomCombo = new JComboBox<String>();
		classRoomCombo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int index = classRoomCombo.getSelectedIndex();
				int count = 0;
				for(Classroom c:classroomAndTimeslots.keySet()){
					Classroom newClassroom = c;
					if(count == index){
						initializeEmptyTimeSlotCombo(newClassroom);
						break;
					}
					else
						count++;
				}
			}
		});
		classRoomCombo.setBounds(588, 225, 164, 30);
		rescheduleIndividual.add(classRoomCombo);
		
		timingAvailableCombo = new JComboBox<String>();
		timingAvailableCombo.setBounds(588, 272, 164, 35);
		rescheduleIndividual.add(timingAvailableCombo);
		
		JLabel lblType = new JLabel("Type");
		lblType.setBounds(520, 93, 46, 14);
		rescheduleIndividual.add(lblType);
		
		JLabel lblLocation = new JLabel("Location");
		lblLocation.setBounds(520, 137, 46, 14);
		rescheduleIndividual.add(lblLocation);
		
		JLabel lblClassroom = new JLabel("Classroom");
		lblClassroom.setBounds(520, 232, 65, 14);
		rescheduleIndividual.add(lblClassroom);
		
		JLabel lblTimingsAvailable = new JLabel("Timings Available");
		lblTimingsAvailable.setBounds(499, 281, 81, 14);
		rescheduleIndividual.add(lblTimingsAvailable);
		
		courseScheduleTextPane = new JTextPane();
		courseScheduleTextPane.setFont(new Font("Times New Roman", Font.BOLD, 12));
		courseScheduleTextPane.setForeground(Color.WHITE);
		courseScheduleTextPane.setBackground(Color.BLACK);
		courseScheduleTextPane.setBounds(30, 87, 278, 186);
		rescheduleIndividual.add(courseScheduleTextPane);
		
		JButton btnSearchAvailable = new JButton("Search available");
		btnSearchAvailable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(courseScheduledCombo.getModel().getSize()>0){
					ClassroomLocation l = ClassroomLocation.valueOf((String)classLocationCombo.getSelectedItem());
					int timeSlotType = timeSlotTypeCombo.getSelectedIndex()+1;
					CourseOffered co = coursesOffered.get((Integer)courseScheduledCombo.getSelectedItem());
					int cap = co.getTotalCapacity();
					initializeEmptyClassAndTimeSlot(l, timeSlotType, cap);
				}
				
				else{
					showMessage("No course offering selected", "Error");
				}
			}
		});
		btnSearchAvailable.setBounds(587, 177, 164, 32);
		rescheduleIndividual.add(btnSearchAvailable);
		
		JButton btnUpdateSelectedCourse = new JButton("Update selected course with above selected timings");
		btnUpdateSelectedCourse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(courseScheduledCombo.getModel().getSize()<=0){
					showMessage("No course offering selected", "Error");
					return;
				}
				int OfferID = (Integer)courseScheduledCombo.getSelectedItem();
				CourseOffered c = coursesOffered.get(OfferID);
				Classroom newClassroom = null;
				Timeslots newTimeslot = null;
				int count = 0;
				int classRoomSelectedIndex = classRoomCombo.getSelectedIndex();
				int timeSlotSelectedIndex = timingAvailableCombo.getSelectedIndex();
				
				for(Classroom classroom:classroomAndTimeslots.keySet()){
					newClassroom = classroom;
					if(count == classRoomSelectedIndex){
						break;
					}
					else
						count++;
				}
				
				count = 0;
				for(Timeslots t : classroomAndTimeslots.get(newClassroom)){
					newTimeslot = t;
					if(count == timeSlotSelectedIndex){
						break;
					}
					else
						count++;
				}
				
				updateCourseSchedule(c, newClassroom, newTimeslot);
				
			}
		});
		btnUpdateSelectedCourse.setBounds(371, 361, 441, 48);
		rescheduleIndividual.add(btnUpdateSelectedCourse);
		
		JLabel lblNewLabel_5 = new JLabel("Select course offering");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_5.setBounds(30, 13, 165, 21);
		rescheduleIndividual.add(lblNewLabel_5);
		
		
		//--------------------------------------------------------
		//--------------------Offer Tab Code----------------------
		//--------------------------------------------------------
		JPanel offerCourseTab = new JPanel();
		managePeople.addTab("Offer Courses", null, offerCourseTab, null);
		offerCourseTab.setLayout(null);
		
		allDepartmentsCombo = new JComboBox<String>();
		allDepartmentsCombo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Department d= new Department((String)allDepartmentsCombo.getSelectedItem());
					ArrayList<Course> deptCourses = Course.getCoursesOfDepartment(d);
					ArrayList<Professor> deptProfessor = Professor.getAllProfInADept(d.getDepartmentID());
					
					DefaultComboBoxModel<String> modelCourse = new DefaultComboBoxModel<String>();
					DefaultComboBoxModel<String> modelProfessor = new DefaultComboBoxModel<String>();
					
					if(deptCourses.size() == 0)
						allCoursesCombo.setModel(modelCourse);
					
					if(deptProfessor.size() == 0)
						allProfessorCombo.setModel(modelProfessor);
					
					for(Course c:deptCourses){
						modelCourse.addElement(c.getCourseName());
						allCoursesCombo.setModel(modelCourse);
					}
					
					for(Professor p:deptProfessor){
						modelProfessor.addElement(p.getUserName());
						allProfessorCombo.setModel(modelProfessor);
					}
					
					
				} catch (Department.DepartmentDoesNotExistException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Professor.ProfessorDoesNotExistException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		allDepartmentsCombo.setBounds(27, 57, 147, 20);
		offerCourseTab.add(allDepartmentsCombo);
		
		allCoursesCombo = new JComboBox<String>();
		allCoursesCombo.setBounds(226, 57, 138, 20);
		offerCourseTab.add(allCoursesCombo);
		
		allProfessorCombo = new JComboBox<String>();
		allProfessorCombo.setBounds(430, 56, 156, 20);
		offerCourseTab.add(allProfessorCombo);
		
		classCapacity = new JComboBox<Integer>(new DefaultComboBoxModel<Integer>(new Integer[] {5,8,10}));
		classCapacity.setBounds(637, 56, 127, 20);
		offerCourseTab.add(classCapacity);
		
		JLabel lblCourseNames = new JLabel("Department");
		lblCourseNames.setBounds(64, 33, 107, 14);
		offerCourseTab.add(lblCourseNames);
		
		JLabel lblNewLabel_3 = new JLabel("Courses");
		lblNewLabel_3.setBounds(256, 34, 97, 14);
		offerCourseTab.add(lblNewLabel_3);
		
		JLabel lblTotalCapacity = new JLabel("Professor");
		lblTotalCapacity.setBounds(462, 32, 107, 14);
		offerCourseTab.add(lblTotalCapacity);
		
		JLabel lblTotalCapacity_1 = new JLabel("Total Capacity");
		lblTotalCapacity_1.setBounds(636, 27, 84, 14);
		offerCourseTab.add(lblTotalCapacity_1);
		
		JButton btnNewButton_1 = new JButton("Offer this course");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(allProfessorCombo.getModel().getSize()<=0){
					showMessage("No professors in this department", "Error");
					return;
				}
				
				if(allDepartmentsCombo.getModel().getSize()<=0){
					showMessage("Department does not exist", "Error");
					return;
				}
				
				if(allCoursesCombo.getModel().getSize()<=0){
					showMessage("Course list empty", "Error");
					return;
				}
				
				try {
					Course course = new Course((String)allCoursesCombo.getSelectedItem());
					Professor professor = new Professor((String)allProfessorCombo.getSelectedItem());
					int expectedCapacity = (Integer)classCapacity.getSelectedItem();	
					try {
						boolean flag = CourseOffered.addCourseOfferingToDatabase(course, professor, expectedCapacity);
						if(flag)
							showMessage("Successfully added the new offering", "Success");
						else
							showMessage("Error adding course offering", "Failure");
					} catch (CourseOffered.CourseOfferingAlreadyExistsException e) {
						showMessage("CourseOffering with same parameters already exists", "Duplicate Offering");
						//e.printStackTrace();
					} catch (CourseOffered.CourseOfferingNotSchedulable e) {
						showMessage("CourseOffering not added as there are no empty class"
								+ " rooms available", "Classrooms full");
						//e.printStackTrace();
					}
					
				} catch (Course.CourseDoesNotExistException e) {
					if(e instanceof Course.CourseDoesNotExistException){
						showMessage("Error Retrieving Course", "Offering not added");
					}
					else{
						showMessage("Error Retrieving Professor", "Offering not added");
					}
					//e.printStackTrace();
				}
				
				finally{
					initializeEveryThing();
				}
			}
		});
		btnNewButton_1.setBounds(282, 130, 245, 59);
		offerCourseTab.add(btnNewButton_1);
		
		manageCourseInstance = manageCourse.getInstance();
		JTabbedPane courseManagementPane = manageCourseInstance;
		managePeople.addTab("Manage Courses", null, courseManagementPane, null);
		
		
		//--------------------------------------------------------
		//--------------------Wait list monitor----------------------
		//--------------------------------------------------------
		JPanel waitListMonitor = new JPanel();
		managePeople.addTab("Monitor wait list", null, waitListMonitor, null);
		waitListMonitor.setLayout(null);
		
		waitListStudents = new JScrollPane();
		waitListStudents.setForeground(Color.WHITE);
		waitListStudents.setBackground(Color.BLACK);
		waitListStudentsJList  = new JList<String>();
		waitListStudentsJList.setSelectedIndex(0);
		waitListStudents.getViewport().setView(waitListStudentsJList);
		waitListStudents.setBounds(28, 83, 284, 271);
		waitListMonitor.add(waitListStudents);
		
		emailedStudents = new JScrollPane();
		emailedStudents.setBackground(Color.BLACK);
		emailedStudents.setForeground(Color.WHITE);
		emailedStudentsJList = new JList<String>();
		emailedStudentsJList.setSelectedIndex(0);
		emailedStudents.getViewport().setView(emailedStudentsJList);
		emailedStudents.setBounds(608, 83, 276, 271);
		waitListMonitor.add(emailedStudents);
		
		courseOfferSelectForWaitListCombo = new JComboBox<Integer>();
		courseOfferSelectForWaitListCombo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int offerID = (Integer)courseOfferSelectForWaitListCombo.getSelectedItem();
				
				DefaultListModel<String> waitList = new DefaultListModel<String>();
				for(Student s:WaitList.getStudentsOnWaitList(offerID)){
					waitList.addElement(s.getName());
				}
				
				waitListStudentsJList.setModel(waitList);
				
				DefaultListModel<String> emailedList = new DefaultListModel<String>();
				for(Student s:WaitList.getStudentsOnEmailList(offerID)){
					emailedList.addElement(s.getName());
				}
				
				emailedStudentsJList.setModel(emailedList);
				
				try {
					CourseOffered co = new CourseOffered(offerID);
					
					DBAnnotation.annoate("courseName", "courses", "CourseName", true);
					String courseName = co.getCourseName();
					String s="Course Name:"+courseName;
					
					DBAnnotation.annoate("deptName", "department", "DepartmentName", true);
					String deptName = co.getDepartmentName();
					s+="\nDepartment:"+deptName;
					
					DBAnnotation.annoate("classLoc", "classroom", "ClassroomLocation", true);
					String classLoc = co.getClassRoomLocation();
					s+="\nClassroom Location:"+classLoc;
					
					DBAnnotation.annoate("className", "classroom", "ClassroomName", true);
					String className = co.getClassRoomName();
					s+="\nClasstroom Name:"+ className;
					
					s+="\nTimings:"+co.getTiming();
					
					DBAnnotation.annoate("prof", "people", "Name", true);
					String prof = co.getProfessorName();
					s+="\nTaught by professor:"+prof;
					
					courseDetailsTextPane.setText(s);
					
				} catch (Course.CourseDoesNotExistException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (CourseOffered.CourseOfferingDoesNotExistException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		courseOfferSelectForWaitListCombo.setBounds(375, 11, 133, 20);
		waitListMonitor.add(courseOfferSelectForWaitListCombo);
		
		JLabel lblCurrentStudentsOn = new JLabel("Current students on waitlist");
		lblCurrentStudentsOn.setBounds(28, 55, 276, 17);
		waitListMonitor.add(lblCurrentStudentsOn);
		
		JLabel lblStudentAllowedTo = new JLabel("Students who received emails to register");
		lblStudentAllowedTo.setBounds(608, 56, 276, 14);
		waitListMonitor.add(lblStudentAllowedTo);
		
		courseDetailsTextPane = new JTextPane();
		courseDetailsTextPane.setFont(new Font("Times New Roman", Font.BOLD, 12));
		courseDetailsTextPane.setForeground(Color.WHITE);
		courseDetailsTextPane.setBackground(Color.BLACK);
		courseDetailsTextPane.setBounds(347, 81, 209, 154);
		waitListMonitor.add(courseDetailsTextPane);
		
		JTabbedPane departmentPane = new JTabbedPane(JTabbedPane.TOP);
		managePeople.addTab("Manage Departments", null, departmentPane, null);
		
		JPanel addDepartmentPanel = new JPanel();
		departmentPane.addTab("Add New Department", null, addDepartmentPanel, null);
		addDepartmentPanel.setLayout(new GridLayout(2, 2, 0, 0));
		
		JLabel lblDepartmentName = new JLabel("Department Name");
		addDepartmentPanel.add(lblDepartmentName);
		
		txtDepartmentName = new JTextField();
		addDepartmentPanel.add(txtDepartmentName);
		txtDepartmentName.setColumns(10);
		
		JLabel lblConfirm_1 = new JLabel("Confirm");
		addDepartmentPanel.add(lblConfirm_1);
		
		JButton btnAddDepartment = new JButton("Add department");
		btnAddDepartment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				boolean checkName = checkStringForName(txtDepartmentName.getText());
				if(!checkName)
					showMessage("Department name format incorrect", "Please correct the department name");
				else{
					try {
						Department.addNewDepartment(txtDepartmentName.getText());
						initializeEveryThing();
						showMessage("Successfully added department:"+txtDepartmentName.getText(), "Success");
						txtDepartmentName.setText("");
					} catch (Department.DepartmentAlreadyExistsException e) {
						showMessage("Department with same name already exists", "Duplicate department");
						e.printStackTrace();
					}
				}
			}
		});
		addDepartmentPanel.add(btnAddDepartment);
		
		JPanel updateDepartment = new JPanel();
		departmentPane.addTab("Update Department", null, updateDepartment, null);
		updateDepartment.setLayout(new GridLayout(3, 2, 0, 0));
		
		JLabel lblSelectDepartment_1 = new JLabel("Select department");
		updateDepartment.add(lblSelectDepartment_1);
		
		updateDeptCombo = new JComboBox<String>(new DefaultComboBoxModel<String>(departmentNameArray));
		updateDepartment.add(updateDeptCombo);
		
		JLabel lblEnterNewName = new JLabel("Enter new name");
		updateDepartment.add(lblEnterNewName);
		
		txtDepartmentnamenew = new JTextField();
		updateDepartment.add(txtDepartmentnamenew);
		txtDepartmentnamenew.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Confirm");
		updateDepartment.add(lblNewLabel_4);
		
		JButton btnUpdateSelectedDepartment = new JButton("Update selected department");
		btnUpdateSelectedDepartment.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(updateDeptCombo.getModel().getSize()<=0){
					showMessage("No department selected", "Error");
					return;
				}
				
				else{
					String oldName = updateDeptCombo.getItemAt(updateDeptCombo.getSelectedIndex());
					Department d;
					try {
						d = new Department(oldName);
						boolean flag = checkStringForName(txtDepartmentnamenew.getText());
						if(flag){
							d.setDepartmentName(txtDepartmentnamenew.getText());
							boolean isUpdated = d.updateDepartment();
							if(isUpdated){
								showMessage("Successfully updated department name", "Success");
								initializeEveryThing();
							}
							else{
								showMessage("Failed to update", "Failure");
							}
						}
						
						else{
							showMessage("Please enter depratment name correctly", "Error in name format");
						}
						
					} catch (Department.DepartmentDoesNotExistException e) {
						showMessage("Department not found", "Error");
						e.printStackTrace();
					} catch (Department.DepartmentAlreadyExistsException e) {
						showMessage("Department name already exists", "Failure");
						e.printStackTrace();
					}
				}
				
			}
		});
		updateDepartment.add(btnUpdateSelectedDepartment);
		
		giveBonusPanel = new GiveBonusUI();
		managePeople.addTab("Manage Employee pay", null, giveBonusPanel, null);
		
		JPanel logOutPanel = new LogOutUI();
		managePeople.addTab("Logout", null, logOutPanel, null);
		logOutPanel.setLayout(null);
		
		JPanel goToNextSemester = new JPanel();
		managePeople.addTab("Go to next Semester", null, goToNextSemester, null);
		goToNextSemester.setLayout(null);
		
		final JButton btnInitializeNextSemester = new JButton("Initialize next semester (USE WITH EXTREME CAUTION)");
		btnInitializeNextSemester.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int reply = JOptionPane.showConfirmDialog(btnInitializeNextSemester, "Are you sure\nAll the grades of all the students will be finalized"
						+ " and a new semester will be initialized. \nWARNING: This is an irreversible process");
				if(reply == 0){
					Semester s = new Semester();
					s.goToNextSemester();
					JOptionPane.showMessageDialog(btnInitializeNextSemester, "Successfully initializes new semester, the application will now close."
							+ " Please restart application and login again for the changes to reflect");
					System.exit(ABORT);
					
				}
			}
		});
		btnInitializeNextSemester.setBounds(195, 135, 442, 112);
		goToNextSemester.add(btnInitializeNextSemester);
				
		
		//initialize data across UI
		initializeEveryThing();
		
		Thread waitListViewRefresh = new Thread(new MonitorWaitList());
		waitListViewRefresh.start();
		
	}
	
	public static AdminUI initializeAdminUI(Admin admin) throws Department.DepartmentDoesNotExistException{
		adminUI = new AdminUI(admin);
		return adminUI; 
	}
	
	public static AdminUI getInstance(){
		return adminUI;
	}
	
	public void initializeEveryThing(){
		initializeBackgroundData();
		initializeJListsForCourseSchedule();
		ClassroomLocation l = ClassroomLocation.valueOf((String)classLocationCombo.getSelectedItem());
		int timeSlotType = timeSlotTypeCombo.getSelectedIndex()+1;
		if(courseScheduledCombo.getModel().getSize()>0){
			CourseOffered co = coursesOffered.get((Integer)courseScheduledCombo.getSelectedItem());
			initilizeSingleRescheduleTab((Integer)courseScheduledCombo.getSelectedItem());
			int cap = co.getTotalCapacity();
			initializeEmptyClassAndTimeSlot(l, timeSlotType, cap);
		}
		
		initializeOfferTab();
		initializeWaitListMonitor();
		manageCourseInstance.initializeAll();
		
		DefaultComboBoxModel<Integer> allIDsModel = new DefaultComboBoxModel<Integer>();
		for(Integer i:coursesOffered.keySet()){
			allIDsModel.addElement(i);
		}
		courseOfferIDComboBox.setModel(allIDsModel);
		
		DefaultComboBoxModel<Integer> allStudentsModel = new DefaultComboBoxModel<Integer>();
		for(Student s:students){
			allStudentsModel.addElement(s.getUIN());
		}
		
		for(Student s:tas){
			allStudentsModel.addElement(s.getUIN());
		}
		taComboBox.setModel(allStudentsModel);
		
		departmentSelectStudent.setModel(new DefaultComboBoxModel<String>(departmentNameArray));
		departmentSelectProfessor.setModel(new DefaultComboBoxModel<String>(departmentNameArray));
		updateDeptCombo.setModel(new DefaultComboBoxModel<String>(departmentNameArray));
		adminDeptCombo.setModel(new DefaultComboBoxModel<String>(departmentNameArray));
		
	}
	
	public boolean checkStringForName(String s){
		return s.matches("[a-zA-Z]+( [a-zA-Z]+)?");
	}
	
	public void showMessage(String message, String title)
    {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
    }
	
	public boolean addStudent(){
		boolean isAdded = false;
		if(!checkStringForName(studentNameText.getText())){
			showMessage("Student name is in incorrect format", "Error in student name");
			return false;
		}
		String departmentName = departmentSelectStudent.getItemAt(departmentSelectStudent.getSelectedIndex());
		Department dept;
		try {
			dept = new Department(departmentName);
			int level = levelSelectStudent.getSelectedIndex()+1;
			isAdded = Student.addStudentToDb(studentNameText.getText(), dept, level);
			return isAdded;
		} catch (Department.DepartmentDoesNotExistException e) {
			showMessage("Department does not exist", "Error in department");
			return isAdded;
		} catch (Student.levelNotExistException e) {
			e.printStackTrace();
			return isAdded;
		}
		
	}
	
	public boolean addProfessor(){
		boolean isAdded = false;
		if(!checkStringForName(professorNameText.getText())){
			showMessage("Professor name is in incorrect format", "Error in professor name");
			return false;
		}
		String departmentName = departmentSelectProfessor.getItemAt(departmentSelectProfessor.getSelectedIndex());
		Department dept;
		try {
			dept = new Department(departmentName);
			isAdded = Professor.addProfToDb(professorNameText.getText(), dept);
			return isAdded;
		} catch (Department.DepartmentDoesNotExistException e) {
			showMessage("Department does not exist", "Error in department");
			return isAdded;
		}
		
	}
	
	public boolean addAdmin(){
		boolean isAdded = false;
		if(!checkStringForName(adminName.getText())){
			showMessage("Admin name is in incorrect format", "Error in admin name");
			return false;
		}
		String departmentName = adminDeptCombo.getItemAt(adminDeptCombo.getSelectedIndex());
		Department dept;
		try {
			dept = new Department(departmentName);
			isAdded = Admin.addAdmin(adminName.getText(), dept);
			return isAdded;
		} catch (Department.DepartmentDoesNotExistException e) {
			showMessage("Department does not exist", "Error in department");
			return isAdded;
		} catch (Admin.loginDetailsnotAdded e) {
			return isAdded;
		}
		
	}

	public ArrayList<Integer> getOfferIdOfScheduledCourses(){
		ArrayList<Integer> offerID = new ArrayList<Integer>();
		courseSchedule = CourseSchedule.getHaspMapForSchedule();
		for(Integer i: courseSchedule.keySet()){
			offerID.add(i);
		}
		
		return offerID;
		
	}

	public void initializeJListsForCourseSchedule(){
		initializeBackgroundData();
		ArrayList<Integer> offerIDs = getOfferIdOfScheduledCourses();
		String[] courseNameList = new String[offerIDs.size()];
		Integer[] courseOfferIDList = new Integer[offerIDs.size()];
		String[] profNameList = new String[offerIDs.size()];
		String[] classLocationList = new String[offerIDs.size()];
		String[] classRoomNameList = new String[offerIDs.size()];
		String[] classTimingList = new String[offerIDs.size()];
		
		for(int i=0;i<offerIDs.size();i++){
			CourseOffered co;
			try {
				System.out.println("OfferID:"+offerIDs.get(i));
				co = new CourseOffered(offerIDs.get(i));
				
				DBAnnotation.annoate("courseName", "courses", "CourseName", true);
				String courseName = co.getCourseName();
				courseNameList[i] = courseName;
				
				DBAnnotation.annoate("offerID", "coursesoffered", "OfferID", true);
				int offerID = co.getOfferID();
				courseOfferIDList[i] = offerID;
				
				DBAnnotation.annoate("prof", "people", "Name", true);
				String prof = co.getProfessorName();
				profNameList[i] = prof;
				
				DBAnnotation.annoate("classLoc", "classroom", "ClassroomLocation", true);
				String classLoc = co.getClassRoomLocation();
				classLocationList[i] = classLoc;
				
				DBAnnotation.annoate("className", "classroom", "ClassroomName", true);
				String className = co.getClassRoomName();
				classRoomNameList[i] = className;
				
				classTimingList[i] = co.getTiming();
				
			} catch (Course.CourseDoesNotExistException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (CourseOffered.CourseOfferingDoesNotExistException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		//Setting the Jlists
		this.courseNameList.setListData(courseNameList);
		this.courseOfferIDList.setListData(courseOfferIDList);
		this.professorNameList.setListData(profNameList);
		this.classLocationList.setListData(classLocationList);
		this.classNameList.setListData(classRoomNameList);
		this.classTimingList.setListData(classTimingList);		
		setAllIndexTo(0);
		
		//setting the course select combo box
		this.courseScheduledCombo.setModel(new DefaultComboBoxModel<Integer>(courseOfferIDList));
		if(courseOfferIDList.length>0)
			initilizeSingleRescheduleTab(courseOfferIDList[0]);
		
	}

	public void initilizeSingleRescheduleTab(int offerID){
		CourseOffered co = coursesOffered.get(offerID);
		String s = "";
		DBAnnotation.annoate("courseName", "courses", "CourseName", true);
		String courseName = co.getCourseName();
		s+="Course Name:"+courseName;
		
		DBAnnotation.annoate("deptName", "department", "DepartmentName", true);
		String deptName = co.getDepartmentName();
		s+="\nDepartment:"+deptName;
		
		DBAnnotation.annoate("classLoc", "classroom", "ClassroomLocation", true);
		String classLoc = co.getClassRoomLocation();
		s+="\nClassroom Location:"+classLoc;
		
		DBAnnotation.annoate("className", "classroom", "ClassroomName", true);
		String className = co.getClassRoomName();
		s+="\nClasstroom Name:"+ className;
		
		s+="\nTimings:"+co.getTiming();
		
		DBAnnotation.annoate("prof", "people", "Name", true);
		String prof = co.getProfessorName();
		s+="\nTaught by professor:"+prof;
		
		courseScheduleTextPane.setText(s);
	}
	
	public void initializeBackgroundData(){
		coursesOffered = CourseOffered.getAllOfferedIDAndCourseOffered();
		courseSchedule = CourseSchedule.getHaspMapForSchedule();
		departments = Department.getAllDepartments();
		courses = Course.getAllCourses();
		students = Student.getAllStudents();
		tas = TA.getAllTAs();
		departmentNameArray = new String[departments.size()];
		for(int i=0;i<departmentNameArray.length;i++){
			departmentNameArray[i] = departments.get(i).getDepartmentName();
		}
	}
	
	public void initializeOfferTab(){
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
		for(Department d:departments){
			model.addElement(d.getDepartmentName());
		}
		allDepartmentsCombo.setModel(model);
		Department d;
		try {
			d = new Department((String)allDepartmentsCombo.getSelectedItem());
			ArrayList<Course> deptCourses = Course.getCoursesOfDepartment(d);
			ArrayList<Professor> deptProfessor = Professor.getAllProfInADept(d.getDepartmentID());
			
			DefaultComboBoxModel<String> modelCourse = new DefaultComboBoxModel<String>();
			DefaultComboBoxModel<String> modelProfessor = new DefaultComboBoxModel<String>();
			
			if(deptCourses.size() == 0)
				allCoursesCombo.setModel(modelCourse);
			
			if(deptProfessor.size() == 0)
				allProfessorCombo.setModel(modelProfessor);
			
			for(Course c:deptCourses){
				modelCourse.addElement(c.getCourseName());
				allCoursesCombo.setModel(modelCourse);
			}
			
			for(Professor p:deptProfessor){
				modelProfessor.addElement(p.getUserName());
				allProfessorCombo.setModel(modelProfessor);
			}
			
		} catch (Department.DepartmentDoesNotExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Professor.ProfessorDoesNotExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

	public void initializeWaitListMonitor(){
		DefaultComboBoxModel<Integer> model = new DefaultComboBoxModel<Integer>();
		for(Integer i:coursesOffered.keySet()){
			model.addElement(i);
		}
		courseOfferSelectForWaitListCombo.setModel(model);
		
		if(model.getSize()>0){
			int offerID = model.getElementAt(0);
			
			DefaultListModel<String> waitList = new DefaultListModel<String>();
			for(Student s:WaitList.getStudentsOnWaitList(offerID)){
				waitList.addElement(s.getName());
			}
			
			waitListStudentsJList.setModel(waitList);
			
			DefaultListModel<String> emailedList = new DefaultListModel<String>();
			for(Student s:WaitList.getStudentsOnEmailList(offerID)){
				emailedList.addElement(s.getName());
			}
			
			emailedStudentsJList.setModel(emailedList);
			
			try {
				CourseOffered co = new CourseOffered(offerID);
				String s = "Course name:"+co.getCourseName();
				s+="\nDepartment name:"+co.getDepartmentName();
				s+="\nTaught by Prof. "+co.getProfessorName();
				s+="\nClass location and name:"+co.getClassRoomLocation()+", "+co.getClassRoomName();
				s+="\nTimings:"+co.getTiming();
				courseDetailsTextPane.setText(s);
				
			} catch (Course.CourseDoesNotExistException	e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (CourseOffered.CourseOfferingDoesNotExistException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
	}
	//???write a initialize manage people tab function???
	
	public void initializeEmptyClassAndTimeSlot(ClassroomLocation location, int timeSlotType, int courseCapacity){
		LinkedHashMap<Integer, Classroom> emptyClassRooms = Classroom.getAllEmptyClassroom(location, timeSlotType, courseCapacity);
		classroomAndTimeslots = new LinkedHashMap<Classroom, ArrayList<Timeslots>>();		
		String[] classRoomStrings = new String[emptyClassRooms.size()];
		int index=0;
		for(Integer i : emptyClassRooms.keySet()){
			Classroom cr = emptyClassRooms.get(i);
			ArrayList<Timeslots> ts = cr.findOpenSlotsForClassroom(timeSlotType);
			classroomAndTimeslots.put(cr, ts);
			classRoomStrings[index] = cr.getClassroomName().toString();			
			if(index == 0)
				initializeEmptyTimeSlotCombo(cr);
			index++;
		}
		
		classRoomCombo.setModel(new DefaultComboBoxModel<String>(classRoomStrings));
//		classRoomCombo.setSelectedIndex(0);
//		timingAvailableCombo.setSelectedIndex(0);
		
	}
	
	public void initializeEmptyTimeSlotCombo(Classroom c){
		ArrayList<Timeslots> ts = classroomAndTimeslots.get(c);
		String[] timeSlotsStrings = new String[ts.size()];
		for(int i=0;i<ts.size();i++){
			timeSlotsStrings[i] = ts.get(i).getStartHour()+ "00 to "+ts.get(i).getEndHour()+"00";
		}
		
		timingAvailableCombo.setModel(new DefaultComboBoxModel<String>(timeSlotsStrings));
	}
	
	public void updateCourseSchedule(CourseOffered coursOffered, Classroom classroom, Timeslots timeslot){
		try {
			CourseSchedule.updateCourseSchedule(coursOffered, classroom, timeslot);
			showMessage("Updated successfully", "Success");
			initilizeSingleRescheduleTab(coursOffered.getOfferID());
			initializeBackgroundData();
			initializeJListsForCourseSchedule();
			ClassroomLocation l = ClassroomLocation.valueOf((String)classLocationCombo.getSelectedItem());
			int timeSlotType = timeSlotTypeCombo.getSelectedIndex()+1;
			CourseOffered co = coursesOffered.get((Integer)courseScheduledCombo.getSelectedItem());
			int cap = co.getTotalCapacity();
			initializeEmptyClassAndTimeSlot(l, timeSlotType, cap);
			
		} catch (CourseOffered.CourseOfferingNotCurrentException e) {
			showMessage("Unable to update", "Unable to update");
			e.printStackTrace();
		}
	}
	
	public void setAllIndexTo(int index){
		courseNameList.setSelectedIndex(index);
		courseOfferIDList.setSelectedIndex(index);
		professorNameList.setSelectedIndex(index);
		classLocationList.setSelectedIndex(index);
		classNameList.setSelectedIndex(index);
		classTimingList.setSelectedIndex(index);
		
	}
	
	//to refresh the data show to the user periodically
	private class MonitorWaitList implements Runnable{
		@Override
		public void run() {
			while(true){
				System.out.println("Initializing the wait list monitor - Admin UI");
				initializeWaitListMonitor();
				try {
					Thread.sleep(10000);
					System.out.println("Finished the wait list monitor - Admin UI");
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}
}

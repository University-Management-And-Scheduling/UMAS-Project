
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


public class OfferCoursesUI extends JPanel {

	private static final long serialVersionUID = 1L;
	private static Admin admin=null;
	private static Department adminDepartment= null;
	private static OfferCoursesUI offerCoursesUI;
	private static JComboBox<String> allCoursesCombo;
	private static JComboBox<String> allProfessorCombo;
	private JComboBox<Integer> classCapacity;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OfferCoursesUI frame = new OfferCoursesUI(new Admin(1));
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public static OfferCoursesUI getInstance(Admin a){
		try {
			offerCoursesUI = new OfferCoursesUI(a);
			return offerCoursesUI;
		} catch (Department.DepartmentDoesNotExistException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	private OfferCoursesUI(Admin a) throws Department.DepartmentDoesNotExistException {
		OfferCoursesUI.admin = a;
		OfferCoursesUI.adminDepartment = new Department(admin.getDeptID());
		setBounds(100, 100, 1024, 800);
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(new BorderLayout(0, 0));
		
		JPanel offerCourseTab = new JPanel();
		add(offerCourseTab, BorderLayout.CENTER);
		offerCourseTab.setLayout(null);
		
		allCoursesCombo = new JComboBox<String>();
		allCoursesCombo.setBounds(39, 52, 106, 20);
		offerCourseTab.add(allCoursesCombo);
		
		allProfessorCombo = new JComboBox<String>();
		allProfessorCombo.setBounds(184, 52, 106, 20);
		offerCourseTab.add(allProfessorCombo);
		
		classCapacity = new JComboBox<Integer>(new DefaultComboBoxModel<Integer>(new Integer[] {40,50,60}));
		classCapacity.setBounds(318, 52, 106, 20);
		offerCourseTab.add(classCapacity);
		
		JLabel lblNewLabel_3 = new JLabel("Courses");
		lblNewLabel_3.setBounds(68, 27, 39, 14);
		offerCourseTab.add(lblNewLabel_3);
		
		JLabel lblTotalCapacity = new JLabel("Professor");
		lblTotalCapacity.setBounds(208, 27, 46, 14);
		offerCourseTab.add(lblTotalCapacity);
		
		JLabel lblTotalCapacity_1 = new JLabel("Total Capacity");
		lblTotalCapacity_1.setBounds(326, 27, 69, 14);
		offerCourseTab.add(lblTotalCapacity_1);
		
		JButton btnNewButton_1 = new JButton("Offer this course");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Course course = new Course((String)allCoursesCombo.getSelectedItem());
					Professor professor = new Professor((String)allProfessorCombo.getSelectedItem());
					int expectedCapacity = (Integer)classCapacity.getSelectedItem();	
					try {
						boolean flag = CourseOffered.addCourseOfferingToDatabase(course, professor, expectedCapacity);
						if(flag){
							showMessage("Successfully added the new offering", "Success");
							DepartmentAdminUI.initializeAllTabs();
						}
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
				}
			}
		});
		btnNewButton_1.setBounds(184, 134, 113, 23);
		offerCourseTab.add(btnNewButton_1);
		
		initializeEveryThing();
		
	}
	
	public void showMessage(String message, String title)
    {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.ERROR_MESSAGE);
    }
	
	public void initializeEveryThing(){
		try {
			Department d = adminDepartment;
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
			
		} catch (Professor.ProfessorDoesNotExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}

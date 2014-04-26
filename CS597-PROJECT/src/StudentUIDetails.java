
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JComboBox;
import javax.swing.JCheckBox;

import java.awt.Color;



public class StudentUIDetails extends JPanel {
	
	static Student student;
	static Department dept;
	static int UIN;
	
	private JPanel StudentUse;
	private JTextField studentName;
	private JTextField studentUIN;
	private JTextField studentUserName;
	private JTextField studentDept;
	private JTextField studentPosition;
	private JTextField newName;
	private JLabel presentName;
	private JTextField newUserName;
	private JLabel presentUserName;
	private JTextField currentPasswordTxt;
	private JTextField newPasswordTxt;
	private JButton checkButton;
	JComboBox<Double> workEx;
	private JCheckBox skill1;
	private JCheckBox skill2;
	private JCheckBox skill3;
	private JCheckBox skill4;
	private JCheckBox skill5;
	private JButton btnAddApplicationDetails;
	private JButton btnUpdateApplicationDetails;
	private JTextField showGPA;
	
	public static StudentUIDetails stdUIdtls;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentUIDetails frame = new StudentUIDetails(new Student(451));
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public static StudentUIDetails getInstance(Student s){
		try {
			stdUIdtls = new StudentUIDetails(s);
		} catch (Department.DepartmentDoesNotExistException e) {
			e.printStackTrace();
		}		
		return stdUIdtls;
	}
	
	
	private StudentUIDetails(Student s) throws Department.DepartmentDoesNotExistException {
		
		StudentUIDetails.student=s;
		System.out.println(student.getDeptID());
		StudentUIDetails.dept=new Department(student.getDeptID());
		StudentUIDetails.UIN=student.getUIN();
		
		
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		//StudentUse = new JPanel();
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(new BorderLayout(0, 0));
		//setContentPane(StudentUse);
		
		JTabbedPane viewDetailsPane = new JTabbedPane(JTabbedPane.TOP);
		add(viewDetailsPane, BorderLayout.CENTER);
		
		final JPanel lblGPA = new JPanel();
		viewDetailsPane.addTab("View Details", null, lblGPA, null);
		lblGPA.setLayout(null);
		
		JLabel studentNameLbl = new JLabel("Name");
		studentNameLbl.setFont(new Font("Tahoma", Font.BOLD, 11));
		studentNameLbl.setBounds(88, 25, 47, 14);
		lblGPA.add(studentNameLbl);
		
		JLabel studentUserNameLbl = new JLabel("User Name");
		studentUserNameLbl.setFont(new Font("Tahoma", Font.BOLD, 11));
		studentUserNameLbl.setBounds(57, 96, 61, 14);
		lblGPA.add(studentUserNameLbl);
		
		JLabel studentUINLbl = new JLabel("UIN");
		studentUINLbl.setFont(new Font("Tahoma", Font.BOLD, 11));
		studentUINLbl.setBounds(98, 62, 20, 14);
		lblGPA.add(studentUINLbl);
		
		JLabel studentDeptLbl = new JLabel("Department");
		studentDeptLbl.setFont(new Font("Tahoma", Font.BOLD, 11));
		studentDeptLbl.setBounds(49, 127, 69, 14);
		lblGPA.add(studentDeptLbl);
		
		JLabel studentpositionLbl = new JLabel("Position");
		studentpositionLbl.setFont(new Font("Tahoma", Font.BOLD, 11));
		studentpositionLbl.setBounds(71, 168, 47, 14);
		lblGPA.add(studentpositionLbl);
		
		studentName = new JTextField();
		studentName.setDisabledTextColor(Color.BLACK);
		studentName.setFont(new Font("Arial", Font.BOLD, 11));
		studentName.setBounds(148, 22, 152, 20);
		lblGPA.add(studentName);
		studentName.setColumns(10);		
		studentName.setText(StudentUIDetails.student.getName());
		studentName.setEnabled(false);
		

		studentUIN = new JTextField();
		studentUIN.setDisabledTextColor(Color.BLACK);
		studentUIN.setFont(new Font("Arial", Font.BOLD, 11));
		studentUIN.setBounds(148, 59, 152, 20);
		lblGPA.add(studentUIN);		
		studentUIN.setText(""+student.getUIN());
		studentUIN.setColumns(10);
		studentUIN.setEnabled(false);
		
		studentUserName = new JTextField();
		studentUserName.setDisabledTextColor(Color.BLACK);
		studentUserName.setFont(new Font("Arial", Font.BOLD, 11));
		studentUserName.setBounds(148, 90, 152, 20);
		lblGPA.add(studentUserName);		
		studentUserName.setText(student.getUserName());
		studentUserName.setColumns(10);
		studentUserName.setEnabled(false);
	
		studentDept = new JTextField();
		studentDept.setDisabledTextColor(Color.BLACK);
		studentDept.setFont(new Font("Arial", Font.BOLD, 11));
		studentDept.setBounds(148, 124, 152, 20);
		lblGPA.add(studentDept);		
		studentDept.setText(dept.getDepartmentName());
		studentDept.setColumns(10);
		studentDept.setEnabled(false);
		
		studentPosition = new JTextField();
		studentPosition.setDisabledTextColor(Color.BLACK);
		studentPosition.setFont(new Font("Arial", Font.BOLD, 11));
		studentPosition.setBounds(148, 165, 152, 20);
		lblGPA.add(studentPosition);		
		studentPosition.setText("Student");
		studentPosition.setColumns(10);
		studentPosition.setEnabled(false);
		
		showGPA = new JTextField();
		showGPA.setDisabledTextColor(Color.BLACK);
		showGPA.setEnabled(false);
		showGPA.setBounds(148, 210, 152, 20);
		lblGPA.add(showGPA);
		showGPA.setColumns(10);
		showGPA.setText(""+student.getGPA());
		
		JLabel lblGpa = new JLabel("GPA");
		lblGpa.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblGpa.setBounds(95, 213, 23, 14);
		lblGPA.add(lblGpa);
		
		JTabbedPane editDetailsPane = new JTabbedPane(JTabbedPane.TOP);
		viewDetailsPane.addTab("Edit details", null, editDetailsPane, null);
		
		JPanel changeNamepanel = new JPanel();
		editDetailsPane.addTab("Change Name", null, changeNamepanel, null);
		changeNamepanel.setLayout(null);
		
		JLabel presentNameLbl = new JLabel("Present Name");
		presentNameLbl.setFont(new Font("Tahoma", Font.BOLD, 11));
		presentNameLbl.setBounds(51, 46, 82, 14);
		changeNamepanel.add(presentNameLbl);
		
		presentName = new JLabel(student.getName());
		presentName.setFont(new Font("Arial", Font.BOLD, 11));
		presentName.setBounds(151, 46, 128, 14);
		changeNamepanel.add(presentName);
		
		JLabel lblNewName = new JLabel("Enter New Name");
		lblNewName.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewName.setBounds(42, 86, 91, 14);
		changeNamepanel.add(lblNewName);
		
		newName = new JTextField();
		newName.setBounds(151, 83, 128, 20);
		changeNamepanel.add(newName);
		newName.setColumns(10);
		
		JButton btnUpdateName = new JButton("Update Name");
		btnUpdateName.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnUpdateName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				boolean check=student.updateStudentName(newName.getText());
				try {
					student= new Student(UIN);
					if(check){
						JOptionPane.showMessageDialog(null, "Name Updated ", "Update", JOptionPane.INFORMATION_MESSAGE);
						initializeViewDetails(student);
						newName.setText("");
					}
					
				} catch (People.PersonDoesNotExistException e) {
					JOptionPane.showMessageDialog(null, "Person does not exist in the database");
					e.printStackTrace();
					return;
				}
	
			}
		});
		
		btnUpdateName.setBounds(157, 157, 122, 23);
		changeNamepanel.add(btnUpdateName);
		
		
		
		JPanel changeUserNamePanel = new JPanel();
		editDetailsPane.addTab("Change User Name", null, changeUserNamePanel, null);
		changeUserNamePanel.setLayout(null);
		
		JLabel lblPresentUserName = new JLabel("Present User Name");
		lblPresentUserName.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPresentUserName.setBounds(35, 46, 108, 14);
		changeUserNamePanel.add(lblPresentUserName);
		
		presentUserName = new JLabel();
		presentUserName.setFont(new Font("Arial", Font.BOLD, 11));
		presentUserName.setBounds(153, 46, 124, 14);
		changeUserNamePanel.add(presentUserName);
		presentUserName.setText(student.getUserName());
		
		JLabel lblNewUserName = new JLabel("New User Name");
		lblNewUserName.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewUserName.setBounds(56, 87, 87, 14);
		changeUserNamePanel.add(lblNewUserName);
		
		newUserName = new JTextField();
		newUserName.setBounds(153, 84, 124, 20);
		changeUserNamePanel.add(newUserName);
		newUserName.setColumns(10);
		
		JButton btnUpdateUserName = new JButton("Update User Name");
		btnUpdateUserName.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnUpdateUserName.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				
				boolean check=student.updateStudentUserName(newUserName.getText());
				try {
					student= new Student(UIN);
					if(check){	
						JOptionPane.showMessageDialog(null, "User Name Updated ", "Update", JOptionPane.INFORMATION_MESSAGE);
						initializeViewDetails(student);
						newUserName.setText("");
					}
				} catch (People.PersonDoesNotExistException e) {
					JOptionPane.showMessageDialog(null, "Person does not exist in the database");
					e.printStackTrace();
					return;
				}
				
				
			}
		});
		btnUpdateUserName.setBounds(139, 156, 151, 23);
		changeUserNamePanel.add(btnUpdateUserName);
		
				
		
		JPanel changePasswordPanel = new JPanel();
		editDetailsPane.addTab("Change Password", null, changePasswordPanel, null);
		changePasswordPanel.setLayout(null);
		
		final JLabel lblEnterCurrentPassword = new JLabel("Enter current password");
		lblEnterCurrentPassword.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblEnterCurrentPassword.setBounds(24, 47, 132, 14);
		changePasswordPanel.add(lblEnterCurrentPassword);
		
		currentPasswordTxt = new JTextField();
		currentPasswordTxt.setBounds(180, 47, 115, 20);
		changePasswordPanel.add(currentPasswordTxt);
		currentPasswordTxt.setColumns(10);
		
		newPasswordTxt = new JTextField();
		newPasswordTxt.setBounds(180, 92, 115, 20);
		changePasswordPanel.add(newPasswordTxt);
		newPasswordTxt.setColumns(10);
		newPasswordTxt.setEnabled(false);
		
		final JLabel lblEnterNewPassword = new JLabel("Enter New Password");
		lblEnterNewPassword.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblEnterNewPassword.setBounds(34, 95, 115, 14);
		changePasswordPanel.add(lblEnterNewPassword);
		lblEnterNewPassword.setEnabled(false);
		
		final JButton changePassword = new JButton("change Password");
		changePassword.setFont(new Font("Tahoma", Font.BOLD, 11));
		changePassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				boolean ifChanged=Login.changePassword(student.getUserName(),newPasswordTxt.getText());
				if(ifChanged){
					JOptionPane.showMessageDialog(null, "Password Updated", "Authenticate", JOptionPane.INFORMATION_MESSAGE);
					
					currentPasswordTxt.setEnabled(true);
					newPasswordTxt.setText("");
					checkButton.setEnabled(true);
					changePassword.setEnabled(false);
					lblEnterNewPassword.setEnabled(false);
					newPasswordTxt.setEnabled(false);
					
				
					}
				else{
					JOptionPane.showMessageDialog(null, "Password NOT Updated", "Authenticate", JOptionPane.INFORMATION_MESSAGE);
				}
			}
			
			
		});
		changePassword.setBounds(168, 151, 140, 20);
		changePasswordPanel.add(changePassword);
		changePassword.setEnabled(false);
		
		//System.out.println("aaaaaa "+s.getUserName());
		//System.out.println("aaaaaa "+currentPasswordTxt.getText());
		
		checkButton = new JButton("Check");
		checkButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		checkButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				Login loginDetails=new Login(student.getUserName(), currentPasswordTxt.getText().toCharArray());
				boolean check=loginDetails.authenticate();
				System.out.println(check);
					if(check)
						
					{
						JOptionPane.showMessageDialog(null, "Password OK", "Authenticate", JOptionPane.INFORMATION_MESSAGE);
						lblEnterCurrentPassword.setEnabled(false);
						currentPasswordTxt.setEnabled(false);
						currentPasswordTxt.setText("");
						changePassword.setEnabled(true);
						lblEnterNewPassword.setEnabled(true);
						newPasswordTxt.setEnabled(true);
						checkButton.setEnabled(false);
						
					}
				
			}
		});
		checkButton.setBounds(326, 46, 89, 23);
		changePasswordPanel.add(checkButton);
		

		
		JTabbedPane studentJobsPanel = new JTabbedPane(JTabbedPane.TOP);
		viewDetailsPane.addTab("Student Jobs", null, studentJobsPanel, null);
		
		JPanel viewApp = new JPanel();
		studentJobsPanel.addTab("View Application Details", null, viewApp, null);
		viewApp.setLayout(null);
		
		workEx = new JComboBox<Double>();
		workEx.setBounds(279, 51, 82, 20);
		workEx.setModel(new DefaultComboBoxModel<Double>(new Double[] {1.0, 1.5, 2.0, 2.5, 3.0, 3.5, 4.0}));
		viewApp.add(workEx);
		workEx.setSelectedIndex(-1);
		
		JLabel lblWorkExperience = new JLabel("Work Experience");
		lblWorkExperience.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblWorkExperience.setBounds(140, 54, 103, 14);
		viewApp.add(lblWorkExperience);
		
		skill1 = new JCheckBox("Skill 1");
		skill1.setFont(new Font("Tahoma", Font.BOLD, 11));
		skill1.setBounds(279, 85, 124, 23);
		viewApp.add(skill1);
		
		skill2 = new JCheckBox("Skill 2");
		skill2.setFont(new Font("Tahoma", Font.BOLD, 11));
		skill2.setBounds(279, 111, 109, 23);
		viewApp.add(skill2);
		
		skill3 = new JCheckBox("Skill 3");
		skill3.setFont(new Font("Tahoma", Font.BOLD, 11));
		skill3.setBounds(279, 137, 109, 23);
		viewApp.add(skill3);
		
		skill4 = new JCheckBox("Skill 4");
		skill4.setFont(new Font("Tahoma", Font.BOLD, 11));
		skill4.setBounds(279, 163, 109, 23);
		viewApp.add(skill4);
		
		skill5 = new JCheckBox("Skill 5");
		skill5.setFont(new Font("Tahoma", Font.BOLD, 11));
		skill5.setBounds(279, 189, 109, 23);
		viewApp.add(skill5);
		
		JLabel label = new JLabel("Select the skills you know");
		label.setFont(new Font("Tahoma", Font.BOLD, 11));
		label.setBounds(89, 130, 152, 14);
		viewApp.add(label);
		
		JLabel label_1 = new JLabel("Student Application Details");
		label_1.setFont(new Font("Arial", Font.BOLD, 13));
		label_1.setBounds(184, 11, 182, 14);
		viewApp.add(label_1);
		
		btnAddApplicationDetails = new JButton("Add Application Details");
		btnAddApplicationDetails.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				boolean check;
				try {
					check = JobApplication.addApplicationDetails(student.getUIN(), (Double) workEx.getSelectedItem(), 
													skill1.isSelected(), skill2.isSelected(), skill3.isSelected(), skill4.isSelected(), skill5.isSelected());
					
					if(check){
						JOptionPane.showMessageDialog(null, "Added Details", "Application Details", JOptionPane.INFORMATION_MESSAGE);
						btnAddApplicationDetails.setVisible(false);
						btnUpdateApplicationDetails.setVisible(true);
					}
					
				} catch (People.PersonDoesNotExistException e1) {
					JOptionPane.showMessageDialog(null, "Person does not exist in the database");
					e1.printStackTrace();
					return;
				}
				
				
			}
		});
		btnAddApplicationDetails.setBounds(128, 236, 172, 23);
		viewApp.add(btnAddApplicationDetails);
		
		btnUpdateApplicationDetails = new JButton("Update Application Details");
		btnUpdateApplicationDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				boolean check;
				try {
					check = JobApplication.updateApplication(student.getUIN(), (Double) workEx.getSelectedItem(), 
							skill1.isSelected(), skill2.isSelected(), skill3.isSelected(), skill4.isSelected(), skill5.isSelected());
					if(check){
						JOptionPane.showMessageDialog(null, "Updated Details", "Application Details", JOptionPane.INFORMATION_MESSAGE);
						btnAddApplicationDetails.setVisible(false);
						btnUpdateApplicationDetails.setVisible(true);
					}
					
				} catch (People.PersonDoesNotExistException e1) {
					JOptionPane.showMessageDialog(null, "Person does not exist in the database");
					e1.printStackTrace();
					return;
				}
				
			}
		});
		btnUpdateApplicationDetails.setBounds(314, 236, 182, 23);
		viewApp.add(btnUpdateApplicationDetails);
		
	
		initializeApplication();
		
	}
	
	public void initializeViewDetails(Student student){
		
		studentName.setText(student.getName());
		studentUIN.setText(""+student.getUIN());
		studentUserName.setText(student.getUserName());
		studentDept.setText(dept.getDepartmentName());
		presentName.setText(student.getName());
		presentUserName.setText(student.getUserName());
		showGPA.setText(""+student.getGPA());

		
	}
	
	public void initializeApplication(){
		
		boolean check=JobApplication.addApplicationDetailsCheck(student.getUIN());
		
		if(check){
			
			JobApplication jobAppli=student.jobApplication;
			double workExperience=jobAppli.getWorkEx();
			boolean retskill1=jobAppli.isSkill1();
			boolean retskill2=jobAppli.isSkill2();
			boolean retskill3=jobAppli.isSkill3();
			boolean retskill4=jobAppli.isSkill4();
			boolean retskill5=jobAppli.isSkill5();
			
			System.out.println("ret work ex is "+workExperience);
			int indexForWorkExComboBox=getIndex(workExperience);
			System.out.println("index is "+indexForWorkExComboBox);
			
			workEx.setSelectedIndex(indexForWorkExComboBox);
			skill1.setSelected(retskill1);
			skill2.setSelected(retskill2);
			skill3.setSelected(retskill3);
			skill4.setSelected(retskill4);
			skill5.setSelected(retskill5);
			
			btnAddApplicationDetails.setVisible(false);
			btnUpdateApplicationDetails.setVisible(true);
			
			try {
				student=new Student(UIN);
			} catch (People.PersonDoesNotExistException e) {
				JOptionPane.showMessageDialog(null, "Person does not exist in the database");
				e.printStackTrace();
				return;
			}
			
			
		}
		else{
			
			btnAddApplicationDetails.setVisible(true);
			btnUpdateApplicationDetails.setVisible(false);
		}

	}
	
	public int getIndex(Double n){
		
		int index=-1;
		
		for(int i=0;i<workEx.getModel().getSize();i++){
			
			if(n.equals(workEx.getItemAt(i)))
				return i;	
		
		}
		
		return index;
	}


}

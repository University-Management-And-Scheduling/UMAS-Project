
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import java.awt.Color;

/**
 * @author Akshay
 * 
 */

/*************** ADMINUSEUI.JAVA CLASS WAS WRITTEN BY AKSHAY THIRKATEH ********************************/

public class AdminUseUI extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static Admin admin;
	static Department dept;
	static int UIN;
	private static AdminUseUI adminUseUI;

	private JTextField adminName;
	private JTextField adminUIN;
	private JTextField adminUserName;
	private JTextField adminDept;
	private JTextField adminPosition;
	private JTextField newName;
	private JLabel presentName;
	private JTextField newUserName;
	private JLabel presentUserName;
	private JTextField currentPasswordTxt;
	private JTextField newPasswordTxt;
	private JButton checkButton;
	private JTextField adminOfficeAddress;
	private JTextField adminOfficeHours;
	private JLabel CurrentOfficeAddress;
	private JTextField newOfficeAddress;
	private JTextField newOfficeHours;
	private JLabel currentOfficeHours;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminUseUI frame = new AdminUseUI(new Admin(1));
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public static AdminUseUI getInstance(Admin a){
		try {
			adminUseUI = new AdminUseUI(a);
		} catch (Department.DepartmentDoesNotExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return adminUseUI;
	}
	
	public AdminUseUI(final Admin a) throws Department.DepartmentDoesNotExistException {
		
		AdminUseUI.admin=a;
		DBAnnotation.annoate("deptID", "People", "DepartmentID", true);
		int deptID = admin.getDeptID();
		AdminUseUI.dept=new Department(deptID);
		
		DBAnnotation.annoate("UIN", "Employee", "UIN", true);
		int uin = admin.getUIN();
		AdminUseUI.UIN=uin;
		
		setBounds(100, 100, 714, 407);
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(new BorderLayout(0, 0));
		
		JTabbedPane viewDetailsPane = new JTabbedPane(JTabbedPane.TOP);
		add(viewDetailsPane, BorderLayout.CENTER);
		
		final JPanel viewDetails = new JPanel();
		viewDetailsPane.addTab("View Details", null, viewDetails, null);
		viewDetails.setLayout(null);
		
		JLabel adminNameLbl = new JLabel("Name");
		adminNameLbl.setFont(new Font("Tahoma", Font.BOLD, 11));
		adminNameLbl.setBounds(88, 25, 47, 14);
		viewDetails.add(adminNameLbl);
		
		JLabel adminUserNameLbl = new JLabel("User Name");
		adminUserNameLbl.setFont(new Font("Tahoma", Font.BOLD, 11));
		adminUserNameLbl.setBounds(57, 96, 61, 14);
		viewDetails.add(adminUserNameLbl);
		
		JLabel adminUINLbl = new JLabel("UIN");
		adminUINLbl.setFont(new Font("Tahoma", Font.BOLD, 11));
		adminUINLbl.setBounds(98, 62, 20, 14);
		viewDetails.add(adminUINLbl);
		
		JLabel adminDeptLbl = new JLabel("Department");
		adminDeptLbl.setFont(new Font("Tahoma", Font.BOLD, 11));
		adminDeptLbl.setBounds(49, 127, 69, 14);
		viewDetails.add(adminDeptLbl);
		
		JLabel adminpositionLbl = new JLabel("Position");
		adminpositionLbl.setFont(new Font("Tahoma", Font.BOLD, 11));
		adminpositionLbl.setBounds(71, 168, 47, 14);
		viewDetails.add(adminpositionLbl);
		
		adminName = new JTextField();
		adminName.setDisabledTextColor(Color.BLACK);
		adminName.setFont(new Font("Arial", Font.BOLD, 11));
		adminName.setBounds(148, 22, 152, 20);
		viewDetails.add(adminName);
		adminName.setColumns(10);		
		adminName.setText(AdminUseUI.admin.getName());
		adminName.setEnabled(false);
		

		adminUIN = new JTextField();
		adminUIN.setDisabledTextColor(Color.BLACK);
		adminUIN.setFont(new Font("Arial", Font.BOLD, 11));
		adminUIN.setBounds(148, 59, 152, 20);
		viewDetails.add(adminUIN);		
		adminUIN.setText(""+admin.getUIN());
		adminUIN.setColumns(10);
		adminUIN.setEnabled(false);
		
		adminUserName = new JTextField();
		adminUserName.setDisabledTextColor(Color.BLACK);
		adminUserName.setFont(new Font("Arial", Font.BOLD, 11));
		adminUserName.setBounds(148, 90, 152, 20);
		viewDetails.add(adminUserName);		
		adminUserName.setText(admin.getUserName());
		adminUserName.setColumns(10);
		adminUserName.setEnabled(false);
	
		adminDept = new JTextField();
		adminDept.setDisabledTextColor(Color.BLACK);
		adminDept.setFont(new Font("Arial", Font.BOLD, 11));
		adminDept.setBounds(148, 124, 152, 20);
		viewDetails.add(adminDept);		
		adminDept.setText(dept.getDepartmentName());
		adminDept.setColumns(10);
		adminDept.setEnabled(false);
		
		adminPosition = new JTextField();
		adminPosition.setDisabledTextColor(Color.BLACK);
		adminPosition.setFont(new Font("Arial", Font.BOLD, 11));
		adminPosition.setBounds(148, 165, 152, 20);
		viewDetails.add(adminPosition);		
		adminPosition.setText("Department Admin");
		adminPosition.setColumns(10);
		adminPosition.setEnabled(false);
		
		adminOfficeAddress = new JTextField();
		adminOfficeAddress.setDisabledTextColor(Color.BLACK);
		adminOfficeAddress.setFont(new Font("Arial", Font.BOLD, 11));
		adminOfficeAddress.setEnabled(false);
		adminOfficeAddress.setBounds(148, 206, 152, 20);
		viewDetails.add(adminOfficeAddress);
		adminOfficeAddress.setColumns(10);
		adminOfficeAddress.setText(admin.getOfficeAddress());
		
		JLabel lblOfficeAddressAdmin = new JLabel("Office Address");
		lblOfficeAddressAdmin.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblOfficeAddressAdmin.setBounds(37, 209, 81, 14);
		viewDetails.add(lblOfficeAddressAdmin);
		
		JLabel lblOfficeHoursAdmin = new JLabel("Office Hours");
		lblOfficeHoursAdmin.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblOfficeHoursAdmin.setBounds(49, 246, 69, 14);
		viewDetails.add(lblOfficeHoursAdmin);
		
		adminOfficeHours = new JTextField();
		adminOfficeHours.setEnabled(false);
		adminOfficeHours.setDisabledTextColor(Color.BLACK);
		adminOfficeHours.setFont(new Font("Arial", Font.BOLD, 11));
		adminOfficeHours.setBounds(148, 243, 152, 20);
		viewDetails.add(adminOfficeHours);
		adminOfficeHours.setColumns(10);
		adminOfficeHours.setText(admin.getOfficeHours());
		
		JTabbedPane editDetailsPane = new JTabbedPane(JTabbedPane.TOP);
		viewDetailsPane.addTab("Edit details", null, editDetailsPane, null);
		
		JPanel changeNamepanel = new JPanel();
		editDetailsPane.addTab("Change Name", null, changeNamepanel, null);
		changeNamepanel.setLayout(null);
		
		JLabel presentNameLbl = new JLabel("Present Name");
		presentNameLbl.setFont(new Font("Tahoma", Font.BOLD, 11));
		presentNameLbl.setBounds(51, 46, 82, 14);
		changeNamepanel.add(presentNameLbl);
		
		presentName = new JLabel(admin.getName());
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
				
				if(newName.getText().equals("")){
					JOptionPane.showMessageDialog(null, "Enter New name ", "Error", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				
				boolean check=admin.updateAdminName(newName.getText());
				try {
					admin= new Admin(UIN);
					
					if(check){
						initializeViewDetails(admin);
						JOptionPane.showMessageDialog(null, "Name Updated ", "Update", JOptionPane.INFORMATION_MESSAGE);
						newName.setText("");
					}
					
				} catch (People.PersonDoesNotExistException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
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
		presentUserName.setText(admin.getUserName());
		
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
				
				if(newUserName.getText().equals("")){
					JOptionPane.showMessageDialog(null, "Enter New Username ", "Error", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				boolean check=admin.updateAdminUserName(newUserName.getText());
				try {
					admin= new Admin(UIN);
					if(check){
						initializeViewDetails(admin);
						JOptionPane.showMessageDialog(null, "User Name Updated ", "Update", JOptionPane.INFORMATION_MESSAGE);
						newUserName.setText("");
					}
					
				} catch (People.PersonDoesNotExistException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
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
		
		currentPasswordTxt = new JPasswordField();
		currentPasswordTxt.setBounds(180, 47, 115, 20);
		changePasswordPanel.add(currentPasswordTxt);
		currentPasswordTxt.setColumns(10);
		
		newPasswordTxt = new JPasswordField();
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
				
				if(newPasswordTxt.getText().equals("")){
					JOptionPane.showMessageDialog(null, "Enter New Password ", "Error", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				
				boolean ifChanged=Login.changePassword(admin.getUserName(),newPasswordTxt.getText());
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
				
				if(currentPasswordTxt.getText().equals("")){
					JOptionPane.showMessageDialog(null, "Enter Current Password", "Authenticate", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				
				Login loginDetails=new Login(admin.getUserName(), currentPasswordTxt.getText().toCharArray());
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
		
		
		JPanel editOfficeDetails = new JPanel();
		editDetailsPane.addTab("Edit Office Details", null, editOfficeDetails, null);
		editOfficeDetails.setLayout(null);
		
		JLabel currentOfficeHoursLbl = new JLabel("Current Office Hours");
		currentOfficeHoursLbl.setFont(new Font("Tahoma", Font.BOLD, 11));
		currentOfficeHoursLbl.setBounds(66, 61, 114, 14);
		editOfficeDetails.add(currentOfficeHoursLbl);
		
		currentOfficeHours = new JLabel(admin.getOfficeHours());
		currentOfficeHours.setFont(new Font("Arial", Font.BOLD, 11));
		currentOfficeHours.setBounds(214, 61, 127, 14);
		editOfficeDetails.add(currentOfficeHours);
		
		JLabel newOfficeHoursLbl = new JLabel("New Office Hours");
		newOfficeHoursLbl.setFont(new Font("Tahoma", Font.BOLD, 11));
		newOfficeHoursLbl.setBounds(86, 201, 94, 14);
		editOfficeDetails.add(newOfficeHoursLbl);
		
		newOfficeHours = new JTextField();
		newOfficeHours.setBounds(214, 198, 127, 20);
		editOfficeDetails.add(newOfficeHours);
		newOfficeHours.setColumns(10);
		
		JButton btnUpdateOfficeHours = new JButton("Update Office Details");
		btnUpdateOfficeHours.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(newOfficeAddress.getText().equals("")||newOfficeHours.getText().equals("")){
					JOptionPane.showMessageDialog(null, "Enter new Details", "Update", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				boolean check;
				try {
					check = Employee.updateEmpDetails(admin.getUIN(), newOfficeAddress.getText(), newOfficeHours.getText());
					admin= new Admin(UIN);
					if(check)
					{
						JOptionPane.showMessageDialog(null, "Employee Details Updated", "Update", JOptionPane.INFORMATION_MESSAGE);
						admin= new Admin(UIN);
						initializeViewDetails(admin);
						newOfficeAddress.setText("");
						newOfficeHours.setText("");
					}
					else{	
						JOptionPane.showMessageDialog(null, "Employee Details Not Updated", "Update", JOptionPane.INFORMATION_MESSAGE);
					}
				} catch (Student.AccessDeniedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (People.PersonDoesNotExistException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnUpdateOfficeHours.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnUpdateOfficeHours.setBounds(196, 247, 160, 23);
		editOfficeDetails.add(btnUpdateOfficeHours);
		
		JLabel lblNewOfficeAddress = new JLabel("New Office Address");
		lblNewOfficeAddress.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewOfficeAddress.setBounds(73, 151, 107, 14);
		editOfficeDetails.add(lblNewOfficeAddress);
		
		newOfficeAddress = new JTextField();
		newOfficeAddress.setBounds(214, 148, 127, 20);
		editOfficeDetails.add(newOfficeAddress);
		newOfficeAddress.setColumns(10);
		
		JLabel currentOfficeAddressLbl = new JLabel("Current Office Address");
		currentOfficeAddressLbl.setFont(new Font("Tahoma", Font.BOLD, 11));
		currentOfficeAddressLbl.setBounds(53, 98, 127, 14);
		editOfficeDetails.add(currentOfficeAddressLbl);
		
		CurrentOfficeAddress = new JLabel(admin.getOfficeAddress());
		CurrentOfficeAddress.setFont(new Font("Arial", Font.BOLD, 11));
		CurrentOfficeAddress.setBounds(214, 98, 127, 14);
		editOfficeDetails.add(CurrentOfficeAddress);
		
	
		
	}
	
	public void initializeViewDetails(Admin admin){
		
		adminName.setText(admin.getName());
		adminUIN.setText(""+admin.getUIN());
		adminUserName.setText(admin.getUserName());
		adminDept.setText(dept.getDepartmentName());
		presentName.setText(admin.getName());
		presentUserName.setText(admin.getUserName());
		adminOfficeAddress.setText(admin.getOfficeAddress());
		adminOfficeHours.setText(admin.getOfficeHours());
		currentOfficeHours.setText(admin.getOfficeHours());
		CurrentOfficeAddress.setText(admin.getOfficeAddress());
		


		
	}


}

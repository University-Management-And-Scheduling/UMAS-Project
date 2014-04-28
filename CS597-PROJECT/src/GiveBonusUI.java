import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.JComboBox;


/**
 * @author Akshay
 * 
 */

/*************** GIVEBONUSUI.JAVA CLASS WAS WRITTEN BY AKSHAY THIRKATEH ********************************/

public class GiveBonusUI extends JPanel {
	
	static Employee prof;
	static Department dept;
	static Employee prof2;
	ArrayList<Department> getAllDepts;
	ArrayList<Employee> getAllEmpNames;

	private JPanel contentPane;
	private JTextField enterBonus;
	private JComboBox<String> deptComboBox;
	private JComboBox<String> employeeComboBox;
	private JLabel currentPay;
	private JLabel updatedPay;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GiveBonusUI frame = new GiveBonusUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GiveBonusUI() {
		
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 553, 391);
		//contentPane = new JPanel();
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(new BorderLayout(0, 0));
		//setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblPayDetails = new JLabel("Pay Details");
		lblPayDetails.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPayDetails.setBounds(216, 31, 75, 14);
		panel.add(lblPayDetails);
		
		enterBonus = new JTextField();
		enterBonus.setBounds(207, 206, 64, 20);
		panel.add(enterBonus);
		enterBonus.setColumns(10);
		
		JButton btnNewButton = new JButton("Give Bonus");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					if(prof==null){
						JOptionPane.showMessageDialog(null, "Please select an employee", "Update", JOptionPane.INFORMATION_MESSAGE);
						return;
					}
					if(prof!=null && enterBonus.getText().equals("")){
						JOptionPane.showMessageDialog(null, "Please enter the bonus percentage", "Update", JOptionPane.INFORMATION_MESSAGE);
						return;
					}
					
					boolean check=Employee.giveBonus(prof.getUIN(), Double.parseDouble(enterBonus.getText()));
					if(check){
						JOptionPane.showMessageDialog(null, "Salary Updated ", "Update", JOptionPane.INFORMATION_MESSAGE);
						initializeSalary(prof);
						initializeAllDepartments();
						enterBonus.setText("");
						updatedPay.setText("");
						//employeeComboBox.setSelectedIndex(0);
					}
					else{
						JOptionPane.showMessageDialog(null, "Salary Not Updated ", "Update", JOptionPane.INFORMATION_MESSAGE);
					}
					
				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(null, "Not a number ", "Update", JOptionPane.INFORMATION_MESSAGE);
					e1.printStackTrace();
				}
				catch (Employee.bonusNotValidException e1) {
					JOptionPane.showMessageDialog(null, "Bonus not valid ", "Update", JOptionPane.INFORMATION_MESSAGE);
					e1.printStackTrace();
				}
				catch (Student.AccessDeniedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton.setBounds(207, 298, 112, 34);
		panel.add(btnNewButton);
		
		JButton tenPercentButton = new JButton("10%");
		tenPercentButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				updatedPay.setText("");
				enterBonus.setText("10.00");
				double newSal=(((prof2.getSalary())*0.10)+prof2.getSalary());
				updatedPay.setText(""+newSal);
				
				
			}
		});
		tenPercentButton.setBounds(381, 149, 67, 23);
		panel.add(tenPercentButton);
		
		JButton fifteenPercentButton = new JButton("15%");
		fifteenPercentButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updatedPay.setText("");
				enterBonus.setText("15.00");
				double newSal=(((prof2.getSalary())*0.15)+prof2.getSalary());
				updatedPay.setText(""+newSal);
			}
		});
		fifteenPercentButton.setBounds(381, 183, 67, 23);
		panel.add(fifteenPercentButton);
		
		JButton twentyPercentbutton = new JButton("20%");
		twentyPercentbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updatedPay.setText("");
				enterBonus.setText("20.00");
				double newSal=(((prof2.getSalary())*0.20)+prof2.getSalary());
				updatedPay.setText(""+newSal);
			}
		});
		twentyPercentbutton.setBounds(381, 217, 67, 23);
		panel.add(twentyPercentbutton);
		
		JButton twentyFivePercentButton = new JButton("25%");
		twentyFivePercentButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				updatedPay.setText("");
				enterBonus.setText("25.00");
				double newSal=(((prof2.getSalary())*0.25)+prof2.getSalary());
				updatedPay.setText(""+newSal);
			}
		});
		twentyFivePercentButton.setBounds(381, 251, 67, 23);
		panel.add(twentyFivePercentButton);
		
		JLabel lblQuickUseTools = new JLabel("Quick use Tools");
		lblQuickUseTools.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblQuickUseTools.setBounds(361, 124, 87, 14);
		panel.add(lblQuickUseTools);
		
		JLabel lblCurrentPay = new JLabel("Current Pay");
		lblCurrentPay.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCurrentPay.setBounds(131, 168, 75, 14);
		panel.add(lblCurrentPay);
		
		currentPay = new JLabel("New label");
		currentPay.setBounds(225, 168, 66, 14);
		panel.add(currentPay);
		//currentPay.setText(""+prof.getSalary());
		
		JLabel dollarLbl = new JLabel("$");
		dollarLbl.setFont(new Font("Tahoma", Font.BOLD, 11));
		dollarLbl.setBounds(210, 168, 12, 14);
		panel.add(dollarLbl);
		
		JLabel lblEnterTheBonus = new JLabel("Enter the bonus Percentage");
		lblEnterTheBonus.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblEnterTheBonus.setBounds(45, 209, 166, 14);
		panel.add(lblEnterTheBonus);
		
		JLabel lblUpdatedPay = new JLabel("Updated Pay");
		lblUpdatedPay.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblUpdatedPay.setBounds(131, 255, 75, 14);
		panel.add(lblUpdatedPay);
		
		updatedPay = new JLabel("New label");
		updatedPay.setBounds(225, 255, 46, 14);
		panel.add(updatedPay);
		
		JLabel dollarSignLbl = new JLabel("$");
		dollarSignLbl.setFont(new Font("Tahoma", Font.BOLD, 11));
		dollarSignLbl.setBounds(216, 255, 12, 14);
		panel.add(dollarSignLbl);
		
		deptComboBox = new JComboBox();
		deptComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String selectedDept=deptComboBox.getItemAt(deptComboBox.getSelectedIndex());
				try {
					Department dept=new Department(selectedDept);
					try {
						getAllEmployeesInADept(dept.getDepartmentName());
						updatedPay.setText("");
						currentPay.setText("");
						enterBonus.setText("");
						if(employeeComboBox.getModel().getSize()>0)
							employeeComboBox.setSelectedIndex(0);						
						
					} catch (Professor.ProfessorDoesNotExistException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} catch (Department.DepartmentDoesNotExistException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				initializeAllDepartments();
				
			}
		});
		deptComboBox.setBounds(207, 81, 112, 20);
		panel.add(deptComboBox);
		
		JLabel lblSelectDepartment = new JLabel("Select Department");
		lblSelectDepartment.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSelectDepartment.setBounds(94, 84, 112, 14);
		panel.add(lblSelectDepartment);
		
		JLabel lblSelectEmployee = new JLabel("Select Employee");
		lblSelectEmployee.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSelectEmployee.setBounds(111, 124, 95, 14);
		panel.add(lblSelectEmployee);
		
		employeeComboBox = new JComboBox();
		employeeComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String selectedEmp=employeeComboBox.getItemAt(employeeComboBox.getSelectedIndex());
				
				prof=new Professor(selectedEmp);
				System.out.println(prof.getName());
				System.out.println(prof.getUserName());
				System.out.println(prof.getUIN());
				try {
					prof2=new Employee(prof.getUIN());
					initializeSalary(prof2);
					updatedPay.setText("");
					enterBonus.setText("");
				} catch (People.PersonDoesNotExistException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				
			}
		});
		employeeComboBox.setBounds(207, 121, 112, 20);
		panel.add(employeeComboBox);
		
		JLabel label = new JLabel("%");
		label.setFont(new Font("Tahoma", Font.BOLD, 11));
		label.setBounds(273, 209, 18, 14);
		panel.add(label);
	
	
	initializeAllDepartments();
	}
	

	public void initializeAllDepartments(){
		
		getAllDepts=Department.getAllDepartments();
		DefaultComboBoxModel<String> model=new DefaultComboBoxModel<String>();
		
		for(Department d: getAllDepts ){
			
			model.addElement(d.getDepartmentName());
			System.out.println(d.getDepartmentName());
			
		}
		deptComboBox.setModel(model);
	}
	
	
	public void getAllEmployeesInADept(String deptNames) throws Professor.ProfessorDoesNotExistException{
		
		try {
			getAllEmpNames=Employee.getAllEmployeesByDepartment(deptNames);
			DefaultComboBoxModel<String> model=new DefaultComboBoxModel<String>();
			
			for(Employee p: getAllEmpNames){
				
				model.addElement(p.getUserName());
			}
			
			employeeComboBox.setModel(model);
		} catch (Department.DepartmentDoesNotExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void initializeSalary(Employee p){
		
		double check=p.getSalary();
		System.out.println(check);
		currentPay.setText(""+p.getSalary());
		
	}
}

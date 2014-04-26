import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedHashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;


//written by Akshay 

public class TaUseUI extends JFrame {
	LinkedHashMap<Integer,Job> retreiveJobs= new LinkedHashMap<Integer, Job>();	
	
	static TA ta;
	static Department dept;
	static int UIN;
	private JLabel currentOfficeHours;
	private JTextField newOfficeHours;
	private JTextField newOfficeAddress;
	private JLabel CurrentOfficeAddress;
	
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TaUseUI frame = new TaUseUI(new TA(1), new CourseOffered(295));
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws Department.DepartmentDoesNotExistException 
	 */
	public TaUseUI(TA t, final CourseOffered c) throws Department.DepartmentDoesNotExistException {
		
		TaUseUI.ta=t;
		TaUseUI.dept=new Department(ta.getDeptID());
		TaUseUI.UIN=ta.getUIN();
		
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 675, 473);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		JPanel editOfficeDetails = new JPanel();
		tabbedPane.addTab("New tab", null, editOfficeDetails, null);
		editOfficeDetails.setLayout(null);
		
		JLabel currentOfficeHoursLbl = new JLabel("Current Office Hours");
		currentOfficeHoursLbl.setFont(new Font("Tahoma", Font.BOLD, 11));
		currentOfficeHoursLbl.setBounds(66, 61, 114, 14);
		editOfficeDetails.add(currentOfficeHoursLbl);
		
		currentOfficeHours = new JLabel(TA.getTAOfficeHours(ta.getUIN(), c.getOfferID()));
		currentOfficeHours.setFont(new Font("Arial", Font.BOLD, 11));
		currentOfficeHours.setBounds(214, 61, 127, 14);
		editOfficeDetails.add(currentOfficeHours);
		
		JLabel newOfficeHoursLbl = new JLabel("New Office Hours");
		newOfficeHoursLbl.setFont(new Font("Tahoma", Font.BOLD, 11));
		newOfficeHoursLbl.setBounds(86, 151, 94, 14);
		editOfficeDetails.add(newOfficeHoursLbl);
		
		newOfficeHours = new JTextField();
		newOfficeHours.setBounds(214, 148, 127, 20);
		editOfficeDetails.add(newOfficeHours);
		newOfficeHours.setColumns(10);
		
		JButton btnUpdateOfficeHours = new JButton("Update Office Details");
		btnUpdateOfficeHours.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(checkStringForName(newOfficeAddress.getText())){
					boolean check=TA.updateTaOfficeAddress(ta.getUIN(), c.getOfferID(), newOfficeAddress.getText());
					try {
						ta= new TA(UIN);
						if(check)
						{
							JOptionPane.showMessageDialog(null, "TA Address Updated", "Update", JOptionPane.INFORMATION_MESSAGE);
							initializeViewDetails(ta,c);
							
							
						}
						else{	
						JOptionPane.showMessageDialog(null, "TA Address Not Updated", "Update", JOptionPane.INFORMATION_MESSAGE);
						
						}
					} catch (People.PersonDoesNotExistException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				else{
					JOptionPane.showMessageDialog(null, "Office Address not in correct format", "Update", JOptionPane.INFORMATION_MESSAGE);
				}
				
				if(checkStringForName(newOfficeHours.getText())){
					boolean check=TA.updateTaOfficeHours(ta.getUIN(), c.getOfferID(), newOfficeHours.getText());
					try {
						ta= new TA(UIN);
						if(check)
						{
							JOptionPane.showMessageDialog(null, "TA Hours Updated", "Update", JOptionPane.INFORMATION_MESSAGE);
							initializeViewDetails(ta,c);
							
							
						}
						else{	
						JOptionPane.showMessageDialog(null, "TA Hours Not Updated", "Update", JOptionPane.INFORMATION_MESSAGE);
						
						}
					} catch (People.PersonDoesNotExistException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						return;
					}
				}
				
				else{
					JOptionPane.showMessageDialog(null, "Office Hours not in correct format", "Update", JOptionPane.INFORMATION_MESSAGE);
				}
				
			}
		});
		btnUpdateOfficeHours.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnUpdateOfficeHours.setBounds(155, 269, 159, 23);
		editOfficeDetails.add(btnUpdateOfficeHours);
		
		JLabel lblNewOfficeAddress = new JLabel("New Office Address");
		lblNewOfficeAddress.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewOfficeAddress.setBounds(73, 201, 107, 14);
		editOfficeDetails.add(lblNewOfficeAddress);
		
		newOfficeAddress = new JTextField();
		newOfficeAddress.setBounds(214, 198, 127, 20);
		editOfficeDetails.add(newOfficeAddress);
		newOfficeAddress.setColumns(10);
		
		JLabel currentOfficeAddressLbl = new JLabel("Current Office Address");
		currentOfficeAddressLbl.setFont(new Font("Tahoma", Font.BOLD, 11));
		currentOfficeAddressLbl.setBounds(53, 98, 127, 14);
		editOfficeDetails.add(currentOfficeAddressLbl);
		
		CurrentOfficeAddress = new JLabel(TA.getTAOfficeAddress(ta.getUIN(), c.getOfferID()));
		CurrentOfficeAddress.setFont(new Font("Arial", Font.BOLD, 11));
		CurrentOfficeAddress.setBounds(214, 98, 127, 14);
		editOfficeDetails.add(CurrentOfficeAddress);
		
		JLabel lblOfferId = new JLabel("Offer ID");
		lblOfferId.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblOfferId.setBounds(134, 33, 46, 14);
		editOfficeDetails.add(lblOfferId);
		
		JLabel showOfferID = new JLabel(c.getCourseName());
		showOfferID.setBounds(214, 33, 46, 14);
		editOfficeDetails.add(showOfferID);
		
//		initializeViewDetails(ta, c);
	}

	public void initializeViewDetails(TA ta1, CourseOffered c){
		
		currentOfficeHours.setText(TA.getTAOfficeHours(ta.getUIN(), c.getOfferID()));
		CurrentOfficeAddress.setText(TA.getTAOfficeAddress(ta.getUIN(), c.getOfferID()));
	
	}
	
	public boolean checkStringForName(String s){
		return s.matches("[a-zA-Z0-9]+( [a-zA-Z0-9]+)?");
	}
}

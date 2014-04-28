
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JPasswordField;



public class LoginUI extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static String loginuserName;
	private static boolean isLoginSuccessfull;
	final private JTextField textField;
	final private JPasswordField passwordField;
	private JButton btnForgotPassword;
	private JButton btnLogin;

	
	public LoginUI() {
		setBounds(100, 100, 500, 300);
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(null);
		
		
		JLabel lblUmasLogin = new JLabel("UMAS Login");
		lblUmasLogin.setBounds(182, 11, 80, 14);
		add(lblUmasLogin);
		
		textField = new JTextField();
		textField.setBounds(171, 77, 121, 20);
		add(textField);
		textField.setColumns(10);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setBounds(81, 80, 80, 14);
		add(lblUsername);
		
		final JLabel lblPassword = new JLabel("Password: ");
		lblPassword.setBounds(82, 120, 79, 14);
		add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(171, 117, 121, 20);
		add(passwordField);
		
		btnLogin = new JButton("LOGIN");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
		 		Login user = new Login(textField.getText(),passwordField.getPassword());
				boolean isPresent = user.authenticate();
				if(isPresent == true){
					isLoginSuccessfull = true;
					loginuserName = textField.getText();
					textField.setText("");
					JOptionPane.showMessageDialog(null, "Login Successful", "Login Message", JOptionPane.INFORMATION_MESSAGE);
					UMASAPP.initializeLoggedInUser(loginuserName, isLoginSuccessfull);
				} else {
					JOptionPane.showMessageDialog(null, "Login Not Successful", "Login Message", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnLogin.setBounds(189, 164, 89, 23);
		add(btnLogin);
		
		btnForgotPassword = new JButton("FORGOT PASSWORD");
		
		btnForgotPassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String passText = btnForgotPassword.getText();
				if(passText.equals("FORGOT PASSWORD")){
					passwordField.setVisible(false);
					lblPassword.setVisible(false);
					btnLogin.setVisible(false);
					textField.setText("");
					btnForgotPassword.setBounds(150, 164, 180, 23);
					btnForgotPassword.setText("RECOVER PASSWORD");
				} else {
					String username = textField.getText();
					if(!(username.isEmpty())){
						boolean passwordrecovered = Login.recoverPassword(username);
						if (passwordrecovered == false){
							JOptionPane.showMessageDialog(null, "Incorrect Username","Error",JOptionPane.ERROR_MESSAGE);
						} else{
							btnForgotPassword.setBounds(231, 164, 160, 23);
							btnForgotPassword.setText("FORGOT PASSWORD");
							textField.setText("");
							passwordField.setVisible(true);
							lblPassword.setVisible(true);
							btnLogin.setVisible(true);
						}
					} else {
						JOptionPane.showMessageDialog(null, "No Data Entered","Error",JOptionPane.ERROR_MESSAGE);
					}

				}
				
			}
		});
		
		btnForgotPassword.setBounds(159, 214, 160, 23);
		add(btnForgotPassword);
		
		
	}
}

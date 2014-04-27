
/****************@author Simant Purohit*********************************/

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class LogOutUI extends JPanel {

	/**
	 * Create the panel.
	 */
	public LogOutUI() {
		setLayout(null);
		
		final JButton btnLogMeOut = new JButton("Log me out");
		btnLogMeOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int answer = JOptionPane.showConfirmDialog(btnLogMeOut, "Are you sure?");
				if(answer == 0){
					UMASAPP.logOutUser();
				}
			}
		});
		btnLogMeOut.setBounds(136, 52, 180, 171);
		add(btnLogMeOut);

	}

}

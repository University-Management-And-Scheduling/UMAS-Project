

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;


public class TaUI extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private StudentUI studentCoursesUI;
	private StudentUIDetails stdDetails;
	private static TaUI taUI;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TaUI frame = new TaUI(new TA(549));
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public static TaUI getInstance(TA ta){
		taUI = new TaUI(ta);
		return taUI;
	}
	
	
	private TaUI(TA ta) {
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1024, 768);
		//contentPane = new JPanel();
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(new BorderLayout(0, 0));
		//setContentPane(contentPane);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		add(tabbedPane, BorderLayout.CENTER);
		
		stdDetails = StudentUIDetails.getInstance(ta);
		JPanel detailsPanel = stdDetails;
		tabbedPane.addTab("Manage my details",detailsPanel);
		
		studentCoursesUI = StudentUI.getInstance(ta);
		JPanel coursesPanel = studentCoursesUI;
		tabbedPane.addTab("Manage my courses", coursesPanel);
		
		JPanel examPanel = new TAExamPanel(ta);
		tabbedPane.addTab("Manage TA courses", examPanel);
		
		JPanel logOut = new LogOutUI();
		tabbedPane.addTab("Log out", logOut);
	}

}

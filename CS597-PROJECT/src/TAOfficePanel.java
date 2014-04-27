

import java.awt.BorderLayout;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;


public class TAOfficePanel extends JPanel {

	public static TA ta;
	private JTabbedPane tabbedPane;
	
	/**
	 * Create the panel.
	 */
	public TAOfficePanel(TA t) {
		ta = t;
		setBounds(new Rectangle(0, 0, 800, 600));
		setLayout(new BorderLayout(0, 0));
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		add(tabbedPane, BorderLayout.CENTER);
		initialize();
	}

	private void initialize() {
		tabbedPane.removeAll();
		tabbedPane.revalidate();
		tabbedPane.repaint();
		ArrayList<CourseOffered> courses = CourseOffered.getAllCurrentCoursesTAedBy(ta);
		System.out.println("TA has "+courses.size()+" courses");
		for(CourseOffered co:courses){
			JPanel p;
			try {
				p = new TaUseUI(ta, co);
				tabbedPane.addTab(co.getCourseName(), p);
			} catch (Department.DepartmentDoesNotExistException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		tabbedPane.revalidate();
		tabbedPane.repaint();
		
	}

}

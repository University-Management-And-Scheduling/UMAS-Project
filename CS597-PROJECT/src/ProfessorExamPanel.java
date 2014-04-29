
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.JTabbedPane;



public class ProfessorExamPanel extends JPanel {

	public static Professor professor;
	private static JTabbedPane tabbedPane;
	/**
	 * Create the panel.
	 */
	public ProfessorExamPanel(Professor p) {
		professor = p;
		setBounds(new Rectangle(0, 0, 800, 600));
		setLayout(new BorderLayout(0, 0));
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		add(tabbedPane, BorderLayout.CENTER);
		initialize();

		
	}
	
	public static void initialize(){
		tabbedPane.removeAll();
		tabbedPane.revalidate();
		tabbedPane.repaint();
		ArrayList<CourseOffered> courses = CourseOffered.getCurrentProfessorCourses(professor);
		System.out.println("Professor has "+courses.size()+" courses");
		for(CourseOffered co:courses){
			JPanel p = new CourseExamsUI(co, false);
			tabbedPane.addTab(co.getCourseName(), p);
			tabbedPane.revalidate();
			tabbedPane.repaint();
			p.revalidate();
			p.repaint();
		}

		
	}

}

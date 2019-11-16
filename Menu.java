import java.awt.*;
import javax.swing.*;

public class Menu {

		public static void main(String[] args)
		{
			// TODO Auto-generated constructor stub
			JFrame Screentitle = new JFrame("eTRT");
			displayMain(Screentitle);

		}

		private static void displayMain(JFrame frame)
		{
			frame.setTitle("eTRT");
			frame.setSize(500, 350);
			JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT,30,30));
			
			JButton Patient = new JButton("Patients");
			Patient.setPreferredSize(new Dimension(200, 100));
			Patient.addActionListener(event -> displayPatient(frame));
			panel.add(Patient);
			
			JButton Visit = new JButton("Visits");
			Visit.setPreferredSize(new Dimension(200, 100));
			Visit.addActionListener(event -> displayVisit(frame));
			panel.add(Visit);
			
			JButton Analytic = new JButton("Analytics");
			Analytic.setPreferredSize(new Dimension(200, 100));
			panel.add(Analytic);
			
			JButton Other = new JButton("Other");
			Other.setPreferredSize(new Dimension(200, 100));
			panel.add(Other);
			
	        frame.setContentPane(panel);
			frame.setVisible(true);
		}
		
		private static void displayPatient(JFrame frame)
		{
			frame.setTitle("Patient");
			frame.getContentPane().removeAll();
			frame.setSize(500, 250);
			
			JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 30, 40));
			
			JButton AddPatient = new JButton("Add New Patient");
			AddPatient.setPreferredSize(new Dimension(200, 100));
			panel.add(AddPatient);
			
			JButton ViewEditPatient = new JButton("View/Edit Patient");
			ViewEditPatient.setPreferredSize(new Dimension(200, 100));
			panel.add(ViewEditPatient);
			
	        frame.setContentPane(panel);
			frame.setVisible(true);
		}
		
		private static void displayVisit(JFrame frame)
		{
			frame.setTitle("Visit");
			frame.getContentPane().removeAll();
			frame.setSize(500, 250);
			
			JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 30, 40));
			
			JButton AddNewVisit = new JButton("Add New Visit");
			AddNewVisit.setPreferredSize(new Dimension(200, 100));
			panel.add(AddNewVisit);
			
			JButton ViewEditVisit = new JButton("View/Edit Visits");
			ViewEditVisit.setPreferredSize(new Dimension(200, 100));
			panel.add(ViewEditVisit);
			
	        frame.setContentPane(panel);
			frame.setVisible(true);
		}
}

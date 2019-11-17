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
			
			JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 30, 20));
			
			JButton AddPatient = new JButton("Add New Patient");
			AddPatient.setPreferredSize(new Dimension(200, 100));
			AddPatient.addActionListener(event -> addNewPatient(frame));
			panel.add(AddPatient);
			
			JButton ViewEditPatient = new JButton("View/Edit Patient");
			ViewEditPatient.setPreferredSize(new Dimension(200, 100));
			panel.add(ViewEditPatient);
			
			JButton GoBack = new JButton("Go Back");
			GoBack.setPreferredSize(new Dimension(100, 50));
			GoBack.addActionListener(event -> displayMain(frame));
			panel.add(GoBack);
			
	        frame.setContentPane(panel);
			frame.setVisible(true);
		}
		
		private static void displayVisit(JFrame frame)
		{
			frame.setTitle("Visit");
			frame.getContentPane().removeAll();
			frame.setSize(500, 250);
			
			JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 30, 20));
			
			JButton AddNewVisit = new JButton("Add New Visit");
			AddNewVisit.setPreferredSize(new Dimension(200, 100));
			panel.add(AddNewVisit);
			
			JButton ViewEditVisit = new JButton("View/Edit Visits");
			ViewEditVisit.setPreferredSize(new Dimension(200, 100));
			panel.add(ViewEditVisit);
			
			JButton GoBack = new JButton("Go Back");
			GoBack.setPreferredSize(new Dimension(100, 50));
			GoBack.addActionListener(event -> displayMain(frame));
			panel.add(GoBack);
			
	        frame.setContentPane(panel);
			frame.setVisible(true);
		}
		
		private static void addNewPatient(JFrame frame)
		{
			frame.setTitle("Add Patient");
			frame.getContentPane().removeAll();
			frame.setSize(700, 450);
			
			JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 20));
			JPanel demoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 20));
			
			setPanel(panel, frame);
			setDemoPanel(demoPanel, frame);
			
			JButton Save = new JButton("Save");
			Save.setPreferredSize(new Dimension(100, 30));
			Save.addActionListener(event -> {
				//save the data to SQL database
				//SQLSend();
				displayPatient(frame);
			});
			panel.add(Save);
			
			JButton demoSave = new JButton("Save");
			demoSave.setPreferredSize(new Dimension(100, 30));
			demoSave.addActionListener(event -> {
				//save the data to SQL database
				//SQLSend();
				displayPatient(frame);
			});
			demoPanel.add(demoSave);
			
			JButton addDemographics = new JButton("Add Demographics");
			addDemographics.setPreferredSize(new Dimension(100, 30));
			addDemographics.addActionListener(event -> {
				frame.setContentPane(demoPanel);
				frame.setVisible(true);
			});
			panel.add(addDemographics);
			
			JButton back = new JButton("Back");
			back.setPreferredSize(new Dimension(100, 30));
			back.addActionListener(event -> {
				frame.setContentPane(panel);
				frame.setVisible(true);
			});
			demoPanel.add(back);
			
			JButton newVisit = new JButton("New Visit");
			newVisit.setPreferredSize(new Dimension(100, 30));
			newVisit.addActionListener(event -> displayPatient(frame));
			panel.add(newVisit);
			
			JButton demoNewVisit = new JButton("New Visit");
			demoNewVisit.setPreferredSize(new Dimension(100, 30));
			demoNewVisit.addActionListener(event -> displayPatient(frame));
			demoPanel.add(demoNewVisit);
			
			JButton Cancel = new JButton("Cancel");
			Cancel.setPreferredSize(new Dimension(100, 30));
			Cancel.addActionListener(event -> displayPatient(frame));
			panel.add(Cancel);
			
			JButton demoCancel = new JButton("Cancel");
			demoCancel.setPreferredSize(new Dimension(100, 30));
			demoCancel.addActionListener(event -> displayPatient(frame));
			demoPanel.add(demoCancel);
			
			frame.setContentPane(panel);
			frame.setVisible(true);
		}
		
		private static void addField(JPanel panel, String label)
		{
			JPanel temp = new JPanel();
			JLabel tempLabel = new JLabel(label);
			JTextField tempField = new JTextField();
			tempField.setColumns(10);
			temp.add(tempLabel, BorderLayout.NORTH);
			temp.add(tempField, BorderLayout.CENTER);
			panel.add(temp);
		}
		
		private static void setPanel(JPanel panel, JFrame frame)
		{
			addField(panel, "First Name*");
			addField(panel, "Last Name*");
			addField(panel, "Middle Name");
			addField(panel, "Date of Birth* (MM-DD-YYYY)");
			addField(panel, "Gender* (M/F)");
			addField(panel, "Phone Number*");
			addField(panel, "Email");
			addField(panel, "Street Address*");
			addField(panel, "City*");
			addField(panel, "State");
			addField(panel, "Zip*");
			addField(panel, "Country*");
			addField(panel, "Social Security Number");
			addField(panel, "Insurance");	
		}
		
		
		
		private static void setDemoPanel(JPanel demoPanel, JFrame frame)
		{
			addField(demoPanel, "Occupation");
			addField(demoPanel, "Work Status");
			addField(demoPanel, "Educational Degree");
			addField(demoPanel, "Tinnitus Onset");
			addField(demoPanel, "Tinnitus Etiology");
			addField(demoPanel, "Hyperacusis Onset");
			addField(demoPanel, "Hyperacusis Etiology");
			
			JPanel temp = new JPanel();
			JLabel tempLabel = new JLabel("Additional Comments");
			JTextArea addComments = new JTextArea(10,15);
			temp.add(tempLabel, BorderLayout.NORTH);
			temp.add(addComments, BorderLayout.CENTER);
			demoPanel.add(temp);
		}
}

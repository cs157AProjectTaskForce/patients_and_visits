import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

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
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
			ViewEditPatient.addActionListener(event -> viewPatients(frame));
			panel.add(ViewEditPatient);
			
			JButton GoBack = new JButton("Go Back");
			GoBack.setPreferredSize(new Dimension(100, 50));
			GoBack.addActionListener(event -> displayMain(frame));
			panel.add(GoBack);
			
	        frame.setContentPane(panel);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
			AddNewVisit.addActionListener(event -> addNewVisit(frame));
			panel.add(AddNewVisit);
			
			JButton ViewEditVisit = new JButton("View/Edit Visits");
			ViewEditVisit.setPreferredSize(new Dimension(200, 100));
			ViewEditVisit.addActionListener(event -> viewVisits(frame));
			panel.add(ViewEditVisit);
			
			JButton GoBack = new JButton("Go Back");
			GoBack.setPreferredSize(new Dimension(100, 50));
			GoBack.addActionListener(event -> displayMain(frame));
			panel.add(GoBack);
			
	        frame.setContentPane(panel);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);
			
		}
		
		private static void addNewPatient(JFrame frame)
		{
			
			frame.setTitle("Add Patient");
			frame.getContentPane().removeAll();
			frame.setSize(700, 450);
			
			JPanel mainPanel = new JPanel(new BorderLayout(10, 20));
			JPanel mainDemo = new JPanel(new BorderLayout(10, 20));
			
			List<JTextField> panelText = new ArrayList<JTextField>();
			List<JTextField> demoText = new ArrayList<JTextField>();
			List<JTextArea> demoArea = new ArrayList<JTextArea>();
			
			JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 20));
			JPanel panelFooter = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 20));
			JPanel demoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 20));
			JPanel demoFooter = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 20));
			
			setPanel(panel, panelText);
			setDemoPanel(demoPanel, demoText, demoArea);
			
			JButton Save = new JButton("Save");
			Save.setPreferredSize(new Dimension(100, 30));
			Save.addActionListener(event -> {
				//save the data to SQL database
				//SavePatient();
				//panelText holds all the content in the textFields.
				displayPatient(frame);
			});
			panelFooter.add(Save);
			
			JButton demoSave = new JButton("Save");
			demoSave.setPreferredSize(new Dimension(100, 30));
			demoSave.addActionListener(event -> {
				//save the data to SQL database
				//SavePatient();
				//SaveDemographics();
				//Demo text and Demo Area hold all the content in the textfields.
				displayPatient(frame);
			});
			demoFooter.add(demoSave);
			
			JButton addDemographics = new JButton("Add Demographics");
			addDemographics.setPreferredSize(new Dimension(100, 30));
			addDemographics.addActionListener(event -> {
				frame.setContentPane(mainDemo);
				frame.setVisible(true);
			});
			panelFooter.add(addDemographics);
			
			JButton back = new JButton("Back");
			back.setPreferredSize(new Dimension(100, 30));
			back.addActionListener(event -> {
				frame.setContentPane(mainPanel);
				frame.setVisible(true);
			});
			demoFooter.add(back);
			
			JButton newVisit = new JButton("New Visit");
			newVisit.setPreferredSize(new Dimension(100, 30));
			newVisit.addActionListener(event -> 
			{
				//save patient first, then new screen.
				//savePatient();
				displayVisit(frame);
				addNewVisit(frame);
				
			});
			panelFooter.add(newVisit);
			
			JButton demoNewVisit = new JButton("New Visit");
			demoNewVisit.setPreferredSize(new Dimension(100, 30));
			demoNewVisit.addActionListener(event -> {
				//save patient first, then new screen
				//savePatient();
				//saveDemographics();
				displayVisit(frame);
				addNewVisit(frame);
			});
			demoFooter.add(demoNewVisit);
			
			JButton Cancel = new JButton("Cancel");
			Cancel.setPreferredSize(new Dimension(100, 30));
			Cancel.addActionListener(event -> displayPatient(frame));
			panelFooter.add(Cancel);
			
			JButton demoCancel = new JButton("Cancel");
			demoCancel.setPreferredSize(new Dimension(100, 30));
			demoCancel.addActionListener(event -> displayPatient(frame));
			demoFooter.add(demoCancel);
			
			mainPanel.add(panel, BorderLayout.CENTER);
			mainPanel.add(panelFooter, BorderLayout.SOUTH);
			mainDemo.add(demoPanel, BorderLayout.CENTER);
			mainDemo.add(demoFooter, BorderLayout.SOUTH);
			
			frame.setContentPane(mainPanel);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);
		}
		
		private static void addNewVisit(JFrame frame)
		{
			frame.setTitle("Add New Visit");
			frame.getContentPane().removeAll();
			frame.setSize(650, 750);
			
			List<JTextField> headerText = new ArrayList<JTextField>();
			List<JTextField> centerText = new ArrayList<JTextField>();
			List<JTextArea> centerArea = new ArrayList<JTextArea>();
			
			JPanel mainHeader = new JPanel(new BorderLayout(10, 30));
			JPanel header = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 20));
			header.setPreferredSize(new Dimension(600, 100));
			JPanel buttonHeader = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 20));
			setHeaderVisit(header, headerText);
			
			//Needs no actionListener
			JButton Interview = new JButton("Interview");
			Interview.setPreferredSize(new Dimension(100, 50));
			buttonHeader.add(Interview);
			
			//Needs no actionListener
			JButton Audiology = new JButton("Audiology");
			Audiology.setPreferredSize(new Dimension(100, 50));
			buttonHeader.add(Audiology);
			
			//Needs no actionListener
			JButton MedicalOther = new JButton("Medical Other");
			MedicalOther.setPreferredSize(new Dimension(100, 50));
			buttonHeader.add(MedicalOther);
			
			//Needs no actionListener
			JButton Diagnose = new JButton("Diagnose");
			Diagnose.setPreferredSize(new Dimension(100, 50));
			buttonHeader.add(Diagnose);
			
			JPanel mainCenter = new JPanel(new BorderLayout(10, 30));
			JPanel center = new JPanel(new FlowLayout(FlowLayout.LEFT, 30, 20));
			center.setPreferredSize(new Dimension(600, 260));
			JPanel buttonCenter = new JPanel(new FlowLayout(FlowLayout.LEFT, 30, 20));
			setCenterVisit(center, centerText, centerArea);
			
			//Needs no actionListener
			JButton InstrumentDetails = new JButton("Instrument Details");
			InstrumentDetails.setPreferredSize(new Dimension(100, 50));
			buttonCenter.add(InstrumentDetails);
			
			//Needs no actionListener
			JButton REMDetails = new JButton("REM Details");
			REMDetails.setPreferredSize(new Dimension(100, 50));
			buttonCenter.add(REMDetails);
			
			//Needs no actionListener
			JButton CounselingDetails = new JButton("Counseling Details");
			CounselingDetails.setPreferredSize(new Dimension(100, 50));
			buttonCenter.add(CounselingDetails);
			
			//Needs no actionListener
			JButton RecommendTreatment = new JButton("Recommend Treatment");
			RecommendTreatment.setPreferredSize(new Dimension(100, 50));
			buttonCenter.add(RecommendTreatment);
			
			JPanel footer = new JPanel(new FlowLayout(FlowLayout.RIGHT, 30, 20));
			footer.setPreferredSize(new Dimension(600, 200));
			
			JButton Save = new JButton("Save");
			Save.setPreferredSize(new Dimension(100, 30));
			Save.addActionListener(event -> {
				try {
					saveVisit(headerText, centerText, centerArea);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				displayVisit(frame);
			});
			footer.add(Save);
			
			JButton Cancel = new JButton("Cancel");
			Cancel.setPreferredSize(new Dimension(100, 30));
			Cancel.addActionListener(event -> displayVisit(frame));
			footer.add(Cancel);
			
			mainHeader.add(header, BorderLayout.CENTER);
			mainHeader.add(buttonHeader, BorderLayout.SOUTH);
			mainCenter.add(center, BorderLayout.CENTER);
			mainCenter.add(buttonCenter, BorderLayout.SOUTH);
			
			frame.add(mainHeader, BorderLayout.NORTH);
			frame.add(mainCenter, BorderLayout.CENTER);
			frame.add(footer, BorderLayout.SOUTH);

			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);
		}
		
		//Basic framework for it. This creates the table. We need columns = table columns and data = entries in database.
		private static void viewPatients(JFrame frame)
		{
			frame.setTitle("View/Edit Patients");
			frame.getContentPane().removeAll();
			frame.setSize(900, 450);
			
			JPanel view = new JPanel(new BorderLayout(20, 20));
			JPanel options = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 20));
			//Call SQL command to retrieve the Patient Table
			//add column names to String[] columns
			String[] columns = {"Test", "Test2"};
			//Iterate through the returned data and add it to JTable.	
			Object[][] data = {{"Test4", "Test4"}, {"Test5", "Test25"}, {"Ok", "Ok"}};
			
			JTable patientTable = new JTable(data, columns) {
				public boolean isCellEditable(int rowIndex, int colIndex) {
					return false;
				}
			};
			patientTable.setRowSelectionAllowed(true);
			patientTable.setAutoCreateRowSorter(true);
			JScrollPane scrollPane = new JScrollPane(patientTable);
			
			JButton viewPatient = new JButton("View Patient");
			viewPatient.setPreferredSize(new Dimension(125, 30));
			options.add(viewPatient);
			
			JButton EditPatient = new JButton("Edit Patient");
			EditPatient.setPreferredSize(new Dimension(125, 30));
			options.add(EditPatient);
			
			JButton deletePatient = new JButton("Delete Patient");
			deletePatient.setPreferredSize(new Dimension(125, 30));
			options.add(deletePatient);
			
			JButton addVisit = new JButton("Add New Visit");
			addVisit.setPreferredSize(new Dimension(125, 30));
			options.add(addVisit);
			
			JButton currentVisit = new JButton("Show Current Visit");
			currentVisit.setPreferredSize(new Dimension(125, 30));
			options.add(currentVisit);
			
			JButton Cancel = new JButton("Cancel");
			Cancel.setPreferredSize(new Dimension(100, 30));
			Cancel.addActionListener(event -> displayPatient(frame));
			options.add(Cancel);
			
			view.add(scrollPane, BorderLayout.CENTER);
			view.add(options, BorderLayout.SOUTH);
			
			frame.setContentPane(view);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);
		}
		
		private static void viewVisits(JFrame frame)
		{
			frame.setTitle("View/Edit Visits");
			frame.getContentPane().removeAll();
			frame.setSize(900, 450);
			
			JPanel view = new JPanel(new BorderLayout(20, 20));
			JPanel options = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 20));
			//Call SQL command to retrieve the Visits Table
			//add column names to String[] columns
			String[] columns = {"New", "Stuff"};
			//Iterate through the returned data and add it to JTable.	
			Object[][] data = {{"Visit", "Visiting"}, {"Abra", "Kadabra"}};
			
			JTable visitTable = new JTable(data, columns) {
				public boolean isCellEditable(int rowIndex, int colIndex) {
					return false;
				}
			};
			visitTable.setRowSelectionAllowed(true);
			visitTable.setAutoCreateRowSorter(true);
			JScrollPane scrollPane = new JScrollPane(visitTable);
			
			JButton viewVisit = new JButton("View/Edit Visit");
			viewVisit.setPreferredSize(new Dimension(125, 30));
			options.add(viewVisit);
			
			JButton deleteVisit = new JButton("Delete Visit");
			deleteVisit.setPreferredSize(new Dimension(125, 30));
			options.add(deleteVisit);
			
			JButton Analyze = new JButton("Analyze");
			Analyze.setPreferredSize(new Dimension(125, 30));
			options.add(Analyze);
			
			JButton Cancel = new JButton("Cancel");
			Cancel.setPreferredSize(new Dimension(100, 30));
			Cancel.addActionListener(event -> displayVisit(frame));
			options.add(Cancel);
			
			view.add(scrollPane, BorderLayout.CENTER);
			view.add(options, BorderLayout.SOUTH);
			
			frame.setContentPane(view);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);
		}
		
		private static void addTextField(JPanel panel, List<JTextField> list, String label)
		{
			JPanel temp = new JPanel();
			JLabel tempLabel = new JLabel(label);
			JTextField tempField = new JTextField();
			tempField.setColumns(10);
			temp.add(tempLabel, BorderLayout.NORTH);
			temp.add(tempField, BorderLayout.CENTER);
			panel.add(temp);
			list.add(tempField);
		}
		
		private static void setHeaderVisit(JPanel header, List<JTextField> list)
		{
			addTextField(header, list, "First Name:");
			addTextField(header, list, "Middle Name:");
			addTextField(header, list, "Last Name:");
			addTextField(header, list, "THC#:");
			addTextField(header, list, "Visit no.");
		}
		
		private static void setCenterVisit(JPanel center, List<JTextField> list, List<JTextArea> area)
		{
			addTextField(center, list, "Problem:");
			addTextField(center, list, "Category:");
			addTextField(center, list, "Protocol:");
			addTextField(center, list, "FU:");
			addTextField(center, list, "Instrument");
			addTextField(center, list, "REM:");
			JPanel temp = new JPanel();
			JLabel tempLabel = new JLabel("Comments:");
			JTextArea addComments = new JTextArea(2,30);
			temp.add(tempLabel, BorderLayout.NORTH);
			temp.add(addComments, BorderLayout.CENTER);
			center.add(temp);
			area.add(addComments);
			addTextField(center, list, "Next Visit:");
		}
		
		private static void setPanel(JPanel panel, List<JTextField> list)
		{
			addTextField(panel, list, "First Name*");
			addTextField(panel, list, "Last Name*");
			addTextField(panel, list, "Middle Name");
			addTextField(panel, list, "Date of Birth* (MM-DD-YYYY)");
			addTextField(panel, list, "Gender* (M/F)");
			addTextField(panel, list, "Phone Number*");
			addTextField(panel, list, "Email");
			addTextField(panel, list, "Street Address*");
			addTextField(panel, list, "City*");
			addTextField(panel, list, "State");
			addTextField(panel, list, "Zip*");
			addTextField(panel, list, "Country*");
			addTextField(panel, list, "Social Security Number");
			addTextField(panel, list, "Insurance");	
		}

		private static void setDemoPanel(JPanel demoPanel, List<JTextField> list, List<JTextArea> area)
		{
			addTextField(demoPanel, list, "Occupation");
			addTextField(demoPanel, list, "Work Status");
			addTextField(demoPanel, list, "Educational Degree");
			addTextField(demoPanel, list, "Tinnitus Onset");
			addTextField(demoPanel, list, "Tinnitus Etiology");
			addTextField(demoPanel, list, "Hyperacusis Onset");
			addTextField(demoPanel, list, "Hyperacusis Etiology");
			
			JPanel temp = new JPanel();
			JLabel tempLabel = new JLabel("Additional Comments");
			JTextArea addComments = new JTextArea(10,30);
			addComments.setLineWrap(true);
			temp.add(tempLabel, BorderLayout.NORTH);
			temp.add(addComments, BorderLayout.CENTER);
			demoPanel.add(temp);
			area.add(addComments);
		}
		
		private static void saveVisit(List<JTextField> header, List<JTextField> center, List<JTextArea> area) throws SQLException {
			int id = 0;
			/*
			SQLVisitLoader test = new SQLVisitLoader();
			try {
				ResultSet visits = test.getVisit();
				visits.last();
				id = visits.getRow();
				visits.close();
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			*/
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			String Date = LocalDate.now().format(formatter);
			
			String FName = header.get(0).getText();
			String MName = header.get(1).getText();
			String LName = header.get(2).getText();
			String THC = header.get(3).getText();
			String Vno = header.get(4).getText();
			//String Prob = center.get(0).getText();
			String cat = center.get(1).getText();
			String prot = center.get(2).getText();
			String FU = center.get(3).getText();
			String Instrument = center.get(4).getText();
			String REM = center.get(5).getText();
			String com = area.get(0).getText();
			String vis = center.get(6).getText();
			String SQL = "insert into VISIT values ('" + id + "','" + Date + "','" + THC
					+ "','" + FName + "','" + MName + "','" + LName + "','" + Vno + "','" +  cat + "','" + prot + "','" +
					Instrument + "','" + REM + "','" + FU + "','" + com + "','" + vis + "')";
			System.out.println(SQL);
			/*
			SQLEntryVisit add = new SQLEntryVisit();
			add.setInsertRow(SQL);
			try {
				add.addEntries();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			add.clear();
			*/
		}
		
}

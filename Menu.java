import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import com.mysql.cj.jdbc.result.ResultSetMetaData;

public class Menu {

	
		/**
		 * @Authors		Leonel, Trevor, Alvin
		 * @param args	Main program to start the GUI
		 * @Project		Problem 1
		 */
		public static void main(String[] args)
		{
			
			JFrame Screentitle = new JFrame("eTRT");
			displayMain(Screentitle);
		}

		/**
		 * 
		 * @param frame:	Frame transitions to new screen
		 * displayMain displays the starting menu for users.	
		 */
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
		
		/**
		 * 
		 * @param frame:	Frame transitions to new screen
		 * displayPatient displays the patient menu for users.	
		 */
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
			ViewEditPatient.addActionListener(event -> {
				try {
					viewPatients(frame);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			});
			panel.add(ViewEditPatient);
			
			JButton GoBack = new JButton("Go Back");
			GoBack.setPreferredSize(new Dimension(100, 50));
			GoBack.addActionListener(event -> displayMain(frame));
			panel.add(GoBack);
			
	        frame.setContentPane(panel);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);
		}
		
		/**
		 * 
		 * @param frame:	Frame transitions to new screen
		 * displayVisit displays the visit menu for users.	
		 */
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
			ViewEditVisit.addActionListener(event -> {
				try {
					viewVisits(frame);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			});
			panel.add(ViewEditVisit);
			
			JButton GoBack = new JButton("Go Back");
			GoBack.setPreferredSize(new Dimension(100, 50));
			GoBack.addActionListener(event -> displayMain(frame));
			panel.add(GoBack);
			
	        frame.setContentPane(panel);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);
			
		}
		
		/**
		 * 
		 * @param frame:	Frame transitions to new screen
		 * addNewPatient displays the form menu for users to fill out. 
		 * Save takes all filled in data and sends it over to savePatient function
		 * Demographics and Back transition the user from one screen to the next.	
		 * Add New Visit saves the user data and then transitions user to addNewVisit menu.
		 * Cancel transitions the user back to the displayPatient menu.
		 */
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
				try {
					String temp = savePatient(panelText);
					saveDemographics(temp, panelText, demoText, demoArea);
				} catch (SQLException e) {
					System.out.println(e);
				}
				displayPatient(frame);
			});
			panelFooter.add(Save);
			
			JButton demoSave = new JButton("Save");
			demoSave.setPreferredSize(new Dimension(100, 30));
			demoSave.addActionListener(event -> {
				try {
					String temp = savePatient(panelText);
					saveDemographics(temp, panelText, demoText, demoArea);
				} catch (SQLException e) {
					System.out.println(e);
				}
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
				try {
					String temp = savePatient(panelText);
					saveDemographics(temp, panelText, demoText, demoArea);
				} catch (SQLException e) {
					System.out.println(e);
				}
				displayPatient(frame);
				addNewPatient(frame);				
			});
			panelFooter.add(newVisit);
			
			JButton demoNewVisit = new JButton("New Visit");
			demoNewVisit.setPreferredSize(new Dimension(100, 30));
			demoNewVisit.addActionListener(event -> {
				try {
					String temp = savePatient(panelText);
					saveDemographics(temp, panelText, demoText, demoArea);
				} catch (SQLException e) {
					System.out.println(e);
				}
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
		
		/**
		 * 
		 * @param frame:	Frame transitions to new screen
		 * addNewVisit displays the visit form for users to fill out.
		 * Save: Saves the data entered by the user by calling saveVisit
		 * Cancel: transitions the user back to DisplayMenu page.	
		 */
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
					System.out.println(e);
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
		
		private static void viewPatients(JFrame frame) throws ClassNotFoundException, SQLException
		{
			frame.setTitle("View/Edit Patients");
			frame.getContentPane().removeAll();
			frame.setSize(900, 450);
			
			JPanel view = new JPanel(new BorderLayout(20, 20));
			JPanel options = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 20));
			SQLPatientLoader test = new SQLPatientLoader();
			ResultSet rs = test.getPatient();
			ResultSetMetaData rsmd = (ResultSetMetaData) rs.getMetaData();
			DefaultTableModel dtm = new DefaultTableModel();
			for(int i = 0; i < rsmd.getColumnCount(); i++)
			{
				dtm.addColumn(rsmd.getColumnName(i+1));
			}		
			while (rs.next())
			{
				Object [] row = new Object[rsmd.getColumnCount()];
				for(int i = 0; i < row.length; ++i)
				{
					row[i] = rs.getObject(i+1);
				}
				dtm.addRow(row);
			}
			
			JTable patientTable = new JTable(dtm) {
				
				public boolean isCellEditable(int rowIndex, int colIndex) {
					return false;
				}		
			};
			
			patientTable.setRowSelectionAllowed(true);
			patientTable.setAutoCreateRowSorter(true);
			JScrollPane scrollPane = new JScrollPane(patientTable);

			JButton viewPatient = new JButton("View Patient");
			viewPatient.setPreferredSize(new Dimension(125, 30));
			viewPatient.addActionListener(event -> {
				try {
					rs.absolute(patientTable.getSelectedRow() + 1);
					Object[] value = new Object[rsmd.getColumnCount()];
					for(int i = 0; i < rsmd.getColumnCount(); ++i)
					{
						value[i] = rs.getObject(i + 1);
					}
					editPatient(value, frame);
				} catch (SQLException e) {
					System.out.println(e);
				}
			});
			options.add(viewPatient);
			
			JButton EditPatient = new JButton("Edit Patient");
			EditPatient.setPreferredSize(new Dimension(125, 30));
			EditPatient.addActionListener(event -> {
				try {
					rs.absolute(patientTable.getSelectedRow() + 1);
					Object[] value = new Object[rsmd.getColumnCount()];
					for(int i = 0; i < rsmd.getColumnCount(); ++i)
					{
						value[i] = rs.getObject(i + 1);
					}
					editPatient(value, frame);
				} catch (SQLException e) {
					System.out.println(e);
				}
			});
			options.add(EditPatient);
			
			JButton deletePatient = new JButton("Delete Patient");
			deletePatient.setPreferredSize(new Dimension(125, 30));
			deletePatient.addActionListener(event -> {
				try {
					rs.absolute(patientTable.getSelectedRow() + 1);
					deletePatient(rs.getInt(1));
					dtm.removeRow(patientTable.getSelectedRow());
				} catch (SQLException e) {
					System.out.println(e);
				}
			});
			options.add(deletePatient);
			
			JButton addVisit = new JButton("Add New Visit");
			addVisit.setPreferredSize(new Dimension(125, 30));
			addVisit.addActionListener(event -> {
				displayVisit(frame);
				addNewVisit(frame);
			});
			options.add(addVisit);
			
			JButton currentVisit = new JButton("Show Current Visit");
			currentVisit.setPreferredSize(new Dimension(125, 30));
			currentVisit.addActionListener(event -> {
				try {
					viewVisits(frame);
				} catch (ClassNotFoundException e) {
					System.out.println(e);
				} catch (SQLException e) {
					System.out.println(e);
				}
			});
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
		
		private static void viewVisits(JFrame frame) throws ClassNotFoundException, SQLException
		{
			frame.setTitle("View/Edit Visits");
			frame.getContentPane().removeAll();
			frame.setSize(900, 450);
			
			JPanel view = new JPanel(new BorderLayout(20, 20));
			JPanel options = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 20));

			SQLVisitLoader test = new SQLVisitLoader();
			ResultSet rs = test.getVisit();
			ResultSetMetaData rsmd = (ResultSetMetaData) rs.getMetaData();
			DefaultTableModel dtm = new DefaultTableModel();
			for(int i = 0; i < rsmd.getColumnCount(); i++)
			{
				dtm.addColumn(rsmd.getColumnName(i+1));
			}		
			while (rs.next())
			{
				Object [] row = new Object[rsmd.getColumnCount()];
				for(int i = 0; i < row.length; ++i)
				{
					row[i] = rs.getObject(i+1);
				}
				dtm.addRow(row);
			}
			
			JTable visitTable = new JTable(dtm) {
				
				public boolean isCellEditable(int rowIndex, int colIndex) {
					return false;
				}
						
			};
			
			visitTable.setRowSelectionAllowed(true);
			visitTable.setAutoCreateRowSorter(true);
			JScrollPane scrollPane = new JScrollPane(visitTable);
			
			JButton viewVisit = new JButton("View/Edit Visit");
			viewVisit.setPreferredSize(new Dimension(125, 30));
			viewVisit.addActionListener(event -> {
				try {
					rs.absolute(visitTable.getSelectedRow() + 1);
					Object[] value = new Object[rsmd.getColumnCount()];
					for(int i = 0; i < rsmd.getColumnCount(); ++i)
					{
						value[i] = rs.getObject(i + 1);
					}
					editVisit(value, frame);
				} catch (SQLException e) {
					System.out.println(e);
				}
			});
			options.add(viewVisit);
			
			JButton deleteVisit = new JButton("Delete Visit");
			deleteVisit.setPreferredSize(new Dimension(125, 30));
			deleteVisit.addActionListener(event -> {
				try {
					rs.absolute(visitTable.getSelectedRow() + 1);
					deleteVisit(rs.getInt(1));
					dtm.removeRow(visitTable.getSelectedRow());
				} catch (SQLException e) {
					System.out.println(e);
				}
			});
			options.add(deleteVisit);
			
			//Doesn't need Action Listener
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
			addTextField(header, list, "First Name:*");
			addTextField(header, list, "Middle Name:");
			addTextField(header, list, "Last Name:*");
			addTextField(header, list, "THC#:*");
		}
		
		private static void setCenterVisit(JPanel center, List<JTextField> list, List<JTextArea> area)
		{
			addTextField(center, list, "Problem:*");
			addTextField(center, list, "Category:*");
			addTextField(center, list, "Protocol:*");
			addTextField(center, list, "FU:");
			addTextField(center, list, "Instrument*");
			addTextField(center, list, "REM*:");
			JPanel temp = new JPanel();
			JLabel tempLabel = new JLabel("Comments:");
			JTextArea addComments = new JTextArea(2,30);
			temp.add(tempLabel, BorderLayout.NORTH);
			temp.add(addComments, BorderLayout.CENTER);
			center.add(temp);
			area.add(addComments);
			addTextField(center, list, "Next Visit:*");
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
			SQLVisitLoader test = new SQLVisitLoader();
			try {
				id = test.getVisitID();
			} catch (ClassNotFoundException e) {
				System.out.println(e);
			}

			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			String Date = LocalDate.now().format(formatter);
			
			String FName = header.get(0).getText();
			String MName = header.get(1).getText();
			if(MName.equals("")) {
				MName = null;
			}
			String LName = header.get(2).getText();
			String THC = header.get(3).getText();
			
			int Vno = 0;
			try {
				Vno = test.getVisitSeq(THC);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			String Prob = center.get(0).getText();
			String cat = center.get(1).getText();
			String prot = center.get(2).getText();
			String FU = center.get(3).getText();
			if(FU.equals("")) {
				FU = "NULL";
			}
			String Instrument = center.get(4).getText();
			String REM = center.get(5).getText();
			String com = area.get(0).getText();
			if(com.equals("")) {
				com = "NULL";
			}
			String vis = center.get(6).getText();
			String SQL = "insert into VISIT values ('" + id + "','" + Date + "','" + THC
					+ "','" + FName + "','" + MName + "','" + LName + "','" + Vno + "','" +  cat + "','" + prot + "','" +
					Instrument + "','" + REM + "','" + FU + "','" + com + "','" + vis + "')";
			SQLEntryVisit add = new SQLEntryVisit();
			add.setSQLStatement(SQL);
			try {
				add.modifyEntries();
			} catch (ClassNotFoundException e) {
				System.out.println(e);
			}
			add.clear();
		}
		
		private static void deleteVisit(int rowNumber)
		{
			SQLEntryVisit delete = new SQLEntryVisit();
			delete.setSQLStatement("delete from VISIT where ID=" + rowNumber);
			try {
				delete.modifyEntries();
			} catch (ClassNotFoundException e) {
				System.out.println(e);
			}
			delete.clear();
		}

		private static void editVisit(Object[] data, JFrame frame)
		{
			frame.setTitle("View/Edit Visits");
			frame.getContentPane().removeAll();
			frame.setSize(900, 450);
			
			JPanel overall = new JPanel(new BorderLayout(10, 10));
			JPanel dataPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 20));
			JPanel buttons = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 20));
			List<JTextField> holder = new ArrayList<JTextField>();
			List<JTextArea> areaHolder = new ArrayList<JTextArea>();
			addTextField(dataPanel, holder, "THC");
			holder.get(0).setText(data[2].toString());
			addTextField(dataPanel, holder, "First Name");
			holder.get(1).setText(data[3].toString());
			addTextField(dataPanel, holder, "Middle Name");
			holder.get(2).setText(data[4].toString());
			addTextField(dataPanel, holder, "Last Name");
			holder.get(3).setText(data[5].toString());
			addTextField(dataPanel, holder, "Category");
			holder.get(4).setText(data[7].toString());
			addTextField(dataPanel, holder, "Protocol");
			holder.get(5).setText(data[8].toString());
			addTextField(dataPanel, holder, "Instrument");
			holder.get(6).setText(data[9].toString());
			addTextField(dataPanel, holder, "REM");
			holder.get(7).setText(data[10].toString());
			addTextField(dataPanel, holder, "FU");
			holder.get(8).setText(data[11].toString());
			JPanel temp = new JPanel();
			JLabel tempLabel = new JLabel("Comments:");
			JTextArea addComments = new JTextArea(2,30);
			temp.add(tempLabel, BorderLayout.NORTH);
			temp.add(addComments, BorderLayout.CENTER);
			dataPanel.add(temp);
			areaHolder.add(addComments);
			areaHolder.get(0).setText(data[12].toString());
			addTextField(dataPanel, holder, "Next Visit");
			holder.get(9).setText(data[13].toString());
			
			JButton Save = new JButton("Save");
			Save.setPreferredSize(new Dimension(100, 30));
			Save.addActionListener(event -> {
				try {
					saveEditVisit(Integer.parseInt(data[0].toString()), holder, areaHolder);
					try {
						viewVisits(frame);
					} catch (ClassNotFoundException e) {
						System.out.println(e);
					}
				} catch (NumberFormatException e) {
					System.out.println(e);
				} catch (SQLException e) {
					System.out.println(e);
				}
			});
		
			buttons.add(Save);
			
			JButton Cancel = new JButton("Cancel");
			Cancel.setPreferredSize(new Dimension(100, 30));
			Cancel.addActionListener(event -> {
				try {
					viewVisits(frame);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			});
			buttons.add(Cancel);
			
			overall.add(dataPanel, BorderLayout.CENTER);
			overall.add(buttons, BorderLayout.SOUTH);
			frame.setContentPane(overall);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);
		}
		
		private static void saveEditVisit(int id, List<JTextField> text, List<JTextArea> comment) throws SQLException {
			String FName = text.get(1).getText();
			String MName = text.get(2).getText();
			String LName = text.get(3).getText();
			String THC = text.get(0).getText();
			String cat = text.get(4).getText();
			String prot = text.get(5).getText();
			String FU = text.get(8).getText();
			String Instrument = text.get(6).getText();
			String REM = text.get(7).getText();
			String com = comment.get(0).getText();
			String vis = text.get(9).getText();
			String SQL = "update PATIENT set THC='" + THC + "', FIRST_NAME='" + FName + "', MIDDLE_NAME='"
					+ MName + "', LAST_NAME='" + LName + "', CATEGORY='" + cat + "', PROTOCOL='" + prot +
					"', INSTRUMENT='" + Instrument + "', REM='" + REM + "', FU='" + FU + "', COMMENTS='" +
					com + "', NEXT_VISIT='" + vis + "' where ID ='" + id + "'";

			SQLEntryVisit update = new SQLEntryVisit();
			update.setSQLStatement(SQL);
			try {
				update.modifyEntries();
			} catch (ClassNotFoundException e) {
				System.out.println(e);
			}
			update.clear();
		}
		
		private static String savePatient(List<JTextField> allText) throws SQLException {
			SQLPatientLoader test = new SQLPatientLoader();
			
			// Gets and saves the current date
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			String Date = LocalDate.now().format(formatter);
			
			// Generate a random number of 
			String THC = String.format("%010d", new Random().nextInt(1000000000));
	
		
		  String FName = allText.get(0).getText(); 
		  String MName = allText.get(1).getText(); 
		  if(MName.equals("")) 
		  { 
			  MName = null; 
		  } 
		  String LName = allText.get(2).getText(); 
		  
		  // Get Date of Birth and reformat it appropriately
		  String DoB = allText.get(3).getText();
		  String[] splitOnDash = DoB.split("-");
		  String month = splitOnDash[0];
		  String day = splitOnDash[1];
		  String year = splitOnDash[2];
		  DoB = year + "-" + month + "-" + day;
		  
		  // Get gender
		  String Gender = allText.get(4).getText();
		  
		  // Get Phone Number
		  String PhoneNum = allText.get(5).getText();
		  
		  // Get Email
		  String Email = allText.get(6).getText();
		  if(Email.equals(""))
		  { 
			  Email = null; 
		  } 
		  
		  // Get Street
		  String Street = allText.get(7).getText();
		  
		  // Get City
		  String City = allText.get(8).getText();
		  
		  // Get State
		  String State = allText.get(9).getText();
		  if(State.equals("")) 
		  { 
			  State = null; 
		  } 
		  
		  // Get Zip
		  String Zip = allText.get(10).getText();
		  
		  // Get Country
		  String Country = allText.get(11).getText();
		  
		  // Get Photo
		  String Photo = allText.get(12).getText();
		  if(Photo.equals("")) 
		  { 
			  Photo = null; 
		  } 
		  
		  // Get SSN
		  String SSN = allText.get(12).getText();
		  if(SSN.equals("")) 
		  { 
			  SSN = null; 
		  }
		  
		  // Get Insurance
		  String Ins = allText.get(13).getText();
		  if(Ins.equals(""))
		  { 
			  Ins = null;
		  } 
		  
		// Create Patient SQL Insert String
		  String SQL = "insert into PATIENT values ('" + THC + "','" + Date + "','" +
		  FName + "','" + MName + "','" + LName + "','" + DoB + "','" + Gender
		  + "','" + PhoneNum + "','" + Email + "','" + Street + "','" + City + "','" +
		  State + "','" + Zip + "','" + Country + "','" + Photo + "','" + SSN + "','" +
		  Ins + "')"; 
			
		// Make SQL Entry
		
		  SQLEntryPatient add = new SQLEntryPatient(); 
		  add.setSQLStatement(SQL); 
		  try {
			  add.modifyEntries(); 
			  } 
		  catch (ClassNotFoundException e) {
				System.out.println(e);
			  } 
		  add.clear();
		  return THC;
		}
		
		private static void saveDemographics(String THC, List<JTextField> info, List<JTextField> Text, List<JTextArea> Area)
		{
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			String Date = LocalDate.now().format(formatter);
			
			String FName = info.get(0).getText(); 
			String MName = info.get(1).getText(); 
			if(MName.equals("")) 
			{ 
			  MName = null; 
			} 
			String LName = info.get(2).getText(); 
			String Occ = Text.get(0).getText();
			if(Occ.equals("")) 
			{ 
			  Occ = null; 
			} 
			String WS = Text.get(1).getText();
			if(WS.equals("")) 
			{ 
			  WS = null; 
			} 
			String ED = Text.get(2).getText();
			if(ED.equals("")) 
			{ 
			  ED = null; 
			} 
			String TO = Text.get(3).getText();
			if(TO.equals("")) 
			{ 
			  TO = null; 
			} 
			String TE = Text.get(4).getText();
			if(TE.equals("")) 
			{ 
			  TE = null; 
			} 
			String HO = Text.get(5).getText();
			if(HO.equals("")) 
			{ 
			  HO = null; 
			} 
			String HE = Text.get(6).getText();
			if(HE.equals("")) 
			{ 
			  HE = null; 
			} 
			String AC = Area.get(0).getText();
			if(AC.equals("")) 
			{
			  AC = null; 
			} 
			
			String SQL = "insert into DEMOGRAPHICS values ('" + THC + "','" + FName + "','" +
					  MName + "','" + LName + "','" + Date + "','" + Occ + "','" + WS
					  + "','" + TO + "','" + TE + "','" + HO + "','" + HE + "','" +
					  AC + "')"; 
			SQLEntryPatient add = new SQLEntryPatient(); 
			add.setSQLStatement(SQL); 
			  try {
				  add.modifyEntries(); 
				  } 
			  catch (ClassNotFoundException e) {
					System.out.println(e);
				  } 
			  add.clear();
					  
		}
		
		private static void deletePatient(int rowNumber)
		{
			SQLEntryPatient delete = new SQLEntryPatient();
			delete.setSQLStatement("delete from PATIENT where THC=" + rowNumber);
			try {
				delete.modifyEntries();
			} catch (ClassNotFoundException e) {
				System.out.println(e);
			}
			delete.clear();
		}

		private static void editPatient(Object[] data, JFrame frame)
		{
			frame.setTitle("View/Edit Visits");
			frame.getContentPane().removeAll();
			frame.setSize(900, 450);
			
			JPanel overall = new JPanel(new BorderLayout(10, 10));
			JPanel dataPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 20));
			JPanel buttons = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 20));
			List<JTextField> holder = new ArrayList<JTextField>();
			addTextField(dataPanel, holder, "First Name");
			holder.get(0).setText(data[2].toString());
			addTextField(dataPanel, holder, "Middle Name");
			holder.get(1).setText(data[4].toString());
			addTextField(dataPanel, holder, "Last Name");
			holder.get(2).setText(data[3].toString());
			addTextField(dataPanel, holder, "DOB");
			holder.get(3).setText(data[5].toString());
			addTextField(dataPanel, holder, "Gender");
			holder.get(4).setText(data[6].toString());
			addTextField(dataPanel, holder, "Phone");
			holder.get(5).setText(data[7].toString());
			addTextField(dataPanel, holder, "Email");
			holder.get(6).setText(data[8].toString());
			addTextField(dataPanel, holder, "Street");
			holder.get(7).setText(data[9].toString());
			addTextField(dataPanel, holder, "City");
			holder.get(8).setText(data[10].toString());
			addTextField(dataPanel, holder, "State");
			holder.get(9).setText(data[11].toString());
			addTextField(dataPanel, holder, "Zip");
			holder.get(10).setText(data[12].toString());
			addTextField(dataPanel, holder, "Country");
			holder.get(11).setText(data[13].toString());
			addTextField(dataPanel, holder, "SSN");
			holder.get(12).setText(data[15].toString());
			addTextField(dataPanel, holder, "Insurance");
			holder.get(13).setText(data[16].toString());
			
			JButton Save = new JButton("Save");
			Save.setPreferredSize(new Dimension(100, 30));
			Save.addActionListener(event -> {
				try {
					saveEditPatient(Integer.parseInt(data[0].toString()), holder);
					try {
						viewPatients(frame);
					} catch (ClassNotFoundException e) {
						System.out.println(e);
					}
				} catch (NumberFormatException e) {
					System.out.println(e);
				} catch (SQLException e) {
					System.out.println(e);
				}
			});
		
			buttons.add(Save);
			
			JButton Cancel = new JButton("Cancel");
			Cancel.setPreferredSize(new Dimension(100, 30));
			Cancel.addActionListener(event -> {
				try {
					viewPatients(frame);
				} catch (ClassNotFoundException e) {
					System.out.println(e);
				} catch (SQLException e) {
					System.out.println(e);
				}
			});
			buttons.add(Cancel);
			
			overall.add(dataPanel, BorderLayout.CENTER);
			overall.add(buttons, BorderLayout.SOUTH);
			frame.setContentPane(overall);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);
		}
		
		private static void saveEditPatient(int id, List<JTextField> text) throws SQLException {
			String FName = text.get(0).getText();
			String MName = text.get(1).getText();
			String LName = text.get(2).getText();
			String DOB = text.get(3).getText();
			String Gender = text.get(4).getText();
			String Phone = text.get(5).getText();
			String Email = text.get(6).getText();
			String Street = text.get(7).getText();
			String City = text.get(8).getText();
			String State = text.get(9).getText();
			String Zip = text.get(10).getText();
			String Country = text.get(11).getText();
			String SSN = text.get(12).getText();
			String Insurance = text.get(13).getText();
			String SQL = "update PATIENT set FIRST_NAME='" + FName + "', MIDDLE_NAME='"
					+ MName + "', LAST_NAME='" + LName + "', DATE_OF_BIRTH='" + DOB + "', GENDER='" + Gender +
					"', PHONE='" + Phone + "', EMAIL='" + Email + "', STREET='" + Street + "', CITY='" +
					City + "', STATE='" + State + "', ZIP='" + Zip + "', COUNTRY='" + Country + 
					"', SSN='" + SSN + "', INSURANCE='" + Insurance + "' where THC='" + id + "'";

			SQLEntryPatient update = new SQLEntryPatient();
			update.setSQLStatement(SQL);
			try {
				update.modifyEntries();
			} catch (ClassNotFoundException e) {
				System.out.println(e);
			}
			update.clear();
		}
}



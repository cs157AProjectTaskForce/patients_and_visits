import java.sql.*;
import java.util.List;
import java.util.ArrayList;

public class SQLEntryVisit {

	public static String USERNAME = new String("root");
    public static String PASSWORD = new String("SQLPacaw42!");
	public static String DB_URL = new String("jdbc:mysql://localhost:3306/test");
	public static String JDBC_DRIVER = new String("com.mysql.cj.jdbc.Driver");
	static List<String> InsertRows = new ArrayList<String>();
	
	public void setInsertRow(String statement) {
		InsertRows.add(statement);
	}
	
	public void clear() {
		InsertRows.clear();
	}
	
	public void addEntries() throws ClassNotFoundException {
		Class.forName(JDBC_DRIVER);
		try
		{
			Connection con = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
			
			Statement stmt = con.createStatement();
			
			for (int i = 0; i<InsertRows.size(); i++)
			{
				System.out.print(InsertRows.get(i) + "...");
				int rowsAffected = stmt.executeUpdate(InsertRows.get(i));
				if(rowsAffected == 1)
					System.out.println("OK");
			}
			
			stmt.close();
			con.close();
			clear();
		}
		catch (SQLException e)
		{
			System.out.println(e);
		}
	}
}
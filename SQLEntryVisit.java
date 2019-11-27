import java.sql.*;
import java.util.List;
import java.util.ArrayList;

public class SQLEntryVisit {

	public static String USERNAME = new String("root");
    public static String PASSWORD = new String("SQLPacaw42!");
	public static String DB_URL = new String("jdbc:mysql://localhost:3306/test");
	public static String JDBC_DRIVER = new String("com.mysql.cj.jdbc.Driver");
	static List<String> SQLStatement = new ArrayList<String>();
	
	public void setSQLStatement(String statement) {
		SQLStatement.add(statement);
	}
	
	public void clear() {
		SQLStatement.clear();
	}
	
	public void modifyEntries() throws ClassNotFoundException {
		Class.forName(JDBC_DRIVER);
		try
		{
			Connection con = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
			
			Statement stmt = con.createStatement();
			
			for (int i = 0; i<SQLStatement.size(); i++)
			{
				System.out.print(SQLStatement.get(i) + "...");
				int rowsAffected = stmt.executeUpdate(SQLStatement.get(i));
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
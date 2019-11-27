import java.sql.*;

public class SQLVisitLoader {

	public static String USERNAME = new String("root");
    public static String PASSWORD = new String("SQLPacaw42!");
	public static String DB_URL = new String("jdbc:mysql://localhost:3306/test");
	public static String JDBC_DRIVER = new String("com.mysql.cj.jdbc.Driver");
	
	public ResultSet getVisit() throws ClassNotFoundException {
		Class.forName(JDBC_DRIVER);

		try
		{
		          // Get a connection from the connection factory
			Connection con = DriverManager.getConnection(
			DB_URL,
			  //"jdbc:oracle:thin:@dbaprod1:1521:SHR1_PRD",
			USERNAME, PASSWORD);

			// Create a Statement object so we can submit SQL statements to the driver
			Statement stmt = con.createStatement();

			// Submit a query, creating a ResultSet object
			ResultSet rs = stmt.executeQuery("SELECT * FROM VISIT");			
			return rs;
		}
		catch (SQLException e)
		{
          	 	SQLUtil.printSQLExceptions(e);	
          	 	return null;
        }
	}	
}

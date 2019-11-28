import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLPatientLoader {
    public static String USERNAME = new String("USER");
    public static String PASSWORD = new String("PASSWORD");
    public static String DB_URL = new String("jdbc:mysql://localhost:3306/DB_NAME_REPLACE");
    public static String JDBC_DRIVER = new String("com.mysql.jdbc.Driver");
	
	public ResultSet getPatient() throws ClassNotFoundException {
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
			ResultSet rs = stmt.executeQuery("SELECT * FROM PATIENT");			
			return rs;
		}
		catch (SQLException e)
		{
          	 	System.out.println(e);	
          	 	return null;
        }
	}	
	
	public int getPatientID() throws ClassNotFoundException {
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
			ResultSet rs = stmt.executeQuery("SELECT * FROM PATIENT");			
			rs.last();
			int last = rs.getInt(1);
			System.out.println(last);
			
			rs.close();
			stmt.close();
			con.close();
			return (last + 1);
		}
		catch (SQLException e)
		{
				System.out.println(e);	
          	 	return -1;
        }
	}
}
import java.sql.*;

public class SQLVisitLoader {

    public static String USERNAME = new String("USER"); //replace user with local username
    public static String PASSWORD = new String("PASSWORD"); //replace password with local user's password
    public static String DB_URL = new String("jdbc:mysql://localhost:3306/DB_NAME_REPLACE"); //replace DB_NAME_REPLACE to the database you are using.
    public static String JDBC_DRIVER = new String("com.mysql.jdbc.Driver"); //this is the SQL driver. don't need to replace.
	
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
          	 	System.out.println(e);	
          	 	return null;
        }
	}	
	
	public int getVisitID() throws ClassNotFoundException {
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
	
	public int getVisitSeq(String THC) throws ClassNotFoundException {
		Class.forName(JDBC_DRIVER);

		try
		{
		          // Get a connection from the connection factory
			Connection con = DriverManager.getConnection(
			DB_URL,
			  //"jdbc:oracle:thin:@dbaprod1:1521:SHR1_PRD",
			USERNAME, PASSWORD);

			// Create a Statement object so we can submit SQL statements to the driver
			Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_READ_ONLY);

			// Submit a query, creating a ResultSet object
			ResultSet rs = stmt.executeQuery("SELECT * FROM VISIT WHERE THC =" + THC);	
			rs.last();
			int last = rs.getRow();
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

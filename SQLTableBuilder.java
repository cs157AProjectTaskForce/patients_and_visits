import java.sql.*;

public class SQLTableBuilder
{
    public static String USERNAME = new String("user");
    public static String PASSWORD = new String("pass");
    public static String DB_URL = new String("jdbc:mysql://localhost:3306/test");
    public static String JDBC_DRIVER = new String("com.mysql.jdbc.Driver");
    // SQL Statements to create tables, formatted as strings.
    private static String[]	Tables = {
            "create table PATIENT (" +
                    "FIRST_NAME varchar(32) not null, " +
                    "MIDDLE_NAME varchar(32), " +
                    "LAST_NAME varchar(32) not null, " +
                    "DATE_OF_BIRTH date not null, " +
                    "GENDER char(1) not null, " +
                    "PHONE varchar(15) not null, " +
                    "EMAIL varchar(40), " +
                    "STREET varchar(32) not null, " +
                    "CITY varchar(32) not null, " +
                    "STATE char(2), " +
                    "ZIP int(10) not null, " +
                    "COUNTRY varchar(20) not null, " +
                    "PHOTO blob(100), " +
                    "SSN int(9), " +
                    "INSURANCE varchar(30))"
    };
    public static void main(String[] args) throws ClassNotFoundException
    {

        // Load the Driver
        Class.forName(JDBC_DRIVER);

        try
        {
            // Get a connection from the connection factory
            Connection con = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

            // Print drvier information
            SQLHelper.printDriverInfo(con);

            // Create a Statement object so we can submit SQL statements to the driver
            Statement stmt = con.createStatement();

            // Submit the statement
            for (int i=0; i<Tables.length; ++i)
            {
                System.out.print(Tables[i] + "...");
                int rowsAffected = stmt.executeUpdate(Tables[i]);
                if (rowsAffected == 0)	// DDL statements return rowcount of 0
                    System.out.println("OK");
            }

            // Close the statement
            stmt.close();

            // Close the connection
            con.close();
        }
        catch (SQLException e) {
            SQLHelper.printSQLExceptions(e);
        }
    }
}

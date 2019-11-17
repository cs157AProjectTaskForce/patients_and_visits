import java.sql.*;

public class SQLBuilder
{
    public static String USERNAME = new String("username");
    public static String PASSWORD = new String("passwd");
    public static String DB_URL = new String("jdbc:mysql://localhost:3306/DB_NAME_REPLACE");
    public static String JDBC_DRIVER = new String("com.mysql.jdbc.Driver");
    // SQL Statements to create tables, formatted as strings.
    private static String[]	Tables = {
            "create table STATE (" +
                    "ABBREVIATION char(2) not null, " +
                    "NAME varchar(32) not null, " +
                    "ENTERED_UNION date null, " +
                    "CAPITAL varchar(32) not null, " +
                    "REGION varchar(16) not null, " +
                    "AREA int not null, " +
                    "FLOWER varchar(32) null, " +
                    "BIRD varchar(32) null)"
    };

    public static void main(String[] args) throws ClassNotFoundException
    {

        // Load the Driver
        Class.forName(JDBC_DRIVER);

        try
        {
            // Get a connection from the connection factory
            Connection con = DriverManager.getConnection, DB_URL, USERNAME, PASSWORD);

            // Print drvier information
            SQLUtil.printDriverInfo(con);

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
            SQLUtil.printSQLExceptions(e);
        }
    }
}

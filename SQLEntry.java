import java.sql.*;

public class SQLEntry
{
    public static String USERNAME = new String("USER");
    public static String PASSWORD = new String("PASSWORD");
    public static String DB_URL = new String("jdbc:mysql://localhost:3306/DB_NAME_REPLACE");
    public static String JDBC_DRIVER = new String("com.mysql.jdbc.Driver");

    public static void
    main(String[] args) throws ClassNotFoundException
    {

        // Load the Driver
        Class.forName(JDBC_DRIVER);

        try
        {
            // Get a connection from the connection factory
            Connection con = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

            // Print driver metadata
            SQLHelper.printDriverInfo(con);

            // Create a Statement object so we can submit SQL statements to the driver
            Statement stmt = con.createStatement();

            // Submit the statement
            for (int i=0; i<InsertRows.length; ++i)
            {
                System.out.print(InsertRows[i] + "...");
                int rowsAffected = stmt.executeUpdate(InsertRows[i]);
                if (rowsAffected == 1)
                    System.out.println("OK");
            }

            // Close the statement
            stmt.close();

            // Close the connection
            con.close();
        }
        catch (SQLException e)
        {
            SQLHelper.printSQLExceptions(e);
        }
    }

    // Entries that will be inserted into the tables
    // ex: "insert into TABLE_NAME values (ATTRIBUTES_VALUES...)"
    static String[]	InsertRows = {

    };
}

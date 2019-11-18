import java.sql.*;

public class SQLUpdater
{
    public static String USERNAME = new String("User");
    public static String PASSWORD = new String("pass");
    public static String DB_URL = new String("jdbc:mysql://localhost:3306/DB_NAME_REPLACE");
    public static String JDBC_DRIVER = new String("com.mysql.jdbc.Driver");

    public static void main(String[] args) throws ClassNotFoundException {
        Connection con = null;

        // Load drivers
        Class.forName(JDBC_DRIVER);

        try
        {
            // Get a connection from the connection factory
            con = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

            // Print driver metadata
            SQLHelper.printDriverInfo(con);

            // Create a Prepared Statement object so we can submit DML to the driver
            Statement stmt = con.createStatement();

            // Turn off auto update so updates are in batches
            con.setAutoCommit(false);

            // Submit the statement
            for (int i=0; i<UpdateRows.length; ++i)
            {
                System.out.println(UpdateRows[i] + "...");
                stmt.addBatch(UpdateRows[i]);
            }

            System.out.println();

            int[] rc = stmt.executeBatch();
            int	rowsAffected = 0;
            for (int i=0; i<rc.length; ++i)
                rowsAffected+=rc[i];

            // Commit the work
            con.commit();
            con.setAutoCommit(true);

            System.out.println("OK -- " + rowsAffected + " rows affected");

            // Close the statement
            stmt.close();
        }
        catch (SQLException e)
        {
            SQLHelper.printSQLExceptions(e);
            try
            {
                System.err.println("Update failed.");
                con.rollback();
            }
            catch (SQLException e2)
            {
                SQLHelper.printSQLExceptions(e2);
            }
        }
        finally
        {
            try
            {
                // Close the connection
                if (con != null)
                    con.close();
            }
            catch (SQLException e2)
            {
                SQLHelper.printSQLExceptions(e2);
            }
        }
    }

    // Updates rows using SQL syntax
    // ex: "update TABLE_NAME set ATTRIBUTE_NAME = XXX where ATTRIBUTE_NAME2 = YYY
    static String[]	UpdateRows = {

    };
}

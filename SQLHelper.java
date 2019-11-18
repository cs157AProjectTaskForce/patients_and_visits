import java.sql.*;

public class SQLHelper
{
    /**
     * This method displays all columns and rows in the given result set.
     * @param rs  The result set to be displayed.
     * @return  None.
     * @exception SQLException
     */

    static public void displayResultSet(ResultSet rs) throws SQLException {
        int i;

        // Get the ResultSetMetaData.  This will be used for the column headings
        ResultSetMetaData rsmd = rs.getMetaData();

        // Get the number of columns in the result set
        int numCols = rsmd.getColumnCount();

        // Display column headings
        for (i = 1; i <= numCols; i++)
        {
            if (i > 1) System.out.print(",");
            System.out.print(rsmd.getColumnLabel(i));
        }
        System.out.println("\n-------------------------------------");

        // Display data, fetching until end of the result set
        while (rs.next()) {
            // Loop through each column, print column data
            for (i = 1; i <= numCols; i++)
            {
                if (i > 1) System.out.print(",");
                System.out.print(rs.getString(i));
            }
            System.out.println("");
        }
    }

    /**
     * This method checks for warnings and displays the warnings' information.
     * Note that multiple warning objects could be chained together.
     * Very similar to printSQLExceptions.
     * @param warn The list of SQLWarnings.
     * @return true if a warning existed, false otherwise.
     * @exception  SQLException
     * @see /Utils#printSQLExceptions
     * @exception SQLException
     */
    static public boolean printSQLWarnings(SQLWarning warn) throws SQLException {
        boolean rc = false;

        if (warn != null)
        {
            System.out.println("\n *** Warning ***\n");
            rc = true;
            while (warn != null)
            {
                System.out.println("SQLState: " + warn.getSQLState());
                System.out.println("Message:  " + warn.getMessage());
                System.out.println("Vendor:   " + warn.getErrorCode());
                System.out.println("");
                warn = warn.getNextWarning();
            }
        }
        return rc;
    }

    /**
     * This method checks for exceptions and displays error information.
     * Note that multiple exception objects could be chained together.
     * Very similar to printSQLWarnings.
     * @param /warn The list of SQLException.
     * @return true if an exception existed, false otherwise.
     * @see /Utils#printSQLWarnings
     */
    static public boolean printSQLExceptions(SQLException ex) {
        boolean rc = false;

        if (ex != null)
        {
            System.out.println("\n*** SQLException caught ***\n");
            rc = true;
            while (ex != null)
            {
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("Message:  " + ex.getMessage());
                System.out.println("Vendor:   " + ex.getErrorCode());
                System.out.println("");
                ex = ex.getNextException();
            }
        }
        return rc;
    }

    static public boolean printSQLExceptions(String method, SQLException ex) {
        boolean rc = false;

        if (ex != null)
        {
            System.out.println("\n*** SQLException caught in " + method + " ***\n");
            rc = true;
            while (ex != null)
            {
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("Message:  " + ex.getMessage());
                System.out.println("Vendor:   " + ex.getErrorCode());
                System.out.println("");
                ex = ex.getNextException();
            }
        }
        return rc;
    }

    /**
     * This method displays driver name and version.
     * @param con The current connection.
     * @return None.
     * @exception SQLException
     */
    static public void printDriverInfo(Connection con) throws SQLException {
        // Get the DatabaseMetaData object and display connection information
        DatabaseMetaData dma = con.getMetaData();

        System.out.println("Database\t" + dma.getDatabaseProductVersion());
        System.out.println("Driver\t\t" + dma.getDriverVersion());
        System.out.println("URL\t\t" + dma.getURL() + ", user '" + dma.getUserName() + "'");
        System.out.println("-------------------------------------");
    }

}


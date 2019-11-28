import java.sql.*;

class TableViewer
{
    public static void main (String args [])
            throws SQLException
    {
        // Load the JDBC driver
        DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());

        // Connect to the database
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/YOUR_DB_NAME","USER", "PASS");

        // Create a Statement
        Statement stmt = conn.createStatement();

        // Select the table names from the user_tables
        ResultSet rset = stmt.executeQuery ("show tables");

        // Iterate through the result and print out the table names
        while (rset.next ())
            System.out.println (rset.getString (1));
    }
}
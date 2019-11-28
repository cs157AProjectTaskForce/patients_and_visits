import java.io.File;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.Scanner;

public class SQLUpdater
{
    public static String USERNAME = new String("root");
    public static String PASSWORD = new String("tader123");
    public static String DB_URL = new String("jdbc:mysql://localhost:3306/test");
    public static String JDBC_DRIVER = new String("com.mysql.cj.jdbc.Driver");

    public static void main(String[] args) throws ClassNotFoundException {
        Connection con = null;

        // Load drivers
        Class.forName(JDBC_DRIVER);
        String line = "";
        String queryString = "";
        String queries[] = new String[3];
        int count = 0;
        try {
			Scanner scanner = new Scanner(new File("sql_queries.txt"));
			while (scanner.hasNextLine()) {
				line = scanner.nextLine();
				boolean insertFound = line.indexOf("INSERT") !=-1? true: false;
				
				if (insertFound)
				{
					if (!queryString.equals(""))
					{
						queries[count++] = queryString;
						queryString = line;
					}
				}
				else
				{
					boolean poundFound = line.indexOf("#") !=-1? true: false;
					//System.out.println("Adding to string");
					if (!poundFound)
					{
						queryString += line;
					}
					
				}
			}
			queries[0] = "INSERT INTO STATE (NAME) VALUES " + queries[0];
			System.out.println(queries[0]);
			System.out.println(queries[1]);
			queries[count] = queryString;
			
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
        
        
        
        
        
        
        
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
            for (int i=0; i<queries.length; ++i)
            {
            	System.out.println(i);
            	if (i < 2)
            	{
            		System.out.println(queries[i] + "...");
            	}
                stmt.addBatch(queries[i]);
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
                // Close the connection.
                if (con != null)
                    con.close();
            }
            catch (SQLException e2)
            {
                SQLHelper.printSQLExceptions(e2);
            }
        }
    }
}

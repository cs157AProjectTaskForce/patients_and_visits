import java.sql.*;

public class SQLTableBuilder
{
    public static String USERNAME = new String("USER");
    public static String PASSWORD = new String("PASSWORD");
    public static String DB_URL = new String("jdbc:mysql://localhost:3306/DB_NAME_REPLACE");
    public static String JDBC_DRIVER = new String("com.mysql.jdbc.Driver");
    // SQL Statements to create tables, formatted as strings.
    private static String[]	Tables = {

            "create table Country (" +
                    "NAME varchar(20) not null PRIMARY KEY)"
            ,

            // zipcodes are unique
            "create table Zip (" +
                    "ID int(15) not null PRIMARY KEY)"

            ,

            "create table State (" +
                    "NAME char(2) not null PRIMARY KEY)"
            ,

            "create table Work_Status (" +
                    "EMPLOYED char(3) not null PRIMARY KEY)"
            ,

            "create table Occupation (" +
                    "OCCUPATION varchar(20) not null PRIMARY KEY, " +
                    "DEGREE varchar(20))"
            ,

            "create table Patient (" +
                    "THC int(15) not null PRIMARY KEY," +
                    "DATE date not null, " +
                    "FIRST_NAME varchar(32) not null, " +
                    "MIDDLE_NAME varchar(32), " +
                    "LAST_NAME varchar(32) not null, " +
                    "DATE_OF_BIRTH date not null, " +
                    "GENDER char(1) not null, " +
                    "PHONE varchar(15) not null, " +
                    "EMAIL varchar(40), " +
                    "STREET varchar(32) not null, " +
                    /* foreign key to zip, state, and country */
                    "CITY varchar(32) not null, " +
                    "STATE char(2), " +
                    "ZIP int(10) not null, " +
                    "COUNTRY varchar(20) not null, " +
                    /*****************************/
                    "PHOTO blob(100), " +
                    "SSN int(9), " +
                    "INSURANCE varchar(30)," +
                    "CONSTRAINT p_zip FOREIGN KEY zip (ZIP) REFERENCES ZIP(ID), " +
                    //"CONSTRAINT p_city FOREIGN KEY city (CITY) REFERENCES Zip(CITY), " +
                    "CONSTRAINT p_state FOREIGN KEY state (STATE) REFERENCES State(NAME), " +
                    "CONSTRAINT p_cty FOREIGN KEY country (COUNTRY) REFERENCES Country(NAME))"
            ,

            "create table Visit (" +
                    "ID int(15) not null PRIMARY KEY, " +
                    "VISIT_DATE date not null, " +
                    "THC int(15) not null, " +
                    "FIRST_NAME varchar(32) not null, " +
                    "MIDDLE_NAME varchar(32), " +
                    "LAST_NAME varchar(32) not null, " +
                    "VISIT_NUMBER int(5), " +
                    "CATEGORY int(1) not null, " +
                    "PROTOCOL int(1) not null, " +
                    "INSTRUMENT char(8) not null, " +
                    "REM char(3) not null, " +
                    "FU varchar(20), " +
                    "COMMENTS varchar(150), " +
                    "NEXT_VISIT date not null)"

            ,

            "create table Demographics (" +
                    "THC int(15) not null PRIMARY KEY, " +
                    "FIRST_NAME varchar(32) not null, " +
                    "MIDDLE_NAME varchar(32), " +
                    "LAST_NAME varchar(32) not null, " +
                    "DATE date not null, " +
                    "OCCUPATION varchar(20), " +
                    "WORK_STATUS char(3)," +
                    "TINNITUS_ONSET char(3), " +
                    "TINNITUS_ETIOLOGY char(3), " +
                    "HYPERACUSIS_ONSET char(3)," +
                    "HYPERACUSIS_ETIOLOGY char(3), " +
                    "ADDITIONAL_COMMENTS varchar(150), " +
                    "CONSTRAINT p_occupation FOREIGN KEY (OCCUPATION) REFERENCES Occupation(OCCUPATION), " +
                    "CONSTRAINT p_employed FOREIGN KEY (WORK_STATUS) REFERENCES Work_Status(EMPLOYED))"

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

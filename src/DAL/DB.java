package DAL;

import java.sql.*;

/*
Main driver class for each database.
Database classes inherit from this parent class in order to make connections to database
 */
public class DB {
    private static final String url = "jdbc:sqlite:database.db";

    protected static final Connection conn = connect();

    /*
    Creates a table in a database corresponding to the tableName String
     */
    public static void createTable(String tableName){

        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS " + tableName + " (\n"
                + "	id integer PRIMARY KEY,\n"
                + "	recLetter text NOT NULL\n,"
                + "	studentID integer NOT NULL,\n"
                + "	FOREIGN KEY(studentID) REFERENCES students(id)\n"
                + ");";
        try{
            Connection conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
    /*
    Drops a table in a database corresponding to the tableName String and the name of the column to be dropped
     */
    public static void alterTable(String tableName, String columnName){
    	String sql = "ALTER TABLE " + tableName + " DROP COLUMN " + columnName;
    	try{
            Connection conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    /*
    Adds a column to a table in a database corresponding to the tableName String,
    the name of the column to be added, and the type of the column
     */
    public static void addColumn(String tableName, String columnName, String type){
    	String sql = "ALTER TABLE " + tableName + " ADD COLUMN " + columnName + " " + type;
    	try{
            Connection conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    /*
    Drops a table in a database corresponding to the tableName String
     */
    public static void deleteTable(String tableName){
        String sql = "DROP TABLE " + tableName;
        try{
            Connection conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    /*
    Connects to the database
     */
    protected static Connection connect() {
        Connection conn = null;
        try {
        	Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(url);
        } catch (SQLException | ClassNotFoundException e) {
        	System.out.print("Huh??  ");
            System.out.println(e.getMessage());
        }
        return conn;
    }
}

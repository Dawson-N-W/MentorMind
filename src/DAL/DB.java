package DAL;

import java.sql.*;


public class DB {
    private static final String url = "jdbc:sqlite:database.db";

    protected static final Connection conn = connect();
    //data access layer
    //access to db
    //create, read, update, delete

    public static void createTable(String tableName){

        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS " + tableName + " (\n"
                + "	id integer PRIMARY KEY,\n"
                + "	courses text NOT NULL,\n"
                + "	studentID integer NOT NULL,\n"
                + " FOREIGN KEY (studentID) REFERENCES students(id)\n"
                + ");";
        try{
            Connection conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

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

    //public abstract void insert(String password);
    //public abstract void delete(int id);
}

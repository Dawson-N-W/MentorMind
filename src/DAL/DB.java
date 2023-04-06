package DAL;

import java.sql.*;


public abstract class DB {
    private static final String url = "jdbc:sqlite:database.db";

    protected static final Connection conn = connect();
    //data access layer
    //access to db
    //create, read, update, delete

    public static void createNewDatabase(String fileName) { //use once to create databases

        String url = "jdbc:sqlite:src/DAL/" + fileName;

        try {
            Connection conn = DriverManager.getConnection(url);
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created.");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void createTable(String tableName){

        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS " + tableName + " (\n"
                + "	id integer PRIMARY KEY,\n"
                + "	password text NOT NULL,\n"
                + " UNIQUE(password)\n"
                + ");";
        try{
            Connection conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public void deleteTable(String tableName){
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

    public abstract void insert(String password);
    public abstract void delete(int id);
}

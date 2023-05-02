package DAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
    * This class is used to access the login table in the database
    * It contains methods to insert, delete, and update the login table
    * It also contains a method to check if the password entered by the user matches the password in the database
    * It is a singleton class
    * It extends the DB class
    * It is used by the Login class
    * It is used in the InitialLoginController class
    * It is used in the SignUpController class
    * It is used in the ResetPasswordController class
 */
public class Login_Database extends DB {
    private final String tableName = "login";
    private final Connection conn;
    private PreparedStatement pstmt;

    //Singleton pattern
    private final static Login_Database login_database = new Login_Database();

    public static Login_Database getLogin_database() {
        return login_database;
    }

    private Login_Database() {
    	conn = DB.conn;

    }

    /**
        * Inserts a password into the login table
     * @param password the password to be inserted
     */
    public void insert(String password) {
        String sql = "INSERT INTO login(password) VALUES(?)";
        try{
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, password);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
        * Deletes a password from the login table
     * @param id the id of the password to be deleted
     */
    public void delete(int id) {
        String sql = "DELETE FROM login WHERE id = ?";
        try{
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    /**
        * Checks if the password entered by the user matches the password in the database
     * @param pass the password entered by the user
     */
    public boolean isLogin(String pass) throws Exception{
    	
    	PreparedStatement pr = null;
    	ResultSet rs = null;
    	
    	String sql = "SELECT * FROM login where password = ?";
    	
    	try {
    		pr = conn.prepareStatement(sql);
    		pr.setString(1, pass);
    		
    		rs = pr.executeQuery();
    		
    		return rs.next();
    	}
    	catch(SQLException ex) {
    		System.out.println("Something went wrong whilst attempting to authenticate password");
    		return false;
    	}
    	
    }

    /**
        * Updates a password in the login table
        * The old password is replaced with the new password
     * @param newPassword the new password
     * @param oldP the old password
     */
    public void updatePassword(String newPassword, String oldP){
        String sql = "UPDATE login SET password = '" + newPassword + "' WHERE password = '" + oldP + "'";
        try{
            pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    

    /**
     * Checks if there exists data in the password column of the login table
     * returns true if there is, else false.
     */
    public boolean isEmpty() {
    	String sql = "SELECT COUNT(password) AS total FROM login";
    	int count = 0;
    	
      	try {
			Statement pr = conn.createStatement();
			ResultSet rs = pr.executeQuery(sql);
			
			while(rs.next()) {
				count = rs.getInt("total");
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
    	if(count == 0) {
    		return true;
    	}
    	return false;
    }
}


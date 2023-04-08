package DAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



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

    //insert method
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

    //delete account
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
    //returns -1 if login failed, otherwise returns id
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

    //change password
    public void updatePassword(String newPassword, String oldP){
        String sql = "UPDATE login SET password = '" + newPassword + "' WHERE password = '" + oldP + "'";
        try{
            pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    

    /*
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


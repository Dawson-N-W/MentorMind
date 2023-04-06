package DAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


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

    @Override
    public void insert(String username, String password) {
        String sql = "INSERT INTO login(username, password) VALUES(?,?)";
        try{
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
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
    public int login(String username, String password){
    String sql = "SELECT * FROM login WHERE username = ? AND password = ?";
        try{
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            } else {
                return -1;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return -1;
        }
    }

    public void updatePassword(int id, String newPassword){
        String sql = "UPDATE login SET password = ? WHERE id = ?";
        try{
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, newPassword);
            pstmt.setInt(2, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}


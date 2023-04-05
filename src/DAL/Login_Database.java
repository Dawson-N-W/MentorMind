package DAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

public class Login_Database extends DB {

    @Override
    public void insert(String username, String password) {
        Random random = new Random();
        final int id = username.length() + password.length() + random.nextInt(100);
        String sql = "INSERT INTO login(username, password, id) VALUES(?,?,?)";
        try{
            Connection conn = DB.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.setInt(3, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}


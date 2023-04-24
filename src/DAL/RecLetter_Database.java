package DAL;
import Members.RecLetter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class RecLetter_Database extends DB{
    private final String tableName = "RecLetters";
    private final Connection conn;
    private PreparedStatement pstmt;

    //Singleton pattern
    private final static RecLetter_Database recLetter_database = new RecLetter_Database();

    public static RecLetter_Database getRecLetter_database() {
        return recLetter_database;
    }

    private RecLetter_Database() {
        conn = DB.conn;
    }

    public void addLetter(RecLetter letter, int studentID) {
        String letterText = letter.getText();
    	String sql = "INSERT INTO RecLetters(letter, studentID) VALUES(?,?)";
        try{
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, letterText);
            pstmt.setInt(2, studentID);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteLetter(int studentID) {
        String sql = "DELETE FROM RecLetters WHERE studentID = ?";
        try{
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, studentID);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void replaceLetter(RecLetter letter, int studentID) {
    	String letterText = letter.getText();
    	String sql = "UPDATE RecLetters SET letter = ? WHERE studentID = ?";
    	try {
    		pstmt = conn.prepareStatement(sql);
    		pstmt.setString(1, letterText);
    		pstmt.setInt(2, studentID);
    		pstmt.executeUpdate();
    	} catch (SQLException e) {
    		System.out.println(e.getMessage());
    	}
    }

    public String getLetter(int studentID) {
    	String sql = "SELECT letter FROM RecLetters WHERE studentID = ?";
    	try {
    		pstmt = conn.prepareStatement(sql);
    		pstmt.setInt(1, studentID);
    		ResultSet rs = pstmt.executeQuery();
    		return rs.getString("recLetter");
    	} catch (SQLException e) {
    		System.out.println(e.getMessage());
    	}
    	return null;
    }
}

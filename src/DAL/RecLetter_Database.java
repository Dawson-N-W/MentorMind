package DAL;
import Members.RecLetter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

/**
 * This class is used to access the RecLetters table in the database
 * It contains methods to insert, delete, and update the RecLetters table
 * It is a singleton class
 * It extends the DB class
 * It is used by the RecLetter class
 * It is used in the RecLetterController class
 */
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

    /**
     * adds the provided letter to the database for the student possessing the provided ID
     * @param letter
     * @param studentID
     */
    public void addLetter(RecLetter letter, int studentID) {
        String letterText = letter.getText();
    	String sql = "INSERT INTO RecLetters(recLetter, studentID) VALUES(?,?)";
        try{
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, letterText);
            pstmt.setInt(2, studentID);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * deletes the letter currently stored in the database for the student possessing the provided ID
     * @param studentID
     */
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

    /**
     * replaces the letter currently stored in the database with the provided letter
     * @param letter
     * @param studentID
     */
    public void replaceLetter(RecLetter letter, int studentID) {
    	String letterText = letter.getText();
    	String sql = "UPDATE RecLetters SET recLetter = ? WHERE studentID = ?";
    	try {
    		pstmt = conn.prepareStatement(sql);
    		pstmt.setString(1, letterText);
    		pstmt.setInt(2, studentID);
    		pstmt.executeUpdate();
    	} catch (SQLException e) {
    		System.out.println(e.getMessage());
    	}
    }
    
    /**
     * checks that the student possessing the provided ID has letter currently stored
     * in the database
     * @param studentID
     * @return
     */
    public boolean checkLetter(int studentID) {
    	int count = 0;
    	String sql = "SELECT COUNT(*) FROM RecLetters WHERE studentID = ?";
    	try {
    		pstmt = conn.prepareStatement(sql);
    		pstmt.setInt(1, studentID);
    		ResultSet rs = pstmt.executeQuery();
    		rs.next();
    		count = rs.getInt(1);
    	} catch (SQLException e) {
    		System.out.println(e.getMessage());
    	}
    	
    	if(count > 0) {
    		return true;
    	}
    	return false;
    }

    /**
     * returns the letter currently stored in the database for the student possessing the provided ID
     * @param studentID
     * @return letter
     */
    public String getLetter(int studentID) {
    	String sql = "SELECT recLetter FROM RecLetters WHERE studentID = ?";
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

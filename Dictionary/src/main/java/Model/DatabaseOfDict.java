package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class DatabaseOfDict {
    private static String DB_URL = "jdbc:sqlite:.\\Dictionary/data/dict_hh.db";


    /**
     * @param dbURL: the path to the database file.
     * @return the connection.
     */
    public static Connection getConnection(String dbURL) {
        Connection conn = null;
        try {
//            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(dbURL);
            System.out.println("connect successfully!");
        } catch (Exception ex) {
            System.out.println("connect failure!");
            ex.printStackTrace();
        }
        return conn;
    }

    /**
     * get max id(primary key) of table.
     * @return max id.
     */
    public static long getMaxId() {
        long lastID = 0;
        try {
            Connection conn = getConnection(DB_URL);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select max(id) from av");
            if (rs.next()) {
                lastID = rs.getInt(1);
            }
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return lastID;
    }

    /**
     * find the word in database.
     * @param querry SQL language.
     * @return the information of word.
     */
    public String getInfoWord(String querry) {
        String tmp = "";
        try {
            Connection conn = getConnection(DB_URL);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(querry);
            while (rs.next()) {
                tmp += rs.getString("id") + "\n"
                        + rs.getString("word") + "\n"
                        + rs.getString("html") + "\n"
                        + rs.getString("description") + "\n"
                        + rs.getString("pronounce") + "\n";
            }
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if(tmp.length() !=0) return tmp;
        return "Not Found";
    }

    public String remove(String word,String language) {
        String tmp = this.getInfoWord(word);
        if (tmp.length() != 0) {
            String querry = String.format("DELETE FROM %s WHERE word = %s",language,word);
            try {
                Connection conn = getConnection(DB_URL);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(querry);
                conn.close();
                return tmp;
            } catch (Exception ex) {
                ex.printStackTrace();
                return("Not Found");
            }
        }
        else{
            return("Not Found");
        }
    }

    /**
     * add new word to database.
     * @param language: has 2 values: av(English to Vietnamese), va(Vietnamese to English)
     * @param wordTarget word.
     * @param wordExplain meaning.
     * @param pronounce pronounce.
     * @param html html code.
     * @return true if success, otherwise false.
     */
    public boolean addWord(String language,String wordTarget, String wordExplain, String pronounce, String html) {
        String querry = String.format("INSERT INTO %s(ID,WORD,HTML,DESCRIPTION,PRONOUNCE) VALUES(%d,'%s','%s','%s','%s');"
                ,language,getMaxId()+1,wordTarget,html, wordExplain,pronounce);
        try {
            Connection conn = getConnection(DB_URL);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(querry);
            conn.close();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

}


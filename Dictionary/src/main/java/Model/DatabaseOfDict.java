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
    private static String DB_URL = "jdbc:sqlite:D:/java_code/Soft_Flame/Dictionary/data/dict_hh.db";


    /**
     * @param dbURL
     * @return
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
        return tmp;
    }

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

    public static void main(String[] args) {
        DatabaseOfDict tmp = new DatabaseOfDict();
//        System.out.println(tmp.addWord("ads","a d s", "",""));
        System.out.println(tmp.getInfoWord("ads"));
    }

}


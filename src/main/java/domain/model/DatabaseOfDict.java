package domain.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
//import https://github.com/diepxuanlinhvp88/Soft_Flame/pull/18/conflict?name=Dictionary%252Fsrc%252Fmain%252Fjava%252FModel%252FDatabaseOfDict.java&ancestor_oid=8e09a17c912135e1dbee4a449dfe5322765ada42&base_oid=52c1094eaff3fe565e5ac292b045caa613019055&head_oid=95f2c257bc5427832a074aad9bf47d8e7af6b5fbjava.sql.SQLException;


public class DatabaseOfDict {

    private static String DB_URL = "jdbc:sqlite:.\\/data/dict_hh.db";



    /**
     * @param dbURL: the path to the database file.
     * @return the connection.
     */
    public static Connection getConnection(String dbURL) {
        Connection conn = null;
        try {
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
                lastID = rs.getLong(1);
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
    public String getHtmlWord(String querry) {
        String tmp = "";
        try {
            Connection conn = getConnection(DB_URL);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(querry);
            while (rs.next()) {
                tmp +=  rs.getString("html") + "\n";

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
            String querry = String.format("DELETE FROM %s WHERE word = '%s';",language,word);
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
    public boolean addBookMard(long id,String word, String meaning, String dateTime){
        String tmp = String.format("insert into bookMark values (%d,'%s','%s','%s')",id,word,meaning,dateTime);
        try {
            Connection conn = getConnection(DB_URL);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(tmp);
            conn.close();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }

    }
    public static void main(String[] args){
        DatabaseOfDict tmp = new DatabaseOfDict();
        System.out.println(tmp.getInfoWord("SELECT * FROM av WHERE word  = 'acc';"));
    }

}


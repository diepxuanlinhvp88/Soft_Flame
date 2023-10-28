package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DatabaseOfDict {
    private static String DB_URL = "jdbc:sqlite:D:/java_code/Soft_Flame/Dictionary/dict_hh.db";
//    private static String USER_NAME = "root";
//    private static String PASSWORD = "utequyen2372004";

    public void connect(String querry) {
        try {
            // connnect to database 'testdb'
            Connection conn = getConnection(DB_URL);
            // crate statement
            Statement stmt = conn.createStatement();
            // get data from table 'student'
            ResultSet rs = stmt.executeQuery(querry);
            // show data
           while (rs.next()) {
               System.out.println(rs.getString("name"));
           }
            // close connection
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args){
        DatabaseOfDict tmp = new DatabaseOfDict();
        tmp.connect("SELECT name FROM sqlite_master WHERE type='table';");
    }

    /**
     *
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
}


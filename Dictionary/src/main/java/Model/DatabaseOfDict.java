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
    private static String DB_URL = "jdbc:sqlite:D:/UET/hk3/oop/Soft_Flame/Dictionary/dict_hh.db";


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

    public static String getInfoWord(String Querry){
        String tmp = "";
        try{
            Connection conn = getConnection(DB_URL);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(Querry);
            while(rs.next()){
                tmp+=rs.getString("id")+"\n"
                        +rs.getString("word")+"\n"
                        + rs.getString("html")+"\n"
                        +rs.getString("description")+"\n"
                        +rs.getString("pronounce") +"\n";
            }
            conn.close();
        } catch (Exception ex){
            ex.printStackTrace();
        }
        return tmp;
    }
//    private static String USER_NAME = "root";
//    private static String PASSWORD = "utequyen2372004";

//    public void connect(String querry) {
//        try {
//            // connnect to database 'testdb'
//            Connection conn = getConnection(DB_URL);
//            // crate statement
//            Statement stmt = conn.createStatement();
//            // get data from table 'student'
//            ResultSet rs = stmt.executeQuery(querry);
//            // show data
//           while (rs.next()) {
//               System.out.println(rs.getString("word"));
//           }
//            // close connection
//            conn.close();
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }

//    public static void main(String[] args){
//        DatabaseOfDict tmp = new DatabaseOfDict();
//        System.out.println(tmp.getInfoWord("select * from av where word = 'hello'"));
//    }


}


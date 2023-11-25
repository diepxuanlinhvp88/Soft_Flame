package data.Account;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class AccountManagement {
    private static final String[] type = {"Newbie","Intermediate","Master","Premium"};
    private static final Hashtable<String,Integer> map = new Hashtable<String, Integer>();
    public AccountManagement(){
        for(int i = 0; i <4; i++){
            map.put(type[i],i);
        }
    }
    private static final String jdbcURL = "jdbc:mysql://sql.freedb.tech:3306/freedb_tienhoa";
    private static final String passWord = "kGDwFjf6$7Vjyr7";
    private static final String userName = "freedb_tienhoa";
    private List<Account> accountList = new ArrayList<>();
    private static String getCurrentDateTime() {
        // Lấy ngày giờ tháng hiện tại
        LocalDateTime currentDateTime = LocalDateTime.now();

        // Định dạng ngày giờ tháng theo ý muốn, ví dụ: "yyyy-MM-dd HH:mm:ss"
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        // Format ngày giờ tháng theo định dạng và chuyển thành chuỗi
        String formattedDateTime = currentDateTime.format(formatter);

        return formattedDateTime;
    }
    public static Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(jdbcURL,userName,passWord);
            System.out.println("connect successfully!");
        } catch (Exception ex) {
            System.out.println("connect failure!");
            ex.printStackTrace();
        }
        return conn;
    }
    private Connection conn = getConnection();

    public Account initAccountFromDB(String username, String password){
        String tmp = String.format("Select typeOfAccount,time,process from Accounts where username = '%s' and password = '%s';",username,password);
        try{
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(tmp);
            Account acc = null;
            if(rs.next()){
                String type = rs.getString("typeOfAccount");
                String datetime = rs.getString("time");
                float process = Float.parseFloat(rs.getString("process"));
                if(type.equals(Account.NewbieAccount)){
                    acc = new NewbieAccount(username,password,datetime,process);
                }
                else if(type.equals((Account.IntermediateAccount))){
                    acc = new IntermediateAccount(username,password,datetime,process);
                }
                else{
                    acc = null;
                }
            }
            System.out.println(rs.getString("typeOfAccount") +"\n"+rs.getString("time"));
            return acc;
        } catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    private boolean check(String username){
        String tmp = String.format("Select time from Accounts where username = '%s';",username);
        try{
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(tmp);
            if(!rs.next()){
                return false;
            }
            return true;
        } catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean Register(String username, String password,String typeOfAccount){
        if(!check(username)){
            String insertQuery = "INSERT INTO Accounts (username, password, typeOfAccount,process,time) VALUES (?, ?,?,?,?)";

            try (
                    PreparedStatement preparedStatement = conn.prepareStatement(insertQuery)
            ) {
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);
                preparedStatement.setInt(3, map.get(typeOfAccount));
                preparedStatement.setFloat(4,0);
                preparedStatement.setString(5,getCurrentDateTime());

                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Dữ liệu đã được chèn thành công!");
                    return true;
                } else {
                    System.out.println("Không có dữ liệu nào được chèn.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    private String getInfo(){
        StringBuilder tmp = new StringBuilder();
        try{
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("Select * from Accounts order by typeOfAccount,process;");
            while(rs.next()){
                String line = "";
                line+=rs.getString("username")+"<token>"+rs.getInt("typeOfAccount")
                        +"<token>"+rs.getFloat("process");
                tmp.append(line).append("\n");
            }
            return String.valueOf(tmp);
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args){
        AccountManagement tmp = new AccountManagement();
        tmp.Register("hoa","hoa","Intermediate");
        tmp.Register("linh","linh","Newbie");
        System.out.println(tmp.getInfo());
    }

}

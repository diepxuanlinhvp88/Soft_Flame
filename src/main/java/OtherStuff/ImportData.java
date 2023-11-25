package OtherStuff;

import java.io.*;
import java.sql.*;

public class ImportData {
    public static String CreateStatement(InputStream inputStream) {
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String line;
        StringBuilder stm = new StringBuilder();
        try {
            while ((line = bufferedReader.readLine()) != null) {
                String[] data = line.split("<token>");
                String tmp = String.format("Insert into A1Ex (question,answer) Values('%s','%s');",data[0],data[1]);
                stm.append(tmp).append("\n");
            }
            return String.valueOf(stm);
        } catch (IOException e){
            e.printStackTrace();
        }
        return "cannot import";
    }
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("D:\\java_code\\backup\\Soft_Flame\\data\\A1Excercise.txt");
        InputStream inputStream = new FileInputStream(file);
        System.out.println(ImportData.CreateStatement(inputStream));
    }
}

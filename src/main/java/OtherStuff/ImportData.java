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
                String[] data = line.split("<tokens>");
                String tmp = String.format("Insert into Exercise (question,answer,typeOfLevel) Values('%s','%s',%d);",data[0],data[1],1);
                stm.append(tmp).append("\n");
            }
            return String.valueOf(stm);
        } catch (IOException e){
            e.printStackTrace();
        }
        return "cannot import";
    }


    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("D:\\java_code\\backup\\Soft_Flame\\data\\IntermediaEx.txt");
        InputStream inputStream = new FileInputStream(file);
        System.out.println(ImportData.CreateStatement(inputStream));
    }
}

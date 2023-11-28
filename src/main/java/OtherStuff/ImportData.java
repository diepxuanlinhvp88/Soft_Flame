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
//                String[] data = line.split("<tokens>");
//                String tmp = String.format("Insert into Exercise (question,answer,typeOfLevel) Values('%s','%s',%d);",data[0],data[1],1);
//                stm.append(tmp).append("\n");
                stm.append(line).append("\n");
            }
        } catch (IOException e){
            e.printStackTrace();
            return "cannot import";
        }
        String[] data = String.valueOf(stm).split("#");
        int cnt = 1;
        for(String i: data){
            System.out.println(i.length());
        }
        StringBuilder re = new StringBuilder();
        for(String i: data){
            String path = "D:\\java_code\\backup\\Soft_Flame\\data\\SignImage\\P"+String.valueOf(cnt)+".png";
            String[] tmp = i.split("\n");
            String answer = tmp[4];
            String question = path+"\n"+tmp[0]+"\n"+tmp[1]+"\n"+tmp[2]+"\n"+tmp[3];
            String tmp2 = String.format("Insert into Exercise (question,answer,typeOfLevel) Values('%s','%s',%d);",question,answer,2);
            re.append(tmp2).append("\n");
            cnt++;
        }
        return String.valueOf(re);
    }


    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("D:\\java_code\\backup\\Soft_Flame\\data\\SignImage\\DataForExpert.txt");
        InputStream inputStream = new FileInputStream(file);
        System.out.println(ImportData.CreateStatement(inputStream));
    }
}

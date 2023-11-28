package data.Account;

import data.Exercise.Exercise;
import data.Exercise.FillBlankEx;
import data.Exercise.NewbieEx;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

public class NewbieAccount extends Account{
    public NewbieAccount(String userName, String passWord, String dateTime) {
        super(userName, passWord, dateTime);
    }


    public NewbieAccount(String userName, String passWord, String dateTime, float process) {
        super(userName, passWord, dateTime, process);
    }

    @Override
    public void setProcess() {
        this.process+= (float) (1.0/40 * 100);
    }

    @Override
    public void addExerciseList() {
        int id = (int)this.process/100*40+1;
        try{
            Statement stm =  AccountManagement.conn.createStatement();
            ResultSet rs = stm.executeQuery(String.format("select * from Exercise where id between %d and 329;",id));
            while (rs.next()){
                String question = rs.getString("answer");
                String answer = rs.getString("question");
                NewbieEx ex = new NewbieEx(question,answer);
                this.exercises.add(ex);
            }
            Collections.shuffle(exercises);
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    public static void main(String[] args){
        NewbieAccount tmp = new NewbieAccount("hoa1234","hoa1234","12");
        System.out.println(tmp.checkBookMark("hello"));



    }
}

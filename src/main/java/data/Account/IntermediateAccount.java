package data.Account;

import data.Exercise.Exercise;
import data.Exercise.FillBlankEx;
import data.Exercise.NewbieEx;
import data.Exercise.RerangeEx;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Collections;

public class IntermediateAccount extends  Account{
    public IntermediateAccount(String userName, String passWord, String dateTime) {
        super(userName, passWord, dateTime);
    }

    public IntermediateAccount(String userName, String passWord, String dateTime, float process) {
        super(userName, passWord, dateTime, process);
    }

    @Override
    public void setProcess() {
        this.process+=(float)1/60 * 100;
    }

    @Override
    public void addExerciseList() {
        try{
            Statement stm =  AccountManagement.conn.createStatement();
            ResultSet rs = stm.executeQuery("select question, answer from Exercise where typeOfLevel = 1");
            while (rs.next()){
                String question = rs.getString("question");
                String answer = rs.getString("answer");
                Exercise ex;
                if(question.contains("/")) {
                    ex = new RerangeEx(question,answer);
                }
                else{
                    ex = new FillBlankEx(question,answer);
                }
                this.exercises.add(ex);
            }
            Collections.shuffle(exercises);
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    public static void main(String[] args){
        IntermediateAccount tmp = new IntermediateAccount("12","12","12");
        tmp.addExerciseList();
        for(Exercise i : tmp.getExercises()){
            if(i instanceof RerangeEx){
                for(String tmp1 : ((RerangeEx) i).shuffleAnswer()){
                    System.out.print(tmp1+" ");
                }
                System.out.println();
            }
        }

    }
}

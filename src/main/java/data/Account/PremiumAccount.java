package data.Account;

import data.Exercise.Exercise;
import data.Exercise.Writing;
import data.api.CallGPTApi;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Collections;

public class PremiumAccount extends Account{
    public PremiumAccount(String userName, String passWord, String dateTime, float process) {
        super(userName, passWord, dateTime, process);
        newbieAccount.addExerciseList();
        intermediateAccount.addExerciseList();
        expertAccount.addExerciseList();
    }

    public PremiumAccount(String userName, String passWord, String dateTime) {
        super(userName, passWord, dateTime);
        newbieAccount.addExerciseList();
        intermediateAccount.addExerciseList();
        expertAccount.addExerciseList();
    }

    public PremiumAccount() {
        newbieAccount.addExerciseList();
        intermediateAccount.addExerciseList();
        expertAccount.addExerciseList();
    }
    private NewbieAccount newbieAccount = new NewbieAccount("","","");
    private IntermediateAccount intermediateAccount = new IntermediateAccount("","","");
    private ExpertAccount expertAccount = new ExpertAccount("","","");
    @Override
    public void setProcess() {
        return;
    }

    @Override
    public void addExerciseList() {
        this.exercises.addAll(newbieAccount.exercises.subList(0,10));
        this.exercises.addAll(intermediateAccount.exercises.subList(0,10));
        this.exercises.addAll(expertAccount.exercises.subList(0,10));

        String sql = "Select question from Exercise where id >= 409";
        try{
            Statement stm = AccountManagement.conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            String line;
            while(rs.next()){
                line = rs.getString("question");
                String answer = "With this Exercise: "+ line + " and here is my essay, give me some feedback about it.";
                Exercise ex = new Writing(line,answer);
                this.exercises.add(ex);
            }
            Collections.shuffle(exercises);
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}

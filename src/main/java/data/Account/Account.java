package data.Account;

import data.Exercise.Exercise;

import java.util.ArrayList;
import java.util.List;

public abstract class Account {
    public static final String NewbieAccount = "Newbie";
    public static final String IntermediateAccount = "Intermediate";
    public static final String ProAccount = "Expert";
    public static final String PremiumAccount = "Premium";
    protected String userName;
    protected String passWord;
    protected String dateTime;

    protected float process;
    protected List<Exercise> exercises = new ArrayList<>();
    public Account(String userName, String passWord,String dateTime,float process) {
        this.userName = userName;
        this.passWord = passWord;
        this.dateTime = dateTime;
        this.process = process;
    }

    public Account(String userName, String passWord, String dateTime) {
        this.userName = userName;
        this.passWord = passWord;
        this.dateTime = dateTime;
        this.process = 0;
    }

    public Account() {

    }

    public String getUserName() {
        return userName;
    }

    public List<Exercise> getExercises() {
        return exercises;
    }

    public void AddInfoActivities(Exercise ex){
        AccountManagement.addAcivities(this.userName,ex);
    }
    public List<String> getActivities(){
        return AccountManagement.getActivities(this.userName);
    }
    public boolean updateProcess(){
        return AccountManagement.reLoadstatus(this.userName,this.process);
    }

    public float getProcess() {
        return process;
    }
    public boolean addBookMark(String target){
        return AccountManagement.AddBookmark(target,this.userName);
    }

    public boolean checkBookMark(String target){
        return AccountManagement.checkBookmark(target,this.userName);
    }

    public abstract void setProcess();
    public abstract void addExerciseList();


}

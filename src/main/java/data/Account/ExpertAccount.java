package data.Account;

import data.Exercise.Exercise;
import data.Exercise.ExpertEx;

import java.io.*;
import java.util.Collections;

public class ExpertAccount extends Account{
    public ExpertAccount(String userName, String passWord, String dateTime, float process) {
        super(userName, passWord, dateTime, process);
    }

    public ExpertAccount(String userName, String passWord, String dateTime) {
        super(userName, passWord, dateTime);
    }

    @Override
    public void setProcess() {

        this.process+=1;

    }

    @Override
    public void addExerciseList() {
        String line;
        StringBuilder stm = new StringBuilder();
        try{
        String path = ".\\/data/SignImage/DataForExpert.txt";
        InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(path));
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);


            while ((line = bufferedReader.readLine()) != null) {
                stm.append(line).append("\n");
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        String[] data = String.valueOf(stm).split("#");
        int cnt = 1;
        for(String i: data){
            System.out.println(i.length());
        }
        StringBuilder re = new StringBuilder();
        for(String i: data){
            String path = ".\\"+"\\/data/SignImage/P"+String.valueOf(cnt)+".png";
            String[] tmp = i.split("\n");
            String answer = tmp[4];
            String question = path+"\n"+tmp[0]+"\n"+tmp[1]+"\n"+tmp[2]+"\n"+tmp[3];
            cnt++;
            this.exercises.add(new ExpertEx(question,answer));
        }
        Collections.shuffle(exercises);
    }
    public static void main(String[] args){
        ExpertAccount tmp = new ExpertAccount("1","1","1");
        tmp.addExerciseList();
        for(Exercise i: tmp.getExercises()){
            String ques[] = i.getQuestion().split("\n");
            System.out.println(i.getQuestion() + ques[0]);
        }
    }
}

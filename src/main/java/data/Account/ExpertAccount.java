package data.Account;

public class ExpertAccount extends Account{
    public ExpertAccount(String userName, String passWord, String dateTime, float process) {
        super(userName, passWord, dateTime, process);
    }

    public ExpertAccount(String userName, String passWord, String dateTime) {
        super(userName, passWord, dateTime);
    }

    @Override
    public void setProcess() {

    }

    @Override
    public void addExerciseList() {

    }
}

package data.Account;

public class NewbieAccount extends Account{
    public NewbieAccount(String userName, String passWord, String dateTime) {
        super(userName, passWord, dateTime);
    }

    public NewbieAccount(String userName, String passWord, String dateTime, float process) {
        super(userName, passWord, dateTime, process);
    }

    @Override
    public void setProcess() {

    }
}

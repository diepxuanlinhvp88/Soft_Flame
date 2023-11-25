package data.Account;

public class IntermediateAccount extends  Account{
    public IntermediateAccount(String userName, String passWord, String dateTime) {
        super(userName, passWord, dateTime);
    }

    public IntermediateAccount(String userName, String passWord, String dateTime, float process) {
        super(userName, passWord, dateTime, process);
    }

    @Override
    public void setProcess() {

    }

}

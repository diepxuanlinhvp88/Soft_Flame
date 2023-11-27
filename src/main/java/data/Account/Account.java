package data.Account;

public abstract class Account {
    public static final String NewbieAccount = "Newbie";
    public static final String IntermediateAccount = "Intermediate";
    public static final String ProAccount = "Expert";
    public static final String PremiumAccount = "Premium";

    protected String userName;
    protected String passWord;
    protected String dateTime;

    protected float process;
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

    public float getProcess() {
        return process;
    }

    public abstract void setProcess();

}

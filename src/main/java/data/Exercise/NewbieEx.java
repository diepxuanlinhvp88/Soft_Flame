package data.Exercise;

public class NewbieEx extends Exercise{

    @Override
    public boolean checkAnswer(String input) {
        return input.equalsIgnoreCase(this.answer);
    }

    public NewbieEx(String question, String answer) {
        super(question, answer);
    }

    @Override
    public String getInfo() {
        return "NewbieEx: "+this.question;
    }


    public static void main(String[] args){

    }
}

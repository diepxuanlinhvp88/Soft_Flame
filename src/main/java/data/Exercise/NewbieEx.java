package data.Exercise;

public class NewbieEx extends Exercise{

    public NewbieEx(String question, String answer) {
        super(question, answer);
    }

    @Override
    public String getInfo() {
        return "NewbieEx";
    }

    @Override
    public boolean checkAnswer(String input) {
        return input.equals(this.answer);
    }
}

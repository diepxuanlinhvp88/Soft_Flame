package data.Exercise;

public class RerangeEx extends Exercise{
    public RerangeEx(String question, String answer) {
        super(question, answer);
    }

    @Override
    public String getInfo() {
        return "RerangeEx";
    }

    @Override
    public boolean checkAnswer(String input) {
        return false;
    }
}

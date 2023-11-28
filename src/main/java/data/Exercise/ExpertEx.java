package data.Exercise;

public class ExpertEx extends Exercise {
    public ExpertEx(String question, String answer) {
        super(question, answer);
    }

    @Override
    public boolean checkAnswer(String input) {
        return input.equals(this.getAnswer());
    }

    @Override
    public String getInfo() {
        return null;
    }
}

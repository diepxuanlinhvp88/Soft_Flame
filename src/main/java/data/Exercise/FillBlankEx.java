package data.Exercise;

public class FillBlankEx extends Exercise{
    @Override
    public boolean checkAnswer(String input) {
        return false;
    }

    public FillBlankEx(String question, String answer) {
        super(question, answer);
    }

    @Override
    public String getInfo() {
        return "FillBlankEx";
    }


}

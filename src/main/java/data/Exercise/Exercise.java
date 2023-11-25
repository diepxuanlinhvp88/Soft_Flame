package data.Exercise;

import java.util.List;

public abstract class Exercise {
    protected String question;
    protected String answer;
    public abstract boolean checkAnswer(String input);
}

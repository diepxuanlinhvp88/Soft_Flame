package data.Exercise;

import java.util.List;

public abstract class Exercise {
    protected String question;
    protected String answer;
    public abstract boolean checkAnswer(String input);


    public Exercise(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
    public abstract String getInfo();

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}

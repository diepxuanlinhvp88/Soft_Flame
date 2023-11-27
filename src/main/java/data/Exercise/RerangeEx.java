package data.Exercise;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class RerangeEx extends Exercise{
    @Override
    public boolean checkAnswer(String input) {
        return false;
    }

    public RerangeEx(String question, String answer) {
        super(question, answer);
    }

    public List<String> shuffleAnswer(){
        String[] data = this.answer.split(" ");
        List<String> key = new ArrayList<>(Arrays.asList(data));
        Collections.shuffle(key);
        return key;
    }

    @Override
    public String getInfo() {
        return "RerangeEx";
    }



}

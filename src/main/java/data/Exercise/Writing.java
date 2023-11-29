package data.Exercise;

import data.api.CallGPTApi;

public class Writing extends Exercise{
    public Writing(String question, String answer) {
        super(question, answer);
    }
    private CallGPTApi callGPTApi = new CallGPTApi();
    public String getFeedback(String text){
        String content = this.answer + text;
        return callGPTApi.getFeedback(content);
    }
    @Override
    public boolean checkAnswer(String input) {
        return false;
    }

    @Override
    public String getInfo() {
        return "writing Task";
    }
}

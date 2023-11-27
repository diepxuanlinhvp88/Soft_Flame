package view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import view.Controller;

public class ExerciseController implements Initializable {
    @FXML
    Label question, alert,score, res;
    @FXML
    Button Ok;
    @FXML
    TextField answer;
    @FXML
    AnchorPane anchorpaneEx;
    int tmp = 0;
    double scoreInt = 0;

    public void Ok() {
        float a = (float) (2.0/40 * 100);

        if(Static_variable.account.getProcess() >= a){
            System.out.println("nâng cấp");
        }
        if (answer.getText().equals(Controller.ex.get(tmp).getAnswer())) {
            alert.setText("Bạn đã trả lời chính xác");
            Static_variable.account.setProcess();
            Static_variable.account.updateProcess();
            Static_variable.account.AddInfoActivities(Controller.ex.get(tmp));
           // System.out.println(Static_variable.account.getProcess());




        } else {
            alert.setText("Sai rồi nè !");
            res.setText("Đáp án phải là : " + Controller.ex.get(tmp).getAnswer());
        }
        score.setText(String.valueOf(Static_variable.account.getProcess()));
        System.out.println(Static_variable.account.getProcess());

    }
    public void next(){
        tmp++;
        showquestion();
        alert.setText("");
        res.setText("");

    }
    public void prev(){
        tmp--;
        showquestion();

    }
    public void home(){
        Node node;
        try {
            node = FXMLLoader.load(getClass().getResource("Controller.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        anchorpaneEx.getChildren().setAll(node);
    }
    public void exit(){
        System.exit(0);
    }
    public void showquestion(){
        if (tmp < Controller.ex.size()) {
            question.setText(Controller.ex.get(tmp).getQuestion());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showquestion();


    }
}

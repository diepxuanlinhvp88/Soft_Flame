package view;

import data.Exercise.FillBlankEx;
import data.Exercise.NewbieEx;
import data.Exercise.RerangeEx;
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
import javafx.scene.shape.Rectangle;

import static view.Controller.exCnt;

public class NewbieExController implements Initializable {
    @FXML
    Label question, alert,score, res,process;
    @FXML
    Button Ok;
    @FXML
    TextField answer;
    @FXML
    AnchorPane anchorpaneEx;

    double scoreInt = 0;
    @FXML
    Rectangle pro, max;

    public void Ok() {
//
//
        if(Static_variable.account.getProcess() >= 100.0){

            System.out.println("nâng cấp");
        }
        if (answer.getText().equalsIgnoreCase(Controller.ex.get(exCnt).getAnswer())) {
            alert.setText("Bạn đã trả lời chính xác");
            Static_variable.account.setProcess();
            pro.setWidth(Static_variable.account.getProcess());

            Static_variable.account.updateProcess();
            Static_variable.account.AddInfoActivities(Controller.ex.get(exCnt));
            System.out.println(Static_variable.account.getProcess());
        } else {
            alert.setText("Sai rồi nè !");
            res.setText("Đáp án phải là : " + Controller.ex.get(exCnt).getAnswer());

        }


        System.out.println(Static_variable.account.getProcess());

    }
    public void next() throws IOException {
        exCnt++;
         if(Controller.ex.get(exCnt) instanceof FillBlankEx){
            Node node;
            node = FXMLLoader.load(getClass().getResource("NewbieEx.fxml"));
            anchorpaneEx.getChildren().setAll(node);
        }
        else if(Controller.ex.get(exCnt) instanceof RerangeEx){
            Node node;
            node = FXMLLoader.load(getClass().getResource("RerangEx.fxml"));
            anchorpaneEx.getChildren().setAll(node);
        }
        else {
             showquestion();
             alert.setText("");
             res.setText("");
             answer.setText("");
         }

    }
    public void prev(){
        exCnt--;
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

            question.setText(Controller.ex.get(exCnt).getQuestion());

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showquestion();
        pro.setWidth(Static_variable.account.getProcess());

    }
}

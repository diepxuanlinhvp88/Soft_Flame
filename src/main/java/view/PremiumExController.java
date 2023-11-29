package view;

import data.Account.ExpertAccount;
import data.Account.IntermediateAccount;
import data.Account.NewbieAccount;
import data.Exercise.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static view.Controller.ex;
import static view.Controller.exCnt;

public class PremiumExController implements Initializable {
    @FXML
    AnchorPane anchorpaneEx;
    @FXML
    TextArea text,comment;
    @FXML
    Label question;


    public void initQuestion() {
        question.setText(ex.get(exCnt).getQuestion());

    }

    public void Ok() {
        if (ex.get(exCnt) instanceof Writing) {
           comment.setText (((Writing) ex.get(exCnt)).getFeedback(text.getText()));
           comment.setWrapText(true);
        }

    }

    public void home() {
        Node node;
        try {
            node = FXMLLoader.load(getClass().getResource("Controller.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        anchorpaneEx.getChildren().setAll(node);

    }

    public void exit() {
        System.exit(0);
    }

    public void Next() throws IOException {
        exCnt++;
        if (Controller.ex.get(exCnt) instanceof FillBlankEx) {
            Node node;
            node = FXMLLoader.load(getClass().getResource("NewbieEx.fxml"));
            anchorpaneEx.getChildren().setAll(node);
        } else if (Controller.ex.get(exCnt) instanceof NewbieEx) {
            Node node;
            node = FXMLLoader.load(getClass().getResource("NewbieEx.fxml"));
            anchorpaneEx.getChildren().setAll(node);
        } else if (Controller.ex.get(exCnt) instanceof RerangeEx) {
            Node node;
            node = FXMLLoader.load(getClass().getResource("RerangEx.fxml"));
            anchorpaneEx.getChildren().setAll(node);

        } else if (Controller.ex.get(exCnt) instanceof ExpertEx) {
            Node node;
            node = FXMLLoader.load(getClass().getResource("ExpertEx.fxml"));
            anchorpaneEx.getChildren().setAll(node);
        }
        else {
            question.setText("");
            comment.setText("");
            initQuestion();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initQuestion();

    }
}

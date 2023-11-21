package view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    AnchorPane anchorPaneParent;
    @FXML
    Pane setPane;


    @FXML
    private void LearnEngController() throws IOException {
        Node node;
        node = FXMLLoader.load(getClass().getResource("LearnEnglish.fxml"));
        anchorPaneParent.getChildren().setAll(node);



    }
    @FXML
    private void SkillTestController() throws IOException {
        Node node;
        node = FXMLLoader.load(getClass().getResource("contest.fxml"));
        anchorPaneParent.getChildren().setAll(node);

    }
    @FXML
    private void setting() throws IOException {
        Node node;
        node = FXMLLoader.load(getClass().getResource("Setting.fxml"));
        setPane.getChildren().setAll(node);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


    }
}

package view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private AnchorPane anchorPaneParent;

    @FXML
    private void LearnEngController() throws IOException {
        Node node;
        node = FXMLLoader.load(getClass().getResource("LearnEnglish.fxml"));
        anchorPaneParent.getChildren().setAll(node);



    }
    @FXML
    private void SkillTestController() throws IOException {
        Node node;
        node = FXMLLoader.load(getClass().getResource("LearnEnglish.fxml"));
        anchorPaneParent.getChildren().setAll(node);

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        Node node;
//        try {
//            node = FXMLLoader.load(getClass().getResource("Login.fxml"));
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//       anchorPaneParent.getChildren().setAll(node);

    }
}

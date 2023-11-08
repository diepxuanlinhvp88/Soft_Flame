package view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML
    AnchorPane anchorPaneLogin;
    @FXML
    private void Letgo() throws IOException {
        Node node;
        node = FXMLLoader.load(getClass().getResource("Controller.fxml"));
        anchorPaneLogin.getChildren().setAll(node);

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


    }
}

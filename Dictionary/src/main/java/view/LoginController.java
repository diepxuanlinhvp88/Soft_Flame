package view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import Controller.DictionaryManagement;
import Controller.DatabaseManagement;
import Controller.ParaTransWithAPI;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    public static DictionaryManagement dic;

    {
        try {
            dic = new DictionaryManagement();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static DatabaseManagement data = new DatabaseManagement();
    public static ParaTransWithAPI tranapi = new ParaTransWithAPI();
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

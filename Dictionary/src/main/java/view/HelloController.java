package view;
import Controller.lookUp;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class HelloController {
    @FXML
    private Label welcomeText;
    lookUp tmp = new lookUp("hello");


    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText(tmp.connectAndQuerry());
    }
}
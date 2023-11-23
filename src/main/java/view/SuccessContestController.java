package view;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class SuccessContestController implements Initializable {
    @FXML
    Pane pane;
    @FXML
    Label tmp;
    public void Ok(){
        pane.setVisible(false);
        //ContestController.ratecount =0;

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tmp.setText(String.valueOf(ContestController.ratecount));

    }
}

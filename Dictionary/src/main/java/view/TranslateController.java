package view;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class TranslateController implements Initializable {
    @FXML
    TextField TextTarget;
    @FXML
    TextField TextExplain;
    @FXML
    Label En;
    @FXML
    Label Vi;
    public void Swap(){
       TextExplain.setText(LoginController.tranapi.lookUp(TextTarget.getText(),"vi","en"));
//        TextTarget.textProperty().addListener(new ChangeListener<String>() {
//
//
//            @Override
//            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
//                TextExplain.setText(LoginController.tranapi.lookUp
//                        (TextTarget.getText(),"vi","en"));
//            }
//        });

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


    }
}

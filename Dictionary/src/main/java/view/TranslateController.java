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
    int cnt = 0;
    @FXML
    TextField TextTarget;
    @FXML
    TextField TextExplain;
    @FXML
    Label En;
    @FXML
    Label Anh;
    @FXML
    Label Viet;

    @FXML
    Label Vi;
    public void Swap(){
        ++cnt;
      if(cnt % 2 == 0) {
          En.setText("en");
          Vi.setText("vi");
          Anh.setText("Anh");
          Viet.setText("Việt");

      }
      else {
          En.setText("vi");
          Vi.setText("en");
          Anh.setText("Việt");
          Viet.setText("Anh");
      }
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
    public void tran(){
        TextExplain.setText(LoginController.tranapi.lookUp(TextTarget.getText(), Vi.getText(), En.getText()));


    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Swap();


    }
}

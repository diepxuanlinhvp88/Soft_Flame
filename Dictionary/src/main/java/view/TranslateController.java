package view;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


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
    ImageView Anh;



    @FXML
    ImageView Viet;

    @FXML
    Label Vi;
    public void Swap(){
        Image Eng = new Image(getClass().getResource("image/16.jpg").toExternalForm());
        Image Vie = new Image(getClass().getResource("image/15.png").toExternalForm());
        cnt++;
      if(cnt % 2 == 0) {
          En.setText("en");
          Vi.setText("vi");
//          Anh.setImage(new Image(getClass().getResource("view/image/16.jpg").toExternalForm()));
//          Viet.setImage(new Image(getClass().getResource("view/image/15.png").toExternalForm()));
          Anh.setImage(Eng);
          Viet.setImage(Vie);
          System.out.println("anhviet");

      }
      else {
          En.setText("vi");
          Vi.setText("en");
//          Anh.setImage(new Image(getClass().getResource("/view/image/15.png").toExternalForm()));
//          Viet.setImage(new Image(getClass().getResource("/view/image/16.jpg").toExternalForm()));
          Anh.setImage(Vie);
          Viet.setImage(Eng);
          System.out.println("vietanh");
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

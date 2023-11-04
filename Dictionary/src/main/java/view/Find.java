package view;

import Controller.DictionaryManagement;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class Find implements Initializable {
    public DictionaryManagement tmp = new DictionaryManagement();
    public Find() throws FileNotFoundException {
    }
    @FXML
    TextField FindA;
    @FXML
    TextArea FindB;
    @FXML
    ListView ListW;





    public void showListWord(){
        FindA.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                List<String> stringList = tmp.wordListTarget(FindA.getText());
                ObservableList<String> observableList = FXCollections.observableList(stringList);

                ListW.setItems(observableList);

            }
        });


    }
    public void sellect(){

        FindA.setText(ListW.getSelectionModel().getSelectedItems().toString().replace("[","").replace("]",""));
        FindB.setText(tmp.find(FindA.getText()) );
    }

    public void Find(){
        FindB.setText(tmp.find(FindA.getText()) );
        FindB.setWrapText(true);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showListWord();
        //sellect();

    }
}

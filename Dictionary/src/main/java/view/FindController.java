package view;

import Controller.DictionaryManagement;
import Controller.DatabaseManagement;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.web.WebView;
import javafx.scene.web.WebEngine;


import java.io.FileNotFoundException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class FindController implements Initializable {
    public DictionaryManagement dic = new DictionaryManagement();
    public DatabaseManagement data = new DatabaseManagement();
    public FindController() throws FileNotFoundException {
    }
    @FXML
    TextField FindA;
    @FXML
    TextArea FindB;
    @FXML
    ListView ListW;
    @FXML
    WebView webView;
    @FXML
    WebEngine webEngine;






    public void showListWord(){
        FindA.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                List<String> stringList = dic.wordListTarget(FindA.getText());
                ObservableList<String> observableList = FXCollections.observableList(stringList);

                ListW.setItems(observableList);

            }
        });


    }
    public void sellect(){

        FindA.setText(ListW.getSelectionModel().getSelectedItems().toString().replace("[","").replace("]",""));
        webEngine.loadContent(data.connectAndQuerry("av"));
        data.setWord(FindA.getText());

    }

    public void Find(){
        //FindB.setText(tmp.find(FindA.getText()) );
        webEngine.loadContent(data.connectAndQuerry("av"));
        data.setWord(FindA.getText());





    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showListWord();
        webEngine = webView.getEngine();
//        webEngine.loadContent(dic.find(FindA.getText()));


        //sellect();

    }


}
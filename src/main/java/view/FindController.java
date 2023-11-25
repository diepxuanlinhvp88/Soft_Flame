package view;


import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.scene.web.WebView;
import javafx.scene.web.WebEngine;

import javax.speech.AudioException;
import javax.speech.EngineException;
import java.beans.PropertyVetoException;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class FindController implements Initializable {

    public FindController() throws FileNotFoundException {

    }

    @FXML
    TextField FindA,targetAdd,meaningAdd, meaningEdit;
    @FXML
    ListView ListW;
    @FXML
    WebView webView;
    @FXML
    WebEngine webEngine;
    @FXML
    Label text, Wrongsellect, av;
    @FXML
    Text worongtext;





    public void showListWord() {
        System.out.println(cnttran);

            FindA.textProperty().addListener(new ChangeListener<String>() {

                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    if (cnttran % 2 == 0) {
                        List<String> stringList = LoginController.dic.wordListTarget(FindA.getText());
                        ObservableList<String> observableList = FXCollections.observableList(stringList);

                        ListW.setItems(observableList);
                        if (stringList.size() == 0) {
                            worongtext.setText("Có phải từ bạn cần tìm là : ");
                            Wrongsellect.setText(LoginController.dic.findWithWrong(FindA.getText()));
                            // FindA.setText(LoginController.dic.findWithWrong(FindA.getText()));

                        }
                    }

                }

            });



    }

    public void sellect() {


        FindA.setText(ListW.getSelectionModel().getSelectedItems().toString().replace("[", "").replace("]", ""));
        //webEngine.loadContent(LoginController.data.connectAndQuerry(av.getText(), FindA.getText()));
        webEngine.loadContent(LoginController.dic.getHtml(FindA.getText()));


    }

    public void Find() {

        if (FindA.getText() == "") {
            text.setText("Bạn chưa nhập từ cần điền");
            System.out.println("chua nhap tu ");
        } else {
            webEngine.loadContent(LoginController.dic.getHtml(FindA.getText()));
            //webEngine.loadContent(LoginController.data.connectAndQuerry(av.getText(), FindA.getText()));

        }


    }



    public void sellectWordWrong() {
        FindA.setText(LoginController.dic.findWithWrong(Wrongsellect.getText()));
       //webEngine.loadContent(LoginController.data.connectAndQuerry(av.getText(), FindA.getText()));
        webEngine.loadContent(LoginController.dic.getHtml(FindA.getText()));
    }

    public void voice() throws PropertyVetoException, AudioException, EngineException, InterruptedException {
        LoginController.tts.init("kevin16");
        LoginController.tts.doSpeak(FindA.getText());
    }

    int cnttran = 0;

    public void tranLanguage() {
        cnttran++;
        System.out.println(cnttran);
        if (cnttran % 2 == 0) {
            av.setText("av");
            worongtext.setText("");
            Wrongsellect.setText("");
            FindA.setText("");
            ListW.getItems().clear();
            webEngine.loadContent("");
            //va.setText("va");
        } else {
            av.setText("va");
            worongtext.setText("");
            Wrongsellect.setText("");
            FindA.setText("");
            ListW.getItems().clear();
            webEngine.loadContent("");
            // va.setText("av");
        }

    }


    /**
     * add db .
     */
    public void Add(){
        if(cnttran % 2 == 0){
            LoginController.data.addWordtoDatabase(av.getText(),FindA.getText(),
                    meaningAdd.getText(),"","",true);
        }

    }

    public void Remove() throws FileNotFoundException {
        if(cnttran % 2 == 0) {
            LoginController.data.removeWord(FindA.getText(), "av");
            LoginController.dic.remove(FindA.getText());
            try {
                LoginController.dic.reLoadDictionaryFromFile("dfg");
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

    }
    public void editw() throws FileNotFoundException {
        LoginController.data.removeWord(FindA.getText(),"av");
        LoginController.data.addWordtoDatabase("av",FindA.getText(),
                meaningEdit.getText(),"","",false);
        LoginController.dic.update(FindA.getText(),meaningEdit.getText());

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

            showListWord();


        webEngine = webView.getEngine();
        //webEngine.loadContent(LoginController.dic.find(FindA.getText()));


        //sellect();


    }


}
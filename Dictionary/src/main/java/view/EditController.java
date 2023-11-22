package view;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class EditController implements Initializable {


    public EditController() throws FileNotFoundException {
    }
    @FXML
    TextField target;
    @FXML
    TextField meaning;
    @FXML
    TextField pronpunce;
    @FXML
    TextField html;
    @FXML
    TextField wordremove;
    @FXML
    ListView listToRemove;
    @FXML
    TextField targetEdit;
    @FXML
    TextField meaningEdit;
    @FXML
    ListView listToEdit;
    @FXML
    Label addwordsuccess, removess,editss;


    /**
     * add db .
     */
    public void Add(){

        if(LoginController.data.addWordtoDatabase("av",target.getText(),
                meaning.getText(),pronpunce.getText(),html.getText())){
            System.out.println("ss");

        };

        try {
            LoginController.dic.reLoadDictionaryFromFile("dfg");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        addwordsuccess.setText("Add Word success!");


    }

    public void Remove(){
        LoginController.data.removeWord(wordremove.getText(),"av");
        try {
            LoginController.dic.reLoadDictionaryFromFile("dfg");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        removess.setText("Remove success!");
    }

    /**
     * show listview in the remove word tab .
     */
    public void showListWordremove(){

        wordremove.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                List<String> stringList = LoginController.dic.wordListTarget(wordremove.getText());
                ObservableList<String> observableList = FXCollections.observableList(stringList);
                listToRemove.setItems(observableList);

            }
        });




    }

    /**
     * mouse click in listview item , set it to wordremove .
     */
    public void selectRemove(){

        wordremove.setText(listToRemove.getSelectionModel().getSelectedItems().toString().replace("[","").replace("]",""));


    }
    public void editw(){
        LoginController.data.removeWord(targetEdit.getText(),"av");
        LoginController.data.addWordtoDatabase("av",meaningEdit.getText(),
                meaningEdit.getText(),meaningEdit.getText(),meaningEdit.getText());

        editss.setText("Edit Word success!");
    }

    public void showListWordEdit(){

        targetEdit.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                List<String> stringList =LoginController.dic.wordListTarget(targetEdit.getText());
                ObservableList<String> observableList = FXCollections.observableList(stringList);

                listToEdit.setItems(observableList);

            }
        });



    }

    /**
     * mouse click in listview item , set it to targetEdit .
     */
    public void selectEdit(){

        targetEdit.setText(listToEdit.getSelectionModel().getSelectedItems().toString().replace("[","").replace("]",""));


    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showListWordremove();
        showListWordEdit();

    }
}

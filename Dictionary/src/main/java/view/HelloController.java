package view;
import Controller.DictionaryManagement;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.FileNotFoundException;

public class HelloController {

    public DictionaryManagement tmp = new DictionaryManagement();
    public HelloController() throws FileNotFoundException {
    }
    public boolean isActionTra = false;
    @FXML
    TextField DichA;
    @FXML
    TextArea DichVi;
    @FXML
    TextField TraA;
    @FXML
    TextField TraB;

    @FXML
    ListView listV;






    @FXML
    public void Dich(){

        DichVi.setText(tmp.find(DichA.getText()));
        DichVi.setWrapText(true);


    }
    public void showListWord(){
        TraA.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                System.out.println(t1);
                System.out.println("000");
            }
        });
//        TraA.textProperty().addListener(new ChangeListener<String>() {
//            @Override
//            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
//                List<String> stringList = tmp.wordListTarget(TraA.getText());
//                ObservableList<String> observableList = FXCollections.observableList(stringList);
//
//                listV.setItems(observableList);
//                System.out.println(TraA.getText().length());
//            }
//        });
    }

    @FXML
    public void Tra(){

        //ListW.setText(tmp.wordListTarget("he").toString());

        TraB.setText(tmp.find(TraA.getText()));
    }
    @FXML
    public void Sua(){

    }
    @FXML
    public void Erase(){
    }

   
    
   
    @FXML
    public void Add(){

    }

}


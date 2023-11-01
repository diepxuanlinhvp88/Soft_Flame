package view;
import Controller.DictionaryManagement;
import Controller.lookUp;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.io.FileNotFoundException;

public class HelloController {

    public static DictionaryManagement tmp;

    static {
        try {
            tmp = new DictionaryManagement();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    HelloController() throws FileNotFoundException {
    }

    @FXML
    public void Dich(){

    }
    @FXML
    public void Tra(){

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
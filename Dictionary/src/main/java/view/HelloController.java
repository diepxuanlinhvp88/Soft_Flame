package view;
import Controller.DictionaryManagement;
import Controller.lookUp;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.io.FileNotFoundException;

public class HelloController {
    @FXML
    private Label welcomeText;
    private DictionaryManagement tmp = new DictionaryManagement();
//
////    static {
////        try {
////            tmp = new DictionaryManagement();
////        } catch (FileNotFoundException e) {
////            throw new RuntimeException(e);
////        }
////    }
//
    public HelloController() throws FileNotFoundException {
    }

    //    public HelloController() throws FileNotFoundException {
//    }
//
//        public static void main(String[] args) throws FileNotFoundException {
//        System.out.println(tmp.lookUp("department"));
//    }
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
package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SigUpController implements Initializable {
    @FXML
    TextField newAcc;
    @FXML
    PasswordField newpassword;
    @FXML
    Label sc;
    @FXML
    AnchorPane anchorpaneSignUp;
    @FXML
    ChoiceBox<String> choiceBox;

    private void showAlert(ActionEvent event) throws IOException {

        Node node = FXMLLoader.load(getClass().getResource("Login.fxml"));

        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setHeaderText(null);
        alert.setContentText("Your account has been created, please log in");

        System.out.println(Audio.mediaPlayer.getMedia() + "SU alert");
        alert.showAndWait().ifPresent(response -> {
            if (response == javafx.scene.control.ButtonType.OK) {

                anchorpaneSignUp.getChildren().setAll(node);

            } else {


            }
        });

    }
    public void SignUpStart(ActionEvent event) throws IOException {
        choiceBox.setOnAction(event1 -> {
            String selectedOption = choiceBox.getValue();
            System.out.println("Selected Option: " + selectedOption);
        });

        if (!newAcc.getText().equals("")) {
            Static_variable.accountmanagement.Register(newAcc.getText(),newpassword.getText(),choiceBox.getValue());
            showAlert(event);
        } else sc.setText("cannot be left blank");



    }

    private void box(){
        choiceBox.getItems().setAll("Newbie","Intermediate","Expert","Premium");

        // Thiết lập giá trị mặc định cho ChoiceBox
        choiceBox.setValue("Newbie");

    }
    public void out(){
        Node node;
        try {
            node = FXMLLoader.load(getClass().getResource("Login.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        anchorpaneSignUp.getChildren().setAll(node);

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Audio.mediaPlayer.play();
        System.out.println(Audio.mediaPlayer.getMedia() + "SU ");
        box();

    }

}

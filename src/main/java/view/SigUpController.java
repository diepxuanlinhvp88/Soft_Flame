package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
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
    public void saveAccout(String username, String pass) throws IOException {

        try {

            BufferedWriter writer = new BufferedWriter(new FileWriter(".\\/data/account.txt", true));
            writer.write(username + "," + pass + System.lineSeparator());
            writer.close();
        } catch (FileNotFoundException e) {
            System.out.println("khong tim duoc file");

            e.printStackTrace();
        }


    }
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

        if (!newAcc.getText().equals("")) {
            saveAccout(newAcc.getText(), newpassword.getText());
            //        sc.setText("create an account success ! learn now ");
            showAlert(event);
        } else sc.setText("cannot be left blank");

    }
    public void newbie(){
        LoginController.accountmanagement.initAccountFromDB(newAcc.getText(),newpassword.getText());
        LoginController.accountmanagement.Register(newAcc.getText(),newpassword.getText(),"Newbie");
    }
    public void inter(){
        LoginController.accountmanagement.initAccountFromDB(newAcc.getText(),newpassword.getText());
        LoginController.accountmanagement.Register(newAcc.getText(),newpassword.getText(),"Intermediate");
    }
    public void master(){
        LoginController.accountmanagement.initAccountFromDB(newAcc.getText(),newpassword.getText());
        LoginController.accountmanagement.Register(newAcc.getText(),newpassword.getText(),"Master");
    }
    public void premium(){
        LoginController.accountmanagement.initAccountFromDB(newAcc.getText(),newpassword.getText());
        LoginController.accountmanagement.Register(newAcc.getText(),newpassword.getText(),"Premium");
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Audio.mediaPlayer.play();
        System.out.println(Audio.mediaPlayer.getMedia() + "SU ");

    }
}

package view;

import Model.Word;

import javafx.beans.property.ObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;

import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import javafx.scene.control.*;

import javafx.scene.layout.AnchorPane;
import Controller.DictionaryManagement;
import Controller.DatabaseManagement;
import Controller.ParaTransWithAPI;
import Controller.textToSpeech;
import javafx.scene.layout.Pane;


import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Objects;

import java.util.ResourceBundle;


public class LoginController implements Initializable {
    public static DictionaryManagement dic;

    {
        try {
            dic = new DictionaryManagement();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static DatabaseManagement data = new DatabaseManagement();
    public static ParaTransWithAPI tranapi = new ParaTransWithAPI();
    public static textToSpeech tts = new textToSpeech();

    public static MediaPlayer mediaPlayer;
    public static String songs ="music/1.mp3";
    @FXML
    public AnchorPane anchorPaneLogin;
    @FXML
    Pane LogPane;
    @FXML
    Pane SignPane;
    @FXML
    TextField acc;

    @FXML
    TextField newAcc;
    @FXML
    PasswordField newpassword;
    @FXML
    PasswordField password;
    @FXML
    Label er;
    @FXML
    Label sc;
    @FXML
    Pane setPane;
    @FXML
    ComboBox<String> comboBox;

    public void saveAccout(String username, String pass) throws IOException {

        try {

            BufferedWriter writer = new BufferedWriter(new FileWriter(".\\/Dictionary/data/account.txt", true));
            writer.write(username + "," + pass + System.lineSeparator());
            writer.close();
        } catch (FileNotFoundException e) {
            System.out.println("khong tim duoc file");

            e.printStackTrace();
        }


    }

    public boolean checkAccount(String username, String pass) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(".\\/Dictionary/data/account.txt");
        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream
                , StandardCharsets.UTF_8);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String line;
        String[] parts;
        try {
            while ((line = bufferedReader.readLine()) != null) {
                parts = line.split(",");
                if (parts[0].equals(username) && parts[1].equals(pass)) return true;

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;

    }


    @FXML
    Pane LogPane;
    @FXML
    Pane SignPane;
    @FXML
    TextField acc;
    @FXML
    TextField newAcc;
    @FXML
    PasswordField newpassword;
    @FXML
    PasswordField password;
    @FXML
    Label er;
    @FXML
    Label sc;

    @FXML
    private void Letgo() throws IOException {


        Node node;
        if (checkAccount(acc.getText(), password.getText())) {

            node = FXMLLoader.load(getClass().getResource("Controller.fxml"));
            anchorPaneLogin.getChildren().setAll(node);
        } else er.setText("username or password is not correct");



    }

    public void SignUp() throws IOException {

        Node node;
        node = FXMLLoader.load(getClass().getResource("SignUp.fxml"));
        anchorPaneLogin.getChildren().setAll(node);


    }

    public void SignUpStart() throws IOException {
        //        Node node;
        //        node = FXMLLoader.load(getClass().getResource("Login.fxml"));
        //        anchorPaneLogin.getChildren().setAll(node);
        if (!newAcc.getText().equals("")) {
            saveAccout(newAcc.getText(), newpassword.getText());
            //        sc.setText("create an account success ! learn now ");
            showAlert();
        } else sc.setText("cannot be left blank");

    }

    private void showAlert() throws IOException {
        Node node;
        node = FXMLLoader.load(getClass().getResource("Login.fxml"));
        anchorPaneLogin.getChildren().setAll(node);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setHeaderText(null);
        alert.setContentText("Your account has been created, please log in");
        alert.showAndWait().ifPresent(response -> {
            if (response == javafx.scene.control.ButtonType.OK) {
                anchorPaneLogin.getChildren().setAll(node);
            } else {


            }
        });

    }



    public void musicBg() throws IOException {

        System.out.println(songs);

        URL musicFilePath = getClass().getResource(songs);
        Media media = new Media(musicFilePath.toString());
        mediaPlayer = new MediaPlayer(media);

        // Chạy nhạc nền liên tục
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.play();

    }



    public void setting() throws IOException {
        Node node = FXMLLoader.load(getClass().getResource("Setting.fxml"));
        setPane.getChildren().setAll(node);

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            musicBg();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }



    }
}

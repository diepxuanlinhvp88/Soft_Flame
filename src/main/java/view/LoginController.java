package view;

import data.Account.Account;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import data.db.DictionaryManagement;
import data.db.DatabaseManagement;
import data.api.ParaTransWithAPI;
import data.api.TextToSpeech;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import data.Account.AccountManagement;
import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ResourceBundle;
import data.Account.*;

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
    public static TextToSpeech tts = new TextToSpeech();
    public static final AccountManagement accountmanagement = new AccountManagement();



    @FXML
    public AnchorPane anchorPaneLogin;
    @FXML
    Pane LogPane;

    @FXML
    TextField acc;

    @FXML
    PasswordField password;
    @FXML
    Label er;
    @FXML
    Pane setPane;
    @FXML
    ComboBox<String> comboBox;




    public boolean checkAccount(String username, String pass) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(".\\/data/account.txt");
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
    private void Letgo() throws IOException {

        Node node;
        if (checkAccount(acc.getText(), password.getText())) {

            node = FXMLLoader.load(getClass().getResource("controller.fxml"));
            anchorPaneLogin.getChildren().setAll(node);
        } else er.setText("username or password is not correct");


    }

    public void SignUp() throws IOException {
//        LoginController.mediaPlayer.pause();
        Node node;
        node = FXMLLoader.load(getClass().getResource("SignUp.fxml"));
        anchorPaneLogin.getChildren().setAll(node);


    }
    public void NotLogin() throws IOException {
        Node node;
        node = FXMLLoader.load(getClass().getResource("find.fxml"));
        anchorPaneLogin.getChildren().setAll(node);
        anchorPaneLogin.autosize();
    }


//    public static void musicBg(String songs) throws IOException {
//
//
//        URL musicFilePath = LoginController.class.getResource(songs);
//        media = new Media(musicFilePath.toString());
//        mediaPlayer = new MediaPlayer(media);
//
//               // Chạy nhạc nền liên tục
//        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
//        mediaPlayer.stop();
//        System.out.println(LoginController.mediaPlayer.getMedia() + "Lo ");
//        mediaPlayer.play();
//
//    }


    public void setting() throws IOException {
        Node node = FXMLLoader.load(getClass().getResource("Setting.fxml"));
        setPane.getChildren().setAll(node);

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // musicBg(songs);
        Audio.playmusic();
    }
}

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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import data.db.DictionaryManagement;
import data.db.DatabaseManagement;
import data.api.ParaTransWithAPI;
import data.api.TextToSpeech;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import data.Account.AccountManagement;
import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ResourceBundle;
import data.Account.*;



public class LoginController implements Initializable {

    Static_variable tmp = new Static_variable();

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




    public boolean checkAccount(String username, String pass, String typeAccount) throws IOException {
       return Static_variable.accountmanagement.Register(username,pass, typeAccount);

    }


    @FXML
    private void Letgo() throws IOException {
        Static_variable.account = Static_variable.accountmanagement.initAccountFromDB(acc.getText(),password.getText());
        if(Static_variable.account == null){
            er.setText("username or password is not correct");
        }
        else {
            Static_variable.account.addExerciseList();
            Node node;
            node = FXMLLoader.load(getClass().getResource("controller.fxml"));
            anchorPaneLogin.getChildren().setAll(node);
        }

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
//        Node node = FXMLLoader.load(getClass().getResource("Setting.fxml"));
//        setPane.getChildren().setAll(node);

        Slider volumeSlider = new Slider(0, 1, 0.5);
        volumeSlider.setLayoutX(97);
        volumeSlider.setLayoutY(109);
        volumeSlider.setPrefSize(156, 16);
        volumeSlider.setShowTickLabels(true);
        volumeSlider.setShowTickMarks(true);

        volumeSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            Audio.mediaPlayer.setVolume(newValue.doubleValue());
        });
        Pane root = new Pane();
        root.setPrefSize(284, 200);
        root.setStyle("-fx-background-color: #B48D77; -fx-background-radius: 5;");

        // Tạo các thành phần giao diện
        ImageView imageView1 = new ImageView(new Image(getClass().getResourceAsStream("image/voice.png")));
        imageView1.setFitHeight(61);
        imageView1.setFitWidth(71);
        imageView1.setLayoutX(15);
        imageView1.setLayoutY(87);

        ImageView imageView2 = new ImageView(new Image(getClass().getResourceAsStream("image/voice1.png")));
        imageView2.setFitHeight(67);
        imageView2.setFitWidth(71);
        imageView2.setLayoutX(15);
        imageView2.setLayoutY(20);

        MenuButton menuButton = new MenuButton("Song");
        MenuItem menuitem1 = new MenuItem("1.mp3");
        menuitem1.setOnAction(event -> {
                Audio.songs = "music/1.mp3";
                Audio.mediaPlayer.pause();
                Audio.playmusic();
        });
        MenuItem menuitem2 = new MenuItem("2.mp3");
        menuitem2.setOnAction(event -> {
            Audio.songs = "music/2.mp3";
            Audio.mediaPlayer.pause();
            Audio.playmusic();
        });
        MenuItem menuitem3 = new MenuItem("3.mp3");
        menuitem3.setOnAction(event ->
        {
            Audio.songs = "music/3.mp3";
            Audio.mediaPlayer.pause();
            Audio.playmusic();
        });

        menuButton.setLayoutX(97);
        menuButton.setLayoutY(35);
        menuButton.setPrefSize(156, 28);
        menuButton.setStyle("-fx-background-color: f4b8b8;");
        menuButton.setTextFill(javafx.scene.paint.Color.valueOf("#e82020"));
        menuButton.getItems().addAll(menuitem1,menuitem2,menuitem3
        );
        root.getChildren().setAll(imageView1, imageView2, volumeSlider, menuButton);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Music");
        DialogPane dialogPane = alert.getDialogPane();
        //dialogPane.getButtonTypes().clear();
        dialogPane.setHeaderText(null);
        dialogPane.setGraphic(null);

        dialogPane.setContent(root);
        dialogPane.setStyle("-fx-background-color: #B48D77;");
//
        alert.showAndWait();
    }




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // musicBg(songs);
        Audio.playmusic();
    }
}


package view;

import data.Exercise.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    AnchorPane anchorPaneParent;
    @FXML
    Pane setPane;
    public static List<Exercise> ex;
    public static int exCnt = 0;


    @FXML
    private void LearnEngController() throws IOException {
        Node node;
        node = FXMLLoader.load(getClass().getResource("LearnEnglish.fxml"));
        anchorPaneParent.getChildren().setAll(node);


    }

    @FXML
    private void SkillTestController() throws IOException {

        Node node;
        node = FXMLLoader.load(getClass().getResource("contest.fxml"));
        anchorPaneParent.getChildren().setAll(node);

    }

    public void Exercise() throws IOException {
        if (ex.get(exCnt) instanceof NewbieEx) {
            Node node;
            node = FXMLLoader.load(getClass().getResource("NewbieEx.fxml"));
            anchorPaneParent.getChildren().setAll(node);
        } else if (ex.get(exCnt) instanceof FillBlankEx) {
            Node node;
            node = FXMLLoader.load(getClass().getResource("NewbieEx.fxml"));
            anchorPaneParent.getChildren().setAll(node);
        } else if (ex.get(exCnt) instanceof RerangeEx) {
            Node node;
            node = FXMLLoader.load(getClass().getResource("RerangEx.fxml"));
            anchorPaneParent.getChildren().setAll(node);
        } else if(ex.get(exCnt) instanceof ExpertEx) {
            Node node;
            node = FXMLLoader.load(getClass().getResource("ExpertEx.fxml"));
            anchorPaneParent.getChildren().setAll(node);
        }
        else {
            Node node;
            node = FXMLLoader.load(getClass().getResource("PremiumEx.fxml"));
            anchorPaneParent.getChildren().setAll(node);
        }
    }

    @FXML
    private void setting() throws IOException {
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
        menuButton.getItems().addAll(menuitem1, menuitem2, menuitem3
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
//        Node node;
//        node = FXMLLoader.load(getClass().getResource("Setting.fxml"));
//        setPane.getChildren().setAll(node);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ex = Static_variable.account.getExercises();
        System.out.println(exCnt);
        System.out.println(ex.size());
    }
}

package view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ResourceBundle;

public class LearnEnglishController implements Initializable {
    @FXML
    AnchorPane anchorParentLearn;
    @FXML
    AnchorPane  anchorPaneChild;
    Node node;



    public void Findcontroller() throws IOException {
        Node node;
        node = FXMLLoader.load(getClass().getResource("find.fxml"));
        anchorPaneChild.getChildren().setAll(node);

    }
    public void Menu() throws IOException {
        Node node;
        node = FXMLLoader.load(getClass().getResource("controller.fxml"));
        anchorParentLearn.getChildren().setAll(node);

    }
    public void Translate() throws IOException{
        Node node;
        node = FXMLLoader.load(getClass().getResource("Translate.fxml"));
        anchorPaneChild.getChildren().setAll(node);

    }
    public void setting(){
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


    public void Game() throws IOException, InterruptedException {
//        Node node;
//        node = FXMLLoader.load(getClass().getResource("v.fxml"));
        executeWordleGame();
//        anchorPaneChild.getChildren().setAll(node);
    }

    private void executeWordleGame() throws InterruptedException, IOException {
        final String command = "java  --module-path \"D:\\javafx\\javafx-sdk-21.0.1\\lib\" --add-modules javafx.controls,javafx.fxml -jar \"D:\\java_code\\backup\\Soft_Flame\\Dictionary\\src\\main\\java\\view\\JavaFX-Wordle.jar\"";

        // Create process builder
        ProcessBuilder processBuilder = new ProcessBuilder();

        // Set the command
        processBuilder.command(command.split("\\s+"));

        // Redirect error stream to output stream
        processBuilder.redirectErrorStream(true);

        // Start the process
        Process process = processBuilder.start();

        // Read the output of the command
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Wait for the process to complete
        int exitCode = process.waitFor();

        System.out.println("Process exited with code: " + exitCode);
    }

    public void Exit() throws IOException{
        System.exit(0);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            Translate();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
//        try {
//            Findcontroller();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        Node node;
//        try {
//            node = FXMLLoader.load(getClass().getResource("find.fxml"));
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        anchorPaneChild.getChildren().setAll(node);

    }

}
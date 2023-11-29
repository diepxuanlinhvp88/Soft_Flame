package view;

import data.Account.ExpertAccount;
import data.Account.IntermediateAccount;
import data.Account.NewbieAccount;
import data.Account.PremiumAccount;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ResourceBundle;

import static java.awt.Color.red;

public class LearnEnglishController implements Initializable {
    @FXML
    AnchorPane anchorParentLearn;
    @FXML
    AnchorPane anchorPaneChild;
    @FXML
    ImageView profile;


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

    public void Translate() throws IOException {
        Node node;
        node = FXMLLoader.load(getClass().getResource("Translate.fxml"));
        anchorPaneChild.getChildren().setAll(node);

    }

    public void setting() {
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
    }


    public void Game() throws IOException, InterruptedException {
//        Node node;
//        node = FXMLLoader.load(getClass().getResource("v.fxml"));
        executeWordleGame();
//        anchorPaneChild.getChildren().setAll(node);
    }

    private void executeWordleGame() throws InterruptedException, IOException {
        final String command = "WordGames";

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

    public void Exit() throws IOException {
        System.exit(0);

    }

    public void profile() {
        Pane root = new Pane();
        root.setPrefWidth(200.0);
        root.setPrefHeight(200.0);

        Image image = new Image(getClass().getResource("image/36.png").toExternalForm());
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(50);
        imageView.setFitHeight(50);


        root.setStyle("-fx-background-color: #6dd8fc;");

        // Tạo VBox
        VBox vBox = new VBox();
        vBox.setLayoutX(0);
        vBox.setLayoutY(0);
        vBox.setPrefWidth(252);
        vBox.setPrefHeight(200);

        // Tạo các HBox và thêm Label vào mỗi HBox
        HBox typeHBox = new HBox();
        Label typeLabel = new Label();
        typeLabel.setFont(Font.font(16));
        typeLabel.setTextFill(Color.RED);
        if (Static_variable.account instanceof NewbieAccount) {
            typeLabel.setText("Loại tài khoản : Newbie");
        } else if (Static_variable.account instanceof IntermediateAccount) {
            typeLabel.setText("Loại tài khoản : Intermedia");
        } else if (Static_variable.account instanceof ExpertAccount) {
            typeLabel.setText("Loại tài khoản : Expert");
        } else {
            typeLabel.setText("Loại tài khoản : Premium");
        }

        typeHBox.getChildren().add(typeLabel);

        HBox userHBox = new HBox();
        Label userLabel = new Label("Username : " + Static_variable.username);
        userLabel.setFont(Font.font(16));
        userLabel.setTextFill(Color.RED);
        userHBox.getChildren().add(userLabel);

        HBox processHBox = new HBox();
        Label processLabel = new Label("Process : " + Static_variable.account.getProcess() + " % ");
        processLabel.setFont(Font.font(16));
        processLabel.setTextFill(Color.RED);
        processHBox.getChildren().add(processLabel);

        HBox dayHBox = new HBox();
        Label dayLabel = new Label("Ngày tạo : " + Static_variable.account.getDateTime());
        dayLabel.setFont(Font.font(16));
        dayLabel.setTextFill(Color.RED);
        dayHBox.getChildren().add(dayLabel);

        HBox rankHBox = new HBox();
        Label rankLabel = new Label("Xếp hạng");
        rankLabel.setFont(Font.font(16));
        rankLabel.setTextFill(Color.RED);
        rankHBox.getChildren().add(rankLabel);

        // Thêm các HBox vào VBox
        vBox.getChildren().addAll(imageView, typeHBox, userHBox, processHBox, dayHBox, rankHBox);

        // Thêm VBox vào AnchorPane
        root.getChildren().add(vBox);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("thông tin");
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.setHeaderText(null);
        dialogPane.setGraphic(null);

        dialogPane.setContent(root);
        dialogPane.setStyle("-fx-background-color: #6dd8fc;");
        alert.showAndWait();

    }

    public void setprofileImg() {
        if (Static_variable.account instanceof NewbieAccount) {
            Image newimage = new Image(String.valueOf(new File(getClass().getResource("image/profile1.png").toExternalForm())));
            profile.setImage(newimage);

        } else if (Static_variable.account instanceof IntermediateAccount) {
            Image interimage = new Image(String.valueOf(new File(getClass().getResource("image/profile3.png").toExternalForm())));
            profile.setImage(interimage);
        } else if (Static_variable.account instanceof ExpertAccount) {
            Image expertimage = new Image(String.valueOf(new File(getClass().getResource("image/profile2.png").toExternalForm())));
            profile.setImage(expertimage);
        } else if (Static_variable.account instanceof PremiumAccount) {
            Image expertimage = new Image(String.valueOf(new File(getClass().getResource("image/profile4.png").toExternalForm())));
            profile.setImage(expertimage);
        } else {
            Image noimage = new Image(String.valueOf(new File(getClass().getResource("image/profile0.png").toExternalForm())));
            profile.setImage(noimage);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            Translate();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        setprofileImg();

    }

}

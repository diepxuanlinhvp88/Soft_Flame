package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import Controller.DictionaryManagement;
import Controller.DatabaseManagement;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {



        Parent root = FXMLLoader.load(HelloApplication.class.getResource("Login.fxml"));


        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("graphic.css").toExternalForm());
        Image image = new Image(String.valueOf(getClass().getResource("image/36.png")));
        stage.getIcons().add(image);


        stage.setTitle("Soft Flame");

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
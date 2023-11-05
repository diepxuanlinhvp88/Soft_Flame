package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import Controller.DictionaryManagement;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(HelloApplication.class.getResource("Edit.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("graphic.css").toExternalForm());

        stage.setTitle("Dictionary");

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
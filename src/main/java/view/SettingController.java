package view;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SettingController implements Initializable {
    @FXML
    private Slider volumeSlider;
    @FXML
    ImageView out;
    @FXML
    Pane paneset;
    public void outAction(){
        paneset.setVisible(false);

    }
    public void action1() throws IOException {
        Audio.songs = "music/1.mp3";
        Audio.mediaPlayer.pause();
        Audio.playmusic();



    }
    public void action2() throws IOException {
        Audio.songs = "music/2.mp3";
        System.out.println(Audio.mediaPlayer.getMedia() + "set");
        Audio.mediaPlayer.pause();
        Audio.playmusic();
        System.out.println(Audio.mediaPlayer.getMedia() + "set");


    }
    public void action3() throws IOException {
        Audio.songs = "music/3.mp3";
        System.out.println(Audio.mediaPlayer.getMedia() + "set");
        Audio.mediaPlayer.pause();
        Audio.playmusic();
        System.out.println(Audio.mediaPlayer.getMedia() + "set");


    }






    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        volumeSlider.setShowTickLabels(true);
        volumeSlider.setShowTickMarks(true);

        volumeSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            Audio.mediaPlayer.setVolume(newValue.doubleValue());
        });

    }
}

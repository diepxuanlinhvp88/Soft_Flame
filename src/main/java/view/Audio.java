package view;

import javafx.fxml.Initializable;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class Audio implements Initializable {
    public static MediaPlayer mediaPlayer;
    public static String songs = "music/1.mp3";
    public static Media media;
    public static void playmusic(){
        if(mediaPlayer!=null){
            mediaPlayer.stop();
        }

//        Media sound = new Media(new File("path/to/sound.mp3").toURI().toString());
//        MediaPlayer mediaPlayer = new MediaPlayer(sound);
//        mediaPlayer.play();


            URL musicFilePath = LoginController.class.getResource(songs);


        media = new Media(musicFilePath.toString());
            mediaPlayer = new MediaPlayer(media);

            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);

            //mediaPlayer.play();
            System.out.println(mediaPlayer.getMedia());

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //playmusic();

    }


}

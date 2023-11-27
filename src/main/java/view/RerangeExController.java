package view;

import data.Exercise.RerangeEx;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class RerangeExController implements Initializable {

    @FXML
    Rectangle process;

    public void initbuton(){
        List<String> questions = new ArrayList<>();
      if (Static_variable.account.getExercises().get(Controller.exCnt) instanceof RerangeEx)
      {
          questions = ((RerangeEx) Static_variable.account.getExercises().get(Controller.exCnt)).shuffleAnswer();
          System.out.println("co");
      }
        System.out.println("khong");



        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        for(int i = 0 ; i < 10;i ++){
            Button button = new Button("buttoon " + String.valueOf(i));
            gridPane.add(button,i,0);
        }




    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        process.setWidth(Static_variable.account.getProcess());
        initbuton();

    }

}

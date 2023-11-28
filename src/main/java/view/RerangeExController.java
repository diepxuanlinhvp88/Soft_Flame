package view;

import data.Account.ExpertAccount;
import data.Account.IntermediateAccount;
import data.Account.NewbieAccount;
import data.Exercise.FillBlankEx;
import data.Exercise.NewbieEx;
import data.Exercise.RerangeEx;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static view.Controller.ex;
import static view.Controller.exCnt;

public class RerangeExController implements Initializable {

    @FXML
    Rectangle process;
    @FXML
    AnchorPane anchorpaneEx;
    @FXML
    GridPane grid;
    @FXML
    Label res;


    public void initbuton(){
        List<String> questions = new ArrayList<>();
      if (Static_variable.account.getExercises().get(exCnt) instanceof RerangeEx)
      {
          questions = ((RerangeEx) Static_variable.account.getExercises().get(exCnt)).shuffleAnswer();
          System.out.println("co");
          System.out.println(questions.size());
      }
        System.out.println("khong");

        grid = new GridPane();
        grid.setLayoutX(200);
        grid.setLayoutY(200);

        Button[] upperRow = new Button[questions.size()];
        Button[] lowerRow = new Button[questions.size()];

        for (int i = 0; i < questions.size(); i++) {
            final int idx = i;

            lowerRow[i] = new Button(questions.get(i));
            lowerRow[i].setMinWidth(120);

            lowerRow[i].setOnAction(e -> swapButtonsup(upperRow, lowerRow, idx)

            );
            grid.add(lowerRow[i], i, 1);


            upperRow[i] = new Button("");
            upperRow[i].setMinWidth(120);
            upperRow[i].setOnAction(event -> swapButtonsdown(upperRow,lowerRow,idx)
            );
            grid.add(upperRow[i], i, 0);
        }
        anchorpaneEx.getStylesheets().add(getClass().getResource("rerangButton.css").toString());


        anchorpaneEx.getChildren().add(grid);


    }
    private void swapButtonsup(Button[] upperRow,Button[] lowerRow,int index){
        int emptyIndex = -1;
        if(!upperRow[upperRow.length-1].getText().isEmpty()){
            String tmp = null;
            for(int i= 0; i < upperRow.length;i++){
                tmp += upperRow[i].getText() + " ";
            }
            res.setText(tmp);
        }
        for (int i = 0; i < upperRow.length; i++) {
            if (upperRow[i].getText().isEmpty()) {
                emptyIndex = i;
                break;
            }
        }


        if (emptyIndex != -1) {

            String lowerText = lowerRow[index].getText();
            upperRow[emptyIndex].setText(lowerText);
            lowerRow[index].setText("");
        }


    }
    private void swapButtonsdown(Button[] upperRow,Button[] lowerRow,int index){

        int lowEmtyId = -1;
        for (int i = 0; i < lowerRow.length; i++) {
            if (lowerRow[i].getText().isEmpty()) {
                lowEmtyId = i;
                break;
            }
        }

        if (lowEmtyId != -1) {

            String lowerText = upperRow[index].getText();
            lowerRow[lowEmtyId].setText(lowerText);
            upperRow[index].setText("");
        }

    }
    public void next() throws IOException {
        exCnt++;
        System.out.println("next");
        if(Controller.ex.get(exCnt) instanceof FillBlankEx){
            Node node;
            node = FXMLLoader.load(getClass().getResource("NewbieEx.fxml"));
            anchorpaneEx.getChildren().setAll(node);
        }
        else if(Controller.ex.get(exCnt) instanceof NewbieEx){
            Node node;
            node = FXMLLoader.load(getClass().getResource("NewbieEx.fxml"));
            anchorpaneEx.getChildren().setAll(node);
        }
        else {
            anchorpaneEx.getChildren().remove(grid);

            initbuton();
        }

    }

    public void Ok(){
        if(Static_variable.account.getProcess() >=0){
            if(Static_variable.account instanceof ExpertAccount){
                return;
            }
            if(Static_variable.account instanceof NewbieAccount) {
                Static_variable.accountmanagement.setAccountLevel(Static_variable.username, 1);
            }
            else if(Static_variable.account instanceof IntermediateAccount){
                Static_variable.accountmanagement.setAccountLevel(Static_variable.username,2);
            }
            Pane root = new Pane();
            root.setPrefSize(600, 400);

            root.setStyle("-fx-background-color: #5fcbb1; -fx-background-radius: 5;");
            Label sc = new Label("xin chúc mừng, tài khoản của bạn đã được nâng cấp");
            Label sc1 = new Label("Hãy khởi động lại để tiếp tục nhé !!");

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Nâng cấp tài khoản");
            DialogPane dialogPane = alert.getDialogPane();
            dialogPane.setHeaderText(null);
            dialogPane.setGraphic(null);

            dialogPane.setContent(root);
            dialogPane.setStyle("-fx-background-color: #5fcbb1;");
//
            alert.showAndWait();
            Node node;
            try {
                node = FXMLLoader.load(getClass().getResource("Login.fxml"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            anchorpaneEx.getChildren().setAll(node);


        }
        if(res.getText().equalsIgnoreCase(ex.get(exCnt).getAnswer())){
            System.out.println( "ban tra loi dung ");
            Static_variable.account.setProcess();
            Static_variable.account.updateProcess();
            Static_variable.account.AddInfoActivities(Controller.ex.get(exCnt));
            System.out.println(Static_variable.account.getProcess());

        }
        System.out.println(ex.get(exCnt).getAnswer());
        process.setWidth(Static_variable.account.getProcess() * 2);

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        process.setWidth(Static_variable.account.getProcess() * 2);
        initbuton();


    }

}

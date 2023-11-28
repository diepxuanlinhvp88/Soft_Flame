package view;

import data.Account.ExpertAccount;
import data.Account.IntermediateAccount;
import data.Account.NewbieAccount;
import data.Exercise.ExpertEx;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
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
    @FXML
    ImageView imageView;
    private Button[] buttons = null;
    private List<String> questions = new ArrayList<>();



    public void initbuton(){

      if (Static_variable.account.getExercises().get(exCnt) instanceof RerangeEx)
      {
          questions = ((RerangeEx) Static_variable.account.getExercises().get(exCnt)).shuffleAnswer();
          System.out.println("co");
          System.out.println(questions.size());
      }
        System.out.println("khong");

        grid = new GridPane();
        grid.setLayoutX(150);
        grid.setLayoutY(200);

        Button[] upperRow = new Button[questions.size()];
        Button[] lowerRow = new Button[questions.size()];

        for (int i = 0; i < questions.size(); i++) {
            final int idx = i;

            lowerRow[i] = new Button(questions.get(i));
            lowerRow[i].setMinWidth(120);

            lowerRow[i].setOnAction(e -> swapButtonsup(upperRow, lowerRow, idx)

            );
            buttons = upperRow;
            grid.add(lowerRow[i], i, 1);


            upperRow[i] = new Button("");
            upperRow[i].setMinWidth(120);
            upperRow[i].setOnAction(event -> swapButtonsdown(upperRow,lowerRow,idx)
            );
            if(i == questions.size() -1) {
                String tmp = "";
                for (int j = 0; j < upperRow.length; j++) {
                    tmp += upperRow[j].getText() + " ";
                }
                res.setText(tmp);
                System.out.println(res.getText());
                System.out.println(tmp + "hello");
            }

            grid.add(upperRow[i], i, 0);
        }
        anchorpaneEx.getStylesheets().add(getClass().getResource("rerangButton.css").toString());


        anchorpaneEx.getChildren().add(grid);


    }
    private void swapButtonsup(Button[] upperRow,Button[] lowerRow,int index){
        int emptyIndex = -1;

        for (int i = 0; i < upperRow.length; i++) {
            if (upperRow[i].getText().isEmpty()) {
                emptyIndex = i;
                System.out.println(res);
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
        imageView.setImage(null);
        if(Controller.ex.get(exCnt) instanceof FillBlankEx){
            Node node;
            node = FXMLLoader.load(getClass().getResource("NewbieEx.fxml"));
            anchorpaneEx.getChildren().setAll(node);
        }
        else if(Controller.ex.get(exCnt) instanceof NewbieEx){
            Node node;
            node = FXMLLoader.load(getClass().getResource("NewbieEx.fxml"));
            anchorpaneEx.getChildren().setAll(node);
        }else if(Controller.ex.get(exCnt) instanceof ExpertEx){
            Node node;
            node = FXMLLoader.load(getClass().getResource("ExpertEx.fxml"));
            anchorpaneEx.getChildren().setAll(node);
        }
        else {
            anchorpaneEx.getChildren().remove(grid);
            initbuton();
        }

    }
    private void showUpLevel(){
        Pane root = new Pane();

        root.setPrefWidth(600.0);
        root.setPrefHeight(400.0);
        ImageView imageView = new ImageView();
        Image image = new Image(getClass().getResource("image/ok.jpg").toExternalForm());
        imageView.setImage(image);
        imageView.setFitWidth(284.0);
        imageView.setFitHeight(268.0);
        imageView.setLayoutX(151.0);
        imageView.setLayoutY(70.0);
        Label titleLabel = new Label();
        Label messageLabel = new Label();
        titleLabel.setPrefWidth(488.0);
        titleLabel.setPrefHeight(93.0);
        titleLabel.setLayoutX(56.0);
        titleLabel.setLayoutY(6.0);

        messageLabel.setPrefWidth(402.0);
        messageLabel.setPrefHeight(68.0);
        messageLabel.setLayoutX(167.0);
        messageLabel.setLayoutY(318.0);

        // Thiết lập nội dung và font cho các Label
        titleLabel.setText("Xin chúc mừng tài khoản của bạn đã được nâng cấp");
        titleLabel.setTextFill(javafx.scene.paint.Color.RED);
        titleLabel.setFont(new Font(20.0));

        messageLabel.setText("Vui lòng đăng nhập lại để tiếp tục");
        messageLabel.setFont(new Font(20.0));

        root.setStyle("-fx-background-color: #5fcbb1; -fx-background-radius: 5;");

        root.getChildren().addAll(titleLabel,messageLabel);

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


    public void Ok(){
        if(buttons.length < questions.size()){return;}
        String answer ="";
        for(int i =0; i < buttons.length -1;i++){
            answer+= buttons[i].getText() + " ";

        }
        answer += buttons[buttons.length-1].getText();
        System.out.println(answer);


        if(Static_variable.account.getProcess() >=100){
            if(Static_variable.account instanceof ExpertAccount){
                return;
            }
            else if (Static_variable.account instanceof NewbieAccount) {
                Static_variable.accountmanagement.setAccountLevel(Static_variable.username, 1);
            }
            else if(Static_variable.account instanceof IntermediateAccount){
                Static_variable.accountmanagement.setAccountLevel(Static_variable.username,2);
            }
            showUpLevel();
        }
        if(answer.equals(ex.get(exCnt).getAnswer())){
            System.out.println( "ban tra loi dung ");
            Image trueImg = new Image(String.valueOf(new File(getClass().getResource("image/26875966_cuteanimated_474.jpg").toString())));
            imageView.setImage(trueImg);
            Static_variable.account.setProcess();
            Static_variable.account.updateProcess();
            Static_variable.account.AddInfoActivities(Controller.ex.get(exCnt));
            System.out.println(Static_variable.account.getProcess());

        }
        else {
            Image falseImg = new Image(String.valueOf(new File(getClass().getResource("image/26875964_cuteanimated_473.jpg").toString())));
            imageView.setImage(falseImg);
            System.out.println("tl sai");
        }
        System.out.println(ex.get(exCnt).getAnswer() + "2");
        System.out.println(res.getText());
        process.setWidth(Static_variable.account.getProcess() * 2);

    }
    public void home(){
        Node node;
        try {
            node = FXMLLoader.load(getClass().getResource("Controller.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        anchorpaneEx.getChildren().setAll(node);

    }public void exit(){
        System.exit(0);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        process.setWidth(Static_variable.account.getProcess() * 2);
        initbuton();


    }

}

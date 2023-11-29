package view;

import data.Account.ExpertAccount;
import data.Account.IntermediateAccount;
import data.Account.NewbieAccount;
import data.Exercise.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static view.Controller.ex;
import static view.Controller.exCnt;

public class ExpertExController implements Initializable {

    @FXML
    AnchorPane anchorPaneEx;
    @FXML
    Rectangle process;
    @FXML
    CheckBox c;
    @FXML
    CheckBox b;
    @FXML
    CheckBox a;
    @FXML
    ImageView imageView;
    @FXML
    Label res;
    @FXML
    Label exx;
    @FXML
    Label trueLb;

    public void initImage() {
        String ques[] = ex.get(exCnt).getQuestion().split("\n");
        Image tmp = new Image(new File(ques[0]).toURI().toString());
        imageView.setImage(tmp);

        System.out.println(ques[0]);
       if(ques.length >4) {
           exx.setText(ques[1]);
        a.setText(ques[2]);
        b.setText(ques[3]);
        c.setText(ques[4]);
       }
        for(String i : ques){
            System.out.println(i);
        }

    }

    public boolean checkAnswer() {

        a.setOnAction(event -> {
            if (a.isSelected()) {
                res.setText("1");

            }
        });
        b.setOnAction(event -> {
            if (b.isSelected()) {
                res.setText("2");

            }
        });
        c.setOnAction(event -> {
            if (c.isSelected()) {
                res.setText("3");

            }
        });
        return (res.getText().equals(ex.get(exCnt).getAnswer()));


    }

    public void Next() throws IOException {
        exCnt++;
        if(exCnt >= ex.size()){
            exCnt = 0;
        }
        if(Controller.ex.get(exCnt) instanceof FillBlankEx){
            Node node;
            node = FXMLLoader.load(getClass().getResource("NewbieEx.fxml"));
            anchorPaneEx.getChildren().setAll(node);
        }
        else if(Controller.ex.get(exCnt) instanceof NewbieEx){
            Node node;
            node = FXMLLoader.load(getClass().getResource("NewbieEx.fxml"));
            anchorPaneEx.getChildren().setAll(node);
        }
        else if (Controller.ex.get(exCnt) instanceof Writing){
            Node node;
            node = FXMLLoader.load(getClass().getResource("PremiumEx.fxml"));
            anchorPaneEx.getChildren().setAll(node);
        }else if (Controller.ex.get(exCnt) instanceof RerangeEx){
            Node node;
            node = FXMLLoader.load(getClass().getResource("RerangEx.fxml"));
            anchorPaneEx.getChildren().setAll(node);
        }
        else {
            a.setSelected(false);
            b.setSelected(false);
            c.setSelected(false);
            trueLb.setText("");

            initImage();

        }

    }

    private void showAlert() {
        Pane root = new Pane();

        root.setPrefWidth(600.0);
        root.setPrefHeight(400.0);
        Image image = new Image(getClass().getResource("image/ok.jpg").toExternalForm());
        ImageView imageView = new ImageView(image);
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

        root.getChildren().addAll(imageView, titleLabel, messageLabel);

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
        anchorPaneEx.getChildren().setAll(node);

    }

    public void Ok() {
        if (Static_variable.account.getProcess() >= 10) {
            if (Static_variable.account instanceof ExpertAccount) {
                Static_variable.accountmanagement.setAccountLevel(Static_variable.username, 3);
            } else if (Static_variable.account instanceof NewbieAccount) {
                Static_variable.accountmanagement.setAccountLevel(Static_variable.username, 1);
            } else if (Static_variable.account instanceof IntermediateAccount) {
                Static_variable.accountmanagement.setAccountLevel(Static_variable.username, 2);
            }
            showAlert();

        }
        if (checkAnswer()) {
            System.out.println("ban tra loi dung ");
            trueLb.setText("Quá đỉnh luôn");

            Static_variable.account.setProcess();
            Static_variable.account.updateProcess();
            Static_variable.account.AddInfoActivities(Controller.ex.get(exCnt));
            System.out.println(Static_variable.account.getProcess());

        } else {
            trueLb.setText("Sai mất rồi");
            System.out.println("Ban da tra loi sai");
        }
        System.out.println(ex.get(exCnt).getAnswer());
        process.setWidth(Static_variable.account.getProcess() * 2);


    }

    public void home() throws IOException {
        Node node;
        node = FXMLLoader.load(getClass().getResource("Controller.fxml"));
        anchorPaneEx.getChildren().setAll(node);
    }

    public void exit() {
        System.exit(0);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        process.setWidth(Static_variable.account.getProcess() * 2);
        initImage();
        checkAnswer();

    }
}

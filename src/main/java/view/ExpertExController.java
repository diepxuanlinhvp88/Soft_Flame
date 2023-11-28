package view;

import data.Exercise.ExpertEx;
import data.Exercise.FillBlankEx;
import data.Exercise.NewbieEx;
import data.Exercise.RerangeEx;
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
        exx.setText(ques[1]);
        a.setText(ques[2]);
        b.setText(ques[3]);
        c.setText(ques[4]);

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
        if (exCnt == ex.size())exCnt = 0;
        a.setSelected(false);
        b.setSelected(false);
        c.setSelected(false);
        trueLb.setText("");
        System.out.println("next");
        if (ex.get(exCnt) instanceof FillBlankEx) {
            Node node;
            node = FXMLLoader.load(getClass().getResource("NewbieEx.fxml"));
            anchorPaneEx.getChildren().setAll(node);
        } else if (ex.get(exCnt) instanceof NewbieEx) {
            Node node;
            node = FXMLLoader.load(getClass().getResource("NewbieEx.fxml"));
            anchorPaneEx.getChildren().setAll(node);
        } else if (ex.get(exCnt) instanceof RerangeEx) {
            Node node;
            node = FXMLLoader.load(getClass().getResource("RerangEx.fxml"));
            anchorPaneEx.getChildren().setAll(node);
        } else {

            initImage();

        }

    }

    private void showAlert() {
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
        titleLabel.setPrefWidth(488.0);
        titleLabel.setPrefHeight(93.0);
        titleLabel.setLayoutX(56.0);
        titleLabel.setLayoutY(6.0);
        titleLabel.setText("Xin chúc mừng bạn đã hoàn thành khóa học");
        titleLabel.setTextFill(javafx.scene.paint.Color.RED);
        titleLabel.setFont(new Font(20.0));

        root.setStyle("-fx-background-color: #5fcbb1; -fx-background-radius: 5;");

        root.getChildren().addAll(titleLabel);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("perfect");
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.setHeaderText(null);
        dialogPane.setGraphic(null);

        dialogPane.setContent(root);
        dialogPane.setStyle("-fx-background-color: #5fcbb1;");
//
        alert.showAndWait();
    }

    public void Ok() {
        if (Static_variable.account.getProcess() >= 100) {
            showAlert();
            return;
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

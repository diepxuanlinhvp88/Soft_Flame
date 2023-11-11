package view;

import Model.Word;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import Controller.DictionaryManagement;
import Controller.DatabaseManagement;
import Controller.ParaTransWithAPI;
import Controller.textToSpeech;
import javafx.scene.layout.Pane;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ResourceBundle;


public class LoginController implements Initializable {
    public static DictionaryManagement dic;

    {
        try {
            dic = new DictionaryManagement();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static DatabaseManagement data = new DatabaseManagement();
    public static ParaTransWithAPI tranapi = new ParaTransWithAPI();
    public static textToSpeech tts = new textToSpeech();

    public void saveAccout(String username, String pass) throws IOException {

        try {

            BufferedWriter writer = new BufferedWriter(new FileWriter(".\\/Dictionary/account.txt",true));
            writer.write(username + "," + pass + System.lineSeparator());
            writer.close();
        } catch (FileNotFoundException e) {
            System.out.println("khong tim duoc file");

            e.printStackTrace();
        }


    }

    public boolean checkAccount(String username, String pass) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(".\\/Dictionary/account.txt");
        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream
                , StandardCharsets.UTF_8);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String line;
        String[] parts;
        try {
            while ((line = bufferedReader.readLine()) != null) {
                parts = line.split(",");
                if (parts[0].equals(username) && parts[1].equals(pass)) return true;

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;

    }

    @FXML
    AnchorPane anchorPaneLogin;
    @FXML
    Pane LogPane;
    @FXML
    Pane SignPane;
    @FXML
    TextField acc;
    @FXML
    TextField newAcc;
    @FXML
    PasswordField newpassword;
    @FXML
    PasswordField password;
    @FXML
    Label er;
    @FXML
    Label sc;

    @FXML
    private void Letgo() throws IOException {

            Node node;
            if (checkAccount(acc.getText(), password.getText())) {

                node = FXMLLoader.load(getClass().getResource("Controller.fxml"));
                anchorPaneLogin.getChildren().setAll(node);
            } else er.setText("username or password is not correct");



    }

    public void SignUp() throws IOException {
        Node node;
        node = FXMLLoader.load(getClass().getResource("SignUp.fxml"));
        anchorPaneLogin.getChildren().setAll(node);


    }
    public void SignUpStart() throws IOException {
//        Node node;
//        node = FXMLLoader.load(getClass().getResource("Login.fxml"));
//        anchorPaneLogin.getChildren().setAll(node);
       saveAccout(newAcc.getText(),newpassword.getText());
//        sc.setText("create an account success ! learn now ");
        showAlert();

    }
    private void showAlert() throws IOException {
        Node node;
         node = FXMLLoader.load(getClass().getResource("Login.fxml"));
        anchorPaneLogin.getChildren().setAll(node);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setHeaderText(null);
        alert.setContentText("Your account has been created, please log in");
        alert.showAndWait().ifPresent(response -> {
            if (response == javafx.scene.control.ButtonType.OK) {
                anchorPaneLogin.getChildren().setAll(node);
            } else {


            }
        });

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


    }
}

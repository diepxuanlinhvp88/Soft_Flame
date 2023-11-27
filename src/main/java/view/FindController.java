package view;


import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.web.WebView;
import javafx.scene.web.WebEngine;

import javax.speech.AudioException;
import javax.speech.EngineException;
import java.beans.PropertyVetoException;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import view.Static_variable;

public class FindController implements Initializable {

    public FindController() throws FileNotFoundException {

    }

    @FXML
    TextField FindA, targetAdd, meaningAdd, meaningEdit;
    @FXML
    ListView ListW;
    @FXML
    WebView webView;
    @FXML
    WebEngine webEngine;
    @FXML
    Label text, Wrongsellect, av;
    @FXML
    Text worongtext;
    @FXML
    AnchorPane MainanchorPane;


    public void showListWord() {
        System.out.println(cnttran);

        FindA.textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (cnttran % 2 == 0) {
                    List<String> stringList = Static_variable.dic.wordListTarget(FindA.getText());
                    ObservableList<String> observableList = FXCollections.observableList(stringList);

                    ListW.setItems(observableList);
                    if (stringList.size() == 0) {
                        worongtext.setText("Có phải từ bạn cần tìm là : ");
                        Wrongsellect.setText(Static_variable.dic.findWithWrong(FindA.getText()));
                        // FindA.setText(LoginController.dic.findWithWrong(FindA.getText()));

                    }
                }

            }

        });


    }

    public void sellect() {


        FindA.setText(ListW.getSelectionModel().getSelectedItems().toString().replace("[", "").replace("]", ""));
        //webEngine.loadContent(LoginController.data.connectAndQuerry(av.getText(), FindA.getText()));
        webEngine.loadContent(Static_variable.dic.getHtml(FindA.getText()));


    }

    public void Find() {

        if (FindA.getText() == "") {
            text.setText("Bạn chưa nhập từ cần điền");
            System.out.println("chua nhap tu ");
        } else {
            if (cnttran % 2 == 0) {
                webEngine.loadContent(Static_variable.dic.getHtml(FindA.getText()));
            } else {
                if (Static_variable.data.connectAndQuerry(av.getText(), FindA.getText()) == "") {
                    webEngine.loadContent("<h1 style=\"color: red;\">Từ bạn cần tìm không có trong từ điển</h1>\n" +
                            "<h2 style=\"color: black;\">Hãy thêm vào từ điển</h2>\n");
                } else
                    webEngine.loadContent(Static_variable.data.connectAndQuerry(av.getText(), FindA.getText()));
            }
            //webEngine.loadContent(LoginController.data.connectAndQuerry(av.getText(), FindA.getText()));

        }


    }


    public void sellectWordWrong() {
        FindA.setText(Static_variable.dic.findWithWrong(Wrongsellect.getText()));
        //webEngine.loadContent(LoginController.data.connectAndQuerry(av.getText(), FindA.getText()));
        webEngine.loadContent(Static_variable.dic.getHtml(FindA.getText()));
    }

    public void voice() throws PropertyVetoException, AudioException, EngineException, InterruptedException {
        Static_variable.tts.init("kevin16");
        Static_variable.tts.doSpeak(FindA.getText());
    }

    int cnttran = 0;

    public void tranLanguage() {
        cnttran++;
        System.out.println(cnttran);
        if (cnttran % 2 == 0) {
            av.setText("av");
            worongtext.setText("");
            Wrongsellect.setText("");
            FindA.setText("");
            ListW.getItems().clear();
            webEngine.loadContent("");
            //va.setText("va");
        } else {
            av.setText("va");
            worongtext.setText("");
            Wrongsellect.setText("");
            FindA.setText("");
            ListW.getItems().clear();
            webEngine.loadContent("");
            // va.setText("av");
        }

    }


    /**
     * add db .
     */
    public void Add() {
        if (cnttran % 2 == 0) {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Add Word");
            VBox inputFields = new VBox(10);
            TextField target = new TextField();
            TextField meaning = new TextField();
            if (FindA.getText().equals("")) {
                inputFields.getChildren().addAll(new Label("Target : "), target, new Label("Meaning : "), meaning);
            } else {
                inputFields.getChildren().addAll(new Label("Meaning : "), meaning);
                dialog.setHeaderText(FindA.getText());
                target.setText(FindA.getText());
            }


            dialog.getDialogPane().setContent(inputFields);
            dialog.showAndWait();

            Static_variable.data.addWordtoDatabase(av.getText(), target.getText(),
                    meaning.getText(), "", "", true);
            try {
                Static_variable.dic.reLoadDictionaryFromFile("d");
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }


        }

    }

    public void Remove() throws FileNotFoundException {
        String target = FindA.getText();
        if (cnttran % 2 == 0) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Xóa từ");
            alert.setHeaderText(null);
            alert.setContentText("Bạn có chắc chắn xóa từ này không?");

            // Thiết lập các nút (OK và Cancel)
            ButtonType buttonTypeOK = new ButtonType("OK");
            ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
            alert.getButtonTypes().setAll(buttonTypeOK, buttonTypeCancel);
            Optional<ButtonType> result = alert.showAndWait();

            // Xử lý kết quả nếu người dùng nhấn OK
            if (result.orElse(buttonTypeCancel) == buttonTypeOK) {
                System.out.println("User clicked OK");
                webEngine.loadContent("");
                Static_variable.data.removeWord(target, "av");
                Static_variable.dic.remove(target);
                try {
                    Static_variable.dic.reLoadDictionaryFromFile("dfg");
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }


            } else {
                System.out.println("User clicked Cancel");

            }
        }

    }

    public void editw() throws FileNotFoundException {
        String target = FindA.getText();
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Edit");
        HBox inputFields = new HBox(10);
        TextArea meaning = new TextArea();
        if (FindA.getText().equals("")) {
        } else {

            inputFields.getChildren().addAll(new Label("Meaning : "), meaning);
            dialog.setHeaderText(FindA.getText());
        }
        dialog.getDialogPane().setContent(inputFields);
        dialog.showAndWait();

        Static_variable.data.removeWord(target, "av");
        Static_variable.data.addWordtoDatabase("av", target,
                meaning.getText(), "", "", false);
        Static_variable.dic.update(target, meaning.getText());

    }

    public void exit() {


//        // Khóa API của bạn từ Google Cloud Console
//        String apiKey = "YOUR_API_KEY";
//
//        // Tạo TextToSpeechClient
//        try (TextToSpeechClient textToSpeechClient = TextToSpeechClient.create()) {
//
//            // Tạo TextToSpeechRequest
//            SynthesisInput input = SynthesisInput.newBuilder()
//                    .setText("Xin chào, đây là một ví dụ về Text-to-Speech trong Java.")
//                    .build();
//
//            VoiceSelectionParams voice = VoiceSelectionParams.newBuilder()
//                    .setLanguageCode("vi-VN")
//                    .setName("vi-VN-Standard-A")
//                    .build();
//
//            AudioConfig audioConfig = AudioConfig.newBuilder()
//                    .setAudioEncoding(AudioEncoding.LINEAR16)
//                    .build();
//
//            SynthesizeSpeechResponse response = textToSpeechClient.synthesizeSpeech(input, voice, audioConfig);
//
//            // Lấy dữ liệu âm thanh từ phản hồi
//            ByteString audioData = response.getAudioContent();
//
//            // Lưu dữ liệu âm thanh vào tệp WAV
//            // Bạn cũng có thể sử dụng MediaPlayer để phát trực tiếp
//            // ...
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        showListWord();


        webEngine = webView.getEngine();
        //webEngine.loadContent(LoginController.dic.find(FindA.getText()));


        //sellect();


    }


}
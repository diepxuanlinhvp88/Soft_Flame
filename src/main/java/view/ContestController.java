package view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class ContestController implements Initializable {


    @FXML
    Label explainquestion, rate, question, success;
    @FXML
    Button A, B, C, D;
    @FXML
    AnchorPane anchorPane;
    @FXML
    Pane pane;
    public static int ratecount = 0;
    int cnt = 0;


    public List<String> Listquestion() throws FileNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(".\\/Dictionary/data/question.txt");
        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream
                , StandardCharsets.UTF_8);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String line;
        String[] parts;

        List<String> tmp = new ArrayList<>();
        try {
            while ((line = bufferedReader.readLine()) != null) {
                parts = line.split("<>");
                tmp.add(parts[0]);
            }
            return tmp;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tmp;

    }

    public String answer(String question) {
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(".\\/Dictionary/data/question.txt");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream
                , StandardCharsets.UTF_8);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String line;
        String[] parts;
        String[] answers;
        try {
            while ((line = bufferedReader.readLine()) != null) {

                parts = line.split("<>");
                if (question.equals(parts[0])) {
                    answers = parts[1].split(",");
                    String answerTrue = answers[0];
                    System.out.println(answerTrue);
                    Random rand = new Random();

                    for (int i = answers.length - 1; i > 0; i--) {
                        int randomIndex = rand.nextInt(i + 1);
                        String temp = answers[i];
                        answers[i] = answers[randomIndex];
                        answers[randomIndex] = temp;
                    }
                    A.setText(answers[0]);
                    B.setText(answers[1]);
                    C.setText(answers[2]);
                    D.setText(answers[3]);
                    return answerTrue;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return " khong tim thay tu";

    }

    public String explainQuestion(String question) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(".\\/Dictionary/data/question.txt");
        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream
                , StandardCharsets.UTF_8);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String line;
        String[] parts;
        try {
            while ((line = bufferedReader.readLine()) != null) {

                parts = line.split("<>");
                if (question.equals(parts[0])) {
                    return parts[2];
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return " khong tim thay cau hoi";
    }

    private void handleButtonClick(Button button, String word) {
        String guessedChar = button.getText();

        if (guessedChar.equals(word)) {
            System.out.println(word);
            success.setText("ban da doan dung");
            ratecount += 5;
            System.out.println(ratecount);
            System.out.println(word);
            System.out.println("dung");
        } else {
            if (ratecount >= 2) {
                ratecount -= 2;
            }
            System.out.println(button.getText());
            System.out.println(word);
            System.out.println(ratecount);
            success.setText("dap an ban chon da sai ");
            System.out.println("sai");
        }
        try {
            explainquestion.setText(explainQuestion(question.getText()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        rate.setText(String.valueOf(ratecount));

    }


    @FXML
    private void setquestion() throws IOException {
        Random random = new Random();
        int ranId = random.nextInt(Listquestion().size());
        question.setText(Listquestion().get(ranId));

    }

    public void setActionButton(String word) {
        A.setOnAction(event -> handleButtonClick(A, word));
        B.setOnAction(event -> handleButtonClick(B, word));
        C.setOnAction(event -> handleButtonClick(C, word));
        D.setOnAction(event -> handleButtonClick(D, word));


    }

    public void out() {
        Node node;
        try {
            node = FXMLLoader.load(getClass().getResource("controller.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        anchorPane.getChildren().setAll(node);


    }

    public void next() {

        if (cnt < 20) {
            try {
                setquestion();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            answer(question.getText());
            setActionButton(answer(question.getText()));

        } else {

            Node node;
            try {
                node = FXMLLoader.load(getClass().getResource("successContest.fxml"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            pane.getChildren().addAll(node);


        }
        cnt++;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            setquestion();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        answer(question.getText());
        setActionButton(answer(question.getText()));


    }
}

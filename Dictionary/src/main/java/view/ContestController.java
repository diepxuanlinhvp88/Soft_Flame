package view;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class ContestController implements Initializable  {


    @FXML
    Label wordVi;
    @FXML
    GridPane words;
    @FXML
    GridPane input;




    public String findWordEng(String wordVi) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(".\\/Dictionary/data/wordConttest.txt");
        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream
                , StandardCharsets.UTF_8);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String line;
        String[] parts;
        try {
            while ((line = bufferedReader.readLine()) != null) {

                parts = line.split("<>");
               if(parts[1].equals(wordVi)){
                   return parts[0];
               }


            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return " khong tim thay tu";

    }
    public List<String> ListwordContestString() throws FileNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(".\\/Dictionary/data/wordConttest.txt");
        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream
                , StandardCharsets.UTF_8);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String line;
        String[] parts;

        List<String> tmp = new ArrayList<>();
        try {int cnt =0;
            while ((line = bufferedReader.readLine()) != null) {
                parts = line.split("<>");

                    cnt++;
                System.out.println(cnt);
                    tmp.add(parts[1]);

            }
            return tmp;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tmp;



    }
    private void createLabels(String wordtarget) {
        for (int i = 0; i < wordtarget.length(); i++) {
            Label label = new Label("_");
            label.setId("letter" + (i + 1));

            words.add(label,i,0);
        }
    }
    private void createButon( String wordtarget) {
        for (int i = 0; i < wordtarget.length() + 3; i++) {
            Random random = new Random();
            int randomIndex = random.nextInt(26);
            int randomIndex2 = random.nextInt(25);
            char randomChar = (char) ('a' + randomIndex);
            char randomChar2 = (char) ('a' + randomIndex2);
            Button button = new Button();
            button.setId(String.valueOf(i +1));
            button.setOnAction(event -> {
                try {
                    handleButtonClick(button,findWordEng(wordVi.getText()));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            if(i == 0) {
                button.setText(String.valueOf(randomChar));
                System.out.println(button.getText());
            }
            else if(i >= wordtarget.length()){
                button.setText(String.valueOf(randomChar2 ));
            }
            else if(i == wordtarget.length() + 2){
                button.setText(String.valueOf(wordtarget.charAt(0)));
            }
            else if(i % 2 == 0 ){
                button.setText(String.valueOf(wordtarget.charAt(i)));
            }else if(i % 2 == 1){
                int j = wordtarget.length()-i -1;
                button.setText(String.valueOf(wordtarget.charAt(j)));
            }
            if(i < 4) {
                input.add(button, i,0 );
            }else if(i<8){
                input.add(button,i-4,1);
                }
            else if(i<12){
                input.add(button,i-8,2);
            }
            else if(i<16){
                input.add(button,i-12,3);
            }


        }
    }

    private void handleButtonClick(Button button, String word) {
        String guessedChar = button.getText();
        boolean correctGuess = false;
        for (int i = 0; i < word.length(); i++) {
            if (String.valueOf(word.charAt(i)).equalsIgnoreCase(guessedChar)) {
                correctGuess = true;

                    int rowIndex = 0; // Hàng cần sửa
                    int colIndex = i; // Cột cần sửa

                    // Lặp qua các nút con của GridPane
                    for (Node node : words.getChildren()) {
                        // Kiểm tra xem nút con có phải là Label không
                        if (node instanceof Label) {
                            // Ép kiểu nút con thành Label để có thể thao tác với các phương thức của Label
                            Label label = (Label) node;

                            // Xác định vị trí của Label trong GridPane
                            int row = GridPane.getRowIndex(label) == null ? 0 : GridPane.getRowIndex(label);
                            int col = GridPane.getColumnIndex(label) == null ? 0 : GridPane.getColumnIndex(label);

                            // Nếu vị trí trùng khớp với vị trí cần sửa, thay đổi giá trị của Label
                            if (row == rowIndex && col == colIndex) {
                                label.setText(button.getText());
                                break; // Thoát vòng lặp sau khi sửa giá trị
                            }
                        }
                    }


            }
        }


        if (correctGuess) {
            input.getChildren().remove(button);

            System.out.println(word);
        }
    }
//    public void save() throws IOException {
//
//            FileInputStream fileInputStream = new FileInputStream(".\\/Dictionary/data/wordConttest.txt");
//            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream
//                    , StandardCharsets.UTF_8);
//            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
//            BufferedWriter writer = new BufferedWriter(new FileWriter(".\\/Dictionary/data/wordh.txt", true));
//            String line;
//            String[] parts;
//            try {
//                while ((line = bufferedReader.readLine()) != null) {
//                    parts = line.split("<>");
//                    for(int i = 0; i < parts[0].length();i++){
//                        if(String.valueOf(parts[0].charAt(i)).equals(" ") ||
//                                String.valueOf(parts[0].charAt(i)).equals("-")){
//                            System.out.println(0);
//                        }
//                        else if(i == parts[0].length()-1) {
//                            writer.write(parts[0] + "<>" + parts[1] + System.lineSeparator());
//                        }
//
//
//                    }
//                }
//                writer.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//
//
//
//
//
//
//
//    }

    @FXML
    private void test() throws IOException {
        Random random = new Random();
        int ranId = random.nextInt(ListwordContestString().size());
        wordVi.setText(ListwordContestString().get(ranId));

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        try {
//            save();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }

        try {
            test();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            createLabels(findWordEng(wordVi.getText()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            createButon(findWordEng(wordVi.getText()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}

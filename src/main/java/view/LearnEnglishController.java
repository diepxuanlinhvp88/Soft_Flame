package view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ResourceBundle;

public class LearnEnglishController implements Initializable {
    @FXML
    AnchorPane anchorParentLearn;
    @FXML
    AnchorPane  anchorPaneChild;
    Node node;

    public void Editcontroller() throws IOException {

        node = FXMLLoader.load(getClass().getResource("Edit.fxml"));
        anchorPaneChild.getChildren().setAll(node);
    }

    public void Findcontroller() throws IOException {
        Node node;
        node = FXMLLoader.load(getClass().getResource("find.fxml"));
        anchorPaneChild.getChildren().setAll(node);

    }
    public void Menu() throws IOException {
        Node node;
        node = FXMLLoader.load(getClass().getResource("controller.fxml"));
        anchorParentLearn.getChildren().setAll(node);

    }
    public void Translate() throws IOException{
        Node node;
        node = FXMLLoader.load(getClass().getResource("Translate.fxml"));
        anchorPaneChild.getChildren().setAll(node);

    }

    /**
     * anh them cái Fmxl game vào đi nhé em set action rồi đó, em tạo 1 anchorpane chứa 2 anchor pane nhỏ, 1 cái chứa các button, 1 cái chứa nội dung kích thước là 720 x 405.
     * @throws IOException
     */
    public void Game() throws IOException, InterruptedException {
//        Node node;
//        node = FXMLLoader.load(getClass().getResource("v.fxml"));
        executeWordleGame();
//        anchorPaneChild.getChildren().setAll(node);
    }

    private void executeWordleGame() throws InterruptedException, IOException {
        final String command = "java  --module-path \"D:\\javafx\\javafx-sdk-21.0.1\\lib\" --add-modules javafx.controls,javafx.fxml -jar \"D:\\java_code\\backup\\Soft_Flame\\Dictionary\\src\\main\\java\\view\\JavaFX-Wordle.jar\"";

        // Create process builder
        ProcessBuilder processBuilder = new ProcessBuilder();

        // Set the command
        processBuilder.command(command.split("\\s+"));

        // Redirect error stream to output stream
        processBuilder.redirectErrorStream(true);

        // Start the process
        Process process = processBuilder.start();

        // Read the output of the command
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Wait for the process to complete
        int exitCode = process.waitFor();

        System.out.println("Process exited with code: " + exitCode);
    }

    public void Exit() throws IOException{
        System.exit(0);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            Translate();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
//        try {
//            Findcontroller();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        Node node;
//        try {
//            node = FXMLLoader.load(getClass().getResource("find.fxml"));
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        anchorPaneChild.getChildren().setAll(node);

    }

}

package view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LearnEnglishController implements Initializable {
    @FXML
    AnchorPane anchorParentLearn;
    @FXML
    private AnchorPane  anchorPaneChild;

    public void Editcontroller() throws IOException {
        Node node;
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
        node = FXMLLoader.load(getClass().getResource("Controller.fxml"));
        anchorParentLearn.getChildren().setAll(node);

    }
    public void DichVanBan() throws IOException{

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            Findcontroller();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
//        Node node;
//        try {
//            node = FXMLLoader.load(getClass().getResource("find.fxml"));
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        anchorPaneChild.getChildren().setAll(node);

    }

}

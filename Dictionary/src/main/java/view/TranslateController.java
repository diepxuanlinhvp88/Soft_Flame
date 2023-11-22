package view;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;


import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class TranslateController implements Initializable {
    int cnt = 0;
    @FXML
    TextArea TextTarget;
    @FXML
    TextArea TextExplain;
    @FXML
    Label En;
    @FXML
    ImageView Anh;
    @FXML
    ImageView CamereIcon;
    @FXML
    AnchorPane pane;



    @FXML
    ImageView Viet;

    @FXML
    Label Vi;
    public void Swap(){
        Image Eng = new Image(getClass().getResource("image/16.jpg").toExternalForm());
        Image Vie = new Image(getClass().getResource("image/15.png").toExternalForm());
        cnt++;
        if(cnt % 2 == 0) {
            En.setText("en");
            Vi.setText("vi");
//          Anh.setImage(new Image(getClass().getResource("view/image/16.jpg").toExternalForm()));
//          Viet.setImage(new Image(getClass().getResource("view/image/15.png").toExternalForm()));
            Anh.setImage(Eng);
            Viet.setImage(Vie);
            System.out.println("anhviet");


        }
        else {
            En.setText("vi");
            Vi.setText("en");
//          Anh.setImage(new Image(getClass().getResource("/view/image/15.png").toExternalForm()));
//          Viet.setImage(new Image(getClass().getResource("/view/image/16.jpg").toExternalForm()));
            Anh.setImage(Vie);
            Viet.setImage(Eng);
            System.out.println("vietanh");
        }
//        TextTarget.textProperty().addListener(new ChangeListener<String>() {
//
//
//            @Override
//            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
//                TextExplain.setText(LoginController.tranapi.lookUp
//                        (TextTarget.getText(),"vi","en"));
//            }
//        });

    }
    public void tran(){
        TextExplain.setText(LoginController.tranapi.lookUp(TextTarget.getText(), Vi.getText(), En.getText()));



    }
    public void FindwithImage(){
        // Tạo một FileChooser
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Chọn ảnh");

        // Thêm bộ lọc để chỉ hiển thị các file hình ảnh
        FileChooser.ExtensionFilter imageFilter =
                new FileChooser.ExtensionFilter("Hình ảnh (*.png, *.jpg, *.jpeg, *.gif, *.bmp)", "*.png", "*.jpg", "*.jpeg", "*.gif", "*.bmp");
        fileChooser.getExtensionFilters().add(imageFilter);

        // Hiển thị hộp thoại chọn file và lấy đường dẫn của file được chọn


        File selectedFile = fileChooser.showOpenDialog(pane.getScene().getWindow());

        // Kiểm tra xem người dùng đã chọn file hay chưa
        if (selectedFile != null) {
            System.out.println("Đường dẫn hình ảnh: " + selectedFile.getAbsolutePath());

            // Gọi phương thức xử lý tìm kiếm hình ảnh với đường dẫn này
            TextTarget.setText(LoginController.tranapi.imageToText(selectedFile.getAbsolutePath()));
        }

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Swap();
        TextTarget.setWrapText(true);
        TextExplain.setWrapText(true);


    }
}
